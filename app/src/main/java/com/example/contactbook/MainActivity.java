package com.example.contactbook;
import android.database.Cursor;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Contact> contactList;
    private RecyclerView recyclerView;
    private ContactAdapater contactAdapater;
    private int selectedContact;
    private Contact selectedContactValue;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddContactDialog addContactDialog = new AddContactDialog();
                addContactDialog.show(getSupportFragmentManager(), " ");
            }
        });

        contactList = new ArrayList<Contact>();
        dataManager = new DataManager(this);

        recyclerView = findViewById(R.id.recyclerView);
        contactAdapater = new ContactAdapater(this, contactList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recyclerView.setAdapter(contactAdapater);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addNewContact (Contact contact) {
//        contactList.add(contact);
//        dataManager.insert(contact.getName(), contact.getPhone(), contact.getEmail(), contact.getStreetAddress(), contact.getCity(), contact.getState(), contact.getZip(), contact.getContactType());
//        Log.i("info", contact.getName() + contact.getPhone() + contact.getEmail() + contact.getStreetAddress() + contact.getCity() + contact.getState());
//        Log.i ("info", Integer.toString(contactList.size()));
//        contactAdapater.notifyDataSetChanged();

        String name = contact.getName();
        String phone = contact.getPhone();
        String email = contact.getEmail();
        String address = contact.getStreetAddress();
        String city = contact.getCity();
        String state = contact.getState();
        String zip = contact.getZip();
        String contactType = contact.getContactType();
        dataManager.insert (name, phone, email, address, city, state, zip, contactType);
        loadData();

    }

    public void showContact (int contactToShow) {
        this.selectedContact = contactToShow;
        this.selectedContactValue = contactList.get(contactToShow);
        ViewContactDialog viewContactDialog = new ViewContactDialog();
        viewContactDialog.sendSelectedContact(contactList.get(contactToShow));
        viewContactDialog.show(getSupportFragmentManager(), "");
    }

    public void deleteContact(){
//        contactList.remove(this.selectedContact);
//        Log.i ("info", Integer.toString(contactList.size()));
//        contactAdapater.notifyDataSetChanged();

        dataManager.delete(this.selectedContactValue.getName(), this.selectedContactValue.getPhone());
        contactList.remove(this.selectedContact);
        contactAdapater.notifyDataSetChanged();
    }

    public void updateContact(Contact contactToUpdate){
        EditContactDialog editContactDialog = new EditContactDialog();
        editContactDialog.sendSelectedContact(contactToUpdate);
        editContactDialog.show(getSupportFragmentManager(),"");
    }

    public void replaceContact(Contact contact){
        dataManager.update(this.selectedContactValue.getName(), this.selectedContactValue.getPhone(), contact.getName(), contact.getPhone(), contact.getEmail(), contact.getStreetAddress(), contact.getCity(), contact.getState(), contact.getZip(), contact.getContactType());
        contactList.set(this.selectedContact, contact);
        contactAdapater.notifyDataSetChanged();
    }

    public void loadData () {
        Cursor cursor = dataManager.selectAll();
        int contactCount = cursor.getCount();

        if (contactCount > 0) {
            contactList.clear();

            while (cursor.moveToNext()) {
                String name = cursor.getString(1);
                String phone = cursor.getString(2);
                String email = cursor.getString(3);
                String address = cursor.getString(4);
                String city = cursor.getString(5);
                String state = cursor.getString(6);
                String zip = cursor.getString(7);
                String contactType = cursor.getString(8);

                Contact contact = new Contact(0, name, phone, email, address, city, state, zip, contactType);

                contactList.add(contact);
            }
        }
        contactAdapater.notifyDataSetChanged();


    }

}