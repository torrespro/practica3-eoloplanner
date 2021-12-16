package es.codeurjc.users;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/topographicdetails")
public class TopographyController {

    private TopographyService topographicService;

    Random random = new Random();

    TopographyController(TopographyService userService) {
        this.topographicService = userService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TopographyDTO> createCity(@RequestBody Mono<TopographyDTO> city) {
        return city
            .map(this::toCity)
            .flatMap(topographicService::createCity)
            .map(this::toTopographyDTO);
    }

    @GetMapping("/")
    public Flux<TopographyDTO> getCities(@RequestParam(required = false) Optional<String> cityName) {
        return topographicService.getCities(cityName)
            .map(this::toTopographyDTO);
    }

    @GetMapping("/{city}")
    public Mono<TopographyDTO> getUser(@PathVariable String city) {
        try {
            TimeUnit.SECONDS.sleep(random.nextLong(4));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return topographicService.getCity(StringUtils.capitalize(city))
            .map(this::toTopographyDTO);
    }

    @DeleteMapping("/{city}")
    public Mono<TopographyDTO> deleteUser(@PathVariable String id) {
        return topographicService.deleteCity(id).map(this::toTopographyDTO);
    }

    private Topography toCity(TopographyDTO TopographyDTO) {
        return new Topography(TopographyDTO.id(), TopographyDTO.id(), TopographyDTO.landscape());
    }

    private TopographyDTO toTopographyDTO(Topography topography) {
        return new TopographyDTO(StringUtils.capitalize(topography.getCity()), topography.getLandscape());
    }

}
