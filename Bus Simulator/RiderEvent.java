// Event for Rider boarding/leaving a Bus
// Calls another RiderEvent of same type (e.g. board or leave) if possible
// uses instance methods from Bus

public class RiderEvent implements Event {
    public RiderEvent(String type, Bus b) {
        action = type;
        this.b = b;
    }

    public void run() {
        if (action.equals("add")) {
            if (b.getDirection().equals("e")) {
                if (!BusSim.stops[b.getCurStop()].isEastEmpty())
                    b.boardRider(BusSim.stops[b.getCurStop()].removeEast());

                // if another passenger wants to board
                if (!BusSim.stops[b.getCurStop()].isEastEmpty()) {
                    BusSim.agenda.add(new RiderEvent("add",b), 1);
                }  // else don't add a new RiderEvent
            }
            else if (b.getDirection().equals("w")) {
                if (!BusSim.stops[b.getCurStop()].isWestEmpty())
                    b.boardRider(BusSim.stops[b.getCurStop()].removeWest());

                // if another passenger wants to board
                if (!BusSim.stops[b.getCurStop()].isWestEmpty()) {
                    BusSim.agenda.add(new RiderEvent("add", b), 1);
                }  // else don't add a new RiderEvent
            }
        }
        else if (action.equals("remove")) {
            b.removeRider();

            // if another passenger wants to get off
            if (b.canRemove()) {
                BusSim.agenda.add(new RiderEvent("remove",b), 2);
            }  // else don't add a new RiderEvent
        }
    } // End run

    private String action; // Determines whether to add or remove Riders
    private Bus b;
} // End RiderEvent
