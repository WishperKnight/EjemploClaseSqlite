package ies.carrillo.ejemploclasesqlite.services;

import android.app.Application;

import java.util.Collections;
import java.util.List;

import ies.carrillo.ejemploclasesqlite.daos.UserAccountDAO;
import ies.carrillo.ejemploclasesqlite.database.DatabaseHelper;
import ies.carrillo.ejemploclasesqlite.models.UserAccount;

public class UserAccountService implements UserAccountDAO {

    private long insertResult;

    private UserAccountDAO userAccountDAO;
    private List<UserAccount> userAccounts;
    private UserAccount user;
    public UserAccountService(Application application) {
        DatabaseHelper db = DatabaseHelper.getInstance(application.getApplicationContext());
        userAccountDAO = db.userAccountDAO();
    }

    @Override
    public long insert(UserAccount userAccount) {
        Thread thread = new Thread(() -> {
            insertResult = userAccountDAO.insert(userAccount);
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return insertResult;
    }

    @Override
    public UserAccount getByUsername(String username) {
        Thread thread = new Thread(() -> {
            user = userAccountDAO.getByUsername(username);
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<UserAccount> getAll() {
        Thread thread = new Thread(() -> {
            userAccounts = userAccountDAO.getAll();
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userAccounts;
    }

    @Override
    public UserAccount getByUserAccountId(long userAccountId) {
        Thread thread = new Thread(() -> {
            user = userAccountDAO.getByUserAccountId(userAccountId);
        });
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

}
