package com.datastax.demo.stargate.chevrons;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.PATCH;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datastax.workshop.todo.Todo;
import com.datastax.workshop.todo.TodoEntity;
import com.datastax.workshop.todo.TodoRepositoryCassandra;
import com.datastax.workshop.todo.TodoRestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/chrevrons")
@CrossOrigin(
        methods = {PUT, POST, GET, OPTIONS, DELETE, PATCH},
        maxAge = 3600,
        allowedHeaders = {"x-requested-with", "origin", "content-type", "accept"},
        origins = "*"
      )
@Tag(name = "Api Chevrons", description = "Chevron for the stargate")
public class ChevronRestController {
    
    private ChevronRepository repo;
    
    public ChevronRestController(ChevronRepository chevronRepo) {
        this.repo = chevronRepo;
    }
    
    @GetMapping
    public Stream<Chevron> findAll(HttpServletRequest req) {
        return repo.findAll().stream();
    }
    
    @GetMapping("/{area}")
    public Stream<Chevron> findByArea(HttpServletRequest req, @PathVariable(value = "uid") String area) {
        return repo.findById(id)
    }
    
    /*
    @GetMapping("/{area}")
    public ResponseEntity<Chevron> findById(HttpServletRequest req, @PathVariable(value = "uid") String uid) {
        Optional<TodoEntity> e = repo.findById(UUID.fromString(uid));
        if (e.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapAsTodo(e.get()).setUrl(req.getRequestURL().toString()));
    }*/

}
