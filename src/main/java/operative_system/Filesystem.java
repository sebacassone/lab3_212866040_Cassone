package operative_system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Filesystem implements FilesystemInterface {
    private final String name;
    private List<String> users;
    private List<DriveInterface> unidades;
    private String currentUser;
    private String currentDrive;
    private String pathActual;
    private List<FolderInterface> directories;
    private final Date fechaCreacion;
    private Date fechaModificacion;

    /**
     *
     * @param name - Nombre del filesystem
     */
    public Filesystem(String name){
        this.name = name;
        this.users = new ArrayList<>();
        unidades = new ArrayList<>();
        this.directories = new ArrayList<>();
        this.fechaCreacion = new Date();
        this.fechaModificacion = new Date();
    }

    /**
     * Agrega al filesystem un nuevo drive
     * @param letter - Letra de la unidad a crear
     * @param name - Nombre de la unidad a crear
     * @param capacity - Capacidad del drive a crear
     */
    public void addDrive(String letter, String name, int capacity){
        if (DriveInterface.getAllLetters(unidades).contains(letter)){
            return;
        }
        DriveInterface nuevoDrive = new Drive(letter, name, capacity);
        unidades.add(nuevoDrive);
        this.fechaModificacion = new Date();
    }

    @Override
    public String toString() {
        return "Filesystem{" +
                "name='" + name + '\'' +
                ", users=" + users +
                ", Drive=" + unidades +
                ", currentUser='" + currentUser + '\'' +
                ", currentDrive='" + currentDrive + '\'' +
                ", pathActual='" + pathActual + '\'' +
                ", directories=" + directories +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }

}
