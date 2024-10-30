package ies.carrillo.ejemploclasesqlite.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ies.carrillo.ejemploclasesqlite.models.UserAccount;

@Dao
public interface UserAccountDAO {
    @Insert
    long insert(UserAccount userAccount);
    @Query("SELECT * FROM user_account")
    List<UserAccount> getAll();
    @Query("SELECT * FROM user_account WHERE userId = :userAccountId")
    UserAccount getByUserAccountId(long userAccountId);
}
