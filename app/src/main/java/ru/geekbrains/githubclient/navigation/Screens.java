package ru.geekbrains.githubclient.navigation;

import androidx.fragment.app.Fragment;

import ru.geekbrains.githubclient.ui.fragments.LoginFragment;
import ru.geekbrains.githubclient.ui.fragments.UsersFragment;
import ru.terrakok.cicerone.android.support.SupportAppScreen;

public class Screens {

    public static class UsersScreen extends SupportAppScreen {

        @Override
        public Fragment getFragment() {
            return UsersFragment.getInstance(0);
        }
    }

    public static class LoginScreen extends SupportAppScreen {
        private int id;

        public LoginScreen(int id) {
            this.id = id;
        }

        public LoginFragment getFragment() {
            return LoginFragment.getInstance(id);
        }
    }
}
