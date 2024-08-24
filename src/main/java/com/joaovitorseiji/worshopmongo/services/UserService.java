package com.joaovitorseiji.worshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaovitorseiji.worshopmongo.domain.User;
import com.joaovitorseiji.worshopmongo.dto.UserDTO;
import com.joaovitorseiji.worshopmongo.repository.UserRepository;
import com.joaovitorseiji.worshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id){
		Optional <User> obj = userRepository.findById(id);
		
		if(obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado"); 
		}else{
			return obj.get();
		}
		}
	
	public User insert(User obj) {
		return userRepository.insert(obj);
	}
	
	public User fromDTO(UserDTO userDto) {
		User user = new User(userDto.getId(), userDto.getName(), userDto.getEmail());
		return user;
	}
	
	
	public void delete(String id) {
		Optional<User> obj = userRepository.findById(id);
		if(obj.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não existe e não pode ser removido!");
		}
		else
			userRepository.deleteById(id);
	}
	
	public User update(User user){//Virá da requesição
		User newObj =  findById(user.getId());
		updateData(newObj, user);
		return userRepository.save(newObj);
	
	}
	
	public void updateData(User newObj, User user) {
		newObj.setId(user.getId());
		newObj.setEmail(user.getEmail());
		newObj.setName(user.getName());
	}

}				
		
	
