package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import frame.MyComponent.MyBookTable;
import frame.MyComponent.MyCardPanel;
import frame.MyComponent.MyComboBox;
import frame.MyComponent.MyPanel;
import model.Book;
import model.Borrow;
import model.Reader;
import service.BookManage_Service;
import service.BookSearch_Service;
import service.LoginLogout_Service;
import service.ReaderManage_Service;
import service.ReturnBorrow_Service;

public class Reader_Frame extends JFrame{
	private String m_ID;
	private static BookSearch_Service bookSearch_Service=BookSearch_Service.getInstance();
	private static ReaderManage_Service readerManage_Service=ReaderManage_Service.getInstance();
	private static BookManage_Service bookManage_Service=BookManage_Service.getInstance();
	private static ReturnBorrow_Service returnBorrow_Service=ReturnBorrow_Service.getInstance();
	private static LoginLogout_Service loginLogout_Service=LoginLogout_Service.getInstance();
	private static MyComponent myComponent=MyComponent.getMyComponent();
	public Reader_Frame(String ID) {
		// TODO Auto-generated constructor stub
		
		//��ʾ����
		pack();
		setVisible(true);
		setBounds(100, 100, 996, 699);
		
		//�����ʼ��
		JButton refreshButton=new JButton("ˢ��");
		m_ID=new String(ID);
		LoginLogout_Service.setIdReader(m_ID);
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("ͼ�����ϵͳ����Ա����");
		m_ID=new String(ID);
		String[]strings= {	"  ͼ������/Book Search   ",
							" ���Ĺ���/Borrow Manage  ",
							" ��������/Reader Setting "
						};
		MyComponent.MyCardPanel myCardPanel=myComponent.new MyCardPanel(strings);
		add(myCardPanel);
		JPanel bookSearchPanel=myCardPanel.getPanel(0);
		JPanel borrowManagePanel=myCardPanel.getPanel(1);
		JPanel readerSettingPanel=myCardPanel.getPanel(2);
		JPanel menuPanel=myCardPanel.getmenuPanel();
		JButton logoutButton=new JButton(
							"       �ǳ�/log out      ");
		menuPanel.add(logoutButton);
		menuPanel.add(Box.createVerticalStrut(10));
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ�ϵǳ�","�ǳ�ȷ��",JOptionPane.YES_NO_OPTION)==0) {
					loginLogout_Service.ReaderLogout();
					Login_Register_Frame login_Register_Frame=new Login_Register_Frame();
					dispose();
				}
			}
		});
		menuPanel.add(Box.createVerticalGlue());
		menuPanel.add(new JLabel(new ImageIcon(".\\img\\logo.png")));
		menuPanel.add(Box.createVerticalStrut(10));
		menuPanel.setBackground(Color.DARK_GRAY);
		
		//ͼ������
		JPanel bookContentPanel=new JPanel();
		bookSearchPanel.add(bookContentPanel);
		JLabel bookidLabel=new JLabel("��ID��/book ID");
		bookContentPanel.add(bookidLabel);
		JTextField bookidTextField=new JTextField(10);
		bookContentPanel.add(bookidTextField);
		JLabel bookNameLabel=new JLabel("����/book name");
		bookContentPanel.add(bookNameLabel);
		JTextField bookNameTextField=new JTextField();
		bookContentPanel.add(bookNameTextField);
		JLabel authorLabel=new JLabel("����/author");
		bookContentPanel.add(authorLabel);
		JTextField authorTextField=new JTextField();
		bookContentPanel.add(authorTextField);
		JLabel publisherLabel=new JLabel("������/publisher");
		bookContentPanel.add(publisherLabel);
		JTextField publisherTextField=new JTextField();
		bookContentPanel.add(publisherTextField);
		JLabel typeLabel=new JLabel("����/type");
		bookContentPanel.add(typeLabel);
		String[] booktypestrings= {"����","��ѧ","��ѧ","С˵","��ѧ","�̲�","����","����","��ʷ"};
		JComboBox<String> bookTypeComboBox=new JComboBox<String>(booktypestrings);
		bookContentPanel.add(bookTypeComboBox);
		JButton bookSearchButton=new JButton("����/search");
		bookContentPanel.add(bookSearchButton);
		bookContentPanel.setLayout(new GridLayout(3,2));
		bookidTextField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				if(bookidTextField.getText().length()==0) {
					bookNameTextField.setEditable(true);
					authorTextField.setEditable(true);
					publisherTextField.setEditable(true);
				}
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				bookNameTextField.setEditable(false);
				authorTextField.setEditable(false);
				publisherTextField.setEditable(false);
			}
			@Override
			public void changedUpdate(DocumentEvent e) {
				//do nothing
			}
		});
		MyBookTable bookSearchResulTable=myComponent.new MyBookTable();
		bookSearchPanel.add(bookSearchResulTable.getJScrollPane());
		bookSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bookSearchResulTable.clear();
				if(bookidTextField.getText().length()!=0) {
					List<Book> searchresult =bookSearch_Service.searchByBookID(bookidTextField.getText());
					if(searchresult.size()==0) {
						JOptionPane.showMessageDialog(null, "δ���ҵ����ͼ��","����",JOptionPane.WARNING_MESSAGE);
						return;
					}
					String[]arr=new String[8];
					Book book=searchresult.get(0);
					arr[0]=book.getIdBook();
					arr[1]=book.getNameBook();
					arr[2]=String.valueOf(book.getPrice());
					arr[3]=book.getType();
					arr[4]=book.getAuthor();
					arr[5]=book.getPublisher();
					arr[6]=String.valueOf(book.getAmount());
					arr[7]=book.getIntro();
					bookSearchResulTable.getDefaultTableModel().addRow(arr);
				}
				else {
					String typestring=(String)bookTypeComboBox.getSelectedItem();
					if(typestring=="����")typestring="";
					List<Book> searchresult = bookSearch_Service.searchByBookInfo("", bookNameTextField.getText(), typestring, 
						authorTextField.getText(), publisherTextField.getText());
					if(searchresult.size()==0) {
						JOptionPane.showMessageDialog(null, "δ���ҵ����ͼ��","����",JOptionPane.WARNING_MESSAGE);
						return;
					}
					for(Book book:searchresult) {
						String[]arr=new String[8];
						arr[0]=book.getIdBook();
						arr[1]=book.getNameBook();
						arr[2]=String.valueOf(book.getPrice());
						arr[3]=book.getType();
						arr[4]=book.getAuthor();
						arr[5]=book.getPublisher();
						arr[6]=String.valueOf(book.getAmount());
						arr[7]=book.getIntro();
						bookSearchResulTable.getDefaultTableModel().addRow(arr);
					}
				}
			}
		});
		JPopupMenu bookSearchPopupMenu=new JPopupMenu();
		bookSearchResulTable.addMenu(bookSearchPopupMenu);
		JMenuItem borrowMenuItem=new JMenuItem("����ͼ��");
		bookSearchPopupMenu.add(borrowMenuItem);
		borrowMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String messageString=returnBorrow_Service.BorrowBook((String)bookSearchResulTable.
						getValueAt(bookSearchResulTable.getSelectedRow(), 0));
				if(messageString=="���ĳɹ�") {
					JOptionPane.showMessageDialog(null, "����ͼ��ɹ���", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
					refreshButton.getActionListeners()[0].actionPerformed(null);
					bookSearchButton.getActionListeners()[0].actionPerformed(null);
				}
				else if(messageString=="����ʧ��") {
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�����ѯ������ԱѰ�����", "����",JOptionPane.ERROR_MESSAGE);
				}
				else if(messageString=="û�п��"){
					JOptionPane.showMessageDialog(null, "���鱾���޿�棬�޷����н���", "����",JOptionPane.WARNING_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "�������δ�����˴λ���ʧ�ܣ�����ѯ������ԱѰ�����", "����",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		JMenuItem showBookIntroductionMenuItem=new JMenuItem("��ʾͼ����");
		bookSearchPopupMenu.add(showBookIntroductionMenuItem);
		showBookIntroductionMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showConfirmDialog(null, (String)bookSearchResulTable.getValueAt(bookSearchResulTable.getSelectedRow(), 7),
						"���",JOptionPane.PLAIN_MESSAGE);
			}
		});
		
		//���Ĺ���
		borrowManagePanel.setLayout(new BorderLayout());
		borrowManagePanel.add(refreshButton,BorderLayout.NORTH);
		String[] borrowAttributeObjects= {"���","����","����","����","������","��������","�黹����","�Ƿ����δ��"};
		MyComponent.MyTable borrowSearchResultTable=myComponent.new MyTable(borrowAttributeObjects);
		JScrollPane borrowScrollPane=borrowSearchResultTable.getJScrollPane();
		borrowManagePanel.add(borrowScrollPane,BorderLayout.CENTER);
		refreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				borrowSearchResultTable.clear();
				List<Borrow> borrows= bookSearch_Service.searchBorrowInfo(m_ID);
				for(Borrow borrow:borrows) {
					List<Book> searchresult =bookSearch_Service.searchByBookID(borrow.getIdBook());
					String[]arr=new String[8];
					Book book=searchresult.get(0);
					arr[0]=book.getIdBook();
					arr[1]=book.getNameBook();
					arr[2]=book.getType();
					arr[3]=book.getAuthor();
					arr[4]=book.getPublisher();
					arr[5]=borrow.getLendDate().toString();
					arr[6]=borrow.getDueDate().toString();
					arr[7]=borrow.getOvertime();
					borrowSearchResultTable.getDefaultTableModel().addRow(arr);
				}
			}
		});
		JPopupMenu borrowPopupMenu=new JPopupMenu();
		borrowSearchResultTable.addMenu(borrowPopupMenu);
		JMenuItem returnItem=new JMenuItem("�黹ͼ��");
		borrowPopupMenu.add(returnItem);
		returnItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int selectrow=borrowSearchResultTable.getSelectedRow();
				String messageString=returnBorrow_Service.ReturnBook((String)borrowSearchResultTable.getValueAt(selectrow, 0));
				if(messageString=="����ɹ�") {
					JOptionPane.showMessageDialog(null, "�黹ͼ��ɹ���", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
					refreshButton.getActionListeners()[0].actionPerformed(null);
				}
				else if(messageString=="����ʧ��") {
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�����ѯ������ԱѰ�����", "����",JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(null, "�������δ�����˴λ���ʧ�ܣ�����ѯ������ԱѰ�����", 
							"����",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		refreshButton.getActionListeners()[0].actionPerformed(null);
		
		//��������
		readerSettingPanel.setLayout(new BorderLayout());
		MyComponent.MyCenterPanel readerCenterPanel=myComponent.new MyCenterPanel();
		readerSettingPanel.add(readerCenterPanel,BorderLayout.CENTER);
		List<Reader>readers=readerManage_Service.SearchReaderByID(m_ID);
		JPanel readerRegisterBorderPanel=new JPanel();
		readerCenterPanel.add(readerRegisterBorderPanel);
		readerRegisterBorderPanel.setLayout(new BorderLayout());
		String[]readerStrings= {"ID","����","����","�Ա�"};
		MyComponent.MyPanel readerPanel=myComponent.new MyPanel(readerStrings, 1,15);
		readerPanel.setText(1, m_ID);
		readerPanel.setText(3, readers.get(0).getNameReader());
		String[] readertypeStrings= {"��ʦ","ѧ��"};
		readerPanel.setJComboBox(5, readertypeStrings);
		MyComboBox typeComboBox=(MyComboBox) readerPanel.getComboBox(0);
		typeComboBox.setSelectedString(readers.get(0).getType());
		String[]genderStrings= {"��","Ů"};
		readerPanel.setJComboBox(7, genderStrings);
		MyComboBox genderComboBox= (MyComboBox) readerPanel.getComboBox(1);
		genderComboBox.setSelectedString(readers.get(0).getSex());
		readerRegisterBorderPanel.add(readerPanel,BorderLayout.CENTER);
		readerPanel.setEditable(false);
		JButton readerCorrectButton=new JButton("�޸���Ϣ");
		readerPanel.add(readerCorrectButton);
		readerPanel.setLayout(new GridLayout(5,2));
		readerCorrectButton.addActionListener(new ActionListener() {
			int i=0;
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(i==0) {
					i=1;
					readerCorrectButton.setText("ȷ���޸�");
					readerPanel.setEditable(true);

				}
				else {
					i=0;
					readerCorrectButton.setText("�޸���Ϣ");
					readerPanel.setEditable(false);
					Reader reader=new Reader(readerPanel.getText(1),readerPanel.getText(3),
							readerPanel.getText(5),readerPanel.getText(7),readers.get(0).getPassword());
					readerManage_Service.UpdateReader(reader);
				}
			}
		});
		JButton readerPwdButton=new JButton("�޸�����");
		readerPanel.add(readerPwdButton);
		readerPwdButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame pwdFrame=new JFrame();
				JPanel borderPanel=new JPanel();
				pwdFrame.add(borderPanel);
				borderPanel.setLayout(new BorderLayout());
				JPanel gridPanel=new JPanel();
				borderPanel.add(gridPanel,BorderLayout.CENTER);
				JLabel oldpwdLabel=new JLabel("�����������");
				gridPanel.add(oldpwdLabel);
				JPasswordField oldpwdField=new JPasswordField();
				gridPanel.add(oldpwdField);
				JLabel newpwdLabel=new JLabel("������������");
				gridPanel.add(newpwdLabel);
				JPasswordField newpwdField=new JPasswordField();
				gridPanel.add(newpwdField);
				JLabel newapwdLabel=new JLabel("���ٴ�����������");
				gridPanel.add(newapwdLabel);
				JPasswordField newapwdField=new JPasswordField();
				gridPanel.add(newapwdField);
				JButton button=new JButton("ȷ���޸�����");
				borderPanel.add(button,BorderLayout.SOUTH);
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(String.valueOf(readers.get(0).getPassword()).equals(String.valueOf(oldpwdField.getPassword()))) {
							if(String.valueOf(newpwdField.getPassword()).equals(String.valueOf(newapwdField.getPassword()))) {
								Reader reader=new Reader(readers.get(0).getIdReader(),readers.get(0).getNameReader(),
										readers.get(0).getType(),readers.get(0).getSex(),String.valueOf(newpwdField.getPassword()));
								readerManage_Service.UpdateReader(reader);
							}
							else {
								JOptionPane.showMessageDialog(null, "���������ٴ�����������벻ƥ�䣬����", "����",JOptionPane.WARNING_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "�����������������Ƿ���ȷ", "����",JOptionPane.WARNING_MESSAGE);
						}
					}
				});
				gridPanel.setLayout(new GridLayout(5,2));
				
				//��ʾ����
				pwdFrame.pack();
				pwdFrame.setVisible(true);
				pwdFrame.setBounds(200, 200, 250, 200);
			}
		});
		
		

	}
	
	static void main(String[] args) {
		Reader_Frame reader_Frame=new Reader_Frame("001");
	}
}
