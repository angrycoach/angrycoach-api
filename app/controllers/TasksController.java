package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.mvc.*;

import views.html.*;


public class TasksController extends Controller {

    public Result getTask() {
        ObjectNode result = Json.newObject();
        result.put("exampleField1", "foobar");
        result.put("exampleField2", "Hello world!");


        Task task1 = new Task();
        task1.text = "Estuadar Matematica";
        task1.done = false;
        JsonNode personJson = Json.toJson(task1);

        return ok(personJson);
    }

    public static class Task {
        public String text;
        public boolean done;
    }

}





