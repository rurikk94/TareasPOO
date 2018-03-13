package tarea20180313;

import java.io.*;

public class Tarea20180313 {

    public static void main(String[] args) throws IOException {
//        Float[][] ratings = {{2f, 5f, 6.5f, 0.1f, 6f, 8f, 5f}, {1f, 2f, 3f, 4f, 5f, 6f, 7f}, {10f, 5f, 6f, 7f, 4f, 5f, 5f}, {10f, 5f, 6f, 7f, 4f, 5f, 5f}};
//        String[] nombreMatinales = {"chv", "c13", "tvn", "cqc"};

        int matinales, dias;
        Float[][] ratings;
        String[] nombreMatinales;

        matinales = ingresarNumero("ingrese la cantidad de matinales");

        nombreMatinales = new String[matinales];
        for (int i = 0; i < matinales; i++) {
            nombreMatinales[i] = ingresarString("ingrese el nombre del matinal");
        }

        dias = ingresarNumero("ingrese la cantidad de dias a almacenar");

        ratings = new Float[matinales][dias];
        for (int i = 0; i < matinales; i++) {
            for (int j = 0; j < dias; j++) {
                do {
                    ratings[i][j] = ingresarFloat("ingrese el rating del matinal '" + nombreMatinales[i] + "' del día '" + j + "'");
                    imprimir("ratings[i][j]=" + ratings[i][j] + "  < 0'");
                } while (ratings[i][j] < 0.1);
            }
        }
        
        mejoresMatinales(nombreMatinales, ratings);
        promedioRatingMatinal(nombreMatinales, ratings);
        ordenarMatinales(nombreMatinales, ratings);

    }

    public static void mejoresMatinales(String[] nombreMatinales, Float[][] ratings) {

        imprimir("El matinal con mayor promedio de rating es: ");
        Float promedioMatinal, mejorPromedio;
        mejorPromedio = -10f;

        for (int i = 0; i < nombreMatinales.length; i++) {
            promedioMatinal = promedioDeMatinal(ratings[i]);
            if (promedioMatinal > mejorPromedio) {
                mejorPromedio = promedioMatinal;
            }
        }

        for (int i = 0; i < nombreMatinales.length; i++) {
            promedioMatinal = promedioDeMatinal(ratings[i]);
            //imprimir("nombre: " + nombreMatinales[i] + " promedio " + promedioMatinal + "/" + mejorPromedio);
            if (compararFloat(mejorPromedio, promedioMatinal)) {
                imprimir(nombreMatinales[i]);
            }
        }

    }

    public static void ordenarMatinales(String[] nombreMatinales, Float[][] ratings) {
        for (int i = 0; i < nombreMatinales.length - 1; i++) {
            for (int j = i + 1; j < nombreMatinales.length; j++) {
                if (promedioDeMatinal(ratings[i]) > promedioDeMatinal(ratings[j])) {
                    //Intercambiamos valores
                    Float[] rating = ratings[i];
                    ratings[i] = ratings[j];
                    ratings[j] = rating;

                    String matinalNombre = nombreMatinales[i];
                    nombreMatinales[i] = nombreMatinales[j];
                    nombreMatinales[j] = matinalNombre;

                }
            }
        }
        imprimir("Los matinales ordenados son:");
        for (int i = 0; i < nombreMatinales.length; i++) {
            imprimir(i+1 + ". " + nombreMatinales[i] + " prom: " + promedioDeMatinal(ratings[i]));
        }
    }

    public static float promedioDeMatinal(Float[] rating) {
        Float promedio = 0f;
        for (int i = 0; i < rating.length; i++) {
            promedio += (rating[i] / rating.length);
        }
        return promedio;
    }

    ;

    public static void promedioRatingMatinal(String[] nombreMatinales, Float[][] ratings) throws IOException {
        System.out.println("Ingrese el nombre de un matinal, e indique un rango de dias para deciros su promedio en esos dias: ");
        int diaInicio = 0, diaFin = 0;
        String nombreMatinal = ingresarString("Ingrese el nombre del matinal");

        Float promedioMatinal = -1f;
        for (int i = 0; i < nombreMatinales.length; i++) {
            //BUSCA el Matinal
            if (nombreMatinales[i].equals(nombreMatinal)) {
                do {
                    diaInicio = ingresarNumero("Ingrese el Inicio del rango de dias");
                } while (diaInicio < 1 || diaInicio > ratings[0].length - 1);
                do {
                    diaFin = ingresarNumero("Ingrese el Fin del rango de dias");
                } while (diaFin < diaInicio || diaFin > ratings[0].length);
                //CALCULA Promedio del matinal
                promedioMatinal = 0f;
                for (int j = diaInicio - 1; j <= diaFin - 1; j++) {
                    promedioMatinal += (ratings[i][j] / diaFin);
                }
                imprimir("El promedio de rating en esos dias en el matinal '" + nombreMatinal + "' es de " + promedioMatinal);
            }
        }
        if (promedioMatinal == -1) {
            imprimir("El matinal " + nombreMatinal + " no fue encontrado");
        }
    }

    public static void imprimir(String mensaje) {
        System.out.println(mensaje);
    }

    public static String leerTeclado(String mensaje) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        imprimir(mensaje);
        String ingresado = lector.readLine();
        return ingresado;
    }

    ;
    public static int ingresarNumero(String mensaje) throws IOException {
        return Integer.parseInt(leerTeclado(mensaje));
    }

    public static String ingresarString(String mensaje) throws IOException {
        return leerTeclado(mensaje);
    }

    public static float ingresarFloat(String mensaje) throws IOException {
        return Float.parseFloat(leerTeclado(mensaje));
    }

    public static boolean compararFloat(float a, float b) {

        if (a - b < 0.00000001 && a - b > -0.00000001) {
            return true;
        }
        return false;
    }
}
