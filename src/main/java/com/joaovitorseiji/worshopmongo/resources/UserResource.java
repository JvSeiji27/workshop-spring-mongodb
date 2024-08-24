package com.joaovitorseiji.worshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaovitorseiji.worshopmongo.domain.User;
import com.joaovitorseiji.worshopmongo.dto.UserDTO;
import com.joaovitorseiji.worshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService userService;
	
	@RequestMapping
	public ResponseEntity<List<UserDTO>> findAll(){
		List <User> list = userService.findAll();
		List <UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
		}
}
