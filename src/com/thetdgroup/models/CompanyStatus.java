package com.thetdgroup.models;

import org.json.JSONException;
import org.json.JSONObject;

public class CompanyStatus
{
 private String code = "";
 private String name = "";
 
 //
 public CompanyStatus(JSONObject jsonObject) throws JSONException
 {
  code = jsonObject.getString("code");
  name = jsonObject.getString("name");
 }

 //
 //
 JSONObject toJSON() throws JSONException
 {
  JSONObject jsonObject = new JSONObject();
  jsonObject.put("code", code);
  jsonObject.put("name", name);
  
  //
  return jsonObject;
 }
}
