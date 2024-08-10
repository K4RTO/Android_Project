package com.example.ga_23s2.view;

import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import androidx.annotation.NonNull;

public interface GpsListener extends LocationListener, GpsStatus.Listener {
  public void onRequestPermissionsResult(
      int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

  public void onLocationChanged(Location location);
}
