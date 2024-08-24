package com.joaovitorseiji.worshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaovitorseiji.worshopmongo.domain.User;
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
	}
				
		
	
