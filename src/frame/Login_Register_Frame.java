package frame;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Login_Register_Frame extends JFrame{
	
	public Login_Register_Frame() {
		//���ڳ�ʼ��
		setBounds(100, 100, 996, 699);
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
		JTextField pwdTextField= new JTextField(1);
		loginPanel.add(pwdTextField);
		JButton loginButton=new JButton("��¼/login");
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
		loginPanel.setLayout(new GridLayout(7,1));
		
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
		registerPanel.add(registerButton);
		JButton retologinButton=new JButton("���ض��ߵ�¼����/back to reader login page");
		retologinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "login");
			}
		});
		registerPanel.add(retologinButton);
		registerPanel.setLayout(new GridLayout(6,1));
		
		//����Ա��¼����
		JLabel liIDLabel= new JLabel("�˺�/account ID");
		liloginPanel.add(liIDLabel);
		JTextField liIDTextField= new JTextField(1);
		liloginPanel.add(liIDTextField);
		JLabel lipwdLabel= new JLabel("����/password");
		liloginPanel.add(lipwdLabel);
		JTextField lipwdTextField= new JTextField(1);
		liloginPanel.add(lipwdTextField);
		JButton liloginButton=new JButton("����Ա��¼/administrator login");
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
					JOptionPane.showMessageDialog(null, "����Ա�˺Ż��������", "��¼ʧ��", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		liloginPanel.add(liloginButton);
		JButton litologinButton=new JButton("���ض��ߵ�¼����/back to reader login page");
		litologinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "login");
			}
		});
		liloginPanel.add(litologinButton);
		liloginPanel.setLayout(new GridLayout(6,1));
		
		//��ʾ����
		pack();
	    setVisible(true);
	}
	
	
	    public static void main(String[] args) {
		Login_Register_Frame login_Register_Frame=new Login_Register_Frame();
	}
	
}
