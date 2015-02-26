package autoGui;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

public class TabbedPane extends JFrame {
    
    public TabbedPane() {
        
         //This will create the title you see in the upper left of the window    
        setTitle("Allen Eaton Auto Rentals");  
        setSize(500,400); //set size so the user can "see" it
        //Here we are creating the object
        JTabbedPane jtp = new JTabbedPane();

        //This creates the template on the windowed application that we will be using
       getContentPane().add(jtp);

       JPanel jp1 = new JPanel();//This will create the first tab

       JPanel jp2 = new JPanel();//This will create the second tab
        
         //This creates a non-editable label, sets what the label will read
        //and adds the label to the first tab
       JLabel label1 = new JLabel();
       label1.setText("Login");
       //jp1.add(label1);

       //This adds the first and second tab to our tabbed pane object and names it
       jtp.addTab("Login", jp1);
       jtp.addTab("Register", jp2);

        //This creates a new button called "Press" and adds it to the second tab
       //jp2.setLayout(new BoxLayout(jp2, BoxLayout.Y_AXIS));
       
       JLabel username = new JLabel();
       username.setBounds(33, 8, 60, 16);
       username.setText("Email:      ");
       JLabel password = new JLabel();
       password.setBounds(241, 8, 60, 16);
       password.setText("Password:");
    
       JTextField un = new JTextField(12);
       un.setBounds(98, 5, 138, 22);
       JTextField pw = new JTextField(12);
       pw.setBounds(306, 5, 138, 22);
       JButton test = new JButton("\nSubmit");
       test.addMouseListener(new MouseAdapter() {
       	@Override
       	public void mouseClicked(MouseEvent arg0) {
       		setVisible(false);
       		Customer home = new Customer();
       		home.setVisible(true);       	}
       });
       test.setBounds(202, 32, 99, 25);
       jp1.setLayout(null);
       jp1.add(username);
       jp1.add(un);
       jp1.add(password);
       jp1.add(pw);
       jp1.add(test);
       
       
       JLabel usernameR = new JLabel();
       usernameR.setText("Email:      ");
       JLabel passwordR = new JLabel();
       passwordR.setText("Password:");
       JLabel confirm = new JLabel();
       confirm.setText("Confirm Password:");
       JTextField unr = new JTextField(12);
       JTextField pwr = new JTextField(12);
       JTextField conf = new JTextField(12);
       jp2.add(usernameR);
       jp2.add(unr);
       jp2.add(passwordR);
       jp2.add(pwr);
       jp2.add(confirm);
       jp2.add(conf);
       
       /*JLabel lab = new JLabel();
       lab.setPreferredSize(new Dimension(200, 30));
       jTabbedPane1.setTabComponentAt(0, lab);  // tab index, jLabel
      */
       
       
       //jp2.setSize(300,400);

        //This is an Action Listener which reacts to clicking on 
        //the test button called "Press"
        ButtonHandler phandler = new ButtonHandler();
        test.addActionListener(phandler);
        setVisible(true); //otherwise you won't "see" it 
    }
    
    //This is the internal class that defines what the above Action Listener
    //will do when the test button is pressed.
    class ButtonHandler implements ActionListener{
           public void actionPerformed(ActionEvent e){
                   //JOptionPane.showMessageDialog(null, "I've been pressed", "What happened?", JOptionPane. INFORMATION_MESSAGE);
           }
    }

    //example usage
     public static void main (String []args){
        TabbedPane tab = new TabbedPane();
    }
}
