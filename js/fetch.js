const fetch = require("node-fetch");

const JSDOM = require('jsdom').JSDOM

const url = 'https://wapo.st/2HJEwmI';

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
