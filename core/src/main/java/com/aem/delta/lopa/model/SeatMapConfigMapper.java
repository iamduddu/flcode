package com.aem.delta.lopa.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Component(immediate = true, metatype = false, label = "SeatMapConfigMapper")
@Service(value = { SeatMapConfigMapper.class })
public class SeatMapConfigMapper {

	public SeatMapConfig getSeatMapConfigObject() {
		SeatMapConfig seatMapConfig = new SeatMapConfig();
		try {

			File file = new File("C:\\Users\\aayush.agrawal\\Downloads\\lopaConfig.xml");

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
					// calls this method for all the children which is Element
					NodeList childNodeList = currentNode.getChildNodes();

					for (int j = 0; j < childNodeList.getLength(); j++) {
						Node node = childNodeList.item(j);

						NodeList voidNodeList = node.getChildNodes();
						for (int k = 0; k < voidNodeList.getLength(); k++) {
							Node voidNode = voidNodeList.item(k);

							if (!voidNode.getNodeName().equalsIgnoreCase("#text")) {
								NodeList stringNodes = voidNode.getChildNodes();
								Node property = voidNode.getAttributes().getNamedItem("property");
								String propertyValue = property.getNodeValue();

								for (int l = 0; l < stringNodes.getLength(); l++) {
									Node stringNode = stringNodes.item(l);
									if (!stringNode.getNodeName().equalsIgnoreCase("#text")) {
										String stringNodeValue = stringNode.getTextContent();
										switch (propertyValue) {
										case "aircraftModel":
											seatMapConfig.setAircraftModel(stringNodeValue);
											break;
										case "subfleetCode":
											seatMapConfig.setSubfleetCode(stringNodeValue);
											break;
										case "lowerDeckBackground":
											seatMapConfig.setLowerDeckBackground(stringNodeValue);
											break;
										case "aircraftCodes":
											List<String> aircraftCodes = new ArrayList<String>();
											NodeList aircraftCodesList = stringNode.getChildNodes();
											for (int m = 0; m < aircraftCodesList.getLength(); m++) {
												Node aircraftCodesVoidNode = aircraftCodesList.item(m);
												if (!aircraftCodesVoidNode.getNodeName().equalsIgnoreCase("#text")) {
													NodeList aircraftCodesStringNodes = aircraftCodesVoidNode
															.getChildNodes();
													for (int n = 0; n < aircraftCodesStringNodes.getLength(); n++) {
														Node aircraftCodesStringNode = aircraftCodesStringNodes.item(n);
														if (!aircraftCodesStringNode.getNodeName()
																.equalsIgnoreCase("#text")) {
															String aircraftCodesStringNodeValue = aircraftCodesStringNode
																	.getTextContent();
															aircraftCodes.add(aircraftCodesStringNodeValue);
														}
													}
												}
											}
											seatMapConfig.setAircraftCodes(aircraftCodes);
											break;
										}

									}

								}

							}

						}

					}
				}
				System.out.println(seatMapConfig);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return seatMapConfig;
	}

}
