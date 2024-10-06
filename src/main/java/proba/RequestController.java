package proba;

import models.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/users")
public class RequestController {

    private final UserRepository repository;

    public RequestController(UserRepository repository) {
        this.repository = repository;
    }
    @RequestMapping("/all")
    public ResponseEntity<List<User>> getAll()
    {
        return new ResponseEntity<>(repository.getAll(), HttpStatus.OK) ;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id)
    {
        User user = repository.getUser(Math.toIntExact(id));
        if (user != null)
        {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User newUser)
    {
        return new ResponseEntity<>(repository.createUser(newUser), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteTask(@PathVariable("id") Long id) {
        repository.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateTask(@PathVariable("id") Long id, @RequestBody User updatedUser) {
        User user = repository.updateUser(id, updatedUser);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
