// Event for a Bus b arriving at a stop (scheduled every 4 minutes)

public class BusEvent implements Event {
     public BusEvent(Bus b) {
         this.b = b;
         elapsed_time = 0;
     }


    // Debarks all Riders wanting to get off at b's curStop
    // boards all Riders going in the same direction as b
    // updates Stat variables
    // advances b's spot appropriately
    // schedules a new BusEvent for b when it arrives at the next Stop
    public void run() {
        double init_time = BusSim.agenda.getCurrentTime();

        // update Bus and queue stats
        Stat.updateBusStat(b);

        b.leave();
        b.board();

        // max time of 15 second wait
        if (BusSim.agenda.getCurrentTime() - init_time < 15)
            elapsed_time = 15;

        // add 4 minutes of travel
        elapsed_time += 240;
        b.update();

        // add new BusEvent
        BusSim.agenda.add(new BusEvent(b), elapsed_time);
    }

    private Bus b;
    private double elapsed_time;
} // End BusEvent
