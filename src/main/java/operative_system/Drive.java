package operative_system;

import java.util.ArrayList;
import java.util.List;

public class Drive implements DriveInterface{
    private String name;
    private String letter;
    private List<FolderInterface> directories;
    private int capacity;

    /**
     * Constructor de Drive
     * @param letter - Letra del drive
     * @param name - Nombre del drive
     * @param capacity - Capacidad del drive
     */
    public Drive(String letter, String name, int capacity) {
        this.name = name;
        this.letter = letter;
        this.directories = new ArrayList<>();
        this.capacity = capacity;
        // Se inserta una carpeta por defecto en Directories para "/"
        FolderInterface carpetaRoot = new Folder("/", null, "/");
        directories.add(carpetaRoot);
    }

    /**
     * ToString de Drive
     * @return - String con los atributos de Drive
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
     * Obtiene la letra de un Drive
     * @return - Letra de una unidad
     */
    public String getLetter() {
        return letter;
    }

    /**
     * Obtiene los directorios de un Drive
     * @return - Lista de directorios
     */
    public List<FolderInterface> getDirectories() {
        return directories;
    }

    /**
     * Inserta carpeta en directorio
     * @param newFolder - Nueva carpeta
     */
    public void setFolderInDrive(FolderInterface newFolder){
        directories.add(newFolder);
    }

}
