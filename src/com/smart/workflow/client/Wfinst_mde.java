package com.smart.workflow.client;
import com.smart.extension.mde.CMdeModelAp;
import com.smart.platform.communicate.ClientRequest;
import com.smart.platform.communicate.ParamCommand;
import com.smart.platform.communicate.ServerResponse;
import com.smart.platform.gui.control.CFrame;
import com.smart.platform.gui.mde.CDetailModel;
import com.smart.platform.gui.mde.CMasterModel;
import com.smart.platform.gui.mde.CMdeModel;
import com.smart.platform.util.SendHelper;

/*功能"流程实例"总单细目Model*/
public class Wfinst_mde extends CMdeModelAp{
	public Wfinst_mde(CFrame frame, String title) {
		super(frame, title);
	}
	protected CMasterModel createMastermodel() {
		return new Wfinst_master(frame,this);
	}
	protected CDetailModel createDetailmodel() {
		return new Wfinst_detail(frame,this);
	}
	public String getMasterRelatecolname() {
		return "wfinstanceid";
	}
	public String getDetailRelatecolname() {
		return "wfinstanceid";
	}
	public String getSaveCommandString() {
		return "Wfinst_mde.保存流程实例";
	}
	@Override
	protected int on_beforemodifymaster(int row) {
		return 0;
	}
	@Override
	protected int on_actionPerformed(String command) {
		if(command.equals("重新启动流程")){
			restartWf();
			return 0;
		}
		return super.on_actionPerformed(command);
	}
	
	/**
	 * 重新启动流程
	 */
	void restartWf() {
		int row=getMasterModel().getRow();
		if(row<0){
			warnMessage("提示","请选择一个实例");
			return;
		}
		
		String wfinstanceid=getMasterModel().getItemValue(row, "wfinstanceid");
		ClientRequest req=new ClientRequest("Wfinst_mde.重新启动流程实例");
		ParamCommand pcmd=new ParamCommand();
		pcmd.addParam("wfinstanceid",wfinstanceid);
		req.addCommand(pcmd);
		try {
			ServerResponse resp=SendHelper.sendRequest(req);
			String resps=resp.getCommand();
			if(!resps.startsWith("+OK")){
				errorMessage("错误",resps);
				return;
			}
			infoMessage("提示","已重新启动这个流程");
		} catch (Exception e) {
			errorMessage("错误", e.getMessage());
			return;
		}
		
	}
	
}
