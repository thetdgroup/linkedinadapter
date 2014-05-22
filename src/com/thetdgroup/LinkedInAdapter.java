package com.thetdgroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLEncoder;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.xpath.XPath;
import org.json.JSONArray;
import org.json.JSONObject;
import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

import com.thetdgroup.configurations.LinkedInAdapterConfigObject;
import com.thetdgroup.models.CompanyObject;

//
public final class LinkedInAdapter extends BaseSocialMediaAdapter
{
 private FuzeInCommunication fuzeInCommunication = new FuzeInCommunication();
 private LinkedInAdapterConfigObject adapterObject = new LinkedInAdapterConfigObject();
 
 private Token accessToken = null;
 private OAuthService service = null;
 
 //
 private static String companyBasicSearchField = "(facets,companies:(id,name,universal-name))";

 private static String companySearchField = "(facets,companies:(id,name,universal-name,email-domains,company-type,ticker,website-url,"
                                           + "industries,status,logo-url,square-logo-url,"
                                           + "blog-rss-url,twitter-id,employee-count-range,specialties,locations,description,"
                                           + "stock-exchange,founded-year,end-year,num-followers))";
 
 private static String companyInfoField = "(id,name,universal-name,email-domains,company-type,ticker,website-url,"
                                         + "industries,status,logo-url,square-logo-url,"
                                         + "blog-rss-url,twitter-id,employee-count-range,specialties,locations,description,"
                                         + "stock-exchange,founded-year,end-year,num-followers)";

 private static String companyProductField = "(id,name,type,creation-timestamp,logo-url,description,features,"
                                            + "video:(title,url),product-deal:(title,url,text),sales-persons,"
                                            + "num-recommendations,recommendations:(recommender,id,product-id,text,reply,timestamp,"
                                            + "likes:(timestamp,person)),product-category,website-url,disclaimer)";
 
 //
 //
 public void initialize(final JSONObject configurationObject) throws Exception
 {
  // validate that all params are here
  if(configurationObject.has("adapter_configuration_file") == false)
  {
   throw new Exception("The adapter_configuration_file parameter was not found");
  }
  
  // Set FuzeIn connection
  if(configurationObject.has("fuzein_connection_info"))
  {
   JSONObject jsonCommParams = configurationObject.getJSONObject("fuzein_connection_info");
   
   fuzeInCommunication.setFuzeInConnection(jsonCommParams.getString("service_url"), 
                                           jsonCommParams.getInt("service_socket_timeout"), 
                                           jsonCommParams.getInt("service_connection_timeout"), 
                                           jsonCommParams.getInt("service_connection_retry"));
  }
  
  //
  parseAdapterConfiguration(configurationObject.getString("adapter_configuration_file"));
  
  //
  // We need a OAuthService to handle authentication and the subsequent calls.
  service = new ServiceBuilder()
               .provider(LinkedInApi.class)
               .apiKey(adapterObject.getApiKey())
               .apiSecret(adapterObject.getSecretKey())
               .build();
  
  // The Access Token is used in all Data calls to the APIs - it basically says our application has been given access
  // to the approved information in LinkedIn
  accessToken = null;
  
  // Since we are going to use the REST APIs we need to generate a request token as the first step in the call.
  // Once we get an access token we can continue to use that until the API key changes or auth is revoked.
  // Therefore, to make this sample easier to re-use we serialize the AuthHandler (which stores the access token) to
  // disk and then reuse it.
  ObjectInputStream inputStream = null;
  ObjectOutputStream outputStream = null;
  
  try
  {
   File file = new File(adapterObject.getServiceFile());

   if(file.exists())
   {
    // If the file exists we assume it has the AuthHandler in it - which in turn contains the Access Token
    inputStream = new ObjectInputStream(new FileInputStream(file));
    
    AuthHandler authHandler = (AuthHandler) inputStream.readObject();
    accessToken = authHandler.getAccessToken();
   } 
   else 
   {
    // In the constructor the AuthHandler goes through the chain of calls to create an Access Token
    outputStream = new ObjectOutputStream(new FileOutputStream(adapterObject.getServiceFile()));

    AuthHandler authHandler = new AuthHandler(service);
    outputStream.writeObject(authHandler);
    
    accessToken = authHandler.getAccessToken();
   }
  }
  finally
  {
   if(inputStream != null)
    inputStream.close();
   
   if(outputStream != null)
    outputStream.close();
  }
 }
 
 //
 public void destroy()
 {
  //
  if(fuzeInCommunication != null)
  {
   fuzeInCommunication.releaseConnection();
  }
 }

 //
 public JSONObject searchCompany(final JSONObject parameters) throws Exception
 {
  JSONArray jsonArray = new JSONArray();

  // Validate that all parameters are present
  if(parameters.has("company_name") == false)
  {
   throw new Exception("The 'company_name' parameter is required.");
  }
  
  //
  String companySearch = "http://api.linkedin.com/v1/company-search:" + 
                          companyBasicSearchField + 
                          "?keywords=" + URLEncoder.encode(parameters.getString("company_name"), "UTF-8") + 
                          "&facet=location,us:0"; 

  OAuthRequest request = new OAuthRequest(Verb.GET, companySearch);
  request.addHeader("x-li-format", "json");
  service.signRequest(accessToken, request);
  
  //
  Response response = request.send();
  String serviceResponse = response.getBody();
  JSONObject jsonResponse = new JSONObject(serviceResponse);
  
  //
  // Get Companies returned
  JSONObject jsonCompanies = jsonResponse.getJSONObject("companies");
  
  if(jsonCompanies.has("_total"))
  {
   int pageCount = jsonCompanies.getInt("_count");
   int start = jsonCompanies.getInt("_start");
   int total = jsonCompanies.getInt("_total");
   
   // For every company found
   JSONArray jsonCompanyArray = jsonCompanies.getJSONArray("values");
   
   for(int iIndex = 0; iIndex < jsonCompanyArray.length(); iIndex++)
   {
    CompanyObject companyObject = new CompanyObject(jsonCompanyArray.getJSONObject(iIndex));
    jsonArray.put(companyObject.toJSON());
   }
  }
  
  //
  JSONObject jsonObject = new JSONObject();
  jsonObject.put(AdapterConstants.ADAPTER_STATUS, AdapterConstants.status.SUCCESS);
  jsonObject.put(AdapterConstants.ADAPTER_DATA, jsonArray);
  
  return jsonObject;
 }
 
 //
 public JSONObject getCompanyInformation(final JSONObject parameters) throws Exception
 {
  
  //
  return null;
 }
 
 //
 public JSONObject getCompanyProducts(final JSONObject parameters) throws Exception
 {
  
  //
  return null;
 }
 
 //
 //
 //
 private void parseAdapterConfiguration(final String adapterConfigurationFile) throws Exception
 {
  //
  // Parse Configuration
  Document configurationDocument = saxBuilder.build(adapterConfigurationFile);
 
  //
  // Parse jets3t properties file
  XPath xPath = XPath.newInstance("linkedin_configuration");
  Element element = (Element) xPath.selectSingleNode(configurationDocument);
  adapterObject.parse(element);
 }
}
