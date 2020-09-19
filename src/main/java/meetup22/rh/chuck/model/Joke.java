package meetup22.rh.chuck.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
//@Table(name = "joke", uniqueConstraints = {
//        @UniqueConstraint(columnNames = {
//                "joke"
//        })
//})
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String joke;

    public Joke() {

    }

    public Joke(@NotBlank String joke) {
        this.joke = joke;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
