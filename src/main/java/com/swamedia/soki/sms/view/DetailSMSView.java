/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swamedia.soki.sms.view;

import java.math.BigDecimal;
import java.sql.SQLException;
import com.swamedia.soki.sms.model.TAlert;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author scatt labs
 */
public class DetailSMSView extends javax.swing.JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates new form DetailUnSentView
	 */
	public DetailSMSView(java.awt.Frame parent, boolean modal, TAlert alert) {
		super(parent, modal);
		initComponents();
		try {
			init(alert);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void init(TAlert alert) throws SQLException {
		textOperatorTujuan.setText(alert.getOperatorTujuan());
		textPesan.setText(alert.getPesan());
		textStatus.setText(alert.getStatus().compareTo(new BigDecimal(1)) == 0 ? "Terkirim" : "Belum Terkirim");
		textTitikAlert.setText(alert.getTitikAlert());
		textTujuan.setText(alert.getTujuan());
		textTanggal.setText(alert.getTanggal());
		textTanggalKirim.setText(alert.getTanggalKirim());
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		textPesan = new javax.swing.JTextArea();
		textOperatorTujuan = new javax.swing.JTextField();
		textTujuan = new javax.swing.JTextField();
		textTitikAlert = new javax.swing.JTextField();
		textTanggal = new javax.swing.JTextField();
		textTanggalKirim = new javax.swing.JTextField();
		textStatus = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("View Detail SMS");
		setResizable(false);

		jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		jLabel1.setText("Operator Tujuan");

		jLabel2.setText("Tujuan");

		jLabel3.setText("Titik Alert");

		jLabel4.setText("Pesan");

		jLabel5.setText("Tanggal");

		jLabel6.setText("Tanggal Kirim");

		jLabel7.setText("Status");

		textPesan.setEditable(false);
		textPesan.setColumns(20);
		textPesan.setRows(5);
		jScrollPane1.setViewportView(textPesan);

		textOperatorTujuan.setEditable(false);

		textTujuan.setEditable(false);

		textTitikAlert.setEditable(false);

		textTanggal.setEditable(false);

		textTanggalKirim.setEditable(false);

		textStatus.setEditable(false);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel1)
								.addComponent(jLabel2).addComponent(jLabel3).addComponent(jLabel4).addComponent(jLabel5)
								.addComponent(jLabel6).addComponent(jLabel7))
						.addGap(18)
						.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
								.addComponent(textOperatorTujuan, 302,
										302, 302)
								.addComponent(textTitikAlert, 302, 302, 302)
								.addGroup(jPanel2Layout.createSequentialGroup().addGroup(jPanel2Layout
										.createParallelGroup(Alignment.LEADING)
										.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(textTanggal, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
														146, Short.MAX_VALUE)
												.addComponent(textTanggalKirim, Alignment.LEADING)
												.addComponent(textStatus, Alignment.LEADING, GroupLayout.PREFERRED_SIZE,
														107, GroupLayout.PREFERRED_SIZE))
										.addComponent(textTujuan, GroupLayout.PREFERRED_SIZE, 100,
												GroupLayout.PREFERRED_SIZE))
										.addGap(0, 156, Short.MAX_VALUE))
								.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout
				.createSequentialGroup().addContainerGap()
				.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(
						textOperatorTujuan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel2).addComponent(
						textTujuan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel3).addComponent(
						textTitikAlert, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel4).addComponent(
						jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel5).addComponent(
						textTanggal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel6).addComponent(
						textTanggalKirim, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
						GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel7).addComponent(
						textStatus, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(25, Short.MAX_VALUE)));
		jPanel2.setLayout(jPanel2Layout);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jPanel2,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jPanel2,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));

		getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

		setSize(new java.awt.Dimension(450, 363));
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args
	 *            the command line arguments
	 */

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextField textOperatorTujuan;
	private javax.swing.JTextArea textPesan;
	private javax.swing.JTextField textStatus;
	private javax.swing.JTextField textTanggal;
	private javax.swing.JTextField textTanggalKirim;
	private javax.swing.JTextField textTitikAlert;
	private javax.swing.JTextField textTujuan;
	// End of variables declaration//GEN-END:variables
}