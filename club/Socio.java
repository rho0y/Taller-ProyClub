package club;
import java.util.ArrayList;

public class Socio {
    public enum Tipo {
        VIP,
        REGULAR
    }

    public final static double FONDOS_INICIALES_REGULARES = 50;
    public final static double FONDOS_INICIALES_VIP = 100;
    public final static double MONTO_MAXIMO_REGULARES = 1000;
    public final static double MONTO_MAXIMO_VIP = 5000;

    private String cedula;
    private String nombre;
    private double fondos;
    private Tipo tipoSubscripcion;
    private ArrayList<Factura> facturas;
    private ArrayList<String> autorizados;

    public Socio(String pCedula, String pNombre, Tipo pTipo) {
        try {
            if(pCedula == null || pCedula.isEmpty()) {
                throw new Exception("Cedula vacia");
            }
            if(pNombre == null || pNombre.isEmpty()) {
                throw new Exception("Nombre vacio");
            }
            if(pTipo == null) {
                throw new Exception("Tipo nulo");
            }

            cedula = pCedula;
            nombre = pNombre;
            tipoSubscripcion = pTipo;

            switch(tipoSubscripcion) {
                case VIP:
                    fondos = FONDOS_INICIALES_VIP;
                    break;
                default:
                    fondos = FONDOS_INICIALES_REGULARES;
            }

            facturas = new ArrayList<Factura>();
            autorizados = new ArrayList<String>();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String darNombre() {
        return nombre;
    }

    public String darCedula() {
        return cedula;
    }

    public double darFondos() {
        return fondos;
    }

    public Tipo darTipo() {
        return tipoSubscripcion;
    }

    public ArrayList<Factura> darFacturas() {
        return facturas;
    }

    public ArrayList<String> darAutorizados() {
        return autorizados;
    }

    private boolean existeAutorizado(String pNombreAutorizado) {
        boolean encontro = false;
        try {
            for(int i = 0; i < autorizados.size() && !encontro; i++) {
                String a = autorizados.get(i);
                if(a.equals(pNombreAutorizado)) {
                    encontro = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return encontro;
    }

    private boolean tieneFacturaAsociada(String pNombreAutorizado) {
        boolean tiene = false;
        try {
            for(int i = 0; i < facturas.size() && !tiene; i++) {
                Factura factura = facturas.get(i);
                if(factura.darNombre().equals(pNombreAutorizado)) {
                    tiene = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return tiene;
    }

    public void aumentarFondos(double pFondos) {
        try {
            if(pFondos <= 0) {
                throw new Exception("Valor invalido");
            }

            if(tipoSubscripcion == Tipo.VIP && pFondos + fondos > MONTO_MAXIMO_VIP) {
                throw new Exception("Excede maximo VIP");
            }

            if(tipoSubscripcion == Tipo.REGULAR && pFondos + fondos > MONTO_MAXIMO_REGULARES) {
                throw new Exception("Excede maximo regular");
            }

            fondos = fondos + pFondos;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void registrarConsumo(String pNombre, String pConcepto, double pValor) {
        try {
            if(pNombre == null || pNombre.isEmpty()) {
                throw new Exception("Nombre vacio");
            }
            if(pConcepto == null || pConcepto.isEmpty()) {
                throw new Exception("Concepto vacio");
            }
            if(pValor <= 0) {
                throw new Exception("Valor invalido");
            }
            if(pValor > fondos) {
                throw new Exception("Fondos insuficientes");
            }

            Factura nuevaFactura = new Factura(pNombre, pConcepto, pValor);
            facturas.add(nuevaFactura);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void agregarAutorizado(String pNombreAutorizado) {
        try {
            if(pNombreAutorizado == null || pNombreAutorizado.isEmpty()) {
                throw new Exception("Nombre vacio");
            }

            if(pNombreAutorizado.equals(darNombre())) {
                throw new Exception("No puede agregar el socio como autorizado");
            }

            if(fondos == 0) {
                throw new Exception("Sin fondos");
            }

            if(existeAutorizado(pNombreAutorizado)) {
                throw new Exception("Autorizado existente");
            }

            autorizados.add(pNombreAutorizado);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void eliminarAutorizado(String pNombreAutorizado) {
        try {
            if(pNombreAutorizado == null || pNombreAutorizado.isEmpty()) {
                throw new Exception("Nombre vacio");
            }

            if(tieneFacturaAsociada(pNombreAutorizado)) {
                throw new Exception("Autorizado con factura pendiente");
            }

            boolean encontro = false;
            int numAutorizados = autorizados.size();
            for(int i = 0; i < numAutorizados && !encontro; i++) {
                String a = autorizados.get(i);
                if(a.equals(pNombreAutorizado)) {
                    encontro = true;
                    autorizados.remove(i);
                }
            }

            if(!encontro) {
                throw new Exception("Autorizado no encontrado");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void pagarFactura(int pIndiceFactura) {
        try {
            if(pIndiceFactura < 0 || pIndiceFactura >= facturas.size()) {
                throw new Exception("Indice invalido");
            }

            Factura factura = facturas.get(pIndiceFactura);
            if(factura.darValor() > fondos) {
                throw new Exception("Fondos insuficientes");
            }

            fondos = fondos - factura.darValor();
            facturas.remove(pIndiceFactura);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public String toString() {
        String socio = cedula + " - " + nombre;
        return socio;
    }
}