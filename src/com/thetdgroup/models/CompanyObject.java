package com.thetdgroup.models;

import org.json.JSONException;
import org.json.JSONObject;

public class CompanyObject
{
 private CompanyType companyType = null;
 private CompanyDomains companyDomains = null;
 private EmployeeCount employeeCount = null;
 private Industries companyIndustries = null;
 private CompanyAddress companyLocations = null;
 private CompanySpecialities companySpeciality = null;
 private CompanyStatus companyStatus = null;
 private CompanyStockExchange companyStockExchange = null;
 
 private String companyID = "";
 private String companyName = "";
 private String companyDescription = "";
 private String companyFoundedYear = "";
 private String companyLogoURL = "";
 private String squareLogoUrl = "";
 private String blogRssUrl = "";
 
 private int numberOfFollowers = 0;
 private String companyTicker = "";
 private String twitterId = "";
 private String universalName = "";
 private String websiteUrl = "";
 
 //
 public CompanyObject(JSONObject jsonCompanyObject) throws JSONException
 {
  if(jsonCompanyObject.has("companyType"))
  {
   companyType = new CompanyType(jsonCompanyObject.getJSONObject("companyType"));
  }
  
  //
  if(jsonCompanyObject.has("description"))
  {
   companyDescription = jsonCompanyObject.getString("description");
  }
  
  //
  if(jsonCompanyObject.has("emailDomains"))
  {
   companyDomains = new CompanyDomains(jsonCompanyObject.getJSONObject("emailDomains"));
  }
  
  //
  if(jsonCompanyObject.has("employeeCountRange"))
  {
   employeeCount = new EmployeeCount(jsonCompanyObject.getJSONObject("employeeCountRange"));
  }
  
  //
  if(jsonCompanyObject.has("foundedYear"))
  {
   companyFoundedYear = jsonCompanyObject.getString("foundedYear");
  }
  
  //
  if(jsonCompanyObject.has("id"))
  {
   companyID = jsonCompanyObject.getString("id");
  }
  
  //
  if(jsonCompanyObject.has("name"))
  {
   companyName = jsonCompanyObject.getString("name");
  }

  //
  if(jsonCompanyObject.has("logoUrl"))
  {
   companyLogoURL = jsonCompanyObject.getString("logoUrl");
  }

  //
  if(jsonCompanyObject.has("numFollowers"))
  {
   numberOfFollowers = jsonCompanyObject.getInt("numFollowers");
  }

  //
  if(jsonCompanyObject.has("industries"))
  {
   companyIndustries = new Industries(jsonCompanyObject.getJSONObject("industries"));
  }
  
  //
  if(jsonCompanyObject.has("locations"))
  {
   companyLocations = new CompanyAddress(jsonCompanyObject.getJSONObject("locations"));
  }
  
  //
  if(jsonCompanyObject.has("specialties"))
  {
   companySpeciality = new CompanySpecialities(jsonCompanyObject.getJSONObject("specialties"));
  }
  
  //
  if(jsonCompanyObject.has("status"))
  {
   companyStatus = new CompanyStatus(jsonCompanyObject.getJSONObject("status"));
  }
  
  //
  if(jsonCompanyObject.has("stockExchange"))
  {
   companyStockExchange = new CompanyStockExchange(jsonCompanyObject.getJSONObject("stockExchange"));
  }  
  
  if(jsonCompanyObject.has("ticker"))
  {
   companyTicker = jsonCompanyObject.getString("ticker");
  }

  if(jsonCompanyObject.has("twitterId"))
  {
   twitterId = jsonCompanyObject.getString("twitterId");
  }
  
  if(jsonCompanyObject.has("universalName"))
  {
   universalName = jsonCompanyObject.getString("universalName");
  }
  
  if(jsonCompanyObject.has("websiteUrl"))
  {
   websiteUrl = jsonCompanyObject.getString("websiteUrl");
  }
  
  if(jsonCompanyObject.has("squareLogoUrl"))
  {
   squareLogoUrl = jsonCompanyObject.getString("squareLogoUrl");
  } 
  
  if(jsonCompanyObject.has("blogRssUrl"))
  {
   blogRssUrl = jsonCompanyObject.getString("blogRssUrl");
  } 
 }
 
 //
 //
 public JSONObject toJSON() throws JSONException
 {
  JSONObject jsonObject = new JSONObject();
  jsonObject.put("company_id", companyID);
  jsonObject.put("company_name", companyName);
  jsonObject.put("company_logo_url", companyLogoURL);
  jsonObject.put("company_description", companyDescription);
  jsonObject.put("company_founded_year", companyFoundedYear);
  jsonObject.put("company_follower_count", numberOfFollowers);
  jsonObject.put("company_square_logo_url", squareLogoUrl);
  jsonObject.put("company_blog_rss_url", blogRssUrl);
  jsonObject.put("company_ticker_symbol", companyTicker);
  jsonObject.put("company_twitter_id", twitterId);
  jsonObject.put("company_universal_name", universalName);
  jsonObject.put("company_website_url", websiteUrl);
  
  if(companyType != null)
   jsonObject.put("company_type", companyType.toJSON());

  if(companyDomains != null)
   jsonObject.put("company_domains", companyDomains.toJSON());

  if(employeeCount != null)
   jsonObject.put("company_domains", employeeCount.toJSON());
  
  if(companyIndustries != null)
   jsonObject.put("company_industries", companyIndustries.toJSON());
  
  if(companyLocations != null)
   jsonObject.put("company_locations", companyLocations.toJSON());
  
  if(companySpeciality != null)
   jsonObject.put("company_speciality", companySpeciality.toJSON());
  
  if(companyStatus != null)
   jsonObject.put("company_status", companyStatus.toJSON());
  
  if(companyStockExchange != null)
   jsonObject.put("company_stock_exchange", companyStockExchange.toJSON());
  
  //
  return jsonObject;
 }
}
