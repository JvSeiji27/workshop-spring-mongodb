package com.joaovitorseiji.worshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaovitorseiji.worshopmongo.domain.Post;
import com.joaovitorseiji.worshopmongo.domain.User;
import com.joaovitorseiji.worshopmongo.dto.AuthorDTO;
import com.joaovitorseiji.worshopmongo.dto.CommentDTO;
import com.joaovitorseiji.worshopmongo.repository.PostRepository;
import com.joaovitorseiji.worshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");		
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
	
	
		Post post1 = new Post(null, sdf.parse("21/08/2024"), "Partiu Viagem", "Vou viajar para SP", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("24/08/2024"), "Bom Dia!", "Acordei feliz hoje!", new AuthorDTO(maria));
	
		CommentDTO comment1 = new CommentDTO("Boa Viagem mano!", sdf.parse("27/08/2024"), new AuthorDTO(alex));
		CommentDTO comment2 = new CommentDTO("Aproveite", sdf.parse("26/08/2024"), new AuthorDTO(bob));
		CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("28/08/2024"), new AuthorDTO(alex));
		
		//post1.setComments(Arrays.asList(comment1,comment2)); se fizesse dessa forma substituo a lista atual
		//No addAll eu só adiciono
		post1.getComments().addAll(Arrays.asList(comment1,comment2));
		post2.getComments().addAll(Arrays.asList(comment3));
		
		
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
	}
	
	
	
	
	
}
