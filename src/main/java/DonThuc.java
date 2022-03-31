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

    public String rutGon(){
        StringBuilder sb = new StringBuilder();
        for(TuDon bien : this.donThuc){
            sb.append(bien.rutGon());
        }
        return sb.toString();
    }

    public DonThuc rutGonHaiDonThuc(DonThuc dt){ //Ung dung ca luat phan phoi, luat dong nhat va luat bu
        //Dung de chua ky tu giong nhau
        StringBuilder sb = new StringBuilder();
        //sb2 va sb3 dung de chua ky tu khac nhau
        StringBuilder sb2 = new StringBuilder();
        StringBuilder sb3 = new StringBuilder();
        if(dt.getDonThuc().size() == this.donThuc.size()){
            for(int i = 0; i < this.donThuc.size(); i++){
                ArrayList<TuDon> temp = dt.getDonThuc();
                if(temp.get(i).getBien().matches(this.getDonThuc().get(i).getBien())){
                    if(temp.get(i).getBit() == '0'){
                        sb.append(temp.get(i).getLetter()).append("'");
                    }
                    else
                        sb.append(temp.get(i).getLetter());
                }
                else{
                    //Tach 2 tu don khac nhau ra 2 chuoi rieng
                    sb2.append(temp.get(i).getBien());
                    sb3.append(this.donThuc.get(i).getBien());
                }
            }
            if(sb2.length() > 0 && sb3.length() > 0){
                DonThuc dt1 = new DonThuc(sb2.toString());
                DonThuc dt2 = new DonThuc(sb3.toString());
                //System.out.printf("%s\t%s\n",dt1.toString(),dt2.toString());
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
}
