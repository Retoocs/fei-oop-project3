package app.user.service;

import app.agreement.domain.AgreementListener;
import org.springframework.stereotype.Service;
import app.user.domain.User;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private Map<Long, User> users;

    public UserService(){
        this.users = new HashMap<>();
    }

    public Map<Long, User> getUsers() {
        return users;
    }

    public void setUsers(Map<Long, User> users) {
        if(users == null){
            throw new IllegalArgumentException("'users' is null.");
        }
        this.users = users;
    }

    public void addUser(User user){
        if(user == null){
            throw new IllegalArgumentException("'user' is null.");
        }
        this.users.put(user.getId(), user);
    }

    public void removeUser(long userID){
        if(users.remove(userID) == null){
            throw new IllegalArgumentException("User with userID: " + userID + " does not exist.");
        }
    }

    public User getUser(long userID){
        if(users.containsKey(userID)){
            return users.get(userID);
        }
        throw new IllegalArgumentException("User with userID: " + userID + " does not exist.");
    }

}
