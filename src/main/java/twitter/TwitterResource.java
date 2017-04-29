package twitter;


import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import org.jnosql.artemis.document.DocumentRepository;
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

@Path("tweets")
public class TwitterResource {


    @Inject
    @Database(value = DatabaseType.DOCUMENT, provider = "mongoDb")
    private TweetRepository mongoDb;

    @Inject
    private TwitterService twitterService;

    @Database(value = DatabaseType.DOCUMENT, provider = "mongoDb")
    private DocumentRepository repository;

    @GET
    @Path("{hashtag}")
    public Response getTweetByHashtag(@PathParam("hashtag") String hashTag){
        try {
            final List<Status> tweets = twitterService.listenTweets(hashTag);
            for(Status status : tweets){
                Tweet tweet = new Tweet(status.getUser().getScreenName(), status.getText(), status.getCreatedAt());
                mongoDb.save(tweet);
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMongoDbTweets(){
        final DocumentQuery query = DocumentQuery.of("tweet");
        List<Tweet> tweets = repository.find(query);
        return Response.ok().build();
    }

}
