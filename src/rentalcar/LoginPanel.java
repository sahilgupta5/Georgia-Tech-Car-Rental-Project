package rentalcar;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import core.User.AdminUser;
import core.User.EmployeeUser;
import core.User.MemberUser;
import core.User.User;
import core.User.UserDao;
import core.User.UserType;

/**
 * 
 * @author Rochelle Lobo
 * 
 *	This class is the login screen where the user enters the system.
 */

public class LoginPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    JLabel userN, passw;
    JTextField userName, password;
    JButton login, newUser;

    public LoginPanel() {
        this.setLayout(new FlowLayout());
        setBounds(screenSize.width/2-200, screenSize.height/2-100, 
                350, 130);
        
        userN = new JLabel("Username");
        userName = new JTextField(20);

        passw = new JLabel("Password");
        password = new JTextField(20);

        login = new JButton("Login");
        login.addActionListener(new LoginButtonListener());
        newUser = new JButton("New User?");
        newUser.addActionListener(new NewUserButtonListener());

        this.add(userN);
        this.add(userName);
        this.add(passw);
        this.add(password);
        this.add(newUser);
        this.add(login);
    }

    private class LoginButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String username, pword;
            username = userName.getText();
            pword = password.getText();
            if(username == null || password == null){
                JOptionPane.showMessageDialog(new JFrame(),
                        "Username or Password not entered",
                        "Inane error",
                        JOptionPane.ERROR_MESSAGE);
            }else{
                UserDao userDao = new UserDao();
                User userObj = userDao.login(username, pword);
                if(userObj == null) {
                    JOptionPane.showMessageDialog(new JFrame(),
                            "Incorrect Login",
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
                else if(userObj.getType() == UserType.ADMIN) {
                    JFrame mainFrame = MainFrame.getMain();
                    AdminUser adminUser = (AdminUser) userObj;
                    AdminRevenuePanel panel = new AdminRevenuePanel(adminUser);
                    mainFrame.setContentPane(panel);
                    mainFrame.setBounds(mainFrame.getContentPane().getBounds());
                    mainFrame.setVisible(true);
                    mainFrame.repaint();
                }
            }
        }
    }

    private class NewUserButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            JFrame mainFrame = MainFrame.getMain();
            mainFrame.setContentPane(new RegisterPanel());
            mainFrame.setBounds(mainFrame.getContentPane().getBounds());
            mainFrame.setVisible(true);
            mainFrame.repaint();
        }
    }
}
