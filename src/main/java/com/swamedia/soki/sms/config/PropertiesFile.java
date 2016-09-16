/**
 * 
 */
package com.swamedia.soki.sms.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * @author ScattLabs
 *
 */

public class PropertiesFile {
	static PropertiesFile propertiesFile;
	Map<String, String> configSMS = new HashMap<>();
	ClassLoader classLoader = getClass().getClassLoader();

	public static PropertiesFile getInstance() {
		if (propertiesFile == null) {
			propertiesFile = new PropertiesFile();
		}
		return propertiesFile;
	}

	public Map<String, String> getConfigSMS() {
		Properties prop = new Properties();
		InputStream input = null;
		Map<String, String> configSMS = new HashMap<>();
		try {
			input = new FileInputStream("configSMS.properties");
			// load a properties file
			prop.load(input);

			configSMS.put("otomatis", prop.getProperty("c.otomatis"));
			configSMS.put("waktu", prop.getProperty("c.waktu"));

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return configSMS;
	}

	public void setConfigSMS(String otomatis, String waktu) {
		Properties prop = new Properties();
		OutputStream output = null;
		try {
			output = new FileOutputStream("configSMS.properties");
			// set the properties value
			prop.setProperty("c.otomatis", otomatis);
			prop.setProperty("c.waktu", waktu);
			// save properties to project root folder
			prop.store(output, null);
			JOptionPane.showMessageDialog(null, "Konfigurasi berhasil disimpan", "INFO",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException io) {
			JOptionPane.showMessageDialog(null, io.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
