package es.etg.dam.impresora.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



   
public class Servidor {

    static final int PUERTO = 8888;

    public static void main(String[] args)throws IOException {

        
            Impresora impresora = new Impresora();
            Tinta tinta = new Tinta(impresora);

            Thread hiloTinta = new Thread(tinta);
            hiloTinta.start();

            ServerSocket server = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando en puerto " + PUERTO);

            while (true) {
                Socket socket = server.accept();
                Conexion conexion = new Conexion(socket);

                String peticion = conexion.recibir();
                String[] partes = peticion.split("_");

                int hojas = Integer.parseInt(partes[0]);
                String tipo = partes[1];

                impresora.pedirImpresion(hojas, tipo, conexion);
            }
  
    }
}

