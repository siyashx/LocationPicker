package com.codesupreme.locationpickermodule;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.codesupreme.maplocationpicker.LocationPickerActivity;
import com.codesupreme.maplocationpicker.MapUtility;
import com.shivtechs.locationpickermodule.R;


public class MainActivity extends Activity implements View.OnClickListener {

    private TextView txtLatLong;
    private TextView txtAddress;
    private static final int ADDRESS_PICKER_REQUEST = 1020;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapUtility.apiKey = getResources().getString(R.string.api_key);
        findViewById(R.id.btnAddressPicker).setOnClickListener(this);
        txtLatLong = findViewById(R.id.txtLatLong);
        txtAddress = findViewById(R.id.txtAddress);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAddressPicker) {
            Intent intent = new Intent(MainActivity.this, LocationPickerActivity.class);
            startActivityForResult(intent, ADDRESS_PICKER_REQUEST);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADDRESS_PICKER_REQUEST) {
            try {
                if (data != null && data.getStringExtra(MapUtility.ADDRESS) != null) {
                    // String address = data.getStringExtra(MapUtility.ADDRESS);
                    double currentLatitude = data.getDoubleExtra(MapUtility.LATITUDE, 0.0);
                    double currentLongitude = data.getDoubleExtra(MapUtility.LONGITUDE, 0.0);
                    Bundle completeAddress = data.getBundleExtra("fullAddress");

                    assert completeAddress != null;
                    txtAddress.setText("addressline2: " +
                            completeAddress.getString("addressline2") + "\ncity: " +
                            completeAddress.getString("city") + "\npostalcode: " +
                            completeAddress.getString("postalcode") + "\nstate: " +
                            completeAddress.getString("state"));

                    txtLatLong.setText("Lat:" + currentLatitude +
                            "  Long:" + currentLongitude);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
