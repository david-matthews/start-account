package com.qa.service;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;


@Transactional(SUPPORTS)
public class DatabaseTransactions {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager entitymanager;
	
	@Inject 
	private JSONUtil util;
	
	public void setEntityManager(EntityManager entitymanager) {
		this.entitymanager = entitymanager;
	}
	
	public void setJSONUtil (JSONUtil util) {
		this.util = util;
	}
	
	
	public String getAllAccounts(){
		Query query = entitymanager.createQuery("SELECT * FROM Account");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}
	
	private Account findAnAccount(long id){
		return entitymanager.find(Account.class, id);
	}
	
	@Transactional(REQUIRED)
	public String createAnAccount(String account){
		Account newAccount = util.getObjectForJSON(account, Account.class);
		entitymanager.persist(newAccount); 
		return "{\"message\": \"account has been sucessfully added\"}";
	}
	
	@Transactional(REQUIRED)
	public String deleteAnAccount(long id) {
		Account existingAccount = findAnAccount(id);
		if (existingAccount != null) {
			entitymanager.remove(existingAccount);
		}
		return "{\\\"message\\\": \\\"account sucessfully deleted\\\"}";
	}
	
	public String upadateAnAccount(long id, String accountToChange){
		Account updatedAccount = util.getObjectForJSON(accountToChange, Account.class);
		Account existingAccount = findAnAccount(id);
		if (accountToChange != null) {
			existingAccount = updatedAccount;
			entitymanager.merge(existingAccount);
		}
		return "{\"message\": \"account sucessfully updated\"}";
	}	
	
	
}