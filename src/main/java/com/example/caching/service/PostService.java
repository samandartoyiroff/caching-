package com.example.caching.service;

import com.example.caching.entity.Post;
import com.example.caching.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Cacheable(value = "posts", key = "#id")
    @SneakyThrows
    public Post getOne(Integer id) {
        Post post = postRepository.findById(id).orElse(null);
        Thread.sleep(3000);
        return post;
    }
    @CacheEvict(value = "posts",key = "#id")
    public void delete(Integer id) {
        postRepository.deleteById(id);
    }

    @CachePut(value = "posts", key = "#id")
    public Post update(Integer id, Post post) {

        Post post1 = postRepository.findById(id).orElse(null);
        post1.setTitle(post.getTitle());
        post1.setBody(post.getBody());
        return postRepository.save(post1);

    }
//    @Scheduled(initialDelay = 20000, fixedDelay = 2000)
//    public void task(){
//        cache.clear();
//        System.out.println("Clear !!!");
//    }
}
