/*
 * Copyright (C) 2015 The CyanogenMod Project
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

import android.text.TextUtils;

import java.util.HashMap;

public final class AppDirNameHelper {

    static HashMap<String, String> appMap = new HashMap<String, String>();

    public static String getAppName(String dirName) {
        appMap.put("360", "360安全卫士");
        appMap.put("360OS", "360OS社区");
        appMap.put("360contacts_v2", "360安全通讯录");
        appMap.put("91Contacts", "91通讯库");
        appMap.put("BaiduMap", "百度地图");
        appMap.put("cn.coupon.kfc", "肯德基");
        appMap.put("com.mylafe.ttmh", "");
        appMap.put("com.taobao.movie.android", "淘宝电影");
        return (TextUtils.isEmpty(appMap.get(dirName)) ? "" : "|" + appMap.get(dirName));
    }  


}
