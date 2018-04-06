# Social Network Search Engine
Social network search engine for UCR CS172 Information Retrieval.

Twitter:

Use the Twitter Streaming API to collect geolocated tweets. Store tweets in large files of about 10 MB, one tweet per row.

If a tweet contains a URL to an html page, get title of that page, and add title as an additional field of the tweet, that is, include it in the JSON of the tweet, so it becomes searchable in Part B. 
Hint: Retrieving title in real time may slow down your Twitter stream and lead to lost data.


In both cases, you should collect at least 5 GB of data.

Deliverables:

1: Report (4-5 pages) in pdf that includes:

  1. Collaboration Details: Description of contribution of each team member.

  2. Overview of system, including (but not limited to)

       a. Architecture.

       b. The Crawling or data collection strategy.

       c. Data Structures employed.

  3. Limitations (if any) of system.

  4. Instruction on how to deploy the system. Ideally, you should include a crawler.bat (Windows) or crawler.sh (Unix/Linux) executable   file that takes as input all necessary parameters.

  Example: [user@server]./crawler.sh <seed-File:seed.txt> <num-pages: 10000> <hops-away: 6> <output-dir>
  Similarly for Twitter.

  5. Screenshots showing the system in action.

2: Zip file with your code

