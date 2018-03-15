package com.craterstudio.juanfrancrater.ipewa.ui.login.Interactor;

import com.craterstudio.juanfrancrater.ipewa.ui.login.Contrats.LoginContrat;

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
