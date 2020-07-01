package frame;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.util.List;
import java.util.Vector;

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
		
		public void setSelectedIndex(String string) {
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
			tableModel.setColumnCount(0);
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
			super.tableModel.addRow(arr);
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
			JTextField textField= (JTextField)this.getComponent(i);
			textField.setEditable(b);
		}
		
		/**
		* @description i必须为奇数
		*/
		public JComboBox<String> setJComboBox(int i,String[]strings) {
			JComboBox<String> comboBox=new JComboBox<String>(strings);
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
		
	}
}
