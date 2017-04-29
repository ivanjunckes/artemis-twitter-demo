package twitter;


import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.jnosql.diana.api.document.DocumentQuery;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("tweets")
public class TwitterResource {


    @Inject
    @Database(value = DatabaseType.DOCUMENT, provider = "couchDb")
    private TweetRepository couchBaseDb;

    @Inject
    @Database(value = DatabaseType.DOCUMENT, provider = "mongoDb")
    private TweetRepository mongoDb;

    @Inject
    private TwitterService twitterService;

    @GET
    @Path("{hashtag}")
    public Response getTweetsByHashtag(@PathParam("hashtag") String hashTag) {
        try {
            final List<Status> tweets = twitterService.readTweets(hashTag);
            for (Status status : tweets) {
                if (status.getText().contains("mongodb")) {
                    handleTweet(status, mongoDb);
                } else if (status.getText().contains("couchdb")) {
                    handleTweet(status, couchBaseDb);
                }
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }

    private void handleTweet(Status status, TweetRepository repository) {
        final Optional<Tweet> byId = repository.findById(status.getId());
        if (!byId.isPresent()) {
            Tweet tweet = new Tweet(String.valueOf(status.getId()), status.getUser().getScreenName(), status.getText(), status.getCreatedAt().toString());
            repository.save(tweet);
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMongoDbTweets() {
        final DocumentQuery tweets = DocumentQuery.of("tweet");
        return Response.ok().build();
    }
}
