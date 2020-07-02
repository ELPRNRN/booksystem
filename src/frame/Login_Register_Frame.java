package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;

import frame.MyComponent.MyPanel;
import frame.MyComponent;
import model.Reader;
import service.LoginLogout_Service;
import service.ReaderManage_Service;

public class Login_Register_Frame extends JFrame{
	private static MyComponent myComponent=MyComponent.getMyComponent();
	private static ReaderManage_Service readerManage_Service=ReaderManage_Service.getInstance();
	public Login_Register_Frame() {
		//���ڳ�ʼ��
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("ͼ�����ϵͳ");
		
		//����
		CardLayout cardLayout=new CardLayout();
		JPanel cardPanel=new JPanel(cardLayout);
		add(cardPanel);
		JPanel loginPanel=new JPanel();
		loginPanel.setBackground(Color.white);
		cardPanel.add(loginPanel,"login");	
		JPanel registerPanel =new JPanel();
		registerPanel.setBackground(Color.white);
		cardPanel.add(registerPanel,"register");
		JPanel liloginPanel=new JPanel();
		liloginPanel.setBackground(Color.white);
		cardPanel.add(liloginPanel,"lilogin");
		
		
		//��¼ҳ��
		setTitle("���ߵ�¼/readerlogin");
		JLabel IDLabel= new JLabel("�˺�/account ID");
		loginPanel.add(IDLabel);
		JTextField IDTextField= new JTextField(1);
		loginPanel.add(IDTextField);
		JLabel pwdLabel= new JLabel("����/password");
		loginPanel.add(pwdLabel);
		JPasswordField pwdTextField= new JPasswordField();
		loginPanel.add(pwdTextField);
		JButton loginButton=new JButton("��¼/login");
		loginButton.setBorderPainted(false);
		loginButton.setBackground(new Color(46,188,79));
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ID=new String(IDTextField.getText());
				String pwd=new String(pwdTextField.getPassword());
				LoginLogout_Service loginLogout_Service = LoginLogout_Service.getInstance();
				if(loginLogout_Service.ReaderLogin(ID,pwd)) {
					Reader_Frame reader_Frame=new Reader_Frame(ID);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "�˺Ż����������������ȷ���˺ź�����", "����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginPanel.add(loginButton);
		loginButton.setUI(new BEButtonUI(). setNormalColor(BEButtonUI.NormalColor.normal));
		JButton toregisterButton=new JButton("�һ�û���˺�/I haven't account yet");
		toregisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(cardPanel);
				setTitle("ע��/register");
			}
		});
		loginPanel.add(toregisterButton);
		JButton toliButton=new JButton("����Ա��¼����/administrator login page");
		toliButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.next(cardPanel);
				cardLayout.next(cardPanel);
			}
		});
		loginPanel.add(toliButton);
		pwdTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loginButton.doClick();
			}
		});
		loginPanel.setLayout(new GridLayout(7,1));
		
		//ע��ҳ�� 
		registerPanel.setLayout(new BorderLayout());
		JPanel readerRegisterBorderPanel=new JPanel();
		registerPanel.add(readerRegisterBorderPanel);
		readerRegisterBorderPanel.setLayout(new BorderLayout());
		JPanel buttonGridPanel=new JPanel();
		readerRegisterBorderPanel.add(buttonGridPanel,BorderLayout.SOUTH);
		String[]readerStrings= {"ID","����","����","�Ա�"};
		MyComponent.MyPanel readerPanel=myComponent.new MyPanel(readerStrings, 1);
		String[] readertypeStrings= {"��ʦ","ѧ��"};
		readerPanel.setJComboBox(5, readertypeStrings);
		String[]genderStrings= {"��","Ů"};
		readerPanel.setJComboBox(7, genderStrings);
		JLabel pwdLabel2=new JLabel("����");
		readerPanel.add(pwdLabel2);
		JPasswordField pwdField=new JPasswordField();
		readerPanel.add(pwdField);
		readerPanel.setLayout(new GridLayout(5,2));
		readerRegisterBorderPanel.add(readerPanel,BorderLayout.CENTER);
		JButton readerRegisterButton=new JButton("ע�����");
		buttonGridPanel.add(readerRegisterButton);
		readerRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Reader reader=new Reader(readerPanel.getText(1),readerPanel.getText(3),
						readerPanel.getText(5),readerPanel.getText(7),String.valueOf(pwdField.getPassword()));
				if(readerManage_Service.AddReader(reader)) {
					JOptionPane.showMessageDialog(null, "�ɹ�ע�����˻��������ص�¼����", "ע��ɹ�", JOptionPane.PLAIN_MESSAGE);
					IDTextField.setText(readerPanel.getText(1));
					pwdTextField.setText(String.valueOf(pwdField.getPassword()));
				}
				else {
					JOptionPane.showMessageDialog(null, "����ѯ������ԱѰ�����", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JButton retologinButton=new JButton("���ض��ߵ�¼����/back to reader login page");
		buttonGridPanel.add(retologinButton);
		retologinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "login");
			}
		});
		buttonGridPanel.setLayout(new GridLayout(2,1));
		
		//����Ա��¼����
		JLabel liIDLabel= new JLabel("�˺�/account ID");
		liloginPanel.add(liIDLabel);
		JTextField liIDTextField= new JTextField(1);
		liloginPanel.add(liIDTextField);
		JLabel lipwdLabel= new JLabel("����/password");
		liloginPanel.add(lipwdLabel);
		JPasswordField lipwdTextField= new JPasswordField();
		liloginPanel.add(lipwdTextField);
		JButton liloginButton=new JButton("����Ա��¼/administrator login");
		liloginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ID=new String(liIDTextField.getText());
				String pwd=new String(lipwdTextField.getPassword());
				LoginLogout_Service loginLogout_Service = LoginLogout_Service.getInstance();
				if(loginLogout_Service.LibLogin(ID, pwd)) {
					Administrator_Frame administrator_Frame=new Administrator_Frame(ID);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "����Ա�˺Ż��������", "��¼ʧ��", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		liloginPanel.add(liloginButton);
		liloginButton.setUI(new BEButtonUI(). setNormalColor(BEButtonUI.NormalColor.normal));
		JButton litologinButton=new JButton("���ض��ߵ�¼����/back to reader login page");
		litologinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "login");
			}
		});
		liloginPanel.add(litologinButton);
		lipwdTextField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				liloginButton.doClick();
			}
		});
		liloginPanel.setLayout(new GridLayout(6,1));
		
		//��ʾ����
		pack();
		setBounds(100, 100, 400, 300);
	    setVisible(true);
	}
	
	public static void main(String[] args) {
		try
	    {
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
	        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
	        UIManager.put("RootPane.setupButtonVisible",false);
	    }
	    catch(Exception e)
	    {
	        //TODO exception
	    }
		Login_Register_Frame login_Register_Frame=new Login_Register_Frame();
		initGlobalFontSetting(new Font("����", Font.PLAIN, 14)); 
	}
	
	//����ȫ������
	public static void initGlobalFontSetting(Font fnt){
	    FontUIResource fontRes = new FontUIResource(fnt);
	    for(Enumeration keys = UIManager.getDefaults().keys(); keys.hasMoreElements();){
	        Object key = keys.nextElement();
	        Object value = UIManager.get(key);
	        if(value instanceof FontUIResource)
	            UIManager.put(key, fontRes);
	    }
	}
}
