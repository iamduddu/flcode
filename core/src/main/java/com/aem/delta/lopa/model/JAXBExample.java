package com.aem.delta.lopa.model;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class JAXBExample {
	public static void main(String[] args) {

		File file = new File("C:\\Users\\aayush.agrawal\\Downloads\\lopaConfig.xml");
		/*try {

			File file = new File("C:\\Users\\aayush.agrawal\\Downloads\\lopaConfig.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(SeatMapConfig.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			SeatMapConfig seatMapConfig = (SeatMapConfig) jaxbUnmarshaller.unmarshal(file);
			System.out.println(seatMapConfig);

		} catch (JAXBException e) {
			e.printStackTrace();
		}*/
		
		SeatMapConfig seatMapConfig=new SeatMapConfig();
		
		try {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(file);
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		XPathExpression expr = xpath.compile("/java/object/void");
		NodeList nodeList = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
	        Node currentNode = nodeList.item(i);
	        if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
	            //calls this method for all the children which is Element
	        	NodeList propertyNodeList=currentNode.getChildNodes();
	        	
	        	/*NodeList testNodeList=currentNode.getChildNodes();
	        	
	        	for (int j = 0; j < testNodeList.getLength(); j++) {
	        		System.out.println(testNodeList.item(j).getNodeName());
	        	}*/
	        	for (int j = 0; j < propertyNodeList.getLength(); j++) {
	                Node node = propertyNodeList.item(j);
	                
	                
	                NodeList finalPropertyList=node.getChildNodes();
	                for (int k = 0; k < finalPropertyList.getLength(); k++) {
	                	Node voidNode=finalPropertyList.item(k);
	                	if(!voidNode.getNodeName().equalsIgnoreCase("#text")) {
	                		NodeList stringNodes=voidNode.getChildNodes();
	                		for (int loop = 0; loop < stringNodes.getLength(); loop++) {
	                			Node stringNode=stringNodes.item(loop);
	                			if(!stringNode.getNodeName().equalsIgnoreCase("#text")) {
	                				System.out.println(stringNode.getTextContent());
	                				
	                			}
	                		
	                		}
	                		
	                	}
	                
	                }
	               
	            }
	          
	        }
	    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*Gson gson = new Gson();
		File file = new File("C:\\Users\\aayush.agrawal\\Downloads\\Abc.json");
        try  {

			// Convert JSON to Java Object
            SeatMapConfig staff = gson.fromJson(file, SeatMapConfig.class);
            System.out.println(staff);

			// Convert JSON to JsonElement, and later to String
            JsonElement json = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(json);
            System.out.println(jsonInString);

        } catch (IOException e) {
            e.printStackTrace();
        }

	*/}
}
