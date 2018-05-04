const fetch = require('node-fetch');

const JSDOM = require('jsdom').JSDOM

const url = 'https://wapo.st/2HJEwmI';

const fs = require('fs');

fs.readFile('../../../json_files/geo-tweets-US-0.json', 'utf-8',(err, readData) => {

	if (err) throw err;

	response = JSON.parse(readData);
	console.log(response.length);
	response.forEach(function(obj, index) {
		if(response[index].entities.urls.length != 0)
		{
			console.log(response[index].entities.urls[0].expanded_url);
		}
	});
	
});

fetch(url)
	.then(function(response) {
		response.text().then(function(html) {
			const doc = new JSDOM(html);
			var pageTitle = doc.window.document.querySelector("title").textContent;
			var stringTitle = JSON.stringify(pageTitle);
			var titleObj = '{"expanded_url_title":' + stringTitle + '}';
			obj = JSON.parse(titleObj);
			console.log(obj.expanded_url_title);
		});
	});
