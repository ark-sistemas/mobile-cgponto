package com.lauszus.facerecognitionapp.mapa;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.lauszus.facerecognitionapp.R;
import com.lauszus.facerecognitionapp.activity.MapaActivity;

import java.io.IOException;
import java.util.List;

import lombok.NonNull;

import static android.support.constraint.Constraints.TAG;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    private GoogleMap googleMap;

    private FusedLocationProviderClient mFusedLocationProviderClient;

    private Boolean mLocationPermissionsGranted = false;

    private Circle circle;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        //Trocar o texto abaixo pelo parametro que será usado para cadastrar o endereço que será buscado
        //Coloca um ponto fixo no endereço do senai como referencia
        LatLng posicaoInformada = findCoordenadaAddress
                ("R. 227-A, 95 - Setor Leste Universitário, Goiânia - GO, 74610-070");

        //Insere o círculo ao redor da localização, com raio de 30 metros
         circle = googleMap.addCircle(new CircleOptions().center(posicaoInformada)
                .radius(30)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));

        if (posicaoInformada != null) {
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoInformada, 17);
            googleMap.moveCamera(update);
        }

        new Localizador(getContext(), googleMap);

        //Aqui retorna a localização atual do usuário que estiver logado
        if (mLocationPermissionsGranted) {
            getDeviceLocation(circle);

            //Tratamento de permissões do aparelho
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            googleMap.setMyLocationEnabled(true);
            googleMap.getUiSettings().setMyLocationButtonEnabled(false);

        }


    }

    private LatLng findCoordenadaAddress(String endereco) {
        try {

            Geocoder geocoder = new Geocoder(getContext());
            List<Address> resultados =
                    geocoder.getFromLocationName(endereco, 1);

            if (resultados != null && !resultados.isEmpty()) {
                LatLng posicao = new LatLng(
                        resultados.get(0).getLatitude(), resultados.get(0).getLongitude()
                );
                return posicao;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Permite pegar a localização atual do dispositivo
    private void getDeviceLocation(Circle circle) {

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());


        final Task location = mFusedLocationProviderClient.getLastLocation();
        location.addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if (task.isSuccessful()) {
                    Location currentLocation = (Location) task.getResult();

                    moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                            15f);
                    float[] distance = new float[0];

                    //Aqui pega a distancia entre o raio e a posição atual da pessoa
                    Location.distanceBetween(currentLocation.getLatitude(),
                            currentLocation.getLongitude(), circle.getCenter().latitude,
                            circle.getCenter().longitude, distance);

                    //Verifica se a pessoao está dentro do raio ou não
                    if (distance[0] > circle.getRadius()) {
                        //Aqui precisa ser inserido a chamada para bater o ponto

                    }else if (distance[0] < circle.getRadius()) {
                        //Caso não esteja dentro do raio da área
                    }

                }
            }
        });
    }

    //Movimentação da câmera
    private void moveCamera(LatLng latLng, float zoom) {
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

}
