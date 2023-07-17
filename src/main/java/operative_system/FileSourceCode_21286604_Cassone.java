package operative_system;

import java.util.List;

public class FileSourceCode extends File{
    /**
     * Constructor de un archivo de código fuente
     * @param nombre - Nombre del archivo
     * @param tipo - Tipo de archivo
     * @param contenido - Contenido del archivo
     */
    public FileSourceCode(String nombre, String tipo, String contenido) {
        super(nombre, tipo, contenido);
    }

    /**
     * Constructor de un archivo de código fuente con atributos de seguridad
     * @param nombre - Nombre del archivo
     * @param tipo - Tipo de archivo
     * @param contenido - Contenido del archivo
     * @param atributosSeguridad - Atributos de seguridad
     */
    public FileSourceCode(String nombre, String tipo, String contenido, List<String> atributosSeguridad) {
        super(nombre, tipo, contenido, atributosSeguridad);
    }
}
