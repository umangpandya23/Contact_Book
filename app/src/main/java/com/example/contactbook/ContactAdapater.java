package com.example.contactbook;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapater extends RecyclerView.Adapter<ContactAdapater.ListItemHolder> {
    private MainActivity mainActivity;
    private ArrayList<Contact> contactList;

    public ContactAdapater (MainActivity mainActivity, ArrayList<Contact> contactList) {
        this.mainActivity = mainActivity;
        this.contactList = contactList;

    }

    @NonNull
    @Override
    public ListItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ListItemHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ListItemHolder holder, int position) {
        Contact contact = contactList.get(position);
        holder.textViewName.setText(contact.getName());
        holder.textViewNumber.setText(contact.getPhone());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ListItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textViewName;
        private TextView textViewNumber;
        public ListItemHolder (View view) {
            super(view);

            textViewName = view.findViewById(R.id.textViewName);
            textViewNumber = view.findViewById(R.id.textViewNumber);
            view.setClickable(true);
            view.setOnClickListener(this);
        }

        public void onClick (View view) {

            mainActivity.showContact(getAdapterPosition());
        }

    }

}
