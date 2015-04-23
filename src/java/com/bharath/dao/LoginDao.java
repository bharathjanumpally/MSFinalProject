/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bharath.dao;

/**
 *
 * @author bharath
 */
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;


public class LoginDao {
    private final String GOSOLAR_URL = "https://www.gosolar.gsu.edu/bprod/twbkwbis.P_WWWLogin";

    public static HtmlPage loginPage;
    public static HtmlPage mainMenuPage; 

    public  boolean validate(String userName, String password) {
        System.out.println("Initializing...");
        /*-----------------------------------------------------------------------
		 * Create a web client
		 *-----------------------------------------------------------------------*/
		WebClient webClient = new WebClient();
		System.out.println("Webclient created!");
            try{
           /*-----------------------------------------------------------------------
		 * Login to the page
		 *-----------------------------------------------------------------------*/
		loginPage = webClient.getPage(GOSOLAR_URL);
		
			/*-----------------------------------------------------------------------
			 * Get the login form
			 *-----------------------------------------------------------------------*/
			HtmlForm loginForm = loginPage.getFormByName("loginform");
			HtmlTextInput userNameField = loginForm.getInputByName("sid");
			HtmlPasswordInput passwordField = loginForm.getInputByName("PIN");
			HtmlSubmitInput submitButton = loginForm.getInputByValue("Login");
						
			/*-----------------------------------------------------------------------
			 * Set the login values
			 *-----------------------------------------------------------------------*/
			userNameField.setValueAttribute(userName);
			passwordField.setValueAttribute(password);
				
			/*-----------------------------------------------------------------------
			 * Submit the form to receive the main menu page
			 *-----------------------------------------------------------------------*/
			mainMenuPage = submitButton.click();
                        
			System.out.println("Logged into GO Solar");
		 
                        return true;
            
           }
           catch (Exception e)
           {
               e.printStackTrace();
           }

         return false;
    }

}
