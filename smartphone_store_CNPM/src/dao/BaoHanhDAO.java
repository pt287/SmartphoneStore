package dao;

import dto.BaoHanhDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaoHanhDAO {
    private MySQLConnect db = new MySQLConnect();

    public ArrayList<BaoHanhDTO> list() {
        ArrayList<BaoHanhDTO> bhList = new ArrayList<>();
        
        try {            
            String sql = "SELECT * FROM baohanh";
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                String id_kh = rs.getString("id_kh");
                String id_hd = rs.getString("id_hd");
                String ten_sp = rs.getString("ten_sp");
                String serial = rs.getString("serial");
                java.sql.Date sqlDate1 = rs.getDate("ngay_mua");
                LocalDate ngay_mua = sqlDate1.toLocalDate();
                java.sql.Date sqlDate2 = rs.getDate("ngay_het_han");
                LocalDate ngay_het_han = sqlDate2.toLocalDate();
                
                BaoHanhDTO bh = new BaoHanhDTO(serial, ten_sp,id_hd,id_kh,  ngay_mua, ngay_het_han);
                bhList.add(bh);
            }
            rs.close();
            db.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(BaoHanhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bhList;
    }
    
    public void addDB(BaoHanhDTO bh) {
        String sql = "INSERT INTO baohanh VALUES (";
        sql += "'" + bh.getSerial() + "', ";
        sql += "'" + bh.getTenSanPham() + "', ";
        sql += "'" + bh.getIdHoaDon() + "', ";
        sql += "'" + bh.getIdKhachHang() + "', ";
        sql += "'" + bh.getNgayMua() + "', ";
        sql += "'" + bh.getNgayHetHan() + "')";
        db.executeUpdate(sql);
    }
}
