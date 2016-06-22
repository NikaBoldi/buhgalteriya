package buhgalteriya;
import java.awt.Component;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;
public class otchet1 extends javax.swing.JFrame {
    public otchet1() {
        initComponents();
        dataLoad();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Обновити");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(20);
        jScrollPane2.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Друкувати звіт");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(0, 10, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       dataLoad();
    }//GEN-LAST:event_jButton1ActionPerformed
    public void dataLoad()
    {
        try {
            Properties p=new Properties();
            p.setProperty("user", "root");
            p.setProperty("password", "");
            p.setProperty("useUnicode", "true"); 
            p.setProperty("useUnicode","true");
            p.setProperty("characterEncoding","cp1251");
            String userName="root",password="",charSet="Cp1251";
            Connection con;
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dom_buhgal",p);
            System.out.println("Connected to database");
            Statement stmt = null; String query ="SELECT a AS 'Название',b AS 'Залишок на рахунку',tip.Name AS 'Тип валюти' FROM(SELECT Name AS a,suma AS b, tipvalut AS c FROM schet) AS t, tip_valyuty AS tip WHERE tip.ID=c";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
             jTable1.setEnabled(false);
        }catch (SQLException ex) {
            Logger.getLogger(zapros1.class.getName()).log(Level.SEVERE, null, ex);}
    }  
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       Date dNow = new Date( );
       SimpleDateFormat ft = 
       new SimpleDateFormat ("E yyyy.MM.dd \t\t\t HH:mm:ss ");
       String date=ft.format(dNow);
       MessageFormat foother=new MessageFormat(date);
        MessageFormat header = new MessageFormat("Page {0,number,integer}"); 
        try{
        boolean complete = jTable1.print(null, header, foother);
        if (complete) {
        System.out.println("success");
    } else {
       System.out.println("Printing was cancelled");
    }
        }catch(PrinterException pe){
        System.out.println("Error!!!");
        }
    }//GEN-LAST:event_jButton2ActionPerformed
 public static void main(String args[]) {
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new otchet1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
