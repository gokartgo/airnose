package ite.kmitl.enose.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import ite.kmitl.enose.R;


@SuppressWarnings("unused")
public class MainFragment extends Fragment {

    EditText edName,edPhone;
    ImageButton imageBtnOil,imageBtnGas,imageBtnBurnTire,imageBtnSmellLiquid,imageBtnHairDye,imageBtnAmmonia
            ,imageBtnFood,imageBtnChemical,imageBtnGrass,imageBtnFruit,imageBtnRotten,imageBtnFish
            ,imageBtnGarbage,imageBtnCarSmoke,imageBtnVinegar,imageBtnSapodilla,imageBtnChlorine,imageBtnGlue
            ,imageBtnIron,imageBtnNoneSmell;
    EditText edOtherDetails;
    Button btnOk;

    // set dialog
    Dialog dialog;
    ImageView ivDialogSmellFeel;
    TextView tvDialogSmellFeel;
    Button btnDialogCancel,btnDialogConfirm;

    String name = "";
    String phone = "";
    String smell = "";
    String details = "";
    String smellDetails = "";

    public MainFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        // find edittext name and phone
        edName = (EditText) rootView.findViewById(R.id.edName);
        edPhone = (EditText) rootView.findViewById(R.id.edPhone);
        // find button id
        imageBtnOil = (ImageButton) rootView.findViewById(R.id.imageBtnOil);
        imageBtnGas = (ImageButton) rootView.findViewById(R.id.imageBtnGas);
        imageBtnBurnTire = (ImageButton) rootView.findViewById(R.id.imageBtnBurnTire);
        imageBtnSmellLiquid = (ImageButton) rootView.findViewById(R.id.imageBtnSmellLiquid);
        imageBtnHairDye = (ImageButton) rootView.findViewById(R.id.imageBtnHairDye);
        imageBtnAmmonia = (ImageButton) rootView.findViewById(R.id.imageBtnAmmonia);
        imageBtnFood = (ImageButton) rootView.findViewById(R.id.imageBtnFood);
        imageBtnChemical = (ImageButton) rootView.findViewById(R.id.imageBtnChemical);
        imageBtnGrass = (ImageButton) rootView.findViewById(R.id.imageBtnGrass);
        imageBtnFruit = (ImageButton) rootView.findViewById(R.id.imageBtnFruit);
        imageBtnRotten = (ImageButton) rootView.findViewById(R.id.imageBtnRotten);
        imageBtnFish = (ImageButton) rootView.findViewById(R.id.imageBtnFish);
        imageBtnGarbage = (ImageButton) rootView.findViewById(R.id.imageBtnGarbage);
        imageBtnCarSmoke = (ImageButton) rootView.findViewById(R.id.imageBtnCarSmoke);
        imageBtnVinegar = (ImageButton) rootView.findViewById(R.id.imageBtnVinegar);
        imageBtnSapodilla = (ImageButton) rootView.findViewById(R.id.imageBtnSapodilla);
        imageBtnChlorine = (ImageButton) rootView.findViewById(R.id.imageBtnChlorine);
        imageBtnGlue = (ImageButton) rootView.findViewById(R.id.imageBtnGlue);
        imageBtnIron = (ImageButton) rootView.findViewById(R.id.imageBtnIron);
        imageBtnNoneSmell = (ImageButton) rootView.findViewById(R.id.imageBtnNoneSmell);
        btnOk = (Button) rootView.findViewById(R.id.btnOk);
        // function set on click image button
        setOnClickButton();
        // find edit text id
        edOtherDetails = (EditText) rootView.findViewById(R.id.edOtherDetails);
    }

    private void setOnClickButton() {
        // set on click image button
        imageBtnOil.setOnClickListener(imageBtnClick);
        imageBtnGas.setOnClickListener(imageBtnClick);
        imageBtnBurnTire.setOnClickListener(imageBtnClick);
        imageBtnSmellLiquid.setOnClickListener(imageBtnClick);
        imageBtnHairDye.setOnClickListener(imageBtnClick);
        imageBtnAmmonia.setOnClickListener(imageBtnClick);
        imageBtnFood.setOnClickListener(imageBtnClick);
        imageBtnChemical.setOnClickListener(imageBtnClick);
        imageBtnGrass.setOnClickListener(imageBtnClick);
        imageBtnFruit.setOnClickListener(imageBtnClick);
        imageBtnRotten.setOnClickListener(imageBtnClick);
        imageBtnFish.setOnClickListener(imageBtnClick);
        imageBtnGarbage.setOnClickListener(imageBtnClick);
        imageBtnCarSmoke.setOnClickListener(imageBtnClick);
        imageBtnVinegar.setOnClickListener(imageBtnClick);
        imageBtnSapodilla.setOnClickListener(imageBtnClick);
        imageBtnChlorine.setOnClickListener(imageBtnClick);
        imageBtnGlue.setOnClickListener(imageBtnClick);
        imageBtnIron.setOnClickListener(imageBtnClick);
        imageBtnNoneSmell.setOnClickListener(imageBtnClick);
        btnOk.setOnClickListener(btnClick);
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

    /************
     * onClick Listener
     */

    final View.OnClickListener imageBtnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == imageBtnOil){
                smell = getString(R.string.smell_oil);
                showDialog(R.drawable.oil,smell);
            }
            if(view == imageBtnGas){
                smell = getString(R.string.smell_gas);
                showDialog(R.drawable.gas,smell);
            }
            if(view == imageBtnBurnTire){
                smell = getString(R.string.smell_burn_tire);
                showDialog(R.drawable.burn_tire,smell);
            }
            if(view == imageBtnSmellLiquid){
                smell = getString(R.string.smell_liquid);
                showDialog(R.drawable.liquid,smell);
            }
            if(view == imageBtnHairDye){
                smell = getString(R.string.smell_hair_dye);
                showDialog(R.drawable.hair_dye,smell);
            }
            if(view == imageBtnAmmonia){
                smell = getString(R.string.smell_ammonia);
                showDialog(R.drawable.ammonia,smell);
            }
            if(view == imageBtnFood){
                smell = getString(R.string.smell_food);
                showDialog(R.drawable.food,smell);
            }
            if(view == imageBtnChemical){
                smell = getString(R.string.smell_chemical);
                showDialog(R.drawable.chemical,smell);
            }
            if(view == imageBtnGrass){
                smell = getString(R.string.smell_grass);
                showDialog(R.drawable.grass,smell);
            }
            if(view == imageBtnFruit){
                smell = getString(R.string.smell_fruit);
                showDialog(R.drawable.fruit,smell);
            }
            if(view == imageBtnRotten){
                smell = getString(R.string.smell_rotten);
                showDialog(R.drawable.rotten,smell);
            }
            if(view == imageBtnFish){
                smell = getString(R.string.smell_fish);
                showDialog(R.drawable.fish,smell);
            }
            if(view == imageBtnGarbage){
                smell = getString(R.string.smell_garbage);
                showDialog(R.drawable.garbage,smell);
            }
            if(view == imageBtnCarSmoke){
                smell = getString(R.string.smell_car_smoke);
                showDialog(R.drawable.car_smoke,smell);
            }
            if(view == imageBtnVinegar){
                smell = getString(R.string.smell_vinegar);
                showDialog(R.drawable.vinegar,smell);
            }
            if(view == imageBtnSapodilla){
                smell = getString(R.string.smell_sapodilla);
                showDialog(R.drawable.lovelorn,smell);
            }
            if(view == imageBtnChlorine){
                smell = getString(R.string.smell_chlorine);
                showDialog(R.drawable.chlorine,smell);
            }
            if(view == imageBtnGlue){
                smell = getString(R.string.smell_glue);
                showDialog(R.drawable.glue,smell);
            }
            if(view == imageBtnIron){
                smell = getString(R.string.smell_iron);
                showDialog(R.drawable.iron,smell);
            }
            if(view == imageBtnNoneSmell){
                smell = getString(R.string.none_smell);
                showDialog(R.drawable.none_smell,smell);
            }
        }
    };

    final View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnOk){
                smell = "-";
                sentValueNextFragment();
            }
        }
    };

    private void showDialog(int picture,String smell) {
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_smell_feel);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        // set dialog
        ivDialogSmellFeel = (ImageView) dialog.findViewById(R.id.ivDialogSmellFeel);
        tvDialogSmellFeel = (TextView) dialog.findViewById(R.id.tvDialogSmellFeel);
        btnDialogCancel = (Button) dialog.findViewById(R.id.btnDialogCancel);
        btnDialogConfirm = (Button) dialog.findViewById(R.id.btnDialogConfirm);
        ivDialogSmellFeel.setImageResource(picture);
        tvDialogSmellFeel.setText(smell);
        btnDialogCancel.setOnClickListener(btnDialogClick);
        btnDialogConfirm.setOnClickListener(btnDialogClick);
        dialog.show();



    }

    /*********
     * dialog button on click
     */
    final View.OnClickListener btnDialogClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(view == btnDialogCancel){
                dialog.cancel();
            }
            if(view == btnDialogConfirm){
                dialog.cancel();
                sentValueNextFragment();
            }
        }
    };

    public void sentValueNextFragment(){
        name = edName.getText().toString().replace(" ","_");
        phone = edPhone.getText().toString().replace(" ","_");
        smell = smell.replace(" ","_");
        details = edOtherDetails.getText().toString().replace(" ","_");
        if(details.trim().equals("")){
            details = "-";
        }
        smellDetails = name+" "+phone+" "+smell+" "+details;
        getFragmentManager().beginTransaction()
                .replace(R.id.contentContainer,FragmentSmellTime.newInstance(smellDetails),"FragmentSmellTime")
                .addToBackStack(null)
                .commit();
    }

}
