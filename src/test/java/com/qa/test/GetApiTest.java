package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBase{
	TestBase testBase;
	RestClient restClient;
	String URI;
	String url;
	String apiurl;
	CloseableHttpResponse closeableHttpResponse;
	
	@BeforeMethod
	public void setup(){
		testBase=new TestBase();
		url=props.getProperty("URL");
		apiurl=props.getProperty("serviceURL");
		URI=url+apiurl;
		restClient=new RestClient();
	}
	
	//without headers
	@Test
	public void GETWithoutHeadersTestMethod() throws ClientProtocolException, IOException{
		restClient.get(URI);
		closeableHttpResponse=restClient.getResponse(URI);
		
		//a. Status Code
		int statuscode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "status Code is not 200");
		
		//b. JSON String
		String response=EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
		JSONObject json=new JSONObject(response);
		
		//Single value Assertion
		//Response values : Per_Page
		String perPageValue=TestUtil.getValueByJPath(json,"/per_page");
		System.out.println("Value of per page is --->"+perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);
		
		//Response values : total
		String totalvalue=TestUtil.getValueByJPath(json,"/total");
		System.out.println("Value of total is --->"+totalvalue);
		Assert.assertEquals(Integer.parseInt(totalvalue), 12);
		
		//get the value from JSON Array
		String last_name=TestUtil.getValueByJPath(json, "/data[0]/last_name");
		String id=TestUtil.getValueByJPath(json, "/data[0]/id");
		String avatar=TestUtil.getValueByJPath(json, "/data[0]/avatar");
		String first_name=TestUtil.getValueByJPath(json, "/data[0]/first_name");
		String email=TestUtil.getValueByJPath(json, "/data[0]/email");
		
		System.out.println(id);
		System.out.println(first_name);
		System.out.println(last_name);
		System.out.println(avatar);		
		System.out.println(email);
		
		
	}
	
	//with headers
	@Test
	public void GETWithHeadersTestMethod() throws ClientProtocolException, IOException{
		HashMap<String, String> Headermap=new HashMap<String, String>();
		Headermap.put("Content-Type", "application/json"); // Default header content
		closeableHttpResponse=restClient.getResponse(URI,Headermap);
		int statuscode=closeableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("inside with headers method---->"+statuscode);
	}
	

}
