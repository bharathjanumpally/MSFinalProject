/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bharath.dao;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.UserAgent;
import com.jaunt.component.Table;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author bharath
 */
public class SelectCourseDao {
    public  byte [] outArray;
    	public HtmlPage viewClassRollPage;
    public void getStudentList(String courseName)
    {
                           LoginDao ld = new LoginDao();
                           SelectTermDao  st = new SelectTermDao();
                         try
                         {
                        HtmlOption courseOption = st.coursesList.getOptionByValue(courseName);
			courseOption.click();
			/*-----------------------------------------------------------------------*/
			
			/*-----------------------------------------------------------------------
			 * Return back to the faculty menu page by submitting the form
			 *-----------------------------------------------------------------------*/
			ld.mainMenuPage = st.courseSelectionForm.getInputByValue("Submit").click();
			System.out.println("Course selected as : " + courseName);
                        
                     
		
		/*-----------------------------------------------------------------------
		 * 	Get into faculty menu and view class roll
		 *-----------------------------------------------------------------------*/
		st.facultyMenuAnchor = ld.mainMenuPage.getAnchorByText("Faculty Menu");
		st.facultyMenuPage = st.facultyMenuAnchor.click();
		
		HtmlAnchor viewClassRollAnchor = st.facultyMenuPage.getAnchorByText("View Class Roll");
		viewClassRollPage = viewClassRollAnchor.click();
		
		System.out.println("View class roll accessed");
		/*-----------------------------------------------------------------------*/
                
		String curl = viewClassRollPage.asXml();
                
                String success =getStudentslisttoExcel(curl);
                System.out.println(success);
    }
      catch (Exception e)
      {
          e.printStackTrace();
      }
              
    }
    
     public String getStudentslisttoExcel(String curl) 
    {
            ArrayList<String> arr = new ArrayList<String>();
            ArrayList<String> arr1 = new ArrayList<String>();
            ArrayList<String> arr2 = new ArrayList<String>();
            ArrayList<String> arr3 = new ArrayList<String>();
            
            try{
            UserAgent userAgent = new UserAgent();
            userAgent.openContent(curl);
            //userAgent.visit(curl);
            
            Table table = userAgent.doc.getTable("<table class=\"datadisplaytable\" summary=\"This table displays a list of students registered for the course; summary information about each student is provided.\" width=\"100%\">");
            System.out.println(table.toString());
            Elements elements = table.getCol(0);                                  
            for (Element element : elements) {
                arr.add(element.innerText());
                //System.out.println(element.innerText());
            }

            Elements elements1 = table.getCol(2);                                  
            for (Element element : elements1) {
                arr1.add(element.innerText());
                //System.out.println(element.innerText());
            }
            Elements elements2 = table.getCol(3);                               
            for (Element element : elements2) {
                arr2.add(element.innerText());
                //System.out.println(element.innerText());
            }
            Elements elements3 = table.getCol(4);                      
            for (Element element : elements3) {
                arr3.add(element.innerText());
                // System.out.println(element.innerText());
            }

             
            HSSFWorkbook workBook = new HSSFWorkbook();
            HSSFSheet spreadSheet = workBook.createSheet("StudentDetails");
            HSSFRow row;
            HSSFCell cell;
            //int colnum = 0;

           
            int rowcount=0;
            //for (int i = 0; i < arr.size(); i++) {
            for(String str:arr)
            {
                                              
                
                row = spreadSheet.createRow((short) rowcount);
                // System.out.println(arr.get(i));
                cell = row.createCell(0);
                cell.setCellValue(str.trim());
                rowcount++; 
              //  colcount++;
            }
            rowcount=0;
            for(String str:arr1)
            {
                
                row = spreadSheet.getRow((short) rowcount);
                // System.out.println(arr.get(i));
                cell = row.createCell(1);
                cell.setCellValue(str.trim());
                rowcount++; 
              //  colcount++;
            }
            rowcount=0;
            for(String str:arr2)
            {
                                              
                
                row = spreadSheet.getRow((short) rowcount);
                // System.out.println(arr.get(i));
                cell = row.createCell(2);
                cell.setCellValue(str.trim());
                rowcount++; 
              //  colcount++;
            }
            rowcount=0;
            for(String str:arr3)
            {
                                              
                row = spreadSheet.getRow((short) rowcount);
                // System.out.println(arr.get(i));
                cell = row.createCell(3);
                cell.setCellValue(str.trim());
                rowcount++; 
              //  colcount++;
            }
            
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            workBook.write(outByteStream);
            outArray = outByteStream.toByteArray();
            System.out.println(outArray.length);
            //fos = new FileOutputStream("student.xls");
            System.out.println("Done");
            //workBook.write(fos);
       
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            return "Done";
  
    }
    
}
