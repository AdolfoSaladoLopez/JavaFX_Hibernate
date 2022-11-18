package com.example.javafx;

import models.Producto;

import java.util.ArrayList;
import java.util.List;

public class LogicaMenuProducto {
    private static final ProductoDAO gestorProductos = new ProductoDAOHib();
    private static List<Producto> cartaProductos = new ArrayList<>();

    /***
     * Método que realiza la lógica de la opción Listar carta de productos.
     */
    public static void opcionListarProductosCarta() {
        System.out.println("Has elegido la opción: Listar productos de la carta.");
        System.out.println("Actualmente contamos con los siguientes productos: ");

        pintarCartaProductos();
    }

    /***
     * Método que realiza la lógica de la opción Modificar productos de la carta.
     */
    public static void opcionModificarProductoCarta() {
        Boolean comprobador = false;
        Integer idProducto = null;

        while (!comprobador) {
            System.out.println("Has elegido la opción: Modificar un producto de la carta.");
            System.out.println("Estos son los productos que hay en la carta: ");
            pintarCartaProductos();
            System.out.print("Elige, mediante su ID, el producto que desea modificar: ");

            idProducto = Utilidades.escaner().nextInt();

            if (idProducto >= 1 && idProducto < cartaProductos.size()) {
                comprobador = true;
                Utilidades.limpiarPantalla();
            } else {
                System.out.println("No has elegido un producto correcto.");
                Utilidades.pulsarTeclaContinuar();
                Utilidades.limpiarPantalla();
            }
        }

        Producto producto = new Producto();
        producto = gestorProductos.obtenerProducto(idProducto);
        System.out.println("Has elegido el producto con el ID " + producto.getId() + ". Nombre: " + producto.getNombre());

        System.out.print("Su nombre actual es " + producto.getNombre() + ". ¿Cuál será su nuevo nombre?: ");
        String nuevoNombre = Utilidades.escaner().nextLine();
        System.out.println();

        System.out.print("Su tipo actual es " + producto.getTipo() + ". ¿Cuál será su nuevo tipo?: ");
        String nuevoTipo = Utilidades.escaner().nextLine();
        System.out.println();

        System.out.print("Su precio actual es " + producto.getPrecio() + ". ¿Cuál será su nuevo tipo?: ");
        Float nuevoPrecio = Utilidades.escaner().nextFloat();
        System.out.println();

        comprobador = false;
        Boolean opcion = false;

        while (!comprobador) {
            System.out.print("Actualmente, la disponibilidad del producto es " +
                    convertirDisponibilidadProducto(producto) +
                    "\n[1].- Disponible.\n" +
                    "\n[2].- No disponible." +
                    "\nElija su disponibilidad: ");
            Integer nuevaDisponibilidad = Utilidades.escaner().nextInt();

            if (nuevaDisponibilidad == 1 || nuevaDisponibilidad == 2) {

                if (nuevaDisponibilidad == 1) {
                    opcion = true;
                }
                comprobador = true;
            } else {
                System.out.println("No has elegido una opción correcta.");
                Utilidades.pulsarTeclaContinuar();
                Utilidades.limpiarPantalla();
            }
        }

        producto.setNombre(nuevoNombre);
        producto.setTipo(nuevoTipo);
        producto.setPrecio(nuevoPrecio);
        producto.setDisponibilidad(opcion);

        if (gestorProductos.actualizarProducto(producto)) {
            System.out.println("Producto modificado correctamente.");
            System.out.println("El producto modificado es el siguiente: ");
            pintarInformacionProducto(producto);
        } else {
            System.out.println("El producto no ha sido modificado con éxito.");
        }
    }

