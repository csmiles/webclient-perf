package csmiles.webclientperf.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("client")
public class ClientController {

    private final WebClient webClient;

    public ClientController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("retrieveSuccess")
    public Mono<String> retrieveSuccess() {
        return webClient.get()
                .uri("http://localhost:8080/server/success")
                .retrieve()
                .bodyToMono(String.class);
    }

}
