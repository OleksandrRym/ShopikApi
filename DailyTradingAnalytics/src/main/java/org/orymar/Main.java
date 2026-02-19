package org.orymar;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) throws Exception {

        long after = Instant.now()
                .minus(7, ChronoUnit.DAYS)
                .getEpochSecond();

        String url = "https://www.strava.com/api/v3/athlete/activities"
                + "?after=" + after
                + "&per_page=50";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer ")
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status: " + response.statusCode());
        System.out.println("Body:");
        System.out.println(response.body());

    }
}