package com.example.contactbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ViewContactDialog extends DialogFragment {
    //Declare variables to hold reference to EditText used for collecting data
    private TextView nameTextView;
    private TextView phoneTextView;
    private TextView emailTextView;
    private TextView streetTextView;
    private TextView cityTextView;
    private TextView stateTextView;
    private TextView zipTextView;
    private TextView typeTextView;
    private Button buttonMainMenu;
    private Button buttonDelete;
    private Button buttonEdit;
    private int id;
    private Contact contact;

    public ViewContactDialog() {
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_view_contact, null);

        //Find reference to views on the form
        nameTextView = dialogView.findViewById(R.id.textViewName);
        phoneTextView = dialogView.findViewById(R.id.textViewPhone);
        emailTextView = dialogView.findViewById(R.id.textViewEmail);
        streetTextView = dialogView.findViewById(R.id.textViewStreet);
        cityTextView = dialogView.findViewById(R.id.textViewCity);
        stateTextView = dialogView.findViewById(R.id.textViewState);
        zipTextView = dialogView.findViewById(R.id.textViewZip);
        typeTextView = dialogView.findViewById(R.id.textViewType);

        buttonMainMenu = dialogView.findViewById(R.id.buttonMainMenu);
        buttonDelete = dialogView.findViewById(R.id.buttonDelete);
        buttonEdit = dialogView.findViewById(R.id.buttonEdit);

        nameTextView.setText(contact.getName());
        phoneTextView.setText(contact.getPhone());
        emailTextView.setText(contact.getEmail());
        streetTextView.setText(contact.getStreetAddress());
        cityTextView.setText(contact.getCity());
        stateTextView.setText(contact.getState());
        zipTextView.setText(contact.getZip());
        typeTextView.setText(contact.getContactType());

        builder.setView(dialogView).setMessage(" ");

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                MainActivity callingActivity = (MainActivity) getActivity();
                callingActivity.updateContact(contact);
            }
        });

        buttonMainMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick (View view) {
                dismiss();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity callingActivity = (MainActivity) getActivity();
                callingActivity.deleteContact();
                dismiss();
            }
        });

        return builder.create();

    }


    public void sendSelectedContact (Contact contact) {
        this.contact = contact;

    }
}
