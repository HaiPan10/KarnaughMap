import java.util.ArrayList;

public class DonThuc {
    private ArrayList<TuDon> donThuc;

    public ArrayList<TuDon> getDonThuc() {
        return this.donThuc;
    }

    public DonThuc(){
        this.donThuc = new ArrayList<>();
    }

    public DonThuc(ArrayList<TuDon> donThuc){
        this.donThuc = donThuc;
    }

    public DonThuc(String donThuc){
        this.donThuc = new ArrayList<>();
        String filter = donThuc.replaceAll("(?!^)",".");
        donThuc = filter.replaceAll("[.](?=['])|[.]$|[.]^", "");
        String[] cacTuDon = donThuc.split("[.]");
        for(String temp : cacTuDon){
            TuDon td = new TuDon(temp);
            this.donThuc.add(td);
        }
    }

    public void setDonThuc(ArrayList<TuDon> cacTuDon) {
        this.donThuc = cacTuDon;
    }

    public void themTuDon(TuDon td){
        donThuc.add(td);
    }

    public void hienThi(){
        for(TuDon td : donThuc){
            td.hienThi();
        }
    }

    public String getBinary(String pattern){ //get a binary string from a pattern also have value x
        StringBuilder sb = new StringBuilder(pattern);
        for(TuDon bien : donThuc){
            int index = pattern.indexOf(Character.toString(bien.getLetter()));
            if(index != -1){
                sb.setCharAt(index,bien.getBit());
            }
        }
        for(int i = 0; i < sb.length(); i++){
            if(sb.charAt(i) != '0' && sb.charAt(i) != '1')
                sb.setCharAt(i,'x');
        }
        return sb.toString();
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(TuDon td : donThuc){
            sb.append(td.getBien());
        }
        return sb.toString();
    }

    public DonThuc rutGon(){
        DonThuc result = new DonThuc();
        result.setDonThuc((ArrayList<TuDon>)this.donThuc.clone());
        for(int i = 1; i < result.getDonThuc().size(); i++){
            for(int j = 0; j < i; j++){
                if(result.getDonThuc().get(j).getLetter() == result.getDonThuc().get(i).getLetter()){
                    if(result.getDonThuc().get(j).getBien().equals(result.getDonThuc().get(i).getBien())){
                        result.getDonThuc().remove(j);
                        break;
                    }
                    else
                        return null;
                }
            }
        }
        return result;
    }

    public DonThuc rutGonHaiDonThuc(DonThuc dt){ //Ung dung ca luat phan phoi, luat dong nhat va luat bu
        //Dung de chua ky tu giong nhau
        if(dt.toString().equals(this.toString())) //2 don thuc giong nhau
        {
            return dt;
        }
        StringBuilder sb = new StringBuilder();
        //Dung de chua 2 ky tu khac nhau
        //Temp1 se luu lai chuoi cua dt
        String temp1 = dt.toString();
        //temp2 se them vao nhung ky tu khac nhau
        StringBuilder temp2 = new StringBuilder();
        for(TuDon tuDon : this.getDonThuc()){
            DonThuc cloneDt = new DonThuc(temp1);
            if(cloneDt.chuaTuDon(tuDon)){
                sb.append(tuDon.getBien());
                //Xoa di cac ky tu giong nhau ben temp1
                temp1 = temp1.replaceAll(tuDon.getBien(),"");
            }
            else{
                //Nhung ky tu khac nhau se duoc luu lai temp2
                temp2.append(tuDon.getBien());
            }
        }

        if(temp1.length() > 0 && temp2.length() > 0){
            DonThuc dt1 = new DonThuc(temp1);
            DonThuc dt2 = new DonThuc(temp2.toString());
            if(dt1.getDonThuc().size() == 1 && dt2.getDonThuc().size() == 1){
                //Neu nhu 2 don thuc da co cung kich thuoc thi tien hanh duyet 2 tu don
                TuDon tuDon1 = dt1.getDonThuc().get(0);
                TuDon tuDon2 = dt2.getDonThuc().get(0);
                if(Character.compare(tuDon1.getLetter(),tuDon2.getLetter()) == 0){
                    if(Integer.parseInt(Character.toString(tuDon1.getBit())) +
                            Integer.parseInt(Character.toString(tuDon2.getBit())) == 1){
                        return new DonThuc(sb.toString());
                    }
                }
            }
        }
        else if(temp1.length() == 0 || temp2.length() == 0){
            return new DonThuc(sb.toString());
        }
        return null;
    }

    //Chieu dang binary va pattern phai bang nhau
    public static DonThuc nhiPhanSangDonThuc(String binary, String pattern){
        StringBuilder sb = new StringBuilder();
        for(int i =0; i < binary.length(); i++){
            if(binary.charAt(i) == '0'){
                sb.append(pattern.charAt(i)).append("'");
            }
            else
                sb.append(pattern.charAt(i));
        }
        return new DonThuc(sb.toString());
    }

    public boolean chuaTuDon(TuDon tuDon){
        for(TuDon temp : this.getDonThuc()){
            if(temp.getBien().equals(tuDon.getBien()))
                return true;
        }
        return false;
    }
}
