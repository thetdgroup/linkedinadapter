package com.thetdgroup.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CompanySpecialities
{
 private ArrayList<String> specialityList = null;
 
 //
 CompanySpecialities(JSONObject jsonObject) throws JSONException
 {
  specialityList = new ArrayList<String>(jsonObject.getInt("_total"));
  JSONArray jsonArray = jsonObject.getJSONArray("values");
  
  //
  for(int iIndex = 0; iIndex < jsonArray.length(); iIndex++)
  {
   specialityList.add(jsonArray.getString(iIndex));
  }
 }

 //
 //
 JSONArray toJSON() throws JSONException
 {
  JSONArray jsonArray = new JSONArray();
  
  for(String speciality : specialityList)
  {
   JSONObject jsonObject = new JSONObject();
   jsonObject.put("speciality_name", speciality);
   
   //
   jsonArray.put(jsonObject);
  }
  
  //
  return jsonArray;
 }
}
