/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inpututilities;

import askuser.AskUser;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author Julio C. Gallardo Quintero
 */
public class mySQL {

      /**
      * Read every line in a file from 
      * the resources folder inside the program
      * and print all the characters inside that file .
      * @return ASCII ART. 
      * @throws java.io.FileNotFoundException
      */      
      public String MyArt () throws FileNotFoundException{
          
          File file = new File("resources/art.txt");
          Scanner reader = new Scanner(file);
          while(reader.hasNextLine()) {
              String line = reader.nextLine();
              System.out.println(line);
              
          }
          System.out.println("    *************************************************************************************");
          
          return null;
          
        
          
      } 
      
     /**
      * Read every line in a file from 
      * the resources folder inside the program
      * and print all the characters inside that file.
      * @return ASCII ART. 
      * @throws java.io.FileNotFoundException
      */      
      public String MyArtEnd () throws FileNotFoundException{
          
          File file = new File("resources/art2.txt");
          Scanner reader = new Scanner(file);
          while(reader.hasNextLine()) {
              String line = reader.nextLine();
              System.out.println(line);
              
          }
          System.out.println(" ");
          
        return null;
          
      }      
      
     /**
      * Connect Java with the NFL-149 Database 
      * Include the query to get the List of all captains ID's 
      * with their first name last name and Team 
      * in a particular order
      * Allows the user to arrange the data in ascending 
      * or descending order.
      * @return data from MySQL database in a table.
      */    
     public String mySQLconnector () {
        
         Scanner myKB = new Scanner(System.in);
         int option;
         boolean alreadyPrint = false;
         String blue = "\033[34m";
         String red = "\033[31m";
         String b = "\u001B[0m";
         
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //this is the DB LOCATION //This is the DBNAME //USER //PASSWORD
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfl-149", "root", "root");
            System.out.println(" ");         
            System.out.println("Connected to DB... \n"); 
            
            Statement stmt = con.createStatement(); // we use stmt to create a new statement 
            Statement stmt2 = con.createStatement(); // we use stmt to create a new statement 
            
            do {// starts a do-while loop, ensuring that the following code is executed at least once and then repeated till meet the condition.
                
                //sub-menu prompt asking the user to select the order in which the data should be shown.               
                System.out.println("\nPlease select the order ASCENDING or DESCENDING ");
                System.out.println("1) Show in ASCENDING order ");
                System.out.println("2) Show in DESCENDING order");
                System.out.println("3) Back to NFL DATA MENU\n");
                
                // prompt the user to enter the number corresponding to their chosen option. The entered value is assigned to the option variable.
                option = new AskUser().askUserforInt("Please enter the number of the option");
                
                switch (option) { //switch statement based on the value of the option variable.
                    
                case 1: //This line represents the first case, where the user selects to show the data in ascending order.
                    
                    System.out.println(b+"\nHere is the list in "+blue+ "ASCENDING "+b+ "order\n");
                    String myQuery =  "Select t.captain, p.firstName, p.lastName, t.teamName FROM player p INNER JOIN teams t ON t.id_Team = p.team WHERE p.id_player = t.captain ORDER BY firstName ASC" ;      
                    //This line above defines the SQL query to retrieve the data from the database in ascending order based on the players' first names and their associated team information.
                    
                    ResultSet queryResult = stmt.executeQuery(myQuery);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult ResultSet.

                    while (queryResult.next()) { //This line starts a loop that iterates through the rows of the queryResult ResultSet.
                        
                         if (!alreadyPrint) { //checks if the header and lines have already been printed.
                             
                         for (int i = 0; i < 76; i++) { //starts a loop to print a line.
                         System.out.print("-");
                         }   
                         //This line prints a formatted header row with column names.   
                         System.out.printf("\n|%-15s | %-15s | %-15s | %-20s|\n","captain ID: ", "Firstname: " , "Surname: " ,  "Team Name: ");
                        
                         for (int i = 0; i < 76; i++) {//starts a loop to print a line.
                         System.out.print("-");
                         } System.out.println("");
                         
                         alreadyPrint = true; //sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                        }
                         
                    //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                    System.out.printf("|%-15s | %-15s | %-15s | %-20s|\n", queryResult.getInt(1), queryResult.getString("firstName"),  queryResult.getString("lastName"), queryResult.getNString("teamName"));
                
                    } 
                    //prompts the user to press enter to go back to the principal menu.
                    System.out.println(b+" \npress " +red+ "enter "+b+ "to back to the principal menu...");
                    myKB.nextLine();
                    
                    break; //ending the execution for the case.
                  
                case 2://This line represents the second case, where the user selects to show the data in descending order.
                    
                    System.out.println(b+"\nHere is the list in "+ blue + "DESCENDING"+b+" order\n");    
                    String myQuery2 =  "Select t.captain, p.firstName, p.lastName, t.teamName FROM player p INNER JOIN teams t ON t.id_Team = p.team WHERE p.id_player = t.captain ORDER BY firstName DESC" ; 
                    //This line above defines the SQL query to retrieve the data from the database in descending order based on the players' first names and their associated team information.
                    
                    //Executes the SQL query using the stmt2 statement object and stores the resulting data in the queryResult2 ResultSet.
                    ResultSet queryResult2 = stmt2.executeQuery(myQuery2);

                    while (queryResult2.next()) { //starts a loop that iterates through the rows of the queryResult2 ResultSet.
                        
                        if (!alreadyPrint) { //checks if the header and lines have already been printed.
                            
                        for (int i = 0; i < 76; i++) { //starts a loop to print a line.
                         System.out.print("-");
                         }                        
                        //This line prints a formatted header row with column names. 
                        System.out.printf("\n|%-15s | %-15s | %-15s | %-20s|\n","captain ID: ", "Firstname: " , "Surname: " ,  "Team Name:");
                        
                        for (int i = 0; i < 76; i++) {//starts a loop to print a line.
                         System.out.print("-");
                         }
                        System.out.println("");//prints an empty line for visual separation.                       
                        alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                        } 
                    //prints a formatted data row with values retrieved from the queryResult2 ResultSet.
                    System.out.printf("|%-15s | %-15s | %-15s | %-20s|\n", queryResult2.getInt(1), queryResult2.getString("firstName"),  queryResult2.getString("lastName"), queryResult2.getNString("teamName"));
             
                    }     
                    //prompts the user to press enter to go back to the principal menu.
                    System.out.println(b+"\npress " + red + " enter "+ b + " to back to the principal menu...");
                    myKB.nextLine();
                
                    break;//ending the execution for the case.
                        
                case 3:////This line represents the third case option.
                    
                    System.out.println(" ");//prints an empty line for visual separation.
                
                    break;//ending the execution for the case.
                   
                    default://default case, which is executed when none of the previous cases match the value of the option variable.
                    System.out.println("That was not a valid choice");
                } 
                
                
            } while ((option !=3) && (option !=2) && (option !=1));//condition for the do-while loop to continue running.
            

        } catch (SQLException e) {
            System.out.println("SQL ERROR --->"); //prints a message indicating an SQL error.
            System.out.println(e.getMessage()); //prints the error message associated with the SQLException.
            System.out.println(e.getSQLState());//prints the SQL state associated with the SQLException.
            
        } 
        catch (ClassNotFoundException e){
        
            System.out.println("Class error --" + e.getMessage()); // prints an error message indicating a class error.
    
}
        return null;
    
        
    }  
    
