package br.edu.iftm.pv_projetoimobiliaria_pt2.util;

public class LimpaTela {
    /**
     * Limpa a tela do console.
     * Funciona em terminais ANSI (Linux, macOS) e no Windows.
     */
    public static void limpar() {
        try {
            // Verifica o sistema operacional
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Linux, macOS e outros sistemas Unix
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Em caso de erro, apenas imprime várias linhas em branco
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}