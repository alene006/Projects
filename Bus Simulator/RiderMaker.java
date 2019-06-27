// Event to create a Rider on semi-random interval, choosing random destination
// one RiderMaker should be made per Stop for the simulation
// modeled after CarMaker2.java from week 10 lecture examples

import java.util.Arrays;

public class RiderMaker implements Event {

    public RiderMaker(int intval, int stop_num, int[] servDist, double[] intDist) {
        interval = intval;
        stop = stop_num;
        destinationDist = servDist;
        intervalDist = intDist;
    }

    public RiderMaker(int intval, int stop_num) {
        interval = intval;
        stop = stop_num;

        // destination distribution array, giving 2x chance for downtown stops
        destinationDist = new int[] {0,0,1,1,2,3,4,5,6,7,8,9,10,11,12,13,14,14,15,15,
                                        16,16,17,18,19,20,21,22,23,24,25,26,27,28,29,29};
        // interval distribution array, based on given percentages
        intervalDist = new double[] {.75, .75, .5, .5, .5, .2, .2, .2, .2, 0, 0,-.2, -.2, -.2, -.5, -.5, -.5, -.75, -.75};
    }

    private int intRandomInterval(int low, int high) {
        return (int) Math.floor((high - low) * Math.random() + low + 0.5);
    }

    // selects a random destination, not equal to Rider's source (stop)
    private int selectDestination() {
        // calculate new random destination, unique from the current stop
        int d = stop;
        while (d==stop) {
            d = destinationDist[intRandomInterval(0, destinationDist.length - 1)];
        }
        return d;
    } // End selectDestination


    // selects a random interval for scheduling a new RiderMaker
    private double selectInterval() {
        return intervalDist[intRandomInterval(0, intervalDist.length - 1)]*interval;
    }


    // schedules new RiderMaker event in the future
    // adds a new Rider to one of Stop stop's queue
    public void run() {
        double nextEvent = selectInterval();
        if ((stop >=18 && stop <=22) || (stop >= 0 && stop <=4)) nextEvent -= 10;
        else if (stop>=15 && stop<=17) nextEvent -= 5;
        // if downtown stop, shorten arrival rate by 10 seconds
        // if campus stop, shorten arrival rate by 10 seconds

        // don't allow interval to be negative
        if (nextEvent < 0) nextEvent = 0;

        BusSim.agenda.add(new RiderMaker(interval, stop), nextEvent);
        new Rider(BusSim.agenda.getCurrentTime(), stop, selectDestination(), stop);
    } // End run

    private int stop;
    private int interval;
    private int[] destinationDist;
    private double[] intervalDist;

} //End RiderMaker
