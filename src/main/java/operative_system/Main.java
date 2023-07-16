package operative_system;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Filesystem s1 = new Filesystem("ironman");
        System.out.println(s1);
        s1.addDrive("A", "Pepino", 1000);
        s1.addDrive("A", "Pepin2o", 1000);
        s1.register("Pedro");
        s1.register("Pedro");
        s1.register("Seba");
        s1.login("Seba");
        s1.login("Pedro");
        s1.logout();
        s1.login("Pedro");
        s1.logout();
        s1.login("Mariana");
        s1.login("Seba");
        s1.addDrive("B", "Pedro Pascal", 60000);
        s1.switchDrive("B");
        s1.switchDrive("A");
        s1.mkdir("Juanito");
        List<String> Atributes = new ArrayList<>();
        Atributes.add("h");
        s1.mkdir("Pepino1", Atributes);
        s1.cd("../../../../Pepino1");
        FileInterface nuevoArchivo = new FileDocument("Pep1", ".docx","Peo");
        FileInterface nuevoArchivo2 = new FileDocument("Pep1", ".docx","Pichi");
        FileInterface nuevoArchivo3 = new FileDocument("Pep2", ".py","Pichi");
        FileInterface nuevoArchivo4 = new FileDocument("Pep3", ".jar","Pichi");
        FileInterface nuevoArchivo5 = new FileDocument("8", ".docx","Pichi");
        FileInterface nuevoArchivo6 = new FileDocument("Pep1", ".java","Pichi");
        System.out.println(nuevoArchivo);
        s1.addFile(nuevoArchivo);
        s1.addFile(nuevoArchivo2);
        s1.addFile(nuevoArchivo3);
        s1.addFile(nuevoArchivo4);
        s1.addFile(nuevoArchivo5);
        s1.addFile(nuevoArchivo6);
        s1.cd("/");
        s1.addFile(nuevoArchivo);
        s1.addFile(nuevoArchivo2);
        s1.cd("/");
        s1.copy("Pepino1", "B:/");
        s1.cd("/");
        s1.move("Pepino1", "A:/Pepino1");
        s1.switchDrive("A");

        System.out.println(s1.getDirectories());
    }
}
