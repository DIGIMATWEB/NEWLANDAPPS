package com.NewLandApps.NewlandApps.ui.home.presenter;

import com.NewLandApps.NewlandApps.ui.home.model.User;

import java.util.List;

public interface presenterHomeFragment {
    void getUsers();

    void setUsers(List<User> usuarios);
}
