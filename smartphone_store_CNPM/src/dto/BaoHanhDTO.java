package dto;

import java.time.LocalDate;

public class BaoHanhDTO {
    private String serial;
    private String  tenSanPham;
    private String idHoaDon;
    private String idKhachHang;
    private LocalDate ngayMua;
    private LocalDate ngayHetHan;

    public BaoHanhDTO(String serial, String tenSanPham, String idHoaDon, String idKhachHang, LocalDate ngayMua, LocalDate ngayHetHan) {
        this.serial = serial;
        this.tenSanPham = tenSanPham;
        this.idHoaDon = idHoaDon;
        this.idKhachHang = idKhachHang;
        this.ngayMua = ngayMua;
        this.ngayHetHan = ngayHetHan;
    }

    public BaoHanhDTO() {
    }

    public String getSerial() {
        return serial;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public LocalDate getNgayMua() {
        return ngayMua;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public void setNgayMua(LocalDate ngayMua) {
        this.ngayMua = ngayMua;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    
}
