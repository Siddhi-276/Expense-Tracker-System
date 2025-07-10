package com.expensetracker;
import java.sql.*;
import com.expensetracker.Expense;     
import com.expensetracker.ExpenseDB; 

public class ExpenseDBManager {
    public void addExpense(Expense e) throws SQLException{
        String query="Insert into expenses(category,amount,date) values(?,?,?)";
        try(Connection c=ExpenseDB.getConnection()){
            PreparedStatement ps=c.prepareStatement(query);
            ps.setString(1,e.getCategory());
            ps.setDouble(2,e.getAmount());
            ps.setDate(3,e.getDate());
            ps.executeUpdate();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public double getTotalExpense() throws SQLException{
        String query="Select sum(amount) from expenses";
        try(Connection c=ExpenseDB.getConnection()){
            Statement st=c.createStatement();
            ResultSet rs=st.executeQuery(query);
                if(rs.next()){
                    return rs.getDouble(1);
                }
            rs.close();
            st.close();
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        return 0;
    }
    public void getAllExpenses() throws SQLException{
        try (Connection c=ExpenseDB.getConnection()) {
            String query="Select * from expenses order by date desc";
            Statement st=c.createStatement();
            ResultSet rs=st.executeQuery(query);
            System.out.println("\nID  Category  Amount  Date");
            while (rs.next()) {
                int id=rs.getInt("id");
                String category=rs.getString("category");
                double amount=rs.getDouble("amount");
                Date date=rs.getDate("date");
                System.out.println(id + "  " + category + "  " + amount + "  " + date);
            }
            rs.close();
            st.close();
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Expense getExpenseById(int id) throws SQLException{
        String query="Select * from expenses where id=?";
        Expense e=null;
        try(Connection c=ExpenseDB.getConnection()){
            PreparedStatement ps=c.prepareStatement(query);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
                e=new Expense(rs.getInt("id"),rs.getString("category"),rs.getDouble("amount"),rs.getDate("date"));
            rs.close();
            ps.close();
        }
        return e;
    }

}
