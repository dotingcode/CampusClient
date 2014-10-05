package com.campus.prime.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.campus.prime.R;
import com.campus.prime.core.utils.BCSUtils;

/**
 * 异步线程加载图片工具类
 * bindAvatar(ImageView imageView, int userId)
 * @author absurd
 *
 */
public class BitmapManager {
	private CommonLog log = LogFactory.createLog();
	/**
	 * BCS storage path for avatar
	 */
	public static final String AVATAR = "/avatar/";
	/**
	 * BCS storage path for media 
	 */
	public static final String MEDIA = "/media/";
	
	
	/**
	 * The shape of the imageView get
	 */
	public static final int ROUND = 1;
	public static final int SQUARE = 2;
	public static final int ROUNDCORNER = 3;
	
	//缓存图片的cache  cache是在内存中
	private static HashMap<String,SoftReference<Bitmap>> cache;
	//
	private static ExecutorService pool;
	
	private static Map<ImageView, String> imageViews;
	
	
	private static BitmapManager bitmapManager;
	
	private static Object classLock = BitmapManager.class;
	
	static{
		cache = new HashMap<String,SoftReference<Bitmap>>();
		pool = Executors.newFixedThreadPool(5);//线程池
		imageViews = new HashMap<ImageView,String>();
		
	}
	
	private final Bitmap defaultAvatar;
	private final  Bitmap defaultMedia;
	
	protected BitmapManager(Context context){
		defaultAvatar = ((BitmapDrawable)(context.getApplicationContext().getResources().getDrawable(R.drawable.avatar)))
				.getBitmap();
		defaultMedia = ((BitmapDrawable)(context.getApplicationContext().getResources().getDrawable(R.drawable.media)))
				.getBitmap();
	}
	
	public static BitmapManager getInstance(Context context){ 
		synchronized(classLock){
			if(bitmapManager == null){
				bitmapManager = new BitmapManager(context);
			}
			return bitmapManager;
		}
		
	}
	
		
	/**
	 * 在imageView中加载图片--可制定图片的高和宽
	 * @param url
	 * @param imageView
	 * @param defaultBmp
	 * @param width
	 * @param height
	 */
	/**
	public void loadBitmap(String filename, ImageView imageView, Bitmap defaultBmp, int width, int height){
		Bitmap rBmp;
		if(!StringUtils.isEmpty(filename)){
			imageViews.put(imageView, filename);
			Bitmap bitmap = getBitmapFromCache(filename);
			//如果cache中已经有该图片，直接加载该图片
			if(bitmap != null){
				rBmp = ImageUtils.roundBitmap(bitmap);
				imageView.setImageBitmap(rBmp);
			}else{
				//检查文件中是否缓存图片
				String generateName = FileUtils.getFileName(AVATAR + filename);
				log.i(generateName);
				String filepath = imageView.getContext().getFilesDir() + File.separator + generateName;
				
				File file = new File(filepath);
				if(file.exists()){
					Bitmap bmp = ImageUtils.getBitmap(imageView.getContext(),generateName);
					rBmp = ImageUtils.roundBitmap(bmp);
					imageView.setImageBitmap(rBmp);
				}else{
					// if not,get from net
					if(defaultBmp != null){
						rBmp = ImageUtils.roundBitmap(defaultBmp);
						imageView.setImageBitmap(rBmp);
					}
					queueJob(AVATAR +  filename,imageView,width,height);
				}
			}
		}else{
			rBmp = ImageUtils.roundBitmap(defaultBmp);
			imageView.setImageBitmap(rBmp);
		}
	}
	**/
	/**
	 * Load Bitmap from local storage
	 * @param filename
	 * @param imageView
	 * @param defaultBmp
	 * @param width
	 * @param height
	 * @return
	 */
	public Bitmap loadBitmap(String filename, ImageView imageView,Bitmap defaultBmp, int width, int height){
		Bitmap rBmp = null;
		if(!StringUtils.isEmpty(filename)){
			imageViews.put(imageView, filename);
			Bitmap bitmap = getBitmapFromCache(filename);
			//如果cache中已经有该图片，直接加载该图片
			if(bitmap != null){
				rBmp = bitmap;
			}else{
				//检查文件中是否缓存图片
				String generateName = FileUtils.getFileName(AVATAR + filename);
				// log.i(generateName);
				String filepath = imageView.getContext().getFilesDir() + File.separator + generateName;
				
				File file = new File(filepath);
				if(file.exists()){
					Bitmap bmp = ImageUtils.getBitmap(imageView.getContext(),generateName);
					rBmp = bmp;
				}
			}
		}
		
		return rBmp;
	}
	
