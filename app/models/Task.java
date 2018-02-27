package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import play.Play;
import uk.co.panaxiom.playjongo.PlayJongo;
import java.util.UUID;

public class Task {

    public String name;
    public String uuid;
    public boolean done;
    public boolean deleted;

    @JsonProperty("_id")
    public ObjectId id;

    public Task(){
        this.uuid = UUID.randomUUID().toString();
        this.done = false;
        this.deleted = false;
    }

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

    public static Task findByUuid(String uuid) {
        return tasks ().findOne("{uuid: #}", uuid).as(Task.class);
    }

}
