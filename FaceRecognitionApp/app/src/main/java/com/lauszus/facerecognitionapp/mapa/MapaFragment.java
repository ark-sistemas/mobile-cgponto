package com.lauszus.facerecognitionapp.mapa;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        //Trocar o texto abaixo pelo parametro que será usado para cadastrar o endereço que será buscado
        LatLng posicaoInformada =  findCoordenadaAddress
                ("R. 227-A, 95 - Setor Leste Universitário, Goiânia - GO, 74610-070");

        if (posicaoInformada != null){
            CameraUpdate update = CameraUpdateFactory.newLatLngZoom(posicaoInformada, 17);
            googleMap.moveCamera(update);
        }

        new Localizador(getContext(), googleMap);

        
    }

    private LatLng findCoordenadaAddress(String endereco) {
        try {

            Geocoder geocoder = new Geocoder(getContext());
            List<Address> resultados =
                    geocoder.getFromLocationName( endereco,1);

            if (resultados != null && !resultados.isEmpty()){
                LatLng posicao = new LatLng(
                        resultados.get(0).getLatitude(), resultados.get(0).getLongitude()
                );
                return posicao;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
