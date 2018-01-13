package com.oyasumisoft.juanfrancrater.ipewa.ui.Register.Contrats;

/**
 * Created by PcCom on 08/01/2018.
 */

public interface SigninContract {
    interface View {
        void onSuccess();
        void onUserEmptyError();
        void onPasswordEmptyError();
        void onEmailEmptyError();
        void onPasswordError();
        void onEmailError();
        void onUserDuplicated();
        void onEmailDuplicated();
        void onUserError();
    }

    interface Presenter {
        void validateCredentials(String user, String password, String email);
    }

    interface Interactor
    {
        void signIn(String user, String password, String email);
    }
}
