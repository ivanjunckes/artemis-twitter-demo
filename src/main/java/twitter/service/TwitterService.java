package twitter.service;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.List;

public class TwitterService {

    public static void main(String[] args) throws TwitterException {
        TwitterService listener = new TwitterService();
        System.out.println(listener.listenTweets("#jnosql"));
    }

    public List<Status> listenTweets(String hashTag) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        Query query = new Query(hashTag);
        QueryResult result = twitter.search(query);
        return result.getTweets();
    }
}
