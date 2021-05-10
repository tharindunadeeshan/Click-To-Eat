package com.project.clicktoeat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConnect {

    FirebaseDatabase database;
    DatabaseReference myRef, mDatabase;

    public FirebaseConnect() {
        this.mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public FirebaseConnect(String message) {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference(message);
    }

    public DatabaseReference getMyRef() {
        return myRef;
    }

    public void setMyRef(DatabaseReference myRef) {
        this.myRef = myRef;
    }
}
