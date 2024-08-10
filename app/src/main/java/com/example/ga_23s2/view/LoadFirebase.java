package com.example.ga_23s2.view;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.MemoryCacheSettings;
import com.google.firebase.firestore.PersistentCacheSettings;

public class LoadFirebase {
  void load() {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseFirestoreSettings settings =
        new FirebaseFirestoreSettings.Builder(db.getFirestoreSettings())
            // Use memory-only cache
            .setLocalCacheSettings(MemoryCacheSettings.newBuilder().build())
            // Use persistent disk cache (default)
            .setLocalCacheSettings(
                PersistentCacheSettings.newBuilder().setSizeBytes(100_000).build())
            .build();

    db.setFirestoreSettings(settings);
  }
}
