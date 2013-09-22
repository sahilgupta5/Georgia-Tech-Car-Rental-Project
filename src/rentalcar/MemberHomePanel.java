package rentalcar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import core.User.MemberUser;

/**
 * @author Sahil Gupta 
 * This class is the home page for students and Georgia Tech staff/faculty.
 */

public class MemberHomePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private MemberUser member;
    private ButtonGroup group;
    private JLabel heading;
    private JRadioButton carRental, enterViewPI, viewRentalInfo;
    private String headingString = "Homepage";
    private String carRentalString = "Rent a car";
    private String enterViewPIString = "Enter/View Personal Information";
    private String viewRentalInfoString = "View Rental Information";

    /**
     * Sets up a panel with a label and a set of radio buttons that control its
     * text.
     */
    public MemberHomePanel(MemberUser member) {
        this.member = member;

        this.setBackground(Color.green);
        this.setLayout(new FlowLayout());
        setBounds(screenSize.width/2-200, screenSize.height/2-100, 
                250, 225);

        heading = new JLabel(headingString);
        heading.setFont(new Font("Helvetica", Font.BOLD, 40));

        carRental = new JRadioButton(carRentalString);
        carRental.setBackground(Color.green);
        enterViewPI = new JRadioButton(enterViewPIString);
        enterViewPI.setBackground(Color.green);
        viewRentalInfo = new JRadioButton(viewRentalInfoString);
        viewRentalInfo.setBackground(Color.green);
        
        group = new ButtonGroup();
        group.add(carRental);
        group.add(enterViewPI);
        group.add(viewRentalInfo);

        JButton nextButton = new JButton("Next >>");
        nextButton.addActionListener(new NextButtonListener());

        this.add(heading);
        this.add(carRental);
        this.add(enterViewPI);
        this.add(viewRentalInfo);
        this.add(nextButton);
    }

    private class NextButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (carRental.isSelected()) {
            	if(member.getDrivingPlan() == null || member.getCreditCard() == null || member.getFirstName() == null){
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Must fill in personal information, driving plan and credit card information!",
                            "Inane error",
                            JOptionPane.ERROR_MESSAGE);

            	}else{
                    JFrame mainFrame = MainFrame.getMain();
                    mainFrame.setContentPane(new RentCarPanel(member));
                    mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                    mainFrame.setVisible(true);
                    mainFrame.repaint();
            	}
            } else if (enterViewPI.isSelected()) {
                JFrame mainFrame = MainFrame.getMain();
                MemberPInfoPanel pInfoPanel = new MemberPInfoPanel(member);
                JScrollPane scrollPane = new JScrollPane(pInfoPanel);
                scrollPane.setBounds(pInfoPanel.getBounds());
                mainFrame.setContentPane(scrollPane);
                mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                mainFrame.setVisible(true);
                mainFrame.repaint();
            } else if (viewRentalInfo.isSelected()) {
                JFrame mainFrame = MainFrame.getMain();
                mainFrame.setContentPane(new RentInfoPanel(member));
                mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                mainFrame.setVisible(true);
                mainFrame.repaint();
            }
        }
    }
}
