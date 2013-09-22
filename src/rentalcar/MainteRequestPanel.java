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

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import com.mysql.jdbc.ResultSet;

import core.DBConnection;
import core.User.EmployeeUser;

/**
 * @author Sahil Gupta 
 * 
 * This class is page for submitting maintenance requests.
 * 
 */

public class MainteRequestPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    final static Dimension screenSize = Toolkit.getDefaultToolkit()
    .getScreenSize();

    protected EmployeeUser employee;
    static DBConnection connection = new DBConnection();

    JLabel pageHeader;
    JLabel Location, Cars, ProblemDescLabel;

    @SuppressWarnings("rawtypes")
    JComboBox LocationCombo, CarsCombo;

    private String[] locationStrings;
    private String[] carsModelStrings;

    JTextArea ProblemDesc;

    String locationString, carModelString;

    JButton submitReqButton;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public MainteRequestPanel(EmployeeUser employee) {
        this.setBackground(Color.green);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBounds(300, 150, 450, 400);
        this.employee = employee;

        pageHeader = new JLabel("Maintenance Requests");
        pageHeader.setFont(new Font("Helvetica", Font.BOLD, 70));

        int i;
        Connection conn = connection.createConnection();
        try {
            String statement1 = "SELECT DISTINCT Model_Name FROM Car";
            PreparedStatement prep1 = conn.prepareStatement(statement1);
            ResultSet rs1 = (ResultSet) prep1.executeQuery();

            int rowcount = 0;
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

        this.add(pageHeader);
        Location = new JLabel("Choose Location:");
        this.add(Location);
        LocationCombo = new JComboBox(locationStrings);
        this.add(LocationCombo);

        Cars = new JLabel("Choose car:");
        this.add(Cars);
        CarsCombo = new JComboBox(carsModelStrings);
        this.add(CarsCombo);

        ProblemDescLabel = new JLabel("Brief Description of the Problem: ");
        this.add(ProblemDescLabel);

        ProblemDesc = new JTextArea();
        DefaultCaret caret = (DefaultCaret) ProblemDesc.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        ProblemDesc.setSize(100, 200);
        this.add(ProblemDesc);

        submitReqButton = new JButton("Submit Request");
        submitReqButton.addActionListener(new submitReqButtonListener());
        this.add(submitReqButton);
    }

    public JPanel getPanel() {
        return this;
    }

    private class submitReqButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            Connection conn = connection.createConnection();
            String statement = "SELECT Vehicle_Sno FROM Car WHERE Location_Name = ? AND Model_Name = ?";
            String locName = (String) LocationCombo.getSelectedItem();
            String ModelName = (String) CarsCombo.getSelectedItem();
            String VehicleNumber = null;
            PreparedStatement prep;
            try {
                prep = conn.prepareStatement(statement);
                prep.setString(1, locName);
                prep.setString(2, ModelName);
                ResultSet rs = (ResultSet) prep.executeQuery();
                while(rs.next()){
                    VehicleNumber = rs.getString("Vehicle_Sno");
                }
                prep.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(VehicleNumber == null){
                JOptionPane.showMessageDialog(new JFrame(), "Please choose a valid Location/Car combination!","Inane error", JOptionPane.ERROR_MESSAGE);
            }else{
                String statement1 = "SELECT Vehicle_Sno, Date_Time, Username FROM Maintenance_Request WHERE Vehicle_Sno = ?";
                java.sql.Date DateT = null;
                String UserN = null;
                PreparedStatement prep1;
                try {
                    prep1 = conn.prepareStatement(statement1);
                    prep1.setString(1, VehicleNumber);
                    ResultSet rs1 = (ResultSet) prep1.executeQuery();
                    while(rs1.next()){
                        VehicleNumber = rs1.getString("Vehicle_Sno");
                        DateT = rs1.getDate("Date_Time");
                        UserN = rs1.getString("Username");
                    }
                    prep1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String statement2 = "INSERT INTO Maintenance_Request VALUES (?, ?, ?)";
                PreparedStatement prep2;
                try {
                    prep2 = conn.prepareStatement(statement2);
                    prep2.setString(1, VehicleNumber);
                    prep2.setDate(2, DateT);
                    prep2.setString(3, UserN);
                    prep2.executeUpdate();
                    prep2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                String statement3 = "INSERT INTO Problems VALUES (?, ?, ?)";
                PreparedStatement prep3;
                try {
                    prep3 = conn.prepareStatement(statement3);
                    prep3.setString(1, VehicleNumber);
                    prep3.setDate(2, DateT);
                    prep3.setString(3, UserN);
                    prep3.executeUpdate();
                    prep3.close();
                    connection.closeConnection(conn);
                } catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Error in insertion, please select correct car location and car model!",
							"Inane error", JOptionPane.ERROR_MESSAGE);
                }
                JFrame mainFrame = MainFrame.getMain();
                mainFrame.setContentPane(new EmployeeHomePanel(employee));
                mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                mainFrame.setVisible(true);
                mainFrame.repaint();
            }
        }
    }
}