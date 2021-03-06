package com.smart.bi.client.tablecolumn;

import com.smart.platform.gui.control.CFrame;
import com.smart.platform.gui.control.DBColumnDisplayInfo;
import com.smart.platform.gui.ste.CSteModel;

import java.awt.*;

/*功能"基表列定义"单表编辑Model*/
public class Tablecolumn_ste extends CSteModel{
	String reportid="";
	public Tablecolumn_ste(CFrame frame) throws HeadlessException {
		super(frame, "基表列");
	}

	public String getTablename() {
		return "npbi_basetable_column";
	}

	public String getSaveCommandString() {
		return "Tablecolumn_ste.保存基表列定义";
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
