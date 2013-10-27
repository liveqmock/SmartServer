package com.inca.np.demo.ste;

import java.awt.Color;
import java.awt.Frame;
import java.awt.HeadlessException;

import javax.swing.JList;

import com.inca.np.auth.ClientUserManager;
import com.inca.np.auth.Userruninfo;
import com.inca.np.gui.control.CStehovEx;
import com.inca.np.gui.control.DBColumnDisplayInfo;
import com.inca.np.gui.control.DBTableModel;
import com.inca.np.gui.ste.CSteModel;
import com.inca.np.gui.ste.Querycond;
import com.inca.np.gui.ste.Querycondline;
import com.inca.np.gui.ste.SteDialog;
import com.inca.np.gui.ste.Steframe;
import com.inca.np.util.DefaultNPParam;

public class Pub_goods_dialog  extends CStehovEx{
    public Pub_goods_dialog() throws HeadlessException {
    	super();
    }




    public static void main(String[] argv){
        new DefaultNPParam();
		DefaultNPParam.debug=1;
		DefaultNPParam.develop=1;
		DefaultNPParam.debugdbip = "192.9.200.89";
		DefaultNPParam.debugdbpasswd = "database2";
		DefaultNPParam.debugdbsid = "pb";
		DefaultNPParam.debugdbusrname = "database2";
		DefaultNPParam.prodcontext = "npserver";
        
        
/*        MdeGeneralTool frm=new MdeGeneralTool();
        frm.pack();
        frm.setVisible(true);
        
        if(true)return;
*/        
        Userruninfo currentuser = new Userruninfo();
        currentuser.setUserid("23");
        ClientUserManager.setCurrentUser(currentuser);

        
        Pub_goods_dialog g=new Pub_goods_dialog();
        DBTableModel result=g.showDialog(null,"test ste hov");
        if(result==null){
        	System.out.println("null");
        }else{
        	System.out.println(result.getRowCount());
        }
        
    }

	@Override
	protected CSteModel createStemodel() {
        return new Pub_goods_ste(null);
	}

	@Override
	public Querycond getQuerycond() {
        Querycond querycond = new Querycond();

        DBColumnDisplayInfo colinfo=null;


        colinfo=new DBColumnDisplayInfo("opcode","varchar","������",false);
        colinfo.setUppercase(true);
        querycond.add(new Querycondline(querycond,colinfo));

        colinfo=new DBColumnDisplayInfo("goodspinyin","varchar","ƴ��",true);
        colinfo.setUppercase(true);
        querycond.add(new Querycondline(querycond,colinfo));

        colinfo=new DBColumnDisplayInfo("goodsname","varchar","Ʒ��",false);
        querycond.add(new Querycondline(querycond,colinfo));

        colinfo=new DBColumnDisplayInfo("goodsid","number","��ƷID",true);
        querycond.add(new Querycondline(querycond,colinfo));

        return querycond;
	}

	public String[] getColumns() {
		// TODO Auto-generated method stub
		return new String[]{"goodsid","goodsname","opcode"};
	}

	public String getDesc() {
		return "�����༭pub_goods hov";
	}

}