package com.kafka.streams.juniper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.kafka.streams.KeyValue;
//import org.json.simple.JSONObject;
import org.json.simple.JSONObject;

public class Filter {
	public int ACCOUNT_NUMBER;
	public String UCODE;
	public int BCIN;
	public String CRUD;

	
//----------------------------Filter class which accepts key as string and value as Json--------------------------------------------
//------ key and json node are assigned with null values and based on the filter conditions these are updated-----------------------
//------ For the Cud_prod_reln which is lookup source we will pass jsonnode when the UCODE matches "KINETICS" and key as the "ACCOUNT_NUMBER"
//------ The Json node only contain ACCOUNT_NUMBER| UCODE | BCIN ------------------------------------------------------------------
	public static KeyValue<String, JsonNode> filterUCODE(String k,  JsonNode jsonNode) throws JsonMappingException, JsonProcessingException {
		
			k= null;
			//StringBuilder str = new StringBuilder();
		  JsonNode jsonNode1 = null;
		 //Filter result = new Filter();
		 JSONObject json1 = new JSONObject();
		 //JsonNode jsonNode1 = null;
		 String UCODE_final=jsonNode.get("UCODE").toString();
		 UCODE_final=UCODE_final.replace("\"",""); 
		 System.out.println("JsonNode value of sourcedata " + jsonNode);
		 String CRUD_final=jsonNode.get("CRUD").toString();
		 CRUD_final=CRUD_final.replace("\"", "");
		 System.out.println("CRUD_final " + CRUD_final);
		 if (UCODE_final.equalsIgnoreCase("KINETICS")) {
			 k= jsonNode.get("ACCOUNT_NUMBER").toString();
			//str.append(jsonNode.get("ACCOUNT_NUMBER").toString()).append(",").append(jsonNode.get("BCIN").toString());	
			    json1.put("ACCOUNT_NUMBER", jsonNode.get("ACCOUNT_NUMBER").asInt());
				json1.put("UCODE", jsonNode.get("UCODE").toString().replace("\"",""));
				json1.put("BCIN", jsonNode.get("BCIN").asInt());
				System.out.println("JsonNode" + jsonNode);
				System.out.println("k" + k);
				 ObjectMapper objectMapper = new ObjectMapper();
				 jsonNode1 = objectMapper.readTree(json1.toString());				
		 } 
		 if (CRUD_final.equalsIgnoreCase("DELETE")) {
				jsonNode1= null;
			}
		 System.out.println("post-filter-source-json " + jsonNode1);
		 return new KeyValue<>(k , jsonNode1);
	}

	public static KeyValue<String, JsonNode> BCINKeyFilter(String k,  JsonNode jsonNode) throws JsonMappingException, JsonProcessingException {
		
		k= null;
		//StringBuilder str = new StringBuilder();
	  JsonNode jsonNode1 = null;
	 //Filter result = new Filter();
	 JSONObject json1 = new JSONObject();
	 //JsonNode jsonNode1 = null;
	 String UCODE_final=jsonNode.get("UCODE").toString();
	 UCODE_final=UCODE_final.replace("\"",""); 
	 System.out.println("JsonNode value of sourcedata " + jsonNode);
	 String CRUD_final=jsonNode.get("CRUD").toString();
	 CRUD_final=CRUD_final.replace("\"", "");
	 System.out.println("CRUD_final " + CRUD_final);
	 if (UCODE_final.equalsIgnoreCase("KINETICS")) {
		 k= jsonNode.get("BCIN").toString();
		//str.append(jsonNode.get("ACCOUNT_NUMBER").toString()).append(",").append(jsonNode.get("BCIN").toString());	
		    json1.put("ACCOUNT_NUMBER", jsonNode.get("ACCOUNT_NUMBER").asInt());
			json1.put("UCODE", jsonNode.get("UCODE").toString().replace("\"",""));
			json1.put("BCIN", jsonNode.get("BCIN").asInt());
			System.out.println("JsonNode" + jsonNode);
			System.out.println("k" + k);
			 ObjectMapper objectMapper = new ObjectMapper();
			 jsonNode1 = objectMapper.readTree(json1.toString());				
	 } 
	 if (CRUD_final.equalsIgnoreCase("DELETE")) {
			jsonNode1= null;
		}
	 System.out.println("post-filter-source-json " + jsonNode1);
	 return new KeyValue<>(k , jsonNode1);
}
	
//------------------------------ Filter class to ensure there is no null values in the key returned--------------------------------
	public static boolean filterNonNull(String key, JsonNode value) {
      return key != null ;
	}
//--------------------------------- Filter-class for Transaction tables where account number is to be passed as key----------------	
	public static KeyValue<String, JsonNode> Transaction(String k, JsonNode jsonNode) throws JsonMappingException, JsonProcessingException {
		k= null;
		  k= jsonNode.get("ACCOUNT_NUMBER").toString();
		  System.out.println("Transaction key value " + k);
		  System.out.println("Transaction jsonnode value " + jsonNode);
		  return new KeyValue<>(k, jsonNode);
	}

	
//--------------------------------- Filter-class for Customer tables where BCIN is to be passed as key----------------	
	public static KeyValue<String, JsonNode> Customer(String k, JsonNode jsonNode) throws JsonMappingException, JsonProcessingException {
		k= null;
		  k= jsonNode.get("BCIN").toString();
		  return new KeyValue<>(k, jsonNode);
	}
				
				}


