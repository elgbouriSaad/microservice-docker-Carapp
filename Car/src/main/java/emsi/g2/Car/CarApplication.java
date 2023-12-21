package emsi.g2.Car;

import emsi.g2.Car.entities.Car;
import emsi.g2.Car.repositories.CarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CarApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(5000);
		requestFactory.setReadTimeout(5000);
		restTemplate.setRequestFactory(requestFactory);

		return restTemplate;
	}

	@Bean
	CommandLineRunner initializeH2Database(CarRepository carRepository){
		return args -> {
			carRepository.save(new Car(Long.parseLong("1"), "Ford", "2022","123B", Long.parseLong("1")));
			carRepository.save(new Car(Long.parseLong("2"), "Mercedes", "2021","123C", Long.parseLong("2")));
			carRepository.save(new Car(Long.parseLong("3"), "BMW", "2020","123D", Long.parseLong("3")));
		};
	}
}
