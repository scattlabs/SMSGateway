package com.swamedia.soki.sms.config;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.NumberFormat;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author scattlabs
 */
public class CurrencyTable extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CurrencyTable() {
		super();
		setHorizontalAlignment(SwingConstants.RIGHT);
	}

	@Override
	public void setValue(Object value) {
		if ((value != null) && (value instanceof Number)) {
			Number numberValue = (Number) value;
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			value = formatter.format(numberValue.doubleValue());
		}
		super.setValue(value);

	}
}
