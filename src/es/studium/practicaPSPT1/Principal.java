package es.studium.practicaPSPT1;


import javax.swing.SwingUtilities;

public class Principal {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Vista vista = new Vista();
            vista.crearYMostrarGUI();
        });
    }
}