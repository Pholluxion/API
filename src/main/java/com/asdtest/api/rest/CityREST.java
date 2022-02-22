package com.asdtest.api.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asdtest.api.dao.CityDAO;
import com.asdtest.api.entitys.City;
import com.asdtest.exceptions.Message;

@RestController
@RequestMapping("api/citys")
public class CityREST {

	@Autowired
	private CityDAO CityDAO;

	@GetMapping
	public ResponseEntity<Object> getCitys() {

		try {

			List<City> cities = CityDAO.findAll();

			if (cities.size() == 0) {

				return 	 ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(HttpStatus.NOT_FOUND.toString(), "no content found","No city found in the database"));
			}else {
				return new ResponseEntity<>(cities, HttpStatus.OK);
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}


	}

	@RequestMapping(value = "name/{nameCity}")
	public ResponseEntity<Object> getCityByName(@PathVariable("nameCity") String nameCity) {

		try {

			if (nameCity.isEmpty() || nameCity.isBlank()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payload null");
			} else {
				Optional<List<City>> city = CityDAO.findByName(nameCity);

				if (city.get().size() != 0) {
					return ResponseEntity.status(HttpStatus.OK).body(city.get());
				} else {
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(HttpStatus.NOT_FOUND.toString(),
							"City not found", "The city you are looking for was not found"));
				}
			}

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}

	}

	@PostMapping
	public ResponseEntity<Message> createCity(@RequestBody City city) {

		try {

			City newCity = CityDAO.save(city);
			return ResponseEntity.ok(new Message(HttpStatus.OK.toString(), "a new city has been created",
					"The city " + newCity.getName() + " has been created successfully"));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new Message(HttpStatus.BAD_REQUEST.toString(), "Cannot create a new city", e.getMessage()));
		}
	}

	@PutMapping
	public ResponseEntity<Message> updateAsset(@RequestBody City upCity) {

		try {

			Optional<City> city = CityDAO.getByName(upCity.getName());

			if (city.isPresent()) {

				City cityDB = city.get();

				cityDB.setName(upCity.getName());
				cityDB.setZipCode(upCity.getZipCode());

				try {

					CityDAO.save(cityDB);
					return ResponseEntity.ok(new Message(HttpStatus.OK.toString(), "a city has been updated",
							"The city " + cityDB.getName() + " has been updated successfully"));
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
							new Message(HttpStatus.BAD_REQUEST.toString(), "Cannot update identity", e.getMessage()));
				}

			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(HttpStatus.NOT_FOUND.toString(),
						"City not found", "The city you want to update could not be found"));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}
	}

	@DeleteMapping(value = "{cityId}")
	public ResponseEntity<Message> deleteAsset(@PathVariable("cityId") long cityId) {

		try {
			if (CityDAO.findById(cityId).isPresent()) {
				try {

					CityDAO.deleteById(cityId);
					return ResponseEntity.ok(new Message(HttpStatus.OK.toString(), "a city has been deleted",
							"The city " + cityId + " has been deleted successfully"));

				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body(new Message(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
									"Errors in the backend layer", e.getMessage()));
				}

			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(HttpStatus.NOT_FOUND.toString(),
						"City not found", "The city you want to delete could not be found"));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}
	}

}
