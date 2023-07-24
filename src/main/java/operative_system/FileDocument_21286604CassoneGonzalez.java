package operative_system;

import java.util.List;

public class FileDocument_21286604CassoneGonzalez extends File_21286604_CassoneGonzalez {
    /**
     * Constructor de un archivo de documento
     * @param nombre - Nombre del documento
     * @param tipo - Tipo de documento
     * @param contenido - Contenido del documento
     */
    public FileDocument_21286604CassoneGonzalez(String nombre, String tipo, String contenido) {
        super(nombre, tipo, contenido);
    }

    /**
     * Constructor de un archivo de un documento con atributos de seguridad
     * @param nombre - Nombre de un documento
     * @param tipo - Tipo de documento
     * @param contenido - Contenido del documento
     * @param atributosSeguridad - Atributos de seguridad de la carpeta
     */
    public FileDocument_21286604CassoneGonzalez(String nombre, String tipo, String contenido, List<String> atributosSeguridad) {
        super(nombre, tipo, contenido, atributosSeguridad);
    }
}
