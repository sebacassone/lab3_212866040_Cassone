package operative_system;

import java.util.ArrayList;
import java.util.List;

public interface FolderInterface {
    String getName();
    String getPathFolder();
    List<FileInterface> getArchivos();
    Boolean checkDuplicateFilesInAFile(String Name);
    void deleteFileInFolder(String Name);

    // Métodos estáticos pertenecientes a la interfaz que se utilizan para cd

    /**
     * Se utiliza para obtener todos los paths de una lista de carpetas
     * @param Folders - Lista de carpetas
     * @return - Lista de Paths
     */
    static List<String> getAllPathsOfFolders(List<FolderInterface> Folders){
        List<String> Paths = new ArrayList<>();
        Paths.add("/");
        for(FolderInterface carpeta: Folders){
            if(carpeta.getPathFolder().equals("/")){
                Paths.add(carpeta.getPathFolder() + carpeta.getName());
            } else {
                Paths.add(carpeta.getPathFolder() + "/" + carpeta.getName());
            }
        }
        return Paths;
    }

    /**
     * Convierte el nombre de una carpeta en un path
     * @param name - Nombre de una carpeta
     * @param path -  Path
     * @return - Path completo para la carpeta
     */
    static String convertNameToPath(String name, String path){
        return path + "/" + name;
    }

    /**
     * Método recursivo para eliminar los "../" de las rutas y obtener una ruta final
     * @param path - Path actual
     * @param newPath - Dirección que se quiere ir.
     * @return - Path convertido
     */
    static String deletePoints(String path, String newPath){
        // Verificar si el path comienza con ".." o "."
        if (newPath.startsWith("..")) {
            // Eliminar los primeros dos caracteres (..)
            newPath = newPath.substring(3);
            // Obtener la posición del último "/"
            int indiceUltimoSlash = path.lastIndexOf("/");

            // Verificar si se encontró un "/" en el texto
            if (indiceUltimoSlash >= 0) {
                // Obtener la subcadena que contiene el texto antes del último "/"
                path = path.substring(0, indiceUltimoSlash);
            }
            if (path.isEmpty()){
                path = "/";
            }
            return deletePoints(path, newPath);
        }
        else if (newPath.startsWith(".")) {
            // Eliminar el primer carácter (.)
            newPath = newPath.substring(2);
            return deletePoints(path, newPath);
        } else {
            if (path.equals("/")){
                path = path + newPath;
            } else {
                path = path + "/" + newPath;
            }
            return path;
        }
    }

    /**
     * Verifica si un path termina en "/" para poder eliminarlo después.
     * @param path - Path que se quiere analizar.
     * @return - Valor de verdad.
     */
    static Boolean verifyFinalSlash(String path){
        return path.endsWith("/");
    }

    // Método que se utilizará para addFile.

    /**
     * A partir de una lista de carpetas, encuentra en esta una carpeta según el path
     * @param carpetas - Lista de carpetas
     * @param path - Path de la carpeta que se requiere
     * @param nameFolder - Nombre de la carpeta
     * @return - Archivo que se busca
     */
    static FolderInterface getFolderInListFolder(List<FolderInterface> carpetas, String path, String nameFolder){
        FolderInterface carpetaEncontrada = null;
        for (FolderInterface carpeta: carpetas){
            if (carpeta.getPathFolder().equals(path) && carpeta.getName().equals(nameFolder)){
                carpetaEncontrada = carpeta;
            }
        }
        return carpetaEncontrada;
    }
}
