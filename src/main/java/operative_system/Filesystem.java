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
        if (FolderInterface.getDuplicityFolders(directories, folderName, pathActual)){
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
            if (pathActual.equals("/"))
                ruta = pathActual + ruta;
            else
                ruta = FolderInterface.convertNameToPath(ruta, pathActual);
        }
        if (!FolderInterface.getAllPathsOfFolders(directories).contains(ruta)){
            return;
        }
        pathActual = ruta;
    }

    /**
     * Obtiene la carpeta actual que corresponde de donde está actualmenete el path
     * @return - Carpeta actual
     */
    private FolderInterface getFolderInListFolderOfActuallyPath(){
        // Se inicializa path para buscar la carpeta
        String path;
        String nameFolder;
        if (pathActual == "/"){
            path = pathActual;
            nameFolder = pathActual;
        } else {
            // Obtener la posición del último "/"
            int indiceUltimoSlash = pathActual.lastIndexOf("/");
            // Obtener la subcadena que contiene el texto antes del último "/"
            path = pathActual.substring(0, indiceUltimoSlash + 1);
            nameFolder = pathActual.substring(indiceUltimoSlash + 1, pathActual.length());
            if (path.endsWith("/") && !path.equals("/"))
                path = path.substring(0, path.length() - 1);
        }
        FolderInterface folderActual = FolderInterface.getFolderInListFolder(directories, path, nameFolder);
        return folderActual;
    }

    /**
     * Añadae un archivo nuevo al path actual
     * @param nuevoArchivo - Nuevo archivo
     */
    public void addFile(FileInterface nuevoArchivo){
        // Verifica que haya una unidad montada o que haya un usuario logeado
        if (currentDrive == null || currentUser == null){
            return;
        }
        FolderInterface folderActual = this.getFolderInListFolderOfActuallyPath();
        // Inserta el archivo en la carpeta actual
        // Se reemplaza si ya existe en la carpeta un archivo con el mismo nombre
        if (folderActual.checkDuplicateFilesInAFile(nuevoArchivo.getNombre(), nuevoArchivo.getTipo())){
            folderActual.deleteFileInFolder(nuevoArchivo.getNombre(), nuevoArchivo.getTipo());
        }
        folderActual.getArchivos().add(nuevoArchivo);
    }

    /**
     * Elimina un archivo, un conjunto de archivos según un patrón o una carpeta.
     * @param fileNamePattern - Lo que se va a eliminar
     */
    public void del(String fileNamePattern){
        // Verifica que haya una unidad montada o que haya un usuario logeado
        if (currentDrive == null || currentUser == null){
            return;
        }
        FolderInterface carpetaActual = this.getFolderInListFolderOfActuallyPath();
        if (fileNamePattern.equals("*.*")){
            carpetaActual.deleteAllFilesInFolder();
        }
        else if (fileNamePattern.contains("*")){
            List<String> nombres = carpetaActual.getAllNamesFiles();
            if (fileNamePattern.indexOf("*") > 0)
                nombres = FolderInterface.getAllNamesStartWithAsterik(nombres,fileNamePattern);
            if (fileNamePattern.indexOf("*") < fileNamePattern.length() - 1)
                nombres = FolderInterface.getAllNamesEndWithAsterik(nombres,fileNamePattern);
            carpetaActual.deleteFilesinListFile(nombres);
        }
        else if (fileNamePattern.contains(".")){
            String nombre = fileNamePattern.substring(0, fileNamePattern.indexOf("."));
            String tipo = fileNamePattern.substring(fileNamePattern.indexOf("."), fileNamePattern.length());
            if (carpetaActual.checkDuplicateFilesInAFile(nombre, tipo))
                carpetaActual.deleteFileInFolder(nombre, tipo);
        }
        else {
            directories.remove(FolderInterface.getFolderInListFolder(directories, pathActual, fileNamePattern));
        }
    }

    public void copy(String source, String targetPath){
        // Verifica que haya una unidad montada o que haya un usuario logeado
        if (currentDrive == null || currentUser == null){
            return;
        }
        FolderInterface carpetaActual = this.getFolderInListFolderOfActuallyPath();
        List<FileInterface> archivosACopiar = null;
        FolderInterface carpetaACopiar = null;
        if (source.equals("*.*")){
            archivosACopiar = carpetaActual.getArchivos();
        } else if (source.contains("*")){
            List<String> nombres = carpetaActual.getAllNamesFiles();
            if (source.indexOf("*") > 0)
                nombres = FolderInterface.getAllNamesStartWithAsterik(nombres,source);
            if (source.indexOf("*") < source.length() - 1)
                nombres = FolderInterface.getAllNamesEndWithAsterik(nombres,source);
            archivosACopiar = carpetaActual.getFilesInListFiles(nombres);
        } else if (source.contains(".")){
            List<String> nombres = new ArrayList<>();
            String nombre = source.substring(0, source.indexOf("."));
            String tipo = source.substring(source.indexOf("."), source.length());
            nombres.add(nombre);
            if (carpetaActual.checkDuplicateFilesInAFile(nombre, tipo))
                archivosACopiar = carpetaActual.getFilesInListFiles(nombres);
        } else {
            carpetaACopiar = FolderInterface.getFolderInListFolder(directories, pathActual, source);
        }
        String path = targetPath.substring(targetPath.indexOf(":") + 1, targetPath.length());
        String driveDestiny = targetPath.substring(0, targetPath.indexOf(":"));
        this.switchDrive(driveDestiny);
        this.cd(path);
        if (archivosACopiar != null){
            for(FileInterface archivo: archivosACopiar){
                addFile(archivo);
            }
        } else if (carpetaACopiar != null) {
            mkdir(carpetaACopiar.getName());
            this.switchDrive(driveDestiny);
            this.cd(path);
            cd(carpetaACopiar.getName());
            List<FileInterface> archivos = carpetaACopiar.getArchivos();
            for(FileInterface archivo: archivos){
                addFile(archivo);
            }
        }
    }

    public void move(String source, String targetPath){
        if (currentDrive == null || currentUser == null){
            return;
        }
        String pathAntiguo = pathActual;
        String unidadAntigua = currentDrive;
        copy(source,targetPath);
        switchDrive(unidadAntigua);
        switchDrive(pathAntiguo);
        del(source);
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

    public List<FolderInterface> getDirectories() {
        return directories;
    }
}
