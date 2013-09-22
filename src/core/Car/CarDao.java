package core.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.Reservation.Reservation;
import core.User.MemberUser;

/**
 * 
 * @author Rochelle Lobo
 * 
 * This author represents the car data access object which helps car object in interacting with the database.
 *
 */

public class CarDao {
    DBConnection connection = new DBConnection();

    public ArrayList<Car> getCars(MemberUser member, ArrayList<String> carTypes,
            ArrayList<String> models, ArrayList<String> locNames) {
        Connection conn = connection.createConnection();
        try {
            String statement = "SELECT Car_Type,Model,Location_Name FROM Car";

            PreparedStatement prep = conn.prepareStatement(statement);
            ResultSet rs = (ResultSet) prep.executeQuery();
            while(rs.next()){
                carTypes.add(rs.getString("Car_Type"));
                models.add(rs.getString("Model"));
                locNames.add(rs.getString("Location_Name"));
            }
            prep.close();
            connection.closeConnection(conn);
            //            return tableElement;
        } catch (SQLException e) {
            connection.closeConnection(conn);
        }
        return null;
    }

    public Object[][] getCarAvailablity(Car car, Reservation reservation) {
        Connection conn = connection.createConnection();
        Object[][] rowData;
        try {

            deleteTableIfExisiting();
            String statement = "CREATE VIEW CAR_INFO AS SELECT Model_Name, Car_Type, " +
            "Location_Name, Color, Hourly_Rate, Daily_Rate, Seating_Cap, " +
            "Transmission_Type, Bluetooth, Auxiliary_Cable, Vehicle_Sno FROM Car " +
            "NATURAL JOIN Reservation WHERE Car_Type = ? AND " +
            "Model_Name = ? AND Car.Location_Name = ? " +
            "AND Pick_Up_Date_Time > ? AND Under_Maintenance_Flag = 'No'";
            PreparedStatement prep = conn.prepareStatement(statement);
            prep.setString(1, car.getCarType());
            prep.setString(2, car.getModelType());
            prep.setString(3, car.getLocName());
            prep.setTimestamp(4, new Timestamp(reservation.getPickupDateTime().getTime()));
            prep.execute();
            prep.close();

            statement = "CREATE VIEW TILL AS SELECT Pick_Up_Date_Time AS Available_till, Vehicle_Sno " +
            "FROM Car NATURAL JOIN Reservation WHERE Car_Type = ? " +
            "AND Model_Name = ? AND Car.Location_Name = ? " +
            "AND Pick_Up_Date_Time > ? AND Under_Maintenance_Flag = 'No'";
            prep = conn.prepareStatement(statement);
            prep.setString(1, car.getCarType());
            prep.setString(2, car.getModelType());
            prep.setString(3, car.getLocName());
            prep.setTimestamp(4,  new Timestamp(reservation.getPickupDateTime().getTime()));
            prep.execute();
            prep.close();

            statement = "CREATE VIEW FINAL_DISCOUNTED AS SELECT " +
            "Frequent_DRate*Hourly_Rate+Hourly_Rate AS Frequent_Discount_Rate, " +
            "Daily_DRate*Hourly_Rate+Hourly_Rate AS Daily_Discount_Rate, Vehicle_Sno " +
            "FROM DISCOUNTED, Car WHERE Car_Type = ? AND " +
            "Model_Name = ? AND Car.Location_Name = ? " +
            "AND Under_Maintenance_Flag = 'No'";
            prep = conn.prepareStatement(statement);
            prep.setString(1, car.getCarType());
            prep.setString(2, car.getModelType());
            prep.setString(3, car.getLocName());
            prep.execute();
            prep.close();

            statement = "SELECT * FROM CAR_INFO NATURAL JOIN TILL NATURAL JOIN FINAL_DISCOUNTED";
            prep = conn.prepareStatement(statement);
            ResultSet rs = (ResultSet) prep.executeQuery();
            int rowcount = 0;
            if(rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst();
            }

            if(rowcount != 0) {
                rowData = new Object[rowcount][16];
                for(int i = 0; rs.next(); i++){
                    rowData[i][0] = rs.getString("Model_Name");
                    rowData[i][1] = rs.getString("Car_Type");
                    rowData[i][2] = rs.getString("Location_Name");
                    rowData[i][3] = rs.getString("Color");
                    rowData[i][4] = rs.getInt("Hourly_Rate");
                    rowData[i][5] = rs.getInt("Frequent_Discount_Rate");
                    rowData[i][6] = rs.getInt("Daily_Discount_Rate");
                    rowData[i][7] = rs.getInt("Daily_Rate");
                    rowData[i][8] = rs.getString("Seating_Cap");
                    rowData[i][9] = rs.getString("Transmission_Type");
                    rowData[i][10] = rs.getString("Bluetooth");
                    rowData[i][11] = rs.getString("Auxiliary_Cable");
                    rowData[i][12] = rs.getDate("Available_till");
                    rowData[i][15] = rs.getString("Vehicle_Sno");
                }
            }
            else{
                rowData = new Object[0][0];
            }
            prep.close();
            connection.closeConnection(conn);
            return rowData;
        }
        catch (SQLException e) {}

        connection.closeConnection(conn);
        return null;
    }

    public void deleteTableIfExisiting() {
        Connection conn = connection.createConnection();
        try{
            String statement = "DROP VIEW CAR_INFO";
            PreparedStatement prep = conn.prepareStatement(statement);
            prep.execute();
            prep.close();

            statement = "DROP VIEW TILL";
            prep = conn.prepareStatement(statement);
            prep.execute();
            prep.close();

            statement = "DROP VIEW FINAL_DISCOUNTED;";
            prep = conn.prepareStatement(statement);
            prep.execute();
            prep.close();
            connection.closeConnection(conn);
        }
        catch (Exception e) {
            connection.closeConnection(conn);
            return;
        }
    }
}
