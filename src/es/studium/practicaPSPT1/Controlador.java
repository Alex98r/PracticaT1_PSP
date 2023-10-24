package es.studium.practicaPSPT1;

import java.io.IOException;

public class Controlador {
    public static void ejecutarEjecutable(String ejecutablePath) {
        try {
            Process process = Runtime.getRuntime().exec(ejecutablePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    }
