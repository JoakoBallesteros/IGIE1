/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ieig1;

import java.util.Random;
import java.util.Scanner;

public class IEIG1 {

    // === Historial de batallas (últimas 5) ===
    private static final int MAX_BATALLAS = 5;
    private static String[] historialBatallas = new String[MAX_BATALLAS];
    private static int contadorBatallas;
    private static int numeroBatallaGlobal = 0;

    // ===================== REPORTE FINAL (tu código) =====================
    private static void generarReporteFinal(Heroe heroe, Villano villano, int turnos) {
        StringBuilder sb = new StringBuilder();

        sb.append("\n=======================================\n");
        sb.append("        REPORTE FINAL DE LA BATALLA\n");
        sb.append("=======================================\n");

        // Mantengo tu salida tal cual
        sb.append("Heroe: apodo ").append(heroe.getNombre())
          .append(" - Vida final: ").append(heroe.getVida()).append("\n");
        sb.append("Villano: apodo ").append(villano.getNombre())
          .append(" - Vida final: ").append(villano.getVida()).append("\n");

        // --- ARMAS INVOCADAS ---
        sb.append("\n--- ARMAS INVOCADAS ---\n");
        sb.append(formatearArmasInvocadas(heroe));
        sb.append(formatearArmasInvocadas(villano));

        // --- ATAQUES ESPECIALES ---
        sb.append("\n--- ATAQUES ESPECIALES ---\n");
        boolean huboAlgo = false;

        if (heroe.getSupremosUsados() > 0) {
            sb.append(heroe.getNombre())
              .append(" activó \"Castigo Bendito\" → ")
              .append(heroe.getUltimoDanoCastigo())
              .append(" de daño\n");
            huboAlgo = true;
        }

        if (villano.getSupremosUsados() > 0) {
            if (villano.getUltimoDanoLeviatan() > 0) {
                sb.append(villano.getNombre())
                  .append(" invocó \"Leviatán del Vacío\" → ")
                  .append(villano.getUltimoDanoLeviatan())
                  .append(" de daño\n");
            } else if (villano.leviatanInterrumpido()) {
                sb.append(villano.getNombre())
                  .append(" invocó \"Leviatán del Vacío\" (interrumpido en turno ")
                  .append(turnos)
                  .append(")\n");
            }
            huboAlgo = true;
        } else if (villano.leviatanInterrumpido()) {
            sb.append(villano.getNombre())
              .append(" invocó \"Leviatán del Vacío\" (interrumpido en turno ")
              .append(turnos)
              .append(")\n");
            huboAlgo = true;
        }

        if (!huboAlgo) {
            sb.append("(sin usos de ataques supremos)\n");
        }

        // --- HISTORIAL RECIENTE ---
        sb.append("\n--- HISTORIAL RECIENTE ---\n");
        int total = Math.min(contadorBatallas, MAX_BATALLAS);
        if (total == 0) {
            sb.append("Aún no se ha registrado ninguna batalla.\n");
        } else {
            for (int i = 0; i < total; i++) {
                String entrada = historialBatallas[i];
                if (entrada == null) entrada = "(vacío)";
                sb.append(entrada).append("\n");
            }
        }

        sb.append("=======================================\n");
        System.out.println(sb.toString());
    }


