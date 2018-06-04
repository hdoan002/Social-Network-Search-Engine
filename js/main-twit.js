var Twit = require('twit');
fs = require("fs");

var T = new Twit({
	consumer_key: '',
	consumer_secret: '',
	access_token: '',
	access_token_secret: '', 
});

var stream = T.stream('statuses/filter', {
  // include stall warnings
  stall_warnings: true,
  //include tweets only in english
  language: "en",
  //include tweets that fall within this boundary location (US)
  locations: "-123.75,28.92,-66.8,49.13"
});

stream.on('connect', function (request) {
  console.log('connected');
})

stream.on('disconnect', function (disconnectMessage) {
  console.log('disconnected');
  console.log(disconnectMessage);
});

stream.on('limit', function (limitMessage) {
  console.log('limited');
  console.log(limitMessage);
});

stream.on('reconnect', function (request, response, connectInterval) {
  console.log('reconnect attempted');
});

stream.on('warning', function (warning) {
  console.log('warning encountered');
  console.log(warning);
});

stream.on('error', function (error) {
  console.log('error encountered');
  console.log(error);
});

var j = 0;
var k = 0;

stream.on('tweet', function(tweet) {

//append opening array bracket on intial start
  if(j == 0)
  {
    fs.appendFile('../json_files/geo-tweets-US' + '-' + k + '.json', '[', 'utf-8', (err) => {

      if (err) throw err;

   });
  }
  //increment j every time a tweet is received to keep count 
  j++;

  if (j < 3000)
  {
    fs.appendFile('../json_files/geo-tweets-US' + '-' + k + '.json', JSON.stringify(tweet) + ',', 'utf-8', (err) => {

      if (err) throw err;

    });
  }

  // after getting 30,000 tweets, write to a new, different JSON file and restart tweet counter (j) and increment file number (k)
  else if(j >= 3000)
  {
//append closing array bracket after getting 3000 tweets
    fs.appendFile('../json_files/geo-tweets-US' + '-' + k + '.json', JSON.stringify(tweet) + ']', 'utf-8', (err) => {

      if (err) throw err;

    });
    j = 0;
    k++;
  }
});
