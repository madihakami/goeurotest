package org.madiha.goeuro.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.madiha.goeuro.dto.Location;
import org.madiha.goeuro.exception.CityNotFoundException;

import com.google.gson.Gson;

/**
 *
 * This service implements the goeuro Api to retrieve locations and writing them
 * to CSV
 *
 * @author mmahmood
 *
 */
public class LocationService {

	private static final String CSV_FILE_HEADER = "_id,name,type,latitude,longitude";

	/**
	 * Takes goeuro URL as input with City param, reads the JSON result and
	 * converts to Locations
	 *
	 * @param url
	 * @return List of Location
	 * @throws CityNotFoundException
	 */
	public List<Location> getLocationsFromJsonApi(String url) throws CityNotFoundException {

		JSONArray jsonarray = getJsonArrayFromURL(url);

		if (jsonarray.length() == 0) {
			throw new CityNotFoundException("Sorry, the city was not found!");
		}

		List<Location> locationsList = new ArrayList<Location>();
		for (int i = 0; i < jsonarray.length(); i++) {

			Gson gson = new Gson();
			Location location = gson.fromJson(jsonarray.get(i).toString(), Location.class);
			locationsList.add(location);
		}

		return locationsList;
	}

	/**
	 * Takes goeuro URL as input with City param, reads and returns json array
	 *
	 * @param url
	 * @return JSONArray
	 */
	private JSONArray getJsonArrayFromURL(String url) {
		JSONArray jsonarray = null;
		InputStream is = null;
		try {
			is = new URL(url).openStream();
		} catch (MalformedURLException e1) {
			System.out.println("Input URL was not in correct format");
		} catch (IOException e1) {
			System.out.println("Error in connecting to API");
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

			String jsonText = readJsonText(reader);
			jsonarray = new JSONArray(jsonText);

		} catch (JSONException e) {
			System.out.println("json returned by Api was not in correct format");
		} catch (IOException e1) {
			System.out.println("Error in reading result from URL");
		}
		return jsonarray;
	}

	private String readJsonText(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	/**
	 * Writes location List TO CSV File
	 *
	 * @param locationsList
	 */
	public void writeLocationsToCSVFile(List<Location> locationsList) {

		File file = new File("locations.csv");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new File("locations.csv"));
		} catch (FileNotFoundException e) {
			System.out.println("Error in Writing to CSV !!!");
		}
		try {

			StringBuilder sb = new StringBuilder();
			sb.append(CSV_FILE_HEADER + '\n');

			for (Location location : locationsList) {
				sb.append(location.get_id());
				sb.append(",");
				sb.append(location.getName());
				sb.append(",");
				sb.append(location.getType());
				sb.append(",");
				sb.append(location.getGeo_position().getLatitude());
				sb.append(",");
				sb.append(location.getGeo_position().getLongitude());

				sb.append('\n');
			}

			pw.write(sb.toString());
			pw.close();
			System.out.println("CSV file was created successfully !!! Location of file:" + file.getAbsolutePath());

		} catch (Exception e) {
			System.out.println("Error in Writing CSV !!!");

		}

	}
}
