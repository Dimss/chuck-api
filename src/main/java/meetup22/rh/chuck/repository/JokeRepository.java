package meetup22.rh.chuck.repository;

import meetup22.rh.chuck.model.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JokeRepository extends JpaRepository<Joke, Integer> {
//    @Query("SELECT j FROM Joke j where joke = :joke")
    @Query(nativeQuery=true, value="SELECT * FROM joke ORDER BY random() LIMIT 1")
//    Optional<Joke> findJokeByJoke(@Param("joke") String joke);
    Optional<Joke> findRandomJoke();
}
