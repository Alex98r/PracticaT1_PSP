package es.studium.practicaPSPT1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.SwingConstants;

public class Vista {

    static void crearYMostrarGUI() {
       
        String programFilesPath = "C:\\Program Files";
        String programFilesX86Path = "C:\\Program Files (x86)";

        
        JFrame frame = new JFrame("Lanzador de aplicaciones");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 2)); // Dividir en dos filas y dos columnas

        
        JLabel lbl1 = new JLabel ("Program Files (x86)");
        JLabel lbl2 = new JLabel ("Program Files");
        lbl1.setHorizontalAlignment(SwingConstants.CENTER);
        lbl2.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel programFilesPanel = crearPanelDeEjecutables(programFilesPath);
        JPanel programFilesX86Panel = crearPanelDeEjecutables(programFilesX86Path);

        
        JScrollPane programFilesScrollPane = new JScrollPane(programFilesPanel);
        JScrollPane programFilesX86ScrollPane = new JScrollPane(programFilesX86Panel);

        
        frame.add(lbl1);
        frame.add(lbl2);
        frame.add(programFilesX86ScrollPane);
        frame.add(programFilesScrollPane);
        frame.setSize(800, 400); 
        frame.setVisible(true);
    }

    private static JPanel crearPanelDeEjecutables(String folderPath) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            listarEjecutablesEnCarpeta(panel, folder);
        } else {
            JLabel label = new JLabel("La carpeta especificada no existe: " + folderPath);
            panel.add(label);
        }

        return panel;
    }

    private static void listarEjecutablesEnCarpeta(JPanel panel, File folder) {
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    listarEjecutablesEnCarpeta(panel, file);
                } else if (file.isFile() && file.getName().toLowerCase().endsWith(".exe")) {
                    String executableName = file.getName();
                    JLabel label = new JLabel(executableName);
                    label.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (e.getClickCount() == 2) {
                                Controlador.ejecutarEjecutable(file.getAbsolutePath());
                            }
                        }
                    });
                    panel.add(label);
                }
            }
        }
    }

    
}
