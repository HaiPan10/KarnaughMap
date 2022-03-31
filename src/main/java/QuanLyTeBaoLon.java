import java.util.ArrayList;

public class QuanLyTeBaoLon {
    private ArrayList<TeBaoLon> danhSachTeBaoLon;
    private String pattern;

    public QuanLyTeBaoLon(String pattern){
        setDanhSachTeBaoLon(new ArrayList<>());
        setPattern(pattern);
    }

    public void themTeBaoLon(TeBaoLon teBao){
        getDanhSachTeBaoLon().add(teBao);
    }

    public void chonTeBaoLon(ArrayList<String> cacGiaTriMot){
        ArrayList<DonThuc> danhSachDonThuc = new ArrayList<>();
        //Them vao cac don thuc
        for(String temp : cacGiaTriMot){
            danhSachDonThuc.add(DonThuc.nhiPhanSangDonThuc(temp, getPattern()));
        }

        boolean flag = false;
        while(!flag){
            boolean flag2 = false; //Trong truong hop 1 te bao khong the gop voi te bao nao khac nua
            ArrayList<DonThuc> temp = new ArrayList<>();
            for(int i = 0; i < danhSachDonThuc.size() - 1; i++){
                DonThuc donThuc1 = danhSachDonThuc.get(i);
                for(int j = 0; j < danhSachDonThuc.size(); j++){
                    DonThuc donThuc2 = danhSachDonThuc.get(j);
                    DonThuc ketQua = donThuc1.rutGonHaiDonThuc(donThuc2);
                    if(ketQua != null){
                        flag = true;
                        flag2 = true;
                        temp.add(ketQua);
                    }
                }
                if(!flag2){
                    temp.add(danhSachDonThuc.get(i));
                }
                else
                    flag2 = false;
            }
            if(flag){
                flag = false;
                danhSachDonThuc.clear();
                danhSachDonThuc = temp;
            }
            else
                flag = true;
        }
        //Tao danh sach gom cac don thuc duoc loc ra
        //Bien doi sang te bao lon
        for(DonThuc donThuc : danhSachDonThuc){
            TeBaoLon teBao = new TeBaoLon(donThuc, pattern);
            if(!isContains(teBao)){
                danhSachTeBaoLon.add(teBao);
            }
        }
    }

    public void hienThi(){
        for(TeBaoLon teBao : getDanhSachTeBaoLon()){
            System.out.printf("Te Bao Lon: %s\n",teBao.getTeBaoLon().toString());
            teBao.hienThi();;
        }
    }

    public ArrayList<TeBaoLon> getDanhSachTeBaoLon() {
        return danhSachTeBaoLon;
    }

    public void setDanhSachTeBaoLon(ArrayList<TeBaoLon> danhSachTeBaoLon) {
        this.danhSachTeBaoLon = danhSachTeBaoLon;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public boolean isContains(TeBaoLon teBao){
        for(TeBaoLon temp : danhSachTeBaoLon){
            if(teBao.getTeBaoLon().toString().equals(temp.getTeBaoLon().toString())){
                return true;
            }
        }
        return false;
    }

    public ArrayList<TeBaoLon> cacTeBaoNhatThietChon(){
        return null;
    }
}
