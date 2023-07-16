package org.guru.core;




import java.sql.Connection;
import java.sql.DriverManager;


public class SingletonConnection {
    static Connection connection = null;

    private SingletonConnection(){

    }
    public static Connection getConnection()  {
        try {
            if (connection == null){
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/car_service", "root", "123456789h");

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }


        return connection;
    }

}


