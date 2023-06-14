package csmiles.webclientperf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/spacex")
public class SpaceXController {

    private final WebClient webClient;

    public SpaceXController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("launches")
    public Flux<Launch> getLaunches() {
        return webClient.get()
                .uri("https://api.spacexdata.com/v5/launches")
                .retrieve()
                .bodyToFlux(Launch.class);
    }

}
