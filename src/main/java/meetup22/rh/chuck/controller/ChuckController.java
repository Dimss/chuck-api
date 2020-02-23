package meetup22.rh.chuck.controller;

import meetup22.rh.chuck.model.Joke;
import meetup22.rh.chuck.payload.JokePayload;
import meetup22.rh.chuck.payload.ResponsePayload;
import meetup22.rh.chuck.repository.JokeRepository;
import meetup22.rh.chuck.service.ChuckClient;
import meetup22.rh.chuck.service.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping("/v1")
public class ChuckController {
    @Autowired
    ResponsePayload responsePayload;

    @Autowired
    JokeRepository jokeRepository;

    @Autowired
    JokePayload jokePayload;

    @Value("${app.joke.color}")
    private String jokeColor;

//    @Autowired
//    ChuckClient chuckClient;

    @Autowired
    Translator translator;

    @GetMapping("/ping")
    public ResponseEntity ping() {

        responsePayload.setData(new HashMap<String, String>() {{
            put("status", "up");
        }});
        return ResponseEntity.ok().body(responsePayload.getJsonPayload());
    }


    @GetMapping("/joke")
    @CrossOrigin(origins = "*")
    public ResponseEntity joke() {
        int jokeId = new Random().nextInt(1001);

        Joke j = jokeRepository.findRandomJoke().orElse(null);
        jokePayload.setJoke(j.getJoke());
        jokePayload.setColor(jokeColor);
//        #ff7700
//        responsePayload.setData(this.chuckClient.getJoke());
//        responsePayload.setData(j).getJsonPayload()
//        responsePayload.setData(j);
        return ResponseEntity.ok().body(responsePayload.setData(jokePayload).getJsonPayload());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/translate/{text}", method = RequestMethod.GET)
    public ResponseEntity translate(@PathVariable("text") String text) {
        responsePayload.setData(translator.translate(text));
        return ResponseEntity.ok().body(responsePayload.getJsonPayload());
    }
}
