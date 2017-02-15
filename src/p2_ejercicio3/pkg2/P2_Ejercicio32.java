/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2_ejercicio3.pkg2;

import java.time.LocalTime;
import java.util.Scanner;

/**
 *
 * @author dygyb
 */
public class P2_Ejercicio32 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String titulo;
        String locutor;
        int duracion;
        int duracionTotal = 0;
        int contador = 0;

        // vector que guarda los programas de radio
        ProgramaRadio[] programasRadio = new ProgramaRadio[24];

        LocalTime localTime = LocalTime.of(0, 0);
        LocalTime horaEmision;

        Scanner scan = new Scanner(System.in);

        do {
            try {
                //Pedimos los datos
                System.out.print("Titulo del programa de radio?: ");
                titulo = scan.nextLine();

                System.out.print("Locutor del programa de radio?: ");
                locutor = scan.nextLine();

                System.out.print("Duracion del programa de radio?: ");
                duracion = Integer.parseInt(scan.nextLine());

                if (duracion < 60) {
                    throw new Exception("No puede haber un programa de radio que dure menos de 60 minutos: "
                            + duracion);
                }

                //Utilizamos la duracion total del anterior programa para iniciar la hora de emision del program actual
                horaEmision = localTime.plusMinutes(duracionTotal);

                //Guardamos la duracion del programa y comprobamos que no supere los 1440 min, si lo hace lazamos excepcion y lo quitamos de la duracion total de los programas
                if ((duracionTotal += duracion) > 1440) {
                    duracionTotal -= duracion;
                    throw new Exception("Los programas de radio no pueden exceder de 1440 minutos: "
                            + duracion);

                }

                //Creamos un objeto ProgramaRadio y lo guardamos en un vector
                programasRadio[contador] = new ProgramaRadio(titulo, locutor, duracion, horaEmision);
                contador++;

            } catch (NumberFormatException exc) {
                System.err.println("ERROR de formato numérico");
            } catch (Exception exc) {
                System.err.println(exc);
            }
        } while (duracionTotal < 1440);

        for (int i = 0; i < contador; i++) {
            //No le paso como condicion limite programasRadio.length porque puede no estar completo y contener elementos null y saltaria un error NullpointerException
            System.out.println("Programa " + i + programasRadio[i].toString());
        }
    }

}
