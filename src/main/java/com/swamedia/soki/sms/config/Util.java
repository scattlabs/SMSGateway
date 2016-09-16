package com.swamedia.soki.sms.config;

import java.text.ParseException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Scattlabs
 */
public class Util {

	static Util util;

	public static Util getInstance() {
		if (util == null) {
			util = new Util();
		}
		return util;

	}

	public String formatTanggal(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public String formatTanggal(String date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Date date2 = null;
		try {
			date2 = formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return format.format(date2);
	}

}
