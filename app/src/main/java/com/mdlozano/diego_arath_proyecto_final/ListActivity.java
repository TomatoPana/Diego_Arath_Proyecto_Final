package com.mdlozano.diego_arath_proyecto_final;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Set;

public class ListActivity extends AppCompatActivity {

    RadioGroup radioGroupElements;
    HashMap<Integer, String> elementos;
    Button btnContinuar;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        radioGroupElements = findViewById(R.id.radioGroupElements);
        btnContinuar = findViewById(R.id.btnContinue);

        Intent intent = getIntent();

        String origen = intent.getExtras().getString("Origen");
        String accion = intent.getExtras().getString("Accion");

        elementos = new HashMap<>();

        switch (origen) {
            case "Clientes":
                prepareClientes();
                break;
            case "Automoviles":
                prepareAutomoviles();
                break;
            case "Empleados":
                prepareEmpleados();
                break;
            case "Servicios":
                prepareServicios();
                break;
            case "Sucursales":
                prepareSucursales();
                break;
            case "Transacciones":
                prepareTransacciones();
                break;
        }

        createRadioButtons();

        btnContinuar.setOnClickListener(v -> {
            int checkedRadioButtonId = radioGroupElements.getCheckedRadioButtonId();
            Intent newActivity;

            if(checkedRadioButtonId != -1){
                switch (accion){
                    case "Read":
                        newActivity = new Intent(this, ReadactivityActivity.class);
                        newActivity.putExtra("Origen", origen);
                        newActivity.putExtra("ID", checkedRadioButtonId);
                        break;
                    case "Update":
                        newActivity = new Intent(this, UpdateactivityActivity.class);
                        newActivity.putExtra("Origen", origen);
                        newActivity.putExtra("ID", checkedRadioButtonId);
                        break;
                    case "Delete":
                        newActivity = new Intent(this, DeleteActivity.class);
                        newActivity.putExtra("Origen", origen);
                        newActivity.putExtra("ID", checkedRadioButtonId);
                        break;
                    default:
                        newActivity = null;
                        finish();
                        break;
                }
                startActivity(newActivity);

            } else {
                Toast.makeText(this, "Por favor, seleccione una entidad", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void prepareTransacciones() {
        Transaccion.TransaccionBD database = new Transaccion.TransaccionBD();
        ArrayList<Transaccion> resultado = database.getTransaccions();

        for(int index = 0; index < resultado.size(); index++){

            int key = resultado.get(index).getCosto();
            String value = resultado.get(index).getTipo_Transaccion();

            elementos.put(key, value);
        }
    }

    private void prepareSucursales() {
        Sucursal.SucursalBD database = new Sucursal.SucursalBD();
        ArrayList<Sucursal> resultado = database.getSucursals();

        for(int index = 0; index < resultado.size(); index++){

            int key = resultado.get(index).getId_Sucursal();
            String value = resultado.get(index).getNombre();

            elementos.put(key, value);
        }
    }

    private void prepareServicios() {
        Servicio.ServicioBD database = new Servicio.ServicioBD();
        ArrayList<Servicio> resultado = database.getServicios();

        for(int index = 0; index < resultado.size(); index++){

            int key = resultado.get(index).getId_Servicio();
            String value = resultado.get(index).getTipo_Servicio() + " del " + resultado.get(index).getFecha_Inicio();

            elementos.put(key, value);
        }
    }

    private void prepareEmpleados() {
        Empleado.EmpleadoBD database = new Empleado.EmpleadoBD();
        ArrayList<Empleado> resultado = database.getEmpleados();

        for(int index = 0; index < resultado.size(); index++){

            int key = resultado.get(index).getId_Empleado();
            String value = resultado.get(index).getNombre();

            elementos.put(key, value);
        }
    }

    private void prepareAutomoviles() {
        Automovil.AutomovilBD database = new Automovil.AutomovilBD();
        ArrayList<Automovil> resultado = database.getAutomovils();

        for(int index = 0; index < resultado.size(); index++){

            int key = resultado.get(index).getId_Automovil();
            String value = resultado.get(index).getMarca();

            elementos.put(key, value);
        }
    }

    private void prepareClientes() {
        Cliente.ClienteBD database = new Cliente.ClienteBD();
        ArrayList<Cliente> resultado = database.getClientes();

        for(int index = 0; index < resultado.size(); index++){

            int key = resultado.get(index).getId_Cliente();
            String value = resultado.get(index).getNombre();

            elementos.put(key, value);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void createRadioButtons() {

        for(int index = 0; index < elementos.size(); index++){
            RadioButton newElemento = new RadioButton(this);
            newElemento.setBackground(AppCompatResources.getDrawable(this, R.color.black));
            newElemento.setBackgroundTintMode(PorterDuff.Mode.ADD);
            newElemento.setTextColor(getColor(R.color.white));

            Set<Integer> setLlaves = elementos.keySet();

            Object[] llaves = setLlaves.toArray();

            Integer llave = (Integer) llaves[index];

            newElemento.setText(elementos.get(llave));
            newElemento.setId(llave);

            radioGroupElements.addView(newElemento, new RadioGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        }

    }
}