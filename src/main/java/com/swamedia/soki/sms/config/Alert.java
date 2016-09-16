package com.swamedia.soki.sms.config;

import java.util.HashMap; 
import java.util.Map;

public class Alert {

	static Alert alert;

	public static final String MEDIA_SMS = "SMS";

	public static final Integer STATUS_UNSEND = 0;
	public static final Integer STATUS_SUCCESS = 1;
	public static final Integer STATUS_FAILED = 2;
	public static final Integer STATUS_PROCESS = 3;

	public static final String TITIK_KIRIM_L2 = "KIRIM L2";
	public static final String TITIK_COMMIT_L2 = "COMMIT L2";
	public static final String TITIK_KOMP_L2 = "KOMPARASI L2";
	public static final String TITIK_BA_FINAL = "BA FINAL SETTLE";
	public static final String TITIK_KIRIM_L3 = "KIRIM L3";
	public static final String TITIK_COMPLETE_SEND = "COMPLETE SEND L3";
	public static final String TITIK_BA_SEMENTARA = "BA SEMENTARA";
	public static final String TITIK_VC = "VOLUME COMPARE";
	public static final String TITIK_BATAS_KIRIM_L2 = "BATAS KIRIM L2";
	public static final String TITIK_BATAS_KIRIM_L3 = "BATAS KIRIM L3";
	public static final String TITIK_BATAS_BPC = "BATAS_BPC";

	public Map<String, String> TEMPLATE_SMS = new HashMap<>();

	public static Alert getInstance() {
		if (alert == null) {
			alert = new Alert();
		}
		return alert;
	}

	public Alert() {
		TEMPLATE_SMS.put(TITIK_KIRIM_L2, "Bilateral Kirim Data L2");
		TEMPLATE_SMS.put(TITIK_COMMIT_L2, "Bilateral Commit Data L2");
		TEMPLATE_SMS.put(TITIK_KOMP_L2, "Bilateral Komparasi l2");
		TEMPLATE_SMS.put(TITIK_BA_FINAL, "Bilateral Settle");
		TEMPLATE_SMS.put(TITIK_KIRIM_L3, "Bilateral Kirim Data L3");
		TEMPLATE_SMS.put(TITIK_COMPLETE_SEND, "Bilateral Complete Send L3");
		TEMPLATE_SMS.put(TITIK_BA_SEMENTARA, "Bilateral Dengan BA Sementara");
		TEMPLATE_SMS.put(TITIK_VC, "Bilateral Selesai VC");
		TEMPLATE_SMS.put(TITIK_BATAS_KIRIM_L2, "Bilateral Belum Kirim Data L2");
		TEMPLATE_SMS.put(TITIK_BATAS_KIRIM_L3, "Bilateral Belum Kirim Data L3");
		TEMPLATE_SMS.put(TITIK_BATAS_BPC, "Bilateral Belum Key In");
	}

	public Map<String, String> getAlerts() {
		return TEMPLATE_SMS;
	}
}
