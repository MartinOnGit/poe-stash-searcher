package com.martin.poestashsearcher.poeapi;

import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

public class Client {

    HttpClient client;

    String accountName;
    
    public Client(String accountName, String poeSessionId){

        HttpCookie poeSessionCookie = new HttpCookie("POESESSID", poeSessionId);
        poeSessionCookie.setPath("/");
        poeSessionCookie.setVersion(0);

        CookieManager cookieManager = new CookieManager();
        cookieManager.getCookieStore().add(URI.create("https://www.pathofexile.com"), poeSessionCookie);

        client = HttpClient.newBuilder()
            .cookieHandler(cookieManager)
            .build();

        this.accountName = accountName;
    }

    public CompletableFuture<StashTab> getStashTab(int tabIndex) {
        StringBuilder uriBuilder = new StringBuilder();
        uriBuilder
            .append("https://www.pathofexile.com/character-window/get-stash-items?accountName=")
            .append(URLEncoder.encode(accountName, StandardCharsets.UTF_8))
            .append("&realm=pc&league=SSF+Allflame&tabs=1&tabIndex=")
            .append(tabIndex);
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(uriBuilder.toString()))
            .header("Accept", "application/json")
            .GET()
            .build();
        return client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(StashTab::read);
    }

}
