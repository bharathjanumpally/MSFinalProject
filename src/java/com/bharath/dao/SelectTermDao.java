package com.bharath.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bharath
 */
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import java.util.ArrayList;
import java.util.List;
public class SelectTermDao {
    public static  HtmlPage facultyMenuPage;
    public static  HtmlPage termSelectionPage;
    public static  HtmlPage courseSelectionPage;
    public static HtmlSelect coursesList ;
    public static HtmlAnchor facultyMenuAnchor;
    public final String TERM_SELECTION_FORM_ACTION     = "/bprod/bwlkostm.P_FacStoreTerm";
    public final String COURSE_SELECTION_FORM_ACTION	= "/bprod/bwlkocrn.P_FacStoreCRN";
    public static HtmlForm courseSelectionForm = null;
    public List<String> selectTerm(String courseTerm)
    {
       LoginDao ld = new LoginDao();
       List<String> courseNames = null;
       System.out.println(ld.mainMenuPage.getAnchors());
         facultyMenuAnchor = ld.mainMenuPage.getAnchorByText("Faculty Menu");
        try
        {
		facultyMenuPage = facultyMenuAnchor.click();
		/*-----------------------------------------------------------------------*/
		
		/*-----------------------------------------------------------------------
		 * Process term selection step
		 *-----------------------------------------------------------------------*/
			/*-----------------------------------------------------------------------
			 * Click on select term anchor
			 *-----------------------------------------------------------------------*/
			HtmlAnchor selectTermAnchor = facultyMenuPage.getAnchorByText("Select Term");
			termSelectionPage = selectTermAnchor.click();
			/*-----------------------------------------------------------------------*/
			
			/*-----------------------------------------------------------------------
			 * Traverse through the list options and select the term
			 *-----------------------------------------------------------------------*/
			List<HtmlForm> forms = termSelectionPage.getForms();
			
			HtmlForm termSelectionForm = null;
				for(HtmlForm form : forms){
					String actionAttrib = form.getActionAttribute();
					if(TERM_SELECTION_FORM_ACTION.equals(actionAttrib)){
						termSelectionForm = form;
						break;
					}
				}
			
			HtmlSelect selectionList = termSelectionForm.getSelectByName("term");
			HtmlOption selectedOption = selectionList.getOptionByValue(courseTerm);
			selectedOption.click();
			/*-----------------------------------------------------------------------*/
			
			/*-----------------------------------------------------------------------
			 * Return back to the main menu page by submitting the form
			 *-----------------------------------------------------------------------*/
			ld.mainMenuPage = termSelectionForm.getInputByValue("Submit").click();
			System.out.println("Term selected as : " + courseTerm);
                        
                        /*-----------------------------------------------------------------------*/
			
		/*-----------------------------------------------------------------------
		 * Process Course Selection
		 *-----------------------------------------------------------------------*/
			
			/*-----------------------------------------------------------------------
			 * Get into faculty menu page
			 *-----------------------------------------------------------------------*/
			facultyMenuAnchor = ld.mainMenuPage.getAnchorByText("Faculty Menu");
			facultyMenuPage = facultyMenuAnchor.click();
			/*-----------------------------------------------------------------------*/
			
			/*-----------------------------------------------------------------------
			 * Click on select course anchor
			 *-----------------------------------------------------------------------*/
			HtmlAnchor selectCourseAnchor = facultyMenuPage.getAnchorByText("Select CRN");
			courseSelectionPage = selectCourseAnchor.click();
			/*-----------------------------------------------------------------------*/
                         /* Traverse through the courses and select the proper one
			 *-----------------------------------------------------------------------*/
			
			List<HtmlForm> pageForms = courseSelectionPage.getForms();
			
			for(HtmlForm form : pageForms){
				String actionAttrib = form.getActionAttribute();
				if(COURSE_SELECTION_FORM_ACTION.equals(actionAttrib)){
					courseSelectionForm = form;
					break;
				}
			}
			 coursesList = courseSelectionForm.getSelectByName("crn");

			// Build a list of courses
			List<HtmlOption> coursesListed = coursesList.getOptions();
			 courseNames = new ArrayList();

			for(HtmlOption option : coursesListed){
				String courseName = option.getText();
				courseNames.add(courseName);
			}
                        
                     //courseNames.add("hello");
             }
        catch (Exception e)
           {
               e.printStackTrace();
           }
          return courseNames; 
    }
    
    
    
}
