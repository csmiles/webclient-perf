package csmiles.webclientperf.client;

import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;

public class WebClientFactory {

    public static WebClient unpooled(String baseUrl) {
        var httpClient = HttpClient.newConnection();
        return createWebClient(baseUrl, httpClient);
    }

    public static WebClient pooled(String baseUrl) {
        var httpClient = HttpClient.create(ConnectionProvider.create("foo", 500));
        return createWebClient(baseUrl, httpClient);
    }

    private static WebClient createWebClient(String baseUrl, HttpClient httpClient) {
        httpClient.responseTimeout(Duration.ofSeconds(30));

        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
        return WebClient.builder()
                .baseUrl(baseUrl)
                .clientConnector(connector)
                .exchangeStrategies(
                        ExchangeStrategies.builder()
                                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024))
                                .build())
                .build();
    }

}
