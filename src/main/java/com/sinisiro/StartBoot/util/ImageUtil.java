package com.sinisiro.StartBoot.util;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

public class ImageUtil 
{
	public static void base64StringToJpeg(String img_b64Data,String filePath) throws Exception
	{
		String imageString = getImageString(img_b64Data);
		String imageType   = getImageType(img_b64Data);

		// create a buffered image
		BufferedImage image = null;
		byte[] imageByte = Base64Util.decodeToBytes(imageString);

		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		image = ImageIO.read(bis);
		bis.close();

		// write the image to a file
		File outputfile = new File(filePath);
		ImageIO.write(image, imageType, outputfile);
	}
	
	public static String getImageString(String img_b64Data) throws Exception
	{
		int index = img_b64Data.indexOf(",");
		
		if(index == -1)
			throw new Exception("getImageString Exception - imageString is not founded");
		
		String imageString = img_b64Data.substring(index);
		return imageString;
	}
	
	public static String getImageType(String img_b64Data) throws Exception
	{
		if(img_b64Data.length() == 0)
			throw new Exception("img_b64Data of length is 0");
		
		//data:image/png;
		String indexString = "data:image/";
		int firstIndex = img_b64Data.indexOf(indexString) + indexString.length();
		int endIndex   = img_b64Data.indexOf(";");

		System.out.println("firstIndex"+firstIndex);
		System.out.println("endIndex:"+endIndex);
		
		if(firstIndex == -1 || endIndex == -1)
			throw new Exception("getImageExtension Exception - imageType is not founded");
		
		String imageExtension = img_b64Data.substring(firstIndex,endIndex);
		return imageExtension;
	}
	
	public static String getImageExtension(String filePath) throws Exception
	{
		String imageExtension = filePath.substring(0,filePath.lastIndexOf("."));
		return imageExtension;
	}
}
