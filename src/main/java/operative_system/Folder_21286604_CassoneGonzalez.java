package operative_system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Folder_21286604_CassoneGonzalez implements FolderInterface_21286604_CassoneGonzalez {
    private List<FileInterface_21286604_CassoneGonzalez> Archivos;
    private String name;
    private String userCreator;
    private Date fechaCreacion;
    private Date ultimaModificacion;
    private List<String> atributosSeguridad;
    private String pathFolder;

    /**
     * Constructor con atributos de seguirdad de una carpeta
     * @param name - Nombre de una carpeta
     * @param userCreator - Creador de la carpeta
     * @param atributosSeguridad - Atributos de seguridad de la carpeta
     * @param pathFolder - Path de la carpeta
     */
    public Folder_21286604_CassoneGonzalez(String name, String userCreator, List<String> atributosSeguridad, String pathFolder) {
        Archivos = new ArrayList<>();
        this.name = name;
        this.userCreator = userCreator;
        this.fechaCreacion = new Date();
        this.ultimaModificacion = new Date();
        this.atributosSeguridad = atributosSeguridad;
        this.pathFolder = pathFolder;
    }

    /**
     * Constructor sin atributos de seguridad de una carpeta
     * @param name - Nombre de la carpeta
     * @param userCreator - Creador de la carpeta
     * @param pathFolder - En donde se crea la carpeta
     */
    public Folder_21286604_CassoneGonzalez(String name, String userCreator, String pathFolder) {
        Archivos = new ArrayList<>();
        this.name = name;
        this.userCreator = userCreator;
        this.fechaCreacion = new Date();
        this.ultimaModificacion = new Date();
        this.atributosSeguridad = new ArrayList<>();
        this.pathFolder = pathFolder;
    }

    /**
     * Obtiene el nombre de una carpeta
     * @return -  Nombre de la carpeta
     */
    public String getName() {
        return name;
    }

    /**
     * Obtiene el path de una carpeta
     * @return - Path de una carpeta
     */
    public String getPathFolder() {
        return pathFolder;
    }

    /**
     * ToString de Folder_21286604_CassoneGonzalez
     * @return - String con los atributos
     */
    @Override
    public String toString() {
        return "Folder" +
                "Archivos=" + Archivos +
                ", name='" + name + '\'' +
                ", userCreator='" + userCreator + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", ultimaModificacion=" + ultimaModificacion +
                ", atributosSeguridad=" + atributosSeguridad +
                ", pathFolder='" + pathFolder + '\'' +
                '}';
    }

    /**
     * Obtiene los archivos de una carpeta
     * @return - Archivos de una carpeta
     */
    public List<FileInterface_21286604_CassoneGonzalez> getArchivos() {
        return Archivos;
    }

    /**
     * Checkea si en una carpeta
     * @param Name - Nombre del archivo
     * @param Type - Tipo del archivo
     * @return - Valor de verdad si existe un archivo con ese nombre
     */
    public Boolean checkDuplicateFilesInAFile(String Name, String Type){
        Boolean isDuplicate = false;
        for (FileInterface_21286604_CassoneGonzalez archivo: Archivos){
            if (archivo.getNombre().equals(Name)){
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }

    /**
     * Elimina un archivo en una carpeta
     * @param Name - Nombre del archivo
     */
    public void deleteFileInFolder(String Name, String Type){
        FileInterface_21286604_CassoneGonzalez archivoEncontrado = null;
        for (FileInterface_21286604_CassoneGonzalez archivo: Archivos){
            if (archivo.getNombre().equals(Name) && archivo.getTipo().equals(Type)){
                archivoEncontrado = archivo;
            }
        }
        Archivos.remove(archivoEncontrado);
    }

    /**
     * Elimina todos archivos de un directorio
     */
    public void deleteAllFilesInFolder(){
        Archivos.clear();
    }

    public List<String> getAllNamesFiles(){
        List<String> nombres = new ArrayList<>();
        for (FileInterface_21286604_CassoneGonzalez archivo: Archivos){
            nombres.add(archivo.getNombre() + archivo.getTipo());
        }
        return nombres;
    }

    public void deleteFilesinListFile(List<String> nombres){
        String tipo;
        for (String nombre: nombres){
            nombre = nombre.substring(0, nombre.indexOf("."));
            tipo = nombre.substring(nombre.indexOf("."), nombre.length());
            this.deleteFileInFolder(nombre, tipo);
        }
    }

    public List<FileInterface_21286604_CassoneGonzalez> getFilesInListFiles(List<String> nombres){
        List<FileInterface_21286604_CassoneGonzalez> carpetasEncontradas = new ArrayList<>();
        for (FileInterface_21286604_CassoneGonzalez archivo: Archivos){
            if (nombres.contains(archivo.getNombre() + archivo.getTipo())){
                carpetasEncontradas.add(archivo);
            }
        }
        return carpetasEncontradas;
    }

    public void setName(String name) {
        this.name = name;
    }
}
