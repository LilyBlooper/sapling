package io.lloyd.sapling.util.http;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseHttpUtils {
	public static final BaseHttpUtils instance = new BaseHttpUtils();
	private static final Logger logger = LoggerFactory.getLogger(BaseHttpUtils.class);
	// 超时时间-毫秒
	private static final int MAX_TIMEOUT_MILLIS = 30000;
	// 请求配置
	private static RequestConfig requestConfig = null;
	// 连接池配置
	private PoolingHttpClientConnectionManager connMgr = null;

	private BaseHttpUtils() {
		// 连接池管理器
		connMgr = new PoolingHttpClientConnectionManager();
		connMgr.setMaxTotal(2048);
		connMgr.setDefaultMaxPerRoute(1024);
		// 设置请求参数配置
		RequestConfig.Builder configBuilder = RequestConfig.custom();
		configBuilder.setConnectTimeout(MAX_TIMEOUT_MILLIS);
		configBuilder.setSocketTimeout(MAX_TIMEOUT_MILLIS);
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT_MILLIS);
		// 在提交请求之前 测试连接是否可用
		// configBuilder.setStaleConnectionCheckEnabled(true);
		requestConfig = configBuilder.build();
	}

	public static BaseHttpUtils getInstance() {
		return instance;
	}

	/**
	 * 发送HTTP请求<code>json</code>方式
	 * 
	 * @param url
	 *            请求地址
	 * @param postBody
	 *            json格式
	 */
	public String doPost(String url, String postBody) {
		CloseableHttpResponse response = null;
		String content = "";
		try {
			HttpPost httpPost = genHttpPost(url, postBody);
			// 初始化一个http连接
			CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connMgr)
					.setDefaultRequestConfig(requestConfig).build();
			response = httpClient.execute(httpPost);
			if (response != null) {
				int status = response.getStatusLine().getStatusCode();
				content = entity2Content(response);
				// TODO
			}
		} catch (Exception e) {
			logger.error("MockHttpUtils doPost:" + e.getMessage());
		} finally {
			closeQuietly(response);
		}
		return content;
	}

	public String doGet(String url) {
		CloseableHttpResponse response = null;
		String content = "";
		try {
			HttpGet httpGet = new HttpGet(url);
			// 初始化一个http连接
			CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connMgr)
					.setDefaultRequestConfig(requestConfig).build();
			response = httpClient.execute(httpGet);
			if (response != null) {
				int status = response.getStatusLine().getStatusCode();
				content = entity2Content(response);
				// TODO
			}
		} catch (Exception e) {
			logger.error("MockHttpUtils doPost:" + e.getMessage());
		} finally {
			closeQuietly(response);
		}
		return content;
	}

	/**
	 * 发送HTTP请求<code>json</code>方式
	 * 
	 * @param url
	 *            请求地址
	 * @param postBody
	 *            json格式
	 */
	public String doPostSSL(String url, String postBody) {
		CloseableHttpResponse response = null;
		String content = "";
		try {
			HttpPost httpPost = genHttpPost(url, postBody);
			CloseableHttpClient httpsClient = HttpClients.custom()
					.setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr)
					.setDefaultRequestConfig(requestConfig).build();
			response = httpsClient.execute(httpPost);
			if (response != null) {
				int status = response.getStatusLine().getStatusCode();
				content = entity2Content(response);
				// TODO
			}
		} catch (Exception e) {
			logger.error("MockHttpUtils doPostSSL:" + e.getMessage());
		} finally {
			closeQuietly(response);
		}
		return content;
	}

	/**
	 * 构造一个HttpPost
	 * 
	 * @param url
	 *            url
	 * @param postBody
	 *            postbody
	 * @return httppost
	 */
	private HttpPost genHttpPost(String url, String postBody) {
		StringEntity se = new StringEntity(postBody, "UTF-8");
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(requestConfig);
		httpPost.setHeader("User-Agent", "Mozilla/5.0");
		httpPost.setEntity(se);
		return httpPost;
	}

	/**
	 * 创建SSL安全连接
	 * 
	 * @return SSL连接socket工厂
	 */
	private SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContext sslContext = new SSLContextBuilder()
					.loadTrustMaterial(null, new TrustStrategy() {
						public boolean isTrusted(X509Certificate[] chain, String authType)
								throws CertificateException {
							return true;
						}
					}).build();
			sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return sslsf;
	}

	/**
	 * 关闭HttpResponse
	 * 
	 * @param response
	 *            HttpResponse
	 */
	private void closeQuietly(CloseableHttpResponse response) {
		HttpClientUtils.closeQuietly(response);
	}

	/**
	 * 转换返回实体
	 * 
	 * @param response
	 *            返回实体
	 * @throws IOException
	 *             io异常
	 */
	private String entity2Content(CloseableHttpResponse response) throws IOException {
		return EntityUtils.toString(response.getEntity(), "utf-8");
	}

}