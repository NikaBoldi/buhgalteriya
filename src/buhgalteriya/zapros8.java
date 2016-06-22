package buhgalteriya;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.proteanit.sql.DbUtils;
public class zapros8 extends javax.swing.JFrame {
    public zapros8() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        buhgalteriyaPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("buhgalteriyaPU").createEntityManager();
        kategoriiDokhodovQuery = java.beans.Beans.isDesignTime() ? null : buhgalteriyaPUEntityManager.createQuery("SELECT k FROM KategoriiDokhodov k");
        kategoriiDokhodovList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : kategoriiDokhodovQuery.getResultList();
        podkategoriiRaskhodovQuery = java.beans.Beans.isDesignTime() ? null : buhgalteriyaPUEntityManager.createQuery("SELECT p FROM PodkategoriiRaskhodov p");
        podkategoriiRaskhodovList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : podkategoriiRaskhodovQuery.getResultList();
        kategoriiRaskhodovQuery = java.beans.Beans.isDesignTime() ? null : buhgalteriyaPUEntityManager.createQuery("SELECT k FROM KategoriiRaskhodov k");
        kategoriiRaskhodovList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : kategoriiRaskhodovQuery.getResultList();
        jButton1 = new javax.swing.JButton();
        year = new javax.swing.JComboBox();
        mounth = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Кількість грошей на певні категорії товарів");
        setFocusable(false);
        setResizable(false);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Назад");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, kategoriiRaskhodovList, jComboBox1);
        bindingGroup.addBinding(jComboBoxBinding);

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Виберіть категорію");

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
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mounth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        setSize(new java.awt.Dimension(718, 458));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       zapros z=new zapros();
       z.setVisible(true);
       dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
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
            String query ="SELECT s.NameCategory AS 'Назва категорії', SUM(r.summa) AS 'Сума' FROM kategorii_dokhodov s,dokhody r WHERE s.ID=(SELECT ID_category FROM podkategorii_dokhodov WHERE ID_subcategory=r.podkategoriy_dokhoda) AND Year(date)='"+rik+"' AND Month(Date)='"+Number+"' AND Day(Date)>=1 AND Day(Date)<=31 AND s.ID='"+namekat+"'GROUP BY s.NameCategory";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query); //while (rs.next()) {
             jTable1.setModel(DbUtils.resultSetToTableModel(rs));
             jTable1.setEnabled(false);   
        }catch (SQLException ex) {
            Logger.getLogger(zapros1.class.getName()).log(Level.SEVERE, null, ex);}
        
    }//GEN-LAST:event_jButton2ActionPerformed
    private void mounthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mounthActionPerformed
    }//GEN-LAST:event_mounthActionPerformed
    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
    }//GEN-LAST:event_jComboBox1ActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(zapros8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(zapros8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(zapros8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(zapros8.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new zapros8().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager buhgalteriyaPUEntityManager;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.util.List<buhgalteriya.KategoriiDokhodov> kategoriiDokhodovList;
    private javax.persistence.Query kategoriiDokhodovQuery;
    private java.util.List<buhgalteriya.KategoriiRaskhodov> kategoriiRaskhodovList;
    private javax.persistence.Query kategoriiRaskhodovQuery;
    private javax.swing.JComboBox mounth;
    private java.util.List<buhgalteriya.PodkategoriiRaskhodov> podkategoriiRaskhodovList;
    private javax.persistence.Query podkategoriiRaskhodovQuery;
    private javax.swing.JComboBox year;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
