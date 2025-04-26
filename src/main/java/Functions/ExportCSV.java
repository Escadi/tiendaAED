package Functions;
import Class.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import Models.ModelUsuarios;

public class ExportCSV {
    public void ExportUsuarios(){
        List<Usuarios> usuariosList = ModelUsuarios.getUsuarios();

        try {
            BufferedWriter ficheroCSVUsuarios = new BufferedWriter(new FileWriter("Usuarios.csv"));
            ficheroCSVUsuarios.write("ID,Nombre,Apellidos,Email\n");
            for (Usuarios usuario : usuariosList){
                ficheroCSVUsuarios.write(usuario.csvFormat()+"\n");
            }

            ficheroCSVUsuarios.close();
        } catch (IOException e) {
        }

    }

}



