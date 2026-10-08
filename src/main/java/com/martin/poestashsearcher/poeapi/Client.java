package com.martin.poestashsearcher.poeapi;

import java.net.URI;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.http.HttpCookie;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.reactive.client.ReactiveRequest;
import org.eclipse.jetty.reactive.client.ReactiveResponse;
import org.eclipse.jetty.reactive.client.ReactiveResponse.Result;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.martin.poestashsearcher.StashExportMain;

import io.reactivex.rxjava3.core.Flowable;

public class Client implements AutoCloseable {

    HttpClient client;

    String accountName;

    final static Logger log = LoggerFactory.getLogger(StashExportMain.class);

    public Client(String accountName, String poeSessionId) {

        HttpCookie poeSessionCookie = HttpCookie.build("POESESSID", poeSessionId, 0).build();

        client = new HttpClient();
        try {
            client.start();
            client.getHttpCookieStore().add(URI.create("https://www.pathofexile.com"), poeSessionCookie);
        } catch (Exception e) {
            throw new IllegalStateException("Error starting the http client", e);
        }

        this.accountName = accountName;
    }

    public Flowable<StashTab> getStashTab(int tabIndex) {
        log.info("Fetching tab {}", tabIndex);

        ReactiveRequest request = ReactiveRequest
                .newBuilder(client.newRequest("https://www.pathofexile.com").path("character-window/get-stash-items")
                        .param("accountName", accountName)
                        .param("realm", "pc")
                        .param("league", "Allflame")
                        .param("tabs", "1")
                        .param("tabIndex", Integer.toString(tabIndex)))
                .build();
        Publisher<Result<String>> publisher = request.response(ReactiveResponse.Content.asStringResult());

        return Flowable.fromPublisher(publisher)
                .map(response -> okContentOrMessage(response, "Error while fetching stash tab " + tabIndex))
                .map(StashTab::read);
    }

    public Flowable<Integer> getStashSize() {
        log.info("Fetching stash size");

        ReactiveRequest request = ReactiveRequest
                .newBuilder(client.newRequest("https://www.pathofexile.com").path("character-window/get-stash-items")
                        .param("accountName", accountName)
                        .param("realm", "pc")
                        .param("league", "Allflame")
                        .param("tabs", "1")
                        .param("tabIndex", "0"))
                .build();
        Publisher<Result<String>> publisher = request.response(ReactiveResponse.Content.asStringResult());

        return Flowable.fromPublisher(publisher)
                .map(response -> okContentOrMessage(response, "Error while fetching stash size"))
                .map(StashTab::read)
                .map(StashTab::getNumTabs);
    }

    private String okContentOrMessage(Result<String> response, String message) throws ClientHttpException {
        if (response.response().getStatus() == HttpStatus.OK_200) {
            return response.content();
        } else {
            throw new ClientHttpException(message + " : " + response.response().getStatus() + " - " + response.content());
        }
    }

    @Override
    public void close() throws Exception {
        client.close();
    }

}
