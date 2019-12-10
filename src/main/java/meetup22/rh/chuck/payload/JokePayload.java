package meetup22.rh.chuck.payload;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class JokePayload {
    private String joke;
    private String color;

    public JokePayload() {
    }

    public String getJoke() {
        return joke;
    }

    public String getColor() {
        return color;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
