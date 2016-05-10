
package br.study.dao;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author jonathalima
 */
public class DBConnect {
    public Connection conn;
    public Statement st;
    public ResultSet rs;
    protected PreparedStatement pst;
    
    public DBConnect() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MyThings?autoReconnect=true"
                    + "&useSSL=false", "root", "jonh");
            st = conn.createStatement();
        }catch(Exception ex) {
            System.out.println("Error: "+ex);
        }
    }
    public void getData() {
        try {
            String query = "SELECT * FROM Category";
            rs = st.executeQuery(query);
            System.out.println("Records:");
            while(rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("nome");
                System.out.println("Id: "+id+" | Name: "+name);
            }
        } catch (Exception e) {
            System.out.print("Error: "+e);
        }
    }
}
