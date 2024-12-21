public class App {
    public static void main(String[] args) throws Exception {
        try {
            System.out.println(args[0]);
            GitHubApiClient gitHubApiClient = new GitHubApiClient(args[0]);
            gitHubApiClient.getUserInfo();
        } catch (Exception e) {
            System.err.println("no given username!");
            e.printStackTrace();
        }
        
    }
}
