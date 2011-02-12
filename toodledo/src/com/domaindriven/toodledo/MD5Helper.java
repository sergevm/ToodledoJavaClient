package com.domaindriven.toodledo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class MD5Helper {
	public static String calculate(final String source) throws NoSuchAlgorithmException {
		// Algorithm taken from
		// http://www.stratos.me/2008/05/java-string-calculate-md5/
		String res = "";

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(source.getBytes());
		byte md5[] = md.digest();

		String tmp = "";
		for (int i = 0; i < md5.length; i++) {
			tmp = (Integer.toHexString(0xFF & md5[i]));
			if (tmp.length() == 1) {
				res += "0" + tmp;
			} else {
				res += tmp;
			}
		}

		return res;
	}
}
