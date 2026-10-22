package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {
    private final String alfabet = "aáàbcçdeéèfghiíïjklmnñoòópqrstuúüvwxyz";

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long llavor = converteixClau(clau);
        return new TextXifrat(transforma(msg, llavor, false).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long llavor = converteixClau(clau);
        return transforma(new String(xifrat.getBytes()), llavor, true);
    }

    private long converteixClau(String clau) throws ClauNoSuportada {
        try {
            return Long.parseLong(clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("La clau per xifrat Polialfabètic ha de ser un String convertible a long");
        }
    }

    private String transforma(String text, long llavor, boolean desxifrar) {
        StringBuilder resultat = new StringBuilder();
        Random random = new Random(llavor);

        for (int i = 0; i < text.length(); i += 2) {
            List<Character> permutacio = new ArrayList<>();

            for (char c : alfabet.toCharArray()) {
                permutacio.add(c);
            }

            Collections.shuffle(permutacio, random);

            StringBuilder destiBuilder = new StringBuilder();

            for (char c : permutacio) {
                destiBuilder.append(c);
            }

            String desti = destiBuilder.toString();
            int finalI = Math.min(i + 2, text.length());

            for (int j = i; j < finalI; j++) {
                char c = text.charAt(j);
                int pos = (desxifrar ? desti : alfabet).indexOf(c);

                if (pos >= 0) {
                    resultat.append((desxifrar ? alfabet : desti).charAt(pos));
                } else {
                    resultat.append(c);
                }
            }
        }

        return resultat.toString();
    }
}
