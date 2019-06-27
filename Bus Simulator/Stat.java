// class with all static variables and methods to keep track of
// statistics for the simulation
// Based on Stat2.java from week 10 lecture examples

public class Stat {

    // updates wait times and travel times given a Rider
    public static void updateRiderStat(Rider p) {
        if (p != null) {
            double wait = p.getBoardTime() - p.getArrivalTime();
            double travel = 180 + wait;
            if (wait > maxWait) maxWait = wait;
            averageWait += wait;
            averageTravel += travel;
            if (travel > maxTravel) maxTravel = travel;
            count++;
        }
    }

    // updates fill stats and queue stats for a Bus
    // Stat is only calculated when Bus is at a Stop
    public static void updateBusStat(Bus t) {
        if (t != null) {
            // update bus statistics
            int riders = t.totalRiders();
            averageFill += riders;
            // All trips are the same time length, so each bus relation to riders is weighted equally
            if (riders > maxFill) maxFill = riders;

            // update queue statistics
            int q_length = 0;
            if (t.getDirection().equals("e"))
                q_length = BusSim.stops[t.getCurStop()].eastLength();
            else if (t.getDirection().equals("w"))
                q_length = BusSim.stops[t.getCurStop()].westLength();
            averageQLength += q_length;
            if (q_length > maxQLength) maxQLength = q_length;

            busCount++;
        }
    }

    public static void displayStats() {
        System.out.println("-- STATITISTICS OUTPUT--\n");
        System.out.println("Average waiting time: " + (averageWait/count));
        System.out.println("Maximum waiting time: " + maxWait+"\n");

        System.out.println("Average travel time: " + (averageTravel/count));
        System.out.println("Maximum travel time: " + maxTravel+"\n");

        System.out.println("Average stop queue length: " + averageQLength/busCount);
        System.out.println("Maximum stop queue length: " + maxQLength+"\n");

        System.out.println("Average bus fill: " + averageFill/busCount);
        System.out.println("Average bus fill %: " + ((averageFill*100)/(busCount*BusSim.numOfOtherBus*50))+"%");
        System.out.println("Maximum bus fill: " + maxFill);
        System.out.println("Maximum bus fill %: " + ((100*maxFill)/(BusSim.numOfOtherBus*50))+"%");
    }

    private static int count;

    private static double maxWait;
    private static double averageWait;
    private static int maxQLength;
    private static double averageQLength;

    private static int maxFill;
    private static int averageFill;
    private static int busCount;

    private static double maxTravel;
    private static double averageTravel;
} // End of Stat
