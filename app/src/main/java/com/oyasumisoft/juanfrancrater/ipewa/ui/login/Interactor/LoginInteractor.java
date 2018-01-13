package com.oyasumisoft.juanfrancrater.ipewa.ui.login.Interactor;

import android.util.Log;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.UserRepository;
import com.oyasumisoft.juanfrancrater.ipewa.ui.login.Contrats.LoginContrat;
import com.oyasumisoft.juanfrancrater.ipewa.ui.login.Presenter.LoginPresenter;

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
        if(UserRepository.getInstance().enterUser(username,password))
        {
            listener.Enter();
        }else{
            listener.showErrorWrongUserPassword();
        }


    }

    public interface LoginListener{
        void Enter();
        void showErrorWrongUserPassword();
    }
}
