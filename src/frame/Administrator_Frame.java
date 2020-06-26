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
		JPanel bookContentPanel=new JPanel();
		bookManagePanel.add(bookContentPanel);
		JLabel bookidLabel=new JLabel("书ID号/book ID");
		bookContentPanel.add(bookidLabel);
		JTextField bookidTextField=new JTextField(10);
		bookContentPanel.add(bookidTextField);
		JLabel bookNameLabel=new JLabel("书名/book name");
		bookContentPanel.add(bookNameLabel);
		JTextField bookNameTextField=new JTextField();
		bookContentPanel.add(bookNameTextField);
		JLabel authorLabel=new JLabel("作者/author");
		bookContentPanel.add(authorLabel);
		JTextField authorTextField=new JTextField();
		bookContentPanel.add(authorTextField);
		JLabel publisherLabel=new JLabel("出版社/publisher");
		bookContentPanel.add(publisherLabel);
		JTextField publisherTextField=new JTextField();
		bookContentPanel.add(publisherTextField);
		JLabel typeLabel=new JLabel("分类/type");
		bookContentPanel.add(typeLabel);
		String[] booktypestrings= {"所有","文学","哲学","小说","科学","教材","军事","经济","历史"};
		JComboBox<String> bookTypeComboBox=new JComboBox<String>(booktypestrings);
		bookContentPanel.add(bookTypeComboBox);
		JButton bookSearchButton=new JButton("搜索/search");
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
		Object[] bookAttributeObjects= {"书号","书名","价格","类型","作者","出版社","库存数量","简介"};
		DefaultTableModel bookSearchResultTableModel =new DefaultTableModel(bookAttributeObjects, 0);
		bookSearchResulTable.setModel(bookSearchResultTableModel);
		bookSearchResulTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			//隐藏简介
		TableColumn idColumn= bookSearchResulTable.getColumnModel().getColumn(7);
		idColumn.setWidth(0);
		idColumn.setMaxWidth(0);
		idColumn.setMinWidth(0);
		bookSearchResulTable.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0); //设置表的标题的宽度也为0,这个很重要
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
					if(typestring=="所有")typestring="";
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
			//图书管理表格右键菜单
		JPopupMenu bookManagePopupMenu=new JPopupMenu();
		JMenuItem showBookIntroductionMenuItem=new JMenuItem("查看书本简介");
		bookManagePopupMenu.add(showBookIntroductionMenuItem);
		JMenuItem bookUpdatemMenuItem=new JMenuItem("更新书本信息");
		bookManagePopupMenu.add(bookUpdatemMenuItem);
		JMenuItem bookDeleteMenuItem=new JMenuItem("删除书本");
		bookManagePopupMenu.add(bookDeleteMenuItem);
		bookSearchResulTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //判断是否为鼠标的BUTTON3按钮，BUTTON3为鼠标右键
                if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                    int focusedRowIndex = bookSearchResulTable.rowAtPoint(evt.getPoint());
                    if (focusedRowIndex == -1) {
                        return;
                    }
                    //设置焦点
                    bookSearchResulTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
                    bookManagePopupMenu.show(bookSearchResulTable, evt.getX(), evt.getY());
                }
            }
		});
		bookUpdatemMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 书本信息更新窗口
				JFrame bookInformationFrame=new JFrame();
				bookInformationFrame.setTitle("更改书本信息");
				JPanel bookInformationGridPanel=new JPanel();
				bookInformationFrame.add(bookInformationGridPanel);
				int selectrow=bookSearchResulTable.getSelectedRow();
				JLabel bookidLabel=new JLabel("书ID号/book ID");
				bookInformationGridPanel.add(bookidLabel);
				JTextField bookidTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 0));
				bookInformationGridPanel.add(bookidTextField);
				bookidTextField.setEditable(false);
				JLabel bookNameLabel=new JLabel("书名/book name");
				bookInformationGridPanel.add(bookNameLabel);
				JTextField bookNameTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 1));
				bookInformationGridPanel.add(bookNameTextField);
				JLabel bookPriceLabel=new JLabel("价格/price");
				bookInformationGridPanel.add(bookPriceLabel);
				JTextField bookPriceTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 2));
				bookInformationGridPanel.add(bookPriceTextField);
				JLabel authorLabel=new JLabel("作者/author");
				bookInformationGridPanel.add(authorLabel);
				JTextField authorTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 4));
				bookInformationGridPanel.add(authorTextField);
				JLabel publisherLabel=new JLabel("出版社/publisher");
				bookInformationGridPanel.add(publisherLabel);
				JTextField publisherTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 5));
				bookInformationGridPanel.add(publisherTextField);
				JLabel typeLabel=new JLabel("分类/type");
				bookInformationGridPanel.add(typeLabel);
				String[] booktypestrings= {"文学","哲学","小说","科学","教材","军事","经济","历史"};
				JComboBox<String> bookTypeComboBox=new JComboBox<String>(booktypestrings);
				bookInformationGridPanel.add(bookTypeComboBox);
				int index=-1;
				for(int i=0;i<booktypestrings.length;i++) {
					if(((String)bookSearchResulTable.getValueAt(selectrow, 3)).equals(booktypestrings[i])) {
						index=i;
					}
				}
				bookTypeComboBox.setSelectedIndex(index);
				JLabel countLabel=new JLabel("库存数量/remaining number");
				bookInformationGridPanel.add(countLabel);
				JTextField countTextField=new JTextField((String)bookSearchResulTable.getValueAt(selectrow, 6));
				bookInformationGridPanel.add(countTextField);
				JLabel introductionLabel=new JLabel("图书简介/introduction");
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
		bookContentPanel.setLayout(new GridLayout(2,6));
		bookSearchResulTable.setPreferredScrollableViewportSize(new Dimension(550,400));
		
		//借阅管理面板
		JPanel readerSearchGridPanel=new JPanel();
		borrowManagePanel.add(readerSearchGridPanel);
		JTextField readerSearchReferenceTextField=new JTextField();
		readerSearchGridPanel.add(readerSearchReferenceTextField);
		String[] readerSearchModeStrings= {"通过读者ID搜索","通过读者姓名搜索"};
		JComboBox<String> readerSearchModeComboBox=new JComboBox<String>(readerSearchModeStrings);
		readerSearchGridPanel.add(readerSearchModeComboBox); 
		JButton readerSearchButton=new JButton("搜索读者");
		readerSearchGridPanel.add(readerSearchButton);
		JTable borrowSearchResulTable=new JTable() {
			public boolean isCellEditable(int rowIndex, int ColIndex){
				return false;
			}
		};
		JScrollPane borrowSearchReasultTableScrollPane=new JScrollPane(borrowSearchResulTable);
		borrowManagePanel.add(borrowSearchReasultTableScrollPane);
		Object[] borrowAttributeObjects= {"书号","书名","类型","作者","出版社","借阅日期","归还日期","是否过期未还"};
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
					selectReaderFrame.setTitle("请选择所要查看的读者并点击确认");
					JPanel selectReaderPanel=new JPanel();
					selectReaderFrame.add(selectReaderPanel);
					JTable readerSearchResulTable=new JTable() {
						public boolean isCellEditable(int rowIndex, int ColIndex){
							return false;
						}
					};
					JScrollPane readerSearchResulTableScrollPane=new JScrollPane(readerSearchResulTable);
					selectReaderPanel.add(readerSearchResulTableScrollPane);
					Object[] readerAttributeObjects= {"读者号","读者名","读者类型","读者性别"};
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
					JButton selectReaderButton=new JButton("确认");
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

		//读者管理面板
		
		
		//添加图书面板
		
		//读者注册面板
		
		//显示窗口
		pack();
		setVisible(true);
		setBounds(100, 100, 996, 699);
	}
	
	public static void main(String[] args) {
		Administrator_Frame administrator_Frame=new Administrator_Frame("123");
	}
	
}
