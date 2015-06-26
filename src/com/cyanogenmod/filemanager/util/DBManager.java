/*
 * Copyright (C) 2015 The SudaMod Project
 * by karon
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

public class DBManager {
	
    private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "app_dir_name.db";
    public static final String PACKAGE_NAME = "com.cyanogenmod.filemanager";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/"
            + PACKAGE_NAME;
    public static ArrayList<String> mDbList;
    
    
    private SQLiteDatabase database;
    private Context mContext;

    public DBManager(Context context) {
    	mContext = context;
    }

    public void openDatabase() {
        database = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    private SQLiteDatabase openDatabase(String dbfile) {

        try {
            if (!(new File(dbfile).exists())) {
                InputStream is = mContext.getResources().openRawResource(
                        R.raw.app_dir_name);
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();
            }
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,null);
            setDbToAllList(db);
            return db;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void setDbToAllList(SQLiteDatabase db) {
    	mDbList = new ArrayList<String>();
    	Cursor mCursor = db.rawQuery("SELECT * FROM dir_name", null);
    	if (mCursor != null) {
    		int count = mCursor.getCount();
    		if (count > 0) {
    			mCursor.moveToFirst();
    			for(int i=0; i<count; i++) {
    				String list = mCursor.getString(mCursor.getColumnIndex("path"))
    						+ " " + mCursor.getString(mCursor.getColumnIndex("name"));
    				mDbList.add(list);
    				mCursor.moveToNext();
    			}
    		}
    		if (mCursor != null) {
    			mCursor.close();
    			mCursor = null;
    		}
    	}
	}

	public void closeDatabase() {
        database.close();
    }
}
