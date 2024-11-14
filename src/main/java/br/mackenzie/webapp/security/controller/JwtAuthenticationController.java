package br.mackenzie.webapp.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.mackenzie.webapp.security.model.User;
import br.mackenzie.webapp.security.service.JwtService;
import br.mackenzie.webapp.security.service.UserService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody User authenticationRequest) throws Exception {

		Authentication authenticate = authenticationManager
			.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		if (authenticate.isAuthenticated()) {
			return ResponseEntity.ok(new JwtResponse(jwtService.generateToken(authenticationRequest.getUsername())));
		}
		else {
			throw new UsernameNotFoundException("Requisição de usuário inválido!");
		}
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<?> saveUser(@RequestBody User user) throws Exception {
		return ResponseEntity.ok(userService.addUser(user));
	}

}