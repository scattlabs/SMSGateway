package com.swamedia.soki.sms.view;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.swamedia.soki.sms.config.DataHolder;
import com.swamedia.soki.sms.config.PropertiesFile;
import com.swamedia.soki.sms.controller.AlertController;

import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseEvent;

/**
 *
 * @author Scattlabs
 */
@Component // ku tandai kaw sebagai class yang akan di periksa spring
@EnableScheduling
public class MainForm extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Creates new form MainForm
	 */
	static MainForm mainForm;

	String menuAktif = "";
	boolean sendAktif = false;

	public static MainForm getInstance() {
		if (mainForm == null) {
			mainForm = new MainForm();
			DataHolder.getInstance().execute();
		}
		return mainForm;
	}

	public MainForm() {
		setResizable(false);
		initComponents();
	}

	@Scheduled(fixedRate = 1000)
	public void scheduleTest() {
		if (!sendAktif) {
			Map<String, String> configSMS = PropertiesFile.getInstance().getConfigSMS();
			if (configSMS.get("otomatis").equals("1")) {
				String pattern = "hh:mm:ss";
				SimpleDateFormat format = new SimpleDateFormat(pattern);
				String currentDate = format.format(new Date());
				String configWaktu = configSMS.get("waktu");
				// System.out.println(currentDate + " == " + configWaktu);
				if (currentDate.equals(configWaktu)) {
					sendAktif = true;
					AlertController.getInstance().sendSMSOtomatis();
					sendAktif = false;
				}
			}
		} else {
			System.out.println("waiting proses kirim");
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */

	private void initComponents() {

		jDesktopPane1 = new javax.swing.JDesktopPane();
		jPanel3 = new javax.swing.JPanel();
		labelKonfigurasi = new javax.swing.JLabel();
		labelManagementNomor = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		labelSMSUnsent = new javax.swing.JLabel();
		labelSMSSent = new javax.swing.JLabel();
		jMenuBar1 = new javax.swing.JMenuBar();
		jMenu2 = new javax.swing.JMenu();
		jMenuItem1 = new javax.swing.JMenuItem();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("SOKI SMS");
		setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		jDesktopPane1.setBackground(new java.awt.Color(102, 102, 255));

		javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
		jDesktopPane1.setLayout(jDesktopPane1Layout);
		jDesktopPane1Layout.setHorizontalGroup(jDesktopPane1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 640, Short.MAX_VALUE));
		jDesktopPane1Layout.setVerticalGroup(jDesktopPane1Layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 0, Short.MAX_VALUE));

		jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

		labelKonfigurasi.setText("Konfigurasi");
		labelKonfigurasi.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				labelKonfigurasiMouseClicked(evt);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!menuAktif.equals(labelKonfigurasi.getText())) {
					labelKonfigurasi.setFont(new Font("Tahoma", Font.BOLD, 11));
				}

			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!menuAktif.equals(labelKonfigurasi.getText())) {
					labelKonfigurasi.setFont(new Font("Tahoma", Font.PLAIN, 11));
				}
			}
		});

		labelManagementNomor.setText("Management Nomor");
		labelManagementNomor.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				labelManagementNomorMouseClicked(evt);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				if (!menuAktif.equals(labelManagementNomor.getText())) {
					labelManagementNomor.setFont(new Font("Tahoma", Font.BOLD, 11));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!menuAktif.equals(labelManagementNomor.getText())) {
					labelManagementNomor.setFont(new Font("Tahoma", Font.PLAIN, 11));
				}
			}
		});

		jLabel3.setText("List SMS");

		labelSMSUnsent.setText("Unsent");
		labelSMSUnsent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				labelSMSUnsentMouseClicked(evt);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!menuAktif.equals(labelSMSUnsent.getText())) {
					labelSMSUnsent.setFont(new Font("Tahoma", Font.BOLD, 11));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!menuAktif.equals(labelSMSUnsent.getText())) {
					labelSMSUnsent.setFont(new Font("Tahoma", Font.PLAIN, 11));
				}
			}
		});

		labelSMSSent.setText("Sent");
		labelSMSSent.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				labelSMSSentMouseClicked(evt);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				if (!menuAktif.equals(labelSMSSent.getText())) {
					labelSMSSent.setFont(new Font("Tahoma", Font.BOLD, 11));
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (!menuAktif.equals(labelSMSSent.getText())) {
					labelSMSSent.setFont(new Font("Tahoma", Font.PLAIN, 11));
				}
			}
		});

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(Alignment.TRAILING).addGroup(jPanel3Layout
				.createSequentialGroup()
				.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap()
								.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
										.addComponent(labelKonfigurasi, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
										.addGroup(jPanel3Layout.createSequentialGroup().addComponent(jLabel3).addGap(57,
												125, Short.MAX_VALUE))
										.addComponent(labelManagementNomor, GroupLayout.DEFAULT_SIZE, 164,
												Short.MAX_VALUE)))
						.addGroup(jPanel3Layout.createSequentialGroup().addGap(53)
								.addGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
										.addComponent(labelSMSSent, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
										.addComponent(labelSMSUnsent, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))))
				.addContainerGap()));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel3Layout.createSequentialGroup().addContainerGap().addComponent(labelKonfigurasi)
						.addGap(18).addComponent(labelManagementNomor).addGap(18).addComponent(jLabel3)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(labelSMSUnsent)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(labelSMSSent)
						.addContainerGap(293, Short.MAX_VALUE)));
		jPanel3.setLayout(jPanel3Layout);

		jMenu2.setText("File");

		jMenuItem1.setAccelerator(
				javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
		jMenuItem1.setText("Logout");
		jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jMenuItem1ActionPerformed(evt);
			}
		});
		jMenu2.add(jMenuItem1);

		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(jDesktopPane1).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jDesktopPane1))
						.addContainerGap()));

		setSize(new java.awt.Dimension(890, 513));
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jMenuItem1ActionPerformed
		this.setVisible(false);
		jDesktopPane1.removeAll();
	}// GEN-LAST:event_jMenuItem1ActionPerformed

	private void labelKonfigurasiMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_labelKonfigurasiMouseClicked
		menuActive(labelKonfigurasi);
		addJIF(KonfigurasiView.getInstance());
	}// GEN-LAST:event_labelKonfigurasiMouseClicked

	private void labelManagementNomorMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_labelManagementNomorMouseClicked
		menuActive(labelManagementNomor);
		addJIF(ManagementNomorView.getInstance());
	}// GEN-LAST:event_labelManagementNomorMouseClicked

	private void labelSMSUnsentMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_labelSMSUnsentMouseClicked
		menuActive(labelSMSUnsent);
		addJIF(SMSListUnsentView.getInstance());
	}// GEN-LAST:event_labelSMSUnsentMouseClicked

	private void labelSMSSentMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_labelSMSSentMouseClicked
		menuActive(labelSMSSent);
		addJIF(SMSListSentView.getInstance());

	}// GEN-LAST:event_labelSMSSentMouseClicked

	public void addJIF(JInternalFrame jif) {
		try {
			boolean status = true;
			JInternalFrame[] children;
			children = jDesktopPane1.getAllFrames();

			if (children.length < 1) {
				jDesktopPane1.add(jif);
				jif.setVisible(true);
			} else {
				for (JInternalFrame jInternalFrame : children) {
					if (jInternalFrame.getTitle().equals(jif.getTitle())) {
						status = false;
						jInternalFrame.setSelected(true);
						break;
					}
				}
				if (status) {
					jDesktopPane1.add(jif);
					jif.setVisible(true);
				}

			}

		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void refreshJIF(JInternalFrame jif) {
		try {
			boolean status = true;
			JInternalFrame[] children;
			children = jDesktopPane1.getAllFrames();

			if (children.length < 1) {
				jDesktopPane1.add(jif);
				jif.setVisible(true);
			} else {
				for (JInternalFrame jInternalFrame : children) {
					if (jInternalFrame.getTitle().equals(jif.getTitle())) {
						status = false;
						jInternalFrame.setSelected(true);
						jInternalFrame.updateUI();
						jInternalFrame.revalidate();
						jInternalFrame.repaint();
						break;
					}
				}
				if (status) {
					jDesktopPane1.add(jif);
					jif.setVisible(true);
				}

			}

		} catch (PropertyVetoException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void menuActive(JLabel menu) {
		labelKonfigurasi.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelManagementNomor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelSMSUnsent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		labelSMSSent.setFont(new Font("Tahoma", Font.PLAIN, 11));
		menu.setFont(new Font("Tahoma", Font.BOLD, 11));
		menuAktif = menu.getText();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) throws UnsupportedLookAndFeelException {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				getInstance().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JDesktopPane jDesktopPane1;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JMenu jMenu2;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem1;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JLabel labelKonfigurasi;
	private javax.swing.JLabel labelManagementNomor;
	private javax.swing.JLabel labelSMSSent;
	private javax.swing.JLabel labelSMSUnsent;
}
