public class Paciente {
        private String idPaciente;
        private String nombre;
        private String telefono;
        private boolean tieneHistorialClinico;

        public Paciente(String idPaciente, String nombre, String telefono) {
            this.idPaciente = idPaciente;
            this.nombre = nombre;
            this.telefono = telefono;
            this.tieneHistorialClinico = false;
        }

        public void crearHistorial() {
            this.tieneHistorialClinico = true;
            System.out.println("✔ Historial clínico generado para: " + nombre);
        }

        public String getNombre() { return nombre; }
        public boolean isTieneHistorialClinico() { return tieneHistorialClinico; }
    }

