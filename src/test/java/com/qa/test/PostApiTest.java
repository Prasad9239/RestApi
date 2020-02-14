package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostApiTest extends TestBase {
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
	
	@Test
	public void PostTestMethod() throws ClientProtocolException, IOException{
		HashMap<String, String> Headermap=new HashMap<String, String>();
		Headermap.put("Content-Type", "application/json");
		
		//jackson API -Converting java object to Json and vice versa
		ObjectMapper mapper=new ObjectMapper();
		Users users=new Users("morpheus","leader");
		
		//Coverting object to Json -marshlling
		mapper.writeValue(new File("C:\\Users\\425627\\Documents\\workspace-sts-3.8.3.RELEASE\\RestApi\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		//Object to Json in String
		String UserJsonString=mapper.writeValueAsString(users);
		System.out.println(UserJsonString);
		
		closeableHttpResponse=restClient.Post(URI, UserJsonString, Headermap);
		System.out.println(closeableHttpResponse.getStatusLine().getStatusCode());
		
		//json String
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		JSONObject responseJson=new JSONObject(responseString);
		
		System.out.println(responseJson);
		
		//Json to Java Object -- unmarshlling
		Users userobj=mapper.readValue(responseString, Users.class);
		System.out.println(userobj);
		
		Assert.assertTrue(users.getUser().equals(userobj.getUser()));
		Assert.assertTrue(users.getJob().equals(userobj.getJob()));
		System.out.println(userobj.getId());
		System.out.println(userobj.getCreatedAt());
		
	}

}
