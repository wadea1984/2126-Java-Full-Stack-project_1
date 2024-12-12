package com.ticketing_reimbursement.net;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TicketingReimbursementApplication{
	public static void main(String[] args) {
		SpringApplication.run(TicketingReimbursementApplication.class, args);
	}
	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello nman!", name);
	}
}
//TicketingReimbursementApplication.class