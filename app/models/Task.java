package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import play.Play;
import uk.co.panaxiom.playjongo.PlayJongo;

public class Task {

    public String name;

    @JsonProperty("_id")
    public ObjectId id;

    public static PlayJongo jongo = Play.application().injector().instanceOf(PlayJongo.class);

    public static MongoCollection tasks() {
        return jongo.getCollection("tasks");
    }

    public Task insert() {
        tasks().save(this);
        return this;
    }

    public void remove() {
        tasks().remove(this.id);
    }

    public static Task findByName(String name) {
        return tasks ().findOne("{name: #}", name).as(Task.class);
    }

}
