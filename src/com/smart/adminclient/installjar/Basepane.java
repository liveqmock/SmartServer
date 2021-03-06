package com.smart.adminclient.installjar;

import java.awt.HeadlessException;
import java.util.Vector;

import javax.swing.JPanel;

import com.smart.platform.gui.control.CFrame;
import com.smart.platform.gui.control.CStetoolbar;
import com.smart.platform.gui.control.DBColumnDisplayInfo;
import com.smart.platform.gui.ste.CSteModel;
import com.smart.server.install.Installinfo;

public class Basepane  extends JPanel{
	protected Vector<DBColumnDisplayInfo> cols=new Vector<DBColumnDisplayInfo>();
	
	protected CFrame frame;
	protected Installinfo installinfo;
	protected Ste ste=null;
	protected String title="";
	public Basepane(CFrame frame,Installinfo installinfo,String title){
		this.frame=frame;
		this.installinfo=installinfo;
		this.title=title;
		createCols();
		init();
		bind();
	}
	
	protected void createCols(){
		
	}
	
	protected void init(){
		ste=new Ste(frame,title);
		add(ste.getRootpanel());
	}
	
	protected void bind(){
	}
	
	public void rebind(){
	}
	
	protected class Ste extends CSteModel{
		public Ste(CFrame frame,String title) throws HeadlessException {
			super(frame, title);
			this.formcolumndisplayinfos=cols;
			this.setTableeditable(true);
		}

		@Override
		public String getTablename() {
			return "";
		}

		@Override
		public String getSaveCommandString() {
			return "";
		}

		@Override
		protected CStetoolbar createToolbar() {
			return new Edittoolbar(this);
		}
	}
}
