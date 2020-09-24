package com.cricket.packages.service;

import com.cricket.packages.repository.UserDetailDao;
import com.cricket.packages.request.ResetPasswordRequest;
import com.cricket.packages.response.GenericResponse;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordService {

    @Autowired
    UserDetailDao userDetailDao;

    public GenericResponse resetPasswordLogic(ResetPasswordRequest resetPasswordRequest) throws Exception{

        System.out.println("Inside Reset Logic:"+resetPasswordRequest.toString());
        GenericResponse genericResponse = new GenericResponse();
        UpdateResult updateResult= userDetailDao.updatePasswordForGivenEmailAndToken(resetPasswordRequest.getUserId(),
                resetPasswordRequest.getToken(),BCrypt.hashpw(resetPasswordRequest.getNewPassword(),BCrypt.gensalt()));
        if(updateResult.getMatchedCount()==1){
            genericResponse.setMessage("Password Reset Successfully");
            genericResponse.setStatus("Success");
        }
        else{
            throw new Exception("Invalid Temporary Password");
        }
        return genericResponse;
    }
}
