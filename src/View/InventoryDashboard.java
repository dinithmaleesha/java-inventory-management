/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.DB;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Dinith Maleesha
 */
public class InventoryDashboard extends javax.swing.JFrame {

    /**
     * Creates new form InventoryDashboard
     */
    public InventoryDashboard() {
        initComponents();
        showProductTable();
        showIncomeTable();
        showOutgoingTable();
        showLowStock();
    }
    
    public void showProductTable(){
        try {
            //Product Table
            Connection conn = DB.createConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `product_id`, `Product_name`, `Product_price`, `Product_qt` FROM `products`");
            
            DefaultTableModel model = (DefaultTableModel) tbl_products.getModel();
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Price");
            model.addColumn("Quantity");
            
            JTableHeader header = tbl_products.getTableHeader();
            header.setFont(new Font("Arial", Font.BOLD, 14));
            header.setBackground(Color.WHITE);
            header.setForeground(Color.BLACK);
            header.setPreferredSize(new Dimension(header.getWidth(), 30));
            header.setResizingAllowed(false);
            header.setReorderingAllowed(false);
            
            while (rs.next()) {
                Object[] row = new Object[4];
                row[0] = rs.getInt("product_id");
                row[1] = rs.getString("Product_name");
                row[2] = rs.getInt("Product_price");
                row[3] = rs.getInt("Product_qt");
                model.addRow(row);
            }
            tbl_products.setEnabled(false);
            JScrollPane scrollPane = new JScrollPane(tbl_products);
            jScrollPane1.setViewportView(scrollPane);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Incoming stock table
    public void showIncomeTable(){
       try{
            Connection conn = DB.createConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT incoming_products.date, incoming_products.quantity, incoming_products.supplier, products.Product_name FROM incoming_products JOIN products ON incoming_products.product_id = products.product_id";
            ResultSet rst = stm.executeQuery(sql);
            
              DefaultTableModel incomingModel = (DefaultTableModel) tbl_incoming.getModel();
              incomingModel.addColumn("Product Name");
              incomingModel.addColumn("Supplier");
              incomingModel.addColumn("Quantity");
              incomingModel.addColumn("Date");
              
              
              JTableHeader header = tbl_incoming.getTableHeader();
              header.setFont(new Font("Arial", Font.BOLD, 14));
              header.setBackground(Color.WHITE);
              header.setForeground(Color.BLACK);
              header.setPreferredSize(new Dimension(header.getWidth(), 30));
              header.setResizingAllowed(false);
              header.setReorderingAllowed(false);
            
            while(rst.next()){
                Object[] row = new Object[4];
                row[0] = rst.getString("Product_name");
                row[1] = rst.getString("supplier");
                row[2] = rst.getString("quantity");
                row[3] = rst.getString("date");
                incomingModel.addRow(row);
            }
            //tbl_incoming.setCellEditor(null);
            tbl_incoming.setEnabled(false);
            JScrollPane incoming = new JScrollPane(tbl_incoming);
            incomingScrollPane.setViewportView(incoming);
            
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    // Outgoing Stock table
    public void showOutgoingTable(){
        try{
            Connection conn = DB.createConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT outgoing_products.date, outgoing_products.quantity, outgoing_products.customer, products.Product_name FROM outgoing_products JOIN products ON outgoing_products.product_id = products.product_id";
            ResultSet rst = stm.executeQuery(sql);
            
              DefaultTableModel incomingModel = (DefaultTableModel) tbl_outgoing.getModel();
              incomingModel.addColumn("Product Name");
              incomingModel.addColumn("Customer");
              incomingModel.addColumn("Quantity");
              incomingModel.addColumn("Date");
              
              
              JTableHeader header = tbl_outgoing.getTableHeader();
              header.setFont(new Font("Arial", Font.BOLD, 14));
              header.setBackground(Color.WHITE);
              header.setForeground(Color.BLACK);
              header.setPreferredSize(new Dimension(header.getWidth(), 30));
              header.setResizingAllowed(false);
              header.setReorderingAllowed(false);
            
            while(rst.next()){
                Object[] row = new Object[4];
                row[0] = rst.getString("Product_name");
                row[1] = rst.getString("customer");
                row[2] = rst.getString("quantity");
                row[3] = rst.getString("date");
                incomingModel.addRow(row);
            }
            //tbl_incoming.setCellEditor(null);
            tbl_outgoing.setEnabled(false);
            JScrollPane outgoing = new JScrollPane(tbl_outgoing);
            outgoingScrollPane.setViewportView(outgoing);
            
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void showLowStock(){
        try{
            Connection conn = DB.createConnection();
            Statement stm = conn.createStatement();
            String sql = "SELECT `product_id`, `Product_name`, `Product_price`, `Product_qt`, `minimum_qt` FROM `products` WHERE `Product_qt` < `minimum_qt`";
            ResultSet rst = stm.executeQuery(sql);
            
              DefaultTableModel lowstockModel = (DefaultTableModel) tbl_lowstock.getModel();
              lowstockModel.addColumn("Product ID");
              lowstockModel.addColumn("Product Name");
              lowstockModel.addColumn("Quantity");
              
              JTableHeader header = tbl_lowstock.getTableHeader();
              header.setFont(new Font("Arial", Font.BOLD, 14));
              header.setBackground(Color.WHITE);
              header.setForeground(Color.BLACK);
              header.setPreferredSize(new Dimension(header.getWidth(), 30));
              header.setResizingAllowed(false);
              header.setReorderingAllowed(false);
            
            while(rst.next()){
                Object[] row = new Object[4];
                row[0] = rst.getString("product_id");
                row[1] = rst.getString("Product_name");
                row[2] = rst.getString("Product_qt");
                lowstockModel.addRow(row);
            }
            //tbl_incoming.setCellEditor(null);
            tbl_lowstock.setEnabled(false);
            JScrollPane outgoing = new JScrollPane(tbl_lowstock);
            lowstockScrollPane.setViewportView(outgoing);
            
            conn.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Bg = new javax.swing.JPanel();
        SidePanel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btn_Management = new javax.swing.JButton();
        TopPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_products = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        incomingScrollPane = new javax.swing.JScrollPane();
        tbl_incoming = new javax.swing.JTable();
        outgoingScrollPane = new javax.swing.JScrollPane();
        tbl_outgoing = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        lowstockScrollPane = new javax.swing.JScrollPane();
        tbl_lowstock = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard");
        setLocation(new java.awt.Point(0, 0));
        setName("DashboardForm"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1280, 1000));
        setResizable(false);
        setSize(new java.awt.Dimension(1280, 1000));

        Bg.setBackground(new java.awt.Color(255, 255, 255));

        SidePanel.setBackground(new java.awt.Color(0, 150, 255));

        jPanel2.setBackground(new java.awt.Color(0, 66, 209));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Inventory\n");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        jButton2.setBackground(new java.awt.Color(0, 150, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Dashboard");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));

        jButton4.setBackground(new java.awt.Color(0, 150, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Report");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btn_Management.setBackground(new java.awt.Color(0, 150, 255));
        btn_Management.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btn_Management.setForeground(new java.awt.Color(255, 255, 255));
        btn_Management.setText("Management");
        btn_Management.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2));
        btn_Management.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ManagementActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SidePanelLayout = new javax.swing.GroupLayout(SidePanel);
        SidePanel.setLayout(SidePanelLayout);
        SidePanelLayout.setHorizontalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(SidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_Management, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        SidePanelLayout.setVerticalGroup(
            SidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SidePanelLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_Management, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        TopPanel.setBackground(new java.awt.Color(0, 150, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dashboard");

        javax.swing.GroupLayout TopPanelLayout = new javax.swing.GroupLayout(TopPanel);
        TopPanel.setLayout(TopPanelLayout);
        TopPanelLayout.setHorizontalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(902, Short.MAX_VALUE))
        );
        TopPanelLayout.setVerticalGroup(
            TopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TopPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        tbl_products.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tbl_products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbl_products);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Outgoing Stock");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Product Table");

        tbl_incoming.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbl_incoming.setGridColor(new java.awt.Color(255, 255, 255));
        incomingScrollPane.setViewportView(tbl_incoming);

        tbl_outgoing.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        outgoingScrollPane.setViewportView(tbl_outgoing);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Incoming Stock");

        tbl_lowstock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        lowstockScrollPane.setViewportView(tbl_lowstock);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Low Stock");

        javax.swing.GroupLayout BgLayout = new javax.swing.GroupLayout(Bg);
        Bg.setLayout(BgLayout);
        BgLayout.setHorizontalGroup(
            BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BgLayout.createSequentialGroup()
                .addComponent(SidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(BgLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(TopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BgLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(BgLayout.createSequentialGroup()
                                    .addGroup(BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel6)
                                        .addComponent(incomingScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                                        .addComponent(lowstockScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(outgoingScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel2)))
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(52, 52, 52))))
        );
        BgLayout.setVerticalGroup(
            BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(BgLayout.createSequentialGroup()
                .addComponent(TopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(BgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(outgoingScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                    .addComponent(incomingScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lowstockScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ManagementActionPerformed
        // TODO add your handling code here:
        InventoryManagement management = new InventoryManagement();
        management.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_ManagementActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        InventoryReport report = new InventoryReport();
        report.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InventoryDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventoryDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventoryDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventoryDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventoryDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Bg;
    private javax.swing.JPanel SidePanel;
    private javax.swing.JPanel TopPanel;
    private javax.swing.JButton btn_Management;
    private javax.swing.JScrollPane incomingScrollPane;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane lowstockScrollPane;
    private javax.swing.JScrollPane outgoingScrollPane;
    private javax.swing.JTable tbl_incoming;
    private javax.swing.JTable tbl_lowstock;
    private javax.swing.JTable tbl_outgoing;
    private javax.swing.JTable tbl_products;
    // End of variables declaration//GEN-END:variables
}
