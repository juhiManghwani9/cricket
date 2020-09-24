package com.cricket.packages.service;

import com.cricket.packages.persistence.UserDetailData;
import com.cricket.packages.repository.UserDetailDao;
import com.cricket.packages.response.RegistrationResponse;
import com.cricket.packages.util.CommonUtil;
import com.fasterxml.uuid.Generators;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class RegistrationService {


    @Autowired
    private UserDetailDao userDetailDao;


    static final Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);


    public RegistrationResponse saveUserData(UserDetailData userDetailData) throws Exception{

        try {

            RegistrationResponse userDetailResponse=new RegistrationResponse();
            //Using BCrypt to hash the password and store in DB
            userDetailData.setPassword(BCrypt.hashpw(userDetailData.getPassword(), BCrypt.gensalt()));
            userDetailData.setVerified(false);
            //Time based Guid(always unique)
            String token=Generators.timeBasedGenerator().generate().toString();
            userDetailData.setToken(token);


            String SUBJECT="Activation Link";
            String BODY = "Please click on the below link to activate your account\n" +"\n"+
                    "Activation Link: http://ec2-3-7-254-173.ap-south-1.compute.amazonaws.com:7070/validate/"+token  + "\n" + "\n" + "Regards," + "\n" + "Cricket League Team!!!";



            userDetailDao.saveUserData(userDetailData);

            ExecutorService executorService = Executors.newFixedThreadPool(1);
            executorService.execute(()->{
                try {
                    CommonUtil.sendMail(userDetailData.getEmailId(),SUBJECT,BODY);
                }
                catch (Exception e){
                    LOGGER.error("Error in Sending mail(Activation):"+e);
                }
            });
            userDetailResponse.setMessage("Activation Link Sent to Registered Email Id");
            userDetailResponse.setStatus("Success");
            userDetailResponse.setName(userDetailData.getFirstName());
            return userDetailResponse;


        }
        catch(Exception e){
            throw new Exception(e.getMessage());
        }


    }
}
