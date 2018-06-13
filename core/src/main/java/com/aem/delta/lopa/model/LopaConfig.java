package com.aem.delta.lopa.model;

import java.util.List;

//@SlingModel
public class LopaConfig {

	private List<SeatMapConfig> seatMapConfigs;

	public List<SeatMapConfig> getSeatMapConfigs() {
		return seatMapConfigs;
	}

	public void setSeatMapConfigs(List<SeatMapConfig> seatMapConfigs) {
		this.seatMapConfigs = seatMapConfigs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seatMapConfigs == null) ? 0 : seatMapConfigs.hashCode());
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
		LopaConfig other = (LopaConfig) obj;
		if (seatMapConfigs == null) {
			if (other.seatMapConfigs != null)
				return false;
		} else if (!seatMapConfigs.equals(other.seatMapConfigs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LopaConfig [seatMapConfigs=" + seatMapConfigs + "]";
	}
	

}
