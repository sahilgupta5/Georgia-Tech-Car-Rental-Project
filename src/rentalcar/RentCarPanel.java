package rentalcar;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jdesktop.swingx.JXDatePicker;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.Car.Car;
import core.Car.CarDao;
import core.Reservation.Reservation;
import core.User.MemberUser;

/**
 * @author Sahil Gupta 
 * 
 * This class is page for renting the car for members.
 */

public class RentCarPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    final static Dimension screenSize = Toolkit.getDefaultToolkit()
    .getScreenSize();

    protected MemberUser member;
    static DBConnection connection = new DBConnection();

    JLabel RentACar;
    JLabel PickUpTime, ReturnTime, Location, Cars;

    @SuppressWarnings("rawtypes")
    JComboBox PickUpTimeCombo, ReturnTimeCombo, LocationCombo, CarsCombo,
    CarTypesCombo;
    JXDatePicker PickUpDateCombo, ReturnDateCombo;

    // Had to hard code it because there was no way out.
    private String[] pickUpTimeStrings = { "12:00 AM", "12:30 AM", "01:00 AM",
            "01:30 AM", "02:00 AM", "02:30 AM", "03:00 AM", "03:30 AM",
            "04:00 AM", "04:30 AM", "05:00 AM", "05:30 AM", "06:00 AM",
            "06:30 AM", "07:00 AM", "07:30 AM", "08:00 AM", "08:30 AM",
            "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM",
            "11:30 AM", "12:00 PM", "12:30 PM", "01:00 PM", "01:30 PM",
            "02:00 PM", "02:30 PM", "03:00 PM", "03:30 PM", "04:00 PM",
            "04:30 PM", "05:00 PM", "05:30 PM", "06:00 PM", "06:30 PM",
            "07:00 PM", "07:30 PM", "08:00 PM", "08:30 PM", "09:00 PM",
            "09:30 PM", "10:00 PM", "10:30 PM", "11:00 PM", "11:30 PM" };

    private String[] returnTimeStrings = { "12:00 AM", "12:30 AM", "01:00 AM",
            "01:30 AM", "02:00 AM", "02:30 AM", "03:00 AM", "03:30 AM",
            "04:00 AM", "04:30 AM", "05:00 AM", "05:30 AM", "06:00 AM",
            "06:30 AM", "07:00 AM", "07:30 AM", "08:00 AM", "08:30 AM",
            "09:00 AM", "09:30 AM", "10:00 AM", "10:30 AM", "11:00 AM",
            "11:30 AM", "12:00 PM", "12:30 PM", "01:00 PM", "01:30 PM",
            "02:00 PM", "02:30 PM", "03:00 PM", "03:30 PM", "04:00 PM",
            "04:30 PM", "05:00 PM", "05:30 PM", "06:00 PM", "06:30 PM",
            "07:00 PM", "07:30 PM", "08:00 PM", "08:30 PM", "09:00 PM",
            "09:30 PM", "10:00 PM", "10:30 PM", "11:00 PM", "11:30 PM" };

    private String[] locationStrings;
    private String[] carTypesStrings;
    private String[] carsModelStrings;

    JButton Search;

    java.sql.Date PickUpTimeDate, ReturnTimeDate;
    String pickUpTimeString, returnTimeString;

    String locationString, carTypeString, carModelString, VehicleNumber;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public RentCarPanel(MemberUser member) {
        this.setBackground(Color.green);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBounds(300, 150, 450, 400);
        this.member = member;

        RentACar = new JLabel("Rent A Car");
        RentACar.setFont(new Font("Helvetica", Font.BOLD, 70));

        PickUpTime = new JLabel("Pick Up Time:");
        java.util.Date pickdate = new java.util.Date();
        PickUpDateCombo = new JXDatePicker(pickdate);
        PickUpTimeCombo = new JComboBox(pickUpTimeStrings);

        ReturnTime = new JLabel("Return Time:");
        java.util.Date returndate = new java.util.Date();
        ReturnDateCombo = new JXDatePicker(returndate);
        ReturnTimeCombo = new JComboBox(returnTimeStrings);

        Connection conn = connection.createConnection();
        try {
            String statement = "SELECT distinct Car_Type FROM Car";
            PreparedStatement prep = conn.prepareStatement(statement);
            ResultSet rs = (ResultSet) prep.executeQuery();

            int rowcount = 0;
            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst();
            }
            carTypesStrings = new String[rowcount];

            int i = 0;
            while (rs.next()) {
                carTypesStrings[i++] = rs.getString("Car_Type");
            }
            prep.close();

            String statement1 = "SELECT DISTINCT Model_Name FROM Car";
            PreparedStatement prep1 = conn.prepareStatement(statement1);
            ResultSet rs1 = (ResultSet) prep1.executeQuery();

            rowcount = 0;
            if (rs1.last()) {
                rowcount = rs1.getRow();
                rs1.beforeFirst();
            }
            carsModelStrings = new String[rowcount];

            i = 0;
            while (rs1.next()) {
                carsModelStrings[i++] = rs1.getString("Model_Name");
            }
            prep1.close();

            String statement2 = "SELECT distinct Location_Name FROM Car";
            PreparedStatement prep2 = conn.prepareStatement(statement2);
            ResultSet rs2 = (ResultSet) prep2.executeQuery();

            rowcount = 0;
            if (rs2.last()) {
                rowcount = rs2.getRow();
                rs2.beforeFirst();
            }
            locationStrings = new String[rowcount];

            i = 0;
            while (rs2.next()) {
                locationStrings[i++] = rs2.getString("Location_Name");
            }
            prep2.close();

            connection.closeConnection(conn);
        } catch (SQLException e) {
            connection.closeConnection(conn);
        }

        this.add(RentACar);

        this.add(PickUpTime);
        this.add(PickUpDateCombo);
        this.add(PickUpTimeCombo);

        this.add(ReturnTime);
        this.add(ReturnDateCombo);
        this.add(ReturnTimeCombo);

        Location = new JLabel("Location:");
        this.add(Location);
        LocationCombo = new JComboBox(locationStrings);
        this.add(LocationCombo);

        Cars = new JLabel("Cars:");
        this.add(Cars);
        CarTypesCombo = new JComboBox(carTypesStrings);
        this.add(CarTypesCombo);

        CarsCombo = new JComboBox(carsModelStrings);
        this.add(CarsCombo);

        Search = new JButton("Search");
        Search.addActionListener(new SearchButtonListener());

        this.add(Search);
    }

    public JPanel getPanel() {
        return this;
    }

    private class SearchButtonListener implements ActionListener {
        @SuppressWarnings("deprecation")
        public void actionPerformed(ActionEvent event) {
            pickUpTimeString = (String) PickUpTimeCombo.getSelectedItem();
            returnTimeString = (String) ReturnTimeCombo.getSelectedItem();
            Integer tempI = null, tempI1 = null;
            StringBuilder sb = new StringBuilder();
            String date = PickUpDateCombo.getDate().toString();
            sb.append(date.substring(24, 28));
            sb.append('-');
            String month = new Integer(PickUpDateCombo.getDate().getMonth() + 1)
            .toString();
            sb.append(month);
            sb.append('-');
            sb.append(date.substring(8, 10));
            sb.append(' ');
            String[] str = pickUpTimeString.substring(0, 5).split(":");

            String pickDetect = pickUpTimeString.substring(6, 8);
            if (month.length() == 1) {
                if(pickDetect.equals("PM")){
                    tempI = Integer.parseInt(pickUpTimeString.substring(0, 2)) + 12;
                    sb.append(str[0]).append(':').append(tempI.toString());
                }else{
                    sb.append(str[0]).append(':').append(pickUpTimeString.substring(3, 5));
                }
            } else {
                if(pickDetect.equals("PM")){
                    tempI = Integer.parseInt(pickUpTimeString.substring(0, 2)) + 12;
                    sb.append(tempI.toString()).append(':').append(str[1]);
                } else{
                    sb.append(str[0]).append(':').append(str[1]);
                }
            }
            sb.append(":00");
            java.util.Date result;
            SimpleDateFormat formatter = new SimpleDateFormat(
            "yyyy-MM-dd hh:mm:ss");
            try {
                result = formatter.parse(sb.toString());
                PickUpTimeDate = new java.sql.Date(result.getTime());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }

            StringBuilder sb1 = new StringBuilder();
            String date1 = ReturnDateCombo.getDate().toString();
            sb1.append(date1.substring(24, 28));
            sb1.append('-');
            String month1 = new Integer(
                    ReturnDateCombo.getDate().getMonth() + 1).toString();
            if (month1.length() == 1) {
                sb1.append('0');
            }
            sb1.append(month1);
            sb1.append('-');
            sb1.append(date1.substring(8, 10));
            sb1.append(' ');
            String[] str1 = returnTimeString.substring(0, 5).split(":");
            String retDetect = returnTimeString.substring(6, 8);
            if (month1.length() == 1) {
                if(retDetect.equals("PM")){
                    tempI1 = Integer.parseInt(returnTimeString.substring(0, 2)) + 12;
                    sb1.append(tempI1.toString()).append(':').append(str1[1]);
                }else{
                    sb1.append(str1[0]).append(':').append(returnTimeString.substring(3, 5));
                }
            }
            else {
                if(retDetect.equals("PM")){
                    tempI1 = Integer.parseInt(returnTimeString.substring(0, 2)) + 12;
                    sb1.append(tempI1.toString()).append(':').append(str[1]);
                }else{
                    sb1.append(str1[0]).append(':').append(str1[1]);
                }
            }
            sb1.append(":00");
            java.util.Date result1;
            SimpleDateFormat formatter1 = new SimpleDateFormat(
                    "yyyy-MM-dd hh:mm:ss");
            try {
                result1 = formatter1.parse(sb1.toString());
                ReturnTimeDate = new java.sql.Date(result1.getTime());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
            long diff = (ReturnTimeDate.getTime() - PickUpTimeDate.getTime());
            if (PickUpTimeDate.compareTo(ReturnTimeDate) > 0) {
                JOptionPane
                .showMessageDialog(
                        new JFrame(),
                        "Return time is before pick up time! Please choose a another return/pickup time",
                        "Inane error", JOptionPane.ERROR_MESSAGE);
            } else if (diff > 172800000) {
                JOptionPane
                .showMessageDialog(
                        new JFrame(),
                        "You cannot book a car for more than 2 days! Please choose a another return time before.",
                        "Inane error", JOptionPane.ERROR_MESSAGE);
            } else {
                locationString = LocationCombo.getSelectedItem().toString();
                carTypeString = CarTypesCombo.getSelectedItem().toString();
                carModelString = CarsCombo.getSelectedItem().toString();
                Reservation reservation = new Reservation(member.getUsername(),
                        VehicleNumber, locationString, PickUpTimeDate, 
                        ReturnTimeDate);
                Car car = new Car(carTypeString, locationString, carModelString);
                Object[][] rowDataArr = null;

                CarDao carDao = new CarDao();
                rowDataArr = carDao.getCarAvailablity(car, reservation);

                if (rowDataArr == null) {
                    JOptionPane
                    .showMessageDialog(
                            new JFrame(),
                            "No Car Available For these Times. Please enter a new pick up time or return time!",
                            "Inane error", JOptionPane.ERROR_MESSAGE);
                } else {
                    for(int i=0; i<rowDataArr.length; i++) {
                        if (member.getDrivingPlan().getName() == "Frequent Driving Plan") {
                            long sec = diff/1000;
                            long min = sec/60;
                            int hr = (int) min/60;
                            rowDataArr[i][13] = (Integer)rowDataArr[i][5] * hr;
                            reservation.setEstimatedCost((Integer) rowDataArr[i][13]);
                        }
                        else if(member.getDrivingPlan().getName() == "Daily Driving Plan") {
                            long sec = diff/1000;
                            long min = sec/60;
                            int hr = (int) min/60;
                            rowDataArr[i][13] = (Integer)rowDataArr[i][6] * hr;
                            reservation.setEstimatedCost((Integer) rowDataArr[i][13]);
                        }
                        else if(member.getDrivingPlan().getName() == "Occasional Driving Plan") {
                            long sec = diff/1000;
                            long min = sec/60;
                            int hr = (int) min/60;
                            rowDataArr[i][13] = (Integer)rowDataArr[i][4] * hr;
                            reservation.setEstimatedCost((Integer) rowDataArr[i][13]);
                        }
                    }
                    JFrame mainFrame = MainFrame.getMain();
                    CarAvailPanel panel = new CarAvailPanel(member, rowDataArr,
                            reservation);
                    mainFrame.setContentPane(panel);
                    mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                    mainFrame.setVisible(true);
                    mainFrame.repaint();
                }
            }
        }
    }
}
