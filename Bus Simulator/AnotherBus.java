// AnotherBus with a LinkedList to hold its Riders

public class AnotherBus {
    private RiderList pass;

    public AnotherBus() {
        pass = new RiderList();
    }

    // returns number of Riders in this
    public int numRiders() {
        return pass.size();
    }

    // Adds Rider p to the List, and updates p's board time
    // Throws BusFullException if p cannot be added
    public void addRider(Rider p) {
        // addRider should only be called if this AnotherBus is not full
        if (isFull()) throw new BusFullException();

        pass.add(p);
        p.setBoardTime(BusSim.agenda.getCurrentTime());
    }

    // Removes the first Rider in the list who gets off at Stop "stop".
    // Returns null if no Riders have stop as destination
    public Rider removeRider(int stop) {
        for (int i=0; i<pass.size(); i++) {
            Rider r = (Rider) pass.get(i);
            if (r.getDestination() == stop) return r;
        }
        return null;
    }

    // Returns true if there is a Rider who has "stop" as destination
    public boolean containsStop(int stop) {
        return pass.containsStop(stop);
    }

    // Returns true if AnotherBus has 50 Riders, false otherwise.
    public boolean isFull() {
        return (pass.size() == 50);
    }
} // End AnotherBus
