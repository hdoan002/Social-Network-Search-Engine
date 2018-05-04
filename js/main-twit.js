var Twit = require('twit');
fs = require("fs");

var T = new Twit({
	consumer_key: 'VRpQ4je2zktzz5NequKPToZZl',
	consumer_secret: 'hek5GiRs3tASXE5eLEvq8hzupRNO6QeXnS3WdBrYlFLUmrxZ18',
	access_token: '4746188238-wMnfon2mdpvWpuWyU1KfJZsRUjVTzM22wnLmqkR',
	access_token_secret: 'XJxEODMXLs0RSHGdkcV9NfWuDMw8HeYrYHHy6YdS9Otit', 
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
  //increment j every time a tweet is received to keep count 
  j++;

  fs.appendFile('../json_files/geo-tweets-US' + '-' + k + '.json', JSON.stringify(tweet) + ',', 'utf-8', (err) => {

    if (err) throw err;

    // after getting 30,000 tweets, write to a new, different JSON file and restart tweet counter (j) and increment file number (k)
    else if(j == 30000)
    {
      j = 0;
      k++;
    }

  });

});
	
