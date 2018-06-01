package edu.ucr.cs.sguar001;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

import org.jsoup.Jsoup;

public class ReadJsonArray{
	
	private ArrayList<String> userNameList;
	private ArrayList<String> tweetTextList;
	private ArrayList<String> placeList;
	private ArrayList<String> sourceList;
	private ArrayList<String> timeCreated;
	private ArrayList<JsonArray> hashTagList;
	private ArrayList<String> urlList;
	private ArrayList<String> nameList;
	private ArrayList<String> profilePicList;
//	for [retweets,favorites]
	private ArrayList<Integer[]> statsList;
	private ArrayList<Double[]> coordinatesList;
	
    //Class Default Constructor
    public ReadJsonArray() {
    	this.userNameList = new ArrayList<String>(0);
    	this.tweetTextList = new ArrayList<String>(0);
    	this.placeList = new ArrayList<String>(0);
    	this.sourceList = new ArrayList<String>(0);
    	this.timeCreated = new ArrayList<String>(0);
    	this.hashTagList = new ArrayList<JsonArray>(0);
    	this.urlList = new ArrayList<String>(0);
    	this.nameList = new ArrayList<String>(0);
    	this.profilePicList = new ArrayList<String>(0);
    	this.statsList = new ArrayList<Integer[]>(0);
    	this.coordinatesList = new ArrayList<Double[]>(0);
    }	 
    
