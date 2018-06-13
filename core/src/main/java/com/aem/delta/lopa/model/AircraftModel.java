package com.aem.delta.lopa.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "void")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class AircraftModel {
	@XmlAttribute(name = "aircraftModel")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
