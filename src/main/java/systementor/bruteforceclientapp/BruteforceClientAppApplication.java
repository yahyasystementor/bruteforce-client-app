package systementor.bruteforceclientapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

@SpringBootApplication
public class BruteforceClientAppApplication implements CommandLineRunner {
    private final RestClient restClient = RestClient.create();

    public static void main(String[] args) {
        SpringApplication.run(BruteforceClientAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int maxRequests = 25;
                for (int i = 0; i < maxRequests; i++) {
                    try {
                        String response = restClient.get()
                                .uri("http://localhost:8080/work")
                                .retrieve()
                                .body(String.class);
                        System.out.println(response);

                    } catch (RestClientResponseException e){
                        System.out.println(e.getResponseBodyAsString());
                    }
                    Thread.sleep(300);
                }

    }

}
