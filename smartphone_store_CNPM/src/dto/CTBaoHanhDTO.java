package dto;
import java.time.LocalDate;
public class CTBaoHanhDTO {

    private String id_Ct_Bh;
    private String serial;
    private String idUser;
    private String tinhTrang_May;
    private int chiPhi;
    private LocalDate ngayNhan;
    private LocalDate ngayTra;

    private boolean enable;
    
    public CTBaoHanhDTO() {
        
    }

    public CTBaoHanhDTO(String id_Ct_Bh, String serial, String idUser,  String tinhTrang_May, int chiPhi, LocalDate ngayNhan, LocalDate ngayTra, boolean enable) {     
        this.id_Ct_Bh = id_Ct_Bh;
        this.serial = serial;
        this.idUser = idUser;
        this.tinhTrang_May = tinhTrang_May;
        this.chiPhi = chiPhi;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
        this.enable = enable;
    }

    public String getSerial() {
        return serial;
    }

    public String getId_Ct_Bh() {
        return id_Ct_Bh;
    } 

    public String getTinhTrang_May() {
        return tinhTrang_May;
    }

    public LocalDate getNgayNhan() {
        return ngayNhan;
    }

    public LocalDate getNgayTra() {
        return ngayTra;
    }

    public int getChiPhi() {
        return chiPhi;
    }
        public boolean isEnable() {
        return enable;
    }

    public String getIdUser() {
        return idUser;
    }
    

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setId_Ct_Bh(String id_Ct_Bh) {
        this.id_Ct_Bh = id_Ct_Bh;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setTinhTrang_May(String tinhTrang_May) {
        this.tinhTrang_May = tinhTrang_May;
    }

    public void setNgayNhan(LocalDate ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public void setNgayTra(LocalDate ngayTra) {
        this.ngayTra = ngayTra;
    }

    public void setChiPhi(int chiPhi) {
        this.chiPhi = chiPhi;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }
    
}
