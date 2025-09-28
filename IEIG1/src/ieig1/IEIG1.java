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
        Heroe heroe = new Heroe(nombreHeroe,
                100 + rnd.nextInt(41),
                21 + rnd.nextInt(11),
                5 + rnd.nextInt(8),
                rnd.nextInt(102));

        // Crear villano
        System.out.print("Ingrese nombre del villano: ");
        String nombreVillano = sc.nextLine();
        Villano villano = new Villano(nombreVillano,
                90 + rnd.nextInt(41),
                20 + rnd.nextInt(11),
                6 + rnd.nextInt(8),
                rnd.nextInt(101));

        System.out.println("\n⚔️ ¡Comienza la batalla! ⚔️");
        pausa(1500);
        System.out.println(heroe);
        System.out.println(villano);
        System.out.println();
        pausa(2000);

        if (heroe.bendicion >= 100) {
            System.out.println("Heroe " + heroe.nombre + " puede usar Castigo Bendito!");
        }
        if (villano.bendicion >= 100) {
            System.out.println(" Villano " + villano.nombre + " puede usar Leviatán del Vacío!");
        }
        System.out.println();
        pausa(1000);

        while (heroe.estaVivo() && villano.estaVivo()) {
            heroe.decidirAccion(villano);
            pausa(1500);
            if (!villano.estaVivo()) {
                break;
            }
            villano.decidirAccion(heroe);
            pausa(1500);
            System.out.println();
        }

        pausa(1000);
        if (heroe.estaVivo()) {
            System.out.println(heroe.nombre + " ha ganado la batalla!");
        } else {
            System.out.println(villano.nombre + " ha ganado la batalla!");
        }

        sc.close();
    }

    public static void pausa(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}