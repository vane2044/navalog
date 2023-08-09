package com.example.NavaLog.contolloer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.NavaLog.model.Shipment;
import com.example.NavaLog.model.ShipmentRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class ShipmentController {

	@Autowired
	ShipmentRepository shipmentRepo;

	@GetMapping("/shipments")
	public ResponseEntity<List<Shipment>> getAllShipments(@RequestParam(required = false) String name) {
		try {
			List<Shipment> shipments = new ArrayList<Shipment>();

			if (name == null) {
				shipmentRepo.findAll().forEach(shipments::add);
			} else {
				shipmentRepo.findByName(name).forEach(shipments::add);
			}
			if (shipments.isEmpty()) {
				// corrected to passed shipments
				return new ResponseEntity<>(shipments, HttpStatus.NO_CONTENT);
			} // corrected to pass shipments
			return new ResponseEntity<>(shipments, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/shipments")
	public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) { 
		try {
			Shipment _shipment = shipmentRepo.save(new Shipment(shipment.getName(), shipment.getDepartCountry(), shipment.getDepartPort(),
					shipment.getDestCountry(), shipment.getDestPort(), shipment.getDepartDate(), shipment.getArrivalDate(), shipment.getSpace(), shipment.getLink(),shipment.getType()));
			
			return new ResponseEntity<>(_shipment, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/shipments/{id}")
	public ResponseEntity<Shipment> updateShipment(@PathVariable("id") long id, @RequestBody Shipment shipment) {
		Optional<Shipment> shipmentsData = shipmentRepo.findById(id);
		if (shipmentsData.isPresent()) {
			Shipment _shipment = shipmentsData.get();
			_shipment.setName(shipment.getName());
			_shipment.setDepartCountry(shipment.getDepartCountry());
			_shipment.setDepartPort(shipment.getDepartPort());
			_shipment.setDestCountry(shipment.getDestCountry());
			_shipment.setDestPort(shipment.getDestPort());
			_shipment.setDepartDate(shipment.getDepartDate());
			_shipment.setArrivalDate(shipment.getArrivalDate());
			_shipment.setSpace(shipment.getSpace());
			_shipment.setLink(shipment.getLink());
			_shipment.setType(shipment.getType());
			
			
			return new ResponseEntity<>(shipmentRepo.save(_shipment), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/shipments/{id}")
	public ResponseEntity<HttpStatus> deleteShipment(@PathVariable("id") long id) {
		try {
			shipmentRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/shipments")
	public ResponseEntity<HttpStatus> deleteAllShipments() {
		try {
			shipmentRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
