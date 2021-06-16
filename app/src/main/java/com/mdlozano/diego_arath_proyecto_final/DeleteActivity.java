package com.mdlozano.diego_arath_proyecto_final;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class DeleteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        Intent intent = getIntent();
        String origen = intent.getExtras().getString("Origen");
        int ID = intent.getExtras().getInt("ID");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Estás seguro de continuar? La acción no se puede deshacer")

                .setPositiveButton("Ok", (dialog, id) -> {

                    switch (origen) {
                        case "Clientes":
                            Cliente.ClienteBD database = new Cliente.ClienteBD();
                            database.deleteCliente(ID);
                            break;
                        case "Automoviles":
                            Automovil.AutomovilBD database2 = new Automovil.AutomovilBD();
                            database2.deleteAutomovil(ID);
                            break;
                        case "Empleados":
                            Empleado.EmpleadoBD database3 = new Empleado.EmpleadoBD();
                            database3.deleteEmpleado(ID);
                            break;
                        case "Servicios":
                            Servicio.ServicioBD database4 = new Servicio.ServicioBD();
                            database4.deleteServicio(ID);
                            break;
                        case "Sucursales":
                            Sucursal.SucursalBD database5 = new Sucursal.SucursalBD();
                            database5.deleteSucursal(ID);
                            break;
                        case "Transacciones":
                            Transaccion.TransaccionBD database6 = new Transaccion.TransaccionBD();
                            database6.deleteTransaccion(ID);
                            break;
                    }

                    Toast.makeText(this, "Elemento eliminado correctamente", Toast.LENGTH_LONG).show();
                    finish();
                })

                .setNegativeButton("Cancelar", (dialog, id) -> finish());

        AlertDialog dialog = builder.create();

        dialog.show();

    }
}