    private static String formatearArmasInvocadas(Personaje p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.getNombre()).append(": ");
        if (p.getArmasInvocadas().isEmpty()) {
            sb.append("(no invocó armas)");
        } else {
            boolean primero = true;
            for (var e : p.getArmasInvocadas().entrySet()) {
                if (!primero) sb.append(", ");
                sb.append(e.getKey()).append(" (").append(e.getValue()).append(")");
                primero = false;
            }
        }
        return sb.append("\n").toString();
    }
    // ==========================================================

    // Crea la entrada usando StringBuilder en el formato pedido
    public static String crearEntradaBatalla(String heroe, String villano, String ganador, int turnos) {
        StringBuilder sb = new StringBuilder();
        sb.append("BATALLA #").append(numeroBatallaGlobal).append(" - ")
          .append("Heroe: ").append(heroe).append(" | ")
          .append("Villano: ").append(villano).append(" | ")
          .append("Ganador: ").append(ganador).append(" | ")
          .append("Turnos: ").append(turnos);
        return sb.toString();
    }

    // Guarda la última batalla manteniendo solo las últimas MAX_BATALLAS
    public static void guardarBatalla(String batalla) {
        if (contadorBatallas < MAX_BATALLAS) {
            historialBatallas[contadorBatallas] = batalla;
            contadorBatallas++;
        } else {
            for (int i = 0; i < MAX_BATALLAS - 1; i++) {
                historialBatallas[i] = historialBatallas[i + 1];
            }
            historialBatallas[MAX_BATALLAS - 1] = batalla;
        }
    }

    // (mostrar historial con StringBuilder)
    public static void mostrarHistorial() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n--- HISTORIAL RECIENTE ---\n");

        if (contadorBatallas == 0) {
            sb.append("Aún no se ha registrado ninguna batalla.\n");
        } else {
            int total = Math.min(contadorBatallas, MAX_BATALLAS);
            for (int i = 0; i < total; i++) {
                String entrada = historialBatallas[i];
                if (entrada == null) entrada = "(vacío)";
                sb.append(entrada).append("\n");
            }
        }

        System.out.println(sb.toString());
    }


    private static String nombreMostrar(String nombreBase, Apodo apodo) {
        if (apodo == null || apodo.getValor() == null || apodo.getValor().isBlank()) return nombreBase;
        return nombreBase + " (" + apodo.getValor() + ")";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rnd = new Random();


        Apodo apodoHeroe = null;
        while (apodoHeroe == null) {
            try {
                System.out.print("Ingrese apodo para el Héroe: ");
                apodoHeroe = new Apodo(sc.nextLine()); // valida: 3-10, solo letras/espacios
            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage());
            }
        }

        Apodo apodoVillano = null;
        while (apodoVillano == null) {
            try {
                System.out.print("Ingrese apodo para el Villano: ");
                apodoVillano = new Apodo(sc.nextLine()); // valida: 3-10, solo letras/espacios
            } catch (IllegalArgumentException e) {
                System.out.println("❌ " + e.getMessage());
            }
        }


        System.out.print("Ingrese nombre del héroe: ");
        String nombreHeroe = sc.nextLine();
        Heroe heroe = new Heroe(
                nombreHeroe,
                100 + rnd.nextInt(41),
                21 + rnd.nextInt(11),
                5 + rnd.nextInt(8),
                rnd.nextInt(102)
        );


        System.out.print("Ingrese nombre del villano: ");
        String nombreVillano = sc.nextLine();
        Villano villano = new Villano(
                nombreVillano,
                90 + rnd.nextInt(41),
                20 + rnd.nextInt(11),
                6 + rnd.nextInt(8),
                rnd.nextInt(101)
        );


        String heroeMostrar = nombreMostrar(heroe.nombre, apodoHeroe);
        String villanoMostrar = nombreMostrar(villano.nombre, apodoVillano);

        System.out.println("\n⚔️ ¡Comienza la batalla! ⚔️");
        pausa(1500);
        System.out.println(heroe);
        System.out.println(villano);
        System.out.println();
        pausa(2000);

        if (heroe.bendicion >= 100) {
            System.out.println("Heroe " + heroeMostrar + " puede usar Castigo Bendito!");
        }
        if (villano.bendicion >= 100) {
            System.out.println(" Villano " + villanoMostrar + " puede usar Leviatán del Vacío!");
        }
        System.out.println();
        pausa(1000);

        int turno = 0;
        while (heroe.estaVivo() && villano.estaVivo()) {
            turno++;
            heroe.decidirAccion(villano);
            pausa(1500);
            if (!villano.estaVivo()) break;
            villano.decidirAccion(heroe);
            pausa(1500);
            System.out.println();
        }

        String ganadorNombre;
        pausa(1000);
        if (heroe.estaVivo()) {
            ganadorNombre = heroeMostrar;
            System.out.println(heroeMostrar + " ha ganado la batalla!");
        } else {
            ganadorNombre = villanoMostrar;
            System.out.println(villanoMostrar + " ha ganado la batalla!");
        }


        numeroBatallaGlobal++; // incrementar ANTES de armar la entrada
        String entrada = crearEntradaBatalla(heroeMostrar, villanoMostrar, ganadorNombre, turno);
        guardarBatalla(entrada);
        mostrarHistorial();

        // reporte completo 
        generarReporteFinal(heroe, villano, turno);

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
