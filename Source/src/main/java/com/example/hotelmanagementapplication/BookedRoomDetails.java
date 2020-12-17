package com.example.hotelmanagementapplication;

public class BookedRoomDetails {

    public String guestName;
    public String roomType;
    public String kidsNum;
    public String adultNum;
    public String checkInDate;
    public String checkOutDate;
    public String stateOfStay;
    public String details;
    public String selectedKidsFeatures;
    public String selectedPetsFeatures;

    public BookedRoomDetails(String guestName, String roomType,String kidsNum,String adultNum, String checkInDate,String checkOutDate,String stateOfStay,String details,String selectedKidsFeatures,String selectedPetsFeatures) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.kidsNum = kidsNum;
        this.adultNum = adultNum;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.stateOfStay = stateOfStay;
        this.details = details;
        this.selectedKidsFeatures = selectedKidsFeatures;
        this.selectedPetsFeatures = selectedPetsFeatures;
    }
}
