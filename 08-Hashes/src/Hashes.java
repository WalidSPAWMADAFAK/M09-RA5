
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

        for (int i = 0; i < chars.length(); i++) {
            String p1 = String.valueOf(chars.charAt(i));
            String r = provar(p1, alg, hash, salt);
            if (r != null) return r;

            for (int j = 0; j < chars.length(); j++) {
                String p2 = p1 + chars.charAt(j);
                r = provar(p2, alg, hash, salt);
                if (r != null) return r;

                for (int k = 0; k < chars.length(); k++) {
                    String p3 = p2 + chars.charAt(k);
                    r = provar(p3, alg, hash, salt);
                    if (r != null) return r;

                    for (int l = 0; l < chars.length(); l++) {
                        String p4 = p3 + chars.charAt(l);
                        r = provar(p4, alg, hash, salt);
                        if (r != null) return r;

                        for (int m = 0; m < chars.length(); m++) {
                            String p5 = p4 + chars.charAt(m);
                            r = provar(p5, alg, hash, salt);
                            if (r != null) return r;

                            for (int n = 0; n < chars.length(); n++) {
                                String p6 = p5 + chars.charAt(n);
                                r = provar(p6, alg, hash, salt);
                                if (r != null) return r;
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    private String provar(String pw, String alg, String hash, String salt) throws Exception {
        npass++;

        String resultat;

        if (alg.equals("SHA-512")) {
            resultat = getSHA512AmbSalt(pw, salt);
        } else {
            resultat = getPBKDF2AmbSalt(pw, salt);
        }

        if (resultat.equalsIgnoreCase(hash)) {
            return pw;
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
            System.out.printf("----------------------------------------%n");
            System.out.printf("Algorisme: %s%n", algorismes[i]);
            System.out.printf("Hash: %s%n", aHashes[i]);
            System.out.printf("----------------------------------------%n");
            System.out.printf("-- Inici de força bruta ---%n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass: %s%n", pwTrobat);
            System.out.printf("Provats: %d%n", h.npass);
            System.out.printf("Temps: %s%n", h.getInterval(t1, t2));
            System.out.printf("%n%n");
        }
    }
}
