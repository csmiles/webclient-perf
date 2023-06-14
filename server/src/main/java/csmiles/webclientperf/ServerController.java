package csmiles.webclientperf;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("server")
public class ServerController {
    @GetMapping("success")
    public Mono<String> success() {
        return Mono.just("Hello world!");
    }
}
