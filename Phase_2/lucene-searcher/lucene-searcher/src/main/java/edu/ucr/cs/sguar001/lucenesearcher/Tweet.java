package edu.ucr.cs.sguar001.lucenesearcher;

public class Tweet {

    public String urlTitle;
    public String body;
    public String username;
    public String name;
    public String profilePic;
    public String place;
    public String[] coordinates;
    public String timeCreated;
    public String[] tweetStats;
    public String source;
    

    public Tweet(){}

    public Tweet(String title, String body, String username, String name, String profilePic, 
    		String place, String[] coordinates, String timeCreated, String[] tweetStats, String source) {
        this.urlTitle = title;
        this.body = body;
        this.username = username;
        this.name = name;
        this.profilePic = profilePic;
        this.place = place;
        this.coordinates = coordinates;
        this.timeCreated = timeCreated;
        this.tweetStats = tweetStats;
        this.source = source;
    }

    public String getTitle() {
        return urlTitle;
    }

    public void setTitle(String title) {
        this.urlTitle = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
    public String getUsername() {
    	return username;
    }
    
    public void setUsername(String username) {
    	this.username = username;
    }

    @Override
    public String toString() {
        return String.format("Tweet[title=%s, body=%s, username=%s]", urlTitle, body, username);
    }
}
