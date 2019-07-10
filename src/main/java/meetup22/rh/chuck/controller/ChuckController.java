package meetup22.rh.chuck.controller;

import meetup22.rh.chuck.payload.ResponsePayload;
import meetup22.rh.chuck.service.ChuckClient;
import meetup22.rh.chuck.service.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@RestController
@RequestMapping("/v1")
public class ChuckController {
    @Autowired
    ResponsePayload responsePayload;

    @Autowired
    ChuckClient chuckClient;

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
        responsePayload.setData(this.chuckClient.getJoke());
        return ResponseEntity.ok().body(responsePayload.getJsonPayload());
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/translate/{text}", method = RequestMethod.GET)
    public ResponseEntity translate(@PathVariable("text") String text) {
        responsePayload.setData(translator.translate(text));
        return ResponseEntity.ok().body(responsePayload.getJsonPayload());
    }
}
