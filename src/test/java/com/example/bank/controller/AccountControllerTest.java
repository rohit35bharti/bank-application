package com.example.bank.controller;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;


import com.example.bank.model.Account;
import com.example.bank.service.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@WebMvcTest({ AccountController.class })
public class AccountControllerTest {

	private static Logger LOG = LoggerFactory.getLogger(AccountControllerTest.class);

	@MockBean
	AccountService accountService;

	@Autowired
	MockMvc mockMvc;

	public static List<Account> accountsList;

	@BeforeAll
	public static void createAccountsData() {
		accountsList = new ArrayList<>();
		accountsList.add(new Account(101, "Sonu", 100.5, "savings"));
		accountsList.add(new Account(102, "Monu", 200.5, "current"));
		accountsList.add(new Account(103, "Tonu", 200.5, "savings"));
		LOG.info(accountsList.toString());
	}

	@Test
	public void testGetAccount1() throws Exception {
		LOG.info("Testing getAccount method 1");
		
		String testUrl = "/Account/details/101";
		
		Mockito.when(accountService.getAccountById(101)).thenReturn(accountsList.get(0));
		
		mockMvc.perform(get(testUrl))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.accountId", Matchers.is(101)))
		.andExpect(jsonPath("$.holderName", Matchers.is("Sonu")))
		.andExpect(jsonPath("$.type", Matchers.is("savings")))
		.andExpect(jsonPath("$.balance", Matchers.is(100.5)));

	}
	
	@Test
	public void testCheckBalance1() throws Exception {
		LOG.info("Testing checkBalance method 1\n\n\n");
		
		String testUrl = "/Account/balance/101";
		
		Mockito.when(accountService.getBalanceById(101)).thenReturn(100.5);
		
		MvcResult result = mockMvc.perform(get(testUrl)).andReturn();
		
		LOG.info(result.getResponse().getContentAsString());
		
		assertEquals(result.getResponse().getStatus(), 200);
		assertEquals(result.getResponse().getContentAsString(), "100.5");

	}
	
	
	@Test
	public void testSaveAccount1() throws Exception {
		LOG.info("Testing saveaccount method 1\n\n\n");
		
		String testUrl = "/Account/create";
		
		Mockito.when(accountService.createAccount(accountsList.get(0)))
		.thenReturn(accountsList.get(0));
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonStringAccount = mapper.writeValueAsString(accountsList.get(0));
		LOG.info(jsonStringAccount);
		
		verify(accountService, times(1)).createAccount(accountsList.get(0));
		
		MvcResult result = mockMvc.perform(post(testUrl)
				.accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonStringAccount)
				).andReturn();
		
		
		LOG.info(result.getResponse().getContentAsString());
		
		assertEquals(result.getResponse().getStatus(), 200);
		assertEquals(result.getResponse().getContentAsString(), "101");

	}

//	@Test
//	public void testGetAllEmployee() throws Exception {
//		LOG.info("testGetAllEmployee using andExpect()");
//
//		Mockito.when(employeeService.getAllEmployee()).thenReturn(accountsList);
//
//		mockMvc.perform(get("/Employee")).andExpect(status().isOk()).andExpect(jsonPath("$", Matchers.hasSize(3)))
//				.andExpect(jsonPath("$[*].id", Matchers.containsInAnyOrder(101, 102, 103)));
//	}
//
//	@Test
//	public void testGetEmployeeById() throws Exception {
//		LOG.info("testGetEmployeeById using andExpect()");
//
//		Mockito.when(employeeService.getEmployeeById(101)).thenReturn(accountsList.get(0));
//
//		mockMvc.perform(get("/Employee/101")).andExpect(status().isOk()).andExpect(jsonPath("$.id", Matchers.is(101)))
//				.andExpect(jsonPath("$.name", Matchers.is("Sonu"))).andExpect(jsonPath("$.salary", Matchers.is(10.5)));
//	}
	
//	@Test
//	public void testDeleteEmployeeByIdPositive() throws Exception {
//		LOG.info("testDeleteEmployeeByIdPositive using assertEquals");
//		
//		Employee expected = new Employee(105,"Arya", 10.5);
//		employeeService.saveOrUpdate(expected);
//		Employee actual = employeeService.delete(105);
//		
//		assertEquals(expected.toString(), actual.toString());
//	}
	
//	@Test
//	public void testSaveEmployee() throws Exception {
//		LOG.info("testSaveEmployee using andExpect()");
//		
//		
//		
//		
//		Employee testEmployee = accountsList.get(0);
//		
//		String formattedData = String.format(
//        		"{\"id\":%d, \"name\":\"%s\", \"salary\":%f}",
//        		testEmployee.getId(),
//        		testEmployee.getName(),
//        		testEmployee.getSalary()
//        		
//        );
//		LOG.info(formattedData);
//		
//		Mockito.when(employeeService.saveOrUpdate(testEmployee)).thenReturn(testEmployee);
//		MvcResult result = mockMvc.perform(post("/Employee")
//	            .contentType(MediaType.APPLICATION_JSON)
//	            .content(formattedData))
//	            .andExpect(status().isOk())
//	            .andReturn();
////		mockMvc.perform(post("/Employee"))
////		        .andExpect(status().isOk());
//		LOG.info(result.getResponse().getContentAsString());
//
//	}
//
//	@AfterAll
//	public static void nullifyEmpData() {
//		LOG.info("nullifyEmpData");
//		accountsList = null;
//	}

}
