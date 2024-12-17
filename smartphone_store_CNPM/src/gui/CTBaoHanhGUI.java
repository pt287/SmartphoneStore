package gui;

import bus.CTBaoHanhBUS;
import bus.BaoHanhBUS;
import bus.SanPhamBUS;
import com.toedter.calendar.JDateChooser;
import dto.CTBaoHanhDTO;
import dto.BaoHanhDTO;
import dto.UserDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;




public class CTBaoHanhGUI extends JPanel {
     private int width, height;
    private Color colorBackground = Color.decode("#FFFFFF");
    private Color color1 = Color.decode("#800000");
    private Color color2 = Color.decode("#B22222");
    private Color color3 = Color.decode("#FF6347");

    private JPanel pnInfor, pnFilter, pnTable;
    private ArrayList<JPanel> arrPnInfor;
    private ArrayList<JLabel> arrLbInfor;
    private ArrayList<JTextField> arrTfInfor;
    private JButton btnThem, btnSua, btnXoa, btnXem_TG_BH;

    private JTable table;
    private TableRowSorter<TableModel> rowSorter;
    private DefaultTableModel model;
    private CTBaoHanhBUS ctBaoHanhBUS = new CTBaoHanhBUS();
    private UserDTO user = new UserDTO();
    private BaoHanhBUS baoHanhBUS = new BaoHanhBUS();
    private boolean quyenThem, quyenSua, quyenXoa;
    private String id_User;
    
    private boolean isEditing = false; // true = đang trong chế độ sửa, false = đang trong chế độ thêm hoặc khác    
    
    public CTBaoHanhGUI(int width, int height, UserDTO user, boolean quyenThem, boolean quyenSua, boolean quyenXoa) {
        this.width = width;
        this.height = height;
        this.user = user;
        this.quyenThem = quyenThem;
        this.quyenSua = quyenSua;
        this.quyenXoa = quyenXoa;
        this.init();
    }
    
    public void init() {
        this.setSize(this.width, this.height);
        this.setBackground(this.colorBackground);
        
        this.pnInfor = this.createPnInfor();
        this.pnFilter = this.createPnFilter();
        this.pnTable = this.createPnTable();
        
        this.setLayout(new BorderLayout());
        this.add(this.pnInfor, BorderLayout.NORTH);
        this.add(this.pnFilter, BorderLayout.CENTER);
        this.add(this.pnTable, BorderLayout.SOUTH);
        loadFirstKMInfo();
    }
    
    public void loadFirstKMInfo() {
        // Lấy khách hàng đầu tiên từ KhachHangBUS
        CTBaoHanhDTO firstCTBHList =ctBaoHanhBUS.getCtbhList().get(0);

        // Đặt các JTextFields với thông tin nhà cung cấp
        if (firstCTBHList != null) {
            this.arrTfInfor.get(0).setText(firstCTBHList.getId_Ct_Bh());
            this.arrTfInfor.get(1).setText(firstCTBHList.getSerial()); 
            this.arrTfInfor.get(2).setText(String.valueOf(firstCTBHList.getIdUser())); 
            this.arrTfInfor.get(3).setText(String.valueOf(firstCTBHList.getTinhTrang_May())); 
            this.arrTfInfor.get(4).setText(String.valueOf(firstCTBHList.getChiPhi())); 
            this.arrTfInfor.get(5).setText(String.valueOf(firstCTBHList.getNgayNhan())); 
            this.arrTfInfor.get(6).setText(String.valueOf(firstCTBHList.getNgayTra())); 
            
        }
        if (isEditing) {
            lockInfor(false);
        }
        else lockInfor(true);
    }
    
