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

        get("/locations/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "location.hbs");
        },new HandlebarsTemplateEngine());

        post("/locations/new", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            String location = request.queryParams("location");
            Location newLocation = new Location(location);
            newLocation.save();
            model.put("locations", newLocation.all());
            return new ModelAndView(model, "success.hbs");
        }, new HandlebarsTemplateEngine());


    }

}
