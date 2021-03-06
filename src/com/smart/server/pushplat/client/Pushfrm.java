package com.smart.server.pushplat.client;

import java.awt.HeadlessException;

import com.smart.platform.gui.ste.CSteModel;
import com.smart.platform.gui.ste.Steframe;
import com.smart.platform.util.DefaultNPParam;

public class Pushfrm extends Steframe{

	public Pushfrm() throws HeadlessException {
		super("推送定义(开发)");
	}

	@Override
	protected CSteModel getStemodel() {
		return new Pushste(this,"推送");
	}
	
	public static void main(String[] args) {
		DefaultNPParam.debug=1;
		DefaultNPParam.develop=1;
		
		DefaultNPParam.debugdbip = "192.9.200.89";
		DefaultNPParam.debugdbpasswd = "database2";
		DefaultNPParam.debugdbsid = "pb";
		DefaultNPParam.debugdbusrname = "database2";
		DefaultNPParam.prodcontext = "npserver";

		Pushfrm f=new Pushfrm();
		f.pack();
		f.setVisible(true);
	}

}
