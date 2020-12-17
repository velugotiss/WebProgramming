package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class BookSuite extends AppCompatActivity {

    TextView nameOfItem;
    ImageButton prev;
    ImageButton next;
    ImageView suiteView;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;
    int j=0;
    Button bookRoom;
    private String userId;
    public String guestName;
    public String roomType;
    public static  BookedRoomDetails copyForBilling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_suite);
        nameOfItem = findViewById(R.id.NameOfItem);
        prev = findViewById(R.id.prevButton);
        next = findViewById(R.id.nextButton);
        suiteView = findViewById(R.id.suiteView);
        bookRoom= findViewById(R.id.bookRoom);
        int i=0;
        int a = getImage(i);
        suiteView.setImageResource(a);
        guestName = LoginActivity.LoggedInUser1;

        mFirebaseInstance = FirebaseDatabase.getInstance();

        // get reference to 'users' node
        mFirebaseDatabase = mFirebaseInstance.getReference("SuiteBooking");

        // store app title to 'app_title' node
        mFirebaseInstance.getReference("app_title").setValue("Realtime Database");

        // app_title change listener
        mFirebaseInstance.getReference("app_title").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String appTitle = dataSnapshot.getValue(String.class);
                // update toolbar title
                getSupportActionBar().setTitle(appTitle);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        bookRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(userId)) {
                    userId = mFirebaseDatabase.push().getKey();
                }
                BookedRoomDetails user = new BookedRoomDetails(LoginActivity.LoggedInUser1Name,  roomType, dayPlan.kidNum, dayPlan.adultNum,  dayPlan.checkInDate, dayPlan.checkOutDate,dayPlan.stateOfStay,AccesabilityList.selectedFeatures,Kids.selectedKidsFeatures,Pets.selectedPetsFeatures);
                copyForBilling = user;
                mFirebaseDatabase.child(userId).setValue(user);

                Intent j = new Intent(BookSuite.this, seivicePref.class);
                j.putExtra("email",LoginActivity.LoggedInUser1Name);
                j.putExtra("userID",userId);
                startActivity(j);
            }
        });

    }


    /**
     * The topping's picture and name shown as the user naviagtes through the list of toppings
     * @param i
     * @return
     */
    public int getImage(int i)
    {
        switch (i)
        {
            case 0 :  { nameOfItem.setText("King"); return R.mipmap.suites1; }
            case 1 :  { nameOfItem.setText("Queen"); return R.mipmap.suites2; }
            case 2 :  { nameOfItem.setText("Double Bed"); return R.mipmap.suites3; }
        }
        return R.drawable.ic_launcher_background;
    }

    /**
     * Navigates to the previous topping image
     * @param view
     */
    public void PreviousSuite(View view) {
        if(j > 0 )
        {
            j--;
        }
        int a = getImage(j);
        suiteView.setImageResource(a);
    }

    /**
     * Navigates to the next topping image
     * @param view
     */
    public void nextSuite(View view) {
        if(j < 3 )
        {
            j++;
        }
        int a = getImage(j);
        suiteView.setImageResource(a);
    }

    /**
     * Adding the floor plan along with the quantity.
     * @param view
     */
    public void addFloorPlan(View view) {

        String currentItem = nameOfItem.getText().toString();
        roomType = currentItem;
        Toast.makeText(this,"Selecting the floor plan " + currentItem,Toast.LENGTH_SHORT).show();
    }


    public void kidFriendlyInstructions(View view) {

        Intent j = new Intent(BookSuite.this, Kids.class);
        startActivity(j);

    }

    public void petFriendlyInstructions(View view) {

        Intent j = new Intent(BookSuite.this, Pets.class);
        startActivity(j);
    }
}