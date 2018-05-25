import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

public class Main {
    public static void main(String[] args) {
        staticFiles.location("/public");
        final Configuration configuration = new Configuration(new Version(2, 3, 28));
        configuration.setClassForTemplateLoading(Main.class, "/");

        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>();
        estudiantes.add(new Estudiante(20131459, "Jean", "Tejeda", "809"));

        get("/", (req, res) -> {
            StringWriter writer = new StringWriter();
            Template template = configuration.getTemplate("templates/index.ftl");
            Map<String, Object> atributos = new HashMap<>();
            atributos.put("estudiantes", estudiantes);
            template.process(atributos, writer);
            return writer;
        });

        get("/agregar-estudiante", (req, res) -> {
            StringWriter writer = new StringWriter();
            Template template = configuration.getTemplate("templates/agregar-estudiante.ftl");
            template.process(null, writer);
            return writer;
        });
    }
}
