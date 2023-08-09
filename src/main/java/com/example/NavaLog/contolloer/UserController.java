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

import com.example.NavaLog.model.Registration;
import com.example.NavaLog.model.RegistrationRepository;
import com.example.NavaLog.model.Shipment;
import com.example.NavaLog.model.ShipmentRepository;
import com.example.NavaLog.model.User;
import com.example.NavaLog.model.UserRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepo;

	@Autowired
	RegistrationRepository registerRepo;

	@Autowired
	ShipmentRepository shipmentRepo;

	/***********************************
	 * Get all the users
	 **********************************************************************************/
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllShipments(@RequestParam(required = false) String name) {
		try {
			List<User> users = new ArrayList<User>();

			if (name == null) {
				userRepo.findAll().forEach(users::add);
			} else {
				userRepo.findByName(name).forEach(users::add);
			}
			if (users.isEmpty()) {
				return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/******************************************
	 * Get users by auto generated id
	 ****************************************************************************/
	@GetMapping("/users/{id}")
	public ResponseEntity<User> getAUser(@PathVariable Long id) {

		try {
			Optional<User> user = userRepo.findById(id);
			if (user.isPresent()) {
				return new ResponseEntity<>(user.get(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/********************************************
	 * Get Users by user_id
	 **********************************************************************/

	@GetMapping("/users/userId/{userId}") 
	public ResponseEntity<User> getAUser(@PathVariable String userId) {
		
		try {
			Optional <User> user=userRepo.findByUserId(userId); // testing on postman  http://localhost:8080/api/courses/1  // used optional to avoid null object i.e null pointer exception
			if(user.isPresent())
			{
				return new ResponseEntity<>(user.get(), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e)
		{
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*******************************************
	 * Create a User
	 ***************************************************************************/
	@PostMapping("/users")

	public ResponseEntity<User> createUser(@RequestBody User user) {
		try {
			User newUser = new User(user.getUserId(), user.getName(), user.getEmail(), user.getPhone(), user.getPassword());
			userRepo.save(newUser);
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**********************************
	 * Updation by auto generated id
	 ********************************************************************/
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {

		try {
			Optional<User> userData = userRepo.findById(id);
			if (userData.isPresent()) {
				User upUser = userData.get();
				upUser.setName(user.getName());
				upUser.setEmail(user.getEmail());
				upUser.setPhone(user.getPhone());
				upUser.setPassword(user.getPassword());

				return new ResponseEntity<>(userRepo.save(upUser), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**********************************
	 * Updation by given user id
	 ******************************************************************/
//@PutMapping("/users/{userId}")
//public ResponseEntity<User> updateUserById(@PathVariable("userId") String userId,@RequestBody User user){
//	
//	try {
//		Optional<User> userData=userRepo.findByUserId(userId);
//		if(userData.isPresent())
//		{
//			User upUser=userData.get();
//			upUser.setName(user.getName());
//			upUser.setEmail(user.getEmail());
//			upUser.setPhone(user.getPhone());
//			
//			
//			return new ResponseEntity<>(userRepo.save(upUser),HttpStatus.OK);
//		}
//		else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	
//	}
//	catch(Exception e)
//	{
//		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}

	/**********************************
	 * Updation of data through email
	 **********************************************************************/
//@PutMapping("/users/{email}")
//public ResponseEntity<User> updateUserByEmail(@PathVariable("email") String email,@RequestBody User user){
//	
//	try {
//		Optional<User> userData=userRepo.findByEmail(email);
//		if(userData.isEmpty())
//		{
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//		else {
//			
//			User upUser=userData.get();
//			upUser.setName(user.getName());
//			upUser.setEmail(user.getEmail());
//			upUser.setPhone(user.getPhone());
//			return new ResponseEntity<>(userRepo.save(upUser),HttpStatus.OK);
//			}
//
//	}
//	catch(Exception e)
//	{
//		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}

	/******************************************
	 * Tried doing with this but not working when writing email
	 ***********************************************************************/
//@PutMapping("/users/{emailOrId}")
//public ResponseEntity<User> updateUserByEmail(@PathVariable("emailOrId") String emailOrId,@RequestBody User user){
//	
//	try {
//		Optional<User> userData=userRepo.findByIdOrEmail(Long.valueOf(emailOrId),emailOrId);
//		if(userData.isEmpty())
//		{
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//	    }
//		else {
//			
//			User upUser=userData.get();
//			upUser.setName(user.getName());
//			//upUser.setEmail(user.getEmail());
//			upUser.setPhone(user.getPhone());
//			return new ResponseEntity<>(userRepo.save(upUser),HttpStatus.OK);
//			}
//
//	}
//	catch(Exception e)
//	{
//		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//	}
//}

	/*********************************
	 * Delete Specific user with id
	 ***********************************************/
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			userRepo.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/*********************************
	 * Delete all users with id
	 ***********************************************/
	@DeleteMapping("/users")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			userRepo.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/****************************************************
	 * To get all shipments the user is registered for.
	 ****************************/
	@GetMapping("/users/{id}/shipments")
	public ResponseEntity<List<Shipment>> getRegisteredShipments(@PathVariable("id") long id) {
		Optional<User> userData = userRepo.findById(id);
		List<Shipment> shipments = new ArrayList<>();

		if (userData.isPresent()) {
			User user = userData.get();
			for (Registration reg : user.getRegistrations()) {
				shipments.add(reg.getShipment());
			}
			return new ResponseEntity<>(shipments, HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
