package fileproject.uploadfile.Controller;

import fileproject.uploadfile.Models.FileUser;
import fileproject.uploadfile.Repositories.UserRepository;
import fileproject.uploadfile.Requests.LoginRequest;
import fileproject.uploadfile.Requests.SignupRequest;
import fileproject.uploadfile.Responses.MessageResponse;
import fileproject.uploadfile.Security.Jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class Authorization {

    //    @Autowired GetJSON json;
    @Autowired
    AuthenticationManager authenticate;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;



    @PostMapping("/login")
    public String generateToken(@Valid @RequestBody LoginRequest authRequest) throws Exception {
        try {
            authenticate.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (Exception ex) {
            throw new Exception("inavalid username/password");
        }
        return jwtUtils.generateToken(authRequest.getUsername());
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Create new user's account
        FileUser user = new FileUser(signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }


}
