package twitter;


import org.jnosql.artemis.CrudRepository;

import java.util.Optional;

public interface TweetRepository extends CrudRepository<Tweet> {

    Optional<Tweet> findById(Long id);

}
