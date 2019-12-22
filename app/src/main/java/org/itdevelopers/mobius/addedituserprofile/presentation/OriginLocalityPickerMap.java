package org.itdevelopers.mobius.addedituserprofile.presentation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.GnssStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import org.itdevelopers.mobius.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * OBS:
 * LocationListener se implementa para conocer el estado del GPS
 */
public class OriginLocalityPickerMap extends SupportMapFragment implements
    OnMapReadyCallback,
    GoogleMap.OnMapClickListener,
    GoogleMap.OnMyLocationClickListener,
    GoogleMap.OnMyLocationButtonClickListener,
    LocationListener,
    EasyPermissions.PermissionCallbacks {

    public static final String TAG = OriginLocalityPickerMap.class.getSimpleName();

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private GoogleMap googleMap;
    private GnssStatus.Callback gnssStatusCallback;
    private LocationManager locationManager;

    public OriginLocalityPickerMap() {
    }

    public static OriginLocalityPickerMap newInstance() {
        return new OriginLocalityPickerMap();
    }

    /**
     * PRO: Escucha del cambio de estado del GPS
     * OBS: Se usó la interfaz GnssStatus.Callback
     * Fuente:
     *  . https://stackoverflow.com/a/43818318/5279996
     *  . https://developer.android.com/reference/android/location/GnssStatus.Callback.html
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!checkLocationPermission())
            requestLocationPermission();

        if (!checkLocationProviderEnabled()) {
            requestEnableLocationProvider();
        }

        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            gnssStatusCallback = new GnssStatus.Callback() {
                @Override
                public void onStarted() {
                    super.onStarted();
                    if (checkLocationPermission() && checkLocationProviderEnabled()) {
                        setMyLocationEnabled(true);
                    }
                }
                @Override
                public void onStopped() {
                    super.onStopped();
                    if (!checkLocationPermission() || !checkLocationProviderEnabled()) {
                        setMyLocationEnabled(false);
                    }
                }
            };
        }
    }

    /**
     * PRO: Habilita la escucha del GPS si el permiso está activado
     * OBS: Esta lógica no funciona en onStart
     * Fuente: https://stackoverflow.com/a/40142454/5279996
     */
    @Override
    public void onResume() {
        super.onResume();
        if (checkLocationPermission() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locationManager.registerGnssStatusCallback(gnssStatusCallback);
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 400, 0, this
            );
        }
    }

    /**
     * PRO: Desactiva la escucha del GPS
     */
    @Override
    public void onStop() {
        locationManager.removeUpdates(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locationManager.unregisterGnssStatusCallback(gnssStatusCallback);
        }
        super.onStop();
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
//        Método de asociación para registrar la actividad como escucha (onMapReadyCallback)
        getMapAsync(this);
    }


    /**
     * PRO: Establece o no el botón y el marcador de mi ubicación
     * PRE: Call requires permission which may be rejected by user
     * OBS: La ventaja de este método es que lo puedo usar dentro de callbacks
     */
    @SuppressLint("MissingPermission")
    private void setMyLocationEnabled(boolean set) {
        this.googleMap.setMyLocationEnabled(set);
    }

    /**
     * La clase GoogleMap es la médula de toda la API, ya que representa al mapa y permite manejar los gráficos
     * sobre este, transformar la cámara, escuchar eventos, tomar instantáneas, etc...
     * PRE: Tiene que existir una asociación para que este método sea llamado.
     * Para visualizar el botón de mi ubicación asegurarme de que la Toolbar no lo tape con CoordinatorLayout.
     * OBS: En el emulador mi ubicación y el botón no aparecen se activan luego de la primera confirmación del permiso
     * Fuente:
     *  . My Answer: https://stackoverflow.com/a/54971368/5279996
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
//        Guardo la instancia GoogleMap obtenida en este método en una campo global, que brinde futuros accesos.
        this.googleMap = googleMap;

        this.googleMap = animateCameraBsAs(googleMap);

//        Relaciono el mapa con la escucha
        this.googleMap.setOnMapClickListener(this);

//        Si se concedió el permiso y el gps está activado, se establece el marcador y botón de mi ubicación
        if (checkLocationPermission() && checkLocationProviderEnabled()) {
            setMyLocationEnabled(true);
        } else {
            setMyLocationEnabled(false);
        }

        this.googleMap.setOnMyLocationButtonClickListener(this);
        this.googleMap.setOnMyLocationClickListener(this);
    }

     /**
     * PRO: Anima la cámara hacia Buenos Aires
     */
    private GoogleMap animateCameraBsAs(GoogleMap googleMap) {
        LatLng buenosAires = new LatLng(-34.607562, -58.437076);
        googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(buenosAires, 10),
                2000,
                new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                        Toast.makeText(getContext(), "Buenos Aires", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancel() {
                    }
                }
        );
        return googleMap;
    }


    /**
     * PRO: Describe si el permiso de ubicación está habilitado
     */
    private boolean checkLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        return EasyPermissions.hasPermissions(getContext(), perms);
    }

    /**
     * PRO: Realiza la solicitud del permiso de ubicación
     * PRE: El permiso no tiene que estar habilitado
     * Fuente: https://stackoverflow.com/a/54971368/5279996
     * */
    private void requestLocationPermission() {
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        EasyPermissions.requestPermissions(this, "Please grant the location permission",
                LOCATION_PERMISSION_REQUEST_CODE, perms);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (checkLocationPermission() && checkLocationProviderEnabled()) {
            setMyLocationEnabled(true);
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (!checkLocationPermission() || !checkLocationProviderEnabled()) {
            setMyLocationEnabled(false);
        }
    }


    /**
      * PRO: Describe si un proveedor de ubicación está habilitado
      * OBS: El provedor puede ser: network or gps
      * @return
      * Fuente: https://stackoverflow.com/a/10311891/5279996
      */
    private boolean checkLocationProviderEnabled() {
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        boolean locationProviderEnabled = false;

        try {
             locationProviderEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ignored) {}

        return locationProviderEnabled;
    }

    /**
     * PRO: Realiza la petición para que el usuario pueda habilitar la ubicación
     * PRE: La ubicación tiene que estar desactivada
     */
    private void requestEnableLocationProvider() {
        new AlertDialog.Builder(getContext())
                .setMessage(R.string.gps_not_enabled)
                .setPositiveButton(R.string.open_location_settings, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getContext().startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    /**
     * PRO: Proceso el evento de la screen
     * PRE:
     * . Implementar la interfaz OnMapClickListener
     * . Asignar la escucha al mapa con GoogleMap.setOnMapClickListener()
     * OBS:
     * Se usa la interfaz OnMapClickListener.
     */
    @Override
    public void onMapClick(LatLng latLng) {
        FragmentActivity activity = getActivity();

//        Comparo que la acción del intent entrante del contexto sea de selección
        if (OriginLocalityPickerMapActivity.ACTION_PICK_LOCATION
                .equals(activity.getIntent().getAction())) {

//            Llamada al Geocoder en la UI del mapa
            getAddressFromLocation(latLng.latitude, latLng.longitude, activity, new GeocoderHandler());
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        FragmentActivity activity = getActivity();

//        Comparo que la acción del intent entrante del contexto sea de selección
        if (OriginLocalityPickerMapActivity.ACTION_PICK_LOCATION
                .equals(activity.getIntent().getAction())) {

//            Llamada al Geocoder en la UI del mapa
            getAddressFromLocation(location.getLatitude(), location.getLongitude(), activity, new GeocoderHandler());
        }
    }

    /**
     * PRO: Simplifica la creación de un intent de respuesta con el extra de la localidad
     *
     */
    private void showAddEditUserProfileScreen(String selectedLocation) {
        Intent responseIntent = new Intent();
        responseIntent.putExtra(OriginLocalityPickerMapActivity.EXTRA_LOCATION, selectedLocation);
        getActivity().setResult(Activity.RESULT_OK, responseIntent);
        getActivity().finish();
    }

    /**
     * PRO: Geocoder call. Obtiene la respuesta del Geocoder sin bloquear la UI.
     * OBS: Este procedimiento puede estar ubicado en una clase auxiliar.
     * Se usa un thread y un hanlder p/dicho propósito.
     */
    public static void getAddressFromLocation(
            final double latitude, final double longitude, final Context context, final Handler handler) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                String result = null;
                try {
                    List<Address> addressList = geocoder.getFromLocation(
                            latitude, longitude, 1);
                    if (addressList != null && addressList.size() > 0) {
                        Address address = addressList.get(0);
//                        Devuelvo la primera linea de dirección y la localidad
                        result = address.getLocality();
                    }
                } catch (IOException e) {
                    Log.e("TAG", "Impossible to connect to Geocoder", e);
                    e.printStackTrace();
                } finally {
                    Message message = Message.obtain();
                    message.setTarget(handler);
                    if (result != null) {
                        message.what = 1;
                        Bundle bundle = new Bundle();
                        bundle.putString("address", result);
                        message.setData(bundle);
                    } else {
                        message.what = 0;
                    }
                    message.sendToTarget();
                }
            }
        };
        thread.start();
    }




    /**
     * PRO: Almacena la ubicación seleccionada
     */
    @SuppressLint("HandlerLeak")
    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            String selectedLocation;
            switch (msg.what) {
                case 1:
                    Bundle bundle = msg.getData();
                    selectedLocation = bundle.getString("address");
                    break;
                default:
                    selectedLocation = null;
            }

//            Reemplazar por lo que se necesite hacer
            try {
                showAddEditUserProfileScreen(selectedLocation);
            } catch (NullPointerException e) {
                Log.d(TAG, "La ubicación seleccionada es nula");
            }

        }
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}
