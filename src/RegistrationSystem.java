import DAO.*;
import hibernate.*;
import hibernate.Account;
import hibernate.Course;
import hibernate.Semester;
import hibernate.Session;
import hibernate.Subject;
import hibernate.Teacher;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import utils.hibernateUtil;

import javax.crypto.SealedObject;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

import static java.awt.Color.black;
import static java.awt.Color.green;

public class RegistrationSystem {
    public static void main(String[]args) throws IOException, ClassNotFoundException {
        List<Account>Accounts= accountDAO.getAllaccounts();
        List<Student>stdList= studentDAO.getAllstudents();
        List<Subject>sbjList= subjectDAO.getAllsubjects();
        List<Semester>semesterList= semesterDAO.getAllsemesters();
        List<Clazz>classList= classDAO.getAllclasses();
        List<Course>courseList=courseDAO.getAllcourses();
        List<Session>sessionList=sessionDAO.getAllsessions();
        String maCurrentSemester="";
        List<Teacher>teacherList=teacherDAO.getAllteachers();
        if(FileSize("DuLieuHocKyhientai.txt")!=0)
        {
            maCurrentSemester+=DocFilecurrentSemester();
        }
        final Semester[] currentSemester = {new Semester()};
        for(int qq=0;qq<semesterList.size();qq++)
        {
            if(maCurrentSemester.equals(semesterList.get(qq).getMaHk()))
            {
                currentSemester[0] =semesterList.get(qq);
            }
        }
        Vector classphu=new Vector();
        if(FileSize("DuLieuLop.txt")!=0)
        {
            classphu=DocFileLop(classphu);
        }
        for(int p=0;p<classphu.size();p++)
        {
            Clazz tempp=(Clazz)classphu.get(p);
            for(int pp=0;pp< classList.size();pp++)
            {
                if(classList.get(pp).getTenLop().equals((tempp.getTenLop())))
                {
                    classList.get(pp).setSVList((tempp.getSVList()));
                }
            }
        }
        Vector Semesterphu=new Vector();
        if(FileSize("DuLieuHocKy.txt")!=0)
        {
            Semesterphu=DocFileSemester(Semesterphu);
        }
        for(int p=0;p<Semesterphu.size();p++)
        {
            Semester tempp=(Semester) Semesterphu.get(p);
            for(int pp=0;pp< semesterList.size();pp++)
            {
                if(semesterList.get(pp).getMaHk().equals((tempp.getMaHk())))
                {
                    semesterList.get(pp).setDKHP((tempp.getDKHP()));
                }
            }
        }
        Vector Stdphu=new Vector();
        if(FileSize("DuLieuHS.txt")!=0)
        {
            Stdphu=DocFileStd(Stdphu);
        }
        for(int p=0;p<Stdphu.size();p++)
        {
            Student tempp=(Student) Stdphu.get(p);
            for(int pp=0;pp< Stdphu.size();pp++)
            {
                if(stdList.get(pp).getMhs()==(tempp.getMhs()))
                {
                    stdList.get(pp).setListCourse((tempp.getListCourse()));
                    stdList.get(pp).setTgDKHP((tempp.getTgDKHP()));
                }
            }
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
        Semester finalCurrentSemester = currentSemester[0];
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==button)
                {
                    final boolean[] check = {false};
                    String user=username.getText().toString();
                    String pass=String.valueOf(password.getPassword());
                    for(int i = 0; i< Accounts.size(); i++)
                    {
                        if(Accounts.get(i).getUsername().equals(user)&& Accounts.get(i).getPassword().equals(pass)) {
                            if (Accounts.get(i).getType().equals("Teacher")) {
                                check[0] = true;
                                JOptionPane.showMessageDialog(null, "Đăng nhập thành công!");
                                JFrame frame = new MyFrame();
                                jFrame.dispose();
                                Teacher temp = new Teacher();
                                int pos = 0;
                                for (int j = 0; j < teacherList.size(); j++) {

                                    if (user.equals(teacherList.get(j).getUsername())) {
                                        pos = j;
                                        temp = teacherList.get(j);
                                    }
                                }
                                BorderLayout borderLayout = new BorderLayout();
                                frame.setLayout(borderLayout);
                                JButton logout = new JButton("Sign out");
                                JPanel northPan = new JPanel();
                                JLabel hello = new JLabel("Hello, " + user);
                                JLabel curSes = new JLabel();
                                if (finalCurrentSemester != null) {
                                    curSes.setText("Học kỳ hiện tại:" + finalCurrentSemester.getTenHk() + "," + finalCurrentSemester.getYear() + "|");
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
                                            Student hs = new Student();
                                            label1.setText("Mã giáo viên:");
                                            JTextField textField = new JTextField();
                                            textField.setText(String.valueOf(finalTemp.getMgv()));
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
                                            textField1.setText(finalTemp1.getTenGv());
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
                                            textField3.setText(finalTemp3.getDiachi());
                                            textField3.setPreferredSize(new Dimension(500, 30));
                                            panel3.add(label4);
                                            panel3.add(textField3);
                                            JPanel panel4 = new JPanel();
                                            JLabel label5 = new JLabel();
                                            label5.setText("Ghi chú:");
                                            JTextField textField4 = new JTextField();
                                            textField4.setText(finalTemp4.getGhichu());
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
                                                            org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                            Transaction transaction=session.getTransaction();
                                                            transaction.begin();
                                                            if(finalTemp!=null) session.remove(finalTemp);
                                                            transaction.commit();
                                                            session.close();
                                                            teacherList.remove(finalPos);
                                                            finalTemp.setMgv(Integer.parseInt(textField.getText()));
                                                            finalTemp.setTenGv(textField1.getText());
                                                            finalTemp.setTuoi(Integer.parseInt(textField2.getText()));
                                                            finalTemp.setDiachi(textField3.getText());
                                                            finalTemp.setGhichu(textField4.getText());
                                                            finalTemp.setHinhanh(textField5.getText());
                                                            teacherList.add(finalPos, finalTemp);
                                                            Configuration config = new Configuration().configure().addAnnotatedClass(Teacher.class);
                                                            SessionFactory sf = config.buildSessionFactory();
                                                            org.hibernate.Session session2 = sf.openSession();
                                                            Transaction tx = session2.beginTransaction();
                                                            session2.save(finalTemp);
                                                            tx.commit();
                                                            session2.close();
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
                                        for (int ii = 0; ii < Accounts.size(); ii++) {
                                            if(Accounts.get(ii).getType().equals("Teacher")){
                                                Vector temp = new Vector();
                                                temp.add(Accounts.get(ii).getUsername());
                                                temp.add(Accounts.get(ii).getPassword());
                                                data.add(temp);
                                            }
                                        }
                                        JTable table = new JTable(data, Header);
                                        JScrollPane sp = new JScrollPane(table);
                                        Account phu=new Account();
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
                                                                for (int i = 0; i < Accounts.size(); i++) {
                                                                    if (name.equals(Accounts.get(i).getUsername())&&Accounts.get(i).getType().equals("Teacher")) {
                                                                        check = true;
                                                                        Vector DuLieu = new Vector();
                                                                        Vector acc = new Vector();
                                                                        phu.setUsername(Accounts.get(i).getUsername());
                                                                        phu.setPassword(Accounts.get(i).getPassword());
                                                                        phu.setType("Teacher");
                                                                        acc.add(Accounts.get(i).getUsername());
                                                                        acc.add(Accounts.get(i).getPassword());
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
                                                    JPanel Jpanel2 = new JPanel();
                                                    JLabel Jlabel2= new JLabel("Mã giáo viên mới:");
                                                    Jpanel2.setLayout(new FlowLayout());
                                                    JTextField JtextField2 = new JTextField();
                                                    JtextField2.setPreferredSize(new Dimension(300, 30));
                                                    Jpanel2.add(Jlabel2);
                                                    Jpanel2.add(JtextField2);
                                                    JPanel Jpanel3 = new JPanel();
                                                    JLabel Jlabel3= new JLabel("Tên giáo viên mới:");
                                                    Jpanel3.setLayout(new FlowLayout());
                                                    JTextField JtextField3 = new JTextField();
                                                    JtextField3.setPreferredSize(new Dimension(300, 30));
                                                    Jpanel3.add(Jlabel3);
                                                    Jpanel3.add(JtextField3);
                                                    JPanel Jpanel4 = new JPanel();
                                                    JLabel Jlabel4= new JLabel("Tuổi:");
                                                    Jpanel4.setLayout(new FlowLayout());
                                                    JTextField JtextField4 = new JTextField();
                                                    JtextField4.setPreferredSize(new Dimension(300, 30));
                                                    Jpanel4.add(Jlabel4);
                                                    Jpanel4.add(JtextField4);
                                                    JPanel Jpanel5 = new JPanel();
                                                    JLabel Jlabel5= new JLabel("Địa chỉ:");
                                                    Jpanel5.setLayout(new FlowLayout());
                                                    JTextField JtextField5 = new JTextField();
                                                    JtextField5.setPreferredSize(new Dimension(300, 30));
                                                    Jpanel5.add(Jlabel5);
                                                    Jpanel5.add(JtextField5);
                                                    JPanel Jpanel6 = new JPanel();
                                                    JLabel Jlabel6= new JLabel("Ghi chú:");
                                                    Jpanel6.setLayout(new FlowLayout());
                                                    JTextField JtextField6 = new JTextField();
                                                    JtextField6.setPreferredSize(new Dimension(300, 30));
                                                    Jpanel6.add(Jlabel6);
                                                    Jpanel6.add(JtextField6);
                                                    JPanel Jpanel7 = new JPanel();
                                                    JLabel Jlabel7= new JLabel("Hình ảnh:");
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
                                                                if (JtextField.getText().equals("") || JtextField1.getText().equals("")||JtextField2.getText().equals("")
                                                                        ||JtextField3.getText().equals("")||JtextField4.getText().equals("")||JtextField5.getText().equals("")||
                                                                        JtextField6.getText().equals("")||JtextField7.getText().equals("")) {
                                                                    JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                                                                } else {
                                                                    String newUser = JtextField.getText();
                                                                    String newPass = JtextField1.getText();
                                                                    int magv=Integer.parseInt(JtextField2.getText());
                                                                    String tengv=JtextField3.getText();
                                                                    Integer tuoi=Integer.parseInt(JtextField4.getText());
                                                                    String diachi=JtextField5.getText();
                                                                    String ghichu=JtextField6.getText();
                                                                    String hinh=JtextField7.getText();
                                                                    boolean kt = false;
                                                                    for (int jj = 0; jj < Accounts.size(); jj++) {
                                                                        if (newUser.equals(Accounts.get(jj).getUsername())) {
                                                                            kt = true;
                                                                            JOptionPane.showMessageDialog(null, "Tài khoản đã tồn tại");
                                                                        }
                                                                    }
                                                                    if (kt == false) {
                                                                        Account newAcc = new Account(newUser, newPass,"Teacher");
                                                                        Accounts.add(newAcc);
                                                                        Teacher newTeacher = new Teacher(magv,tengv,tuoi,diachi,ghichu,hinh,newAcc.getUsername());
                                                                        teacherList.add(newTeacher);
                                                                        Vector dulieu = new Vector();
                                                                        for (int iii = 0; iii < Accounts.size(); iii++) {
                                                                            if (Accounts.get(iii).getType().equals("Teacher")) {
                                                                                Vector phu = new Vector();
                                                                                phu.add(Accounts.get(iii).getUsername());
                                                                                phu.add(Accounts.get(iii).getPassword());
                                                                                dulieu.add(phu);
                                                                            }
                                                                        }
                                                                        table.setModel(new DefaultTableModel(dulieu, Header));
                                                                        Configuration config = new Configuration().configure().addAnnotatedClass(Teacher.class);
                                                                        SessionFactory sf = config.buildSessionFactory();
                                                                        org.hibernate.Session session = sf.openSession();
                                                                        Transaction tx = session.beginTransaction();
                                                                        session.save(newTeacher);
                                                                        tx.commit();
                                                                        session.close();
                                                                        Configuration config2 = new Configuration().configure().addAnnotatedClass(Account.class);
                                                                        SessionFactory sf2 = config.buildSessionFactory();
                                                                        org.hibernate.Session session2 = sf2.openSession();
                                                                        Transaction tx2 = session2.beginTransaction();
                                                                        session2.save(newAcc);
                                                                        tx2.commit();
                                                                        session2.close();
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
                                                        Teacher tempteach=new Teacher();
                                                        boolean cheCk=false;
                                                        if(phu.getType()=="Teacher")
                                                        {
                                                            for(int x=0;x<teacherList.size();x++)
                                                            {
                                                                if(phu.getUsername().equals(teacherList.get(x).getUsername()))
                                                                {
                                                                    cheCk=true;
                                                                    tempteach=teacherList.get(x);
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        if(cheCk==false) tempteach = (Teacher) teacherList.get(table.getSelectedRow());
                                                        JFrame jFrame1 = new JFrame();
                                                        jFrame1.setTitle("Update");
                                                        ImageIcon Icon = new ImageIcon("update.png");
                                                        jFrame1.setIconImage(Icon.getImage());
                                                        jFrame1.setSize(520, 450);
                                                        jFrame1.setResizable(false);
                                                        jFrame1.setVisible(true);
                                                        JPanel panel = new JPanel();
                                                        JLabel label1 = new JLabel();
                                                        Student hs = new Student();
                                                        label1.setText("Mã giáo viên:");
                                                        JTextField textField = new JTextField();
                                                        textField.setText(String.valueOf(tempteach.getMgv()));
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
                                                        textField1.setText(tempteach.getTenGv());
                                                        textField1.setPreferredSize(new Dimension(500, 30));
                                                        panel1.add(label2);
                                                        panel1.add(textField1);
                                                        JPanel panel2 = new JPanel();
                                                        JLabel label3 = new JLabel();
                                                        label3.setText("Tuổi:");
                                                        JTextField textField2 = new JTextField();
                                                        textField2.setText(String.valueOf(tempteach.getTuoi()));
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
                                                        textField3.setText(tempteach.getDiachi());
                                                        textField3.setPreferredSize(new Dimension(500, 30));
                                                        panel3.add(label4);
                                                        panel3.add(textField3);
                                                        JPanel panel4 = new JPanel();
                                                        JLabel label5 = new JLabel();
                                                        label5.setText("Ghi chú:");
                                                        JTextField textField4 = new JTextField();
                                                        textField4.setText(tempteach.getGhichu());
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
                                                        textField5.setText(tempteach.getHinhanh());
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
                                                        Teacher finalTempteach = tempteach;
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
                                                                        int poss = table.getSelectedRow();
                                                                        org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                                        Transaction transaction=session.getTransaction();
                                                                        transaction.begin();
                                                                        if(teacherList.get(poss)!=null) session.remove(teacherList.get(poss));
                                                                        transaction.commit();
                                                                        session.close();
                                                                        teacherList.remove(poss);
                                                                        finalTempteach.setMgv(Integer.parseInt(textField.getText()));
                                                                        finalTempteach.setTenGv(textField1.getText());
                                                                        finalTempteach.setTuoi(Integer.parseInt(textField2.getText()));
                                                                        finalTempteach.setDiachi(textField3.getText());
                                                                        finalTempteach.setGhichu(textField4.getText());
                                                                        finalTempteach.setHinhanh(textField5.getText());
                                                                        teacherList.add(poss, finalTempteach);
                                                                        Configuration config2 = new Configuration().configure().addAnnotatedClass(Teacher.class);
                                                                        SessionFactory sf2 = config2.buildSessionFactory();
                                                                        org.hibernate.Session session2 = sf2.openSession();
                                                                        Transaction tx2 = session2.beginTransaction();
                                                                        session2.save(teacherList.get(poss));
                                                                        tx2.commit();
                                                                        session2.close();

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
                                                        if(phu.getType()!=null)
                                                        {
                                                            for(int m=0;m<Accounts.size();m++)
                                                            {
                                                                if(Accounts.get(m).getType().equals("Teacher")&&Accounts.get(m).getUsername().equals(phu.getUsername()))
                                                                {
                                                                        Accounts.get(m).setPassword(Accounts.get(m).getUsername());
                                                                        Configuration config = new Configuration().configure().addAnnotatedClass(Account.class);
                                                                        SessionFactory sf = config.buildSessionFactory();
                                                                        org.hibernate.Session session2 = sf.openSession();
                                                                        Transaction tx = session2.beginTransaction();
                                                                        session2.update(Accounts.get(m));
                                                                        tx.commit();
                                                                        session2.close();
                                                                        break;
                                                                }
                                                            }
                                                            Vector list = new Vector();
                                                            for (int zz = 0; zz < Accounts.size(); zz++) {
                                                                if(Accounts.get(zz).getType().equals("Teacher")) {
                                                                    Vector tmp = new Vector();
                                                                    tmp.add(Accounts.get(zz).getUsername());
                                                                    tmp.add(Accounts.get(zz).getPassword());
                                                                    list.add(tmp);
                                                                }
                                                            }
                                                            table.setModel(new DefaultTableModel(list, Header));
                                                        }
                                                        else
                                                        {
                                                        int vitri = table.getSelectedRow();
                                                        int dem=0;
                                                        for(int m=0;m<Accounts.size();m++)
                                                        {
                                                            if(Accounts.get(m).getType().equals("Teacher"))
                                                            {
                                                                if(dem==vitri)
                                                                {
                                                                    Accounts.get(m).setPassword(Accounts.get(m).getUsername());
                                                                    Configuration config = new Configuration().configure().addAnnotatedClass(Account.class);
                                                                    SessionFactory sf = config.buildSessionFactory();
                                                                    org.hibernate.Session session2 = sf.openSession();
                                                                    Transaction tx = session2.beginTransaction();
                                                                    session2.saveOrUpdate(Accounts.get(m));
                                                                    tx.commit();
                                                                    session2.close();
                                                                    break;
                                                                }
                                                                dem++;
                                                            }
                                                        }
                                                        Vector list = new Vector();
                                                        for (int zz = 0; zz < Accounts.size(); zz++) {
                                                            if(Accounts.get(zz).getType().equals("Teacher")) {
                                                                Vector tmp = new Vector();
                                                                tmp.add(Accounts.get(zz).getUsername());
                                                                tmp.add(Accounts.get(zz).getPassword());
                                                                list.add(tmp);
                                                            }
                                                        }
                                                        table.setModel(new DefaultTableModel(list, Header));}
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
                                                        JOptionPane.showMessageDialog(null, "Xóa thành công!");
                                                        if(phu.getType()!=null)
                                                        {
                                                            for(int m=0;m<Accounts.size();m++)
                                                            {
                                                                if(Accounts.get(m).getType().equals("Teacher")&&Accounts.get(m).getUsername().equals(phu.getUsername()))
                                                                {
                                                                    org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                                    Transaction transaction=session.getTransaction();
                                                                    transaction.begin();
                                                                    if(Accounts.get(m)!=null) session.remove(Accounts.get(m));
                                                                    transaction.commit();
                                                                    session.close();
                                                                    Accounts.remove(m);
                                                                    break;
                                                                }
                                                            }
                                                            Vector list = new Vector();
                                                            for (int zz = 0; zz < Accounts.size(); zz++) {
                                                                if(Accounts.get(zz).getType().equals("Teacher")) {
                                                                    Vector tmp = new Vector();
                                                                    tmp.add(Accounts.get(zz).getUsername());
                                                                    tmp.add(Accounts.get(zz).getPassword());
                                                                    list.add(tmp);
                                                                }
                                                            }
                                                            table.setModel(new DefaultTableModel(list, Header));
                                                        }
                                                        else
                                                        {
                                                        int position = table.getSelectedRow();
                                                        int dem=0;
                                                        String name="";
                                                        for(int m=0;m<Accounts.size();m++)
                                                        {
                                                            if(Accounts.get(m).getType().equals("Teacher"))
                                                            {
                                                                if(dem==position)
                                                                {
                                                                    name+=Accounts.get(m).getUsername();
                                                                    org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                                    Transaction transaction=session.getTransaction();
                                                                    transaction.begin();
                                                                    if(Accounts.get(m)!=null) session.remove(Accounts.get(m));
                                                                    transaction.commit();
                                                                    session.close();
                                                                    Accounts.remove(m);
                                                                    break;
                                                                }
                                                                dem++;
                                                            }
                                                        }
                                                        for(int mm=0;mm<teacherList.size();mm++)
                                                        {
                                                            if(name.equals(teacherList.get(mm).getUsername()))
                                                            {
                                                                org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                                Transaction transaction=session.getTransaction();
                                                                transaction.begin();
                                                                if(teacherList.get(mm)!=null) session.remove(teacherList.get(mm));
                                                                transaction.commit();
                                                                session.close();
                                                                teacherList.remove(mm);
                                                                break;
                                                            }
                                                        }
                                                        Vector List = new Vector();
                                                        for (int zzz = 0; zzz < Accounts.size(); zzz++) {
                                                            if(Accounts.get(zzz).getType().equals("Teacher"))
                                                            {
                                                            Vector tm = new Vector();
                                                            tm.add(Accounts.get(zzz).getUsername());
                                                            tm.add(Accounts.get(zzz).getPassword());
                                                            List.add(tm);
                                                            }
                                                        }
                                                        table.setModel(new DefaultTableModel(List, Header));
                                                    }}
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
                                                                    for (int l = 0; l < Accounts.size(); l++) {
                                                                        if (user.equals(Accounts.get(l).getUsername())) {
                                                                            Accounts.get(l).setPassword(jtextField1.getText());
                                                                            org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                                            Transaction transaction=session.getTransaction();
                                                                            transaction.begin();
                                                                            if(Accounts.get(l)!=null) session.saveOrUpdate(Accounts.get(l));
                                                                            transaction.commit();
                                                                            session.close();
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
                                            for (int ii = 0; ii < sbjList.size(); ii++) {
                                                Vector temp = new Vector();
                                                temp.add(sbjList.get(ii).getMaMh());
                                                temp.add(sbjList.get(ii).getTenMh());
                                                temp.add(sbjList.get(ii).getTinchi());
                                                data.add(temp);
                                            }
                                            JTable table = new JTable(data, Header);
                                            JScrollPane sp = new JScrollPane(table);
                                            Subject phu=new Subject();
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
                                                                    for (int i = 0; i < sbjList.size(); i++) {
                                                                        if (name.equals(sbjList.get(i).getTenMh())) {
                                                                            check = true;
                                                                            Vector acc = new Vector();
                                                                            phu.setMaMh(sbjList.get(i).getMaMh());
                                                                            phu.setTenMh(sbjList.get(i).getTenMh());
                                                                            phu.setTinchi(sbjList.get(i).getTinchi());
                                                                            acc.add(sbjList.get(i).getMaMh());
                                                                            acc.add(sbjList.get(i).getTenMh());
                                                                            acc.add(sbjList.get(i).getTinchi());
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
                                                                        for (int jj = 0; jj < sbjList.size(); jj++) {
                                                                            if (ma.equals(sbjList.get(jj).getMaMh())) {
                                                                                kt = true;
                                                                                JOptionPane.showMessageDialog(null, "Môn học đã tồn tại");
                                                                            }
                                                                        }
                                                                        if (kt == false) {
                                                                            Subject newSub = new Subject(ma, ten, tinchi);
                                                                            sbjList.add(newSub);
                                                                            Configuration config = new Configuration().configure().addAnnotatedClass(Subject.class);
                                                                            SessionFactory sf = config.buildSessionFactory();
                                                                            org.hibernate.Session session2 = sf.openSession();
                                                                            Transaction tx = session2.beginTransaction();
                                                                            session2.save(newSub);
                                                                            tx.commit();
                                                                            session2.close();
                                                                            Vector dulieu = new Vector();
                                                                            for (int iii = 0; iii < sbjList.size(); iii++) {
                                                                                Vector phu = new Vector();
                                                                                phu.add(sbjList.get(iii).getMaMh());
                                                                                phu.add(sbjList.get(iii).getTenMh());
                                                                                phu.add(sbjList.get(iii).getTinchi());
                                                                                dulieu.add(phu);
                                                                            }
                                                                            table.setModel(new DefaultTableModel(dulieu, Header));
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
                                                            Subject tempsub=new Subject();
                                                            if(phu.getMaMh()!=null)
                                                            {
                                                                tempsub=phu;
                                                            }
                                                            else
                                                            {tempsub = (Subject) sbjList.get(table.getSelectedRow());}
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
                                                            textField.setText(String.valueOf(tempsub.getMaMh()));
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
                                                            textField1.setText(tempsub.getTenMh());
                                                            textField1.setPreferredSize(new Dimension(500, 30));
                                                            panel1.add(label2);
                                                            panel1.add(textField1);
                                                            JPanel panel2 = new JPanel();
                                                            JLabel label3 = new JLabel();
                                                            label3.setText("Số tín chỉ:");
                                                            JTextField textField2 = new JTextField();
                                                            textField2.setText(String.valueOf(tempsub.getTinchi()));
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
                                                            hibernate.Subject finalTempsub = tempsub;
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
                                                                            if(phu.getMaMh()!=null)
                                                                            {
                                                                                for(int i1=0;i1<sbjList.size();i1++)
                                                                                {
                                                                                    if(finalTempsub.getMaMh().equals(sbjList.get(i1).getMaMh()))
                                                                                    {
                                                                                        org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                                                        Transaction transaction=session.getTransaction();
                                                                                        transaction.begin();
                                                                                        if(sbjList.get(i1)!=null) session.remove(sbjList.get(i1));
                                                                                        transaction.commit();
                                                                                        session.close();
                                                                                        sbjList.remove(i1);
                                                                                        finalTempsub.setMaMh(textField.getText());
                                                                                        finalTempsub.setTenMh(textField1.getText());
                                                                                        finalTempsub.setTinchi(Integer.parseInt(textField2.getText()));
                                                                                        sbjList.add(i1, finalTempsub);
                                                                                        Configuration config2 = new Configuration().configure().addAnnotatedClass(Subject.class);
                                                                                        SessionFactory sf2 = config2.buildSessionFactory();
                                                                                        org.hibernate.Session session2 = sf2.openSession();
                                                                                        Transaction tx2 = session2.beginTransaction();
                                                                                        session2.save(sbjList.get(i1));
                                                                                        tx2.commit();
                                                                                        session2.close();
                                                                                    }
                                                                                }
                                                                                Vector dulieu = new Vector();
                                                                                for (int iii = 0; iii < sbjList.size(); iii++) {
                                                                                    Vector phu = new Vector();
                                                                                    phu.add(sbjList.get(iii).getMaMh());
                                                                                    phu.add(sbjList.get(iii).getTenMh());
                                                                                    phu.add(sbjList.get(iii).getTinchi());
                                                                                    dulieu.add(phu);
                                                                                }
                                                                                table.setModel(new DefaultTableModel(dulieu, Header));
                                                                            }
                                                                            else
                                                                            {
                                                                            int posz = table.getSelectedRow();
                                                                            org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                                            Transaction transaction=session.getTransaction();
                                                                            transaction.begin();
                                                                            if(sbjList.get(posz)!=null) session.remove(sbjList.get(posz));
                                                                            transaction.commit();
                                                                            session.close();
                                                                            sbjList.remove(posz);
                                                                            finalTempsub.setMaMh(textField.getText());
                                                                            finalTempsub.setTenMh(textField1.getText());
                                                                            finalTempsub.setTinchi(Integer.parseInt(textField2.getText()));
                                                                            sbjList.add(posz, finalTempsub);
                                                                            Configuration config2 = new Configuration().configure().addAnnotatedClass(Subject.class);
                                                                            SessionFactory sf2 = config2.buildSessionFactory();
                                                                            org.hibernate.Session session2 = sf2.openSession();
                                                                            Transaction tx2 = session2.beginTransaction();
                                                                            session2.save(sbjList.get(posz));
                                                                            tx2.commit();
                                                                            session2.close();
                                                                            Vector dulieu = new Vector();
                                                                            for (int iii = 0; iii < sbjList.size(); iii++) {
                                                                                Vector phu = new Vector();
                                                                                phu.add(sbjList.get(iii).getMaMh());
                                                                                phu.add(sbjList.get(iii).getTenMh());
                                                                                phu.add(sbjList.get(iii).getTinchi());
                                                                                dulieu.add(phu);
                                                                            }
                                                                            table.setModel(new DefaultTableModel(dulieu, Header));
                                                                        }}
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
                                                            JOptionPane.showMessageDialog(null, "Đã xóa thành công!");
                                                            if(phu.getMaMh()!=null)
                                                            {
                                                                for(int i1=0;i1<sbjList.size();i1++)
                                                                {
                                                                    if(phu.getMaMh().equals(sbjList.get(i1).getMaMh()))
                                                                    {
                                                                        org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                                        Transaction transaction=session.getTransaction();
                                                                        transaction.begin();
                                                                        if(sbjList.get(i1)!=null) session.remove(sbjList.get(i1));
                                                                        transaction.commit();
                                                                        session.close();
                                                                        sbjList.remove(i1);
                                                                    }
                                                                }
                                                                Vector List = new Vector();
                                                                for (int zzz = 0; zzz < sbjList.size(); zzz++) {
                                                                    Vector tm = new Vector();
                                                                    tm.add(sbjList.get(zzz).getMaMh());
                                                                    tm.add(sbjList.get(zzz).getTenMh());
                                                                    tm.add(sbjList.get(zzz).getTinchi());
                                                                    List.add(tm);
                                                                }
                                                                table.setModel(new DefaultTableModel(List, Header));
                                                            }
                                                            else{
                                                            int position = table.getSelectedRow();
                                                            org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                            Transaction transaction=session.getTransaction();
                                                            transaction.begin();
                                                            if(sbjList.get(position)!=null) session.remove(sbjList.get(position));
                                                            transaction.commit();
                                                            session.close();
                                                            sbjList.remove(position);
                                                            Vector List = new Vector();
                                                            for (int zzz = 0; zzz < sbjList.size(); zzz++) {
                                                                Vector tm = new Vector();
                                                                tm.add(sbjList.get(zzz).getMaMh());
                                                                tm.add(sbjList.get(zzz).getTenMh());
                                                                tm.add(sbjList.get(zzz).getTinchi());
                                                                List.add(tm);
                                                            }
                                                            table.setModel(new DefaultTableModel(List, Header));
                                                        }}
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
                                            Header.add("Mã học kỳ");
                                            Header.add("Tên học kỳ");
                                            Header.add("Năm học");
                                            Header.add("Ngày bắt đầu");
                                            Header.add("Ngày kết thúc");
                                            Vector data = new Vector();
                                            for (int ii = 0; ii < semesterList.size(); ii++) {
                                                Vector temp = new Vector();
                                                temp.add(semesterList.get(ii).getMaHk());
                                                temp.add(semesterList.get(ii).getTenHk());
                                                temp.add(semesterList.get(ii).getYear());
                                                temp.add(semesterList.get(ii).getNgayBd());
                                                temp.add(semesterList.get(ii).getNgayKt());
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
                                                            currentSemester[0] = semesterList.get(pos);
                                                            JOptionPane.showMessageDialog(null, "Đã set thành học kỳ hiện tại!");
                                                            curSes.setText("Học kỳ hiện tại:" + currentSemester[0].getTenHk() + "," + currentSemester[0].getYear() + "|");
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
                                                        JPanel Jpanelma = new JPanel();
                                                        JLabel Jlabelma = new JLabel("Mã học kỳ:");
                                                        Jpanelma.setLayout(new FlowLayout());
                                                        JTextField JtextFieldma = new JTextField();
                                                        JtextFieldma.setPreferredSize(new Dimension(300, 30));
                                                        Jpanelma.add(Jlabelma);
                                                        Jpanelma.add(JtextFieldma);
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
                                                        JLabel Jlabel2 = new JLabel("Ngày bắt đầu(yyyy-mm-dd):");
                                                        Jpanel2.setLayout(new FlowLayout());
                                                        JTextField JtextField2 = new JTextField();
                                                        JtextField2.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel2.add(Jlabel2);
                                                        Jpanel2.add(JtextField2);
                                                        JPanel Jpanel3 = new JPanel();
                                                        JLabel Jlabel3 = new JLabel("Ngày kết thúc(yyyy-mm-dd):");
                                                        Jpanel3.setLayout(new FlowLayout());
                                                        JTextField JtextField3 = new JTextField();
                                                        JtextField3.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel3.add(Jlabel3);
                                                        Jpanel3.add(JtextField3);
                                                        JPanel BPanel = new JPanel();
                                                        BoxLayout boxLayout1 = new BoxLayout(BPanel, BoxLayout.Y_AXIS);
                                                        BPanel.setLayout(boxLayout1);
                                                        BPanel.add(Jpanelma);
                                                        BPanel.add(Jpanel);
                                                        BPanel.add(Jpanel1);
                                                        BPanel.add(Jpanel2);
                                                        BPanel.add(Jpanel3);
                                                        JButton Them = new JButton("Thêm");
                                                        Them.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == Them) {
                                                                    if (JtextFieldma.getText().equals("")||JtextField.getText().equals("") || JtextField1.getText().equals("") ||
                                                                            JtextField2.getText().equals("") || JtextField3.getText().equals("")) {
                                                                        JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                                                                    } else {
                                                                        String mahk=JtextFieldma.getText();
                                                                        String tenhk = JtextField.getText();
                                                                        int namhoc = Integer.parseInt(JtextField1.getText());
                                                                        String strstart=JtextField2.getText();
                                                                        Date start =Date.valueOf(strstart);
                                                                        String strend=JtextField3.getText();
                                                                        Date end = Date.valueOf(strend);
                                                                        boolean kt = false;
                                                                        for (int jj = 0; jj < semesterList.size(); jj++) {
                                                                            if (namhoc == semesterList.get(jj).getYear() && start == semesterList.get(jj).getNgayBd() && end == semesterList.get(jj).getNgayKt()) {
                                                                                kt = true;
                                                                                JOptionPane.showMessageDialog(null, "Học kỳ đã tồn tại");
                                                                            }
                                                                        }
                                                                        if (kt == false) {
                                                                            Semester newSem = new Semester(mahk,tenhk, namhoc, start, end);
                                                                            semesterList.add(newSem);
                                                                            Configuration config = new Configuration().configure().addAnnotatedClass(Semester.class);
                                                                            SessionFactory sf = config.buildSessionFactory();
                                                                            org.hibernate.Session session2 = sf.openSession();
                                                                            Transaction tx = session2.beginTransaction();
                                                                            session2.save(newSem);
                                                                            tx.commit();
                                                                            session2.close();
                                                                            Vector dulieu = new Vector();
                                                                            for (int iii = 0; iii < semesterList.size(); iii++) {
                                                                                Vector phu = new Vector();
                                                                                phu.add(semesterList.get(iii).getMaHk());
                                                                                phu.add(semesterList.get(iii).getTenHk());
                                                                                phu.add(semesterList.get(iii).getYear());
                                                                                phu.add(semesterList.get(iii).getNgayBd());
                                                                                phu.add(semesterList.get(iii).getNgayKt());
                                                                                dulieu.add(phu);
                                                                            }
                                                                            table.setModel(new DefaultTableModel(dulieu, Header));
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
                                                            JOptionPane.showMessageDialog(null, "Đã xóa thành công!");
                                                            int position = table.getSelectedRow();
                                                            org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                            Transaction transaction=session.getTransaction();
                                                            transaction.begin();
                                                            if(semesterList.get(position)!=null) session.remove(semesterList.get(position));
                                                            transaction.commit();
                                                            session.close();
                                                            semesterList.remove(position);
                                                            Vector List = new Vector();
                                                            for (int zzz = 0; zzz < semesterList.size(); zzz++) {
                                                                Vector tm = new Vector();
                                                                tm.add(semesterList.get(zzz).getMaHk());
                                                                tm.add(semesterList.get(zzz).getTenHk());
                                                                tm.add(semesterList.get(zzz).getYear());
                                                                tm.add(semesterList.get(zzz).getNgayBd());
                                                                tm.add(semesterList.get(zzz).getNgayKt());
                                                                List.add(tm);
                                                            }
                                                            table.setModel(new DefaultTableModel(List, Header));




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
                                            for (int ii = 0; ii < classList.size(); ii++) {
                                                Vector temp = new Vector();
                                                temp.add(classList.get(ii).getTenLop());
                                                temp.add(classList.get(ii).getSlsv());
                                                temp.add(classList.get(ii).getSlNam());
                                                temp.add(classList.get(ii).getSlNu());
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
                                                                        for (int jj = 0; jj < classList.size(); jj++) {
                                                                            if (tenlop.equals(classList.get(jj).getTenLop())) {
                                                                                kt = true;
                                                                                JOptionPane.showMessageDialog(null, "Lớp đã tồn tại!");
                                                                            }
                                                                        }
                                                                        if (kt == false) {
                                                                            Clazz newClass = new Clazz(tenlop, sl, slnam, slnu);
                                                                            classList.add(newClass);
                                                                            Configuration config = new Configuration().configure().addAnnotatedClass(Clazz.class);
                                                                            SessionFactory sf = config.buildSessionFactory();
                                                                            org.hibernate.Session session2 = sf.openSession();
                                                                            Transaction tx = session2.beginTransaction();
                                                                            session2.save(newClass);
                                                                            tx.commit();
                                                                            session2.close();
                                                                            Vector dulieu = new Vector();
                                                                            for (int iii = 0; iii < classList.size(); iii++) {
                                                                                Vector phu = new Vector();
                                                                                phu.add(classList.get(iii).getTenLop());
                                                                                phu.add(classList.get(iii).getSlsv());
                                                                                phu.add(classList.get(iii).getSlNam());
                                                                                phu.add(classList.get(iii).getSlNu());
                                                                                dulieu.add(phu);
                                                                            }
                                                                            table.setModel(new DefaultTableModel(dulieu, Header));
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
                                                            JOptionPane.showMessageDialog(null, "Đã xóa thành công!");
                                                            int position = table.getSelectedRow();
                                                            org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                            Transaction transaction=session.getTransaction();
                                                            transaction.begin();
                                                            if(classList.get(position)!=null) session.remove(classList.get(position));
                                                            transaction.commit();
                                                            session.close();
                                                            classList.remove(position);
                                                            Vector List = new Vector();
                                                            for (int zzz = 0; zzz < classList.size(); zzz++) {
                                                                Vector tm = new Vector();
                                                                tm.add(classList.get(zzz).getTenLop());
                                                                tm.add(classList.get(zzz).getSlsv());
                                                                tm.add(classList.get(zzz).getSlNam());
                                                                tm.add(classList.get(zzz).getSlNu());
                                                                List.add(tm);
                                                            }
                                                            table.setModel(new DefaultTableModel(List, Header));

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
                                            for (int ii = 0; ii < stdList.size(); ii++) {
                                                Vector temp = new Vector();
                                                temp.add(stdList.get(ii).getMhs());
                                                temp.add(stdList.get(ii).getTenHs());
                                                temp.add(stdList.get(ii).getDiem());
                                                temp.add(stdList.get(ii).getDiachi());
                                                temp.add(stdList.get(ii).getLop());
                                                temp.add(stdList.get(ii).getHinhanh());
                                                data.add(temp);
                                            }
                                            JTable table = new JTable(data, Header);
                                            JScrollPane sp = new JScrollPane(table);
                                            Student phu=new Student();
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
                                                                    for (int i = 0; i < stdList.size(); i++) {
                                                                        if (ma == stdList.get(i).getMhs()) {
                                                                            check = true;
                                                                            Vector DuLieu = new Vector();
                                                                            Vector acc = new Vector();
                                                                            phu.setMhs(stdList.get(i).getMhs());
                                                                            phu.setTenHs(stdList.get(i).getTenHs());
                                                                            phu.setDiem(stdList.get(i).getDiem());
                                                                            phu.setDiachi(stdList.get(i).getDiachi());
                                                                            phu.setLop(stdList.get(i).getLop());
                                                                            phu.setHinhanh(stdList.get(i).getHinhanh());
                                                                            acc.add(stdList.get(i).getMhs());
                                                                            acc.add(stdList.get(i).getTenHs());
                                                                            acc.add(stdList.get(i).getDiem());
                                                                            acc.add(stdList.get(i).getDiachi());
                                                                            acc.add(stdList.get(i).getLop());
                                                                            acc.add(stdList.get(i).getHinhanh());
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
                                                        JPanel Jpanel6 = new JPanel();
                                                        JLabel Jlabel6 = new JLabel("Username:");
                                                        Jpanel6.setLayout(new FlowLayout());
                                                        JTextField JtextField6 = new JTextField();
                                                        JtextField6.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel6.add(Jlabel6);
                                                        Jpanel6.add(JtextField6);
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
                                                        JButton Them = new JButton("Thêm");
                                                        Them.addActionListener(new ActionListener() {
                                                            @Override
                                                            public void actionPerformed(ActionEvent e) {
                                                                if (e.getSource() == Them) {
                                                                    if (JtextField.getText().equals("") || JtextField1.getText().equals("")
                                                                            || JtextField2.getText().equals("") || JtextField3.getText().equals("") ||
                                                                            JtextField4.getText().equals("") || JtextField5.getText().equals("")||
                                                                            JtextField6.getText().equals("")) {
                                                                        JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                                                                    } else {
                                                                        int ma = Integer.parseInt(JtextField.getText());
                                                                        String ten = JtextField1.getText();
                                                                        double diem = Double.parseDouble(JtextField2.getText());
                                                                        String diachi = JtextField3.getText();
                                                                        String lop = JtextField4.getText();
                                                                        String hinhanh = JtextField5.getText();
                                                                        String us=JtextField6.getText();
                                                                        boolean kt = false;
                                                                        for (int jj = 0; jj < stdList.size(); jj++) {
                                                                            if (ma == stdList.get(jj).getMhs()) {
                                                                                kt = true;
                                                                                JOptionPane.showMessageDialog(null, "Sinh viên đã tồn tại");
                                                                            }
                                                                        }
                                                                        if (kt == false) {
                                                                            Student hocSinh = new Student(ma, ten, diem, hinhanh, diachi, lop,us);
                                                                            Configuration config = new Configuration().configure().addAnnotatedClass(Student.class);
                                                                            SessionFactory sf = config.buildSessionFactory();
                                                                            org.hibernate.Session session2 = sf.openSession();
                                                                            Transaction tx = session2.beginTransaction();
                                                                            session2.save(hocSinh);
                                                                            tx.commit();
                                                                            session2.close();
                                                                            stdList.add(hocSinh);
                                                                            for (int q = 0; q < classList.size(); q++) {
                                                                                if (lop.equals(classList.get(q).getTenLop())) {
                                                                                    Vector<Student> cllist = classList.get(q).getSVList();
                                                                                    cllist.add(hocSinh);
                                                                                    classList.get(q).setSVList(cllist);
                                                                                }
                                                                            }
                                                                            Vector dulieu = new Vector();
                                                                            for (int iii = 0; iii < stdList.size(); iii++) {
                                                                                Vector phu = new Vector();
                                                                                phu.add(stdList.get(iii).getMhs());
                                                                                phu.add(stdList.get(iii).getTenHs());
                                                                                phu.add(stdList.get(iii).getDiem());
                                                                                phu.add(stdList.get(iii).getDiachi());
                                                                                phu.add(stdList.get(iii).getLop());
                                                                                phu.add(stdList.get(iii).getHinhanh());
                                                                                dulieu.add(phu);
                                                                            }
                                                                            table.setModel(new DefaultTableModel(dulieu, Header));
                                                                            try {
                                                                                LamTrangFile("DuLieuLop.txt");
                                                                                GhiFileLop(classList);
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
                                                            Student hs=new Student();
                                                            int posss = table.getSelectedRow();
                                                            if(phu.getTenHs()!=null)
                                                            {
                                                                hs=phu;
                                                            }
                                                            else
                                                            {hs = (Student) stdList.get(posss);}
                                                            JFrame jFrame1 = new JFrame();
                                                            jFrame1.setTitle("Update");
                                                            ImageIcon Icon = new ImageIcon("update.png");
                                                            jFrame1.setIconImage(Icon.getImage());
                                                            jFrame1.setSize(520, 450);
                                                            jFrame1.setResizable(false);
                                                            jFrame1.setVisible(true);
                                                            JPanel panel = new JPanel();
                                                            JLabel label1 = new JLabel();
                                                            label1.setText("Mã sinh viên:");
                                                            JTextField textField = new JTextField();
                                                            textField.setText(String.valueOf(hs.getMhs()));
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
                                                            textField1.setText(hs.getTenHs());
                                                            textField1.setPreferredSize(new Dimension(500, 30));
                                                            panel1.add(label2);
                                                            panel1.add(textField1);
                                                            JPanel panel2 = new JPanel();
                                                            JLabel label3 = new JLabel();
                                                            label3.setText("Điểm:");
                                                            JTextField textField2 = new JTextField();
                                                            textField2.setText(String.valueOf(hs.getDiem()));
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
                                                            textField3.setText(hs.getDiachi());
                                                            textField3.setPreferredSize(new Dimension(500, 30));
                                                            panel3.add(label4);
                                                            panel3.add(textField3);
                                                            JPanel panel4 = new JPanel();
                                                            JLabel label5 = new JLabel();
                                                            label5.setText("Lớp:");
                                                            JTextField textField4 = new JTextField();
                                                            textField4.setText(hs.getLop());
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
                                                            textField5.setText(hs.getHinhanh());
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
                                                            Student finalHs = hs;
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
                                                                            if(phu.getTenHs()!=null)
                                                                            {
                                                                                for(int i1=0;i1<stdList.size();i1++)
                                                                                {
                                                                                    if(phu.getMhs()==stdList.get(i1).getMhs())
                                                                                    {
                                                                                        org.hibernate.Session session = hibernateUtil.getSessionFactory().openSession();
                                                                                        Transaction transaction = session.getTransaction();
                                                                                        transaction.begin();
                                                                                        if (stdList.get(i1) != null)
                                                                                            session.remove(stdList.get(i1));
                                                                                        transaction.commit();
                                                                                        session.close();
                                                                                        finalHs.setUsername(stdList.get(i1).getUsername());
                                                                                        stdList.remove(i1);
                                                                                        finalHs.setMhs(Integer.parseInt(textField.getText()));
                                                                                        finalHs.setTenHs(textField1.getText());
                                                                                        finalHs.setDiem(Double.parseDouble(textField2.getText()));
                                                                                        finalHs.setLop(textField3.getText());
                                                                                        finalHs.setDiachi(textField4.getText());
                                                                                        finalHs.setHinhanh(textField5.getText());
                                                                                        stdList.add(i1, finalHs);
                                                                                        Configuration config2 = new Configuration().configure().addAnnotatedClass(Student.class);
                                                                                        SessionFactory sf2 = config2.buildSessionFactory();
                                                                                        org.hibernate.Session session2 = sf2.openSession();
                                                                                        Transaction tx2 = session2.beginTransaction();
                                                                                        session2.save(stdList.get(i1));
                                                                                        tx2.commit();
                                                                                        session2.close();
                                                                                    }
                                                                                }
                                                                                Vector dulieu = new Vector();
                                                                                for (int i1 = 0; i1 < stdList.size(); i1++) {
                                                                                    Vector tm = new Vector();
                                                                                    tm.add(stdList.get(i1).getMhs());
                                                                                    tm.add(stdList.get(i1).getTenHs());
                                                                                    tm.add(stdList.get(i1).getDiem());
                                                                                    tm.add(stdList.get(i1).getDiachi());
                                                                                    tm.add(stdList.get(i1).getLop());
                                                                                    tm.add(stdList.get(i1).getHinhanh());
                                                                                    dulieu.add(tm);
                                                                                }
                                                                                table.setModel(new DefaultTableModel(dulieu, Header));
                                                                            }
                                                                            else {
                                                                                org.hibernate.Session session = hibernateUtil.getSessionFactory().openSession();
                                                                                Transaction transaction = session.getTransaction();
                                                                                transaction.begin();
                                                                                if (stdList.get(posss) != null)
                                                                                    session.remove(stdList.get(posss));
                                                                                transaction.commit();
                                                                                session.close();
                                                                                finalHs.setUsername(stdList.get(posss).getUsername());
                                                                                stdList.remove(posss);
                                                                                finalHs.setMhs(Integer.parseInt(textField.getText()));
                                                                                finalHs.setTenHs(textField1.getText());
                                                                                finalHs.setDiem(Double.parseDouble(textField2.getText()));
                                                                                finalHs.setLop(textField3.getText());
                                                                                finalHs.setDiachi(textField4.getText());
                                                                                finalHs.setHinhanh(textField5.getText());
                                                                                stdList.add(posss, finalHs);
                                                                                Configuration config2 = new Configuration().configure().addAnnotatedClass(Student.class);
                                                                                SessionFactory sf2 = config2.buildSessionFactory();
                                                                                org.hibernate.Session session2 = sf2.openSession();
                                                                                Transaction tx2 = session2.beginTransaction();
                                                                                session2.save(stdList.get(posss));
                                                                                tx2.commit();
                                                                                session2.close();
                                                                                Vector dulieu = new Vector();
                                                                                for (int i1 = 0; i1 < stdList.size(); i1++) {
                                                                                    Vector tm = new Vector();
                                                                                    tm.add(stdList.get(i1).getMhs());
                                                                                    tm.add(stdList.get(i1).getTenHs());
                                                                                    tm.add(stdList.get(i1).getDiem());
                                                                                    tm.add(stdList.get(i1).getDiachi());
                                                                                    tm.add(stdList.get(i1).getLop());
                                                                                    tm.add(stdList.get(i1).getHinhanh());
                                                                                    dulieu.add(tm);
                                                                                }
                                                                                table.setModel(new DefaultTableModel(dulieu, Header));
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
                                                            if(phu.getTenHs()!=null)
                                                            {
                                                                for(int i1=0;i1<stdList.size();i1++)
                                                                {
                                                                    if(phu.getMhs()==stdList.get(i1).getMhs())
                                                                    {
                                                                        stdList.get(i1).setUsername(String.valueOf(stdList.get(i1).getMhs()));
                                                                        Configuration config2 = new Configuration().configure().addAnnotatedClass(Student.class);
                                                                        SessionFactory sf2 = config2.buildSessionFactory();
                                                                        org.hibernate.Session session2 = sf2.openSession();
                                                                        Transaction tx2 = session2.beginTransaction();
                                                                        session2.update(stdList.get(i1));
                                                                        tx2.commit();
                                                                        session2.close();
                                                                        Vector list = new Vector();
                                                                        for (int zz = 0; zz < stdList.size(); zz++) {
                                                                            Vector tmp = new Vector();
                                                                            tmp.add(stdList.get(zz).getMhs());
                                                                            tmp.add(stdList.get(zz).getTenHs());
                                                                            tmp.add(stdList.get(zz).getDiem());
                                                                            tmp.add(stdList.get(zz).getDiachi());
                                                                            tmp.add(stdList.get(zz).getLop());
                                                                            tmp.add(stdList.get(zz).getHinhanh());
                                                                            list.add(tmp);
                                                                        }
                                                                        table.setModel(new DefaultTableModel(list, Header));
                                                                        Account moi = new Account(String.valueOf(stdList.get(i1).getMhs()), String.valueOf(stdList.get(i1).getMhs()), "Student");
                                                                        Accounts.add(moi);
                                                                        Configuration config3 = new Configuration().configure().addAnnotatedClass(Account.class);
                                                                        SessionFactory sf3 = config3.buildSessionFactory();
                                                                        org.hibernate.Session session3 = sf3.openSession();
                                                                        Transaction tx3 = session3.beginTransaction();
                                                                        session3.save(moi);
                                                                        tx3.commit();
                                                                        session3.close();
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            else {
                                                                stdList.get(vitri).setUsername(String.valueOf(stdList.get(vitri).getMhs()));
                                                                Configuration config2 = new Configuration().configure().addAnnotatedClass(Student.class);
                                                                SessionFactory sf2 = config2.buildSessionFactory();
                                                                org.hibernate.Session session2 = sf2.openSession();
                                                                Transaction tx2 = session2.beginTransaction();
                                                                session2.update(stdList.get(vitri));
                                                                tx2.commit();
                                                                session2.close();
                                                                Vector list = new Vector();
                                                                for (int zz = 0; zz < stdList.size(); zz++) {
                                                                    Vector tmp = new Vector();
                                                                    tmp.add(stdList.get(zz).getMhs());
                                                                    tmp.add(stdList.get(zz).getTenHs());
                                                                    tmp.add(stdList.get(zz).getDiem());
                                                                    tmp.add(stdList.get(zz).getDiachi());
                                                                    tmp.add(stdList.get(zz).getLop());
                                                                    tmp.add(stdList.get(zz).getHinhanh());
                                                                    list.add(tmp);
                                                                }
                                                                table.setModel(new DefaultTableModel(list, Header));
                                                                Account moi = new Account(String.valueOf(stdList.get(vitri).getMhs()), String.valueOf(stdList.get(vitri).getMhs()), "Student");
                                                                Accounts.add(moi);
                                                                Configuration config3 = new Configuration().configure().addAnnotatedClass(Account.class);
                                                                SessionFactory sf3 = config3.buildSessionFactory();
                                                                org.hibernate.Session session3 = sf3.openSession();
                                                                Transaction tx3 = session3.beginTransaction();
                                                                session3.save(moi);
                                                                tx3.commit();
                                                                session3.close();
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
                                                            Vector<Course> datatemp = stdList.get(posit).getListCourse();
                                                            int size;
                                                            if (datatemp == null) {
                                                                size = 0;
                                                            } else {
                                                                size = datatemp.size();
                                                            }
                                                            for (int d = 0; d < size; d++) {
                                                                Vector phu = new Vector();
                                                                phu.add(datatemp.get(d).getMaMon());
                                                                phu.add(datatemp.get(d).getTenMon());
                                                                phu.add(datatemp.get(d).getSoTinChi());
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
                                            String[] nameclass = new String[classList.size()];
                                            for (int y = 0; y < classList.size(); y++) {
                                                nameclass[y] = "";
                                            }
                                            for (int i1 = 0; i1 < classList.size(); i1++) {
                                                nameclass[i1] += classList.get(i1).getTenLop();
                                            }
                                            JComboBox<String> chooseClass = new JComboBox<>(nameclass);
                                            chooseClass.addItemListener(new ItemListener() {
                                                @Override
                                                public void itemStateChanged(ItemEvent e) {
                                                    if (e.getStateChange() == ItemEvent.SELECTED) {
                                                        Vector DuLieu = new Vector();
                                                        for (int i = 0; i < stdList.size(); i++) {
                                                            if (e.getItem().toString().equals(stdList.get(i).getLop())) {
                                                                Vector acc = new Vector();
                                                                acc.add(stdList.get(i).getMhs());
                                                                acc.add(stdList.get(i).getTenHs());
                                                                acc.add(stdList.get(i).getDiem());
                                                                acc.add(stdList.get(i).getDiachi());
                                                                acc.add(stdList.get(i).getLop());
                                                                acc.add(stdList.get(i).getHinhanh());
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
                                            for (int ii = 0; ii < sessionList.size(); ii++) {
                                                Vector temp = new Vector();
                                                temp.add(sessionList.get(ii).getName());
                                                temp.add(sessionList.get(ii).getStart());
                                                temp.add(sessionList.get(ii).getEnd());
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
                                                        JLabel Jlabel2 = new JLabel("Ngày bắt đầu(yyyy-mm-dd):");
                                                        Jpanel2.setLayout(new FlowLayout());
                                                        JTextField JtextField2 = new JTextField();
                                                        JtextField2.setPreferredSize(new Dimension(300, 30));
                                                        Jpanel2.add(Jlabel2);
                                                        Jpanel2.add(JtextField2);
                                                        JPanel Jpanel3 = new JPanel();
                                                        JLabel Jlabel3 = new JLabel("Ngày kết thúc(yyyy-mm-dd):");
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
                                                                    if(currentSemester[0].getMaHk()==null) JOptionPane.showMessageDialog(null,"Hãy set học kỳ hiện tại");
                                                                    if (JtextField.getText().equals("") || JtextField2.getText().equals("")
                                                                            || JtextField3.getText().equals("")) {
                                                                        JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin");
                                                                    } else {
                                                                        String tenhk = JtextField.getText();
                                                                        String strBD=JtextField2.getText();
                                                                        java.sql.Date start = Date.valueOf(strBD);
                                                                        String strKT=JtextField3.getText();
                                                                        Date end = Date.valueOf(strKT);
                                                                        boolean kt = false;
                                                                        for (int jj = 0; jj < sessionList.size(); jj++) {
                                                                            if (tenhk.equals(sessionList.get(jj).getName()) && start == sessionList.get(jj).getStart()
                                                                                    && end == sessionList.get(jj).getEnd()) {
                                                                                kt = true;
                                                                                JOptionPane.showMessageDialog(null, "Học kỳ đã tồn tại");
                                                                            }
                                                                        }
                                                                        if (kt == false) {
                                                                            Session newSes = new Session(tenhk, start, end);
                                                                            Vector<Session> temp = new Vector<>();
                                                                            temp.add(newSes);
                                                                            for (int x = 0; x < semesterList.size(); x++) {
                                                                                if (currentSemester[0].getMaHk().equals(semesterList.get(x).getMaHk())) {
                                                                                    semesterList.get(x).setDKHP(temp);
                                                                                }
                                                                            }
                                                                            sessionList.add(newSes);
                                                                            Configuration config2 = new Configuration().configure().addAnnotatedClass(Session.class);
                                                                            SessionFactory sf2 = config2.buildSessionFactory();
                                                                            org.hibernate.Session session2 = sf2.openSession();
                                                                            Transaction tx2 = session2.beginTransaction();
                                                                            session2.save(newSes);
                                                                            tx2.commit();
                                                                            session2.close();
                                                                            Vector dulieu = new Vector();
                                                                            for (int iii = 0; iii < sessionList.size(); iii++) {
                                                                                Vector phu = new Vector();
                                                                                phu.add(sessionList.get(iii).getName());
                                                                                phu.add(sessionList.get(iii).getStart());
                                                                                phu.add(sessionList.get(iii).getEnd());
                                                                                dulieu.add(phu);
                                                                            }
                                                                            table.setModel(new DefaultTableModel(dulieu, Header));
                                                                            try {
                                                                                LamTrangFile("DuLieuHocKy.txt");
                                                                                GhiFileSemester(semesterList);
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
                                            for (int ii = 0; ii < courseList.size(); ii++) {
                                                Vector temp = new Vector();
                                                temp.add(courseList.get(ii).getMaMon());
                                                temp.add(courseList.get(ii).getTenMon());
                                                temp.add(courseList.get(ii).getSoTinChi());
                                                temp.add(courseList.get(ii).getGv());
                                                temp.add(courseList.get(ii).getPhongHoc());
                                                temp.add(courseList.get(ii).getDayofWeek());
                                                temp.add(courseList.get(ii).getCa());
                                                temp.add(courseList.get(ii).getMaxSlot());
                                                data.add(temp);
                                            }
                                            JTable table = new JTable(data, Header);
                                            JScrollPane sp = new JScrollPane(table);
                                            Course phu=new Course();
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
                                                                    for (int i = 0; i < courseList.size(); i++) {
                                                                        if (name.equals(courseList.get(i).getTenMon())) {
                                                                            check = true;
                                                                            Vector acc = new Vector();
                                                                            phu.setMaMon(courseList.get(i).getMaMon());
                                                                            phu.setTenMon(courseList.get(i).getTenMon());
                                                                            phu.setSoTinChi(courseList.get(i).getSoTinChi());
                                                                            phu.setGv(courseList.get(i).getGv());
                                                                            phu.setPhongHoc(courseList.get(i).getPhongHoc());
                                                                            phu.setDayofWeek(courseList.get(i).getDayofWeek());
                                                                            phu.setCa(courseList.get(i).getCa());
                                                                            phu.setMaxSlot(courseList.get(i).getMaxSlot());
                                                                            acc.add(courseList.get(i).getMaMon());
                                                                            acc.add(courseList.get(i).getTenMon());
                                                                            acc.add(courseList.get(i).getSoTinChi());
                                                                            acc.add(courseList.get(i).getGv());
                                                                            acc.add(courseList.get(i).getPhongHoc());
                                                                            acc.add(courseList.get(i).getDayofWeek());
                                                                            acc.add(courseList.get(i).getCa());
                                                                            acc.add(courseList.get(i).getMaxSlot());
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
                                                                        for (int jj = 0; jj < courseList.size(); jj++) {
                                                                            if (ma.equals(courseList.get(jj).getMaMon())) {
                                                                                kt = true;
                                                                                JOptionPane.showMessageDialog(null, "Học phần đã tồn tại");
                                                                            }
                                                                        }
                                                                        if (kt == false) {
                                                                            Course newCourse = new Course(ma, ten, tinchi, gv, phong, ngayhoc, cahoc, slot);
                                                                            courseList.add(newCourse);
                                                                            Configuration config2 = new Configuration().configure().addAnnotatedClass(Course.class);
                                                                            SessionFactory sf2 = config2.buildSessionFactory();
                                                                            org.hibernate.Session session2 = sf2.openSession();
                                                                            Transaction tx2 = session2.beginTransaction();
                                                                            session2.save(newCourse);
                                                                            tx2.commit();
                                                                            session2.close();
                                                                            Vector dulieu = new Vector();
                                                                            for (int iii = 0; iii < courseList.size(); iii++) {
                                                                                Vector phu = new Vector();
                                                                                phu.add(courseList.get(iii).getTenMon());
                                                                                phu.add(courseList.get(iii).getMaMon());
                                                                                phu.add(courseList.get(iii).getSoTinChi());
                                                                                phu.add(courseList.get(iii).getGv());
                                                                                phu.add(courseList.get(iii).getPhongHoc());
                                                                                phu.add(courseList.get(iii).getDayofWeek());
                                                                                phu.add(courseList.get(iii).getCa());
                                                                                phu.add(courseList.get(iii).getMaxSlot());
                                                                                dulieu.add(phu);
                                                                            }
                                                                            table.setModel(new DefaultTableModel(dulieu, Header));




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
                                                            JOptionPane.showMessageDialog(null, "Đã xóa thành công!");
                                                            if(phu.getMaMon()!=null)
                                                            {
                                                                for(int i1=0;i1<courseList.size();i1++)
                                                                {
                                                                    if(phu.getMaMon().equals(courseList.get(i1).getMaMon()))
                                                                    {
                                                                        org.hibernate.Session session = hibernateUtil.getSessionFactory().openSession();
                                                                        Transaction transaction = session.getTransaction();
                                                                        transaction.begin();
                                                                        if (courseList.get(i1) != null)
                                                                            session.remove(courseList.get(i1));
                                                                        transaction.commit();
                                                                        session.close();
                                                                        courseList.remove(i1);
                                                                        Vector List = new Vector();
                                                                        for (int zzz = 0; zzz < courseList.size(); zzz++) {
                                                                            Vector tm = new Vector();
                                                                            tm.add(courseList.get(zzz).getMaMon());
                                                                            tm.add(courseList.get(zzz).getTenMon());
                                                                            tm.add(courseList.get(zzz).getSoTinChi());
                                                                            tm.add(courseList.get(zzz).getGv());
                                                                            tm.add(courseList.get(zzz).getPhongHoc());
                                                                            tm.add(courseList.get(zzz).getDayofWeek());
                                                                            tm.add(courseList.get(zzz).getCa());
                                                                            tm.add(courseList.get(zzz).getMaxSlot());
                                                                            List.add(tm);
                                                                        }
                                                                        table.setModel(new DefaultTableModel(List, Header));
                                                                    }
                                                                }
                                                            }
                                                            else {
                                                                int position = table.getSelectedRow();
                                                                org.hibernate.Session session = hibernateUtil.getSessionFactory().openSession();
                                                                Transaction transaction = session.getTransaction();
                                                                transaction.begin();
                                                                if (courseList.get(position) != null)
                                                                    session.remove(courseList.get(position));
                                                                transaction.commit();
                                                                session.close();
                                                                courseList.remove(position);
                                                                Vector List = new Vector();
                                                                for (int zzz = 0; zzz < courseList.size(); zzz++) {
                                                                    Vector tm = new Vector();
                                                                    tm.add(courseList.get(zzz).getMaMon());
                                                                    tm.add(courseList.get(zzz).getTenMon());
                                                                    tm.add(courseList.get(zzz).getSoTinChi());
                                                                    tm.add(courseList.get(zzz).getGv());
                                                                    tm.add(courseList.get(zzz).getPhongHoc());
                                                                    tm.add(courseList.get(zzz).getDayofWeek());
                                                                    tm.add(courseList.get(zzz).getCa());
                                                                    tm.add(courseList.get(zzz).getMaxSlot());
                                                                    List.add(tm);
                                                                }
                                                                table.setModel(new DefaultTableModel(List, Header));
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
                                                            Course tempCourse=courseList.get(vitri);
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
                                                            Vector<Student>HSDK=new Vector<>();
                                                            for(int kk=0;kk<stdList.size();kk++)
                                                            {
                                                                int listsize;
                                                                if(stdList.get(kk).getListCourse()==null) listsize=0;
                                                                else{listsize=stdList.get(kk).getListCourse().size();}
                                                                for(int kkk=0;kkk<listsize;kkk++)
                                                                {
                                                                    if(tempCourse.getMaMon().equals(stdList.get(kk).getListCourse().get(kkk).getMaMon()))
                                                                    {
                                                                        HSDK.add(stdList.get(kk));
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            Vector data=new Vector();
                                                            for(int i1=0;i1<HSDK.size();i1++)
                                                            {
                                                                Vector dt=new Vector();
                                                                dt.add(HSDK.get(i1).getMhs());
                                                                dt.add(HSDK.get(i1).getTenHs());
                                                                dt.add(tempCourse.getMaMon());
                                                                dt.add(tempCourse.getTenMon());
                                                                dt.add(tempCourse.getGv());
                                                                dt.add(tgHoc);
                                                                for(int i2=0;i2<HSDK.get(i1).getListCourse().size();i2++)
                                                                {
                                                                    if(HSDK.get(i1).getListCourse().get(i2).getMaMon().equals(tempCourse.getMaMon()))
                                                                    {
                                                                        if(HSDK.get(i1).getTgDKHP()!=null)
                                                                        { dt.add(HSDK.get(i1).getTgDKHP().get(i2));
                                                                        }
                                                                        break;
                                                                    }
                                                                }
                                                                data.add(dt);
                                                            }
                                                            JTable jTable=new JTable(data,tieuDe);
                                                            JScrollPane scrollPane=new JScrollPane(jTable);
                                                            mainPan.add(scrollPane,BorderLayout.CENTER);
                                                            JButton Tim=new JButton("Search");
                                                            Tim.setIcon(new ImageIcon("lupe.png"));
                                                            String finalTgHoc = tgHoc;
                                                            Tim.addActionListener(new ActionListener() {
                                                                @Override
                                                                public void actionPerformed(ActionEvent e) {
                                                                    if(e.getSource()==Tim)
                                                                    {
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
                                                                                    for (int i3 = 0; i3 < data.size(); i3++) {
                                                                                        Vector tm= (Vector) data.get(i3);
                                                                                        if (ma ==(int)tm.get(0)) {
                                                                                            check = true;
                                                                                            Vector infomation = new Vector();
                                                                                            Vector phu=new Vector();
                                                                                            phu.add(HSDK.get(i3).getMhs());
                                                                                            phu.add(HSDK.get(i3).getTenHs());
                                                                                            phu.add(tempCourse.getMaMon());
                                                                                            phu.add(tempCourse.getTenMon());
                                                                                            phu.add(tempCourse.getGv());
                                                                                            phu.add(finalTgHoc);
                                                                                            for(int i4=0;i4<HSDK.get(i3).getListCourse().size();i4++)
                                                                                            {
                                                                                                if(HSDK.get(i3).getListCourse().get(i4).getMaMon().equals(tempCourse.getMaMon()))
                                                                                                {
                                                                                                    phu.add(HSDK.get(i3).getTgDKHP().get(i4));
                                                                                                    break;
                                                                                                }
                                                                                            }
                                                                                            infomation.add(phu);
                                                                                            jTable.setModel(new DefaultTableModel(infomation,tieuDe));
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                    if (check == false) {
                                                                                        JOptionPane.showMessageDialog(null, "Không tìm thấy sinh viên!");
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
                                                            mainPan.add(Tim,BorderLayout.SOUTH);
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
                                Student temp = new Student();
                                int pos = 0;
                                for (int j = 0; j < stdList.size(); j++) {
                                    if (user.equals(stdList.get(j).getUsername())) {
                                        pos = j;
                                        temp = stdList.get(j);
                                    }
                                }
                                BorderLayout borderLayout = new BorderLayout();
                                frame.setLayout(borderLayout);
                                JButton logout = new JButton("Sign out");
                                JPanel northPan = new JPanel();
                                JLabel hello = new JLabel("Hello, " + user);
                                JLabel curSes = new JLabel();
                                if (finalCurrentSemester != null) {
                                    curSes.setText("Học kỳ hiện tại:" + finalCurrentSemester.getTenHk() + "," + finalCurrentSemester.getYear() + "|");
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
                                Student finalTemp = temp;
                                Student finalTemp1 = temp;
                                Student finalTemp2 = temp;
                                Student finalTemp3 = temp;
                                Student finalTemp4 = temp;
                                Student finalTemp5 = temp;
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
                                            Student hs = new Student();
                                            label1.setText("Mã sinh viên:");
                                            JTextField textField = new JTextField();
                                            textField.setText(String.valueOf(finalTemp.getMhs()));
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
                                            textField1.setText(finalTemp1.getTenHs());
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
                                            textField3.setText(finalTemp3.getDiachi());
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
                                                            org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                            Transaction transaction=session.getTransaction();
                                                            transaction.begin();
                                                            if(finalTemp!=null) session.remove(finalTemp);
                                                            transaction.commit();
                                                            session.close();
                                                            stdList.remove(finalPos);
                                                            finalTemp.setMhs(Integer.parseInt(textField.getText()));
                                                            finalTemp.setTenHs(textField1.getText());
                                                            finalTemp.setDiem(Double.parseDouble(textField2.getText()));
                                                            finalTemp.setDiachi(textField3.getText());
                                                            finalTemp.setLop(textField4.getText());
                                                            finalTemp.setHinhanh(textField5.getText());
                                                            stdList.add(finalPos, finalTemp);
                                                            Configuration config2 = new Configuration().configure().addAnnotatedClass(Student.class);
                                                            SessionFactory sf2 = config2.buildSessionFactory();
                                                            org.hibernate.Session session2 = sf2.openSession();
                                                            Transaction tx2 = session2.beginTransaction();
                                                            session2.save(finalTemp);
                                                            tx2.commit();
                                                            session2.close();
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
                                                                    for (int l = 0; l < Accounts.size(); l++) {
                                                                        if (user.equals(Accounts.get(l).getUsername())) {
                                                                            Accounts.get(l).setPassword(jtextField1.getText());
                                                                            org.hibernate.Session session=hibernateUtil.getSessionFactory().openSession();
                                                                            Transaction transaction=session.getTransaction();
                                                                            transaction.begin();
                                                                            if(Accounts.get(l)!=null) session.saveOrUpdate(Accounts.get(l));
                                                                            transaction.commit();
                                                                            session.close();
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
                                                tmp.add(finalTemp.getListCourse().get(ii).getGv());
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
                                                        if(finalCurrentSemester.getMaHk()==null)
                                                        {
                                                            JOptionPane.showMessageDialog(null,"Giáo vụ chưa set học kỳ hiện tại!");
                                                        }
                                                        else
                                                        {
                                                            if(finalCurrentSemester.getDKHP()==null) kichthuoc=0;
                                                            else
                                                            {
                                                                kichthuoc= finalCurrentSemester.getDKHP().size();
                                                            }
                                                            for(int c=0;c<kichthuoc;c++)
                                                            {
                                                                Date bd= finalCurrentSemester.getDKHP().get(c).getStart();
                                                                Date kt= finalCurrentSemester.getDKHP().get(c).getEnd();
                                                                if(LonHon(day,month,year,bd.getDate(),bd.getMonth()+1,bd.getYear()+1900)&&
                                                                        NhoHon(day,month,year,kt.getDate(),kt.getMonth()+1,kt.getYear()+1900))
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
                                                                    for (int ii = 0; ii < courseList.size(); ii++) {
                                                                        Vector tempt = new Vector();
                                                                        tempt.add(courseList.get(ii).getMaMon());
                                                                        tempt.add(courseList.get(ii).getTenMon());
                                                                        tempt.add(courseList.get(ii).getSoTinChi());
                                                                        tempt.add(courseList.get(ii).getGv());
                                                                        tempt.add(courseList.get(ii).getPhongHoc());
                                                                        tempt.add(courseList.get(ii).getDayofWeek());
                                                                        tempt.add(courseList.get(ii).getCa());
                                                                        tempt.add(courseList.get(ii).getMaxSlot());
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
                                                                                    for(int v=0;v<si;v++)
                                                                                    {
                                                                                        if(finalTemp.getListCourse().get(v).getMaMon().equals(courseList.get(posi).getMaMon()))
                                                                                        {
                                                                                            ck=true;
                                                                                            JOptionPane.showMessageDialog(null,"Đã đăng ký môn này rồi!");
                                                                                            break;
                                                                                        }
                                                                                        if(finalTemp.getListCourse().get(v).getCa()==courseList.get(posi).getCa()&&
                                                                                                finalTemp.getListCourse().get(v).getDayofWeek().equals(courseList.get(posi).getDayofWeek()))
                                                                                        {
                                                                                            ck=true;
                                                                                            JOptionPane.showMessageDialog(null,"Trùng ca với môn học khác!");
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                    if(ck==false)
                                                                                    {
                                                                                        Vector<Course>t=finalTemp5.getListCourse();
                                                                                        Vector<String> dkhp=finalTemp5.getTgDKHP();
                                                                                        if(t==null)
                                                                                        {
                                                                                            t=new Vector<>();
                                                                                        }
                                                                                        if(dkhp==null)
                                                                                        {
                                                                                            dkhp=new Vector<>();
                                                                                        }
                                                                                        if(t.size()==8)
                                                                                        {
                                                                                            JOptionPane.showMessageDialog(null,"Đã đăng ký đủ 8 môn.Không thể đăng ký thêm!");
                                                                                        }
                                                                                        else
                                                                                        {
                                                                                            t.add(courseList.get(posi));
                                                                                            JOptionPane.showMessageDialog(null,"Đăng ký thành công!");
                                                                                            dkhp.add(String.valueOf(java.time.LocalDate.now()));
                                                                                            finalTemp5.setTgDKHP(dkhp);
                                                                                            finalTemp5.setListCourse(t);
                                                                                            for(int mm = 0; mm< stdList.size(); mm++)
                                                                                            {
                                                                                                if(stdList.get(mm).getMhs()==finalTemp5.getMhs())
                                                                                                {
                                                                                                    stdList.remove(mm);
                                                                                                    stdList.add(mm,finalTemp5);
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
                                                                                                tt.add(finalTemp5.getListCourse().get(xx).getGv());
                                                                                                tt.add(finalTemp5.getListCourse().get(xx).getPhongHoc());
                                                                                                tt.add(finalTemp5.getListCourse().get(xx).getDayofWeek());
                                                                                                tt.add(finalTemp5.getListCourse().get(xx).getCa());
                                                                                                tt.add(finalTemp5.getListCourse().get(xx).getMaxSlot());
                                                                                                DuLieu.add(tt);
                                                                                            }
                                                                                            table.setModel(new DefaultTableModel(DuLieu,Header));
                                                                                            try {
                                                                                                LamTrangFile("DuLieuHS.txt");
                                                                                                GhiFileStd(stdList);
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
                                                            if(finalCurrentSemester==null)
                                                            {
                                                                JOptionPane.showMessageDialog(null,"Giáo vụ chưa set học kỳ hiện tại!");
                                                            }
                                                            else
                                                            {
                                                                if(finalCurrentSemester.getDKHP()==null) kichthuoc=0;
                                                                else
                                                                {
                                                                    kichthuoc= finalCurrentSemester.getDKHP().size();
                                                                }
                                                                for(int c=0;c<kichthuoc;c++)
                                                                {
                                                                    Date bd= finalCurrentSemester.getDKHP().get(c).getStart();
                                                                    Date kt= finalCurrentSemester.getDKHP().get(c).getEnd();
                                                                    if(LonHon(day,month,year,bd.getDate(),bd.getMonth()+1,bd.getYear()+1900)&&
                                                                            NhoHon(day,month,year,kt.getDate(),kt.getMonth()+1,kt.getYear()+1900))
                                                                    {
                                                                        Vector<Course>tempCourse=finalTemp5.getListCourse();
                                                                        Vector<String>tempDKHP=finalTemp5.getTgDKHP();
                                                                        KT=true;
                                                                        JOptionPane.showMessageDialog(null,"Đã xóa thành công!");
                                                                        int position = table.getSelectedRow();
                                                                        if(tempDKHP!=null) tempDKHP.remove(position);
                                                                        tempCourse.remove(position);
                                                                        finalTemp5.setListCourse(tempCourse);
                                                                        finalTemp5.setTgDKHP(tempDKHP);
                                                                        for(int b = 0; b< stdList.size(); b++)
                                                                        {
                                                                            if(stdList.get(b).getMhs()==finalTemp5.getMhs())
                                                                            {
                                                                                stdList.remove(b);
                                                                                stdList.add(b,finalTemp5);
                                                                                break;
                                                                            }
                                                                        }
                                                                        Vector List = new Vector();
                                                                        for (int zzz = 0; zzz < tempCourse.size(); zzz++) {
                                                                            Vector tm = new Vector();
                                                                            tm.add(tempCourse.get(zzz).getMaMon());
                                                                            tm.add(tempCourse.get(zzz).getTenMon());
                                                                            tm.add(tempCourse.get(zzz).getSoTinChi());
                                                                            tm.add(tempCourse.get(zzz).getGv());
                                                                            tm.add(tempCourse.get(zzz).getPhongHoc());
                                                                            tm.add(tempCourse.get(zzz).getDayofWeek());
                                                                            tm.add(tempCourse.get(zzz).getCa());
                                                                            tm.add(tempCourse.get(zzz).getMaxSlot());
                                                                            List.add(tm);
                                                                        }
                                                                        table.setModel(new DefaultTableModel(List, Header));
                                                                        try {
                                                                            LamTrangFile("DuLieuHS.txt");
                                                                            GhiFileStd(stdList);
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
            setSize(700,300);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setTitle("Course Registration System");
            setIconImage(imageIcon.getImage());
            setVisible(true);
            setResizable(false);
        }
    }
    static void GhiFilecurrentSemester(Semester a) throws IOException {
        OutputStreamWriter out=new OutputStreamWriter(new FileOutputStream("DuLieuHocKyhientai.txt"));
        out.write(a.getMaHk());
        out.flush();
        out.close();
    }
    static void GhiFileLop(List<Clazz> c) throws IOException
    {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieuLop.txt"));
        out.writeInt(c.size());
        for(int i=0;i<c.size();i++)
        {
            out.writeObject(c.get(i));
        }
        out.flush();
        out.close();
    }
    static Vector DocFileLop(Vector a) throws IOException, ClassNotFoundException {

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("DuLieuLop.txt"));
        int count=in.readInt();
        for(int i=0;i<count;i++)
        {
            Clazz acc= (Clazz) in.readObject();
            a.add(acc);
        }
        in.close();
        return a;
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
    static String DocFilecurrentSemester() throws IOException, ClassNotFoundException {

        InputStreamReader in=new InputStreamReader(new FileInputStream("DuLieuHocKyhientai.txt"));
        BufferedReader br=new BufferedReader(in);
        String result="";
        result+=br.readLine();
        in.close();
        br.close();
        return result;
    }
    static void LamTrangFile(String filename) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(filename));
        out.writeBytes("");
        out.flush();
        out.close();
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
    static void GhiFileSemester(List<Semester> a) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieuHocKy.txt"));
        out.writeInt(a.size());
        for(int i=0;i<a.size();i++)
        {
            out.writeObject(a.get(i));
        }
        out.flush();
        out.close();
    }
    static Vector DocFileStd(Vector a) throws IOException, ClassNotFoundException {

        ObjectInputStream in=new ObjectInputStream(new FileInputStream("DuLieuHS.txt"));
        int count=in.readInt();
        for(int i=0;i<count;i++)
        {
            Student hs= (Student) in.readObject();
            a.add(hs);
        }
        in.close();
        return a;
    }
    static void GhiFileStd(List<Student> a) throws IOException {
        ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("DuLieuHS.txt"));
        out.writeInt(a.size());
        for(int i=0;i<a.size();i++)
        {
            out.writeObject(a.get(i));
        }
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
