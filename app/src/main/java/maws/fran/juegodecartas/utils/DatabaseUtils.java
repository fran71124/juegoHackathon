package maws.fran.juegodecartas.utils;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by cdamian on 30/9/17.
 */

public class DatabaseUtils {

    public static void loadCardsStaticData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.getReference("cartas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("ASdfasdf");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
