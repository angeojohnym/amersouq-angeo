package com.shopping.techxform.amersouq.fcm_classes;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.common.eventbus.EventBus;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.shopping.techxform.amersouq.R;
import com.shopping.techxform.amersouq.Utils.Constants;
import com.shopping.techxform.amersouq.activities.Home.HomePage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mobiz on 04-Mar-18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
//    private DevicePolicyManager mgr = null;
//    private ComponentName cn = null;

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        cn=new ComponentName(this, AdminReceiver.class);
//        mgr=(DevicePolicyManager)getSystemService(DEVICE_POLICY_SERVICE);
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

                String type_flag = remoteMessage.getData().get("flag");
            String msg = remoteMessage.getData().get("msg");
            String imageurl = remoteMessage.getData().get("imgurl");

            send_push(imageurl,msg);
//                if (type_flag.equals("0")) {

//                    String User_FirstName = "", User_MobileNumber = "";
//
//                    Bundle bundle = new Bundle();
//                    try {
//
//
//                        User_FirstName = remoteMessage.getData().get("User_FirstName");
//                        String User_Photo = remoteMessage.getData().get("User_Photo");
//                        User_MobileNumber = remoteMessage.getData().get("User_MobileNumber");
//                        String User_Id = remoteMessage.getData().get("User_Id");
//                        String HelpRequest_Id = remoteMessage.getData().get("HelpRequest_Id");
//                        String User_Address = remoteMessage.getData().get("User_Address");
//                        String HelpRequest_Latitude = remoteMessage.getData().get("HelpRequest_Latitude");
//                        String HelpRequest_Longitude = remoteMessage.getData().get("HelpRequest_Longitude");
//
//                        bundle.putString("User_FirstName", User_FirstName);
//                        bundle.putString("User_Photo", User_Photo);
//                        bundle.putString("User_MobileNumber", User_MobileNumber);
//                        bundle.putString("User_Id", User_Id);
//                        bundle.putString("HelpRequest_Id", HelpRequest_Id);
//                        bundle.putString("User_Address", User_Address);
//                        bundle.putString("HelpRequest_Latitude", HelpRequest_Latitude);
//                        bundle.putString("HelpRequest_Longitude", HelpRequest_Longitude);
//                    } catch (Exception e) {
//                        Log.v("angeo", "error in fcm data");
//                    }




//            }

        }

    }



    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.e("NEW_TOKEN", s);
    }


    public void send_push(String imageurl,String text) {
        Intent intent = new Intent(this, HomePage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                intent.putExtra("quote",description);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

//        Bitmap bmp=getBitmapfromUrl(imageurl);

        String channelId = "";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Bitmap image=getBitmapfromUrl(imageurl);

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.logo)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(image))
                        .setContentTitle("Amer Souq")
                        .setContentText(text)
                        .setPriority(NotificationManager.IMPORTANCE_HIGH)
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_LIGHTS|Notification.DEFAULT_VIBRATE)
                        .setContentIntent(pendingIntent);

//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
//                .setSmallIcon(R.drawable.ic_launcher)
//                .setContentText("Amersouq")
//                .setContentText(text)
//                .setLargeIcon(bmp)
//                .setPriority(NotificationManager.IMPORTANCE_HIGH)
//                .setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS | Notification.DEFAULT_VIBRATE)
//                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Amersouq channel",
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());


    }



}
