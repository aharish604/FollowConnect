package com.appcare.followconnect.Common;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.appcare.followconnect.InToTo.Bean.intotoprivate;
import com.appcare.followconnect.InToTo.Bean.intotopublic;
import com.appcare.followconnect.R;
import com.appcare.followconnect.SearchFriends.Bean.SearchHistoryResponseBean1;
import com.appcare.followconnect.Settings.PasswordChangeActivity;
import com.appcare.followconnect.SignUp.CountrySpinner.CountryBean;
import com.google.android.exoplayer2.extractor.ogg.OggExtractor;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.pixplicity.sharp.Sharp;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.Context.ACTIVITY_SERVICE;
import static com.appcare.followconnect.UploadPost.UploadPostActivity.STORAGE_PERMISSION;

public class Constants {


    public static final String KEY_MP3 = "mp3";
    public static final String KEY_OGG = "ogg";
    public static final String KEY_MP4 = "mp4";
    public static final String ToolbarName = "toolbarname";
    public static final String KEY_MP4_CAPS = "MP4";
    public static final String KEY_HLS = "m3u8";
    public static final int MyviewAdapterPosition =0;
    public static final String KEY_USER_AGENT = "exoplayer-codelab";

    public static final int RC_SIGN_IN = 101;
    //public static String BASE_URL = "http://159.65.145.170/vamshirubbers/";
    public static String BASE_URL = "http://13.126.39.225/socialmedia/index.php/";

    public static String Email = "email";
    public static String User_ID = "userid";
    public static String password = "password";
    public static String UserName = "username";
    public static String FullName = "fullname";
    public static String ProfilrURL = "profilrurl";
    public static String loginStatus = "loginstatus";
    public static String countryAPI = "https://restcountries.eu/rest/v2/all";

    public static SearchHistoryResponseBean1 searchFriendsListData;
    public static String Toid="toid";

    //  http://13.126.39.225/socialmedia/index.php/register/register

    public static boolean isNetworkAvailable(Context context) {
        @SuppressLint("WrongConstant") ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static ArrayList<String> getGender() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Gender");
        arrayList.add("Male");
        arrayList.add("Female");
        arrayList.add("Other");
        return arrayList;
    }

