package club;
import java.util.ArrayList;
import club.Socio.Tipo;

public class Club {
    public final static int MAXIMO_VIP = 3;

    private ArrayList<Socio> socios;

    public Club() {
        socios = new ArrayList<Socio>();
    }

    public ArrayList<Socio> darSocios() {
        return socios;
    }

    public void afiliarSocio(String pCedula, String pNombre, Tipo pTipo) {
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

            Socio s = buscarSocio(pCedula);

            if(s != null) {
                throw new Exception("Socio existente");
            }

            if(pTipo == Tipo.VIP && contarSociosVIP() >= MAXIMO_VIP) {
                throw new Exception("Maximo VIP alcanzado");
            }

            Socio nuevoSocio = new Socio(pCedula, pNombre, pTipo);
            socios.add(nuevoSocio);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Socio buscarSocio(String pCedulaSocio) {
        Socio elSocio = null;
        try {
            if(pCedulaSocio == null || pCedulaSocio.isEmpty()) {
                throw new Exception("Cedula vacia");
            }

            boolean encontre = false;
            int numSocios = socios.size();

            for(int i = 0; i < numSocios && !encontre; i++) {
                Socio s = socios.get(i);
                if(s.darCedula().equals(pCedulaSocio)) {
                    elSocio = s;
                    encontre = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return elSocio;
    }

    public int contarSociosVIP() {
        int conteo = 0;
        try {
            for(Socio socio : socios) {
                if(socio.darTipo() == Tipo.VIP) {
                    conteo++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return conteo;
    }

    public ArrayList<String> darAutorizadosSocio(String pCedulaSocio) {
        ArrayList<String> autorizados = new ArrayList<String>();
        try {
            Socio s = buscarSocio(pCedulaSocio);

            if(s == null) {
                throw new Exception("Socio no existe");
            }

            autorizados.add(s.darNombre());
            autorizados.addAll(s.darAutorizados());

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return autorizados;
    }

    public void agregarAutorizadoSocio(String pCedulaSocio, String pNombreAutorizado) {
        try {
            Socio s = buscarSocio(pCedulaSocio);

            if(s == null) {
                throw new Exception("Socio no existe");
            }

            s.agregarAutorizado(pNombreAutorizado);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void eliminarAutorizadoSocio(String pCedulaSocio, String pNombreAutorizado) {
        try {
            Socio s = buscarSocio(pCedulaSocio);

            if(s == null) {
                throw new Exception("Socio no existe");
            }

            s.eliminarAutorizado(pNombreAutorizado);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void registrarConsumo(String pCedulaSocio, String pNombreCliente, String pConcepto, double pValor) {
        try {
            Socio s = buscarSocio(pCedulaSocio);

            if(s == null) {
                throw new Exception("Socio no existe");
            }

            s.registrarConsumo(pNombreCliente, pConcepto, pValor);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public ArrayList<Factura> darFacturasSocio(String pCedulaSocio) {
        ArrayList<Factura> facturas = new ArrayList<Factura>();
        try {
            Socio s = buscarSocio(pCedulaSocio);

            if(s == null) {
                throw new Exception("Socio no existe");
            }

            facturas = s.darFacturas();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return facturas;
    }

    public void pagarFacturaSocio(String pCedulaSocio, int pFacturaIndice) {
        try {
            Socio s = buscarSocio(pCedulaSocio);

            if(s == null) {
                throw new Exception("Socio no existe");
            }

            s.pagarFactura(pFacturaIndice);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void aumentarFondosSocio(String pCedulaSocio, double pValor) {
        try {
            Socio s = buscarSocio(pCedulaSocio);

            if(s == null) {
                throw new Exception("Socio no existe");
            }

            s.aumentarFondos(pValor);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public double calcularTotalConsumosSocio(String pCedula) {
        double total = 0;
        try {
            Socio socio = buscarSocio(pCedula);

            if(socio == null) {
                throw new Exception("Socio no existe");
            }

            ArrayList<Factura> facturas = socio.darFacturas();

            for(Factura factura : facturas) {
                total += factura.darValor();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return total;
    }

    public boolean sePuedeEliminarSocio(String pCedula) {
        try {
            Socio socio = buscarSocio(pCedula);

            if(socio == null) {
                throw new Exception("Socio no existe");
            }

            if(socio.darTipo() == Tipo.VIP) {
                throw new Exception("Socio VIP no eliminable");
            }

            if(socio.darFacturas().size() > 0) {
                throw new Exception("Socio con facturas pendientes");
            }

            if(socio.darAutorizados().size() > 1) {
                throw new Exception("Socio con multiples autorizados");
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public String metodo1() {
        return "respuesta1";
    }

    public String metodo2() {
        return "respuesta2";
    }
}