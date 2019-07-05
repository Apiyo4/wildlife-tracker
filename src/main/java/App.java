import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;
public class App {
    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;

        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));

        } else {
            port = 4567;
        }
        port(port);


        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());

        get("/dashboards/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "form.hbs");
        },new HandlebarsTemplateEngine());

        post("/dashboards", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String type = request.queryParams("type");
            if(type.equals("Endangered")) {
                EndangeredAnimal newEndangeredAnimal = new EndangeredAnimal(name, health, age);
                newEndangeredAnimal.save();
                model.put("endangeredAnimals", newEndangeredAnimal.all());
            }else{
                NonEndangeredAnimal newNonEndangeredAnimal = new NonEndangeredAnimal(name);
                newNonEndangeredAnimal.save();
                model.put("nonEndangeredAnimals", newNonEndangeredAnimal.all());
            }

            return new ModelAndView(model, "dashboard.hbs");
        }, new HandlebarsTemplateEngine());
        get("/dashboards", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "dashboard.hbs");
        },new HandlebarsTemplateEngine());


        get("/rangers", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "ranger.hbs");
        },new HandlebarsTemplateEngine());
        post("/rangers", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");
            int badgeNumber = Integer.parseInt(request.queryParams("badgeNumber"));
            Ranger newRanger = new Ranger("name", badgeNumber);
            newRanger.save();
            model.put("rangers", newRanger.all());
            return new ModelAndView(model, "ranger.hbs");
        }, new HandlebarsTemplateEngine());


        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "sightings.hbs");
        },new HandlebarsTemplateEngine());
        post("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String location = request.queryParams("location");
            String ranger = request.queryParams("ranger");
            int animalId = Integer.parseInt(request.queryParams("animalId"));
            Sighting newSighting = new Sighting("location", "ranger", animalId);
            newSighting.save();
            model.put("sightings", newSighting.all());
            return new ModelAndView(model, "sightings.hbs");
        }, new HandlebarsTemplateEngine());


        get("/locations", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "location.hbs");
        },new HandlebarsTemplateEngine());

        post("/locations", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String location = request.queryParams("location");
            Location newLocation = new Location(location);
            newLocation.save();
            model.put("locations", newLocation.all());
            return new ModelAndView(model, "location.hbs");
        }, new HandlebarsTemplateEngine());


    }

}
