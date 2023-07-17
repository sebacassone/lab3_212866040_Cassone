package operative_system;

import java.util.List;

public class FilePlainText extends File{
    /**
     * Constructor de un archivo de texto plano
     * @param nombre - Nombre del archivo
     * @param tipo - Tipo de archivo
     * @param contenido - Contenido del archivo
     */
    public FilePlainText(String nombre, String tipo, String contenido) {
        super(nombre, tipo, contenido);
    }

    /**
     * Constructor de un archivo de texto plano con atributos de seguridad
     * @param nombre - Nombre del archivo
     * @param tipo - Tipo de archivo
     * @param contenido - Contenido del archiv
     * @param atributosSeguridad - Atributos de seguridad
     */
    public FilePlainText(String nombre, String tipo, String contenido, List<String> atributosSeguridad) {
        super(nombre, tipo, contenido, atributosSeguridad);
    }
}
