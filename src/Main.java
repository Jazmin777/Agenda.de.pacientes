import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class Main {
    // "Base de datos" interna: Estructura de Llave -> Valor
    // Guardaremos: "Día - Hora" -> "Estado (Disponible / Ocupado)"
    private static HashMap<String, String> agendaCompleta = new HashMap<>();

    public static void main(String[] args) {
        // 1. Inicializamos la agenda con días y horas específicas como ejemplo
        String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sabado"};
        String[] horas = {"09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00"};

        // Por defecto, llenamos todo el calendario como "Disponible"
        for (String dia : dias) {
            for (String hora : horas) {
                agendaCompleta.put(dia + " @ " + hora, "Disponible");
            }
        }

        // Simulamos que ya hay algunos horarios ocupados por otros odontólogos
        agendaCompleta.put("Lunes 15 @ 10:00 AM", "Ocupado");
        agendaCompleta.put("Martes 16 @ 09:00 AM", "Ocupado");
        agendaCompleta.put("Martes 16 @ 11:00 AM", "Ocupado");

        // 2. Crear la ventana principal
        JFrame ventana = new JFrame("Agenda Dental - Filtro de Días y Horas");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(550, 500);
        ventana.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        // 3. Componentes del Front
        JLabel etiquetaNombre = new JLabel("Nombre del Paciente:");
        JTextField campoNombre = new JTextField(25);

        JLabel etiquetaDia = new JLabel("Seleccione el Día:");
        JComboBox<String> selectorDias = new JComboBox<>(dias);

        JLabel etiquetaHora = new JLabel("Seleccione la Hora:");
        JComboBox<String> selectorHoras = new JComboBox<>(horas);

        // Alerta visual de disponibilidad
        JLabel etiquetaEstado = new JLabel("Estado del horario: Seleccione opciones");
        etiquetaEstado.setFont(new Font("Arial", Font.BOLD, 13));

        JButton botonAgendar = new JButton("Confirmar Cita en este Horario");

        JTextArea areaResultado = new JTextArea(8, 45);
        areaResultado.setEditable(false);
        JScrollPane scrollResultado = new JScrollPane(areaResultado);

        // 4. Agregar componentes a la ventana
        ventana.add(etiquetaNombre);
        ventana.add(campoNombre);
        ventana.add(new JSeparator(JSeparator.HORIZONTAL));
        ventana.add(etiquetaDia);
        ventana.add(selectorDias);
        ventana.add(etiquetaHora);
        ventana.add(selectorHoras);
        ventana.add(etiquetaEstado);
        ventana.add(botonAgendar);
        ventana.add(scrollResultado);

        // ============================================================
        // LÓGICA CENTRAL: Validador de Disponibilidad (Día + Hora)
        // ============================================================
        ActionListener validadorDisponibilidad = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String diaSeleccionado = (String) selectorDias.getSelectedItem();
                String horaSeleccionada = (String) selectorHoras.getSelectedItem();

                // Creamos la llave combinada para buscar en nuestro mapa
                String llaveAgenda = diaSeleccionado + " @ " + horaSeleccionada;
                String estado = agendaCompleta.get(llaveAgenda);

                // Verificamos si el bloque exacto está libre
                if ("Disponible".equals(estado)) {
                    etiquetaEstado.setText("🟢 ¡HORARIO DISPONIBLE! Dispuesto para agendar.");
                    etiquetaEstado.setForeground(new Color(34, 139, 34)); // Verde
                    botonAgendar.setEnabled(true);
                } else {
                    etiquetaEstado.setText("🔴 HORARIO OCUPADO. Intente con otra hora o día.");
                    etiquetaEstado.setForeground(Color.RED); // Rojo
                    botonAgendar.setEnabled(false); // Congela el botón
                }
            }
        };

        // Enlazamos el validador a ambos selectores (Si cambia el día o cambia la hora, se verifica)
        selectorDias.addActionListener(validadorDisponibilidad);
        selectorHoras.addActionListener(validadorDisponibilidad);

        // Ejecutar una primera validación automática al abrir la pantalla
        selectorDias.setSelectedIndex(0);

        // ============================================================
        // LÓGICA DEL BOTÓN: Registrar la Cita
        // ============================================================
        botonAgendar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreTxt = campoNombre.getText().trim();
                String diaTxt = (String) selectorDias.getSelectedItem();
                String horaTxt = (String) selectorHoras.getSelectedItem();
                String llaveAgenda = diaTxt + " @ " + horaTxt;

                if (nombreTxt.isEmpty()) {
                    areaResultado.setText("⚠ Error: Debe ingresar el nombre del paciente para apartar la cita.");
                    return;
                }

                // 1. Usamos tu estructura POO existente
                Paciente pacienteGui = new Paciente("PAC-GUI", nombreTxt, "555-9876");
                Cita citaGui = new Cita("CITA-GUI", pacienteGui, diaTxt, horaTxt, "Evaluación General");

                // 2. Ejecutamos el método del objeto que ya tenías programado
                citaGui.confirmarCita();

                // 3. Bloqueamos el horario en la base de datos interna
                agendaCompleta.put(llaveAgenda, "Ocupado");

                // 4. Forzamos a la pantalla a actualizarse y ponerse en rojo de inmediato
                selectorDias.actionPerformed(null);

                // 5. Mostramos el reporte en el Front-end
                areaResultado.setText("=== ¡ÉXITO! CITA AGENDADA AUTOMÁTICAMENTE ===\n" +
                        "Paciente: " + nombreTxt + "\n" +
                        "Día reservado: " + diaTxt + "\n" +
                        "Bloque de horario: " + horaTxt + "\n" +
                        "Estado del sistema: Confirmada sin intermediarios.\n\n" +
                        "Este espacio ha quedado bloqueado para otros odontólogos.");

                // Limpiar el campo de texto para el siguiente registro
                campoNombre.setText("");
            }
        });

        // 5. Lanzar ventana
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}