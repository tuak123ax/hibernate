import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class Hibernate {
    public static void main(String[]args)
    {
       JFrame jFrame=MyFrame();
       JLabel jLabel=new JLabel("LOGIN FORM");
       JPanel northPanel=new JPanel();
       northPanel.add(jLabel);
       jFrame.add(northPanel,BorderLayout.NORTH);
       JPanel centerPanel=new JPanel();
       JPanel panel1=new JPanel();
       JTextField username=new HintTextField("Username");
       username.setPreferredSize(new Dimension(300,30));
       BoxLayout boxLayout=new BoxLayout(centerPanel,BoxLayout.Y_AXIS);
       centerPanel.setLayout(boxLayout);
       centerPanel.setBorder(new EmptyBorder(new Insets(30, 50, 50, 50)));
       ImageIcon icon=new ImageIcon("rsz_username.png");
       JLabel jLabel1=new JLabel(icon);
       panel1.add(jLabel1);
       panel1.add(username);
       JPanel panel2=new JPanel();
       JTextField password=new HintTextField("Password");
       password.setPreferredSize(new Dimension(300,30));
       ImageIcon iconpass=new ImageIcon("rsz_password.png");
       JLabel jLabel2=new JLabel(iconpass);
       panel2.add(jLabel2);
       panel2.add(password);
       centerPanel.add(panel1);
       centerPanel.add(panel2);
       JButton button=new JButton("LOGIN");
       JPanel butPanel=new JPanel();
       butPanel.add(button);
       centerPanel.add(butPanel);
       jFrame.add(centerPanel,BorderLayout.CENTER);
       jFrame.pack();
    }
    static JFrame MyFrame()
    {
        ImageIcon imageIcon=new ImageIcon("student.png");
        JFrame jFrame=new JFrame();
        jFrame.setSize(700,700);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setTitle("Course Registration System");
        jFrame.setIconImage(imageIcon.getImage());
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        return jFrame;
    }
    static void background(JFrame jFrame)
    {
        java.awt.GridBagConstraints gridBagConstraints;

        JPanel jPanel1 = new javax.swing.JPanel();
        JLabel jLabel1 = new javax.swing.JLabel();

        jFrame.getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 515, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 495, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jFrame.getContentPane().add(jPanel1, gridBagConstraints);

        jLabel1.setIcon(new ImageIcon("rsz_background.jpg"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jFrame.getContentPane().add(jLabel1, gridBagConstraints);
    }
}
