package org.springframework.social.facebook.connect;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.social.facebook.api.PageOperations;
import org.springframework.social.facebook.api.ads.AccountGroupOperations;
import org.springframework.social.facebook.api.ads.AccountOperations;
import org.springframework.social.facebook.api.ads.AdGroupOperations;
import org.springframework.social.facebook.api.ads.CampaignOperations;
import org.springframework.social.facebook.api.ads.CreativeOperations;
import org.springframework.social.facebook.api.ads.impl.FacebookAdsTemplate;
import org.springframework.web.client.RestClientException;

/**
 * @author Karthick Sankarachary
 */
public class FacebookAdsTemplateTest {
	private String accessToken = "<YOUR ACCESS TOKEN>";
	private String accountId = "<YOUR ADS ACCOUNT ID>";
	private String campaignId = "<YOUR CAMPAING ID>";
	private String creativeId = "<YOUR CREATIVE ID>";
	private String accountGroupId = "<YOUR ACCOUNT GROUP ID>";
	private String pageId = "DisneyUniverse";

	private FacebookAdsTemplate template;

	private ObjectMapper mapper;

	@Before
	public void setUp() {
		this.template = new FacebookAdsTemplate(accessToken);
		this.mapper = new ObjectMapper();
		this.mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
	}

	@Test
        @Ignore
	public void testAccountOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		AccountOperations accountOps = template.accountOperations();
		printValue(accountOps.getAccount(accountId));
		printValue(accountOps.getAccountStats(accountId));
	}

	@Test(expected = RestClientException.class)
        @Ignore
	public void testAccountGroupOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		AccountGroupOperations accountGroupOps = template
				.accountGroupOperations();
		printValue(accountGroupOps.getAccountGroup(accountGroupId));
	}

	@Test
        @Ignore
	public void testCampaignOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		CampaignOperations campaignOps = template.campaignOperations();
		printValue(campaignOps.getCampaigns(accountId));
		printValue(campaignOps.getCampaign(campaignId));
	}

	@Test
        @Ignore
	public void testCreativeOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		CreativeOperations creativeOps = template.creativeOperations();
		printValue(creativeOps.getCreatives(accountId));
		printValue(creativeOps.getCreative(creativeId));
	}

	@Test
        @Ignore
	public void testAdGroupOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		AdGroupOperations adGroupOperations = template.adGroupOperations();
		printValue(adGroupOperations.getAdGroups(accountId));
	}
	
	@Test
        @Ignore
	public void testPageOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		PageOperations pageOps = template.pageOperations();
		printValue(pageOps.getPage(pageId));
		printValue(pageOps.getAccounts());
	}

	@Test
        @Ignore
	public void testStatsOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		AccountOperations accountOps = template.accountOperations();
		printValue(accountOps.getReachEstimate(accountId, "USD", null));
	}

	private void printValue(Object object) throws JsonGenerationException,
			JsonMappingException, IOException {
		StringWriter sw = new StringWriter();
		mapper.writeValue(sw, object);
		sw.close();
		System.out.println("--------------------------------------------------");
		System.out.println(object.getClass().getCanonicalName() + ":");
		System.out.println(sw.getBuffer().toString());
		System.out.println("--------------------------------------------------");
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(String campaignId) {
		this.campaignId = campaignId;
	}

	public String getCreativeId() {
		return creativeId;
	}

	public void setCreativeId(String creativeId) {
		this.creativeId = creativeId;
	}

	public String getAccountGroupId() {
		return accountGroupId;
	}

	public void setAccountGroupId(String accountGroupId) {
		this.accountGroupId = accountGroupId;
	}

}
