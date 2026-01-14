package es.etg.dam.impresora.cliente;
import java.io.IOException;
import java.net.Socket;

import es.etg.dam.impresora.servidor.Conexion;

public class Cliente {

    static final int PUERTO = 8888;
    static final String HOST = "localhost";

    public static void main(String[] args) {

     

        String mensaje = args[0] + "_" + args[1];

        try {
            Socket socket = new Socket(HOST, PUERTO);
            Conexion conexion = new Conexion(socket);

            conexion.enviar(mensaje);

            String respuesta = conexion.recibir();
            System.out.println("Respuesta del servidor: " + respuesta);

            conexion.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
