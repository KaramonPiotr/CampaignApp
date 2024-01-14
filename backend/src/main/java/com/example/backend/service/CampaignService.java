package com.example.backend.service;

import com.example.backend.exception.CampaignNotFoundException;
import com.example.backend.model.Campaign;
import com.example.backend.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {
    @Autowired
    private CampaignRepository campaignRepository;


    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }
    public Optional<Campaign> getCampaignById(Long id) {
        Campaign  campaign = campaignRepository.findById(id).orElseThrow(()-> new CampaignNotFoundException(id));
        return Optional.ofNullable(campaign);
    }
    public Campaign saveCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }

}
