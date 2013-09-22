package rentalcar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import org.jdesktop.swingx.JXDatePicker;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.User.MemberUser;

/**
 * @author Sahil Gupta
 *
 * This Panel shows the rental information of the car such as current and previous reservations.
 * 
 */

@SuppressWarnings("rawtypes")
public class RentInfoPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private MemberUser member;

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    DBConnection connection = new DBConnection();
    int DIALOGWIDTH = 500, DIALOGHEIGHT = 500;

    Object[] columnNamesCurrRes = { "Date", "Reservation Time", "Car",
            "Location", "Amount" };
    Object[][] rowDataCurrRes;

    Object[] columnNamesPrevRes = { "Date", "Reservation Time", "Car",
            "Location", "Amount", "Return Status" };
    Object[][] rowDataPrevRes;

    JTable tableCurrRes, tablePrevRes;
    JLabel pageHeading, chooseRetTime, currRes, PrevRes, currError, prevError, extendL;
    JComboBox ReturnTimeCombo;
    JXDatePicker ReturnDateCombo;
    JButton updateButton;

    java.sql.Date Pick_Up_Date_Time, newReservationTimeDate;

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

    @SuppressWarnings("unchecked")
    public RentInfoPanel(MemberUser member) {
        this.member = member;
        this.setBounds(screenSize.width / 3, screenSize.height / 3,
                DIALOGWIDTH, DIALOGHEIGHT);

        JPanel heading = new JPanel();
        heading.setLayout(new BorderLayout());
        pageHeading = new JLabel("Rental Information");
        pageHeading.setFont(new Font("Helvetica", Font.BOLD, 70));
        heading.add(pageHeading);

        Connection conn = connection.createConnection();
        int rowcount = 0;
        try {
            deleteViewsIfExisting();
            String statement = "CREATE VIEW  RESERVATION_INFO AS SELECT " +
            "Username, Pick_Up_Date_Time, Return_Date_Time, " +
            "Vehicle_Sno, Location_Name FROM Reservation WHERE " +
            "Username = ?";
            PreparedStatement prep = conn.prepareStatement(statement);
            prep.setString(1, member.getUsername());
            prep.execute();
            prep.close();

            statement = "SELECT CURRENT_DATE, TIME(Pick_Up_Date_Time),TIME(Return_Date_Time),"
                + " Model_Name, Location_Name, Estimated_Cost, Vehicle_Sno "
                + "FROM RESERVATION_INFO NATURAL JOIN AMOUNT "
                + "WHERE Username= ?";

            prep = conn.prepareStatement(statement);
            prep.setString(1, member.getUsername());
            ResultSet rs = (ResultSet) prep.executeQuery();

            if (rs.last()) {
                rowcount = rs.getRow();
                rs.beforeFirst();
            }
            rowDataCurrRes = new Object[rowcount][6];
            for (int i = 0; rs.next(); i++) {
                rowDataCurrRes[i][0] = rs.getDate("CURRENT_DATE");
                Pick_Up_Date_Time = rs.getDate(2);
                String pickT = rs.getDate(2).toString();
                String returnT = rs.getDate(3).toString();
                String reservationTime = "Day: " + pickT.substring(8, 10)
                + " Time: " + pickT.substring(0, 5) + " to " + "Day: "
                + returnT.substring(8, 10) + " Time: "
                + returnT.substring(0, 5);
                rowDataCurrRes[i][1] = reservationTime;
                rowDataCurrRes[i][2] = rs.getString("Model_Name");
                rowDataCurrRes[i][3] = rs.getString("Location_Name");
                rowDataCurrRes[i][4] = rs.getInt("Estimated_Cost");
                rowDataCurrRes[i][5] = rs.getString("Vehicle_Sno");
            }
            prep.close();
        } catch (SQLException e) {
            connection.closeConnection(conn);
        }
        int rowcount1 = 0;
        try {
            deleteViewsIfExisting();
            String statement = "CREATE VIEW  RESERVATION_INFO AS SELECT " +
            "Username, Pick_Up_Date_Time, Return_Date_Time, " +
            "Vehicle_Sno, Location_Name FROM Reservation WHERE " +
            "Username = ?";
            PreparedStatement prep = conn.prepareStatement(statement);
            prep.setString(1, member.getUsername());
            prep.execute();
            prep.close();
            String statement1 = "SELECT CURRENT_DATE, Pick_Up_Date_Time , Return_Date_Time,"
                + " Model_Name, Location_Name, Estimated_Cost "
                + "FROM RESERVATION_INFO NATURAL JOIN AMOUNT "
                + "WHERE Username= ?";

            PreparedStatement prep1 = conn.prepareStatement(statement1);
            prep1.setString(1, member.getUsername());
            ResultSet rs1 = (ResultSet) prep1.executeQuery();
            if (rs1.last()) {
                rowcount1 = rs1.getRow();
                rs1.beforeFirst();
            }
            rowDataPrevRes = new Object[rowcount1][6];
            for (int i = 0; rs1.next(); i++) {
                rowDataPrevRes[i][0] = rs1.getDate("CURRENT_DATE");

                String pickT = rs1.getDate(2).toString();
                String returnT = rs1.getDate(3).toString();
                String reservationTime = "Day: " + pickT.substring(8, 10)
                + " Time: " + pickT.substring(0, 5) + " to " + "Day: "
                + returnT.substring(8, 10) + " Time: "
                + returnT.substring(0, 5);
                rowDataPrevRes[i][1] = reservationTime;
                rowDataPrevRes[i][2] = rs1.getString("Model_Name");
                rowDataPrevRes[i][3] = rs1.getString("Location_Name");
                rowDataPrevRes[i][4] = rs1.getInt("Estimated_Cost");
                rowDataPrevRes[i][5] = rs1.getString("Return_Status");
            }
            prep1.close();
        } catch (SQLException e) {
            connection.closeConnection(conn);
        }
        connection.closeConnection(conn);

        JPanel panel = new JPanel();
        currRes = new JLabel("Current Reservation");
        extendL = new JLabel("Select a row to extend the reservation and enter the details below.");

        if (rowcount != 0) {
            tableCurrRes = new JTable(rowDataCurrRes, columnNamesCurrRes);
            tableCurrRes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            chooseRetTime = new JLabel("Choose Return Time: ");
            java.util.Date returndate = new java.util.Date();
            ReturnDateCombo = new JXDatePicker(returndate);
            ReturnTimeCombo = new JComboBox(returnTimeStrings);
            updateButton = new JButton("Update");
            updateButton.addActionListener(new UpdateButtonListener());
            //            this.add(tableCurrRes);
            //            this.add(new JScrollPane(tableCurrRes));
            panel.add(extendL);
            panel.add(chooseRetTime);
            panel.add(ReturnDateCombo);
            panel.add(ReturnTimeCombo);
            panel.add(updateButton);

        } else {
            currError = new JLabel("No Current Reservations");
            //            panel.add(currError);
        }

        PrevRes = new JLabel("Previous Reservation");

        if (rowcount1 != 0) {
            tablePrevRes = new JTable(rowDataPrevRes, columnNamesPrevRes);
            tablePrevRes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            //            this.add(new JScrollPane(tableCurrRes), BorderLayout.CENTER);
            //            this.add(tablePrevRes);
        } else {
            prevError = new JLabel("No Previous Reservations");
            //            this.add(prevError);
        }

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(heading);
        this.add(currRes);
        if(currError != null)
            this.add(currError);
        this.add(new JScrollPane(tableCurrRes));
        this.add(panel);
        this.add(PrevRes);
        if(prevError != null)
            this.add(prevError);
        this.add(new JScrollPane(tablePrevRes));

        this.setBackground(Color.green);
        this.setBounds(0, 0, screenSize.width, screenSize.height-30);
    }

    private class UpdateButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int row = tableCurrRes.getSelectedRow();
            if(row == -1) {
                JOptionPane.showMessageDialog(new JFrame(),
                        "Row from Current Reservations is not selected, cannot update time.",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                @SuppressWarnings("unused")
				Object[] rowDataCurrSelected = new Object[6];
                rowDataCurrSelected = rowDataCurrRes[row];
                StringBuilder sb = new StringBuilder();
                String returnTString = (String) ReturnTimeCombo.getSelectedItem();

                String date = ReturnDateCombo.getDate().toString();
                sb.append(date.substring(24, 28));
                sb.append('-');
                @SuppressWarnings("deprecation")
                String month = new Integer(ReturnDateCombo.getDate().getMonth() + 1).toString();
                if(month.length() == 1){
                    sb.append('0');
                }
                sb.append(month);
                sb.append('-');
                sb.append(date.substring(8, 10));
                sb.append(' ');
                sb.append(returnTString.substring(0, 5));
                sb.append(":00");
                java.util.Date result;
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try {
                    result = formatter.parse (sb.toString());
                    newReservationTimeDate = new java.sql.Date(result.getTime());
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                
                String statement = "UPDATE Reservation SET Return_Date_Time = ? " +
                "WHERE (Username = ? AND Pick_Up_Date_Time = ?) " +
                "AND ? < ALL (SELECT Pick_Up_Date_Time FROM Reservation " +
                "WHERE Vehicle_Sno= ?)";
                Connection conn = connection.createConnection();

                try {
                    PreparedStatement prep = conn.prepareStatement(statement);
                    prep.setTimestamp(1, new Timestamp(newReservationTimeDate.getTime()));
                    prep.setString(2, member.getUsername());
                    prep.setTimestamp(3, new Timestamp(Pick_Up_Date_Time.getTime()));
                    prep.setTimestamp(4, new Timestamp(Pick_Up_Date_Time.getTime()));
                    prep.setString(5, (String)rowDataCurrRes[row][5]);
                    prep.executeUpdate();
                    prep.close();
                    connection.closeConnection(conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void deleteViewsIfExisting() {
        Connection conn = connection.createConnection();
        try {
            String statement = "Drop view RESERVATION_INFO";
            PreparedStatement prep = conn.prepareStatement(statement);
            prep.execute();
            prep.close();
            connection.closeConnection(conn);
        }
        catch (Exception e) {
            connection.closeConnection(conn);
        }
    }

    public class LineWrapCellRenderer  extends JTextArea implements TableCellRenderer {
        private static final long serialVersionUID = 1L;

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {
            this.setText((String)value);
            this.setWrapStyleWord(true);            
            this.setLineWrap(true);         
            return this;
        }
    }

}
