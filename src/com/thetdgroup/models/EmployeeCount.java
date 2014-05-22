package com.thetdgroup.models;

import org.json.JSONException;
import org.json.JSONObject;

public class EmployeeCount
{
 private String code = "";
 private String name = "";
 
 //
 public EmployeeCount(JSONObject jsonObject) throws JSONException
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
