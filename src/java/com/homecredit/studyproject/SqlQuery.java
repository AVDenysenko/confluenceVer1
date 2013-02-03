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
import javax.servlet.ServletException;
import javax.sql.DataSource;

/**
 *
 * @author andrew
 */
public class SqlQuery implements SqlStatement{

    private Context env = null; 
    private DataSource pool = null;
    private Connection connection = null;
    private Statement stat = null;
    private ResultSet rs = null;

    public SqlQuery() throws ServletException {
        try {
            env = (Context) new InitialContext().lookup("java:comp/env");
            pool = (DataSource) env.lookup("jdbc/confluence");
            if (pool == null) throw new ServletException("'confluence' is an uncnown DataSource");        
        } catch (NamingException ne) {
            throw new ServletException(ne.getMessage());
        }
    }
    
    public ResultSet executeStatement(String sql) throws ServletException {        
        try {
            connection = pool.getConnection();
            stat = connection.createStatement();
            rs = stat.executeQuery(sql);            
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
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
