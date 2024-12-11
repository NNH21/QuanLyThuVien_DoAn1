package doan1.QuanLyThuVien_DoAn1.view;

import doan1.QuanLyThuVien_DoAn1.controller.QuanLySach;
import doan1.QuanLyThuVien_DoAn1.model.Bao;
import doan1.QuanLyThuVien_DoAn1.model.Sach;
import doan1.QuanLyThuVien_DoAn1.model.TaiLieu;
import doan1.QuanLyThuVien_DoAn1.model.TapChi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class QuanLyCMD {
    private QuanLySach quanLySach;
    private Scanner scanner;

    public QuanLyCMD() {
        quanLySach = new QuanLySach();
        scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n==== QUẢN LÝ TÀI LIỆU THƯ VIỆN ====");
            System.out.println("1. Thêm tài liệu");
            System.out.println("2. Xóa tài liệu");
            System.out.println("3. Hiển thị danh sách tài liệu");
            System.out.println("4. Tìm kiếm tài liệu");
            System.out.println("5. Thoát");
            System.out.print("Chọn một tùy chọn: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    themTaiLieu();
                    break;
                case 2:
                    xoaTaiLieu();
                    break;
                case 3:
                    hienThiTaiLieu();
                    break;
                case 4:
                    timKiemTaiLieu();
                    break;
                case 5:
                    System.out.println("Chương trình kết thúc.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        }
    }

    private void themTaiLieu() {
        System.out.print("Nhập mã tài liệu: ");
        String maTaiLieu = scanner.nextLine();

        System.out.print("Nhập tên nhà xuất bản: ");
        String tenNhaXuatBan = scanner.nextLine();

        System.out.print("Nhập số bản phát hành: ");
        int soBanPhatHanh = Integer.parseInt(scanner.nextLine());

        System.out.println("Chọn loại tài liệu:");
        System.out.println("1. Sách");
        System.out.println("2. Tạp Chí");
        System.out.println("3. Báo");
        System.out.print("Lựa chọn: ");
        int loaiTaiLieu = Integer.parseInt(scanner.nextLine());

        switch (loaiTaiLieu) {
            case 1:
                System.out.print("Nhập tên tác giả: ");
                String tenTacGia = scanner.nextLine();

                System.out.print("Nhập số trang: ");
                int soTrang = Integer.parseInt(scanner.nextLine());

                Sach sach = new Sach(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, tenTacGia, soTrang);
                quanLySach.addTaiLieu(sach);
                break;

            case 2:
                System.out.print("Nhập số phát hành: ");
                int soPhatHanh = Integer.parseInt(scanner.nextLine());

                System.out.print("Nhập tháng phát hành: ");
                int thangPhatHanh = Integer.parseInt(scanner.nextLine());

                TapChi tapChi = new TapChi(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, soPhatHanh, thangPhatHanh);
                quanLySach.addTaiLieu(tapChi);
                break;

            case 3:
                System.out.print("Nhập ngày phát hành (dd/MM/yyyy): ");
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date ngayPhatHanh = dateFormat.parse(scanner.nextLine());
                    Bao bao = new Bao(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, ngayPhatHanh);
                    quanLySach.addTaiLieu(bao);
                } catch (ParseException e) {
                    System.out.println("Ngày phát hành không hợp lệ!");
                }
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ.");
        }
    }

    private void xoaTaiLieu() {
        System.out.print("Nhập mã tài liệu cần xóa: ");
        String maTaiLieu = scanner.nextLine();

        if (quanLySach.deleteTaiLieu(maTaiLieu)) {
            System.out.println("Xóa tài liệu thành công!");
        } else {
            System.out.println("Không tìm thấy tài liệu với mã: " + maTaiLieu);
            System.out.println("Xóa thất bại!");
        }
    }

    private void hienThiTaiLieu() {
        List<TaiLieu> taiLieuList = quanLySach.getAllTaiLieu();

        if (taiLieuList.isEmpty()) {
            System.out.println("Danh sách tài liệu trống.");
        } else {
            System.out.println("\nDANH SÁCH TÀI LIỆU:");
            for (TaiLieu taiLieu : taiLieuList) {
                System.out.println(taiLieu);
            }
        }
    }

    private void timKiemTaiLieu() {
        System.out.println("Chọn loại tài liệu để tìm kiếm:");
        System.out.println("1. Sách");
        System.out.println("2. Tạp Chí");
        System.out.println("3. Báo");
        System.out.print("Lựa chọn: ");
        int loaiTaiLieu = Integer.parseInt(scanner.nextLine());

        String loai;
        if (loaiTaiLieu == 1) loai = "Sach";
        else if (loaiTaiLieu == 2) loai = "TapChi";
        else if (loaiTaiLieu == 3) loai = "Bao";
        else {
            System.out.println("Lựa chọn không hợp lệ.");
            return;
        }

        List<TaiLieu> taiLieuList = quanLySach.searchTaiLieu(loai);
        if (taiLieuList.isEmpty()) {
            System.out.println("Không tìm thấy tài liệu nào thuộc loại: " + loai);
        } else {
            System.out.println("\nDANH SÁCH TÌM KIẾM:");
            for (TaiLieu taiLieu : taiLieuList) {
                System.out.println(taiLieu);
            }
        }
    }

    public static void main(String[] args) {
        new QuanLyCMD().run();
    }
}
