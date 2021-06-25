package com.kavki.fastfxrechargeapis.DTO;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Component
public class RSAEncryptorDecryptor {
   
    private static String privateKey = "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";
    private static String publicKey = "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAsIwVStQi6aSMLBZu3vhafOR5NTMNp+TXPwyk/6VoaSQfDnZaSQPYhdt4a8X215KwXwpIL1eBJOH2NW8jp5AO4WauHWEwEggJvPaC8FgzZtDhjYexOk+/yaDbY7U9BofJSU76VIBxRoN7YmAknAKrpfn0ukXPPuUx5Ny/cy85nunqo5M8Acf2VVwSGZQMBZFSm3yxYOdS4laDlM+s1w+5wLDMjYSgIMm76rpVdO3hs2n2dSAYM6XMOaqNDwHdZk6n8lPgivYVXjTz7KU9eqkFnecWvn2ugRI7hgrplZxS020k0QBeYd0AH7zJZKS3Xo5VycL01UO/WYOQvB7v8lge7TiQZ3CCrnuykqcJ/r5DMLO/cKQAeZi+LQ95FQg39joO8G7bfO7+a3Gs8Re3mRW7AA8x1aEn7XZMOUu4l4IfNvwh20V4cz3xvGXdr9ZLFvgX5593MxCDBjkiaynzG8gmLVTIoaItPy+khwO/vjfWka0L3yvT3l55R4H/KRKxlHaY58HVdLbuWrUoH/4gbkYFYFC+rejBW5wbE0FJmWIkEXLKsTlXcsn6eAzi4BRxidQ/4rIEf8qWpSFzJobivBnWe4bpBA19g3N47PDpD5xS6uj7ODSBhEn22UnsiDaGV+RhsXYA/xqaJCjB6+W7CN00Lowr87sUoT4VAK8wrOk4D5sCAwEAAQ==";
  
    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }catch(Exception e){
            //e.getMessage();
            System.out.println("Error /n"+e.getMessage());
        }
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return cipher.doFinal(data.getBytes());
    }

    public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }

    public static String decrypt(String data) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        String base64PrivateKey = privateKey;
        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
    }

    public static String encrypt(String toBeEncrypted) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        String encryptedString = Base64.getEncoder().encodeToString(encrypt(toBeEncrypted, publicKey));
        return encryptedString;
    }

    public String decrypt(ResponseEntity<String> response) {
        return null;
    }
    
    // public static void main(String[] argss) throws IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, BadPaddingException {
    //     try {
        
    //         String toBeEncrypted = "{"+"\"payerVa\""+" : " +"\"testo1@icici\""+","+"\"amount\"" +": "+"\"10.00\""+","+ "\"note\"" +":"+ "\"collect-pay-request\""+","+"\"collectByDate\"" +":"+"\"23/06/2021 12:20 PM\""+","+ "\"merchantId\"" +": "+"\"401579\""+","+ "\"merchantName\""+":"+"\"Testmerchant\""+","+"\"subMerchantId\"" +": "+"\"401579\""+","+ "\"subMerchantName\"" +": "+"\"Testmerchant\""+","+"\"terminalId\"" +": "+"\"5411\""+","+"\"merchantTranId\""+" : "+"\"FASTFX012345\""+","+"\"billNumber\"" +": "+"\"FFX012\""+"}";
    // //         String encryptedString = Base64.getEncoder().encodeToString(encrypt(toBeEncrypted, publicKey));
    //         String encryptedString = encrypt(toBeEncrypted);
    //         System.out.println("\nBefore encrypt: "+toBeEncrypted);
    //         System.out.println("\nencrypted test: "+encryptedString);
            
    // //         String toDecrypt = "lDcwJhhGrFpHGug9GsMQZ066tDjmttSpV7cOMkTy8n/1xUjK+abvjvPiZmth+eTl1fLCGxrmyWoM3XG4MPCI78csU7pgYA0ZcWgWUpFyADJ936rbUvUEiikfpesBRCdiCF+CFWIN6/BeycjB3JB98xd7RpxaPBZMUk7lvVe/oR6rG6bbwF1Ks2bzqtwvOwG5Kf0QuYDw3sVmau1hF5HbgOxRCTaQbsil++t5rrMCJFIP88+mp9eSlfi6I9laWDdPLOlMjIIoICEEwwCP1ei/aQACltDomMkjhIYRhvPFb5OLYRA70vXznBeRTPk3WhtVEFqojtC3MluvTxZOVK0V4GNiOth9gVwbZy5cQbINDL4Raq+OI1uiTHNNv4J7FNeC4Rra0Ur2/zduk40LTFFtLhHwDps73GDIjHgREqkIxXccnOvdHsNmGztHV9YynhiW18PMviZyXcRCjj2bKr153M1Wjuz/Q922f9mEvLm7bDzlCKOnim2FopdFj+8gst5mYBaS7xXAW1Ot0SSX/pDRHODEw9Ld0SpFszMDBAC1svPNcorKiN2SuIM4TzP92cJyX69IB6eT0aPNu/Nk8ysZL8aALfGS9rvc4nkrglmsbhkdSJXGLMuFDsLPry2R+zMsPSlidzqq9EkuW0WEFozjJ5lmWZyTVFl/t03BLjn4P7w=";
    // //         //System.out.println(encryptedString);
    // //         String decryptedString = RSAEncryptorDecryptor.decrypt(toDecrypt, privateKey);
    // //         System.out.println("\nResponse:\n"+ decryptedString);
    //     } catch (NoSuchAlgorithmException e) {
    //         System.err.println(e.getMessage());
    //     }

    // }
}


