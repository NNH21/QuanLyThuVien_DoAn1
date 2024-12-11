package doan1.QuanLyThuVien_DoAn1.model;

public class TaiLieu {
    private String maTaiLieu;
    private String tenNhaXuatBan;
    private int soBanPhatHanh;
    private String loaiTaiLieu;

    public TaiLieu(String maTaiLieu, String tenNhaXuatBan, int soBanPhatHanh, String loaiTaiLieu) {
        this.maTaiLieu = maTaiLieu;
        this.tenNhaXuatBan = tenNhaXuatBan;
        this.soBanPhatHanh = soBanPhatHanh;
        this.loaiTaiLieu = loaiTaiLieu;
    }

    public String getMaTaiLieu() {
        return maTaiLieu;
    }

    public void setMaTaiLieu(String maTaiLieu) {
        this.maTaiLieu = maTaiLieu;
    }

    public String getTenNhaXuatBan() {
        return tenNhaXuatBan;
    }

    public void setTenNhaXuatBan(String tenNhaXuatBan) {
        this.tenNhaXuatBan = tenNhaXuatBan;
    }

    public int getSoBanPhatHanh() {
        return soBanPhatHanh;
    }

    public void setSoBanPhatHanh(int soBanPhatHanh) {
        this.soBanPhatHanh = soBanPhatHanh;
    }

    public String getLoaiTaiLieu() {
        return loaiTaiLieu;
    }

    public void setLoaiTaiLieu(String loaiTaiLieu) {
        this.loaiTaiLieu = loaiTaiLieu;
    }

    @Override
    public String toString() {
        return "TaiLieu: " +
                maTaiLieu + " |\t " +
                tenNhaXuatBan + " |\t " +
                soBanPhatHanh + " |\t " +
                loaiTaiLieu;
    }
}