     /**
     * Connect Java with the NFL-149 Database 
     * Include the query to get the 
     * List of all players with a particular name
     * Ask the user to introduce a surname or part of 
     * Allows the user to enter just text as input
     * using the input to filter the data
     * @return data from MySQL database in a table.
     */ 
     public String mySQLconnector2 () {
        
         Scanner myKB = new Scanner(System.in);
         boolean datafound = false;
         boolean alreadyPrint = false;
         String blue = "\033[34m";
         String red = "\033[31m";
         String b = "\u001B[0m";
         
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //this is the DB LOCATION //This is the DBNAME //USER //PASSWORD
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfl-149", "root", "root");
                    
            System.out.println("Connected to DB..."); 
 
            Statement stmt = con.createStatement(); // we use stmt to create a new statement 
            
            ////promt to user and read input method.
            System.out.println("Please introduce the surname you wish to search for ");
            String letter = new askuser.AskUser().askUserForText("Introduce the Surname or part of ");
            
            
            String myQuery =  "SELECT  p.lastName, p.firstName, t.teamName FROM player p INNER JOIN teams t ON t.id_Team = p.team WHERE lastName LIKE  \'"+ letter + "%\' ORDER BY p.lastName ASC, p.firstName ASC";      
            //This line above defines the SQL query to retrieve the data from the database.
            
            ResultSet queryResult = stmt.executeQuery(myQuery);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult ResultSet.
           
            while (queryResult.next()) { //starts a loop that iterates through the rows of the queryResult ResultSet.    
                
                if (!alreadyPrint) {//checks if the header and lines have already been printed.
                         System.out.println(b+"\nList of players with the username or part of : " + blue + letter + ("\n"));
                         for (int i = 0; i < 58; i++) { //starts a loop to print a line.
                         System.out.print("-");
                         }
                         
                  //This line prints a formatted header row with column names.        
                 System.out.printf("\n|%-15s | %-15s | %-20s| \n","Lastname: ", "Firstname: " , "TeamName: ");
                 
                         for (int i = 0; i < 58; i++) {//starts a loop to print a line.
                         System.out.print("-");
                         } System.out.println("");
                         
                 alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                }
                //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                System.out.printf("|%-15s | %-15s | %-20s| \n", queryResult.getString("lastName"), queryResult.getString("firstName"),  queryResult.getString("teamName"));
                
                datafound = true;//sets the datafound flag to true to indicate that data rows have been found.
            }  
            
            if (!datafound) {//the condition checks if no data rows were found and print a message saying that no data found.
                  System.out.println(b+"\ndata not found for:" + blue + letter);               
            }     
            
                    //prompts the user to press enter to go back to the principal menu.
                    System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                    myKB.nextLine();                    
            
        } catch (SQLException e) {
            System.out.println("SQL ERROR --->");//prints a message indicating an SQL error.
            System.out.println(e.getMessage());//prints the error message associated with the SQLException.
            System.out.println(e.getSQLState());//prints the SQL state associated with the SQLException.
            
        } 
        catch (ClassNotFoundException e){
        
            System.out.println("Class error --" + e.getMessage()); // prints an error message indicating a class error.
    
}       
        return null;
        
    }

     /**
     * Connect Java with the NFL-149 Database 
     * Include the query to get the 
     * List of all players that lost in a particular matchday
     * Asking to the user to select the matchday with a sub menu
     * Allows the user back to the principal menu. 
     * @return data from MySQL database in a table.
     */      
     public String mySQLconnector3 () {
        
         Scanner myKB = new Scanner(System.in);
         int option;
         boolean alreadyPrint = false;
         String blue = "\033[34m";
         String red = "\033[31m";
         String b = "\u001B[0m";
         
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //this is the DB LOCATION //This is the DBNAME //USER //PASSWORD
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfl-149", "root", "root");
            System.out.println(" ");         
            System.out.println("Connected to DB... \n"); 
            
            Statement stmt = con.createStatement(); // we use stmt to create a new statement 
            Statement stmt2 = con.createStatement(); // we use stmt2 to create a new statement 
            Statement stmt3 = con.createStatement(); // we use stmt3 to create a new statement 
            Statement stmt4 = con.createStatement(); // we use stmt4 to create a new statement 
            
            
            do {// starts a do-while loop, ensuring that the following code is executed at least once and then repeated till meet the condition.
                 
                //sub-menu prompt asking the user to select which data should be shown.      
                System.out.println("\nPlease select the matchday  ");
                System.out.println("1) Show players that lost  the first matchday");
                System.out.println("2) Show players that lost  the second matchday");
                System.out.println("3) Show players that lost  the third matchday");
                System.out.println("4) Show players that lost  the fourth matchday");
                System.out.println("5) Back to NFL DATA MENU\n");
                
                // prompt the user to enter the number corresponding to their chosen option. The entered value is assigned to the option variable.
                option = new AskUser().askUserforInt("please enter the number of the option");
                
                switch (option) {//switch statement based on the value of the option variable.
                    
                case 1://This line represents the first case, where the user selects to show the data of players that lost  the first matchday.
                    
                    System.out.println(b+"\nHere is the list of players that lost  the "+ blue + "first matchday\n");
                    String myQuery =  "SELECT p.* , t.teamName FROM player p INNER JOIN gameresults gr ON  gr.team = p.team INNER JOIN teams t ON t.id_Team = gr.team INNER JOIN games g ON gr.game = g.id_Games\n WHERE  gameDate > \"2022-08-28\" AND gameDate < \"2022-09-02\" AND gameResult = \"Loss\" ORDER BY skill_Level ASC;" ;      
                     //This line above defines the SQL query to retrieve the data from the database.
                    
                    ResultSet queryResult = stmt.executeQuery(myQuery);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult ResultSet.

                    while (queryResult.next()) {//This line starts a loop that iterates through the rows of the queryResult ResultSet. 
                        
                        if (!alreadyPrint) {//checks if the header and lines have already been printed.
                        for (int i = 0; i < 160; i++) {//starts a loop to print a line.
                         System.out.print("-");
                         }    
                        //This line prints a formatted header row with column names.
                        System.out.printf("\n|%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s|\n","Player ID: ", "Firstname: " , "Surname: " , "position" , "Skill Level:", "Team ID:", "teamName: ");
                        
                        for (int i = 0; i < 160; i++) {//starts a loop to print a line.
                        System.out.print("-");
                        }
                        System.out.println(" ");
                        alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                        }
                    //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                    System.out.printf("|%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s|\n", queryResult.getInt(1), queryResult.getString("firstName"),  queryResult.getString("lastName"),queryResult.getString("Position"),queryResult.getString("skill_Level"), queryResult.getInt("team"),queryResult.getString("teamName"));               
                    } 
                    //prompts the user to press enter to go back to the principal menu.
                    System.out.println(b+"\npress "+red+ "enter "+b+ "to back to the principal menu...");
                    myKB.nextLine();
                    
                    break;//ending the execution for the case.
                  
                case 2://This line represents the second case, where the user selects to show the data of players that lost  the second matchday.
                    
                    System.out.println(b+"\nHere is the list of players that lost  the " +blue +"second matchday\n");    
                    String myQuery2 =  "SELECT p.* , t.teamName FROM player p INNER JOIN gameresults gr ON  gr.team = p.team INNER JOIN teams t ON t.id_Team = gr.team INNER JOIN games g ON gr.game = g.id_Games\n WHERE  gameDate > \"2022-09-07\" AND gameDate < \"2022-09-11\" AND gameResult = \"Loss\" ORDER BY skill_Level ASC;" ;      
                    //This line above defines the SQL query to retrieve the data from the database.
                    
                    ResultSet queryResult2 = stmt2.executeQuery(myQuery2);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult2 ResultSet.

                    while (queryResult2.next()) {//This line starts a loop that iterates through the rows of the queryResult ResultSet. 
                        
                    if (!alreadyPrint) {//checks if the header and lines have already been printed.
                        for (int i = 0; i < 160; i++) {//starts a loop to print a line.
                         System.out.print("-");
                         } 
                        //This line prints a formatted header row with column names.
                        System.out.printf("\n|%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s|\n","Player ID: ", "Firstname: " , "Surname: " , "position" , "Skill Level:", "Team ID:", "teamName: ");
                        
                        for (int i = 0; i < 160; i++) {//starts a loop to print a line.
                        System.out.print("-");
                        }
                        System.out.println(" ");
                        alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                        }
                    //This line prints a formatted data row with values retrieved from the queryResult2 ResultSet.
                    System.out.printf("|%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s|\n", queryResult2.getInt(1), queryResult2.getString("firstName"),  queryResult2.getString("lastName"),queryResult2.getString("Position"),queryResult2.getString("skill_Level"), queryResult2.getInt("team"),queryResult2.getString("teamName"));
             
                    }     
                    //prompts the user to press enter to go back to the principal menu.
                    System.out.println(b+"\npress "+red+ "enter "+b+ "to back to the principal menu...");
                    myKB.nextLine();
                
                    break;//ending the execution for the case.
                        
                case 3://This line represents the third case, where the user selects to show the data of players that lost  the third matchday.
                    
                    System.out.println(b+"\nHere is the list of players that lost  the "+blue+"third matchday\n");    
                    String myQuery3 =  "SELECT p.* , t.teamName FROM player p INNER JOIN gameresults gr ON  gr.team = p.team INNER JOIN teams t ON t.id_Team = gr.team INNER JOIN games g ON gr.game = g.id_Games\n WHERE  gameDate > \"2022-09-13\" AND gameDate < \"2022-09-17\" AND gameResult = \"Loss\" ORDER BY skill_Level ASC;" ;      
                    //This line above defines the SQL query to retrieve the data from the database.
                    
                    ResultSet queryResult3 = stmt3.executeQuery(myQuery3);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult3 ResultSet.

                    while (queryResult3.next()) {//This line starts a loop that iterates through the rows of the queryResult ResultSet. 
                                             
                        if (!alreadyPrint) {//checks if the header and lines have already been printed.
                         for (int i = 0; i < 160; i++) {//starts a loop to print a line.
                         System.out.print("-");
                         }    
                        //This line prints a formatted header row with column names. 
                        System.out.printf("\n|%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s|\n","Player ID: ", "Firstname: " , "Surname: " , "position" , "Skill Level:", "Team ID:", "teamName: ");
                        for (int i = 0; i < 160; i++) {//starts a loop to print a line.
                        System.out.print("-");
                        }
                        System.out.println(" ");
                        alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                        }    
                    //This line prints a formatted data row with values retrieved from the queryResult3 ResultSet.
                    System.out.printf("|%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s|\n", queryResult3.getInt(1), queryResult3.getString("firstName"),  queryResult3.getString("lastName"),queryResult3.getString("Position"),queryResult3.getString("skill_Level"), queryResult3.getInt("team"),queryResult3.getString("teamName"));
             
                    }     
                    //prompts the user to press enter to go back to the principal menu.
                    System.out.println(b+"\npress "+red+ "enter "+b+ "to back to the principal menu...");
                    myKB.nextLine();
                
                    break;//ending the execution for the case.
                    
                case 4://This line represents the fourth case, where the user selects to show the data of players that lost  the fourth matchday.
                    
                    System.out.println(b+"\nHere is the list of players that lost the "+blue+ "fourth matchday\n");    
                    String myQuery4 =  "SELECT p.* , t.teamName FROM player p INNER JOIN gameresults gr ON  gr.team = p.team INNER JOIN teams t ON t.id_Team = gr.team INNER JOIN games g ON gr.game = g.id_Games\n WHERE  gameDate > \"2022-09-20\" AND gameDate < \"2022-09-24\" AND gameResult = \"Loss\" ORDER BY skill_Level ASC;" ;      
                      //This line above defines the SQL query to retrieve the data from the database.
         
                    ResultSet queryResult4 = stmt4.executeQuery(myQuery4);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult4 ResultSet.

                    while (queryResult4.next()) {//This line starts a loop that iterates through the rows of the queryResult ResultSet.                        
                    
                        if (!alreadyPrint) {//checks if the header and lines have already been printed.
                        for (int i = 0; i < 160; i++) {//starts a loop to print a line.
                        System.out.print("-");
                        }    
                         //This line prints a formatted header row with column names. 
                        System.out.printf("\n|%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s|\n","Player ID: ", "Firstname: " , "Surname: " , "position" , "Skill Level:", "Team ID:", "teamName: ");
                        for (int i = 0; i < 160; i++) {
                        System.out.print("-");
                        }
                        System.out.println(" ");
                        alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                        }        
                    //This line prints a formatted data row with values retrieved from the queryResult3 ResultSet.
                    System.out.printf("|%-20s | %-20s | %-20s | %-20s | %-20s | %-20s | %-20s|\n", queryResult4.getInt(1), queryResult4.getString("firstName"),  queryResult4.getString("lastName"),queryResult4.getString("Position"),queryResult4.getString("skill_Level"), queryResult4.getInt("team"),queryResult4.getString("teamName"));
             
                    }     
                     //prompts the user to press enter to go back to the principal menu.
                    System.out.println(b+"\npress "+red+ "enter "+b+ "to back to the principal menu...");
                    myKB.nextLine();
                
                    break;//ending the execution for the case.
                    
                case 5://This line represents the fifth case, where the user selects back to the nfl data base principal menu.
                    
                    System.out.println(" ");
                    
                    break;//ending the execution for the case.
                    
                    default://default case, which is executed when none of the previous cases match the value of the option variable.
                    System.out.println("That was not a valid choice");
                } 
                
                
            } while ((option !=3) && (option !=2) && (option !=1) && (option !=4) && (option !=5));//condition for the do-while loop to continue running.
            

        } catch (SQLException e) {
            System.out.println("SQL ERROR --->");//prints a message indicating an SQL error.
            System.out.println(e.getMessage());//prints the error message associated with the SQLException.
            System.out.println(e.getSQLState());//prints the SQL state associated with the SQLException.
            
        } 
        catch (ClassNotFoundException e){
        
            System.out.println("Class error --" + e.getMessage()); // prints an error message indicating a class error.
    
}
        return null;
    
           
        
     }
      
     /**
     * Connect Java with the NFL-149 Database 
     * Include the query to get the Amount of teams that 
     * 'Win' OR 'Loss' OR 'Draw' in a particular matchday
     * Asking the user to select the matchday with a sub menu
     * opening other sub menu that allows the user to pick 
     * the game result that they want to see
     * or let them back to the previous menu.
     * @return data from MySQL database in a table.
     */      
     public String mySQLconnector4 () {
         //setting the variables.
         Scanner myKB = new Scanner(System.in);
         int option;
         boolean alreadyPrint = false;
         String blue = "\033[34m";
         String red = "\033[31m";
         String b = "\u001B[0m";
         
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //this is the DB LOCATION //This is the DBNAME //USER //PASSWORD
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfl-149", "root", "root");
            System.out.println(" ");         
            System.out.println("Connected to DB... \n"); 
            
            Statement stmt = con.createStatement();
             // we use stmt to create a new statement
             
            
            do {// starts a do-while loop, ensuring that the following code is executed at least once and then repeated till meet the condition.
                
                //sub-menu prompt asking the user to select which data should be shown.    
                System.out.println("\nPlease select the matchday that you want to know");
                System.out.println("1) Show information for  the first matchday");
                System.out.println("2) Show information for  the second matchday");
                System.out.println("3) Show information for  the third matchday");
                System.out.println("4) Show information for  the fourth matchday");
                System.out.println("5) Back to NFL DATA MENU\n");
                
                // prompt the user to enter the number corresponding to their chosen option. The entered value is assigned to the option variable.
                option = new AskUser().askUserforInt("please enter the number of the option");
                
                switch (option) {//switch statement based on the value of the option variable.
                    
                case 1://This line represents the first case, where the user selects to show the information for the first matchday.
                    
                     System.out.println("\nWhich type of result you want to know");
                     System.out.println("1) How many teams Lost a match?");
                     System.out.println("2) How many teams Won a match?");
                     System.out.println("3) How many teams Drawn a match?");
                     System.out.println("4) Back to the matchday selection menu\n");   
                     option = new AskUser().askUserforInt("please enter the number of the option");
                     
                        switch (option) {//switch statement based on the value of the option variable.
                            
                            case 1://This line represents the first case of the sub-menu, where the user pick to see how many teams lost  the first matchday.
                                
                             String myQuery =  "SELECT  Count( gr.gameResult), gr.gameResult FROM gameresults gr INNER JOIN games g ON gr.game = g.id_Games WHERE  gameDate > \"2022-08-28\" AND  gameDate < \"2022-09-02\" AND gameResult = \"Loss\" GROUP BY gameResult;" ;      
                             //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult = stmt.executeQuery(myQuery);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult ResultSet.

                                System.out.println(b+"\nAmount of teams that"+blue+ " lost the first matchday\n");
                             
                             while (queryResult.next()) { //This line starts a loop that iterates through the rows of the queryResult ResultSet.
                                 
                                 if (!alreadyPrint) {//checks if the header and lines have already been printed.
                                     
                                    for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                    //This line prints a formatted header row with column names.
                                    System.out.printf("\n|%-20s | %-20s|\n", "Amount Of Teams " , " Game Result ");
                                     
                                    for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                    System.out.println(" ");                                    
                                    alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }
    
                             //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                             System.out.printf("|%-20s | %-20s|\n", queryResult.getInt(1), queryResult.getString("gameResult"));
                
                            } 
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option =5;//change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break;//ending the execution for the case.
                             
                            case 2://This line represents the second case of the sub-menu, where the user pick to see how many teams won  the first matchday.
                                
                             String myQuery2 =  "SELECT  Count( gr.gameResult), gr.gameResult FROM gameresults gr INNER JOIN games g ON gr.game = g.id_Games WHERE  gameDate > \"2022-08-28\" AND  gameDate < \"2022-09-02\" AND gameResult = \"Win\" GROUP BY gameResult;" ;      
                              //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult2 = stmt.executeQuery(myQuery2);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult2 ResultSet.
                             
                             System.out.println(b+"\nAmount of teams that"+blue+ " Won the first matchday\n");
                             
                             while (queryResult2.next()) {//This line starts a loop that iterates through the rows of the queryResult2 ResultSet.
                                 
                             if (!alreadyPrint) {//This line starts a loop that iterates through the rows of the queryResult2 ResultSet.
                                 
                                 for (int i = 0; i < 45; i++) {//checks if the header and lines have already been printed.
                                    System.out.print("-");
                                    } 
                                 
                                    //This line prints a formatted header row with column names.
                                    System.out.printf("\n|%-20s | %-20s|\n", "Amount Of Teams " , " Game Result ");
                                    
                                    for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                    System.out.println(" "); 
                                    alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }    
                            //This line prints a formatted data row with values retrieved from the queryResult2 ResultSet.
                             System.out.printf("|%-20s | %-20s|\n", queryResult2.getInt(1), queryResult2.getString("gameResult"));
                
                            } 
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option =5;//change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break; //ending the execution for the case.   
                            
                            case 3://This line represents the third case of the sub-menu, where the user pick to see how many teams Draw  the first matchday.
                                
                             String myQuery3 =  "SELECT  Count( gr.gameResult), gr.gameResult FROM gameresults gr INNER JOIN games g ON gr.game = g.id_Games WHERE  gameDate > \"2022-08-28\" AND  gameDate < \"2022-09-02\" AND gameResult = \"Draw\" GROUP BY gameResult;" ;      
                             //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult3 = stmt.executeQuery(myQuery3);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult3 ResultSet.

                             System.out.println(b+"\nAmount of teams that"+blue+ " Drawn the first matchday\n");
                             
                             while (queryResult3.next()) {//This line starts a loop that iterates through the rows of the queryResult3 ResultSet.
                                 
                             if (!alreadyPrint) {//checks if the header and lines have already been printed.
                                 for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }          
                                 
                                    //This line prints a formatted header row with column names.
                                    System.out.printf("\n|%-20s | %-20s|\n", "Amount Of Teams " , " Game Result ");
                                    
                                    for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                    System.out.println(" ");  
                                    alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }    
                             //This line prints a formatted data row with values retrieved from the queryResult3 ResultSet.
                             System.out.printf(" %-20s   %-20s\n", queryResult3.getInt(1), queryResult3.getString("gameResult"));
                
                            } 
                             
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option =5;//change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break; //ending the execution for the case.  
                             
                            case 4: //This represents the fourth case, where the user can back to the matchday selection menu(previous menu).
                                
                                System.out.println("");
                                break;//ending the execution for the case.
                                
                            default://default case, which is executed when none of the previous cases match the value of the option variable.
                                
                                 System.out.println("That was not a valid choice");
                                 
                        }
                     
                 
                    break;//ending the execution for the case.
                  
                case 2://This line represents the second case, where the user selects to show the information for the second matchday.
                     
                     System.out.println("\nWhich type of result you want to know");
                     System.out.println("1) How many teams Lost a match?");
                     System.out.println("2) How many teams Won a match?");
                     System.out.println("3) How many teams Drawn a match?");
                     System.out.println("4) Back to the matchday selection menu\n");   
                     
                     // prompt the user to enter the number corresponding to their chosen option. The entered value is assigned to the option variable.
                     option = new AskUser().askUserforInt("please enter the number of the option");
                     
                     
                        switch (option) {//switch statement based on the value of the option variable.
                            
                            case 1://This line represents the first case of the sub-menu, where the user pick to see how many teams lost  the second matchday.
                             String myQuery =  "SELECT  Count( gr.gameResult), gr.gameResult FROM gameresults gr INNER JOIN games g ON gr.game = g.id_Games WHERE  gameDate > \"2022-09-07\" AND  gameDate < \"2022-09-11\" AND gameResult = \"Loss\" GROUP BY gameResult;" ;      
                             //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult = stmt.executeQuery(myQuery);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult ResultSet.
                             
                             System.out.println(b+"\nAmount of teams that"+blue+ " lost the second matchday\n");

                             while (queryResult.next()) { //This line starts a loop that iterates through the rows of the queryResult ResultSet.
                                 
                             if (!alreadyPrint) {//checks if the header and lines have already been printed.
                                 for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }          
                                 
                                    //This line prints a formatted header row with column names.
                                    System.out.printf("\n|%-20s | %-20s|\n", "Amount Of Teams " , " Game Result ");
                                    
                                    for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                    System.out.println(" ");  
                                    alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }     
                             //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                             System.out.printf("|%-20s | %-20s|\n", queryResult.getInt(1), queryResult.getString("gameResult"));
                
                            } 
                             //prompts the user to press enter to go back to the principal menu.                  
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option =5;//change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break;//ending the execution for the case.
                             
                            case 2://This line represents the second case of the sub-menu, where the user pick to see how many teams won  the second matchday.
                             String myQuery2 =  "SELECT  Count( gr.gameResult), gr.gameResult FROM gameresults gr INNER JOIN games g ON gr.game = g.id_Games WHERE  gameDate > \"2022-09-07\" AND  gameDate < \"2022-09-11\" AND gameResult = \"Win\" GROUP BY gameResult;" ;      
                             //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult2 = stmt.executeQuery(myQuery2);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult2 ResultSet.
                             
                             System.out.println(b+"\nAmount of teams that"+blue+ " Won the second matchday\n");

                             while (queryResult2.next()) { //This line starts a loop that iterates through the rows of the queryResult2 ResultSet.
                                 
                             if (!alreadyPrint) {//checks if the header and lines have already been printed.
                                 for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }          
                                    //This line prints a formatted header row with column names.
                                    System.out.printf("\n|%-20s | %-20s|\n", "Amount Of Teams " , " Game Result ");
                                    
                                    for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                    System.out.println(" ");  
                                    alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                    
                                 }     
                             //This line prints a formatted data row with values retrieved from the queryResult2 ResultSet.
                             System.out.printf("|%-20s | %-20s|\n", queryResult2.getInt(1), queryResult2.getString("gameResult"));
                
                            } 
                            
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option =5;//change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break;//ending the execution for the case.   
                            
                            case 3://This line represents the third case of the sub-menu, where the user pick to see how many teams Draw the second matchday.
                                
                             String myQuery3 =  "SELECT  Count( gr.gameResult), gr.gameResult FROM gameresults gr INNER JOIN games g ON gr.game = g.id_Games WHERE  gameDate > \"2022-09-07\" AND  gameDate < \"2022-09-11\" AND gameResult = \"Draw\" GROUP BY gameResult;" ;      
                            //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult3 = stmt.executeQuery(myQuery3);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult3 ResultSet.
                             
                             System.out.println(b+"\nAmount of teams that"+blue+ " Drawn the second matchday\n");

                             while (queryResult3.next()) { //This line starts a loop that iterates through the rows of the queryResult3 ResultSet.
                                 
                             if (!alreadyPrint) {//checks if the header and lines have already been printed.
                                 
                                 for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }                                    
                                    System.out.printf("\n|%-20s | %-20s|\n", "Amount Of Teams " , " Game Result ");
                                    
                                    for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                    System.out.println(" ");  
                                    alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }     
                             
                             //This line prints a formatted data row with values retrieved from the queryResult3 ResultSet.
                             System.out.printf("|%-20s | %-20s|\n", queryResult3.getInt(1), queryResult3.getString("gameResult"));
                
                            } 
                             
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option =5;//change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break; //ending the execution for the case.  
                             
                            case 4://This represents the fourth case, where the user can back to the matchday selection menu(previous menu).
                                
                                System.out.println("");
                                
                                break;//ending the execution for the case.  
                                
                            default: //default case, which is executed when none of the previous cases match the value of the option variable.
                                
                                 System.out.println("That was not a valid choice");
                        }
 
                    break;//ending the execution for the case. 
                        
                case 3://This line represents the third case, where the user selects to see the information for the third matchday.
                    
                     System.out.println("\nWhich type of result you want to know");
                     System.out.println("1) How many teams Lost a match?");
                     System.out.println("2) How many teams Won a match?");
                     System.out.println("3) How many teams Drawn a match?");
                     System.out.println("4) Back to the matchday selection menu\n");   
                     
                     // prompt the user to enter the number corresponding to their chosen option. The entered value is assigned to the option variable.
                     option = new AskUser().askUserforInt("please enter the number of the option");
                     
                        switch (option) {//switch statement based on the value of the option variable.
                            
                            case 1://This line represents the first case of the sub-menu, where the user pick to see how many teams lost the third matchday.
                                
                             String myQuery =  "SELECT  Count( gr.gameResult), gr.gameResult FROM gameresults gr INNER JOIN games g ON gr.game = g.id_Games WHERE  gameDate > \"2022-09-13\" AND  gameDate < \"2022-09-17\" AND gameResult = \"Loss\" GROUP BY gameResult;" ;      
                             //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult = stmt.executeQuery(myQuery);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult ResultSet.
                             
                             System.out.println(b+"\nAmount of teams that"+blue+ " lost the third matchday\n");

                             while (queryResult.next()) { //This line starts a loop that iterates through the rows of the queryResult ResultSet.   
                                 
                              if (!alreadyPrint) {//checks if the header and lines have already been printed.                                     
                                    for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                    //This line prints a formatted header row with column names.
                                     System.out.printf("\n|%-20s | %-20s|\n", "Amount Of Teams " , " Game Result ");
                                     for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                     System.out.println(" ");
                                     alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }      
                             //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                             System.out.printf("|%-20s | %-20s|\n", queryResult.getInt(1), queryResult.getString("gameResult"));
                
                            } 
                            
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option =5;//change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break;//ending the execution for the case.
                             
                            case 2://This line represents the second case of the sub-menu, where the user pick to see how many teams won the third matchday.
                             String myQuery2 =  "SELECT  Count( gr.gameResult), gr.gameResult FROM gameresults gr INNER JOIN games g ON gr.game = g.id_Games WHERE  gameDate > \"2022-09-13\" AND  gameDate < \"2022-09-17\" AND gameResult = \"Win\" GROUP BY gameResult;" ;      
                             //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult2 = stmt.executeQuery(myQuery2);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult2 ResultSet.
                             
                             System.out.println(b+"\nAmount of teams that"+blue+ " Won the third matchday\n");

                             while (queryResult2.next()) {      
                               if (!alreadyPrint) {//checks if the header and lines have already been printed.                                     
                                    for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                    //This line prints a formatted header row with column names.
                                     System.out.printf("\n|%-20s | %-20s|\n", "Amount Of Teams " , " Game Result ");
                                     for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                     System.out.println(" ");
                                     alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }   
                               
                             //This line prints a formatted data row with values retrieved from the queryResult2 ResultSet.
                             System.out.printf("|%-20s | %-20s|\n", queryResult2.getInt(1), queryResult2.getString("gameResult"));
                
                            } 
                             
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option =5;//change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break; //ending the execution for the case.   
                            
                            case 3://This line represents the third case of the sub-menu, where the user pick to see how many teams won the third matchday.
                                
                             String myQuery3 =  "SELECT  Count( gr.gameResult), gr.gameResult FROM gameresults gr INNER JOIN games g ON gr.game = g.id_Games WHERE  gameDate > \"2022-09-13\" AND  gameDate < \"2022-09-17\" AND gameResult = \"Draw\" GROUP BY gameResult;" ;      
                             //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult3 = stmt.executeQuery(myQuery3);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult2 ResultSet.
                             
                             
                             System.out.println(b+"\nAmount of teams that"+blue+ " Drawn the third matchday\n");

                             while (queryResult3.next()) {   
                              if (!alreadyPrint) {//checks if the header and lines have already been printed.                                     
                                    for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                    //This line prints a formatted header row with column names.
                                     System.out.printf("\n|%-20s | %-20s|\n", "Amount Of Teams " , " Game Result ");
                                     for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                     System.out.println(" ");
                                     alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }      
                
                             //This line prints a formatted data row with values retrieved from the queryResult3 ResultSet.
                             System.out.printf("|%-20s | %-20s|\n", queryResult3.getInt(1), queryResult3.getString("gameResult"));
                
                            } 
                           
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option =5;//change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break;  //ending the execution for the case.    
                             
                            case 4://This represents the fourth case, where the user can back to the matchday selection menu(previous menu).
                                
                                System.out.println(" ");
                                
                                break;//ending the execution for the case.  
                                
                            default: //default case, which is executed when none of the previous cases match the value of the option variable.
                                
                                 System.out.println("That was not a valid choice");
                        }
                                           
                    
                    break;//ending the execution for the case.  
                    
                case 4://This line represents the fourth case, where the user selects to see the information for the fourth matchday.
                    
                     System.out.println("\nWhich type of result you want to know");
                     System.out.println("1) How many teams Lost a match?");
                     System.out.println("2) How many teams Won a match?");
                     System.out.println("3) How many teams Drawn a match?");
                     System.out.println("4) Back to the matchday selection menu\n");   
                     
                     // prompt the user to enter the number corresponding to their chosen option. The entered value is assigned to the option variable.
                     option = new AskUser().askUserforInt("please enter the number of the option");
                     
                        switch (option) {//switch statement based on the value of the option variable.
                            
                            case 1://This line represents the first case of the sub-menu, where the user pick to see hoe many teams lost  the fourth matchday.
                                
                             String myQuery =  "SELECT  Count( gr.gameResult), gr.gameResult FROM gameresults gr INNER JOIN games g ON gr.game = g.id_Games WHERE  gameDate > \"2022-09-20\" AND  gameDate < \"2022-09-24\" AND gameResult = \"Loss\" GROUP BY gameResult;" ;      
                             //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult = stmt.executeQuery(myQuery);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult ResultSet.
                             
                             System.out.println(b+"\nAmount of teams that"+blue+ " Lost the fourth matchday\n");

                             while (queryResult.next()) { //This line starts a loop that iterates through the rows of the queryResult ResultSet.
                                 
                              if (!alreadyPrint) {//checks if the header and lines have already been printed.
                                  for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }  
                                  
                                    //This line prints a formatted header row with column names.
                                    System.out.printf("\n|%-20s | %-20s|\n", "Amount Of Teams " , " Game Result ");
                                    
                                    for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }
                                    System.out.println(" ");  
                                    alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }      
                             //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                             System.out.printf("|%-20s | %-20s|\n", queryResult.getInt(1), queryResult.getString("gameResult"));
                
                            } 
                            
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option =5;;//change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break;//ending the execution for the case.
                             
                            case 2://This line represents the first case of the sub-menu, where the user pick to see hoe many teams lost  the fourth matchday.
                                
                             String myQuery2 =  "SELECT  Count( gr.gameResult), gr.gameResult FROM gameresults gr INNER JOIN games g ON gr.game = g.id_Games WHERE  gameDate > \"2022-09-20\" AND  gameDate < \"2022-09-24\" AND gameResult = \"Win\" GROUP BY gameResult;" ;      
                              //This line above defines the SQL query to retrieve the data from the database
                             
                             ResultSet queryResult2 = stmt.executeQuery(myQuery2);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult2 ResultSet.
                             
                             
                             System.out.println(b+"\nAmount of teams that"+blue+ " Won the fourth matchday\n");

                             while (queryResult2.next()) { //This line starts a loop that iterates through the rows of the queryResult ResultSet.     
                              if (!alreadyPrint) {
                                  for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    } 
                                  
                                    //This line prints a formatted header row with column names.
                                    System.out.printf("\n|%-20s | %-20s|\n", "Amount Of Teams " , " Game Result ");
                                    for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }System.out.println(" ");  
                                    alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }      
                
                             System.out.printf("|%-20s | %-20s|\n", queryResult2.getInt(1), queryResult2.getString("gameResult"));
                
                            } 
                           
                    
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option =5;
                             break;    
                            
                            case 3:
                                
                             String myQuery3 =  "SELECT  Count( gr.gameResult), gr.gameResult FROM gameresults gr INNER JOIN games g ON gr.game = g.id_Games WHERE  gameDate > \"2022-09-20\" AND  gameDate < \"2022-09-24\" AND gameResult = \"Draw\" GROUP BY gameResult;" ;      
                              //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult3 = stmt.executeQuery(myQuery3);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult3 ResultSet.
                             
                             
                             System.out.println(b+"\nAmount of teams that"+blue+ " Drawn the fourth matchday\n");
                             
                             while (queryResult3.next()) { //This line starts a loop that iterates through the rows of the queryResult ResultSet.    
                              if (!alreadyPrint) {
                                  for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }            
                                  
                                    //This line prints a formatted header row with column names.
                                    System.out.printf("\n|%-20s | %-20s|\n", "Amount Of Teams " , " Game Result ");
                                    
                                    for (int i = 0; i < 45; i++) {//starts a loop to print a line.
                                    System.out.print("-");
                                    }System.out.println(" ");  
                                    alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }  
                             //This line prints a formatted data row with values retrieved from the queryResult3 ResultSet.  
                             System.out.printf("|%-20s | %-20s|\n", queryResult3.getInt(1), queryResult3.getString("gameResult"));
                
                            } 
                            
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option =5;//change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break; //ending the execution for the case.
                             
                            case 4://This represents the fourth case, where the user can back to the matchday selection menu(previous menu).
                                
                                System.out.println(" ");
                                
                                break;//ending the execution for the case.
                                
                            default://default case, which is executed when none of the previous cases match the value of the option variable.
                                
                                 System.out.println("That was not a valid choice");
                        }                                           
                    
                    break;//ending the execution for the case.
                    
                    
                case 5:// fifth option case that allows the user Back to NFL DATA MENU.
                    System.out.println(" ");                   
                    break;//ending the execution for the case.
                    
                    default://default case, which is executed when none of the previous cases match the value of the option variable.
                    
                    System.out.println("That was not a valid choice");
                } 
                
                
            } while ((option !=5));//condition for the do-while loop to continue running.
            

        } catch (SQLException e) {
            System.out.println("SQL ERROR --->");//prints a message indicating an SQL error.
            System.out.println(e.getMessage());//prints the error message associated with the SQLException
            System.out.println(e.getSQLState());//prints the SQL state associated with the SQLException.
            
        } 
        catch (ClassNotFoundException e){
        
            System.out.println("Class error --" + e.getMessage()); // prints an error message indicating a class error.
    
}
        return null;
    
           
        
     }
     
     /**
     * Connect Java with the NFL-149 Database 
     * Include the query to get the List of players 
     * that had or have an injury opening a sub menu
     * which picking the *option 1)* open another sub-menu
     * Allow the user to pick between show all the players
     * that had or has an injury or search by introducing the name 
     * or back to the previous menu if they prefer
     * Picking the *option 2)* shows how many players 
     * have or had more than one injury.
     * *option 3)* Back to the principal menu.
     * @return data from MySQL database in a table.
     */       
     public String mySQLconnector5 () {
        
         Scanner myKB = new Scanner(System.in);
         int option;
         boolean alreadyPrint = false;
         boolean dataNotFound = false;
         String blue = "\033[34m";
         String red = "\033[31m";
         String b = "\u001B[0m";
         
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //this is the DB LOCATION //This is the DBNAME //USER //PASSWORD
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfl-149", "root", "root");
            System.out.println(" ");         
            System.out.println("Connected to DB... \n"); 
            
            Statement stmt = con.createStatement();
             // we use stmt to create a new statement 
             
            
            do {// starts a do-while loop, ensuring that the following code is executed at least once and then repeated till meet the condition.
                
                //sub-menu prompt asking the user to select which data should be shown.      
                System.out.println("\nplease select what information you want to see");
                System.out.println("1) Show all the players that had or has an injury ");
                System.out.println("2) Show me how many players have or had more than one injury ");
                System.out.println("3) Back to NFL DATA MENU\n");
                
                // prompt the user to enter the number corresponding to their chosen option. The entered value is assigned to the option variable.
                option = new AskUser().askUserforInt("please enter the number of the option");
                
                switch (option) {//switch statement based on the value of the option variable.
                    
                case 1://This line represents the first case, where the user selects to show the data of all the players that had or has an injury.
                 
                do { // starts a do-while loop to create a sub-menu inside the sub-menu, ensuring that the following code is executed at least once and then repeated till meet the condition.
                 
                //sub-menu prompt asking the user to select which data should be shown.       
                System.out.println("\nplease select what information you want to see");
                System.out.println("1) Show all the players that had or has an injury ");
                System.out.println("2) Search an specific name ");
                System.out.println("3) Back to previuos  menu\n");
                
                // prompt the user to enter the number corresponding to their chosen option. The entered value is assigned to the option variable.
                option = new AskUser().askUserforInt("please enter the number of the option");
                
                switch (option) {//switch statement based on the value of the option variable.
                    
                    case 1://This line represents the first case, where the user selects to show the data of all the players that had or has an injury.
                        
                    System.out.println(blue+"\nHere is the list of all the players that had or has an injury\n");
                    String myQuery =  "SELECT  p.*, t.teamName, t.city, injuryType, injuryDate FROM injuriesrecord ir INNER JOIN injuries i ON i.id_Injuries = ir.id_injury INNER JOIN player p  ON ir.id_player = p.id_Player INNER JOIN teams t ON t.id_Team = p.team GROUP BY p.id_Player, injuryDate, injuryType ORDER BY teamName ASC;" ;      
                     //This line above defines the SQL query to retrieve the data from the database.
                    
                    ResultSet queryResult = stmt.executeQuery(myQuery);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult ResultSet.

                    while (queryResult.next()) {  //This line starts a loop that iterates through the rows of the queryResult ResultSet.
                        
                        if (!alreadyPrint) { //checks if the header and lines have already been printed.                        
                        for (int i = 0; i < 189; i++) {//starts a loop to print a line.
                        System.out.print("-");
                        }
                        //This line prints a formatted header row with column names.
                        System.out.printf("\n|%-15s | %-15s | %-15s | %-20s | %-15s | %-15s | %-20s | %-15s | %-15s | %-15s|\n","Player ID: ", "Firstname: " , "Surname: " , "position" , "Skill Level:", "Team ID:", "teamName: ","city","injuryType","injuryDate");
                        
                        for (int i = 0; i < 189; i++) {//starts a loop to print a line.
                        System.out.print("-");
                        } 
                        System.out.println(" ");
                        alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                        }
                    //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                    System.out.printf("|%-15s | %-15s | %-15s | %-20s | %-15s | %-15s | %-20s | %-15s | %-15s | %-15s|\n", queryResult.getInt(1), queryResult.getString("firstName"),  queryResult.getString("lastName"),queryResult.getString("Position"), queryResult.getString("skill_Level"), queryResult.getInt("team"), queryResult.getString("teamName"), queryResult.getString("city"), queryResult.getString("injuryType"), queryResult.getString("injuryDate"));
                        
                                
                    }  
                    //prompts the user to press enter to go back to the principal menu.
                    System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                    myKB.nextLine();
                    
                    break;//ending the execution for the case.
                    
                     case 2://This line represents the first case, where the user can search for an specific name.
                    
                    //promt to user and read input method. 
                    String letter = new askuser.AskUser().askUserForText("\nIntroduce the surname or part of the surname\n");
                    String myQuery2 =  "SELECT  p.*, t.teamName, t.city, injuryType, injuryDate FROM injuriesrecord ir INNER JOIN injuries i ON i.id_Injuries = ir.id_injury INNER JOIN player p  ON ir.id_player = p.id_Player INNER JOIN teams t ON t.id_Team = p.team WHERE lastName LIKE  \'"+ letter + "%\' GROUP BY p.id_Player, injuryDate, injuryType ORDER BY teamName ASC;" ;      
                    //This line above defines the SQL query to retrieve the data from the database.
                    
                    ResultSet queryResult2 = stmt.executeQuery(myQuery2);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult2 ResultSet.
                    
                    while (queryResult2.next()) { //This line starts a loop that iterates through the rows of the queryResult ResultSet. 
                        
                        if (!alreadyPrint) {//checks if the header and lines have already been printed.  
                         System.out.println("\nHere is the result for : "+blue + letter);   
                         for (int i = 0; i < 189; i++) {//starts a loop to print a line.
                         System.out.print("-");
                         }      
                         //This line prints a formatted header row with column names.
                         System.out.printf("\n|%-15s | %-15s | %-15s | %-20s | %-15s | %-15s | %-20s | %-15s | %-15s | %-15s|\n","Player ID: ", "Firstname: " , "Surname: " , "position" , "Skill Level:", "Team ID:", "teamName: ","city","injuryType","injuryDate");
                         
                         for (int i = 0; i < 189; i++) {//starts a loop to print a line.
                         System.out.print("-");
                         } 
                         System.out.println(" ");
                         alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                        }
                       //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                       System.out.printf("|%-15s | %-15s | %-15s | %-20s | %-15s | %-15s | %-20s | %-15s | %-15s | %-15s|\n", queryResult2.getInt(1), queryResult2.getString("firstName"),  queryResult2.getString("lastName"),queryResult2.getString("Position"), queryResult2.getString("skill_Level"), queryResult2.getInt("team"), queryResult2.getString("teamName"), queryResult2.getString("city"), queryResult2.getString("injuryType"), queryResult2.getString("injuryDate"));
                       
                       dataNotFound = true;//sets the datafound flag to true to indicate that data rows have been found.
                     
                        } if (!dataNotFound) {//the condition checks if no data rows were found and print a message saying that no data found.
                            System.out.println("\ndata not found for : "+ blue + letter);
                            
                        }
                    
                     //prompts the user to press enter to go back to the principal menu.
                     System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                     myKB.nextLine();
                    
                     break;//ending the execution for the case.
                    
                     case 3:
                     System.out.println(" ");
                    
                    break;//ending the execution for the case.
                    
                    default://default case, which is executed when none of the previous cases match the value of the option variable.
                    System.out.println("That was not a valid choice");
                         
                }
                        
                    } while ((option != 1) && (option != 2) && (option != 3));//condition for the do-while loop to continue running.
                    
                     break;//ending the execution for the case.
                     
                case 2: //This line represents the second case for the first sub-menu, where the user selects to show the data of how many players have or had more than one injury .
                    
                    System.out.println("\nHere is the amount of players that had or have more than one injury\n");
                    String myQuery =  "SELECT  COUNT(ir.id_injury) AS Amount_InjuriesPlayers, p.*, t.teamName, t.city FROM injuriesrecord ir INNER JOIN injuries i ON i.id_Injuries = ir.id_injury INNER JOIN player p  ON ir.id_player = p.id_Player INNER JOIN teams t ON t.id_Team = p.team GROUP BY p.id_Player HAVING Amount_InjuriesPlayers > 1;" ;      
                    //This line above defines the SQL query to retrieve the data from the database.
                    
                    ResultSet queryResult = stmt.executeQuery(myQuery);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult2 ResultSet.                   

                    while (queryResult.next()) { //This line starts a loop that iterates through the rows of the queryResult ResultSet.                         
                        
                         if (!alreadyPrint) {//checks if the header and lines have already been printed. 
                         for (int i = 0; i < 159; i++) {//starts a loop to print a line.
                         System.out.print("-");
                         }
                         //This line prints a formatted header row with column names.
                         System.out.printf("\n|%-20s | %-15s | %-15s | %-10s | %-15s | %-15s | %-10s | %-20s | %-12s |\n","Amount of injuries","Player ID: ", "Firstname: " , "Surname: " , "position" , "Skill Level:", "Team ID:", "teamName: ","city");
                         
                         for (int i = 0; i < 159; i++) {//starts a loop to print a line.
                         System.out.print("-");
                         } System.out.println(" ");
                         
                         alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                        }
                        
                        System.out.printf("|%-20s | %-15s | %-15s | %-10s | %-15s | %-15s | %-10s | %-20s | %-12s |\n", queryResult.getInt(1), queryResult.getInt(2),queryResult.getString("firstName"),  queryResult.getString("lastName"),queryResult.getString("Position"), queryResult.getString("skill_Level"), queryResult.getInt("team"), queryResult.getString("teamName"), queryResult.getString("city"));
                        
                                
                    }  
                    //prompts the user to press enter to go back to the principal menu.
                    System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                    myKB.nextLine();
                    
                    break;//ending the execution for the case.
                    
                case 3://This line represents the third case for the first sub-menu, where the user selects to back to the principal menu.                  
                    
                    System.out.println(" ");                   
                    break;//ending the execution for the case.
                   
                    default://default case, which is executed when none of the previous cases match the value of the option variable.
                    System.out.println("That was not a valid choice");
                } 
                
                
            } while ((option !=3) && (option !=2) && (option !=1));//condition for the do-while loop to continue running.
            

        } catch (SQLException e) {
            System.out.println("SQL ERROR --->");//prints a message indicating an SQL error.
            System.out.println(e.getMessage());//prints the error message associated with the SQLException.
            System.out.println(e.getSQLState());//prints the SQL state associated with the SQLException.
            
        } 
        catch (ClassNotFoundException e){
        
            System.out.println("Class error --" + e.getMessage()); // prints an error message indicating a class error.
    
}
        return null;
    
        
    }  
 
     /**
     * Connect Java with the NFL-149 Database 
     * Include the query to get the Amount of players 
     * that have Low or Medium or High skill level by teams
     * let the user pick between see all the skill level together 
     * or one by one and select a particular order to see the data
     * @return data from MySQL database in a table.
     */      
     public String mySQLconnector6 () {
         
         //setting up the variables
         Scanner myKB = new Scanner(System.in);
         int option;
         boolean alreadyPrint = false;
         String blue = "\033[34m";
         String red = "\033[31m";
         String b = "\u001B[0m";
         
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //this is the DB LOCATION //This is the DBNAME //USER //PASSWORD
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfl-149", "root", "root");
            System.out.println(" ");         
            System.out.println("Connected to DB... \n"); 
            
            Statement stmt = con.createStatement();
             // we use stmt to create a new statement
            
                       
            do {// starts a do-while loop, ensuring that the following code is executed at least once and then repeated till meet the condition.
                
                //sub-menu prompt asking the user to select which data should be shown.  
                System.out.println("\nplease select the information that you want to know");
                System.out.println("1) Show information of teams related with players with all skill levels ");
                System.out.println("2) Show information of teams related with players with low skill level");
                System.out.println("3) Show information of teams related with players with medium skill level");
                System.out.println("4) Show information of teams related with players with high skill level");
                System.out.println("5) Back to NFL DATA MENU\n");
                
                // prompt the user to enter the number corresponding to their chosen option. The entered value is assigned to the option variable.
                option = new AskUser().askUserforInt("please enter the number of the option");
                
                switch (option) {//switch statement based on the value of the option variable.
                    
                case 1://This line represents the first case, where the user selects to show the information of teams related with players with all skill levels.
                                        
                     System.out.println("\nHow Do you whant to see the information?");
                     System.out.println("1) In Ascending order please");
                     System.out.println("2) In Descending order please");
                     System.out.println("3) Back to the type of information select menu");   
                     option = new AskUser().askUserforInt("please enter the number of the option");
                     
                        switch (option) {//switch statement based on the value of the option variable.
                            
                            case 1: //This line represents the first case, where the user selects to show the data in ascending order.
                                
                             String myQuery =  "SELECT COUNT(p.id_Player) AS Amount_Players,  p.skill_Level, p.team, t.teamName FROM player p INNER JOIN teams t ON t.id_Team = p.team GROUP BY team , skill_Level , teamName ORDER BY team ASC;" ;      
                               //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult = stmt.executeQuery(myQuery);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult ResultSet.

                             System.out.println(b+"\nHere is the teams information frelated with players  with "+blue+ " all the skill levels in ascending order");
                             
                             while (queryResult.next()) { //This line starts a loop that iterates through the rows of the queryResult ResultSet.
                                 
                                 if (!alreadyPrint) {//checks if the header and lines have already been printed.
                                     
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     }  
                                     //This line prints a formatted header row with column names.
                                     System.out.printf("\n|%-20s | %-20s | %-10s | %-22s|\n", "Amount Of Players: " , " Skill Level: ", "team ID: ", "Team Name");
                                     
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     }  
                                     System.out.println(" ");
                                     alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }
    
                             //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                             System.out.printf("|%-20s | %-20s | %-10s | %-22s|\n", queryResult.getInt(1), queryResult.getString("skill_Level"), queryResult.getInt("team"), queryResult.getString("teamName"));
                
                            } 
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();                            
                             option = 5; //change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break;//ending the execution for the case.
                             
                            case 2://This line represents the second case, where the user selects to show the data in descending order.
                                
                             String myQuery2 =  "SELECT COUNT(p.id_Player) AS Amount_Players,  p.skill_Level, p.team, t.teamName FROM player p INNER JOIN teams t ON t.id_Team = p.team GROUP BY team , skill_Level , teamName ORDER BY team DESC;" ;      
                             //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult2 = stmt.executeQuery(myQuery2);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult2 ResultSet.
                             
                            System.out.println(b+"\nHere is the teams information frelated with players  with "+blue+ " all the skill levels in ascending order");

                             
                             while (queryResult2.next()) { //This line starts a loop that iterates through the rows of the queryResult2 ResultSet.
                                 
                                 if (!alreadyPrint) {//checks if the header and lines have already been printed.
                                    for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     }     
                                      //This line prints a formatted header row with column names.
                                     System.out.printf("\n|%-20s | %-20s | %-10s | %-22s|\n", "Amount Of Players: " , " Skill Level: ", "team ID: ", "Team Name");
                                    
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     } 
                                     System.out.println(" ");
                                     alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }
    
                             //This line prints a formatted data row with values retrieved from the queryResult2 ResultSet.
                             System.out.printf("|%-20s | %-20s | %-10s | %-22s|\n", queryResult2.getInt(1), queryResult2.getString("skill_Level"), queryResult2.getInt("team"), queryResult2.getString("teamName"));
                
                            } 
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option = 5;//change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break; //ending the execution for the case.   
                            
                            case 3://This line represents the second case, where the user selects to go back to the  previus menu.                                
                                
                                System.out.println(" ");
                             
                             break;//ending the execution for the case.  
                                
                            default://default case, which is executed when none of the previous cases match the value of the option variable.
                                
                                 System.out.println("That was not a valid choice");
                        }                
                    
                    break;//ending the execution for the case.
                  
                case 2: //This line represents the first case, where the user selects to show the information of teams related with players with low skill levels.                   
                     
                     System.out.println("\nHow Do you whant to see the information?");
                     System.out.println("1) In Ascending order please");
                     System.out.println("2) In Descending order please");
                     System.out.println("3) Back to the type of information select menu");   
                     option = new AskUser().askUserforInt("please enter the number of the option");
                     
                        switch (option) {//switch statement based on the value of the option variable.
                            
                            case 1://This line represents the first case, where the user selects to show the data in ascending order.
                                
                             String myQuery =  "SELECT COUNT(p.id_Player) AS Amount_Players,  p.skill_Level, p.team, t.teamName FROM player p INNER JOIN teams t ON t.id_Team = p.team WHERE skill_Level Like \"Low\"  GROUP BY team , skill_Level , teamName ORDER BY team ASC;" ;      
                             //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult = stmt.executeQuery(myQuery);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult ResultSet.

                            System.out.println(b+"\nHere is the teams information frelated with players  with "+blue+ " low skill levels in ascending order");
                             
                             while (queryResult.next()) {//This line starts a loop that iterates through the rows of the queryResult ResultSet.
                                 
                                 if (!alreadyPrint) { //checks if the header and lines have already been printed.                                
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     }     
                                      //This line prints a formatted header row with column names.
                                     System.out.printf("\n|%-20s | %-20s | %-10s | %-22s|\n", "Amount Of Players: " , " Skill Level: ", "team ID: ", "Team Name");
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     }  
                                     System.out.println(" ");
                                     alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }
    
                             //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                             System.out.printf("|%-20s | %-20s | %-10s | %-22s|\n", queryResult.getInt(1), queryResult.getString("skill_Level"), queryResult.getInt("team"), queryResult.getString("teamName"));
                
                            } 
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option = 5;//change the value for the variable option to 5 to go directly to the principal nfl menu.
                             
                             break;//ending the execution for the case.
                             
                            case 2://This line represents the first case, where the user selects to show the data in descending order.
                                
                             String myQuery2 =  "SELECT COUNT(p.id_Player) AS Amount_Players,  p.skill_Level, p.team, t.teamName FROM player p INNER JOIN teams t ON t.id_Team = p.team WHERE skill_Level LIKE \"Low\" GROUP BY team , skill_Level , teamName ORDER BY team DESC;" ;      
                             //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult2 = stmt.executeQuery(myQuery2);

                            System.out.println(b+"\nHere is the teams information frelated with players  with "+blue+ " low skill levels in descending order");
                             
                             while (queryResult2.next()) { //This line starts a loop that iterates through the rows of the queryResult2 ResultSet.
                                 
                                 if (!alreadyPrint) {//checks if the header and lines have already been printed.    
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     }
                                     //This line prints a formatted header row with column names.
                                     System.out.printf("\n|%-20s | %-20s | %-10s | %-22s|\n", "Amount Of Players: " , " Skill Level: ", "team ID: ", "Team Name");
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     }  
                                     System.out.println(" ");
                                     alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }
    
                             //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                             System.out.printf("|%-20s | %-20s | %-10s | %-22s|\n", queryResult2.getInt(1), queryResult2.getString("skill_Level"), queryResult2.getInt("team"), queryResult2.getString("teamName"));
                
                            } 
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option=5;//change the value for the variable option to 5 to go directly to the principal nfl menu.                        
                             
                             break;//ending the execution for the case.    
                            
                            case 3://This line represents the third case, where the user back to the previus menu.
                                
                                System.out.println(" ");
                             
                             break;//ending the execution for the case.      
                                
                            default://default case, which is executed when none of the previous cases match the value of the option variable.
                                
                                 System.out.println("That was not a valid choice");
                        }
                                                              
                    
                    break;//ending the execution for the case.
                        
                case 3://This line represents the first case, where the user selects to show the information of teams related with players with medium skill levels. 
                     
                     //sub-menu prompt asking the user to select which data should be shown.
                     System.out.println("\nHow Do you whant to see the information?");
                     System.out.println("1) In Ascending order please");
                     System.out.println("2) In Descending order please");
                     System.out.println("3) Back to the type of information select menu");   
                     
                     // prompt the user to enter the number corresponding to their chosen option. The entered value is assigned to the option variable.
                     option = new AskUser().askUserforInt("please enter the number of the option");
                     
                        switch (option) {//switch statement based on the value of the option variable.
                            
                             case 1://This line represents the first case, where the user selects to show the data in ascending order.
                                
                             String myQuery =  "SELECT COUNT(p.id_Player) AS Amount_Players,  p.skill_Level, p.team, t.teamName FROM player p INNER JOIN teams t ON t.id_Team = p.team WHERE skill_Level LIKE \"Medium\" GROUP BY team , skill_Level , teamName ORDER BY team ASC;" ;      
                              //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult = stmt.executeQuery(myQuery);//This line starts a loop that iterates through the rows of the queryResult ResultSet.

                             System.out.println(b+"\nHere is the teams information frelated with players  with "+blue+ " medium skill levels in ascending order");
                             
                             while (queryResult.next()) { //This line starts a loop that iterates through the rows of the queryResult ResultSet. 
                                 
                                 if (!alreadyPrint) { //checks if the header and lines have already been printed.
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     }
                                     //This line prints a formatted header row with column names.
                                     System.out.printf("\n|%-20s | %-20s | %-10s | %-22s|\n", "Amount Of Players: " , " Skill Level: ", "team ID: ", "Team Name");
                                     
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     }  
                                     System.out.println(" ");
                                     alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }
    
                             //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                             System.out.printf("|%-20s | %-20s | %-10s | %-22s|\n", queryResult.getInt(1), queryResult.getString("skill_Level"), queryResult.getInt("team"), queryResult.getString("teamName"));
                
                            } 
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option = 5;//change the value for the variable option to 5 to go directly to the principal nfl menu.  
                             
                             break;//ending the execution for the case.
                             
                            case 2://This line represents the second case, where the user selects to show the data in descending order.
                                
                             String myQuery2 =  "SELECT COUNT(p.id_Player) AS Amount_Players,  p.skill_Level, p.team, t.teamName FROM player p INNER JOIN teams t ON t.id_Team = p.team WHERE skill_Level LIKE  \"Medium\" GROUP BY team , skill_Level , teamName ORDER BY team DESC;" ;      
                             //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult2 = stmt.executeQuery(myQuery2);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult ResultSet.

                             System.out.println(b+"\nHere is the teams information frelated with players  with "+blue+ " medium skill levels in descending order");
                             
                             while (queryResult2.next()) {  //This line starts a loop that iterates through the rows of the queryResult ResultSet.
                                 
                                 if (!alreadyPrint) {//checks if the header and lines have already been printed.
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     } 
                                     //This line prints a formatted header row with column names.
                                     System.out.printf("\n|%-20s | %-20s | %-10s | %-22s|\n", "Amount Of Players: " , " Skill Level: ", "team ID: ", "Team Name");
                                     
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     }  
                                     System.out.println(" ");
                                     alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }
    
                             //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                             System.out.printf("|%-20s | %-20s | %-10s | %-22s|\n", queryResult2.getInt(1), queryResult2.getString("skill_Level"), queryResult2.getInt("team"), queryResult2.getString("teamName"));
                
                            } 
                             
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option = 5;//change the value for the variable option to 5 to go directly to the principal nfl menu.                        
                             
                             break; //ending the execution for the case.    
                            
                            case 3://This line represents the third case, where the user back to the previus menu.
                                
                                System.out.println(" ");
                             
                             break; //ending the execution for the case.   
                                
                            default://default case, which is executed when none of the previous cases match the value of the option variable.
                                
                                 System.out.println("That was not a valid choice");
                        }
                        
                    
                    
                    break;//ending the execution for the case. 
                    
                case 4://This line represents the fourth case, where the user selects to show the information of teams related with players with high skill levels.                      
                    
                     //sub-menu prompt asking the user to select which data should be shown.                
                     System.out.println("\nHow Do you whant to see the information?");
                     System.out.println("1) In Ascending order please");
                     System.out.println("2) In Descending order please");
                     System.out.println("3) Back to the type of information select menu");   
                     option = new AskUser().askUserforInt("please enter the number of the option");
                     
                        switch (option) {//switch statement based on the value of the option variable.
                            
                             case 1://This line represents the first case, where the user selects to show the data in ascending order.
                                
                             String myQuery =  "SELECT COUNT(p.id_Player) AS Amount_Players,  p.skill_Level, p.team, t.teamName FROM player p INNER JOIN teams t ON t.id_Team = p.team WHERE p.skill_Level = \"High \" GROUP BY team , skill_Level , teamName ORDER BY team ASC;" ;      
                               //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult = stmt.executeQuery(myQuery);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult ResultSet.

                             System.out.println(b+"\nHere is the teams information frelated with players  with "+blue+ " high skill levels in ascending order");
                             
                             while (queryResult.next()) { //This line starts a loop that iterates through the rows of the queryResult ResultSet.
                                 
                                 if (!alreadyPrint) { //checks if the header and lines have already been printed.
                                     
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     }  
                                     //This line prints a formatted header row with column names.
                                     System.out.printf("\n|%-20s | %-20s | %-10s | %-22s|\n", "Amount Of Players: " , " Skill Level: ", "team ID: ", "Team Name");
                                     
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     } 
                                     System.out.println(" ");
                                     alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }
    
                             //This line prints a formatted data row with values retrieved from the queryResult ResultSet.
                             System.out.printf("|%-20s | %-20s | %-10s | %-22s|\n", queryResult.getInt(1), queryResult.getString("skill_Level"), queryResult.getInt("team"), queryResult.getString("teamName"));
                
                            } 
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option = 5;//change the value for the variable option to 5 to go directly to the principal nfl menu.                                                    
                             
                             break;//ending the execution for the case. 
                             
                            case 2://This line represents the second case, where the user selects to show the data in descending order.
                                
                             String myQuery2 =  "SELECT COUNT(p.id_Player) AS Amount_Players,  p.skill_Level, p.team, t.teamName FROM player p INNER JOIN teams t ON t.id_Team = p.team WHERE skill_Level = \"High \" GROUP BY team , skill_Level , teamName ORDER BY team DESC;" ;      
                             //This line above defines the SQL query to retrieve the data from the database.
                             
                             ResultSet queryResult2 = stmt.executeQuery(myQuery2);//executes the SQL query using the stmt statement object and stores the resulting data in the queryResult2 ResultSet.

                             System.out.println(b+"\nHere is the teams information frelated with players  with "+blue+ " high skill levels in descending order");
                             
                             while (queryResult2.next()) { //This line starts a loop that iterates through the rows of the queryResult ResultSet.
                                 
                                 if (!alreadyPrint) {//checks if the header and lines have already been printed.
                                     for (int i = 0; i < 83; i++) { //starts a loop to print a line.
                                     System.out.print("-");
                                     }                                      
                                      //This line prints a formatted header row with column names.
                                     System.out.printf("\n|%-20s | %-20s | %-10s | %-22s|\n", "Amount Of Players: " , " Skill Level: ", "team ID: ", "Team Name");
                                     
                                     for (int i = 0; i < 83; i++) {//starts a loop to print a line.
                                     System.out.print("-");
                                     } 
                                     System.out.println(" ");
                                     alreadyPrint = true;//sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                                 }
    
                             //This line prints a formatted data row with values retrieved from the queryResult2 ResultSet.
                             System.out.printf("|%-20s | %-20s | %-10s | %-22s|\n", queryResult2.getInt(1), queryResult2.getString("skill_Level"), queryResult2.getInt("team"), queryResult2.getString("teamName"));
                
                            } 
                             //prompts the user to press enter to go back to the principal menu.
                             System.out.println(b+ "\npress " + red + " enter " + b +" to back to the principal menu...");
                             myKB.nextLine();
                             option =5;//change the value for the variable option to 5 to go directly to the principal nfl menu.                                                   
                             
                             break;//ending the execution for the case.     
                            
                            case 3://This line represents the third case, where the user back to the previus menu.
                                
                                System.out.println(" ");
                             
                             break; //ending the execution for the case.    
                                
                            default://default case, which is executed when none of the previous cases match the value of the option variable.
                                
                                 System.out.println("That was not a valid choice");
                        }                        
                   
                    
                    break;//ending the execution for the case. 
                    
                case 5://This line represents the fifth case, where the user can go back to the  nfl data menu.
                    
                    System.out.println(" ");
                    
                    break;//ending the execution for the case. 
                    
                    default://default case, which is executed when none of the previous cases match the value of the option variable.
                        
                    System.out.println("That was not a valid choice");
                } 
                
                
            } while ( (option !=5));//condition for the do-while loop to continue running.
            

        } catch (SQLException e) {
            System.out.println("SQL ERROR --->");//prints a message indicating an SQL error.
            System.out.println(e.getMessage());//prints the error message associated with the SQLException
            System.out.println(e.getSQLState());//prints the SQL state associated with the SQLException.
            
        } 
        catch (ClassNotFoundException e){
        
            System.out.println("Class error --" + e.getMessage()); // prints an error message indicating a class error.
    
}
        return null;
    
           
        
     }
    
      /**
     * Connect Java with the NFL-149 Database 
     * Include the query to get the 
     * List of all players with a particular name
     * Ask the user to introduce the first name or part of 
     * Allows the user to enter just text as input
     * using the input to filter the data
     * @return data from MySQL database in a table.
     */  
     public String mySQLconnector7 () {
        
         Scanner myKB = new Scanner(System.in);
         boolean datafound = false;
         boolean alreadyPrint = false;
         String blue = "\033[34m";
         String red = "\033[31m";
         String b = "\u001B[0m";
         
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //this is the DB LOCATION //This is the DBNAME //USER //PASSWORD
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/nfl-149", "root", "root");
                    
            System.out.println("Connected to DB..."); 
 
            Statement stmt = con.createStatement(); // we use stmt to create a new statement 
            
            System.out.println("please introduce the firstname you wish to search for ");
            //promt to user and read input method.
            String letter = new askuser.AskUser().askUserForText("Introduce the name or part of  ");
            
            
            String myQuery =  "SELECT  p.position, CONCAT(firstName, ' ' , lastName) AS PlayerName, id_Player FROM player p WHERE firstName LIKE \'"+ letter + "%\' ORDER BY position;";      
            //SELECT  p.position, CONCAT(firstName, ' ' , lastName) AS PlayerName, id_Player FROM player p WHERE firstName LIKE "A%" ORDER BY position;
            ResultSet queryResult = stmt.executeQuery(myQuery);
            
             
            System.out.println(" \n");
            System.out.println(b+"List of players with the firstname or part of : "+blue + letter + ("\n"));
            
            
            while (queryResult.next()) { //This line initiates a loop that iterates over each row in the queryResult ResultSet object. 
                
                if (!alreadyPrint) { //This condition checks if the header section has already been printed. If it hasn't, it proceeds to print the header section.
                    
                 for (int i = 0; i < 70; i++) { // This loop prints a horizontal line of dashes to visually separate the header section from the data rows.
                         System.out.print("-");
                         }
                 //prints the row with column names in a formatted manner.
                 System.out.printf("\n|%-25s | %-25s | %-12s|\n","Position: ", "Player Name: " , " Player ID: ");  
                                 
                 for (int i = 0; i < 70; i++) { // This loop prints a horizontal line of dashes to visually separate the header section from the data rows.
                         System.out.print("-");
                         }  System.out.println(" ");
                         
                 alreadyPrint = true; //sets the alreadyPrint flag to true to indicate that the  section above has been printed.
                }
                //This line prints the data rows retrieved from the database in a formatted manner.
                System.out.printf("|%-25s | %-25s | %-12s| \n", queryResult.getString("position"), queryResult.getString("PlayerName"),  queryResult.getInt("id_Player"));
                
                datafound = true;//sets the datafound flag to true to indicate that data rows have been found.
            }  
            
            if (!datafound) {//the condition checks if no data rows were found and print a message saying that no data found.
                  System.out.println(b+ "data not found for :"+blue + letter);
                
            }
                    
                    System.out.println(" ");
                    System.out.println(b+ " press " + red + " enter " + b +" to back to the principal menu...");
                    myKB.nextLine();
            
        } catch (SQLException e) {
            System.out.println("SQL ERROR --->");//prints a message indicating an SQL error.
            System.out.println(e.getMessage()); //prints the error message associated with the SQLException.
            System.out.println(e.getSQLState());//prints the SQL state associated with the SQLException.
            
        } 
        catch (ClassNotFoundException e){
        
            System.out.println("Class error --" + e.getMessage());  // prints an error message indicating a class error.
    
}       
        return null;
        
    }


}
     
     
     




       
