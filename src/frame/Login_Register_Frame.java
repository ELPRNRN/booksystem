package frame;

import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.LoginLogout_Service;
import javax.swing.*;

public class Login_Register_Frame extends JFrame{
	
	public Login_Register_Frame() {
		//���ڳ�ʼ��
		setBounds(100, 100, 996, 699);
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("ͼ�����ϵͳ");
		
		//����
		CardLayout cardLayout=new CardLayout();
		JPanel cardPanel=new JPanel(cardLayout);
		add(cardPanel);
		JPanel loginPanel=new JPanel();
		cardPanel.add(loginPanel);	
		JPanel registerPanel =new JPanel();
		cardPanel.add(registerPanel);
		
		//��¼ҳ��
		JLabel IDLabel= new JLabel("�˺�/account ID");
		loginPanel.add(IDLabel);
		JTextField IDTextField= new JTextField(1);
		loginPanel.add(IDTextField);
		JLabel pwdLabel= new JLabel("����/password");
		loginPanel.add(pwdLabel);
		JTextField pwdTextField= new JTextField(1);
		loginPanel.add(pwdTextField);
		JButton loginButton=new JButton("��¼/login");
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
					JOptionPane.showMessageDialog(null, "�˺Ż����������������ȷ���˺ź�����", "����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginPanel.add(loginButton);
		JButton toregisterButton=new JButton("�һ�û���˺�/I haven't account yet");
		toregisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.next(cardPanel);
			}
		});
		loginPanel.add(toregisterButton);
		loginPanel.setLayout(new GridLayout(6,1));
		
		//ע��ҳ�� 
		JLabel rIDLabel= new JLabel("�˺�/account ID");
		registerPanel.add(rIDLabel);
		JTextField rIDTextField= new JTextField(1);
		registerPanel.add(rIDTextField);
		JLabel rpwdLabel= new JLabel("����/password");
		registerPanel.add(rpwdLabel);
		JTextField rpwdTextField= new JTextField(1);
		registerPanel.add(rpwdTextField);
		JButton registerButton=new JButton("ע��/register");
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
					JOptionPane.showMessageDialog(null, "ע��ʧ��", "����", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		JButton tologinButton=new JButton("���ص�¼����/back to login page");
		registerPanel.add(registerButton);
		tologinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.previous(cardPanel);
			}
		});
		registerPanel.add(tologinButton);
		registerPanel.setLayout(new GridLayout(6,1));
		
		//��ʾ����
		pack();
	    setVisible(true);
	}
	
	public static void main(String[] args) {
		Login_Register_Frame login_Register_Frame=new Login_Register_Frame();
	}
}
