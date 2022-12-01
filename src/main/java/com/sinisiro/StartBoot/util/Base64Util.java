package com.sinisiro.StartBoot.util;

import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

public class Base64Util 
{
	public static byte [] encodeToBytes(String s) throws UnsupportedEncodingException
	{
		byte [] binaryData = s.getBytes("UTF-8");
		return Base64.encodeBase64(binaryData);
	}
	
	public static String encodeToString(String s) throws UnsupportedEncodingException
	{
		byte [] binaryData = s.getBytes("UTF-8");
		return Base64.encodeBase64String(binaryData);
	}
	
	public static byte [] decodeToBytes(String s) throws UnsupportedEncodingException
	{
		byte [] binaryData = s.getBytes("UTF-8");
		return Base64.decodeBase64(binaryData);
	}
	
	public static String decodeToString(String s) throws UnsupportedEncodingException
	{
		byte [] binaryData = Base64.decodeBase64(s);
		return new String(binaryData,"UTF-8");
	}
}
