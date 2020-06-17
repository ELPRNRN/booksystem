package frame;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.LoginLogout_Service;
import javax.swing.*;

public class Login_Register_Frame extends JFrame{
	
	public Login_Register_Frame() {
		//窗口初始化
		setBounds(100, 100, 996, 699);
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("图书管理系统");
		
		//布局
		CardLayout cardLayout=new CardLayout();
		JPanel cardPanel=new JPanel(cardLayout);
		add(cardPanel);
		JPanel loginPanel=new JPanel();
		cardPanel.add(loginPanel);	
		JPanel registerPanel =new JPanel();
		cardPanel.add(registerPanel);
		
		//登录页面
		JLabel IDLabel= new JLabel("账号/account ID");
		loginPanel.add(IDLabel);
		JTextField IDTextField= new JTextField(1);
		loginPanel.add(IDTextField);
		JLabel pwdLabel= new JLabel("密码/password");
		loginPanel.add(pwdLabel);
		JTextField pwdTextField= new JTextField(1);
		loginPanel.add(pwdTextField);
		JButton loginButton=new JButton("登录/login");
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
			}
		});
		loginPanel.add(toregisterButton);
		loginPanel.setLayout(new GridLayout(6,1));
		
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
		JButton tologinButton=new JButton("返回登录界面/back to login page");
		registerPanel.add(registerButton);
		tologinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.previous(cardPanel);
			}
		});
		registerPanel.add(tologinButton);
		registerPanel.setLayout(new GridLayout(6,1));
		
		//显示窗口
		pack();
	    setVisible(true);
	}
	
	public static void main(String[] args) {
		Login_Register_Frame login_Register_Frame=new Login_Register_Frame();
	}
}
