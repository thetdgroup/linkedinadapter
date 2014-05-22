package com.thetdgroup.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CompanyDomains
{
 private ArrayList<String> domainList = null;
 
 //
 CompanyDomains(JSONObject jsonObject) throws JSONException
 {
  domainList = new ArrayList<String>(jsonObject.getInt("_total"));
  JSONArray jsonArray = jsonObject.getJSONArray("values");
  
  //
  for(int iIndex = 0; iIndex < jsonArray.length(); iIndex++)
  {
   domainList.add(jsonArray.getString(iIndex));
  }
 }

 //
 //
 JSONArray toJSON() throws JSONException
 {
  JSONArray jsonArray = new JSONArray();
  
  for(String domain : domainList)
  {
   JSONObject jsonObject = new JSONObject();
   jsonObject.put("domain_name", domain);
   
   jsonArray.put(jsonObject);
  }
  
  //
  return jsonArray;
 }
}