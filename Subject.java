import java.io.Serializable;

public class Subject implements Serializable {
    private String MaMH;
    private String tenMH;
    private int tinChi;
    public Subject(){}
    public Subject(String MaMH,String tenMH,int tinChi)
    {
        this.MaMH=MaMH;
        this.tenMH=tenMH;
        this.tinChi=tinChi;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String maMH) {
        MaMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public int getTinChi() {
        return tinChi;
    }

    public void setTinChi(int tinChi) {
        this.tinChi = tinChi;
    }
}
