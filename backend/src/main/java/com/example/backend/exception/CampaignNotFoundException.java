package com.example.backend.exception;



public class CampaignNotFoundException extends RuntimeException {
    public CampaignNotFoundException(Long id){
        super("Could not found the campaign with id " + id);
    }
}
