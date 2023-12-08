/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package askuser;

import java.util.Scanner;

/**
 *
 * @author julio
 */
public class AskUser {

    /**
     * @param args the command line arguments
     */
    
        // TODO code application logic here
       

    /**
     * Ask the user to enter some text by issuing a prompt
     * if they do not enter text, display an error and re-prompt
     * Allow spaces. Do *not* allow numbers or special characters
     * 
     * @param prompt - a question or prompt to display 
     * @return text entered by the user as a String
     */
    public String askUserForText (String prompt){
        
        Scanner myKB = new Scanner(System.in);
        String userInput = "";
        
       do{
         
        System.out.println(prompt); //issue prompt
        System.out.println("You must enter text only.");
        userInput = myKB.nextLine(); //read input from user
       
         //keep going while input does NOT match text (space is allowed)
       }while (!userInput.matches("[a-zA-Z ]+"));
       
       //The input MUST be text only!
       
       return userInput;
        
    }
    
    /**
     * Ask the user to enter an integer value by issuing a prompt
     * The input must be an integer - if not, display an error and re-prompt
     * The method will not enforce any restriction on the integer
     * @param prompt - a question or prompt to display
     * @return an integer entered by the user
     */
    
        public int askUserforInt(String prompt){
       
       Scanner myKB = new Scanner(System.in);
        String userInput = "";
        
       do{
         
        System.out.println(prompt); //issue prompt
        System.out.println("Enter values only");
        userInput = myKB.nextLine(); //read input from user
       
         //keep going while input is NOT an integer
       }while (!userInput.matches("[0-9]+"));
       
       //The input MUST be a number, but it is still being tored as a atring
       //just convert it to return an int!
       
       return Integer.parseInt(userInput);
   }
   
   /**
     * Ask the user to enter an integer value by issuing a prompt
     * The input must be an integer - if not, display an error and re-prompt
     * The method will require the user to enter a value GREATER than a specified minimum value
     * 
     * @param prompt - a question or prompt to display
     * @param minValue the minimum value that is allowed
     * @return a valid integer entered by the user (must be greater than minValue)
     */
    public int askUserForInt(String prompt, int minValue){
        
         Scanner myKB = new Scanner(System.in);
        int userInput=0;
        boolean valid = false; //assume input is NOT valid
        
       do{
         
        System.out.println(prompt); //issue prompt
        System.out.println("You must enter integer values greater than " + minValue );
        
        try{
            
            userInput = myKB.nextInt();
            valid = true;
            
        }catch(Exception e){
            //it was not possible to read as a int
            System.out.println("Invalid input - must be an integer!");
            myKB.nextLine();
        }
       
         //keep going while not an integer or the input was less than the min allowed
         
       }while ( (!valid) || (userInput < minValue));
        
       return userInput;
    }
        
    }
    

