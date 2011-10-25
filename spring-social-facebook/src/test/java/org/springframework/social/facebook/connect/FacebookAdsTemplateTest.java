package org.springframework.social.facebook.connect;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.social.facebook.api.Identifier;
import org.springframework.social.facebook.api.PageOperations;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.ads.AccountGroupOperations;
import org.springframework.social.facebook.api.ads.AccountOperations;
import org.springframework.social.facebook.api.ads.AdCampaign;
import org.springframework.social.facebook.api.ads.AdCreative;
import org.springframework.social.facebook.api.ads.AdGroup;
import org.springframework.social.facebook.api.ads.AdGroupOperations;
import org.springframework.social.facebook.api.ads.CampaignOperations;
import org.springframework.social.facebook.api.ads.CreativeOperations;
import org.springframework.social.facebook.api.ads.ValidKeyword;
import org.springframework.social.facebook.api.ads.impl.FacebookAdsTemplate;
import org.springframework.web.client.RestClientException;

/**
 * @author Karthick Sankarachary
 */
public class FacebookAdsTemplateTest {
        private String accessToken = "<YOUR ACCESS TOKEN>";
        private String accountId = "<YOUR ACCOUNT ID>";
        private String campaignId = "<YOUR CAMPAIGN ID>";
        private String creativeId = "<YOUR CREATIVE ID>";
        private String accountGroupId = "<YOUR ACCOUNT GROUP ID>";
	private String pageId = "cnn";

	private FacebookAdsTemplate template;

	private ObjectMapper mapper;

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

	@Before
	public void setUp() {
		this.template = new FacebookAdsTemplate(accessToken);
		this.mapper = new ObjectMapper();
		this.mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
	}

	@Test
	public void testAccountOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		AccountOperations accountOps = template.accountOperations();

		assertPrintable(accountOps.getAccount(accountId));

		assertPrintable(accountOps.getAccountStats(accountId));
	}

	@Ignore
	@Test(expected = RestClientException.class)
	public void testAccountGroupOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		AccountGroupOperations accountGroupOps = template
				.accountGroupOperations();
		 assertPrintable(accountGroupOps.getAccountGroup(accountGroupId));
	}

	@Test
	public void testCampaignOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		CampaignOperations campaignOps = template.campaignOperations();

		List<AdCampaign> campaigns = campaignOps.getCampaigns(accountId);
		assertPrintable(campaigns);
		assertListOf(campaigns, AdCampaign.class);

		if (campaigns.size() > 0) {
			AdCampaign campaign = campaigns.get(0);
			campaign = campaignOps
					.getCampaign(String.valueOf(campaign.getId()));
			assertPrintable(campaign);
		}
	}

	@Test
	public void testCreativeOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		CreativeOperations creativeOps = template.creativeOperations();

		List<AdCreative> creatives = creativeOps.getCreatives(accountId);
		assertPrintable(creatives);
		assertListOf(creatives, AdCreative.class);

		if (creatives.size() > 0) {
			AdCreative creative = creatives.get(0);
			creative = creativeOps.getCreative(String.valueOf(creative
					.getCreativeId()));
			assertPrintable(creative);
		}
	}

	@Test
	public void testAdGroupOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		AdGroupOperations adGroupOperations = template.adGroupOperations();

		List<AdGroup> adGroups = adGroupOperations.getAdGroups(accountId);
		assertPrintable(adGroups);
		assertListOf(adGroups, AdGroup.class);

		if (adGroups.size() > 0) {
			AdGroup adGroup = adGroups.get(0);
			adGroup = adGroupOperations.getAdGroup(String.valueOf(adGroup
					.getAdId()));
			assertPrintable(adGroup);
		}
	}

	@Test
	public void testPageOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		PageOperations pageOps = template.pageOperations();

		assertPrintable(pageOps.getPage(pageId));

		assertPrintable(pageOps.getAccounts());

		List<Post> posts = pageOps.getPosts(pageId);
		assertPrintable(posts);
		assertListOf(posts, Post.class);
	}

	@Test
	public void testStatsOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		AccountOperations accountOps = template.accountOperations();

		assertPrintable(accountOps.getReachEstimate(accountId, "USD", null));

		assertPrintable(accountOps.getConnectionObjects(accountId, Map.class));
	}

	@Test
	public void testSearchOperations() throws JsonGenerationException,
			JsonMappingException, IOException {
		org.springframework.social.facebook.api.ads.SearchOperations searchOps = template
				.searchOperations();
		List<Identifier> identifiers = searchOps.search("adkeyword", "string",
				Identifier.class);
		assertPrintable(identifiers);
		assertListOf(identifiers, Identifier.class);

		identifiers = searchOps.search("adobjectbyurl", "www.xa.net",
				Identifier.class);
		assertPrintable(identifiers);
		assertListOf(identifiers, Identifier.class);

		List<ValidKeyword> validKeywords = searchOps.getValidKeywords("xa",
				"facebook");
		assertPrintable(validKeywords);
		assertListOf(validKeywords, ValidKeyword.class);

		identifiers = searchOps.getKeywordAutocomplete("xa");
		assertPrintable(identifiers);
		assertListOf(identifiers, Identifier.class);

		identifiers = searchOps.getKeywordSuggestions("xa", "facebook");
		assertPrintable(identifiers);
		assertListOf(identifiers, Identifier.class);

		assertPrintable(searchOps.getIdByUrl("www.xa.net"));
	}

	private void assertPrintable(Object object) throws JsonGenerationException,
			JsonMappingException, IOException {
		Assert.assertNotNull(object);
		StringWriter sw = new StringWriter();
		mapper.writeValue(sw, object);
		sw.close();
		System.out
				.println("--------------------------------------------------");
		System.out.println(object.getClass().getCanonicalName() + ":");
		System.out.println(sw.getBuffer().toString());
		System.out
				.println("--------------------------------------------------");
	}

	private <T> void assertListOf(Collection<T> collection, Class<T> elementType) {
		Assert.assertTrue("The collection " + collection + " is not a list",
				collection instanceof List);
		Assert.assertTrue("The list is empty", collection.size() > 0);
		T element = (T) ((List<T>) collection).get(0);
		Assert.assertTrue("The element type is not an instance of "
				+ elementType, elementType.isAssignableFrom(element.getClass()));
	}
}
