package com.ash.digivahan.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ash.digivahan.data.model.User;
// later we will add ChatMessage, VehicleInfo etc.

@Database(
        entities = {User.class}, // add ChatMessage.class, VehicleInfo.class when ready
        version = 1,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    // --- DAO references ---
    public abstract UserDao userDao();
    // public abstract ChatDao chatDao();
    // public abstract VehicleDao vehicleDao();

    // --- Singleton Instance ---
    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    "digivahan_db"
                            )
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

