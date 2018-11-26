package com.example.vdanielmora.myp.Helper;

import android.content.Context;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

public class ValidacionEntradas {

    private Context context;

    public ValidacionEntradas(Context context){
        this.context = context;
    }

    //Revisar si los campos de texto estan vacios o llenos
    public boolean validacionCamposVacios(EditText editText, String mensaje){
            String valor = editText.getText().toString().trim();
            if(valor.isEmpty()) {
                Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                return false;
            }
            return true;
    }

    public boolean validacionCorreo(EditText editText, String mensaje){
        String valor = editText.getText().toString().trim();
        if(valor.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(valor).matches()){
            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean validacionCamposContraseña(EditText contraseña1, EditText contraseña2){
        String valor1 = contraseña1.getText().toString().trim();
        String valor2 = contraseña2.getText().toString().trim();

        if(!valor1.contentEquals(valor2)){
            Toast.makeText(context, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public boolean validacionCamposNumericos(EditText editText, String mensaje){

        int dato = Integer.parseInt(editText.getText().toString().trim());

        if(dato!=(int)dato) {
            return false;
        }
        return true;

    }

}
