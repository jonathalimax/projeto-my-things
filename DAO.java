/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.study.dao;

import br.study.modelo.Products;
import java.awt.Image;
import java.awt.List;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import javax.imageio.ImageIO;

//import java.sql.*;
/**
 *
 * @author jonathalima
 */
public class DAO {
    private static DBConnect dbCon = new DBConnect();

    public static String[] getSubCategory(int indexCat) {
        String subCategory[] = new String[8];
        String query;
        int i = 0;
       
        try {
            query = "SELECT * FROM Sub_category sc JOIN Category c ON sc.id_category = c.id WHERE sc.id_category = "+indexCat;
            dbCon.rs = dbCon.st.executeQuery(query);
            while(dbCon.rs.next()) {
                subCategory[i] = dbCon.rs.getString("nome");
                i++;
            }
        } catch (Exception e) {
            System.err.println("Err: "+e);
        }
        return subCategory;
    }
    
    public static String[] getSubCategory() {
        String[] subCategory = new String[50];
        subCategory[0] = "Escolha a subcategoria";
        int i = 1;
        
        try {
            dbCon.rs = dbCon.st.executeQuery("SELECT nome FROM Sub_category ORDER BY nome");
            while(dbCon.rs.next()) 
                subCategory[i++] = dbCon.rs.getString("nome");
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return subCategory;
    }
            
    
    public static void insertBD(Products products) throws FileNotFoundException, IOException {
        int id_category = 0, id_sub_category = 0;
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        RenderedImage image = null;
        ImageIO.write(image,"png", os); 
        InputStream fis = new ByteArrayInputStream(os.toByteArray());
        //FileInputStream in = new FileInputStream(products.getImage1());
        //Recuperando id da categoria
        try{
            String sql = "INSERT INTO Products (nome, description, price, purchased_year, id_category, id_sub_category, image1) "
                    + "VALUES (?,?,?,?,?,?,?)";

            dbCon.pst = dbCon.conn.prepareStatement(sql);
            dbCon.pst.setString(1, products.getNome());
            dbCon.pst.setString(2, products.getDescription());
            dbCon.pst.setDouble(3, products.getPrice());
            dbCon.pst.setString(4, products.getPurchasedYear());
            
            sql = "SELECT id FROM Category WHERE nome LIKE '"+products.getCategory()+"%'";
            dbCon.rs = dbCon.st.executeQuery(sql);
                while(dbCon.rs.next())
                    id_category = dbCon.rs.getInt(1);
            
            sql = "SELECT id FROM Sub_category WHERE nome LIKE '"+products.getSubCategory()+"%'";
            dbCon.rs = dbCon.st.executeQuery(sql);
            while(dbCon.rs.next())
                id_sub_category = dbCon.rs.getInt(1);
            
            dbCon.pst.setInt(5, id_category);
            dbCon.pst.setInt(6, id_sub_category);
            dbCon.pst.setBinaryStream(7,(InputStream) fis);
            dbCon.pst.executeUpdate();
            
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static List updateList(String sql) {
        List list = new List();
        try {
            dbCon.pst=dbCon.conn.prepareStatement(sql);
            dbCon.rs = dbCon.pst.executeQuery();
            
            while(dbCon.rs.next())
                list.add(dbCon.rs.getString("nome"));
            
        } catch (SQLException ex) {
            System.err.print(ex);
        }
        
        return list;
    }
    
    public static Products getData(String sql) throws FileNotFoundException, IOException {
        Products products = new Products();
        int i = 0;
        try {
            dbCon.rs = dbCon.st.executeQuery(sql);
            
            while(dbCon.rs.next()) {
                products.setNome(dbCon.rs.getString("nome"));
                products.setDescription(dbCon.rs.getString("description"));
                products.setPrice(dbCon.rs.getDouble("price"));
                String year = dbCon.rs.getString("purchased_year");
                products.setPurchasedYear(year.substring(0, 4));
                InputStream in = dbCon.rs.getBinaryStream("image1");
                Image img = ImageIO.read(in);
                products.setImage1(img);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return products;
    }
}
