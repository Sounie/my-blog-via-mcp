Having a play around with MCP, by setting up a mechanism for AI to find content from my blog.

Work in progress...

Planning to involve two parts:
- wrapper around search engine to find relevant content
- access to page content as follow-up to search results, to allow for more detailed information retrieval.

Also taking an initial use of http4k.

Using JSoup to parse the HTML content of the blog pages, and extract the relevant information. Initially 
processed body content as String, but then tried out making more memory efficient by
streaming. This required adjusting the OkHttp client creation away from the default configuration.



