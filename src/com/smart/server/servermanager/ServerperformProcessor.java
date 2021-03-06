package com.smart.server.servermanager;

import com.smart.platform.auth.Userruninfo;
import com.smart.platform.communicate.ClientRequest;
import com.smart.platform.communicate.ParamCommand;
import com.smart.platform.communicate.ServerResponse;
import com.smart.platform.communicate.StringCommand;
import com.smart.platform.server.RequestProcessorAdapter;
import com.smart.server.server.Server;

/**
 * 查询返回Server所收到请求数,成功处理数量,用时.
 * 
 * @author user
 * 
 */
public class ServerperformProcessor extends RequestProcessorAdapter {
	static String SVRCOMMAND = "npserver:serverperform";
	static String SVRCOMMAND1 = "npserver:resetserverperform";

	public int process(Userruninfo userinfo, ClientRequest req,
			ServerResponse resp) throws Exception {
		if (!SVRCOMMAND.equals(req.getCommand())
				&& !SVRCOMMAND1.equals(req.getCommand())) {
			return -1;
		}

		resp.addCommand(new StringCommand("+OK"));

		Server svr = Server.getInstance();
		if (req.getCommand().equals(SVRCOMMAND)) {

			ParamCommand pcmd = new ParamCommand();
			resp.addCommand(pcmd);
			pcmd.addParam("starttime",String.valueOf(svr.getStarttime()));
			pcmd.addParam("nowtime",String.valueOf(System.currentTimeMillis()));
			pcmd
					.addParam("requestcount", String.valueOf(svr
							.getRequestcount()));
			pcmd
					.addParam("processcount", String.valueOf(svr
							.getProcesscount()));
			pcmd.addParam("processms", String.valueOf(svr.getProcessms()));

		} else if (req.getCommand().equals(SVRCOMMAND1)) {
			svr.setRequestcount(0);
			svr.setProcesscount(0);
			svr.setProcessms(0);
			svr.resetStarttime();
		}
		return 0;
	}
}
