/**
 * 
 */
package com.swamedia.soki.sms.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.swamedia.soki.sms.config.Alert;
import com.swamedia.soki.sms.config.DataHolder;
import com.swamedia.soki.sms.config.Util;
import com.swamedia.soki.sms.model.POperator;
import com.swamedia.soki.sms.model.TAlert;
import com.swamedia.soki.sms.view.SMSListSentView;
import com.swamedia.soki.sms.view.SMSListUnsentView;

/**
 * @author ScattLabs
 *
 */
public class AlertController {
	static AlertController alertController;

	public static AlertController getInstance() {
		if (alertController == null) {
			alertController = new AlertController();
		}
		return alertController;
	}

	public void populateTableSMS(SMSListUnsentView smsListView, String where) {
		try {
			Object header[] = { "", "ID", "OPERATOR TUJUAN", "TITIK ALERT", "NO HP", "STATUS", "TANGGAL" };
			DefaultTableModel data = new DefaultTableModel(null, header);
			smsListView.getTableSMS().setModel(data);

			List<TAlert> alerts = new ArrayList<>();
			if (where.equals("")) {
				alerts = DataHolder.getInstance().getAlertsDao().list("idAlert", "media = 'SMS' and status != 1");
			} else {
				alerts = DataHolder.getInstance().getAlertsDao().list("idAlert",
						"media = 'SMS' and status != 1 and " + where);
			}

			int i = 1;
			for (TAlert alert : alerts) {
				String kolom1 = "" + i;
				String kolom2 = alert.getIdAlert().toString();
				String kolom3 = alert.getOperatorTujuan();
				String kolom4 = alert.getTitikAlert();
				String kolom5 = alert.getTujuan();
				String kolom6 = alert.getStatus().compareTo(new BigDecimal(0)) == 0 ? "Belum Dikirim" : "Gagal Kirim";
				String kolom7 = alert.getTanggal().equals("") ? ""
						: Util.getInstance().formatTanggal(alert.getTanggal(), "dd/MM/yyyy HH:mm:ss");
				String kolom[] = { kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7 };
				data.addRow(kolom);
				i++;
			}
			TableColumnModel columnModel = smsListView.getTableSMS().getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(30);
			columnModel.getColumn(1).setPreferredWidth(30);
			columnModel.getColumn(2).setPreferredWidth(110);
			columnModel.getColumn(3).setPreferredWidth(120);
			columnModel.getColumn(4).setPreferredWidth(95);
			columnModel.getColumn(5).setPreferredWidth(80);
			columnModel.getColumn(6).setPreferredWidth(105);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void populateTableSMS(SMSListSentView smsListView, String where) {
		try {
			Object header[] = { "", "ID", "OPERATOR TUJUAN", "TITIK ALERT", "NO HP", "STATUS", "TANGGAL KIRIM" };
			DefaultTableModel data = new DefaultTableModel(null, header);
			smsListView.getTableSMS().setModel(data);

			List<TAlert> alerts = new ArrayList<>();
			if (where.equals("")) {
				alerts = DataHolder.getInstance().getAlertsDao().list("idAlert", "media = 'SMS' and status = 1");
			} else {
				alerts = DataHolder.getInstance().getAlertsDao().list("idAlert",
						"media = 'SMS' and status = 1 and " + where);
			}

			int i = 1;
			for (TAlert alert : alerts) {
				String kolom1 = "" + i;
				String kolom2 = alert.getIdAlert().toString();
				String kolom3 = alert.getOperatorTujuan();
				String kolom4 = alert.getTitikAlert();
				String kolom5 = alert.getTujuan();
				String kolom6 = alert.getStatus().compareTo(new BigDecimal(1)) == 0 ? "Terkirim" : "Belum Terkirim";
				String kolom7 = alert.getTanggalKirim().equals("") ? ""
						: Util.getInstance().formatTanggal(alert.getTanggalKirim(), "dd-MM-yyyy HH:mm:ss");
				String kolom[] = { kolom1, kolom2, kolom3, kolom4, kolom5, kolom6, kolom7 };
				data.addRow(kolom);
				i++;
			}
			TableColumnModel columnModel = smsListView.getTableSMS().getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(30);
			columnModel.getColumn(1).setPreferredWidth(30);
			columnModel.getColumn(2).setPreferredWidth(110);
			columnModel.getColumn(3).setPreferredWidth(120);
			columnModel.getColumn(4).setPreferredWidth(95);
			columnModel.getColumn(5).setPreferredWidth(80);
			columnModel.getColumn(6).setPreferredWidth(105);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void populateComboBoxOperator(SMSListUnsentView smsListView) {
		smsListView.getCbOperatorTujuan().removeAllItems();
		smsListView.getCbOperatorTujuan().addItem("Select Operator Tujuan");
		List<POperator> operators = DataHolder.getInstance().getOperatorDao().list("idoperator");
		for (POperator pOperator : operators) {
			smsListView.getCbOperatorTujuan().addItem(pOperator.getNamaoperator());
		}

	}

	public void populateComboBoxOperator(SMSListSentView smsListView) {
		smsListView.getCbOperatorTujuan().removeAllItems();
		smsListView.getCbOperatorTujuan().addItem("Select Operator Tujuan");
		List<POperator> operators = DataHolder.getInstance().getOperatorDao().list("idoperator");
		for (POperator pOperator : operators) {
			smsListView.getCbOperatorTujuan().addItem(pOperator.getNamaoperator());
		}
	}

	public void actionSearch(SMSListUnsentView smsListView) {
		String where = "";
		String operatorTujuan = (String) smsListView.getCbOperatorTujuan().getSelectedItem();
		if (!operatorTujuan.equals("Select Operator Tujuan")) {
			POperator operator = DataHolder.getInstance().getOperatorDao().findBy(operatorTujuan, "namaoperator");
			where = "operatorTujuan = '" + operator.getIdoperator() + "'";
		}

		String tujuan = smsListView.getTextNomor().getText();
		if (!tujuan.equals("")) {
			if (!operatorTujuan.equals("Select Operator Tujuan")) {
				where = where + " and ";
			}
			where = where + "tujuan = '" + tujuan + "'";
		}

		String tanggal = "";
		if (smsListView.getTextTanggal().getDate() != null) {
			tanggal = Util.getInstance().formatTanggal(smsListView.getTextTanggal().getDate(), "dd/MM/yyyy");
			System.out.println("tanggal : " + tanggal);
			if (!tanggal.equals("")) {
				if (!tujuan.equals("") || !operatorTujuan.equals("Select Operator Tujuan")) {
					where = where + " and ";
				}
				where = where + "tanggal = TO_DATE('" + tanggal + "','dd/MM/yyyy')";
			}
		}

		String titikAlert = (String) smsListView.getCbTitikAlert().getSelectedItem();
		if (!smsListView.getCbTitikAlert().getSelectedItem().equals("Select Titik Alert")) {
			if (!tujuan.equals("") || !operatorTujuan.equals("Select Operator Tujuan") || !tanggal.equals("")) {
				where = where + " and ";
			}
			where = where + "titikAlert = '" + titikAlert + "'";
		}

		System.out.println("where : " + where);
		populateTableSMS(smsListView, where);
	}

	public void actionSearch(SMSListSentView smsListView) {
		String where = "";
		String operatorTujuan = (String) smsListView.getCbOperatorTujuan().getSelectedItem();
		if (!operatorTujuan.equals("Select Operator Tujuan")) {
			POperator operator = DataHolder.getInstance().getOperatorDao().findBy(operatorTujuan, "namaoperator");
			where = "operatorTujuan = '" + operator.getIdoperator() + "'";
		}

		String tujuan = smsListView.getTextNomor().getText();
		if (!tujuan.equals("")) {
			if (!operatorTujuan.equals("Select Operator Tujuan")) {
				where = where + " and ";
			}
			where = where + "tujuan = '" + tujuan + "'";
		}

		String tanggal = "";
		if (smsListView.getTextTanggal().getDate() != null) {
			tanggal = Util.getInstance().formatTanggal(smsListView.getTextTanggal().getDate(), "dd/MM/yyyy");
			System.out.println("tanggal : " + tanggal);
			if (!tanggal.equals("")) {
				if (!tujuan.equals("") || !operatorTujuan.equals("Select Operator Tujuan")) {
					where = where + " and ";
				}

				where = where + "to_char(tanggalKirim, 'dd/mm/yyyy') = '" + tanggal + "'";
			}
		}

		String titikAlert = (String) smsListView.getCbTitikAlert().getSelectedItem();
		if (!smsListView.getCbTitikAlert().getSelectedItem().equals("Select Titik Alert")) {
			if (!tujuan.equals("") || !operatorTujuan.equals("Select Operator Tujuan") || !tanggal.equals("")) {
				where = where + " and ";
			}
			where = where + "titikAlert = '" + titikAlert + "'";
		}

		System.out.println("where : " + where);
		populateTableSMS(smsListView, where);
	}

	/**
	 * @param smsListView
	 */
	public void actionReset(SMSListUnsentView smsListView) {
		smsListView.getCbOperatorTujuan().setSelectedIndex(0);
		smsListView.getTextNomor().setText("");
		smsListView.getCbTitikAlert().setSelectedIndex(0);
		smsListView.getTextTanggal().setCalendar(null);
	}

	public void actionReset(SMSListSentView smsListView) {
		smsListView.getCbOperatorTujuan().setSelectedIndex(0);
		smsListView.getTextNomor().setText("");
		smsListView.getCbTitikAlert().setSelectedIndex(0);
		smsListView.getTextTanggal().setCalendar(null);
	}

	/**
	 * @param smsListSentView
	 */
	public void populateComboBoxTitikAlert(SMSListSentView smsListView) {
		smsListView.getCbTitikAlert().removeAllItems();
		smsListView.getCbTitikAlert().addItem("Select Titik Alert");
		Map<String, String> map = Alert.getInstance().getAlerts();
		for (Map.Entry<String, String> alert : map.entrySet()) {
			smsListView.getCbTitikAlert().addItem(alert.getKey());
		}
	}

	/**
	 * @param smsListUnsentView
	 */
	public void populateComboBoxTitikAlert(SMSListUnsentView smsListView) {
		smsListView.getCbTitikAlert().removeAllItems();
		smsListView.getCbTitikAlert().addItem("Select Titik Alert");
		Map<String, String> map = Alert.getInstance().getAlerts();
		for (Map.Entry<String, String> alert : map.entrySet()) {
			smsListView.getCbTitikAlert().addItem(alert.getKey());
		}
	}

	public void sendSMSOtomatis() {
		List<TAlert> alerts = DataHolder.getInstance().getAlertsDao().list("idAlert", "media = 'SMS' and status != 1");
		SendSMSController smsController = new SendSMSController();
		smsController.doIt(alerts);
	}
}
