package com.JWT.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.JWT.Models.Role;
import com.JWT.Models.User;
import com.JWT.Security.JwtTokenUtil;
import com.JWT.Service.IUserService;
import com.JWT.ServiceImpl.RoleService;

@Controller
@RequestMapping("/v1/api/")
public class UserController {
	
	/** Created by Jony Mittal on 04.04.19. **/
	
	@Autowired
	private IUserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@RequestMapping(value = "userSignup", method = RequestMethod.POST) //REST API TO SIGNUP USER
	public ResponseEntity<Map<String, Object>> addStudent(@RequestBody @Valid User user, BindingResult result) {
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			if (user == null) {
				response.put("data", "");
				response.put("status", "Fail");
				response.put("code", "400");
				response.put("message", "Bad Request");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			} else if (result.hasFieldErrors()) {
				response.put("data", "");
				response.put("status", "Fail");
				response.put("code", "400");
				response.put("message", result.getFieldError().getDefaultMessage());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			} else {
				User user1 = userService.getUserByEmail(user.getEmail());
				if (user1 == null) {
					Role role = roleService.getRoleById(2); 
					user.setRole(role);
					user = userService.saveUser(user);

					final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

					final String token = jwtTokenUtil.generateToken(userDetails);

					data.put("access_token", token);
					data.put("user", user);
					response.put("data", data);
					response.put("status", "OK");
					response.put("code", "200");
					response.put("message", "Record created successfully.");
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
				} else {
					response.put("data", "");
					response.put("status", "Fail");
					response.put("code", "406");
					response.put("message", "Email already registered with us.");
					return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
				}
			}
		} catch (Exception e) {
			response.put("data", data);
			response.put("status", "ERROR");
			response.put("code", "500");
			response.put("message", "Error while creating record.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "userLogin", method = RequestMethod.POST) //REST API TO LOGIN USER, AFTER LOGIN IT GIVES THE ACCESS TOKEN
	public ResponseEntity<Map<String, Object>> login(@RequestBody String jsonStr) {
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		try {

			JSONObject jsonObject = new JSONObject(jsonStr);

			User user1 = userService.getUserByEmail(jsonObject.getString("email"));
			if (user1 != null && User.PASSWORD_ENCODER.matches(jsonObject.getString("password"), user1.getPassword())) {

				final UserDetails userDetails = userDetailsService.loadUserByUsername(user1.getEmail());

				final String token = jwtTokenUtil.generateToken(userDetails);

				System.out.println("TOKEN IS" + token);

				data.put("access_token", token);
				data.put("user", user1);
				response.put("data", data);
				response.put("status", "OK");
				response.put("code", "200");
				response.put("message", "Login successfull.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				data.put("user", user1);
				response.put("data", "");
				response.put("status", "Fail");
				response.put("code", "400");
				response.put("message", "email and password incorrect.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_ACCEPTABLE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.put("data", data);
			response.put("status", "ERROR");
			response.put("code", "500");
			response.put("message", "Error while creating record.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "user/{id}", method = RequestMethod.GET) //REST API TO GET USER DETAIL, HERE AUTHENTICATION TOKEN IS REQUIRED TO GET DETAILS.
	public ResponseEntity<Map<String, Object>> getStudentById(@PathVariable("id") Long id) {
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			User user = userService.getUserById(id);
			if (user == null) {
				response.put("data", "");
				response.put("status", "ERROR");
				response.put("code", "404");
				response.put("message", "Not found");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			} else {
				data.put("user", user);
				response.put("data", data);
				response.put("status", "OK");
				response.put("code", "302");
				response.put("message", "Record found successfully.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			response.put("data", data);
			response.put("status", "ERROR");
			response.put("code", "500");
			response.put("message", "Something went wrong");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "user/{userId}/resetPassword", method = RequestMethod.POST) //REST API TO CHANGE USER PASSWORD, HERE AUTHENTICATION TOKEN IS REQUIRED TO GET DETAILS.
	public ResponseEntity<Map<String, Object>> updatebusiness(@PathVariable("userId") Long userId, @RequestBody String jsonStr) {
		Map<String, Object> response = new HashMap<String, Object>();
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			User user = userService.getUserById(userId);
			if (user != null) {
				JSONObject jsonObject = new JSONObject(jsonStr);
				user.setPassword(jsonObject.getString("newPassword"));
				user.setLastPasswordResetDate(new Date());
				user = userService.saveUser(user);
				data.put("user", user);
				response.put("data", data);
				response.put("status", "OK");
				response.put("code", "200");
				response.put("message", "password updates successfully.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			} else {
				response.put("status", "ERROR");
				response.put("code", "404");
				response.put("message", "user not found.");
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

		} catch (Exception e) {
			response.put("data", data);
			response.put("status", "ERROR");
			response.put("code", "500");
			response.put("message", "Something went wrong.");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
