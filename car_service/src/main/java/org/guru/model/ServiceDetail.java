package org.guru.model;

import javax.persistence.*;
import lombok.*;
import org.guru.core.SingletonConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "service_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceDetailId;
    private String operationToBePerformed;
    private Double serviceCost;

    private String serviceHistoryCarLicensePlate;

    private int serviceHistoryServiceId;
    public Object[] getInfo(){
        Object[] info = {serviceHistoryCarLicensePlate,operationToBePerformed,serviceCost,serviceDetailId};
        return info;
    }


    public Double getTotalCost (String carLicensePlate, int serviceHistoryId){
        Connection connection = SingletonConnection.getConnection();
        ServiceHistory serviceHistory = new ServiceHistory();
        try {
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select SUM(service_cost) FROM service_detail WHERE car_license_plate = '"+carLicensePlate+"' AND service_history_service_id = '"+serviceHistoryId+"'");

            while(resultSet.next()){
                serviceHistory.setServiceCost(resultSet.getDouble("SUM(service_cost)"));
            }
            System.out.println(serviceHistory.getServiceCost().doubleValue());
            statement.executeUpdate("UPDATE service_history SET service_cost='"
                    +serviceHistory.getServiceCost().doubleValue()
                    +"' WHERE service_id='"
                    +serviceHistoryId
                    +"' AND car_license_plate='"
                    +carLicensePlate+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serviceHistory.getServiceCost();
    }
    public void insertSqlSentence(){
        Connection connection = SingletonConnection.getConnection();
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("INSERT INTO service_detail (operation_to_be_performed,service_cost,car_license_plate,service_history_service_id)"
                    + "VALUES('"
                    + getOperationToBePerformed()
                    + "',"
                    + getServiceCost()
                    + ",'"
                    + getServiceHistoryCarLicensePlate()
                    + "',"
                    + getServiceHistoryServiceId()
                    + ")");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSqlSentence(){
        Connection connection = SingletonConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE service_detail SET operation_to_be_performed='"
                    +getOperationToBePerformed()
                    +"',service_cost='"
                    +getServiceCost()
                    +"',service_cost='"
                    +getServiceCost()
                    +"', car_license_plate='"
                    +getServiceHistoryCarLicensePlate()
                    +"',service_history_service_id='"
                    +getServiceHistoryServiceId()
                    +"' WHERE service_id= '"
                    +getServiceHistoryCarLicensePlate()
                    +"'");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteSqlSentence (String carLicensePlate, int serviceDetailId){
        Connection connection = SingletonConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM service_detail WHERE car_license_plate = '"+carLicensePlate+"' AND service_detail_id = '"+serviceDetailId+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getTotalCost(getServiceHistoryCarLicensePlate(),getServiceHistoryServiceId());
    }
    public ServiceDetail findSqlSentence(String carLicensePlate){
        ServiceDetail serviceDetail = new ServiceDetail();
        Connection connection = SingletonConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM service_detail WHERE car_license_plate = '"+carLicensePlate+"'");
            while (resultSet.next()){

                serviceDetail.setServiceCost(resultSet.getDouble("service_cost"));
                serviceDetail.setOperationToBePerformed(resultSet.getString("operation_to_be_performed"));
                serviceDetail.setServiceHistoryCarLicensePlate(resultSet.getString("car_license_plate"));
                serviceDetail.setServiceHistoryServiceId(resultSet.getInt("service_history_service_id"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return serviceDetail;
    }

    public List<ServiceDetail> getAllSqlSentence(Object carLicensePlate, Object serviceHistoryId){
        List<ServiceDetail> serviceDetails = new ArrayList<ServiceDetail>();
        Connection connection = SingletonConnection.getConnection();
        ServiceDetail serviceDetail;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT operation_to_be_performed," +
                    "service_cost, " +
                    "car_license_plate, " +
                    "service_history_service_id, " +
                    "service_detail_id " +
                    "FROM service_detail WHERE car_license_plate = '"+carLicensePlate+"' AND service_history_service_id = '"+serviceHistoryId+"'");
            while (resultSet.next()) {

                serviceDetail = new ServiceDetail();
                serviceDetail.setOperationToBePerformed(resultSet.getString("operation_to_be_performed"));
                serviceDetail.setServiceCost(resultSet.getDouble("service_cost"));
                serviceDetail.setServiceHistoryCarLicensePlate(resultSet.getString("car_license_plate"));
                serviceDetail.setServiceHistoryServiceId(resultSet.getInt("service_history_service_id"));
                serviceDetail.setServiceDetailId(resultSet.getInt("service_detail_id"));
                serviceDetails.add(serviceDetail);

            }
        }

        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return serviceDetails;
    }
}
