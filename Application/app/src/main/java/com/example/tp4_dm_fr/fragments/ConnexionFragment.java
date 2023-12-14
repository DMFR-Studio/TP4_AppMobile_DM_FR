package com.example.tp4_dm_fr.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tp4_dm_fr.ConsommationREST;
import com.example.tp4_dm_fr.customListener.OnLoginResultListener;
import com.example.tp4_dm_fr.R;
import com.google.android.material.navigation.NavigationView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConnexionFragment extends Fragment {
    ConsommationREST consoRest = new ConsommationREST();
    private Button connexionLogInButton;
    private EditText emailInput;
    private EditText passwordInput;
    private boolean passwordIsValid;
    private boolean emailIsValid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_connexion, container, false);
        passwordIsValid = false;
        emailIsValid = false;
        initInputField(view);
        return view;
    }

    public void initInputField(View view){
        emailInput = view.findViewById(R.id.emailLogIn);
        passwordInput = view.findViewById(R.id.passwordLogin);
        connexionLogInButton = view.findViewById(R.id.connexionLogInButton);

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
                consoRest.logInUser(
                        view.getContext(),
                        emailInput.getText().toString(),
                        passwordInput.getText().toString(),
                        new OnLoginResultListener() {
                            @Override
                            public void onLoginResult(boolean success, int id) {
                                if (success && id != 0) {

                                    //TODO: tester si le changement de vue fonctionne
//                                    FragmentTransaction transaction = new ConnexionFragment().getChildFragmentManager().beginTransaction();
//                                    transaction.replace(R.id.frame, new SelectionPizzaFragment());
//                                    transaction.commit();
//                                    ((ImageView)(view.findViewById(R.id.icone_user))).setVisibility(View.VISIBLE);
//                                    NavigationView navView = (NavigationView) view.findViewById(R.id.navigation);
//                                    for (int i = 0; i < navView.getMenu().size(); i++) {
//                                        navView.getMenu().getItem(i).setVisible(true);
//                                    }
                                } else {
                                    AlertDialog.Builder alert = createAlertWindow(view.getContext(), "Erreur", "Le courriel ou le mot de passe est invalide");
                                    alert.create().show();
                                }
                            }
                        }
                );
            }
        });

    }

    private boolean isEmailValid() {
        Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailInput.getText().toString());
        return matcher.find();
    }

    private boolean isPasswordValid() {
        return passwordInput.getText().toString().length() > 5;
    }

    private boolean areFieldsValid() {
        return emailIsValid && passwordIsValid;
    }

    private AlertDialog.Builder createAlertWindow(Context context, String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View dialogView = getLayoutInflater().inflate(R.layout.alert_dialog_layout, null);
        TextView alertMessageTextView = dialogView.findViewById(R.id.alertMessageTextView);

        alertMessageTextView.setText(message);

        builder.setView(dialogView);
        builder.setTitle(title);
        builder.setPositiveButton(getText(R.string.strOk), (dialog, which) -> {
            emailInput.setText("");
            passwordInput.setText("");
        });

        return builder;
    }

}
