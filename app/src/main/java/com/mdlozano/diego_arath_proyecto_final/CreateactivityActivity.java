package com.mdlozano.diego_arath_proyecto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateactivityActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;

    Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createactivity);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);

        btnCreate = findViewById(R.id.btnCreate);

        Intent intent = getIntent();

        String origen = intent.getExtras().getString("Origen");

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

    }

    private void prepareTransacciones() {

        editText1.setHint("Costo");
        editText2.setHint("Fecha de entrega");
        editText3.setHint("Tipo de transaccion");
        editText4.setVisibility(View.INVISIBLE);

        btnCreate.setOnClickListener(view -> {
            Transaccion dato = new Transaccion();

            dato.setCosto(Integer.parseInt(editText1.getText().toString()));
            dato.setFecha_Entrega(editText2.getText().toString());
            dato.setTipo_Transaccion(editText3.getText().toString());

            Transaccion.TransaccionBD database = new Transaccion.TransaccionBD();

            if(database.insertTransaccion(dato)){
                Toast.makeText(this, "Elemento Insertado Correctamente", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Ocurrió un problema", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void prepareSucursales() {

        editText1.setHint("Domicilio");
        editText2.setHint("Nombre");
        editText3.setHint("ID de Empleado");
        editText4.setVisibility(View.INVISIBLE);

        btnCreate.setOnClickListener(view -> {
            Sucursal dato = new Sucursal();

            dato.setDomicilio(editText1.getText().toString());
            dato.setNombre(editText2.getText().toString());
            dato.setId_Empleado(Integer.parseInt(editText3.getText().toString()));

            Sucursal.SucursalBD database = new Sucursal.SucursalBD();

            if(database.insertSucursal(dato)){
                Toast.makeText(this, "Elemento Insertado Correctamente", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Ocurrió un problema", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void prepareServicios() {

        editText1.setHint("Tipo de servicio");
        editText2.setHint("Fecha de inicio");
        editText3.setVisibility(View.INVISIBLE);
        editText4.setVisibility(View.INVISIBLE);

        btnCreate.setOnClickListener(view -> {
            Servicio dato = new Servicio();

            dato.setTipo_Servicio(editText1.getText().toString());
            dato.setFecha_Inicio(editText2.getText().toString());

            Servicio.ServicioBD database = new Servicio.ServicioBD();

            if(database.insertServicio(dato)){
                Toast.makeText(this, "Elemento Insertado Correctamente", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Ocurrió un problema", Toast.LENGTH_LONG).show();
            }

        });
    }

    private void prepareEmpleados() {

        editText1.setHint("Nombre");
        editText2.setHint("Telefono");
        editText3.setHint("Apellidos");
        editText4.setHint("Domicilio");

        btnCreate.setOnClickListener(view -> {
            Empleado dato = new Empleado();

            dato.setNombre(editText1.getText().toString());
            dato.setTelefono(editText2.getText().toString());
            dato.setApellidos(editText3.getText().toString());
            dato.setDomicilio(editText4.getText().toString());

            Empleado.EmpleadoBD database = new Empleado.EmpleadoBD();

            if(database.insertEmpleado(dato)){
                Toast.makeText(this, "Elemento Insertado Correctamente", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Ocurrió un problema", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void prepareAutomoviles() {

        editText1.setHint("Placas");
        editText2.setHint("Marca");
        editText3.setHint("Modelo");
        editText4.setHint("ID de Cliente");

        btnCreate.setOnClickListener(view -> {
            Automovil dato = new Automovil();

            dato.setPlacas(editText1.getText().toString());
            dato.setMarca(editText2.getText().toString());
            dato.setModelo(editText3.getText().toString());
            dato.setId_Cliente(Integer.parseInt(editText4.getText().toString()));

            Automovil.AutomovilBD database = new Automovil.AutomovilBD();

            if(database.insertAutomovil(dato)){
                Toast.makeText(this, "Elemento Insertado Correctamente", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Ocurrió un problema", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void prepareClientes() {

        editText1.setHint("Nombre");
        editText2.setHint("Telefono");
        editText3.setHint("Apellidos");
        editText4.setHint("Domicilio");

        btnCreate.setOnClickListener(view -> {
            Cliente dato = new Cliente();

            dato.setNombre(editText1.getText().toString());
            dato.setTelefono(editText2.getText().toString());
            dato.setApellidos(editText3.getText().toString());
            dato.setDomicilio(editText4.getText().toString());

            Cliente.ClienteBD database = new Cliente.ClienteBD();

            if(database.insertCliente(dato)) {
                Toast.makeText(this, "Elemento Insertado Correctamente", Toast.LENGTH_LONG).show();
                finish();
            } else {
                Toast.makeText(this, "Ocurrió un problema", Toast.LENGTH_LONG).show();
            }
        });
    }
}