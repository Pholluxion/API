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

import com.asdtest.api.dao.FixedAssetDAO;
import com.asdtest.api.entitys.FixedAsset;
import com.asdtest.exceptions.Message;

@RestController
@RequestMapping("api/fixed_assets")
public class FixedAssetREST {

	@Autowired
	private FixedAssetDAO fixedAssetDAO;

	@GetMapping
	public ResponseEntity<Object> getFixedAssets() {

		try {
			List<FixedAsset> assets = fixedAssetDAO.findAll();

			if (assets.size() != 0) {
				return new ResponseEntity<>(fixedAssetDAO.findAll(), HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(HttpStatus.NOT_FOUND.toString(),
						"no content found", "No fixed asset found in the database"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}

	}
	
	@RequestMapping(value = "example")
	public ResponseEntity<Object> getCityByName() {
		
		FixedAsset fixedAsset = new FixedAsset();
		fixedAsset.setName("Server 4");
		fixedAsset.setReceiver(null);
		
		return new ResponseEntity<>(fixedAsset, HttpStatus.OK);

	}

	@RequestMapping(value = "serial/{assetSerial}")
	public ResponseEntity<Object> getFixedAssetBySerial(@PathVariable("assetSerial") String assetSerial) {
		try {
			Optional<List<FixedAsset>> asset = fixedAssetDAO.findBySerial(assetSerial);

			if (asset.get().size() != 0) {
				return new ResponseEntity<>(asset.get(), HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(HttpStatus.NOT_FOUND.toString(),
						"no content found", "No fixed asset found in the database"));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}
	}

	@RequestMapping(value = "type/{assetType}")
	public ResponseEntity<Object> getFixedAssetByType(@PathVariable("assetType") String assetType) {

		try {

			Optional<List<FixedAsset>> asset = fixedAssetDAO.findByType(assetType);

			if (asset.get().size() != 0) {
				return new ResponseEntity<>(asset.get(), HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(HttpStatus.NOT_FOUND.toString(),
						"no content found", "No fixed asset found in the database"));
			}

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}
	}

	@RequestMapping(value = "date_of_purchase/{assetDateOfPurchase}")
	public ResponseEntity<Object> getFixedAssetByDateOfPurchase(
			@PathVariable("assetDateOfPurchase") String assetDateOfPurchase) {

		try {

			Optional<List<FixedAsset>> asset = fixedAssetDAO.findByDateOfPurchase(assetDateOfPurchase);

			if (asset.get().size() != 0) {
				return new ResponseEntity<>(asset.get(), HttpStatus.OK);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(HttpStatus.NOT_FOUND.toString(),
						"no content found", "No fixed asset found in the database"));
			}

		} catch (Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}

	}

	@PostMapping
	public ResponseEntity<Object> createAsset(@RequestBody FixedAsset fiAsset) {

		try {

			FixedAsset fixedAsset = fixedAssetDAO.save(fiAsset);
			return ResponseEntity.ok(new Message(HttpStatus.OK.toString(), "a new fixed asset has been created",
					"The fixed asset " + fixedAsset.getName() + " has been created successfully"));

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}
	}

	@PutMapping
	public ResponseEntity<Object> updateAsset(@RequestBody FixedAsset fiAsset) {

		try {

			Optional<FixedAsset> asset = fixedAssetDAO.findById(fiAsset.getInventoryNumber());

			if (asset.isPresent()) {

				FixedAsset fixedAsset = asset.get();

				fixedAsset.setDateOfPurchase(fiAsset.getDateOfPurchase());
				fixedAsset.setDescription(fiAsset.getDescription());
				fixedAsset.setHeight(fiAsset.getHeight());
				fixedAsset.setLength(fiAsset.getLength());
				fixedAsset.setName(fiAsset.getName());
				fixedAsset.setPurchaseValue(fiAsset.getPurchaseValue());
				fixedAsset.setSerial(fiAsset.getSerial());
				fixedAsset.setType(fiAsset.getType());
				fixedAsset.setWeight(fiAsset.getWeight());
				fixedAsset.setWidth(fiAsset.getWidth());
				fixedAsset.setReceiver(fiAsset.getReceiver());

				try {

					fixedAssetDAO.save(fixedAsset);
					return ResponseEntity.ok(new Message(HttpStatus.OK.toString(), "a fixed asset has been updated",
							"The fixed asset " + fixedAsset.getName() + " has been updated successfully"));
				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
							new Message(HttpStatus.BAD_REQUEST.toString(), "Cannot update identity", e.getMessage()));
				}

			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(HttpStatus.NOT_FOUND.toString(),
						"no content found", "No fixed asset found in the database"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}

	}

	@DeleteMapping(value = "{assetInventoryNumber}")
	public ResponseEntity<Object> deleteAsset(@PathVariable("assetInventoryNumber") long assetInventoryNumber) {

		try {
			if (fixedAssetDAO.findById(assetInventoryNumber).isPresent()) {
				try {

					fixedAssetDAO.deleteById(assetInventoryNumber);
					return ResponseEntity.ok(new Message(HttpStatus.OK.toString(), "a fixed asset has been deleted",
							"The fixed asset " + assetInventoryNumber + " has been deleted successfully"));

				} catch (Exception e) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body(new Message(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
									"Errors in the backend layer", e.getMessage()));
				}

			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(HttpStatus.NOT_FOUND.toString(),
						"no content found", "No fixed asset found in the database"));
			}

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(
					HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Errors in the backend layer", e.getMessage()));
		}

	}

}
