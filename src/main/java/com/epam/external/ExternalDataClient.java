package com.epam.external;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ExternalDataClient {

    public String callGoogle(String url) throws Exception {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest build = HttpRequest.newBuilder().uri(URI.create(url)).build();

        HttpResponse<InputStream> send = client.send(build, HttpResponse.BodyHandlers.ofInputStream());

        String s = IOUtils.toString(send.body(), StandardCharsets.UTF_8);

        return s;
    }
}
