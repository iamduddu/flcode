package com.aem.delta.lopa.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.apache.sling.commons.json.xml.XML;

public class XMLToJsonConversion {
	static String line = "", str = "";

	public static void main(String[] args) throws JSONException, IOException {
		File file = new File("C:\\Users\\aayush.agrawal\\Downloads\\lopaConfig.xml");
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((line = br.readLine()) != null) {
			str += line;
		}
		JSONObject jsondata = XML.toJSONObject(str);
		System.out.println(jsondata);
	}
}
