package com.qa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class TestingDatabaseTransactions {
	
	@InjectMocks
	private DatabaseTransactions dbt;
	
	@Mock 
	private EntityManager entitymanager;
	
	@Mock
	private Query query;
	
	private JSONUtil util; 
	
	private static final String MOCK_OBJECT = "{\"firstName\":\"David\",\"secondName\":\"Matthews\",\"accountNumber\":\"4321\"}";
	
	private static final String MOCK_DATA_ARRAY = "[{\"firstName\":\"David\",\"secondName\":\"Matthews\",\"accountNumber\":\"4321\"}]";
	
	@Before 
	public void setup () {
		dbt.setEntityManager(entitymanager);
		util = new JSONUtil();
		dbt.setJSONUtil(util);
	}
	
	@Test
	public void testGetAllAccounts() {
	Mockito.when(entitymanager.createQuery(Mockito.anyString())).thenReturn(query);
	List<Account> accounts = new ArrayList<Account>();
	accounts.add(new Account("David","Matthews","4321"));
	Mockito.when(query.getResultList()).thenReturn(accounts);
	Assert.assertEquals(MOCK_DATA_ARRAY, dbt.getAllAccounts());
	}

	@Test
	public void testCreateAccount() {
		String reply = dbt.createAnAccount(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"account has been sucessfully added\"}");
	}

	@Test
	public void testUpdateAccount() {
		String reply = dbt.upadateAnAccount(1L, MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"account sucessfully updated\"}");
	}

	@Test
	public void testDeleteAccount() {
		String reply = dbt.deleteAnAccount(1L);
		Assert.assertEquals(reply, "{\"message\": \"account sucessfully deleted\"}");
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
