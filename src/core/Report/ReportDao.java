package core.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;

/**
 * 
 * @author Rochelle Lobo
 *
 * This class helps the User in interacting with the database and user authentication.
 *
 */

public class ReportDao {
    DBConnection connection = new DBConnection();
    Object[][] rowData;
    
    public Object[][] getAdminReportArray() {
        Connection conn = connection.createConnection();
        try {
            String statement = "SELECT  Vehicle_Sno, Car_Type, Model_Name, SUM(Estimated_Cost)" +
            " AS Reservation_Revenue, SUM(Late_Fees) FROM Car NATURAL JOIN Reservation " +
            "WHERE 0 <= DATEDIFF( CURRENT_DATE, Return_Date_Time ) /30 <=3 AND " +
            "Return_Date_Time < CURRENT_DATE GROUP BY Vehicle_Sno";
    
            PreparedStatement prep = conn.prepareStatement(statement);
            ResultSet rs = (ResultSet) prep.executeQuery();
            int rowcount = 0;
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst();
            }
            rowData = new Object[rowcount][5];
            for(int i = 0; rs.next(); i++){
                rowData[i][0] = rs.getString("Vehicle_Sno");
                rowData[i][1] = rs.getString("Car_Type");
                rowData[i][2] = rs.getString("Model_Name");
                rowData[i][3] = rs.getString("Reservation_Revenue");
                rowData[i][4] = rs.getString(5);
            }
            prep.close();
            connection.closeConnection(conn);
            return rowData;
        } catch (SQLException e) {
            connection.closeConnection(conn);
            return null;
        }
    }
    
    public Object[][] getLocPrefReportArray() {
        Connection conn = connection.createConnection();
        try {
            String statement = "SELECT YEAR( Pick_Up_Date_Time ), MONTH( Pick_Up_Date_Time ), Location_Name, COUNT( * ) " +
            "AS NumOfReservation, SUM( TIMEDIFF( Return_Date_Time, Pick_Up_Date_Time ) + Late_By ) AS TotalNoOfHours " +
            "FROM Reservation NATURAL JOIN Car WHERE (DATEDIFF( CURRENT_DATE, Pick_Up_Date_Time ) /30) >=0 AND " +
            "(DATEDIFF( CURRENT_DATE, Pick_Up_Date_Time ) /30) <=3 AND Pick_Up_Date_Time < CURRENT_DATE " +
            "GROUP BY Location_Name, MONTH( Pick_Up_Date_Time ) , YEAR( Pick_Up_Date_Time ) " +
            "ORDER BY YEAR( Pick_Up_Date_Time ) , MONTH( Pick_Up_Date_Time ) DESC ";

            PreparedStatement prep = conn.prepareStatement(statement);
            ResultSet rs = (ResultSet) prep.executeQuery();
            int rowcount = 0;
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst();
            }
            rowData = new Object[rowcount][4];
            for(int i = 0; rs.next(); i++){
                rowData[i][0] = rs.getString(1);
                rowData[i][1] = rs.getString(2);
                rowData[i][2] = rs.getString("NumOfReservation");
                rowData[i][3] = rs.getString("TotalNoOfHours");
            }
            prep.close();
            connection.closeConnection(conn);
            return rowData;
        } catch (SQLException e) {
            connection.closeConnection(conn);
            return null;
        }
    }
    
    public Object[][] getMaintenanceHistReportArray() {
        Connection conn = connection.createConnection();
        try {
            String statement = "SELECT Model_Name,Date_Time,Username,Problems " +
                "FROM Car NATURAL JOIN Maintenance_Request NATURAL JOIN Problems NATURAL JOIN NUM_PROBLEMS " +
                "ORDER BY Num_Prob DESC";

            PreparedStatement prep = conn.prepareStatement(statement);
            ResultSet rs = (ResultSet) prep.executeQuery();
            int rowcount = 0;
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst();
            }
            rowData = new Object[rowcount][4];
            for(int i = 0; rs.next(); i++){
                rowData[i][0] = rs.getString("Model_Name");
                rowData[i][1] = rs.getString("Date_Time");
                rowData[i][2] = rs.getString("Username");
                rowData[i][3] = rs.getString("Problems");
            }
            prep.close();
            connection.closeConnection(conn);
            return rowData;
        } catch (SQLException e) {
            connection.closeConnection(conn);
            return null;
        }
    }
    
    public Object[][] getFreqUserReportArray() {
        Connection conn = connection.createConnection();
        try {
            String statement = "SELECT Username, Plan_Type , COUNT(*)/3 AS NoOfReservationsPerMonth " +
            "FROM Reservation NATURAL JOIN Member " +
            "WHERE (DATEDIFF( CURRENT_DATE, Pick_Up_Date_Time ) /30 ) >=0 " +
            "AND (DATEDIFF( CURRENT_DATE, Pick_Up_Date_Time ) /30) <=3 GROUP BY Username ORDER BY COUNT(*) DESC";

            PreparedStatement prep = conn.prepareStatement(statement);
            ResultSet rs = (ResultSet) prep.executeQuery();
            int rowcount = 0;
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst();
            }
            rowData = new Object[rowcount][3];
            for(int i = 0; rs.next(); i++){
                rowData[i][0] = rs.getString("Username");
                rowData[i][1] = rs.getString("Plan_Type");
                rowData[i][2] = rs.getString("NoOfReservationsPerMonth");
            }
            prep.close();
            connection.closeConnection(conn);
            return rowData;
        } catch (SQLException e) {
            connection.closeConnection(conn);
            return null;
        }
    }
}
