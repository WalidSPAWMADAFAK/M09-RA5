import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

public class AES {

    public static final String ALGORITME_XIFRAT = "AES";
    public static final String ALGORITME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static byte[] xifraAES(String msg, String password) throws Exception {
        byte[] bytes = msg.getBytes("UTF-8");

        // IV local para evitar fallos de concurrencia
        byte[] iv = new byte[MIDA_IV];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);

        MessageDigest digest = MessageDigest.getInstance(ALGORITME_HASH);
        byte[] hash = digest.digest(password.getBytes("UTF-8"));

        SecretKeySpec clau = new SecretKeySpec(hash, ALGORITME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, clau, new IvParameterSpec(iv));

        byte[] missatgeXifrat = cipher.doFinal(bytes);

        byte[] resultat = new byte[iv.length + missatgeXifrat.length];

        System.arraycopy(iv, 0, resultat, 0, iv.length);
        System.arraycopy(missatgeXifrat, 0, resultat, iv.length, missatgeXifrat.length);

        return resultat;
    }

    public static String desxifraAES(byte[] bIvMsgXifrat, String password) throws Exception {
        byte[] iv = new byte[MIDA_IV];
        byte[] missatgeXifrat = new byte[bIvMsgXifrat.length - MIDA_IV];

        System.arraycopy(bIvMsgXifrat, 0, iv, 0, MIDA_IV);
        System.arraycopy(bIvMsgXifrat, MIDA_IV, missatgeXifrat, 0, missatgeXifrat.length);

        MessageDigest digest = MessageDigest.getInstance(ALGORITME_HASH);
        byte[] hash = digest.digest(password.getBytes("UTF-8"));

        SecretKeySpec clau = new SecretKeySpec(hash, ALGORITME_XIFRAT);

        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, clau, new IvParameterSpec(iv));

        byte[] bytesDesxifrats = cipher.doFinal(missatgeXifrat);

        return new String(bytesDesxifrats, "UTF-8");
    }

    public static void main(String[] args) {

        String[] msgs = {
            "Lorem ipsum dicet",
            "Hola Andrés cómo está tu cuñado",
            "Àgora Ílla Òtto"
        };

        for (int i = 0; i < msgs.length; i++) {

            String msg = msgs[i];
            byte[] bXifrats = null;
            String desxifrat = "";

            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}