package com.Suresh.Repository;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.Suresh.Models.Item;

@Repository
public class ItemRepository {

	private static Logger logger = LoggerFactory.getLogger(ItemRepository.class);

	private static final String KEY = "ITEM";

	/*
	 * @Autowired private RedisTemplate<String, Item> redisTemplate;
	 */

	private HashOperations<String, String, Item> hashOperations;

	ItemRepository(RedisTemplate<String, Item> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}

	public void create(Item item) {
		hashOperations.put(KEY, item.getId(), item);
		logger.info(String.format("Item with ID %s saved", item.getId()));
	}

	public Item get(String itemId) {
		return (Item) hashOperations.get("ITEM", itemId);
	}

	public Map<String, Item> getAll() {
		return hashOperations.entries("ITEM");
	}

	public void update(Item item) {
		hashOperations.put(KEY, item.getId(), item);
		logger.info(String.format("ITEM with ID %s updated", item.getId()));
	}

	public void delete(String itemId) {
		hashOperations.delete(KEY, itemId);
		logger.info(String.format("ITEM with ID %s deleted", itemId));
	}

}
