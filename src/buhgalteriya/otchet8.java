package buhgalteriya;
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
import net.proteanit.sql.DbUtils;
public class otchet8 extends javax.swing.JFrame {
    public otchet8() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        buhgalteriyaPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("buhgalteriyaPU").createEntityManager();
        kategoriiRaskhodovQuery = java.beans.Beans.isDesignTime() ? null : buhgalteriyaPUEntityManager.createQuery("SELECT k FROM KategoriiRaskhodov k");
        kategoriiRaskhodovList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : kategoriiRaskhodovQuery.getResultList();
        year = new javax.swing.JComboBox();
        mounth = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        setTitle("Кількість грошей на певні категорії товарів");
        setFocusable(false);
        setResizable(false);

        year.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        year.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028" }));

        mounth.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        mounth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Січень", "Лютий", "Березень", "Квітень", "Травнь", "Червень", "Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень" }));
        mounth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mounthActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.setRowHeight(20);
        jScrollPane1.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Показати");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Рік");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Місяць");

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Друкувати");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Виберіть категорію");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, kategoriiRaskhodovList, jComboBox1);
        bindingGroup.addBinding(jComboBoxBinding);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 676, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(mounth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(mounth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        setSize(new java.awt.Dimension(718, 438));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Object Number=mounth.getSelectedIndex()+1;//pole mounth
        String years=year.getSelectedItem().toString();
        int namekat=jComboBox1.getSelectedIndex();
        int rik = Integer.parseInt(years);//pole rik
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
            Statement stmt = null;
            String query ="SELECT s.NameCategory AS 'Назва категорії', SUM(r.summa) AS 'Сума' FROM kategorii_dokhodov s,\n" +
"dokhody r \n" +
"WHERE s.ID=(SELECT ID_category FROM podkategorii_dokhodov WHERE ID_subcategory=r.podkategoriy_dokhoda) AND Year(date)='"+rik+"' AND Month(Date)='"+Number+"' AND Day(Date)>=1 AND Day(Date)<=31 AND s.ID='"+namekat+"'GROUP BY s.NameCategory";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
             jTable1.setEnabled(false);
        }catch (SQLException ex) {
            Logger.getLogger(zapros1.class.getName()).log(Level.SEVERE, null, ex);}    
    }//GEN-LAST:event_jButton2ActionPerformed
    private void mounthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mounthActionPerformed
    }//GEN-LAST:event_mounthActionPerformed
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
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
    }//GEN-LAST:event_jButton3ActionPerformed
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new otchet8().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager buhgalteriyaPUEntityManager;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.util.List<buhgalteriya.KategoriiRaskhodov> kategoriiRaskhodovList;
    private javax.persistence.Query kategoriiRaskhodovQuery;
    private javax.swing.JComboBox mounth;
    private javax.swing.JComboBox year;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
