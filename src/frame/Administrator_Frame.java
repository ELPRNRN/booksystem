package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import service.BookManage_Service;

public class Administrator_Frame extends JFrame{
	String m_ID;
	public Administrator_Frame(String ID) {
		//�����ʼ��
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("ͼ�����ϵͳ����Ա����");
		m_ID=new String(ID);
		BorderLayout borderLayout=new BorderLayout();
		setLayout(borderLayout);
		JPanel cardPanel=new JPanel();
		CardLayout cardLayout=new CardLayout();
		cardPanel.setLayout(cardLayout);
		add(cardPanel,BorderLayout.CENTER);
		JPanel blankPanel=new JPanel();
		cardPanel.add(blankPanel,"blank");
		JPanel bookManagePanel=new JPanel();
		cardPanel.add(bookManagePanel,"book manage");
		JPanel borrowManagePanel=new JPanel();
		cardPanel.add(borrowManagePanel,"borrow manage");
		JPanel readerManagePanel=new JPanel();
		cardPanel.add(readerManagePanel,"reader manage");
		JPanel addBookPanel=new JPanel();
		cardPanel.add(addBookPanel,"add book");
		JPanel readerRegisterPanel=new JPanel();
		cardPanel.add(readerRegisterPanel,"reader register");
		
		//�˵����
		JPanel manuPanel=new JPanel();
		Box box=Box.createVerticalBox();
		manuPanel.add(box);
		add(manuPanel,BorderLayout.WEST);
		JButton toBookManageButton=new JButton("ͼ�����/Book Manage");
		toBookManageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "book manage");
			}
		});
		box.add(toBookManageButton);
		JButton toBorrowManageButton=new JButton("���Ĺ���/Borrow Manage");
		toBorrowManageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "borrow manage");
			}
		});
		box.add(toBorrowManageButton);
		JButton toReaderManageButton=new JButton("���߹���/Reader Manage");
		toReaderManageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "reader manage");
			}
		});
		box.add(toReaderManageButton);
		JButton toAddBookButton=new JButton("���ͼ��/Add Book");
		toAddBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "add book");
			}
		});
		box.add(toAddBookButton);
		JButton toReaderRegisterButton=new JButton("����ע��/Reader Register");
		toReaderRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "reader register");
			}
		});
		box.add(toReaderRegisterButton);
		
		//�����
		blankPanel.setBackground(Color.white);
		
		//ͼ��������
		JLabel bookidLabel=new JLabel("��ID��/book ID");
		bookManagePanel.add(bookidLabel);
		JTextField bookidTextField=new JTextField(10);
		bookManagePanel.add(bookidTextField);
		JLabel bookNameLabel=new JLabel("����/book name");
		bookManagePanel.add(bookNameLabel);
		JTextField bookNameTextField=new JTextField();
		bookManagePanel.add(bookNameTextField);
		JLabel authorLabel=new JLabel("����/author");
		bookManagePanel.add(authorLabel);
		JTextField authorTextField=new JTextField();
		bookManagePanel.add(authorTextField);
		JLabel publisherLabel=new JLabel("������/publisher");
		bookManagePanel.add(publisherLabel);
		JTextField publisherTextField=new JTextField();
		bookManagePanel.add(publisherTextField);
		JButton searchButton=new JButton("����/search");
		bookManagePanel.add(searchButton);
		bookidTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(bookidTextField.getText().length()==0) {
					bookNameTextField.setEditable(true);
					authorTextField.setEditable(true);
					publisherTextField.setEditable(true);
				}
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				bookNameTextField.setEditable(false);
				authorTextField.setEditable(false);
				publisherTextField.setEditable(false);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub	
			}
		});
		
		//���Ĺ������
		
		
		//���߹������
		//���ͼ�����
		
		//����ע�����
		//��ʾ����
		pack();
		setVisible(true);
		setBounds(100, 100, 996, 699);
	}
	
	public static void main(String[] args) {
		Administrator_Frame administrator_Frame=new Administrator_Frame("123");
	}
	
}
