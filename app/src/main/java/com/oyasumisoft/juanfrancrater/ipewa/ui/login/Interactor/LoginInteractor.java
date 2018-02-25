package com.oyasumisoft.juanfrancrater.ipewa.ui.login.Interactor;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.UserRepository;
import com.oyasumisoft.juanfrancrater.ipewa.ui.login.Contrats.LoginContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.login.Presenter.LoginPresenter;
import com.oyasumisoft.juanfrancrater.ipewa.util.ThisApplication;

import java.util.concurrent.Executor;

/**
 * Created by PcCom on 31/12/2017.
 */

public class LoginInteractor  implements LoginContrat.Interactor{

    private LoginListener listener;
    public LoginInteractor(LoginListener listener) {
        this.listener=listener;
    }

    @Override
    public void validateUser(String username, String password) {
        if(username.isEmpty()) {
            listener.showErrorWrongUserPassword();
        } else
        if(password.isEmpty()) {
            listener.showErrorWrongUserPassword();
        }else {
            listener.Enter();
        }
    }

    public interface LoginListener{
        void Enter();
        void showErrorWrongUserPassword();
    }
}
