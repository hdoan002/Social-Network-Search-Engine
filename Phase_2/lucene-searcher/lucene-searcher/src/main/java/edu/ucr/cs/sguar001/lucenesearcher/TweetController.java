package edu.ucr.cs.sguar001.lucenesearcher;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
//import org.apache.lucene.document.Document;
//import org.apache.lucene.index.DirectoryReader;
//import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
//import org.apache.lucene.queryparser.classic.ParseException;
//import org.apache.lucene.search.IndexSearcher;
//import org.apache.lucene.search.Query;
//import org.apache.lucene.search.ScoreDoc;
//import org.apache.lucene.store.Directory;
//import org.apache.lucene.store.FSDirectory;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TweetController {
	
    static List<Tweet> tweets;
	
	public static ScoreDoc[] createTweets(String q) throws ParseException, IOException {
		
		Analyzer analyzer = new StandardAnalyzer();
        // To store an index on disk, use this instead:
		final String INDEX_DIRECTORY = "/Users/guas5/eclipse-workspace/Lucene/src/edu/ucr/cs/sguar001/Index";
				
        Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
		
		DirectoryReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        
        // Variables to be inputted into MultiField Query Parser
//        String[] fields = {"username", "text", "place", "source", "time", "hashtag", "url"};
//        URL here should be link title?
        
//        TODO add more fields and their respective boosts?
//        boost time more for freshness?
        String[] fields = {"username", "text", "place", "time", "hashtag", "url"};
        Map<String, Float> boosts = new HashMap<>();
        boosts.put(fields[0], 0.4f);
        boosts.put(fields[1], 1.0f);
        boosts.put(fields[2], 0.5f);
        boosts.put(fields[3], 0.3f);
        boosts.put(fields[4], 0.1f);
        boosts.put(fields[5], 0.7f);
//        boosts.put(fields[6], 0.2f);
        
        // Parse a simple query that searches for "text":
        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer, boosts);
        Query query = parser.parse(q);
        
        int topHitCount = 100;
        ScoreDoc[] hits = indexSearcher.search(query, topHitCount).scoreDocs;
        
        tweets = new ArrayList<>();
        
        // Iterate through the results:
        for (int rank = 0; rank < hits.length; ++rank) {
            Document hitDoc = indexSearcher.doc(hits[rank].doc);
            
            String urlTitle = hitDoc.get("url");
            String body = hitDoc.get("text");
            String username = hitDoc.get("username");
            String name = hitDoc.get("name");
            String profilePic = hitDoc.get("profile_pic");
            String place = hitDoc.get("place");
            String[] coordinates = new String[] {hitDoc.get("latitude"), hitDoc.get("longitude")};
            String timeCreated = hitDoc.get("time");
            String[] tweetStats = new String[] {hitDoc.get("retweets"), hitDoc.get("favorites")};
            String source = hitDoc.get("source");
            
            tweets.add(new Tweet(urlTitle, body, username, name, profilePic, place, coordinates, 
            		timeCreated, tweetStats, source));
        }
        indexReader.close();
        directory.close();
        
		return hits;
	}
	
    //Add different search functions here? (i.e. for different buttons)
    @GetMapping("/tweets")
    public List<Tweet> searchTweets(
            @RequestParam(required=false, defaultValue="") String query) {
        if (query.isEmpty())
        {
        	tweets.clear();
            return tweets;
        }
        	

        try {
			ScoreDoc[] hits = createTweets(query);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        List<Tweet> matches = new ArrayList<>();
        for (Tweet tweet: tweets) {
//            if (tweet.body.contains(query) || tweet.username.contains(query) || tweet.urlTitle.contains(query))
                matches.add(tweet);
        }
        return matches;
    }
}