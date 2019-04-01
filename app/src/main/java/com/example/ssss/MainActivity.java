package com.example.ssss;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.example.ssss.Fragment.EP_Fragment;
import com.example.ssss.Fragment.FriendListFragment;
import com.example.ssss.Fragment.HomeFragment;
import com.example.ssss.Fragment.MeetingFragment;
import com.example.ssss.Fragment.TimeTableFragment;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.helper.log.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "ssss.MainActivity";

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private HomeFragment homeFragment = new HomeFragment();
    private FriendListFragment friendListFragment = new FriendListFragment();
    private TimeTableFragment timeTableFragment = new TimeTableFragment();
    private MeetingFragment meetingFragment = new MeetingFragment();

    private EP_Fragment epFragment = new EP_Fragment();

    private Long kakaoUserID;
    private String kakaoUserProfileImg;
    private String kakaoUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestMe();
//
//        Log.d(TAG, "user id :" + kakaoUserID);
//        Log.d(TAG, "user name :" + kakaoUserName);

//        Log.d(TAG, "user id :" + kakaoUserID);
//        Log.d(TAG, "user name :" + kakaoUserName);
//        Log.d(TAG, "user profile img : " + kakaoUserProfileImg);


        //for bottomNavigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, timeTableFragment).commitAllowingStateLoss();
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                //home , timeTable, friends, meeting
                switch (item.getItemId()) {
//                    case R.id.navigation_home:
//                        transaction.replace(R.id.frame_layout,homeFragment).commitAllowingStateLoss();
//                        break;
                    case R.id.navigation_timetable:
                        transaction.replace(R.id.frame_layout, epFragment).commitAllowingStateLoss();
                        break;
                    case R.id.navigation_meeting:
                        transaction.replace(R.id.frame_layout,meetingFragment).commitAllowingStateLoss();
                        break;
                    case R.id.navigation_friends:
                        transaction.replace(R.id.frame_layout,friendListFragment).commitAllowingStateLoss();
                        break;
                }
                return true;
            }
        });


    }


    private void requestMe() {
        List<String> keys = new ArrayList<>();
        keys.add("properties.nickname");
        keys.add("properties.profile_image");
        keys.add("kakao_account.email");

        UserManagement.getInstance().me(keys, new MeV2ResponseCallback() {
            @Override
            public void onFailure(ErrorResult errorResult) {
                String message = "failed to get user info. msg=" + errorResult;
                Logger.d(message);
            }

            @Override
            public void onSessionClosed(ErrorResult errorResult) {
//                redirectLoginActivity();
            }

            @Override
            public void onSuccess(MeV2Response response) {
                kakaoUserName = response.getNickname();
                kakaoUserID = response.getId();
                kakaoUserProfileImg = response.getProfileImagePath();

                Log.d(TAG, "user id :" + kakaoUserID);
                Log.d(TAG, "user name :" + kakaoUserName);
                Log.d(TAG, "user profile img : " + kakaoUserProfileImg);
            }

//            @Override
//            public void onNotSignedUp() {
//                showSignup();
//            }
        });
    }









}
