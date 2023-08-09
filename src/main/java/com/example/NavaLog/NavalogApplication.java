package com.example.NavaLog;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.NavaLog.model.Registration;
import com.example.NavaLog.model.RegistrationRepository;
import com.example.NavaLog.model.Shipment;
import com.example.NavaLog.model.ShipmentRepository;
import com.example.NavaLog.model.User;
import com.example.NavaLog.model.UserRepository;



@SpringBootApplication
public class NavalogApplication {

	public static void main(String[] args) {
		SpringApplication.run(NavalogApplication.class, args);
	}
	@Bean
	ApplicationRunner init(ShipmentRepository shipmentRepo,UserRepository userRepo,RegistrationRepository registrationRepo) 
	{
		return args -> 
		{
			
			
			/****************************************Create User Repo********************************************************************************/
			ArrayList<User> users=new ArrayList<>();
			users.add(new User("Admin","admin","admin@gmail.com","9999543210","p999"));
			users.add(new User("E2345","Methanex","a@gmail.com","9876543210","p123"));
			users.add(new User("E3256","COSCO","p@gmail.com","8765432190","p456"));
			users.add(new User("E1234","Ocean Network Express","s@gmail.com","1234567890","p789"));
			users.add(new User("E4567","Evergreen Line","v@gmail.com","6543217890", "p987"));
	
			userRepo.saveAll(users);
			userRepo.findAll().forEach(System.out::println);
			
			/****************************************Create Shipment Repo********************************************************************************/
			ArrayList<Shipment> shipments = new ArrayList<>();
		    shipments.add(new Shipment("M5566","India","Port of Kandla" , "Canada", "Port of Vancouver","02/19/2003","02/19/2003", "105 m","www.lo.com","Cointainer"));
		    shipments.add(new Shipment("M111","India","Port of Kandla" , "Canada", "Port of Vancouver","02/19/2003","02/19/2003", "105 m ", "www.lo.com","Cointainer"));
		    shipments.add(new Shipment("M888","India","Port of Kandla" , "Canada", "Port of Vancouver","02/19/2003","02/19/2003", "105 m ", "www.lo.com","Cointainer"));


			shipmentRepo.saveAll(shipments);
			shipmentRepo.findAll().forEach(System.out::println);
			
			/****************************************Registering the one user to many Shipments********************************************************************************/
			ArrayList<Registration> register=new ArrayList<>();
    
			  //first user register to first shipment
			  register.add(new Registration(users.get(1),shipments.get(0)));
			  //first user register to second shipment
			  register.add(new Registration(users.get(1),shipments.get(1)));
			  //first user register to third shipment
			  register.add(new Registration(users.get(1),shipments.get(2)));
			
			  // 2nd user register to first shipment
			  register.add(new Registration(users.get(2),shipments.get(0)));
			  registrationRepo.saveAll(register);
			  registrationRepo.findAll().forEach(System.out::println);
			
	      };
	}
}
