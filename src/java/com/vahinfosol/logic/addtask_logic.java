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
public class addtask_logic implements comman_members {

    private PrintWriter out;

    @Override
    public void SetOut(PrintWriter out) {
        this.out = out;
    }

    public String DBEntry(String task_name, String task_type, String frequency, String target_date, String deadline, String task_details) {
        Statement stm;
        Connection conn;

        conn = Database.conection(this.out);
        try {
            stm = conn.createStatement();

            int pid = 0;
            stm.executeUpdate("INSERT INTO task_creater_td(`pi_name`,`pi_type`,`frequencey`,`pi_details`,`pi_creted_id`)VALUES('" + task_name + "','" + task_type + "','" + frequency + "','" + task_details + "','" + Common.getUid() + "')",Statement.RETURN_GENERATED_KEYS);
            int uid= Common.getUid();            
            
            ResultSet generatedKeys = stm.getGeneratedKeys();
            if (generatedKeys.next()) {
                pid = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
            stm.executeUpdate("INSERT INTO `target_master_tb`(`user_id`, `pi_id`, `dead_line`, `date_of_complition`) VALUES(" + uid + "," + pid + ",'" + target_date + "','" + deadline + "')");
            
            /*
            //forieng key
            ResultSet rs = stm.executeQuery("select pi_id from task_creater_td where pi_name = " + "'" + task_name + "'");
            while (rs.next()) {
                int pid = rs.getInt("pi_id");
                stm = conn.createStatement();
                int uid= Common.getUid();
                stm.executeUpdate("INSERT INTO `target_master_tb`(`user_id`, `pi_id`, `dead_line`, `date_of_complition`) VALUES(" + uid + "," + pid + ",'" + target_date + "','" + deadline + "')");
                return pid;
            }*/
            
            
            stm.close();
            conn.close();
            return "all done";

        } catch (Exception e) {
            return e.getMessage();
            //System.out.println(e.getMessage());
        }
        //return "Opps ! Something went wrong";
    }
}
