package src;

public class RotX {
    
    private static final String lletres = "AÀÁBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private static final char[] minuscules = lletres.toLowerCase().toCharArray();
    private static final char[] majuscules = lletres.toCharArray();

    public static void main(String[] args) {
        String msgs[] = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String msgsXifrats[] = new String[msgs.length];


        System.out.println("\nXifrat");
        System.out.println("------");

         for (int i = 0; i < msgs.length; i++) {

            int desplaçament = i * 2;
            msgsXifrats[i] = xifraRotX(msgs[i], desplaçament);
            System.out.printf("(%d)-%-21s => %s%n", desplaçament, msgs[i], msgsXifrats[i]);
        }

        System.out.println("\nDesxifrat");
        System.out.println("---------");

        for (int i = 0; i < msgsXifrats.length; i++) {

            int desplaçament = i * 2;
            System.out.printf("(%d)-%-21s => %s%n", desplaçament, msgsXifrats[i], desxifraRotX(msgsXifrats[i], desplaçament));
        }

        System.out.println("\nForça bruta");
        System.out.println("-----------");

        String missatgeXifrat = xifraRotX("Perdó, per tu què és?", 6);
        System.out.println("Missatge xifrat: " + missatgeXifrat);

        forcaBrutaRotX(missatgeXifrat);
    }

        public static String xifraRotX(String cadena, int desplaçament) {
            
            String resultat = "";

            for (int i = 0; i < cadena.length(); i++) {

                char lletra = cadena.charAt(i);

                for (int j = 0; j < majuscules.length; j++) {
                    if (majuscules[j] == lletra) {
                        int novaPosicio = (j + desplaçament) % majuscules.length;
                        lletra = majuscules[novaPosicio];
                        break;
                    }
                }

                for (int j = 0; j < minuscules.length; j++) {
                    if (minuscules[j] == lletra) {
                        int novaPosicio = (j + desplaçament) % minuscules.length;
                        lletra = minuscules[novaPosicio];
                        break;
                    }
                }

                resultat += lletra;
            }

            return resultat;
    }


    public static String desxifraRotX(String cadena, int desplaçament) {

    String resultat = "";

    for (int i = 0; i < cadena.length(); i++) {

        char lletra = cadena.charAt(i);

        
        for (int j = 0; j < majuscules.length; j++) {

            if (majuscules[j] == lletra) {

                int novaPosicio =
                        (j - desplaçament + majuscules.length)
                        % majuscules.length;

                lletra = majuscules[novaPosicio];

                break;
            }
        }

        
        for (int j = 0; j < minuscules.length; j++) {

            if (minuscules[j] == lletra) {

                int novaPosicio = (j - desplaçament + minuscules.length) % minuscules.length;
                lletra = minuscules[novaPosicio];

                break;
            }
        }

        resultat += lletra;
    }

    return resultat;

    }

    public static void forcaBrutaRotX(String cadenaXifrada) {

        for (int desplaçament = 0; desplaçament < majuscules.length; desplaçament++) {

            String resultat = desxifraRotX(cadenaXifrada, desplaçament);
            System.out.printf("(%d)->%s%n", desplaçament, resultat);
        }
    }
} 
    