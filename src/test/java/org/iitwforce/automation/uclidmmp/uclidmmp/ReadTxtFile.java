package org.iitwforce.automation.uclidmmp.uclidmmp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class ReadTxtFile {

	public static void main(String[] args) throws Exception {
		
		int icount=0;
		String sword= "selenium";
		 // File path is passed as parameter
        File file = new File("C:\\Batch22\\Test.txt");
 
        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)
 
        // Creating an object of BufferedReader class
        BufferedReader br  = new BufferedReader(new FileReader(file));
 
        // Declaring a string variable
        String st;
        // Condition holds true till
        // there is character in a string
        while ((st = br.readLine()) != null)
        {
        	System.out.println(st);
        
        	if(st.indexOf(sword)>0) 
        	{
                 icount = icount+1;
        	 }
         
        }
          // Print the string
            System.out.println("selenium word count is " + icount);
           
        	
    }
}

