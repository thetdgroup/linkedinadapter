package com.thetdgroup.configurations;

import org.jdom.Element;
import org.jdom.JDOMException;

public class LinkedInAdapterConfigObject
{
 private String companyName = "";
 private String applicationName = "";
 private String apiKey = "";
 private String secretKey = "";
 private String oauthUserToken = "";
 private String oauthSecretToken = "";
 private String serviceFile = "";

 //
 public void parse(final Element element) throws JDOMException
 {
  //
  Element childElement = element.getChild("linkedin_app_info");
  companyName = childElement.getChildText("company");
  applicationName = childElement.getChildText("application_name");
  
  //
  childElement = element.getChild("linkedin_key_info");
  apiKey = childElement.getChildText("api_key");
  secretKey = childElement.getChildText("secret_key");
  serviceFile = childElement.getChildText("service_dat_file");

  //
  childElement = element.getChild("linkedin_oauth_token_info");
  oauthUserToken = childElement.getChildText("oauth_user_token");
  oauthSecretToken = childElement.getChildText("oauth_secret_token");
 }

 //
 public String getCompanyName()
 {
  return companyName;
 }

 public String getApplicationName()
 {
  return applicationName;
 }

 public String getApiKey()
 {
  return apiKey;
 }

 public String getSecretKey()
 {
  return secretKey;
 }

 public String getOauthUserToken()
 {
  return oauthUserToken;
 }

 public String getOauthSecretToken()
 {
  return oauthSecretToken;
 }

 public String getServiceFile()
 {
  return serviceFile;
 }
}