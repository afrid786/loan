/**
 * 
 */
package com.afrid.loan.controller;

import com.afrid.loan.config.LoansConfig;
import com.afrid.loan.model.Customer;
import com.afrid.loan.model.Loans;
import com.afrid.loan.model.Properties;
import com.afrid.loan.repository.LoansRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class LoansController {

	@Autowired
	private LoansRepository loansRepository;

	@Autowired
	private LoansConfig loansConfig;

	@PostMapping("/loans")
	public List<Loans> getLoansDetails(@RequestBody Customer customer) {
		List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
		return loans;

	}

	@GetMapping("/application/properties")
	public String getProperties() {
		String propertiesString = "";
		try {
			ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();

			Properties properties = new Properties(loansConfig.getMsg(), loansConfig.getBuildVersion(),
					loansConfig.getMailDetails(), loansConfig.getActiveBranches());

			propertiesString = objectWriter.writeValueAsString(properties);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return propertiesString;
	}
}
