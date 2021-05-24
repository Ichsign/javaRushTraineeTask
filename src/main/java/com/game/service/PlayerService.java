package com.game.service;

import com.game.controller.request.CreatePlayerRequest;
import com.game.controller.request.FilterRequest;
import com.game.controller.request.UpdatePlayerRequest;
import com.game.entity.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {

    Optional<Player> getPlayer(Long id);

    List<Player> getAllPlayers(FilterRequest request, Integer pageSize, Integer pageNumber);

    boolean deleteById(Long id);

    void createPlayer(CreatePlayerRequest player);

    long getCount(FilterRequest request);

    Optional<Player> updateById(Long id, UpdatePlayerRequest request);
}
