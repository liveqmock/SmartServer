package com.smart.platform.gui.ste;

import com.smart.platform.gui.control.CDialog;
import com.smart.platform.gui.control.CScrollPane;

import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2007-3-28
 * Time: 15:39:20
 * ��ѯ�Ի���
 */
public class Stequerydialog extends CDialog{
    public Stequerydialog() throws HeadlessException {
        super();
        dialogInit();
    }

    public Stequerydialog(Frame owner, String title) throws HeadlessException {
        super(owner, title);
        dialogInit();
    }


    
    CScrollPane condpane=null;



}
