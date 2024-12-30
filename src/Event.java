public class Event {
    private String id;
    private String type;
    private String repoName;
    private int numberOfcommits;

    public Event(String id, String type, String reponame, int numberOfCommits) {
        this.id = id;
        this.type = type;
        this.repoName = reponame;
        this.numberOfcommits = numberOfCommits;
    }

    public String getId() {
        String id = this.id;
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        String type = this.type;
        return type;
    }

    public String getRepoName() {
        String repoName = this.repoName;
        return repoName;
    }

    public int getNumberOfCommits() {
        return this.numberOfcommits;
    }
}
