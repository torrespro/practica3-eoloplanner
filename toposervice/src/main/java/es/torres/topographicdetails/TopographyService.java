package es.torres.topographicdetails;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TopographyService {

    private TopographyRepository topographyRepository;

    TopographyService(TopographyRepository topographyRepository) {
        this.topographyRepository = topographyRepository;
    }

    public Mono<Topography> createCity(Topography city) {
        return topographyRepository.save(city);
    }

    public Flux<Topography> getCities(Optional<String> city) {
        return city
            .map(value -> topographyRepository.findByCityIgnoreCase(value))
            .orElseGet(() -> topographyRepository.findAll());
    }

    public Mono<Topography> getCity(String city) {
        return topographyRepository.findOne(Example.of(new Topography(null, city, null)))
            .switchIfEmpty(
                Mono.error(new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "City with name "+city+" not found")));
    }

    public Mono<Topography> deleteCity(String cityName) {
        Mono<Topography> deletedCity = topographyRepository.findOne(Example.of(new Topography(null, cityName, null)));
        return deletedCity.flatMap(city -> topographyRepository.deleteById(city.getId()).then(Mono.just(city)));
    }

    @PostConstruct
    public void init() {

        Random random_method = new Random();

        topographyRepository.deleteAll().block();
        String[] cities = {"Avilés", "Toledo", "Madrid", "Barcelona", "Jaca", "Andorra", "Valencia", "Sevilla", "Zaragoza", "Málaga", "Murcia", "Palma", "Bilbao", "Alicante", "Córdoba", "Valladolid", "Vigo", "Gijón", "Vitoria"};
        String[] topographyTypes = {"flat", "mountain"};
        ArrayList<Topography> topographies = new ArrayList<Topography>();
        for (int i = 0; i < cities.length; i++) {
            topographies.add(new Topography(cities[i], topographyTypes[random_method.nextInt(topographyTypes.length)]));
        }
        Topography[] topographiesArray = new Topography[topographies.size()];
        topographiesArray = topographies.toArray(topographiesArray);
        Flux<Topography> users = Flux.fromArray(topographiesArray);

        users
            .flatMap(this.topographyRepository::save)
            .blockLast();
    }
}
