package com.heeexy.example.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Util {
	
	private static Logger logger = LoggerFactory.getLogger(MD5Util.class);
	
	public static String encode(byte[] bytes) {
		MessageDigest md5;
		try {
			md5 = MessageDigest.getInstance("MD5");
			return bytes2HexString(md5.digest(bytes));
		} catch (NoSuchAlgorithmException e) {
			StringWriter stringWriter = new StringWriter();
	        e.printStackTrace(new PrintWriter(stringWriter, true));
	        logger.error( stringWriter.toString());
		}
		return null;
	}
	
    public static String bytes2HexString(byte[] bytes) {
    	String base = "0123456789ABCDEF";
    	char[] baseChars = new char[base.length()];
    	base.getChars(0, base.length(), baseChars, 0);
    	char[] chars = new char[bytes.length*2];
    	int k = 0;
    	for (int i = 0; i < bytes.length; i++) {
			chars[k++] = baseChars[bytes[i] >>> 4 & 0xf];
			chars[k++] = baseChars[bytes[i]  & 0xf];
		}
    	return new String(chars);
    }
    
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(uuid);
		System.out.println(encode(("123456" + uuid).getBytes()));
		System.out.println(encode(("123456e95266279ea946cba66ee78cea6ffa70").getBytes()));
		System.out.println(encode(("12345661866c31e66d487eaaf792f296aefa99").getBytes()));
		System.out.println(encode(("12345648f84e8c75914628951d666a50e2b800").getBytes()));
		System.out.println(encode(("123456f94e6ee0d3714daebe3f44195fe6783d").getBytes()));
	}
	

}
