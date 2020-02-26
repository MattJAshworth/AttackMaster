package xyz.mattjashworth.android.rewardmaster;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by mattjashworth on 14/05/2019.
 * For AttackMaster.
 */

public class CustomAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener {

    private ArrayList<DataModel> dataSet;
    Context mContext;
    Boolean reportState = false;

    String globalURI;

    private String placementID = "rewardedVideo";

    Boolean hasRewarded;

    // View lookup cache
    private static class ViewHolder {
        TextView txtReward;
        TextView txtDate;
        TextView txtVersion;
        ImageView attackMaster;
        ImageView status;
        Button open;
        Button shareLink;
        Button report;
        String URIs;
        ConstraintLayout constraintLayout;


    }

    public CustomAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.row_layout, data);
        this.dataSet = data;
        this.mContext=context;


    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModel dataModel=(DataModel)object;

        switch (v.getId())
        {

        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        final DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;


        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.row_layout, parent, false);
            viewHolder.txtReward = (TextView) convertView.findViewById(R.id.item_Reward);
            viewHolder.txtDate = (TextView) convertView.findViewById(R.id.item_Date);
            viewHolder.attackMaster = (ImageView) convertView.findViewById(R.id.img_DailyBonus);
            viewHolder.status = (ImageView) convertView.findViewById(R.id.img_Status);
            viewHolder.open = (Button) convertView.findViewById(R.id.btn_open);
            viewHolder.shareLink = (Button) convertView.findViewById(R.id.btn_share);
            viewHolder.constraintLayout = (ConstraintLayout) convertView.findViewById(R.id.const_bg);


            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }





        //Ads


       /* mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-7439808203904930/8590925098");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
                Log.e("ADS", "Interstitial closed: " + dataModel.URI);
                String url = dataModel.URI;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(globalURI));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(getContext());
        loadRewardedVideoAd();
        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {

            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                loadRewardedVideoAd();
                if (hasRewarded) {

                    String url = dataModel.URI;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(globalURI));
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(i);


                    hasRewarded = false;
                }
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                hasRewarded = true;
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        });*/



        viewHolder.txtReward.setText(dataModel.getReward());
        viewHolder.txtDate.setText(dataModel.getDate());
        viewHolder.attackMaster.setImageResource(R.drawable.attackmastershield);
        if (position == 0 || position == 1 || position == 2) {
            viewHolder.status.setImageResource(R.drawable.green_dot);
        } if (position == 3 || position == 4 || position == 5 || position == 6) {
            viewHolder.status.setImageResource(R.drawable.orange_dot);
        } if (position == 7 || position == 8 || position == 9) {
            viewHolder.status.setImageResource(R.drawable.red_dot);
        } if (position > 9) {
            viewHolder.status.setImageResource(R.drawable.red_dot);
        }

        viewHolder.open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get index
                globalURI = dataModel.URI;


                String url = dataModel.URI;
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(globalURI));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });
        viewHolder.shareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get index

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, "Coin Master Bonus Gift of " + dataModel.reward + " - "
                            + dataModel.URI + "\n\n Via Attack Master - get on Google Play:");
                    sendIntent.setType("text/plain");
                    sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getContext().startActivity(sendIntent);
                }

        });
        // Return the completed view to render on screen
        return convertView;
    }



}