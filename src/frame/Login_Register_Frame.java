package frame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login_Register_Frame extends JFrame{
	
	public Login_Register_Frame() {
		//窗口初始化
		setBounds(100, 100, 996, 699);
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
		JTextField pwdTextField= new JTextField(1);
		loginPanel.add(pwdTextField);
		JButton loginButton=new JButton("登录/login");
		loginButton.setBorderPainted(false);
		loginButton.setBackground(new Color(46,188,79));
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ID=new String(IDTextField.getText());
				String pwd=new String(pwdTextField.getText());
				//LoginLogout_Service loginLogout_Service = LoginLogout_Service.getInstance();
				if(false) {//loginLogout_Service.ReaderLogin(ID,pwd)
					//login success
				}
				else {
					JOptionPane.showMessageDialog(null, "账号或密码错误，请输入正确的账号和密码", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginPanel.add(loginButton);
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
		loginPanel.setLayout(new GridLayout(7,1));
		
		//注册页面 
		JLabel rIDLabel= new JLabel("账号/account ID");
		registerPanel.add(rIDLabel);
		JTextField rIDTextField= new JTextField(1);
		registerPanel.add(rIDTextField);
		JLabel rpwdLabel= new JLabel("密码/password");
		registerPanel.add(rpwdLabel);
		JTextField rpwdTextField= new JTextField(1);
		registerPanel.add(rpwdTextField);
		JButton registerButton=new JButton("注册/register");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ID=new String(rIDTextField.getText());
				String pwd=new String(rpwdTextField.getText());
				//LoginLogout_Service loginLogout_Service = LoginLogout_Service.getInstance();
				if(false) {//loginLogout_Service.ReaderLogin(ID,pwd)
					//login success
				}
				else {
					JOptionPane.showMessageDialog(null, "注册失败", "错误", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		registerPanel.add(registerButton);
		JButton retologinButton=new JButton("返回读者登录界面/back to reader login page");
		retologinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "login");
			}
		});
		registerPanel.add(retologinButton);
		registerPanel.setLayout(new GridLayout(6,1));
		
		//管理员登录界面
		JLabel liIDLabel= new JLabel("账号/account ID");
		liloginPanel.add(liIDLabel);
		JTextField liIDTextField= new JTextField(1);
		liloginPanel.add(liIDTextField);
		JLabel lipwdLabel= new JLabel("密码/password");
		liloginPanel.add(lipwdLabel);
		JTextField lipwdTextField= new JTextField(1);
		liloginPanel.add(lipwdTextField);
		JButton liloginButton=new JButton("管理员登录/administrator login");
		liloginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String ID=new String(liIDTextField.getText());
				String pwd=new String(lipwdTextField.getText());
				//LoginLogout_Service loginLogout_Service = LoginLogout_Service.getInstance();
				if(false) {//loginLogout_Service.ReaderLogin(ID,pwd)
					//login success
				}
				else {
					JOptionPane.showMessageDialog(null, "管理员账号或密码错误", "登录失败", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		liloginPanel.add(liloginButton);
		JButton litologinButton=new JButton("返回读者登录界面/back to reader login page");
		litologinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "login");
			}
		});
		liloginPanel.add(litologinButton);
		liloginPanel.setLayout(new GridLayout(6,1));
		
		//显示窗口
		pack();
	    setVisible(true);
	}
	
	
	    public static void main(String[] args) {
		Login_Register_Frame login_Register_Frame=new Login_Register_Frame();
	}
	
}
