package com.craterstudio.juanfrancrater.ipewa.ui.login.Contrats;

/**
 * Created by PcCom on 31/12/2017.
 */

public interface LoginContrat {
    interface View{
        void Enter();
        void signUp();
        void showError(String error);
    }

    interface Presenter{
        void signIn(String username, String password);
        void signUp();
    }

    interface Interactor
    {
        void validateUser(String username, String password);

    }

}
