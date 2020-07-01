package frame;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.Book;
import model.Reader;



public class MyComponent {
	private MyComponent() {}
	private static MyComponent myComponent=new MyComponent();
	public static MyComponent getMyComponent() {return myComponent;}
	
	public class MyComboBox extends JComboBox<String>{
		private String[] m_strings;
		public MyComboBox(String[]strings,int i) {
			// TODO Auto-generated constructor stub
			super(strings);
			super.setSelectedIndex(i);
			m_strings=strings;
		}
		
		public MyComboBox(String[]strings) {
			// TODO Auto-generated constructor stub
			super(strings);
			super.setSelectedIndex(-1);
			m_strings=strings;
		}
		
		public void setSelectedString(String string) {
			for(int i=0;i<this.getItemCount();i++) {
				if(string.equals(m_strings[i])) {
					this.setSelectedIndex(i);
				}
			}
		}
	}
	
	public class MyTable extends JTable{
		private DefaultTableModel tableModel =null;
		public JScrollPane jScrollPane=null;//存放表格的地方
		
		public boolean isCellEditable(int rowIndex, int ColIndex){
			return false;
		}
		
		public MyTable(String[]strings) {
			// TODO Auto-generated constructor stub
			tableModel =new DefaultTableModel(strings,0);
			super.setModel(tableModel);
			super.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			jScrollPane=new JScrollPane(this);
		}
		
		public DefaultTableModel getDefaultTableModel() {return tableModel;}
		
		public JScrollPane getJScrollPane() {return jScrollPane;}
		
		public void hidecolumn(int i) {//隐藏某列
			TableColumn idColumn= this.getColumnModel().getColumn(i);
			idColumn.setWidth(0);
			idColumn.setMaxWidth(0);
			idColumn.setMinWidth(0);
			this.getTableHeader().getColumnModel().getColumn(i).setMaxWidth(0); //设置表的标题的宽度也为0,这个很重要
			this.getTableHeader().getColumnModel().getColumn(i).setMinWidth(0);
		}
		
		public void setwidth(int i,int width) {
			TableColumn idColumn= this.getColumnModel().getColumn(i);
			idColumn.setWidth(width);
			idColumn.setMaxWidth(width);
			this.getTableHeader().getColumnModel().getColumn(i).setMaxWidth(width); //设置表的标题的宽度也为0,这个很重要
		}
		
