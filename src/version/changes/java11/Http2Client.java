package version.changes.java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Flow;
import java.util.stream.Stream;

import static java.net.http.HttpClient.Version.HTTP_2;
import static java.util.stream.Collectors.toList;

public class Http2Client {
    private static final List<URI> URLS = Stream.of(
        "https://en.wikipedia.org/wiki/List_of_compositions_by_Franz_Schubert",
        "https://en.wikipedia.org/wiki/2018_in_American_television",
        "https://en.wikipedia.org/wiki/List_of_compositions_by_Johann_Sebastian_Bach",
        "https://en.wikipedia.org/wiki/List_of_Australian_treaties",
        "https://en.wikipedia.org/wiki/2016%E2%80%9317_Coupe_de_France_Preliminary_Rounds",
        "https://en.wikipedia.org/wiki/Timeline_of_the_war_in_Donbass_(April%E2%80%93June_2018)",
        "https://en.wikipedia.org/wiki/List_of_giant_squid_specimens_and_sightings",
        "https://en.wikipedia.org/wiki/List_of_members_of_the_Lok_Sabha_(1952%E2%80%93present)",
        "https://en.wikipedia.org/wiki/1919_New_Year_Honours",
        "https://en.wikipedia.org/wiki/List_of_International_Organization_for_Standardization_standards"
    ).map(URI::create).collect(toList());

    private static final String SEARCH_TERM = "Foo";

    public static void main(String[] args) throws IOException, InterruptedException {
        // for more info: https://blog.codefx.org/java/http-2-api-tutorial/
        // and: https://blog.codefx.org/java/reactive-http-2-requests-responses/

        // Besides the HTTP version, connection timeout, and redirect policy, you can also configure the proxy,
        // SSL context and parameters, the authenticator, and cookie handler.
        HttpClient client = HttpClient.newBuilder()
            // just to show off; HTTP/2 is the default
            .version(HTTP_2)
            .connectTimeout(Duration.ofSeconds(5))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

        // GET
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create("http://codefx.org"))
            .header("Accept-Language", "en-US,en;q=0.5")
            .build();

        // If you send anything else but a GET, you need to include a BodyPublisher when configuring the HTTP method:
        // you can get instances of it from BodyPublishers – depending in what form your body comes, you can call these (and a few more) static methods on it:
        // ofByteArray(byte[])
        // ofFile(Path)
        // ofString(String)
        // ofInputStream(Supplier<InputStream>)
        HttpRequest.BodyPublisher requestBody = HttpRequest.BodyPublishers.ofString("{ request body }");
        HttpRequest request2 = HttpRequest.newBuilder()
            .POST(requestBody)
            .uri(URI.create("http://codefx.org"))
            .build();

        // HttpResponse<T>.body() returns a T
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String responseBody = response.body();
        System.out.println(responseBody);

        // Asynchronous HTTP Request Handling
        CompletableFuture[] futures = URLS.stream()
            .map(url -> asyncSearch(client, url, SEARCH_TERM))
            .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
    }

    private static CompletableFuture<Void> asyncSearch(HttpClient client, URI url, String term) {
        HttpRequest request = HttpRequest.newBuilder(url).GET().build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenApply(body -> body.contains(term))
            .exceptionally(error -> false)
            .thenAccept(found -> System.out.println("Completed " + url + " / found: " + found));
    }

    private static CompletableFuture<Void> reactiveSearch(HttpClient client, URI url, String term) {
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(url)
            .build();
        StringFinder finder = new StringFinder(term);
        client.sendAsync(request, HttpResponse.BodyHandlers.fromLineSubscriber(finder))
            .exceptionally(ex -> {
                finder.onError(ex);
                return null;
            });
        return finder
            .found()
            .exceptionally(error -> false)
            .thenAccept(found -> System.out.println(url + ":" + found));
    }

    private static class StringFinder implements Flow.Subscriber<String> {
        private final String term;
        private Flow.Subscription subscription;
        private final CompletableFuture<Boolean> found = new CompletableFuture<>();

        private StringFinder(String term) {
            this.term = term;
        }

        @Override
        public void onSubscribe(Flow.Subscription subscription) {
            this.subscription = subscription;
            this.subscription.request(1);
        }

        @Override
        public void onNext(String line) {
            if (!found.isDone() && line.contains(term)) {
                found.complete(true);
            }
            subscription.request(1);
        }

        @Override
        public void onError(Throwable ex) {
            found.completeExceptionally(ex);
        }

        @Override
        public void onComplete() {
            found.complete(false);
        }

        public CompletableFuture<Boolean> found() {
            return found;
        }
    }

}