    public static ArrayList<String> getPrivacy() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Public");
        arrayList.add("Private");
        return arrayList;
    }

    public static ProgressDialog showProgressDialog(Context mContext, String message) {
        ProgressDialog pdLoadDialog = null;
        try {
            pdLoadDialog = ProgressDialog.show(mContext, null, null, true);
            pdLoadDialog.setContentView(R.layout.elemento_progress_splash);
            pdLoadDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            pdLoadDialog.show();
            pdLoadDialog.setCancelable(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdLoadDialog;
    }

    public static void displayLongToast(Context mContext, String message) {

        if(!message.equalsIgnoreCase("friend list")) {
            try {
                Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("data toast:-"+mContext.getClass().toString());

        }
    }


    private static OkHttpClient httpClient;

    public static void fetchSvg(Context context, String url, final ImageView target) {

        if (!url.equalsIgnoreCase("")) {
            if (httpClient == null) {
                // Use cache for performance and basic offline capability
                httpClient = new OkHttpClient.Builder()
                        .cache(new Cache(context.getCacheDir(), 5 * 1024 * 1014))
                        .build();
            }

            Request request = new Request.Builder().url(url).build();
            httpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    // target.setImageDrawable(R.drawable.follow_connect_logo);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    InputStream stream = response.body().byteStream();
                    try {
                        Sharp.loadInputStream(stream).into(target);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    stream.close();
                }

            });
        }
    }

    public static ArrayList<String> getCountryNames(ArrayList<CountryBean> counrylist) {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.clear();
        for (int i = 0; i <= counrylist.size() - 1; i++) {
            arrayList.add(counrylist.get(i).getCountry());
        }
        return arrayList;
    }

    public static ArrayList<String> permissionList() {
        ArrayList<String> arrayList = new ArrayList<>();

        arrayList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        arrayList.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        arrayList.add(Manifest.permission.CAMERA);
        arrayList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        arrayList.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        return arrayList;
    }


    //For Image Size 640*480, use MAX_SIZE =  307200 as 640*480 307200
    //private static long MAX_SIZE = 360000;
    //private static long THUMB_SIZE = 6553;

    public static Bitmap reduceBitmapSize(Bitmap bitmap, int MAX_SIZE) {
        double ratioSquare;
        int bitmapHeight, bitmapWidth;
        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth();
        ratioSquare = (bitmapHeight * bitmapWidth) / MAX_SIZE;
        if (ratioSquare <= 1)
            return bitmap;
        double ratio = Math.sqrt(ratioSquare);
        // Log.d("mylog", "Ratio: " + ratio);
        int requiredHeight = (int) Math.round(bitmapHeight / ratio);
        int requiredWidth = (int) Math.round(bitmapWidth / ratio);
        return Bitmap.createScaledBitmap(bitmap, requiredWidth, requiredHeight, true);
    }

    public static Bitmap generateThumb(Bitmap bitmap, int THUMB_SIZE) {
        double ratioSquare;
        int bitmapHeight, bitmapWidth;
        bitmapHeight = bitmap.getHeight();
        bitmapWidth = bitmap.getWidth();
        ratioSquare = (bitmapHeight * bitmapWidth) / THUMB_SIZE;
        if (ratioSquare <= 1)
            return bitmap;
        double ratio = Math.sqrt(ratioSquare);
        // Log.d("mylog", "Ratio: " + ratio);
        int requiredHeight = (int) Math.round(bitmapHeight / ratio);
        int requiredWidth = (int) Math.round(bitmapWidth / ratio);
        return Bitmap.createScaledBitmap(bitmap, requiredWidth, requiredHeight, true);
    }

    public static String getEmail(Context context) {

        String email="";
        if(AppPreference.getInstance()!=null)
        {
            email=  AppPreference.getInstance().getString(Constants.Email);
        }
        else {
            AppPreference.getInstance(context);
            getEmail(context);
        }

        return email;
    }

    public static ArrayList<intotopublic> getImageArrayintoto(ArrayList<intotopublic> aPublic) {

        ArrayList<intotopublic> list=new ArrayList<>();
        for(int i=0;i<aPublic.size();i++)
        {
            if(aPublic.get(i).getImgf()!=null) {
                if (!aPublic.get(i).getImgf().isEmpty()) {
                    list.add(aPublic.get(i));

                }
            }

        }

        return list;
    }

    public static ArrayList<intotopublic> getVideoArrayintoto(ArrayList<intotopublic> aPublic) {


        ArrayList<intotopublic> list=new ArrayList<>();
        for(int i=0;i<aPublic.size();i++)
        {
            if(aPublic.get(i).getVfThumb()!=null) {

                if (!aPublic.get(i).getVfThumb().isEmpty()) {
                    list.add(aPublic.get(i));

                }
            }

        }

        return list;

    }

    public static Boolean isPrivateImagesSpoolvid(ArrayList<intotoprivate> aPrivate) {


        if(aPrivate!=null) {
            for (int i = 0; i < aPrivate.size(); i++) {
                if (aPrivate.get(i).getImgf() != null) {

                    if (!aPrivate.get(i).getImgf().isEmpty()) {

                        return true;
                    }
                }

            }
        }

        return false;
    }

    public static Boolean isPrivateVideosSpoolvid(ArrayList<intotoprivate> aPrivate) {
        if(aPrivate!=null) {

            for (int i = 0; i < aPrivate.size(); i++) {
                if (aPrivate.get(i).getVfThumb() != null) {

                    if (!aPrivate.get(i).getVfThumb().isEmpty()) {

                        return true;
                    }
                }

            }
        }


        return false;

    }

    /*public static void getCountrypostion(ArrayList<CountryBean> countrylist, String countryname) {

        for(int cnt = 0; cnt < countrylist.size(); cnt++)
        {
            if(countrylist.get(cnt).getName().equals(countryname)) return cnt;

        }
    }*/


   /* public void findItem(String name)
    {

        for(int cnt = 0; cnt <= max/2; cnt++)
        {
            if(contactList.get(cnt).getName().equals(name)) return cnt;
            if(contactList.get(descCnt).getName().equals(name)) return descCnt;
        }

    }
*/




    public boolean isStoragePermissionGranted(Activity context) {
        int ACCESS_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if ((ACCESS_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, STORAGE_PERMISSION);
            return false;
        }
        return true;
    }


    public static void setSpinText(Spinner spin, String text) {
        for (int i = 0; i < spin.getAdapter().getCount(); i++) {
            if (spin.getAdapter().getItem(i).toString().contains(text)) {
                spin.setSelection(i);
            }
        }

    }
      public static String getUid(Context context) {
          String userid="";
          if(AppPreference.getInstance()!=null)
        {
             userid=  AppPreference.getInstance().getString(Constants.User_ID);
        }
        else {
            AppPreference.getInstance(context);
            getUid(context);
        }


      return userid;
    }


    public static MediaSource buildMediaSource(String source, HashMap<String, String> extraHeaders, Context context) {


        if (source == null) {
            displayLongToast(context,"Input Is Invalid.");

            return null;
        }
        boolean validUrl = URLUtil.isValidUrl(source);
        if(!validUrl)
        {
            displayLongToast(context,"Not a Valid URL.");

        }

        Uri uri = Uri.parse(source);
        if (uri == null || uri.getLastPathSegment() == null) {
            displayLongToast(context,"Uri Converter Failed, Input Is Invalid.");
            return null;
        }
        if (validUrl && (uri.getLastPathSegment().contains(Constants.KEY_MP4) || uri.getLastPathSegment().contains(Constants.KEY_MP4_CAPS))) {

            DefaultHttpDataSourceFactory sourceFactory = new DefaultHttpDataSourceFactory(Constants.KEY_USER_AGENT);
            if (extraHeaders != null) {
                for (Map.Entry<String, String> entry : extraHeaders.entrySet())
                    sourceFactory.getDefaultRequestProperties().set(entry.getKey(), entry.getValue());
            }

            return new ProgressiveMediaSource.Factory(sourceFactory)
                    .createMediaSource(uri);

        } else if (validUrl && (uri.getLastPathSegment().contains(Constants.KEY_MP4)) || uri.getLastPathSegment().contains(Constants.KEY_MP4_CAPS)) {
            return new ProgressiveMediaSource.Factory(new DefaultDataSourceFactory(context, Constants.KEY_USER_AGENT))
                    .createMediaSource(uri);

        } else if (validUrl && (uri.getLastPathSegment().contains(Constants.KEY_HLS))) {

            DefaultHttpDataSourceFactory sourceFactory = new DefaultHttpDataSourceFactory(Constants.KEY_USER_AGENT);
            if (extraHeaders != null) {
                for (Map.Entry<String, String> entry : extraHeaders.entrySet())
                    sourceFactory.getDefaultRequestProperties().set(entry.getKey(), entry.getValue());
            }

            return new HlsMediaSource.Factory(sourceFactory).createMediaSource(uri);



        } else if (uri.getLastPathSegment().contains(Constants.KEY_MP3)){
            DefaultHttpDataSourceFactory sourceFactory = new DefaultHttpDataSourceFactory(Constants.KEY_USER_AGENT);
            if (extraHeaders != null) {
                for (Map.Entry<String, String> entry : extraHeaders.entrySet())
                    sourceFactory.getDefaultRequestProperties().set(entry.getKey(), entry.getValue());
            }
            return new ProgressiveMediaSource.Factory(sourceFactory)
                    .createMediaSource(uri);

        } else if (uri.getLastPathSegment().contains(Constants.KEY_OGG)){

            DefaultHttpDataSourceFactory sourceFactory = new DefaultHttpDataSourceFactory(Constants.KEY_USER_AGENT);
            if (extraHeaders != null) {
                for (Map.Entry<String, String> entry : extraHeaders.entrySet())
                    sourceFactory.getDefaultRequestProperties().set(entry.getKey(), entry.getValue());
            }

            return new ProgressiveMediaSource.Factory(sourceFactory, OggExtractor.FACTORY)
                    .createMediaSource(uri);

        } else {
            DefaultDashChunkSource.Factory dashChunkSourceFactory = new DefaultDashChunkSource.Factory(new DefaultHttpDataSourceFactory("ua", new DefaultBandwidthMeter()));
            DefaultHttpDataSourceFactory manifestDataSourceFactory = new DefaultHttpDataSourceFactory(Constants.KEY_USER_AGENT);
            return new DashMediaSource.Factory(dashChunkSourceFactory, manifestDataSourceFactory)
                    .createMediaSource(uri);
        }
    }

    public static long checkVideoDuration(Context context, Uri uri){
        Cursor cursor = MediaStore.Video.query(context.getContentResolver(), uri, new
                String[]{MediaStore.Video.VideoColumns.DURATION});
        long duration = 0;
        if (cursor != null && cursor.moveToFirst()) {
            duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Video
                    .VideoColumns.DURATION));
            cursor.close();
        }

        return duration;
    }

    public static void clearAppData(Context mcontext) {
        try {
            // clearing app data
            if (Build.VERSION_CODES.KITKAT <= Build.VERSION.SDK_INT) {
                ((ActivityManager)mcontext.getSystemService(ACTIVITY_SERVICE)).clearApplicationUserData(); // note: it has a return value!
            } else {
                String packageName = mcontext.getApplicationContext().getPackageName();
                Runtime runtime = Runtime.getRuntime();
                runtime.exec("pm clear "+packageName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
