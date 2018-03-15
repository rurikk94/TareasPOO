import java.io.*;

public class Tarea20180313 {

    public static void main(String[] args) throws IOException {
//        Float[][] ratings = {{2f, 5f, 6.5f, 0.1f, 6f, 8f, 5f}, {1f, 2f, 3f, 4f, 5f, 6f, 7f}, {10f, 5f, 6f, 7f, 4f, 5f, 5f}, {10f, 5f, 6f, 7f, 4f, 5f, 5f}};
//        String[] nombreMatinales = {"chv", "c13", "tvn", "cqc"};

        int matinales, dias,respuesta=0;
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
        
        
        while (respuesta !=4)
        {
        respuesta=ingresarNumero("Ingrese una Opcion \n 1. Mejor Matinal 2. Promedio 3.Ordenar Matinales 4.Salir");
            switch(respuesta){            
                case 1:
                    imprimir("El matinal con mayor promedio de rating es: ");
                    imprimir(mejoresMatinales(nombreMatinales, ratings));                    
                    break;
                case 2:
                    imprimir("Ingrese el nombre de un matinal, e indique un rango de dias para deciros su promedio en esos dias: ");
                    String nombreMatinal = ingresarString("Ingrese el nombre del matinal");
                    int diaInicio,diaFin;
                    do {
                         diaInicio = ingresarNumero("Ingrese el Inicio del rango de dias (1~"+ (ratings[0].length - 1)+")");
                    } while (diaInicio < 1 || diaInicio > ratings[0].length - 1);
                    do {
                        diaFin = ingresarNumero("Ingrese el Fin del rango de dias ("+(diaInicio+1)+"~"+ (ratings[0].length)+")");
                    } while (diaFin < diaInicio || diaFin > ratings[0].length);
                   Float promedioRatingMatinal = promedioRatingMatinal(nombreMatinales, ratings,nombreMatinal,diaInicio,diaFin);
                    if (promedioRatingMatinal != -1)
                        imprimir("El promedio de rating en esos dias en el matinal '" + nombreMatinal + "' es de " + promedioRatingMatinal);
                    else
                        imprimir("El matinal " + nombreMatinal + " no fue encontrado");                    
                    break;
                case 3:
                    ordenarMatinales(nombreMatinales, ratings);
                    imprimir("Los matinales ordenados son:");
                    for (int i = 0; i < nombreMatinales.length; i++) {
                        imprimir(i+1 + ". " + nombreMatinales[i] + " prom: " + promedioDeMatinal(ratings[i]));
                    }
                    break;            
            }
        
            
        }
        

    }

    public static String mejoresMatinales(String[] nombreMatinales, Float[][] ratings) {

        Float promedioMatinal, mejorPromedio;
        mejorPromedio = -10f;
        String mejoresMatinales="";

        for (int i = 0; i < nombreMatinales.length; i++) {
            promedioMatinal = promedioDeMatinal(ratings[i]);
            if (promedioMatinal > mejorPromedio) {
                mejorPromedio = promedioMatinal;
            }
        }

        for (int i = 0; i < nombreMatinales.length; i++) {
            promedioMatinal = promedioDeMatinal(ratings[i]);
            if (compararFloat(mejorPromedio, promedioMatinal)) {
                mejoresMatinales += nombreMatinales[i] + " ";
            }
        }
        return mejoresMatinales;
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
    }

    public static float promedioDeMatinal(Float[] rating) {
        Float promedio = 0f;
        for (int i = 0; i < rating.length; i++) {
            promedio += (rating[i] / rating.length);
        }
        return promedio;
    }

    ;

    public static Float promedioRatingMatinal(String[] nombreMatinales, Float[][] ratings,String nombreMatinal,int diaInicio, int diaFin) throws IOException {
        
        Float promedioMatinal = -1f;
        for (int i = 0; i < nombreMatinales.length; i++) {
            //BUSCA el Matinal
            if (nombreMatinales[i].equals(nombreMatinal)) {
                //CALCULA Promedio del matinal
                promedioMatinal = 0f;
                for (int j = diaInicio - 1; j <= diaFin - 1; j++) {
                    promedioMatinal += (ratings[i][j] / diaFin);
                }
            }
        }
        return promedioMatinal;
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
