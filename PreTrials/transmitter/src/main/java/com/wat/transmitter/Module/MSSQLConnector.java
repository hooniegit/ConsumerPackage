package com.wat.transmitter.Module;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MSSQLConnector {
    private final String JDBC_URL;
    private final String JDBC_USER;
    private final String JDBC_PASSWORD;
    
    // [Initialize]
    public MSSQLConnector(String URL, String DB, String USER, String PW) {
    	this.JDBC_URL = "jdbc:sqlserver://" + URL + ";databaseName=" + DB;
    	this.JDBC_USER = USER;
    	this.JDBC_PASSWORD = PW;
    }

    // [Connect] Defined SQL Server & Database
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    // [Fetch] ResultSet of Inserted Query
    public ResultSet fetchResult(String QUERY) throws SQLException {
       Connection connection = getConnection();
       Statement statement = connection.createStatement();
       
       // [Return] ResultSet
       return statement.executeQuery(QUERY);
    }

    // [Execute] Inserted Query 
    public int insertData(String QUERY) throws SQLException {
    	Connection connection = getConnection();
        Statement statement = connection.createStatement();
        
        // [Return] Count of Affected Rows
        return statement.executeUpdate(QUERY);
    }
}
