package com.example.backend;

import com.example.backend.controller.CampaignController;
import com.example.backend.exception.CampaignNotFoundException;
import com.example.backend.model.Account;
import com.example.backend.model.Campaign;
import com.example.backend.repository.CampaignRepository;
import com.example.backend.service.CampaignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

}
class CampaignControllerTest {

	@Mock
	private CampaignService campaignService;

	@Mock
	private Account account;

	@InjectMocks
	private CampaignController campaignController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllCampaigns() {
		List<Campaign> campaigns = new ArrayList<>();
		when(campaignService.getAllCampaigns()).thenReturn(campaigns);

		List<Campaign> result = campaignController.getAllCampaigns();

		assertEquals(campaigns, result);
	}

	@Test
	void getCampaignById() {
		Long campaignId = 1L;
		Campaign campaign = new Campaign();
		when(campaignService.getCampaignById(campaignId)).thenReturn(Optional.of(campaign));

		ResponseEntity<Campaign> result = campaignController.getCampaignById(campaignId);

		assertEquals(ResponseEntity.ok(campaign), result);
	}

	@Test
	void getCampaignByIdNotFound() {
		Long campaignId = 1L;
		when(campaignService.getCampaignById(campaignId)).thenReturn(Optional.empty());

		ResponseEntity<Campaign> result = campaignController.getCampaignById(campaignId);

		assertEquals(ResponseEntity.notFound().build(), result);
	}



	@Test
	void updateCampaignNotFound() {
		Long campaignId = 1L;
		when(campaignService.getCampaignById(campaignId)).thenReturn(Optional.empty());

		ResponseEntity<Campaign> result = campaignController.updateCampaign(campaignId, new Campaign());

		assertEquals(ResponseEntity.notFound().build(), result);
	}

}
class CampaignServiceTest {

	@Mock
	private CampaignRepository campaignRepository;

	@InjectMocks
	private CampaignService campaignService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void getAllCampaigns() {
		List<Campaign> campaigns = new ArrayList<>();
		when(campaignRepository.findAll()).thenReturn(campaigns);

		List<Campaign> result = campaignService.getAllCampaigns();

		assertEquals(campaigns, result);
	}

	@Test
	void getCampaignById() {
		Long campaignId = 1L;
		Campaign campaign = new Campaign();
		when(campaignRepository.findById(campaignId)).thenReturn(Optional.of(campaign));

		Optional<Campaign> result = campaignService.getCampaignById(campaignId);

		assertEquals(Optional.of(campaign), result);
	}

	@Test
	void getCampaignByIdNotFound() {
		Long campaignId = 1L;
		when(campaignRepository.findById(campaignId)).thenReturn(Optional.empty());

		assertThrows(CampaignNotFoundException.class, () -> campaignService.getCampaignById(campaignId));
	}

	@Test
	void saveCampaign() {
		Campaign campaign = new Campaign();

		when(campaignRepository.save(campaign)).thenReturn(campaign);

		Campaign result = campaignService.saveCampaign(campaign);

		assertEquals(campaign, result);
	}


}
