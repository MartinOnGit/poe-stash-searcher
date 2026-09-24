package com.martin.poestashsearcher.poeapi;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;

public class Client {

    HttpClient client;
    
    public Client(){
        client = HttpClient.newHttpClient();
    }

    public CompletableFuture<String> getStashList() {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://dummy-json.mock.beeceptor.com/continents"))
            .GET()
            .build();
        return client.sendAsync(request, BodyHandlers.ofString()).thenApply(response -> response.body());
    }

}
