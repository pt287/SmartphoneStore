package dao;
import dto.CTBaoHanhDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CTBaoHanhDAO {
    private MySQLConnect db = new MySQLConnect();
    
    public ArrayList<CTBaoHanhDTO> list() {
        ArrayList<CTBaoHanhDTO> ctbhList = new ArrayList<>();

        try {
            
            String sql = "SELECT * FROM ctbaohanh";
            ResultSet rs = db.executeQuery(sql);
            while (rs.next()) {
                String id_ct_bh = rs.getString("id_ct_bh");
                String serial = rs.getString("serial");
                String id_user = rs.getString("id_user");
                String tinhtrang_may = rs.getString("tinhtrang_may");
                java.sql.Date sqlDate1 = rs.getDate("ngaynhan");
                LocalDate ngaynhan = sqlDate1.toLocalDate();
                java.sql.Date sqlDate2 = rs.getDate("ngaytra");
                LocalDate ngaytra = sqlDate2.toLocalDate();
                int chiphi = rs.getInt("chiphi");
                boolean enable = rs.getBoolean("enable");
                
                CTBaoHanhDTO ctBaoHanh = new CTBaoHanhDTO(id_ct_bh, serial, id_user, tinhtrang_may, chiphi, ngaynhan, ngaytra, enable);
                ctbhList.add(ctBaoHanh);
            }
            rs.close();
            db.disConnect();
        } catch (SQLException ex) {
            Logger.getLogger(CTBaoHanhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ctbhList;
    }
    
     public void updateDB(CTBaoHanhDTO ctbh) {
        String sql = "UPDATE ctbaohanh SET ";
        sql += "serial='" + ctbh.getId_Ct_Bh() + "', ";
        sql += "id_user='" + ctbh.getIdUser() + "', ";
        sql += "tinhtrang_may='" + ctbh.getTinhTrang_May() + "', ";
        sql += "chiphi='" + ctbh.getChiPhi() + "',";
        sql += "ngaynhan='" + ctbh.getNgayNhan() + "', ";
        sql += "ngaytra='" + ctbh.getNgayTra() + "'";
        sql += "WHERE id_ct_bh='" + ctbh.getId_Ct_Bh() + "'";
        db.executeUpdate(sql);
    }

    public void addDB(CTBaoHanhDTO ctbh) {
        String sql = "INSERT INTO ctbaohanh VALUES (";
        sql += "'" + ctbh.getId_Ct_Bh() + "', ";
        sql += "'" + ctbh.getSerial() + "', ";  
        sql += "'" + ctbh.getIdUser() + "', ";
        sql += "N'" + ctbh.getTinhTrang_May() + "', ";
        sql += "'" + ctbh.getChiPhi() + "',";
        sql += "'" + ctbh.getNgayNhan() + "', ";
        sql += "'" + ctbh.getNgayTra() + "', ";  
        sql += "'1')";
        db.executeUpdate(sql);
    }
   
    public void deleteDB(String id) {
        String sql = "UPDATE ctbaohanh SET enable = 0 WHERE id_ct_bh='" + id + "'";
        db.executeUpdate(sql);
    }
}