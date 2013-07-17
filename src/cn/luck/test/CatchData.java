package cn.luck.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import net.htmlparser.jericho.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class CatchData {

	public void getDate() throws HttpException, IOException {

		HttpClient httpClient = new DefaultHttpClient();// 核心应用类
		HttpUriRequest request = new HttpGet("http://baidu.lecai.com/lottery/draw/list/50?ds=2013-01-01&de=2013-05-20");// HTTP请求
		System.out.println(request.getRequestLine());// 打印请求信息
		HttpEntity entity = null;
		try {
			// 发送请求，返回响应
			HttpResponse response = httpClient.execute(request);

			// 打印响应信息
			System.out.println(response.getStatusLine());
			System.out.println("----------------------------------------");
			String responseString = null;
			entity = response.getEntity();
			if (entity != null) {
				//responseString = EntityUtils.toString(entity);
				//System.out.println(responseString); // 打印出服务器响应的HTML代码
				
				// 取出服务器返回的数据流
				InputStream stream = entity.getContent();
/*				BufferedReader in = new BufferedReader(new InputStreamReader(stream,"utf-8"));
				String str=null;
				while((str=in.readLine())!=null){
					
					if(str.trim().startsWith("<td class=\"td1\">2013")){
						System.out.println(str);
					}
				}*/
				

			}
		} catch (ClientProtocolException e) {
			// 协议错误
			e.printStackTrace();
		} catch (IOException e) {
			// 网络异常
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (entity != null)
				entity.consumeContent();
		}
	}

	public static void main(String[] args) {
		CatchData c = new CatchData();
		try {
			c.getDate();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
