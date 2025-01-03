import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            System.out.println(args[0]);
            GitHubApiClient gitHubApiClient = new GitHubApiClient(args[0]);
            ArrayList<Event> result = gitHubApiClient.getEvents();
            for(Event event : result) {
                System.out.println(event.toString());
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println("no given username!");
            e.printStackTrace();
        }
        
    }
}
