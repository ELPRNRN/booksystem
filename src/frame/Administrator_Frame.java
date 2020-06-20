package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Administrator_Frame extends JFrame{
	String m_ID;
	public Administrator_Frame(String ID) {
		//�����ʼ��
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
		manuPanel.setLayout(new FlowLayout());
		add(manuPanel,BorderLayout.WEST);
		JButton toBookManageButton=new JButton("ͼ�����/Book Manage");
		toBookManageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "book manage");
			}
		});
		JButton toBorrowManageButton=new JButton("���Ĺ���/Borrow Manage");
		toBorrowManageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "borrow manage");
			}
		});
		JButton toReaderManageButton=new JButton("���߹���/Reader Manage");
		toReaderManageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "reader manage");
			}
		});
		JButton toAddBookButton=new JButton("���ͼ��/Add Book");
		toAddBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "add book");
			}
		});
		JButton toReaderRegisterButton=new JButton("����ע��/Reader Register");
		toReaderRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "reader register");
			}
		});
		
		
		//ͼ��������
		//���Ĺ������
		//���߹������
		//���ͼ�����
		//����ע�����
	}
}
