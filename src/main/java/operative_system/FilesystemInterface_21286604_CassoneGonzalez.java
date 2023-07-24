package operative_system;

import java.util.List;

public interface FilesystemInterface_21286604_CassoneGonzalez {
    void addDrive(String letter, String name, int capacity);
    void register(String username);
    void login(String username);
    void logout();
    void switchDrive(String letter);
    void mkdir(String folderName);
    void mkdir(String folderName, List<String> securityAtributes);
    void cd(String ruta);
    void addFile(FileInterface_21286604_CassoneGonzalez nuevoArchivo);
    void del(String fileNamePattern);
    void copy(String source, String targetPath);
    void move(String source, String targetPath);
    List<FolderInterface_21286604_CassoneGonzalez> getDirectories();
}
