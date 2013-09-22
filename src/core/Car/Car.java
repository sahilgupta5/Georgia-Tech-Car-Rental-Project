package core.Car;

import core.Location.Location;

/**
 * 
 * @author Rochelle Lobo
 * 
 * This class represents the Car object with all the attributes related to the car.
 *
 */

public class Car {
    private String vehicleSNO, modelType, carType, color, transmission;
    private boolean auxCable, underMaintainance, bluetooth;
    private int hourlyRate, dailyRate, seatCapacity;
    private Location location = new Location();
    
    public Car(String vehicleSNO, String locName, String modelType, String carType, 
            String color, String transmission, boolean auxCable, boolean underMaintenance,
            boolean bluetooth, int hourlyRate, int dailyRate, int seatCapacity) {
        this.vehicleSNO = vehicleSNO;
        this.location.setLocName(locName);
        this.modelType = modelType;
        this.carType = carType;
        this.color = color;
        this.transmission = transmission;
        this.auxCable = auxCable;
        this.underMaintainance = underMaintenance;
        this.bluetooth = bluetooth;
        this.hourlyRate = hourlyRate;
        this.dailyRate = dailyRate;
        this.seatCapacity = seatCapacity;
    }
    
    public Car(String carType, String locName, String modelType) {
        this.carType = carType;
        this.location.setLocName(locName);
        this.modelType = modelType;
    }
    
    public Car() {
        this(null, null, null, null, null, null, false, false, false, 0, 0, 0);
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
        return location.getLocName();
    }

    /**
     * @param locName the locName to set
     */
    public void setLocName(String locName) {
        this.location.setLocName(locName);
    }

    /**
     * @return the modelType
     */
    public String getModelType() {
        return modelType;
    }

    /**
     * @param modelType the modelType to set
     */
    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    /**
     * @return the carType
     */
    public String getCarType() {
        return carType;
    }

    /**
     * @param carType the carType to set
     */
    public void setCarType(String carType) {
        this.carType = carType;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the transmission
     */
    public String getTransmission() {
        return transmission;
    }

    /**
     * @param transmission the transmission to set
     */
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * @return the auxCable
     */
    public boolean isAuxCable() {
        return auxCable;
    }

    /**
     * @param auxCable the auxCable to set
     */
    public void setAuxCable(boolean auxCable) {
        this.auxCable = auxCable;
    }

    /**
     * @return the underMaintainance
     */
    public boolean isUnderMaintainance() {
        return underMaintainance;
    }

    /**
     * @param underMaintainance the underMaintainance to set
     */
    public void setUnderMaintainance(boolean underMaintainance) {
        this.underMaintainance = underMaintainance;
    }

    /**
     * @return the bluetooth
     */
    public boolean isBluetooth() {
        return bluetooth;
    }

    /**
     * @param bluetooth the bluetooth to set
     */
    public void setBluetooth(boolean bluetooth) {
        this.bluetooth = bluetooth;
    }

    /**
     * @return the hourlyRate
     */
    public int getHourlyRate() {
        return hourlyRate;
    }

    /**
     * @param hourlyRate the hourlyRate to set
     */
    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    /**
     * @return the dailyRate
     */
    public int getDailyRate() {
        return dailyRate;
    }

    /**
     * @param dailyRate the dailyRate to set
     */
    public void setDailyRate(int dailyRate) {
        this.dailyRate = dailyRate;
    }

    /**
     * @return the seatCapacity
     */
    public int getSeatCapacity() {
        return seatCapacity;
    }

    /**
     * @param seatCapacity the seatCapacity to set
     */
    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }
}
