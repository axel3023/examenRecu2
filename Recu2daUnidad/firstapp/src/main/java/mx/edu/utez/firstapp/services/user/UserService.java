package mx.edu.utez.firstapp.services.user;

import mx.edu.utez.firstapp.config.ApiResponse;
import mx.edu.utez.firstapp.models.user.User;
import mx.edu.utez.firstapp.models.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository repository;
    public UserService(UserRepository repository) {
        this.repository = repository;
    }
    @Transactional(readOnly = true)
    public Optional<User> findUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> finAll() {
        return new ResponseEntity<>(
                new ApiResponse(repository.findAll(),HttpStatus.OK),
                HttpStatus.OK
        );
    }

    /*@Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> update(User user){
        try{

        }catch (Exception e){

        }
    }*/

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<ApiResponse> changeStatus(Long id){
        Optional<User> foundUser = repository.findById(id);
        if (foundUser.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST,true,"UserNotFound"),
                    HttpStatus.BAD_REQUEST);
        User user = foundUser.get();
        user.setStatus(!user.getStatus());
        return new ResponseEntity<>(new ApiResponse(repository.save(user), HttpStatus.OK), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<ApiResponse> findById(Long id){
        Optional<User> foundUser = repository.findById(id);
        if (foundUser.isEmpty())
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "UserNotFound"), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(new ApiResponse(foundUser.get(), HttpStatus.OK), HttpStatus.OK);
    }
}
