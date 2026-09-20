public class Rot13 {

    static char[] majuscules = {'A', 'Á', 'À', 'B', 'C', 'Ç', 'D', 'E', 'É', 'È',
        'F', 'G', 'H', 'I', 'Í', 'Ì', 'Ï', 'J', 'K', 'L',
        'M', 'N', 'Ñ', 'O', 'Ó', 'Ò', 'P', 'Q', 'R', 'S',
        'T', 'U', 'Ú', 'Ù', 'Ü', 'V', 'W', 'X', 'Y', 'Z'  };
    static char[] minuscules = { 'a', 'á', 'à', 'b', 'c', 'ç', 'd', 'e', 'é', 'è',
        'f', 'g', 'h', 'i', 'í', 'ì', 'ï', 'j', 'k', 'l',
        'm', 'n', 'ñ', 'o', 'ó', 'ò', 'p', 'q', 'r', 's',
        't', 'u', 'ú', 'ù', 'ü', 'v', 'w', 'x', 'y', 'z'};
    public static String xifraRot13(String cadena) {
    String resultat = "";
       for (int i = 0; i < cadena.length(); i++) {
        char caracter = cadena.charAt(i);
            boolean trobat = false;
            for (int j = 0; j < majuscules.length; j++) {

                if (caracter == majuscules[j]) {
                    int novaPosicio = (j + 13) % majuscules.length;
                   resultat += majuscules[novaPosicio];
                    trobat = true;
                    break;
            }
             }
         if (!trobat) {
                for (int j = 0; j < minuscules.length; j++) {
                    if (caracter == minuscules[j]) {
                        int novaPosicio = (j + 13) % minuscules.length;
                        resultat += minuscules[novaPosicio];
                trobat = true;
                       break;
                    }
                }
            }

            if (!trobat) {
                resultat += caracter;
            }
        }

    return resultat;
    }
    public static String desxifraRot13(String cadena) {
        String resultat = "";
    for (int i = 0; i < cadena.length(); i++) {

            char caracter = cadena.charAt(i);
            boolean trobat = false;

            for (int j = 0; j < majuscules.length; j++) {

                if (caracter == majuscules[j]) {
                 int novaPosicio = (j - 13 + majuscules.length) % majuscules.length;
                    resultat += majuscules[novaPosicio];
                trobat = true;
                    break;
                }
            }

            if (!trobat) {
       for (int j = 0; j < minuscules.length; j++) {
                    if (caracter == minuscules[j]) {
                        int novaPosicio = (j - 13 + minuscules.length) % minuscules.length;
                        resultat += minuscules[novaPosicio];
                       trobat = true;
                        break;
                    }
                }
            }

            if (!trobat) {
                resultat += caracter;
            }
        }

    return resultat;
    }

    public static void main(String[] args) {

   String[] msgs = {
            "ABC",
            "XYZ",
            "Hola, Mr. calçot",
            "Perdó, per tu què és?"
        };

        System.out.println("Xifrat");
        System.out.println("------");

        for (String msg : msgs) {
            System.out.println(msg + " => " + xifraRot13(msg));
        }

        System.out.println();
     System.out.println("Desxifrat");
        System.out.println("---------");

        for (String msg : msgs) {
          String xifrat = xifraRot13(msg);
            System.out.println(xifrat + " => " + desxifraRot13(xifrat));
        }
      }
}
