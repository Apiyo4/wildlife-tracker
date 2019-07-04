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
            String location = request.queryParams("location");
            String rangerName = request.queryParams("rangerName");
            String badgeNumber = request.queryParams("badgeNumber");
            String animalName = request.queryParams("animalName");
            String animalType = request.queryParams("animalType");
            String animalHealth = request.queryParams("animalHealth");
            String animalAge = request.queryParams("animalAge");

            if (!(rangerName.trim().isEmpty()|| badgeNumber.trim().isEmpty())) {
                Ranger ranger = new Ranger(rangerName, Integer.parseInt(badgeNumber));
                ranger.save();
            }else if (animalType.equals("Endangered") && (!(animalName.trim().isEmpty() || animalHealth.trim().isEmpty() || animalAge.trim().isEmpty()))) {
                EndangeredAnimal endangeredAnimal = new EndangeredAnimal(animalName, animalHealth, animalAge);
                endangeredAnimal.save();
                Sighting newSighting = new Sighting(rangerName, location, endangeredAnimal.getId());
                newSighting.save();
            } else if (animalType.equals("NonEndangered") && (!(animalName.trim().isEmpty() || animalHealth.trim().isEmpty() || animalAge.trim().isEmpty()))) {

                NonEndangeredAnimal nonEndangeredAnimal = new NonEndangeredAnimal(animalName);
                nonEndangeredAnimal.save();
                Sighting newSighting = new Sighting(rangerName, location, nonEndangeredAnimal.getId());
                newSighting.save();
            }else if (!(location.trim().isEmpty())) {

                Location newLocation = new Location(location);
                newLocation.save();
            }else {
                System.out.println("Please fill in all the fields");

        }
            List<Sighting> allSighting = Sighting.all();
            List<EndangeredAnimal> allEndangeredAnimal= EndangeredAnimal.all();
            List<NonEndangeredAnimal> allNonEndangeredAnimal= NonEndangeredAnimal.all();
            List<Location> allLocation= Location.all();
            List<Ranger> allRanger= Ranger.all();
            model.put("sighting", allSighting);
            model.put("endangeredAnimal", allEndangeredAnimal);
            model.put("nonEndangeredAnimal", allNonEndangeredAnimal);
            model.put("location", allLocation);
            model.put("ranger", allRanger);
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());

        get("/dashboards", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sighting", Sighting.all());
            model.put("endangeredAnimal", EndangeredAnimal.all());
            model.put("nonEndangeredAnimal", NonEndangeredAnimal.all());
            model.put("location", Location.all());
            model.put("ranger", Ranger.all());
            return new ModelAndView(model, "dashboard.hbs");
        }, new HandlebarsTemplateEngine());
    }

}
