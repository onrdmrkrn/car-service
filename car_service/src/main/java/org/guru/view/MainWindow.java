package org.guru.view;

import org.guru.model.Car;
import org.guru.model.StockComplexType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;


public class MainWindow extends JFrame  {

	CarProcessesUI carProcessesUI = CarProcessesUI.getInstance();
	ServiceProcessesUI serviceProcessesUI = ServiceProcessesUI.getInstance();
	public MainWindow() {
		initWindow();
	}

	public void initWindow() {
		JPanel panel = initPanel();
		add(panel);
		setTitle("Araç Servis Otomasyonu");
		setVisible(true); 
		setSize(1000, 700);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public JPanel initPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		JTabbedPane pane = initTabs();
		panel.add(pane,BorderLayout.CENTER);
		return panel;
	}
	Object [] stockColumn = {"Araç Numarası","Şasi Numarası","Marka","Model","Plaka","Araç Sahibi","Araç Sahibi Telefon Numarası","Araç Servis Giriş Tarihi"};
	DefaultTableModel model = new DefaultTableModel(stockColumn,0);
	JTable table = new JTable(model);
	JTabbedPane pane = new JTabbedPane();
	ImageIcon icon = new ImageIcon("icons/79739_cut_scissors_stock_icon (2).png");
	ImageIcon icon2 = new ImageIcon("icons/24px.png");
	ImageIcon icon3 = new ImageIcon("icons/hmg.png");
	public JTabbedPane initTabs() {
		JPanel stockPanel = new JPanel(new BorderLayout());
		JPanel stockLeftPanel = new JPanel(new BorderLayout());
		JPanel stockLeftUpPanel = new JPanel(new GridLayout(6,2));
		JPanel stockLeftUnderPanel = new JPanel();
		stockLeftPanel.setBorder(BorderFactory.createTitledBorder("Araç Bilgileri"));
		JScrollPane stockTablePane = new JScrollPane(table);

		for (StockComplexType complexType: new Car().getAllSqlSentenceType()){
			model.addRow(complexType.getInfo());
			model.fireTableDataChanged();
		}
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int row = table.rowAtPoint(e.getPoint());
					int column = table.columnAtPoint(e.getPoint());
					showPopupMenu(e, row, column);
				}
			}
		});
		JButton stockRefreshButton = new JButton("Yenile");
		stockLeftUpPanel.add(stockRefreshButton);

		stockRefreshButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int refresh = model.getRowCount();
				for (int i = 0; i <refresh ; i++) {
					model.removeRow(0);
				}
				for (StockComplexType type: new Car().getAllSqlSentenceType()){
					model.addRow(type.getInfo());
				}
			}
		});

		JButton quitButton = new JButton("Çıkış");
		stockLeftUnderPanel.add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});

		stockPanel.add(stockLeftPanel,BorderLayout.WEST);
		stockPanel.add(stockTablePane,BorderLayout.CENTER);
		stockLeftPanel.add(stockLeftUpPanel, BorderLayout.NORTH);
		stockLeftPanel.add(stockLeftUnderPanel,BorderLayout.SOUTH);

		pane.addTab("Servis", icon, stockPanel,"Main Page");
		pane.addTab("Araç İşlemleri", icon3, carProcessesUI, "Car Page");
		pane.addTab("Araç Bul",icon2, CarListUI.initPanel(),"Search Page");

		return pane;
	}
	private void showPopupMenu(MouseEvent e, int row, int column) {
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem serviceLog = new JMenuItem("Servis Geçmişi");
		JMenuItem serviceProcesses = new JMenuItem("Servis İşlemleri");
		serviceLog.addActionListener(event -> {
			JDialog dialog = new JDialog();
			int selectedRow = table.getSelectedRow();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.getContentPane().add(ServiceLogListUI.initPanel(String.valueOf(table.getValueAt(selectedRow,4))));
			dialog.setSize(1000, 700);
			dialog.setVisible(true);
		});
		serviceProcesses.addActionListener(event ->{
			serviceProcessesUI.setCarLicensePlateField(table.getValueAt(table.getSelectedRow(),4).toString());
			JDialog dialog = new JDialog();
			dialog.getContentPane().add(ServiceProcessesUI.getInstance());
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setSize(1000, 700);
			dialog.setVisible(true);
		});
		popupMenu.add(serviceLog);
		popupMenu.add(serviceProcesses);
		popupMenu.show(table, e.getX(), e.getY());
	}
	public CarProcessesUI getStockUI() {
		return CarProcessesUI.getInstance();
	}
	public ServiceProcessesUI getServiceProcessesUI() {
		return ServiceProcessesUI.getInstance();
	}
	public ServiceDetailsProcessesUI getServiceDetailsProcessesUI() {
		return ServiceDetailsProcessesUI.getInstance();
	}

}
