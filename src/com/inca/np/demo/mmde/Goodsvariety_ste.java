package com.inca.np.demo.mmde;

import java.awt.HeadlessException;

import com.inca.np.gui.control.CFrame;
import com.inca.np.gui.ste.CSteModel;

public class Goodsvariety_ste extends CSteModel{

	public Goodsvariety_ste() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Goodsvariety_ste(CFrame frame, String title)
			throws HeadlessException {
		super(frame, title);
	}

	@Override
	public String getTablename() {
		return "pub_goods_variety";
		
	}

	@Override
	public String getSaveCommandString() {
		return "npserver.demo.����Ʒ��";
	}

}