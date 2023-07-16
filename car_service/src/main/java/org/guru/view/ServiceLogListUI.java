package org.guru.view;
import org.guru.model.ServiceDetail;
import org.guru.model.ServiceHistory;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ServiceLogListUI {
    public static Object [] stockColumn = {"Araç Plakası","Servise Gelme Nedeni","Toplam Ücret","Servisi Yapan Kişi","Servisi Yapan Kişi Numarası","Servis Notu","Servis Kayıt Numarası"};
    static DefaultTableModel model = new DefaultTableModel(stockColumn,0);
    static JTable table = new JTable(model);
    private static void showPopupMenu(MouseEvent e, int row, int column) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem serviceProcesses = new JMenuItem("İşlem Detayı Ekle");
        JMenuItem serviceHistoryDelete = new JMenuItem("Servis Kaydını Sil");
        JMenuItem serviceHistoryUpdate = new JMenuItem("Servis Kaydını Güncelle");
        JMenuItem serviceDetails = new JMenuItem("İşlem Detayları");

        serviceProcesses.addActionListener(event ->{
            ServiceDetailsProcessesUI serviceDetailsProcessesUI = ServiceDetailsProcessesUI.getInstance();
            int selectedRow = table.getSelectedRow();
            serviceDetailsProcessesUI.setCarLicensePlateField(table.getValueAt(table.getSelectedRow(),0).toString());
            serviceDetailsProcessesUI.setServiceHistoryId(Integer.parseInt(table.getValueAt(selectedRow,6).toString()));
            JDialog dialog = new JDialog();
            dialog.getContentPane().add(serviceDetailsProcessesUI);
            dialog.setSize(1000, 700);
            dialog.setVisible(true);
        });

        serviceHistoryDelete.addActionListener(event -> {
            int selectedRow = table.getSelectedRow();
            ServiceHistory serviceHistory = new ServiceHistory();
            serviceHistory.deleteSqlSentence(table.getValueAt(selectedRow,0).toString(),Integer.parseInt(table.getValueAt(selectedRow,6).toString()));
            model.removeRow(selectedRow);
        });
        serviceHistoryUpdate.addActionListener(event -> {
            ServiceProcessesUI serviceProcessesUI = ServiceProcessesUI.getInstance();
            int selectedRow = table.getSelectedRow();
            serviceProcessesUI.setCarLicensePlateField(table.getValueAt(table.getSelectedRow(),0).toString());
            serviceProcessesUI.setReasonForServiceField(table.getValueAt(table.getSelectedRow(),1).toString());
            serviceProcessesUI.setServicePerformedByField(table.getValueAt(table.getSelectedRow(),3).toString());
            serviceProcessesUI.setServicePerformerPhoneField(table.getValueAt(table.getSelectedRow(),4).toString());
            serviceProcessesUI.setServiceNoteField(table.getValueAt(table.getSelectedRow(),5).toString());
            serviceProcessesUI.setServiceIdField(Integer.parseInt(table.getValueAt(selectedRow,6).toString()));
            JDialog dialog = new JDialog();
            dialog.getContentPane().add(serviceProcessesUI);
            dialog.setSize(1000, 700);
            dialog.setVisible(true);
        });

        serviceDetails.addActionListener(event ->{
            JDialog dialog = new JDialog();
            int selectedRow = table.getSelectedRow();
            dialog.getContentPane().add(ServiceDetailsListUI.initPanel(table.getValueAt(selectedRow,0),Integer.parseInt(table.getValueAt(selectedRow,6).toString())));
            dialog.setSize(1000, 700);
            dialog.setVisible(true);
            dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        });
        popupMenu.add(serviceProcesses);
        popupMenu.add(serviceHistoryDelete);
        popupMenu.add(serviceHistoryUpdate);
        popupMenu.add(serviceDetails);
        popupMenu.show(table, e.getX(), e.getY());
    }
    public static JPanel initPanel(String carLicensePlate) {
        model.setRowCount(0);
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
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder( carLicensePlate + " plakalı aracın geçmiş servis kayıtları."));
        JPanel up = new JPanel(new GridLayout(4,2));

        JScrollPane stockTablePane = new JScrollPane(table);
        for (ServiceHistory serviceHistory: new ServiceHistory().getAllSqlSentence(carLicensePlate)){
            model.addRow(serviceHistory.getInfo());
        }
        up.add(stockTablePane);
        panel.add(up,BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton refreshButton = new JButton("Yenile");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowCount = model.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    model.removeRow(0);
                }
                // Yeniden veri ekleme
                for (ServiceHistory serviceHistory: new ServiceHistory().getAllSqlSentence(carLicensePlate)){
                    model.addRow(serviceHistory.getInfo());
                }
            }
        });
        buttonPanel.add(refreshButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

}
