package com.cricket.packages.repository;

import com.cricket.packages.exception.MongoDBException;
import com.cricket.packages.persistence.UserDetailData;
import com.mongodb.MongoException;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Repository
public class UserDetailDaoImpl implements UserDetailDao {

    @Autowired
    private MongoOperations mongoOperations;

    static final Logger LOGGER = LoggerFactory.getLogger(UserDetailDaoImpl.class);


    @Override
    public UserDetailData getUserDetailByEmail(String emailId) throws Exception{

        try {
            Query query = new Query(Criteria.where("emailId").is(emailId));
            List<UserDetailData> user = mongoOperations.find(query, UserDetailData.class, "user_details");
            if (CollectionUtils.isEmpty(user)) {
                throw new UsernameNotFoundException("User not found");
            }
            return user.get(0);
        }
        catch (MongoException e) {
            LOGGER.error("Exception in getting userDetail:"+e);
            throw new MongoDBException("Exception in getting userDetail");
        } catch (Exception e) {
            LOGGER.error("Exception in getting userDetail:"+e);
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public void saveUserData(UserDetailData userDetailData) throws Exception{
        try {
            mongoOperations.save(userDetailData);
        }
        catch (DuplicateKeyException ex){
            throw new Exception("User Already Exists");

        }
        catch (MongoException e) {
            LOGGER.error("Exception in saving userDetail:"+e);
            throw new MongoDBException("Failed save documents");
        } catch (Exception e) {
            LOGGER.error("Exception in saving userDetail:"+e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UpdateResult updatePasswordForGivenEmailAndToken(String emailId , String token, String password) throws Exception{


        try {
            Update update = new Update();
            update.set("password", password);

            Query query = new Query(Criteria.where("emailId").is(emailId).and("token").is(token));

            UpdateResult updateResult = mongoOperations.updateFirst(query, update, "user_details");
            return updateResult;
        }
        catch (MongoException e) {
            LOGGER.error("Exception in updating Password:"+e);
            throw new MongoDBException("Failed in updating password");
        } catch (Exception e) {
            LOGGER.error("Exception in updating Password:"+e);
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UpdateResult updateTokenForGivenEmail(String emailId , String token) throws Exception{

        try {
            Update update = new Update();
            update.set("token", token);

            Query query = new Query(Criteria.where("emailId").is(emailId));

            UpdateResult updateResult = mongoOperations.updateFirst(query, update, "user_details");
            return updateResult;
        }
        catch (MongoException e) {
            LOGGER.error("Exception in updating token:"+e);
            throw new MongoDBException("Failed in updating token");
        } catch (Exception e) {
            LOGGER.error("Exception in updating token:"+e);
            throw new Exception(e.getMessage());
        }
    }
    @Override
    public UserDetailData getUserDetailByEmailAndVerified(String emailId,boolean isVerified){
            Query query = new Query(Criteria.where("emailId").is(emailId).and("isVerified").is(true));
            List<UserDetailData> user = mongoOperations.find(query, UserDetailData.class, "user_details");

            if (CollectionUtils.isEmpty(user)) {
                throw new UsernameNotFoundException("User not found");
            }
            return user.get(0);


    }


}
