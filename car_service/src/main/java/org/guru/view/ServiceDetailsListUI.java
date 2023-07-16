package org.guru.view;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.guru.model.ServiceDetail;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.io.IOException;

public class ServiceDetailsListUI extends JPanel {

    public static Object [] stockColumn = {"Araç Plakası","Yapılacak İşlem","Ücret","Servis Detayının Numarası"};
    static DefaultTableModel model = new DefaultTableModel(stockColumn,0);
    static JTable table = new JTable(model);
    static ServiceDetailsProcessesUI serviceDetailsProcessesUI = ServiceDetailsProcessesUI.getInstance();


    private static ServiceDetailsListUI serviceDetailsListUI;

        public static ServiceDetailsListUI getInstance() {
            if (serviceDetailsListUI == null) {
                serviceDetailsListUI = new ServiceDetailsListUI();
            }
            return serviceDetailsListUI;
        }
    private static void showPopupMenu(MouseEvent e, int row, int column) {
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem serviceDetailsDelete = new JMenuItem("İşlem Detayını Sil");
        JMenuItem serviceDetailsUpdate = new JMenuItem("İşlem Detayını Güncelle");

        serviceDetailsDelete.addActionListener(event -> {
            int selectedRow = table.getSelectedRow();
            ServiceDetail serviceDetail = new ServiceDetail();
            serviceDetail.deleteSqlSentence(table.getValueAt(selectedRow,0).toString(),Integer.parseInt(table.getValueAt(selectedRow,3).toString()));
            model.removeRow(selectedRow);
        });
        serviceDetailsUpdate.addActionListener(event -> {
            serviceDetailsProcessesUI.setCarLicensePlateField(table.getValueAt(table.getSelectedRow(),0).toString());
            serviceDetailsProcessesUI.setOperationToBePerformedField(table.getValueAt(table.getSelectedRow(),1).toString());
            serviceDetailsProcessesUI.setServiceCostField(Double.valueOf(table.getValueAt(table.getSelectedRow(),2).toString()));
            JDialog dialog = new JDialog();
            dialog.getContentPane().add(serviceDetailsProcessesUI);
            dialog.setSize(1000, 700);
            dialog.setVisible(true);
        });
        popupMenu.add(serviceDetailsDelete);
        popupMenu.add(serviceDetailsUpdate);
        popupMenu.show(table, e.getX(), e.getY());
    }
    public static JPanel initPanel(Object car_license_plate, Object serviceHistoryId) {
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
        table.setPreferredScrollableViewportSize(new Dimension(500, 200)); // Boyutu burada ayarlayabilirsiniz
        for (ServiceDetail serviceDetail: new ServiceDetail().getAllSqlSentence(car_license_plate,serviceHistoryId)){
            model.addRow(serviceDetail.getInfo());
        }

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder( car_license_plate + " plakalı aracın geçmiş servis detay kayıtları."));

        JScrollPane tableScrollPane = new JScrollPane(table);
        panel.add(tableScrollPane, BorderLayout.CENTER);

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
                for (ServiceDetail serviceDetail: new ServiceDetail().getAllSqlSentence(car_license_plate,serviceHistoryId)){
                    model.addRow(serviceDetail.getInfo());
                }
            }
        });

        JButton printButton = new JButton("Yazdır");
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Show file chooser dialog to select file and location
                    JFileChooser fileChooser = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx");
                    fileChooser.setFileFilter(filter);

                    int returnVal = fileChooser.showSaveDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                        if (!filePath.toLowerCase().endsWith(".xlsx")) {
                            filePath += ".xlsx";
                        }
                        // Export the JTable to XLSX and save at the specified location
                        exportTableToExcel(table, filePath);
                        JOptionPane.showConfirmDialog(null, "Excel dosyası başarıyla oluşturuldu.", "Excel Dosyası Oluşturuldu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showConfirmDialog(null, "Excel dosyası oluşturulamadı.", "Excel Dosyası Oluşturulamadı", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonPanel.add(refreshButton);
        buttonPanel.add(printButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
    private static double exportTableToExcel(JTable table, String filePath) {
        double totalCost = 0.0;

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sheet1");

            // Export column headers
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < table.getColumnCount(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(table.getColumnName(i));
            }

            // Export table data and calculate total cost
            for (int i = 0; i < table.getRowCount(); i++) {
                Row dataRow = sheet.createRow(i + 1);
                for (int j = 0; j < table.getColumnCount(); j++) {
                    Cell cell = dataRow.createCell(j);
                    Object value = table.getValueAt(i, j);
                    cell.setCellValue(value.toString());

                    if (j == 2 && value instanceof Double) {
                        totalCost += (Double) value;
                    }
                }
            }

            // Auto-size columns for better fit
            for (int i = 0; i < table.getColumnCount(); i++) {
                sheet.autoSizeColumn(i);
            }
            sheet.setColumnWidth(2, 60 * 256); // 60 characters wide (256 units per character)

            // Add total cost in the row below the last row
            int lastRowNum = table.getRowCount();
            int lastColumnNum = table.getColumnCount() - 1;

            Row totalRow = sheet.createRow(lastRowNum + 1);
            Cell totalLabelCell = totalRow.createCell(lastColumnNum - 1);
            totalLabelCell.setCellValue("Toplam Ücret: " + totalCost + " TL");

            // Write the workbook to the file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return totalCost;
    }
    }

