package src;

import java.util.Random;

public class Monoalfabetic {
    static String alfabet = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ";

    static char[] alfabetPermutat = permutaAlfabet(alfabet);

    public static char[] permutaAlfabet(String alfabet) {

        char[] resultat = alfabet.toCharArray();

        Random random = new Random();

        for (int i = resultat.length - 1; i > 0; i--) {

            int posicio = random.nextInt(i + 1);

            char temporal = resultat[i];
            resultat[i] = resultat[posicio];
            resultat[posicio] = temporal;
        }

        return resultat;
    }

    public static String xifraMonoAlfa(String cadena) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char caracter = cadena.charAt(i);

         
            char caracterMajuscula = Character.toUpperCase(caracter);

            for (int j = 0; j < alfabet.length(); j++) {

                if (caracterMajuscula == alfabet.charAt(j)) {

                    char xifrat = alfabetPermutat[j];

                    if (Character.isLowerCase(caracter)) {
                        xifrat = Character.toLowerCase(xifrat);
                    }

                    resultat += xifrat;
                    break;
                }

               
                if (j == alfabet.length() - 1) {
                    resultat += caracter;
                }
            }
        }

        return resultat;
    }

    public static String desxifraMonoAlfa(String cadena) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char caracter = cadena.charAt(i);

            char caracterMajuscula = Character.toUpperCase(caracter);

            for (int j = 0; j < alfabetPermutat.length; j++) {

                if (caracterMajuscula == alfabetPermutat[j]) {

                    char desxifrat = alfabet.charAt(j);

                 
                    if (Character.isLowerCase(caracter)) {
                        desxifrat = Character.toLowerCase(desxifrat);
                    }

                    resultat += desxifrat;
                    break;
                }

             
                if (j == alfabetPermutat.length - 1) {
                    resultat += caracter;
                }
            }
        }

        return resultat;
    }

   public static void main(String[] args) {

    
    for (int i = 0; i < alfabet.length(); i++) {
        System.out.print(alfabet.charAt(i) + " ");
    }

    System.out.println();


    for (int i = 0; i < alfabetPermutat.length; i++) {
        System.out.print(alfabetPermutat[i] + " ");
    }

    System.out.println();
    System.out.println();

    String text1 = "Test 01 àrbitre, coixí, Perímetre";
    String text2 = "Test 02 Taüll, DÍA, año";
    String text3 = "Test 03 Peça, Òrrius, Bòvila";

    System.out.println("Xifratge:");

    String xifrat1 = xifraMonoAlfa(text1);
    String xifrat2 = xifraMonoAlfa(text2);
    String xifrat3 = xifraMonoAlfa(text3);


    System.out.println(text1 + " -> " + xifrat1);
    System.out.println(text2 + " -> " + xifrat2);
    System.out.println(text3 + " -> " + xifrat3);

    System.out.println();

    System.out.println("Desxifratge:");

    System.out.println(xifrat1 + " -> " + desxifraMonoAlfa(xifrat1));
    System.out.println(xifrat2 + " -> " + desxifraMonoAlfa(xifrat2));
    System.out.println(xifrat3 + " -> " + desxifraMonoAlfa(xifrat3));
  }
}