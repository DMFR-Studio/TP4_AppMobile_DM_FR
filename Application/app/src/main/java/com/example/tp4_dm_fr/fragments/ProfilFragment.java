package com.example.tp4_dm_fr.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.tp4_dm_fr.Client;
import com.example.tp4_dm_fr.ConsommationREST;
import com.example.tp4_dm_fr.MainActivity;
import com.example.tp4_dm_fr.OnUserAddedListener;
import com.example.tp4_dm_fr.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfilFragment extends Fragment {

    Button connexionLogInButton;
    EditText emailInput;
    EditText passwordInput;
    EditText adresseInput;
    EditText telephoneInput;
    EditText nomInput;
    private boolean emailIsValid;
    private boolean passwordIsValid;
    private boolean nameIsValid;
    ConsommationREST consoRest = new ConsommationREST();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profil_layout, container, false);
        passwordIsValid = false;
        emailIsValid = false;
        nameIsValid = false;
        remplirInformations(view);
        ajouterListeners();
        return view;
    }

    private void ajouterListeners() {
        nomInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                connexionLogInButton.setEnabled(areFieldsValid());
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
                connexionLogInButton.setEnabled(areFieldsValid());
            }
        });

        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                connexionLogInButton.setEnabled(areFieldsValid());
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
                connexionLogInButton.setEnabled(areFieldsValid());
            }
        });

        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                connexionLogInButton.setEnabled(areFieldsValid());
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
                connexionLogInButton.setEnabled(areFieldsValid());
            }
        });

        connexionLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consoRest.addUser(
                        v.getContext(),
                        nomInput.getText().toString(),
                        emailInput.getText().toString(),
                        passwordInput.getText().toString(),
                        adresseInput.getText().toString(),
                        telephoneInput.getText().toString(),
                        new OnUserAddedListener() {
                            @Override
                            public void onUserAdded(int id) {
                                if (id != 0) {
                                    //TODO: appeler la méthode de l'api qui modifie un utilisateur
                                }
                            }
                        });

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

    private void remplirInformations(View view) {
        connexionLogInButton = view.findViewById(R.id.modificationCompteButton);
        emailInput = view.findViewById(R.id.emailEditText);
        passwordInput = view.findViewById(R.id.passwordEditText);
        adresseInput = view.findViewById(R.id.adresseEditText);
        telephoneInput = view.findViewById(R.id.telephoneEditText);
        nomInput = view.findViewById(R.id.nomEditText);

        Client client = MainActivity.clientLoggedIn;

        nomInput.setText(client.getCourriel());
        passwordInput.setText(client.getMot_de_passe());
        adresseInput.setText(client.getAdresse());
        telephoneInput.setText(client.getTelephone());
    }
}
