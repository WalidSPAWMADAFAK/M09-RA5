package src;
public class RotX {

    static String majuscules = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    static String minuscules = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";

    public static String xifraRotX(String cadena, int desplacament) {

        String resultat = "";

        for (int i = 0; i < cadena.length(); i++) {

            char caracter = cadena.charAt(i);
            boolean trobat = false;

            for (int j = 0; j < majuscules.length(); j++) {

                if (caracter == majuscules.charAt(j)) {

                    int novaPosicio = (j + desplacament + majuscules.length())
                            % majuscules.length();

                    resultat += majuscules.charAt(novaPosicio);
                    trobat = true;
                    break;
                }
            }

            if (!trobat) {

                for (int j = 0; j < minuscules.length(); j++) {

                    if (caracter == minuscules.charAt(j)) {

                        int novaPosicio = (j + desplacament + minuscules.length())
                                % minuscules.length();

                        resultat += minuscules.charAt(novaPosicio);
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

    public static String desxifraRotX(String cadena, int desplacament) {
        return xifraRotX(cadena, -desplacament);
    }

    public static void forcaBrutaRotX(String cadenaXifrada) {

        for (int i = 0; i < minuscules.length(); i++) {
            System.out.println("(" + i + ")->"
                    + desxifraRotX(cadenaXifrada, i));
        }
    }

    public static void main(String[] args) {

        String[] missatges = {
            "ABC",
            "XYZ",
            "Hola, Mr. calçot",
            "Perdó, per tu què és?"
        };

        System.out.println("Xifrat");
        System.out.println("------");

        int desplacament = 0;

        for (String missatge : missatges) {

            String xifrat = xifraRotX(missatge, desplacament);

            System.out.println("(" + desplacament + ")-"
                    + missatge
                    + " => "
                    + xifrat);

            desplacament += 2;
        }

        System.out.println();

        System.out.println("Desxifrat");
        System.out.println("---------");

        desplacament = 0;

        for (String missatge : missatges) {

            String xifrat = xifraRotX(missatge, desplacament);

            String desxifrat = desxifraRotX(xifrat, desplacament);

            System.out.println("(" + desplacament + ")"
                    + xifrat
                    + " => "
                    + desxifrat);

            desplacament += 2;
        }

        System.out.println();

        System.out.println("Força bruta");
        System.out.println("-----------");

        String cadenaXifrada =
                xifraRotX("Perdó, per tu què és?", 6);

        forcaBrutaRotX(cadenaXifrada);
    }
}