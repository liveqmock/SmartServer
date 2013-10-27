package com.inca.npserver.pushplat.client;

import java.awt.HeadlessException;

import com.inca.np.gui.mde.CMdeModel;
import com.inca.np.gui.mde.MdeFrame;
import com.inca.np.util.DefaultNPParam;

public class Rolepush_frm extends MdeFrame{

	public Rolepush_frm() throws HeadlessException {
		super("��ɫ���Ͷ���");
	}

	@Override
	protected CMdeModel getMdeModel() {
		return new Rolepush_mde(this,"��ɫ����");
	}

	public static void main(String[] args) {
		DefaultNPParam.debug=1;
		DefaultNPParam.develop=1;

		DefaultNPParam.debugdbip = "192.9.200.89";
		DefaultNPParam.debugdbpasswd = "database2";
		DefaultNPParam.debugdbsid = "pb";
		DefaultNPParam.debugdbusrname = "database2";
		DefaultNPParam.prodcontext = "npserver";
		
		Rolepush_frm frm=new Rolepush_frm();
		frm.pack();
		frm.setVisible(true);
		
	}
}