package meetup22.rh.chuck;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import meetup22.rh.chuck.model.Joke;
import meetup22.rh.chuck.repository.JokeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                File resource = new ClassPathResource("data/jokes.json").getFile();
                Gson gson = new Gson();
                Joke[] jokes = gson.fromJson(new FileReader(resource), Joke[].class);
                for (Joke current : jokes) {
                    jokeRepository.save(current);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
