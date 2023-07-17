package operative_system;

import java.util.List;

public interface FilesystemInterface {
    void addDrive(String letter, String name, int capacity);
    void register(String username);
    void login(String username);
    void logout();
    void switchDrive(String letter);
    void mkdir(String folderName);
    void mkdir(String folderName, List<String> securityAtributes);
    void cd(String ruta);
}
