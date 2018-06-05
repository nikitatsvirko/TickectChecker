package by.belzhd.android.tickectchecker.data;

public class PassengerDisemb {

    private int id;
    private String secondName;
    private String initials;
    private int carriageNumber;
    private int seatNumber;
    private String status;

    public PassengerDisemb(int id, String secondName, String initials, int carriageNumber, int seatNumber, String status) {
        this.id = id;
        this.secondName = secondName;
        this.initials = initials;
        this.carriageNumber = carriageNumber;
        this.seatNumber = seatNumber;
        this.status = status;
    }

    public PassengerDisemb() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public int getCarriageNumber() {
        return carriageNumber;
    }

    public void setCarriageNumber(int carriageNumber) {
        this.carriageNumber = carriageNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
