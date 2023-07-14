package csmiles.webclientperf.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("client")
public class ClientController {

    private final URI serverScheme;

    private final WebClient webClient;

    public ClientController(
            @Value("http://${SERVER_SVC_SERVICE_HOST}:${SERVER_SVC_SERVICE_PORT}") URI serverScheme,
            WebClient webClient
    ) {
        this.serverScheme = serverScheme;
        this.webClient = webClient;
    }

    @GetMapping("retrieveSuccess")
    public Mono<String> retrieveSuccess() {
        return webClient.get()
                .uri(serverScheme + "/server/success")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("unpooled")
    public Mono<String> unpooled() {
        var webClient = WebClientFactory.unpooled(serverScheme.toString());
        return webClient.get()
                .uri(serverScheme + "/server/success")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("pooled")
    public Mono<String> pooled() {
        var webClient = WebClientFactory.pooled(serverScheme.toString());
        return webClient.get()
                .uri(serverScheme + "/server/success")
                .retrieve()
                .bodyToMono(String.class);
    }

}
