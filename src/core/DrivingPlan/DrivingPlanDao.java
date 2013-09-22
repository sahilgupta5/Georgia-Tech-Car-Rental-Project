package core.DrivingPlan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.User.MemberUser;

/**
 * 
 * @author Rochelle Lobo
 * 
 * This class represents the driving plan data access object which helps the driving plan object in the accessing information from the database.
 *
 */

public class DrivingPlanDao {
    DBConnection connection = new DBConnection();
    ArrayList<DrivingPlan> tableElement = new ArrayList<DrivingPlan>();
    
    public ArrayList<DrivingPlan> getDrivingPlans(MemberUser member){
        Connection conn = connection.createConnection();
        try {
            String statement = "SELECT * FROM Driving_Plan";

            PreparedStatement prep = conn.prepareStatement(statement);
            ResultSet rs = (ResultSet) prep.executeQuery();
            while(rs.next()){
                DrivingPlan plan = new DrivingPlan(rs.getString("Plan_Type"),
                        Integer.parseInt(rs.getString("Annual_Fees")),
                        Integer.parseInt(rs.getString("Monthly_Payment")),
                        Float.parseFloat(rs.getString("Discount")));
                tableElement.add(plan);
            }
            prep.close();
            connection.closeConnection(conn);
            return tableElement;
        } catch (SQLException e) {
            connection.closeConnection(conn);
        }
        return null;
    }
}
