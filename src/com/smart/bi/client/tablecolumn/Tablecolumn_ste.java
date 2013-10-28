package com.smart.bi.client.tablecolumn;

import com.smart.platform.gui.control.CFrame;
import com.smart.platform.gui.control.DBColumnDisplayInfo;
import com.smart.platform.gui.ste.CSteModel;

import java.awt.*;

/*����"�����ж���"�����༭Model*/
public class Tablecolumn_ste extends CSteModel{
	String reportid="";
	public Tablecolumn_ste(CFrame frame) throws HeadlessException {
		super(frame, "������");
	}

	public String getTablename() {
		return "npbi_basetable_column";
	}

	public String getSaveCommandString() {
		return "Tablecolumn_ste.��������ж���";
	}

	public String getReportid() {
		return reportid;
	}

	public void setReportid(String reportid) {
		this.reportid = reportid;
	}

	@Override
	protected int on_new(int row) {
		if(super.on_new(row)!=0)return -1;
		setItemValue(row, "reportid", reportid);
		return 0;
	}
	
}