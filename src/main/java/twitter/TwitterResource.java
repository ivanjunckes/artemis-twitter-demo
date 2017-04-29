package twitter;


import org.jnosql.artemis.Database;
import org.jnosql.artemis.DatabaseType;
import twitter4j.Status;
import twitter4j.TwitterException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("tweets")
public class TwitterResource {


    @Inject
    @Database(value = DatabaseType.DOCUMENT, provider = "mongoDb")
    private TweetRepository mongoDb;

    @Inject
    private TwitterService twitterService;

    @GET
    public void getTweets(@PathParam("hashtag") String hashTag){
        try {
            final List<Status> tweets = twitterService.listenTweets(hashTag);
            for(Status tweet : tweets){
                System.out.println(tweet);
            }
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

}
