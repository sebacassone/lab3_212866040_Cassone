package operative_system;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;
        Filesystem s1 = null;

        while (!salir) {
            System.out.println("### Manipulador de Sistema de Archivos ###");
            System.out.println("Escoja su opción:");
            System.out.println("1. Crear un Sistema de Archivos");
            System.out.println("2. Modificar un Sistema de Archivos");
            System.out.println("3. Visualizar Sistema de Archivos");
            System.out.println("4. Salir");


            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            switch (opcion) {
                case 1:
                    System.out.println("Opción 1 seleccionada: Crear un Sistema de Archivos");
                    System.out.println("Ingrese el nombre del sistema:");
                    String systemName = scanner.nextLine();
                    s1 = new Filesystem(systemName);
                    System.out.println(s1);
                    break;
                case 2:
                    if (s1 == null){
                        System.out.println("Cree un sistema primero");
                        break;
                    }
                    System.out.println("Opción 2 seleccionada: Modificar un Sistema de Archivos");
                    boolean volver = false;

                    while (!volver) {
                        System.out.println("Escoja una opción:");
                        System.out.println("1. addDrive");
                        System.out.println("2. register");
                        System.out.println("3. login");
                        System.out.println("4. logout");
                        System.out.println("5. switchDrive");
                        System.out.println("6. mkdir");
                        System.out.println("7. cd");
                        System.out.println("8. addFile");
                        System.out.println("9. del");
                        System.out.println("10. copy");
                        System.out.println("11. move");
                        System.out.println("12. Volver al menú principal");

                        int opcion2 = scanner.nextInt();
                        scanner.nextLine(); // Consumir el salto de línea

                        switch (opcion2) {
                            case 1:
                                System.out.println("Opción addDrive seleccionada");
                                System.out.println("Ingrese el nombre de la unidad de disco:");
                                String driveName = scanner.nextLine();
                                System.out.println("Ingrese el nombre del usuario:");
                                String userName = scanner.nextLine();
                                System.out.println("Ingrese el espacio disponible:");
                                int availableSpace = scanner.nextInt();
                                scanner.nextLine(); // Consumir el salto de línea
                                s1.addDrive(driveName, userName, availableSpace);
                                break;
                            case 2:
                                System.out.println("Opción register seleccionada");
                                System.out.println("Ingrese el nombre del usuario:");
                                String registerUser = scanner.nextLine();
                                s1.register(registerUser);
                                break;
                            case 3:
                                System.out.println("Opción login seleccionada");
                                System.out.println("Ingrese el nombre del usuario:");
                                String loginUser = scanner.nextLine();
                                s1.login(loginUser);
                                break;
                            case 4:
                                System.out.println("Opción logout seleccionada");
                                s1.logout();
                                break;
                            case 5:
                                System.out.println("Opción switchDrive seleccionada");
                                System.out.println("Ingrese el nombre de la unidad de disco:");
                                String switchDriveName = scanner.nextLine();
                                s1.switchDrive(switchDriveName);
                                break;
                            case 6:
                                System.out.println("Opción mkdir seleccionada");
                                System.out.println("Ingrese el nombre del directorio a crear:");
                                String directoryName = scanner.nextLine();
                                s1.mkdir(directoryName);
                                break;
                            case 7:
                                System.out.println("Opción cd seleccionada");
                                System.out.println("Ingrese el nombre del directorio al que desea cambiar:");
                                String newDirectory = scanner.nextLine();
                                s1.cd(newDirectory);
                                break;
                            case 8:
                                System.out.println("Opción addFile seleccionada");
                                System.out.println("Ingrese el nombre del archivo:");
                                String fileName = scanner.nextLine();
                                System.out.println("Ingrese la extensión del archivo:");
                                String extension = scanner.nextLine();
                                System.out.println("Ingrese el contenido del archivo:");
                                String content = scanner.nextLine();
                                FileInterface newFile = new FileDocument(fileName, extension, content);
                                s1.addFile(newFile);
                                break;
                            case 9:
                                System.out.println("Opción del seleccionada");
                                System.out.println("Ingrese el nombre del archivo o directorio a eliminar:");
                                String deleteName = scanner.nextLine();
                                s1.del(deleteName);
                                break;
                            case 10:
                                System.out.println("Opción copy seleccionada");
                                System.out.println("Ingrese el nombre del archivo o directorio a copiar:");
                                String source = scanner.nextLine();
                                System.out.println("Ingrese la ruta de destino:");
                                String destination = scanner.nextLine();
                                s1.copy(source, destination);
                                break;
                            case 11:
                                System.out.println("Opción move seleccionada");
                                System.out.println("Ingrese el nombre del archivo o directorio a mover:");
                                String moveName = scanner.nextLine();
                                System.out.println("Ingrese la ruta de destino:");
                                String moveDestination = scanner.nextLine();
                                s1.move(moveName, moveDestination);
                                break;
                            case 12:
                                System.out.println("Volviendo al menú principal");
                                volver = true;
                                break;
                            default:
                                System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                                break;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Opción 3 seleccionada: Visualizar Sistema de Archivos");
                    System.out.println(s1.getDirectories());
                    break;
                case 4:
                    System.out.println("Opción 4 seleccionada: Salir");
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
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


        System.out.println(s1);
    }
}
