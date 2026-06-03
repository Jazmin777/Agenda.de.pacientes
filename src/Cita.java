public class Cita {
    private String idCita;
    private Paciente paciente; // Relación con la clase Paciente
    private String fecha;
    private String hora;
    private String tratamiento;
    private String estado;

    public Cita(String idCita, Paciente paciente, String fecha, String hora, String tratamiento) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
        this.tratamiento = tratamiento;
        this.estado = "Pendiente";
    }

    public void confirmarCita() {
        this.estado = "Confirmada";
        System.out.println("Cita " + idCita + " CONFIRMADA para " + paciente.getNombre() + " el " + fecha + " a las " + hora);

        if (!paciente.isTieneHistorialClinico()) {
            System.out.println("[Nota]: El paciente es nuevo. Llenar historial clínico al llegar.");
        }
    }

    public void mostrarDatosCita() {
        System.out.println("=========================================");
        System.out.println("Código de Cita : " + idCita);
        System.out.println("Paciente       : " + paciente.getNombre());
        System.out.println("Fecha y Hora   : " + fecha + " a las " + hora);
        System.out.println("Tratamiento    : " + tratamiento);
        System.out.println("Estado Actual  : [" + estado + "]");
        System.out.println("=========================================\n");
    }
}