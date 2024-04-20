package com.welgammal.walid.profitandloss;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.welgammal.walid.profitandloss.R;

public class ContactActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private List<Contact> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recycler_view_contacts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize contactList (replace this with your actual data retrieval)
        contactList = new ArrayList<>();
        contactList.add(new Contact("Mister Whiskers Stark", "(456) 789-1234", "mister.whiskers42@gmail.com"));
        contactList.add(new Contact("Lady Fluffy Wayne", "(234) 567-8901", "lady.fluffywayne17@outlook.com"));
        contactList.add(new Contact("Sir Paws Parker", "(789) 012-3456", "sir.paws8@aol.com"));
        contactList.add(new Contact("Duchess Fuzzy Kent", "(890) 123-4567", "duchess.fuzzykent55@gmail.com"));
        contactList.add(new Contact("Countess Mittens Prince", "(567) 890-1234", "countess.mittensprince33@outlook.com"));
        contactList.add(new Contact("King Snuggles Rogers", "(123) 456-7890", "king.snugglesrogers76@aol.com"));
        contactList.add(new Contact("Queen Whiskers Romanoff", "(678) 901-2345", "queen.whiskersromanoff22@gmail.com"));
        contactList.add(new Contact("Mister Furry Stark", "(345) 678-9012", "mister.furrystark99@outlook.com"));
        contactList.add(new Contact("Princess Purr Wilson", "(901) 234-5678", "princess.purrwilson41@aol.com"));
        contactList.add(new Contact("Lord Fluffytail Odinson", "(012) 345-6789", "lord.fluffytailodinson23@gmail.com"));
        contactList.add(new Contact("Duchess Paws Wayne", "(789) 012-3456", "duchess.pawswayne67@outlook.com"));
        contactList.add(new Contact("Baron Snickers Banner", "(567) 890-1234", "baron.snickersbanner12@aol.com"));
        contactList.add(new Contact("Captain Whiskerpaws Kent", "(234) 567-8901", "captain.whiskerpawskent88@gmail.com"));
        contactList.add(new Contact("Lady Snugglepuff Prince", "(890) 123-4567", "lady.snugglepuffprince29@outlook.com"));
        contactList.add(new Contact("Mister Meowington Wilson", "(456) 789-1234", "mister.meowingtonwilson70@aol.com"));
        contactList.add(new Contact("Princess Fuzzywinks Romanoff", "(678) 901-2345", "princess.fuzzywinksromanoff44@gmail.com"));
        contactList.add(new Contact("Sir Furryface Parker", "(345) 678-9012", "sir.furryfaceparker96@outlook.com"));
        contactList.add(new Contact("Lady Pawsworth Banner", "(901) 234-5678", "lady.pawsworthbanner50@aol.com"));
        contactList.add(new Contact("Count Whiskersworth Wayne", "(012) 345-6789", "count.whiskersworthwayne61@gmail.com"));
        contactList.add(new Contact("Duchess Purrington Rogers", "(789) 012-3456", "duchess.purringtonrogers10@outlook.com"));


        // Initialize ContactAdapter with the contactList and set it to RecyclerView
        adapter = new ContactAdapter(contactList, this);
        recyclerView.setAdapter(adapter);

    }
    private void addContact(String name, String phoneNumber, String email) {
        // Create a new Contact object with the provided details
        Contact newContact = new Contact(name, phoneNumber, email);

        // Add the new contact to the list
        contactList.add(newContact);

        // Notify the adapter that the data set has changed
        adapter.notifyDataSetChanged();
    }
}