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

import com.asdtest.api.dao.ReceiverDAO;
import com.asdtest.api.entitys.City;
import com.asdtest.api.entitys.Receiver;
import com.asdtest.exceptions.Message;

@RestController
@RequestMapping("api/receivers")
public class ReceiverREST {

	@Autowired
	private ReceiverDAO receiverDAO;

	@GetMapping
	public ResponseEntity<Object> getReceivers() {

		try {

			List<Receiver> receivers = receiverDAO.findAll();

			if (receivers.size() != 0) {

				return new ResponseEntity<>(receivers, HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(HttpStatus.NOT_FOUND.toString(),
						"no content found", "No receiver found in the database"));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}

	}
	
	@RequestMapping(value = "example")
	public ResponseEntity<Object> getCityByName() {
		
		Receiver receiver = new Receiver();
		receiver.setName("Juan Torres");
		receiver.setCity(new City());
		
		return new ResponseEntity<>(receiver, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Object> createReceiver(@RequestBody Receiver receiver) {

		try {

			Receiver newReceiver = receiverDAO.save(receiver);
			return ResponseEntity.ok(new Message(HttpStatus.OK.toString(), "a new receiver has been created",
					"The receiver " + newReceiver.getName() + " has been created successfully"));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}
	}

	@PutMapping
	public ResponseEntity<Object> updateAsset(@RequestBody Receiver upReceiver) {

		try {

			Optional<Receiver> receiver = receiverDAO.findById(upReceiver.getId());

			if (receiver.isPresent()) {

				Receiver receiverDB = receiver.get();

				receiverDB.setAdress(upReceiver.getAdress());
				receiverDB.setCity(upReceiver.getCity());
				receiverDB.setName(upReceiver.getName());
				receiverDB.setType(upReceiver.getType());

				try {

					receiverDAO.save(receiverDB);
					return ResponseEntity.ok(new Message(HttpStatus.OK.toString(), "a receiver has been updated",
							"The receiver " + receiverDB.getName() + " has been updated successfully"));
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
							new Message(HttpStatus.BAD_REQUEST.toString(), "Cannot update identity", e.getMessage()));
				}

			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(HttpStatus.NOT_FOUND.toString(),
						"no content found", "No receiver found in the database"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}

	}

	@DeleteMapping(value = "{receiverId}")
	public ResponseEntity<Object> deleteAsset(@PathVariable("receiverId") long receiverId) {

		try {
			if (receiverDAO.findById(receiverId).isPresent()) {
				try {

					receiverDAO.deleteById(receiverId);
					return ResponseEntity.ok(new Message(HttpStatus.OK.toString(), "a receiver has been deleted",
							"The receiver " + receiverId + " has been deleted successfully"));

				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body(new Message(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
									"Errors in the backend layer", e.getMessage()));
				}

			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(HttpStatus.NOT_FOUND.toString(),
						"no content found", "No receiver found in the database"));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}
	}

}
