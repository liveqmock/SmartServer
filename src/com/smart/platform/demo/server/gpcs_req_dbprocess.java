package com.smart.platform.demo.server;

import com.smart.platform.communicate.RecordTrunk;
import com.smart.platform.demo.mde.gpcs_req_mdemodel;
import com.smart.platform.gui.control.DBTableModel;
import com.smart.platform.gui.mde.CMdeModel;
import com.smart.platform.server.process.MdeProcessor;

import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2007-4-18
 * Time: 17:21:12
 * To change this template use File | Settings | File Templates.
 */
public class gpcs_req_dbprocess extends MdeProcessor{
    protected CMdeModel getMdeModel() {
        return new gpcs_req_mdemodel(null,"");
    }

    protected String getMastertablename() {
        return "gpcs_reqsupply";
    }

    protected String getDetailtablename() {
        return "gpcs_reqsupplydtl";
    }


    protected void on_beforesave(DBTableModel masterdbmodel, DBTableModel detaildbmodel) throws Exception {
        SimpleDateFormat datefmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(int r=0;r<masterdbmodel.getRowCount();r++){
            if(masterdbmodel.getdbStatus(r) == RecordTrunk.DBSTATUS_NEW){
                masterdbmodel.setItemValue(r,"PLACEPOINTID","250");
                masterdbmodel.setItemValue(r,"REQSTORAGEID","250");
                masterdbmodel.setItemValue(r,"REQMANID","0");
                masterdbmodel.setItemValue(r,"REQDATE",datefmt.format(new Date()));
                masterdbmodel.setItemValue(r,"REQLASTDATE",datefmt.format(new Date()));
            }
        }
    }

}
