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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableCellRenderer;

import core.DBConnection;
import core.Reservation.Reservation;
import core.User.MemberUser;

/**
 * 
 * @author Sahil Gupta
 * 
 * This class helps the user to view all the cars that are available for reservation and then make a reservation when the user selects an available car.
 * 
 */

public class CarAvailPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    MemberUser member;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int DIALOGWIDTH = 500, DIALOGHEIGHT = 500;

    Object[][] rowData;

    Object[] columnNames = { "Model Name", "Car Type", "Location Name",
            "Color", "Hourly Rate (Occasional Driving Plan)", 
            "Discounted Rate (Frequent Driving Plan)", "Discounted Rate " +
            "(Daily Driving Plan)", "Daily Rate", "Seating Cap",
            "Transmission Type", "Bluetooth", "Auxiliary Cable",
            "Estimated Cost"};

    Reservation reservation;
    JTable table;
    ButtonGroup group = new ButtonGroup();

    JLabel pageHeading;
    JButton reserveButton;
    String ReservationTime;
    JRadioButton Selected;
    DBConnection connection = new DBConnection();

    public CarAvailPanel(MemberUser member, Object[][] rowData, Reservation reservation) {
        this.reservation = reservation;
        this.member = member;
        this.rowData = rowData;
        this.setBounds(screenSize.width / 3, screenSize.height / 3,
                DIALOGWIDTH, DIALOGHEIGHT);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        pageHeading = new JLabel("Car Availability");
        pageHeading.setFont(new Font("Helvetica", Font.BOLD, 70));
        panel.add(pageHeading, BorderLayout.WEST);
        panel.setBackground(Color.GREEN);

        table = new JTable(rowData, columnNames);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(new ReserveButtonListener());

        panel.add(reserveButton, BorderLayout.EAST);
        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        this.setBackground(Color.green);
        this.setBounds(0, 0, screenSize.width, screenSize.height);
        this.add(panel, BorderLayout.NORTH);
    }

    private class ReserveButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int row = table.getSelectedRow();

            Connection conn = connection.createConnection();
            try {
                String statement = "INSERT INTO Reservation VALUES( ? , ? , ? , ? , ? , 0 , 0 , 0 , ? )";
                PreparedStatement prep = conn.prepareStatement(statement);
                prep.setString(1, (String) member.getUsername());
                prep.setTimestamp(2, new Timestamp(reservation.getPickupDateTime().getTime()));
                prep.setTimestamp(3,new Timestamp(reservation.getRetDateTime().getTime()));
                reservation.setVehicleSNO((String)  rowData[row][15]);
                prep.setString(4, reservation.getVehicleSNO());
                prep.setString(5, (String) reservation.getLocName());
                prep.setInt(6, reservation.getEstimatedCost());
                prep.execute();
                prep.close();
                connection.closeConnection(conn);

                JFrame mainFrame = MainFrame.getMain();
                mainFrame.setContentPane(new MemberHomePanel(member));
                mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                mainFrame.setVisible(true);
                mainFrame.repaint();
                
                JOptionPane pane = new JOptionPane("Reservation created!", JOptionPane.OK_OPTION);
                pane.setVisible(true);
            } catch (SQLException e) {
                connection.closeConnection(conn);
            }

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