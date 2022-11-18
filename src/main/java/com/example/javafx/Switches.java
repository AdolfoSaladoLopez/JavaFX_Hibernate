package com.example.javafx;

public class Switches {
    public static void switchMenuInicio() {
        Integer eleccion = null;
        Boolean comprobador = false;

        while (!comprobador) {
            MenuVisualPrograma.menuInicio();
            eleccion = Utilidades.escaner().nextInt();

            if (eleccion >= 0 && eleccion < 3) {
                comprobador = true;
                switch (eleccion) {
                    case 0 -> {
                        Utilidades.limpiarPantalla();
                        System.out.println("¡Gracias por utilizar nuestro programa!");
                        System.out.println("Este software ha sido creado por: ");
                        System.out.println("\t- Jose Luis Dommarco Berrocal");
                        System.out.println("\t- Adolfo Salado López");
                        System.out.println("SEGUNDO - C.F.G.S. DAM - CESUR MÁLAGA ESTE");
                        System.out.println("Asignatura: Acceso a Datos.");
                        System.out.println("Curso: 2022 / 2023.");
                        System.exit(0);
                    }
                    case 1 -> {
                        Utilidades.limpiarPantalla();
                        switchProducto();
                    }
                    case 2 -> {
                        Utilidades.limpiarPantalla();
                        switchPedido();
                    }
                }
            } else {
                Utilidades.limpiarPantalla();
                System.out.println("No has elegido una opción correcta. ");
                Utilidades.pulsarTeclaContinuar();
                Utilidades.limpiarPantalla();
            }
        }


    }

    public static void switchProducto() {
        Integer eleccion = null;
        Boolean comprobador = false;

        while (!comprobador) {
            MenuVisualPrograma.menuProductos();
            eleccion = Utilidades.escaner().nextInt();

            if (eleccion >= 0 && eleccion < 5) {
                comprobador = true;
                switch (eleccion) {
                    case 0 -> {
                        Utilidades.limpiarPantalla();
                        switchMenuInicio();
                    }
                    case 1 -> {
                        Utilidades.limpiarPantalla();
                        LogicaMenuProducto.opcionListarProductosCarta();
                        Utilidades.pulsarTeclaContinuar();
                        Utilidades.limpiarPantalla();
                        switchProducto();
                    }
                    case 2 -> {
                        Utilidades.limpiarPantalla();
                        LogicaMenuProducto.opcionModificarProductoCarta();
                        Utilidades.pulsarTeclaContinuar();
                        Utilidades.limpiarPantalla();
                        switchProducto();
                    }
                    case 3 -> {
                        Utilidades.limpiarPantalla();
                        LogicaMenuProducto.opcionEliminarProductoCarta();
                        Utilidades.pulsarTeclaContinuar();
                        Utilidades.limpiarPantalla();
                        switchProducto();
                    }
                    case 4 -> {
                        Utilidades.limpiarPantalla();
                        LogicaMenuProducto.opcionCrearProductoCarta();
                        Utilidades.pulsarTeclaContinuar();
                        Utilidades.limpiarPantalla();
                        switchProducto();
                    }
                }
            } else {
                Utilidades.limpiarPantalla();
                System.out.println("No has elegido una opción correcta. ");
                Utilidades.pulsarTeclaContinuar();
                Utilidades.limpiarPantalla();
            }
        }
    }

    public static void switchPedido() {
        Integer eleccion = null;
        Boolean comprobador = false;

        while (!comprobador) {
            MenuVisualPrograma.menuPedidos();
            eleccion = Utilidades.escaner().nextInt();

            if (eleccion >= 0 && eleccion < 6) {
                comprobador = true;
                switch (eleccion) {
                    case 0 -> {
                        Utilidades.limpiarPantalla();
                        switchMenuInicio();
                    }
                    case 1 -> {
                        Utilidades.limpiarPantalla();
                        LogicaMenuPedido.opcionListarPedidos();
                        Utilidades.pulsarTeclaContinuar();
                        Utilidades.limpiarPantalla();
                        switchPedido();
                    }
                    case 2 -> {
                        Utilidades.limpiarPantalla();
                        LogicaMenuPedido.opcionListarPedidosHoy();
                        Utilidades.pulsarTeclaContinuar();
                        Utilidades.limpiarPantalla();
                        switchPedido();
                    }
                    case 3 -> {
                        Utilidades.limpiarPantalla();
                        LogicaMenuPedido.opcionModificarPedido();
                        Utilidades.pulsarTeclaContinuar();
                        Utilidades.limpiarPantalla();
                        switchPedido();
                    }
                    case 4 -> {
                        Utilidades.limpiarPantalla();
                        LogicaMenuPedido.opcionEliminarPedido();
                        Utilidades.pulsarTeclaContinuar();
                        Utilidades.limpiarPantalla();
                        switchPedido();
                    }
                    case 5 -> {
                        Utilidades.limpiarPantalla();
                        LogicaMenuPedido.opcionCrearPedido();
                        Utilidades.pulsarTeclaContinuar();
                        Utilidades.limpiarPantalla();
                        switchPedido();
                    }
                }
            } else {
                Utilidades.limpiarPantalla();
                System.out.println("No has elegido una opción correcta. ");
                Utilidades.pulsarTeclaContinuar();
                Utilidades.limpiarPantalla();
            }
        }
    }
}
