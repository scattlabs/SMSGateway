/**
 * 
 */
package com.swamedia.soki.sms.dao.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.swamedia.soki.sms.config.Util;
import com.swamedia.soki.sms.dao.AlertDao;
import com.swamedia.soki.sms.model.TAlert;

/**
 * @author ScattLabs
 *
 */
@Service
public class AlertDaoImpl extends GenericDaoImpl<TAlert> implements AlertDao {

	@Override
	public void updateStatusKirim(BigDecimal idAlert, int status) {
		try {
			String tanggal = Util.getInstance().formatTanggal(new Date(), "dd/MM/yyyy HH:mm:ss");
			String query = "update t_alert set status = " + status + ", tanggal_kirim = TO_DATE('" + tanggal
					+ "','dd/MM/yyyy HH24:MI:SS') where id_alert = " + idAlert + "";
			sessionFactory.getCurrentSession().createSQLQuery(query).executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// TODO: handle exception
		}
	}
}
