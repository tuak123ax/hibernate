import java.io.Serializable;

public class Semester implements Serializable {
    private String tenHK;
    private int year;
    private DateTime ngayBD;
    private DateTime ngayKT;
    public Semester(){}
    public Semester(String tenHK,int year,DateTime ngayBD,DateTime ngayKT)
    {
        this.tenHK=tenHK;
        this.year=year;
        this.ngayBD=ngayBD;
        this.ngayKT=ngayKT;
    }
    public String getTenHK() {
        return tenHK;
    }

    public void setTenHK(String tenHK) {
        this.tenHK = tenHK;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public DateTime getNgayBD() {
        return ngayBD;
    }

    public void setNgayBD(DateTime ngayBD) {
        this.ngayBD = ngayBD;
    }

    public DateTime getNgayKT() {
        return ngayKT;
    }

    public void setNgayKT(DateTime ngayKT) {
        this.ngayKT = ngayKT;
    }
}
