package com.swamedia.soki.sms.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRootPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.swamedia.soki.sms.config.DataHolder;
import com.swamedia.soki.sms.controller.AlertController;
import com.swamedia.soki.sms.controller.SendSMSController;
import com.swamedia.soki.sms.model.TAlert;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.KeyAdapter;

/**
 *
 * @author scatt labs
 */
public class SMSListUnsentView extends javax.swing.JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates new form SMSUnsentView
	 */
	static SMSListUnsentView sMSUnsentView;

	public static SMSListUnsentView getInstance() {
		if (sMSUnsentView == null) {
			sMSUnsentView = new SMSListUnsentView();
			sMSUnsentView.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
			sMSUnsentView.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			((BasicInternalFrameUI) sMSUnsentView.getUI()).setNorthPane(null);
			sMSUnsentView.setBorder(null);
		}
		return sMSUnsentView;
	}

	public SMSListUnsentView() {
		initComponents();
		AlertController.getInstance().populateTableSMS(this, "");
		AlertController.getInstance().populateComboBoxOperator(this);
		AlertController.getInstance().populateComboBoxTitikAlert(this);
	}

	public javax.swing.JTable getTableSMS() {
		return tableSMS;
	}

	public javax.swing.JComboBox<String> getCbOperatorTujuan() {
		return cbOperatorTujuan;
	}

	public javax.swing.JComboBox<String> getCbTitikAlert() {
		return cbTitikAlert;
	}

	public javax.swing.JTextField getTextNomor() {
		return textNomor;
	}

	public com.toedter.calendar.JDateChooser getTextTanggal() {
		return textTanggal;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	private void textNomorKeyTyped(java.awt.event.KeyEvent evt) {
		char c = evt.getKeyChar();
		if (textNomor.getText().length() >= 12) {
			getToolkit().beep();
			evt.consume();
		} else {
			if (!((Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)))) {
				getToolkit().beep();
				evt.consume();
			}
		}

	}

	private void actionSearch() {
		buttonSearch.setEnabled(false);
		AlertController.getInstance().actionSearch(this);
		buttonSearch.setEnabled(true);

	}

	private void actionReset() {
		AlertController.getInstance().actionReset(this);
	}

	private void actionView() {
		int baris = tableSMS.getSelectedRow();
		if (baris < 0) {
			messageWarning();
		} else {
			TAlert alert = DataHolder.getInstance().getAlertsDao()
					.find(new BigDecimal(tableSMS.getValueAt(baris, 1).toString()));
			DetailSMSView unSentView = new DetailSMSView(MainForm.getInstance(), true, alert);
			unSentView.setVisible(true);
		}

	}

	private void actionResent() {
		int baris = tableSMS.getSelectedRow();
		if (baris < 0) {
			messageWarning();
		} else {
			try {
				List<TAlert> alerts = new ArrayList<>();
				TAlert alert = DataHolder.getInstance().getAlertsDao()
						.find(new BigDecimal(tableSMS.getValueAt(baris, 1).toString()));
				alerts.add(alert);
				SendSMSController smsController = new SendSMSController();
				if (smsController.doIt(alerts)) {
					JOptionPane.showMessageDialog(this, "Pesan Berhasil Terkirim ke no " + alert.getTujuan() + "",
							"INFO", 1);
					AlertController.getInstance().populateTableSMS(this, "");
					SMSListSentView.getInstance().populateTable();
				}

			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Warning", 2);
				// TODO: handle exception
			}
		}

	}

	private void actionDelete() {
		int baris = tableSMS.getSelectedRow();
		if (baris < 0) {
			messageWarning();
		} else {
			TAlert alert = DataHolder.getInstance().getAlertsDao()
					.find(new BigDecimal(tableSMS.getValueAt(baris, 1).toString()));
			int response = JOptionPane.showConfirmDialog(null,
					"Yakin menghapus data dengan id " + alert.getIdAlert() + "?");
			System.out.println(response);
			if (response == 0) {
				try {
					DataHolder.getInstance().getAlertsDao().delete(alert.getIdAlert());
					JOptionPane.showMessageDialog(MainForm.getInstance(),
							"Data Alert Dengan id " + alert.getIdAlert() + " berhasil di hapus", "INFO", 1);
					AlertController.getInstance().populateTableSMS(this, "");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(MainForm.getInstance(), e.getMessage(), "ERROR", 3);
					// TODO: handle exception
				}
			}
		}
	}

	private void messageWarning() {
		JOptionPane.showMessageDialog(this, "Pilih salah satu data pada tabel", "Warning", 2);
	}

	private void initComponents() {
		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		textNomor = new javax.swing.JTextField();
		cbOperatorTujuan = new javax.swing.JComboBox<>();
		cbOperatorTujuan.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					textNomor.requestFocus();
				}
			}
		});
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		cbTitikAlert = new javax.swing.JComboBox<>();
		cbTitikAlert.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					buttonSearch.requestFocus();
					actionSearch();
				}
			}
		});
		buttonSearch = new javax.swing.JButton();
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionSearch();
			}
		});
		textTanggal = new com.toedter.calendar.JDateChooser();
		textTanggal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					cbTitikAlert.requestFocus();
				}
			}
		});
		jPanel3 = new javax.swing.JPanel();
		buttonView = new javax.swing.JButton();
		buttonView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionView();
			}
		});
		buttonDelete = new javax.swing.JButton();
		buttonDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionDelete();
			}
		});
		buttonResent = new javax.swing.JButton();
		buttonResent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionResent();
			}
		});
		jScrollPane1 = new javax.swing.JScrollPane();
		tableSMS = new javax.swing.JTable();
		labelTitle = new javax.swing.JLabel();

		setTitle("Content Unsent SMS");
		setPreferredSize(new java.awt.Dimension(658, 441));

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Filter"));

		jLabel1.setText("Operator Tujuan");

		jLabel2.setText("Nomor");

		textNomor.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent evt) {
				textNomorKeyTyped(evt);
			}

			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					textTanggal.requestFocus();
				}
			}
		});

		cbOperatorTujuan.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel3.setText("Tanggal Kirim");

		jLabel4.setText("Titik Alert");

		cbTitikAlert.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		buttonSearch.setText("Search");

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionReset();
			}
		});

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout
				.createSequentialGroup().addContainerGap().addGroup(jPanel2Layout
						.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout.createSequentialGroup()
								.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel1)
										.addComponent(jLabel2))
								.addGap(18)
								.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(cbOperatorTujuan, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(textNomor, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
								.addGap(18)
								.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(jLabel3)
										.addComponent(jLabel4))
								.addGap(18)
								.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(textTanggal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addComponent(cbTitikAlert, GroupLayout.PREFERRED_SIZE, 146,
												GroupLayout.PREFERRED_SIZE)))
						.addGroup(jPanel2Layout.createSequentialGroup().addComponent(buttonSearch)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnReset)))
				.addContainerGap(100, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout
				.createSequentialGroup().addContainerGap()
				.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel3)
										.addComponent(textTanggal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel4)
										.addComponent(cbTitikAlert, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addContainerGap())
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1)
										.addComponent(cbOperatorTujuan, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel2)
										.addComponent(textNomor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18).addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
										.addComponent(buttonSearch).addComponent(btnReset))
								.addContainerGap()))));
		jPanel2.setLayout(jPanel2Layout);

		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("List SMS"));

		buttonView.setText("View");

		buttonDelete.setText("Delete");

		buttonResent.setText("Resent");

		tableSMS.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "", "Pesan", "Titik Alert", "No HP", "Status", "Tanggal Kirim" }));
		jScrollPane1.setViewportView(tableSMS);

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.TRAILING)
				.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel3Layout.createParallelGroup(Alignment.TRAILING)
								.addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 580, Short.MAX_VALUE)
								.addGroup(jPanel3Layout.createSequentialGroup().addComponent(buttonResent)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(buttonDelete)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(buttonView)))
						.addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup()
						.addGroup(jPanel3Layout.createParallelGroup(Alignment.BASELINE).addComponent(buttonView)
								.addComponent(buttonDelete).addComponent(buttonResent))
						.addGap(18).addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
						.addContainerGap()));
		jPanel3.setLayout(jPanel3Layout);

		labelTitle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		labelTitle.setText("Content Unsent SMS");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel1Layout
				.createSequentialGroup().addContainerGap()
				.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
						.addComponent(jPanel3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
						.addComponent(jPanel2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup().addComponent(labelTitle)
								.addGap(0, 485, Short.MAX_VALUE)))
				.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(
						jPanel1Layout.createParallelGroup(Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addComponent(labelTitle)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 124,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
										.addContainerGap()));
		jPanel1.setLayout(jPanel1Layout);

		getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton buttonSearch;
	private javax.swing.JButton buttonView;
	private javax.swing.JButton buttonDelete;
	private javax.swing.JButton buttonResent;
	private javax.swing.JComboBox<String> cbOperatorTujuan;
	private javax.swing.JComboBox<String> cbTitikAlert;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTable tableSMS;
	private javax.swing.JLabel labelTitle;
	private javax.swing.JTextField textNomor;
	private com.toedter.calendar.JDateChooser textTanggal;
}
