package com.smart.platform.gui.mde;

import java.awt.HeadlessException;

import javax.swing.JPopupMenu;

import com.smart.platform.gui.control.CFormFocusTraversalPolicy;
import com.smart.platform.gui.control.CFrame;
import com.smart.platform.gui.control.CSteFormWindow;
import com.smart.platform.gui.control.CStetoolbar;

/**
 * ֻ��ϸ��
 */
public abstract class CQueryDetailModel extends CDetailModel{

	protected CQueryDetailModel(CFrame frame, String title, CMdeModel mdemodel)
			throws HeadlessException {
		super(frame, title, mdemodel);
	}
/*
	@Override
	protected CSteFormWindow createFormwindow() {
        CQueryDetailFormWindow formwindow = new CQueryDetailFormWindow(this.getParentFrame(),form,mdemodel,title);
        formwindow.setFocusTraversalPolicy(new CFormFocusTraversalPolicy(mdemodel.getDetailModel()));
        return formwindow;
	}
*/
	@Override
	protected JPopupMenu createPopmenu() {
        return MdeControlFactory.createQueryPopupmenu(mdemodel);
	}

	@Override
	protected CStetoolbar createToolbar() {
		return null;
	}

	@Override
	public String getTablename() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSaveCommandString() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
