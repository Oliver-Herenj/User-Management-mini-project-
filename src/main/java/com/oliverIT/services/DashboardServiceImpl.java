package com.oliverIT.services;

import com.oliverIT.dto.QuoteApiResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DashboardServiceImpl implements DashboardService{

    private String quoteApiURL = "https://dummyjson.com/quotes/random";

    @Override
    public QuoteApiResponseDTO getQuote() {
        RestTemplate restTemplate = new RestTemplate();

        //Send HTTP get request and store response into the QuoteApiResponseDTO object
        ResponseEntity<QuoteApiResponseDTO> forEntity = restTemplate.getForEntity(quoteApiURL, QuoteApiResponseDTO.class);
        
        return forEntity.getBody();

    }



}


