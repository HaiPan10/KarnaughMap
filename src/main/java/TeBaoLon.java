public class TeBaoLon {
    private KarnaughMap map;
    private DonThuc teBaoLon;

    public TeBaoLon(DonThuc dt, String pattern){
        this.setMap(new KarnaughMap(dt, pattern));
        this.setTeBaoLon(dt);
    }

    public KarnaughMap getMap() {
        return map;
    }

    public void setMap(KarnaughMap map) {
        this.map = map;
    }

    public DonThuc getTeBaoLon() {
        return teBaoLon;
    }

    public void setTeBaoLon(DonThuc teBaoLon) {
        this.teBaoLon = teBaoLon;
    }

    public void hienThi(){
        map.hienThi();
    }
}