package todo.tests;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

public class PublicKeyTest {
	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
		KeyPairGenerator pairGen = KeyPairGenerator.getInstance("RSA");
		// 512bit로 초기화
		pairGen.initialize(512);

		// key pair 생성
		KeyPair pair = pairGen.generateKeyPair();
		PrivateKey privateKey = pair.getPrivate();
		PublicKey publicKey = pair.getPublic();
		System.out.println("privateKey: " + new String(Base64.encodeBase64(privateKey.getEncoded())));
		System.out.println("publicKey: " + new String(Base64.encodeBase64(publicKey.getEncoded())));

		Cipher cipher = Cipher.getInstance("RSA");
		
		// public key로 암호화
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] data = "this is sample data".getBytes("utf8");
		byte[] cipherText = cipher.doFinal(data);
		System.out.println("cipher text length: " + cipherText.length);
		System.out.println("cipher text: " + new String(Base64.encodeBase64(cipherText)));

		// private key로 복호화
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] normalText = cipher.doFinal(cipherText);
		System.out.println("normal text length: " + normalText.length);
		System.out.println("normal text: " + new String(normalText));
	}
}
