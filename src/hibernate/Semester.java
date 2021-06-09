package hibernate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Vector;

@Entity
public class Semester implements Serializable {
    private String maHk;
    private String tenHk;
    private Integer year;
    private Date ngayBd;
    private Date ngayKt;
    private Vector<Session> DKHP;
    public Semester() {
    }

    public Semester(String maHk, String tenHk, Integer year, Date ngayBd, Date ngayKt) {
        this.maHk = maHk;
        this.tenHk = tenHk;
        this.year = year;
        this.ngayBd = ngayBd;
        this.ngayKt = ngayKt;
    }

    public Semester(String maHk, String tenHk, Integer year, Date ngayBd, Date ngayKt, Vector<Session> DKHP) {
        this.maHk = maHk;
        this.tenHk = tenHk;
        this.year = year;
        this.ngayBd = ngayBd;
        this.ngayKt = ngayKt;
        this.DKHP = DKHP;
    }

    @Id
    @Column(name = "maHK", nullable = false, length = 10)
    public String getMaHk() {
        return maHk;
    }

    public void setMaHk(String maHk) {
        this.maHk = maHk;
    }

    @Basic
    @Column(name = "tenHK", nullable = true, length = 50)
    public String getTenHk() {
        return tenHk;
    }

    public void setTenHk(String tenHk) {
        this.tenHk = tenHk;
    }

    @Basic
    @Column(name = "year", nullable = true)
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    @Basic
    @Column(name = "ngayBD", nullable = true)
    public Date getNgayBd() {
        return ngayBd;
    }

    public void setNgayBd(Date ngayBd) {
        this.ngayBd = ngayBd;
    }

    @Basic
    @Column(name = "ngayKT", nullable = true)
    public Date getNgayKt() {
        return ngayKt;
    }

    public void setNgayKt(Date ngayKt) {
        this.ngayKt = ngayKt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Semester semester = (Semester) o;
        return Objects.equals(maHk, semester.maHk) && Objects.equals(tenHk, semester.tenHk) && Objects.equals(year, semester.year) && Objects.equals(ngayBd, semester.ngayBd) && Objects.equals(ngayKt, semester.ngayKt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maHk, tenHk, year, ngayBd, ngayKt);
    }

    public Vector<Session> getDKHP() {
        return DKHP;
    }

    public void setDKHP(Vector<Session> DKHP) {
        this.DKHP = DKHP;
    }
}
