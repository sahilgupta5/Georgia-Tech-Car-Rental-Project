package core.Reservation;

import java.sql.Date;

/**
 * 
 * @author Rochelle Lobo
 * 
 * This class represents the Reservation Model object.
 *
 */

public class Reservation {
    private String username, vehicleSNO, locName, retStatus;
    private Date pickupDateTime, retDateTime;
    private int lateBy, lateFees, estimatedCost;
    
    public Reservation(String username, String vehicleSNO, String locName, String retStatus,
            Date pickupDateTime, Date retDateTime, int lateBy, int lateFees, 
            int estimatedCost) {
        this.username = username;
        this.vehicleSNO = vehicleSNO;
        this.locName = locName;
        this.pickupDateTime = pickupDateTime;
        this.retDateTime = retDateTime;
        this.lateBy = lateBy;
        this.lateFees = lateFees;
        this.estimatedCost = estimatedCost;
    }
    
    public Reservation(String username, String vehicleSNO, String locName,
            Date pickupDateTime, Date retDateTime){
        this(username, vehicleSNO, locName, null, pickupDateTime, retDateTime, 0, 0, 0);
    }

    /**
     * @return the userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param userName the userName to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the vehicleSNO
     */
    public String getVehicleSNO() {
        return vehicleSNO;
    }

    /**
     * @param vehicleSNO the vehicleSNO to set
     */
    public void setVehicleSNO(String vehicleSNO) {
        this.vehicleSNO = vehicleSNO;
    }

    /**
     * @return the locName
     */
    public String getLocName() {
        return locName;
    }

    /**
     * @param locName the locName to set
     */
    public void setLocName(String locName) {
        this.locName = locName;
    }

    /**
     * @return the retStatus
     */
    public String getRetStatus() {
        return retStatus;
    }

    /**
     * @param retStatus the retStatus to set
     */
    public void setRetStatus(String retStatus) {
        this.retStatus = retStatus;
    }

    /**
     * @return the pickupDateTime
     */
    public Date getPickupDateTime() {
        return pickupDateTime;
    }

    /**
     * @param pickupDateTime the pickupDateTime to set
     */
    public void setPickupDateTime(Date pickupDateTime) {
        this.pickupDateTime = pickupDateTime;
    }

    /**
     * @return the retDateTime
     */
    public Date getRetDateTime() {
        return retDateTime;
    }

    /**
     * @param retDateTime the retDateTime to set
     */
    public void setRetDateTime(Date retDateTime) {
        this.retDateTime = retDateTime;
    }

    /**
     * @return the lateBy
     */
    public int getLateBy() {
        return lateBy;
    }

    /**
     * @param lateBy the lateBy to set
     */
    public void setLateBy(int lateBy) {
        this.lateBy = lateBy;
    }

    /**
     * @return the lateFees
     */
    public int getLateFees() {
        return lateFees;
    }

    /**
     * @param lateFees the lateFees to set
     */
    public void setLateFees(int lateFees) {
        this.lateFees = lateFees;
    }

    /**
     * @return the estimatedCost
     */
    public int getEstimatedCost() {
        return estimatedCost;
    }

    /**
     * @param estimatedCost the estimatedCost to set
     */
    public void setEstimatedCost(int estimatedCost) {
        this.estimatedCost = estimatedCost;
    }
}
