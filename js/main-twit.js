var Twit = require('twit');
  fs = require("fs");

if (process.platform === "win32") {
  var rl = require("readline").createInterface({
    input: process.stdin,
    output: process.stdout
  });

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

// array to hold all collected tweets
tweetArr = [];

stream.on('tweet', function(tweet) {
  tweetArr.push(JSON.stringify(tweet));
});
	
// code to handle when Ctrl + C is pressed
rl.on("SIGINT", function () {
    process.emit("SIGINT");
  });
};

// when ctrl + c is pressed, then write array to JSON file
process.on("SIGINT", function () {
  fs.appendFile('../../../json_files/geo-tweets-US-6.json', '[' + tweetArr + ']', 'utf-8', (err) => {

    if (err) throw err;

    else process.exit();

  });

});