	/**
	 * Load avatar on imageView
	 * @param avatarUrl
	 * @param imageView
	 * @param width
	 * @param height
	 * @param shape
	 */
	public void avatarLoader(String avatarUrl,ImageView imageView,int width,int height,int shape){
		Bitmap rBmp = loadBitmap(avatarUrl, imageView, defaultAvatar, width, height);
		if(rBmp == null){
			rBmp = cropBitmap(defaultAvatar, shape);
			imageView.setImageBitmap(rBmp);
			if(avatarUrl != null)
				queueJob(AVATAR + avatarUrl, imageView, width, height,shape);
		}
		else{	
			rBmp = cropBitmap(rBmp, shape);
			imageView.setImageBitmap(rBmp);
		}
		
	}
	
	/**
	 * Load avatar on imageView
	 * default shape is ROUND
	 * @param avatarUrl
	 * @param imageView
	 * @param width
	 * @param height
	 */
	public void avatarLoader(String avatarUrl,ImageView imageView,int width,int height){
		avatarLoader(avatarUrl, imageView, width, height, ROUND);
	}
	
	/**
	 * Crop the Bitmap
	 * @param src
	 * @param shape
	 * @return
	 */
	private Bitmap cropBitmap(Bitmap src,int shape){
		Bitmap result = src;
		if(shape == ROUND)
			result = ImageUtils.roundBitmap(src);
		else if(shape == ROUNDCORNER)
			result = ImageUtils.roundCorners(src, 2);
		return result;
	}
	
	
	/**
	 * Load media in message
	 * @param mediaUrl
	 * @param imageView
	 * @param defaultBmp
	 * @param width
	 * @param height
	 */
	public void mediaLoader(String mediaUrl,ImageView imageView,int width,int height){
		Bitmap rBmp = loadBitmap(mediaUrl, imageView, defaultMedia, width, height);
		if(rBmp == null )
			if(mediaUrl != null)
				queueJob(MEDIA + mediaUrl, imageView, width, height,SQUARE);
		else
			imageView.setImageBitmap(rBmp);
	}
	
	
	/**
	 * load photo from net
	 * @param url
	 * @param imageView
	 * @param width
	 * @param height
	 */
	private void queueJob(final String url,final ImageView imageView, final int width, final int height,final int shape){
		final Handler handler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				//String tag = imageViews.get(imageView);
				//if(tag != null && tag.equals(url)){
					if(msg.obj != null){
						Bitmap rBmp = cropBitmap((Bitmap)msg.obj, shape);
						imageView.setImageBitmap(rBmp);
						// 向SD卡写入缓存
						try{
							ImageUtils.saveImage(imageView.getContext(),FileUtils.getFileName(url),(Bitmap)msg.obj);
						}catch(IOException e){
							e.printStackTrace();
						}
						
					}
				}
			
		};
		
		//线程池  最多5个想成同时执行
		pool.execute(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Message message = new Message();
				message.obj = downloadBitmap(url,width,height);
				handler.sendMessage(message);
			}
		});
	}
	
	
	private Bitmap downloadBitmap(String urlPath,int width, int height){
		Bitmap bitmap = null;
		try{
			//http请求   获取图片
			/**
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(6 * 1000);
			int responseCode = conn.getResponseCode();
			if(responseCode == 200){
				InputStream inputStream = conn.getInputStream();
				byte[] data = (byte[]) readStream(inputStream);
				bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
				
			}
			**/
			InputStream inputStream = BCSUtils.getObjectContent(urlPath);
			byte[] data = (byte[]) readStream(inputStream);
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			if(width > 0 && height > 0){
				//制定显示图片的大小
				bitmap = Bitmap.createScaledBitmap(bitmap, width, height, true);
			}
			cache.put(urlPath,new SoftReference<Bitmap>(bitmap));
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return bitmap;
	}
	
	/**
	 * 读取从网络得到的IO流
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	private byte[] readStream(InputStream inputStream)throws Exception{
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while((len = inputStream.read(buffer)) != -1){
			outStream.write(buffer,0,len);
		}
		outStream.close();
		inputStream.close();
		
		return outStream.toByteArray();
		
	}
	
	
	/**
	 * 从cache中获取图片
	 * @param url
	 * @return
	 */
	private Bitmap getBitmapFromCache(String url){
		Bitmap bitmap = null;
		if(cache.containsKey(url)){
			bitmap = cache.get(url).get();
		}
		return bitmap;
	}
	
}