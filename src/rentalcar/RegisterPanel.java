package rentalcar;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.User.EmployeeUser;
import core.User.MemberUser;
import core.User.User;
import core.User.UserDao;
import core.User.UserType;

/**
 * @author Rochelle Lobo
 * This class helps the user in register in the system.
 */

@SuppressWarnings("rawtypes")
public class RegisterPanel extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;

	final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    JLabel userN, passw, confPwd, type;
    JTextField userName, password, confirmP;
    JButton register, cancel;
	JComboBox userType;
    UserType[] newTypes;
        
    @SuppressWarnings("unchecked")
	public RegisterPanel() {
        this.setLayout(new FlowLayout());
        setBounds(screenSize.width/2-200, screenSize.height/2-100, 
                375, 190);
        
        userN = new JLabel("Username:                ");
        userName = new JTextField(20);
    
        passw = new JLabel("Password:                 ");
        password = new JTextField(20);
        
        confPwd = new JLabel("Confirm Password: ");
        confirmP = new JTextField(20);
        
        type = new JLabel("Type of User: ");
        UserType[] types = UserType.values();
        UserType[] newTypes = new UserType[types.length-1];
        for(int i=0; i<types.length-1; i++){
            newTypes[i]=types[i];
        }
        userType = new JComboBox(newTypes);
        
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        register = new JButton("Register");
        register.addActionListener(this);
        
        
        this.add(userN);
        this.add(userName);
        this.add(passw);
        this.add(password);
        this.add(confPwd);
        this.add(confirmP);
        this.add(type);
        this.add(userType);
        this.add(cancel);
        this.add(register);       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == register) {
            if(!confirmP.getText().equals(password.getText())){
                JOptionPane.showMessageDialog(new JFrame(),
                        "Passwords do not match",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                UserDao userDao = new UserDao();
                User userObj = userDao.addUser(userName.getText(),
                        password.getText(), UserType.values()[userType.getSelectedIndex()]);
                if(userObj == null) {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Username already exists",
                            "Inane error",
                            JOptionPane.ERROR_MESSAGE);
                }
                else if(userObj.getType() == UserType.MEMBER) {
                    JFrame mainFrame = MainFrame.getMain();
                    MemberUser memUser = (MemberUser) userObj;
                    MemberHomePanel panel = new MemberHomePanel(memUser);
                    mainFrame.setContentPane(panel);
                    mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                    mainFrame.setVisible(true);
                    mainFrame.repaint();
                }
                else if(userObj.getType() == UserType.EMPLOYEE) {
                    JFrame mainFrame = MainFrame.getMain();
                    EmployeeUser empUser = (EmployeeUser) userObj;
                    EmployeeHomePanel panel = new EmployeeHomePanel(empUser);
                    mainFrame.setContentPane(panel);
                    mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                    mainFrame.setVisible(true);
                    mainFrame.repaint();
                }
            
            }
        }
        else {
            JFrame mainFrame = MainFrame.getMain();
            mainFrame.setContentPane(new LoginPanel());
            mainFrame.setBounds(mainFrame.getContentPane().getBounds());
            mainFrame.setVisible(true);
            mainFrame.repaint();
        }
    }
}
