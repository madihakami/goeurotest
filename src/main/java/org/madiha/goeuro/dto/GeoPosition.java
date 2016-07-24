package org.madiha.goeuro.dto;

/**
 * This class represents the sub JSON object representing the location of the
 * City returned from the GoEuro API
 *
 * @author mmahmood
 * @see Location
 *
 */
public class GeoPosition {

	Double latitude;
	Double longitude;

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "GeoPosition [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
}
