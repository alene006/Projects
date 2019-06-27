// adopted from CarSim.java from week 10 lecture examples

public class BusSim {

    // modifiable variables
    static int numOfOtherBus = 3;
    static int numBus = 23;
    static int pass_interval = 120;

    // public variables
    static Stop[] stops = new Stop[30];
    static Bus[] buses = new Bus[numBus];
    static PQ agenda = new PQ();

    public static void main(String args[]) {

        // initialize all 29 Stops and 29 RiderMakers

        for (int a=0; a<stops.length; a++) {
            stops[a] = new Stop(a);
            agenda.add(new RiderMaker(pass_interval, a), a);
        }

        // initialize all Buss

        // calculate even spacing for Buss' initial positions
        double spacing = (2*stops.length)/ buses.length;
        int spot;
        String direct;
        for (int b=0; b<buses.length; b++) {
            spot = (int) Math.floor(b * spacing);
            if (spot > 29) {
                direct = "w";
               spot = 29 - (spot-29);
                // for certain numbers of buses, spot is set to negative integer
                // see Bus constructor for adjustment
            } else direct = "e";

            // add Bus to array
            // create it's first BusEvent for arriving at initial Stop
            buses[b] = new Bus(spot,direct,numOfOtherBus);
            agenda.add(new BusEvent(buses[b]),b);
        }


        // run simulation
        while (agenda.getCurrentTime() <= 10000)
            agenda.remove().run();

        Stat.displayStats();

    }  // End main


} // End BusSim
