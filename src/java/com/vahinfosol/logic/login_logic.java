/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vahinfosol.logic;

import com.vahinfosol.common.Common;
import com.vahinfosol.common.comman_members;
import com.vahinfosol.model.Database;
import java.io.PrintWriter;
import java.sql.*;

/**
 *
 * @author kiku
 */
public class login_logic implements comman_members{

    private PrintWriter out;
    private String password;

    @Override
    public void SetOut(PrintWriter out) {
        this.out = out;
    }

    public boolean LoginCh(String uname, String password) {
        Statement stm;
        Connection conn;

        conn = Database.conection(this.out);

        try {

            stm = conn.createStatement();
            String selquery = "select * from registration where user_name = " + "'" + uname + "' and password = '" + password + "'";
            ResultSet rs = stm.executeQuery(selquery);

            while (rs.next()) {
                
                if (rs.getRow()>=1) {
                    // username is correct
                    
                    int uid=rs.getInt("user_id");
                    
                    Common.setUid(uid);
                    
                    stm = conn.createStatement();
                    stm.executeUpdate("INSERT INTO login(`user_id`) VALUES ('" + uid + "')");
                    return true;                    
                } 
                else{
                    //invalid data
                                        
                    return false;
                }
            }
            stm.close();
            conn.close();
            
        } catch (SQLException se) {
            out.println(se.getMessage() + "Errorlogin");
        }
        return false;
    }
}
