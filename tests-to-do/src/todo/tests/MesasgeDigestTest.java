package todo.tests;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Set;

import blueprint.sdk.util.StringUtil;

public class MesasgeDigestTest {
	public static void main(String[] args) {
		System.out.println("Supported MessageDigest Algorithms:");
		Set<String> algs = Security.getAlgorithms("MessageDigest");
		for (String alg : algs) {
			System.out.println("\t" + alg);
		}
		System.out.println();

		doDigest("MD2");
		doDigest("MD5");
		doDigest("SHA");
		doDigest("SHA-256");
		doDigest("SHA-384");
		doDigest("SHA-512");
	}

	private static void doDigest(String algorithm) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);

			String text = "1234567890";
			byte[] bytes = text.getBytes("UTF-8");
			md.update(bytes, 0, bytes.length);
			String digest = StringUtil.toHex(md.digest());

			System.out.println(algorithm + ": " + digest + " (" + digest.length() + "bytes)");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
