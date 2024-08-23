package com.joaovitorseiji.worshopmongo.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaovitorseiji.worshopmongo.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@RequestMapping
	public ResponseEntity<List<User>> findAll(){
			User maria = new User("1","Maria Silva","mariasilva@gmail.com");
			User alex = new User("2","Alex Moura","alexmoura@gmail.com");
			List<User> lista = new ArrayList<>();
			lista.add(maria);
			lista.add(alex);
			return ResponseEntity.ok().body(lista);
		}
}
