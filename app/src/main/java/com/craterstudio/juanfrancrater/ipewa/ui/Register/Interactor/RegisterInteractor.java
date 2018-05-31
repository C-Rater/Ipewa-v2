package com.craterstudio.juanfrancrater.ipewa.ui.Register.Interactor;

import com.craterstudio.juanfrancrater.ipewa.ui.Register.Contrats.SigninContract;
import com.craterstudio.juanfrancrater.ipewa.util.EmailValidator;

/**
 * Created by PcCom on 08/01/2018.
 */

public class RegisterInteractor implements SigninContract.Interactor {
    RegisterListener listener;
    EmailValidator validator= new EmailValidator();
    public RegisterInteractor(RegisterListener listener) {
        this.listener=listener;
    }

    @Override
    public void signIn( final String password,final String passwordAgain, final String email) {
        if(password.equals(passwordAgain)) {
            if (password.isEmpty()) {
                listener.onPasswordEmptyError();
            } else if (email.isEmpty()) {
                listener.onEmailEmptyError();
            } else if (password.length() < 6) {
                listener.onPasswordError();
            } else if (!validator.validate(email)) {

                listener.onEmailError();

            } else {
                listener.onSuccess();
            }
        }else {listener.onPasswordDifferent();}
    }
    public interface RegisterListener
    {
        void onSuccess();
        void onUserEmptyError();
        void onPasswordEmptyError();
        void onEmailEmptyError();
        void onPasswordError();
        void onEmailError();
        void onUserError();
        void onUserDuplicated();
        void onEmailDuplicated();
        void onPasswordDifferent();
    }
}
