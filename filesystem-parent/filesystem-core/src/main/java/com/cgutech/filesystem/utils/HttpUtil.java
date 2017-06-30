package com.cgutech.filesystem.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;

/**
 * HTTP请求公共类
 * @author becan@cgutech.com
 * @date 2016年9月5日10:22:53
 */
@SuppressWarnings("deprecation")
public class HttpUtil {
	
	private static final Log log = LogFactory.getLog(HttpUtil.class);

	@SuppressWarnings("resource")
	public static String doPost(String url, String json){
		String message = null;
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		try {			
			StringEntity entity = new StringEntity(json, HTTP.UTF_8);
			entity.setContentType("application/json");
			httpPost.setEntity(entity);
			
			HttpResponse response = client.execute(httpPost);
			InputStream inputStream = response.getEntity().getContent();
			StringBuffer buffer = new StringBuffer();
			InputStreamReader inputReader = new InputStreamReader(inputStream,"UTF-8");
			BufferedReader bufferReader = new BufferedReader(inputReader);
			/*String str = new String("");*/
			int intC;
			while ((intC = bufferReader.read()) != -1) {
			char c = (char) intC;
			if (c == '\n') {
				break;
				}
				if (buffer.length() >=Integer.MAX_VALUE) {
				throw new Exception("input too long");
				}
				buffer.append(c);
				message = buffer.toString();
				}
			

			//bufferReader.close();
			if (bufferReader != null) {
				try {
					bufferReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (inputReader != null) {
				try {
					inputReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if ( inputStream  != null) {
				try {
					 inputStream .close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("do post 请求超时:"+url);
			return "error";
		}finally{
			httpPost.releaseConnection();
		}
		return message;
	}
	
	@SuppressWarnings("resource")
	public static String doGet(String url){
		String message = null;
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		
		try {			
			HttpResponse response = client.execute(httpGet);
			
			InputStream inputStream = response.getEntity().getContent();
			StringBuffer buffer = new StringBuffer();
			InputStreamReader inputReader = new InputStreamReader(inputStream,"UTF-8");
			BufferedReader bufferReader = new BufferedReader(inputReader);
			/*String str = new String("");*/
			/*while ((str = bufferReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferReader.close();
			message = buffer.toString();*/
			int intC;
			while ((intC = bufferReader.read()) != -1) {
			char c = (char) intC;
			if (c == '\n') {
				break;
				}
				if (buffer.length() >=Integer.MAX_VALUE) {
				throw new Exception("input too long");
				}
				buffer.append(c);
				message = buffer.toString();
				}
			//log.info(message);
			httpGet.releaseConnection();
			if (bufferReader != null) {
				try {
					bufferReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (inputReader != null) {
				try {
					inputReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if ( inputStream  != null) {
				try {
					 inputStream .close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("do get 请求超时:"+url);
			return "error";
		}finally{
			httpGet.releaseConnection();
		}
		return message;
	}
	
	@SuppressWarnings("resource")
	public static String doPut(String url, String json){
		String message = null;
		HttpClient client = new DefaultHttpClient();
		HttpPut httpPut = new HttpPut(url);
		try {			
			StringEntity entity = new StringEntity(json, HTTP.UTF_8);
			entity.setContentType("application/json");
			httpPut.setEntity(entity);
			
			HttpResponse response = client.execute(httpPut);
			InputStream inputStream = response.getEntity().getContent();
			StringBuffer buffer = new StringBuffer();
			InputStreamReader inputReader = new InputStreamReader(inputStream,"UTF-8");
			BufferedReader bufferReader = new BufferedReader(inputReader);
			int intC;
			while ((intC = bufferReader.read()) != -1) {
			char c = (char) intC;
			if (c == '\n') {
				break;
				}
				if (buffer.length() >=Integer.MAX_VALUE) {
				throw new Exception("input too long");
				}
				buffer.append(c);
				message = buffer.toString();
				}
			

			//bufferReader.close();
			if (bufferReader != null) {
				try {
					bufferReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (inputReader != null) {
				try {
					inputReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if ( inputStream  != null) {
				try {
					 inputStream .close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("do put 请求超时:"+url);
			return "error";
		}finally{
			httpPut.releaseConnection();
		}
		return message;
	}
	
	@SuppressWarnings("resource")
	public static String doDelete(String url){
		String message = null;
		HttpClient client = new DefaultHttpClient();	
		HttpDelete httpDelete = new HttpDelete(url);
		
		try {			
			HttpResponse response = client.execute(httpDelete);
			
			InputStream inputStream = response.getEntity().getContent();
			StringBuffer buffer = new StringBuffer();
			InputStreamReader inputReader = new InputStreamReader(inputStream,"UTF-8");
			BufferedReader bufferReader = new BufferedReader(inputReader);
			int intC;
			while ((intC = bufferReader.read()) != -1) {
			char c = (char) intC;
			if (c == '\n') {
				break;
				}
				if (buffer.length() >=Integer.MAX_VALUE) {
				throw new Exception("input too long");
				}
				buffer.append(c);
				message = buffer.toString();
				}
			//log.info(message);
			httpDelete.releaseConnection();
			if (bufferReader != null) {
				try {
					bufferReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (inputReader != null) {
				try {
					inputReader.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if ( inputStream  != null) {
				try {
					 inputStream .close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.info("do delete 请求超时:"+url);
			return "error";
		}finally{
			httpDelete.releaseConnection();
		}
		return message;
	}
}

