import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        String username = "";
        
        // test #1 - valid username
        // args[0] = "giacomib";
        
        // test #2 - empty username
        // args[0] = "";

        // test #3 - invalid username
        // args[0] = "fkjdnafk";
        
        try{
            username = args[0];
        } catch (Exception e) {
            System.err.println("username not specified");
            // e.printStackTrace();
            return;
        }

        try {
            System.out.println();
            System.out.println("retrieving information about user: " + args[0]);
            System.out.println();
            GitHubApiClient gitHubApiClient = new GitHubApiClient(username);
            ArrayList<Event> result = gitHubApiClient.getEvents();
            for(Event event : result) {
                System.out.println(event.toString());
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println("found zero users with given username!");
            // e.printStackTrace();
            return;
        }
        
    }
}
