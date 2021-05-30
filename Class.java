import java.io.Serializable;

public class Class implements Serializable {
    private String tenLop;
    private int SLSV;
    private int SLNam;
    private int SLNu;
    public Class(){}
    public Class(String tenLop,int SLSV,int SLNam,int SLNu)
    {
        this.tenLop=tenLop;
        this.SLSV=SLSV;
        this.SLNam=SLNam;
        this.SLNu=SLNu;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public int getSLSV() {
        return SLSV;
    }

    public void setSLSV(int SLSV) {
        this.SLSV = SLSV;
    }

    public int getSLNam() {
        return SLNam;
    }

    public void setSLNam(int SLNam) {
        this.SLNam = SLNam;
    }

    public int getSLNu() {
        return SLNu;
    }

    public void setSLNu(int SLNu) {
        this.SLNu = SLNu;
    }
}
