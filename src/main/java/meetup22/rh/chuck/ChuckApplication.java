package meetup22.rh.chuck;

import com.google.gson.Gson;
import meetup22.rh.chuck.model.Joke;
import meetup22.rh.chuck.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.stream.Collectors;

@SpringBootApplication
public class ChuckApplication {
    @Autowired
    JokeRepository jokeRepository;

    public static void main(String[] args) {
        SpringApplication.run(ChuckApplication.class, args);
    }

    @PostConstruct
    public void init() {
        Joke j = jokeRepository.findJokeByJoke("Chuck Norris can change the font of a dot.").orElse(null);
        if (j == null) {
            try {
                InputStream resource = new ClassPathResource("data/jokes.json").getInputStream();
                Gson gson = new Gson();
                String result = new BufferedReader(new InputStreamReader(resource))
                        .lines().collect(Collectors.joining("\n"));
                Joke[] jokes = gson.fromJson(result, Joke[].class);
                for (Joke current : jokes) {
                    jokeRepository.save(current);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
