package com.craterstudio.juanfrancrater.ipewa.ui.Register.Presenter;

import com.craterstudio.juanfrancrater.ipewa.ui.Register.Contrats.SigninContract;
import com.craterstudio.juanfrancrater.ipewa.ui.Register.Interactor.RegisterInteractor;

/**
 * Created by PcCom on 08/01/2018.
 */

public class RegisterPresenter implements SigninContract.Presenter,RegisterInteractor.RegisterListener {

    SigninContract.Interactor interactor;
    SigninContract.View view;

    public RegisterPresenter(SigninContract.View view) {
        this.interactor = new RegisterInteractor(this);
        this.view=view;
    }

    @Override
    public void validateCredentials( String password, String email) {
    interactor.signIn(password,email);
    }

    @Override
    public void onSuccess() {
    view.onSuccess();
    }

    @Override
    public void onUserEmptyError() {
        view.onUserEmptyError();
    }

    @Override
    public void onPasswordEmptyError() {
        view.onPasswordEmptyError();
    }

    @Override
    public void onEmailEmptyError() {
        view.onEmailEmptyError();
    }

    @Override
    public void onPasswordError() {
        view.onPasswordError();
    }

    @Override
    public void onEmailError() {
        view.onEmailError();
    }

    @Override
    public void onUserError() {
        view.onUserError();
    }


    @Override
    public void onUserDuplicated() {
        view.onUserDuplicated();
    }

    @Override
    public void onEmailDuplicated() {
        view.onEmailDuplicated();
    }
}


