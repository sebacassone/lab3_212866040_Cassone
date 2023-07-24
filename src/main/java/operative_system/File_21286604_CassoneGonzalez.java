package operative_system;

import java.util.ArrayList;
import java.util.List;

public abstract class File_21286604_CassoneGonzalez implements FileInterface_21286604_CassoneGonzalez {
    private String nombre;
    private String tipo;
    private String contenido;
    private List<String> atributosSeguridad;

    /**
     * Constructor de un archivo
     * @param nombre - Nombre de un archivo
     * @param tipo - Tipo de archivo
     * @param contenido - Contenido del archivo
     */
    public File_21286604_CassoneGonzalez(String nombre, String tipo, String contenido) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.contenido = contenido;
        this.atributosSeguridad = new ArrayList<>();
    }

    /**
     * Constructor de un archivo
     * @param nombre - Nombre del archivo
     * @param tipo - Tipo de archivo
     * @param contenido - Contenido del archivo
     * @param atributosSeguridad - Atributos de seguridad
     */
    public File_21286604_CassoneGonzalez(String nombre, String tipo, String contenido, List<String> atributosSeguridad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.contenido = contenido;
        this.atributosSeguridad = atributosSeguridad;
    }

    /**
     * ToString de File_21286604_CassoneGonzalez
     * @return - String con los atributos de File_21286604_CassoneGonzalez
     */
    @Override
    public String toString() {
        return "File{" +
                "nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", contenido='" + contenido + '\'' +
                ", atributosSeguridad=" + atributosSeguridad +
                '}';
    }

    /**
     * Obtiene el nombre de un archivo
     * @return - Nombre del archivo
     */
    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
