package operative_system;

import java.util.ArrayList;
import java.util.List;

public class Drive_21286604_CassoneGonzalez implements DriveInterface_21286604_CassoneGonzalez {
    private String name;
    private String letter;
    private List<FolderInterface_21286604_CassoneGonzalez> directories;
    private int capacity;

    /**
     * Constructor de Drive_21286604_CassoneGonzalez
     * @param letter - Letra del drive
     * @param name - Nombre del drive
     * @param capacity - Capacidad del drive
     */
    public Drive_21286604_CassoneGonzalez(String letter, String name, int capacity) {
        this.name = name;
        this.letter = letter;
        this.directories = new ArrayList<>();
        this.capacity = capacity;
        // Se inserta una carpeta por defecto en Directories para "/"
        FolderInterface_21286604_CassoneGonzalez carpetaRoot = new Folder_21286604_CassoneGonzalez("/", null, "/");
        directories.add(carpetaRoot);
    }

    /**
     * ToString de Drive_21286604_CassoneGonzalez
     * @return - String con los atributos de Drive_21286604_CassoneGonzalez
     */
    @Override
    public String toString() {
        return "Drive{" +
                "name='" + name + '\'' +
                ", letter='" + letter + '\'' +
                ", directories=" + directories +
                ", capacity=" + capacity +
                '}';
    }

    /**
     * Obtiene la letra de un Drive_21286604_CassoneGonzalez
     * @return - Letra de una unidad
     */
    public String getLetter() {
        return letter;
    }

    /**
     * Obtiene los directorios de un Drive_21286604_CassoneGonzalez
     * @return - Lista de directorios
     */
    public List<FolderInterface_21286604_CassoneGonzalez> getDirectories() {
        return directories;
    }

    /**
     * Inserta carpeta en directorio
     * @param newFolder - Nueva carpeta
     */
    public void setFolderInDrive(FolderInterface_21286604_CassoneGonzalez newFolder){
        directories.add(newFolder);
    }

}
