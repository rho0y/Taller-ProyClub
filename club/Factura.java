package club;

public class Factura {
    private String concepto;
    private double valor;
    private String nombre;

    public Factura(String pNombre, String pConcepto, double pValor) {
        try {
            nombre = pNombre;
            concepto = pConcepto;
            valor = pValor;

            if(pNombre == null || pNombre.isEmpty()) {
                throw new Exception("Nombre vacio");
            }
            if(pConcepto == null || pConcepto.isEmpty()) {
                throw new Exception("Concepto vacio");
            }
            if(pValor <= 0) {
                throw new Exception("Valor invalido");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String darConcepto() {
        return concepto;
    }

    public double darValor() {
        return valor;
    }

    public String darNombre() {
        return nombre;
    }

    public String toString() {
        String factura = concepto + " $" + valor + " (" + nombre + ")";
        return factura;
    }
}