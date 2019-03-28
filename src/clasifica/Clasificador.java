package clasifica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Clasificador {
    static ArrayList<String> lista = new ArrayList();
    
    public static void main(String[] args) {
        Lector l = (a) -> {
            try {
                BufferedReader br = new BufferedReader(
                                    new InputStreamReader(
                                    new FileInputStream(a)));
                String line = br.readLine();
                if (line != null) {
                    while (line != null) {
                        lista.add(line);
                        line = br.readLine();
                    }
                    return lista;
                } else return null;
            } catch (IOException e) { return null; }
        };
        lista = l.leer(new File("src/clasifica/archivo.txt"));
        
        lista.forEach((palabra) -> {
            run(palabra);
            System.out.println();
        });
    }
    
    public static void run(String c) {
        System.out.println("Palabra: " + c
                        + "\nLogitud: " + c.length()
                        + "\nMayúsculas: " + c.toUpperCase()
                        + "\nMinúsculas: "+ c.toLowerCase());

        Palindromo p = (String cad) -> {
            cad = cad.replace(" ", "").toLowerCase();
            for (int i = 0; i < cad.length(); i++)
                if (cad.charAt(i) != cad.charAt(cad.length() - i - 1))
                    return false;
            return true;
        };
        System.out.println("¿Palindromo? " + p.esPalindromo(c));
        
        long rep = lista.stream()
                .filter(e -> e.equals(lista.get(lista.indexOf(c)))).count();
        System.out.println("Repeticiones: " + rep);
    }
}