    package doan1.QuanLyThuVien_DoAn1.model;

    import java.util.Date;

    public class Bao extends TaiLieu {
        private Date ngayPhatHanh;

        public Bao(String maTaiLieu, String tenNhaXuatBan, int soBanPhatHanh, Date ngayPhatHanh) {
            super(maTaiLieu, tenNhaXuatBan, soBanPhatHanh, "Bao");
            this.ngayPhatHanh = ngayPhatHanh;
        }

        public Date getNgayPhatHanh() {
            return ngayPhatHanh;
        }

        public void setNgayPhatHanh(Date ngayPhatHanh) {
            this.ngayPhatHanh = ngayPhatHanh;
        }

        @Override
        public String toString() {
            return "Bao: " +
                    super.toString() + " |\t " +
                    ngayPhatHanh;
        }
    }
