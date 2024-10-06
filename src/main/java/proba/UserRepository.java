package proba;

import databasecon.database;
import models.User;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UserRepository {

    private database db;

    public UserRepository() {
        this.db = new database("users.sqlite");
    }

    public List<User> getAll() {
        return db.selectAll();
    }

    public User getUser(int id) {
        return db.selectUser(id);
    }

    public User createUser(User user) {
        db.insert(user);
        return user;
    }

    public void deleteUser(Long id) {
        db.delete(id);
    }

    public User updateUser(Long id, User updatedUser) {
        db.update(id, updatedUser);
        return updatedUser;
    }
}
