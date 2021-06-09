package hibernate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

@Entity
public class Student implements Serializable {
    private int mhs;
    private String tenHs;
    private Double diem;
    private String diachi;
    private String lop;
    private String hinhanh;
    private String username;
    private Vector<Subject> listSub;
    private Vector<Course> listCourse;
    private Vector<String>tgDKHP;
    public Student() {
    }

    public Student(int mhs, String tenHs, Double diem, String diachi, String lop, String hinhanh) {
        this.mhs = mhs;
        this.tenHs = tenHs;
        this.diem = diem;
        this.diachi = diachi;
        this.lop = lop;
        this.hinhanh = hinhanh;
    }

    public Student(int mhs, String tenHs, Double diem, String diachi, String lop, String hinhanh, String username) {
        this.mhs = mhs;
        this.tenHs = tenHs;
        this.diem = diem;
        this.diachi = diachi;
        this.lop = lop;
        this.hinhanh = hinhanh;
        this.username = username;
    }

    public Student(int mhs, String tenHs, Double diem, String diachi, String lop, String hinhanh, String username, Vector<Subject> listSub) {
        this.mhs = mhs;
        this.tenHs = tenHs;
        this.diem = diem;
        this.diachi = diachi;
        this.lop = lop;
        this.hinhanh = hinhanh;
        this.username = username;
        this.listSub = listSub;
    }

    public Student(int mhs, String tenHs, Double diem, String diachi, String lop, String hinhanh, String username, Vector<Subject> listSub, Vector<Course> listCourse) {
        this.mhs = mhs;
        this.tenHs = tenHs;
        this.diem = diem;
        this.diachi = diachi;
        this.lop = lop;
        this.hinhanh = hinhanh;
        this.username = username;
        this.listSub = listSub;
        this.listCourse = listCourse;
    }

    public Student(int mhs, String tenHs, Double diem, String diachi, String lop, String hinhanh, String username, Vector<Subject> listSub, Vector<Course> listCourse, Vector<String> tgDKHP) {
        this.mhs = mhs;
        this.tenHs = tenHs;
        this.diem = diem;
        this.diachi = diachi;
        this.lop = lop;
        this.hinhanh = hinhanh;
        this.username = username;
        this.listSub = listSub;
        this.listCourse = listCourse;
        this.tgDKHP = tgDKHP;
    }

    @Id
    @Column(name = "MHS", nullable = false)
    public int getMhs() {
        return mhs;
    }

    public void setMhs(int mhs) {
        this.mhs = mhs;
    }

    @Basic
    @Column(name = "TenHS", nullable = true, length = 50)
    public String getTenHs() {
        return tenHs;
    }

    public void setTenHs(String tenHs) {
        this.tenHs = tenHs;
    }

    @Basic
    @Column(name = "Diem", nullable = true, precision = 0)
    public Double getDiem() {
        return diem;
    }

    public void setDiem(Double diem) {
        this.diem = diem;
    }

    @Basic
    @Column(name = "Diachi", nullable = true, length = 50)
    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    @Basic
    @Column(name = "Lop", nullable = true, length = 10)
    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    @Basic
    @Column(name = "Hinhanh", nullable = true, length = 50)
    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    @Basic
    @Column(name = "Username", nullable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return mhs == student.mhs && Objects.equals(tenHs, student.tenHs) && Objects.equals(diem, student.diem) && Objects.equals(diachi, student.diachi) && Objects.equals(lop, student.lop) && Objects.equals(hinhanh, student.hinhanh) && Objects.equals(username, student.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mhs, tenHs, diem, diachi, lop, hinhanh, username);
    }

    public Vector<Subject> getListSub() {
        return listSub;
    }

    public void setListSub(Vector<Subject> listSub) {
        this.listSub = listSub;
    }

    public Vector<Course> getListCourse() {
        return listCourse;
    }

    public void setListCourse(Vector<Course> listCourse) {
        this.listCourse = listCourse;
    }

    public Vector<String> getTgDKHP() {
        return tgDKHP;
    }

    public void setTgDKHP(Vector<String> tgDKHP) {
        this.tgDKHP = tgDKHP;
    }
}
