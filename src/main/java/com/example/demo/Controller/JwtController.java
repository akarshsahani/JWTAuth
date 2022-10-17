package com.example.demo.Controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.helper.JwtUtil;
import com.example.demo.model.JwtRequest;
import com.example.demo.model.JwtResponse;
import com.example.demo.services.CustomUserDetailsService;

import io.jsonwebtoken.Claims;

@RestController
public class JwtController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
//	@Autowired
//	private JwtRequest abc;
	
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		System.out.println(jwtRequest);
		
		try {
			
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
		}catch(UsernameNotFoundException e)
		{
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}catch (BadCredentialsException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		
		UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
		
		String token = ((JwtUtil) this.jwtUtil).generateToken(userDetails);
		System.out.println("JWT" + token);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/payload", method = RequestMethod.POST)
	public JwtRequest payload(@RequestBody JwtRequest jwtRequest) {
		
		//System.out.println(jwtRequest);
		
		String aa = jwtRequest.getToken();
		
		//System.out.println(aa);
		
		//JwtController aa = new JwtController();
		//String tok = jwtRequest.getToken(jw);
		 //System.out.println(token);
		
		String uname = (this.jwtUtil).getUsernameFromToken(aa);
		Date exp = jwtUtil.extractExpiration(aa);
		//String sub = jwtUtil.extractAllClaims
		String cl = jwtUtil.extractClaim(aa,  Claims::getSubject);
		
		System.out.println("User Name: " + uname);
		System.out.println("Exp Date: " + exp);
		//System.out.println("Clain " + cl);
		return null;
	}

}
