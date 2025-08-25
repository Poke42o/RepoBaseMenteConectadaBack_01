package eventos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HU033C3SolicitarCodigoEvento {

    public static void main(String[] args) {

        List<String> eventos = new ArrayList<>();
        eventos.add("Taller de Control Emocional");
        eventos.add("Seminario sobre Trastorno Límite de la Personalidad (TLP)");
        eventos.add("Jornada de Mindfulness y Ansiedad");
        eventos.add("Charla sobre Depresión y Autoestima");
        eventos.add("Grupo de Apoyo para Jóvenes con Ansiedad");
        eventos.add("Conferencia: Salud Mental en el Trabajo");

        Scanner scanner = new Scanner(System.in);
        int indiceEvento = -1;

        // Bucle para mostrar la lista y permitir la selección del evento
        do {
            mostrarEventos(eventos);
            System.out.println("Por favor, ingrese el número del evento para validar la fecha: ");

            try {
                indiceEvento = Integer.parseInt(scanner.nextLine()) - 1;

                if (indiceEvento >= 0 && indiceEvento < eventos.size()) {
                    String nombreEventoSeleccionado = eventos.get(indiceEvento);
                    System.out.println("\nValidando fecha para el evento: " + nombreEventoSeleccionado);
                    validarFechaEvento(scanner); // Se pasa el objeto scanner
                    break; // Salir del bucle si la selección es válida
                } else {
                    System.out.println("❌ ERROR: Número de evento no válido. Por favor, intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ ERROR: Entrada no válida. Por favor, ingrese un número.");
            }
        } while (true);

        scanner.close();
    }

    /**
     Muestra la lista de eventos con sus respectivos índices.
     *
     * @param eventos La lista de eventos a mostrar.
     */
    public static void mostrarEventos(List<String> eventos) {
        System.out.println("\n--- Lista de Eventos ---");
        for (int i = 0; i < eventos.size(); i++) {
            System.out.println((i + 1) + ". " + eventos.get(i));
        }
        System.out.println("------------------------");
    }

    /**
     * Valida que la fecha ingresada por el usuario no esté vacía,
     * tenga 10 caracteres y cumpla con el formato dd-MM-yyyy.
     *
     * @param scanner El objeto Scanner para la entrada del usuario.
     */
    public static void validarFechaEvento(Scanner scanner) {
        String fechaIngresada;
        boolean fechaValida = false;

        do {
            System.out.println("Por favor, ingrese la fecha del evento (dd-MM-yyyy):");
            fechaIngresada = scanner.nextLine();

            // 1. Verificación de campo vacío
            if (fechaIngresada.trim().isEmpty()) {
                System.out.println("❌ ERROR: La fecha no puede estar vacía. Por favor, reintente.");
                continue;
            }

            // 2. Verificación de la longitud de la fecha
            if (fechaIngresada.length() != 10) {
                System.out.println("❌ ERROR: La fecha debe tener 10 caracteres (dd-MM-yyyy). Por favor, reintente.");
                continue;
            }

            // 3. Verificación del formato con Regex
            String regex = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)\\d{2}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(fechaIngresada);

            if (matcher.matches()) {
                System.out.println("✅ ¡Fecha validada correctamente! La fecha ingresada es: " + fechaIngresada);
                fechaValida = true;
            } else {
                System.out.println("❌ ERROR: El formato de fecha no es válido. Utilice el formato dd-MM-yyyy. Por favor, reintente.");
            }

        } while (!fechaValida);
    }
}