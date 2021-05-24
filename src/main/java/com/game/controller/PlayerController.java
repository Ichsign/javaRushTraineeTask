package com.game.controller;

import com.game.controller.request.CreatePlayerRequest;
import com.game.controller.request.FilterRequest;
import com.game.controller.request.UpdatePlayerRequest;
import com.game.entity.Player;
import com.game.service.PlayerService;
import com.game.util.CreatePlayerRequestValidator;
import com.game.util.UpdatePlayerRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public ResponseEntity<List<Player>> getPlayers(FilterRequest request, @RequestParam @Nullable Integer pageSize, @RequestParam @Nullable Integer pageNumber) {
        List<Player> players = playerService.getAllPlayers(request, pageSize, pageNumber);
        return ResponseEntity.ok(players);
    }


    @GetMapping("/count")
    public ResponseEntity<Long> getPlayersCount(FilterRequest request) {
        return ResponseEntity.ok(playerService.getCount(request));
    }

    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody CreatePlayerRequest request, @Autowired CreatePlayerRequestValidator validator) {
        if (!validator.isValid(request)) {
            return ResponseEntity.badRequest().build();
        }
        playerService.createPlayer(request);
        return ResponseEntity.ok(null);
    }

    //fixme: our users lost and internal error happens
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        Optional<Player> player = playerService.getPlayer(id);
        return player.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PostMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody UpdatePlayerRequest player, @Autowired UpdatePlayerRequestValidator validator) {
        if (id <= 0 || !validator.isValid(player)) {
            return ResponseEntity.badRequest().build();
        }
        return playerService.updateById(id, player).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //TODO: id checker and 400 error if invalid
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        if (id <= 0) {
            return ResponseEntity.badRequest().build();
        }
        if (playerService.deleteById(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
