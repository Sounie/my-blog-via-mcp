Having a play around with MCP, by setting up a mechanism for AI to find content from my blog.

Work in progress... - there's nothing MCP related in place yet...

Planning to involve two parts:
- wrapper around search engine to find relevant content
- access to page content as follow-up to search results, to allow for more detailed information retrieval.

Also taking an initial use of http4k.

Using JSoup to parse the HTML content of the blog pages, and extract the relevant information. Initially processed body content as String, but then tried out making more memory efficient by streaming. This required adjusting the OkHttp client creation away from the default configuration.

As part of trying out http4k I have applied my usual discipline of checking the memory utilisation by profiling - which is how I noticed that the default client setup was loading more content into memory than I expected.


