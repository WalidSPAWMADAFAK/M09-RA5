import java.util.Random;

public class Polialfabetic {

    private static final char[] alfabet = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ".toCharArray();
    private static char[] alfabetPermutat;
    private static Random random;
    private static final int clauSecreta = 1234;

    public static void initRandom(int clau) {
        random = new Random(clau);
    }

    public static void permutaAlfabet() {
        alfabetPermutat = alfabet.clone();

        for (int i = alfabetPermutat.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = alfabetPermutat[i];
            alfabetPermutat[i] = alfabetPermutat[j];
            alfabetPermutat[j] = temp;
        }
    }

    public static String xifraPoliAlfa(String msg) {
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            char lletraOriginal = msg.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletraOriginal);
            char lletraMajuscula = Character.toUpperCase(lletraOriginal);
            char lletraXifrada = lletraOriginal;

            permutaAlfabet();

            for (int j = 0; j < alfabet.length; j++) {
                if (alfabet[j] == lletraMajuscula) {
                    char lletraPermutada = alfabetPermutat[j];
                    if (esMinuscula) {
                        lletraXifrada = Character.toLowerCase(lletraPermutada);
                    } else {
                        lletraXifrada = lletraPermutada;
                    }
                    break;
                }
            }

            resultat.append(lletraXifrada);
        }

        return resultat.toString();
    }

    public static String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < msgXifrat.length(); i++) {
            char lletraXifrada = msgXifrat.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletraXifrada);
            char lletraMajuscula = Character.toUpperCase(lletraXifrada);
            char lletraOriginal = lletraXifrada;

            permutaAlfabet();

            for (int j = 0; j < alfabetPermutat.length; j++) {
                if (alfabetPermutat[j] == lletraMajuscula) {
                    char lletraDesxifrada = alfabet[j];
                    if (esMinuscula) {
                        lletraOriginal = Character.toLowerCase(lletraDesxifrada);
                    } else {
                        lletraOriginal = lletraDesxifrada;
                    }
                    break;
                }
            }

            resultat.append(lletraOriginal);
        }

        return resultat.toString();
    }

    public static void main(String[] args) {
        String msgs[] = {
            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("\nDesxifratge:\n------------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}