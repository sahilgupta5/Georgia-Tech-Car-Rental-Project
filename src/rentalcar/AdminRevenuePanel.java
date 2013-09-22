package rentalcar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import core.DBConnection;
import core.Report.ReportDao;
import core.User.AdminUser;

/**
 * 
 * @author Sahil Gupta
 * 
 * This class represents the Administrator Revenue report.
 *
 */

public class AdminRevenuePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    DBConnection connection = new DBConnection();

    AdminUser admin;

    Object[] tableElement;
    Object[] columnNames = { "Vehicle Sno", "Type", "Car Model",
            "Reservation revenue", "Late Fees Reveue" };
    Object[][] rowData;

    JTable table;
    JLabel pageHeading;

    public AdminRevenuePanel(AdminUser admin) {
        this.admin = admin;

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        pageHeading = new JLabel("Revenue Generated");
        pageHeading.setFont(new Font("Helvetica", Font.BOLD, 70));
        JButton logout = new JButton("Logout");
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = MainFrame.getMain();
                mainFrame.setContentPane(new LoginPanel());
                mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                mainFrame.setVisible(true);
                mainFrame.repaint();
            }
        });
        subPanel.add(pageHeading, BorderLayout.WEST);
        subPanel.add(logout, BorderLayout.EAST);
        subPanel.setBackground(Color.green);

        ReportDao reportDao = new ReportDao();
        Object[][] rowData = reportDao.getAdminReportArray();

        table = new JTable(rowData, columnNames);

        this.setLayout(new BorderLayout());
        this.add(subPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        this.setBackground(Color.green);
        this.setBounds(0, 0, screenSize.width, screenSize.height);
    }
}
