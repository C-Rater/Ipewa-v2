package com.oyasumisoft.juanfrancrater.ipewa.ui.login.Presenter;

import android.content.Context;

import com.oyasumisoft.juanfrancrater.ipewa.R;
import com.oyasumisoft.juanfrancrater.ipewa.ui.login.Contrats.LoginContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.login.Interactor.LoginInteractor;

/**
 * Created by PcCom on 31/12/2017.
 */

public class LoginPresenter implements LoginContrat.Presenter, LoginInteractor.LoginListener {
    private LoginContrat.Interactor interactor;
    private LoginContrat.View view;
    private Context context;

    public LoginPresenter(LoginContrat.View view, Context context) {
        this.interactor = new LoginInteractor(this);
        this.view = view;
        this.context = context;
    }

    @Override
    public void signIn(String username, String password) {
        interactor.validateUser(username,password);
    }

    @Override
    public void signUp() {

    }

    @Override
    public void Enter() {
        view.Enter();
    }

    @Override
    public void showErrorWrongUserPassword() {
        view.showError(context.getResources().getString(R.string.errorWrongUserPassword));
    }
}
