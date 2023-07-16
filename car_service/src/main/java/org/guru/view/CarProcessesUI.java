package org.guru.view;

import com.toedter.calendar.JDateChooser;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.sql.Date;
import java.text.SimpleDateFormat;


@Getter
@Setter
public class CarProcessesUI extends JPanel {
	public void clear() {
		sasiNumberField.setText("");
		brandField.setText("");
		modelField.setText("");
		licensePlateField.setText("");
		vehicleOwnerField.setText("");
		vehicleOwnerPhoneNumberField.setText("");
		chooseDate.setDate(null);
	}
	private static CarProcessesUI carProcessesUI;
	public static CarProcessesUI getInstance (){
		if (carProcessesUI == null){
			carProcessesUI = new CarProcessesUI();
		}
		return carProcessesUI;
	}
	public JLabel vehicleOwnerLabel = new JLabel("Araç Sahibi:", JLabel.LEFT);
	public JTextField vehicleOwnerField = new JTextField();
	public JLabel vehicleOwnerPhoneNumberLabel = new JLabel("Araç Sahibi Telefon Numarası:", JLabel.LEFT);
	public JTextField vehicleOwnerPhoneNumberField = new JTextField();

	public JLabel sasiNumberLabel = new JLabel("Şasi Numarası:", JLabel.LEFT);
	public JTextField sasiNumberField = new JTextField();

	public JLabel brandLabel = new JLabel("Marka:", JLabel.LEFT);
	public JTextField brandField = new JTextField();
	public JLabel modelLabel = new JLabel("Model:", JLabel.LEFT);
	public JTextField modelField = new JTextField();
	public JLabel licensePlateLabel = new JLabel("Plaka:", JLabel.LEFT);
	public JTextField licensePlateField = new JTextField();
	public JScrollPane descScrollPane = new JScrollPane();
	public JLabel dateLabel = new JLabel("Giriş Tarihi: ",JLabel.LEFT);

	public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	public JDateChooser chooseDate = new JDateChooser();
	public JButton saveButton = new JButton("Araç Kaydet");

	public JButton updateButton = new JButton("Araç Güncelle");

	public JButton findButton = new JButton("Araç Ara");

	public JButton deleteButton = new JButton("Araç Sil");


	public CarProcessesUI(){
		initPanel();
	}


	public void initPanel() {
		add(sasiNumberLabel);
		add(sasiNumberField);
		add(brandLabel);
		add(brandField);
		add(modelLabel);
		add(modelField);
		add(licensePlateLabel);
		add(licensePlateField);
		add(vehicleOwnerLabel);
		add(vehicleOwnerField);
		add(vehicleOwnerPhoneNumberLabel);
		add(vehicleOwnerPhoneNumberField);
		add(dateLabel);
		add(chooseDate);
		add(saveButton);
		add(updateButton);
		add(findButton);
		add(deleteButton);


	}

	public String getlicensePlate() {
		return licensePlateField.getText();
	}
	public String getbrand() {
		return brandField.getText();
	}
	public void setbrand(String brand) {
		brandField.setText(brand);

	}
	public String getVehicleOwnerPhoneNumber() {
		return vehicleOwnerPhoneNumberField.getText();
	}
	public void setVehicleOwnerPhoneNumber(String vehicleOwnerPhoneNumber) {
		vehicleOwnerPhoneNumberField.setText(vehicleOwnerPhoneNumber);

	}
	public String getvehicleOwner() {
		return vehicleOwnerField.getText();
	}
	public void setvehicleOwner(String vehicleOwner) {
		vehicleOwnerField.setText(vehicleOwner);

	}
	public String getModel() {
		return modelField.getText();
	}
	public void setModel(String model) {
		modelField.setText(model);

	}
	public String getchassisNumber() {
		return sasiNumberField.getText();
	}
	public Date getentryDate() {
		String date = format.format(chooseDate.getDate());
		return Date.valueOf(date);
	}

	public void setchassisNumber(String chassisNumber) {
		sasiNumberField.setText(chassisNumber);

	}
	public void setlicensePlate(String licensePlate) {
		licensePlateField.setText(licensePlate);
	}

	public void setentryDate(Date entryDate) {
		chooseDate.setDate(entryDate);

	}
}
