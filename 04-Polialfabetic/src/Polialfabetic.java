import java.util.Random;

public class Polialfabetic {

    static String alfabet = "aáàbcçdeéèfghiíïjklmnñoòópqrstuúüvwxyz";

    static char[] alfabetPermutat;
    static Random random;
    static long clauSecreta = 12345678;
    
    public static void initRandom(long clau) {
       random = new Random(clau);
    }
    public static void permutaAlfabet() {
        alfabetPermutat = alfabet.toCharArray();

        for (int i = alfabetPermutat.length - 1; i > 0; i--) {
            int posicio = random.nextInt(i + 1);
            char temporal = alfabetPermutat[i];
            alfabetPermutat[i] = alfabetPermutat[posicio];
            alfabetPermutat[posicio] = temporal;
         }
    }
    public static String xifraPoliAlfa(String msg) {

        String resultat = "";
        int comptadorLletres = 0;

        for (int i = 0; i < msg.length(); i++) {

            char lletra = msg.charAt(i);
            int posicio = alfabet.indexOf(Character.toLowerCase(lletra));

            if (posicio != -1) {

                if (comptadorLletres % 2 == 0) {
                    permutaAlfabet();
                }

                char lletraXifrada = alfabetPermutat[posicio];

                if (Character.isUpperCase(lletra)) {
                    lletraXifrada = Character.toUpperCase(lletraXifrada);
                }

                resultat += lletraXifrada;
                comptadorLletres++;

            } else {
                resultat += lletra;
            }
        }

        return resultat;
    }

    public static String desxifraPoliAlfa(String msgXifrat) {

        String resultat = "";
        int comptadorLletres = 0;

        for (int i = 0; i < msgXifrat.length(); i++) {

            char lletra = msgXifrat.charAt(i);

            if (Character.isLetter(lletra)) {

                if (comptadorLletres % 2 == 0) {
                    permutaAlfabet();
                }

                int posicio = -1;

                for (int j = 0; j < alfabetPermutat.length; j++) {

                    if (Character.toLowerCase(alfabetPermutat[j])
                            == Character.toLowerCase(lletra)) {
                        posicio = j;
                        break;
                    }
                }

            if (posicio != -1) {

                    char lletraDesxifrada = alfabet.charAt(posicio);

                    if (Character.isUpperCase(lletra)) {
                       lletraDesxifrada = Character.toUpperCase(lletraDesxifrada);
                    }

                  resultat += lletraDesxifrada;

                } else {
                    resultat += lletra;
                }
                comptadorLletres++;

            } else {
                resultat += lletra;
         }
        }
        return resultat;
    }
    public static void main(String[] args) {

        String[] msgs = {
                "Test 01 àrbitre, coixí, Perímetre",
                "Test 02 Taüll, DÍA, año",
                "Test 03 Peça, Òrrius, Bòvila" };

        String[] msgsXifrats = new String[msgs.length];

        System.out.println("Xifratge:\n----------");

        for (int i = 0; i < msgs.length; i++) {

            initRandom(clauSecreta);
          msgsXifrats[i] = xifraPoliAlfa(msgs[i]);

            System.out.printf("%-34s -> %s%n",msgs[i],msgsXifrats[i] );
        }

        System.out.println("Desxifratge:\n------------");

        for (int i = 0; i < msgs.length; i++) {

            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n",msgsXifrats[i], msg);
         }
  }
}