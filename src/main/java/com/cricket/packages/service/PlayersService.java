package com.cricket.packages.service;


import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.exception.PlayersFetchException;
import com.cricket.packages.model.Players;
import com.cricket.packages.response.PlayersResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayersService {

    private static final Logger LOGGER = LoggerFactory.getLogger( PlayersService.class );

    public PlayersResponse getPlayersList(String team1,String team2) throws PlayersFetchException {
        List<String> finalPlayersList;
        PlayersResponse playersResponse = new PlayersResponse();
        try {
           // ClassLoader classLoader = getClass().getClassLoader();
            //File teamPlayers = new File(classLoader.getResource("Players.json").getFile());

            File teamPlayers = new File("Players.json");
            LOGGER.info("Loaded all the players for teams {}, {}", team1, team2);

            ObjectMapper objectMapper = new ObjectMapper();
            List<Players> playersList = objectMapper.readValue(teamPlayers, new TypeReference<List<Players>>() {
            });

            finalPlayersList=playersList.stream().
                    filter(p->p.getTeam_name().equalsIgnoreCase(team1)|| p.getTeam_name().equalsIgnoreCase(team2)).
                    map(Players::getPlayers).collect(ArrayList::new, List::addAll, List::addAll);

            playersResponse.setStatus(AppConstants.SUCCESS);
            playersResponse.setMessage("Players fetched successfully");
            playersResponse.setPlayerList(finalPlayersList);

        } catch(Exception e) {
            LOGGER.error("Failed to fetch players from file" +e.getLocalizedMessage());
            throw new PlayersFetchException("Failed to fetch players from file "+e.getLocalizedMessage());
        }
            return playersResponse;
    }
}
