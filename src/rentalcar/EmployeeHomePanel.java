package rentalcar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import core.User.EmployeeUser;

/**
 * @author Sahil Gupta
 *
 *This class is the home page for employees where can view, enter information and also access reports.
 *
 */

public class EmployeeHomePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JFrame mainFrame;
    private JLabel heading;
    private JButton Next;
    private ButtonGroup group;
    @SuppressWarnings({ "rawtypes" })
    private JComboBox chooseReport;
    private EmployeeUser employee;
    private JRadioButton manageCars, maintainanceRequests, rentalChangeRequest, viewReports;
    private String headingString = "Homepage";
    private String manageCarsString = "Manage Cars";
    private String maintainanceRequestsString = "Maintainance requests";
    private String rentalChangeRequestString = "Rental Change Request";
    private String viewReportsString = "View Reports";

    private String[] reportStrings = { "Location Preferance Report", "Frequent Users Report", "Maintenance History Report" };

    /**
     * Sets up a panel with a label and a set of radio buttons that control its
     * text.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public EmployeeHomePanel(final EmployeeUser employee) {
        mainFrame = MainFrame.getMain();
        this.employee = employee;

        this.setBackground(Color.green);
        this.setLayout(new FlowLayout());
        setBounds(screenSize.width/2-200, screenSize.height/2-100, 
                280, 300);

        JPanel subPanel = new JPanel();
        subPanel.setLayout(new BorderLayout());
        heading = new JLabel(headingString);
        heading.setFont(new Font("Helvetica", Font.BOLD, 35));
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
        subPanel.add(heading, BorderLayout.WEST);
        subPanel.add(logout, BorderLayout.EAST);
        subPanel.setBackground(Color.green);

        manageCars = new JRadioButton(manageCarsString);
        manageCars.setBackground(Color.green);
        maintainanceRequests = new JRadioButton(maintainanceRequestsString);
        maintainanceRequests.setBackground(Color.green);
        rentalChangeRequest = new JRadioButton(rentalChangeRequestString);
        rentalChangeRequest.setBackground(Color.green);
        viewReports = new JRadioButton(viewReportsString);
        viewReports.setBackground(Color.green);
        viewReports.addChangeListener(new ChangeListener() {   
            @Override
            public void stateChanged(ChangeEvent e) {
                if(viewReports.isSelected())
                    chooseReport.setEnabled(true);
                else
                    chooseReport.setEnabled(false);
            }
        });
        
        group = new ButtonGroup();
        group.add(manageCars);
        group.add(maintainanceRequests);
        group.add(rentalChangeRequest);
        group.add(viewReports);

        chooseReport = new JComboBox(reportStrings);
        chooseReport.setEnabled(false);

        Next = new JButton("Next >>");
        Next.addActionListener(new NextButtonListener());

        this.add(subPanel);
        this.add(manageCars);
        this.add(maintainanceRequests);
        this.add(rentalChangeRequest);
        this.add(viewReports);
        this.add(chooseReport);
        this.add(Next);
    }

    private class NextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
              if (manageCars.isSelected()) {
                mainFrame.setContentPane(new ManageCarsPanel(employee));
                mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                mainFrame.setVisible(true);
                mainFrame.repaint();
            } else if (maintainanceRequests.isSelected()) {
                mainFrame.setContentPane(new MainteRequestPanel(employee));
                mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                mainFrame.setVisible(true);
                mainFrame.repaint();
            } else if (rentalChangeRequest.isSelected()) {
                mainFrame.setContentPane(new RentChangePanel(employee));
                mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                mainFrame.setVisible(true);
                mainFrame.repaint();
            } else if (viewReports.isSelected()) {
                String reportSelection = reportStrings[chooseReport.getSelectedIndex()];
                if(reportSelection.equals("Location Preferance Report")) {
                    mainFrame.setContentPane(new LocPreferReportPanel(employee));
                    mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                    mainFrame.setVisible(true);
                    mainFrame.repaint();
                }
                else if(reportSelection.equals("Frequent Users Report")) {
                    mainFrame.setContentPane(new FreqUserReportPanel(employee));
                    mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                    mainFrame.setVisible(true);
                    mainFrame.repaint();
                }
                else if(reportSelection.equals("Maintenance History Report")) {
                    mainFrame.setContentPane(new MainHistoryReportPanel(employee));
                    mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                    mainFrame.setVisible(true);
                    mainFrame.repaint();
                }
            }
        }
    }
}
