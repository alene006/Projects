// Object to represent a Bus, travelling from stop to stop
// Contains an array of Buses to hold Riders
// Boards and removes Riders based on appropriate BusEvents

public class Bus {
    private int curStop;
    private String direction;
    private AnotherBus[] buses;

    public Bus(int initialStop, String direction, int num_buses) {
        if (initialStop < 0) initialStop = 0;
        curStop = initialStop;
        this.direction = direction;
        buses = new AnotherBus[num_buses];
        for (int i=0; i<num_buses; i++) {
            buses[i] = new AnotherBus();
        }
    }

    // simple getters and setters

    public int length() {return buses.length;}


    public int numCars() { return buses.length;}

    public String getDirection() {return direction;}

    public int getCurStop() {return curStop;}

    public void setCurStop(int stop) {curStop = stop;}


    // Returns number of Riders in all Buss
    public int totalRiders() {
        int count = 0;
        for (int i=0; i<buses.length; i++) {
            count += buses[i].numRiders();
        }
        return count;
    }

    // Returns true if there is a Rider in any Bus that
    // has Stop curStop as its destination
    public boolean canRemove() {
        for (int i=0; i<buses.length; i++){
            if (buses[i].containsStop(curStop)) return true;
        }
        return false;
    }

    // Adds all Riders boarding at Stop curStop in correct direction
    // Could throw BusFullException from boardRider() calls
    public void board() {
        if (direction.equals("e")) {
            try {
                BusSim.agenda.add(new RiderEvent("add",this),2);
            } catch (BusFullException e) {}
        }
        else if (direction.equals("w")) {
            try {
                BusSim.agenda.add(new RiderEvent("add",this),2);
            } catch (BusFullException e) {}
        }
    } // End board

    // Removes all Riders who get off at Stop curStop
    // Calculates statistics for each Rider who leaves
    public void leave() {
        BusSim.agenda.add(new RiderEvent("remove",this),2);
    }

    // Puts Rider r into the first non-full Bus
    public void boardRider(Rider r) {
        for (int i=0; i < buses.length; i++) {
            if (!buses[i].isFull()) {
                buses[i].addRider(r);
                r.setBoardTime(BusSim.agenda.getCurrentTime());
                return;
            }
        }
    } // End boardRider

    // Removes Rider r who gets off at Stop curStop
    // Calculates statistics from r
    public void removeRider() {
        Rider r;
        for (int i=0; i<buses.length; i++){
           if (canRemove()) {
               r = buses[i].removeRider(curStop);

               Stat.updateRiderStat(r);
               return;
            }
        }
    } // End removeRider


    // Increment Bus's curStop and update direction where appropriate
    public void update() {
        if (direction.equals("e")) {
            curStop++;
            if (curStop > 29) {
                curStop = 29;
                direction = "w";
            }
        }
        else if (direction.equals("w")) {
            curStop--;
            if (curStop < 0) {
                curStop = 0;
                direction = "e";
            }
        }
    } //End update
} // End Bus
