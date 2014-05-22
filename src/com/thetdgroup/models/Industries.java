package com.thetdgroup.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Industries
{
 private ArrayList<JSONObject> industryList = null;
 
 //
 Industries(JSONObject jsonObject) throws JSONException
 {
  industryList = new ArrayList<JSONObject>(jsonObject.getInt("_total"));
  JSONArray jsonArray = jsonObject.getJSONArray("values");
  
  //
  for(int iIndex = 0; iIndex < jsonArray.length(); iIndex++)
  {
   industryList.add(jsonArray.getJSONObject(iIndex));
  }
 }

 //
 //
 JSONArray toJSON() throws JSONException
 {
  JSONArray jsonArray = new JSONArray();
  
  for(JSONObject jsonIndustry : industryList)
  {
   JSONObject jsonObject = new JSONObject();
   jsonObject.put("industry", jsonIndustry);
   
   //
   jsonArray.put(jsonObject);
  }
  
  //
  return jsonArray;
 }
}
