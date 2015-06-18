/*
 * Copyright (C) 2015 The SudaMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cyanogenmod.filemanager.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import java.io.IOException;

import java.util.HashMap;

public final class AppDirNameHelper {
    private static Context ct;
    private static DBHelper dbHelper;
    private static SQLiteDatabase db;
    private static String appName;

    public AppDirNameHelper(Context ct) {
        this.ct = ct;
        dbHelper = new DBHelper(ct);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getAppName(String dirName) {
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("dir_name", null, "path=?",
                    new String[]{dirName}, null, null, null);

        appName = cursor.moveToFirst() ? "|" + cursor.getString(cursor.getColumnIndex("name")) : "";
        db.close();
        return appName;
    }  


}