    public JPanel createPnInfor() {
        JPanel result = new JPanel(new FlowLayout(1, 0, 25));
        result.setPreferredSize(new Dimension(this.width, 320));
        
        JPanel pn_infor = new JPanel(new BorderLayout());
        pn_infor.setPreferredSize(new Dimension(this.width - 100, 290));
        pn_infor.setBackground(this.color1);
        pn_infor.setBorder(BorderFactory.createLineBorder(color1, 2));

        
        // create panel description
        JPanel pn_desc = new JPanel(new FlowLayout(1, 10, 10));

        
        String[] thuoc_tinh = {
             "id BH", "Serial","Mã nhân viên", "Lý do", "Chi phí", "Ngày nhận", "Ngày trả"
        };
        int len = thuoc_tinh.length;
        this.arrPnInfor = new ArrayList<>();
        this.arrLbInfor = new ArrayList<>();
        this.arrTfInfor = new ArrayList<>();

        
        
        Dimension d_pn = new Dimension(530, 30);
        Dimension d_lb = new Dimension(130, 30);
        Dimension d_tf = new Dimension(400, 30);

        Color color_font = this.color1;
        Font font_infor = new Font("Segoe UI", Font.PLAIN, 15);
        for (int i = 0; i < len; i++) {
            this.arrPnInfor.add(new JPanel(new FlowLayout(0, 0, 0)));
            this.arrPnInfor.get(i).setPreferredSize(d_pn);
            
            this.arrLbInfor.add(new JLabel(thuoc_tinh[i]));
            this.arrLbInfor.get(i).setPreferredSize(d_lb);
            this.arrTfInfor.add(new JTextField());
            this.arrTfInfor.get(i).setPreferredSize(d_tf);

            this.arrLbInfor.get(i).setForeground(color_font);
            this.arrLbInfor.get(i).setFont(font_infor);
            this.arrTfInfor.get(i).setForeground(color_font);
            this.arrTfInfor.get(i).setFont(font_infor);
            
            this.arrPnInfor.get(i).add(this.arrLbInfor.get(i));
            this.arrPnInfor.get(i).add(this.arrTfInfor.get(i));
            pn_desc.add(this.arrPnInfor.get(i));
        }
        this.arrTfInfor.get(0).setEditable(false); // khóa luôn khả năng chỉnh sửa mã sản phẩm    
        this.arrTfInfor.get(2).setEditable(false);

            id_User = this.user.getIdUser();
        
        
        // create panel button
        JPanel pn_btn = new JPanel(new FlowLayout(1, 25, 10));
        pn_btn.setPreferredSize(new Dimension(250, 250));
        
        // các nút chức năng mặc định
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnXem_TG_BH = new JButton("Xem thời hạn BH");     
        showCN();
        
        // các nút chức năng phụ
        JButton btn_hoan_thanh = new JButton("Hoàn thành");
        JButton btn_tro_ve = new JButton("Trở về");
        
        // Thiết kế giao diện nút
        Dimension d_btn = new Dimension(150, 30);
        btnThem.setPreferredSize(d_btn);
        btnSua.setPreferredSize(d_btn);
        btnXoa.setPreferredSize(d_btn);
        btnXem_TG_BH.setPreferredSize(d_btn);
        
        
        btn_hoan_thanh.setPreferredSize(d_btn);
        btn_tro_ve.setPreferredSize(d_btn);
        btn_hoan_thanh.setVisible(false);
        btn_tro_ve.setVisible(false);
        
        Color color_button = this.color2;
        btnThem.setBackground(color_button);
        btnSua.setBackground(color_button);
        btnXoa.setBackground(color_button);
        btnXem_TG_BH.setBackground(color_button);
        

        btn_hoan_thanh.setBackground(color_button);
        btn_tro_ve.setBackground(color_button);
        
        Color color_font_btn = this.colorBackground;
        btnThem.setForeground(color_font_btn);
        btnSua.setForeground(color_font_btn);
        btnXoa.setForeground(color_font_btn);
        btnXem_TG_BH.setForeground(color_font_btn);
        

        btn_hoan_thanh.setForeground(color_font_btn);
        btn_tro_ve.setForeground(color_font_btn);
        
        Font font_btn = new Font("Segoe UI", Font.BOLD, 13);
        btnThem.setFont(font_btn);
        btnSua.setFont(font_btn);
        btnXoa.setFont(font_btn);
        btnXem_TG_BH.setFont(font_btn);
        

        btn_hoan_thanh.setFont(font_btn);
        btn_tro_ve.setFont(font_btn);
        
        // thêm các nút
        pn_btn.add(btnThem);
        pn_btn.add(btnSua);
        pn_btn.add(btnXoa);
        pn_btn.add(btnXem_TG_BH);
        pn_btn.add(btn_hoan_thanh);
        pn_btn.add(btn_tro_ve);
        
        // khi ấn nút thêm
       btnThem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                blankInfor();
                isEditing = false;
                lockInfor(false);
                
                arrTfInfor.get(0).setText(ctBaoHanhBUS.createNewId());
                arrTfInfor.get(5).setText(LocalDate.now()+"");
                arrTfInfor.get(2).setText(id_User);
                arrTfInfor.get(5).setEditable(false);
                btnThem.setVisible(false);
                btnSua.setVisible(false);
                btnXoa.setVisible(false);

                
                
                btn_hoan_thanh.setVisible(true);
                btn_tro_ve.setVisible(true);
                
                table.clearSelection();
                table.setEnabled(false);
            }
        });
        
        // khi ấn nút sửa
        btnSua.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (arrTfInfor.get(0).getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bảo hành cần sửa!");
                    return;
                }
                isEditing = true;
                
                lockInfor(false);
                
                btnThem.setVisible(false);
                btnSua.setVisible(false);
                btnXoa.setVisible(false);

                
                
                btn_hoan_thanh.setVisible(true);
                btn_tro_ve.setVisible(true);
                
                table.setEnabled(false);
            }
        });
        
        // khi ấn nút xóa
        btnXoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (arrTfInfor.get(0).getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn bảo hành cần xóa!");
                    return;
                }
                
                int confirmed = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (confirmed == 0) { // xác nhận xóa
                    ctBaoHanhBUS.deleteCTBaoHanh(arrTfInfor.get(0).getText());
                    blankInfor();
                    table.clearSelection();
                    reloadCTBH(ctBaoHanhBUS.getCtbhList());
                }
            }
        });
        
        // khi ấn nút xem thời hạn bảo hành
        btnXem_TG_BH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            XemDSBaoHanhGUI result = new XemDSBaoHanhGUI();
            System.out.print("chào");
            }
            
        });
        
        
        // khi ấn nút hoàn thành
        btn_hoan_thanh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirmed;
                if (isEditing) { // đang trong chế độ sửa
                    confirmed = JOptionPane.showConfirmDialog(null, "Xác nhận sửa bảo hành", "", JOptionPane.YES_NO_OPTION);
                    if (confirmed == 0) { // xác nhận sửa                 
                        String id_ctbh = arrTfInfor.get(0).getText();
                        String serial = arrTfInfor.get(1).getText();
                        String id_user = arrTfInfor.get(2).getText();
                        String ly_do = arrTfInfor.get(3).getText();
                        String chiphi = arrTfInfor.get(4).getText();
                        String ngaynhan = arrTfInfor.get(5).getText();
                        String ngaytra = arrTfInfor.get(6).getText();
                        
                        
                        
                        int chi_phi = Integer.parseInt(arrTfInfor.get(4).getText());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng ngày
                        LocalDate ngay_nhan = LocalDate.parse(arrTfInfor.get(5).getText(), formatter);
                        System.out.println("Ngày nhận: " + ngay_nhan);
                        LocalDate ngay_tra =LocalDate.parse(arrTfInfor.get(6).getText(), formatter);
                        System.out.println("Ngày trả: " + ngay_tra);
                        
                        
                        try {
                                // Kiểm tra định dạng
                                LocalDate date = LocalDate.parse(ngaynhan, formatter);
                            } catch (DateTimeParseException ee) {
                                JOptionPane.showMessageDialog(null, "Định dạng không hợp lệ! Vui lòng nhập ngày theo định dạng yyyy-MM-dd", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        try {
                                // Kiểm tra định dạng
                                LocalDate date = LocalDate.parse(ngaytra, formatter);
                            } catch (DateTimeParseException ee) {
                                JOptionPane.showMessageDialog(null, "Định dạng không hợp lệ! Vui lòng nhập ngày theo định dạng yyyy-MM-dd", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        
                        
                        if(id_ctbh.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền serial của thiết bị cần bảo hành!");
                        }
                        if(ly_do.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền nguyên nhân bảo hành!");
                        }
                        if(chiphi.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền chi phí bảo hành!");
                        }
                        if(chiphi.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền chi phí bảo hành!");
                        }
                        if(chiphi.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền chi phí bảo hành!");
                        }
                        if(ngaynhan.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền ngày nhận thiết bị");
                        }
                        if(ngaytra.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền ngày trả thiết bị");
                        }
                        if(ngay_nhan.isAfter(ngay_tra)){
                            JOptionPane.showMessageDialog(null, "ngày nhận phải trước ngày trả!");
                        }
                        baoHanhBUS.list();
                        ArrayList<BaoHanhDTO> bhList = baoHanhBUS.getBhList();
                        
                        for (BaoHanhDTO bh : bhList) {    
                            if (serial.equals(bh.getSerial())&& ngay_nhan.isAfter(bh.getNgayHetHan()) ) {
                                JOptionPane.showMessageDialog(null, "thiết bị đã hết hạn bảo hành!");
                            return;
                            }
                        }
                        
                        
                        CTBaoHanhDTO ctbh = new CTBaoHanhDTO(id_ctbh, serial,id_user,  ly_do,chi_phi, ngay_nhan, ngay_tra,  true);
                        ctBaoHanhBUS.updateCTBaoHanh(ctbh);
                        reloadCTBH(ctBaoHanhBUS.getCtbhList());

                        JOptionPane.showMessageDialog(null, "Sửa thành công", "OK", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
                else { // đang trong chế độ thêm
                    confirmed = JOptionPane.showConfirmDialog(null, "Xác nhận thêm bảo hành", "", JOptionPane.YES_NO_OPTION);
                    if (confirmed == 0) { // xác nhận thêm

                        String id_ctbh = arrTfInfor.get(0).getText();
                        String serial = arrTfInfor.get(1).getText();
                        String id_user = arrTfInfor.get(2).getText();
                        String ly_do = arrTfInfor.get(3).getText();
                        String chiphi = arrTfInfor.get(4).getText();
                        String ngaynhan = arrTfInfor.get(5).getText();
                        String ngaytra = arrTfInfor.get(6).getText();
                        
                        int chi_phi = Integer.parseInt(arrTfInfor.get(4).getText());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // Định dạng ngày
                        LocalDate ngay_nhan = LocalDate.parse(arrTfInfor.get(5).getText(), formatter);
                        System.out.println("Ngày nhận: " + ngay_nhan);
                        LocalDate ngay_tra =LocalDate.parse(arrTfInfor.get(6).getText(), formatter);
                        System.out.println("Ngày trả: " + ngay_tra);
                        
                        try {
                                // Kiểm tra định dạng
                                LocalDate date = LocalDate.parse(ngaynhan, formatter);
                            } catch (DateTimeParseException ee) {
                                JOptionPane.showMessageDialog(null, "Định dạng không hợp lệ! Vui lòng nhập ngày theo định dạng yyyy-MM-dd", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        try {
                                // Kiểm tra định dạng
                                LocalDate date = LocalDate.parse(ngaytra, formatter);
                            } catch (DateTimeParseException ee) {
                                JOptionPane.showMessageDialog(null, "Định dạng không hợp lệ! Vui lòng nhập ngày theo định dạng yyyy-MM-dd", "Lỗi", JOptionPane.ERROR_MESSAGE);
                            }
                        
                        
                        if(id_ctbh.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền serial của thiết bị cần bảo hành!");
                        }
                        if(ly_do.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền nguyên nhân bảo hành!");
                        }
                        if(chiphi.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền chi phí bảo hành!");
                        }
                        if(chiphi.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền chi phí bảo hành!");
                        }
                        if(chiphi.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền chi phí bảo hành!");
                        }
                        if(ngaynhan.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền ngày nhận thiết bị");
                        }
                        if(ngaytra.isBlank()){
                            JOptionPane.showMessageDialog(null, "cần điền ngày trả thiết bị");
                        }
                        if(ngay_nhan.isAfter(ngay_tra)){
                            JOptionPane.showMessageDialog(null, "ngày nhận phải trước ngày trả!");
                        }
                        baoHanhBUS.list();
                        ArrayList<BaoHanhDTO> bhList = baoHanhBUS.getBhList();
                        boolean found=false;
                        for (BaoHanhDTO bh : bhList) {
                            if (bh.getSerial().equals(serial)) {
                                found = true;
                            }
                        }
                            
                        if (found == false) {
                                JOptionPane.showMessageDialog(null, "serial không tồn tại!");
                            return;
                        }
                        else{
                            for (BaoHanhDTO bh : bhList) {    
                                if (serial.equals(bh.getSerial())&& ngay_nhan.isAfter(bh.getNgayHetHan()) ) {
                                    JOptionPane.showMessageDialog(null, "thiết bị đã hết hạn bảo hành!");
                                return;
                                }
                            }
                        }
                           
                        CTBaoHanhDTO ctbh = new CTBaoHanhDTO(id_ctbh, serial, id_user,  ly_do, chi_phi, ngay_nhan, ngay_tra, true);
                        ctBaoHanhBUS.addCTBaoHanh(ctbh);

                        reloadCTBH(ctBaoHanhBUS.getCtbhList());
                        blankInfor();
                    }
                }
                btn_hoan_thanh.setVisible(false);

            }
        });
        
        // khi ấn nút trở về
        btn_tro_ve.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                blankInfor();
                lockInfor(true);
                
                // nếu đang trong chế độ sửa khi thoát ra chỉnh isEditing = false
                if (isEditing) isEditing = false;
                
                btnThem.setVisible(true);
                btnSua.setVisible(true);
                btnXoa.setVisible(true);
                btnXem_TG_BH.setVisible(true);
                
                showCN();
                
                btn_hoan_thanh.setVisible(false);
                btn_tro_ve.setVisible(false);
                
                table.setEnabled(true);
            }
        });
            
        // add components
        pn_infor.add(pn_desc, BorderLayout.CENTER);
        pn_infor.add(pn_btn, BorderLayout.EAST);
        
        result.add(pn_infor);
        
        return result;
    }
    
    public JPanel createPnFilter() {
        JPanel pn_filter = new JPanel(new FlowLayout(1, 20, 10));
        
        Font font_filter = new Font("Segoe UI", Font.BOLD, 13);
        JLabel lb_tim_kiem = new JLabel("Tìm kiếm");
        lb_tim_kiem.setFont(font_filter);
        lb_tim_kiem.setForeground(color1);
        
        JPanel pn_tim_kiem = new JPanel(new FlowLayout(1, 0, 0));
        pn_tim_kiem.setPreferredSize(new Dimension(500, 30));
        JComboBox cb_tim_kiem = new JComboBox();
        cb_tim_kiem.setPreferredSize(new Dimension(140, 30));
        cb_tim_kiem.addItem("Mã bảo hành");
        cb_tim_kiem.addItem("Serial");
        cb_tim_kiem.addItem("Mã nhân viên");
        cb_tim_kiem.setForeground(color1);
        cb_tim_kiem.setBackground(colorBackground);
        cb_tim_kiem.setFont(font_filter);
        
        JTextField tf_tim_kiem = new JTextField();
        tf_tim_kiem.setPreferredSize(new Dimension(350, 30));
        tf_tim_kiem.setFont(font_filter);
        tf_tim_kiem.setForeground(color1);
        
        tf_tim_kiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = tf_tim_kiem.getText();
                int choice = cb_tim_kiem.getSelectedIndex();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                }
                else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + "", choice)); 
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = tf_tim_kiem.getText();
                int choice = cb_tim_kiem.getSelectedIndex();
                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                }
                else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text + "", choice)); 
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        
        pn_tim_kiem.add(cb_tim_kiem);
        pn_tim_kiem.add(tf_tim_kiem);
        
        pn_filter.add(lb_tim_kiem);
        pn_filter.add(pn_tim_kiem);
        
        return pn_filter;
    }
    
    
    public JPanel createPnTable() {
        JPanel pn_table = new JPanel(new FlowLayout(1, 0, 0));
        pn_table.setPreferredSize(new Dimension(this.width, 300));
        
        String[] col = {
             "id BH","Serial", "Mã nhân viên", "Lý do", "Chi phí", "Ngày nhận", "Ngày trả"
        };
        this.model = new DefaultTableModel(col, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                // Tất cả các ô đều không thể chỉnh sửa
                return false;
            }
        };
        this.table = new JTable();
        rowSorter = new TableRowSorter<TableModel>(model);
        this.table.setModel(model);
        this.table.setRowSorter(rowSorter);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setPreferredSize(new Dimension(900, 250));
           
        table.getColumnModel().getColumn(0).setPreferredWidth(120);
        table.getColumnModel().getColumn(1).setPreferredWidth(120);
        table.getColumnModel().getColumn(2).setPreferredWidth(120);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(5).setPreferredWidth(120);
        table.getColumnModel().getColumn(6).setPreferredWidth(120);
        this.loadCTBH();
        
        pn_table.add(scroll);
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (table.getRowSorter() != null) {
                    row = table.getRowSorter().convertRowIndexToModel(row);
                }               
                // set thông tin cho khuyến mãi
                arrTfInfor.get(0).setText(table.getModel().getValueAt(row, 0).toString());
                arrTfInfor.get(1).setText(table.getModel().getValueAt(row, 1).toString());
                arrTfInfor.get(2).setText(table.getModel().getValueAt(row, 2).toString());  
                arrTfInfor.get(3).setText(table.getModel().getValueAt(row, 3).toString());   
                arrTfInfor.get(4).setText(table.getModel().getValueAt(row, 4).toString());   
                arrTfInfor.get(5).setText(table.getModel().getValueAt(row, 5).toString());
                arrTfInfor.get(6).setText(table.getModel().getValueAt(row, 6).toString());

                
                if (isEditing) {
                    lockInfor(false);
                }
                else lockInfor(true);
            }
        });
        
        
        // giao diện table
        Font font_table = new Font("Segoe UI", Font.BOLD, 13);
        table.getTableHeader().setBackground(color1);
        table.getTableHeader().setFont(font_table);
        table.getTableHeader().setForeground(this.colorBackground);
        table.getTableHeader().setOpaque(false); 
        table.getTableHeader().setBorder(BorderFactory.createLineBorder(this.color1));
        
        // căn giữa các chữ trong ô
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        for (int i = 0; i < col.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        table.setFocusable(false);
        table.setShowVerticalLines(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setFillsViewportHeight(true);
        table.setSelectionBackground(color3);
        table.setRowHeight(30);
        table.setBorder(BorderFactory.createLineBorder(this.color1));
        return pn_table;
    }
    
    
    public void loadCTBH() {
        if (ctBaoHanhBUS.getCtbhList() == null) {
            ctBaoHanhBUS.list();
        }
        ArrayList<CTBaoHanhDTO> ctbhList = ctBaoHanhBUS.getCtbhList();
        model.setRowCount(0);
        reloadCTBH(ctbhList);
    }
    
    public void reloadCTBH(ArrayList<CTBaoHanhDTO> ctbhList) {
        model.setRowCount(0);
        for (CTBaoHanhDTO ctbh : ctbhList) {
            if (ctbh.isEnable()) {
                model.addRow(new Object[]{
                    ctbh.getId_Ct_Bh(),ctbh.getSerial(),ctbh.getIdUser(), ctbh.getTinhTrang_May(), ctbh.getChiPhi(),  ctbh.getNgayNhan(), ctbh.getNgayTra()
                });
            }
        }
    }
        // khóa khả năng thao tác với thông tin
    public void lockInfor(boolean lock) {
        arrTfInfor.get(1).setEditable(!lock);
        arrTfInfor.get(3).setEditable(!lock);
        arrTfInfor.get(4).setEditable(!lock); 
        arrTfInfor.get(5).setEditable(!lock);
        arrTfInfor.get(6).setEditable(!lock);


    }
    
    public void blankInfor() {
        arrTfInfor.get(0).setText("");
        arrTfInfor.get(1).setText("");
        arrTfInfor.get(2).setText("");
        arrTfInfor.get(3).setText("");
        arrTfInfor.get(4).setText("");
        arrTfInfor.get(5).setText("");
        arrTfInfor.get(6).setText("");
        
    }
    public void showCN() {
        this.btnThem.setVisible(quyenThem);
        this.btnSua.setVisible(quyenSua);
        this.btnXoa.setVisible(quyenXoa);
    }

}
