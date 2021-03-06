package com.smart.extension.mde;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.log4j.Category;

import com.smart.extension.ste.ZxmodifyUploadHelper;
import com.smart.platform.filedb.CurrentdirHelper;
import com.smart.platform.gui.control.CFrame;
import com.smart.platform.gui.control.CLinenoDisplayinfo;
import com.smart.platform.gui.control.DBColumnDisplayInfo;
import com.smart.platform.gui.mde.CMasterModel;
import com.smart.platform.gui.mde.CMdeModel;
import com.smart.platform.gui.ste.DBColumnInfoStoreHelp;
import com.smart.platform.util.ZipHelper;

public class CMasterModelGeneral extends CMasterModel {
	Category logger = Category.getInstance(CMasterModelGeneral.class);

	File zxzipfile = null;
	String viewname = "";

	public CMasterModelGeneral(CFrame frame, String title, CMdeModel mdemodel,String opid,
			String viewname, File zxzipfile) throws HeadlessException {
		super();
		this.mdemodel = mdemodel;
		this.frame = frame;
		this.title = title;
		this.viewname = viewname;
		this.zxzipfile = zxzipfile;
		setOpid(opid);

		/*
		 * // 加载专项 initInitdelegate();
		 */
		loadDBColumnInfos();

		if (ruleeng != null) {
			ruleeng.process(this, "设置下拉选择");
			ruleeng.process(this, "设置系统下拉选择");
			ruleeng.process(this, "设置SQL下拉选择");
			ruleeng.process(this, "表格可以编辑");
		}

		if (initdelegate != null) {
			initdelegate.on_init(this);
		}

		DBColumnDisplayInfo colinfo = getDBColumnDisplayInfo("filegroupid");
		useattachfile = colinfo != null;
		useap = true;
	}

	@Override
	protected void loadDBColumnInfos() {
		formcolumndisplayinfos = new Vector<DBColumnDisplayInfo>();
		formcolumndisplayinfos.add(new CLinenoDisplayinfo());
		File tempmodelfile = null;
		try {
			tempmodelfile = File.createTempFile("temp", ".model");
			String filename = "ste.model";
			if (!ZipHelper.extractFile(zxzipfile, filename, tempmodelfile)) {
				return;
			}
			DBColumnInfoStoreHelp.readFile(this, tempmodelfile);
			orgdbmodelcols=new Vector<DBColumnDisplayInfo>();
			orgdbmodelcols.addAll(formcolumndisplayinfos);
			
		} catch (Exception e) {
			logger.error("load ", e);
			errorMessage("错误", e.getMessage());
		} finally {
			if (tempmodelfile != null) {
				tempmodelfile.delete();
			}
		}

	}

	@Override
	public String getTablename() {
		// TODO Auto-generated method stub
		return viewname;
	}

	@Override
	public String getSaveCommandString() {
		return null;
	}
}
