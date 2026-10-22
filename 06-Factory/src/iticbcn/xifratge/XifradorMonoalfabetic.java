package iticbcn.xifratge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorMonoalfabetic implements Xifrador {
    private final String alfabet = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÒÓPQRSTUÚÜVWXYZ";
    private String alfabetPermutat;

    public XifradorMonoalfabetic() {
        List<Character> lletres = new ArrayList<>();

        for (char c : alfabet.toCharArray()) {
            lletres.add(c);
        }

        Collections.shuffle(lletres, new Random());

        StringBuilder resultat = new StringBuilder();

        for (char c : lletres) {
            resultat.append(c);
        }

        alfabetPermutat = resultat.toString();
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        }

        return new TextXifrat(transforma(msg, alfabet, alfabetPermutat).getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau != null) {
            throw new ClauNoSuportada("Xifratge monoalfabètic no suporta clau != null");
        }

        return transforma(new String(xifrat.getBytes()), alfabetPermutat, alfabet);
    }

    private String transforma(String text, String origen, String desti) {
        StringBuilder resultat = new StringBuilder();

        for (char c : text.toCharArray()) {
            int pos = origen.indexOf(c);

            if (pos >= 0) {
                resultat.append(desti.charAt(pos));
            } else {
                resultat.append(c);
            }
        }

        return resultat.toString();
    }
}
