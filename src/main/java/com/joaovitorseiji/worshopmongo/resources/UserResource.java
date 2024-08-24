package com.joaovitorseiji.worshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
   @RequestMapping(value = "{id}")
   public ResponseEntity<UserDTO> findById(@PathVariable String id){
	   User user = userService.findById(id);
	   return ResponseEntity.ok().body(new UserDTO(user));
   }
   
   
   //Esse post serve para inserimos um User através de DTO (boa prática) e retorna o Status CREATED com o caminho (id e tal)
   @PostMapping
   public ResponseEntity<Void> insert(@RequestBody UserDTO userDto){
	  User obj = userService.fromDTO(userDto);
	   userService.insert(obj);
	   URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	   return ResponseEntity.created(uri).build();
   }
   
   @DeleteMapping(value = "/{id}")
   public ResponseEntity<Void> deleteById(@PathVariable String id){
	   userService.delete(id);
	   return ResponseEntity.noContent().build();
   }
}
