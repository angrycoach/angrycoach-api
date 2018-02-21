package models;

public class Task {

    public String name;

    @JsonProperty("_id")
    public ObjectId id;

    public static PlayJongo jongo = Play.application().injector().instanceOf(PlayJongo.class);

    public static MongoCollection tasks() {
        return jongo.getCollection("tasks");
    }

    public Task insert() {
        tasks ().save(this);
    }

    public void remove() {
        tasks().remove(this.id);
    }

    public static Task findByName(String name) {
        return tasks ().findOne("{name: #}", name).as(User.class);
    }

}
