const fetch = require('node-fetch');

const JSDOM = require('jsdom').JSDOM

const fs = require('fs');

fs.readFile('../json_files/geo-tweets-US-31.json', 'utf-8', (err, readData) => {

	if (err) throw err;

	response = JSON.parse(readData);
	console.log(response.length);
	response.forEach(function(obj, index) {
		if(response[index].entities.urls.length != 0)
		{
			// console.log(response[index].entities.urls[0].expanded_url);
			var url = response[index].entities.urls[0].expanded_url;
			if(url.indexOf("twitter") == -1 && url.indexOf("instagram") == -1)
			{
				fetch(url)
				.then(function(response) {
					response.text().then(function(html) {
						const doc = new JSDOM(html);
						var pageTitle = doc.window.document.querySelector("title").textContent;
						var stringTitle = JSON.stringify(pageTitle);
						var titleObj = '{"expanded_url_title":' + stringTitle + '}';
						object = JSON.parse(titleObj);
						obj.entities.urls.push(object);
						console.log(obj);
					});
				})
				.catch(function(error) {
					console.log(error);
				});
			}
		}
	});
	
});
