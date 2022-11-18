package com.example.javafx;

import models.Pedido;
import models.Producto;

import java.util.ArrayList;
import java.util.List;

public class LogicaMenuPedido {
    private static final ProductoDAO gestorProductos = new ProductoDAOHib();
    private static final PedidoDAO gestorPedidos = new PedidoDAOHib();
    private static List<Pedido> listadoPedidos = new ArrayList<>();

    /***
     * Método que cuenta con la la lógica de la opción listar pedidos
     */
    public static void opcionListarPedidos() {
        System.out.println("Has elegido la opción: Listar todos los pedidos existentes.");

        if (gestorPedidos.obtenerListadoPedidos().size() > 0) {
            System.out.println("Se han realizado los siguientes pedidos: ");
            pintarListadoPedidos();
        } else {
            System.out.println("Actualmente, no contamos con pedidos.");
        }

    }

    public static void opcionListarPedidosHoy() {
        List<Pedido> listadoPedidosPendientes = new ArrayList<>(gestorPedidos.obtenerListadoPedidos());

        if (listadoPedidosPendientes.size() > 0) {
            System.out.println("Has elegido la opción: Listar los pedidos pendientes de hoy.");
            System.out.println("Estos son los pedidos pendientes de hoy: ");

            listadoPedidosPendientes.forEach(
                    pedido -> System.out.println("ID del pedido: " + "[" + pedido.getId() + "]" +
                            ". Cliente: " + pedido.getCliente() + ". Fecha: " + pedido.getFecha() +
                            ". Estado: " + pedido.getEstado() +
                            "\n\tProducto del pedido: " + pedido.getProducto().getNombre())
            );
        } else {
            System.out.println("Actualmente, no existen pedidos pendientes.");
        }

    }

