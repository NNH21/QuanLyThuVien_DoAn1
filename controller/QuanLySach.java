package doan1.QuanLyThuVien_DoAn1.controller;

import doan1.QuanLyThuVien_DoAn1.model.*;
import doan1.QuanLyThuVien_DoAn1.db.dbConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

public class QuanLySach {
    public List<TaiLieu> getAllTaiLieu() {
        List<TaiLieu> taiLieus = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = dbConnect.connectToDatabase();
            if (conn != null) {
                stm = conn.createStatement();
                String sql = "SELECT * FROM TaiLieu";
                rs = stm.executeQuery(sql);
                while (rs.next()) {
                    String maTaiLieu = rs.getString("maTaiLieu");
                    String tenNhaXuatBan = rs.getString("tenNhaXuatBan");
                    int soBanPhatHanh = rs.getInt("soBanPhatHanh");
                    String loaiTaiLieu = rs.getString("loaiTaiLieu");

                    TaiLieu taiLieu = null;
                    if ("Sach".equalsIgnoreCase(loaiTaiLieu)) {
                        String tenTacGia = rs.getString("tenTacGia");
                        int soTrang = rs.getInt("soTrang");
                        taiLieu = new Sach(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, tenTacGia, soTrang);
                    } else if ("TapChi".equalsIgnoreCase(loaiTaiLieu)) {
                        int soPhatHanh = rs.getInt("soPhatHanh");
                        int thangPhatHanh = rs.getInt("thangPhatHanh");
                        taiLieu = new TapChi(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, soPhatHanh, thangPhatHanh);
                    } else if ("Bao".equalsIgnoreCase(loaiTaiLieu)) {
                        Date ngayPhatHanh = rs.getDate("ngayPhatHanh");
                        taiLieu = new Bao(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, ngayPhatHanh);
                    } else {
                        taiLieu = new TaiLieu(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu);
                    }
                    taiLieus.add(taiLieu);
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return taiLieus;
    }

    public boolean addTaiLieu(TaiLieu taiLieu) {
        Connection conn = null;
        Statement stm = null;
        int row = 0;
        try {
            conn = dbConnect.connectToDatabase();
            if (conn != null) {
                stm = conn.createStatement();
                String sql = "INSERT INTO TaiLieu (maTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu";
                if (taiLieu instanceof Sach) {
                    Sach sach = (Sach) taiLieu;
                    sql += ", tenTacGia, soTrang) VALUES ('" + sach.getMaTaiLieu() + "', '" + sach.getTenNhaXuatBan() + "', " + sach.getSoBanPhatHanh() + ", 'Sach', '" + sach.getTenTacGia() + "', " + sach.getSoTrang() + ")";
                } else if (taiLieu instanceof TapChi) {
                    TapChi tapChi = (TapChi) taiLieu;
                    sql += ", soPhatHanh, thangPhatHanh) VALUES ('" + tapChi.getMaTaiLieu() + "', '" + tapChi.getTenNhaXuatBan() + "', " + tapChi.getSoBanPhatHanh() + ", 'TapChi', " + tapChi.getSoPhatHanh() + ", " + tapChi.getThangPhatHanh() + ")";
                } else if (taiLieu instanceof Bao) {
                    Bao bao = (Bao) taiLieu;
                    sql += ", ngayPhatHanh) VALUES ('" + bao.getMaTaiLieu() + "', '" + bao.getTenNhaXuatBan() + "', " + bao.getSoBanPhatHanh() + ", 'Bao', '" + new java.sql.Date(bao.getNgayPhatHanh().getTime()) + "')";
                } else {
                    sql += ") VALUES ('" + taiLieu.getMaTaiLieu() + "', '" + taiLieu.getTenNhaXuatBan() + "', " + taiLieu.getSoBanPhatHanh() + ", 'TaiLieu')";
                }
                row = stm.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return row > 0;
    }

    public boolean deleteTaiLieu(String maTaiLieu) {
        Connection conn = null;
        Statement stm = null;
        int row = 0;
        try {
            conn = dbConnect.connectToDatabase();
            if (conn != null) {
                stm = conn.createStatement();
                String sql = "DELETE FROM TaiLieu WHERE maTaiLieu = '" + maTaiLieu + "'";
                row = stm.executeUpdate(sql);
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return row > 0;
    }

    public List<TaiLieu> searchTaiLieu(String loaiTaiLieu) {
        List<TaiLieu> taiLieus = new ArrayList<>();
        Connection conn = null;
        Statement stm = null;
        ResultSet rs = null;
        try {
            conn = dbConnect.connectToDatabase();
            if (conn != null) {
                stm = conn.createStatement();
                String sql = "SELECT * FROM TaiLieu WHERE loaiTaiLieu = '" + loaiTaiLieu + "'";
                rs = stm.executeQuery(sql);
                while (rs.next()) {
                    String maTaiLieu = rs.getString("maTaiLieu");
                    String tenNhaXuatBan = rs.getString("tenNhaXuatBan");
                    int soBanPhatHanh = rs.getInt("soBanPhatHanh");

                    TaiLieu taiLieu = null;
                    if ("Sach".equalsIgnoreCase(loaiTaiLieu)) {
                        String tenTacGia = rs.getString("tenTacGia");
                        int soTrang = rs.getInt("soTrang");
                        taiLieu = new Sach(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, tenTacGia, soTrang);
                    } else if ("TapChi".equalsIgnoreCase(loaiTaiLieu)) {
                        int soPhatHanh = rs.getInt("soPhatHanh");
                        int thangPhatHanh = rs.getInt("thangPhatHanh");
                        taiLieu = new TapChi(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, soPhatHanh, thangPhatHanh);
                    } else if ("Bao".equalsIgnoreCase(loaiTaiLieu)) {
                        Date ngayPhatHanh = rs.getDate("ngayPhatHanh");
                        taiLieu = new Bao(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, ngayPhatHanh);
                    } else {
                        taiLieu = new TaiLieu(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, loaiTaiLieu);
                    }
                    taiLieus.add(taiLieu);
                }
            }
        } catch (SQLException e) {
            System.out.println("Loi: " + e.getMessage());
        }
        return taiLieus;
    }
}