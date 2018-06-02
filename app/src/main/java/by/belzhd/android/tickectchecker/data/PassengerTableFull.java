package by.belzhd.android.tickectchecker.data;

public class PassengerTableFull {

    private String secondName;
    private String initials;
    private int carriageNumber;
    private int seatNumber;
    private String startStation;
    private String endStation;
    private String status;

    public PassengerTableFull(String secondName, String initials, int carriageNumber, int seatNumber, String startStation, String endStation, String status) {
        this.secondName = secondName;
        this.initials = initials;
        this.carriageNumber = carriageNumber;
        this.seatNumber = seatNumber;
        this.startStation = startStation;
        this.endStation = endStation;
        this.status = status;
    }

    public PassengerTableFull() {

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

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
