package org.madiha.goeuro;

import java.util.List;

import org.json.JSONArray;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.madiha.goeuro.dto.Location;
import org.madiha.goeuro.service.LocationService;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(LocationService.class)
public class LocationServiceTest {

	/**
	 * @throws Exception
	 */
	@Test
	public void testGetLocationsFromJsonApi() {

		LocationService locationService = PowerMockito.spy(new LocationService());
		String jsonText = "[{'_id':376217,'key':null,'name':'Berlin','fullName':'Berlin, Germany','iata_airport_code':null,'type':'location','country':'Germany','geo_position':{'latitude':52.52437,'longitude':13.41053},'locationId':8384,'inEurope':true,'countryId':56,'countryCode':'DE','coreCountry':true,'distance':null,'names':{'pt':'Berlim','nl':'Berlijn'},'alternativeNames':{}}]";
		JSONArray jsonarray = new JSONArray(jsonText);
		List<Location> locations = null;

		try {
			PowerMockito.doReturn(jsonarray).when(locationService, "getJsonArrayFromURL", Mockito.anyString());
			locations = locationService.getLocationsFromJsonApi("test");
		} catch (Exception e) {

			Assert.fail();
		}

		Assert.assertEquals(1, locations.size());

	}

}
