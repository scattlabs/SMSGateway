package com.swamedia.soki.sms.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.JRootPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import com.swamedia.soki.sms.config.PropertiesFile;

import javax.swing.GroupLayout.Alignment;

import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;

/**
 *
 * @author scatt labs
 */
public class KonfigurasiView extends javax.swing.JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates new form KonfigurasiView
	 */
	static KonfigurasiView konfigurasiView;

	public static KonfigurasiView getInstance() {
		if (konfigurasiView == null) {
			konfigurasiView = new KonfigurasiView();
			konfigurasiView.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
			konfigurasiView.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
			((BasicInternalFrameUI) konfigurasiView.getUI()).setNorthPane(null);
			konfigurasiView.setBorder(null);
		}
		return konfigurasiView;
	}

	public KonfigurasiView() {
		initComponents();
		Map<String, String> configSMS = PropertiesFile.getInstance().getConfigSMS();
		if (configSMS.get("otomatis").equals("0")) {
			cbOtomatis.setSelected(false);
			textWaktu.setText(configSMS.get("waktu"));
			textWaktu.setEditable(false);
		} else {
			cbOtomatis.setSelected(true);
			textWaktu.setText(configSMS.get("waktu"));
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	public void actionSave() {
		PropertiesFile.getInstance().setConfigSMS(cbOtomatis.isSelected() == true ? "1" : "0", textWaktu.getText());
	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jPanel2 = new javax.swing.JPanel();
		cbOtomatis = new javax.swing.JCheckBox();
		cbOtomatis.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent em) {
				if (cbOtomatis.isSelected() == false) {
					textWaktu.setEditable(false);
					textWaktu.setEnabled(false);
				} else {
					textWaktu.setEditable(true);
					textWaktu.setEnabled(true);
				}
			}
		});
		buttonSimpan = new javax.swing.JButton();
		buttonSimpan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionSave();
			}
		});
		jLabel1 = new javax.swing.JLabel();
		textWaktu = new javax.swing.JFormattedTextField();
		textWaktu.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					buttonSimpan.requestFocus();
					actionSave();
				}
			}
		});
		try {
			textWaktu.setFormatterFactory(
					new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##:##:##")));
		} catch (java.text.ParseException ex) {
			ex.printStackTrace();
		}
		jLabel2 = new javax.swing.JLabel();

		setResizable(true);
		setTitle("Content Konfigurasi");
		setPreferredSize(new java.awt.Dimension(658, 441));

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Konfigurasi"));

		cbOtomatis.setText("Kirim SMS Otomatis");

		buttonSimpan.setText("SIMPAN");

		jLabel1.setText("Schedule Kirim SMS");

		JLabel lblJam = new JLabel("24 jam");
		lblJam.setForeground(Color.RED);
		lblJam.setHorizontalAlignment(SwingConstants.LEFT);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
								.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addComponent(cbOtomatis)
										.addComponent(buttonSimpan)
										.addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLabel1).addGap(18)
												.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
														.addComponent(lblJam, GroupLayout.DEFAULT_SIZE, 87,
																Short.MAX_VALUE)
														.addComponent(textWaktu, GroupLayout.PREFERRED_SIZE, 87,
																GroupLayout.PREFERRED_SIZE))))
								.addContainerGap(404, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel2Layout
				.createSequentialGroup().addContainerGap().addComponent(cbOtomatis)
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE).addComponent(jLabel1).addComponent(
						textWaktu, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblJam)
				.addPreferredGap(ComponentPlacement.RELATED, 253, Short.MAX_VALUE).addComponent(buttonSimpan)
				.addContainerGap()));
		jPanel2.setLayout(jPanel2Layout);

		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel2.setText("Content Konfigurasi");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel2).addGap(0, 0,
										Short.MAX_VALUE)))
						.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(
						jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel2)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addContainerGap()));

		getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton buttonSimpan;
	private javax.swing.JCheckBox cbOtomatis;
	private javax.swing.JFormattedTextField textWaktu;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
}
