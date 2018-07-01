package org.olxscrapper.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public enum Hash {
	MD5("MD5"), SHA1("SHA1"), SHA256("SHA-256"), SHA512("SHA-512");

	private String name;

	Hash(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String checksum(String stringToEncrypt) {
		try {
			MessageDigest md = MessageDigest.getInstance(getName());

			// Change this to UTF-16 if needed
			md.update(stringToEncrypt.getBytes(StandardCharsets.UTF_8));
			byte[] digest = md.digest();

			String hex = String.format("%064x", new BigInteger(1, digest));
			return hex;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
