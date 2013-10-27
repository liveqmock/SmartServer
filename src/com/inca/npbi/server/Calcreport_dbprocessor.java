package com.inca.npbi.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;


import org.apache.log4j.Category;

import com.inca.np.auth.Userruninfo;
import com.inca.np.communicate.ClientRequest;
import com.inca.np.communicate.DataCommand;
import com.inca.np.communicate.ServerResponse;
import com.inca.np.communicate.StringCommand;
import com.inca.np.gui.control.DBTableModel;
import com.inca.np.server.RequestProcessorAdapter;

/**
 * ���㱨��ʵ��
 * 
 * @author user
 * 
 */
public class Calcreport_dbprocessor extends RequestProcessorAdapter {
	static String COMMAND = "npbi.���㱨��ʵ��";
	Category logger = Category.getInstance(Calcreport_dbprocessor.class);

	@Override
	public int process(Userruninfo userinfo, ClientRequest req,
			ServerResponse resp) throws Exception {
		if (!COMMAND.equals(req.getCommand()))
			return -1;
		DataCommand dcmd = (DataCommand) req.commandAt(1);
		Vector<String> instanceidmap = new Vector<String>();
		DBTableModel dm = dcmd.getDbmodel();
		for (int i = 0; i < dm.getRowCount(); i++) {
			String instanceid = dm.getItemValue(i, "instanceid");
			instanceidmap.add(instanceid);
		}
		Runthread t=new Runthread(instanceidmap);
		t.start();
		resp.addCommand(new StringCommand("+OK"));
		return 0;
	}

	class Runthread extends Thread {
		Vector<String> instanceidmap = null;

		public Runthread(Vector<String> instanceidmap) {
			super();
			this.instanceidmap = instanceidmap;
		}

		public void run() {
			Connection con = null;
			try {
				con = Dsengine.getInstance().getConnection();
				Enumeration<String> en = instanceidmap.elements();
				while (en.hasMoreElements()) {
					String instanceid = en.nextElement();
					Dsengine.getInstance().runReport(con, instanceid);
				}

			} catch (Exception e) {
				logger.error("Error", e);

			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}
}