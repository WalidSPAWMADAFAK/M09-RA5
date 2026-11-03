package iticbcn.xifratge;

import java.security.KeyPair;
import java.util.HexFormat;

public class Main {

    public static void main(String[] args) throws Exception {

        ClauPublica cp = new ClauPublica();

        String msg = "Missatge de prova per xifrar àéíóú àéíóú àéíóú";

        KeyPair parellClaus = cp.generaParellClausRSA();

        byte[] msgXifrat = cp.xifrarRSA(msg, parellClaus.getPublic());

        System.out.println("================================");
        System.out.print("Text xifrat: ");
        System.out.println(HexFormat.of().withUpperCase().formatHex(msgXifrat));

        String msgDesxifrat = cp.desxifrarRSA(msgXifrat, parellClaus.getPrivate());

        System.out.println("================================");
        System.out.println("Text desxifrat: " + msgDesxifrat);

        String strClauPub = HexFormat.of().withUpperCase().formatHex(
                parellClaus.getPublic().getEncoded());

        String strClauPriv = HexFormat.of().withUpperCase().formatHex(
                parellClaus.getPrivate().getEncoded());

        System.out.println("================================");
        System.out.println("Clau pública: " + strClauPub);
        System.out.println("================================");
        System.out.println("Clau privada: " + strClauPriv);
    }
}