package ru.geekbrains.githubclient.mvp.presenter;

import moxy.MvpPresenter;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUserRepo;
import ru.geekbrains.githubclient.mvp.view.LoginView;


public class LoginPresenter extends MvpPresenter<LoginView> {

    private GithubUserRepo usersRepo = new GithubUserRepo();


    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    public GithubUser loginData(int loginId) {
        return usersRepo.getUser(loginId);
    }

    public LoginPresenter() {
        return;
    }

}
