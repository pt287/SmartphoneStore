package dto;

import java.time.LocalDate;

public class HoaDonDTO {
    private String idHoaDon;
    private String idKhachHang;
    private String idKhuyenMai;
    private String idUser;
    private LocalDate ngayXuat;
    private int tongTien;

    public HoaDonDTO() {
        
    }

    public HoaDonDTO(String idHoaDon, String idKhachHang, String idKhuyenMai, String idUser, LocalDate ngayXuat, int tongTien) {
        this.idHoaDon = idHoaDon;
        this.idKhachHang = idKhachHang;
        this.idKhuyenMai = idKhuyenMai;
        this.idUser = idUser;
        this.ngayXuat = ngayXuat;
        this.tongTien = tongTien;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public String getIdKhuyenMai() {
        return idKhuyenMai;
    }

    public String getIdUser() {
        return idUser;
    }

    public LocalDate getNgayXuat() {
        return ngayXuat;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public void setIdKhuyenMai(String idKhuyenMai) {
        this.idKhuyenMai = idKhuyenMai;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public void setNgayXuat(LocalDate ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
    
    
}