    public static void opcionModificarPedido() {

        if (gestorPedidos.obtenerListadoPedidos().size() > 0) {
            Integer eleccion = null;
            Boolean comprobador = false;

            while (!comprobador) {
                System.out.println("Has elegido la opción: Modificar un pedido.");
                System.out.println("Estos son todos los pedidos: ");
                pintarListadoPedidos();
                System.out.print("Selecciona el pedido a modificar: ");
                eleccion = Utilidades.escaner().nextInt();

                if (eleccion >= 0 && eleccion <= listadoPedidos.size()) {
                    comprobador = true;
                } else {
                    System.out.println("No ha elegido un pedido válido.");
                }
            }

            Pedido pedido = new Pedido();
            pedido = gestorPedidos.obtenerPedido(eleccion);

            comprobador = false;
            eleccion = null;

            while (!comprobador) {
                Utilidades.limpiarPantalla();
                System.out.println("El pedido elegido es el siguiente: ");
                pintarPedidoConcreto(pedido);
                System.out.print("Opciones a modificar:" +
                        "\n\t[1].- Nombre del cliente." +
                        "\n\t[2].- Estado." +
                        "\n\t[3].- Producto." +
                        "\nElige una opción: ");
                eleccion = Utilidades.escaner().nextInt();

                if (eleccion > 0 && eleccion < 4) {
                    comprobador = true;
                } else {
                    System.out.println("No ha elegido una opción correcta.");
                    Utilidades.limpiarPantalla();
                }
            }

            if (eleccion == 1) {
                Utilidades.limpiarPantalla();
                System.out.println("El nombre actual del cliente es " + pedido.getCliente());
                System.out.print("¿Qué nombre desea añadir?: ");
                String nombre = Utilidades.escaner().nextLine();

                pedido.setCliente(nombre);

                if (gestorPedidos.actualizarPedido(pedido)) {
                    System.out.println("El pedido ha sido modificado con éxito. Estos son sus datos: ");
                    pintarPedidoConcreto(gestorPedidos.obtenerPedido(pedido.getId()));
                } else {
                    System.out.println("No ha sido posible modificar el cliente del pedido. ");
                }
            } else if (eleccion == 2) {
                Integer opcion = null;
                comprobador = false;

                Utilidades.limpiarPantalla();
                while (!comprobador) {
                    System.out.println("Actualmente, el pedido tiene un estado " + pedido.getEstado());
                    System.out.println("\t[1].- Sí.");
                    System.out.println("\t[2].- No.");
                    System.out.print("\n¿Estás seguro de que deseas cambiar su estado a " + cambiarEstado(pedido) + "?: ");
                    opcion = Utilidades.escaner().nextInt();

                    if (opcion == 1 || opcion == 2) {
                        comprobador = true;
                    } else {
                        Utilidades.limpiarPantalla();
                        System.out.println("No has elegido una opción válida.");
                        Utilidades.pulsarTeclaContinuar();
                        Utilidades.limpiarPantalla();
                    }
                }

                if (opcion == 1) {
                    pedido.setEstado(cambiarEstado(pedido));
                    if (gestorPedidos.actualizarPedido(pedido)) {
                        Utilidades.limpiarPantalla();
                        System.out.println("El pedido ha sido modificado con éxito. Estos son sus datos: ");
                        pintarPedidoConcreto(gestorPedidos.obtenerPedido(pedido.getId()));
                    } else {
                        Utilidades.limpiarPantalla();
                        System.out.println("No ha sido posible modificar el cliente del pedido. ");
                        Utilidades.pulsarTeclaContinuar();
                        Utilidades.limpiarPantalla();
                    }
                } else {
                    Utilidades.limpiarPantalla();
                    System.out.println("El pedido no será modificado.");
                    Utilidades.pulsarTeclaContinuar();
                    Utilidades.limpiarPantalla();
                }
            } else if (eleccion == 3) {
                comprobador = false;
                Integer id = null;

                while (!comprobador) {
                    Utilidades.limpiarPantalla();
                    System.out.println("El producto del pedido es " + pedido.getProducto().getNombre());
                    System.out.println("Estos son los productos que tenemos en la carta: ");
                    LogicaMenuProducto.pintarCartaProductos();
                    System.out.print("Elige, mediante su ID, el nuevo producto de su pedido: ");
                    id = Utilidades.escaner().nextInt();

                    if (id > 0 && id <= gestorProductos.obtenerListadoProductos().size()) {
                        comprobador = true;
                    } else {
                        Utilidades.limpiarPantalla();
                        System.out.println("No has elegido un producto correcto.");
                        Utilidades.pulsarTeclaContinuar();
                    }
                }

                Producto producto = gestorProductos.obtenerProducto(id);
                comprobador = false;

                Integer opcion = null;
                Utilidades.limpiarPantalla();
                System.out.println("El nuevo producto que se añadirá al pedido es " + producto.getNombre());

                while (!comprobador) {
                    System.out.println("¡Advertencia!");
                    System.out.println("\t[1].- Sí.");
                    System.out.println("\t[2].- No.");
                    System.out.print("\n¿Estás seguro que quieres modificar el producto del pedido?: ");
                    opcion = Utilidades.escaner().nextInt();

                    if (opcion == 1 || opcion == 2) {
                        comprobador = true;
                    }
                }

                if (opcion == 1) {
                    System.out.println("El producto del pedido será modificado.");
                    pedido.setProducto(producto);

                    if (gestorPedidos.crearPedido(pedido)) {
                        Utilidades.limpiarPantalla();
                        System.out.println("El producto ha sido modificado con éxito");
                        System.out.println("Ahora el pedido queda de la siguiente manera: ");
                        pintarPedidoConcreto(gestorPedidos.obtenerPedido(pedido.getId()));
                    } else {
                        System.out.println("No ha sido posible modificar el producto del pedido.");
                    }
                } else {
                    System.out.println("No será modificado el producto del pedido. ");
                }
            }
        } else {
            Utilidades.limpiarPantalla();
            System.out.println("No existen pedidos a modificar.");
            Utilidades.pulsarTeclaContinuar();
        }

    }

    public static void opcionEliminarPedido() {

        if (gestorPedidos.obtenerListadoPedidos().size() > 0) {
            Boolean comprobador = false;
            Integer id = null;

            while (!comprobador) {
                System.out.println("Ha elegido la opción: Eliminar un pedido.");
                System.out.println("Estos son todos los pedidos: ");
                pintarListadoPedidos();
                System.out.print("¿Qué pedido quieres eliminar?: ");
                id = Utilidades.escaner().nextInt();

                if (id > 0 && id <= listadoPedidos.size()) {
                    comprobador = true;
                } else {
                    Utilidades.limpiarPantalla();
                    System.out.println("No has elegido un pedido correcto.");
                    Utilidades.pulsarTeclaContinuar();
                    Utilidades.limpiarPantalla();
                }
            }

            Pedido pedido = gestorPedidos.obtenerPedido(id);

            comprobador = false;

            System.out.println("El pedido elegido es el siguiente: ");
            pintarPedidoConcreto(pedido);

            Integer opcion = null;

            while (!comprobador) {
                System.out.print("¡Advertencia!" +
                        "\n\t[1].- Sí." +
                        "\n\t[2].- No." +
                        "\n¿Estás seguro que deseas eliminarlo? ¡No habrá vuelta atrás!: ");
                opcion = Utilidades.escaner().nextInt();

                if (opcion == 1 || opcion == 2) {
                    comprobador = true;
                } else {
                    System.out.println("No has elegido una opción correcta. ");
                }
            }

            if (opcion == 1) {
                if (gestorPedidos.eliminarPedido(pedido)) {
                    Utilidades.limpiarPantalla();
                    System.out.println("El pedido ha sido eliminado con éxito.");
                } else {
                    Utilidades.limpiarPantalla();
                    System.out.println("No ha sido posible eliminar el pedido. ");
                }
            } else {
                Utilidades.limpiarPantalla();
                System.out.println("No se eliminará el producto de la carta.");
            }
        } else {
            Utilidades.limpiarPantalla();
            System.out.println("Actualmente, no tenemos pedidos en nuestra base de datos. ");
            Utilidades.pulsarTeclaContinuar();
        }
    }

