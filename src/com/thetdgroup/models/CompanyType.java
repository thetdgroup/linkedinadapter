package com.thetdgroup.models;

import org.json.JSONException;
import org.json.JSONObject;

public final class CompanyType
{
 private String code = "";
 private String name = "";
 
 //
 CompanyType(JSONObject jsonObject) throws JSONException
 {
  code = jsonObject.getString("code");
  name = jsonObject.getString("name");
 }
 
 //
 //
 public String getCode()
 {
  return code;
 }
 
 public String getName()
 {
  return name;
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
