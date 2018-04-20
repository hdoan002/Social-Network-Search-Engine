var TwitterStream = require("twitter-stream-api"),
  fs = require("fs");

var keys = {
  consumer_key: "",
  consumer_secret: "",
  token: "",
  token_secret: ""
};

var Twitter = new TwitterStream(keys, false);
Twitter.stream("statuses/filter", {
  // track: "javascript",
  // include stall warnings
  stall_warnings: true,
  //include tweets only in english
  language: "en",
  //include tweets that fall within this boundary location (US)
  locations: "-123.75,28.92,-66.8,49.13"
});

//  stall connection is a connection which have not received any new data or keep alive messages from the Twitter Stream API during a period of 90 seconds.
Twitter.on('connection error stall', function () {
  console.log('connection error stall');
});

// Emitted when the connection to the Twitter Stream API have TCP/IP level network errors. This error event are normally emitted if there are network level errors during the connection process.
Twitter.on('connection error network', function (error) {
  console.log('connection error network', error);
});

// Emitted when a the connection to the Twitter Stream API are taken down / closed.
Twitter.on('connection aborted', function () {
  console.log('connection aborted');
});

// This error event are normally emitted if there are HTTP errors during the connection process.
Twitter.on('connection error http', function (httpStatusCode) {
  console.log('connection error http', httpStatusCode);
});

// Emitted when the connection to the Twitter Stream API throw an unexpected error which are not within the errors defined by the Twitter Stream API documentation.
Twitter.on('connection error unknown', function (error) {
  console.log('connection error unknown', error);
  Twitter.close();
});

// Emitted if the client received an message from the Twitter Stream API which the client could not parse into an object or handle in some other way.
Twitter.on('data error', function (error) {
  console.log('data error', error);
});

// spawn thread to run streaming concurrently (doesn't work?)
// var _worker = new Worker('worker_1.js');
// _worker.onerror = function(e) {console.log('Error: ', e) };

//start the worker
// _worker.postMessage();

Twitter.pipe(fs.createWriteStream("json_files/geo-tweets.json"));
