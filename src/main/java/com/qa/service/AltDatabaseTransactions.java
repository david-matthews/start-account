	package com.qa.service;
	
	import java.util.HashMap;
	import java.util.Map;

	import javax.enterprise.context.ApplicationScoped;
	import javax.enterprise.inject.Alternative;
	import javax.inject.Inject;

	import com.qa.domain.Account;
	import com.qa.util.JSONUtil;
	
	@Alternative
	public class AltDatabaseTransactions {
		
		private final Long Start_Count = 1L;
		private Map<Long, Account> accountMap;
		private Long ID;
		
		@Inject 
		private JSONUtil util;
		
		public AltDatabaseTransactions() {
			
			this.accountMap = new HashMap<Long, Account>();
			ID = Start_Count;
			startAccountMap();
		}
		
		public String getAllAccounts() {
		
		return util.getJSONForObject(accountMap.values());
		
		}
		
		public String CreateAnAccount (String account) {
			ID++;
			Account newAccount = util.getObjectForJSON(account, Account.class);
			accountMap.put(ID, newAccount);
			return "{\"message\"; \"account successfully added\"}";
		}
		
		public String updateAnAccount(Long id, String accountToChange) {
			Account newAccount =util.getObjectForJSON(accountToChange, Account.class);
			accountMap.put(id, newAccount);
			return "{\"message\"; \"account successfully updated\"}";
		}
		
		public String deleteAnAccount(Long id) {
			accountMap.remove(id);
			return "{\"message\"; \"account successfully removed\"}";
		}
		
		public void startAccountMap() {
			Account account = new Account("David", "Matthews", "4321");
			accountMap.put(Start_Count, account);
		}
		
		public void setUtil(JSONUtil util) {
			this.util = util;
		}	
		
	}
	
