package frame;

import java.awt.CardLayout;
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
		JLabel IDLabel= new JLabel("�˺�/account ID");
		loginPanel.add(IDLabel);
		JTextField IDTextField= new JTextField(1);
		loginPanel.add(IDTextField);
		JLabel pwdLabel= new JLabel("����/password");
		loginPanel.add(pwdLabel);
		JTextField pwdTextField= new JTextField(1);
		loginPanel.add(pwdTextField);
		JButton loginButton=new JButton();
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
		
		//��ʾ����
		pack();
	    setVisible(true);
	}
	
	public static void main(String[] args) {
		Login_Register_Frame login_Register_Frame=new Login_Register_Frame();
	}
}
