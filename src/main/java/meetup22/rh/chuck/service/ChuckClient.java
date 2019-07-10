package meetup22.rh.chuck.service;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ChuckClient {
    Logger logger = LoggerFactory.getLogger(ChuckClient.class);
    @Value("${app.chuck.api.url}")
    private String chuckApi;
    private OkHttpClient client;
    private JsonParser parser;

    public ChuckClient() {
        this.client = new OkHttpClient();
        this.parser = new JsonParser();
    }

    public String getJoke() throws RuntimeException {
        try {
            logger.info("Fetching random joke");
            Request request = new Request.Builder().url(this.chuckApi).build();
            Response response = client.newCall(request).execute();
            JsonObject jo = parser.parse(response.body().string()).getAsJsonObject();
            return jo.get("value").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
