/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package software.development.ca.pkg2023149;

import askuser.AskUser;
import inpututilities.mySQL;
import java.io.FileNotFoundException;

/**
 *
 * @author julio C. Gallardo Quintero.
 */
public class SoftwareDevelopmentCA2023149 {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
/**Java program that connects the nfl-149 Database from MySQL to java.
 * console menu system to allow the user to carry out a selection of the SQL
 * queries and with an option to exit from the menu, the menu system interact with 
 * the user and allows modify the seven queries to get different results.
 * 
 */

       String red = "\033[31m";
       String blue = "\033[34m";
       int option;
       
       
        do{// starts a do-while loop, ensuring that the following code is executed at least once and then repeated till meet the condition.
               
               new mySQL().MyArt();               
               System.out.println(blue+"OPTIONS OF THE NFL DATABASE MENU ");
               System.out.println("1) List all captains ID's with their first name, last name and Team , in a particular order");
               System.out.println("2) List all players with a particular name");
               System.out.println("3) List all players that lost in a particular  matchday ");
               System.out.println("4) Amount of teams that 'Win' OR 'Loss' OR 'Draw' in a particular matchday");
               System.out.println("5) List of players that had or have an injury ");
               System.out.println("6) Amount of players that have Low or Medium or High skill level by teams");
               System.out.println("7) List of all players and their positions with a name filter in a particular order");
               System.out.println(red+"8) Exit");
               
            // prompt the user to enter the number corresponding to their chosen option. The entered value is assigned to the option variable.
            option = new AskUser().askUserforInt("which option from the nfl data base do you want to know?");
               
               // now the action depend on the option picked by user
               
               switch (option){
                   
                   case 1: //first case that show the sub-menu for the list of all captains ID's with their first name, last name and Team , in a particular order.
                       
                    new mySQL().mySQLconnector();             
                      break;
                      
                   case 2: //second case that show the sub-menu for the list of all players with a particular name.                       
                       
                    new mySQL().mySQLconnector2();
                      break;
                      
                   case 3: //third case that show the sub-menu for the list of all players that lost in a particular  matchday.  
                       
                    new mySQL().mySQLconnector3();                       
                       break;
                       
                   case 4://fourth case that show the sub-menu for the Amount of teams that 'Win' OR 'Loss' OR 'Draw' in a particular matchday. 
                       
                    new mySQL().mySQLconnector4(); 
                       break;
                       
                   case 5://fifth case that show the sub-menu for the list of players that had or have an injury.
                       
                    new mySQL().mySQLconnector5();
                       break; 
                       
                   case 6://sixth case that show the sub-menu for the amount of players that have Low or Medium or High skill level by teams.
                       
                    new mySQL().mySQLconnector6();
                       break; 
                       
                   case 7://seventh case that show the sub-menu for the  list of all players and their positions with a name filter in a particular order.
                       
                    new mySQL().mySQLconnector7();
                       break; 
                       
                   case 8://eigth case. close the program.
                       
                    new mySQL().MyArtEnd();
                       break;       
                          
                   default://default case, which is executed when none of the previous cases match the value of the option variable.
                       
                       System.out.println("That was not a valid choice!");
               }
           }while (option != 8);//condition for the do-while loop to continue running.
    }
    
}
