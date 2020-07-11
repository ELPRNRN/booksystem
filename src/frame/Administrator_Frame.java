package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import frame.MyComponent.MyComboBox;
import frame.MyComponent.MyPanel;
import service.BookManage_Service;
import service.BookSearch_Service;
import service.LoginLogout_Service;
import service.ReaderManage_Service;
import service.ReturnBorrow_Service;
import model.Author;
import model.Book;
import model.Borrow;
import model.Reader;
import model.Publisher;

public class Administrator_Frame extends JFrame{
	private String m_ID;
	private static BookSearch_Service bookSearch_Service=BookSearch_Service.getInstance();
	private static ReaderManage_Service readerManage_Service=ReaderManage_Service.getInstance();
	private static BookManage_Service bookManage_Service=BookManage_Service.getInstance();
	private static ReturnBorrow_Service returnBorrow_Service=ReturnBorrow_Service.getInstance();
	private static LoginLogout_Service loginLogout_Service=LoginLogout_Service.getInstance();
	private static MyComponent myComponent=MyComponent.getMyComponent();
	public Administrator_Frame(String ID) {
		//�����ʼ��
		setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("ͼ�����ϵͳ����Ա����");
		m_ID=new String(ID);
		String[]strings= {	"  ͼ�����/Book Manage  ",
							" ���Ĺ���/Borrow Manage ",
							" ���߹���/Reader Manage ",
							"    ���ͼ��/Add Book   ",
							"����ע��/Reader Register",
		};
		MyComponent.MyCardPanel myCardPanel=myComponent.new MyCardPanel(strings);
		add(myCardPanel);
		JPanel bookManagePanel=myCardPanel.getPanel(0);
		JPanel borrowManagePanel=myCardPanel.getPanel(1);
		JPanel readerManagePanel=myCardPanel.getPanel(2);
		JPanel addBookPanel=myCardPanel.getPanel(3);
		JPanel readerRegisterPanel=myCardPanel.getPanel(4);
		JPanel menuPanel=myCardPanel.getmenuPanel();
		JButton logoutButton=new JButton(
							"      �ǳ�/log out     ");
		menuPanel.add(logoutButton);
		menuPanel.add(Box.createVerticalStrut(10));
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null, "�Ƿ�ȷ�ϵǳ�","�ǳ�ȷ��",JOptionPane.YES_NO_OPTION)==0) {
					loginLogout_Service.LibLogout();
					Login_Register_Frame login_Register_Frame=new Login_Register_Frame();
					dispose();
				}
			}
		});
		menuPanel.add(Box.createVerticalGlue());
		menuPanel.add(new JLabel(new ImageIcon(".\\img\\logo.png")));
		menuPanel.add(Box.createVerticalStrut(10));
		menuPanel.setBackground(Color.DARK_GRAY);
		
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
		Object[] bookAttributeObjects= {"���","����","�۸�","����","����","����","������","�������ַ","���","���"};
		DefaultTableModel bookSearchResultTableModel =new DefaultTableModel(bookAttributeObjects, 0);
		bookSearchResulTable.setModel(bookSearchResultTableModel);
		bookSearchResulTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			//���ؼ��
		TableColumn idColumn= bookSearchResulTable.getColumnModel().getColumn(9);
		idColumn.setWidth(0);
		idColumn.setMaxWidth(0);
		idColumn.setMinWidth(0);
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(9).setMaxWidth(0); //���ñ�ı���Ŀ��ҲΪ0,�������Ҫ
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(9).setMinWidth(0);
			//���ؼ��
		TableColumn idColumn1= bookSearchResulTable.getColumnModel().getColumn(7);
		idColumn1.setWidth(0);
		idColumn1.setMaxWidth(0);
		idColumn1.setMinWidth(0);
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0); //���ñ�ı���Ŀ��ҲΪ0,�������Ҫ
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
		TableColumn idColumn2= bookSearchResulTable.getColumnModel().getColumn(0);
		idColumn2.setWidth(35);
		idColumn2.setMaxWidth(35);
		idColumn2.setMinWidth(35);
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(35); 
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
		TableColumn idColumn3= bookSearchResulTable.getColumnModel().getColumn(2);
		idColumn3.setWidth(35);
		idColumn3.setMaxWidth(35);
		idColumn3.setMinWidth(35);
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(35);
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(2).setMinWidth(35);
		TableColumn idColumn4= bookSearchResulTable.getColumnModel().getColumn(8);
		idColumn4.setWidth(35);
		idColumn4.setMaxWidth(35);
		idColumn4.setMinWidth(35);
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(8).setMaxWidth(35);
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(8).setMinWidth(35);
		TableColumn idColumn5= bookSearchResulTable.getColumnModel().getColumn(3);
		idColumn5.setWidth(35);
		idColumn5.setMaxWidth(35);
		idColumn5.setMinWidth(35);
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(3).setMaxWidth(35);
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(3).setMinWidth(35);
		TableColumn idColumn6= bookSearchResulTable.getColumnModel().getColumn(5);
		idColumn6.setWidth(35);
		idColumn6.setMaxWidth(35);
		idColumn6.setMinWidth(35);
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(35);
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(5).setMinWidth(35);
		bookSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int num = bookSearchResultTableModel.getRowCount();
				for (int i = 0; i < num; i++) {
					bookSearchResultTableModel.removeRow(0);
				}
				if(bookidTextField.getText().length()!=0) {
					List<Book> searchresult =bookSearch_Service.searchByBookID(bookidTextField.getText());
					if(searchresult.size()==0) {
						JOptionPane.showMessageDialog(null, "δ���ҵ�����鱾","����",JOptionPane.WARNING_MESSAGE);
						return;
					}
					String[]arr=new String[10];
					Book book=searchresult.get(0);
					arr[0]=book.getIdBook();
					arr[1]=book.getNameBook();
					arr[2]=String.valueOf(book.getPrice());
					arr[3]=book.getType();
					arr[4]=book.getAuthor();
					arr[5]=bookSearch_Service.searchAuthorInfo(book.getAuthor()).get(0).getNationality();
					arr[6]=book.getPublisher();
					arr[7]=bookSearch_Service.searchPublisherInfo(book.getPublisher()).get(0).getAddress();
					arr[8]=String.valueOf(book.getAmount());
					arr[9]=book.getIntro();
					bookSearchResultTableModel.addRow(arr);
				}
				else {
					String typestring=(String)bookTypeComboBox.getSelectedItem();
					if(typestring=="����")typestring="";
					List<Book> searchresult = bookSearch_Service.searchByBookInfo("", bookNameTextField.getText(), typestring, 
						authorTextField.getText(), publisherTextField.getText());
					if(searchresult.size()==0) {
						JOptionPane.showMessageDialog(null, "δ���ҵ�����鱾","����",JOptionPane.WARNING_MESSAGE);
						return;
					}
					for(Book book:searchresult) {
						String[]arr=new String[10];
						arr[0]=book.getIdBook();
						arr[1]=book.getNameBook();
						arr[2]=String.valueOf(book.getPrice());
						arr[3]=book.getType();
						arr[4]=book.getAuthor();
						arr[5]=bookSearch_Service.searchAuthorInfo(book.getAuthor()).get(0).getNationality();
						arr[6]=book.getPublisher();
						arr[7]=bookSearch_Service.searchPublisherInfo(book.getPublisher()).get(0).getAddress();
						arr[8]=String.valueOf(book.getAmount());
						arr[9]=book.getIntro();
						bookSearchResultTableModel.addRow(arr);
					}
				}
			}
		});
			//ͼ��������Ҽ��˵�
		JPopupMenu bookManagePopupMenu=new JPopupMenu();
		JMenuItem showBookIntroductionMenuItem=new JMenuItem("�鿴�鱾���");
		bookManagePopupMenu.add(showBookIntroductionMenuItem);
		JMenuItem bookUpdatemMenuItem=new JMenuItem("�����鱾��Ϣ");
		bookManagePopupMenu.add(bookUpdatemMenuItem);
		JMenuItem bookDeleteMenuItem=new JMenuItem("ɾ���鱾");
		bookManagePopupMenu.add(bookDeleteMenuItem);
		bookSearchResulTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //�ж��Ƿ�Ϊ����BUTTON3��ť��BUTTON3Ϊ����Ҽ�
                if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    int focusedRowIndex = bookSearchResulTable.rowAtPoint(evt.getPoint());
                    if (focusedRowIndex == -1) {
                        return;
                    }
                    //���ý���
                    bookSearchResulTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                    bookManagePopupMenu.show(bookSearchResulTable, evt.getX(), evt.getY());
                }
            }
		});
		showBookIntroductionMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showConfirmDialog(null, (String)bookSearchResulTable.getValueAt(bookSearchResulTable.getSelectedRow(), 9),
						"���",JOptionPane.PLAIN_MESSAGE);
			}
		});
		bookUpdatemMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// �鱾��Ϣ���´���
				int selectrow=bookSearchResulTable.getSelectedRow();
				JFrame bookInformationFrame=new JFrame();
				bookInformationFrame.setTitle("�����鱾��Ϣ");
				JPanel bookBorderPanel=new JPanel(); 
				bookInformationFrame.add(bookBorderPanel);
				bookBorderPanel.setLayout(new BorderLayout());
			 	JPanel bookSouthGridPanel=new JPanel();
			 	bookBorderPanel.add(bookSouthGridPanel,BorderLayout.SOUTH);
				String[]bookStrings= {"��ID��","����","�۸�","����","����","���߹���","������","���"};
				MyComponent.MyPanel bookPanel=myComponent.new MyPanel(bookStrings, 2);
				bookBorderPanel.add(bookPanel,BorderLayout.CENTER);
				bookPanel.setJComboBox(7, booktypestrings);
				bookPanel.setText(1, (String)bookSearchResulTable.getValueAt(selectrow, 0));
				bookPanel.setText(3, (String)bookSearchResulTable.getValueAt(selectrow, 1));
				bookPanel.setText(5, (String)bookSearchResulTable.getValueAt(selectrow, 2));
				bookPanel.setText(7, (String)bookSearchResulTable.getValueAt(selectrow, 3));
				bookPanel.setText(9, (String)bookSearchResulTable.getValueAt(selectrow, 4));
				bookPanel.setText(11, (String)bookSearchResulTable.getValueAt(selectrow, 5));
				bookPanel.setText(13, (String)bookSearchResulTable.getValueAt(selectrow, 6));
				bookPanel.setText(15, (String)bookSearchResulTable.getValueAt(selectrow, 8));
				JLabel bookAddAddressLabel=new JLabel("�������ַ");
				bookSouthGridPanel.add(bookAddAddressLabel);
				JTextField bookAddAddressTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 7));
				bookSouthGridPanel.add(bookAddAddressTextField);
				JLabel bookAddIntroductionLabel=new JLabel("���");
				bookSouthGridPanel.add(bookAddIntroductionLabel);
				JTextField bookAddIntroductionTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 9));
				bookSouthGridPanel.add(bookAddIntroductionTextField);
				JButton addBookButton=new JButton("����ͼ����Ϣ");
				bookSouthGridPanel.add(addBookButton);
				addBookButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						if(bookPanel.getText(1).equals("")||bookPanel.getText(3).equals("")||bookPanel.getText(5).equals("")||
								bookPanel.getText(7).equals("")||bookPanel.getText(9).equals("")||bookPanel.getText(11).equals("")||
								bookPanel.getText(13).equals("")||bookAddAddressTextField.getText().equals("")||
								bookAddIntroductionTextField.getText().equals("")||bookPanel.getText(15).equals("")||
								bookAddAddressTextField.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "����������Ϊ�գ�", "����",JOptionPane.WARNING_MESSAGE);
							return;
						}
						Book book=new Book(bookPanel.getText(1), bookPanel.getText(3),Integer.parseInt(bookPanel.getText(5)),
								bookPanel.getText(7),bookPanel.getText(9),bookPanel.getText(13),
								bookAddIntroductionTextField.getText(),Integer.parseInt(bookPanel.getText(15)));
						Author author=new Author();
						author.setName(bookPanel.getText(9));
						author.setNationality(bookPanel.getText(11));
						Publisher publisher=new Publisher();
						publisher.setName(bookPanel.getText(13));
						publisher.setAddress(bookAddAddressTextField.getText());
						if(bookManage_Service.UpdateBook(book, author, publisher)) {
							JOptionPane.showMessageDialog(null, "����ͼ��ɹ���", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
							bookSearchButton.getActionListeners()[0].actionPerformed(null);
							bookInformationFrame.dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "����ͼ��ʧ�ܣ�����ѯ������ԱѰ�����", "����",JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				bookSouthGridPanel.setLayout(new GridLayout(5,1));
				bookInformationFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				bookInformationFrame.pack();
				bookInformationFrame.setVisible(true);
				bookInformationFrame.setBounds(200, 200, 370,300);
			}
		});
		bookDeleteMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null,"�Ƿ�ɾ����ͼ�飿","ע��",JOptionPane.YES_NO_OPTION)==0) {
					if(bookManage_Service.DeleteBook((String)bookSearchResulTable.getValueAt(bookSearchResulTable.getSelectedRow(), 0))) {
						JOptionPane.showMessageDialog(null, "ɾ��ͼ��ɹ���", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
						bookSearchButton.getActionListeners()[0].actionPerformed(null);
					}
					else {
						JOptionPane.showMessageDialog(null, "ɾ��ͼ��ʧ�ܣ�", "����",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		bookContentPanel.setLayout(new GridLayout(2,6));
		bookSearchResulTable.setPreferredScrollableViewportSize(new Dimension(550,400));
		
		//���Ĺ������
		String[]readerID=new String[1];
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
		TableColumn idColumn7= borrowSearchResulTable.getColumnModel().getColumn(0);
		idColumn7.setWidth(35);
		idColumn7.setMaxWidth(35);
		idColumn7.setMinWidth(35);
		borrowSearchResulTable.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(35);
		borrowSearchResulTable.getTableHeader().getColumnModel().getColumn(0).setMinWidth(35);
		TableColumn idColumn8= borrowSearchResulTable.getColumnModel().getColumn(2);
		idColumn8.setWidth(35);
		idColumn8.setMaxWidth(35);
		idColumn8.setMinWidth(35);
		borrowSearchResulTable.getTableHeader().getColumnModel().getColumn(2).setMaxWidth(35);
		borrowSearchResulTable.getTableHeader().getColumnModel().getColumn(2).setMinWidth(35);
		readerSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int num = borrowSearchResultTableModel.getRowCount();
				for (int i = 0; i < num; i++) {
					borrowSearchResultTableModel.removeRow(0);
				}
				int readerSearchMode=readerSearchModeComboBox.getSelectedIndex();
				if(readerSearchMode==0) {
					List<Borrow> borrows= bookSearch_Service.searchBorrowInfo(readerSearchReferenceTextField.getText());
					if(borrows.size()==0) {
						JOptionPane.showMessageDialog(null, "δ���ҵ��ö��ߵĽ��ļ�¼","����",JOptionPane.WARNING_MESSAGE);
						return;
					}
					readerID[0]=readerSearchReferenceTextField.getText();
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
					if(readers.size()==0) {
						JOptionPane.showMessageDialog(null, "δ���ҵ���ض���","����",JOptionPane.WARNING_MESSAGE);
						return;
					}
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
							if(borrows.size()==0) {
								JOptionPane.showMessageDialog(null, "δ���ҵ��ö��ߵĽ��ļ�¼","����",JOptionPane.WARNING_MESSAGE);
								return;
							}
							readerID[0]=rIDString;
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
							readerSearchReferenceTextField.setText(readers.get(0).getIdReader());
							readerSearchModeComboBox.setSelectedIndex(0);
							selectReaderFrame.dispose();
						}
					});
					readerSearchResulTable.setPreferredScrollableViewportSize(new Dimension(350,200));
					selectReaderFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					selectReaderFrame.pack();
					selectReaderFrame.setVisible(true);
					selectReaderFrame.setBounds(200, 200, 370,330);
				}
			}
		});
		JPopupMenu borrowPopupMenu=new JPopupMenu();
		JMenuItem returnMenuItem=new JMenuItem("ɾ������ͼ��");
		borrowPopupMenu.add(returnMenuItem);
		returnMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method
				if(JOptionPane.showConfirmDialog(null, "�Ƿ�ɾ���ý��ļ�¼��ϵͳ�������黹ͼ�飩","ɾ��ȷ��",JOptionPane.YES_NO_OPTION)==0) {
					int selectrow=borrowSearchResulTable.getSelectedRow();
					if(returnBorrow_Service.DeleteBorrowBook(readerID[0], (String)borrowSearchResulTable.getValueAt(selectrow, 0))
							.equals("ɾ���ɹ�")) {
						readerSearchButton.getActionListeners()[0].actionPerformed(null);
					}
					else {
						JOptionPane.showMessageDialog(null, "ɾ�����ļ�¼ʧ�ܣ�����ѯ������ԱѰ�����","ɾ��ʧ��",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		borrowSearchResulTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //�ж��Ƿ�Ϊ����BUTTON3��ť��BUTTON3Ϊ����Ҽ�
                if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    int focusedRowIndex = borrowSearchResulTable.rowAtPoint(evt.getPoint());
                    if (focusedRowIndex == -1) {
                        return;
                    }
                    //���ý���
                    borrowSearchResulTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                    borrowPopupMenu.show(borrowSearchResulTable, evt.getX(), evt.getY());
                }
            }
		});
		readerSearchGridPanel.setLayout(new GridLayout(1,3));	
		borrowSearchResulTable.setPreferredScrollableViewportSize(new Dimension(550,400));
		
		//���߹������
		readerManagePanel.setLayout(new BorderLayout());
		JPanel readerManageGridPanel=new JPanel();
		readerManagePanel.add(readerManageGridPanel,BorderLayout.NORTH);
		JTextField readerManageSearchInformationTextField=new JTextField();
		readerManageGridPanel.add(readerManageSearchInformationTextField);
		String[]searchModeStrings= {"������ID����","��������������"};
		MyComponent.MyComboBox searchModeComboBox=myComponent.new MyComboBox(searchModeStrings, -1);
		readerManageGridPanel.add(searchModeComboBox);
		JButton readerManageSearchJButton=new JButton("��������");
		readerManageGridPanel.add(readerManageSearchJButton);
		readerManageGridPanel.setLayout(new GridLayout(1,3));
		MyComponent.MyReaderTable readerManageSearchResulTable=myComponent.new MyReaderTable();
		readerManagePanel.add(readerManageSearchResulTable.getJScrollPane(),BorderLayout.CENTER);
		readerManageSearchJButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				readerManageSearchResulTable.clear();
				List<Reader>readers;
				if(searchModeComboBox.getSelectedIndex()==0) {
					readers=readerManage_Service.SearchReaderByID(readerManageSearchInformationTextField.getText());
				}
				else {
					readers=readerManage_Service.SearchReaderByName(readerManageSearchInformationTextField.getText());
				}
				if(readers.size()==0) {
					JOptionPane.showMessageDialog(null, "δ���ҵ���ض���","����",JOptionPane.WARNING_MESSAGE);
					return;
				}
				for(Reader reader:readers) {
					readerManageSearchResulTable.addReader(reader);
				}
			}
		});
		JPopupMenu readerManagePopupMenu=new JPopupMenu();
		readerManageSearchResulTable.addMenu(readerManagePopupMenu);
		JMenuItem deleteReaderMenuItem=new JMenuItem("ɾ������");
		readerManagePopupMenu.add(deleteReaderMenuItem);
		deleteReaderMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null,"�Ƿ�ɾ���ö��ߵ���Ϣ��","ע��",JOptionPane.YES_NO_OPTION)==0) {
					int selectrow=readerManageSearchResulTable.getSelectedRow();
					if(readerManage_Service.DeleteReader((String)readerManageSearchResulTable.getValueAt(selectrow, 0))) {
						JOptionPane.showMessageDialog(null, "ɾ���ɹ���", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
						readerManageSearchJButton.getActionListeners()[0].actionPerformed(null);
					}
					else {
						JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�����ѯ������ԱѰ�����", "����",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		
		//���ͼ�����
		addBookPanel.setLayout(new BorderLayout());
		MyComponent.MyCenterPanel addCenterPanel=myComponent.new MyCenterPanel();
		addBookPanel.add(addCenterPanel,BorderLayout.CENTER);
		JPanel bookAddBorderPanel=new JPanel();
		addCenterPanel.add(bookAddBorderPanel);
		bookAddBorderPanel.setLayout(new BorderLayout());
		JPanel bookAddSouthGridPanel=new JPanel();
		bookAddBorderPanel.add(bookAddSouthGridPanel,BorderLayout.SOUTH);
		String[]bookStrings= {"��ID��","����","�۸�","����","����","���߹���","������","���"};
		MyComponent.MyPanel bookPanel=myComponent.new MyPanel(bookStrings, 2,15);
		bookAddBorderPanel.add(bookPanel,BorderLayout.CENTER);
		bookPanel.setJComboBox(7, booktypestrings);
		JLabel bookAddAddressLabel=new JLabel("�������ַ");
		bookAddSouthGridPanel.add(bookAddAddressLabel);
		JTextField bookAddAddressTextField=new JTextField();
		bookAddSouthGridPanel.add(bookAddAddressTextField);
		JLabel bookAddIntroductionLabel=new JLabel("���");
		bookAddSouthGridPanel.add(bookAddIntroductionLabel);
		JTextField bookAddIntroductionTextField=new JTextField();
		bookAddSouthGridPanel.add(bookAddIntroductionTextField);
		JButton addBookButton=new JButton("���ͼ��");
		bookAddSouthGridPanel.add(addBookButton);
		bookAddSouthGridPanel.setLayout(new GridLayout(5,1));
		bookPanel.setText(1, bookManage_Service.GenerateBookID());
		bookPanel.setEditable(1,false);
		addBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(bookPanel.getText(1).equals("")||bookPanel.getText(3).equals("")||bookPanel.getText(5).equals("")||
						bookPanel.getText(7).equals("")||bookPanel.getText(9).equals("")||bookPanel.getText(11).equals("")||
						bookPanel.getText(13).equals("")||bookAddAddressTextField.getText().equals("")||
						bookAddIntroductionTextField.getText().equals("")||bookPanel.getText(15).equals("")) {
					JOptionPane.showMessageDialog(null, "����������Ϊ�գ�", "����",JOptionPane.WARNING_MESSAGE);
					return;
				}
				Book book=new Book(bookPanel.getText(1), bookPanel.getText(3),Integer.parseInt(bookPanel.getText(5)),
						bookPanel.getText(7),bookPanel.getText(9),bookPanel.getText(13),bookAddIntroductionTextField.getText(),
						Integer.parseInt(bookPanel.getText(15)));
				Author author=new Author();
				author.setName(bookPanel.getText(9));
				author.setNationality(bookPanel.getText(11));
				Publisher publisher=new Publisher();
				publisher.setName(bookPanel.getText(13));
				publisher.setAddress(bookAddAddressTextField.getText());
				if(bookManage_Service.RegisterBook(book, author, publisher)) {
					JOptionPane.showMessageDialog(null, "���ͼ��ɹ���", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
					bookSearchButton.getActionListeners()[0].actionPerformed(null);
					bookManage_Service.UpdateGenerateBookID();
					bookPanel.cleartext();
					bookAddIntroductionTextField.setText("");
					bookAddAddressTextField.setText("");
					bookPanel.setText(1, bookManage_Service.GenerateBookID());
				}
				else {
					JOptionPane.showMessageDialog(null, "����ѯ������ԱѰ�����", "���ʧ��", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//����ע�����
		readerRegisterPanel.setLayout(new BorderLayout());
		MyComponent.MyCenterPanel readerCenterPanel=myComponent.new MyCenterPanel();
		readerRegisterPanel.add(readerCenterPanel,BorderLayout.CENTER);
		JPanel readerRegisterBorderPanel=new JPanel();
		readerCenterPanel.add(readerRegisterBorderPanel);
		readerRegisterBorderPanel.setLayout(new BorderLayout());
		String[]readerStrings= {"ID","����","����","�Ա�"};
		MyComponent.MyPanel readerPanel=myComponent.new MyPanel(readerStrings, 1,15);
		String[] readertypeStrings= {"��ʦ","ѧ��"};
		readerPanel.setJComboBox(5, readertypeStrings);
		String[]genderStrings= {"��","Ů"};
		readerPanel.setJComboBox(7, genderStrings);
		readerRegisterBorderPanel.add(readerPanel,BorderLayout.CENTER);
		JButton readerRegisterButton=new JButton("ע�����");
		readerRegisterBorderPanel.add(readerRegisterButton,BorderLayout.SOUTH);
		readerPanel.setText(1, readerManage_Service.GenerateReaderID());
		readerPanel.setEditable(1,false);
		readerRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Reader reader=new Reader(readerPanel.getText(1),readerPanel.getText(3),
						readerPanel.getText(5),readerPanel.getText(7),"root");
				if(readerManage_Service.AddReader(reader)) {
					JOptionPane.showMessageDialog(null, "�ɹ�ע�����˻�", "ע��ɹ�", JOptionPane.PLAIN_MESSAGE);
					readerManage_Service.UpdateGenerateReaderID();
					readerPanel.cleartext();
					readerPanel.setText(1, readerManage_Service.GenerateReaderID());
					
				}
				else {
					JOptionPane.showMessageDialog(null, "����ѯ������ԱѰ�����", "ע��ʧ��", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//��ʾ����
		pack();
		setVisible(true);
		setBounds(100, 100, 996, 699);
	}
	
}
