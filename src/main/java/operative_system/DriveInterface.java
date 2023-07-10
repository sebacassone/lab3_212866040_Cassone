package operative_system;

import java.util.ArrayList;
import java.util.List;

public interface DriveInterface {
    String getLetter();
    List<FolderInterface> getDirectories();
    void setFolderInDrive(FolderInterface newFolder);

    /**
     * A partir de una lista de unidades, obtiene las letras de las unidades
     * @param unidades - Lista de unidades
     * @return - Lista de letras
     */
    static List<String> getAllLetters(List<DriveInterface> unidades) {
        // Verifica que la letra exista y que el usuario esté logeado
        List<String> letras = new ArrayList<>();

        // Comienza a recorrar la lista de unidades y obtiene las letras
        for (DriveInterface unidad: unidades){
            letras.add(unidad.getLetter());
        }
        return letras;
    }

    /**
     * Obtiene un drive en una lista de drives
     * @param unidades - Lista de drives
     * @param letter - Letra del drive que se quiere obtener
     * @return - Drive pedido
     */
    static DriveInterface getDriveInListDrive(List<DriveInterface> unidades, String letter) {
        DriveInterface unidadRequerida = null;

        for (DriveInterface unidad: unidades){
            // Si es la letra que se desea lo obtiene de inmediato
            if (unidad.getLetter().equals(letter)){
                unidadRequerida = unidad;
            }
        }
        return unidadRequerida;
    }
}
