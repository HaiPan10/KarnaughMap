import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class QuanLyTeBaoLon {
    private ArrayList<TeBaoLon> danhSachTeBaoLon;
    private ArrayList<String> cacGiaTriMot;
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
        this.setCacGiaTriMot(cacGiaTriMot);
        //Them vao cac don thuc
        for(String temp : cacGiaTriMot){
            DonThuc newDonThuc = DonThuc.nhiPhanSangDonThuc(temp, getPattern());
            danhSachDonThuc.add(newDonThuc);
        }

        boolean flag = false; //Trong biểu thức có còn kết được nữa hay không
        while(!flag){
            boolean flag2 = false; //Trong truong hop 1 te bao khong the gop voi te bao nao khac nua
            ArrayList<DonThuc> temp = new ArrayList<>();
            for(int i = 0; i < danhSachDonThuc.size(); i++){
                DonThuc donThuc1 = danhSachDonThuc.get(i);
                for(int j = 0; j < danhSachDonThuc.size(); j++){
                    DonThuc donThuc2 = danhSachDonThuc.get(j);
                    DonThuc ketQua = donThuc1.rutGonHaiDonThuc(donThuc2);
                    if(ketQua != null && !ketQua.toString().equals(donThuc1.toString())){
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
                int n = temp.size();
                for(int i = 1; i < n; i++){
                    for(int j = 0; j < i; j++){
                        if(temp.get(i).toString().equals(temp.get(j).toString())){
                            temp.remove(j);
                            --i;
                            --n;
                        }
                    }
                }
                danhSachDonThuc = temp;
            }
            else
                flag = true;
        }
        //Tao danh sach gom cac don thuc duoc loc ra
        //Bien doi sang te bao lon
        for(DonThuc donThuc : danhSachDonThuc){
            TeBaoLon teBao = new TeBaoLon(donThuc, getPattern());
            getDanhSachTeBaoLon().add(teBao);
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

    public ArrayList<TeBaoLon> cacTeBaoNhatThietChon(){
        if(cacGiaTriMot == null || this.danhSachTeBaoLon == null)
            return null;
        @SuppressWarnings("unchecked")
        ArrayList<String> cloneCacGiaTriMot = (ArrayList<String>) this.cacGiaTriMot.clone();
        @SuppressWarnings("unchecked")
        ArrayList<TeBaoLon> cloneTeBao = (ArrayList<TeBaoLon>) this.danhSachTeBaoLon.clone();
        //clone la gia tri copy tu cac gia tri mot
        ArrayList<TeBaoLon> choose = new ArrayList<>(); //Chon cac te bao nhat thiet phai chon
        //Duyet qua tung cac gia tri mot
        for(int i = 0; i < cloneCacGiaTriMot.size(); i++){
            //Tao temp de luu lai cac te bao lon chua gia tri mot do
            ArrayList<TeBaoLon> temp = new ArrayList<>();
            DonThuc donThuc = DonThuc.nhiPhanSangDonThuc(cloneCacGiaTriMot.get(i),pattern);
            for(TeBaoLon teBao : cloneTeBao){
                if(teBao.getTeBaoLon().rutGonHaiDonThuc(donThuc) != null){
                    temp.add(teBao);
                }
            }
            //Neu chi co mot te bao lon chua gia tri mot
            //thi no se la te bao lon nhat thiet phai chon
            //dong thoi loai bo cac gia tri mot thuoc te bao lon
            if(temp.size() != 0){
                //Tien hanh chon te bao lon nhat trong danh sach chon duoc
                TeBaoLon teBao = temp.get(0);
                for(int index = 1; index < temp.size(); index++){
                    if(temp.get(index).getTeBaoLon().toString().length() < teBao.getTeBaoLon().toString().length()){
                        teBao = temp.get(index);
                    }
                }
                choose.add(teBao);
                //Loc ra cac so nhi phan cua te bao duoc chon
                ArrayList<String> binary = KarnaughMap.locBinary(cloneCacGiaTriMot,teBao.getTeBaoLon().getBinary(pattern));
                i = -1;
                //Loai bo te bao da duoc chon ra khoi danh sach
                for(TeBaoLon temp2 : cloneTeBao){
                    if(temp2.getTeBaoLon().toString().equals(teBao.getTeBaoLon().toString())){
                        cloneTeBao.remove(temp2);
                        break;
                    }
                }
                //Loai bo cac vi tri gia tri mot
                for (String s : binary) {
                    for (int k = 0; k < cloneCacGiaTriMot.size(); k++) {
                        if (s.equals(cloneCacGiaTriMot.get(k))) {
                            cloneCacGiaTriMot.remove(k);
                            break;
                        }
                    }
                }
            }
        }
        return choose;
    }

    public ArrayList<String> getCacGiaTriMot() {
        return cacGiaTriMot;
    }

    public void setCacGiaTriMot(ArrayList<String> cacGiaTriMot) {
        this.cacGiaTriMot = cacGiaTriMot;
    }

    public static String taoDaThuc(ArrayList<TeBaoLon> danhSachTeBao){
        StringBuilder sb = new StringBuilder();
        for(TeBaoLon teBao : danhSachTeBao){
            sb.append(teBao.getTeBaoLon().toString()).append("+");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
