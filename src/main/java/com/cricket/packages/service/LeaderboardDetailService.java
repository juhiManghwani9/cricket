package com.cricket.packages.service;

import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.exception.LeaderboardException;
import com.cricket.packages.model.AggregatedLeaderBoard;
import com.cricket.packages.repository.UserMatchDetailDao;
import com.cricket.packages.response.LeaderBoardResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class LeaderboardDetailService {


    @Autowired
    private UserMatchDetailDao userMatchDetailDao;

    private static final Logger LOGGER = LoggerFactory.getLogger( LeaderboardDetailService.class );


    public LeaderBoardResponse globalLeaderboardData(String type,String value,int offset,int size) throws LeaderboardException {

        LeaderBoardResponse LeaderBoardResponse = new LeaderBoardResponse();

        LOGGER.info("Inside Leader board data service");
        Criteria criteria = new Criteria();
        if(AppConstants.TOURNAMENT.equalsIgnoreCase(type)) {
            LOGGER.info("Tournament data");
            criteria = Criteria.where("tournamentId").is(Integer.parseInt(value));
        }
        else{
            LOGGER.info("Month data");
            String requestedDate[] = value.split("-");
            int year=Integer.parseInt(requestedDate[1]);
            int month=Integer.parseInt(requestedDate[0]);
            Calendar startDate=Calendar.getInstance();
            startDate.set(year,month,1);
            startDate.set(Calendar.HOUR_OF_DAY,0);
            startDate.set(Calendar.MINUTE,0);
            startDate.set(Calendar.SECOND,0);

            Calendar endDate=Calendar.getInstance();
            int lastDayOfMonth=startDate.getActualMaximum(Calendar.DATE);
            endDate.set(year,month,lastDayOfMonth);
            endDate.set(Calendar.HOUR_OF_DAY,23);
            endDate.set(Calendar.MINUTE,59);
            endDate.set(Calendar.SECOND,59);

            LOGGER.info(startDate.getTime()+" "+endDate.getTime());

            criteria=new Criteria().andOperator(
                    Criteria.where("date").gte(new Date(startDate.getTimeInMillis())),
                    Criteria.where("date").lte(new Date(endDate.getTimeInMillis())));
        }
        try {
            List<AggregatedLeaderBoard> aggregatedLeaderBoardList = userMatchDetailDao.globalLeaderboardData(criteria, offset, size);
            LeaderBoardResponse.setStatus(AppConstants.SUCCESS);
            LeaderBoardResponse.setMessage("Data Fetched successfully");

            if (!CollectionUtils.isEmpty(aggregatedLeaderBoardList)) {
                LeaderBoardResponse.setAggregatedLeaderBoardList(aggregatedLeaderBoardList);
            }
            else LeaderBoardResponse.setAggregatedLeaderBoardList(new ArrayList<>());
            return LeaderBoardResponse;
        } catch (Exception e) {
            LOGGER.error("Failed to retrieve global leaderboard data"+ e);
            throw new LeaderboardException("Failed to retrieve global leaderboard data"+ e.getLocalizedMessage());
        }
    }
}