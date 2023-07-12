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

     /**
     * Se usa para registrar a un usuario
     * @param username - Nombre del usuario a registrar
     */
    public void register(String username){
        if (users.contains(username)){
            return;
        }
        users.add(username);
    }

     /**
     * Cierra sesión de un usuario
     */
    public void logout(){
        this.currentUser = null;
    }

    /**
     * Monta la unidad requerida por la letra
     * @param letter - Letra de la unidad que se quiere montar
     */
    public void switchDrive(String letter){
        // Verifica que la letra exista y que el usuario esté logeado
        if (this.currentUser == null || !DriveInterface.getAllLetters(unidades).contains(letter)){
            return;
        }
        // Monta la unidad que se requiere
        DriveInterface unidadRequerida = DriveInterface.getDriveInListDrive(unidades, letter);
        this.currentDrive = unidadRequerida.getLetter();
        directories = unidadRequerida.getDirectories();
        this.pathActual = "/";
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
