/**
 * 
 */
package com.swamedia.soki.sms.dao;

import java.math.BigDecimal;

import com.swamedia.soki.sms.model.TAlert;

/**
 * @author ScattLabs
 *
 */
public interface AlertDao extends GenericDao<TAlert> {

	/**
	 * @param idAlert
	 */

	void updateStatusKirim(BigDecimal idAlert, int status);
}
