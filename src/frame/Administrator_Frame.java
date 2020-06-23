package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import service.BookManage_Service;
import service.BookSearch_Service;
import service.ReaderManage_Service;
import model.Book;
import model.Borrow;
import model.Reader;

public class Administrator_Frame extends JFrame{
	private String m_ID;
	private static BookSearch_Service bookSearch_Service=BookSearch_Service.getInstance();
	private static ReaderManage_Service readerManage_Service=ReaderManage_Service.getInstance();
	public Administrator_Frame(String ID) {
		m_ID=ID;
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
		JPanel bookContentPanel=new JPanel();
		bookManagePanel.add(bookContentPanel);
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
		JTable bookSearchResulTable=new JTable() {
			public boolean isCellEditable(int rowIndex, int ColIndex){
				return false;
			}
		};
		JScrollPane bookTableScrollPane=new JScrollPane(bookSearchResulTable);
		bookManagePanel.add(bookTableScrollPane);
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
		Object[] bookAttributeObjects= {"���","����","�۸�","����","����","������","�������","���"};
		DefaultTableModel bookSearchResultTableModel =new DefaultTableModel(bookAttributeObjects, 0);
		bookSearchResulTable.setModel(bookSearchResultTableModel);
		bookSearchResulTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);//
		bookSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(bookidTextField.getText().length()!=0) {
					List<Book> searchresult =bookSearch_Service.searchByBookID(bookidTextField.getText());
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
					bookSearchResultTableModel.addRow(arr);
				}
				else {
					String typestring=(String)bookTypeComboBox.getSelectedItem();
					if(typestring=="����")typestring="";
					List<Book> searchresult = bookSearch_Service.searchByBookInfo("", bookNameTextField.getText(), typestring, 
						authorTextField.getText(), publisherTextField.getText());
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
						bookSearchResultTableModel.addRow(arr);
					}
				}
			}
		});
		bookContentPanel.setLayout(new GridLayout(2,6));
		bookSearchResulTable.setPreferredScrollableViewportSize(new Dimension(550,400));
		
		//���Ĺ������
		JPanel readerSearchGridPanel=new JPanel();
		borrowManagePanel.add(readerSearchGridPanel);
		JTextField readerSearchReferenceTextField=new JTextField();
		readerSearchGridPanel.add(readerSearchReferenceTextField);
		String[] readerSearchModeStrings= {"ͨ������ID����","ͨ��������������"};
		JComboBox<String> readerSearchModeComboBox=new JComboBox<String>(readerSearchModeStrings);
		readerSearchGridPanel.add(readerSearchModeComboBox); 
		JButton readerSearchButton=new JButton("��������");
		readerSearchGridPanel.add(readerSearchButton);
		JTable borrowSearchResulTable=new JTable() {
			public boolean isCellEditable(int rowIndex, int ColIndex){
				return false;
			}
		};
		JScrollPane borrowSearchReasultTableScrollPane=new JScrollPane(borrowSearchResulTable);
		borrowManagePanel.add(borrowSearchReasultTableScrollPane);
		Object[] borrowAttributeObjects= {"���","����","����","����","������","��������","�黹����","�Ƿ����δ��"};
		DefaultTableModel borrowSearchResultTableModel =new DefaultTableModel(borrowAttributeObjects, 0);
		borrowSearchResulTable.setModel(borrowSearchResultTableModel);
		borrowSearchResulTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		readerSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int readerSearchMode=readerSearchModeComboBox.getSelectedIndex();
				if(readerSearchMode==0) {
					List<Borrow> borrows= bookSearch_Service.searchBorrowInfo(readerSearchReferenceTextField.getText());
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
						borrowSearchResultTableModel.addRow(arr);
					}
				}
				else {
					List<Reader>readers= readerManage_Service.SearchReaderByName(readerSearchReferenceTextField.getText());
					JFrame selectReaderFrame=new JFrame();
					selectReaderFrame.setTitle("��ѡ����Ҫ�鿴�Ķ��߲����ȷ��");
					JPanel selectReaderPanel=new JPanel();
					selectReaderFrame.add(selectReaderPanel);
					JTable readerSearchResulTable=new JTable() {
						public boolean isCellEditable(int rowIndex, int ColIndex){
							return false;
						}
					};
					JScrollPane readerSearchResulTableScrollPane=new JScrollPane(readerSearchResulTable);
					selectReaderPanel.add(readerSearchResulTableScrollPane);
					Object[] readerAttributeObjects= {"���ߺ�","������","��������","�����Ա�"};
					DefaultTableModel readerSearchResultTableModel =new DefaultTableModel(readerAttributeObjects, 0);
					readerSearchResulTable.setModel(readerSearchResultTableModel);
					readerSearchResulTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					for(Reader reader:readers) {
						String[]arr=new String[4];
						arr[0]=reader.getIdReader();
						arr[1]=reader.getNameReader();
						arr[2]=reader.getType();
						arr[3]=reader.getSex();
						readerSearchResultTableModel.addRow(arr);
					}
					JButton selectReaderButton=new JButton("ȷ��");
					selectReaderPanel.add(selectReaderButton);
					selectReaderButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							int selectrow=readerSearchResulTable.getSelectedRow();
							String rIDString=(String)readerSearchResulTable.getValueAt(selectrow, 0);
							List<Borrow> borrows= bookSearch_Service.searchBorrowInfo(rIDString);
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
								borrowSearchResultTableModel.addRow(arr);
							}
							selectReaderFrame.dispose();
						}
					});
					readerSearchResulTable.setPreferredScrollableViewportSize(new Dimension(350,200));
					selectReaderFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					selectReaderFrame.pack();
					selectReaderFrame.setVisible(true);
					selectReaderFrame.setBounds(200, 200, 370,300);
				}
			}
		});
		readerSearchGridPanel.setLayout(new GridLayout(1,3));
		borrowSearchResulTable.setPreferredScrollableViewportSize(new Dimension(550,400));
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
