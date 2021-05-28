import javax.crypto.SealedObject;
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
        Vector<Subject>sbjList=new Vector<>();
        Vector teacherList=new Vector();
        if(FileSize("DuLieu.txt")!=0)
        {
            teacherList=DocFile(teacherList);
        }
        if(FileSize("DuLieuAccount.txt")!=0)
        {
            teacherAccounts=DocFile2(teacherAccounts);
        }
        if(FileSize("DuLieuMonHoc.txt")!=0)
        {
            sbjList=DocFileMonHoc(sbjList);
        }
        if(teacherAccounts.size()==0)
        {
            Account admin=new Account("admin","admin");
            teacherAccounts.add(admin);
            Account minh=new Account("minh","minh");
            teacherAccounts.add(minh);
            Account tu=new Account("tu","tu");
            teacherAccounts.add(tu);
        }
        if(teacherList.size()==0)
        {
            Teacher admin=new Teacher();
            admin.setUsername("admin");
            admin.setPassword("admin");
            teacherList.add(admin);
            Teacher minh=new Teacher();
            minh.setUsername("minh");
            minh.setPassword("minh");
            teacherList.add(minh);
            Teacher tu=new Teacher();
            tu.setUsername("tu");
            tu.setPassword("tu");
            teacherList.add(tu);
        }
        if(sbjList.size()==0)
        {
            Subject math=new Subject();
            math.setMaMH("M001");
            math.setTenMH("Toán tổ hợp");
            math.setTinChi(4);
            Subject Physics=new Subject();
            Physics.setMaMH("PHY001");
            Physics.setTenMH("Lý 2");
            Physics.setTinChi(3);
            Subject programming=new Subject();
            programming.setMaMH("PR001");
            programming.setTenMH("Kỹ thuật lập trình");
            programming.setTinChi(4);
            sbjList.add(math);
            sbjList.add(Physics);
            sbjList.add(programming);
        }
        System.out.println(teacherAccounts.size());
        System.out.println(teacherList.size());
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
        Vector<Account> finalTeacherAccounts1 = teacherAccounts;
        Vector<Account> finalTeacherAccounts2 = teacherAccounts;
        Vector<Account> finalTeacherAccounts3 = teacherAccounts;
        Vector finalTeacherList3 = teacherList;
        Vector finalTeacherList4 = teacherList;
        Vector finalTeacherList5 = teacherList;
        Vector finalTeacherList6 = teacherList;
        Vector finalTeacherList7 = teacherList;
        Vector<Account> finalTeacherAccounts4 = teacherAccounts;
        Vector<Account> finalTeacherAccounts5 = teacherAccounts;
        Vector finalTeacherList8 = teacherList;
        Vector<Account> finalTeacherAccounts6 = teacherAccounts;
        Vector<Subject> finalSbjList = sbjList;
        Vector<Subject> finalSbjList1 = sbjList;
        Vector<Subject> finalSbjList2 = sbjList;
        Vector<Subject> finalSbjList3 = sbjList;
        Vector<Subject> finalSbjList4 = sbjList;
        Vector<Subject> finalSbjList5 = sbjList;
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
                            int pos=0;
                            for(int j = 0; j< finalTeacherList.size(); j++)
                            {
                                if(user.equals(finalTeacherList.get(j).getUsername())&&pass.equals(finalTeacherList.get(j).getPassword()))
                                {
                                    pos=j;
                                    temp=finalTeacherList.get(j);
                                }
                            }
                            BorderLayout borderLayout=new BorderLayout();
                            frame.setLayout(borderLayout);
                            JButton logout=new JButton("Sign out");
                            JPanel northPan=new JPanel();
                            JLabel hello=new JLabel("Hello, "+user);
                            northPan.setLayout(new FlowLayout(FlowLayout.RIGHT));
                            northPan.add(hello);
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
                            int finalPos = pos;
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
                                                        finalTeacherList.remove(finalPos);
                                                        finalTemp.setMGV(Integer.parseInt(textField.getText()));
                                                        finalTemp.setTenGV(textField1.getText());
                                                        finalTemp.setTuoi(Integer.parseInt(textField2.getText()));
                                                        finalTemp.setHinhanh(textField3.getText());
                                                        finalTemp.setDiaChi(textField4.getText());
                                                        finalTemp.setGhiChu(textField5.getText());
                                                        finalTeacherList.add(finalPos,finalTemp);
                                                        try {
                                                            LamTrangFile("DuLieu.txt");
                                                            GhiFile(finalTeacherList, finalTeacherList.size());
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
                            JButton DanhSach=new JButton("Danh sách account GV");
                            mainPanel.add(DanhSach);
                            DanhSach.setIcon(new ImageIcon("list.png"));
                            DanhSach.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JFrame listFrame=new JFrame();
                                    listFrame.setTitle("Danh sách account GV");
                                    listFrame.setIconImage(new ImageIcon("list.png").getImage());
                                    listFrame.setSize(700,700);
                                    listFrame.setVisible(true);
                                    JButton Search=new JButton("Search");
                                    Search.setIcon(new ImageIcon("lupe.png"));
                                    BorderLayout borderLayout1=new BorderLayout();
                                    listFrame.setLayout(borderLayout1);
                                    JPanel centerPanel=new JPanel();
                                    JLabel listlabel=new JLabel("List Accounts");
                                    listlabel.setHorizontalAlignment(0);
                                    Vector Header=new Vector();
                                    Header.add("Username");
                                    Header.add("Password");
                                    Vector data=new Vector();
                                    for(int ii = 0; ii< finalTeacherAccounts1.size(); ii++)
                                    {
                                        Vector temp=new Vector();
                                        temp.add(finalTeacherAccounts1.get(ii).getKey());
                                        temp.add(finalTeacherAccounts1.get(ii).getValue());
                                        data.add(temp);
                                    }
                                    JTable table=new JTable(data,Header);
                                    JScrollPane sp=new JScrollPane(table);
                                    Search.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if(e.getSource()==Search)
                                            {
                                                JFrame temp=new JFrame();
                                                temp.setSize(300,300);
                                                temp.setVisible(true);
                                                temp.setIconImage(new ImageIcon("lupe.png").getImage());
                                                temp.setTitle("Search");
                                                JPanel panel=new JPanel();
                                                JLabel label=new JLabel("Username:");
                                                panel.setLayout(new FlowLayout());
                                                JTextField jTextField=new JTextField();
                                                jTextField.setPreferredSize(new Dimension(300,30));
                                                panel.add(label);
                                                panel.add(jTextField);
                                                JPanel bigPanel=new JPanel();
                                                bigPanel.add(panel);
                                                JButton but=new JButton("Search");
                                                but.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        if(e.getSource()==but)
                                                        {
                                                            String name=jTextField.getText();
                                                            boolean check=false;
                                                            for(int i = 0; i< finalTeacherAccounts2.size(); i++)
                                                            {
                                                                if(name.equals(finalTeacherAccounts2.get(i).getKey()))
                                                                {
                                                                    check=true;
                                                                    Vector DuLieu=new Vector();
                                                                    Vector acc=new Vector();
                                                                    acc.add(finalTeacherAccounts2.get(i).getKey());
                                                                    acc.add(finalTeacherAccounts2.get(i).getValue());
                                                                    DuLieu.add(acc);
                                                                    table.setModel(new DefaultTableModel(DuLieu,Header));
                                                                    break;
                                                                }
                                                            }
                                                            if(check==false)
                                                            {
                                                                JOptionPane.showMessageDialog(null,"Không tìm thấy account!");
                                                            }
                                                        }
                                                    }
                                                });
                                                bigPanel.add(but);
                                                temp.add(bigPanel);
                                                temp.pack();
                                            }
                                        }
                                    });
                                    JButton add=new JButton("Add");
                                    add.setIcon(new ImageIcon("rsz_add.png"));
                                    add.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if(e.getSource()==add)
                                            {
                                                JFrame Temp=new JFrame();
                                                Temp.setSize(300,300);
                                                Temp.setVisible(true);
                                                Temp.setIconImage(new ImageIcon("rsz_add.png").getImage());
                                                Temp.setTitle("Add");
                                                JPanel Jpanel=new JPanel();
                                                JLabel Jlabel=new JLabel("Username:");
                                                Jpanel.setLayout(new FlowLayout());
                                                JTextField JtextField=new JTextField();
                                                JtextField.setPreferredSize(new Dimension(300,30));
                                                Jpanel.add(Jlabel);
                                                Jpanel.add(JtextField);
                                                JPanel Jpanel1=new JPanel();
                                                JLabel Jlabel1=new JLabel("Password:");
                                                Jpanel1.setLayout(new FlowLayout());
                                                JTextField JtextField1=new JTextField();
                                                JtextField1.setPreferredSize(new Dimension(300,30));
                                                Jpanel1.add(Jlabel1);
                                                Jpanel1.add(JtextField1);
                                                JPanel BPanel=new JPanel();
                                                BoxLayout boxLayout1=new BoxLayout(BPanel,BoxLayout.Y_AXIS);
                                                BPanel.setLayout(boxLayout1);
                                                BPanel.add(Jpanel);
                                                BPanel.add(Jpanel1);
                                                JButton Them=new JButton("Thêm");
                                                Them.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        if(e.getSource()==Them)
                                                        {
                                                            if(JtextField.getText().equals("")||JtextField1.getText().equals(""))
                                                            {
                                                                JOptionPane.showMessageDialog(null,"Hãy nhập đầy đủ thông tin");
                                                            }
                                                            else
                                                            {
                                                                String newUser=JtextField.getText();
                                                                String newPass=JtextField1.getText();
                                                                boolean kt=false;
                                                                for(int jj=0;jj<finalTeacherAccounts3.size();jj++)
                                                                {
                                                                    if(newUser.equals(finalTeacherAccounts3.get(jj).getKey()))
                                                                    {
                                                                        kt=true;
                                                                        JOptionPane.showMessageDialog(null,"Tài khoản đã tồn tại");
                                                                    }
                                                                }
                                                                if(kt==false) {
                                                                    Account newAcc = new Account(newUser, newPass);
                                                                    finalTeacherAccounts3.add(newAcc);
                                                                    Teacher newTeacher = new Teacher();
                                                                    newTeacher.setUsername(newUser);
                                                                    newTeacher.setPassword(newPass);
                                                                    finalTeacherList3.add(newTeacher);
                                                                    try {
                                                                        LamTrangFile("DuLieu.txt");
                                                                        LamTrangFile("DuLieuAccount.txt");
                                                                        GhiFile(finalTeacherList3, finalTeacherList3.size());
                                                                        GhiFile2(finalTeacherAccounts3);
                                                                        Vector dulieu = new Vector();
                                                                        for (int iii = 0; iii < finalTeacherAccounts3.size(); iii++) {
                                                                            Vector phu = new Vector();
                                                                            phu.add(finalTeacherAccounts3.get(iii).getKey());
                                                                            phu.add(finalTeacherAccounts3.get(iii).getValue());
                                                                            dulieu.add(phu);
                                                                        }
                                                                        table.setModel(new DefaultTableModel(dulieu, Header));
                                                                    } catch (IOException ioException) {
                                                                        ioException.printStackTrace();
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                });
                                                BPanel.add(Them);
                                                Temp.setResizable(false);
                                                Temp.add(BPanel);
                                                Temp.pack();
                                            }
                                        }
                                    });
                                    JButton Update=new JButton("Update");
                                    Update.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if(e.getSource()==Update)
                                            {
                                                if(table.getSelectedRow()<0)
                                                {
                                                    JOptionPane.showMessageDialog(null,"Hãy chọn đối tượng cần update!");
                                                }
                                                else
                                                {
                                                    Teacher temp= (Teacher) finalTeacherList6.get(table.getSelectedRow());
                                                    JFrame jFrame1=new JFrame();
                                                    jFrame1.setTitle("Update");
                                                    ImageIcon Icon=new ImageIcon("update.png");
                                                    jFrame1.setIconImage(Icon.getImage());
                                                    jFrame1.setSize(520,450);
                                                    jFrame1.setResizable(false);
                                                    jFrame1.setVisible(true);
                                                    JPanel panel=new JPanel();
                                                    JLabel label1=new JLabel();
                                                    HocSinh hs=new HocSinh();
                                                    label1.setText("Mã giáo viên:");
                                                    JTextField textField=new JTextField();
                                                    textField.setText(String.valueOf(temp.getMGV()));
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
                                                    textField1.setText(temp.getTenGV());
                                                    textField1.setPreferredSize(new Dimension(500,30));
                                                    panel1.add(label2);
                                                    panel1.add(textField1);
                                                    JPanel panel2=new JPanel();
                                                    JLabel label3=new JLabel();
                                                    label3.setText("Tuổi:");
                                                    JTextField textField2=new JTextField();
                                                    textField2.setText(String.valueOf(temp.getTuoi()));
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
                                                    textField3.setText(temp.getDiaChi());
                                                    textField3.setPreferredSize(new Dimension(500,30));
                                                    panel3.add(label4);
                                                    panel3.add(textField3);
                                                    JPanel panel4=new JPanel();
                                                    JLabel label5=new JLabel();
                                                    label5.setText("Ghi chú:");
                                                    JTextField textField4=new JTextField();
                                                    textField4.setText(temp.getGhiChu());
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
                                                    textField5.setText(temp.getHinhanh());
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
                                                                    int pos=table.getSelectedRow();
                                                                    finalTeacherList.remove(pos);
                                                                    temp.setMGV(Integer.parseInt(textField.getText()));
                                                                    temp.setTenGV(textField1.getText());
                                                                    temp.setTuoi(Integer.parseInt(textField2.getText()));
                                                                    temp.setHinhanh(textField3.getText());
                                                                    temp.setDiaChi(textField4.getText());
                                                                    temp.setGhiChu(textField5.getText());
                                                                    finalTeacherList.add(pos,temp);
                                                                    try {
                                                                        LamTrangFile("DuLieu.txt");
                                                                        GhiFile(finalTeacherList, finalTeacherList.size());
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
                                        }
                                    });
                                    Update.setIcon(new ImageIcon("update.png"));
                                    JButton reset=new JButton("Reset");
                                    reset.setIcon(new ImageIcon("reset.png"));
                                    reset.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if(e.getSource()==reset)
                                            {
                                                if(table.getSelectedRow()<0)
                                                {
                                                    JOptionPane.showMessageDialog(null,"Hãy chọn đối tượng cần reset password");
                                                }
                                                else
                                                {
                                                    JOptionPane.showMessageDialog(null,"Reset password thành công!");
                                                    JOptionPane.showMessageDialog(null,"Đã reset password giống với username!");
                                                    int vitri=table.getSelectedRow();
                                                    Teacher tempTeacher= (Teacher) finalTeacherList7.get(vitri);
                                                    finalTeacherList7.remove(vitri);
                                                    finalTeacherList7.add(vitri,tempTeacher);
                                                    finalTeacherAccounts4.get(vitri).setValue(finalTeacherAccounts4.get(vitri).getKey());
                                                    Vector list=new Vector();
                                                    for(int zz=0;zz<finalTeacherList7.size();zz++)
                                                    {
                                                        Vector tmp=new Vector();
                                                        tmp.add(finalTeacherAccounts4.get(zz).getKey());
                                                        tmp.add(finalTeacherAccounts4.get(zz).getValue());
                                                        list.add(tmp);
                                                    }
                                                    table.setModel(new DefaultTableModel(list,Header));
                                                    try {
                                                        LamTrangFile("DuLieu.txt");
                                                        LamTrangFile("DuLieuAccount.txt");
                                                        GhiFile(finalTeacherList7,finalTeacherList7.size());
                                                        GhiFile2(finalTeacherAccounts4);
                                                    } catch (IOException ioException) {
                                                        ioException.printStackTrace();
                                                    }

                                                }
                                            }
                                        }
                                    });
                                    JButton delete=new JButton("Delete");
                                    delete.setIcon(new ImageIcon("delete.png"));
                                    delete.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            if(e.getSource()==delete)
                                            {
                                                if(table.getSelectedRow()<0)
                                                {
                                                    JOptionPane.showMessageDialog(null,"Hãy chọn đối tượng cần xóa!");
                                                }
                                                else
                                                {
                                                    int position=table.getSelectedRow();
                                                    finalTeacherList8.remove(position);
                                                    finalTeacherAccounts6.remove(position);
                                                    Vector List=new Vector();
                                                    for(int zzz=0;zzz<finalTeacherList8.size();zzz++)
                                                    {
                                                        Vector tm=new Vector();
                                                        tm.add(finalTeacherAccounts6.get(zzz).getKey());
                                                        tm.add(finalTeacherAccounts6.get(zzz).getValue());
                                                        List.add(tm);
                                                    }
                                                    table.setModel(new DefaultTableModel(List,Header));
                                                    try {
                                                        LamTrangFile("DuLieu.txt");
                                                        LamTrangFile("DuLieuAccount.txt");
                                                        GhiFile(finalTeacherList8,finalTeacherList8.size());
                                                        GhiFile2(finalTeacherAccounts6);
                                                    } catch (IOException ioException) {
                                                        ioException.printStackTrace();
                                                    }
                                                }
                                            }
                                        }
                                    });
                                    JPanel southPanel=new JPanel();
                                    southPanel.add(Search);
                                    southPanel.add(add);
                                    southPanel.add(Update);
                                    southPanel.add(reset);
                                    southPanel.add(delete);
                                    centerPanel.add(sp);
                                    listFrame.add(listlabel,BorderLayout.NORTH);
                                    listFrame.add(centerPanel,BorderLayout.CENTER);
                                    listFrame.add(southPanel,BorderLayout.SOUTH);
                                    listFrame.pack();
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
                                                                for(int k = 0; k< finalTeacherList2.size(); k++)
                                                                {
                                                                    if(user.equals(finalTeacherList2.get(k).getUsername()))
                                                                    {
                                                                        finalTeacherList2.get(k).setPassword(jtextField1.getText());
                                                                    }
                                                                }
                                                                for(int l = 0; l< finalTeacherAccounts.size(); l++)
                                                                {
                                                                    if(user.equals(finalTeacherAccounts.get(l).getKey()))
                                                                    {
                                                                        finalTeacherAccounts.get(l).setValue(jtextField1.getText());
                                                                    }
                                                                }
                                                                try {
                                                                    LamTrangFile("DuLieu.txt");
                                                                    LamTrangFile("DuLieuAccount.txt");
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
                                        });
                                        passPanel.add(butPan);
                                        passframe.add(passPanel);
                                        passframe.pack();
                                    }
                                }
                            });
                            JButton Subject=new JButton("Subject");
                            Subject.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if(e.getSource()==Subject)
                                    {
                                        JFrame listFrame=new JFrame();
                                        listFrame.setTitle("Danh sách môn học");
                                        listFrame.setIconImage(new ImageIcon("subject.png").getImage());

                                        listFrame.setSize(700,700);
                                        listFrame.setVisible(true);
                                        JButton Search=new JButton("Search");
                                        Search.setIcon(new ImageIcon("lupe.png"));
                                        BorderLayout borderLayout1=new BorderLayout();
                                        listFrame.setLayout(borderLayout1);
                                        JPanel centerPanel=new JPanel();
                                        JLabel listlabel=new JLabel("List Subjects");
                                        listlabel.setHorizontalAlignment(0);
                                        Vector Header=new Vector();
                                        Header.add("Mã môn học");
                                        Header.add("Tên môn học");
                                        Header.add("Số tín chỉ");
                                        Vector data=new Vector();
                                        for(int ii = 0; ii< finalSbjList.size(); ii++)
                                        {
                                            Vector temp=new Vector();
                                            temp.add(finalSbjList.get(ii).getMaMH());
                                            temp.add(finalSbjList.get(ii).getTenMH());
                                            temp.add(finalSbjList.get(ii).getTinChi());
                                            data.add(temp);
                                        }
                                        JTable table=new JTable(data,Header);
                                        JScrollPane sp=new JScrollPane(table);
                                        Search.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if(e.getSource()==Search)
                                                {
                                                    JFrame temp=new JFrame();
                                                    temp.setSize(300,300);
                                                    temp.setVisible(true);
                                                    temp.setIconImage(new ImageIcon("lupe.png").getImage());
                                                    temp.setTitle("Search");
                                                    JPanel panel=new JPanel();
                                                    JLabel label=new JLabel("Tên môn học:");
                                                    panel.setLayout(new FlowLayout());
                                                    JTextField jTextField=new JTextField();
                                                    jTextField.setPreferredSize(new Dimension(300,30));
                                                    panel.add(label);
                                                    panel.add(jTextField);
                                                    JPanel bigPanel=new JPanel();
                                                    bigPanel.add(panel);
                                                    JButton but=new JButton("Search");
                                                    but.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if(e.getSource()==but)
                                                            {
                                                                String name=jTextField.getText();
                                                                boolean check=false;
                                                                Vector DuLieu=new Vector();
                                                                for(int i = 0; i< finalSbjList1.size(); i++)
                                                                {
                                                                    if(name.equals(finalSbjList1.get(i).getTenMH()))
                                                                    {
                                                                        check=true;
                                                                        Vector acc=new Vector();
                                                                        acc.add(finalSbjList1.get(i).getMaMH());
                                                                        acc.add(finalSbjList1.get(i).getTenMH());
                                                                        acc.add(finalSbjList1.get(i).getTinChi());
                                                                        DuLieu.add(acc);
                                                                    }
                                                                }
                                                                if(check==false)
                                                                {
                                                                    JOptionPane.showMessageDialog(null,"Không tìm thấy môn học!");
                                                                }
                                                                else
                                                                {
                                                                    table.setModel(new DefaultTableModel(DuLieu,Header));
                                                                }
                                                            }
                                                        }
                                                    });
                                                    bigPanel.add(but);
                                                    temp.add(bigPanel);
                                                    temp.pack();
                                                }
                                            }
                                        });
                                        JButton add=new JButton("Add");
                                        add.setIcon(new ImageIcon("rsz_add.png"));
                                        add.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if(e.getSource()==add)
                                                {
                                                    JFrame Temp=new JFrame();
                                                    Temp.setSize(300,300);
                                                    Temp.setVisible(true);
                                                    Temp.setIconImage(new ImageIcon("rsz_add.png").getImage());
                                                    Temp.setTitle("Add");
                                                    JPanel Jpanel=new JPanel();
                                                    JLabel Jlabel=new JLabel("Mã môn học:");
                                                    Jpanel.setLayout(new FlowLayout());
                                                    JTextField JtextField=new JTextField();
                                                    JtextField.setPreferredSize(new Dimension(300,30));
                                                    Jpanel.add(Jlabel);
                                                    Jpanel.add(JtextField);
                                                    JPanel Jpanel1=new JPanel();
                                                    JLabel Jlabel1=new JLabel("Tên môn học:");
                                                    Jpanel1.setLayout(new FlowLayout());
                                                    JTextField JtextField1=new JTextField();
                                                    JtextField1.setPreferredSize(new Dimension(300,30));
                                                    Jpanel1.add(Jlabel1);
                                                    Jpanel1.add(JtextField1);
                                                    JPanel Jpanel2=new JPanel();
                                                    JLabel Jlabel2=new JLabel("Số tín chỉ:");
                                                    Jpanel2.setLayout(new FlowLayout());
                                                    JTextField JtextField2=new JTextField();
                                                    JtextField2.setPreferredSize(new Dimension(300,30));
                                                    Jpanel2.add(Jlabel2);
                                                    Jpanel2.add(JtextField2);
                                                    JPanel BPanel=new JPanel();
                                                    BoxLayout boxLayout1=new BoxLayout(BPanel,BoxLayout.Y_AXIS);
                                                    BPanel.setLayout(boxLayout1);
                                                    BPanel.add(Jpanel);
                                                    BPanel.add(Jpanel1);
                                                    BPanel.add(Jpanel2);
                                                    JButton Them=new JButton("Thêm");
                                                    Them.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if(e.getSource()==Them)
                                                            {
                                                                if(JtextField.getText().equals("")||JtextField1.getText().equals(""))
                                                                {
                                                                    JOptionPane.showMessageDialog(null,"Hãy nhập đầy đủ thông tin");
                                                                }
                                                                else
                                                                {
                                                                    String ma=JtextField.getText();
                                                                    String ten=JtextField1.getText();
                                                                    int tinchi=Integer.parseInt(JtextField2.getText());
                                                                    boolean kt=false;
                                                                    for(int jj = 0; jj< finalSbjList2.size(); jj++)
                                                                    {
                                                                        if(ma.equals(finalSbjList2.get(jj).getMaMH()))
                                                                        {
                                                                            kt=true;
                                                                            JOptionPane.showMessageDialog(null,"Tài khoản đã tồn tại");
                                                                        }
                                                                    }
                                                                    if(kt==false) {
                                                                        Subject newSub = new Subject(ma,ten,tinchi);
                                                                        finalSbjList2.add(newSub);
                                                                        try {
                                                                            LamTrangFile("DuLieuMonHoc.txt");
                                                                            GhiFileMonHoc(finalSbjList2);
                                                                            Vector dulieu = new Vector();
                                                                            for (int iii = 0; iii < finalSbjList2.size(); iii++) {
                                                                                Vector phu = new Vector();
                                                                                phu.add(finalSbjList2.get(iii).getMaMH());
                                                                                phu.add(finalSbjList2.get(iii).getTenMH());
                                                                                phu.add(finalSbjList2.get(iii).getTinChi());
                                                                                dulieu.add(phu);
                                                                            }
                                                                            table.setModel(new DefaultTableModel(dulieu, Header));
                                                                        } catch (IOException ioException) {
                                                                            ioException.printStackTrace();
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    });
                                                    BPanel.add(Them);
                                                    Temp.setResizable(false);
                                                    Temp.add(BPanel);
                                                    Temp.pack();
                                                }
                                            }
                                        });
                                        JButton Update=new JButton("Update");
                                        Update.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if(e.getSource()==Update)
                                                {
                                                    if(table.getSelectedRow()<0)
                                                    {
                                                        JOptionPane.showMessageDialog(null,"Hãy chọn đối tượng cần update!");
                                                    }
                                                    else
                                                    {
                                                        Subject temp= (Subject) finalSbjList3.get(table.getSelectedRow());
                                                        JFrame jFrame1=new JFrame();
                                                        jFrame1.setTitle("Update");
                                                        ImageIcon Icon=new ImageIcon("update.png");
                                                        jFrame1.setIconImage(Icon.getImage());
                                                        jFrame1.setSize(520,450);
                                                        jFrame1.setResizable(false);
                                                        jFrame1.setVisible(true);
                                                        JPanel panel=new JPanel();
                                                        JLabel label1=new JLabel();
                                                        label1.setText("Mã môn học:");
                                                        JTextField textField=new JTextField();
                                                        textField.setText(String.valueOf(temp.getMaMH()));
                                                        if(textField.getText().equals("0"))
                                                        {
                                                            textField.setText("");
                                                        }
                                                        textField.setPreferredSize(new Dimension(500,30));
                                                        panel.add(label1);
                                                        panel.add(textField);
                                                        JPanel panel1=new JPanel();
                                                        JLabel label2=new JLabel();
                                                        label2.setText("Tên môn học:");
                                                        JTextField textField1=new JTextField();
                                                        textField1.setText(temp.getTenMH());
                                                        textField1.setPreferredSize(new Dimension(500,30));
                                                        panel1.add(label2);
                                                        panel1.add(textField1);
                                                        JPanel panel2=new JPanel();
                                                        JLabel label3=new JLabel();
                                                        label3.setText("Số tín chỉ:");
                                                        JTextField textField2=new JTextField();
                                                        textField2.setText(String.valueOf(temp.getTinChi()));
                                                        if(textField2.getText().equals("0"))
                                                        {
                                                            textField2.setText("");
                                                        }
                                                        textField2.setPreferredSize(new Dimension(500,30));
                                                        panel2.add(label3);
                                                        panel2.add(textField2);
                                                        textField.setFocusable(false);
                                                        textField1.setFocusable(false);
                                                        textField2.setFocusable(false);
                                                        JPanel mainPanel=new JPanel();
                                                        BoxLayout boxLayout=new BoxLayout(mainPanel,BoxLayout.Y_AXIS);
                                                        mainPanel.setLayout(boxLayout);
                                                        mainPanel.add(panel);
                                                        mainPanel.add(panel1);
                                                        mainPanel.add(panel2);
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
                                                                    ) {
                                                                        JOptionPane.showMessageDialog(null,"Hãy nhập đầy đủ thông tin!");
                                                                    }
                                                                    else{
                                                                        JOptionPane.showMessageDialog(null,"Đã lưu lại thông tin!");
                                                                        textField.setFocusable(false);
                                                                        textField1.setFocusable(false);
                                                                        textField2.setFocusable(false);
                                                                        int pos=table.getSelectedRow();
                                                                        finalSbjList3.remove(pos);
                                                                        temp.setMaMH(textField.getText());
                                                                        temp.setTenMH(textField1.getText());
                                                                        temp.setTinChi(Integer.parseInt(textField2.getText()));
                                                                        finalSbjList3.add(pos,temp);
                                                                        Vector dulieu = new Vector();
                                                                        for (int iii = 0; iii < finalSbjList2.size(); iii++) {
                                                                            Vector phu = new Vector();
                                                                            phu.add(finalSbjList2.get(iii).getMaMH());
                                                                            phu.add(finalSbjList2.get(iii).getTenMH());
                                                                            phu.add(finalSbjList2.get(iii).getTinChi());
                                                                            dulieu.add(phu);
                                                                        }
                                                                        try {
                                                                            LamTrangFile("DuLieuMonHoc.txt");
                                                                            GhiFileMonHoc(finalSbjList3);
                                                                            table.setModel(new DefaultTableModel(dulieu,Header));
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
                                            }
                                        });
                                        Update.setIcon(new ImageIcon("update.png"));
                                        JButton delete=new JButton("Delete");
                                        delete.setIcon(new ImageIcon("delete.png"));
                                        delete.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if(e.getSource()==delete)
                                                {
                                                    if(table.getSelectedRow()<0)
                                                    {
                                                        JOptionPane.showMessageDialog(null,"Hãy chọn đối tượng cần xóa!");
                                                    }
                                                    else
                                                    {
                                                        int position=table.getSelectedRow();
                                                        finalSbjList5.remove(position);
                                                        Vector List=new Vector();
                                                        for(int zzz = 0; zzz< finalSbjList5.size(); zzz++)
                                                        {
                                                            Vector tm=new Vector();
                                                            tm.add(finalSbjList5.get(zzz).getMaMH());
                                                            tm.add(finalSbjList5.get(zzz).getTenMH());
                                                            tm.add(finalSbjList5.get(zzz).getTinChi());
                                                            List.add(tm);
                                                        }
                                                        table.setModel(new DefaultTableModel(List,Header));
                                                        try {
                                                            LamTrangFile("DuLieuMonHoc.txt");
                                                            GhiFileMonHoc(finalSbjList5);
                                                        } catch (IOException ioException) {
                                                            ioException.printStackTrace();
                                                        }
                                                    }
                                                }
                                            }
                                        });
                                        JPanel southPanel=new JPanel();
                                        southPanel.add(Search);
                                        southPanel.add(add);
                                        southPanel.add(Update);
                                        southPanel.add(delete);
                                        centerPanel.add(sp);
                                        listFrame.add(listlabel,BorderLayout.NORTH);
                                        listFrame.add(centerPanel,BorderLayout.CENTER);
                                        listFrame.add(southPanel,BorderLayout.SOUTH);
                                        listFrame.pack();
                                    }
                                }
                            });
                            Subject.setIcon(new ImageIcon("subject.png"));
                            mainPanel.add(Subject);
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
    static void GhiFileMonHoc(Vector a) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieuMonHoc.txt"));
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
    static Vector DocFileMonHoc(Vector a) throws IOException, ClassNotFoundException {

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("DuLieuMonHoc.txt"));
        int count=in.readInt();
        for(int i=0;i<count;i++)
        {
            Subject acc= (Subject) in.readObject();
            a.add(acc);
        }
        in.close();
        return a;
    }
    static void LamTrangFile(String filename) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(filename));
        out.writeBytes("");
        out.flush();
        out.close();
    }
}
