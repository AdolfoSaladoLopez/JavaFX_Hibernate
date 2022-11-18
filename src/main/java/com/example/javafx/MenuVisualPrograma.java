package com.example.javafx;

public class MenuVisualPrograma {
    public static void menuInicio() {
        System.out.println("¡Bienvenido al programa CRUD de Desayunos V2!");
        System.out.println("Esta vez, funciono bajo Hibernate.");
        System.out.println("Menú de opciones: ");
        System.out.println("\t[1].- Gestión de productos.");
        System.out.println("\t[2].- Gestión de pedidos.");
        System.out.println("\t[0].- Salir del programa.");
        System.out.print("Elige una opción: ");
    }

    public static void menuProductos() {
        System.out.println("Te encuentras en el gestor de productos.");
        System.out.println("\t[1].- Listar productos de la carta.");
        System.out.println("\t[2].- Modificar un producto de la carta.");
        System.out.println("\t[3].- Eliminar un producto de la carta.");
        System.out.println("\t[4].- Añadir un nuevo producto a la carta.");
        System.out.println("\t[0].- Volver al menú de inicio.");
        System.out.print("Elige una opción: ");
    }

    public static void menuPedidos() {
        System.out.println("Te encuentras en el gestor de pedidos.");
        System.out.println("\t[1].- Listar todos los pedidos existentes.");
        System.out.println("\t[2].- Listar los pedidos pendientes de hoy.");
        System.out.println("\t[3].- Modificar un pedido.");
        System.out.println("\t[4].- Eliminar un pedido.");
        System.out.println("\t[5].- Crear un nuevo pedido.");
        System.out.println("\t[0].- Volver al menú de inicio.");
        System.out.print("Elige una opción: ");
    }
}
