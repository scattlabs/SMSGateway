/**
 * 
 */
package com.swamedia.soki.sms.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.swamedia.soki.sms.config.DataHolder;
import com.swamedia.soki.sms.model.POperator;
import com.swamedia.soki.sms.view.EditNomorOperatorView;
import com.swamedia.soki.sms.view.MainForm;
import com.swamedia.soki.sms.view.ManagementNomorView;

/**
 * @author ScattLabs
 *
 */
public class OperatorController {
	static OperatorController operatorController;

	public static OperatorController getInstance() {
		if (operatorController == null) {
			operatorController = new OperatorController();
		}
		return operatorController;
	}

	public void populateTableOperator(ManagementNomorView mnView, String where) {
		try {
			Object header[] = { "", "ID", "OPERATOR", "NAMA", "NOMOR HP" };
			DefaultTableModel data = new DefaultTableModel(null, header);
			mnView.getTableOperator().setModel(data);
			List<POperator> operators = new ArrayList<>();
			if (where.equals("")) {
				operators = DataHolder.getInstance().getOperatorDao().list("idTbl");
			} else {
				operators = DataHolder.getInstance().getOperatorDao().list("idTbl", where);
			}

			int i = 1;
			for (POperator operator : operators) {
				String kolom1 = "" + i;
				// Clob clob = alert.getPesan();
				// String kolom2 = clob.getSubString(1, (int) clob.length());
				String kolom2 = operator.getIdTbl().toString();
				String kolom3 = operator.getIdoperator();
				String kolom4 = operator.getNamaoperator();
				String kolom5 = operator.getHp();
				String kolom[] = { kolom1, kolom2, kolom3, kolom4, kolom5 };
				data.addRow(kolom);
				i++;
			}
			TableColumnModel columnModel = mnView.getTableOperator().getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(30);
			columnModel.getColumn(1).setPreferredWidth(30);
			columnModel.getColumn(2).setPreferredWidth(70);
			columnModel.getColumn(3).setPreferredWidth(140);
			columnModel.getColumn(4).setPreferredWidth(240);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erorr", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void populateTableNomor(EditNomorOperatorView enoView, String nomor) {
		try {
			Object header[] = { "", "NOMOR" };
			DefaultTableModel data = new DefaultTableModel(null, header);
			enoView.getTableNomor().setModel(data);
			String[] nomors = nomor.split(",");
			if (!nomor.equals("")) {
				for (int i = 0; i < nomors.length; i++) {
					String kolom1 = "" + (i + 1);
					String kolom2 = nomors[i];
					String kolom[] = { kolom1, kolom2 };
					data.addRow(kolom);
				}
			}
			if (nomors.length >= 3) {
				enoView.getButtonSave().setEnabled(false);
			} else {
				enoView.getButtonSave().setEnabled(true);
			}
			TableColumnModel columnModel = enoView.getTableNomor().getColumnModel();
			columnModel.getColumn(0).setPreferredWidth(10);
			columnModel.getColumn(1).setPreferredWidth(240);
			populateTableOperator(ManagementNomorView.getInstance(), "");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void populateComboBoxOperator(ManagementNomorView mnView) {
		mnView.getCbOperator().removeAllItems();
		mnView.getCbOperator().addItem("Select Operator");
		List<POperator> operators = DataHolder.getInstance().getOperatorDao().list("idoperator");
		for (POperator pOperator : operators) {
			mnView.getCbOperator().addItem(pOperator.getNamaoperator());
		}

	}

	/**
	 * @param managementNomorView
	 */
	public void actionSearch(ManagementNomorView mnView) {
		String where = "";
		String operatorSearch = (String) mnView.getCbOperator().getSelectedItem();
		if (!operatorSearch.equals("Select Operator")) {
			POperator operator = DataHolder.getInstance().getOperatorDao().findBy(operatorSearch, "namaoperator");
			where = "idoperator = '" + operator.getIdoperator() + "'";
		}

		String hp = mnView.getTextNomor().getText();
		if (!hp.equals("")) {
			if (!operatorSearch.equals("Select Operator")) {
				where = where + " and ";
			}
			where = where + "hp = '" + hp + "'";
		}

		System.out.println("where : " + where);
		populateTableOperator(mnView, where);
	}

	public void addNomor(EditNomorOperatorView enoView) {
		try {
			POperator operator = DataHolder.getInstance().getOperatorDao()
					.find(new BigDecimal(enoView.getLabelOutputID().getText()));
			if (operator != null) {
				if (operator.getHp() == null) {
					operator.setHp(enoView.getTextNoHP().getText());
				} else {
					operator.setHp(operator.getHp() + "," + enoView.getTextNoHP().getText());
				}
				System.out.println(operator.getHp());
				DataHolder.getInstance().getOperatorDao().save(operator);
				populateTableNomor(enoView, operator.getHp());
				enoView.getTextNoHP().setText("");
			} else {
				System.out.println("operator null");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainForm.getInstance(), e.getMessage());
		}
	}

	public void actionReset(ManagementNomorView mnView) {
		mnView.getCbOperator().setSelectedIndex(0);
		mnView.getTextNomor().setText("");
		populateTableOperator(mnView, "");
	}

}
