import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import static java.awt.Color.black;
import static java.awt.Color.green;

public class Hibernate {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        Vector<Account>teacherAccounts=new Vector<>();
        Vector<HocSinh>stdList=new Vector<>();
        Vector teacherList=new Vector();
        if(FileSize("DuLieu.txt")!=0)
        {
            teacherList=DocFile(teacherList);
        }
        if(FileSize("DuLieuAccount.txt")!=0)
        {
            teacherAccounts=DocFile2(teacherAccounts);
        }
        if(teacherAccounts.size()==0)
        {
            Account admin=new Account("admin","admin");
            teacherAccounts.add(admin);
        }
        if(teacherList.size()==0)
        {
            Teacher admin=new Teacher();
            admin.setUsername("admin");
            admin.setPassword("admin");
            teacherList.add(admin);
        }
        JFrame jFrame=new MyFrame();
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
        JPasswordField password=new HintPasswordField("Password");
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
        Vector<Teacher> finalTeacherList = teacherList;
        Vector<Teacher> finalTeacherList1 = teacherList;
        Vector<Teacher> finalTeacherList2 = teacherList;
        Vector<Account> finalTeacherAccounts = teacherAccounts;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button)
                {
                    boolean check=false;
                    String user=username.getText().toString();
                    String pass=String.valueOf(password.getPassword());
                    for(int i = 0; i< finalTeacherAccounts.size(); i++)
                    {
                        if(finalTeacherAccounts.get(i).getKey().equals(user)&& finalTeacherAccounts.get(i).getValue().equals(pass))
                        {
                            check =true;
                            JOptionPane.showMessageDialog(null,"Đăng nhập thành công!");
                            JFrame frame=new MyFrame();
                            jFrame.dispose();
                            Teacher temp=new Teacher();
                            for(int j = 0; j< finalTeacherList.size(); j++)
                            {
                                if(user.equals(finalTeacherList.get(j).getUsername())&&pass.equals(finalTeacherList.get(j).getPassword()))
                                {
                                    temp=finalTeacherList.get(j);
                                }
                            }
                            BorderLayout borderLayout=new BorderLayout();
                            frame.setLayout(borderLayout);
                            JButton logout=new JButton("Sign out");
                            JPanel northPan=new JPanel();
                            northPan.setLayout(new FlowLayout(FlowLayout.RIGHT));
                            northPan.add(logout);
                            logout.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(e.getSource()==logout)
                                    {
                                        JOptionPane.showMessageDialog(null,"Đăng xuất thành công!");
                                        System.exit(0);
                                    }
                                }
                            });
                            frame.add(northPan,BorderLayout.NORTH);
                            JPanel mainPanel=new JPanel();
                            mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                            JButton info=new JButton("User account");
                            mainPanel.add(info);
                            Teacher finalTemp = temp;
                            Teacher finalTemp1 = temp;
                            Teacher finalTemp2 = temp;
                            Teacher finalTemp3 = temp;
                            Teacher finalTemp4 = temp;
                            Teacher finalTemp5 = temp;
                            info.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(e.getSource()==info)
                                    {
                                        JFrame jFrame1=new JFrame();
                                        jFrame1.setTitle("User account");
                                        ImageIcon Icon=new ImageIcon("user.png");
                                        jFrame1.setIconImage(Icon.getImage());
                                        jFrame1.setSize(520,450);
                                        jFrame1.setResizable(false);
                                        jFrame1.setVisible(true);
                                        JPanel panel=new JPanel();
                                        JLabel label1=new JLabel();
                                        HocSinh hs=new HocSinh();
                                        label1.setText("Mã giáo viên:");
                                        JTextField textField=new JTextField();
                                        textField.setText(String.valueOf(finalTemp.getMGV()));
                                        if(textField.getText().equals("0"))
                                        {
                                            textField.setText("");
                                        }
                                        textField.setPreferredSize(new Dimension(500,30));
                                        panel.add(label1);
                                        panel.add(textField);
                                        JPanel panel1=new JPanel();
                                        JLabel label2=new JLabel();
                                        label2.setText("Tên giáo viên:");
                                        JTextField textField1=new JTextField();
                                        textField1.setText(finalTemp1.getTenGV());
                                        textField1.setPreferredSize(new Dimension(500,30));
                                        panel1.add(label2);
                                        panel1.add(textField1);
                                        JPanel panel2=new JPanel();
                                        JLabel label3=new JLabel();
                                        label3.setText("Tuổi:");
                                        JTextField textField2=new JTextField();
                                        textField2.setText(String.valueOf(finalTemp2.getTuoi()));
                                        if(textField2.getText().equals("0"))
                                        {
                                            textField2.setText("");
                                        }
                                        textField2.setPreferredSize(new Dimension(500,30));
                                        panel2.add(label3);
                                        panel2.add(textField2);
                                        JPanel panel3=new JPanel();
                                        JLabel label4=new JLabel();
                                        label4.setText("Địa chỉ:");
                                        JTextField textField3=new JTextField();
                                        textField3.setText(finalTemp3.getDiaChi());
                                        textField3.setPreferredSize(new Dimension(500,30));
                                        panel3.add(label4);
                                        panel3.add(textField3);
                                        JPanel panel4=new JPanel();
                                        JLabel label5=new JLabel();
                                        label5.setText("Ghi chú:");
                                        JTextField textField4=new JTextField();
                                        textField4.setText(finalTemp4.getGhiChu());
                                        textField4.setPreferredSize(new Dimension(500,30));
                                        panel4.add(label5);
                                        panel4.add(textField4);
                                        JPanel panel5=new JPanel();
                                        JLabel label6=new JLabel();
                                        label6.setText("Hình ảnh:");
                                        panel5.add(label6);
                                        JButton fileBut=new JButton("Chọn ảnh");
                                        panel5.add(fileBut);
                                        JTextField textField5=new JTextField();
                                        textField5.setText(finalTemp5.getHinhanh());
                                        textField5.setPreferredSize(new Dimension(500,30));
                                        panel5.add(textField5);
                                        fileBut.setVisible(false);
                                        fileBut.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if(e.getSource()==fileBut)
                                                {
                                                    JFileChooser fileChooser=new JFileChooser();
                                                    int response=fileChooser.showOpenDialog(null);
                                                    if(response==JFileChooser.APPROVE_OPTION)
                                                    {
                                                        File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                                                        String path=String.valueOf(file);
                                                        textField5.setText(path);
                                                    }
                                                }
                                            }
                                        });
                                        textField.setFocusable(false);
                                        textField1.setFocusable(false);
                                        textField2.setFocusable(false);
                                        textField3.setFocusable(false);
                                        textField4.setFocusable(false);
                                        textField5.setFocusable(false);
                                        JPanel mainPanel=new JPanel();
                                        BoxLayout boxLayout=new BoxLayout(mainPanel,BoxLayout.Y_AXIS);
                                        mainPanel.setLayout(boxLayout);
                                        mainPanel.add(panel);
                                        mainPanel.add(panel1);
                                        mainPanel.add(panel2);
                                        mainPanel.add(panel3);
                                        mainPanel.add(panel4);
                                        mainPanel.add(panel5);
                                        jFrame1.add(mainPanel);
                                        JPanel butPanel=new JPanel();
                                        JButton but1=new JButton("Sửa thông tin");
                                        but1.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if(e.getSource()==but1)
                                                {
                                                    textField.setFocusable(true);
                                                    textField1.setFocusable(true);
                                                    textField2.setFocusable(true);
                                                    textField3.setFocusable(true);
                                                    textField4.setFocusable(true);
                                                    textField5.setFocusable(true);
                                                    fileBut.setVisible(true);
                                                }
                                            }
                                        });
                                        JButton saveBut=new JButton("Lưu");
                                        saveBut.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if(e.getSource()==saveBut)
                                                {
                                                    if(textField.getText().equals("")||textField1.getText().equals("")||textField2.getText().equals("")
                                                            ||textField3.getText().equals("")||textField4.getText().equals("")||textField5.getText().equals("")) {
                                                        JOptionPane.showMessageDialog(null,"Hãy nhập đầy đủ thông tin!");
                                                    }
                                                    else{
                                                        JOptionPane.showMessageDialog(null,"Đã lưu lại thông tin!");
                                                        textField.setFocusable(false);
                                                        textField1.setFocusable(false);
                                                        textField2.setFocusable(false);
                                                        textField3.setFocusable(false);
                                                        textField4.setFocusable(false);
                                                        textField5.setFocusable(false);
                                                        fileBut.setVisible(false);
                                                        finalTemp.setMGV(Integer.parseInt(textField.getText()));
                                                        finalTemp.setTenGV(textField1.getText());
                                                        finalTemp.setTuoi(Integer.parseInt(textField2.getText()));
                                                        finalTemp.setHinhanh(textField3.getText());
                                                        finalTemp.setDiaChi(textField4.getText());
                                                        finalTemp.setGhiChu(textField5.getText());
                                                        finalTeacherList.add(finalTemp);
                                                        try {
                                                            LamTrangFile();
                                                            GhiFile(finalTeacherList, finalTeacherList1.size());
                                                        } catch (IOException ioException) {
                                                            ioException.printStackTrace();
                                                        }
                                                    }
                                                }
                                            }
                                        });
                                        FlowLayout flowLayout=new FlowLayout();
                                        butPanel.setLayout(flowLayout);
                                        butPanel.add(but1);
                                        butPanel.add(saveBut);
                                        mainPanel.add(butPanel);
                                    }
                                }
                            });
                            JButton changePass=new JButton("Đổi mật khẩu");
                            mainPanel.add(changePass);
                            ImageIcon useraccount=new ImageIcon("user.png");
                            info.setIcon(useraccount);
                            ImageIcon changepass=new ImageIcon("key.png");
                            changePass.setIcon(changepass);
                            changePass.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(e.getSource()==changePass)
                                    {
                                        JFrame passframe=new JFrame();
                                        passframe.setTitle("Đổi mật khẩu");
                                        ImageIcon icon1=new ImageIcon("key.png");
                                        passframe.setIconImage(icon1.getImage());
                                        passframe.setSize(520,450);
                                        passframe.setResizable(false);
                                        passframe.setVisible(true);
                                        JPanel passPanel=new JPanel();
                                        JPanel jPanel=new JPanel();
                                        BoxLayout boxLayout=new BoxLayout(passPanel,BoxLayout.Y_AXIS);
                                        passPanel.setLayout(boxLayout);
                                        JLabel jlabel=new JLabel("Mật khẩu cũ:");
                                        jPanel.add(jlabel);
                                        JTextField jtextField=new JTextField();
                                        jtextField.setPreferredSize(new Dimension(300,30));
                                        jPanel.add(jtextField);
                                        passPanel.add(jPanel);
                                        JPanel jPanel1=new JPanel();
                                        JLabel jlabel1=new JLabel("Mật khẩu mới:");
                                        jPanel1.add(jlabel1);
                                        JTextField jtextField1=new JTextField();
                                        jtextField1.setPreferredSize(new Dimension(300,30));
                                        jPanel1.add(jtextField1);
                                        passPanel.add(jPanel1);
                                        JPanel jPanel2=new JPanel();
                                        JLabel jlabel2=new JLabel("Nhập lại mật khẩu mới:");
                                        jPanel2.add(jlabel2);
                                        JTextField jtextField2=new JTextField();
                                        jtextField2.setPreferredSize(new Dimension(300,30));
                                        jPanel2.add(jtextField2);
                                        passPanel.add(jPanel2);
                                        JPanel butPan=new JPanel();
                                        JButton change=new JButton("Đổi mật khẩu");
                                        butPan.add(change);
                                        change.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if(e.getSource()==change)
                                                {
                                                    if(jtextField.getText().equals("")||jtextField1.getText().equals("")||jtextField2.getText().equals(""))
                                                    {
                                                        JOptionPane.showMessageDialog(null,"Hãy nhập đầy đủ thông tin");
                                                    }
                                                    else
                                                    {
                                                        if(!jtextField.getText().equals(pass))
                                                        {
                                                            JOptionPane.showMessageDialog(null,"Sai mật khẩu cũ!");
                                                        }
                                                        else
                                                        {
                                                            if(!jtextField1.getText().equals(jtextField2.getText()))
                                                            {
                                                                JOptionPane.showMessageDialog(null,"Mật khẩu mới phải trùng nhau");
                                                            }
                                                            else
                                                            {
                                                                JOptionPane.showMessageDialog(null,"Đổi mật khẩu thành công!");
                                                                Account temp=new Account(user,pass);
                                                                finalTeacherAccounts.remove(temp);
                                                                temp.setValue(jtextField1.getText());
                                                                finalTeacherAccounts.add(temp);
                                                                for(int k = 0; k< finalTeacherList2.size(); k++)
                                                                {
                                                                    if(user.equals(finalTeacherList2.get(k).getUsername()))
                                                                    {
                                                                        finalTeacherList2.get(k).setPassword(jtextField1.getText());
                                                                        for(int l = 0; l< finalTeacherAccounts.size(); l++)
                                                                        {
                                                                            if(user.equals(finalTeacherAccounts.get(l).getKey()))
                                                                            {
                                                                                finalTeacherAccounts.get(l).setValue(jtextField1.getText());
                                                                            }
                                                                        }
                                                                        try {
                                                                            LamTrangFile();
                                                                            GhiFile(finalTeacherList2,finalTeacherList2.size());
                                                                            GhiFile2(finalTeacherAccounts);
                                                                        } catch (IOException ioException) {
                                                                            ioException.printStackTrace();
                                                                        }

                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        });
                                        passPanel.add(butPan);
                                        passframe.add(passPanel);
                                        passframe.pack();
                                    }
                                }
                            });
                            frame.add(mainPanel,BorderLayout.CENTER);
                            break;
                        }
                    }
                    if(check==false)
                    {
                        JOptionPane.showMessageDialog(null,"Tên đăng nhập hoặc mật khẩu bị sai! Xin thử lại!");
                    }
                }
            }
        });
        jFrame.pack();
    }
    static class MyFrame extends JFrame
    {
        public MyFrame(){
            ImageIcon imageIcon=new ImageIcon("student.png");
            setSize(700,700);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setTitle("Course Registration System");
            setIconImage(imageIcon.getImage());
            setVisible(true);
            setResizable(false);
        }
    }
    static void GhiFile(Vector a,int count) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieu.txt"));
        out.writeInt(count);
        for(int i=0;i<a.size();i++)
        {
            out.writeObject(a.get(i));
        }
        out.flush();
        out.close();
    }
    static void GhiFile2(Vector a) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieuAccount.txt"));
        out.writeInt(a.size());
        for(int i=0;i<a.size();i++)
        {
            out.writeObject(a.get(i));
        }
        out.flush();
        out.close();
    }
    static long FileSize(String filename)
    {
        File file=new File(filename);
        if(!file.exists()||!file.isFile()) return 0;
        else
        {
            return file.length();
        }
    }
    static Vector DocFile(Vector a) throws IOException, ClassNotFoundException {

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("DuLieu.txt"));
        int count=in.readInt();
        for(int i=0;i<count;i++)
        {
            Teacher hs= (Teacher) in.readObject();
            a.add(hs);
        }
        in.close();
        return a;
    }
    static Vector DocFile2(Vector a) throws IOException, ClassNotFoundException {

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("DuLieuAccount.txt"));
        int count=in.readInt();
        for(int i=0;i<count;i++)
        {
            Account acc= (Account) in.readObject();
            a.add(acc);
        }
        in.close();
        return a;
    }
    static void LamTrangFile() throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieu.txt"));
        out.writeBytes("");
        out.flush();
        out.close();
    }
}
