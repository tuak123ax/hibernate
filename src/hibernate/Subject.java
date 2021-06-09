package hibernate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Subject implements Serializable {
    private String maMh;
    private String tenMh;
    private Integer tinchi;

    public Subject() {
    }

    public Subject(String maMh, String tenMh, Integer tinchi) {
        this.maMh = maMh;
        this.tenMh = tenMh;
        this.tinchi = tinchi;
    }

    @Id
    @Column(name = "maMH", nullable = false, length = 10)
    public String getMaMh() {
        return maMh;
    }

    public void setMaMh(String maMh) {
        this.maMh = maMh;
    }

    @Basic
    @Column(name = "tenMH", nullable = true, length = 50)
    public String getTenMh() {
        return tenMh;
    }

    public void setTenMh(String tenMh) {
        this.tenMh = tenMh;
    }

    @Basic
    @Column(name = "tinchi", nullable = true)
    public Integer getTinchi() {
        return tinchi;
    }

    public void setTinchi(Integer tinchi) {
        this.tinchi = tinchi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(maMh, subject.maMh) && Objects.equals(tenMh, subject.tenMh) && Objects.equals(tinchi, subject.tinchi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maMh, tenMh, tinchi);
    }
}
