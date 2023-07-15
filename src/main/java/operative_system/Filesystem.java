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

    /**
     * Crea una carpeta en filesystem y lo inserta en el drive actual.
     * @param folderName - Nombre de la carpeta a crear.
     */
    public void mkdir(String folderName){
        // Verifica que haya una unidad montada o que haya un usuario logeado
        if (currentDrive == null || currentUser == null){
            return;
        }
        FolderInterface nuevaCarpeta = new Folder(folderName, currentUser, pathActual);
        // Inserta la carpeta en la unidad
        DriveInterface driveActual = DriveInterface.getDriveInListDrive(unidades, currentDrive);
        driveActual.setFolderInDrive(nuevaCarpeta);
        switchDrive(currentDrive);
    }

    /**
     * Crea una carpeta en filesystem con parametros de seguridad, lo inserta en el drive actual
     * @param folderName -  Nombre de la carpeta
     * @param securityAtributes - Atributos de seguridad
     */
    public void mkdir(String folderName, List<String> securityAtributes){
        // Verifica que haya una unidad montada o que haya un usuario logeado
        if (currentDrive == null || currentUser == null){
            return;
        }
        FolderInterface nuevaCarpeta = new Folder(folderName, currentUser, securityAtributes, pathActual);
        // Inserta la carpeta en la unidad
        DriveInterface driveActual = DriveInterface.getDriveInListDrive(unidades, currentDrive);
        driveActual.setFolderInDrive(nuevaCarpeta);
        switchDrive(currentDrive);
    }

    /**
     * Cambia la ruta del filesystem
     * @param ruta - ruta a ingresar
     */
    public void cd(String ruta){
        // Verifica que esta ruta existe
        if (FolderInterface.verifyFinalSlash(ruta) && !ruta.equals("/")){
            ruta = ruta.substring(0, ruta.length() - 1);
        }
        if (ruta.contains(".") || ruta.contains("..")){
            ruta = FolderInterface.deletePoints(pathActual, ruta);
        }
        if (!ruta.contains("/") || !(ruta.charAt(0) == '/')){
            ruta = FolderInterface.convertNameToPath(ruta, pathActual);
        }
        if (!FolderInterface.getAllPathsOfFolders(directories).contains(ruta)){
            return;
        }
        pathActual = ruta;
    }

    public void addFile(FileInterface nuevoArchivo){
        // Verifica que haya una unidad montada o que haya un usuario logeado
        if (currentDrive == null || currentUser == null){
            return;
        }
        // Se inicializa path para buscar la carpeta
        String path;
        String nameFolder;
        // Inserta el archivo en la carpeta actual
        List<FolderInterface> directorioActual = DriveInterface.getDriveInListDrive(unidades, currentDrive).getDirectories();
        if (pathActual == "/"){
            path = pathActual;
            nameFolder = pathActual;
        } else {
            // Obtener la posición del último "/"
            int indiceUltimoSlash = pathActual.lastIndexOf("/");
            // Obtener la subcadena que contiene el texto antes del último "/"
            path = pathActual.substring(0, indiceUltimoSlash + 1);
            nameFolder = pathActual.substring(indiceUltimoSlash + 1, pathActual.length());
        }
        FolderInterface folderActual = FolderInterface.getFolderInListFolder(directories, path, nameFolder);
        // Se reemplaza si ya existe en la carpeta un archivo con el mismo nombre
        if (folderActual.checkDuplicateFilesInAFile(nuevoArchivo.getNombre())){
            folderActual.deleteFileInFolder(nuevoArchivo.getNombre());
        }
        folderActual.getArchivos().add(nuevoArchivo);
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
