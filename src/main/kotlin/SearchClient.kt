import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.runBlocking
import org.http4k.client.OkHttp
import org.http4k.core.BodyMode
import org.http4k.core.Method
import org.http4k.core.Request
import org.jsoup.parser.Parser
import org.jsoup.parser.StreamParser
import java.io.InputStreamReader

fun main() {
    val myClient = SearchClient()

    runBlocking {
        myClient.search("java")
    }
}

class SearchClient {
    // Utilises an http4k client to make GET calls on an endpoint
    // Parses response and returns a list of SearchResult objects
    // Handles any exceptions that may occur during the request and checks response codes

    suspend fun search(terms : String) {
        // TODO: Clean up format of search terms to ensure they are URL safe

        val client = OkHttp(bodyMode = BodyMode.Stream)

        withContext(Dispatchers.IO) {
            client.invoke(Request(Method.GET, "https://blog.elegant-solutions.london/search?q=$terms"))
                .use { response ->
                    if (response.status.successful) {
                        val parser = StreamParser(Parser.htmlParser())

                        parser.parse(
                            InputStreamReader(response.body.stream, Charsets.UTF_8).buffered(),
                            "https://blog.elegant-solutions.london/"
                        )

                        // step through elements finding match for specific element name and class
                        parser.stream().filter { it.nodeName() == "div" && it.classNames().contains("post-outer") }
                            .forEach { element ->
                                val title = element.select("h3.post-title").text()
                                val description = element.select("div.post-body").text()

                                println("Title: $title")
                                println("Description: $description")
                                println()
                            }
                    } else {
                        // Handle non-successful response codes

                    }
                }
        }
        client.close()
        // At this point response and parser are out of scope, so memory should be freeable.
    }

}