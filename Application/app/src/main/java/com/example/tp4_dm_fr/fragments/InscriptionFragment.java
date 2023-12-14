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
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tp4_dm_fr.ConsommationREST;
import com.example.tp4_dm_fr.customListener.OnUserAddedListener;
import com.example.tp4_dm_fr.R;
import com.google.android.material.navigation.NavigationView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InscriptionFragment extends Fragment {
    private EditText nomInput;
    private EditText emailInput;
    private EditText passwordInput;
    private EditText adresseInput;
    private EditText telephoneInput;
    private Button creationCompteButton;
    private boolean passwordIsValid;
    private boolean emailIsValid;
    private boolean nameIsValid;

    ConsommationREST consoRest = new ConsommationREST();

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
        adresseInput = view.findViewById(R.id.adresseEditText);
        telephoneInput = view.findViewById(R.id.telephoneEditText);
        creationCompteButton = view.findViewById(R.id.creationCompteButton);
        creationCompteButton.setEnabled(false);
        nomInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                creationCompteButton.setEnabled(areFieldsValid());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isNameValid()) {
                    nomInput.setError(getText(R.string.strNomInvaldie));
                    nameIsValid = false;
                } else {
                    nameIsValid = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                creationCompteButton.setEnabled(areFieldsValid());
            }
        });

        emailInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                creationCompteButton.setEnabled(areFieldsValid());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isEmailValid()) {
                    emailInput.setError(getText(R.string.strCourrielInvalide));
                    emailIsValid = false;
                } else {
                    emailIsValid = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                creationCompteButton.setEnabled(areFieldsValid());
            }
        });

        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                creationCompteButton.setEnabled(areFieldsValid());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isPasswordValid()) {
                    passwordInput.setError(getText(R.string.strMotDePasse));
                    passwordIsValid = false;
                } else {
                    passwordIsValid = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                creationCompteButton.setEnabled(areFieldsValid());
            }
        });

        creationCompteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consoRest.addUser(
                        view.getContext(),
                        nomInput.getText().toString(),
                        emailInput.getText().toString(),
                        passwordInput.getText().toString(),
                        adresseInput.getText().toString(),
                        telephoneInput.getText().toString(),
                        new OnUserAddedListener() {
                            @Override
                            public void onUserAdded(int id) {
                                if (id != 0) {
                                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                    transaction.replace(R.id.frame, new SelectionPizzaFragment());
                                    transaction.commit();

                                    NavigationView navView = requireActivity().findViewById(R.id.navigation);
//                                    ImageView iconeUser = navView.findViewById(R.id.icone_user);
//                                    iconeUser.setVisibility(View.VISIBLE);

                                    for (int i = 0; i < navView.getMenu().size(); i++) {
                                        navView.getMenu().getItem(i).setVisible(true);
                                    }
                                } else {
                                    AlertDialog.Builder alert = createAlertWindow(requireContext(), (String) getText(R.string.strErreur), (String) getText(R.string.strReesayer));
                                    alert.create().show();
                                }
                            }
                        });
            }
        });
    }

    private boolean isPasswordValid() {
        return passwordInput.getText().toString().length() > 5;
    }

    private boolean isNameValid() {
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
