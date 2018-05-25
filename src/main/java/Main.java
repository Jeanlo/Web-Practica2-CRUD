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
        final Configuration configuration = new Configuration(new Version(2, 3, 28));
        configuration.setClassForTemplateLoading(Main.class, "/");

        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        //Estableciendo la ruta de inicio, enviando el listado de estudiantes
        get("/", (req, res) -> {
            StringWriter writer = new StringWriter();
            Template template = configuration.getTemplate("templates/index.ftl");
            Map<String, Object> atributos = new HashMap<>();
            atributos.put("estudiantes", estudiantes);
            template.process(atributos, writer);
            return writer;
        });

        //Estableciendo la ruta para mostrar el formulario de agregar estudiantes
        get("/estudiante/agregar", (req, res) -> {
            StringWriter writer = new StringWriter();
            Template template = configuration.getTemplate("templates/agregar-estudiante.ftl");
            template.process(null, writer);
            return writer;
        });

        //Estableciendo la ruta para guardar el estudiante a agregar
        post("/estudiante/agregar", (req, res) -> {
            int matricula = Integer.parseInt(req.queryParams("matricula"));
            String nombre = req.queryParams("nombre");
            String apellido = req.queryParams("apellido");
            String telefono = req.queryParams("telefono");

            estudiantes.add(new Estudiante(matricula, nombre, apellido, telefono));

            res.redirect("/");
            return null;
        });

        //Estableciendo la ruta para mostrar los datos de un estudiante seleccionado
        get("/estudiante/:matricula", (req, res) -> {
            try {
                StringWriter writer = new StringWriter();
                Template template = configuration.getTemplate("templates/estudiante.ftl");
                Map<String, Object> atributos = new HashMap<>();
                Estudiante estudiante = null;

                for (Estudiante est : estudiantes) {
                    if (est.getMatricula() == Integer.parseInt(req.params("matricula"))) {
                        estudiante = est;
                    }
                }

                if (estudiante == null) {
                    throw new Exception();
                }

                atributos.put("estudiante", estudiante);
                template.process(atributos, writer);
                return writer;
            } catch (Exception error) {
                res.status(404);

                StringWriter writer = new StringWriter();
                Template template = configuration.getTemplate("templates/404.ftl");
                template.process(null, writer);

                res.body(writer.toString());
                return writer;
            }
        });

        //Estableciendo la ruta para mostrar el formulario de editar estudiante
        get("estudiante/editar/:matricula", (req, res) -> {
            try {
                StringWriter writer = new StringWriter();
                Template template = configuration.getTemplate("templates/editar-estudiante.ftl");
                Map<String, Object> atributos = new HashMap<>();
                Estudiante estudiante = null;

                for (Estudiante est : estudiantes) {
                    if (est.getMatricula() == Integer.parseInt(req.params("matricula"))) {
                        estudiante = est;
                    }
                }

                if (estudiante == null) {
                    throw new Exception();
                }

                atributos.put("estudiante", estudiante);
                template.process(atributos, writer);
                return writer;
            } catch (Exception error) {
                res.status(404);

                StringWriter writer = new StringWriter();
                Template template = configuration.getTemplate("templates/404.ftl");
                template.process(null, writer);

                res.body(writer.toString());
                return writer;
            }
        });

        //Estableciendo la ruta para guardar los cambios editados en el estudiante
        post("/estudiante/editar", (req, res) -> {
            int matricula = Integer.parseInt(req.queryParams("matricula"));
            String nombre = req.queryParams("nombre");
            String apellido = req.queryParams("apellido");
            String telefono = req.queryParams("telefono");

            for (Estudiante est : estudiantes) {
                if (est.getMatricula() == matricula) {
                    est.setNombre(nombre);
                    est.setApellido(apellido);
                    est.setTelefono(telefono);
                }
            }

            res.redirect("/");
            return null;
        });

        //Estableciendo la ruta para mostrar el aviso de confirmación de borrar estudiante
        get("/estudiante/borrar/:matricula", (req, res) -> {
            try {
                StringWriter writer = new StringWriter();
                Template template = configuration.getTemplate("templates/borrar-estudiante.ftl");
                Map<String, Object> atributos = new HashMap<>();
                Estudiante estudiante = null;

                for (Estudiante est : estudiantes) {
                    if (est.getMatricula() == Integer.parseInt(req.params("matricula"))) {
                        estudiante = est;
                    }
                }

                if (estudiante == null) {
                    throw new Exception();
                }

                atributos.put("estudiante", estudiante);
                template.process(atributos, writer);
                return writer;
            } catch (Exception error) {
                res.status(404);

                StringWriter writer = new StringWriter();
                Template template = configuration.getTemplate("templates/404.ftl");
                template.process(null, writer);

                res.body(writer.toString());
                return writer;
            }
        });

        //Estableciendo la ruta para ejecutar el borrado del estudiante
        post("/estudiante/borrar/:matricula", (req, res) -> {
            int matricula = Integer.parseInt(req.params("matricula"));

            Estudiante estudiante = null;
            for (Estudiante est : estudiantes) {
                if (est.getMatricula() == matricula) {
                    estudiante = est;
                }
            }

            estudiantes.remove(estudiante);

            res.redirect("/");
            return null;
        });
    }
}
