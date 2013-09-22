package rentalcar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import core.DrivingPlan.DrivingPlan;
import core.DrivingPlan.DrivingPlanDao;
import core.User.MemberUser;

/**
 * 
 * @author Sahil Gupta This page shows the plan details of the different driving
 *         plans.
 */

public class PlanDetailsPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int DIALOGWIDTH = 500, DIALOGHEIGHT = 500;
 
    Object[] tableElement = new Object[4];
    ArrayList<Object[]> rowDataArr = new ArrayList<Object[]>();
    Object[][] rowData;
    Object[] columnNames = { "Plan_Type", "Monthly_Payment", "Discount",
    "Annual_Fees" };
    JTable table;

    JLabel pageHeading;
    String drivingPlan, discout, annualFees;
    Float monthlyPayment;

    public PlanDetailsPanel(MemberUser member) {
        this.setBounds(screenSize.width / 3, screenSize.height / 3,
                DIALOGWIDTH, DIALOGHEIGHT);
        
        pageHeading = new JLabel("Driving Plans");
        pageHeading.setFont(new Font("Helvetica", Font.BOLD, 40));

        DrivingPlanDao planObj = new DrivingPlanDao();
        ArrayList<DrivingPlan> plans = planObj.getDrivingPlans(member);
        rowData = new Object[plans.size()][4];
        for(int i=0; i<plans.size(); i++){
            rowData[i][0] = plans.get(i).getName();
            rowData[i][1] = plans.get(i).getMonthlyPay();
            rowData[i][2] = plans.get(i).getDiscount();
            rowData[i][3] = plans.get(i).getAnnualFees();
        }
        
        table = new JTable(rowData, columnNames);

        this.setLayout(new BorderLayout());
        this.add(pageHeading, BorderLayout.NORTH);
        this.add(new JScrollPane(table), BorderLayout.CENTER);

        this.setBackground(Color.green);
        this.setBounds(400, 300, 500, 200);

    }
}
