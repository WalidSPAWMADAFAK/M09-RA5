
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.HexFormat;

public class Hashes {

    public int npass = 0;

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        byte[] bytes = md.digest(pw.getBytes(StandardCharsets.UTF_8));
        return HexFormat.of().formatHex(bytes);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(
                pw.toCharArray(),
                salt.getBytes(StandardCharsets.UTF_8),
                10000,
                128
        );

        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] bytes = skf.generateSecret(spec).getEncoded();
        spec.clearPassword();

        return HexFormat.of().formatHex(bytes);
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        String chars = "abcdefABCDEF1234567890!";
        npass = 0;

        for (int longitud = 1; longitud <= 6; longitud++) {
            String resultat = provar("", longitud, chars, alg, hash, salt);

            if (resultat != null) {
                return resultat;
            }
        }

        return null;
    }

    private String provar(String actual, int longitud, String chars,
                          String alg, String hash, String salt) throws Exception {

        if (actual.length() == longitud) {
            npass++;

            String resultat;

            if (alg.equals("SHA-512")) {
                resultat = getSHA512AmbSalt(actual, salt);
            } else {
                resultat = getPBKDF2AmbSalt(actual, salt);
            }

            if (resultat.equalsIgnoreCase(hash)) {
                return actual;
            }

            return null;
        }

        for (int i = 0; i < chars.length(); i++) {
            String resultat = provar(
                    actual + chars.charAt(i),
                    longitud,
                    chars,
                    alg,
                    hash,
                    salt
            );

            if (resultat != null) {
                return resultat;
            }
        }

        return null;
    }

    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;
        long millis = diff % 1000;
        long seconds = (diff / 1000) % 60;
        long minutes = (diff / (1000 * 60)) % 60;
        long hours = (diff / (1000 * 60 * 60)) % 24;
        long days = diff / (1000 * 60 * 60 * 24);

        return String.format("%d dies/%d hores / %d minuts / %d segons / %d millis", 
                days, hours, minutes, seconds, millis);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruanslkdfjz";
        String pw = "aaabFf";

        Hashes h = new Hashes();

        String[] aHashes = {
                h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt)
        };

        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("----------------------------------------\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("----------------------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass: %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps: %s\n", h.getInterval(t1, t2));
            System.out.printf("\n\n");
        }
    }
}