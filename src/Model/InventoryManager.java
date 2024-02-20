/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Dinith Maleesha
 */
public class InventoryManager {
    InventoryItem item;
    
    public InventoryManager(InventoryItem item){
        this.item = item;
    }
    
    public void addItem(){
        try{
            Connection conn = DB.createConnection();
            String sql = "INSERT INTO `products`(`Product_name`, `Product_price`, `Product_qt`)"
                    + "VALUES ('"+item.getName()+"','"+item.getPrice()+"','"+item.getQt()+"')";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        }
        catch(SQLException ex){
            System.out.println("Error : "+ex.getLocalizedMessage());
        }
    }
}
