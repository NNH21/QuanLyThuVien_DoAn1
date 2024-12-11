package doan1.QuanLyThuVien_DoAn1.view;

import doan1.QuanLyThuVien_DoAn1.controller.QuanLySach;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import doan1.QuanLyThuVien_DoAn1.model.*;

public class QuanLyView extends JFrame {
    private QuanLySach quanLySach;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField tfMaTaiLieu, tfTenNhaXuatBan, tfSoBanPhatHanh, tfTenTacGia, tfSoTrang, tfSoPhatHanh, tfThangPhatHanh, tfNgayPhatHanh;
    private JButton btnThem, btnXoa, btnHienThi, btnTimKiem, btnExit;

    public QuanLyView() {
        // Khởi tạo dữ liệu quản lý
        quanLySach = new QuanLySach();

        // Thiết lập cửa sổ
        setTitle("QUẢN LÝ TÀI LIỆU THƯ VIỆN");
        setSize(1300, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        getContentPane().setBackground(Color.LIGHT_GRAY);

        // Tiêu đề chính
        JLabel lblTitle = new JLabel("QUẢN LÝ TÀI LIỆU THƯ VIỆN", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setForeground(Color.RED);
        add(lblTitle, BorderLayout.NORTH);

        // Bảng hiển thị
        String[] columnNames = {"Mã Tài Liệu", "Tên Nhà Xuất Bản", "Số Bản Phát Hành", "Tên Tác Giả", "Số Trang", "Số Phát Hành", "Tháng Phát Hành", "Ngày Phát Hành"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Form nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        getContentPane().setBackground(Color.LIGHT_GRAY);

        inputPanel.add(createLabel("MÃ TÀI LIỆU(*):"));
        tfMaTaiLieu = new JTextField();
        inputPanel.add(tfMaTaiLieu);

        inputPanel.add(createLabel("TÊN NHÀ XUẤT BẢN(*):"));
        tfTenNhaXuatBan = new JTextField();
        inputPanel.add(tfTenNhaXuatBan);

        inputPanel.add(createLabel("SỐ BẢN PHÁT HÀNH(*):"));
        tfSoBanPhatHanh = new JTextField();
        inputPanel.add(tfSoBanPhatHanh);

        inputPanel.add(createLabel("TÊN TÁC GIẢ(Sách):"));
        tfTenTacGia = new JTextField();
        inputPanel.add(tfTenTacGia);

        inputPanel.add(createLabel("SỐ TRANG(Sách):"));
        tfSoTrang = new JTextField();
        inputPanel.add(tfSoTrang);

        inputPanel.add(createLabel("SỐ PHÁT HÀNH(Tạp Chí):"));
        tfSoPhatHanh = new JTextField();
        inputPanel.add(tfSoPhatHanh);

        inputPanel.add(createLabel("THÁNG PHÁT HÀNH(Tạp Chí):"));
        tfThangPhatHanh = new JTextField();
        inputPanel.add(tfThangPhatHanh);

        inputPanel.add(createLabel("NGÀY PHÁT HÀNH(Báo):"));
        tfNgayPhatHanh = new JTextField();
        inputPanel.add(tfNgayPhatHanh);


        add(inputPanel, BorderLayout.WEST);

        // Nút chức năng
        JPanel buttonPanel = new JPanel(new GridLayout(1, 6, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(getContentPane().getBackground());

        btnThem = new JButton("THÊM TÀI LIỆU");
        btnThem.setBackground(Color.ORANGE);
        btnXoa = new JButton("XÓA TÀI LIỆU");
        btnXoa.setBackground(Color.ORANGE);
        btnHienThi = new JButton("HIỂN THỊ TÀI LIỆU");
        btnHienThi.setBackground(Color.ORANGE);
        btnTimKiem = new JButton("TÌM KIẾM TÀI LIỆU");
        btnTimKiem.setBackground(Color.ORANGE);
        btnExit = new JButton("THOÁT");
        btnExit.setBackground(Color.ORANGE);

        buttonPanel.add(btnThem);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnHienThi);
        buttonPanel.add(btnTimKiem);
        buttonPanel.add(btnExit);

        add(buttonPanel, BorderLayout.SOUTH);

        // Gắn sự kiện cho các nút
        btnThem.addActionListener(this::handleAddTaiLieu);
        btnXoa.addActionListener(this::handleDeleteTaiLieu);
        btnHienThi.addActionListener(this::handleDisplayTaiLieu);
        btnTimKiem.addActionListener(this::handleSearchTaiLieu);
        btnExit.addActionListener(this::handleExitProgram);
    }

    public JLabel createLabel(String text) {
        return new JLabel("<html>" + text.replaceAll("\\((.*?)\\)", "<font color='red'>($1)</font>") + "</html>");
    }

    // Xử lý nút "Thêm Tài Liệu"
    private void handleAddTaiLieu(ActionEvent e) {
        String maTaiLieu = tfMaTaiLieu.getText();
        String tenNhaXuatBan = tfTenNhaXuatBan.getText();
        String soBanPhatHanhStr = tfSoBanPhatHanh.getText();

        if (maTaiLieu.isEmpty() || tenNhaXuatBan.isEmpty() || soBanPhatHanhStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin vào các ô bắt buộc.");
            return;
        }

        int soBanPhatHanh = Integer.parseInt(soBanPhatHanhStr);
        boolean isAdded = false;

        boolean isSachFilled = !tfTenTacGia.getText().isEmpty() && !tfSoTrang.getText().isEmpty();
        boolean isTapChiFilled = !tfSoPhatHanh.getText().isEmpty() && !tfThangPhatHanh.getText().isEmpty();
        boolean isBaoFilled = !tfNgayPhatHanh.getText().isEmpty();

        if (isSachFilled && isTapChiFilled && isBaoFilled) {
            JOptionPane.showMessageDialog(this, "Chỉ điền thông tin vào một trong ba ô (Sách, Tạp Chí, Báo).");
            return;
        } else if (isSachFilled && isTapChiFilled) {
            JOptionPane.showMessageDialog(this, "Chỉ điền thông tin vào Sách hoặc Tạp Chí.");
            return;
        } else if (isSachFilled && isBaoFilled) {
            JOptionPane.showMessageDialog(this, "Chỉ điền thông tin vào Sách hoặc Báo.");
            return;
        } else if (isTapChiFilled && isBaoFilled) {
            JOptionPane.showMessageDialog(this, "Chỉ điền thông tin vào Tạp Chí hoặc Báo.");
            return;
        }

        if (isSachFilled) {
            String tenTacGia = tfTenTacGia.getText();
            int soTrang = Integer.parseInt(tfSoTrang.getText());
            Sach sach = new Sach(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, tenTacGia, soTrang);
            isAdded = quanLySach.addTaiLieu(sach);
        } else if (isTapChiFilled) {
            int soPhatHanh = Integer.parseInt(tfSoPhatHanh.getText());
            int thangPhatHanh = Integer.parseInt(tfThangPhatHanh.getText());
            TapChi tapChi = new TapChi(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, soPhatHanh, thangPhatHanh);
            isAdded = quanLySach.addTaiLieu(tapChi);
        } else if (isBaoFilled) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date ngayPhatHanh = dateFormat.parse(tfNgayPhatHanh.getText());
                Bao bao = new Bao(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, ngayPhatHanh);
                isAdded = quanLySach.addTaiLieu(bao);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Ngày phát hành không hợp lệ. Vui lòng nhập theo định dạng dd/MM/yyyy.");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin vào các ô bắt buộc.");
            return;
        }

        if (isAdded) {
            JOptionPane.showMessageDialog(this, "Thêm tài liệu thành công!");
            handleDisplayTaiLieu(null);
        } else {
            JOptionPane.showMessageDialog(this, "Thêm tài liệu thất bại!");
        }
    }

    // Xử lý nút "Xóa Tài Liệu"
    private void handleDeleteTaiLieu(ActionEvent e) {
        int[] selectedRows = table.getSelectedRows();
        String maTaiLieuInput = tfMaTaiLieu.getText().trim();

        if (selectedRows.length > 0 || !maTaiLieuInput.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa các tài liệu đã chọn?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean allDeleted = true;

                // Delete selected rows
                for (int selectedRow : selectedRows) {
                    String maTaiLieu = (String) tableModel.getValueAt(selectedRow, 0);
                    boolean isDeleted = quanLySach.deleteTaiLieu(maTaiLieu);
                    if (!isDeleted) {
                        allDeleted = false;
                    }
                }

                // Delete by input document code
                if (!maTaiLieuInput.isEmpty()) {
                    boolean isDeleted = quanLySach.deleteTaiLieu(maTaiLieuInput);
                    if (!isDeleted) {
                        allDeleted = false;
                    }
                }

                if (allDeleted) {
                    JOptionPane.showMessageDialog(this, "Xóa tài liệu thành công!");
                    handleDisplayTaiLieu(null);
                } else {
                    JOptionPane.showMessageDialog(this, "Không thể xóa một số tài liệu.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ít nhất một dòng để xóa hoặc nhập mã tài liệu.");
        }
    }

    private void handleDisplayTaiLieu(ActionEvent e) {
        tableModel.setRowCount(0);
        List<TaiLieu> taiLieuList = quanLySach.getAllTaiLieu();
        for (TaiLieu taiLieu : taiLieuList) {
            if (taiLieu instanceof Sach) {
                Sach sach = (Sach) taiLieu;
                tableModel.addRow(new Object[]{sach.getMaTaiLieu(), sach.getTenNhaXuatBan(), sach.getSoBanPhatHanh(), sach.getTenTacGia(), sach.getSoTrang(), "", "", ""});
            } else if (taiLieu instanceof TapChi) {
                TapChi tapChi = (TapChi) taiLieu;
                tableModel.addRow(new Object[]{tapChi.getMaTaiLieu(), tapChi.getTenNhaXuatBan(), tapChi.getSoBanPhatHanh(), "", "", tapChi.getSoPhatHanh(), tapChi.getThangPhatHanh(), ""});
            } else if (taiLieu instanceof Bao) {
                Bao bao = (Bao) taiLieu;
                tableModel.addRow(new Object[]{bao.getMaTaiLieu(), bao.getTenNhaXuatBan(), bao.getSoBanPhatHanh(), "", "", "", "", bao.getNgayPhatHanh()});
            }
        }
    }

    // Xử lý nút "Tìm Kiếm Tài Liệu"
    private void handleSearchTaiLieu(ActionEvent e) {
        String[] options = {"Sach", "TapChi", "Bao"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        int result = JOptionPane.showConfirmDialog(this, comboBox, "Chọn loại tài liệu", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String loaiTaiLieu = (String) comboBox.getSelectedItem();
            tableModel.setRowCount(0);
            List<TaiLieu> taiLieuList = quanLySach.searchTaiLieu(loaiTaiLieu);
            for (TaiLieu taiLieu : taiLieuList) {
                if (taiLieu instanceof Sach) {
                    Sach sach = (Sach) taiLieu;
                    tableModel.addRow(new Object[]{sach.getMaTaiLieu(), sach.getTenNhaXuatBan(), sach.getSoBanPhatHanh(), sach.getTenTacGia(), sach.getSoTrang(), "", "", ""});
                } else if (taiLieu instanceof TapChi) {
                    TapChi tapChi = (TapChi) taiLieu;
                    tableModel.addRow(new Object[]{tapChi.getMaTaiLieu(), tapChi.getTenNhaXuatBan(), tapChi.getSoBanPhatHanh(), "", "", tapChi.getSoPhatHanh(), tapChi.getThangPhatHanh(), ""});
                } else if (taiLieu instanceof Bao) {
                    Bao bao = (Bao) taiLieu;
                    tableModel.addRow(new Object[]{bao.getMaTaiLieu(), bao.getTenNhaXuatBan(), bao.getSoBanPhatHanh(), "", "", "", "", bao.getNgayPhatHanh()});
                }
            }
        }
    }

    // Xử lý nút Exit
    private void handleExitProgram(ActionEvent e) {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thoát?", "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            QuanLyView frame = new QuanLyView();
            frame.setVisible(true);
        });
    }
}