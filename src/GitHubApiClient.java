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
        this.events = new ArrayList<Event>();
        fromJsonToList();
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
        String jsonInfo = this.jsonRequestUserInfo;
        String workingJsonInfo = jsonInfo;

        while(workingJsonInfo.indexOf('{') != -1) {
            int currentPos = workingJsonInfo.indexOf('{');
            int startingPos = currentPos;
            // to use as a stack (lifo) to count how many brackets are passed
            int bracketCounter = 1;

            while(bracketCounter != 0) {
                if(workingJsonInfo.indexOf('{', currentPos + 1) != -1 && workingJsonInfo.indexOf('}', currentPos + 1) != -1) {
                    if(workingJsonInfo.indexOf('{', currentPos + 1) < workingJsonInfo.indexOf('}', currentPos + 1)) {
                        bracketCounter++;
                        currentPos = workingJsonInfo.indexOf('{', currentPos + 1);
                    }
                    else {
                        bracketCounter--;
                        currentPos = workingJsonInfo.indexOf('}', currentPos + 1);
                    }
                }
                else{
                    bracketCounter--;
                    currentPos = workingJsonInfo.indexOf('}', currentPos + 1);
                }
            }
            String eventTmp = workingJsonInfo.substring(startingPos, currentPos + 1);
            String id = eventTmp.substring(eventTmp.indexOf("id") + 5, eventTmp.indexOf('"', eventTmp.indexOf("id") + 6));
            String type = eventTmp.substring(eventTmp.indexOf("type") + 7, eventTmp.indexOf('"', eventTmp.indexOf("type") + 8));
            String repoName = eventTmp.substring(eventTmp.indexOf("name") + 7, eventTmp.indexOf('"', eventTmp.indexOf("name") + 8));
            Event currentEvent = new Event(id, type, repoName);
            this.events.add(currentEvent);
            workingJsonInfo = workingJsonInfo.substring(currentPos + 1);
        }
    }

    public ArrayList<Event> getEvents(){
        return this.events;
    }

}