package club;

import java.util.Scanner;
import java.util.ArrayList;
import club.Socio.Tipo;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int op = 0;
        Club c = new Club();

        do{
            try {
                System.out.println("\n=== MENU CLUB ===");
                System.out.println("1. Afiliar un socio al club");
                System.out.println("2. Registrar una persona autorizada por un socio");
                System.out.println("3. Pagar una factura");
                System.out.println("4. Registrar un consumo en la cuenta de un socio");
                System.out.println("5. Aumentar fondos de la cuenta de un socio");
                System.out.println("6. Salir");
                System.out.print("Ingrese una opcion: ");

                op = Integer.parseInt(sc.nextLine());

                switch (op){
                    case 1:{
                        System.out.println("\n===REGISTRO SOCIO===");

                        String cedula = "";
                        boolean cedulaValida = false;
                        while(!cedulaValida) {
                            try {
                                System.out.print("Ingrese la cedula del socio: ");
                                cedula = sc.nextLine();
                                if(cedula.isEmpty()) {
                                    throw new Exception("La cedula no puede estar vacia");
                                }
                                cedulaValida = true;
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        String nombre = "";
                        boolean nombreValido = false;
                        while(!nombreValido) {
                            try {
                                System.out.print("Ingrese el nombre del socio: ");
                                nombre = sc.nextLine();
                                if(nombre.isEmpty()) {
                                    throw new Exception("El nombre no puede estar vacio");
                                }
                                nombreValido = true;
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        Tipo tipo = null;
                        boolean tipoValido = false;
                        while(!tipoValido) {
                            try {
                                System.out.println("Tipo de suscripcion:");
                                System.out.println("1. VIP");
                                System.out.println("2. REGULAR");
                                System.out.print("Seleccione el tipo: ");
                                int tipoOp = Integer.parseInt(sc.nextLine());

                                if(tipoOp != 1 && tipoOp != 2) {
                                    throw new Exception("Opcion invalida");
                                }

                                tipo = (tipoOp == 1) ? Tipo.VIP : Tipo.REGULAR;
                                tipoValido = true;

                            } catch (NumberFormatException e) {
                                System.out.println("Debe ingresar un numero valido");
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        c.afiliarSocio(cedula, nombre, tipo);
                        System.out.println("Proceso completado");

                    }break;

                    case 2:{
                        System.out.println("\n===REGISTRAR AUTORIZADO===");

                        String cedula = "";
                        boolean cedulaValida = false;
                        while(!cedulaValida) {
                            try {
                                System.out.print("Ingrese la cedula del socio: ");
                                cedula = sc.nextLine();
                                if(cedula.isEmpty()) {
                                    throw new Exception("La cedula no puede estar vacia");
                                }
                                cedulaValida = true;
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        String nombreAutorizado = "";
                        boolean nombreValido = false;
                        while(!nombreValido) {
                            try {
                                System.out.print("Ingrese el nombre del autorizado: ");
                                nombreAutorizado = sc.nextLine();
                                if(nombreAutorizado.isEmpty()) {
                                    throw new Exception("El nombre no puede estar vacio");
                                }
                                nombreValido = true;
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        c.agregarAutorizadoSocio(cedula, nombreAutorizado);

                    }break;

                    case 3:{
                        System.out.println("\n===PAGAR FACTURA===");

                        String cedula = "";
                        boolean cedulaValida = false;
                        while(!cedulaValida) {
                            try {
                                System.out.print("Ingrese la cedula del socio: ");
                                cedula = sc.nextLine();
                                if(cedula.isEmpty()) {
                                    throw new Exception("La cedula no puede estar vacia");
                                }
                                cedulaValida = true;
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        ArrayList<Factura> facturas = c.darFacturasSocio(cedula);

                        try {
                            if(facturas.isEmpty()){
                                throw new Exception("El socio no tiene facturas pendientes");
                            }

                            System.out.println("\nFacturas pendientes:");
                            for(int i = 0; i < facturas.size(); i++){
                                System.out.println(i + ". " + facturas.get(i).toString());
                            }

                            int indice = -1;
                            boolean indiceValido = false;
                            while(!indiceValido) {
                                try {
                                    System.out.print("\nSeleccione el numero de factura a pagar: ");
                                    indice = Integer.parseInt(sc.nextLine());

                                    if(indice < 0 || indice >= facturas.size()) {
                                        throw new Exception("Numero de factura invalido");
                                    }
                                    indiceValido = true;

                                } catch (NumberFormatException e) {
                                    System.out.println("Debe ingresar un numero valido");
                                } catch (Exception e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            }

                            c.pagarFacturaSocio(cedula, indice);

                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    }break;

                    case 4:{
                        System.out.println("\n===REGISTRAR CONSUMO===");

                        String cedula = "";
                        boolean cedulaValida = false;
                        while(!cedulaValida) {
                            try {
                                System.out.print("Ingrese la cedula del socio: ");
                                cedula = sc.nextLine();
                                if(cedula.trim().isEmpty()) {
                                    throw new Exception("La cedula no puede estar vacia");
                                }
                                cedulaValida = true;
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        ArrayList<String> autorizados = c.darAutorizadosSocio(cedula);

                        try {
                            if(autorizados.isEmpty()) {
                                throw new Exception("No se encontraron personas autorizadas");
                            }

                            System.out.println("\nPersonas autorizadas:");
                            for(int i = 0; i < autorizados.size(); i++){
                                System.out.println(i + ". " + autorizados.get(i));
                            }

                            int indicePersona = -1;
                            boolean indiceValido = false;
                            while(!indiceValido) {
                                try {
                                    System.out.print("\nSeleccione quien realiza el consumo: ");
                                    indicePersona = Integer.parseInt(sc.nextLine());

                                    if(indicePersona < 0 || indicePersona >= autorizados.size()){
                                        throw new Exception("Seleccion invalida");
                                    }
                                    indiceValido = true;

                                } catch (NumberFormatException e) {
                                    System.out.println("Error: Debe ingresar un numero valido");
                                } catch (Exception e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            }

                            String nombreCliente = autorizados.get(indicePersona);

                            String concepto = "";
                            boolean conceptoValido = false;
                            while(!conceptoValido) {
                                try {
                                    System.out.print("Ingrese el concepto del consumo: ");
                                    concepto = sc.nextLine();
                                    if(concepto.isEmpty()) {
                                        throw new Exception("El concepto no puede estar vacio");
                                    }
                                    conceptoValido = true;
                                } catch (Exception e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            }

                            double valor = 0;
                            boolean valorValido = false;
                            while(!valorValido) {
                                try {
                                    System.out.print("Ingrese el valor del consumo: ");
                                    valor = Double.parseDouble(sc.nextLine());
                                    if(valor <= 0) {
                                        throw new Exception("El valor debe ser mayor a cero");
                                    }
                                    valorValido = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Error: Debe ingresar un numero valido");
                                } catch (Exception e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            }

                            c.registrarConsumo(cedula, nombreCliente, concepto, valor);

                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    }break;

                    case 5:{
                        System.out.println("\n===AUMENTAR FONDOS===");

                        String cedula = "";
                        boolean cedulaValida = false;
                        while(!cedulaValida) {
                            try {
                                System.out.print("Ingrese la cedula del socio: ");
                                cedula = sc.nextLine();
                                if(cedula.trim().isEmpty()) {
                                    throw new Exception("La cedula no puede estar vacia");
                                }
                                cedulaValida = true;
                            } catch (Exception e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }

                        Socio socio = c.buscarSocio(cedula);

                        try {
                            if(socio == null) {
                                throw new Exception("No existe un socio con esa cedula");
                            }

                            System.out.println("Fondos actuales: $" + socio.darFondos());

                            double valor = 0;
                            boolean valorValido = false;
                            while(!valorValido) {
                                try {
                                    System.out.print("Ingrese el valor a aumentar: ");
                                    valor = Double.parseDouble(sc.nextLine());
                                    if(valor <= 0) {
                                        throw new Exception("El valor debe ser mayor a cero");
                                    }
                                    valorValido = true;
                                } catch (NumberFormatException e) {
                                    System.out.println("Error: Debe ingresar un numero valido");
                                } catch (Exception e) {
                                    System.out.println("Error: " + e.getMessage());
                                }
                            }

                            c.aumentarFondosSocio(cedula, valor);
                            System.out.println("Fondos actualizados: $" + socio.darFondos());

                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }

                    }break;

                    case 6:{
                        System.out.println("\nGracias por usar el sistema");
                    }break;

                    default:
                        try{
                            throw new Exception("Opcion invalida, seleccione entre 1 y 6");
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }

                }

            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido para el menu");
                op = 0;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                op = 0;
            }

        }while(op!=6);

        sc.close();
    }
}