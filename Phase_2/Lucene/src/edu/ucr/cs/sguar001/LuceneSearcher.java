package edu.ucr.cs.sguar001;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.json.JsonArray;
import javax.json.JsonObject;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;

/**
 * Lucene simple demo. Based on:
 * https://lucene.apache.org/core/7_3_0/core/overview-summary.html#overview.description
 */
public class LuceneSearcher {
	
    public static void main(String[] args) throws IOException, ParseException {
    	Analyzer analyzer = new StandardAnalyzer();
    	
//        // To store an index on disk, use this instead:
		final String INDEX_DIRECTORY = "/Users/guas5/eclipse-workspace/Lucene/src/edu/ucr/cs/sguar001/Index";
//		
        Directory directory = FSDirectory.open(Paths.get(INDEX_DIRECTORY));
//        
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter indexWriter = new IndexWriter(directory, config);
        
        File dir = new File("/Users/guas5/eclipse-workspace/Lucene/src/edu/ucr/cs/sguar001/JSON");
        
        for (File file : dir.listFiles()) {
        	
        	  ReadJsonArray rjObject = new ReadJsonArray();
//          
	          ArrayList<String> jsonUserNames = rjObject.getUserNames(file);
	
	          ArrayList<String> jsonNames = rjObject.getNames(file);
	
	          ArrayList<String> jsonProfilePics = rjObject.getProfilePics(file);
	          
	          ArrayList<String> jsonTweetText = rjObject.getTweetText(file);
	  //
	          ArrayList<String> jsonTweetPlaces = rjObject.getPlaces(file);
	
	          ArrayList<Double[]> jsonTweetCoordinates = rjObject.getCoordinates(file);
	          
	          ArrayList<String> jsonTweetURLs = rjObject.getURLs(file);
	
	          ArrayList<String> jsonTweetSources = rjObject.getSources(file);
	  //
	          ArrayList<String> jsonTweetTimeCreation = rjObject.getTimeCreated(file);
	          
	          ArrayList<Integer[]> jsonTweetStats = rjObject.getTweetStats(file);
	          
	//        ArrayList<JsonArray> jsonTweetHashTags = rjObject.getHashTags("/Users/guas5/eclipse-workspace/Lucene/src/edu/ucr/cs/sguar001/geo-tweets-US.json");
	          
	          
	          for(int i = 0; i < jsonUserNames.size(); i++) {
	          	Document doc = new Document();
	          	doc.add(new Field("username", jsonUserNames.get(i), TextField.TYPE_STORED));
	          	doc.add(new Field("name", jsonNames.get(i), TextField.TYPE_STORED));
	          	doc.add(new Field("profile_pic", jsonProfilePics.get(i), TextField.TYPE_STORED));
	          	doc.add(new Field("text", jsonTweetText.get(i), TextField.TYPE_STORED));
	          	doc.add(new Field("place", jsonTweetPlaces.get(i), TextField.TYPE_STORED));
	          	doc.add(new Field("latitude", jsonTweetCoordinates.get(i)[0].toString(), TextField.TYPE_STORED));
	          	doc.add(new Field("longitude", jsonTweetCoordinates.get(i)[1].toString(), TextField.TYPE_STORED));
	          	doc.add(new Field("source", jsonTweetSources.get(i), TextField.TYPE_STORED));
	          	doc.add(new Field("time", jsonTweetTimeCreation.get(i), TextField.TYPE_STORED));
	          	doc.add(new Field("retweets", jsonTweetStats.get(i)[0].toString(), TextField.TYPE_STORED));
	          	doc.add(new Field("favorites", jsonTweetStats.get(i)[1].toString(), TextField.TYPE_STORED));
	          	
	          	//add each hashtag included in tweet to document
	//              for (int i1 = 0; i1 < jsonTweetHashTags.size(); i1++) {
	//              	if(!jsonTweetHashTags.get(i1).isEmpty())
	//              	{
	//              		for(int j = 0; j < jsonTweetHashTags.get(i1).size(); j++) {
	//                      	doc.add(new Field("hashtag", jsonTweetHashTags.get(i1).get(j).asJsonObject().getString("text"), TextField.TYPE_STORED));
	//              		}
	//              	}
	//              }
	              
	          	doc.add(new Field("url", jsonTweetURLs.get(i), TextField.TYPE_STORED));
	          	indexWriter.addDocument(doc);
	          }
        	
        }
        
//        

        
        indexWriter.close();

//        // Now search the index:
//        DirectoryReader indexReader = DirectoryReader.open(directory);
//        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
//        
//        // Variables to be inputted into MultiField Query Parser
//        String[] fields = {"username", "text", "place", "source", "time", "hashtag", "url"};
//        Map<String, Float> boosts = new HashMap<>();
//        boosts.put(fields[0], 0.4f);
//        boosts.put(fields[1], 1.0f);
//        boosts.put(fields[2], 0.5f);
//        boosts.put(fields[3], 0.3f);
//        boosts.put(fields[4], 0.1f);
//        boosts.put(fields[5], 0.7f);
//        boosts.put(fields[6], 0.2f);
//        
//        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer, boosts);
//        Query query = parser.parse("please");
//
//        int topHitCount = 100;
//        ScoreDoc[] hits = indexSearcher.search(query, topHitCount).scoreDocs;
//        
////        // Iterate through the results:
////        for (int rank = 0; rank < hits.length; ++rank) {
////            Document hitDoc = indexSearcher.doc(hits[rank].doc);
////            System.out.println((rank + 1) + " (score:" + hits[rank].score + ") --> " +
////                               hitDoc.get("text"));
//////            System.out.println(indexSearcher.explain(query, hits[rank].doc));
////        }
//        indexReader.close();
//        directory.close();
	}
}