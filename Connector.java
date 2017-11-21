package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/////////////////////////////////////////////
///Author: Yoseph Hasson; GitHub: MDbrosev///
/////////////////////////////////////////////

public class Connector {
    
    //Variables for connection to database
    private Connection conn = null;  
    //com.mysql.jdbc.Driver enables java to interact with a database
    private String Driver = "com.mysql.jdbc.Driver";
    /*Create jdbc connection to database
                            jdbc:type of database://ip of database:port/database name*/
    private String DBurl = "jdbc:mysql://10.0.0.111:3306/db";
    //Username and password to database
    private String Username = "admin";
    private String Password = "password";
    private static Statement st = null;
    private static String availableSpaces = null;
    private static String parkingLotNum = null;
    
    public Connector() throws InterruptedException{

        try{
            //Load the jdbc driver
            Class.forName(Driver);
            //Connection to database
            conn = DriverManager.getConnection(DBurl, Username, Password);
            System.out.println("Database connected!");
            //Definition of object for executing our SQL
            st = conn.createStatement();
            // conn.close();
        }catch(Exception e){
            System.out.println("Error no connection!" + e);
            }
    }
    
    static void update(int pLotNum, int availSpaces){
        
      parkingLotNum = Integer.toString(pLotNum);
      availableSpaces = Integer.toString(availSpaces);
      
      try{
          //Variables that will inserted into database
          String timeStamp = new SimpleDateFormat("yyyy-MM-dd | HH:mm:ss").format(new Date());
          //Query for inserting variable values into database
          String insert = "INSERT INTO project (TimeStamp,ParkingLotNum,AvailableSpaces)"
                        + "VALUES('"+timeStamp+"','"+parkingLotNum+"','"+availableSpaces+"');";
          //Execute SQL statement to update with a string variable
          st.executeUpdate(insert);
          System.out.println("Update Success! \n");
        }catch(Exception e){
          System.out.println("Error no connection!" + e);
        }
    }
}
