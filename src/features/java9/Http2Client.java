package features.java9;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class Http2Client {

    // detailed tutorial: https://www.baeldung.com/java-9-http-client
    // Make a GET request and process the response as a String
    public static void getURLSync(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder(new URI(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        processResponse(response);
    }

    // Make a POST request and write the response to a file
    public static void postURLSync(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder(new URI(url))
                .headers("Foo", "foovalue", "Bar", "barValue")
                .POST(HttpRequest.BodyPublishers.ofString("Some string"))
                .build();

        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get("fileXXXX.txt")));

        processResponse(response);
    }

    // Make an Asynchronous GET request and process the response as a String
    public static void getURLASync(String url) throws Exception {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest
                .newBuilder(new URI(url))
                .GET()
                .build();

        CompletableFuture<HttpResponse<String>> compFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Async request has been made...");
        while (!compFuture.isDone()) {
            System.out.println("Do something else while we wait...");
            /* if (someCondition) {
                compFuture.cancel(true);
                System.out.println("Async request has been cancelled");
            } */
        }

        System.out.println("Async request is done...");
        processResponse(compFuture.get());
    }

    // Process the response
    // Display the status code
    // All the header data
    // and finally the response body
    private static void processResponse(HttpResponse<?> response) {
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Headers:");

        HttpHeaders httpHeaders = response.headers();
        Map<String, List<String>> headers = httpHeaders.map();
        headers.forEach((k, v) -> System.out.println("\t" + k + ":" + v));

        System.out.println(response.body());
    }

}
