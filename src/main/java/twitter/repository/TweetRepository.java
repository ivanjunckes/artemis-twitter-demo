package twitter.repository;


import org.jnosql.artemis.CrudRepository;
import twitter.model.Tweet;

import java.util.Optional;

public interface TweetRepository extends CrudRepository<Tweet> {

    Optional<Tweet> findById(Long id);
}
