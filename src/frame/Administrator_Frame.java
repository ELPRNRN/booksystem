package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Administrator_Frame extends JFrame{
	String m_ID;
	public Administrator_Frame(String ID) {
		//界面初始化
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("图书管理系统管理员界面");
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
		
		//菜单面板
		JPanel manuPanel=new JPanel();
		Box box=Box.createVerticalBox();
		manuPanel.add(box);
		add(manuPanel,BorderLayout.WEST);
		JButton toBookManageButton=new JButton("图书管理/Book Manage");
		toBookManageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "book manage");
			}
		});
		box.add(toBookManageButton);
		JButton toBorrowManageButton=new JButton("借阅管理/Borrow Manage");
		toBorrowManageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "borrow manage");
			}
		});
		box.add(toBorrowManageButton);
		JButton toReaderManageButton=new JButton("读者管理/Reader Manage");
		toReaderManageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "reader manage");
			}
		});
		box.add(toReaderManageButton);
		JButton toAddBookButton=new JButton("添加图书/Add Book");
		toAddBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "add book");
			}
		});
		box.add(toAddBookButton);
		JButton toReaderRegisterButton=new JButton("读者注册/Reader Register");
		toReaderRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cardLayout.show(cardPanel, "reader register");
			}
		});
		box.add(toReaderRegisterButton);
		
		//空面版
		blankPanel.setBackground(Color.white);
		//图书管理面板
		JLabel bookidLabel=new JLabel("书ID号");
		
		//借阅管理面板
		//读者管理面板
		//添加图书面板
		//读者注册面板
		//显示窗口
		pack();
		setVisible(true);
		setBounds(100, 100, 996, 699);
	}
	
	static void main(String[] args) {
		Administrator_Frame administrator_Frame=new Administrator_Frame("123");
	}
	
}
