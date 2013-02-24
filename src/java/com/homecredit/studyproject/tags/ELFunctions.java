/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homecredit.studyproject.tags;

import com.homecredit.studyproject.SqlQuery;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author andrew
 */
public class ELFunctions {
   public static String getPageTextByTitle(String title) {
        String text = null;
        SqlQuery sqText = new SqlQuery();
        ResultSet rsText = sqText.executeStatement("SELECT * FROM posted_text WHERE name = '" + title +"'");
        try {
            while (rsText.next()) {
                text = rsText.getString("text");
            }
        } catch(SQLException e) {            
        }
        return text;
   } 
}
