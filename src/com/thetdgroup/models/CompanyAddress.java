package com.thetdgroup.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CompanyAddress
{
 private ArrayList<JSONObject> addressList = null;
 
 //
 CompanyAddress(JSONObject jsonObject) throws JSONException
 {
  addressList = new ArrayList<JSONObject>(jsonObject.getInt("_total"));
  JSONArray jsonArray = jsonObject.getJSONArray("values");
  
  //
  for(int iIndex = 0; iIndex < jsonArray.length(); iIndex++)
  {
   JSONObject jsonLocationObject = jsonArray.getJSONObject(iIndex);
   
   //
   JSONObject jsonAddress = new JSONObject();
   
   if(jsonLocationObject.getJSONObject("address").has("city"))
    jsonAddress.put("city", jsonLocationObject.getJSONObject("address").getString("city"));
   
   if(jsonLocationObject.getJSONObject("address").has("postalCode"))
    jsonAddress.put("postal_code", jsonLocationObject.getJSONObject("address").getString("postalCode"));
   
   if(jsonLocationObject.getJSONObject("address").has("street1"))
    jsonAddress.put("address", jsonLocationObject.getJSONObject("address").getString("street1"));
   
   //
   JSONObject jsonContact = new JSONObject();

   if(jsonLocationObject.getJSONObject("contactInfo").has("fax"))
    jsonContact.put("fax", jsonLocationObject.getJSONObject("contactInfo").getString("fax"));
 
   if(jsonLocationObject.getJSONObject("contactInfo").has("phone1"))
    jsonContact.put("phone", jsonLocationObject.getJSONObject("contactInfo").getString("phone1"));
   
   //
   JSONObject jsonAddressObject = new JSONObject();
   jsonAddressObject.put("address", jsonAddress);
   jsonAddressObject.put("contact", jsonContact);

   addressList.add(jsonAddressObject);
  }
 }

 //
 //
 JSONArray toJSON() throws JSONException
 {
  JSONArray jsonArray = new JSONArray();
  
  for(JSONObject jsonAddress : addressList)
  {
   JSONObject jsonObject = new JSONObject();
   jsonObject.put("company_location", jsonAddress);
   
   //
   jsonArray.put(jsonObject);
  }
  
  //
  return jsonArray;
 }
}
