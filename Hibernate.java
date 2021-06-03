import javax.crypto.SealedObject;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import static java.awt.Color.black;
import static java.awt.Color.green;

public class Hibernate {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        Vector<Account>Accounts=new Vector<>();
        Vector<HocSinh>stdList=new Vector<>();
        Vector<Subject>sbjList=new Vector<>();
        Vector<Semester>semesterList=new Vector<>();
        Vector<Class>classList=new Vector<>();
        Vector<Course>courseList=new Vector<>();
        Vector<Session>sessionList=new Vector<>();
        final Semester[] currentSemester = new Semester[1];
        Vector teacherList=new Vector();
        if(FileSize("DuLieuCourse.txt")!=0)
        {
            courseList=DocFileCourse(courseList);
        }
        if(FileSize("DuLieuLop.txt")!=0)
        {
            classList=DocFileLop(classList);
        }
        if(FileSize("DuLieuHocKyhientai.txt")!=0)
        {
            currentSemester[0]=DocFilecurrentSemester(currentSemester[0]);
        }
        if(FileSize("DuLieu.txt")!=0)
        {
            teacherList=DocFile(teacherList);
        }
        if(FileSize("DuLieuAccount.txt")!=0)
        {
            Accounts=DocFile2(Accounts);
        }
        if(FileSize("DuLieuMonHoc.txt")!=0)
        {
            sbjList=DocFileMonHoc(sbjList);
        }
        if(FileSize("DuLieuHocKy.txt")!=0)
        {
            semesterList=DocFileSemester(semesterList);
        }
        if(FileSize("DuLieuHS.txt")!=0)
        {
            stdList=DocFileStd(stdList);
        }
        if(FileSize("DuLieuSession.txt")!=0)
        {
            sessionList=DocFileSession(sessionList);
        }
        if(stdList.size()==0)
        {
            HocSinh hs1=new HocSinh(111,"Tuan",8,"Hình tuấn","150 Quang Trung","19CTT2");
            HocSinh hs2=new HocSinh(112,"Hung",5,"Hình hưng","100 Trần Phú","19CTT2");
            Vector<Subject>test=new Vector<>();
            Subject mon1=new Subject("M001","Toán tổ hợp",4);
            test.add(mon1);
            HocSinh hs3=new HocSinh(113,"Hau",9,"Hình hậu","15 Đỗ Trạc","19CTT2",test);
            stdList.add(hs1);
            stdList.add(hs2);
            stdList.add(hs3);
        }
        if(Accounts.size()==0)
        {
            Teacher tc1=new Teacher();
            Account admin=new Account("admin","admin","Teacher");
            Accounts.add(admin);
            tc1.setAccount(admin);
            Teacher tc2=new Teacher();
            Account minh=new Account("minh","minh","Teacher");
            Accounts.add(minh);
            tc2.setAccount(minh);
            Account hung=new Account("hung","hung","Student");
            Accounts.add(hung);
            stdList.get(1).setAccount(hung);
            teacherList.add(tc1);
            teacherList.add(tc2);
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
        if(semesterList.size()==0)
        {
            Semester one=new Semester();
            one.setTenHK("Học kỳ 1");
            one.setYear(2021);
            DateTime bd=new DateTime(1,1,2021);
            DateTime kt=new DateTime(31,3,2021);
            one.setNgayBD(bd);
            one.setNgayKT(kt);
            Semester two=new Semester();
            two.setTenHK("Học kỳ 2");
            two.setYear(2021);
            DateTime bd2=new DateTime(1,4,2021);
            DateTime kt2=new DateTime(31,6,2021);
            two.setNgayBD(bd2);
            two.setNgayKT(kt2);
            Semester three=new Semester();
            three.setTenHK("Học kỳ 3");
            three.setYear(2021);
            DateTime bd3=new DateTime(1,7,2021);
            DateTime kt3=new DateTime(31,9,2021);
            three.setNgayBD(bd3);
            three.setNgayKT(kt3);
            semesterList.add(one);
            semesterList.add(two);
            semesterList.add(three);
        }
        if(sessionList.size()==0)
        {
            Session one=new Session();
            one.setName("Học kỳ 1");
            DateTime bd=new DateTime(1,1,2021);
            DateTime kt=new DateTime(10,1,2021);
            one.setStart(bd);
            one.setEnd(kt);
            Session two=new Session();
            two.setName("Học kỳ 2");
            DateTime bd2=new DateTime(1,6,2021);
            DateTime kt2=new DateTime(10,6,2021);
            two.setStart(bd2);
            two.setEnd(kt2);
            Session three=new Session();
            three.setName("Học kỳ 3");
            DateTime bd3=new DateTime(1,7,2021);
            DateTime kt3=new DateTime(10,7,2021);
            three.setStart(bd3);
            three.setEnd(kt3);
            sessionList.add(one);
            sessionList.add(two);
            sessionList.add(three);
            Vector<Session>sessions=new Vector<>();
            sessions.add(two);
            semesterList.get(1).setDKHP(sessions);
        }
        if(classList.size()==0)
        {
            Class cls1=new Class("19CTT2",100,70,30);
            Class cls2=new Class("19CTT3",120,100,20);
            Class cls3=new Class("19CTT4",100,80,20);
            classList.add(cls1);
            classList.add(cls2);
            classList.add(cls3);
            classList.get(0).setSVList(stdList);
        }
        if(courseList.size()==0)
        {
            Course c1=new Course("JV001","Lập trình ứng dụng Java",4,"Nguyễn Văn Khiết","F302","Wednesday",1,150);
            Course c2=new Course("PHY002","Lý 1",3,"Lê Văn Luyện","F201","Monday",2,150);
            Course c3=new Course("BI001","Sinh 1",3,"Bạch Thái Bưởi","F205","Monday",2,150);
            Course c4=new Course("JV002","Lập trình ứng dụng Java 2",4,"Nguyễn Văn Khiết","E105","Thursday",3,100);
            Course c5=new Course("PHY003","Lý 3",3,"Nguyễn Văn Hùng","F101","Friday",4,120);
            Course c6=new Course("BI002","Sinh 2",3,"Bạch Thái Công","F202","Tuesday",3,110);
            Course c7=new Course("JV003","Lập trình ứng dụng Java 3",4,"Nguyễn Văn Nghĩa","C102","Saturday",1,150);
            Course c8=new Course("MA003","Vi tích phân 3B",2,"Lê Văn Chánh","D203","Wednesday",4,150);
            Course c9=new Course("MA001","Vi tích phân 1B",1,"Lê Thị Kiều","G101","Monday",1,140);
            Course c10=new Course("PRO001","Nhập môn lập trình",3,"Thái Hùng Văn","G104","Saturday",4,130);
            courseList.add(c1);
            courseList.add(c2);
            courseList.add(c3);
            courseList.add(c4);
            courseList.add(c5);
            courseList.add(c6);
            courseList.add(c7);
            courseList.add(c8);
            courseList.add(c9);
            courseList.add(c10);
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
        Vector<Account> finalAccounts = Accounts;
        Vector<Account> finalAccounts1 = Accounts;
        Vector<Account> finalAccounts2 = Accounts;
        Vector<Account> finalAccounts3 = Accounts;
        Vector finalTeacherList3 = teacherList;
        Vector finalTeacherList4 = teacherList;
        Vector finalTeacherList5 = teacherList;
        Vector finalTeacherList6 = teacherList;
        Vector finalTeacherList7 = teacherList;
        Vector<Account> finalAccounts4 = Accounts;
        Vector<Account> finalAccounts5 = Accounts;
        Vector finalTeacherList8 = teacherList;
        Vector<Account> finalAccounts6 = Accounts;
        Vector<Subject> finalSbjList = sbjList;
        Vector<Subject> finalSbjList1 = sbjList;
        Vector<Subject> finalSbjList2 = sbjList;
        Vector<Subject> finalSbjList3 = sbjList;
        Vector<Subject> finalSbjList4 = sbjList;
        Vector<Subject> finalSbjList5 = sbjList;
        Vector<Semester> finalSemesterList = semesterList;
        Vector<Semester> finalSemesterList1 = semesterList;
        Vector<Semester> finalSemesterList2 = semesterList;
        Vector<Semester> finalSemesterList3 = semesterList;
        Vector<Class> finalClassList = classList;
        Vector<Class> finalClassList1 = classList;
        Vector<Class> finalClassList2 = classList;
        Vector<HocSinh> finalStdList = stdList;
        Vector<Class> finalClassList3 = classList;
        Vector<HocSinh> finalStdList1 = stdList;
        Vector<HocSinh> finalStdList2 = stdList;
        Vector<HocSinh> finalStdList3 = stdList;
        Vector<HocSinh> finalStdList4 = stdList;
        Vector<Class> finalClassList4 = classList;
        Vector<HocSinh> finalStdList5 = stdList;
        Vector<HocSinh> finalStdList6 = stdList;
        Vector<HocSinh> finalStdList7 = stdList;
        Vector<Session> finalSessionList = sessionList;
        Vector<Session> finalSessionList1 = sessionList;
        Vector<Semester> finalSemesterList4 = semesterList;
        Vector<Course> finalCourseList = courseList;
        Vector<Course> finalCourseList1 = courseList;
        Vector<Course> finalCourseList2 = courseList;
        Vector<Course> finalCourseList3 = courseList;
        Vector<Course> finalCourseList4 = courseList;
        Vector<HocSinh> finalStdList8 = stdList;
        Vector<HocSinh> finalStdList9 = stdList;
        Vector<Course> finalCourseList5 = courseList;
        Vector<Course> finalCourseList6 = courseList;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button)
                {
                    final boolean[] check = {false};
                    String user=username.getText().toString();
                    String pass=String.valueOf(password.getPassword());
                    for(int i = 0; i< finalAccounts.size(); i++)
                    {
                        if(finalAccounts.get(i).getKey().equals(user)&& finalAccounts.get(i).getValue().equals(pass)) {
                            if (finalAccounts.get(i).getType().equals("Teacher")) {
                                check[0] = true;
                                JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
                                JFrame frame = new MyFrame();
                                jFrame.dispose();
                                Teacher temp = new Teacher();
                                int pos = 0;
                                for (int j = 0; j < finalTeacherList.size(); j++) {
                                    if (user.equals(finalTeacherList.get(j).getAccount().getKey()) && pass.equals(finalTeacherList.get(j).getAccount().getValue())) {
                                        pos = j;
                                        temp = finalTeacherList.get(j);
                                    }
                                }
                                BorderLayout borderLayout = new BorderLayout();
                                frame.setLayout(borderLayout);
                                JButton logout = new JButton("Sign out");
                                JPanel northPan = new JPanel();
                                JLabel hello = new JLabel("Hello, " + user);
                                JLabel curSes = new JLabel();
                                if (currentSemester[0] != null) {
                                    curSes.setText("Học kỳ hiện tại:" + currentSemester[0].getTenHK() + "," + currentSemester[0].getYear() + "|");
                                }
                                JLabel now=new JLabel("Ngày hiện tại:"+java.time.LocalDate.now()+"|");
                                northPan.setLayout(new FlowLayout(FlowLayout.RIGHT));
                                northPan.add(now);
                                northPan.add(curSes);
                                northPan.add(hello);
                                northPan.add(logout);
                                logout.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == logout) {
                                            JOptionPane.showMessageDialog(null, "Đăng xuất thành công!");
                                            System.exit(0);
                                        }
                                    }
                                });
                                frame.add(northPan, BorderLayout.NORTH);
                                JPanel mainPanel = new JPanel();
                                mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                                JButton info = new JButton("User account");
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
                                        if (e.getSource() == info) {
                                            JFrame jFrame1 = new JFrame();
                                            jFrame1.setTitle("User account");
                                            ImageIcon Icon = new ImageIcon("user.png");
                                            jFrame1.setIconImage(Icon.getImage());
                                            jFrame1.setSize(520, 450);
                                            jFrame1.setResizable(false);
                                            jFrame1.setVisible(true);
                                            JPanel panel = new JPanel();
                                            JLabel label1 = new JLabel();
                                            HocSinh hs = new HocSinh();
                                            label1.setText("Mã giáo viên:");
                                            JTextField textField = new JTextField();
                                            textField.setText(String.valueOf(finalTemp.getMGV()));
                                            if (textField.getText().equals("0")) {
                                                textField.setText("");
                                            }
                                            textField.setPreferredSize(new Dimension(500, 30));
                                            panel.add(label1);
                                            panel.add(textField);
                                            JPanel panel1 = new JPanel();
                                            JLabel label2 = new JLabel();
                                            label2.setText("Tên giáo viên:");
                                            JTextField textField1 = new JTextField();
                                            textField1.setText(finalTemp1.getTenGV());
                                            textField1.setPreferredSize(new Dimension(500, 30));
                                            panel1.add(label2);
                                            panel1.add(textField1);
                                            JPanel panel2 = new JPanel();
                                            JLabel label3 = new JLabel();
                                            label3.setText("Tuổi:");
                                            JTextField textField2 = new JTextField();
                                            textField2.setText(String.valueOf(finalTemp2.getTuoi()));
                                            if (textField2.getText().equals("0")) {
                                                textField2.setText("");
                                            }
                                            textField2.setPreferredSize(new Dimension(500, 30));
                                            panel2.add(label3);
                                            panel2.add(textField2);
                                            JPanel panel3 = new JPanel();
                                            JLabel label4 = new JLabel();
                                            label4.setText("Địa chỉ:");
                                            JTextField textField3 = new JTextField();
                                            textField3.setText(finalTemp3.getDiaChi());
                                            textField3.setPreferredSize(new Dimension(500, 30));
                                            panel3.add(label4);
                                            panel3.add(textField3);
                                            JPanel panel4 = new JPanel();
                                            JLabel label5 = new JLabel();
                                            label5.setText("Ghi chú:");
                                            JTextField textField4 = new JTextField();
                                            textField4.setText(finalTemp4.getGhiChu());
                                            textField4.setPreferredSize(new Dimension(500, 30));
                                            panel4.add(label5);
                                            panel4.add(textField4);
                                            JPanel panel5 = new JPanel();
                                            JLabel label6 = new JLabel();
                                            label6.setText("Hình ảnh:");
                                            panel5.add(label6);
                                            JButton fileBut = new JButton("Chọn ảnh");
                                            panel5.add(fileBut);
                                            JTextField textField5 = new JTextField();
                                            textField5.setText(finalTemp5.getHinhanh());
                                            textField5.setPreferredSize(new Dimension(500, 30));
                                            panel5.add(textField5);
                                            fileBut.setVisible(false);
                                            fileBut.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == fileBut) {
                                                        JFileChooser fileChooser = new JFileChooser();
                                                        int response = fileChooser.showOpenDialog(null);
                                                        if (response == JFileChooser.APPROVE_OPTION) {
                                                            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                                                            String path = String.valueOf(file);
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
                                            JPanel mainPanel = new JPanel();
                                            BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
                                            mainPanel.setLayout(boxLayout);
                                            mainPanel.add(panel);
                                            mainPanel.add(panel1);
                                            mainPanel.add(panel2);
                                            mainPanel.add(panel3);
                                            mainPanel.add(panel4);
                                            mainPanel.add(panel5);
                                            jFrame1.add(mainPanel);
                                            JPanel butPanel = new JPanel();
                                            JButton but1 = new JButton("Sửa thông tin");
                                            but1.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == but1) {
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
                                            JButton saveBut = new JButton("Lưu");
                                            saveBut.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == saveBut) {
                                                        if (textField.getText().equals("") || textField1.getText().equals("") || textField2.getText().equals("")
                                                                || textField3.getText().equals("") || textField4.getText().equals("") || textField5.getText().equals("")) {
                                                            JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin!");
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "Đã lưu lại thông tin!");
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
                                                            finalTeacherList.add(finalPos, finalTemp);
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
                                            FlowLayout flowLayout = new FlowLayout();
                                            butPanel.setLayout(flowLayout);
                                            butPanel.add(but1);
                                            butPanel.add(saveBut);
                                            mainPanel.add(butPanel);
                                        }
                                    }
                                });
                                JButton DanhSach = new JButton("Danh sách account GV");
                                mainPanel.add(DanhSach);
                                DanhSach.setIcon(new ImageIcon("list.png"));
                                DanhSach.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        JFrame listFrame = new JFrame();
                                        listFrame.setTitle("Danh sách account GV");
                                        listFrame.setIconImage(new ImageIcon("list.png").getImage());
                                        listFrame.setSize(700, 700);
                                        listFrame.setVisible(true);
                                        JButton Search = new JButton("Search");
                                        Search.setIcon(new ImageIcon("lupe.png"));
                                        BorderLayout borderLayout1 = new BorderLayout();
                                        listFrame.setLayout(borderLayout1);
                                        JPanel centerPanel = new JPanel();
                                        JLabel listlabel = new JLabel("List Accounts");
                                        listlabel.setHorizontalAlignment(0);
                                        Vector Header = new Vector();
                                        Header.add("Username");
                                        Header.add("Password");
                                        Vector data = new Vector();
                                        for (int ii = 0; ii < finalAccounts1.size(); ii++) {
                                            Vector temp = new Vector();
                                            temp.add(finalAccounts1.get(ii).getKey());
                                            temp.add(finalAccounts1.get(ii).getValue());
                                            data.add(temp);
                                        }
                                        JTable table = new JTable(data, Header);
                                        JScrollPane sp = new JScrollPane(table);
                                        Search.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if (e.getSource() == Search) {
                                                    JFrame temp = new JFrame();
                                                    temp.setSize(300, 300);
                                                    temp.setVisible(true);
                                                    temp.setIconImage(new ImageIcon("lupe.png").getImage());
                                                    temp.setTitle("Search");
                                                    JPanel panel = new JPanel();
                                                    JLabel label = new JLabel("Username:");
                                                    panel.setLayout(new FlowLayout());
                                                    JTextField jTextField = new JTextField();
                                                    jTextField.setPreferredSize(new Dimension(300, 30));
                                                    panel.add(label);
                                                    panel.add(jTextField);
                                                    JPanel bigPanel = new JPanel();
                                                    bigPanel.add(panel);
                                                    JButton but = new JButton("Search");
                                                    but.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (e.getSource() == but) {
                                                                String name = jTextField.getText();
                                                                boolean check = false;
                                                                for (int i = 0; i < finalAccounts2.size(); i++) {
                                                                    if (name.equals(finalAccounts2.get(i).getKey())) {
                                                                        check = true;
                                                                        Vector DuLieu = new Vector();
                                                                        Vector acc = new Vector();
                                                                        acc.add(finalAccounts2.get(i).getKey());
                                                                        acc.add(finalAccounts2.get(i).getValue());
                                                                        DuLieu.add(acc);
                                                                        table.setModel(new DefaultTableModel(DuLieu, Header));
                                                                        break;
                                                                    }
                                                                }
                                                                if (check == false) {
                                                                    JOptionPane.showMessageDialog(null, "Không tìm thấy account!");
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
                                        JButton add = new JButton("Add");
                                        add.setIcon(new ImageIcon("rsz_add.png"));
                                        add.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if (e.getSource() == add) {
                                                    JFrame Temp = new JFrame();
                                                    Temp.setSize(300, 300);
                                                    Temp.setVisible(true);
                                                    Temp.setIconImage(new ImageIcon("rsz_add.png").getImage());
                                                    Temp.setTitle("Add");
                                                    JPanel Jpanel = new JPanel();
                                                    JLabel Jlabel = new JLabel("Username:");
                                                    Jpanel.setLayout(new FlowLayout());
                                                    JTextField JtextField = new JTextField();
                                                    JtextField.setPreferredSize(new Dimension(300, 30));
                                                    Jpanel.add(Jlabel);
                                                    Jpanel.add(JtextField);
                                                    JPanel Jpanel1 = new JPanel();
                                                    JLabel Jlabel1 = new JLabel("Password:");
                                                    Jpanel1.setLayout(new FlowLayout());
                                                    JTextField JtextField1 = new JTextField();
                                                    JtextField1.setPreferredSize(new Dimension(300, 30));
                                                    Jpanel1.add(Jlabel1);
                                                    Jpanel1.add(JtextField1);
                                                    JPanel BPanel = new JPanel();
                                                    BoxLayout boxLayout1 = new BoxLayout(BPanel, BoxLayout.Y_AXIS);
                                                    BPanel.setLayout(boxLayout1);
                                                    BPanel.add(Jpanel);
                                                    BPanel.add(Jpanel1);
                                                    JButton Them = new JButton("Thêm");
                                                    Them.addActionListener(new ActionListener() {
                                                        @Override
                                                        public void actionPerformed(ActionEvent e) {
                                                            if (e.getSource() == Them) {
                                                                if (JtextField.getText().equals("") || JtextField1.getText().equals("")) {
                                                                    JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                                                                } else {
                                                                    String newUser = JtextField.getText();
                                                                    String newPass = JtextField1.getText();
                                                                    boolean kt = false;
                                                                    for (int jj = 0; jj < finalAccounts3.size(); jj++) {
                                                                        if (newUser.equals(finalAccounts3.get(jj).getKey())) {
                                                                            kt = true;
                                                                            JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại");
                                                                        }
                                                                    }
                                                                    if (kt == false) {
                                                                        Account newAcc = new Account(newUser, newPass);
                                                                        finalAccounts3.add(newAcc);
                                                                        Teacher newTeacher = new Teacher();
                                                                        newTeacher.setAccount(newAcc);
                                                                        finalTeacherList3.add(newTeacher);
                                                                        try {
                                                                            LamTrangFile("DuLieu.txt");
                                                                            LamTrangFile("DuLieuAccount.txt");
                                                                            GhiFile(finalTeacherList3, finalTeacherList3.size());
                                                                            GhiFile2(finalAccounts3);
                                                                            Vector dulieu = new Vector();
                                                                            for (int iii = 0; iii < finalAccounts3.size(); iii++) {
                                                                                Vector phu = new Vector();
                                                                                phu.add(finalAccounts3.get(iii).getKey());
                                                                                phu.add(finalAccounts3.get(iii).getValue());
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
                                        JButton Update = new JButton("Update");
                                        Update.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if (e.getSource() == Update) {
                                                    if (table.getSelectedRow() < 0) {
                                                        JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần update!");
                                                    } else {
                                                        Teacher temp = (Teacher) finalTeacherList6.get(table.getSelectedRow());
                                                        JFrame jFrame1 = new JFrame();
                                                        jFrame1.setTitle("Update");
                                                        ImageIcon Icon = new ImageIcon("update.png");
                                                        jFrame1.setIconImage(Icon.getImage());
                                                        jFrame1.setSize(520, 450);
                                                        jFrame1.setResizable(false);
                                                        jFrame1.setVisible(true);
                                                        JPanel panel = new JPanel();
                                                        JLabel label1 = new JLabel();
                                                        HocSinh hs = new HocSinh();
                                                        label1.setText("Mã giáo viên:");
                                                        JTextField textField = new JTextField();
                                                        textField.setText(String.valueOf(temp.getMGV()));
                                                        if (textField.getText().equals("0")) {
                                                            textField.setText("");
                                                        }
                                                        textField.setPreferredSize(new Dimension(500, 30));
                                                        panel.add(label1);
                                                        panel.add(textField);
                                                        JPanel panel1 = new JPanel();
                                                        JLabel label2 = new JLabel();
                                                        label2.setText("Tên giáo viên:");
                                                        JTextField textField1 = new JTextField();
                                                        textField1.setText(temp.getTenGV());
                                                        textField1.setPreferredSize(new Dimension(500, 30));
                                                        panel1.add(label2);
                                                        panel1.add(textField1);
                                                        JPanel panel2 = new JPanel();
                                                        JLabel label3 = new JLabel();
                                                        label3.setText("Tuổi:");
                                                        JTextField textField2 = new JTextField();
                                                        textField2.setText(String.valueOf(temp.getTuoi()));
                                                        if (textField2.getText().equals("0")) {
                                                            textField2.setText("");
                                                        }
                                                        textField2.setPreferredSize(new Dimension(500, 30));
                                                        panel2.add(label3);
                                                        panel2.add(textField2);
                                                        JPanel panel3 = new JPanel();
                                                        JLabel label4 = new JLabel();
                                                        label4.setText("Địa chỉ:");
                                                        JTextField textField3 = new JTextField();
                                                        textField3.setText(temp.getDiaChi());
                                                        textField3.setPreferredSize(new Dimension(500, 30));
                                                        panel3.add(label4);
                                                        panel3.add(textField3);
                                                        JPanel panel4 = new JPanel();
                                                        JLabel label5 = new JLabel();
                                                        label5.setText("Ghi chú:");
                                                        JTextField textField4 = new JTextField();
                                                        textField4.setText(temp.getGhiChu());
                                                        textField4.setPreferredSize(new Dimension(500, 30));
                                                        panel4.add(label5);
                                                        panel4.add(textField4);
                                                        JPanel panel5 = new JPanel();
                                                        JLabel label6 = new JLabel();
                                                        label6.setText("Hình ảnh:");
                                                        panel5.add(label6);
                                                        JButton fileBut = new JButton("Chọn ảnh");
                                                        panel5.add(fileBut);
                                                        JTextField textField5 = new JTextField();
                                                        textField5.setText(temp.getHinhanh());
                                                        textField5.setPreferredSize(new Dimension(500, 30));
                                                        panel5.add(textField5);
                                                        fileBut.setVisible(false);
                                                        fileBut.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == fileBut) {
                                                                    JFileChooser fileChooser = new JFileChooser();
                                                                    int response = fileChooser.showOpenDialog(null);
                                                                    if (response == JFileChooser.APPROVE_OPTION) {
                                                                        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                                                                        String path = String.valueOf(file);
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
                                                        JPanel mainPanel = new JPanel();
                                                        BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
                                                        mainPanel.setLayout(boxLayout);
                                                        mainPanel.add(panel);
                                                        mainPanel.add(panel1);
                                                        mainPanel.add(panel2);
                                                        mainPanel.add(panel3);
                                                        mainPanel.add(panel4);
                                                        mainPanel.add(panel5);
                                                        jFrame1.add(mainPanel);
                                                        JPanel butPanel = new JPanel();
                                                        JButton but1 = new JButton("Sửa thông tin");
                                                        but1.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == but1) {
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
                                                        JButton saveBut = new JButton("Lưu");
                                                        saveBut.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == saveBut) {
                                                                    if (textField.getText().equals("") || textField1.getText().equals("") || textField2.getText().equals("")
                                                                            || textField3.getText().equals("") || textField4.getText().equals("") || textField5.getText().equals("")) {
                                                                        JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin!");
                                                                    } else {
                                                                        JOptionPane.showMessageDialog(null, "Đã lưu lại thông tin!");
                                                                        textField.setFocusable(false);
                                                                        textField1.setFocusable(false);
                                                                        textField2.setFocusable(false);
                                                                        textField3.setFocusable(false);
                                                                        textField4.setFocusable(false);
                                                                        textField5.setFocusable(false);
                                                                        fileBut.setVisible(false);
                                                                        int pos = table.getSelectedRow();
                                                                        finalTeacherList.remove(pos);
                                                                        temp.setMGV(Integer.parseInt(textField.getText()));
                                                                        temp.setTenGV(textField1.getText());
                                                                        temp.setTuoi(Integer.parseInt(textField2.getText()));
                                                                        temp.setHinhanh(textField3.getText());
                                                                        temp.setDiaChi(textField4.getText());
                                                                        temp.setGhiChu(textField5.getText());
                                                                        finalTeacherList.add(pos, temp);
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
                                                        FlowLayout flowLayout = new FlowLayout();
                                                        butPanel.setLayout(flowLayout);
                                                        butPanel.add(but1);
                                                        butPanel.add(saveBut);
                                                        mainPanel.add(butPanel);
                                                    }
                                                }
                                            }
                                        });
                                        Update.setIcon(new ImageIcon("update.png"));
                                        JButton reset = new JButton("Reset");
                                        reset.setIcon(new ImageIcon("reset.png"));
                                        reset.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if (e.getSource() == reset) {
                                                    if (table.getSelectedRow() < 0) {
                                                        JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần reset password");
                                                    } else {
                                                        JOptionPane.showMessageDialog(null, "Reset password thành công!");
                                                        JOptionPane.showMessageDialog(null, "Đã reset password giống với username!");
                                                        int vitri = table.getSelectedRow();
                                                        Teacher tempTeacher = (Teacher) finalTeacherList7.get(vitri);
                                                        finalTeacherList7.remove(vitri);
                                                        finalTeacherList7.add(vitri, tempTeacher);
                                                        finalAccounts4.get(vitri).setValue(finalAccounts4.get(vitri).getKey());
                                                        Vector list = new Vector();
                                                        for (int zz = 0; zz < finalTeacherList7.size(); zz++) {
                                                            Vector tmp = new Vector();
                                                            tmp.add(finalAccounts4.get(zz).getKey());
                                                            tmp.add(finalAccounts4.get(zz).getValue());
                                                            list.add(tmp);
                                                        }
                                                        table.setModel(new DefaultTableModel(list, Header));
                                                        try {
                                                            LamTrangFile("DuLieu.txt");
                                                            LamTrangFile("DuLieuAccount.txt");
                                                            GhiFile(finalTeacherList7, finalTeacherList7.size());
                                                            GhiFile2(finalAccounts4);
                                                        } catch (IOException ioException) {
                                                            ioException.printStackTrace();
                                                        }

                                                    }
                                                }
                                            }
                                        });
                                        JButton delete = new JButton("Delete");
                                        delete.setIcon(new ImageIcon("delete.png"));
                                        delete.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent e) {
                                                if (e.getSource() == delete) {
                                                    if (table.getSelectedRow() < 0) {
                                                        JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần xóa!");
                                                    } else {
                                                        int position = table.getSelectedRow();
                                                        finalTeacherList8.remove(position);
                                                        finalAccounts6.remove(position);
                                                        Vector List = new Vector();
                                                        for (int zzz = 0; zzz < finalTeacherList8.size(); zzz++) {
                                                            Vector tm = new Vector();
                                                            tm.add(finalAccounts6.get(zzz).getKey());
                                                            tm.add(finalAccounts6.get(zzz).getValue());
                                                            List.add(tm);
                                                        }
                                                        table.setModel(new DefaultTableModel(List, Header));
                                                        try {
                                                            LamTrangFile("DuLieu.txt");
                                                            LamTrangFile("DuLieuAccount.txt");
                                                            GhiFile(finalTeacherList8, finalTeacherList8.size());
                                                            GhiFile2(finalAccounts6);
                                                        } catch (IOException ioException) {
                                                            ioException.printStackTrace();
                                                        }
                                                    }
                                                }
                                            }
                                        });
                                        JPanel southPanel = new JPanel();
                                        southPanel.add(Search);
                                        southPanel.add(add);
                                        southPanel.add(Update);
                                        southPanel.add(reset);
                                        southPanel.add(delete);
                                        centerPanel.add(sp);
                                        listFrame.add(listlabel, BorderLayout.NORTH);
                                        listFrame.add(centerPanel, BorderLayout.CENTER);
                                        listFrame.add(southPanel, BorderLayout.SOUTH);
                                        listFrame.pack();
                                    }
                                });


                                JButton changePass = new JButton("Đổi mật khẩu");
                                mainPanel.add(changePass);
                                ImageIcon useraccount = new ImageIcon("user.png");
                                info.setIcon(useraccount);
                                ImageIcon changepass = new ImageIcon("key.png");
                                changePass.setIcon(changepass);
                                changePass.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == changePass) {
                                            JFrame passframe = new JFrame();
                                            passframe.setTitle("Đổi mật khẩu");
                                            ImageIcon icon1 = new ImageIcon("key.png");
                                            passframe.setIconImage(icon1.getImage());
                                            passframe.setSize(520, 450);
                                            passframe.setResizable(false);
                                            passframe.setVisible(true);
                                            JPanel passPanel = new JPanel();
                                            JPanel jPanel = new JPanel();
                                            BoxLayout boxLayout = new BoxLayout(passPanel, BoxLayout.Y_AXIS);
                                            passPanel.setLayout(boxLayout);
                                            JLabel jlabel = new JLabel("Mật khẩu cũ:");
                                            jPanel.add(jlabel);
                                            JTextField jtextField = new JTextField();
                                            jtextField.setPreferredSize(new Dimension(300, 30));
                                            jPanel.add(jtextField);
                                            passPanel.add(jPanel);
                                            JPanel jPanel1 = new JPanel();
                                            JLabel jlabel1 = new JLabel("Mật khẩu mới:");
                                            jPanel1.add(jlabel1);
                                            JTextField jtextField1 = new JTextField();
                                            jtextField1.setPreferredSize(new Dimension(300, 30));
                                            jPanel1.add(jtextField1);
                                            passPanel.add(jPanel1);
                                            JPanel jPanel2 = new JPanel();
                                            JLabel jlabel2 = new JLabel("Nhập lại mật khẩu mới:");
                                            jPanel2.add(jlabel2);
                                            JTextField jtextField2 = new JTextField();
                                            jtextField2.setPreferredSize(new Dimension(300, 30));
                                            jPanel2.add(jtextField2);
                                            passPanel.add(jPanel2);
                                            JPanel butPan = new JPanel();
                                            JButton change = new JButton("Đổi mật khẩu");
                                            butPan.add(change);
                                            change.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == change) {
                                                        if (jtextField.getText().equals("") || jtextField1.getText().equals("") || jtextField2.getText().equals("")) {
                                                            JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                                                        } else {
                                                            if (!jtextField.getText().equals(pass)) {
                                                                JOptionPane.showMessageDialog(null, "Sai mật khẩu cũ!");
                                                            } else {
                                                                if (!jtextField1.getText().equals(jtextField2.getText())) {
                                                                    JOptionPane.showMessageDialog(null, "Mật khẩu mới phải trùng nhau");
                                                                } else {
                                                                    JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
                                                                    for (int k = 0; k < finalTeacherList2.size(); k++) {
                                                                        if (user.equals(finalTeacherList2.get(k).getAccount().getKey())) {
                                                                            finalTeacherList2.get(k).getAccount().setValue(jtextField1.getText());
                                                                        }
                                                                    }
                                                                    for (int l = 0; l < finalAccounts.size(); l++) {
                                                                        if (user.equals(finalAccounts.get(l).getKey())) {
                                                                            finalAccounts.get(l).setValue(jtextField1.getText());
                                                                        }
                                                                    }
                                                                    try {
                                                                        LamTrangFile("DuLieu.txt");
                                                                        LamTrangFile("DuLieuAccount.txt");
                                                                        GhiFile(finalTeacherList2, finalTeacherList2.size());
                                                                        GhiFile2(finalAccounts);
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
                                JButton Subject = new JButton("Subject");
                                Subject.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == Subject) {
                                            JFrame listFrame = new JFrame();
                                            listFrame.setTitle("Danh sách môn học");
                                            listFrame.setIconImage(new ImageIcon("subject.png").getImage());

                                            listFrame.setSize(700, 700);
                                            listFrame.setVisible(true);
                                            JButton Search = new JButton("Search");
                                            Search.setIcon(new ImageIcon("lupe.png"));
                                            BorderLayout borderLayout1 = new BorderLayout();
                                            listFrame.setLayout(borderLayout1);
                                            JPanel centerPanel = new JPanel();
                                            JLabel listlabel = new JLabel("List Subjects");
                                            listlabel.setHorizontalAlignment(0);
                                            Vector Header = new Vector();
                                            Header.add("Mã môn học");
                                            Header.add("Tên môn học");
                                            Header.add("Số tín chỉ");
                                            Vector data = new Vector();
                                            for (int ii = 0; ii < finalSbjList.size(); ii++) {
                                                Vector temp = new Vector();
                                                temp.add(finalSbjList.get(ii).getMaMH());
                                                temp.add(finalSbjList.get(ii).getTenMH());
                                                temp.add(finalSbjList.get(ii).getTinChi());
                                                data.add(temp);
                                            }
                                            JTable table = new JTable(data, Header);
                                            JScrollPane sp = new JScrollPane(table);
                                            Search.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == Search) {
                                                        JFrame temp = new JFrame();
                                                        temp.setSize(300, 300);
                                                        temp.setVisible(true);
                                                        temp.setIconImage(new ImageIcon("lupe.png").getImage());
                                                        temp.setTitle("Search");
                                                        JPanel panel = new JPanel();
                                                        JLabel label = new JLabel("Tên môn học:");
                                                        panel.setLayout(new FlowLayout());
                                                        JTextField jTextField = new JTextField();
                                                        jTextField.setPreferredSize(new Dimension(300, 30));
                                                        panel.add(label);
                                                        panel.add(jTextField);
                                                        JPanel bigPanel = new JPanel();
                                                        bigPanel.add(panel);
                                                        JButton but = new JButton("Search");
                                                        but.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == but) {
                                                                    String name = jTextField.getText();
                                                                    boolean check = false;
                                                                    Vector DuLieu = new Vector();
                                                                    for (int i = 0; i < finalSbjList1.size(); i++) {
                                                                        if (name.equals(finalSbjList1.get(i).getTenMH())) {
                                                                            check = true;
                                                                            Vector acc = new Vector();
                                                                            acc.add(finalSbjList1.get(i).getMaMH());
                                                                            acc.add(finalSbjList1.get(i).getTenMH());
                                                                            acc.add(finalSbjList1.get(i).getTinChi());
                                                                            DuLieu.add(acc);
                                                                        }
                                                                    }
                                                                    if (check == false) {
                                                                        JOptionPane.showMessageDialog(null, "Không tìm thấy môn học!");
                                                                    } else {
                                                                        table.setModel(new DefaultTableModel(DuLieu, Header));
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
                                            JButton add = new JButton("Add");
                                            add.setIcon(new ImageIcon("rsz_add.png"));
                                            add.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == add) {
                                                        JFrame Temp = new JFrame();
                                                        Temp.setSize(300, 300);
                                                        Temp.setVisible(true);
                                                        Temp.setIconImage(new ImageIcon("rsz_add.png").getImage());
                                                        Temp.setTitle("Add");
                                                        JPanel Jpanel = new JPanel();
                                                        JLabel Jlabel = new JLabel("Mã môn học:");
                                                        Jpanel.setLayout(new FlowLayout());
                                                        JTextField JtextField = new JTextField();
                                                        JtextField.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel.add(Jlabel);
                                                        Jpanel.add(JtextField);
                                                        JPanel Jpanel1 = new JPanel();
                                                        JLabel Jlabel1 = new JLabel("Tên môn học:");
                                                        Jpanel1.setLayout(new FlowLayout());
                                                        JTextField JtextField1 = new JTextField();
                                                        JtextField1.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel1.add(Jlabel1);
                                                        Jpanel1.add(JtextField1);
                                                        JPanel Jpanel2 = new JPanel();
                                                        JLabel Jlabel2 = new JLabel("Số tín chỉ:");
                                                        Jpanel2.setLayout(new FlowLayout());
                                                        JTextField JtextField2 = new JTextField();
                                                        JtextField2.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel2.add(Jlabel2);
                                                        Jpanel2.add(JtextField2);
                                                        JPanel BPanel = new JPanel();
                                                        BoxLayout boxLayout1 = new BoxLayout(BPanel, BoxLayout.Y_AXIS);
                                                        BPanel.setLayout(boxLayout1);
                                                        BPanel.add(Jpanel);
                                                        BPanel.add(Jpanel1);
                                                        BPanel.add(Jpanel2);
                                                        JButton Them = new JButton("Thêm");
                                                        Them.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == Them) {
                                                                    if (JtextField.getText().equals("") || JtextField1.getText().equals("")) {
                                                                        JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                                                                    } else {
                                                                        String ma = JtextField.getText();
                                                                        String ten = JtextField1.getText();
                                                                        int tinchi = Integer.parseInt(JtextField2.getText());
                                                                        boolean kt = false;
                                                                        for (int jj = 0; jj < finalSbjList2.size(); jj++) {
                                                                            if (ma.equals(finalSbjList2.get(jj).getMaMH())) {
                                                                                kt = true;
                                                                                JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại");
                                                                            }
                                                                        }
                                                                        if (kt == false) {
                                                                            Subject newSub = new Subject(ma, ten, tinchi);
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
                                            JButton Update = new JButton("Update");
                                            Update.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == Update) {
                                                        if (table.getSelectedRow() < 0) {
                                                            JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần update!");
                                                        } else {
                                                            Subject temp = (Subject) finalSbjList3.get(table.getSelectedRow());
                                                            JFrame jFrame1 = new JFrame();
                                                            jFrame1.setTitle("Update");
                                                            ImageIcon Icon = new ImageIcon("update.png");
                                                            jFrame1.setIconImage(Icon.getImage());
                                                            jFrame1.setSize(520, 450);
                                                            jFrame1.setResizable(false);
                                                            jFrame1.setVisible(true);
                                                            JPanel panel = new JPanel();
                                                            JLabel label1 = new JLabel();
                                                            label1.setText("Mã môn học:");
                                                            JTextField textField = new JTextField();
                                                            textField.setText(String.valueOf(temp.getMaMH()));
                                                            if (textField.getText().equals("0")) {
                                                                textField.setText("");
                                                            }
                                                            textField.setPreferredSize(new Dimension(500, 30));
                                                            panel.add(label1);
                                                            panel.add(textField);
                                                            JPanel panel1 = new JPanel();
                                                            JLabel label2 = new JLabel();
                                                            label2.setText("Tên môn học:");
                                                            JTextField textField1 = new JTextField();
                                                            textField1.setText(temp.getTenMH());
                                                            textField1.setPreferredSize(new Dimension(500, 30));
                                                            panel1.add(label2);
                                                            panel1.add(textField1);
                                                            JPanel panel2 = new JPanel();
                                                            JLabel label3 = new JLabel();
                                                            label3.setText("Số tín chỉ:");
                                                            JTextField textField2 = new JTextField();
                                                            textField2.setText(String.valueOf(temp.getTinChi()));
                                                            if (textField2.getText().equals("0")) {
                                                                textField2.setText("");
                                                            }
                                                            textField2.setPreferredSize(new Dimension(500, 30));
                                                            panel2.add(label3);
                                                            panel2.add(textField2);
                                                            textField.setFocusable(false);
                                                            textField1.setFocusable(false);
                                                            textField2.setFocusable(false);
                                                            JPanel mainPanel = new JPanel();
                                                            BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
                                                            mainPanel.setLayout(boxLayout);
                                                            mainPanel.add(panel);
                                                            mainPanel.add(panel1);
                                                            mainPanel.add(panel2);
                                                            jFrame1.add(mainPanel);
                                                            JPanel butPanel = new JPanel();
                                                            JButton but1 = new JButton("Sửa thông tin");
                                                            but1.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (e.getSource() == but1) {
                                                                        textField.setFocusable(true);
                                                                        textField1.setFocusable(true);
                                                                        textField2.setFocusable(true);
                                                                    }
                                                                }
                                                            });
                                                            JButton saveBut = new JButton("Lưu");
                                                            saveBut.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (e.getSource() == saveBut) {
                                                                        if (textField.getText().equals("") || textField1.getText().equals("") || textField2.getText().equals("")
                                                                        ) {
                                                                            JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin!");
                                                                        } else {
                                                                            JOptionPane.showMessageDialog(null, "Đã lưu lại thông tin!");
                                                                            textField.setFocusable(false);
                                                                            textField1.setFocusable(false);
                                                                            textField2.setFocusable(false);
                                                                            int pos = table.getSelectedRow();
                                                                            finalSbjList3.remove(pos);
                                                                            temp.setMaMH(textField.getText());
                                                                            temp.setTenMH(textField1.getText());
                                                                            temp.setTinChi(Integer.parseInt(textField2.getText()));
                                                                            finalSbjList3.add(pos, temp);
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
                                                                                table.setModel(new DefaultTableModel(dulieu, Header));
                                                                            } catch (IOException ioException) {
                                                                                ioException.printStackTrace();
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            });
                                                            FlowLayout flowLayout = new FlowLayout();
                                                            butPanel.setLayout(flowLayout);
                                                            butPanel.add(but1);
                                                            butPanel.add(saveBut);
                                                            mainPanel.add(butPanel);
                                                        }
                                                    }
                                                }
                                            });
                                            Update.setIcon(new ImageIcon("update.png"));
                                            JButton delete = new JButton("Delete");
                                            delete.setIcon(new ImageIcon("delete.png"));
                                            delete.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == delete) {
                                                        if (table.getSelectedRow() < 0) {
                                                            JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần xóa!");
                                                        } else {
                                                            int position = table.getSelectedRow();
                                                            finalSbjList5.remove(position);
                                                            Vector List = new Vector();
                                                            for (int zzz = 0; zzz < finalSbjList5.size(); zzz++) {
                                                                Vector tm = new Vector();
                                                                tm.add(finalSbjList5.get(zzz).getMaMH());
                                                                tm.add(finalSbjList5.get(zzz).getTenMH());
                                                                tm.add(finalSbjList5.get(zzz).getTinChi());
                                                                List.add(tm);
                                                            }
                                                            table.setModel(new DefaultTableModel(List, Header));
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
                                            JPanel southPanel = new JPanel();
                                            southPanel.add(Search);
                                            southPanel.add(add);
                                            southPanel.add(Update);
                                            southPanel.add(delete);
                                            centerPanel.add(sp);
                                            listFrame.add(listlabel, BorderLayout.NORTH);
                                            listFrame.add(centerPanel, BorderLayout.CENTER);
                                            listFrame.add(southPanel, BorderLayout.SOUTH);
                                            listFrame.pack();
                                        }
                                    }
                                });
                                Subject.setIcon(new ImageIcon("subject.png"));
                                JButton semester = new JButton("Semester");
                                semester.setIcon(new ImageIcon("semester.png"));
                                semester.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == semester) {
                                            JFrame listFrame = new JFrame();
                                            listFrame.setTitle("Danh sách học kỳ");
                                            listFrame.setIconImage(new ImageIcon("semester.png").getImage());

                                            listFrame.setSize(700, 700);
                                            listFrame.setVisible(true);
                                            JButton Set = new JButton("Set");
                                            Set.setIcon(new ImageIcon("set.png"));
                                            BorderLayout borderLayout1 = new BorderLayout();
                                            listFrame.setLayout(borderLayout1);
                                            JPanel centerPanel = new JPanel();
                                            JLabel listlabel = new JLabel("List Semesters");
                                            listlabel.setHorizontalAlignment(0);
                                            Vector Header = new Vector();
                                            Header.add("Tên học kỳ");
                                            Header.add("Năm học");
                                            Header.add("Ngày bắt đầu");
                                            Header.add("Ngày kết thúc");
                                            Vector data = new Vector();
                                            for (int ii = 0; ii < finalSemesterList.size(); ii++) {
                                                Vector temp = new Vector();
                                                temp.add(finalSemesterList.get(ii).getTenHK());
                                                temp.add(finalSemesterList.get(ii).getYear());
                                                temp.add(finalSemesterList.get(ii).getNgayBD());
                                                temp.add(finalSemesterList.get(ii).getNgayKT());
                                                data.add(temp);
                                            }
                                            JTable table = new JTable(data, Header);
                                            JScrollPane sp = new JScrollPane(table);
                                            Set.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == Set) {
                                                        if (table.getSelectedRow() < 0) {
                                                            JOptionPane.showMessageDialog(null, "Hãy chọn học kỳ để set!");
                                                        } else {
                                                            int pos = table.getSelectedRow();
                                                            currentSemester[0] = finalSemesterList3.get(pos);
                                                            JOptionPane.showMessageDialog(null, "Đã set thành học kỳ hiện tại!");
                                                            curSes.setText("Học kỳ hiện tại:" + currentSemester[0].getTenHK() + "," + currentSemester[0].getYear() + "|");
                                                            try {
                                                                LamTrangFile("DuLieuHocKyhientai.txt");
                                                                GhiFilecurrentSemester(currentSemester[0]);
                                                            } catch (IOException ioException) {
                                                                ioException.printStackTrace();
                                                            }
                                                        }
                                                    }
                                                }
                                            });
                                            JButton add = new JButton("Add");
                                            add.setIcon(new ImageIcon("rsz_add.png"));
                                            add.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == add) {
                                                        JFrame Temp = new JFrame();
                                                        Temp.setSize(300, 300);
                                                        Temp.setVisible(true);
                                                        Temp.setIconImage(new ImageIcon("rsz_add.png").getImage());
                                                        Temp.setTitle("Add");
                                                        JPanel Jpanel = new JPanel();
                                                        JLabel Jlabel = new JLabel("Tên học kỳ:");
                                                        Jpanel.setLayout(new FlowLayout());
                                                        JTextField JtextField = new JTextField();
                                                        JtextField.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel.add(Jlabel);
                                                        Jpanel.add(JtextField);
                                                        JPanel Jpanel1 = new JPanel();
                                                        JLabel Jlabel1 = new JLabel("Năm học:");
                                                        Jpanel1.setLayout(new FlowLayout());
                                                        JTextField JtextField1 = new JTextField();
                                                        JtextField1.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel1.add(Jlabel1);
                                                        Jpanel1.add(JtextField1);
                                                        JPanel Jpanel2 = new JPanel();
                                                        JLabel Jlabel2 = new JLabel("Ngày bắt đầu(dd/mm/yyyy):");
                                                        Jpanel2.setLayout(new FlowLayout());
                                                        JTextField JtextField2 = new JTextField();
                                                        JtextField2.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel2.add(Jlabel2);
                                                        Jpanel2.add(JtextField2);
                                                        JPanel Jpanel3 = new JPanel();
                                                        JLabel Jlabel3 = new JLabel("Ngày kết thúc(dd/mm/yyyy):");
                                                        Jpanel3.setLayout(new FlowLayout());
                                                        JTextField JtextField3 = new JTextField();
                                                        JtextField3.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel3.add(Jlabel3);
                                                        Jpanel3.add(JtextField3);
                                                        JPanel BPanel = new JPanel();
                                                        BoxLayout boxLayout1 = new BoxLayout(BPanel, BoxLayout.Y_AXIS);
                                                        BPanel.setLayout(boxLayout1);
                                                        BPanel.add(Jpanel);
                                                        BPanel.add(Jpanel1);
                                                        BPanel.add(Jpanel2);
                                                        BPanel.add(Jpanel3);
                                                        JButton Them = new JButton("Thêm");
                                                        Them.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == Them) {
                                                                    if (JtextField.getText().equals("") || JtextField1.getText().equals("") ||
                                                                            JtextField2.getText().equals("") || JtextField3.getText().equals("")) {
                                                                        JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                                                                    } else {
                                                                        String tenhk = JtextField.getText();
                                                                        int namhoc = Integer.parseInt(JtextField1.getText());
                                                                        StringTokenizer tokenizer = new StringTokenizer(JtextField2.getText(), "/");
                                                                        Vector Date = new Vector();
                                                                        while (tokenizer.hasMoreTokens()) {
                                                                            Date.add(tokenizer.nextToken());
                                                                        }
                                                                        int day = Integer.parseInt((String) Date.get(0));
                                                                        int month = Integer.parseInt((String) Date.get(1));
                                                                        int year = Integer.parseInt((String) Date.get(2));
                                                                        DateTime start = new DateTime(day, month, year);
                                                                        StringTokenizer tokenizer2 = new StringTokenizer(JtextField3.getText(), "/");
                                                                        Vector Date2 = new Vector();
                                                                        while (tokenizer2.hasMoreTokens()) {
                                                                            Date2.add(tokenizer2.nextToken());
                                                                        }
                                                                        int day2 = Integer.parseInt((String) Date2.get(0));
                                                                        int month2 = Integer.parseInt((String) Date2.get(1));
                                                                        int year2 = Integer.parseInt((String) Date2.get(2));
                                                                        DateTime end = new DateTime(day2, month2, year2);
                                                                        boolean kt = false;
                                                                        for (int jj = 0; jj < finalSemesterList1.size(); jj++) {
                                                                            if (namhoc == finalSemesterList1.get(jj).getYear() && start == finalSemesterList1.get(jj).getNgayBD() && end == finalSemesterList1.get(jj).getNgayKT()) {
                                                                                kt = true;
                                                                                JOptionPane.showMessageDialog(null, "Học kỳ đã tồn tại");
                                                                            }
                                                                        }
                                                                        if (kt == false) {
                                                                            Semester newSem = new Semester(tenhk, namhoc, start, end);
                                                                            finalSemesterList1.add(newSem);
                                                                            try {
                                                                                LamTrangFile("DuLieuHocKy.txt");
                                                                                GhiFileSemester(finalSemesterList1);
                                                                                Vector dulieu = new Vector();
                                                                                for (int iii = 0; iii < finalSemesterList1.size(); iii++) {
                                                                                    Vector phu = new Vector();
                                                                                    phu.add(finalSemesterList1.get(iii).getTenHK());
                                                                                    phu.add(finalSemesterList1.get(iii).getYear());
                                                                                    phu.add(finalSemesterList1.get(iii).getNgayBD());
                                                                                    phu.add(finalSemesterList1.get(iii).getNgayKT());
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
                                            JButton delete = new JButton("Delete");
                                            delete.setIcon(new ImageIcon("delete.png"));
                                            delete.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == delete) {
                                                        if (table.getSelectedRow() < 0) {
                                                            JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần xóa!");
                                                        } else {
                                                            int position = table.getSelectedRow();
                                                            finalSemesterList2.remove(position);
                                                            Vector List = new Vector();
                                                            for (int zzz = 0; zzz < finalSemesterList2.size(); zzz++) {
                                                                Vector tm = new Vector();
                                                                tm.add(finalSemesterList2.get(zzz).getTenHK());
                                                                tm.add(finalSemesterList2.get(zzz).getYear());
                                                                tm.add(finalSemesterList2.get(zzz).getNgayBD());
                                                                tm.add(finalSemesterList2.get(zzz).getNgayKT());
                                                                List.add(tm);
                                                            }
                                                            table.setModel(new DefaultTableModel(List, Header));
                                                            try {
                                                                LamTrangFile("DuLieuHocKy.txt");
                                                                GhiFileSemester(finalSemesterList2);
                                                            } catch (IOException ioException) {
                                                                ioException.printStackTrace();
                                                            }
                                                        }
                                                    }
                                                }
                                            });
                                            JPanel southPanel = new JPanel();
                                            southPanel.add(Set);
                                            southPanel.add(add);
                                            southPanel.add(delete);
                                            centerPanel.add(sp);
                                            listFrame.add(listlabel, BorderLayout.NORTH);
                                            listFrame.add(centerPanel, BorderLayout.CENTER);
                                            listFrame.add(southPanel, BorderLayout.SOUTH);
                                            listFrame.pack();
                                        }
                                    }
                                });
                                mainPanel.add(Subject);
                                mainPanel.add(semester);
                                JButton Lop = new JButton("Class");
                                Lop.setIcon(new ImageIcon("class.png"));
                                Lop.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == Lop) {
                                            JFrame listFrame = new JFrame();
                                            listFrame.setTitle("Danh sách lớp");
                                            listFrame.setIconImage(new ImageIcon("class.png").getImage());

                                            listFrame.setSize(700, 700);
                                            listFrame.setVisible(true);
                                            BorderLayout borderLayout1 = new BorderLayout();
                                            listFrame.setLayout(borderLayout1);
                                            JPanel centerPanel = new JPanel();
                                            JLabel listlabel = new JLabel("List Classes");
                                            listlabel.setHorizontalAlignment(0);
                                            Vector Header = new Vector();
                                            Header.add("Tên lớp");
                                            Header.add("Số lượng sinh viên");
                                            Header.add("Số lượng nam");
                                            Header.add("Số lượng nữ");
                                            Vector data = new Vector();
                                            for (int ii = 0; ii < finalClassList.size(); ii++) {
                                                Vector temp = new Vector();
                                                temp.add(finalClassList.get(ii).getTenLop());
                                                temp.add(finalClassList.get(ii).getSLSV());
                                                temp.add(finalClassList.get(ii).getSLNam());
                                                temp.add(finalClassList.get(ii).getSLNu());
                                                data.add(temp);
                                            }
                                            JTable table = new JTable(data, Header);
                                            JScrollPane sp = new JScrollPane(table);
                                            JButton add = new JButton("Add");
                                            add.setIcon(new ImageIcon("rsz_add.png"));
                                            add.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == add) {
                                                        JFrame Temp = new JFrame();
                                                        Temp.setSize(300, 300);
                                                        Temp.setVisible(true);
                                                        Temp.setIconImage(new ImageIcon("rsz_add.png").getImage());
                                                        Temp.setTitle("Add");
                                                        JPanel Jpanel = new JPanel();
                                                        JLabel Jlabel = new JLabel("Tên lớp:");
                                                        Jpanel.setLayout(new FlowLayout());
                                                        JTextField JtextField = new JTextField();
                                                        JtextField.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel.add(Jlabel);
                                                        Jpanel.add(JtextField);
                                                        JPanel Jpanel1 = new JPanel();
                                                        JLabel Jlabel1 = new JLabel("Số lượng sinh viên:");
                                                        Jpanel1.setLayout(new FlowLayout());
                                                        JTextField JtextField1 = new JTextField();
                                                        JtextField1.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel1.add(Jlabel1);
                                                        Jpanel1.add(JtextField1);
                                                        JPanel Jpanel2 = new JPanel();
                                                        JLabel Jlabel2 = new JLabel("Số lượng nam:");
                                                        Jpanel2.setLayout(new FlowLayout());
                                                        JTextField JtextField2 = new JTextField();
                                                        JtextField2.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel2.add(Jlabel2);
                                                        Jpanel2.add(JtextField2);
                                                        JPanel Jpanel3 = new JPanel();
                                                        JLabel Jlabel3 = new JLabel("Số lượng nữ:");
                                                        Jpanel3.setLayout(new FlowLayout());
                                                        JTextField JtextField3 = new JTextField();
                                                        JtextField3.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel3.add(Jlabel3);
                                                        Jpanel3.add(JtextField3);
                                                        JPanel BPanel = new JPanel();
                                                        BoxLayout boxLayout1 = new BoxLayout(BPanel, BoxLayout.Y_AXIS);
                                                        BPanel.setLayout(boxLayout1);
                                                        BPanel.add(Jpanel);
                                                        BPanel.add(Jpanel1);
                                                        BPanel.add(Jpanel2);
                                                        BPanel.add(Jpanel3);
                                                        JButton Them = new JButton("Thêm");
                                                        Them.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == Them) {
                                                                    if (JtextField.getText().equals("") || JtextField1.getText().equals("")) {
                                                                        JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                                                                    } else {
                                                                        String tenlop = JtextField.getText();
                                                                        int sl = Integer.parseInt(JtextField1.getText());
                                                                        int slnam = Integer.parseInt(JtextField2.getText());
                                                                        int slnu = Integer.parseInt(JtextField3.getText());
                                                                        boolean kt = false;
                                                                        for (int jj = 0; jj < finalClassList1.size(); jj++) {
                                                                            if (tenlop.equals(finalClassList1.get(jj).getTenLop())) {
                                                                                kt = true;
                                                                                JOptionPane.showMessageDialog(null, "Lớp đã tồn tại!");
                                                                            }
                                                                        }
                                                                        if (kt == false) {
                                                                            Class newClass = new Class(tenlop, sl, slnam, slnu);
                                                                            finalClassList1.add(newClass);
                                                                            try {
                                                                                LamTrangFile("DuLieuLop.txt");
                                                                                GhiFileLop(finalClassList1);
                                                                                Vector dulieu = new Vector();
                                                                                for (int iii = 0; iii < finalClassList1.size(); iii++) {
                                                                                    Vector phu = new Vector();
                                                                                    phu.add(finalClassList1.get(iii).getTenLop());
                                                                                    phu.add(finalClassList1.get(iii).getSLSV());
                                                                                    phu.add(finalClassList1.get(iii).getSLNam());
                                                                                    phu.add(finalClassList1.get(iii).getSLNu());
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
                                            JButton delete = new JButton("Delete");
                                            delete.setIcon(new ImageIcon("delete.png"));
                                            delete.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == delete) {
                                                        if (table.getSelectedRow() < 0) {
                                                            JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần xóa!");
                                                        } else {
                                                            int position = table.getSelectedRow();
                                                            finalClassList2.remove(position);
                                                            Vector List = new Vector();
                                                            for (int zzz = 0; zzz < finalClassList2.size(); zzz++) {
                                                                Vector tm = new Vector();
                                                                tm.add(finalClassList2.get(zzz).getTenLop());
                                                                tm.add(finalClassList2.get(zzz).getSLSV());
                                                                tm.add(finalClassList2.get(zzz).getSLNam());
                                                                tm.add(finalClassList2.get(zzz).getSLNu());
                                                                List.add(tm);
                                                            }
                                                            table.setModel(new DefaultTableModel(List, Header));
                                                            try {
                                                                LamTrangFile("DuLieuLop.txt");
                                                                GhiFileLop(finalClassList2);
                                                            } catch (IOException ioException) {
                                                                ioException.printStackTrace();
                                                            }
                                                        }
                                                    }
                                                }
                                            });
                                            JPanel southPanel = new JPanel();
                                            southPanel.add(add);
                                            southPanel.add(delete);
                                            centerPanel.add(sp);
                                            listFrame.add(listlabel, BorderLayout.NORTH);
                                            listFrame.add(centerPanel, BorderLayout.CENTER);
                                            listFrame.add(southPanel, BorderLayout.SOUTH);
                                            listFrame.pack();
                                        }
                                    }
                                });
                                JButton student = new JButton("Danh sách sinh viên");
                                student.setIcon(new ImageIcon("list_student.png"));
                                student.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == student) {
                                            JFrame listFrame = new JFrame();
                                            listFrame.setTitle("Danh sách sinh viên");
                                            listFrame.setIconImage(new ImageIcon("list_student.png").getImage());
                                            listFrame.setSize(700, 700);
                                            listFrame.setVisible(true);
                                            JButton Search = new JButton("Search");
                                            Search.setIcon(new ImageIcon("lupe.png"));
                                            BorderLayout borderLayout1 = new BorderLayout();
                                            listFrame.setLayout(borderLayout1);
                                            JPanel centerPanel = new JPanel();
                                            JLabel listlabel = new JLabel("Danh sách sinh viên");
                                            listlabel.setHorizontalAlignment(0);
                                            Vector Header = new Vector();
                                            Header.add("Mã sinh viên");
                                            Header.add("Tên sinh viên");
                                            Header.add("Điểm");
                                            Header.add("Địa chỉ");
                                            Header.add("Lớp");
                                            Header.add("Hình ảnh");
                                            Vector data = new Vector();
                                            for (int ii = 0; ii < finalStdList.size(); ii++) {
                                                Vector temp = new Vector();
                                                temp.add(finalStdList.get(ii).getMHS());
                                                temp.add(finalStdList.get(ii).getTenHS());
                                                temp.add(finalStdList.get(ii).getDiem());
                                                temp.add(finalStdList.get(ii).getDiaChi());
                                                temp.add(finalStdList.get(ii).getLop());
                                                temp.add(finalStdList.get(ii).getHinhanh());
                                                data.add(temp);
                                            }
                                            JTable table = new JTable(data, Header);
                                            JScrollPane sp = new JScrollPane(table);
                                            Search.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == Search) {
                                                        JFrame temp = new JFrame();
                                                        temp.setSize(300, 300);
                                                        temp.setVisible(true);
                                                        temp.setIconImage(new ImageIcon("lupe.png").getImage());
                                                        temp.setTitle("Search");
                                                        JPanel panel = new JPanel();
                                                        JLabel label = new JLabel("Mã sinh viên:");
                                                        panel.setLayout(new FlowLayout());
                                                        JTextField jTextField = new JTextField();
                                                        jTextField.setPreferredSize(new Dimension(300, 30));
                                                        panel.add(label);
                                                        panel.add(jTextField);
                                                        JPanel bigPanel = new JPanel();
                                                        bigPanel.add(panel);
                                                        JButton but = new JButton("Search");
                                                        but.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == but) {
                                                                    int ma = Integer.parseInt(jTextField.getText());
                                                                    boolean check = false;
                                                                    for (int i = 0; i < finalStdList2.size(); i++) {
                                                                        if (ma == finalStdList2.get(i).getMHS()) {
                                                                            check = true;
                                                                            Vector DuLieu = new Vector();
                                                                            Vector acc = new Vector();
                                                                            acc.add(finalStdList2.get(i).getMHS());
                                                                            acc.add(finalStdList2.get(i).getTenHS());
                                                                            acc.add(finalStdList2.get(i).getDiem());
                                                                            acc.add(finalStdList2.get(i).getDiaChi());
                                                                            acc.add(finalStdList2.get(i).getLop());
                                                                            acc.add(finalStdList2.get(i).getHinhanh());
                                                                            DuLieu.add(acc);
                                                                            table.setModel(new DefaultTableModel(DuLieu, Header));
                                                                            break;
                                                                        }
                                                                    }
                                                                    if (check == false) {
                                                                        JOptionPane.showMessageDialog(null, "Không tìm thấy account!");
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
                                            JButton add = new JButton("Add");
                                            add.setIcon(new ImageIcon("rsz_add.png"));
                                            add.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == add) {
                                                        JFrame Temp = new JFrame();
                                                        Temp.setSize(300, 300);
                                                        Temp.setVisible(true);
                                                        Temp.setIconImage(new ImageIcon("rsz_add.png").getImage());
                                                        Temp.setTitle("Add");
                                                        JPanel Jpanel = new JPanel();
                                                        JLabel Jlabel = new JLabel("Mã sinh viên:");
                                                        Jpanel.setLayout(new FlowLayout());
                                                        JTextField JtextField = new JTextField();
                                                        JtextField.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel.add(Jlabel);
                                                        Jpanel.add(JtextField);
                                                        JPanel Jpanel1 = new JPanel();
                                                        JLabel Jlabel1 = new JLabel("Tên sinh viên:");
                                                        Jpanel1.setLayout(new FlowLayout());
                                                        JTextField JtextField1 = new JTextField();
                                                        JtextField1.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel1.add(Jlabel1);
                                                        Jpanel1.add(JtextField1);
                                                        JPanel Jpanel2 = new JPanel();
                                                        JLabel Jlabel2 = new JLabel("Điểm:");
                                                        Jpanel2.setLayout(new FlowLayout());
                                                        JTextField JtextField2 = new JTextField();
                                                        JtextField2.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel2.add(Jlabel2);
                                                        Jpanel2.add(JtextField2);
                                                        JPanel Jpanel3 = new JPanel();
                                                        JLabel Jlabel3 = new JLabel("Địa chỉ:");
                                                        Jpanel3.setLayout(new FlowLayout());
                                                        JTextField JtextField3 = new JTextField();
                                                        JtextField3.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel3.add(Jlabel3);
                                                        Jpanel3.add(JtextField3);
                                                        JPanel Jpanel4 = new JPanel();
                                                        JLabel Jlabel4 = new JLabel("Lớp:");
                                                        Jpanel4.setLayout(new FlowLayout());
                                                        JTextField JtextField4 = new JTextField();
                                                        JtextField4.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel4.add(Jlabel4);
                                                        Jpanel4.add(JtextField4);
                                                        JPanel Jpanel5 = new JPanel();
                                                        JLabel Jlabel5 = new JLabel("Hình ảnh:");
                                                        Jpanel5.setLayout(new FlowLayout());
                                                        JTextField JtextField5 = new JTextField();
                                                        JtextField5.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel5.add(Jlabel5);
                                                        JButton button1 = new JButton("Chọn ảnh");
                                                        button1.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == button1) {
                                                                    JFileChooser fileChooser = new JFileChooser();
                                                                    int response = fileChooser.showOpenDialog(null);
                                                                    if (response == JFileChooser.APPROVE_OPTION) {
                                                                        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                                                                        String path = String.valueOf(file);
                                                                        JtextField5.setText(path);
                                                                    }
                                                                }
                                                            }
                                                        });
                                                        Jpanel5.add(button1);
                                                        Jpanel5.add(JtextField5);
                                                        JPanel BPanel = new JPanel();
                                                        BoxLayout boxLayout1 = new BoxLayout(BPanel, BoxLayout.Y_AXIS);
                                                        BPanel.setLayout(boxLayout1);
                                                        BPanel.add(Jpanel);
                                                        BPanel.add(Jpanel1);
                                                        BPanel.add(Jpanel2);
                                                        BPanel.add(Jpanel3);
                                                        BPanel.add(Jpanel4);
                                                        BPanel.add(Jpanel5);
                                                        JButton Them = new JButton("Thêm");
                                                        Them.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == Them) {
                                                                    if (JtextField.getText().equals("") || JtextField1.getText().equals("")
                                                                            || JtextField2.getText().equals("") || JtextField3.getText().equals("") ||
                                                                            JtextField4.getText().equals("") || JtextField5.getText().equals("")) {
                                                                        JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                                                                    } else {
                                                                        int ma = Integer.parseInt(JtextField.getText());
                                                                        String ten = JtextField1.getText();
                                                                        float diem = Float.parseFloat(JtextField2.getText());
                                                                        String diachi = JtextField3.getText();
                                                                        String lop = JtextField4.getText();
                                                                        String hinhanh = JtextField5.getText();
                                                                        boolean kt = false;
                                                                        for (int jj = 0; jj < finalStdList3.size(); jj++) {
                                                                            if (ma == finalStdList3.get(jj).getMHS()) {
                                                                                kt = true;
                                                                                JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại");
                                                                            }
                                                                        }
                                                                        if (kt == false) {
                                                                            HocSinh hocSinh = new HocSinh(ma, ten, diem, hinhanh, diachi, lop);
                                                                            finalStdList3.add(hocSinh);
                                                                            for (int q = 0; q < finalClassList4.size(); q++) {
                                                                                if (lop.equals(finalClassList4.get(q).getTenLop())) {
                                                                                    Vector<HocSinh> cllist = finalClassList4.get(q).getSVList();
                                                                                    cllist.add(hocSinh);
                                                                                    finalClassList4.get(q).setSVList(cllist);
                                                                                }
                                                                            }
                                                                            try {
                                                                                LamTrangFile("DuLieuHS.txt");
                                                                                GhiFileStd(finalStdList3);
                                                                                Vector dulieu = new Vector();
                                                                                for (int iii = 0; iii < finalStdList3.size(); iii++) {
                                                                                    Vector phu = new Vector();
                                                                                    phu.add(finalStdList3.get(iii).getMHS());
                                                                                    phu.add(finalStdList3.get(iii).getTenHS());
                                                                                    phu.add(finalStdList3.get(iii).getDiem());
                                                                                    phu.add(finalStdList3.get(iii).getDiaChi());
                                                                                    phu.add(finalStdList3.get(iii).getLop());
                                                                                    phu.add(finalStdList3.get(iii).getHinhanh());
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
                                            JButton Update = new JButton("Update");
                                            Update.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == Update) {
                                                        if (table.getSelectedRow() < 0) {
                                                            JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần update!");
                                                        } else {
                                                            int pos = table.getSelectedRow();
                                                            HocSinh temp = (HocSinh) finalStdList4.get(pos);
                                                            JFrame jFrame1 = new JFrame();
                                                            jFrame1.setTitle("Update");
                                                            ImageIcon Icon = new ImageIcon("update.png");
                                                            jFrame1.setIconImage(Icon.getImage());
                                                            jFrame1.setSize(520, 450);
                                                            jFrame1.setResizable(false);
                                                            jFrame1.setVisible(true);
                                                            JPanel panel = new JPanel();
                                                            JLabel label1 = new JLabel();
                                                            HocSinh hs = new HocSinh();
                                                            label1.setText("Mã sinh viên:");
                                                            JTextField textField = new JTextField();
                                                            textField.setText(String.valueOf(temp.getMHS()));
                                                            if (textField.getText().equals("0")) {
                                                                textField.setText("");
                                                            }
                                                            textField.setPreferredSize(new Dimension(500, 30));
                                                            panel.add(label1);
                                                            panel.add(textField);
                                                            JPanel panel1 = new JPanel();
                                                            JLabel label2 = new JLabel();
                                                            label2.setText("Tên sinh viên:");
                                                            JTextField textField1 = new JTextField();
                                                            textField1.setText(temp.getTenHS());
                                                            textField1.setPreferredSize(new Dimension(500, 30));
                                                            panel1.add(label2);
                                                            panel1.add(textField1);
                                                            JPanel panel2 = new JPanel();
                                                            JLabel label3 = new JLabel();
                                                            label3.setText("Điểm:");
                                                            JTextField textField2 = new JTextField();
                                                            textField2.setText(String.valueOf(temp.getDiem()));
                                                            if (textField2.getText().equals("0")) {
                                                                textField2.setText("");
                                                            }
                                                            textField2.setPreferredSize(new Dimension(500, 30));
                                                            panel2.add(label3);
                                                            panel2.add(textField2);
                                                            JPanel panel3 = new JPanel();
                                                            JLabel label4 = new JLabel();
                                                            label4.setText("Địa chỉ:");
                                                            JTextField textField3 = new JTextField();
                                                            textField3.setText(temp.getDiaChi());
                                                            textField3.setPreferredSize(new Dimension(500, 30));
                                                            panel3.add(label4);
                                                            panel3.add(textField3);
                                                            JPanel panel4 = new JPanel();
                                                            JLabel label5 = new JLabel();
                                                            label5.setText("Lớp:");
                                                            JTextField textField4 = new JTextField();
                                                            textField4.setText(temp.getLop());
                                                            textField4.setPreferredSize(new Dimension(500, 30));
                                                            panel4.add(label5);
                                                            panel4.add(textField4);
                                                            JPanel panel5 = new JPanel();
                                                            JLabel label6 = new JLabel();
                                                            label6.setText("Hình ảnh:");
                                                            panel5.add(label6);
                                                            JButton fileBut = new JButton("Chọn ảnh");
                                                            panel5.add(fileBut);
                                                            JTextField textField5 = new JTextField();
                                                            textField5.setText(temp.getHinhanh());
                                                            textField5.setPreferredSize(new Dimension(500, 30));
                                                            panel5.add(textField5);
                                                            fileBut.setVisible(false);
                                                            fileBut.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (e.getSource() == fileBut) {
                                                                        JFileChooser fileChooser = new JFileChooser();
                                                                        int response = fileChooser.showOpenDialog(null);
                                                                        if (response == JFileChooser.APPROVE_OPTION) {
                                                                            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                                                                            String path = String.valueOf(file);
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
                                                            JPanel mainPanel = new JPanel();
                                                            BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
                                                            mainPanel.setLayout(boxLayout);
                                                            mainPanel.add(panel);
                                                            mainPanel.add(panel1);
                                                            mainPanel.add(panel2);
                                                            mainPanel.add(panel3);
                                                            mainPanel.add(panel4);
                                                            mainPanel.add(panel5);
                                                            jFrame1.add(mainPanel);
                                                            JPanel butPanel = new JPanel();
                                                            JButton but1 = new JButton("Sửa thông tin");
                                                            but1.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (e.getSource() == but1) {
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
                                                            JButton saveBut = new JButton("Lưu");
                                                            saveBut.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if (e.getSource() == saveBut) {
                                                                        if (textField.getText().equals("") || textField1.getText().equals("") || textField2.getText().equals("")
                                                                                || textField3.getText().equals("") || textField4.getText().equals("") || textField5.getText().equals("")) {
                                                                            JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin!");
                                                                        } else {
                                                                            JOptionPane.showMessageDialog(null, "Đã lưu lại thông tin!");
                                                                            textField.setFocusable(false);
                                                                            textField1.setFocusable(false);
                                                                            textField2.setFocusable(false);
                                                                            textField3.setFocusable(false);
                                                                            textField4.setFocusable(false);
                                                                            textField5.setFocusable(false);
                                                                            fileBut.setVisible(false);
                                                                            finalStdList5.remove(pos);
                                                                            hs.setMHS(Integer.parseInt(textField.getText()));
                                                                            hs.setTenHS(textField1.getText());
                                                                            hs.setDiem(Float.parseFloat(textField2.getText()));
                                                                            hs.setLop(textField3.getText());
                                                                            hs.setDiaChi(textField4.getText());
                                                                            hs.setHinhanh(textField5.getText());
                                                                            finalStdList5.add(pos, hs);
                                                                            try {
                                                                                LamTrangFile("DuLieuHS.txt");
                                                                                LamTrangFile("DuLieuLop.txt");
                                                                                GhiFileLop(finalClassList4);
                                                                                GhiFileStd(finalStdList5);
                                                                            } catch (IOException ioException) {
                                                                                ioException.printStackTrace();
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            });
                                                            FlowLayout flowLayout = new FlowLayout();
                                                            butPanel.setLayout(flowLayout);
                                                            butPanel.add(but1);
                                                            butPanel.add(saveBut);
                                                            mainPanel.add(butPanel);
                                                        }
                                                    }
                                                }
                                            });
                                            Update.setIcon(new ImageIcon("update.png"));
                                            JButton reset = new JButton("Reset Password");
                                            reset.setIcon(new ImageIcon("reset.png"));
                                            reset.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == reset) {
                                                        if (table.getSelectedRow() < 0) {
                                                            JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần reset password");
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "Reset password thành công!");
                                                            JOptionPane.showMessageDialog(null, "Đã reset username và password giống với mã sinh viên!");
                                                            int vitri = table.getSelectedRow();
                                                            HocSinh tempHS = (HocSinh) finalStdList6.get(vitri);
                                                            finalStdList6.remove(vitri);
                                                            tempHS.setAccount(new Account(String.valueOf(tempHS.getMHS()),String.valueOf(tempHS.getMHS())));
                                                            finalStdList6.add(vitri, tempHS);
                                                            Vector list = new Vector();
                                                            for (int zz = 0; zz < finalStdList6.size(); zz++) {
                                                                Vector tmp = new Vector();
                                                                tmp.add(finalStdList6.get(zz).getMHS());
                                                                tmp.add(finalStdList6.get(zz).getTenHS());
                                                                tmp.add(finalStdList6.get(zz).getDiem());
                                                                tmp.add(finalStdList6.get(zz).getDiaChi());
                                                                tmp.add(finalStdList6.get(zz).getLop());
                                                                tmp.add(finalStdList6.get(zz).getHinhanh());
                                                                list.add(tmp);
                                                            }
                                                            table.setModel(new DefaultTableModel(list, Header));
                                                            finalAccounts6.add(new Account(tempHS.getAccount().getKey(), tempHS.getAccount().getValue()));
                                                            try {
                                                                LamTrangFile("DuLieuHS.txt");
                                                                LamTrangFile("DuLieuAccount.txt");
                                                                GhiFileStd(finalStdList6);
                                                                GhiFile2(finalAccounts6);
                                                            } catch (IOException ioException) {
                                                                ioException.printStackTrace();
                                                            }

                                                        }
                                                    }
                                                }
                                            });
                                            JButton register = new JButton("Môn đã đăng ký");
                                            register.setIcon(new ImageIcon("subject.png"));
                                            register.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == register) {
                                                        if (table.getSelectedRow() < 0) {
                                                            JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần xem!");
                                                        } else {
                                                            int posit = table.getSelectedRow();
                                                            JFrame subFrame = new JFrame();
                                                            subFrame.setIconImage(new ImageIcon("subject.png").getImage());
                                                            subFrame.setSize(700, 700);
                                                            subFrame.setVisible(true);
                                                            subFrame.setTitle("Môn đã đăng ký");
                                                            JPanel bigPan = new JPanel();
                                                            BorderLayout borderLayout2 = new BorderLayout();
                                                            bigPan.setLayout(borderLayout2);
                                                            JLabel label = new JLabel("Danh sách các môn đã đăng ký");
                                                            label.setHorizontalAlignment(0);
                                                            JPanel center = new JPanel();
                                                            Vector Head = new Vector();
                                                            Head.add("Mã môn học");
                                                            Head.add("Tên môn học");
                                                            Head.add("Số tín chỉ");
                                                            Vector dataOfStd = new Vector();
                                                            Vector<Subject> datatemp = finalStdList7.get(posit).getListSub();
                                                            int size;
                                                            if (datatemp == null) {
                                                                size = 0;
                                                            } else {
                                                                size = datatemp.size();
                                                            }
                                                            for (int d = 0; d < size; d++) {
                                                                Vector phu = new Vector();
                                                                phu.add(datatemp.get(d).getMaMH());
                                                                phu.add(datatemp.get(d).getTenMH());
                                                                phu.add(datatemp.get(d).getTinChi());
                                                                dataOfStd.add(phu);
                                                            }
                                                            JTable tab = new JTable(dataOfStd, Head);
                                                            JScrollPane sp = new JScrollPane(tab);
                                                            bigPan.add(label, BorderLayout.NORTH);
                                                            bigPan.add(sp, BorderLayout.CENTER);
                                                            subFrame.add(bigPan);
                                                        }
                                                    }
                                                }
                                            });
                                            JPanel southPanel = new JPanel();
                                            southPanel.add(Search);
                                            southPanel.add(add);
                                            southPanel.add(Update);
                                            southPanel.add(reset);
                                            southPanel.add(register);
                                            String[] nameclass = new String[finalClassList3.size()];
                                            for (int y = 0; y < finalClassList3.size(); y++) {
                                                nameclass[y] = "";
                                            }
                                            for (int i1 = 0; i1 < finalClassList3.size(); i1++) {
                                                nameclass[i1] += finalClassList3.get(i1).getTenLop();
                                            }
                                            JComboBox<String> chooseClass = new JComboBox<>(nameclass);
                                            chooseClass.addItemListener(new ItemListener() {
                                                @Override
                                                public void itemStateChanged(ItemEvent e) {
                                                    if (e.getStateChange() == ItemEvent.SELECTED) {
                                                        Vector DuLieu = new Vector();
                                                        for (int i = 0; i < finalStdList1.size(); i++) {
                                                            if (e.getItem().toString().equals(finalStdList1.get(i).getLop())) {
                                                                Vector acc = new Vector();
                                                                acc.add(finalStdList1.get(i).getMHS());
                                                                acc.add(finalStdList1.get(i).getTenHS());
                                                                acc.add(finalStdList1.get(i).getDiem());
                                                                acc.add(finalStdList1.get(i).getDiaChi());
                                                                acc.add(finalStdList1.get(i).getLop());
                                                                acc.add(finalStdList1.get(i).getHinhanh());
                                                                DuLieu.add(acc);
                                                            }
                                                        }
                                                        table.setModel(new DefaultTableModel(DuLieu, Header));
                                                    }
                                                }
                                            });
                                            centerPanel.add(chooseClass);
                                            centerPanel.add(sp);
                                            listFrame.add(listlabel, BorderLayout.NORTH);
                                            listFrame.add(centerPanel, BorderLayout.CENTER);
                                            listFrame.add(southPanel, BorderLayout.SOUTH);
                                            listFrame.pack();
                                        }
                                    }
                                });
                                JButton session = new JButton("Course Registration Session");
                                session.setIcon(new ImageIcon("session.png"));
                                session.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == session) {
                                            JFrame listFrame = new JFrame();
                                            listFrame.setTitle("Danh sách các kỳ đăng ký học phần");
                                            listFrame.setIconImage(new ImageIcon("session.png").getImage());

                                            listFrame.setSize(700, 700);
                                            listFrame.setVisible(true);
                                            BorderLayout borderLayout1 = new BorderLayout();
                                            listFrame.setLayout(borderLayout1);
                                            JPanel centerPanel = new JPanel();
                                            JLabel listlabel = new JLabel("Course Registration Session");
                                            listlabel.setHorizontalAlignment(0);
                                            Vector Header = new Vector();
                                            Header.add("Tên kỳ");
                                            Header.add("Ngày bắt đầu");
                                            Header.add("Ngày kết thúc");
                                            Vector data = new Vector();
                                            for (int ii = 0; ii < finalSessionList.size(); ii++) {
                                                Vector temp = new Vector();
                                                temp.add(finalSessionList.get(ii).getName());
                                                temp.add(finalSessionList.get(ii).getStart());
                                                temp.add(finalSessionList.get(ii).getEnd());
                                                data.add(temp);
                                            }
                                            JTable table = new JTable(data, Header);
                                            JScrollPane sp = new JScrollPane(table);
                                            JButton add = new JButton("Add");
                                            add.setIcon(new ImageIcon("rsz_add.png"));
                                            add.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == add) {
                                                        JFrame Temp = new JFrame();
                                                        Temp.setSize(300, 300);
                                                        Temp.setVisible(true);
                                                        Temp.setIconImage(new ImageIcon("rsz_add.png").getImage());
                                                        Temp.setTitle("Add");
                                                        JPanel Jpanel = new JPanel();
                                                        JLabel Jlabel = new JLabel("Tên kỳ đăng ký HP:");
                                                        Jpanel.setLayout(new FlowLayout());
                                                        JTextField JtextField = new JTextField();
                                                        JtextField.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel.add(Jlabel);
                                                        Jpanel.add(JtextField);
                                                        JPanel Jpanel2 = new JPanel();
                                                        JLabel Jlabel2 = new JLabel("Ngày bắt đầu(dd/mm/yyyy):");
                                                        Jpanel2.setLayout(new FlowLayout());
                                                        JTextField JtextField2 = new JTextField();
                                                        JtextField2.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel2.add(Jlabel2);
                                                        Jpanel2.add(JtextField2);
                                                        JPanel Jpanel3 = new JPanel();
                                                        JLabel Jlabel3 = new JLabel("Ngày kết thúc(dd/mm/yyyy):");
                                                        Jpanel3.setLayout(new FlowLayout());
                                                        JTextField JtextField3 = new JTextField();
                                                        JtextField3.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel3.add(Jlabel3);
                                                        Jpanel3.add(JtextField3);
                                                        JPanel BPanel = new JPanel();
                                                        BoxLayout boxLayout1 = new BoxLayout(BPanel, BoxLayout.Y_AXIS);
                                                        BPanel.setLayout(boxLayout1);
                                                        BPanel.add(Jpanel);
                                                        BPanel.add(Jpanel2);
                                                        BPanel.add(Jpanel3);
                                                        JButton Them = new JButton("Thêm");
                                                        Them.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == Them) {
                                                                    if (JtextField.getText().equals("") || JtextField2.getText().equals("")
                                                                            || JtextField3.getText().equals("")) {
                                                                        JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                                                                    } else {
                                                                        String tenhk = JtextField.getText();
                                                                        StringTokenizer tokenizer = new StringTokenizer(JtextField2.getText(), "/");
                                                                        Vector Date = new Vector();
                                                                        while (tokenizer.hasMoreTokens()) {
                                                                            Date.add(tokenizer.nextToken());
                                                                        }
                                                                        int day = Integer.parseInt((String) Date.get(0));
                                                                        int month = Integer.parseInt((String) Date.get(1));
                                                                        int year = Integer.parseInt((String) Date.get(2));
                                                                        DateTime start = new DateTime(day, month, year);
                                                                        StringTokenizer tokenizer2 = new StringTokenizer(JtextField3.getText(), "/");
                                                                        Vector Date2 = new Vector();
                                                                        while (tokenizer2.hasMoreTokens()) {
                                                                            Date2.add(tokenizer2.nextToken());
                                                                        }
                                                                        int day2 = Integer.parseInt((String) Date2.get(0));
                                                                        int month2 = Integer.parseInt((String) Date2.get(1));
                                                                        int year2 = Integer.parseInt((String) Date2.get(2));
                                                                        DateTime end = new DateTime(day2, month2, year2);
                                                                        boolean kt = false;
                                                                        for (int jj = 0; jj < finalSessionList1.size(); jj++) {
                                                                            if (tenhk.equals(finalSessionList1.get(jj).getName()) && start == finalSessionList1.get(jj).getStart()
                                                                                    && end == finalSessionList1.get(jj).getEnd()) {
                                                                                kt = true;
                                                                                JOptionPane.showMessageDialog(null, "Học kỳ đã tồn tại");
                                                                            }
                                                                        }
                                                                        if (kt == false) {
                                                                            Session newSes = new Session(tenhk, start, end);
                                                                            Vector<Session> temp = new Vector<>();
                                                                            temp.add(newSes);
                                                                            for (int x = 0; x < finalSemesterList4.size(); x++) {
                                                                                if (currentSemester[0] == finalSemesterList4.get(x)) {
                                                                                    finalSemesterList4.get(x).setDKHP(temp);
                                                                                }
                                                                            }
                                                                            finalSessionList1.add(newSes);
                                                                            try {
                                                                                LamTrangFile("DuLieuHocKy.txt");
                                                                                LamTrangFile("DuLieuSession.txt");
                                                                                GhiFileSession(finalSessionList1);
                                                                                GhiFileSemester(finalSemesterList4);
                                                                                Vector dulieu = new Vector();
                                                                                for (int iii = 0; iii < finalSessionList1.size(); iii++) {
                                                                                    Vector phu = new Vector();
                                                                                    phu.add(finalSessionList1.get(iii).getName());
                                                                                    phu.add(finalSessionList1.get(iii).getStart());
                                                                                    phu.add(finalSessionList1.get(iii).getEnd());
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
                                            JPanel southPanel = new JPanel();
                                            southPanel.add(add);
                                            centerPanel.add(sp);
                                            listFrame.add(listlabel, BorderLayout.NORTH);
                                            listFrame.add(centerPanel, BorderLayout.CENTER);
                                            listFrame.add(southPanel, BorderLayout.SOUTH);
                                            listFrame.pack();
                                        }
                                    }
                                });
                                JButton course = new JButton("Course");
                                course.setIcon(new ImageIcon("course.png"));
                                course.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == course) {
                                            JFrame listFrame = new JFrame();
                                            listFrame.setTitle("Danh sách học phần");
                                            listFrame.setIconImage(new ImageIcon("course.png").getImage());

                                            listFrame.setSize(1000, 1000);
                                            listFrame.setVisible(true);
                                            JButton Search = new JButton("Search");
                                            Search.setIcon(new ImageIcon("lupe.png"));
                                            BorderLayout borderLayout1 = new BorderLayout();
                                            listFrame.setLayout(borderLayout1);
                                            JPanel centerPanel = new JPanel();
                                            JLabel listlabel = new JLabel("List Courses");
                                            listlabel.setHorizontalAlignment(0);
                                            Vector Header = new Vector();
                                            Header.add("Mã môn học");
                                            Header.add("Tên môn học");
                                            Header.add("Số tín chỉ");
                                            Header.add("Giáo viên");
                                            Header.add("Phòng học");
                                            Header.add("Ngày học");
                                            Header.add("Ca");
                                            Header.add("Số lượng");
                                            Vector data = new Vector();
                                            for (int ii = 0; ii < finalCourseList.size(); ii++) {
                                                Vector temp = new Vector();
                                                temp.add(finalCourseList.get(ii).getMaMon());
                                                temp.add(finalCourseList.get(ii).getTenMon());
                                                temp.add(finalCourseList.get(ii).getSoTinChi());
                                                temp.add(finalCourseList.get(ii).getGV());
                                                temp.add(finalCourseList.get(ii).getPhongHoc());
                                                temp.add(finalCourseList.get(ii).getDayofWeek());
                                                temp.add(finalCourseList.get(ii).getCa());
                                                temp.add(finalCourseList.get(ii).getMaxSlot());
                                                data.add(temp);
                                            }
                                            JTable table = new JTable(data, Header);
                                            JScrollPane sp = new JScrollPane(table);
                                            Search.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == Search) {
                                                        JFrame temp = new JFrame();
                                                        temp.setSize(300, 300);
                                                        temp.setVisible(true);
                                                        temp.setIconImage(new ImageIcon("lupe.png").getImage());
                                                        temp.setTitle("Search");
                                                        JPanel panel = new JPanel();
                                                        JLabel label = new JLabel("Tên môn học:");
                                                        panel.setLayout(new FlowLayout());
                                                        JTextField jTextField = new JTextField();
                                                        jTextField.setPreferredSize(new Dimension(300, 30));
                                                        panel.add(label);
                                                        panel.add(jTextField);
                                                        JPanel bigPanel = new JPanel();
                                                        bigPanel.add(panel);
                                                        JButton but = new JButton("Search");
                                                        but.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == but) {
                                                                    String name = jTextField.getText();
                                                                    boolean check = false;
                                                                    Vector DuLieu = new Vector();
                                                                    for (int i = 0; i < finalCourseList1.size(); i++) {
                                                                        if (name.equals(finalCourseList1.get(i).getTenMon())) {
                                                                            check = true;
                                                                            Vector acc = new Vector();
                                                                            acc.add(finalCourseList1.get(i).getMaMon());
                                                                            acc.add(finalCourseList1.get(i).getTenMon());
                                                                            acc.add(finalCourseList1.get(i).getSoTinChi());
                                                                            acc.add(finalCourseList1.get(i).getGV());
                                                                            acc.add(finalCourseList1.get(i).getPhongHoc());
                                                                            acc.add(finalCourseList1.get(i).getDayofWeek());
                                                                            acc.add(finalCourseList1.get(i).getCa());
                                                                            acc.add(finalCourseList1.get(i).getMaxSlot());
                                                                            DuLieu.add(acc);
                                                                        }
                                                                    }
                                                                    if (check == false) {
                                                                        JOptionPane.showMessageDialog(null, "Không tìm thấy môn học!");
                                                                    } else {
                                                                        table.setModel(new DefaultTableModel(DuLieu, Header));
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
                                            JButton add = new JButton("Add");
                                            add.setIcon(new ImageIcon("rsz_add.png"));
                                            add.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == add) {
                                                        JFrame Temp = new JFrame();
                                                        Temp.setSize(300, 300);
                                                        Temp.setVisible(true);
                                                        Temp.setIconImage(new ImageIcon("rsz_add.png").getImage());
                                                        Temp.setTitle("Add");
                                                        JPanel Jpanel = new JPanel();
                                                        JLabel Jlabel = new JLabel("Mã môn học:");
                                                        Jpanel.setLayout(new FlowLayout());
                                                        JTextField JtextField = new JTextField();
                                                        JtextField.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel.add(Jlabel);
                                                        Jpanel.add(JtextField);
                                                        JPanel Jpanel1 = new JPanel();
                                                        JLabel Jlabel1 = new JLabel("Tên môn học:");
                                                        Jpanel1.setLayout(new FlowLayout());
                                                        JTextField JtextField1 = new JTextField();
                                                        JtextField1.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel1.add(Jlabel1);
                                                        Jpanel1.add(JtextField1);
                                                        JPanel Jpanel2 = new JPanel();
                                                        JLabel Jlabel2 = new JLabel("Số tín chỉ:");
                                                        Jpanel2.setLayout(new FlowLayout());
                                                        JTextField JtextField2 = new JTextField();
                                                        JtextField2.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel2.add(Jlabel2);
                                                        Jpanel2.add(JtextField2);
                                                        JPanel Jpanel3 = new JPanel();
                                                        JLabel Jlabel3 = new JLabel("Giáo viên:");
                                                        Jpanel3.setLayout(new FlowLayout());
                                                        JTextField JtextField3 = new JTextField();
                                                        JtextField3.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel3.add(Jlabel3);
                                                        Jpanel3.add(JtextField3);
                                                        JPanel Jpanel4 = new JPanel();
                                                        JLabel Jlabel4 = new JLabel("Phòng học:");
                                                        Jpanel4.setLayout(new FlowLayout());
                                                        JTextField JtextField4 = new JTextField();
                                                        JtextField4.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel4.add(Jlabel4);
                                                        Jpanel4.add(JtextField4);
                                                        JPanel Jpanel5 = new JPanel();
                                                        JLabel Jlabel5 = new JLabel("Ngày học:");
                                                        Jpanel5.setLayout(new FlowLayout());
                                                        JTextField JtextField5 = new JTextField();
                                                        JtextField5.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel5.add(Jlabel5);
                                                        Jpanel5.add(JtextField5);
                                                        JPanel Jpanel6 = new JPanel();
                                                        JLabel Jlabel6 = new JLabel("Ca:");
                                                        Jpanel6.setLayout(new FlowLayout());
                                                        JTextField JtextField6 = new JTextField();
                                                        JtextField6.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel6.add(Jlabel6);
                                                        Jpanel6.add(JtextField6);
                                                        JPanel Jpanel7 = new JPanel();
                                                        JLabel Jlabel7 = new JLabel("Số lượng tối đa:");
                                                        Jpanel7.setLayout(new FlowLayout());
                                                        JTextField JtextField7 = new JTextField();
                                                        JtextField7.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel7.add(Jlabel7);
                                                        Jpanel7.add(JtextField7);

                                                        JPanel BPanel = new JPanel();
                                                        BoxLayout boxLayout1 = new BoxLayout(BPanel, BoxLayout.Y_AXIS);
                                                        BPanel.setLayout(boxLayout1);
                                                        BPanel.add(Jpanel);
                                                        BPanel.add(Jpanel1);
                                                        BPanel.add(Jpanel2);
                                                        BPanel.add(Jpanel3);
                                                        BPanel.add(Jpanel4);
                                                        BPanel.add(Jpanel5);
                                                        BPanel.add(Jpanel6);
                                                        BPanel.add(Jpanel7);
                                                        JButton Them = new JButton("Thêm");
                                                        Them.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == Them) {
                                                                    if (JtextField.getText().equals("") || JtextField1.getText().equals("") || JtextField2.getText().equals("") ||
                                                                            JtextField3.getText().equals("") || JtextField4.getText().equals("") || JtextField5.getText().equals("") ||
                                                                            JtextField6.getText().equals("") || JtextField7.getText().equals("")) {
                                                                        JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                                                                    } else {
                                                                        String ma = JtextField.getText();
                                                                        String ten = JtextField1.getText();
                                                                        int tinchi = Integer.parseInt(JtextField2.getText());
                                                                        String gv = JtextField3.getText();
                                                                        String phong = JtextField4.getText();
                                                                        String ngayhoc = JtextField5.getText();
                                                                        int cahoc = Integer.parseInt(JtextField6.getText());
                                                                        int slot = Integer.parseInt(JtextField7.getText());
                                                                        boolean kt = false;
                                                                        for (int jj = 0; jj < finalCourseList2.size(); jj++) {
                                                                            if (ma.equals(finalCourseList2.get(jj).getMaMon())) {
                                                                                kt = true;
                                                                                JOptionPane.showMessageDialog(null, "Học phần đã tồn tại");
                                                                            }
                                                                        }
                                                                        if (kt == false) {
                                                                            Course newCourse = new Course(ma, ten, tinchi, gv, phong, ngayhoc, cahoc, slot);
                                                                            finalCourseList2.add(newCourse);
                                                                            try {
                                                                                LamTrangFile("DuLieuCourse.txt");
                                                                                GhiFileCourse(finalCourseList3);
                                                                                Vector dulieu = new Vector();
                                                                                for (int iii = 0; iii < finalCourseList3.size(); iii++) {
                                                                                    Vector phu = new Vector();
                                                                                    phu.add(finalCourseList3.get(iii).getTenMon());
                                                                                    phu.add(finalCourseList3.get(iii).getMaMon());
                                                                                    phu.add(finalCourseList3.get(iii).getSoTinChi());
                                                                                    phu.add(finalCourseList3.get(iii).getGV());
                                                                                    phu.add(finalCourseList3.get(iii).getPhongHoc());
                                                                                    phu.add(finalCourseList3.get(iii).getDayofWeek());
                                                                                    phu.add(finalCourseList3.get(iii).getCa());
                                                                                    phu.add(finalCourseList3.get(iii).getMaxSlot());
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
                                            JButton delete = new JButton("Delete");
                                            delete.setIcon(new ImageIcon("delete.png"));
                                            delete.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == delete) {
                                                        if (table.getSelectedRow() < 0) {
                                                            JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần xóa!");
                                                        } else {
                                                            int position = table.getSelectedRow();
                                                            finalCourseList4.remove(position);
                                                            Vector List = new Vector();
                                                            for (int zzz = 0; zzz < finalCourseList4.size(); zzz++) {
                                                                Vector tm = new Vector();
                                                                tm.add(finalCourseList4.get(zzz).getMaMon());
                                                                tm.add(finalCourseList4.get(zzz).getTenMon());
                                                                tm.add(finalCourseList4.get(zzz).getSoTinChi());
                                                                tm.add(finalCourseList4.get(zzz).getGV());
                                                                tm.add(finalCourseList4.get(zzz).getPhongHoc());
                                                                tm.add(finalCourseList4.get(zzz).getDayofWeek());
                                                                tm.add(finalCourseList4.get(zzz).getCa());
                                                                tm.add(finalCourseList4.get(zzz).getMaxSlot());
                                                                List.add(tm);
                                                            }
                                                            table.setModel(new DefaultTableModel(List, Header));
                                                            try {
                                                                LamTrangFile("DuLieuCourse.txt");
                                                                GhiFileCourse(finalCourseList4);
                                                            } catch (IOException ioException) {
                                                                ioException.printStackTrace();
                                                            }
                                                        }
                                                    }
                                                }
                                            });
                                            JPanel southPanel = new JPanel();
                                            JButton listStudent=new JButton("Sinh viên đã đăng ký");
                                            listStudent.setIcon(new ImageIcon("list.png"));
                                            listStudent.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if(e.getSource()==listStudent)
                                                    {
                                                        if(table.getSelectedRow()<0)
                                                        {
                                                            JOptionPane.showMessageDialog(null,"Hãy chọn học phần cần xem!");
                                                        }
                                                        else
                                                        {
                                                            JFrame frame1=new JFrame();
                                                            frame1.setSize(1000,1000);
                                                            frame1.setTitle("Sinh viên đã đăng ký");
                                                            frame1.setVisible(true);
                                                            frame1.setIconImage(new ImageIcon("list.png").getImage());
                                                            frame1.setResizable(false);
                                                            JPanel mainPan=new JPanel();
                                                            BorderLayout borderLayout1=new BorderLayout();
                                                            mainPan.setLayout(borderLayout1);
                                                            JLabel label=new JLabel("Sinh viên đăng ký");
                                                            label.setHorizontalAlignment(0);
                                                            mainPan.add(label,BorderLayout.NORTH);
                                                            Vector tieuDe=new Vector();
                                                            tieuDe.add("Mã sinh viên");
                                                            tieuDe.add("Họ tên");
                                                            tieuDe.add("Mã môn");
                                                            tieuDe.add("Tên môn");
                                                            tieuDe.add("Giáo viên");
                                                            tieuDe.add("Thời gian học");
                                                            tieuDe.add("Thời gian DKHP");
                                                            int vitri=table.getSelectedRow();
                                                            Course tempCourse=finalCourseList5.get(vitri);
                                                            String tgHoc="";
                                                            if(tempCourse.getCa()==1)
                                                            {
                                                                tgHoc+="7:30-9:30";
                                                            }
                                                            if(tempCourse.getCa()==2)
                                                            {
                                                                tgHoc+="9:30-11:30";
                                                            }
                                                            if(tempCourse.getCa()==3)
                                                            {
                                                                tgHoc+="13:30-15:30";
                                                            }
                                                            if(tempCourse.getCa()==4)
                                                            {
                                                                tgHoc+="15:30-17:30";
                                                            }
                                                            Vector<HocSinh>HSDK=new Vector<>();
                                                            for(int kk=0;kk<finalStdList9.size();kk++)
                                                            {
                                                                int listsize;
                                                                if(finalStdList9.get(kk).getListCourse()==null) listsize=0;
                                                                else{listsize=finalStdList9.get(kk).getListCourse().size();}
                                                                for(int kkk=0;kkk<listsize;kkk++)
                                                                {
                                                                    if(tempCourse.getMaMon().equals(finalStdList9.get(kk).getListCourse().get(kkk).getMaMon()))
                                                                    {
                                                                        HSDK.add(finalStdList9.get(kk));
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            Vector data=new Vector();
                                                            for(int i1=0;i1<HSDK.size();i1++)
                                                            {
                                                                Vector dt=new Vector();
                                                                dt.add(HSDK.get(i1).getMHS());
                                                                dt.add(HSDK.get(i1).getTenHS());
                                                                dt.add(tempCourse.getMaMon());
                                                                dt.add(tempCourse.getTenMon());
                                                                dt.add(tempCourse.getGV());
                                                                dt.add(tgHoc);
                                                                for(int i2=0;i2<HSDK.get(i1).getListCourse().size();i2++)
                                                                {
                                                                    if(HSDK.get(i1).getListCourse().get(i2).getMaMon().equals(tempCourse.getMaMon()))
                                                                    {
                                                                        dt.add(HSDK.get(i1).getTgDKHP().get(i2));
                                                                        break;
                                                                    }
                                                                }
                                                                data.add(dt);
                                                            }
                                                            JTable jTable=new JTable(data,tieuDe);
                                                            JScrollPane scrollPane=new JScrollPane(jTable);
                                                            mainPan.add(scrollPane,BorderLayout.CENTER);
                                                            frame1.add(mainPan);
                                                            frame1.pack();
                                                        }
                                                    }
                                                }
                                            });
                                            southPanel.add(Search);
                                            southPanel.add(add);
                                            southPanel.add(delete);
                                            southPanel.add(listStudent);
                                            centerPanel.add(sp);
                                            listFrame.add(listlabel, BorderLayout.NORTH);
                                            listFrame.add(centerPanel, BorderLayout.CENTER);
                                            listFrame.add(southPanel, BorderLayout.SOUTH);
                                            listFrame.pack();
                                        }
                                    }
                                });
                                mainPanel.add(Lop);
                                mainPanel.add(student);
                                mainPanel.add(session);
                                mainPanel.add(course);
                                frame.add(mainPanel, BorderLayout.CENTER);
                                break;
                            }
                            else
                            {
                                check[0] = true;
                                JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
                                JFrame frame = new MyFrame();
                                jFrame.dispose();
                                HocSinh temp = new HocSinh();
                                int pos = 0;
                                for (int j = 0; j < finalStdList.size(); j++) {
                                    if(finalStdList.get(j).getAccount()==null)
                                    {
                                        continue;
                                    }
                                    if (user.equals(finalStdList.get(j).getAccount().getKey()) && pass.equals(finalStdList.get(j).getAccount().getValue())) {
                                        pos = j;
                                        temp = finalStdList.get(j);
                                    }
                                }
                                BorderLayout borderLayout = new BorderLayout();
                                frame.setLayout(borderLayout);
                                JButton logout = new JButton("Sign out");
                                JPanel northPan = new JPanel();
                                JLabel hello = new JLabel("Hello, " + user);
                                JLabel curSes = new JLabel();
                                if (currentSemester[0] != null) {
                                    curSes.setText("Học kỳ hiện tại:" + currentSemester[0].getTenHK() + "," + currentSemester[0].getYear() + "|");
                                }
                                JLabel now=new JLabel("Ngày hiện tại:"+java.time.LocalDate.now()+"|");
                                northPan.setLayout(new FlowLayout(FlowLayout.RIGHT));
                                northPan.add(now);
                                northPan.add(curSes);
                                northPan.add(hello);
                                northPan.add(logout);
                                logout.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == logout) {
                                            JOptionPane.showMessageDialog(null, "Đăng xuất thành công!");
                                            System.exit(0);
                                        }
                                    }
                                });
                                frame.add(northPan, BorderLayout.NORTH);
                                JPanel mainPanel = new JPanel();
                                mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                                JButton info = new JButton("User account");
                                mainPanel.add(info);
                                HocSinh finalTemp = temp;
                                HocSinh finalTemp1 = temp;
                                HocSinh finalTemp2 = temp;
                                HocSinh finalTemp3 = temp;
                                HocSinh finalTemp4 = temp;
                                HocSinh finalTemp5 = temp;
                                int finalPos = pos;
                                info.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == info) {
                                            JFrame jFrame1 = new JFrame();
                                            jFrame1.setTitle("User account");
                                            ImageIcon Icon = new ImageIcon("user.png");
                                            jFrame1.setIconImage(Icon.getImage());
                                            jFrame1.setSize(520, 450);
                                            jFrame1.setResizable(false);
                                            jFrame1.setVisible(true);
                                            JPanel panel = new JPanel();
                                            JLabel label1 = new JLabel();
                                            HocSinh hs = new HocSinh();
                                            label1.setText("Mã sinh viên:");
                                            JTextField textField = new JTextField();
                                            textField.setText(String.valueOf(finalTemp.getMHS()));
                                            if (textField.getText().equals("0")) {
                                                textField.setText("");
                                            }
                                            textField.setPreferredSize(new Dimension(500, 30));
                                            panel.add(label1);
                                            panel.add(textField);
                                            JPanel panel1 = new JPanel();
                                            JLabel label2 = new JLabel();
                                            label2.setText("Tên sinh viên:");
                                            JTextField textField1 = new JTextField();
                                            textField1.setText(finalTemp1.getTenHS());
                                            textField1.setPreferredSize(new Dimension(500, 30));
                                            panel1.add(label2);
                                            panel1.add(textField1);
                                            JPanel panel2 = new JPanel();
                                            JLabel label3 = new JLabel();
                                            label3.setText("Điểm:");
                                            JTextField textField2 = new JTextField();
                                            textField2.setText(String.valueOf(finalTemp2.getDiem()));
                                            if (textField2.getText().equals("0.0")) {
                                                textField2.setText("");
                                            }
                                            textField2.setPreferredSize(new Dimension(500, 30));
                                            panel2.add(label3);
                                            panel2.add(textField2);
                                            JPanel panel3 = new JPanel();
                                            JLabel label4 = new JLabel();
                                            label4.setText("Địa chỉ:");
                                            JTextField textField3 = new JTextField();
                                            textField3.setText(finalTemp3.getDiaChi());
                                            textField3.setPreferredSize(new Dimension(500, 30));
                                            panel3.add(label4);
                                            panel3.add(textField3);
                                            JPanel panel4 = new JPanel();
                                            JLabel label5 = new JLabel();
                                            label5.setText("Lớp:");
                                            JTextField textField4 = new JTextField();
                                            textField4.setText(finalTemp4.getLop());
                                            textField4.setPreferredSize(new Dimension(500, 30));
                                            panel4.add(label5);
                                            panel4.add(textField4);
                                            JPanel panel5 = new JPanel();
                                            JLabel label6 = new JLabel();
                                            label6.setText("Hình ảnh:");
                                            panel5.add(label6);
                                            JButton fileBut = new JButton("Chọn ảnh");
                                            panel5.add(fileBut);
                                            JTextField textField5 = new JTextField();
                                            textField5.setText(finalTemp5.getHinhanh());
                                            textField5.setPreferredSize(new Dimension(500, 30));
                                            panel5.add(textField5);
                                            fileBut.setVisible(false);
                                            fileBut.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == fileBut) {
                                                        JFileChooser fileChooser = new JFileChooser();
                                                        int response = fileChooser.showOpenDialog(null);
                                                        if (response == JFileChooser.APPROVE_OPTION) {
                                                            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                                                            String path = String.valueOf(file);
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
                                            JPanel mainPanel = new JPanel();
                                            BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
                                            mainPanel.setLayout(boxLayout);
                                            mainPanel.add(panel);
                                            mainPanel.add(panel1);
                                            mainPanel.add(panel2);
                                            mainPanel.add(panel3);
                                            mainPanel.add(panel4);
                                            mainPanel.add(panel5);
                                            jFrame1.add(mainPanel);
                                            JPanel butPanel = new JPanel();
                                            JButton but1 = new JButton("Sửa thông tin");
                                            but1.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == but1) {
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
                                            JButton saveBut = new JButton("Lưu");
                                            saveBut.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == saveBut) {
                                                        if (textField.getText().equals("") || textField1.getText().equals("") || textField2.getText().equals("")
                                                                || textField3.getText().equals("") || textField4.getText().equals("") || textField5.getText().equals("")) {
                                                            JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin!");
                                                        } else {
                                                            JOptionPane.showMessageDialog(null, "Đã lưu lại thông tin!");
                                                            textField.setFocusable(false);
                                                            textField1.setFocusable(false);
                                                            textField2.setFocusable(false);
                                                            textField3.setFocusable(false);
                                                            textField4.setFocusable(false);
                                                            textField5.setFocusable(false);
                                                            fileBut.setVisible(false);
                                                            finalStdList.remove(finalPos);
                                                            finalTemp.setMHS(Integer.parseInt(textField.getText()));
                                                            finalTemp.setTenHS(textField1.getText());
                                                            finalTemp.setDiem(Float.parseFloat(textField2.getText()));
                                                            finalTemp.setDiaChi(textField3.getText());
                                                            finalTemp.setLop(textField4.getText());
                                                            finalTemp.setHinhanh(textField5.getText());
                                                            finalStdList.add(finalPos, finalTemp);
                                                            try {
                                                                LamTrangFile("DuLieuHS.txt");
                                                                GhiFileStd(finalStdList);
                                                            } catch (IOException ioException) {
                                                                ioException.printStackTrace();
                                                            }
                                                        }
                                                    }
                                                }
                                            });
                                            FlowLayout flowLayout = new FlowLayout();
                                            butPanel.setLayout(flowLayout);
                                            butPanel.add(but1);
                                            butPanel.add(saveBut);
                                            mainPanel.add(butPanel);
                                        }
                                    }
                                });
                                JButton changePass = new JButton("Đổi mật khẩu");
                                mainPanel.add(changePass);
                                ImageIcon useraccount = new ImageIcon("user.png");
                                info.setIcon(useraccount);
                                ImageIcon changepass = new ImageIcon("key.png");
                                changePass.setIcon(changepass);
                                changePass.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == changePass) {
                                            JFrame passframe = new JFrame();
                                            passframe.setTitle("Đổi mật khẩu");
                                            ImageIcon icon1 = new ImageIcon("key.png");
                                            passframe.setIconImage(icon1.getImage());
                                            passframe.setSize(520, 450);
                                            passframe.setResizable(false);
                                            passframe.setVisible(true);
                                            JPanel passPanel = new JPanel();
                                            JPanel jPanel = new JPanel();
                                            BoxLayout boxLayout = new BoxLayout(passPanel, BoxLayout.Y_AXIS);
                                            passPanel.setLayout(boxLayout);
                                            JLabel jlabel = new JLabel("Mật khẩu cũ:");
                                            jPanel.add(jlabel);
                                            JTextField jtextField = new JTextField();
                                            jtextField.setPreferredSize(new Dimension(300, 30));
                                            jPanel.add(jtextField);
                                            passPanel.add(jPanel);
                                            JPanel jPanel1 = new JPanel();
                                            JLabel jlabel1 = new JLabel("Mật khẩu mới:");
                                            jPanel1.add(jlabel1);
                                            JTextField jtextField1 = new JTextField();
                                            jtextField1.setPreferredSize(new Dimension(300, 30));
                                            jPanel1.add(jtextField1);
                                            passPanel.add(jPanel1);
                                            JPanel jPanel2 = new JPanel();
                                            JLabel jlabel2 = new JLabel("Nhập lại mật khẩu mới:");
                                            jPanel2.add(jlabel2);
                                            JTextField jtextField2 = new JTextField();
                                            jtextField2.setPreferredSize(new Dimension(300, 30));
                                            jPanel2.add(jtextField2);
                                            passPanel.add(jPanel2);
                                            JPanel butPan = new JPanel();
                                            JButton change = new JButton("Đổi mật khẩu");
                                            butPan.add(change);
                                            change.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == change) {
                                                        if (jtextField.getText().equals("") || jtextField1.getText().equals("") || jtextField2.getText().equals("")) {
                                                            JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                                                        } else {
                                                            if (!jtextField.getText().equals(pass)) {
                                                                JOptionPane.showMessageDialog(null, "Sai mật khẩu cũ!");
                                                            } else {
                                                                if (!jtextField1.getText().equals(jtextField2.getText())) {
                                                                    JOptionPane.showMessageDialog(null, "Mật khẩu mới phải trùng nhau");
                                                                } else {
                                                                    JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công!");
                                                                    for (int k = 0; k < finalStdList2.size(); k++) {
                                                                        if (user.equals(finalStdList2.get(k).getAccount().getKey())) {
                                                                            finalStdList2.get(k).getAccount().setValue(jtextField1.getText());
                                                                        }
                                                                    }
                                                                    for (int l = 0; l < finalAccounts.size(); l++) {
                                                                        if (user.equals(finalAccounts.get(l).getKey())) {
                                                                            finalAccounts.get(l).setValue(jtextField1.getText());
                                                                        }
                                                                    }
                                                                    try {
                                                                        LamTrangFile("DuLieuHS.txt");
                                                                        LamTrangFile("DuLieuAccount.txt");
                                                                        GhiFileStd(finalStdList2);
                                                                        GhiFile2(finalAccounts);
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
                                JButton Subject = new JButton("Đăng ký học phần");
                                Subject.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        if (e.getSource() == Subject) {
                                            JFrame listFrame = new JFrame();
                                            listFrame.setTitle("Đăng ký học phần");
                                            listFrame.setIconImage(new ImageIcon("subject.png").getImage());
                                            listFrame.setSize(1000, 1000);
                                            listFrame.setVisible(true);
                                            BorderLayout borderLayout1 = new BorderLayout();
                                            listFrame.setLayout(borderLayout1);
                                            JPanel centerPanel = new JPanel();
                                            JLabel listlabel = new JLabel("Danh sách các học phần đã đăng ký");
                                            listlabel.setHorizontalAlignment(0);
                                            Vector Header = new Vector();
                                            Header.add("Mã môn");
                                            Header.add("Tên môn");
                                            Header.add("Số tín chỉ");
                                            Header.add("Giáo viên");
                                            Header.add("Phòng");
                                            Header.add("Ngày học");
                                            Header.add("Ca");
                                            Header.add("Số lượng");
                                            Vector data = new Vector();
                                            int sz;
                                            if(finalTemp.getListCourse()==null) sz=0;
                                            else {sz=finalTemp.getListCourse().size();}
                                            for (int ii = 0; ii < sz; ii++) {
                                                Vector tmp = new Vector();
                                                tmp.add(finalTemp.getListCourse().get(ii).getMaMon());
                                                tmp.add(finalTemp.getListCourse().get(ii).getTenMon());
                                                tmp.add(finalTemp.getListCourse().get(ii).getSoTinChi());
                                                tmp.add(finalTemp.getListCourse().get(ii).getGV());
                                                tmp.add(finalTemp.getListCourse().get(ii).getPhongHoc());
                                                tmp.add(finalTemp.getListCourse().get(ii).getDayofWeek());
                                                tmp.add(finalTemp.getListCourse().get(ii).getCa());
                                                tmp.add(finalTemp.getListCourse().get(ii).getMaxSlot());
                                                data.add(tmp);
                                            }
                                            JTable table = new JTable(data, Header);
                                            JScrollPane sp = new JScrollPane(table);
                                            JButton DK = new JButton("Đăng ký thêm");
                                            DK.setIcon(new ImageIcon("rsz_add.png"));
                                            DK.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == DK) {
                                                        String time=String.valueOf(java.time.LocalDate.now());
                                                        StringTokenizer tokenizer=new StringTokenizer(time,"-");
                                                        Vector<String> data=new Vector();
                                                        while(tokenizer.hasMoreTokens())
                                                        {
                                                            data.add(tokenizer.nextToken());
                                                        }
                                                        int year= Integer.parseInt(data.get(0));
                                                        int month=Integer.parseInt(data.get(1));
                                                        int day=Integer.parseInt(data.get(2));
                                                        boolean KTra=false;
                                                        int kichthuoc;
                                                        if(currentSemester[0]==null)
                                                        {
                                                            JOptionPane.showMessageDialog(null,"Giáo vụ chưa set học kỳ hiện tại!");
                                                        }
                                                        else
                                                        {
                                                            if(currentSemester[0].getDKHP()==null) kichthuoc=0;
                                                            else
                                                            {
                                                                kichthuoc=currentSemester[0].getDKHP().size();
                                                            }
                                                            for(int c=0;c<kichthuoc;c++)
                                                            {
                                                                DateTime bd=currentSemester[0].getDKHP().get(c).getStart();
                                                                DateTime kt=currentSemester[0].getDKHP().get(c).getEnd();
                                                                if(LonHon(day,month,year,bd.getDay(),bd.getMonth(),bd.getYear())&&
                                                                        NhoHon(day,month,year,kt.getDay(),kt.getMonth(),kt.getYear()))
                                                                {
                                                                    KTra=true;
                                                                    JFrame listFrame = new JFrame();
                                                                    listFrame.setTitle("Danh sách học phần");
                                                                    listFrame.setIconImage(new ImageIcon("course.png").getImage());
                                                                    listFrame.setSize(1000, 1000);
                                                                    listFrame.setVisible(true);
                                                                    JButton select = new JButton("Đăng ký");
                                                                    select.setIcon(new ImageIcon("rsz_add.png"));
                                                                    BorderLayout borderLayout1 = new BorderLayout();
                                                                    listFrame.setLayout(borderLayout1);
                                                                    JPanel centerPanelmoi = new JPanel();
                                                                    JLabel listlabel = new JLabel("List Courses");
                                                                    listlabel.setHorizontalAlignment(0);
                                                                    Vector Headermoi = new Vector();
                                                                    Headermoi.add("Mã môn học");
                                                                    Headermoi.add("Tên môn học");
                                                                    Headermoi.add("Số tín chỉ");
                                                                    Headermoi.add("Giáo viên");
                                                                    Headermoi.add("Phòng học");
                                                                    Headermoi.add("Ngày học");
                                                                    Headermoi.add("Ca");
                                                                    Headermoi.add("Số lượng");
                                                                    Vector datamoi = new Vector();
                                                                    for (int ii = 0; ii < finalCourseList.size(); ii++) {
                                                                        Vector tempt = new Vector();
                                                                        tempt.add(finalCourseList.get(ii).getMaMon());
                                                                        tempt.add(finalCourseList.get(ii).getTenMon());
                                                                        tempt.add(finalCourseList.get(ii).getSoTinChi());
                                                                        tempt.add(finalCourseList.get(ii).getGV());
                                                                        tempt.add(finalCourseList.get(ii).getPhongHoc());
                                                                        tempt.add(finalCourseList.get(ii).getDayofWeek());
                                                                        tempt.add(finalCourseList.get(ii).getCa());
                                                                        tempt.add(finalCourseList.get(ii).getMaxSlot());
                                                                        datamoi.add(tempt);
                                                                    }
                                                                    JTable tablemoi = new JTable(datamoi, Headermoi);
                                                                    JScrollPane spmoi = new JScrollPane(tablemoi);
                                                                    select.addActionListener(new ActionListener() {
                                                                        @Override
                                                                        public void actionPerformed(ActionEvent e) {
                                                                            if (e.getSource() == select) {
                                                                                if(tablemoi.getSelectedRow()<0)
                                                                                {
                                                                                    JOptionPane.showMessageDialog(null,"Hãy chọn môn học cần đăng ký!");
                                                                                }
                                                                                else
                                                                                {
                                                                                    int posi=tablemoi.getSelectedRow();
                                                                                    boolean ck=false;
                                                                                    int si;
                                                                                    if(finalTemp.getListCourse()==null)
                                                                                    {
                                                                                        si=0;
                                                                                    }
                                                                                    else
                                                                                    {
                                                                                        si=finalTemp.getListCourse().size();
                                                                                    }
                                                                                    System.out.println(si);
                                                                                    for(int v=0;v<si;v++)
                                                                                    {
                                                                                        if(finalTemp.getListCourse().get(v).getMaMon().equals(finalCourseList4.get(posi).getMaMon()))
                                                                                        {
                                                                                            ck=true;
                                                                                            JOptionPane.showMessageDialog(null,"Đã đăng ký môn này rồi!");
                                                                                            break;
                                                                                        }
                                                                                        if(finalTemp.getListCourse().get(v).getCa()==finalCourseList4.get(posi).getCa()&&
                                                                                                finalTemp.getListCourse().get(v).getDayofWeek().equals(finalCourseList4.get(posi).getDayofWeek()))
                                                                                        {
                                                                                            ck=true;
                                                                                            JOptionPane.showMessageDialog(null,"Trùng ca với môn học khác!");
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                    if(ck==false)
                                                                                    {
                                                                                        Vector<Course>t=finalTemp5.getListCourse();
                                                                                        if(t==null)
                                                                                        {
                                                                                            t=new Vector<>();
                                                                                        }
                                                                                        if(t.size()==8)
                                                                                        {
                                                                                            JOptionPane.showMessageDialog(null,"Đã đăng ký đủ 8 môn.Không thể đăng ký thêm!");
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            t.add(finalCourseList5.get(posi));
                                                                                            JOptionPane.showMessageDialog(null,"Đăng ký thành công!");
                                                                                            if(finalTemp5.getTgDKHP()==null)
                                                                                            {
                                                                                                finalTemp5.setTgDKHP(new Vector<>());
                                                                                            }
                                                                                            finalTemp5.getTgDKHP().add(String.valueOf(java.time.LocalDate.now()));
                                                                                            try {
                                                                                                LamTrangFile("DuLieuCourse.txt");
                                                                                                GhiFileCourse(finalCourseList5);
                                                                                            } catch (IOException ioException) {
                                                                                                ioException.printStackTrace();
                                                                                            }
                                                                                            finalTemp5.setListCourse(t);
                                                                                            for(int mm = 0; mm< finalStdList9.size(); mm++)
                                                                                            {
                                                                                                if(finalStdList9.get(mm).getAccount()==null)
                                                                                                {
                                                                                                    continue;
                                                                                                }
                                                                                                if(finalStdList9.get(mm)==finalTemp5)
                                                                                                {
                                                                                                    finalStdList9.remove(mm);
                                                                                                    finalStdList9.add(mm,finalTemp5);
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                            Vector DuLieu=new Vector();
                                                                                            int SIZE;
                                                                                            if(finalTemp5.getListCourse()==null) SIZE=0;
                                                                                            else
                                                                                            {
                                                                                                SIZE=finalTemp5.getListCourse().size();
                                                                                            }
                                                                                            for(int xx=0;xx<SIZE;xx++)
                                                                                            {
                                                                                                Vector tt=new Vector();
                                                                                                tt.add(finalTemp5.getListCourse().get(xx).getMaMon());
                                                                                                tt.add(finalTemp5.getListCourse().get(xx).getTenMon());
                                                                                                tt.add(finalTemp5.getListCourse().get(xx).getSoTinChi());
                                                                                                tt.add(finalTemp5.getListCourse().get(xx).getGV());
                                                                                                tt.add(finalTemp5.getListCourse().get(xx).getPhongHoc());
                                                                                                tt.add(finalTemp5.getListCourse().get(xx).getDayofWeek());
                                                                                                tt.add(finalTemp5.getListCourse().get(xx).getCa());
                                                                                                tt.add(finalTemp5.getListCourse().get(xx).getMaxSlot());
                                                                                                DuLieu.add(tt);
                                                                                            }
                                                                                            table.setModel(new DefaultTableModel(DuLieu,Header));
                                                                                            try {
                                                                                                LamTrangFile("DuLieuHS.txt");
                                                                                                GhiFileStd(finalStdList9);
                                                                                            } catch (IOException ioException) {
                                                                                                ioException.printStackTrace();
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    });
                                                                    JPanel southPanel = new JPanel();
                                                                    southPanel.add(select);
                                                                    centerPanelmoi.add(spmoi);
                                                                    listFrame.add(listlabel, BorderLayout.NORTH);
                                                                    listFrame.add(centerPanelmoi, BorderLayout.CENTER);
                                                                    listFrame.add(southPanel, BorderLayout.SOUTH);
                                                                    listFrame.pack();
                                                                }
                                                            }
                                                            if(KTra==false)
                                                            {
                                                                JOptionPane.showMessageDialog(null,"Đã hết hạn đăng ký học phần");
                                                            }
                                                        }
                                                    }
                                                }
                                            });
                                            JButton delete = new JButton("Delete");
                                            delete.setIcon(new ImageIcon("delete.png"));
                                            delete.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    if (e.getSource() == delete) {
                                                        if (table.getSelectedRow() < 0) {
                                                            JOptionPane.showMessageDialog(null, "Hãy chọn đối tượng cần xóa!");
                                                        } else {
                                                            String time=String.valueOf(java.time.LocalDate.now());
                                                            StringTokenizer tokenizer=new StringTokenizer(time,"-");
                                                            Vector<String> data=new Vector();
                                                            while(tokenizer.hasMoreTokens())
                                                            {
                                                                data.add(tokenizer.nextToken());
                                                            }
                                                            int year= Integer.parseInt(data.get(0));
                                                            int month=Integer.parseInt(data.get(1));
                                                            int day=Integer.parseInt(data.get(2));
                                                            boolean KT=false;
                                                            int kichthuoc;
                                                            if(currentSemester[0]==null)
                                                            {
                                                                JOptionPane.showMessageDialog(null,"Giáo vụ chưa set học kỳ hiện tại!");
                                                            }
                                                            else
                                                            {
                                                                if(currentSemester[0].getDKHP()==null) kichthuoc=0;
                                                                else
                                                                {
                                                                    kichthuoc=currentSemester[0].getDKHP().size();
                                                                }
                                                                for(int c=0;c<kichthuoc;c++)
                                                                {
                                                                    DateTime bd=currentSemester[0].getDKHP().get(c).getStart();
                                                                    DateTime kt=currentSemester[0].getDKHP().get(c).getEnd();
                                                                    if(LonHon(day,month,year,bd.getDay(),bd.getMonth(),bd.getYear())&&
                                                                            NhoHon(day,month,year,kt.getDay(),kt.getMonth(),kt.getYear()))
                                                                    {
                                                                        Vector<Course>tempCourse=finalTemp5.getListCourse();
                                                                        Vector<String>tempDKHP=finalTemp5.getTgDKHP();
                                                                        KT=true;
                                                                        JOptionPane.showMessageDialog(null,"Delete thành công!");
                                                                        int position = table.getSelectedRow();
                                                                        tempDKHP.remove(position);
                                                                        tempCourse.remove(position);
                                                                        finalTemp5.setListCourse(tempCourse);
                                                                        finalTemp5.setTgDKHP(tempDKHP);
                                                                        for(int b = 0; b< finalStdList8.size(); b++)
                                                                        {
                                                                            if(finalStdList8.get(b).getMHS()==finalTemp5.getMHS())
                                                                            {
                                                                                finalStdList8.remove(b);
                                                                                finalStdList8.add(b,finalTemp5);
                                                                                break;
                                                                            }
                                                                        }
                                                                        Vector List = new Vector();
                                                                        for (int zzz = 0; zzz < tempCourse.size(); zzz++) {
                                                                            Vector tm = new Vector();
                                                                            tm.add(tempCourse.get(zzz).getMaMon());
                                                                            tm.add(tempCourse.get(zzz).getTenMon());
                                                                            tm.add(tempCourse.get(zzz).getSoTinChi());
                                                                            tm.add(tempCourse.get(zzz).getGV());
                                                                            tm.add(tempCourse.get(zzz).getPhongHoc());
                                                                            tm.add(tempCourse.get(zzz).getDayofWeek());
                                                                            tm.add(tempCourse.get(zzz).getCa());
                                                                            tm.add(tempCourse.get(zzz).getMaxSlot());
                                                                            List.add(tm);
                                                                        }
                                                                        table.setModel(new DefaultTableModel(List, Header));
                                                                        try {
                                                                            LamTrangFile("DuLieuHS.txt");
                                                                            GhiFileStd(finalStdList8);
                                                                        } catch (IOException ioException) {
                                                                            ioException.printStackTrace();
                                                                        }
                                                                    }
                                                                }
                                                                if(KT==false)
                                                                {
                                                                    JOptionPane.showMessageDialog(null,"Đã hết hạn đăng ký học phần");
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            });
                                            JPanel southPanel = new JPanel();
                                            southPanel.add(DK);
                                            southPanel.add(delete);
                                            centerPanel.add(sp);
                                            listFrame.add(listlabel, BorderLayout.NORTH);
                                            listFrame.add(centerPanel, BorderLayout.CENTER);
                                            listFrame.add(southPanel, BorderLayout.SOUTH);
                                            listFrame.pack();
                                        }
                                    }
                                });
                                Subject.setIcon(new ImageIcon("subject.png"));
                                mainPanel.add(Subject);
                                frame.add(mainPanel, BorderLayout.CENTER);
                                break;
                            }
                        }
                    }
                    if(check[0] ==false)
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
    static void GhiFileStd(Vector a) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieuHS.txt"));
        out.writeInt(a.size());
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
    static void GhiFileSemester(Vector a) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieuHocKy.txt"));
        out.writeInt(a.size());
        for(int i=0;i<a.size();i++)
        {
            out.writeObject(a.get(i));
        }
        out.flush();
        out.close();
    }
    static void GhiFilecurrentSemester(Semester a) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieuHocKyhientai.txt"));
        out.writeObject(a);
        out.flush();
        out.close();
    }
    static void GhiFileLop(Vector a) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieuLop.txt"));
        out.writeInt(a.size());
        for(int i=0;i<a.size();i++)
        {
            out.writeObject(a.get(i));
        }
        out.flush();
        out.close();
    }
    static void GhiFileSession(Vector a) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieuSession.txt"));
        out.writeInt(a.size());
        for(int i=0;i<a.size();i++)
        {
            out.writeObject(a.get(i));
        }
        out.flush();
        out.close();
    }
    static void GhiFileCourse(Vector a) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieuCourse.txt"));
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
    static Vector DocFileStd(Vector a) throws IOException, ClassNotFoundException {

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("DuLieuHS.txt"));
        int count=in.readInt();
        for(int i=0;i<count;i++)
        {
            HocSinh hs= (HocSinh) in.readObject();
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
    static Vector DocFileSemester(Vector a) throws IOException, ClassNotFoundException {

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("DuLieuHocKy.txt"));
        int count=in.readInt();
        for(int i=0;i<count;i++)
        {
            Semester acc= (Semester) in.readObject();
            a.add(acc);
        }
        in.close();
        return a;
    }
    static Vector DocFileSession(Vector a) throws IOException, ClassNotFoundException {

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("DuLieuSession.txt"));
        int count=in.readInt();
        for(int i=0;i<count;i++)
        {
            Session acc= (Session) in.readObject();
            a.add(acc);
        }
        in.close();
        return a;
    }
    static Semester DocFilecurrentSemester(Semester a) throws IOException, ClassNotFoundException {

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("DuLieuHocKyhientai.txt"));
        a= (Semester) in.readObject();
        in.close();
        return a;
    }
    static Vector DocFileLop(Vector a) throws IOException, ClassNotFoundException {

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("DuLieuLop.txt"));
        int count=in.readInt();
        for(int i=0;i<count;i++)
        {
            Class acc= (Class) in.readObject();
            a.add(acc);
        }
        in.close();
        return a;
    }
    static Vector DocFileCourse(Vector a) throws IOException, ClassNotFoundException {

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("DuLieuCourse.txt"));
        int count=in.readInt();
        for(int i=0;i<count;i++)
        {
            Course acc= (Course) in.readObject();
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
    static boolean LonHon(int day,int month,int year,int day2,int month2,int year2)
    {
        if(year>year2)
        {
            return true;
        }
        else
        {
            if(year<year2)
            {
                return false;
            }
            else
            {
                if(month>month2)
                {
                    return true;
                }
                else
                {
                    if(month<month2)
                    {
                        return false;
                    }
                    else
                    {
                        if(day>=day2)
                        {
                            return true;
                        }
                        else
                        {
                            return false;
                        }
                    }
                }
            }
        }
    }
    static boolean NhoHon(int day,int month,int year,int day2,int month2,int year2)
    {
        if(year>year2)
        {
            return false;
        }
        else
        {
            if(year<year2)
            {
                return true;
            }
            else
            {
                if(month>month2)
                {
                    return false;
                }
                else
                {
                    if(month<month2)
                    {
                        return true;
                    }
                    else
                    {
                        if(day>day2)
                        {
                            return false;
                        }
                        else
                        {
                            return true;
                        }
                    }
                }
            }
        }
    }
}
