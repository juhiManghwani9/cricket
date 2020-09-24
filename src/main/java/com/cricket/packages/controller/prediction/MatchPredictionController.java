package com.cricket.packages.controller.prediction;

import com.cricket.packages.exception.InvalidPredictDataException;
import com.cricket.packages.request.PredictRequest;
import com.cricket.packages.response.GenericResponse;
import com.cricket.packages.service.MatchPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/match-predict")
public class MatchPredictionController {

    @Autowired
    MatchPredictionService matchPredictionService;

    @PostMapping
    ResponseEntity<GenericResponse> matchPrediction(@Valid @RequestBody PredictRequest predictRequest, BindingResult bindingResult) throws Exception
    {

        if (bindingResult.hasErrors()) {
            String message = bindingResult.getAllErrors().get(0).getDefaultMessage();
            throw new InvalidPredictDataException(message);
        }
            try {
                GenericResponse predictionResponse = matchPredictionService.predictedData(predictRequest);

                return new ResponseEntity<>(predictionResponse, HttpStatus.CREATED);
            }
            catch (Exception e)
            {
                throw new Exception(e.getMessage());
            }
    }

}
