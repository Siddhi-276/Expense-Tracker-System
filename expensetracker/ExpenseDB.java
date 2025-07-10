package com.expensetracker;
import java.sql.*;

public class ExpenseDB {

    public static Connection getConnection() throws SQLException {
        try{
            Class.forName("org.postgresql.Driver"); 
        }
        catch(ClassNotFoundException e){
            System.out.println("PostgreSQL JDBC driver not found.");
        }
        return DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/expensetracker",
            "postgres",
            "siddhi"
        );
    }

    public static void createTable(String table, String query) {
        try (Connection c=getConnection()){
            Statement st=c.createStatement();
            st.executeUpdate(query);
            System.out.println(table + " created");
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        String tbl="expenses";
        String query="CREATE TABLE IF NOT EXISTS " + tbl + " (" + " id serial primary key," + " category varchar(50)," + " amount decimal(10,2)," + " date date default current_date" + ")";
        ExpenseDB.createTable(tbl, query);
    }
}