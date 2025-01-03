public class Event {
    private String id;
    private String type;
    private String repoName;

    public Event(String id, String type, String reponame) {
        this.id = id;
        this.type = type;
        this.repoName = reponame;
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

    public String toString() {
        return "Made an event of type " + this.type + " with id " + this.id + " in the repository " + this.repoName;
    }
}
