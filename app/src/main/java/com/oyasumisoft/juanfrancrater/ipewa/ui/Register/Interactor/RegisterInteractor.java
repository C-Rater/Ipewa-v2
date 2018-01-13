package com.oyasumisoft.juanfrancrater.ipewa.ui.Register.Interactor;

import com.oyasumisoft.juanfrancrater.ipewa.data.db.Repository.UserRepository;
import com.oyasumisoft.juanfrancrater.ipewa.data.db.model.Usuarios;
import com.oyasumisoft.juanfrancrater.ipewa.ui.Register.Contrats.SigninContract;
import com.oyasumisoft.juanfrancrater.ipewa.ui.Register.Presenter.RegisterPresenter;
import com.oyasumisoft.juanfrancrater.ipewa.util.EmailValidator;

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
    public void signIn(String user, String password, String email) {
        if(user.isEmpty())
        {listener.onUserEmptyError();
        }else if(password.isEmpty())
        {listener.onPasswordEmptyError();
        }else if(email.isEmpty())
        {listener.onEmailEmptyError();
        }else if(user.length()<6)
        {listener.onUserError();
        }else if(password.length()<6)
        {listener.onPasswordError();
        }else if(!validator.validate(email)) {

        listener.onEmailError();

        }else if(UserRepository.getInstance().findUser(user)){
        listener.onUserDuplicated();
        }else if(UserRepository.getInstance().findEmail(email)) {
        listener.onEmailDuplicated();
        }else{
        UserRepository.getInstance().addUser(user,password,email);
        listener.onSuccess();
        }
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
    }
}
