package com.campus.prime.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.campus.prime.core.utils.BCSUtils;


import android.content.Context;
import android.os.Environment;


/**
 * �ļ��������߰�
 * @author absurd
 *
 */
public class FileUtils {
	
	
	/**
	 * д�ı��ļ�
	 * @param context
	 * @param fileName
	 * @param content
	 */
	public static void write(Context context, String fileName, String content){
		if(content == null){
			content = "";
		}
		try{
			FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.write(content.getBytes());
			fos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ�ı��ļ�
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static String read(Context context, String fileName){
		try{
			FileInputStream fis = context.openFileInput(fileName);
			return readInstream(fis);
		}catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	
	
	private static String readInstream(FileInputStream fis) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		String str = "";
		try{
			byte[] buffer = new byte[512];
			int length = -1;
			while((length = fis.read(buffer)) != -1){
				outStream.write(buffer,0,length);
			}
			str = outStream.toString();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				outStream.close();
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return str;
	}
	
	
	/**
	 * �����ļ�
	 * @param folderPath
	 * @param fileName
	 * @return
	 */
	public static File createFile(String folderPath, String fileName){
		File destDir = new File(folderPath);
		if(!destDir.exists()){
			destDir.mkdirs();
		}
		return new File(folderPath,fileName);
	}
	
	/**
	 * ���ֻ�дͼƬ
	 * @param buffer
	 * @param folder
	 * @param fileName
	 * @return
	 */
	public static boolean writeFile(byte[] buffer, String folder, String fileName){
		boolean writeSucc = false;
		
		boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
		
		String folderPath = "";
		if(sdCardExist){
			folderPath = Environment.getExternalStorageDirectory() + File.separator + folder + File.separator;
			
		}else{
			writeSucc = false;
		}
		
		File fileDir = new File(folderPath);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		
		File file = new File(folderPath + fileName);
		FileOutputStream out = null;
		try{
			out = new FileOutputStream(file);
			out.write(buffer);
			writeSucc = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				out.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		return writeSucc;
	}
	/**
	 * ����BCS�������ϴ洢���ļ�����ȡ���ػ����ļ���
	 * ʹ��BCS�ṩ�ļ����㷨����Ψһ���ļ���
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath){
		if(StringUtils.isEmpty(filePath)){
			return "";
		}
		String generate = BCSUtils.generateUrl(filePath);
		return generate.substring(generate.lastIndexOf('=') + 1);
	}
	
	/**
	 * ����ֻ��洢���Ƿ񻺴����ļ�
	 * @param context
	 * @param filename
	 * @return
	 */
	public static boolean isExist(Context context,String filename){
		File file = new File(context.getFilesDir() + File.separator + filename);
		return file.exists();
	}
	
	
	
}
