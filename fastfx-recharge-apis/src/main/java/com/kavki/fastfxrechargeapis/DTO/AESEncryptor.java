package com.kavki.fastfxrechargeapis.DTO;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncryptor {
    
    public static final String algorithm = "AES/ECB/PKCS5Padding";
    private static final Random RANDOM = new SecureRandom();
    private static SecretKey secretkey;
    private static String newSalt;

    public static String getSalt() {
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static SecretKey getKeyFromPassword(String password, String salt)
        throws NoSuchAlgorithmException, InvalidKeySpecException {
            
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        return secret;
    }

    public String[] encrypt(String password)
        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
        InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        
        newSalt = getSalt();
        secretkey = getKeyFromPassword(password,newSalt);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretkey);
        byte[] cipherText = cipher.doFinal(password.getBytes());
        String encryptPassword= Base64.getEncoder().encodeToString(cipherText);

        String [] cred = {encryptPassword, newSalt};
        return cred;

    }

    public String verify(String password, String salt)
        throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
        InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidKeySpecException {
        
        secretkey = getKeyFromPassword(password,salt);
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, secretkey);
        byte[] cipherText = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(cipherText);
    }

    
}