    //NEED
    public ArrayList<String> getUserNames(File file) {
 
    	this.userNameList = new ArrayList<String>(0);
    	
        try {
        	InputStream is = new FileInputStream(file);
 
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
 
            // Get the JsonArray structure from JsonReader.
            JsonArray jsonArray = reader.readArray();
            reader.close();
            
            int size = jsonArray.size();
            
//            Set ArrayList object to size of array
            this.userNameList = new ArrayList<String>(size);
            
            for (JsonValue value : jsonArray) {
            	JsonObject obj = value.asJsonObject();
//              System.out.println(obj.getString("text"));
//              System.out.println(obj.getJsonObject("user").getJsonNumber("id"));
//              System.out.println(obj.getJsonObject("user").getString("screen_name"));
            	String userName = obj.getJsonObject("user").getString("screen_name");
            	this.userNameList.add(userName);
            }
            
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
                
		return userNameList;
        
    }
    
    //NEED
    public ArrayList<String> getNames(File file) {
    	 
    	this.nameList = new ArrayList<String>(0);
    	
        try {
        	InputStream is = new FileInputStream(file);
 
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
 
            // Get the JsonArray structure from JsonReader.
            JsonArray jsonArray = reader.readArray();
            reader.close();
            
            int size = jsonArray.size();
            
//            Set ArrayList object to size of array
            this.nameList = new ArrayList<String>(size);
            
            for (JsonValue value : jsonArray) {
            	JsonObject obj = value.asJsonObject();
            	String name = obj.getJsonObject("user").getString("name");
            	this.nameList.add(name);
            }
            
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
                
		return nameList;
        
    }

    //NEED
    public ArrayList<String> getProfilePics(File file) {
    	 
    	this.profilePicList = new ArrayList<String>(0);
    	
        try {
        	InputStream is = new FileInputStream(file);
 
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
 
            // Get the JsonArray structure from JsonReader.
            JsonArray jsonArray = reader.readArray();
            reader.close();
            
            int size = jsonArray.size();
            
//            Set ArrayList object to size of array
            this.profilePicList = new ArrayList<String>(size);
            
            for (JsonValue value : jsonArray) {
            	JsonObject obj = value.asJsonObject();
            	String profilePicUrl = obj.getJsonObject("user").getString("profile_image_url_https");
            	this.profilePicList.add(profilePicUrl);
            }
            
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
                
		return profilePicList;
        
    }

    //NEED
    public ArrayList<Integer[]> getTweetStats(File file) {
   	 
    	this.statsList = new ArrayList<Integer[]>(0);
    	
        try {
        	InputStream is = new FileInputStream(file);
 
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
 
            // Get the JsonArray structure from JsonReader.
            JsonArray jsonArray = reader.readArray();
            reader.close();
            
            int size = jsonArray.size();
            
//            Set ArrayList object to size of array
            this.statsList = new ArrayList<Integer[]>(size);
            
            for (JsonValue value : jsonArray) {
            	JsonObject obj = value.asJsonObject();
            	Integer retweetCount = obj.getInt("retweet_count");
            	Integer favoriteCount = obj.getInt("favorite_count");
            	Integer[] stats = new Integer[] {retweetCount, favoriteCount};
            	this.statsList.add(stats);
            }
            
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
                
		return statsList;
        
    }

    //NEED
    public ArrayList<String> getTweetText(File file) {
    	 
    	this.tweetTextList = new ArrayList<String>(0);
    	
        try {
        	InputStream is = new FileInputStream(file);
 
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
 
            // Get the JsonArray structure from JsonReader.
            JsonArray jsonArray = reader.readArray();
            reader.close();
            
            int size = jsonArray.size();
            
//            Set ArrayList object to size of array
            this.tweetTextList = new ArrayList<String>(size);
            
            for (JsonValue value : jsonArray) {
            	JsonObject obj = value.asJsonObject();
//              System.out.println(obj.getString("text"));
//              System.out.println(obj.getJsonObject("user").getJsonNumber("id"));
//              System.out.println(obj.getJsonObject("user").getString("screen_name"));
            	String tweetText = obj.getString("text");
            	this.tweetTextList.add(tweetText);
            }
            
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
                
		return tweetTextList;
        
    }

    //NEED
    public ArrayList<String> getPlaces(File file) {
   	 
    	this.placeList = new ArrayList<String>(0);
    	
        try {
        	InputStream is = new FileInputStream(file);
 
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
 
            // Get the JsonArray structure from JsonReader.
            JsonArray jsonArray = reader.readArray();
            reader.close();
            
            int size = jsonArray.size();
            
//            Set ArrayList object to size of array
            this.placeList = new ArrayList<String>(size);
            
            for (JsonValue value : jsonArray) {
            	JsonObject obj = value.asJsonObject();
//              System.out.println(obj.getString("text"));
//              System.out.println(obj.getJsonObject("user").getJsonNumber("id"));
//              System.out.println(obj.getJsonObject("user").getString("screen_name"));
            	String tweetPlace = obj.getJsonObject("place").getString("full_name") + ", " + obj.getJsonObject("place").getString("country");
            	this.placeList.add(tweetPlace);
            }
            
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
                
		return placeList;
        
    }

    //NEED
    public ArrayList<Double[]> getCoordinates(File file) {
      	 
    	this.coordinatesList = new ArrayList<Double[]>(0);
    	
        try {
        	InputStream is = new FileInputStream(file);
 
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
 
            // Get the JsonArray structure from JsonReader.
            JsonArray jsonArray = reader.readArray();
            reader.close();
            
            int size = jsonArray.size();
            
//            Set ArrayList object to size of array
            this.coordinatesList = new ArrayList<Double[]>(size);
            
            for (JsonValue value : jsonArray) {
            	JsonObject obj = value.asJsonObject();
            	JsonArray coordinates = obj.getJsonObject("place").getJsonObject("bounding_box").getJsonArray("coordinates"); 
            	// get first coordinate of boundary box for the city
            	Double c1 = coordinates.get(0).asJsonArray().get(0).asJsonArray().getJsonNumber(0).doubleValue();

            	//get second coordinate of boundary box for the city
            	Double c2 = coordinates.get(0).asJsonArray().get(0).asJsonArray().getJsonNumber(1).doubleValue();
            	
            	Double[] coords = new Double[] {c1, c2};
            	
            	this.coordinatesList.add(coords);
//            	System.out.println(coordinates.get(0).asJsonArray().get(0).asJsonArray().getJsonNumber(1).doubleValue());
            }
            
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
                
		return coordinatesList;
        
    }
    
    //NEED
    public ArrayList<String> getSources(File file) {
      	 
    	this.sourceList = new ArrayList<String>(0);
    	
        try {
        	InputStream is = new FileInputStream(file);
 
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
 
            // Get the JsonArray structure from JsonReader.
            JsonArray jsonArray = reader.readArray();
            reader.close();
            
            int size = jsonArray.size();
            
//            Set ArrayList object to size of array
            this.sourceList = new ArrayList<String>(size);
            
            for (JsonValue value : jsonArray) {
            	JsonObject obj = value.asJsonObject();
//              System.out.println(obj.getString("text"));
//              System.out.println(obj.getJsonObject("user").getJsonNumber("id"));
//              System.out.println(obj.getJsonObject("user").getString("screen_name"));
            	String tweetSourceHTML = obj.getString("source");
            	String tweetSource = Jsoup.parse(tweetSourceHTML).text();
            	this.sourceList.add(tweetSource);
            }
            
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
                
		return sourceList;
        
    }

    //NEED
    public ArrayList<String> getTimeCreated(File file) {
     	 
    	this.timeCreated = new ArrayList<String>(0);
    	
        try {
        	InputStream is = new FileInputStream(file);
 
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
 
            // Get the JsonArray structure from JsonReader.
            JsonArray jsonArray = reader.readArray();
            reader.close();
            
            int size = jsonArray.size();
            
//            Set ArrayList object to size of array
            this.timeCreated = new ArrayList<String>(size);
            
            for (JsonValue value : jsonArray) {
            	JsonObject obj = value.asJsonObject();
//              System.out.println(obj.getString("text"));
//              System.out.println(obj.getJsonObject("user").getJsonNumber("id"));
//              System.out.println(obj.getJsonObject("user").getString("screen_name"));
            	String creationTime = obj.getString("created_at");
            	this.timeCreated.add(creationTime);
            }
            
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
                
		return timeCreated;
        
    }

    public ArrayList<JsonArray> getHashTags(File file) {
    	 
    	this.hashTagList = new ArrayList<JsonArray>(0);
    	
        try {
        	InputStream is = new FileInputStream(file);
 
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
 
            // Get the JsonArray structure from JsonReader.
            JsonArray jsonArray = reader.readArray();
            reader.close();
            
            int size = jsonArray.size();
            
//            Set ArrayList object to size of array
            this.hashTagList = new ArrayList<JsonArray>(size);
            
            for (JsonValue value : jsonArray) {
            	JsonObject obj = value.asJsonObject();
//              System.out.println(obj.getString("text"));
//              System.out.println(obj.getJsonObject("user").getJsonNumber("id"));
//              System.out.println(obj.getJsonObject("user").getString("screen_name"));
            	JsonArray hashTags = obj.getJsonObject("entities").getJsonArray("hashtags");
            	this.hashTagList.add(hashTags);
            }
            
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
                
		return hashTagList;
        
    }

    //NEED
    public ArrayList<String> getURLs(File file) {
   	 
    	this.urlList = new ArrayList<String>(0);
    	
        try {
        	InputStream is = new FileInputStream(file);
 
            // Create JsonReader from Json.
            JsonReader reader = Json.createReader(is);
 
            // Get the JsonArray structure from JsonReader.
            JsonArray jsonArray = reader.readArray();
            reader.close();
            
            int size = jsonArray.size();
            
//            Set ArrayList object to size of array
            this.urlList = new ArrayList<String>(size);
            
            for (JsonValue value : jsonArray) {
            	JsonObject obj = value.asJsonObject();
//              System.out.println(obj.getString("text"));
//              System.out.println(obj.getJsonObject("user").getJsonNumber("id"));
//              System.out.println(obj.getJsonObject("user").getString("screen_name"));
            	JsonArray url = obj.getJsonObject("entities").getJsonArray("urls");
            	if(!url.isEmpty())
            	{
            		this.urlList.add(url.get(0).asJsonObject().getString("expanded_url"));
            	}
            	else if(url.isEmpty())
            	{
                	this.urlList.add("");
            	}
            }
            
        } catch (Exception e) {
            System.out.println("Failed: " + e.getMessage());
        }
                
		return urlList;
        
    }
    
}

