/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vahinfosol.common;

/**
 *
 * @author Rizwan
 */
public class Common {
    private static int uid;

    public static void setUid(int uid) {
        Common.uid = uid;
    }
   
    public static int getUid() {
        return uid;
    }
    
    
}
