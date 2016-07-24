package org.madiha.goeuro;

import java.util.ArrayList;
import java.util.List;

import org.madiha.goeuro.dto.Location;
import org.madiha.goeuro.exception.CityNotFoundException;
import org.madiha.goeuro.service.LocationService;

class GoEuroTest {

	public static void main(String[] args) {

		LocationService service = new LocationService();
		List<Location> locationsList = new ArrayList<Location>();

		if (args.length == 1) {
			String city = args[0];

			try {
				locationsList = service
						.getLocationsFromJsonApi("http://api.goeuro.com/api/v2/position/suggest/en/" + city);
			} catch (CityNotFoundException e) {
				System.out.println(e.getMessage());
				return;
			}

			service.writeLocationsToCSVFile(locationsList);

		} else {
			System.out.println("Usage: java -jar GoEuroTest.jar \"CITY_NAME\"");
		}

	}

}
