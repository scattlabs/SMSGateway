package com.swamedia.soki.sms.config;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.swing.SwingWorker;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import com.swamedia.soki.sms.dao.AlertDao;
import com.swamedia.soki.sms.dao.OperatorDao;

/**
 *
 * @author scattlabs
 */
public class DataHolder extends SwingWorker<Void, Void> {

	static DataHolder dataHolder;
	static AlertDao alertDao;
	static OperatorDao operatorDao;
	static Environment environment;

	public static DataHolder getInstance() {
		if (dataHolder == null) {
			dataHolder = new DataHolder();
		}

		return dataHolder;
	}

	static ApplicationContext ctx;

	public ApplicationContext getCtx() {
		if (ctx == null) {
			ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
			System.out.println("ctx not null");
		}
		return ctx;
	}

	public Environment getEnvironment() {
		if (environment == null) {
			environment = (Environment) getCtx().getBean("environment");
		}
		return environment;
	}

	public AlertDao getAlertsDao() {
		if (alertDao == null) {
			alertDao = (AlertDao) getCtx().getBean("alertDaoImpl");
		}
		return alertDao;
	}

	public OperatorDao getOperatorDao() {
		if (operatorDao == null) {
			operatorDao = (OperatorDao) getCtx().getBean("operatorDaoImpl");
		}
		return operatorDao;
	}

	public void initializeData() {
		System.out.println("initializeData");
		getCtx();
		getAlertsDao();
		getOperatorDao();
		getEnvironment();
	}

	@Override
	protected Void doInBackground() throws Exception {

		while (true) {
			initializeData();
			Thread.sleep(3600000);
		}
	}
};

;
