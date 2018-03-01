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
        MongoCollection tasks = Task.tasks();
        MongoCursor<Task> iter = tasks.find().as(Task.class);
        Stream<Task> stream = StreamSupport.stream(iter.spliterator(), false);
        List<JsonNode> jsons = stream.map(t -> Json.toJson(t)).collect(Collectors.toList());
        ArrayNode arr = Json.newArray().addAll(jsons);

        return ok(arr);
    }

    public Result saveTask(){

        JsonNode body = request().body().asJson();
        Task taskNew = Json.fromJson(body, Task.class);

        try{
            taskNew.insert();
            return ok("Task = " + taskNew.name + " was saved successfully!");
        }catch(Exception e){
            return badRequest(e.getMessage());
        }
    }

    public Result atualizar(String uuid){

        JsonNode body = request().body().asJson();
        Task taskNew = Json.fromJson(body, Task.class);

        Task task = Task.findByUuid(uuid);
        task.name = taskNew.name;
        task.done = taskNew.done;
        task.deleted = taskNew.deleted;

        try{
            task.insert();
            return ok("Task = " + task.name + " was updated successfully!");
        }catch(Exception e){
            return badRequest(e.getMessage());
        }
    }
}





