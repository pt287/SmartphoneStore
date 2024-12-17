package gui;

import bus.QuyenBUS;
import bus.UserBUS;
import dto.QuyenDTO;
import dto.UserDTO;
import gui.model.IconModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class NhanVienGUI extends JPanel {
    private int width, height;
    private Color colorBackground = Color.decode("#FFFFFF");
     private Color color1 = Color.decode("#800000");
    private Color color2 = Color.decode("#B22222");
    private Color color3 = Color.decode("#FF6347");

    private JPanel pnInfor, pnFilter, pnTable;
    private ArrayList<JPanel> arrPnInfor;
    private ArrayList<JLabel> arrLbInfor;
    private ArrayList<JTextField> arrTfInfor;
    private JLabel lbImgNhanVien;
    private JComboBox cbGioiTinh, cbQuyen;
    private BufferedImage bufferImg = null;
    private JButton btnThem, btnSua, btnXoa;

        
    private JTable table;
    private TableRowSorter<TableModel> rowSorter;
    private DefaultTableModel model;
    private String imgNhanVien = "null";
    private UserBUS userBUS = new UserBUS();
    private QuyenBUS quyenBUS = new QuyenBUS();
    
    private boolean quyenThem, quyenSua, quyenXoa;
    
    private boolean isEditing = false;
    
    public NhanVienGUI(int width, int height, boolean quyenThem, boolean quyenSua, boolean quyenXoa) {
        this.width = width;
        this.height = height;
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
        loadFirstNVInfo();
    }
public void loadFirstNVInfo() {
    // Lấy nhân viên đầu tiên từ userBUS
    UserDTO firstNVList = userBUS.getUserList().get(0); 

    // Đặt các JTextFields với thông tin nhà cung cấp
    if (firstNVList != null) {

        
        this.arrTfInfor.get(0).setText(firstNVList.getIdUser());
        this.arrTfInfor.get(1).setText(firstNVList.getPassword()); 
        this.arrTfInfor.get(2).setText(firstNVList.getTenUser()); 
//      this.arrTfInfor.get(4).setText(firstNVList.getGioiTinh());
        this.arrTfInfor.get(3).setText(firstNVList.getSdt());
        this.arrTfInfor.get(4).setText(firstNVList.getDiaChi());
//        this.arrTfInfor.get(5).setText(firstNVList.getQuyen());
//        IconModel icon_nv = new IconModel(175, 200, "NhanVien/" + firstNVList.getImgUser());
//        lbImgNhanVien.setText("");
//                lbImgNhanVien.setIcon(icon_nv.createIcon());

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
        
        // create panel avatar
        JPanel pn_avatar = new JPanel(new FlowLayout(1, 0, 25));
        pn_avatar.setPreferredSize(new Dimension(250, 250));
        
        this.lbImgNhanVien = new JLabel("Image", JLabel.CENTER);
        this.lbImgNhanVien.setPreferredSize(new Dimension(175, 200));
        this.lbImgNhanVien.setBorder(BorderFactory.createLineBorder(Color.black));
        
        pn_avatar.add(this.lbImgNhanVien);
        
        // create panel description
        JPanel pn_desc = new JPanel(new FlowLayout(1, 15, 10));

        String[] thuoc_tinh = {"Mã nhân viên", "Mật khẩu", "Tên nhân viên", "Số điện thoại","Địa chỉ"};
        int len = thuoc_tinh.length;
        this.arrPnInfor = new ArrayList<>();
        this.arrLbInfor = new ArrayList<>();
        this.arrTfInfor = new ArrayList<>();
        
        Dimension d_pn = new Dimension(400, 30);
        Dimension d_lb = new Dimension(130, 30);
        Dimension d_tf = new Dimension(220, 30);
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
        this.arrTfInfor.get(0).setEditable(false); // khóa luôn khả năng chỉnh sửa mã nhân viên
        
        JPanel pn_gioi_tinh = new JPanel(new FlowLayout(0, 0, 0));
        pn_gioi_tinh.setPreferredSize(d_pn);
        pn_gioi_tinh.setForeground(color_font);
        pn_gioi_tinh.setFont(font_infor);
        
        JLabel lb_gioitinh = new JLabel("Giới tính");
        lb_gioitinh.setPreferredSize(d_lb);
        lb_gioitinh.setForeground(color_font);
        lb_gioitinh.setFont(font_infor);
        
        String[] gt = {"Nam", "Nữ"};
        this.cbGioiTinh = new JComboBox(gt);
        this.cbGioiTinh.setPreferredSize(d_tf);
        this.cbGioiTinh.setForeground(color_font);
        this.cbGioiTinh.setFont(font_infor);
        
        pn_gioi_tinh.add(lb_gioitinh);
        pn_gioi_tinh.add(this.cbGioiTinh);
        
        JPanel pn_quyen = new JPanel(new FlowLayout(0, 0, 0));
        pn_quyen.setPreferredSize(d_pn);
        pn_quyen.setForeground(color_font);
        pn_quyen.setFont(font_infor);
        
        JLabel lb_quyen = new JLabel("Quyền");
        lb_quyen.setPreferredSize(d_lb);
        lb_quyen.setForeground(color_font);
        lb_quyen.setFont(font_infor);
        
        loadQuyen();
        
        Vector<QuyenDTO> quyen = new Vector<>();
        for (QuyenDTO q : quyenBUS.getQuyenList()) {
            if (q.isEnable()) {
                quyen.add(q);
            }
        }
        this.cbQuyen = new JComboBox(quyen);
        this.cbQuyen.setPreferredSize(d_tf);
        this.cbQuyen.setForeground(color_font);
        this.cbQuyen.setFont(font_infor);
        
        pn_quyen.add(lb_quyen);
        pn_quyen.add(this.cbQuyen);
        
        pn_desc.add(pn_gioi_tinh);
        pn_desc.add(pn_quyen);
        
        // create panel button
        JPanel pn_btn = new JPanel(new FlowLayout(1, 25, 10));
        pn_btn.setPreferredSize(new Dimension(200, 250));
        
        // các nút chức năng mặc định
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");

        
        showCN();
        
        // các nút chức năng phụ
        JButton btn_hoan_thanh = new JButton("Hoàn thành");
        JButton btn_tro_ve = new JButton("Trở về");
        JButton btn_chon_anh = new JButton("Chọn ảnh");
        
        // Thiết kế giao diện nút
        Dimension d_btn = new Dimension(150, 30);
        btnThem.setPreferredSize(d_btn);
        btnSua.setPreferredSize(d_btn);
        btnXoa.setPreferredSize(d_btn);

        
        btn_hoan_thanh.setPreferredSize(d_btn);
        btn_tro_ve.setPreferredSize(d_btn);
        btn_chon_anh.setPreferredSize(d_btn);
        btn_hoan_thanh.setVisible(false);
        btn_tro_ve.setVisible(false);
        btn_chon_anh.setVisible(false);
        
        Color color_button = this.color2;
        btnThem.setBackground(color_button);
        btnSua.setBackground(color_button);
        btnXoa.setBackground(color_button);


        btn_hoan_thanh.setBackground(color_button);
        btn_tro_ve.setBackground(color_button);
        btn_chon_anh.setBackground(color_button);
        
        Color color_font_btn = this.colorBackground;
        btnThem.setForeground(color_font_btn);
        btnSua.setForeground(color_font_btn);
        btnXoa.setForeground(color_font_btn);


        btn_hoan_thanh.setForeground(color_font_btn);
        btn_tro_ve.setForeground(color_font_btn);
        btn_chon_anh.setForeground(color_font_btn);
        
        Font font_btn = new Font("Segoe UI", Font.BOLD, 13);
        btnThem.setFont(font_btn);
        btnSua.setFont(font_btn);
        btnXoa.setFont(font_btn);


        btn_hoan_thanh.setFont(font_btn);
        btn_tro_ve.setFont(font_btn);
        btn_chon_anh.setFont(font_btn);
        
        // thêm các nút
        pn_btn.add(btnThem);
        pn_btn.add(btnSua);
        pn_btn.add(btnXoa);
//        pn_btn.add(btnNhapExcel);
//        pn_btn.add(btnXuatExcel);
        pn_btn.add(btn_hoan_thanh);
        pn_btn.add(btn_tro_ve);
        pn_btn.add(btn_chon_anh);
        
        // khi ấn nút thêm
        btnThem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                blankInfor();
                isEditing = false;
                lockInfor(false);
                
                arrTfInfor.get(0).setText(userBUS.createNewId());
                
                btnThem.setVisible(false);
                btnSua.setVisible(false);
                btnXoa.setVisible(false);

                
                btn_hoan_thanh.setVisible(true);
                btn_tro_ve.setVisible(true);
                btn_chon_anh.setVisible(true);
                
                table.clearSelection();
                table.setEnabled(false);
            }
        });
        
        // khi ấn nút sửa
        btnSua.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (arrTfInfor.get(0).getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần sửa!");
                    return;
                }
                isEditing = true;
                
                lockInfor(false);
                
                btnThem.setVisible(false);
                btnSua.setVisible(false);
                btnXoa.setVisible(false);

                
                btn_hoan_thanh.setVisible(true);
                btn_tro_ve.setVisible(true);
                btn_chon_anh.setVisible(true);
                
                table.setEnabled(false);
            }
        });
        
        // khi ấn nút xóa
        btnXoa.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (arrTfInfor.get(0).getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên cần xóa!");
                    return;
                }
                
                int confirmed = JOptionPane.showConfirmDialog(null, "Xác nhận xóa", "Alert", JOptionPane.YES_NO_OPTION);
                if (confirmed == 0) { // xác nhận xóa
                    userBUS.deleteUser(arrTfInfor.get(0).getText());
                    blankInfor();
                    table.clearSelection();
                    reloadUser(userBUS.getUserList());
                }
            }
        });
        
        
        
        // khi ấn nút hoàn thành
        btn_hoan_thanh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirmed;
                if (isEditing) { // đang trong chế độ sửa
                    confirmed = JOptionPane.showConfirmDialog(null, "Xác nhận sửa nhân viên", "", JOptionPane.YES_NO_OPTION);
                    if (confirmed == 0) { // xác nhận sửa
                        String id = arrTfInfor.get(0).getText();
                        String pass = arrTfInfor.get(1).getText();
                        String ten = arrTfInfor.get(2).getText();
                        String gt = (String) cbGioiTinh.getItemAt(cbGioiTinh.getSelectedIndex());
                        String sdt = arrTfInfor.get(3).getText();
                        String dc = arrTfInfor.get(4).getText();
                        QuyenDTO quyenDTO = (QuyenDTO)cbQuyen.getSelectedItem();
                        String quyen = quyenDTO.getIdQuyen();
                        String newImg = imgNhanVien;
                        
                        if ( pass.isBlank() ) {
                            JOptionPane.showMessageDialog(null, "Điền mật khẩu  !");
                            return;
                        }
                        
                        if ( ten.isBlank() ) {
                            JOptionPane.showMessageDialog(null, "Điền tên nhân viên!");
                            return;
                        }
                        if (ten.matches(".*\\d.*")) {
                            JOptionPane.showMessageDialog(null, "Tên nhân viên không thể điền số!");
                            return;
                        }
                        
                        if ( sdt.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Điền số điện thoại!");   
                            return;
                        }                                                                  
                        if (!sdt.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, " số điện thoại chỉ có thể điền số!");
                            return;
                        }
                        
                        if (!sdt.matches("^0\\d{9}$")) {
                            JOptionPane.showMessageDialog(null, "số điện thoại phải là 10 chữ số và bắt đầu từ số 0");
                            return;
                        }
                        ArrayList<UserDTO> userList = userBUS.getUserList();
                        for (UserDTO us : userList) {
                            if (sdt.equals(us.getSdt() )&& !id.equals(us.getIdUser() )) {
                                JOptionPane.showMessageDialog(null, "số điện thoại đã tồn tại!");
                            return;
                            }
                        }
                        
                        UserDTO user = new UserDTO(id, pass, ten, gt, sdt,dc, quyen, newImg, true);
                        userBUS.updateUser(user);
                        
                        saveImg();
                        reloadUser(userBUS.getUserList());
                        
                        JOptionPane.showMessageDialog(null, "Sửa thành công", "OK", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else { // đang trong chế độ thêm
                    confirmed = JOptionPane.showConfirmDialog(null, "Xác nhận thêm nhân viên", "", JOptionPane.YES_NO_OPTION);
                    if (confirmed == 0) { // xác nhận thêm
                        String id = arrTfInfor.get(0).getText();
                        String pass = arrTfInfor.get(1).getText();
                        String ten = arrTfInfor.get(2).getText();
                        String gt = (String) cbGioiTinh.getItemAt(cbGioiTinh.getSelectedIndex());
                        String sdt = arrTfInfor.get(3).getText();
                        String dc = arrTfInfor.get(4).getText();
                        QuyenDTO quyenDTO = (QuyenDTO)cbQuyen.getSelectedItem();
                        String quyen = quyenDTO.getIdQuyen();
                        String newImg = imgNhanVien;
                        
                        if (ten.isEmpty() || ten.isBlank() ) {
                            JOptionPane.showMessageDialog(null, "Điền tên nhân viên!");
                            return;
                        }
                        if (pass.isEmpty() || pass.isBlank() ) {
                            JOptionPane.showMessageDialog(null, "Điền mật khẩu!");
                            return;
                        }
                        if (sdt.isEmpty() || sdt.isBlank()) {
                            JOptionPane.showMessageDialog(null, "Điền số điện thoại!");
                            return;
                        }
                        if (!sdt.matches("\\d+")) {
                            JOptionPane.showMessageDialog(null, "Số đhỉ có thể điền số!");
                            return;
                        }
                        if (!sdt.matches("^0\\d{9}$")) {
                            JOptionPane.showMessageDialog(null, "số điện thoại phải là 10 chữ số và bắt đầu từ số 0");
                            return;
                        }
                        ArrayList<UserDTO> userList = userBUS.getUserList();
                        for (UserDTO us : userList) {
                            if (sdt.equals(us.getSdt())) {
                                JOptionPane.showMessageDialog(null, "số điện thoại đã tồn tại!");
                            return;
                            }
                        }
                        
                        UserDTO user = new UserDTO(id, pass, ten, gt, sdt,dc, quyen, newImg, true);
                        userBUS.addUser(user);
                        
                        saveImg();                        
                        reloadUser(userBUS.getUserList());

                        blankInfor();
                    }
                }
                 btn_hoan_thanh.setVisible(false);
                 btn_chon_anh.setVisible(false);
                 lockInfor(true);
            }
        });
        
        // khi ấn nút trở về
        btn_tro_ve.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                blankInfor();
                
                // nếu đang trong chế độ sửa khi thoát ra chỉnh isEditing = false
                if (isEditing) isEditing = false;
                
                btnThem.setVisible(true);
                btnSua.setVisible(true);
                btnXoa.setVisible(true);

                
                showCN();
                
                btn_hoan_thanh.setVisible(false);
                btn_tro_ve.setVisible(false);
                btn_chon_anh.setVisible(false);
                
                table.setEnabled(true);
            }
        });
        
        // khi ấn nút chọn ảnh
        btn_chon_anh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser fc = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG images", "jpg", "png");
                fc.setFileFilter(filter);
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fc.getSelectedFile();
                        bufferImg = ImageIO.read(file);
                        // set tên ảnh là tên mã nhân viên
                        imgNhanVien = arrTfInfor.get(0).getText().concat(".png");      

                        lbImgNhanVien.setText("");
                        lbImgNhanVien.setIcon(new ImageIcon(bufferImg.getScaledInstance(175, 200, Image.SCALE_SMOOTH)));
                    } catch (IOException ex) {
                        Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        // add components
        pn_infor.add(pn_avatar, BorderLayout.WEST);
        pn_infor.add(pn_desc, BorderLayout.CENTER);
        pn_infor.add(pn_btn, BorderLayout.EAST);
        
        result.add(pn_infor);
        
        return result;
    }
    
    public JPanel createPnFilter() {
        JPanel pn_filter = new JPanel(new FlowLayout(1, 10, 10));
        
        Font font_filter = new Font("Segoe UI", Font.BOLD, 13);
        JLabel lb_tim_kiem = new JLabel("Tìm kiếm");
        lb_tim_kiem.setFont(font_filter);
        lb_tim_kiem.setForeground(color1);
        
        JPanel pn_tim_kiem = new JPanel(new FlowLayout(1, 0, 0));
        pn_tim_kiem.setPreferredSize(new Dimension(320, 30));
        JComboBox cb_tim_kiem = new JComboBox();
        cb_tim_kiem.setPreferredSize(new Dimension(120, 30));
        cb_tim_kiem.addItem("Mã nhân viên");
        cb_tim_kiem.addItem("Tên nhân viên");
        cb_tim_kiem.addItem("Số điện thoại");
        cb_tim_kiem.addItem("Địa chỉ");


        cb_tim_kiem.setForeground(color1);
        cb_tim_kiem.setBackground(colorBackground);
        cb_tim_kiem.setFont(font_filter);
        
        JTextField tf_tim_kiem = new JTextField();
        tf_tim_kiem.setPreferredSize(new Dimension(200, 30));
        tf_tim_kiem.setFont(font_filter);
        tf_tim_kiem.setForeground(color1);
        
        tf_tim_kiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = tf_tim_kiem.getText();
                int choice = cb_tim_kiem.getSelectedIndex();
                if(cb_tim_kiem.getSelectedIndex()==1){
                    choice =2;
                }else if(cb_tim_kiem.getSelectedIndex()==2){
                    choice =4;
                }else if(cb_tim_kiem.getSelectedIndex()==3){
                    choice =5;
                }
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
        
        // Khu lọc theo một số thuộc tính giới tính
        JLabel lb_gioitinh = new JLabel("Giới tính", JLabel.CENTER);
        String[] gioitinh = {"", "Nam", "Nữ"};    
        JComboBox cb_gioitinh = new JComboBox(gioitinh);
        cb_gioitinh.setPreferredSize(new Dimension(70, 30));
        
        JLabel lb_quyen = new JLabel("Quyền", JLabel.CENTER);
        Vector<QuyenDTO> quyen = new Vector<>();
        quyen.add(new QuyenDTO("", "", true));
        for (QuyenDTO q : quyenBUS.getQuyenList()) {
            if (q.isEnable()) {
                quyen.add(q);
            }
        }
        JComboBox cb_quyen = new JComboBox(quyen);
        cb_quyen.setPreferredSize(new Dimension(200, 30));
        
        JButton btn_loc = new JButton("Lọc");
        btn_loc.setPreferredSize(new Dimension(70, 30));
        
        btn_loc.addMouseListener(new MouseAdapter() { 
            @Override
            public void mouseClicked(MouseEvent e) {
                String gioitinh_filter = (String) cb_gioitinh.getItemAt(cb_gioitinh.getSelectedIndex());
//                String quyen_filter = (String) cb_quyen.getItemAt(cb_quyen.getSelectedIndex());
                QuyenDTO quyenDTO = (QuyenDTO)cb_quyen.getSelectedItem();
                String quyen_filter = quyenDTO.getIdQuyen();
                reloadUser(userBUS.filter(gioitinh_filter, quyen_filter));
            }
        });
        
        //
        
        lb_tim_kiem.setFont(font_filter);
        tf_tim_kiem.setFont(font_filter);
        lb_gioitinh.setFont(font_filter);
        cb_gioitinh.setFont(font_filter);
        lb_quyen.setFont(font_filter);
        
        lb_tim_kiem.setForeground(color1);
        tf_tim_kiem.setForeground(color1);
        lb_gioitinh.setForeground(color1);
        cb_gioitinh.setForeground(color1);
        lb_quyen.setForeground(color1);
        
        btn_loc.setBackground(color2);
        btn_loc.setFont(font_filter);
        btn_loc.setForeground(this.colorBackground);
        
        pn_tim_kiem.add(cb_tim_kiem);
        pn_tim_kiem.add(tf_tim_kiem);
        
        pn_filter.add(lb_tim_kiem);
        pn_filter.add(pn_tim_kiem);
        pn_filter.add(lb_gioitinh);
        pn_filter.add(cb_gioitinh);
        pn_filter.add(lb_quyen);
        pn_filter.add(cb_quyen);
        pn_filter.add(btn_loc);
        
        
        


        return pn_filter;
    }
    
    public JPanel createPnTable() {
        JPanel pn_table = new JPanel(new FlowLayout(1, 0, 0));
        pn_table.setPreferredSize(new Dimension(this.width, 290));
        
        String[] col = {
            "Mã nhân viên", "Mật khẩu", "Tên nhân viên", "Giới tính", "SĐT","Địa chỉ", "Quyền", "IMG"
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
        
        table.getColumnModel().getColumn(0).setPreferredWidth(30);
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(70);
        table.getColumnModel().getColumn(5).setPreferredWidth(40);
        table.getColumnModel().getColumn(6).setPreferredWidth(40);
        table.getColumnModel().getColumn(7).setPreferredWidth(40);
        this.loadUser();
        
        pn_table.add(scroll);
        
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (table.getRowSorter() != null) {
                    row = table.getRowSorter().convertRowIndexToModel(row);
                }
                imgNhanVien = table.getModel().getValueAt(row, 7).toString();
                IconModel icon_nv = new IconModel(175, 200, "NhanVien/" + imgNhanVien);
                
                // set thông tin cho sản phẩm
                arrTfInfor.get(0).setText(table.getModel().getValueAt(row, 0).toString());
                arrTfInfor.get(1).setText(table.getModel().getValueAt(row, 1).toString());
                arrTfInfor.get(2).setText(table.getModel().getValueAt(row, 2).toString());
                arrTfInfor.get(3).setText(table.getModel().getValueAt(row, 4).toString());
                cbGioiTinh.setSelectedItem(table.getModel().getValueAt(row, 3).toString());
                arrTfInfor.get(4).setText(table.getModel().getValueAt(row, 5).toString());
                for (QuyenDTO q : quyenBUS.getQuyenList()) {
                    if (q.isEnable() && q.getIdQuyen().equals(table.getModel().getValueAt(row, 6).toString())) {
                        cbQuyen.setSelectedItem(q);
                        break;
                    }
                }
                
                lbImgNhanVien.setText("");
                lbImgNhanVien.setIcon(icon_nv.createIcon());
                
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
    
    public void loadUser() {
        if (userBUS.getUserList()== null) {
            userBUS.list();
        }
        ArrayList<UserDTO> userList = userBUS.getUserList();
        model.setRowCount(0);
        reloadUser(userList);
    }
    
    public void reloadUser(ArrayList<UserDTO> userList) {
        model.setRowCount(0);
        for (UserDTO us : userList) {
            if (us.isEnable()) {
                model.addRow(new Object[]{
                    us.getIdUser(), us.getPassword(), us.getTenUser(), us.getGioiTinh(), us.getSdt(), us.getDiaChi(), us.getQuyen(), us.getImgUser()
                });
            }
        }
    }
    
    public void loadQuyen() {
        if (quyenBUS.getQuyenList() == null) {
            quyenBUS.list();
        }
    }
    
    public void saveImg() {
        try {
            if (bufferImg != null) {
                File save = new File("src/img/NhanVien/" + imgNhanVien);
                ImageIO.write(bufferImg, "png", save);
                bufferImg = null;
            }
        } catch (IOException ex) {
            Logger.getLogger(NhanVienGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    // khóa khả năng thao tác với thông tin
    public void lockInfor(boolean lock) {
        arrTfInfor.get(1).setEditable(!lock);
        arrTfInfor.get(2).setEditable(!lock);
        arrTfInfor.get(3).setEditable(!lock);
        arrTfInfor.get(4).setEditable(!lock);    
        cbGioiTinh.setEnabled(!lock);
        cbQuyen.setEnabled(!lock);
    }
    
    public void blankInfor() {
        arrTfInfor.get(0).setText("");
        arrTfInfor.get(1).setText("");
        arrTfInfor.get(2).setText("");
        arrTfInfor.get(3).setText("");
        arrTfInfor.get(4).setText("");
        lbImgNhanVien.setIcon(null);
        lbImgNhanVien.setText("Image");
        imgNhanVien = "null";
    }
    
    public void showCN() {
        this.btnThem.setVisible(quyenThem);
        this.btnSua.setVisible(quyenSua);
        this.btnXoa.setVisible(quyenXoa);

    }
    
    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
}
