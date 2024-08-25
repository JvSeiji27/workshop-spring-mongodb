package com.joaovitorseiji.worshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaovitorseiji.worshopmongo.domain.Post;
import com.joaovitorseiji.worshopmongo.repository.PostRepository;
import com.joaovitorseiji.worshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService{
	
	@Autowired
	private PostRepository postRepository;
	
	public Post findById(String id) {
		Optional <Post> obj = postRepository.findById(id);
		
		if(obj.isEmpty()) {
			throw new ObjectNotFoundException("O post não existe! Id inválido.");
		}else {
		return obj.get();		
				}
	}
}
