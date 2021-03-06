package com.smart.platform.auth;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import org.apache.log4j.Category;

import com.smart.platform.communicate.ClientRequest;
import com.smart.platform.communicate.ParamCommand;
import com.smart.platform.communicate.ServerResponse;
import com.smart.platform.communicate.StringCommand;
import com.smart.platform.gui.control.CDialog;
import com.smart.platform.util.MD5Helper;
import com.smart.platform.util.SendHelper;

/**
 * ������
 * @author Administrator
 *
 */
public class RepasswordDialog extends CDialog{

	private JPasswordField textoldpassword;
	private JPasswordField textnewpassword;
	private JPasswordField textnewpassword1;
	private Category logger = Category.getInstance(RepasswordDialog.class);

	public RepasswordDialog(JFrame parent)
			throws HeadlessException {
		super(parent, "�޸�����", true);
		initDialog();
		localScreenCenter();
		this.setDefaultCloseOperation(CDialog.DISPOSE_ON_CLOSE);
	}
	
	
	void initDialog(){
		JPanel jp=new JPanel();
		Dimension textsize = new Dimension(114, 27);
		GridBagLayout g = new GridBagLayout();
		jp.setLayout(g);

		JLabel lb = new JLabel("ԭ����");
		lb.setHorizontalAlignment(JLabel.RIGHT);
		jp.add(lb, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,
						12, 5, 5), 0, 0));

		textoldpassword = new JPasswordField(10);
		addEnterkeyTraver(textoldpassword);
		textoldpassword.setPreferredSize(textsize);
		jp.add(textoldpassword, new GridBagConstraints(1, 2, 3, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 10, 5, 12), 0, 0));
		

		lb = new JLabel("������");
		lb.setHorizontalAlignment(JLabel.RIGHT);
		jp.add(lb, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,
						12, 5, 5), 0, 0));

		textnewpassword = new JPasswordField(10);
		textnewpassword.setText("");
		textnewpassword.setPreferredSize(textsize);
		addEnterkeyTraver(textnewpassword);
		jp.add(textnewpassword, new GridBagConstraints(1, 3, 3, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 10, 5, 12), 0, 0));

		lb = new JLabel("������");
		lb.setHorizontalAlignment(JLabel.RIGHT);
		jp.add(lb, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5,
						12, 5, 5), 0, 0));

		textnewpassword1 = new JPasswordField(10);
		textnewpassword1.setText("");
		textnewpassword1.setPreferredSize(textsize);
		addEnterkeyTraver(textnewpassword1);
		jp.add(textnewpassword1, new GridBagConstraints(1, 4, 3, 1, 1.0, 0.0,
				GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL,
				new Insets(5, 10, 5, 12), 0, 0));
		
		JPanel toolbar = new JPanel();
		toolbar.setLayout(new FlowLayout());

		JButton btnok = new JButton("�޸�����");
		btnok.setActionCommand("repassword");
		btnok.addActionListener(new ActionHandle());
		toolbar.add(btnok);
		addEnterkeyConfirm(btnok);

		JButton btncancel = new JButton("ȡ��");
		btncancel.setActionCommand("cancel");
		btncancel.addActionListener(new ActionHandle());
		toolbar.add(btncancel);
		addEnterkeyTraver(btncancel);


		

		Container  cp = this.getContentPane();
		cp.setLayout(new BorderLayout());
		cp.add(jp, BorderLayout.CENTER);
		cp.add(toolbar, BorderLayout.SOUTH);
	}

	
	public static void main(String[] argv){
		RepasswordDialog dlg=new RepasswordDialog(null);
		dlg.pack();
		dlg.setVisible(true);
	}
	
	
	void repassword(){
		String password=new String(textoldpassword.getPassword());
		String newpassword=new String(textnewpassword.getPassword());
		String newpassword1=new String(textnewpassword1.getPassword());
		
		if(!newpassword.equals(newpassword1)){
			JOptionPane.showMessageDialog(this, "�������벻һ��","��ʾ",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		ClientRequest req=new ClientRequest();
		req.addCommand(new StringCommand("npclient:��������"));
		
		ParamCommand param=new ParamCommand();
		param.addParam("password",MD5Helper.MD5(password));
		param.addParam("newpassword",MD5Helper.MD5(newpassword));
		req.addCommand(param);
		
		ServerResponse svrresp=null;
		
		try{
			svrresp = SendHelper.sendRequest(req);
		}catch(Exception e){
			logger.error("ERROR",e);
			JOptionPane.showMessageDialog(this, "ʧ��:"+e.getMessage(),"ʧ��",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		StringCommand respcmd=(StringCommand)svrresp.commandAt(0);
		String respstr=respcmd.getString();
		if(respstr.startsWith("+OK")){
			JOptionPane.showMessageDialog(this, "�����޸ĳɹ�","�ɹ�",JOptionPane.INFORMATION_MESSAGE);
			dispose();
		}else{
			JOptionPane.showMessageDialog(this, "ʧ��:"+respstr,"ʧ��",JOptionPane.ERROR_MESSAGE);
		}
				
	}
	
	class ActionHandle implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("repassword")) {
				repassword();
			}else{
				RepasswordDialog.this.dispose();
			}
		}
	}

	protected void localScreenCenter() {
		Dimension screensize = this.getToolkit().getScreenSize();
		Dimension size = this.getPreferredSize();
		double x = (screensize.getWidth() - size.getWidth()) / 2;
		double y = (screensize.getHeight() - size.getHeight()) / 2;

		this.setLocation((int) x, (int) y);
	}

}
