package com.example.backend.controller;


import com.example.backend.model.Account;
import com.example.backend.model.Campaign;
import com.example.backend.model.Town;
import com.example.backend.service.CampaignService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/campaigns")
@CrossOrigin("http://localhost:3000/")
public class CampaignController {
    @Autowired
    private CampaignService campaignService;
    @Autowired
    private Account account;
    @GetMapping
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long id) {
        return campaignService.getCampaignById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Campaign createCampaign(@Valid @RequestBody Campaign campaign) {
        account.setBalance(account.getBalance() - campaign.getCampaignFund());
        return campaignService.saveCampaign(campaign);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable Long id, @Valid  @RequestBody Campaign updatedCampaign) {
        return campaignService.getCampaignById(id)
                .map(existingCampaign -> {
                    double fundDifference =  existingCampaign.getCampaignFund() - updatedCampaign.getCampaignFund();
                    account.setBalance(account.getBalance() + fundDifference);
                    existingCampaign.setCampaignName(updatedCampaign.getCampaignName());
                    existingCampaign.setKeywords(updatedCampaign.getKeywords());
                    existingCampaign.setBidAmount(updatedCampaign.getBidAmount());
                    existingCampaign.setCampaignFund(updatedCampaign.getCampaignFund());
                    existingCampaign.setStatus(updatedCampaign.getStatus());
                    existingCampaign.setTown(updatedCampaign.getTown());
                    existingCampaign.setRadius(updatedCampaign.getRadius());
                    return ResponseEntity.ok(campaignService.saveCampaign(existingCampaign));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCampaign(@PathVariable Long id) {
        campaignService.deleteCampaign(id);
        return ResponseEntity.noContent().build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}