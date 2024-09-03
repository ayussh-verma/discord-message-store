package com.ayusshverma.discord_message_store.controllers;

import com.ayusshverma.discord_message_store.models.User;
import com.ayusshverma.discord_message_store.repos.UserRepo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "User", description = "The User API")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepo.findAll());
    }

    @Operation(summary = "Get a specific user", description = "Returns a single user", tags = { "User" })
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "User found", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
        }
    )
    @GetMapping("/{id}")
    public ResponseEntity<User> getASpecificUser(@PathVariable String id) {
        Optional<User> user = userRepo.findById(id);
        return user.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        int deletionCount = userRepo.deleteByIdReturningCount(id);
        return deletionCount > 0 ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> editUserDetails(@PathVariable String id, @RequestBody User user) {
        Optional<User> currentUser = userRepo.findById(id);
        if (currentUser.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User updatedUser = userRepo.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user, UriComponentsBuilder uriComponentsBuilder) {
        Optional<User> currentUser = userRepo.findById(user.id);
        if (!currentUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        User createdUser = userRepo.save(user);
        URI location = uriComponentsBuilder.path("/users/{id}").buildAndExpand(createdUser.id).toUri();
        return ResponseEntity.created(location).body(createdUser);
    }
}