package com.example.ga_23s2.view;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import com.example.ga_23s2.cloud.dataAccess.DataAccess;
import com.example.ga_23s2.cloud.dataAccess.Keys;
import com.example.ga_23s2.databinding.FragmentAccountBinding;
import com.example.ga_23s2.model.UserId;
import com.example.ga_23s2.viewmodel.Session;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationHelper implements LocationListener, GpsListener {

  private static final int REQUEST_CODE = 100;
  private MainActivity activity;
  private Context context;
  private FragmentAccountBinding binding;

  public LocationHelper(Context context, FragmentAccountBinding binding, MainActivity activity) {
    this.context = context;
    this.binding = binding;
    this.activity = activity;
  }

  public void checkLocationPermissionAndFetch() {
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
      askPermission();
    } else {
      showLocation();
    }
  }

  private void askPermission() {
    ActivityCompat.requestPermissions(
        activity, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
  }

  @Override
  public void onRequestPermissionsResult(
      int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    if (requestCode == REQUEST_CODE) {
      if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        showLocation();
      }
    } else {
      Toast.makeText(activity, "GPS Permission Denied", Toast.LENGTH_SHORT).show();
      binding.txtLocation.setVisibility(View.INVISIBLE);
    }
  }

  @SuppressLint("MissingPermission")
  private void showLocation() {
    LocationManager locationManager =
        (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
      locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    } else {
      Toast.makeText(activity, "Enable GPS", Toast.LENGTH_SHORT).show();
    }
  }

  @Override
  public void onLocationChanged(@NonNull Location location) {
    if (location != null && !Session.getInstance().isGuestState()) {
      Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
      try {
        List<Address> addressList =
            geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        binding.txtLocation.setText(addressList.get(0).getAdminArea());
        UserId currentId = Session.getInstance().getCurrentUserId();
        DataAccess dataAccess = new DataAccess();
        dataAccess
            .getCollections(Keys.CollectionName.PUBLIC_USER.KEY)
            .document(currentId.toString())
            .update(Keys.UserDocumentName.LOCATION.KEY(), addressList.get(0).getAdminArea());
      } catch (IOException e) {
      }
    }
  }

  @Override
  public void onGpsStatusChanged(int i) {}
}
