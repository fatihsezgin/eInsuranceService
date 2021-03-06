package edu.aydin.insurance.Controller;

import edu.aydin.insurance.Dtos.DriverDto;
import edu.aydin.insurance.Dtos.ServiceIncDto;
import edu.aydin.insurance.Dtos.UserAccountDto;
import edu.aydin.insurance.Service.DriverService;
import edu.aydin.insurance.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private DriverService driverService;

    @PostMapping(path = "/auth")
    public ResponseEntity<ServiceIncDto> login(@RequestBody UserAccountDto userAccountDto){

        return new ResponseEntity(loginService.login(userAccountDto.getUsername(),userAccountDto.getPassword()), HttpStatus.OK);
    }

    @PostMapping(path = "add")
    public void addDriver(@RequestBody DriverDto driverDto){
        driverService.addDriver(driverDto);
    }

}
