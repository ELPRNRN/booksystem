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
		//窗口初始化
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("图书管理系统");
		
		//布局
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
		
		
		//登录页面
		setTitle("读者登录/readerlogin");
		JLabel IDLabel= new JLabel("账号/account ID");
		loginPanel.add(IDLabel);
		JTextField IDTextField= new JTextField(1);
		loginPanel.add(IDTextField);
		JLabel pwdLabel= new JLabel("密码/password");
		loginPanel.add(pwdLabel);
		JPasswordField pwdTextField= new JPasswordField();
		loginPanel.add(pwdTextField);
		JButton loginButton=new JButton("登录/login");
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
					JOptionPane.showMessageDialog(null, "账号或密码错误，请输入正确的账号和密码", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginPanel.add(loginButton);
		loginButton.setUI(new BEButtonUI(). setNormalColor(BEButtonUI.NormalColor.normal));
		JButton toregisterButton=new JButton("我还没有账号/I haven't account yet");
		toregisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(cardPanel);
				setTitle("注册/register");
			}
		});
		loginPanel.add(toregisterButton);
		JButton toliButton=new JButton("管理员登录界面/administrator login page");
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
		
		//注册页面 
		registerPanel.setLayout(new BorderLayout());
		JPanel readerRegisterBorderPanel=new JPanel();
		registerPanel.add(readerRegisterBorderPanel);
		readerRegisterBorderPanel.setLayout(new BorderLayout());
		JPanel buttonGridPanel=new JPanel();
		readerRegisterBorderPanel.add(buttonGridPanel,BorderLayout.SOUTH);
		String[]readerStrings= {"ID","姓名","类型","性别"};
		MyComponent.MyPanel readerPanel=myComponent.new MyPanel(readerStrings, 1);
		String[] readertypeStrings= {"教师","学生"};
		readerPanel.setJComboBox(5, readertypeStrings);
		String[]genderStrings= {"男","女"};
		readerPanel.setJComboBox(7, genderStrings);
		JLabel pwdLabel2=new JLabel("密码");
		readerPanel.add(pwdLabel2);
		JPasswordField pwdField=new JPasswordField();
		readerPanel.add(pwdField);
		readerPanel.setLayout(new GridLayout(5,2));
		readerRegisterBorderPanel.add(readerPanel,BorderLayout.CENTER);
		JButton readerRegisterButton=new JButton("注册读者");
		buttonGridPanel.add(readerRegisterButton);
		readerRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Reader reader=new Reader(readerPanel.getText(1),readerPanel.getText(3),
						readerPanel.getText(5),readerPanel.getText(7),String.valueOf(pwdField.getPassword()));
				if(readerManage_Service.AddReader(reader)) {
					JOptionPane.showMessageDialog(null, "成功注册新账户，将返回登录界面", "注册成功", JOptionPane.PLAIN_MESSAGE);
					IDTextField.setText(readerPanel.getText(1));
					pwdTextField.setText(String.valueOf(pwdField.getPassword()));
				}
				else {
					JOptionPane.showMessageDialog(null, "请咨询管理人员寻求帮助", "注册失败", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JButton retologinButton=new JButton("返回读者登录界面/back to reader login page");
		buttonGridPanel.add(retologinButton);
		retologinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "login");
			}
		});
		buttonGridPanel.setLayout(new GridLayout(2,1));
		
		//管理员登录界面
		JLabel liIDLabel= new JLabel("账号/account ID");
		liloginPanel.add(liIDLabel);
		JTextField liIDTextField= new JTextField(1);
		liloginPanel.add(liIDTextField);
		JLabel lipwdLabel= new JLabel("密码/password");
		liloginPanel.add(lipwdLabel);
		JPasswordField lipwdTextField= new JPasswordField();
		liloginPanel.add(lipwdTextField);
		JButton liloginButton=new JButton("管理员登录/administrator login");
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
					JOptionPane.showMessageDialog(null, "管理员账号或密码错误", "登录失败", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		liloginPanel.add(liloginButton);
		liloginButton.setUI(new BEButtonUI(). setNormalColor(BEButtonUI.NormalColor.normal));
		JButton litologinButton=new JButton("返回读者登录界面/back to reader login page");
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
		
		//显示窗口
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
		initGlobalFontSetting(new Font("黑体", Font.PLAIN, 14)); 
	}
	
	//设置全局字体
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
