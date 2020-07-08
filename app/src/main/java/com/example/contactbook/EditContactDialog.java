package com.example.contactbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class EditContactDialog extends DialogFragment {

    //Variables to store reference to edit views
    private EditText nameEditText;
    private EditText phoneEditText;
    private EditText emailEditText;
    private EditText streetEditText;
    private EditText cityEditText;
    private EditText stateEditText;
    private EditText zipEditText;
    private RadioButton businessRadioButton;
    private RadioButton familiyRadioButton;
    private RadioButton friendRadioButton;

    //Variables to store reference to buttons
    private Button buttonSave;
    private Button buttonMainMenu;
    private Button buttonClear;
    private Contact contact;

    public EditContactDialog() {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflator = getActivity().getLayoutInflater();

        View dialogView = inflator.inflate(R.layout.dialog_edit_contact, null);

        //Find reference to views on the form
        nameEditText = dialogView.findViewById(R.id.textInputName);
        phoneEditText = dialogView.findViewById(R.id.textInputPhone);
        emailEditText = dialogView.findViewById(R.id.textInputEmail);
        streetEditText = dialogView.findViewById(R.id.textInputStreet);
        cityEditText = dialogView.findViewById(R.id.textInputCity);
        stateEditText = dialogView.findViewById(R.id.textInputState);
        zipEditText = dialogView.findViewById(R.id.textInputZip);
        businessRadioButton = dialogView.findViewById(R.id.radioButtonBusiness);
        familiyRadioButton = dialogView.findViewById(R.id.radioButtonFamily);
        friendRadioButton = dialogView.findViewById(R.id.radioButtonFriend);

        nameEditText.setText(contact.getName());
        phoneEditText.setText(contact.getPhone());
        emailEditText.setText(contact.getEmail());
        streetEditText.setText(contact.getStreetAddress());
        cityEditText.setText(contact.getCity());
        stateEditText.setText(contact.getState());
        zipEditText.setText(contact.getZip());
        if(contact.getContactType()=="Business"){
            businessRadioButton.setChecked(true);
        }else if(contact.getContactType()=="Family") {
                familiyRadioButton.setChecked(true);
        }else if(contact.getContactType()=="Friend"){
            friendRadioButton.setChecked(true);
        }

        buttonSave = dialogView.findViewById(R.id.buttonSave);
        buttonClear = dialogView.findViewById(R.id.buttonClear);
        buttonMainMenu = dialogView.findViewById(R.id.buttonMainMenu);

        buttonMainMenu.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    dismiss();
                }
        }
        );

        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String street = streetEditText.getText().toString();
                String city = cityEditText.getText().toString();
                String state = stateEditText.getText().toString();
                String zip = zipEditText.getText().toString();

                String contactType = "";

                if (businessRadioButton.isChecked()) {
                    contactType = "Business";
                }
                else if (familiyRadioButton.isChecked()) {
                    contactType = "Family";
                }
                else {
                    contactType ="Friend";
                }


                Contact contact = new Contact (0, name, phone, email, street, state,
                        city, zip, contactType);

                MainActivity callingActivity = (MainActivity) getActivity();
                callingActivity.replaceContact(contact);

                dismiss();
            }
        }
        );

        buttonClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                nameEditText.setText("");
                phoneEditText.setText("");
                emailEditText.setText("");
                streetEditText.setText("");
                cityEditText.setText("");
                stateEditText.setText("");
                zipEditText.setText("");

                businessRadioButton.setChecked(true);
                nameEditText.requestFocus();

            }
        });

        builder.setView(dialogView);  //.setMessage(" ");

        return builder.create();
    }

    public void sendSelectedContact (Contact contact) {
        this.contact = contact;
    }
}
