package core.MaintenanceRequests;

import java.sql.Date;

/**
 * 
 * @author Rochelle Lobo
 *
 * This class represents the model for Maintenance Object Model and all it's attributes.
 *
 */

public class MaintenanceRequest {
    private String vehicleSNO, username;
    private Date dateTime;
    
    public MaintenanceRequest(String vehicleSNO, Date dateTime, String username){
        this.setVehicleSNO(vehicleSNO);
        this.setDateTime(dateTime);
        this.setUsername(username);
    }

    /**
     * @param vehicleSNO the vehicleSNO to set
     */
    public void setVehicleSNO(String vehicleSNO) {
        this.vehicleSNO = vehicleSNO;
    }

    /**
     * @return the vehicleSNO
     */
    public String getVehicleSNO() {
        return vehicleSNO;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * @return the dateTime
     */
    public Date getDateTime() {
        return dateTime;
    }
}
