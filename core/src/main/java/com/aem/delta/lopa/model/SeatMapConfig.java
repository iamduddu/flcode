package com.aem.delta.lopa.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.google.gson.annotations.SerializedName;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "java")
public class SeatMapConfig {

	@SerializedName("property")
	private String aircraftModel;

	private String subfleetCode;

	private String lowerDeckBackground;

	private List<String> aircraftCodes;

	
	public String getAircraftModel() {
		return aircraftModel;
	}

	public void setAircraftModel(String aircraftModel) {
		this.aircraftModel = aircraftModel;
	}

	public String getSubfleetCode() {
		return subfleetCode;
	}

	public void setSubfleetCode(String subfleetCode) {
		this.subfleetCode = subfleetCode;
	}

	public String getLowerDeckBackground() {
		return lowerDeckBackground;
	}

	public void setLowerDeckBackground(String lowerDeckBackground) {
		this.lowerDeckBackground = lowerDeckBackground;
	}

	public List<String> getAircraftCodes() {
		return aircraftCodes;
	}

	public void setAircraftCodes(List<String> aircraftCodes) {
		this.aircraftCodes = aircraftCodes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aircraftCodes == null) ? 0 : aircraftCodes.hashCode());
		result = prime * result + ((aircraftModel == null) ? 0 : aircraftModel.hashCode());
		result = prime * result + ((lowerDeckBackground == null) ? 0 : lowerDeckBackground.hashCode());
		result = prime * result + ((subfleetCode == null) ? 0 : subfleetCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SeatMapConfig other = (SeatMapConfig) obj;
		if (aircraftCodes == null) {
			if (other.aircraftCodes != null)
				return false;
		} else if (!aircraftCodes.equals(other.aircraftCodes))
			return false;
		if (aircraftModel == null) {
			if (other.aircraftModel != null)
				return false;
		} else if (!aircraftModel.equals(other.aircraftModel))
			return false;
		if (lowerDeckBackground == null) {
			if (other.lowerDeckBackground != null)
				return false;
		} else if (!lowerDeckBackground.equals(other.lowerDeckBackground))
			return false;
		if (subfleetCode == null) {
			if (other.subfleetCode != null)
				return false;
		} else if (!subfleetCode.equals(other.subfleetCode))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SeatMapConfig [aircraftModel=" + aircraftModel + ", subfleetCode=" + subfleetCode
				+ ", lowerDeckBackground=" + lowerDeckBackground + ", aircraftCodes=" + aircraftCodes + "]";
	}

}
