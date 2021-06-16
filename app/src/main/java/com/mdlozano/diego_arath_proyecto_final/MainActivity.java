package com.mdlozano.diego_arath_proyecto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroupAction;
    RadioGroup radioGroupEntity;

    Button btnAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupAction = findViewById(R.id.radioGroupAction);
        radioGroupEntity = findViewById(R.id.radioGroupEntity);

        btnAction = findViewById(R.id.btnAvanzar);

        btnAction.setOnClickListener(view -> {
            Intent newActivity;
            boolean hasError = true;

            int selectedActionRadioButtonId = radioGroupAction.getCheckedRadioButtonId();

            switch (selectedActionRadioButtonId){
                case R.id.radioBtnCreate:
                    newActivity = new Intent(this, CreateactivityActivity.class);
                    hasError = false;
                    break;
                case R.id.radioBtnRead:
                    newActivity = new Intent(this, ListActivity.class);
                    newActivity.putExtra("Accion", "Read");
                    hasError = false;
                    break;
                case R.id.radioBtnUpdate:
                    newActivity = new Intent(this, ListActivity.class);
                    newActivity.putExtra("Accion", "Update");
                    hasError = false;
                    break;
                case R.id.radioBtnDelete:
                    newActivity = new Intent(this, ListActivity.class);
                    newActivity.putExtra("Accion", "Delete");
                    hasError = false;
                    break;
                default:
                    newActivity = null;
                    Toast.makeText(this, "Selecciona una acción", Toast.LENGTH_LONG).show();
                    hasError = true;
                    break;
            }

            int selectedEntityRadioButtonId = radioGroupEntity.getCheckedRadioButtonId();

            switch (selectedEntityRadioButtonId) {
                case R.id.radioBtnCliente:
                    if(!hasError){
                        newActivity.putExtra("Origen", "Clientes");
                    }
                    break;
                case R.id.radioBtnAutomovil:
                    if(!hasError){
                        newActivity.putExtra("Origen", "Automoviles");
                    }
                    break;
                case R.id.radioBtnEmpleado:
                    if(!hasError){
                        newActivity.putExtra("Origen", "Empleados");
                    }
                    break;
                case R.id.radioBtnServicio:
                    if(!hasError){
                        newActivity.putExtra("Origen", "Servicios");
                    }
                    break;
                case R.id.radioBtnSucursal:
                    if(!hasError){
                        newActivity.putExtra("Origen", "Sucursales");
                    }
                    break;
                case R.id.radioBtnTransaccion:
                    if(!hasError){
                        newActivity.putExtra("Origen", "Transacciones");
                    }
                    break;
                default:
                    newActivity = null;
                    Toast.makeText(this, "Selecciona una entidad", Toast.LENGTH_LONG).show();
                    hasError = true;
                    break;
            }

            if(!hasError){
                startActivity(newActivity);
            }

        });

    }
}