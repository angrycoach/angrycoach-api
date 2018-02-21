package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.Task;
import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import play.libs.Json;
import play.mvc.*;

import views.html.*;

import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class TasksController extends Controller {

    public Result getTask() {
        ObjectNode result = Json.newObject();
        result.put("exampleField1", "foobar");
        result.put("exampleField2", "Hello world!");

        MongoCollection tasks = Task.tasks();

        MongoCursor<Task> iter = tasks.find().as(Task.class);
        Stream<Task> stream = StreamSupport.stream(iter.spliterator(), false);

        List<JsonNode> jsons = stream.map(t -> Json.toJson(t)).collect(Collectors.toList());

        ArrayNode arr = Json.newArray().addAll(jsons);
        
        return ok(arr);
    }

    public Result saveTask(){
        String firstNome = "My First Task";
        ObjectId firstId = new ObjectId("507f191e810c19729de860ea");

        Task firstTask = new Task();
        firstTask.name = firstNome;
        firstTask.id = firstId;

        try{
            firstTask.insert();
            return ok("Task = " + firstTask.name + " was saved successfully!");
        }catch(Exception e){
            return ok(e.getMessage());
        }
    }
}





