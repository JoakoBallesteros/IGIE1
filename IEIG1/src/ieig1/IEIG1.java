/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ieig1;

/**
 *
 * @author Fede
 */

import java.util.Random;
import java.util.Scanner;
public class IEIG1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();

        // Crear héroe
        System.out.print("Ingrese nombre del héroe: ");
        String nombreHeroe = sc.nextLine();
        Heroe heroe = new Heroe(nombreHeroe, 100, 20, 5, rnd.nextInt(101));

        // Crear villano
        System.out.print("Ingrese nombre del villano: ");
        String nombreVillano = sc.nextLine();
        Villano villano = new Villano(nombreVillano, 100, 20, 5, rnd.nextInt(101));

        // Mostrar estado inicial
        System.out.println("\n⚔️ ¡Comienza la batalla! ⚔️");
        System.out.println(heroe);
        System.out.println(villano);
        System.out.println();


        if (heroe.bendicion >= 100) {
            System.out.println("Heroe " + heroe.nombre + " puede usar Castigo Bendito!");
        }
        if (villano.bendicion >= 100) {
            System.out.println(" Villano " + villano.nombre + " puede usar Leviatán del Vacío!");
        }
        System.out.println();


        // Simulación de batalla
        while (heroe.estaVivo() && villano.estaVivo()) {
            heroe.decidirAccion(villano);
            if (!villano.estaVivo()) break;
            villano.decidirAccion(heroe);
            System.out.println();
        }

        // Resultado
        if (heroe.estaVivo()) {
            System.out.println(heroe.nombre + " ha ganado la batalla!");
        } else {
            System.out.println(villano.nombre + " ha ganado la batalla!");
        }

        sc.close();
    }
    
}
