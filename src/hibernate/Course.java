package hibernate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Course implements Serializable {
    private String maMon;
    private String tenMon;
    private Integer soTinChi;
    private String gv;
    private String phongHoc;
    private String dayofWeek;
    private Integer ca;
    private Integer maxSlot;

    public Course() {
    }

    public Course(String maMon, String tenMon, Integer soTinChi, String gv, String phongHoc, String dayofWeek, Integer ca, Integer maxSlot) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.soTinChi = soTinChi;
        this.gv = gv;
        this.phongHoc = phongHoc;
        this.dayofWeek = dayofWeek;
        this.ca = ca;
        this.maxSlot = maxSlot;
    }

    @Id
    @Column(name = "maMon", nullable = false, length = 10)
    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    @Basic
    @Column(name = "tenMon", nullable = true, length = 50)
    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    @Basic
    @Column(name = "soTinChi", nullable = true)
    public Integer getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(Integer soTinChi) {
        this.soTinChi = soTinChi;
    }

    @Basic
    @Column(name = "GV", nullable = true, length = 50)
    public String getGv() {
        return gv;
    }

    public void setGv(String gv) {
        this.gv = gv;
    }

    @Basic
    @Column(name = "phongHoc", nullable = true, length = 10)
    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

    @Basic
    @Column(name = "dayofWeek", nullable = true, length = 10)
    public String getDayofWeek() {
        return dayofWeek;
    }

    public void setDayofWeek(String dayofWeek) {
        this.dayofWeek = dayofWeek;
    }

    @Basic
    @Column(name = "Ca", nullable = true)
    public Integer getCa() {
        return ca;
    }

    public void setCa(Integer ca) {
        this.ca = ca;
    }

    @Basic
    @Column(name = "maxSlot", nullable = true)
    public Integer getMaxSlot() {
        return maxSlot;
    }

    public void setMaxSlot(Integer maxSlot) {
        this.maxSlot = maxSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(maMon, course.maMon) && Objects.equals(tenMon, course.tenMon) && Objects.equals(soTinChi, course.soTinChi) && Objects.equals(gv, course.gv) && Objects.equals(phongHoc, course.phongHoc) && Objects.equals(dayofWeek, course.dayofWeek) && Objects.equals(ca, course.ca) && Objects.equals(maxSlot, course.maxSlot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maMon, tenMon, soTinChi, gv, phongHoc, dayofWeek, ca, maxSlot);
    }
}
