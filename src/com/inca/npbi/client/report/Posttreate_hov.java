package com.inca.npbi.client.report;

import java.awt.Frame;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;

import com.inca.np.gui.control.CMultiHov;
import com.inca.np.gui.control.DBColumnDisplayInfo;
import com.inca.np.gui.control.DBTableModel;
import com.inca.np.gui.ste.Querycond;

/**
 * ����hov
 * @author user
 *
 */
public class Posttreate_hov extends CMultiHov{

	@Override
	protected TableModel createTablemodel() {
		Vector<DBColumnDisplayInfo> cols=new Vector<DBColumnDisplayInfo>();
		DBColumnDisplayInfo col;
		col=new DBColumnDisplayInfo("name","varchar","����");
		cols.add(col);
		col=new DBColumnDisplayInfo("desc","varchar","˵��");
		cols.add(col);
		col=new DBColumnDisplayInfo("expr","varchar","����ʽ");
		cols.add(col);
		return new DBTableModel(cols);
	}

	@Override
	public String getDefaultsql() {
		return "";
	}

	@Override
	public Querycond getQuerycond() {
		Querycond cond=new Querycond();
		return cond;
	}

	public String[] getColumns() {
		return new String[]{"expr"};
	}

	public String getDesc() {
		return "����hov";
	}

	String msg[][]={
			{"����","sql���order by�Ӿ�.","sort:��1 asc,��2 desc"},
			{"����","�����,����ǰn����,��ǰ100��.","top:100"},
	};
	
	@Override
	protected void doQuery() {
		DBTableModel dm=(DBTableModel) table.getModel();
		dm.clearAll();
		for(int i=0;i<msg.length;i++){
			int newrow=dm.getRowCount()-1;
			dm.appendRow();
			dm.setItemValue(newrow, "name", msg[i][0]);
			dm.setItemValue(newrow, "desc", msg[i][1]);
			dm.setItemValue(newrow, "expr", msg[i][2]);
		}
		dlgtable.tableChanged(new TableModelEvent(dlgtable.getModel()));
		dlgtable.autoSize();
		dlgtable.getSelectionModel().setSelectionInterval(0, 0);
	}

	public static void main(String[] args) {
		Posttreate_hov hov=new Posttreate_hov();
		hov.showDialog((Frame)null,"");
	}

	@Override
	protected boolean autoSelect() {
		return true;
	}
	
}