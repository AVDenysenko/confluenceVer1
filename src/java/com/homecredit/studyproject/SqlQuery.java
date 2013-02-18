/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.homecredit.studyproject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Class can perform simple mysql select for any dinamic expression
 * Conformed especially for use in jsp
 * @author andrew
 */
public class SqlQuery {
    private Context env = null; 
    private DataSource pool = null;
    private Connection connection = null;
    private Statement stat = null;
    private ResultSet rs = null;

    public SqlQuery() {
        try {
            env = (Context) new InitialContext().lookup("java:comp/env");
            pool = (DataSource) env.lookup("jdbc/confluence");
            //if (pool == null) throw new ServletException("'confluence' is an uncnown DataSource");        
        } catch (NamingException ne) {            
        }
    }
    
    public ResultSet executeStatement(String sql) {        
        try {
            connection = pool.getConnection();
            stat = connection.createStatement();
            rs = stat.executeQuery(sql);            
        } catch (SQLException e) {            
        }
        return rs;
    }
    
    public void closeConnection() {
        try {
            if (connection != null) connection.close();
            if (stat != null) stat.close();
            if (rs != null) rs.close();
        } catch (SQLException se) {}
    }
}
