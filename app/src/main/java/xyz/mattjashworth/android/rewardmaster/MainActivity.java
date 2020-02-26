package xyz.mattjashworth.android.rewardmaster;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.UnityAds;

public class MainActivity extends AppCompatActivity {

    ArrayList<DataModel> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    FirebaseFirestore db;
    String[][] testStr = new String[10][3];
    Date timestamp;

    Boolean hasRewarded;
    String globalURI;


    private String unityGameID = "3336066";
    private Boolean testMode = false;
    private String placementID = "rewardedVideo";



    SharedPreferences pref;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.center_actionbar);

        //shared pref
        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();




        if (pref.getBoolean("dialogIntent", false) == false) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Reward Master!");
            alertDialog.setMessage("- If you receive the message \"You have already received the gift from this link\" " +
                    "when redeeming any bonuses, you have probably opened this link from elsewhere. We recommend only " +
                    "using one rewards app or website. \n \n- Bonus Gifts also expire after a certain amount of time, " +
                    "only the past few days links will be shown. \n \nThanks MattJAshworth");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
            editor.putBoolean("dialogIntent", true).commit();
        }


        //Firebase
        db = FirebaseFirestore.getInstance();
        //readClass();
        readFirestore();


        //Ads
        /*final IUnityAdsListener myAdsListener = new UnityAdsListener();
        // Initialize the SDK:
        UnityAds.initialize (this , unityGameID, myAdsListener, testMode);


        Button AdReward = (Button) findViewById(R.id.btn_OpenAd);
        AdReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UnityAds.isReady(placementID)) {
                    UnityAds.show(MainActivity.this, placementID);
                }
            }
        });*/


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_twitter:
                String twitter = "https://twitter.com/MattJAshworth";
                Intent tIntent = new Intent(Intent.ACTION_VIEW);
                tIntent.setData(Uri.parse(twitter));
                startActivity(tIntent);
                return true;
            case R.id.menu_rate:
                final String appPackageName = getPackageName();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                return true;
            case R.id.menu_instagram:
                String url = "https://instagram.com/MattJAshworth";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                return true;
            case R.id.menu_youtube:
                String yt = "https://youtube.com/MattJAshworth";
                Intent iYt = new Intent(Intent.ACTION_VIEW);
                iYt.setData(Uri.parse(yt));
                startActivity(iYt);
                return true;
            case R.id.menu_privacy:
                String privacy = "https://mattjashworth.xyz/privacy.html";
                Intent iPrivacy = new Intent(Intent.ACTION_VIEW);
                iPrivacy.setData(Uri.parse(privacy));
                startActivity(iPrivacy);
                return true;
            case R.id.menu_gdpr:
                //Do something
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void readFirestore() {


        db.collection("Links")
                .orderBy("Date", Query.Direction.DESCENDING)
                .limit(10)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<DocumentSnapshot> myListOfDocuments = task.getResult().getDocuments();
                            Log.d("TEST101", Integer.toString(myListOfDocuments.size()));
                            for (int i = 0; i < myListOfDocuments.size(); i++) {
                                timestamp = myListOfDocuments.get(i).getDate("Date");
                                String pattern = "dd-MM-yy";
                                SimpleDateFormat simpleDateFormat =
                                        new SimpleDateFormat(pattern);

                                String strDate = simpleDateFormat.format(timestamp);
                                testStr[i][0] = strDate;
                                testStr[i][1] = myListOfDocuments.get(i).get("Title").toString();
                                testStr[i][2] = myListOfDocuments.get(i).get("URI").toString();
                                Log.e("TAG", myListOfDocuments.get(i).get("URI").toString());
                            }

                            ListView listView = (ListView) findViewById(R.id.listView_bonus);

                            dataModels= new ArrayList<>();


                            for (int i = 1; i < testStr.length; i++) {
                                dataModels.add(new DataModel(testStr[i][1], testStr[i][0], testStr[i][2]));
                            }

                            adapter= new CustomAdapter(dataModels,getApplicationContext());

                            listView.setAdapter(adapter);



                        }
                    }
                });


    }



    public String getURI(int position) {
        return testStr[position][2];
    }




    private class UnityAdsListener implements IUnityAdsListener {

        public void onUnityAdsReady(String placementId) {
            // Implement functionality for an ad being ready to show.
        }

        @Override
        public void onUnityAdsStart(String placementId) {
            // Implement functionality for a user starting to watch an ad.
        }

        @Override
        public void onUnityAdsFinish(String placementId, UnityAds.FinishState finishState) {
            // Implement conditional logic for each ad completion status:
            if (finishState != UnityAds.FinishState.SKIPPED) {
                //Reward User
                String url = testStr[0][2];
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getBaseContext().startActivity(i);
            }
        }

        @Override
        public void onUnityAdsError(UnityAds.UnityAdsError unityAdsError, String s) {
            Log.e("ADS", "Unity Error");

        }
    }


}
