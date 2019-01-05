package edu.drexel.sakila.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class BankAccountServiceClient {
	private static Logger logger = Logger.getLogger(BankAccountServiceClient.class.getName());

	public static void main(String[] args) {
		
		getAll();
	}

	private static void getAll() {
		HttpURLConnection conn = null;
		try {
			logger.info("Setting url");
			URL url = new URL("http://localhost:8080/SakilaApp/api/bankaccounts/");
			
			logger.info("Connecting ...");
			conn = (HttpURLConnection) url.openConnection();
			
			logger.info("Setting headers ....");
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			logger.info("Checking reponse...");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			logger.info("Output from Server ....");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			logger.info("Output from server ....");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
	
			logger.info("Done");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.disconnect();
			}
			catch (Exception e) {
			}
		}
	}
	
	private static void post() { //work in progress 
		HttpURLConnection conn = null;
		try {
			logger.info("Setting url");
			URL url = new URL("http://localhost:8080/SakilaApp/api/bankaccounts/");
			
			logger.info("Connecting ...");
			conn = (HttpURLConnection) url.openConnection();
			
			logger.info("Setting headers ....");
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");

			logger.info("Checking reponse...");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			logger.info("Output from Server ....");
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			logger.info("Output from server ....");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
	
			logger.info("Done");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.disconnect();
			}
			catch (Exception e) {
			}
		}
	}


}
