/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vahinfosol.model;

import java.io.PrintWriter;
import java.sql.*;

/**
 *
 * @author Rizwan
 */
public class Database {

  

       private static final String Url = "jdbc:mysql://localhost:3306/ana_bot_db?zeroDateTimeBehavior=convertToNull";
       private static final String UserName = "root";
       private static final String Pass = "";
       private static Connection mysqlCon = null;

      public static Connection conection(PrintWriter out) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Database.mysqlCon = DriverManager.getConnection(Database.Url, Database.UserName, Database.Pass);
        } catch (ClassNotFoundException cnfe) {
            out.println(cnfe + "error");
        } catch (Exception ex) {
            out.println(ex.getMessage());
        }
        return Database.mysqlCon;
    }
}
