package tarea20180320;

import java.io.*;

public class Tarea20180320 {

    public static void main(String[] args) throws IOException {
        Persona p1 = new Persona();
        String nombre;
        int edad;
        boolean seguir;

        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        seguir = true;

        while (seguir) {
            System.out.println("edad: 0 para salir");
            edad = Integer.parseInt(lector.readLine());

            System.out.println("nombre:");
            nombre = lector.readLine();

            if (edad > p1.getEdad()) {
                p1.setNombre(nombre);
                p1.setEdad(edad);
            }
            if (edad == 0) {
                seguir = false;
            }
        }

        System.out.println("Los datos son:");
        System.out.println(p1.getNombre() + " " + p1.getApellido() + " - " + p1.getEdad());
    }
}
