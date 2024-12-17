package dao;

import java.time.LocalDate;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dto.KhuyenMaiDTO;
public class KhuyenMaiDAO {
    private MySQLConnect db = new MySQLConnect();

    public ArrayList<KhuyenMaiDTO> list() {
        ArrayList<KhuyenMaiDTO> kmList = new ArrayList<>();

        try {            
            String sql = "SELECT * FROM khuyenmai";
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                String id = rs.getString("id");
                String ten = rs.getString("ten");
                int ti_le_giam_gia = rs.getInt("ti_le_giam_gia");
                
                java.sql.Date sqlDate1 = rs.getDate("ngay_bd");
                LocalDate ngay_bd = sqlDate1.toLocalDate();
                
                java.sql.Date sqlDate2 = rs.getDate("ngay_kt");
                LocalDate ngay_kt = sqlDate2.toLocalDate();
                boolean enable = rs.getBoolean("enable");
           
                KhuyenMaiDTO khuyenMai = new KhuyenMaiDTO(id,ten, ti_le_giam_gia,ngay_bd,ngay_kt,enable);
                kmList.add(khuyenMai);
               
            }
            rs.close();
            db.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(KhachHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }


        return kmList;
    }
    
    public void updateDB(KhuyenMaiDTO km) {
        String sql = "UPDATE khuyenmai SET ";
        sql += "ten='" + km.getTen_km() + "', ";
        sql += "ti_le_giam_gia='" + km.getTiLeGiamGia() + "', "; 
        sql += "ngay_bd='" + km.getNgay_bd() + "', ";
        sql += "ngay_kt='" + km.getNgay_kt() + "' ";
        sql += "WHERE id='" + km.getId_km() + "'";
        db.executeUpdate(sql);
    }
    
    
    
    public void addDB(KhuyenMaiDTO km) {
        String sql = "INSERT INTO khuyenmai VALUES (";
        sql += "'" +  km.getId_km() + "', ";
        sql += "N'"+ km.getTen_km() + "', "; 
        sql += "'" + km.getTiLeGiamGia() + "', ";
        sql += "'" + km.getNgay_bd() + "', ";
        sql += "'" + km.getNgay_kt() + "', ";
        sql += "'1')";
        db.executeUpdate(sql);
    }
    
    public void deleteDB(String id) {
        String sql = "UPDATE khuyenmai SET enable = 0 WHERE id='" + id + "'";
        db.executeUpdate(sql);
    }
    
    

   
}