    /***
     * Método que realiza la lógica de la opción Eliminar productos de la carta.
     */
    public static void opcionEliminarProductoCarta() {
        Boolean comprobador = false;
        Integer idProducto = null;
        Boolean decision = false;
        Boolean resultado = false;

        while (!comprobador) {
            System.out.println("Has elegido la opción: Eliminar un producto de la carta.");
            System.out.println("Estos son los productos que hay en la carta: ");
            pintarCartaProductos();
            System.out.print("Elige, mediante su ID, el producto que deseas eliminar: ");

            idProducto = Utilidades.escaner().nextInt();

            if (idProducto >= 1 && idProducto <= cartaProductos.size()) {
                comprobador = true;
                Utilidades.limpiarPantalla();
            } else {
                System.out.println("No has elegido un producto correcto.");
                Utilidades.pulsarTeclaContinuar();
                Utilidades.limpiarPantalla();
            }
        }

        Producto producto = new Producto();
        producto = gestorProductos.obtenerProducto(idProducto);

        System.out.println("Has elegido el producto con el ID " + producto.getId() + ". Nombre: " + producto.getNombre());

        Integer opcion = null;

        while (!decision) {
            System.out.print("¡Advertencia!" +
                    "\n\t[1].- Sí." +
                    "\n\t[2].- No." +
                    "\n¿Estás seguro que deseas eliminarlo? ¡No habrá vuelta atrás!: ");
            opcion = Utilidades.escaner().nextInt();

            if (opcion == 1 || opcion == 2) {
                decision = true;
            } else {
                System.out.println("No has elegido una opción correcta. ");
            }
        }

        if (opcion == 1) {
            if (gestorProductos.eliminarProducto(producto)) {
                System.out.println("El producto ha sido eliminado con éxito.");
            } else {
                System.out.println("No ha sido posible eliminar el producto. ");
            }
        } else {
            System.out.println("No se eliminará el producto de la carta.");
        }
    }

    /***
     * Método que realiza la lógica de la opción Añadir un nuevo producto a la carta.
     */
    public static void opcionCrearProductoCarta() {
        Producto producto = new Producto();
        Boolean comprobador = false;
        Integer opcion = null;
        Boolean disponibilidad = null;

        System.out.println("Ha elegido la opción: Añadir un nuevo producto a la carta.");
        System.out.println();

        System.out.print("Indica el nombre del producto: ");
        String nombre = Utilidades.escaner().nextLine();
        System.out.println();

        System.out.print("Indica el tipo de producto: ");
        String tipo = Utilidades.escaner().nextLine();
        System.out.println();

        System.out.print("Indica el precio del producto (Utiliza una coma para separar los centimos): ");
        Float precio = Utilidades.escaner().nextFloat();
        System.out.println();

        while (!comprobador) {
            System.out.print(
                    "Disponibilidad: " +
                            "\n\t[1].- Disponible." +
                            "\n\t[2].- No disponible." +
                            "\nElige: ");
            opcion = Utilidades.escaner().nextInt();

            if (opcion == 1 || opcion == 2) {
                disponibilidad = opcion == 1;
                comprobador = true;
            } else {
                System.out.println("No has elegido una opción correcta.");
                Utilidades.pulsarTeclaContinuar();
                Utilidades.limpiarPantalla();
            }
        }

        producto.setNombre(nombre);
        producto.setTipo(tipo);
        producto.setPrecio(precio);
        producto.setDisponibilidad(disponibilidad);

        if (gestorProductos.crearProducto(producto)) {
            System.out.println("El producto ha sido añadido con éxito. ");
            pintarInformacionProducto((gestorProductos.obtenerListadoProductos().get(gestorProductos.obtenerListadoProductos().size() - 1)));

        } else {
            System.out.println("No ha sido posible añadir el producto con éxito.");
        }
    }

    /***
     * Método que pinta la carta de productos.
     */
    public static void pintarCartaProductos() {
        cartaProductos = gestorProductos.obtenerListadoProductos();
        cartaProductos.forEach(
                producto -> System.out.println("ID: " + "[" + producto.getId() + "]" +
                        ". Nombre: " + producto.getNombre() + ". Tipo: " + producto.getTipo() +
                        ". Precio: " + producto.getPrecio() +
                        ". Disponibilidad: " + convertirDisponibilidadProducto(producto))
        );
    }

    /***
     * Método que pinta la información de un producto concreto
     * @param producto: Producto que deseamos pintar
     */
    private static void pintarInformacionProducto(Producto producto) {
        System.out.println("ID: " + "[" + producto.getId() + "]" +
                ". Nombre: " + producto.getNombre() + ". Tipo: " + producto.getTipo() +
                ". Precio: " + producto.getPrecio() +
                ". Disponibilidad: " + convertirDisponibilidadProducto(producto));
    }

    /***
     * Método que recibe un producto para obtener su disponibilidad y convertirla a String
     * @param producto: Producto que obtenemos para obtener su disponibilidad.
     * @return String: Si está disponible o no el producto.
     */
    private static String convertirDisponibilidadProducto(Producto producto) {
        String estaDisponible = "";

        if (producto.getDisponibilidad()) {
            estaDisponible = "Disponible";
        } else {
            estaDisponible = "No disponible";
        }

        return estaDisponible;
    }
}
