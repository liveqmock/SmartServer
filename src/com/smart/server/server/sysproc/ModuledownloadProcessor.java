package com.smart.server.server.sysproc;

import java.io.File;
import java.util.Enumeration;

import com.smart.platform.auth.Userruninfo;
import com.smart.platform.communicate.ClientRequest;
import com.smart.platform.communicate.DataCommand;
import com.smart.platform.communicate.ServerResponse;
import com.smart.platform.communicate.StringCommand;
import com.smart.platform.server.RequestProcessorAdapter;
import com.smart.platform.util.MD5Helper;
import com.smart.server.prod.ModuleManager;
import com.smart.server.prod.Moduleinfo;

/**
 * 下载模块信息 产品 模块 模块JAR文件名和MD5
 * 
 * @author Administrator
 * 
 */
public class ModuledownloadProcessor extends RequestProcessorAdapter {
	static String COMMAND = "npclient:downloadmodules";

	@Override
	public int process(Userruninfo userinfo, ClientRequest req,
			ServerResponse resp) throws Exception {

		if (!COMMAND.equals(req.getCommand())) {
			return -1;
		}

		ModuleManager mm = ModuleManager.getInst();
		ModuleDbmodel moduledbmodel = new ModuleDbmodel();
		Enumeration<Moduleinfo> en = mm.getModules().elements();
		while (en.hasMoreElements()) {
			Moduleinfo minfo = en.nextElement();
			int r = moduledbmodel.getRowCount();
			moduledbmodel.appendRow();
			moduledbmodel.setItemValue(r, "prodname", minfo.prodname);
			moduledbmodel.setItemValue(r, "modulename", minfo.modulename);
			moduledbmodel.setItemValue(r, "engname", minfo.moduleengname);
			moduledbmodel.setItemValue(r, "version", minfo.version);
			moduledbmodel.setItemValue(r, "clientjar", minfo.clientjar);
			moduledbmodel.setItemValue(r, "clientjarmd5", minfo.clientjarmd5);
		}
		//加入npbichart
		int r = moduledbmodel.getRowCount();
		moduledbmodel.appendRow();
		moduledbmodel.setItemValue(r, "prodname", "npserver");
		moduledbmodel.setItemValue(r, "modulename", "npbichart");
		moduledbmodel.setItemValue(r, "engname", "npbichart");
		moduledbmodel.setItemValue(r, "version", "2.3.1");
		moduledbmodel.setItemValue(r, "clientjar", "npbichart-2.3.1.jar");
		
		File jarfile=new File(CurrentappHelper.getLibrarydir(),"npbichart-2.3.1.jar");
		String md5=MD5Helper.MD5(jarfile);
		moduledbmodel.setItemValue(r, "clientjarmd5", md5);
		
		resp.addCommand(new StringCommand("+OK"));
		DataCommand dcmd = new DataCommand();
		dcmd.setDbmodel(moduledbmodel);
		resp.addCommand(dcmd);
		return 0;
	}

}
