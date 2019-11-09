package br.ufrpe.revcare.infra.gui;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import java.util.InputMismatchException;

import br.ufrpe.revcare.R;

public class Validacao {

    public boolean isCampoValido(EditText editText) {
        boolean result = true;
        if (editText.getText().toString().trim().length()==0) {
            editText.requestFocus();
            editText.setError("Preencha o Campo.");
            result = false;
        }
        return result;
    }

    public boolean isValido(EditText... editTexts) {
        boolean result = true;
        for (EditText editText : editTexts) {
            if (!isCampoValido(editText)) {
                result = false;
                break;
            }
        }
        return result;
    }
    //Validação de CPF tirada do aplicativo Trainee, feita por Henrique Cesar.
    // Link : https://github.com/TraineeUFRPE
    public static boolean isCPF(EditText CPF) {
        String cpf = CPF.getText().toString();
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11)){
            CPF.requestFocus();
            CPF.setError("CPF Inválido");
            return(false);

        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = cpf.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = cpf.charAt(i) - 48;
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10)))
                return(true);
            else{
                CPF.requestFocus();
                CPF.setError("CPF Inválido");
                return(false);
            }
        } catch (InputMismatchException erro) {
            return(false);
        }
    }


    public boolean confirmarSenha(Context context,String nSenha, String nConfirmarSenha) {
        boolean result = true;
        if (!nSenha.equals(nConfirmarSenha)) {
            result = false;
            Toast.makeText(context,"Senhas diferentes", Toast.LENGTH_LONG).show();
        }
        return result;
    }
}
