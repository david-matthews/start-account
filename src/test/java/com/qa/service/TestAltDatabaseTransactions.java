package com.qa.service;


import static org.junit.Assert.assertEquals;

import javax.enterprise.inject.Alternative;


import org.junit.Before;
import org.junit.Test;

import com.qa.util.JSONUtil;


@Alternative
public class TestAltDatabaseTransactions {
	
	private AltDatabaseTransactions  adt;
	private JSONUtil util;
	private String mock = "{\"firstName\":\"David\",\"secondName\":\"Matthews\",\"accountNumber\":\"4321\"}";
	private String update = "{\"firstName\":\"David\",\"secondName\":\"Mathews\",\"accountNumber\":\"4321\"}";
	
	@Before 
	public void setUp() {
	util = new JSONUtil();
	adt = new AltDatabaseTransactions();
	adt.setUtil(util);
	}
	
	@Test
	public void testGetAllAccounts() {
		adt.startAccountMap();
		String expected = "[{\"firstName\":\"David\",\"secondName\":\"Matthews\",\"accountNumber\":\"4321\"}]";
		assertEquals(expected, adt.getAllAccounts());
		
	}
	
	@Test
	public void testAddAnAccount() {
		String actual = adt.CreateAnAccount(mock);
		String expected = "{\"message\"; \"account successfully added\"}";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testDeleteAnAccount() {
		String actual = adt.deleteAnAccount(1L);
		String expected = "{\"message\"; \"account successfully removed\"}";
		assertEquals(expected, actual);
	}
	
	@Test
	public void testUpdateAnAccount() {
		String actual = adt.updateAnAccount(1L, update);
		String expected = "{\"message\"; \"account successfully updated\"}";
		assertEquals(expected,actual);
	}
}