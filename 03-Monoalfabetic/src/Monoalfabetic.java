package src;

import java.util.ArrayList;
import java.util.Random;

public class Monoalfabetic {

    private static final char[] alfabet = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private static String alfabetPermutat = "";


    public static char[] permutaAlfabet(char[] alfabet) {

        ArrayList<Character> lista = new ArrayList<>();
        for (int i = 0; i < alfabet.length; i++) {
            lista.add(alfabet[i]);
        }

        Random random = new Random();

        for (int i = 0; i < lista.size(); i++) {
            int posicio = random.nextInt(lista.size());

            Character temporal = lista.get(i);
            lista.set(i, lista.get(posicio));
            lista.set(posicio, temporal);
        }

        char[] resultat = new char[lista.size()];

        for (int i = 0; i < lista.size(); i++) {
            resultat[i] = lista.get(i);
        }

        return resultat;
    }

    public static String xifraMonoAlfa(String cadena) {
        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {
            char lletraOriginal = cadena.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletraOriginal);
            
            char lletraMajuscula = Character.toUpperCase(lletraOriginal);
            char lletraXifrada = lletraOriginal;

            for (int j = 0; j < alfabet.length; j++) {
                if (alfabet[j] == lletraMajuscula) {
                    char lletraPermutada = alfabetPermutat.charAt(j);
                    if (esMinuscula) {
                        lletraXifrada = Character.toLowerCase(lletraPermutada);
                    } else {
                        lletraXifrada = lletraPermutada;
                    }
                    break;
                }
            }
            resultat += lletraXifrada;
        }

        return resultat;
    }

    public static String desxifraMonoAlfa(String cadena) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char lletra = cadena.charAt(i);
            boolean trobada = false;

            for (int j = 0; j < alfabetPermutat.length(); j++) {

                if (alfabetPermutat.charAt(j) == Character.toUpperCase(lletra)) {
                    char lletraDesxifrada = alfabet[j];

                    if (Character.isLowerCase(lletra)) {
                        lletraDesxifrada = Character.toLowerCase(lletraDesxifrada);
                    }

                    resultat += lletraDesxifrada;
                    trobada = true;
                    break;
                }
            }
            if (!trobada) {
                resultat += lletra;
            }
        }

        return resultat;
    }

    public static void main(String[] args) {
        alfabetPermutat = new String(permutaAlfabet(alfabet));

        System.out.println("Alfabet original:");
        for (int i = 0; i < alfabet.length; i++) {
            System.out.print(alfabet[i] + " ");
        }

        System.out.println();

        System.out.println("Alfabet permutat:");
        for (int i = 0; i < alfabetPermutat.length(); i++) {
            System.out.print(alfabetPermutat.charAt(i) + " ");
        }

        System.out.println();
        System.out.println();

        System.out.println("Xifratge:");

        String test01 = "Test 01 àrbitre, coixí, Perímetre";
        String test02 = "Test 02 Taüll, DÍA, año";
        String test03 = "Test 03 Peça, Òrrius, Bòvila";

        String xifrat01 = xifraMonoAlfa(test01);
        String xifrat02 = xifraMonoAlfa(test02);
        String xifrat03 = xifraMonoAlfa(test03);

        System.out.println(test01 + " -> " + xifrat01);
        System.out.println(test02 + " -> " + xifrat02);
        System.out.println(test03 + " -> " + xifrat03);

        System.out.println();
        System.out.println("Desxifratge:");

        System.out.println(xifrat01 + " -> " + desxifraMonoAlfa(xifrat01));
        System.out.println(xifrat02 + " -> " + desxifraMonoAlfa(xifrat02));
        System.out.println(xifrat03 + " -> " + desxifraMonoAlfa(xifrat03));
    }
}