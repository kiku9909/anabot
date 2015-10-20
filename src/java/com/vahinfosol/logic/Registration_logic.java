/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vahinfosol.logic;

/**
 *
 * @author Rizwan
 */
import com.vahinfosol.common.Common;
import com.vahinfosol.common.comman_members;
import com.vahinfosol.model.Database;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Registration_logic implements comman_members {

    private PrintWriter out;

    @Override
    public void SetOut(PrintWriter out) {
        this.out = out;
    }

    public boolean DBEntry(String fname, String sname, String uname, String password, String email, String phone, String address, String dob, int gender, String Utype) {
        Statement stm;
        Connection conn;
        int uid;
        conn = Database.conection(this.out);
        try {
            stm = conn.createStatement();
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String query = "INSERT INTO registration(`first_name`,`last_name`,`user_name`,`password`,`email_id`,`phone_num`,`address`,`date_of_birth`,`gender`,`registration_status`,`user_type`)VALUES ('" + fname + "','" + sname + "','" + uname + "','" + password + "','" + email + "','" + phone + "','" + address + "','" + Date.valueOf(dob) + "','" + gender + "','" + 1 + "','" + Utype + "')";
            stm.executeUpdate(query);

            //forieng key
            String selquery = "select user_id from registration where user_name = " + "'" + uname + "'";
            ResultSet rs = stm.executeQuery(selquery);
            while (rs.next()) {
                uid = rs.getInt("user_id");
                Common.setUid(uid);
                stm = conn.createStatement();
                stm.executeUpdate("INSERT INTO login(`user_id`) VALUES ('" + uid + "')");
            }
            stm.close();
            conn.close();
            return true;
        } catch (SQLException se) {
            out.println(se.getMessage() + "ErrorInsert");
        }
        return false;
    }
}
