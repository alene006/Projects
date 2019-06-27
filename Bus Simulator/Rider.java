// class to model each passenger
// holds details on travel time + pickup and destination stop

public class Rider {
    double arrivalTime;  // time arrived at stop
    double boardTime;  // time boarded bus
    int pickup;  // initial stop
    int destination;
    String direction;  // direction of travel(east or west)

    public Rider() {}

    public Rider(double arrivalTime, int pickup, int destination, double currentStop) {
        this.arrivalTime = arrivalTime;
        this.pickup = pickup;
        this.destination = destination;

        if (destination > currentStop) direction = "w";
        else direction = "e";

        // add to Stop queue in correct direction
        if (direction.equals("e")) BusSim.stops[pickup].addEast(this);
        if (direction.equals("w")) BusSim.stops[pickup].addWest(this);
    }

    // method to set board time once passenger boards bus
    public void setBoardTime(double boardTime) { this.boardTime = boardTime;}

    // getters

    public double getBoardTime() {return boardTime;}

    public double getArrivalTime() {return arrivalTime;}

    public double getDestination() {return destination;}
} // End Rider
