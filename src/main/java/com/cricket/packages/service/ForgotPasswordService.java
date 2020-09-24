package com.cricket.packages.service;


import com.cricket.packages.constants.AppConstants;
import com.cricket.packages.repository.UserDetailDao;
import com.cricket.packages.response.GenericResponse;
import com.cricket.packages.util.CommonUtil;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ForgotPasswordService {

    @Autowired
    private UserDetailDao userDetailDao;

    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger( ForgotPasswordService.class );


    public void forgetPasswordLogic(String userId, GenericResponse forgotPasswordResponse) throws Exception{

        try {
            LOGGER.info("Inside forgetPasswordLogic for user {}", userId);
            String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
            String specialCharacters = "!@#$";
            String numbers = "1234567890";
            String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
            Random random = new Random();
            char[] password = new char[8];

            for (int i = 0; i < 8; i++) {
                password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
            }
            UpdateResult updateResult = userDetailDao.updateTokenForGivenEmail(userId,String.valueOf(password));

            if (updateResult.getMatchedCount() == 1) {

                String SUBJECT="Forgot Password";
                String BODY="This is an auto response mail from Cricket League Web site in response to your query on Lost Password \n" +
                        "Password:" + String.valueOf(password) + "\n" + "\n" + "Regards," + "\n" + "Cricket League Team!!!";

                ExecutorService executorService = Executors.newFixedThreadPool(1);
                executorService.execute(()-> {
                    try {
                        CommonUtil.sendMail(userId,SUBJECT,BODY);
                    }
                    catch (Exception e){
                        LOGGER.error("Error in Sending mail(Forgot):"+e);
                    }
                });
                LOGGER.info("Mail for forget password sent successfully for user - {} ", userId);
                forgotPasswordResponse.setMessage("Temporary password sent to registered email id");
                forgotPasswordResponse.setStatus(AppConstants.SUCCESS);
            } else {
                throw new Exception("User does not exist");
            }
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        LOGGER.info("Bye Bye..");

    }
}
