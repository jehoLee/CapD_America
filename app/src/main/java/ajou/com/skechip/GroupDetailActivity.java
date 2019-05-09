package ajou.com.skechip;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import ajou.com.skechip.Fragment.GroupDetailFragment;

public class GroupDetailActivity extends AppCompatActivity {

    private boolean isNewGroup;

//    private List<String> friendsNickname_list = new ArrayList<>();
//    private String kakaoUserImg;
//    private String kakaoUserName;
//    private Long kakaoUserID;
//    private List<AppFriendInfo> kakaoFriends;

    private GroupDetailFragment groupDetailFragment;

    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        if (getIntent() != null) {
            bundle = getIntent().getBundleExtra("kakaoBundle");
            isNewGroup = getIntent().getBooleanExtra("isNewGroup", false);

//            kakaoUserID = kakaoBundle.getLong("kakaoUserID");
//            kakaoUserName = kakaoBundle.getString("kakaoUserName");
//            kakaoUserImg = kakaoBundle.getString("kakaoUserImg");
//            kakaoFriends = kakaoBundle.getParcelableArrayList("kakaoFriends");
//            friendsNickname_list = kakaoBundle.getStringArrayList("friendsNickname_list");
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        groupDetailFragment = GroupDetailFragment.newInstance(bundle);
        transaction.add(R.id.frame_layout, groupDetailFragment);
        transaction.commit();


    }


}
