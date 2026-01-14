package es.etg.dam.impresora.servidor;

public class Tinta implements Runnable {

    private int color = 100;
    private int bn = 100;
    private Impresora impresora;

    public Tinta(Impresora impresora) {
        this.impresora = impresora;
    }

    public synchronized String gastar(int hojas, String tipo) {

        if (tipo.equals("C")) {
            if (hojas > color) return "ERROR: No hay tinta suficiente en color";
            color -= hojas;
            return "OK Precio: " + (hojas * 1.0) + "€";
        }

        if (tipo.equals("B")) {
            if (hojas > bn) return "ERROR: No hay tinta suficiente en blanco y negro";
            bn -= hojas;
            return "OK Precio: " + (hojas * 0.5) + "€";
        }

        return "ERROR: Tipo no reconocido";
    }

    @Override
    public void run() {
        while (true) {
            impresora.procesar(this);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
