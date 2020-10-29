package features.java9;

import java.util.concurrent.Flow;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

// Java 9 Flow API enables us to adopt Reactive Programming using just the JDK, not needing additional libraries
// such as RxJava or Project Reactor, amongst others.
// https://thepracticaldeveloper.com/reactive-programming-java-9-flow/
public class ReactiveStreams {

    public static class MagazineSubscriber implements Flow.Subscriber<Integer> {

        public static final String JACK = "Jack";
        public static final String PETE = "Pete";

        private final long sleepTime;
        private final String subscriberName;
        private Flow.Subscription subscription;
        private int nextMagazineExpected;
        private int totalRead;

        MagazineSubscriber(final long sleepTime, final String subscriberName) {
            this.sleepTime = sleepTime;
            this.subscriberName = subscriberName;
            this.nextMagazineExpected = 1;
            this.totalRead = 0;
        }

        @Override
        public void onSubscribe(final Flow.Subscription subscription) {
            this.subscription = subscription;
            subscription.request(1);
        }

        @Override
        public void onNext(final Integer magazineNumber) {
            if (magazineNumber != nextMagazineExpected) {
                IntStream.range(nextMagazineExpected, magazineNumber).forEach(
                        (msgNumber) ->
                                log("Oh no! I missed the magazine " + msgNumber)
                );
                // Catch up with the number to keep tracking missing ones
                nextMagazineExpected = magazineNumber;
            }
            log("Great! I got a new magazine: " + magazineNumber);
            takeSomeRest();
            nextMagazineExpected++;
            totalRead++;

            log("I'll get another magazine now, next one should be: " +
                    nextMagazineExpected);
            subscription.request(1);
        }

        @Override
        public void onError(final Throwable throwable) {
            log("Oops I got an error from the Publisher: " + throwable.getMessage());
        }

        @Override
        public void onComplete() {
            log("Finally! I completed the subscription, I got in total " +
                    totalRead + " magazines.");
        }

        private void log(final String logMessage) {
            System.out.println("<=========== [" + subscriberName + "] : " + logMessage);
        }

        public String getSubscriberName() {
            return subscriberName;
        }

        private void takeSomeRest() {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int NUMBER_OF_MAGAZINES = 20;

        ReactiveStreams app = new ReactiveStreams();

        System.out.println("\n\n### CASE 1: Subscribers are fast, buffer size is not so " +
                "important in this case.");
        app.magazineDeliveryExample(100L, 100L, 8);

        System.out.println("\n\n### CASE 2: A slow subscriber, but a good enough buffer " +
                "size on the publisher's side to keep all items until they're picked up");
        app.magazineDeliveryExample(1000L, 3000L, NUMBER_OF_MAGAZINES);

        System.out.println("\n\n### CASE 3: A slow subscriber, and a very limited buffer " +
                "size on the publisher's side so it's important to keep the slow " +
                "subscriber under control");
        app.magazineDeliveryExample(1000L, 3000L, 8);
    }

    void magazineDeliveryExample(final long sleepTimeJack,
                                 final long sleepTimePete,
                                 final int maxStorageInPO) throws Exception {
        long MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE = 2;

        final SubmissionPublisher<Integer> publisher =
                new SubmissionPublisher<>(ForkJoinPool.commonPool(), maxStorageInPO);

        final MagazineSubscriber jack = new MagazineSubscriber(
                sleepTimeJack,
                MagazineSubscriber.JACK
        );
        final MagazineSubscriber pete = new MagazineSubscriber(
                sleepTimePete,
                MagazineSubscriber.PETE
        );

        publisher.subscribe(jack);
        publisher.subscribe(pete);

        System.out.println("Printing 20 magazines per subscriber, with room in publisher for "
                + maxStorageInPO + ". They have " + MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE +
                " seconds to consume each magazine.");
        IntStream.rangeClosed(1, 20).forEach((number) -> {
            System.out.println("Offering magazine " + number + " to consumers");
            final int lag = publisher.offer(
                    number,
                    MAX_SECONDS_TO_KEEP_IT_WHEN_NO_SPACE,
                    TimeUnit.SECONDS,
                    (subscriber, msg) -> {
                        subscriber.onError(
                                new RuntimeException("Hey " + ((MagazineSubscriber) subscriber)
                                        .getSubscriberName() + "! You are too slow getting magazines" +
                                        " and we don't have more space for them! " +
                                        "I'll drop your magazine: " + msg));
                        return false; // don't retry, we don't believe in second opportunities
                    });
            if (lag < 0) {
                log("Dropping " + -lag + " magazines");
            } else {
                log("The slowest consumer has " + lag +
                        " magazines in total to be picked up");
            }
        });

        // Blocks until all subscribers are done (this part could be improved
        // with latches, but this way we keep it simple)
        while (publisher.estimateMaximumLag() > 0) {
            Thread.sleep(500L);
        }

        // Closes the publisher, calling the onComplete() method on every subscriber
        publisher.close();
        // give some time to the slowest consumer to wake up and notice
        // that it's completed
        Thread.sleep(Math.max(sleepTimeJack, sleepTimePete));
    }

    private static void log(final String message) {
        System.out.println("===========> " + message);
    }

}
