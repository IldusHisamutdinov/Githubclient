package ru.geekbrains.githubclient.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import moxy.MvpAppCompatFragment;
import moxy.MvpDelegate;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.geekbrains.githubclient.R;
import ru.geekbrains.githubclient.mvp.model.entity.GithubUser;
import ru.geekbrains.githubclient.mvp.presenter.LoginPresenter;
import ru.geekbrains.githubclient.mvp.view.LoginView;


public class LoginFragment extends MvpAppCompatFragment implements LoginView {
    private static final String USER_INDEX = "user_index";
    private TextView textView;
    private View view;
    private int userId;

    @InjectPresenter
    public LoginPresenter presenter;

    @ProvidePresenter
    LoginPresenter presenter() {
        return new LoginPresenter();
    }

    public static LoginFragment getInstance(int data) {
        LoginFragment loginFragment = new LoginFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(USER_INDEX, data);

        loginFragment.setArguments(bundle);
        return loginFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            userId = bundle.getInt(USER_INDEX);
        }
    }

    @Override
    public void init() {
        textView = view.findViewById(R.id.tv_login1);
        textView.setText(presenter.loginData(userId).getLogin());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

}
