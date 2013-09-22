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
import core.User.EmployeeUser;

/**
 * 
 * @author Sahil Gupta
 * 
 * This class creates the panel for Location preference report panel accessible to the employee user.
 *
 */

public class LocPreferReportPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    DBConnection connection = new DBConnection();
    EmployeeUser employee;
    Object[] tableElement;
    Object[] columnNames = { "Month" , "Location", "No of Reservations", "Total no of hours"};
    Object[][] rowData;
    JTable table;
    JLabel pageHeading;
    JButton back;

    public LocPreferReportPanel(final EmployeeUser employee) {
        this.employee = employee;

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        pageHeading = new JLabel("Location Preference Report");
        pageHeading.setFont(new Font("Helvetica", Font.BOLD, 70));
        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame mainFrame = MainFrame.getMain();
                mainFrame.setContentPane(new EmployeeHomePanel(employee));
                mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                mainFrame.setVisible(true);
                mainFrame.repaint();
            }
        });
        subPanel.add(pageHeading, BorderLayout.WEST);
        subPanel.add(back, BorderLayout.EAST);
        subPanel.setBackground(Color.green);

        ReportDao reportDao = new ReportDao();
        rowData = reportDao.getLocPrefReportArray();

        table = new JTable(rowData, columnNames);

        this.setLayout(new BorderLayout());
        this.add(subPanel, BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        this.setBackground(Color.green);
        this.setBounds(0, 0, screenSize.width, screenSize.height);
    }
}
