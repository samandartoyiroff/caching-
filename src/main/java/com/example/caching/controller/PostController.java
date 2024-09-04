package com.example.caching.controller;


import com.example.caching.entity.Post;
import com.example.caching.repo.PostRepository;
import com.example.caching.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostRepository postRepository;
    private final PostService postService;


    @GetMapping("/{id}")
    public Post getPost(@PathVariable Integer id) {

        return postService.getOne(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        postService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody Post post) {
        postService.update(id, post);
    }


}
