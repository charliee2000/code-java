package com.charlie.rest;

import java.io.IOException;

import org.junit.Assert;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RestClientOkHttpDemo {
	
	public static void main(String[] args){
		try {
			// init client
			OkHttpClient client = new OkHttpClient();
			// Demo API
			// Using google app script simple api for demo
			String link = "https://script.google.com/macros/s/AKfycbyPthJ8iO6B1fPR1YBVvltBQz4dvAnrtE48sw0VxqMIW-XwejTsS4db3Pgw3oUWA4U/exec";
			Request request = new Request
				.Builder()
				.header("Content-Type", "application/json")
				.url(link).build();
			Response response = client.newCall(request).execute();
			System.out.println("response = " + response);
			System.out.println("response.code() = " + response.code());
			String json = response.body().string();
			System.out.println("response.body().string() = " + json);
			
			// gson
			JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
			System.out.println(jsonObject);
			
			Assert.assertEquals(200, response.code());
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// junit check
	}
}
