package es.etg.dam.impresora.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Conexion {

    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public Conexion(Socket socket) throws IOException {
        this.socket = socket;
        this.input = new DataInputStream(socket.getInputStream());
        this.output = new DataOutputStream(socket.getOutputStream());
    }

    public void enviar(String mensaje) throws IOException {
        output.writeUTF(mensaje);
    }

    public String recibir() throws IOException {
        return input.readUTF();
    }

    public void close()throws IOException {
       
            socket.close();
      
    }
}
