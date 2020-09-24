package com.cricket.packages.repository;

import com.cricket.packages.persistence.UserDetailData;
import com.mongodb.client.result.UpdateResult;

public interface UserDetailDao {


    public UserDetailData getUserDetailByEmail(String emailId) throws Exception;
    public UserDetailData getUserDetailByEmailAndVerified(String emailId,boolean isVerified);
    public void saveUserData(UserDetailData userDetailData) throws Exception;
    public UpdateResult updatePasswordForGivenEmailAndToken(String emailId , String token ,String password) throws Exception;
    public UpdateResult updateTokenForGivenEmail(String emailId , String token) throws Exception;
}
