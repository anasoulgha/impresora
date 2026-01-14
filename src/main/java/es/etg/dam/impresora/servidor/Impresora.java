package es.etg.dam.impresora.servidor;

public class Impresora {

    public static final String MSG_IMPRIMIR = "Cliente pide imprimir ";
    public static final String MSG_IMPRIMIR_FIN = " hojas";
    public static final String MSG_TINTA = "Tinta: trabajo de ";
    public static final String MSG_TINTA_FIN = " procesado";

    private boolean ocupado = false;
    private int hojas = 0;
    private String tipo = "";
    private Conexion conexion;

    public synchronized void pedirImpresion(int hojas, String tipo, Conexion conexion) {
        while (ocupado) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        this.hojas = hojas;
        this.tipo = tipo;
        this.conexion = conexion;

        System.out.println(MSG_IMPRIMIR + hojas + " " + tipo + MSG_IMPRIMIR_FIN);

        ocupado = true;
        notify();
    }

    public synchronized void procesar(Tinta tinta) {
        while (!ocupado) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }

        String resultado = tinta.gastar(hojas, tipo);

        try {
            conexion.enviar(resultado);
            conexion.close();
        } catch (Exception e) {
            System.out.println("Error enviando respuesta");
        }

        System.out.println(MSG_TINTA + hojas + " " + tipo + MSG_TINTA_FIN);

        ocupado = false;
        notify();
    }
}
