package org.madiha.goeuro.dto;

/**
 * This class represents the JSON object showing city information, returned from
 * the GoEuro API
 * 
 * @author mmahmood
 * @see GeoPosition
 *
 */
public class Location {

	int _id;
	String name;
	String type;
	GeoPosition geo_position;

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public GeoPosition getGeo_position() {
		return geo_position;
	}

	public void setGeo_position(GeoPosition geo_position) {
		this.geo_position = geo_position;
	}

	@Override
	public String toString() {
		return "Location [_id=" + _id + ", name=" + name + ", type=" + type + ", geo_position=" + geo_position + "]";
	}

}
