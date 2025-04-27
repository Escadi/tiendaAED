package Functions;

import Class.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;

import Models.ModelUsuarios;
import javafx.scene.control.Alert;

public class ExportCSV {
    public void ExportUsuarios() {
        List<Usuarios> usuariosList = ModelUsuarios.getUsuarios();
        String descarga = System.getProperty("user.home") + File.separator + "Downloads";
        File archivo = new File(descarga, "Usuarios.csv");
        try {
            BufferedWriter ficheroCSVUsuarios = new BufferedWriter(new FileWriter(archivo));
            ficheroCSVUsuarios.write("ID,Nombre,Apellidos,Email\n");
            for (Usuarios usuario : usuariosList) {
                ficheroCSVUsuarios.write(usuario.csvFormat() + "\n");
            }

            ficheroCSVUsuarios.close();
        } catch (IOException e) {
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exportar CSV");
        alert.setHeaderText(null);
        alert.setContentText("El archivo CSV se ha exportado en : \n " + archivo.getAbsolutePath());
        alert.showAndWait();

    }

}



