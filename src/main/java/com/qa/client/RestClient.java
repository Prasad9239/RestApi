package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//1. GET method without headers
	public void get(String url) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpclient=HttpClients.createDefault(); //Create client connection, Return one HTTP client
		HttpGet httpget=new HttpGet(url); //HTTP get request
		CloseableHttpResponse closeableHttpResponse=httpclient.execute(httpget); //hit the GET URL
		
		int status=closeableHttpResponse.getStatusLine().getStatusCode(); //get status code
		System.out.println("status code-->"+status);
		
		String response=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8"); //get GET response
		JSONObject responsejson=new JSONObject(response); //convert string into JSON object
		System.out.println("Response JSON--->"+responsejson);
		
		//Retrieve header section from HTTP response
		Header[] headerArray=closeableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders=new HashMap<String, String>();
		for(Header header:headerArray){
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("headers Array-->"+allHeaders);
	}
	
	//2. GET Method to return HTTP reponse and without headers
	public CloseableHttpResponse getResponse(String url) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpclient=HttpClients.createDefault(); //Create client connection, Return one HTTP client
		HttpGet httpget=new HttpGet(url); //HTTP get request
		CloseableHttpResponse closeableHttpResponse=httpclient.execute(httpget); //hit the GET URL
		return closeableHttpResponse;
	}
	
	//3. GET method with headers
    public CloseableHttpResponse getResponse(String url,HashMap<String, String> HeaderMap) throws ClientProtocolException, IOException{
		
		CloseableHttpClient httpclient=HttpClients.createDefault(); //Create client connection, Return one HTTP client
		HttpGet httpget=new HttpGet(url); //HTTP get request
		for(Map.Entry<String, String> entry:HeaderMap.entrySet()){
			httpget.addHeader(entry.getKey(),entry.getValue());
		}
		
		CloseableHttpResponse closeableHttpResponse=httpclient.execute(httpget); //hit the GET URL
		return closeableHttpResponse;
	}
    
    //4. POST method
    public CloseableHttpResponse Post(String url,String EntityString,HashMap<String, String> HeaderMap) throws ClientProtocolException, IOException{
    	CloseableHttpClient httpclient=HttpClients.createDefault();
    	HttpPost httppost=new HttpPost(url);
    	httppost.setEntity(new StringEntity(EntityString));
    	for(Map.Entry<String, String> entry:HeaderMap.entrySet()){
			httppost.addHeader(entry.getKey(),entry.getValue());
		}
    	
    	CloseableHttpResponse closeableHttpResponse=httpclient.execute(httppost);
    	return closeableHttpResponse;
    }
	
}
