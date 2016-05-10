/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.study.modelo;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jonathalima
 */
public class Products {
    private String nome;
    private String description;
    private double price;
    private String Category;
    private String subCategory;
    private String purchasedYear;
    private Image image1;
    //private byte[] image1;
    //private byte[] image2;
    //private byte[] image3;
    //private byte[] image4;
    //private int i = 0;
    
    public Products() {
        this.nome = null;
        this.description = null;
        this.price = 0.0;
        this.Category = null;
        this.subCategory = null;
        this.purchasedYear = null;
    }
    
    public Products(String nome, String description, double price, String category, String subCategory, String purchasedYear) {
        this.nome = nome;
        this.description = description;
        this.price = price;
        this.Category = category;
        this.subCategory = subCategory;
        this.purchasedYear = purchasedYear;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getPurchasedYear() {
        return purchasedYear;
    }

    public void setPurchasedYear(String purchasedYear) {
        this.purchasedYear = purchasedYear;
    }

    public Image getImage1() {
        return image1;
    }

    public void setImage1(Image img) {
        this.image1 = img;
    }
    
    public Boolean isEmpty() {        
        return (nome.equals("") && description.equals("") && price == 0 && 
                Category.equals("") && subCategory.equals("") && purchasedYear.equals(""));
    }
}
