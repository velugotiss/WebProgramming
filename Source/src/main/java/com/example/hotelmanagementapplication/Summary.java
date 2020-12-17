package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class Summary extends AppCompatActivity {
    TextView SuiteBooked;
    TextView TotalBill;
    Button PayBill;
    Button HomePage;
    int billedAmount;


    public String guestName;
    public String roomType;
    public String kidsNum;
    public String adultNum;
    public int checkInDate;
    public int checkOutDate;
    public String stateOfStay;
    public String details;
    public String selectedKidsFeatures;
    public String selectedPetsFeatures;

    String summary="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_summary);

            SuiteBooked = findViewById(R.id.SuiteBooked);
            TotalBill = findViewById(R.id.TotalBill);
            PayBill = findViewById(R.id.PayBill);
            HomePage = findViewById(R.id.HomePage);

            roomType = BookSuite.copyForBilling.roomType;
            checkInDate = Integer.parseInt(BookSuite.copyForBilling.checkInDate.substring(0, 2));
            checkOutDate = Integer.parseInt(BookSuite.copyForBilling.checkOutDate.substring(0, 2));

            guestName = BookSuite.copyForBilling.guestName;
            kidsNum = BookSuite.copyForBilling.kidsNum;
            adultNum = BookSuite.copyForBilling.adultNum;
            stateOfStay = BookSuite.copyForBilling.stateOfStay;
            details = BookSuite.copyForBilling.details;
            selectedKidsFeatures = BookSuite.copyForBilling.selectedKidsFeatures;
            selectedPetsFeatures = BookSuite.copyForBilling.selectedPetsFeatures;

            summary = "guestName = " + guestName + "\n" +
                    "roomType = " + roomType + "\n" +
                    "checkInDate =" + BookSuite.copyForBilling.checkInDate + "\n" +
                    "checkOutDate =" + BookSuite.copyForBilling.checkOutDate + "\n" +
                    "stateOfStay = " + stateOfStay + "\n" +
                    "Personalized features = " + details + "\n" +
                    selectedKidsFeatures + "\n" +
                    selectedPetsFeatures + "\n";

            SuiteBooked.setText(summary);
            billedAmount = 100;
            int day = checkOutDate - checkInDate;
            switch (day) {
                case 1:
                    billedAmount = billedAmount * 1;
                    break;
                case 2:
                    billedAmount = billedAmount * 2;
                    break;
                case 3:
                    billedAmount = billedAmount * 3;
                    break;
                case 4:
                    billedAmount = billedAmount * 4;
                    break;
            }


            switch (roomType) {
                case "King":
                    billedAmount += 50;
                    break;
                case "Queen":
                    billedAmount += 40;
                    break;
                case "Double bed":
                    billedAmount += 100;
                    break;
            }


            TotalBill.setText("Total billed amount  = "+billedAmount);
        }catch (Exception e)
        {
            System.out.println("exception  = " + e);
        }
    }

}