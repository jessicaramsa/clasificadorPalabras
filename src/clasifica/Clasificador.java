package clasifica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JFileChooser;

public class Clasificador {
    static ArrayList<String> lista = new ArrayList();
    
    public static void main(String[] args) {
        //lista = leer(abrir());
        lista = leer(new File("src/clasificador/archivo.txt"));
        lista.forEach((palabra) -> {
            run(palabra);
            System.out.println();
        });
    }
    
    public static void run(String c) {
        System.out.println("Palabra: " + c);
        System.out.println("Longitud: " + c.length());
        System.out.println("Mayúsculas: " + c.toUpperCase());
        System.out.println("Minúsculas: " + c.toLowerCase());
        System.out.println("¿Palindromo?: " + esPalindromo(c.replaceAll(" ", "")));
        
        long rep = lista.stream()
                .filter(e -> e.equals(lista.get(lista.indexOf(c)))).count();
        System.out.println("Repeticiones: " + rep);
    }
    
    static boolean esPalindromo(String cad) {
        cad = cad.toLowerCase();
        for (int i = 0; i < cad.length(); i++)
            if (cad.charAt(i) != cad.charAt(cad.length() - i - 1))
                return false;
        return true;
    }
    
    public static File abrir() {
        JFileChooser fch = new JFileChooser("src/clasificador");
        fch.showOpenDialog(fch);
        
        return fch.isFileSelectionEnabled() ?
                new File(fch.getSelectedFile().getAbsolutePath()) : null;
    }
    
    public static ArrayList leer(File arch) {
        try {
            BufferedReader br = new BufferedReader(
                                new InputStreamReader(
                                new FileInputStream(arch)));
            
            String l = br.readLine();
            if (l != null) {
                while (l != null) {
                    lista.add(l);
                    l = br.readLine();
                }
                return lista;
            } else return null;
        } catch (IOException e) { return null; }
    }
}