		public void addMenu(JPopupMenu popupMenu) {
			MyTable myTable=this;
			this.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                //判断是否为鼠标的BUTTON3按钮，BUTTON3为鼠标右键
	                if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
	                    int focusedRowIndex = myTable.rowAtPoint(evt.getPoint());
	                    if (focusedRowIndex == -1) {
	                        return;
	                    }
	                    //设置焦点
	                    myTable.setRowSelectionInterval(focusedRowIndex, focusedRowIndex);
	                    popupMenu.show(myTable, evt.getX(), evt.getY());
	                }
	            }
			});
		}
		
		public void clear() {
			int num = tableModel.getRowCount();
			for (int i = 0; i < num; i++) {
				tableModel.removeRow(0);
			}
		}
	}
	
	final private static String[] readerstrings={"ID","姓名","类型","性别","pwd"};
	public class MyReaderTable extends MyTable{
		public MyReaderTable() {
			super(readerstrings);
			super.hidecolumn(4);
		}
		
		public void addReader(Reader reader) {
			String[]arr=new String[5];
			arr[0]=reader.getIdReader();
			arr[1]=reader.getNameReader();
			arr[2]=reader.getType();
			arr[3]=reader.getSex();
			arr[4]=reader.getPassword();
			super.getDefaultTableModel().addRow(arr);
		}
	}
	
	final private static String[] BOOKS_STRINGS= {"书号","书名","价格","类型","作者","出版社","库存数量","简介"};
	public class MyBookTable extends MyTable{
		public MyBookTable() {
			super(BOOKS_STRINGS);
			super.hidecolumn(7);
		}
		
		public void addBook(Book book) {
			String[]arr=new String[8];
			arr[0]=book.getIdBook();
			arr[1]=book.getNameBook();
			arr[2]=String.valueOf(book.getPrice());
			arr[3]=book.getType();
			arr[4]=book.getAuthor();
			arr[5]=book.getPublisher();
			arr[6]=String.valueOf(book.getAmount());
			arr[7]=book.getIntro();
			super.getDefaultTableModel().addRow(arr);
		}
	}
	
	public class MyPanel extends JPanel{
		public MyPanel(String[]strings,int column) {
			// TODO Auto-generated constructor stub
			for(String string:strings) {
				JLabel tempLabel=new JLabel(string);
				super.add(tempLabel);
				JTextField tempTextField=new JTextField();
				super.add(tempTextField);
			}
			if(strings.length%2==0) {
				super.setLayout(new GridLayout(strings.length/column,column));
			}
			else {
				super.setLayout(new GridLayout(strings.length/column+1,column));
			}
		}
		

		public void setEditable(int i,boolean b) {
			Component component=this.getComponent(i);
			if(component instanceof JTextField) {
				((JTextField) component).setEditable(b);
			}
			if(component instanceof MyComboBox) {
				((MyComboBox) component).setEnabled(b);
			}
		}
		
		public void setEditable(boolean b) {
			for(int i=0;i<this.getComponentCount();i++) {
				Component component=this.getComponent(i);
				if(component instanceof JTextField) {
					((JTextField) component).setEditable(b);
				}
				if(component instanceof MyComboBox) {
					((MyComboBox) component).setEnabled(b);
				}
			}
		}
		
		/**
		* @description i必须为奇数
		*/
		public MyComboBox setJComboBox(int i,String[]strings) {
			MyComboBox comboBox=new MyComboBox(strings);
			this.remove(this.getComponent(i));
			this.add(comboBox,i);
		    this.revalidate();//对panel1面板中的组件重新布局并绘制
		    this.repaint();//对panel1本身进行重绘
			return comboBox;
		}
		
		
		/**
		* @description i必须为奇数
		*/
		public String getText(int i) {
			Component component=this.getComponent(i);
			if(component instanceof JTextField) {
				return ((JTextField) component).getText();
			}
			if(component instanceof JComboBox<?>) {
				return (String)((JComboBox) component).getSelectedItem();
			}
			else return null;
		}
		
		/**
		* @description i必须为奇数
		*/
		public void setText(int i,String string) {
			Component component=this.getComponent(i);
			if(component instanceof JTextField) {
				((JTextField) component).setText(string);
			}
			if(component instanceof MyComboBox) {
				((MyComboBox) component).setSelectedString(string);
			}
		}
	}
	
	public class MyCardPanel extends JPanel{
		private JPanel menuPanel=new JPanel();
		private JPanel cardPanel=new JPanel();
		private CardLayout cardLayout=new CardLayout();
		public MyCardPanel(String[]strings) {
			// TODO Auto-generated constructor stub
			super();
			super.setLayout(new BorderLayout());
			super.add(menuPanel,BorderLayout.WEST);
			super.add(cardPanel,BorderLayout.CENTER);
			menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
			menuPanel.add(Box.createVerticalStrut(10));
			cardPanel.setLayout(cardLayout);
			for(String string:strings) {
				JButton button=new JButton(string);
				menuPanel.add(button);
				menuPanel.add(Box.createVerticalStrut(10));
				JPanel panel=new JPanel();
				cardPanel.add(panel,string);
				button.addActionListener(new ActionListener() {
					String nString=string;
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						showPanel(nString);
					}
				});
			}
		}
		
		public JPanel getmenuPanel() {return menuPanel;}
		
		public JPanel getcardPanel() {return cardPanel;}
		
		public void showPanel (String string) {
			cardLayout.show(cardPanel, string);
		}
		
		public JPanel getPanel(int i) {
			return (JPanel)cardPanel.getComponent(i);
		}
	}

	public void addRow(String[] arr) {
		// TODO Auto-generated method stub
		
	}
}
