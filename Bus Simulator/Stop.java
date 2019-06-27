// Object to model a Bus stop
// Contains a queue (Q) for eastbound passengers and one for westbound passengers

public class Stop {
    private Q east_q;
    private Q west_q;
    private int num;

    public Stop(int stop_num) {
        num = stop_num;
        east_q = new Q2();
        west_q = new Q2();
    }

    public void addEast(Rider r) { east_q.add(r);}

    public void addWest(Rider r) { west_q.add(r);}

    public Rider removeEast() { return (Rider) east_q.remove();}

    public Rider removeWest() { return (Rider) west_q.remove();}

    public boolean isEastEmpty() { return east_q.length()==0;}

    public boolean isWestEmpty() { return west_q.length()==0;}

    public int eastLength() { return east_q.length(); }

    public int westLength() { return west_q.length(); }
}
