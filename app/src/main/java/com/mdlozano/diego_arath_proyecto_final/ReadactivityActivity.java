package com.mdlozano.diego_arath_proyecto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReadactivityActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    EditText editText3;
    EditText editText4;

    Button btnAtras;

    int ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readactivity);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);

        btnAtras = findViewById(R.id.btnAtras);

        Intent intent = getIntent();

        String origen = intent.getExtras().getString("Origen");
        ID = intent.getExtras().getInt("ID");

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

        btnAtras.setOnClickListener(v -> {
            finish();
        });

    }

    private void prepareTransacciones() {

        editText1.setHint("Costo");
        editText2.setHint("Fecha de entrega");
        editText3.setHint("Tipo de transaccion");
        editText4.setVisibility(View.INVISIBLE);

        Transaccion.TransaccionBD database = new Transaccion.TransaccionBD();

        Transaccion dato = database.getTransaccion(ID);

        editText1.setText(dato.getCosto());
        editText2.setText(dato.getFecha_Entrega());
        editText3.setText(dato.getTipo_Transaccion());

    }

    private void prepareSucursales() {

        editText1.setHint("Domicilio");
        editText2.setHint("Nombre");
        editText3.setHint("ID de Empleado");
        editText4.setVisibility(View.INVISIBLE);

        Sucursal.SucursalBD database = new Sucursal.SucursalBD();

        Sucursal dato = database.getSucursal(ID);

        editText1.setText(dato.getDomicilio());
        editText2.setText(dato.getNombre());
        editText3.setText(String.valueOf(dato.getId_Empleado()));

    }

    private void prepareServicios() {

        editText1.setHint("Tipo de servicio");
        editText2.setHint("Fecha de inicio");
        editText3.setVisibility(View.INVISIBLE);
        editText4.setVisibility(View.INVISIBLE);

        Servicio.ServicioBD database = new Servicio.ServicioBD();

        Servicio dato = database.getServicio(ID);

        editText1.setText(dato.getTipo_Servicio());
        editText2.setText(dato.getFecha_Inicio());

    }

    private void prepareEmpleados() {

        editText1.setHint("Nombre");
        editText2.setHint("Telefono");
        editText3.setHint("Apellidos");
        editText4.setHint("Domicilio");

        Empleado.EmpleadoBD database = new Empleado.EmpleadoBD();

        Empleado dato = database.getEmpleado(ID);

        editText1.setText(dato.getNombre());
        editText2.setText(dato.getTelefono());
        editText3.setText(dato.getApellidos());
        editText4.setText(dato.getDomicilio());
    }

    private void prepareAutomoviles() {

        editText1.setHint("Placas");
        editText2.setHint("Marca");
        editText3.setHint("Modelo");
        editText4.setHint("ID de Cliente");

        Automovil.AutomovilBD database = new Automovil.AutomovilBD();

        Automovil dato = database.getAutomovil(ID);

        editText1.setText(dato.getPlacas());
        editText2.setText(dato.getMarca());
        editText3.setText(dato.getModelo());
        editText4.setText(String.valueOf(dato.getId_Cliente()));
    }

    private void prepareClientes() {

        editText1.setHint("Nombre");
        editText2.setHint("Telefono");
        editText3.setHint("Apellidos");
        editText4.setHint("Domicilio");

        Cliente.ClienteBD database = new Cliente.ClienteBD();

        Cliente dato = database.getCliente(ID);

        editText1.setText(dato.getNombre());
        editText2.setText(dato.getTelefono());
        editText3.setText(dato.getApellidos());
        editText4.setText(dato.getDomicilio());
    }

}