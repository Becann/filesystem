package com.cgutech.filesystem.dd.ding.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cgutech.filesystem.dd.ding.bean.DingAccessToken;
import com.cgutech.filesystem.dd.ding.bean.UserId;
import com.cgutech.filesystem.utils.JsonUtil;


public class DingUtils {
	private static Logger log = LoggerFactory.getLogger(DingUtils.class);

	// 获取access_token的接口地址（GET） 限200（次/天）   
	public final static String access_token_url = "https://oapi.dingtalk.com/gettoken?corpid=CORPID&corpsecret=CORPSECRET";  
	public final static String userId_url = "https://oapi.dingtalk.com/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE";
	/** 
	 * 获取access_token 
	 *  
	 * @param corpid 凭证 
	 * @param corpsecret 密钥 
	 * @return 
	 */  
	public static DingAccessToken getAccessToken(String corpid, String corpsecret) {  
	    DingAccessToken accessToken = null;  
	  
	    String requestUrl = access_token_url.replace("CORPID", corpid).replace("CORPSECRET", corpsecret);  
	    String response = httpRequest(requestUrl, "GET", null);  
	    // 如果请求成功   
	    if (!TextUtils.isEmpty(response)) {  
	    	accessToken = JsonUtil.getGson().fromJson(response, DingAccessToken.class);
	    }  
	    return accessToken;  
	} 

	/** 
	 * 获取userId 
	 *  
	 * @param access_token 凭证 
	 * @param code 密钥 
	 * @return 
	 */  
	public static UserId getUserId(String access_token, String code) {  
	    UserId userId = null;  
	    String requestUrl = userId_url.replace("ACCESS_TOKEN", access_token).replace("CODE", code);  
	    String response = httpRequest(requestUrl, "GET", null);  
	    // 如果请求成功   
	    if (!TextUtils.isEmpty(response)) {  
	    	userId = JsonUtil.getGson().fromJson(response, UserId.class);
	    }  
	    return userId;  
	} 
	
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static String httpRequest(String requestUrl, String requestMethod, String outputStr) {
		log.info("钉钉, url:" + requestUrl + ", method:" + requestMethod + ", outputStr:" + outputStr);
		String responseText = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new X509TrustManager() {
				
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				
				@Override
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
				
				@Override
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				}
			} };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.addRequestProperty("Content-Type", "application/json");
			if ("GET".equalsIgnoreCase(requestMethod)){
				httpUrlConn.connect();
			}
			else if("POST".equalsIgnoreCase(requestMethod)){
				httpUrlConn.setRequestMethod(requestMethod);
				httpUrlConn.connect();
			}
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			responseText = buffer.toString();
			log.info("API调用返回：" + responseText);
		} catch (ConnectException ce) {
			log.error("DINGDING server connection timed out.", ce);
		} catch (Exception e) {
			log.error("https request error:{}", e);
		}
		return responseText;
	}
}
