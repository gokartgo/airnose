package ite.kmitl.enose.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import ite.kmitl.enose.R;


@SuppressWarnings("unused")
public class FragmentSmellFeel extends Fragment {

    String smellDetailsTime = "";
    String smellDetailsTimeFeel = "";
    String feel = "";
    ImageButton imageBtnSmellVeryLight;
    ImageButton imageBtnSmellLight;
    ImageButton imageBtnSmellStrong;
    ImageButton imageBtnSmellVeryStrong;

    public FragmentSmellFeel() {
        super();
    }

    @SuppressWarnings("unused")
    public static FragmentSmellFeel newInstance(String smellDetailsTime) {
        FragmentSmellFeel fragment = new FragmentSmellFeel();
        Bundle args = new Bundle();
        args.putString("smellDetailsTime",smellDetailsTime);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);

        smellDetailsTime = getArguments().getString("smellDetailsTime");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_smell_feel, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // find image button resource
        imageBtnSmellVeryLight = (ImageButton)rootView.findViewById(R.id.imageBtnSmellVeryLight);
        imageBtnSmellLight = (ImageButton)rootView.findViewById(R.id.imageBtnSmellLight);
        imageBtnSmellStrong = (ImageButton)rootView.findViewById(R.id.imageBtnSmellStrong);
        imageBtnSmellVeryStrong = (ImageButton)rootView.findViewById(R.id.imageBtnSmellVeryStrong);
        imageBtnSmellVeryLight.setOnClickListener(btnImageFeelClick);
        imageBtnSmellLight.setOnClickListener(btnImageFeelClick);
        imageBtnSmellStrong.setOnClickListener(btnImageFeelClick);
        imageBtnSmellVeryStrong.setOnClickListener(btnImageFeelClick);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    /*********
     * Listener
     */

    final View.OnClickListener btnImageFeelClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == imageBtnSmellVeryLight){
                feel = getString(R.string.smell_very_light);
            }
            if(view == imageBtnSmellLight){
                feel = getString(R.string.smell_light);
            }
            if(view == imageBtnSmellStrong){
                feel = getString(R.string.smell_strong);
            }
            if(view == imageBtnSmellVeryStrong){
                feel = getString(R.string.smell_very_strong);
            }
            feel = feel.replace(" ","_");
            smellDetailsTimeFeel = smellDetailsTime+" "+feel;
            getFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer,FragmentSentInformation.newInstance(smellDetailsTimeFeel),"FragmentSentInformation")
                    .addToBackStack(null)
                    .commit();
        }
    };

}
