package com.swamedia.soki.sms.controller;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

//SendMessage.java - Sample application.
//
// This application shows you the basic procedure for sending messages.
// You will find how to send synchronous and asynchronous messages.
//
// For asynchronous dispatch, the example application sets a callback
// notification, to see what's happened with messages.

import org.smslib.AGateway;
import org.smslib.GatewayException;
import org.smslib.IOutboundMessageNotification;
import org.smslib.Library;
import org.smslib.OutboundMessage;
import org.smslib.SMSLibException;
import org.smslib.Service;
import org.smslib.TimeoutException;
import org.smslib.modem.SerialModemGateway;

import com.swamedia.soki.sms.config.DataHolder;
import com.swamedia.soki.sms.model.TAlert;

public class SendSMSController {

	private OutboundNotification outboundNotification;
	private SerialModemGateway gateway;

	public boolean doIt(List<TAlert> alerts) {
		TAlert alertTemp = new TAlert();
		boolean send = false;
		try {
			outboundNotification = new OutboundNotification();
			System.out.println(Library.getLibraryDescription());
			System.out.println("Version: " + Library.getLibraryVersion());
			gateway = new SerialModemGateway("modem.com4", "COM3", 115200, "WAVECOM MODEM", "MULTIBAND  900E  1800");
			gateway.setInbound(true);
			gateway.setOutbound(true);
			Service.getInstance().setOutboundMessageNotification(outboundNotification);
			boolean gatewayStatus = false;
			for (AGateway aGateway : Service.getInstance().getGateways()) {
				if (aGateway.getGatewayId().equals(gateway.getGatewayId())) {
					gatewayStatus = true;
				}
			}
			if (!gatewayStatus) {
				Service.getInstance().addGateway(gateway);
			}
			Service.getInstance().startService();
			System.out.println("service status : " + Service.getInstance().getServiceStatus());
			System.out.println("Modem Information:");
			System.out.println("  Manufacturer: " + gateway.getManufacturer());
			System.out.println("  Model: " + gateway.getModel());
			System.out.println("  Serial No: " + gateway.getSerialNo());
			System.out.println("  SIM IMSI: " + gateway.getImsi());
			System.out.println("  Signal Level: " + gateway.getSignalLevel() + " dBm");
			System.out.println("  Battery Level: " + gateway.getBatteryLevel() + "%");
			OutboundMessage msg = null;
			for (TAlert alert : alerts) {
				alertTemp = alert;
				msg = new OutboundMessage(alert.getTujuan(), alert.getPesan());
				Service.getInstance().sendMessage(msg);
				DataHolder.getInstance().getAlertsDao().updateStatusKirim(alert.getIdAlert(), 1);
				send = true;
			}
		} catch (GatewayException e) {
			DataHolder.getInstance().getAlertsDao().updateStatusKirim(alertTemp.getIdAlert(), 2);
			JOptionPane.showMessageDialog(null, "d : GatewayException : " + e.getMessage(), "Warning", 2);
			System.out.println("GatewayException : " + e.getMessage());
			e.printStackTrace();
		} catch (TimeoutException e) {
			DataHolder.getInstance().getAlertsDao().updateStatusKirim(alertTemp.getIdAlert(), 2);
			JOptionPane.showMessageDialog(null, "d : TimeoutException : " + e.getMessage(), "Warning", 2);
			System.out.println("TimeoutException : " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			DataHolder.getInstance().getAlertsDao().updateStatusKirim(alertTemp.getIdAlert(), 2);
			JOptionPane.showMessageDialog(null, "d : IOException : " + e.getMessage(), "Warning", 2);
			System.out.println("IOException : " + e.getMessage());
			e.printStackTrace();
		} catch (InterruptedException e) {
			DataHolder.getInstance().getAlertsDao().updateStatusKirim(alertTemp.getIdAlert(), 2);
			JOptionPane.showMessageDialog(null, "d : InterruptedException : " + e.getMessage(), "Warning", 2);
			System.out.println("InterruptedException : " + e.getMessage());
			e.printStackTrace();
		} catch (SMSLibException e) {
			JOptionPane.showMessageDialog(null, "d : SMSLibException : " + e.getMessage(), "Warning", 2);
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				Service.getInstance().stopService();
				Service.getInstance().removeGateway(gateway);
			} catch (TimeoutException e) {
				JOptionPane.showMessageDialog(null, "f : TimeoutException : " + e.getMessage(), "Warning", 2);
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GatewayException e) {
				JOptionPane.showMessageDialog(null, "f : GatewayException : " + e.getMessage(), "Warning", 2);
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SMSLibException e) {
				JOptionPane.showMessageDialog(null, "f : SMSLibException : " + e.getMessage(), "Warning", 2);
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "f : IOException : " + e.getMessage(), "Warning", 2);
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, "f : InterruptedException : " + e.getMessage(), "Warning", 2);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return send;
	}

	public class OutboundNotification implements IOutboundMessageNotification {
		public void process(AGateway gateway, OutboundMessage msg) {
			System.out.println("Outbound handler called from Gateway: " + gateway.getGatewayId());
			System.out.println(msg);
		}
	}
}
