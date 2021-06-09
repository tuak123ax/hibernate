package hibernate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Teacher implements Serializable {
    private int mgv;
    private String tenGv;
    private Integer tuoi;
    private String diachi;
    private String ghichu;
    private String hinhanh;
    private String username;

    public Teacher() {
    }

    public Teacher(int mgv, String tenGv, Integer tuoi, String diachi, String ghichu, String hinhanh, String username) {
        this.mgv = mgv;
        this.tenGv = tenGv;
        this.tuoi = tuoi;
        this.diachi = diachi;
        this.ghichu = ghichu;
        this.hinhanh = hinhanh;
        this.username = username;
    }

    @Id
    @Column(name = "MGV", nullable = false)
    public int getMgv() {
        return mgv;
    }

    public void setMgv(int mgv) {
        this.mgv = mgv;
    }

    @Basic
    @Column(name = "TenGV", nullable = false, length = 50)
    public String getTenGv() {
        return tenGv;
    }

    public void setTenGv(String tenGv) {
        this.tenGv = tenGv;
    }

    @Basic
    @Column(name = "Tuoi", nullable = true)
    public Integer getTuoi() {
        return tuoi;
    }

    public void setTuoi(Integer tuoi) {
        this.tuoi = tuoi;
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
    @Column(name = "Ghichu", nullable = true, length = 50)
    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
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
        Teacher teacher = (Teacher) o;
        return mgv == teacher.mgv && Objects.equals(tenGv, teacher.tenGv) && Objects.equals(tuoi, teacher.tuoi) && Objects.equals(diachi, teacher.diachi) && Objects.equals(ghichu, teacher.ghichu) && Objects.equals(hinhanh, teacher.hinhanh) && Objects.equals(username, teacher.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mgv, tenGv, tuoi, diachi, ghichu, hinhanh, username);
    }
}
