var TwitterStream = require("twitter-stream-api"),
  fs = require("fs");

var keys = {
  consumer_key: "your_consumer_key",
  consumer_secret: "your_consumer_secret",
  token: "your_access_token_key",
  token_secret: "your_access_token_secret"
};

var Twitter = new TwitterStream(keys, false);
Twitter.stream("statuses/filter", {
  track: "javascript"
});

Twitter.pipe(fs.createWriteStream("tweets.json"));
