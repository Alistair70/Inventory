/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javadbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 *
 * @author Alistair
 */
public class conn 
{
    public static Connection getConnection() throws Exception{
        try
        {
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/javadatabase";
            String username = "root";
            String password = "root";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url,username,password);
            System.out.println("Connected");
            return conn;
        } catch(Exception e){System.out.println(e);}
    return null;
    }
    
    public static void executeQuery(String q)
    {
        try
        {
            Connection con = conn.getConnection();
            PreparedStatement query = con.prepareStatement(q);
            query.executeUpdate();
        }catch(Exception e){System.out.println(e);}
    finally{System.out.println("Query execution complete");}
    }  
    
    public static String[][]  getInv(String q1, int rows)
    {
        
        String[][] inv = new String[rows][3];
        
        int i = 0;
        
        try
        {
            Connection con = conn.getConnection();
            PreparedStatement query = con.prepareStatement(q1);
            ResultSet res = query.executeQuery();
            
            while(res.next())
            {
                
                inv[i][0] = res.getString("prodName");
                inv[i][1] = Integer.toString(res.getInt("prodAmt"));
                inv[i][2] = Double.toString(res.getDouble("prodPrice"));
                
                i++;
                
            }
            
           // System.out.print(inv[0][0]);

                        
        }catch(Exception e){System.out.println(e);}
    finally{System.out.println("Full Inventory Rquest Completed");}
    return inv;
    }
    
    public static int count(String q)
    {
    int rows = 0; 
    
        {
        try{
            Connection con = conn.getConnection();
            
            Statement stmt = con.createStatement();
               
            ResultSet rs = stmt.executeQuery(q);
            rs.next();
            rows = rs.getInt(1);
            System.out.println("rows1: "+ rows);
        }catch(Exception e){System.out.println(e);}
        finally{System.out.println("Count Rows works");}
        }  
    return rows;
    }
    
    public static String[] getProd()
    {
        String q = "";
        int rows = count("Select count(*) from inventory");
        String[] prod = new String[rows];
        int i = 0;
        int j = 0;
        try
        {
            Connection con = conn.getConnection();
            PreparedStatement query = con.prepareStatement("Select * from inventory");
            ResultSet res = query.executeQuery();
            
            while(res.next())
            {
                prod[i] = res.getString("prodName"); 
                i++;
                
            }
           // System.out.print(inv[0][0]);

                        
        }catch(Exception e){System.out.println(e);}
        return prod;
    }
    
    public static String[] getCols()
    {   
        int colCount = 0;
        ResultSetMetaData md = null;
        try
        {
            Connection con = conn.getConnection();
            PreparedStatement query = con.prepareStatement("Select * from inventory");
            ResultSet res = query.executeQuery();
            md = res.getMetaData();
            colCount = md.getColumnCount();
            System.out.println("Col count: " + colCount);
      
        }catch(Exception e){System.out.println(e);}
         
        String[] cols = new String[colCount];
       
        for(int i = 0; i < colCount; i++)
        {
            try
            {  
            cols[i] = md.getColumnName(i+1);
            System.out.println(cols[i]);
            System.out.println(i);
        
            }catch(Exception e){System.out.println(e);}
        }    
        return cols;
    }    
}
