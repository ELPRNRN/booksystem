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
import service.BookManage_Service;
import service.BookSearch_Service;
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
	private static MyComponent myComponent=MyComponent.getMyComponent();
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
		bookSearchResulTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			//���ؼ��
		TableColumn idColumn= bookSearchResulTable.getColumnModel().getColumn(7);
		idColumn.setWidth(0);
		idColumn.setMaxWidth(0);
		idColumn.setMinWidth(0);
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0); //���ñ�ı���Ŀ��ҲΪ0,�������Ҫ
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
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
		bookUpdatemMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// �鱾��Ϣ���´���
				JFrame bookInformationFrame=new JFrame();
				bookInformationFrame.setTitle("�����鱾��Ϣ");
				JPanel bookInformationGridPanel=new JPanel();
				bookInformationFrame.add(bookInformationGridPanel);
				int selectrow=bookSearchResulTable.getSelectedRow();
				JLabel bookidLabel=new JLabel("��ID��/book ID");
				bookInformationGridPanel.add(bookidLabel);
				JTextField bookidTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 0));
				bookInformationGridPanel.add(bookidTextField);
				bookidTextField.setEditable(false);
				JLabel bookNameLabel=new JLabel("����/book name");
				bookInformationGridPanel.add(bookNameLabel);
				JTextField bookNameTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 1));
				bookInformationGridPanel.add(bookNameTextField);
				JLabel bookPriceLabel=new JLabel("�۸�/price");
				bookInformationGridPanel.add(bookPriceLabel);
				JTextField bookPriceTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 2));
				bookInformationGridPanel.add(bookPriceTextField);
				JLabel authorLabel=new JLabel("����/author");
				bookInformationGridPanel.add(authorLabel);
				JTextField authorTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 4));
				bookInformationGridPanel.add(authorTextField);
				JLabel publisherLabel=new JLabel("������/publisher");
				bookInformationGridPanel.add(publisherLabel);
				JTextField publisherTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 5));
				bookInformationGridPanel.add(publisherTextField);
				JLabel typeLabel=new JLabel("����/type");
				bookInformationGridPanel.add(typeLabel);
				String[] booktypestrings= {"��ѧ","��ѧ","С˵","��ѧ","�̲�","����","����","��ʷ"};
				JComboBox<String> bookTypeComboBox=new JComboBox<String>(booktypestrings);
				bookInformationGridPanel.add(bookTypeComboBox);
				int index=-1;
				for(int i=0;i<booktypestrings.length;i++) {
					if(((String)bookSearchResulTable.getValueAt(selectrow, 3)).equals(booktypestrings[i])) {
						index=i;
					}
				}
				bookTypeComboBox.setSelectedIndex(index);
				JLabel countLabel=new JLabel("�������/remaining number");
				bookInformationGridPanel.add(countLabel);
				JTextField countTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 6));
				bookInformationGridPanel.add(countTextField);
				JLabel introductionLabel=new JLabel("ͼ����/introduction");
				bookInformationGridPanel.add(introductionLabel);
				JTextField introductionTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 7));
				bookInformationGridPanel.add(introductionTextField);
				bookInformationGridPanel.setLayout(new GridLayout(8,2));
				JButton updateButton=new JButton();
				bookInformationFrame.add(updateButton);
				updateButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
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
		readerSearchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int readerSearchMode=readerSearchModeComboBox.getSelectedIndex();
				if(readerSearchMode==0) {
					List<Borrow> borrows= bookSearch_Service.searchBorrowInfo(readerSearchReferenceTextField.getText());
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
		JPopupMenu borrowPopupMenu=new JPopupMenu();
		borrowSearchResulTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //�ж��Ƿ�Ϊ����BUTTON3��ť��BUTTON3Ϊ����Ҽ�
                if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    int focusedRowIndex = bookSearchResulTable.rowAtPoint(evt.getPoint());
                    if (focusedRowIndex == -1) {
                        return;
                    }
                    //���ý���
                    borrowSearchResulTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                    borrowPopupMenu.show(borrowSearchResulTable, evt.getX(), evt.getY());
                }
            }
		});
		JMenuItem returnMenuItem=new JMenuItem();
		borrowPopupMenu.add(returnMenuItem);
		returnMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method
				int selectrow=borrowSearchResulTable.getSelectedRow();
				returnBorrow_Service.DeleteBorrowBook(readerID[0], (String)borrowSearchResulTable.getValueAt(selectrow, 0));
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
		JPanel bookAddBorderPanel=new JPanel();
		addBookPanel.add(bookAddBorderPanel);
		bookAddBorderPanel.setLayout(new BorderLayout());
		String[]bookStrings= {"��ID��","����","�۸�","����","����","���߹���","������","�������ַ","���","���"};
		MyComponent.MyPanel bookPanel=myComponent.new MyPanel(bookStrings, 2);
		bookAddBorderPanel.add(bookPanel,BorderLayout.CENTER);
		bookPanel.setJComboBox(7, booktypestrings);
		JButton addBookButton=new JButton("���ͼ��");
		bookAddBorderPanel.add(addBookButton,BorderLayout.SOUTH);
		addBookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Book book=new Book(bookPanel.getText(1), bookPanel.getText(3),Integer.parseInt(bookPanel.getText(5)),
						bookPanel.getText(7),bookPanel.getText(9),bookPanel.getText(13),bookPanel.getText(17),
						Integer.parseInt(bookPanel.getText(19)));
				Author author=new Author();
				author.setName(bookPanel.getText(9));
				author.setNationality(bookPanel.getText(11));
				Publisher publisher=new Publisher();
				publisher.setName(bookPanel.getText(13));
				publisher.setAddress(bookPanel.getText(15));
				if(bookManage_Service.RegisterBook(book, author, publisher)) {
					JOptionPane.showMessageDialog(null, "���ͼ��ɹ���", "��ʾ",JOptionPane.INFORMATION_MESSAGE);
					bookSearchButton.getActionListeners()[0].actionPerformed(null);
				}
			}
		});
		
		//����ע�����
		JPanel readerRegisterBorderPanel=new JPanel();
		readerRegisterPanel.add(readerRegisterBorderPanel);
		readerRegisterBorderPanel.setLayout(new BorderLayout());
		String[]readerStrings= {"ID","����","����","�Ա�"};
		MyComponent.MyPanel readerPanel=myComponent.new MyPanel(readerStrings, 1);
		String[] readertypeStrings= {"��ʦ","ѧ��"};
		readerPanel.setJComboBox(5, readertypeStrings);
		String[]genderStrings= {"��","Ů"};
		readerPanel.setJComboBox(7, genderStrings);
		readerRegisterBorderPanel.add(readerPanel,BorderLayout.CENTER);
		JButton readerRegisterButton=new JButton("ע�����");
		readerRegisterBorderPanel.add(readerRegisterButton,BorderLayout.SOUTH);
		readerRegisterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Reader reader=new Reader(readerPanel.getText(1),readerPanel.getText(3),
						readerPanel.getText(5),readerPanel.getText(7),"root");
				readerManage_Service.AddReader(reader);
			}
		});
		
		//��ʾ����
		pack();
		setVisible(true);
		setBounds(100, 100, 996, 699);
	}
	
	public static void main(String[] args) {
		Administrator_Frame administrator_Frame=new Administrator_Frame("123");
	}
	
}
