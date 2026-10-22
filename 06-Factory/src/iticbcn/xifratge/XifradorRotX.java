package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    private final String majuscules = "AÁÀBCÇDEÉÈFGHIÍÌÏJKLMNÑOÓÒPQRSTUÚÙÜVWXYZ";
    private final String minuscules = "aáàbcçdeéèfghiíìïjklmnñoóòpqrstuúùüvwxyz";

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int rotacio = validaClau(clau);
        return new TextXifrat(xifraRotX(msg, rotacio).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int rotacio = validaClau(clau);
        return desxifraRotX(new String(xifrat.getBytes()), rotacio);
    }

    private int validaClau(String clau) throws ClauNoSuportada {
        try {
            int rotacio = Integer.parseInt(clau);

            if (rotacio < 0 || rotacio > 40) {
                throw new NumberFormatException();
            }

            return rotacio;
        } catch (Exception e) {
            throw new ClauNoSuportada("Clau de RotX ha de ser un sencer de 0 a 40");
        }
    }

    private String xifraRotX(String text, int rotacio) {
        return transforma(text, rotacio);
    }

    private String desxifraRotX(String text, int rotacio) {
        return transforma(text, -rotacio);
    }

    private String transforma(String text, int rotacio) {
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int pos = majuscules.indexOf(c);

            if (pos >= 0) {
                resultat.append(majuscules.charAt(Math.floorMod(pos + rotacio, majuscules.length())));
            } else {
                pos = minuscules.indexOf(c);

                if (pos >= 0) {
                    resultat.append(minuscules.charAt(Math.floorMod(pos + rotacio, minuscules.length())));
                } else {
                    resultat.append(c);
                }
            }
        }

        return resultat.toString();
    }
}
