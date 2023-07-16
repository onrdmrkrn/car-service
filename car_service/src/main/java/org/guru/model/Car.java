package org.guru.model;




import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.guru.core.SingletonConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car")
public class Car {

	private static final String COL_CAR_ID = "car_id";
	private static final String COL_LICENSE_PLATE = "license_plate";
	private static final String COL_CHASSIS_NUMBER = "chassis_number";
	private static final String COL_BRAND = "brand";
	private static final String COL_MODEL = "model";
	private static final String COL_VEHICLE_OWNER = "vehicle_owner";
	private static final String COL_ENTRY_DATE = "entry_date";
	private static final String COL_STATUS = "status";
	private static final String COL_SERVICE_HISTORY_SERVICE_ID = "service_history_service_id";
	private static final String COL_VEHICLE_OWNER_PHONE_NUMBER = "vehicle_owner_phone_number";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = COL_CAR_ID)
	private int carId;

	@Column(name = COL_LICENSE_PLATE, unique = true, length = 50)
	private String licensePlate;
	@Column(name = COL_CHASSIS_NUMBER, length = 100)
	private String chassisNumber;
	@Column(name = COL_BRAND, length = 100)
	private String brand;
	@Column(name = COL_MODEL, length = 100)
	private String model;
	@Column(name = COL_VEHICLE_OWNER, length = 255)
	private String vehicleOwner;
	@Temporal(TemporalType.DATE)
	@Column (name = COL_ENTRY_DATE)
	private Date entryDate;

	@Column(name = COL_SERVICE_HISTORY_SERVICE_ID)
	@Transient
	private int serviceHistoryServiceId;


	@Column(name = COL_VEHICLE_OWNER_PHONE_NUMBER, length = 255)
	private String vehicleOwnerPhoneNumber;


	@Override
	public String toString() {
		return "Car{" +
				"carId=" + carId +
				", licensePlate='" + licensePlate + '\'' +
				", chassisNumber='" + chassisNumber + '\'' +
				", brand='" + brand + '\'' +
				", model='" + model + '\'' +
				", vehicleOwner='" + vehicleOwner + '\'' +
				", entryDate=" + entryDate +
				", serviceHistoryServiceId=" + serviceHistoryServiceId +
				", vehicleOwnerPhoneNumber='" + vehicleOwnerPhoneNumber + '\'' +
				'}';
	}

	public Car findSqlSentence(String licensePlate){
		Car car = new Car();

		Connection connection = SingletonConnection.getConnection();

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM car WHERE licensePlate = '"+licensePlate+"'");
			while (resultSet.next()){

				car.setChassisNumber(resultSet.getString("chassis_number"));
				car.setBrand(resultSet.getString("brand"));
				car.setModel(resultSet.getString("model"));
				car.setLicensePlate(resultSet.getString("license_plate"));
				car.setVehicleOwner(resultSet.getString("vehicle_owner"));
				car.setEntryDate(resultSet.getDate("entry_date"));
				car.setVehicleOwnerPhoneNumber(resultSet.getString("vehicle_owner_phone_number"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return car;
	}

	public List<Car> listSqlSentence (String licensePlate){
		List<Car> carList = new ArrayList<Car>();
		Connection connection = SingletonConnection.getConnection();

		try {
			Statement statement =connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT chassis_number,brand,model,license_plate,vehicle_owner,entry_date,service_history_id,vehicle_owner_phone_number FROM car WHERE license_plate LIKE '" +'%' +licensePlate +'%' +"'");

			while(resultSet.next()){
				Car car = new Car();
				car.setChassisNumber(resultSet.getString("chassis_number"));
				car.setBrand(resultSet.getString("brand"));
				car.setModel(resultSet.getString("model"));
				car.setLicensePlate(resultSet.getString("license_plate"));
				car.setVehicleOwner(resultSet.getString("vehicle_owner"));
				car.setEntryDate(resultSet.getDate("entry_date"));
				car.setServiceHistoryServiceId(resultSet.getInt("service_history_id"));
				car.setVehicleOwnerPhoneNumber(resultSet.getString("vehicle_owner_phone_number"));
				carList.add(car);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return carList;
	}

	public void updateSqlSentence (){
		Connection connection = SingletonConnection.getConnection();


		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE car SET chassis_number='"
					+getChassisNumber()
					+"',entry_date='"
					+getEntryDate()
					+"',license_plate='"
					+getLicensePlate()
					+"', brand='"
					+getBrand()
					+"',model='"
					+getModel()
					+"',vehicle_owner='"
					+getVehicleOwner()
					+"' WHERE license_plate= '"
					+getLicensePlate()
					+"'");


		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteSqlSentence (){
		Connection connection = SingletonConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM car WHERE license_plate = '"+getLicensePlate()+"'");
			statement.executeUpdate("DELETE FROM service_history WHERE carLicensePlate = '"+getLicensePlate()+"'");
			statement.executeUpdate("DELETE FROM service_detail WHERE car_license_plate = '"+getLicensePlate()+"'");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public List<Car> getAllSqlSentence(){
		List<Car> productList = new ArrayList<Car>();
		Connection connection = SingletonConnection.getConnection();
		Car product;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT chassis_number,model,vehicle_owner,vehicle_owner_phone_number,brand,entry_date,license_plate FROM car");

			while (resultSet.next()) {
				product = new Car();
				product.setChassisNumber(resultSet.getString("chassis_number"));
				product.setModel(resultSet.getString("model"));
				product.setVehicleOwner(resultSet.getString("vehicle_owner"));
				product.setVehicleOwnerPhoneNumber(resultSet.getString("vehicle_owner_phone_number"));
				product.setBrand(resultSet.getString("brand"));
				product.setEntryDate(resultSet.getDate("entry_date"));
				product.setLicensePlate(resultSet.getString("license_plate"));

				productList.add(product);
			}

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}

	public int getServiceHistoryIdinDb (String carLicensePlate){
		Connection connection = SingletonConnection.getConnection();
		ServiceHistory serviceHistory = new ServiceHistory();
		List<Double> totalCost = new ArrayList<Double>();
		try {
			Statement statement =connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select service_id FROM service_history WHERE car_license_plate = '"+carLicensePlate+"'");
			while(resultSet.next()){
				serviceHistory.setServiceId(resultSet.getInt("service_id"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return serviceHistory.getServiceId();
	}
	public void insertSqlSentence(){
		Connection connection = SingletonConnection.getConnection();
		try {
			Statement statement = connection.createStatement();
			//statement.executeUpdate("INSERT INTO service_history (service_id,car_license_plate) "
			//		+"VALUES('"
			//		+ getServiceHistoryServiceId()
			//		+"','"
			//		+ getLicensePlate()
			//		+"')");
			statement.executeUpdate("INSERT INTO car (chassis_number, brand,model, license_plate, vehicle_owner,vehicle_owner_phone_number,entry_date) "
					+"VALUES('"
					+ getChassisNumber()
					+"','"
					+ getBrand()
					+ "','"
					+getModel()
					+"','"
					+getLicensePlate()
					+"','"
					+getVehicleOwner()
					+"','"
					+getVehicleOwnerPhoneNumber()
					+"','"
					+getEntryDate()
					+"')");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<StockComplexType> getAllSqlSentenceType(){
		List<StockComplexType> productList = new ArrayList<StockComplexType>();
		Connection connection = SingletonConnection.getConnection();
		StockComplexType product;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM car");

			while (resultSet.next()) {

				product = new StockComplexType();
				product.setCarId(resultSet.getInt("car_id"));
				product.setChassisNumber(resultSet.getString("chassis_number"));
				product.setBrand(resultSet.getString("brand"));
				product.setModel(resultSet.getString("model"));
				product.setLicensePlate(resultSet.getString("license_plate"));
				product.setVehicleOwner(resultSet.getString("vehicle_owner"));
				product.setVehicleOwnerPhoneNumber(resultSet.getString("vehicle_owner_phone_number"));
				product.setEntryDate(resultSet.getDate("entry_date"));
				productList.add(product);
			}

		}

		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}
}
