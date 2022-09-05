package com.charlie.rest;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;

public class RestClientDemo {

	public static void main(String[] args) {
		Client client = ClientBuilder.newClient();
		// demo api
		String link = "https://script.google.com/macros/s/AKfycbyPthJ8iO6B1fPR1YBVvltBQz4dvAnrtE48sw0VxqMIW-XwejTsS4db3Pgw3oUWA4U/exec";
		Response response = client
				.target(link)
				.request(MediaType.APPLICATION_JSON)
				.get();
		
		// read response body
		String body = response.readEntity(String.class);
		
		System.out.println("response = " + response);
		System.out.println("response.getStatus() = " + response.getStatus());
		System.out.println("body = " + body);
		
		// junit check
		Assert.assertEquals(200, response.getStatus());
    }
}
