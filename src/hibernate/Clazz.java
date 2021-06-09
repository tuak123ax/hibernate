package hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

@Entity
@Table(name = "class", schema = "hibernate", catalog = "")
public class Clazz implements Serializable {
    private String tenLop;
    private Integer slsv;
    private Integer slNam;
    private Integer slNu;
    private Vector<Student> SVList;
    public Clazz() {
    }

    public Clazz(String tenLop, Integer slsv, Integer slNam, Integer slNu) {
        this.tenLop = tenLop;
        this.slsv = slsv;
        this.slNam = slNam;
        this.slNu = slNu;
    }

    public Clazz(String tenLop, Integer slsv, Integer slNam, Integer slNu, Vector<Student> SVList) {
        this.tenLop = tenLop;
        this.slsv = slsv;
        this.slNam = slNam;
        this.slNu = slNu;
        this.SVList = SVList;
    }

    @Id
    @Column(name = "tenLop", nullable = false, length = 10)
    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    @Basic
    @Column(name = "SLSV", nullable = true)
    public Integer getSlsv() {
        return slsv;
    }

    public void setSlsv(Integer slsv) {
        this.slsv = slsv;
    }

    @Basic
    @Column(name = "SLNam", nullable = true)
    public Integer getSlNam() {
        return slNam;
    }

    public void setSlNam(Integer slNam) {
        this.slNam = slNam;
    }

    @Basic
    @Column(name = "SLNu", nullable = true)
    public Integer getSlNu() {
        return slNu;
    }

    public void setSlNu(Integer slNu) {
        this.slNu = slNu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz clazz = (Clazz) o;
        return Objects.equals(tenLop, clazz.tenLop) && Objects.equals(slsv, clazz.slsv) && Objects.equals(slNam, clazz.slNam) && Objects.equals(slNu, clazz.slNu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tenLop, slsv, slNam, slNu);
    }

    public Vector<Student> getSVList() {
        return SVList;
    }

    public void setSVList(Vector<Student> SVList) {
        this.SVList = SVList;
    }
}
