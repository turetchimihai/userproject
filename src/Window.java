import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

public class Window extends JFrame implements  ActionListener {

    private JButton    loginButton;
    private JButton    registerButton;
    private JButton    switchButton;
    private JButton    okButtonLog;
    private JButton    okButtonReg;
    private JLabel     userNameLabel;
    private JLabel     passowrdLabel;
    private JTextField userNameText;
    private JTextField passwordText;
    private JDialog    dialogLog;
    private JDialog    dialogReg;
    private JLabel     dialogLabelLog;
    private JLabel     dialogLabelReg;
    private Controller controller;

    public Window() throws SQLException, ClassNotFoundException {
        super("Loggin");
        setLayout(null);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width/2 - 150, dimension.height/2 - 60, 300, 120);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginButton    = new JButton("Login");
        registerButton = new JButton("Register");
        switchButton   = new JButton("Switch");
        userNameLabel  = new JLabel("Username");
        passowrdLabel  = new JLabel("Password");
        userNameText   = new JTextField(20);
        passwordText   = new JTextField(20);
        dialogLog      = new JDialog(this, "Login", true);
        dialogReg      = new JDialog(this, "Register", true);
        okButtonLog    = new JButton("OK");
        okButtonReg    = new JButton("OK");
        dialogLabelLog = new JLabel("gfdsgfds");
        dialogLabelReg = new JLabel("Hello");

        userNameLabel.setBounds(10, 10, 80, 20);
        passowrdLabel.setBounds(10, 30, 80, 20);
        userNameText.setBounds(90, 10, 200, 20);
        passwordText.setBounds(90, 30, 200, 20);
        loginButton.setBounds(10, 60, 130, 20);
        registerButton.setBounds(160, 60, 130, 20);
        dialogLog.setBounds(dimension.width/2 - 150, dimension.height/2 - 60, 300, 120);
        dialogReg.setBounds(dimension.width/2 - 150, dimension.height/2 - 60, 300, 120);
        dialogLog.setLayout(null);
        dialogReg.setLayout(null);
        okButtonLog.setBounds(10, 60, 280, 20);
        okButtonReg.setBounds(10, 60, 280, 20);
        dialogLabelLog.setBounds(10, 10, 280, 20);
        dialogLabelReg.setBounds(10, 10, 280, 20);

        add(loginButton);
        add(passowrdLabel);
        add(userNameLabel);
        add(userNameText);
        add(passwordText);
        add(registerButton);
        dialogLog.add(okButtonLog);
        dialogReg.add(okButtonReg);
        dialogLog.add(dialogLabelLog);
        dialogReg.add(dialogLabelReg);

        revalidate();
        setVisible(true);

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        okButtonLog.addActionListener(this);
        okButtonReg.addActionListener(this);

        dialogLog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    controller.disconnectFromDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        dialogReg.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    controller.disconnectFromDatabase();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void actionPerformed(ActionEvent e) {

          switch (e.getActionCommand()) {

              case "Login" : {
                  if (controller.verifyStringLength(userNameText.getText()) && controller.verifyStringLength(passwordText.getText()) == true) {
                      try {
                          controller.connectToDatabase();
                          System.out.println("Loging...");
                          if (controller.tryToLogin(userNameText.getText(), passwordText.getText()) == true) {
                              dialogLog.setVisible(true);
                              dialogLabelLog.setText("Hello ");
                          } else {
                              setTitle("Incorrect name/pwd");
                          }
                      } catch (SQLException e1) {
                          e1.printStackTrace();
                      } catch (ClassNotFoundException e1) {
                          e1.printStackTrace();
                      }
                  } else {
                      setTitle("Enought characters");
                  }
                  break;
              }

              case "Register" : {
                  if (controller.verifyStringLength((userNameText.getText())) == true && controller.verifyStringLength(passwordText.getText()) == true) {
                      try {
                          controller.connectToDatabase();
                          System.out.println("Registering...");
                          if (controller.tryToRegister(userNameText.getText(), passwordText.getText())) {
                              dialogReg.setVisible(true);
                              dialogLabelReg.setText("Hhgdhg");
                          }
                      } catch (SQLException e1) {
                          e1.printStackTrace();
                      } catch (ClassNotFoundException e1) {
                          e1.printStackTrace();
                      }
                  } else {
                      setTitle("Enought characters");
                  }
                  break;
              }

              case "OK" : {
                  try {
                      System.out.println("OK");
                      controller.disconnectFromDatabase();
                      dialogLog.setVisible(false);
                      dialogReg.setVisible(false);
                  } catch (SQLException e1) {
                      e1.printStackTrace();
                  }
                  break;
              }
          }
    }
}
