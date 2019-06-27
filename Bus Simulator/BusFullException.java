// Runtime Exception for trying to add a Passenger to a full train

public class BusFullException extends RuntimeException {
    public BusFullException() {
        super("Bus is full - could not add passenger.");
    }

    public BusFullException(String s) {
        super(s);
    }
}
