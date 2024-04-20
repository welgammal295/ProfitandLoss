package com.welgammal.walid.profitandloss;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import com.welgammal.walid.profitandloss.R;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> contactList;
    private Context context;

    // Constructor to initialize the contact list and context
    public ContactAdapter(List<Contact> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder holder, int position) {
        // Bind data to the views
        Contact contact = contactList.get(position);
        holder.textViewName.setText(contact.getName());
        holder.textViewPhoneNumber.setText(contact.getPhoneNumber());
        holder.textViewEmail.setText(contact.getEmail());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    // ViewHolder class
    public static class ContactViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        TextView textViewPhoneNumber;
        TextView textViewEmail;
        Button buttonAddContact;

        public ContactViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPhoneNumber = itemView.findViewById(R.id.textViewPhoneNumber);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            buttonAddContact = itemView.findViewById(R.id.buttonAddContact);
        }
    }
}