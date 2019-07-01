package com.example.android.pets.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class PetContract {
    private PetContract() {
    }
    public final static String PATH_PETS="pets";
    public final static String CONTENT_AUTHORITY = "com.example.android.pets.data";
    public final static String CONTENT_LIST_TYPE= ContentResolver.CURSOR_DIR_BASE_TYPE+"/"+CONTENT_AUTHORITY+"/"+PATH_PETS;
    public final static String CONTENT_ITEM_TYPE= ContentResolver.CURSOR_ITEM_BASE_TYPE+"/"+CONTENT_AUTHORITY+"/"+PATH_PETS;
    public final static Uri BASE_CONTENT_URI   =Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final class PetEntry implements BaseColumns{
        public static Uri CONTENT_URI=Uri.withAppendedPath(BASE_CONTENT_URI,PATH_PETS);
        public static String TABLE_NAME="pets";
        public static String _ID=BaseColumns._ID;
        public static String COLUMN_PET_NAME="name";
        public static String COLUMN_PET_BREED="breed";
        public static String COLUMN_PET_GENDER="gender";
        public static String COLUMN_PET_WEIGHT="weight";

        /**
         * constants for pet gender
         */
        public static  final int GENDER_UNKNOWN=0;
        public static final int GENDER_MALE=1;
        public static final int GENDER_FEMALE=2;
        public static boolean isValidGender(int gender) {
            if (gender == GENDER_UNKNOWN || gender == GENDER_MALE || gender == GENDER_FEMALE) {
                return true;
            }
            return false;
        }
    }
}