    public static void opcionCrearPedido() {
        Boolean comprobador = false;
        String nombre = "";
        Integer idProducto = null;
        Pedido pedido = new Pedido();
        Producto producto = new Producto();
        Integer eleccion = null;

        System.out.println("Has elegido la opción: Crear un nuevo pedido.");
        System.out.print("Indica el nombre del cliente: ");
        nombre = Utilidades.escaner().nextLine();

        pedido.setCliente(nombre);

        while (!comprobador) {
            Utilidades.limpiarPantalla();
            System.out.println("Listado de productos: ");
            LogicaMenuProducto.pintarCartaProductos();
            System.out.print("Elige el producto a añadir a su pedido: ");
            idProducto = Utilidades.escaner().nextInt();

            if (idProducto > 0 && idProducto <= gestorProductos.obtenerListadoProductos().size()) {
                comprobador = true;
            }

        }

        producto = gestorProductos.obtenerProducto(idProducto);

        System.out.println("El producto elegido es: ");
        System.out.println("ID del producto: " + producto.getId() + ". Nombre: " + producto.getNombre());

        pedido.setProducto(producto);

        comprobador = false;

        while (!comprobador) {
            Utilidades.limpiarPantalla();
            System.out.println("Confirmación: ");
            System.out.println("\t[1].- Sí.");
            System.out.println("\t[2].- No.");
            System.out.print("¿Deseas crear el pedido?: ");
            eleccion = Utilidades.escaner().nextInt();

            if (eleccion == 1 || eleccion == 2) {
                comprobador = true;
            } else {
                Utilidades.limpiarPantalla();
                System.out.println("No has elegido una opción válida.");
                Utilidades.pulsarTeclaContinuar();
            }
        }

        pedido.setEstado("Pendiente");

        if (eleccion == 1) {
            Utilidades.limpiarPantalla();
            if (gestorPedidos.crearPedido(pedido)) {
                System.out.println("El pedido ha sido creado con éxito.");
                pintarPedidoConcreto(gestorPedidos.obtenerListadoPedidos().get(gestorPedidos.obtenerListadoPedidos().size() - 1));

            } else {
                System.out.println("No se ha podido crear el pedido. ");
            }
        } else if (eleccion == 2) {
            System.out.println("No se creará el pedido.");
        }
    }

    private static void pintarPedidoConcreto(Pedido pedido) {
        System.out.println("ID del pedido: " + "[" + pedido.getId() + "]" +
                ". Cliente: " + pedido.getCliente() + ". Fecha: " + pedido.getFecha() +
                ". Estado: " + pedido.getEstado() +
                "\n\tProducto del pedido: " + pedido.getProducto().getNombre());
    }

    private static void pintarListadoPedidos() {
        listadoPedidos = gestorPedidos.obtenerListadoPedidos();
        listadoPedidos.forEach(
                pedido -> System.out.println("ID del pedido: " + "[" + pedido.getId() + "]" +
                        ". Cliente: " + pedido.getCliente() + ". Fecha: " + pedido.getFecha() +
                        ". Estado: " + pedido.getEstado() +
                        "\n\tProducto del pedido: " + pedido.getProducto().getNombre())
        );
    }

    private static String cambiarEstado(Pedido pedido) {
        String estado = "";

        if (pedido.getEstado().equals("Pendiente")) {
            estado = "Recogido";
        } else if (pedido.getEstado().equals("Recogido")) {
            estado = "Pendiente";
        }

        return estado;
    }

}
