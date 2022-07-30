package com.Suresh.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.Suresh.Models.Item;
import com.Suresh.Repository.ItemRepository;

@RestController
public class FirstController {

	@Autowired
	private ItemRepository repository;
	
	
	@RequestMapping("/all")
	public ResponseEntity<Map<String,Item>> getAllItems() {
		Map<String, Item> items = repository.getAll();
		return new ResponseEntity<>(items,HttpStatus.OK);
	}
	
	
	   @PostMapping(value = "/addItem",consumes = {"application/json"},produces = {"application/json"})
	    public ResponseEntity<Item> addItem(@RequestBody Item item,UriComponentsBuilder builder){
		   repository.create(item);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(builder.path("/addItem/{id}").buildAndExpand(item.getId()).toUri());
	        return new ResponseEntity<Item>(headers, HttpStatus.CREATED);
	    }
	
	
	
	
	
	
	
	
}
