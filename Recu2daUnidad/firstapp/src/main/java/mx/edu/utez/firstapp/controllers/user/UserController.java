package mx.edu.utez.firstapp.controllers.user;


import lombok.RequiredArgsConstructor;
import mx.edu.utez.firstapp.config.ApiResponse;
import mx.edu.utez.firstapp.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"*"})
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.finAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getById(@PathVariable("id") Long id){
        return service.findById(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ApiResponse> changeStatus(@PathVariable("id") Long id){
        return service.changeStatus(id);
    }

    // Controller / Update -> DTO -> USer -> Service
    // Service -> Reglas de negocio -> Lógica de negocio
    // Repositorio -> Almacena los datos finales en la BD
}
