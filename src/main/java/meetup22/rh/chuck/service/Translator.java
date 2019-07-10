package meetup22.rh.chuck.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class Translator {
    @Value("${app.translator.api.key}")
    private String apiKey;
    @Value("${app.translator.api.url}")
    private String api;
    private OkHttpClient client;
    private JsonParser parser;

    public Translator() {
        this.client = new OkHttpClient();
        this.parser = new JsonParser();
    }

    public String translate(String text) {
        HttpUrl.Builder httpUrlBuilder = HttpUrl.parse(api).newBuilder();
        httpUrlBuilder.addQueryParameter("key", apiKey);
        httpUrlBuilder.addQueryParameter("lang", "en-he");
        httpUrlBuilder.addQueryParameter("text", text);
        Request request = new Request.Builder().url(httpUrlBuilder.build()).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            JsonArray ja = parser.parse(response.body().string()).getAsJsonObject().get("text").getAsJsonArray();
            String translatedText = "";
            for (JsonElement e : ja) translatedText += e.getAsString();
            return translatedText;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
