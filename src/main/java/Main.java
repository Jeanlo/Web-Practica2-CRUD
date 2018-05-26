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
            Map<String, Object> atributos = new HashMap<>();
            Template template = configuration.getTemplate("templates/index.ftl");

            atributos.put("estudiantes", estudiantes);
            template.process(atributos, writer);

            return writer;
        });

        //Agrupando todas las rutas /estudiante/* para simplificar su establecimiento
        path("/estudiante", () -> {
            //Estableciendo la ruta para mostrar el formulario de agregar estudiantes
            get("/agregar", (req, res) -> {
                StringWriter writer = new StringWriter();
                Template template = configuration.getTemplate("templates/agregar-estudiante.ftl");

                template.process(null, writer);

                return writer;
            });

            //Estableciendo la ruta para guardar el estudiante a agregar
            post("/agregar", (req, res) -> {
                int matricula = Integer.parseInt(req.queryParams("matricula"));
                String nombre = req.queryParams("nombre");
                String apellido = req.queryParams("apellido");
                String telefono = req.queryParams("telefono");

                estudiantes.add(new Estudiante(matricula, nombre, apellido, telefono));

                res.redirect("/");

                return null;
            });

            //Estableciendo la ruta para mostrar los datos de un estudiante seleccionado
            get("/:matricula", (req, res) -> {
                try {
                    StringWriter writer = new StringWriter();
                    Map<String, Object> atributos = new HashMap<>();
                    Estudiante estudiante = null;
                    Template template = configuration.getTemplate("templates/estudiante.ftl");

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
                    error.printStackTrace();

                    return null;
                }
            });

            //Estableciendo la ruta para mostrar el formulario de editar estudiante
            get("/editar/:matricula", (req, res) -> {
                try {
                    StringWriter writer = new StringWriter();
                    Map<String, Object> atributos = new HashMap<>();
                    Estudiante estudiante = null;
                    Template template = configuration.getTemplate("templates/editar-estudiante.ftl");

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
                    error.printStackTrace();

                    return null;
                }
            });

            //Estableciendo la ruta para guardar los cambios editados en el estudiante
            post("/editar", (req, res) -> {
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
            get("/borrar/:matricula", (req, res) -> {
                try {
                    StringWriter writer = new StringWriter();
                    Map<String, Object> atributos = new HashMap<>();
                    Estudiante estudiante = null;
                    Template template = configuration.getTemplate("templates/borrar-estudiante.ftl");

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
                    error.printStackTrace();

                    return null;
                }
            });

            //Estableciendo la ruta para ejecutar el borrado del estudiante
            post("/borrar/:matricula", (req, res) -> {
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
        });

        notFound((req, res) -> {
            StringWriter writer = new StringWriter();
            Template template = configuration.getTemplate("templates/404.ftl");

            template.process(null, writer);
            res.status(404);
            res.body(writer.toString());

            return writer;
        });
    }
}
