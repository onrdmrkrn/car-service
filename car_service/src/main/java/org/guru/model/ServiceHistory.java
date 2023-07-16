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
import java.util.Objects;


@Entity
@Table(name = "service_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;
    private String reasonForService;
    private String operationToBePerformed;
    private Double serviceCost;
    private String servicePerformedBy;
    private String servicePerformerPhone;
    @Column(name = "car_license_plate")
    private String carLicensePlate;

    private String serviceNote;


    public Object[] getInfo(){
        Object[] info = {carLicensePlate,reasonForService,serviceCost,servicePerformedBy,servicePerformerPhone,serviceNote,serviceId};
        return info;
    }
    public ServiceHistory findSqlSentence(String carLicensePlate){
        ServiceHistory serviceHistory = new ServiceHistory();

        Connection connection = SingletonConnection.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM service_history WHERE car_license_plate = '"+carLicensePlate+"'");
            while (resultSet.next()){

                serviceHistory.setServiceCost(resultSet.getDouble("service_cost"));
                serviceHistory.setOperationToBePerformed(resultSet.getString("operation_to_be_performed"));
                serviceHistory.setServicePerformedBy(resultSet.getString("service_performed_by"));
                serviceHistory.setServicePerformerPhone(resultSet.getString("service_performer_phone"));
                serviceHistory.setReasonForService(resultSet.getString("reason_for_service"));
                serviceHistory.setCarLicensePlate(resultSet.getString("car_license_plate"));
                serviceHistory.setServiceNote(resultSet.getString("service_note"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return serviceHistory;
    }

    public List<ServiceHistory> listSqlSentence (String serviceId){
        List<ServiceHistory> serviceHistories = new ArrayList<ServiceHistory>();
        Connection connection = SingletonConnection.getConnection();

        try {
            Statement statement =connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM service_history WHERE service_id = '"+serviceId+"'");
            while(resultSet.next()){
                ServiceHistory serviceHistory = new ServiceHistory();
                serviceHistory.setServiceCost(resultSet.getDouble("service_cost"));
                serviceHistory.setOperationToBePerformed(resultSet.getString("operation_to_be_performed"));
                serviceHistory.setServicePerformedBy(resultSet.getString("service_performed_by"));
                serviceHistory.setServicePerformerPhone(resultSet.getString("service_performer_phone"));
                serviceHistory.setReasonForService(resultSet.getString("reason_for_service"));
                serviceHistory.setCarLicensePlate(resultSet.getString("car_license_plate"));
                serviceHistory.setServiceNote(resultSet.getString("service_note"));
                serviceHistories.add(serviceHistory);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return serviceHistories;
    }

    public void updateSqlSentence (){
        Connection connection = SingletonConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE service_history SET reason_for_service='"
                    +getReasonForService()
                    +"',service_cost='"
                    +getServiceCost()
                    +"', service_performed_by='"
                    +getServicePerformedBy()
                    +"',service_performer_phone='"
                    +getServicePerformerPhone()
                    +"',car_license_plate='"
                    +getCarLicensePlate()
                    +"',service_note='"
                    +getServiceNote()
                    +"' WHERE service_id= '"
                    +getServiceId()
                    +"'");


        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSqlSentence (String carLicensePlate, int serviceId){
        Connection connection = SingletonConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM service_history WHERE car_license_plate = '"+carLicensePlate+"' AND service_id = '"+serviceId+"'");
            statement.executeUpdate("DELETE FROM service_detail WHERE car_license_plate = '"+carLicensePlate+"' AND service_history_service_id = '"+getServiceId()+"'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<ServiceHistory> getAllSqlSentence(String carLicensePlate){
        List<ServiceHistory> serviceHistories = new ArrayList<ServiceHistory>();
        ServiceDetail serviceDetail = new ServiceDetail();
        Connection connection = SingletonConnection.getConnection();
        ServiceHistory serviceHistory = new ServiceHistory();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT car_license_plate," +
                    "reason_for_service," +
                    "service_cost," +
                    "service_performed_by," +
                    "service_performer_phone," +
                    "service_note, " +
                    "service_id " +
                    "FROM service_history WHERE car_license_plate = '"+carLicensePlate+"'");

            while (resultSet.next()) {
                serviceHistory.setCarLicensePlate(resultSet.getString("car_license_plate"));
                serviceHistory.setReasonForService(resultSet.getString("reason_for_service"));
                serviceHistory.setServiceCost(resultSet.getDouble("service_cost"));
                serviceHistory.setServicePerformedBy(resultSet.getString("service_performed_by"));
                serviceHistory.setServicePerformerPhone(resultSet.getString("service_performer_phone"));
                serviceHistory.setServiceNote(resultSet.getString("service_note"));
                serviceHistory.setServiceId(resultSet.getInt("service_id"));
                serviceHistories.add(serviceHistory);
            }
            serviceDetail.getTotalCost(carLicensePlate,serviceHistory.getServiceId());

        }

        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return serviceHistories;
    }
    public void insertSqlSentence(){
        Connection connection = SingletonConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
                statement.executeUpdate("INSERT INTO service_history (reason_for_service,service_cost, service_performed_by, service_performer_phone,car_license_plate,service_note) "
                        +"VALUES('"
                        + getReasonForService()
                        +"',"
                        +getServiceCost()
                        +",'"
                        +getServicePerformedBy()
                        +"','"
                        +getServicePerformerPhone()
                        +"','"
                        +getCarLicensePlate()
                        +"','"
                        +getServiceNote()
                        +"')");

        }catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
