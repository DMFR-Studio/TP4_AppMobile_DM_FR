package com.example.tp4_dm_fr;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InscriptionFragment extends Fragment {
    private EditText nomInput;
    private EditText emailInput;
    private EditText passwordInput;
    private Button connexionButton;
    private boolean passwordIsValid;
    private boolean emailIsValid;
    private boolean nameIsValid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inscription, container, false);
        passwordIsValid = false;
        emailIsValid = false;
        nameIsValid = false;
        initFields(view);
        return view;
    }

    private void initFields(View view) {
        nomInput = view.findViewById(R.id.nomEditText);
        emailInput = view.findViewById(R.id.emailEditText);
        passwordInput = view.findViewById(R.id.passwordEditText);
        connexionButton = view.findViewById(R.id.creationCompteButton);
        connexionButton.setEnabled(false);

        nomInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                connexionButton.setEnabled(areFieldsValid());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!isNameValid()){
                    nomInput.setError(getText(R.string.strNomInvaldie));
                    nameIsValid = false;
                } else {
                    nameIsValid = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                connexionButton.setEnabled(areFieldsValid());
            }
        });

        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                connexionButton.setEnabled(areFieldsValid());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!isEmailValid()){
                    emailInput.setError(getText(R.string.strCourrielInvalide));
                    emailIsValid = false;
                } else {
                    emailIsValid = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                connexionButton.setEnabled(areFieldsValid());
            }
        });

        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                connexionButton.setEnabled(areFieldsValid());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!isPasswordValid()){
                    passwordInput.setError(getText(R.string.strMotDePasse));
                    passwordIsValid = false;
                } else {
                    passwordIsValid = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                connexionButton.setEnabled(areFieldsValid());
            }
        });
    }

    private boolean isPasswordValid() {
        return passwordInput.getText().toString().length() > 5;
    }

    private boolean isNameValid(){
        return nomInput.getText().toString().length() > 4;
    }

    private boolean isEmailValid() {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailInput.getText().toString());
        return matcher.find();
    }

    private boolean areFieldsValid() {
        return emailIsValid && passwordIsValid && nameIsValid;
    }
}
