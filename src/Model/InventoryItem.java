/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Dinith Maleesha
 */
public class InventoryItem {
    private String productName;
    private int productPrice, productQt;
    
    public InventoryItem(String productName, int productPrice, int productQt){
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQt = productQt;
    }
    public String getName(){
        return productName;
    }
    public int getPrice(){
        return productPrice;
    }
    public int getQt(){
        return productQt;
    }
    
}
