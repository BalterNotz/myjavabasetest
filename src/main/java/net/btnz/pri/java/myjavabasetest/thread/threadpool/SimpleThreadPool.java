package net.btnz.pri.java.myjavabasetest.thread.threadpool;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {

    public static void main(String... args) throws InterruptedException {
        WebClient webClient = WebClient.create();
        ExecutorService services = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 50; i++) {
            services.submit(() -> {
                Mono<String> result = webClient.post().uri("http://localhost:8080/datadelivery/echo")
                        .contentType(MediaType.APPLICATION_JSON).body(Mono.just(Thread.currentThread().toString()), String.class)
                        .retrieve().bodyToMono(String.class);
                System.out.println(result.block(Duration.ofMillis(1000)));
            });
        }
        services.shutdown(); //线程池不再接收新任务
        while (!services.isTerminated()) {
            System.out.println("子线程还未执行完毕");
            Thread.sleep(1000);
        }
        System.out.println("主线程退出");
    }
}
