import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;

public class GitHubApiClient {
    private HttpClient httpClient;
    private String endpoint;
    private ArrayList<Event> events;
    private String jsonRequestUserInfo;

    GitHubApiClient(String username) {
        this.httpClient = HttpClient.newBuilder()
                                    .version(HttpClient.Version.HTTP_2)
                                    .connectTimeout(Duration.ofSeconds(10))
                                    .build();
        this.endpoint = "https://api.github.com/users/" + username + "/events";
        this.jsonRequestUserInfo = requestUserInfo();
    }

    public String requestUserInfo() {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endpoint)).GET().build();
        HttpResponse<String> response;

        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new RuntimeException("error while getting user info");
            }
            return response.body();
        } catch (IOException e) {
            // Handle network/connectivity errors
            System.err.println("Network error: " + e.getMessage());
        } catch (InterruptedException e) {
            // Handle interruption
            System.err.println("Request was interrupted: " + e.getMessage());
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }
        return "error while getting the response";  
    }

    public void fromJsonToList() {
        String jsonInfoCopy = this.jsonRequestUserInfo;
        
        // to use as a stack (lifo) to count how many parentesis are passed
        int counter = 0;

        int firstOpenOccurrence = jsonInfoCopy.indexOf('{');
        System.out.println(jsonInfoCopy.substring(firstOpenOccurrence));


/* 
		while (jsonInfoCopy.indexOf(']') != -1) {
			int parentesisCount = 0;
			do {
				if()
			}
			while(blockFinished = false);
		
		}
 */

        /* 
        while(info.indexOf("id") != -1) {
			System.out.println();
            String id = info.substring(info.indexOf("id") + 5, info.indexOf('"', info.indexOf("id") + 5));
            info = info.substring(info.indexOf('"', info.indexOf("id") + 5), info.length());
            System.out.println("id: " + id);

            String type = info.substring(info.indexOf("type") + 7, info.indexOf('"', info.indexOf("type") + 7));
            info = info.substring(info.indexOf('"', info.indexOf("type") + 7), info.length());
            System.out.println("type: " + type);

            String actor = info.substring(info.indexOf("actor") + 7, info.indexOf('}', info.indexOf("actor") + 7));
            info = info.substring(info.indexOf('}', info.indexOf("actor") + 7), info.length());
            System.out.println("actor: " + actor);

            String repo = info.substring(info.indexOf("repo") + 6, info.indexOf('}', info.indexOf("repo") + 6));
            info = info.substring(info.indexOf('}', info.indexOf("repo") + 6), info.length());
            System.out.println("repo: " + repo);

            String payload = info.substring(info.indexOf("payload") + 9, info.indexOf('}', info.indexOf(']')));
            info = info.substring(info.indexOf('}', info.indexOf(']')), info.length());
            System.out.println("payload: " + payload);
        }
		 */
    }

    public void getUserInfo(){
        fromJsonToList();
        return;
    }

}