package com.game.service;

import com.game.controller.request.CreatePlayerRequest;
import com.game.controller.request.FilterRequest;
import com.game.controller.request.UpdatePlayerRequest;
import com.game.entity.Player;
import com.game.repository.PlayerRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.game.service.Specifications.playerSpecification;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    @Override
    public Optional<Player> getPlayer(Long id) {
        return playerRepository.findById(id);
    }


    //TODO: pagination filter
    @Override
    public List<Player> getAllPlayers(FilterRequest request, Integer pageSize, Integer pageNumber) {
        Sort sort = Sort.unsorted();
        if (request.getOrder() != null) {
            sort = Sort.by(request.getOrder().getFieldName());
        }
//        if (pageNumber != null && pageSize != null) {
//            Pageable requestedPage = PageRequest.of(pageNumber, pageSize, sort);
//            return playerRepository.findAll(playerSpecification(request), requestedPage).getContent();
//        } else {
//            return playerRepository.findAll(playerSpecification(request), sort);
//        }
        int size = pageSize != null ? pageSize : 3;
        int number = pageNumber != null ? pageNumber : 0;
        Pageable requestedPage = PageRequest.of(number, size, sort);
        return playerRepository.findAll(playerSpecification(request), requestedPage).getContent();
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            playerRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    @Override
    public void createPlayer(CreatePlayerRequest request) {
        Player player = new Player();
        player.setBanned(request.isBanned());
        player.setBirthday(new Date(request.getBirthday()));
        player.setExperience(request.getExperience());
        player.setName(request.getName());
        player.setProfession(request.getProfession().name());
        player.setLevel(countLevel(request.getExperience()));
        player.setUntilNextLevel(leftUntilNextLevel(player.getLevel(), request.getExperience()));
        player.setRace(request.getRace().name());
        player.setTitle(request.getTitle());
        playerRepository.save(player);
    }

    @Override
    public long getCount(FilterRequest request) {
        return playerRepository.count(playerSpecification(request));
    }

    @Override
    public Optional<Player> updateById(Long id, UpdatePlayerRequest request) {
        Optional<Player> player = playerRepository.findById(id);
        return player.map((p) -> {
            if (request.getTitle() != null) {
                p.setTitle(request.getTitle());
            }
            if (request.getName() != null) {
                p.setName(request.getName());
            }
            if (request.getExperience() != null) {
                p.setExperience(request.getExperience());
                p.setLevel(countLevel(request.getExperience()));
                p.setUntilNextLevel(leftUntilNextLevel(p.getLevel(), request.getExperience()));
            }
            if (request.getBirthday() != null) {
                p.setBirthday(new Date(request.getBirthday()));
            }
            if (request.getProfession() != null) {
                p.setProfession(request.getProfession().name());
            }
            if (request.getRace() != null) {
                p.setRace(request.getRace().name());
            }
            if (request.isBanned() != null) {
                p.setBanned(request.isBanned());
            }
            return playerRepository.save(p);
        });
    }

    private Integer countLevel(Integer experience) {
        return (Integer) (int) ((Math.sqrt(2500 + 200 * experience) - 50) / 100);
    }

    private Integer leftUntilNextLevel(Integer lvl, Integer exp) {
        return 50 * (lvl + 1) * (lvl + 2) - exp;
    }
}