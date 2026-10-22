package iticbcn.xifratge;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {
    private final String formatAES = "AES/CBC/PKCS5Padding";
    private final int midaIV = 16;

    @Override
    public TextXifrat xifra(String msg, String clau) {
        try {
            byte[] iv = new byte[midaIV];
            new SecureRandom().nextBytes(iv);

            Cipher cipher = Cipher.getInstance(formatAES);
            cipher.init(Cipher.ENCRYPT_MODE, creaClau(clau), new IvParameterSpec(iv));

            byte[] xifrat = cipher.doFinal(msg.getBytes(StandardCharsets.UTF_8));
            ByteBuffer buffer = ByteBuffer.allocate(iv.length + xifrat.length);
            buffer.put(iv);
            buffer.put(xifrat);

            return new TextXifrat(buffer.array());
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) {
        try {
            byte[] bytes = xifrat.getBytes();
            byte[] iv = new byte[midaIV];
            byte[] dades = new byte[bytes.length - midaIV];

            System.arraycopy(bytes, 0, iv, 0, midaIV);
            System.arraycopy(bytes, midaIV, dades, 0, dades.length);

            Cipher cipher = Cipher.getInstance(formatAES);
            cipher.init(Cipher.DECRYPT_MODE, creaClau(clau), new IvParameterSpec(iv));

            return new String(cipher.doFinal(dades), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println(e.getLocalizedMessage());
            System.exit(1);
            return null;
        }
    }

    private SecretKeySpec creaClau(String clau) throws Exception {
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        byte[] clauBytes = sha.digest(clau.getBytes(StandardCharsets.UTF_8));
        byte[] clauAES = new byte[16];

        System.arraycopy(clauBytes, 0, clauAES, 0, clauAES.length);

        return new SecretKeySpec(clauAES, "AES");
    }
}
