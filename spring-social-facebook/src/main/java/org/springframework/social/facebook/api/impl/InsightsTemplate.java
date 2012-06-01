package org.springframework.social.facebook.api.impl;

import java.net.URI;
import java.util.Iterator;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonNode;
import org.springframework.social.facebook.api.Insights;
import org.springframework.social.facebook.api.Insights.Values;
import org.springframework.social.facebook.api.InsightsOperations;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.client.RestTemplate;

public class InsightsTemplate extends AbstractFacebookOperations implements InsightsOperations
{
	private final RestTemplate restTemplate;
	
	public InsightsTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(isAuthorized);
		this.restTemplate = restTemplate;
	}

	public JsonNode getInsights(String userID, String function, String period)
	{
		URI uri = URIBuilder.fromUri(
				"https://graph.facebook.com/" + userID + "/insights/" + function + "/" + period ).build();
		JsonNode responseNode = restTemplate.getForObject(uri, JsonNode.class);
		return responseNode;
	}
	
	private Insights deserializeInsights(JsonNode jsonNode) 
	{
		Insights data = new Insights();
		JsonNode dataNode = jsonNode.get("data");
		JsonNode valuesNode = dataNode.findValue("values");
		
		data.setId(dataNode.findValue("id").toString());
		data.setName(dataNode.findValue("name").toString());
		data.setPeriod(dataNode.findValue("period").toString());
		data.setTitle(dataNode.findValue("title").toString());
		data.setDescription(dataNode.findValue("description").toString());
		
		// Now we get into "Values node" to get list of "value"s
		for (Iterator<JsonNode> valuesElements = valuesNode.getElements(); valuesElements.hasNext();) 
		{
			JsonNode valuesElement = valuesElements.next();
			Values tmp_val = new Values();
			boolean isValueList = false;
			
			// Now we get into one of "Value" nodes from Values list
			// to get a list of data that is interesting for us + end_time
			for (Iterator<JsonNode> valueElement = valuesElement.getElements(); valueElement.hasNext();)
			{
				JsonNode valueNode = valueElement.next();
				
				Iterator<Entry<String, JsonNode>> dataList = valueNode.getFields();
				if (dataList.hasNext())
				{
					for (; dataList.hasNext() ;)
					{
						Entry<String, JsonNode> dataListElement = dataList.next();
						tmp_val.putValue(dataListElement.getKey(), dataListElement.getValue().asInt());
					}
				
					tmp_val.setEnd_time(valuesElement.findValue("end_time").asText());
					isValueList = true;
				}
				
				if (!isValueList)
				{
					tmp_val.setEnd_time(valuesElement.findValue("end_time").asText());
					tmp_val.putValue("value", valuesElement.findValue("value").asInt());
				}
			}
			data.addValues(tmp_val);
		}
		
		return data;
	}
	
	private String periodConverter(int period) {
		String converted;
		switch(period){
			case 0:
				converted = "lifetime";
				break;
			case 86400:
				converted = "day";
				break;
			case 604800:
				converted = "week";
				break;
			case 2419200:
				converted = "days_28";
				break;
			case 2592000: 
				converted = "month";
				break;
			default:
				converted = "day";
		}
		return converted;
	}
	
	
	@Override
	public Insights applicationActiveUsers(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_active_users", periodConverter(period)));
	}

	@Override
	public Insights applicationActiveUsers(String userId, String period) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_active_users", period));
	}

	@Override
	public Insights applicationActiveUsersLocale(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_active_users_locale", "day"));
	}

	@Override
	public Insights applicationActiveUsersCity(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_active_users_city", "day"));
	}

	@Override
	public Insights applicationActiveUsersCountry(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_active_users_country", "day"));
	}

	@Override
	public Insights applicationActiveUsersGender(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_active_users_gender", "day"));
	}

	@Override
	public Insights applicationActiveUsersAge(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_active_users_age", "day"));
	}

	@Override
	public Insights applicationActiveUsersGenderAge(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_active_users_gender_age", "day"));
	}

	@Override
	public Insights applicationInstalledUsers(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_installed_users", "lifetime"));
	}

	@Override
	public Insights applicationInstalledUsersLocale(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_installed_users_locale", "lifetime"));
	}

	@Override
	public Insights applicationInstalledUsersCity(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_installed_users_city", "lifetime"));	
	}

	@Override
	public Insights applicationInstalledUsersCountry(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_installed_users_country", "lifetime"));
	}

	@Override
	public Insights applicationInstalledUsersGender(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_installed_users_gender", "lifetime"));
	}

	@Override
	public Insights applicationInstalledUsersAge(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_installed_users_age", "lifetime"));
	}

	@Override
	public Insights applicationInstalledUsersGenderAge(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_installed_users_gender_age", "lifetime"));
	}

	@Override
	public Insights applicationInstallationAdds(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_installation_adds", "day"));
	}

	@Override
	public Insights applicationInstallationAddsUnique(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_installation_adds_unique", "day"));
	}

	@Override
	public Insights applicationInstallationRemoves(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_installation_removes", "day"));
	}

	@Override
	public Insights applicationInstallationRemovesUnique(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_installation_removes_unique", "day"));
	}

	@Override
	public Insights applicationTosViews(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "applicationTos_views", "day"));
	}

	@Override
	public Insights applicationTosViewsUnique(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_tos_views_unique", "day"));
	}

	@Override
	public Insights applicationPermissionViewsTop(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_permission_views_top", "day"));
	}

	@Override
	public Insights applicationPermissionViewsTopUnique(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_permission_views_top_unique", "day"));
	}

	@Override
	public Insights applicationPermissionGrantsTop(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_permission_grants_top", "day"));
	}

	@Override
	public Insights applicationPermissionGrantsTopUnique(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_permission_grants_top_unique", "day"));
	}

	@Override
	public Insights applicationBlockAdds(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_block_adds", "day"));
	}

	@Override
	public Insights applicationBlockAddsUnique(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_block_adds_unique", "day"));
	}

	@Override
	public Insights applicationBlockRemoves(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_block_removes", "day"));
	}

	@Override
	public Insights applicationBlockRemovesUnique(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_block_removes_unique", "day"));
	}

	@Override
	public Insights applicationLikeAdds(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_like_adds", "day"));
	}

	@Override
	public Insights applicationLikeAddsUnique(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_like_adds_unique", "day"));
	}

	@Override
	public Insights applicationLikeRemoves(String userId) {
		requireAuthorization();
		 return deserializeInsights(getInsights(userId, "application_like_removes", "day"));
	}

	@Override
	public Insights applicationLikeRemovesUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_like_removes_unique", "day"));
	}

	@Override
	public Insights applicationCommentAdds(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_comment_adds", "day"));
	}

	@Override
	public Insights applicationCommentAddsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_comment_adds_unique", "day"));
	}

	@Override
	public Insights applicationPhotos(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_photos", "day"));
	}

	@Override
	public Insights applicationPhotosUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_photos_unique", "day"));
	}

	@Override
	public Insights applicationShares(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_shares", "day"));
	}

	@Override
	public Insights applicationSharesUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_shares_unique", "day"));
	}

	@Override
	public Insights applicationStatusUpdates(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_status_updates", "day"));
	}

	@Override
	public Insights applicationStatusUpdatesUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_status_updates_unique", "day"));
	}

	@Override
	public Insights applicationStreamStories(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_stream_stories", "day"));
	}

	@Override
	public Insights applicationStreamStoriesUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_stream_stories_unique", "day"));
	}

	@Override
	public Insights applicationFeedFormViews(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_feed_form_views", "day"));
	}

	@Override
	public Insights applicationFeedFormViewsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_feed_form_views_unique", "day"));
	}

	@Override
	public Insights applicationFeedFormViewsLogin(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_feed_form_views_login", "day"));
	}

	@Override
	public Insights applicationFeedFormViewsLoginUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_feed_form_views_login_unique", "day"));
	}

	@Override
	public Insights applicationFeedFormViewsLogout(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_feed_form_views_logout", "day"));
	}

	@Override
	public Insights applicationWidgetActivityViews(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_activity_views", "day"));
	}

	@Override
	public Insights applicationWidgetActivityViewsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_activity_views_unique", "day"));
	}

	@Override
	public Insights applicationWidgetActivityViewsLogin(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_activity_views_login", "day"));
	}

	@Override
	public Insights applicationWidgetActivityViewsLoginUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_activity_views_login_unique", "day"));
	}

	@Override
	public Insights applicationWidgetActivityViewsLogout(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_activity_views_logout", "day"));
	}

	@Override
	public Insights applicationWidgetActivityViewsExternalReferrals(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_activity_views_external_referrals", "day"));
	}

	@Override
	public Insights applicationWidgetCommentsViews(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_comments_views", "day"));
	}

	@Override
	public Insights applicationWidgetCommentsViewsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_comments_views_unique", "day"));
	}

	@Override
	public Insights applicationWidgetCommentsViewsLogin(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_comments_views_login", "day"));
	}

	@Override
	public Insights applicationWidgetCommentsViewsLoginUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_comments_views_login_unique", "day"));
	}

	@Override
	public Insights applicationWidgetCommentsViewsLogout(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_comments_views_logout", "day"));
	}

	@Override
	public Insights applicationWidgetFanViews(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_fan_views", "day"));
	}

	@Override
	public Insights applicationWidgetFanViewsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_fan_views_unique", "day"));
	}

	@Override
	public Insights applicationWidgetFanViewsLogin(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_fan_views_login", "day"));
	}

	@Override
	public Insights applicationWidgetFanViewsLoginUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_fan_views_login_unique", "day"));
	}

	@Override
	public Insights applicationWidgetFanViewsLogout(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_fan_views_logout", "day"));
	}

	@Override
	public Insights applicationWidgetFanViewsExternalReferrals(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_fan_views_external_referrals", "day"));
	}

	@Override
	public Insights applicationWidgetLikeViews(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_like_views", "day"));
	}

	@Override
	public Insights applicationWidgetLikeViewsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_like_views_unique", "day"));
	}

	@Override
	public Insights applicationWidgetLikeViewsLogin(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_like_views_login", "day"));
	}

	@Override
	public Insights applicationWidgetLikeViewsLoginUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_like_views_login_unique", "day"));
	}

	@Override
	public Insights applicationWidgetLikeViewsLogout(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_like_views_logout", "day"));
	}

	@Override
	public Insights applicationWidgetLikeViewsExternalReferrals(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_like_views_external_referrals", "day"));
	}

	@Override
	public Insights applicationWidgetLiveStreamViews(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_live_stream_views", "day"));
	}

	@Override
	public Insights applicationWidgetLiveStreamViewsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_live_stream_views_unique", "day"));
	}

	@Override
	public Insights applicationWidgetLiveStreamViewsLogin(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_live_stream_views_login", "day"));
	}

	@Override
	public Insights applicationWidgetLiveStreamViewsLoginUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_live_stream_views_login_unique", "day"));
	}

	@Override
	public Insights applicationWidgetLiveStreamViewsLogout(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_live_stream_views_logout", "day"));
	}

	@Override
	public Insights applicationWidgetLiveStreamViewsExternalReferrals(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_live_stream_views_external_referrals", "day"));
	}

	@Override
	public Insights applicationWidgetRecommendationViews(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_recommendation_views", "day"));
	}

	@Override
	public Insights applicationWidgetRecommendationViewsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_recommendation_views_unique", "day"));
	}

	@Override
	public Insights applicatonWidgetRecommendationViewsLogin(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "applicaton_widget_recommendation_views_login", "day"));
	}

	@Override
	public Insights applicationWidgetRecommendationViewsLoginUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_recommendation_views_login_unique", "day"));
	}

	@Override
	public Insights applicationWidgetRecommendationViewsLogout(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_recommendation_views_logout", "day"));
	}

	@Override
	public Insights applicationWidgetRecommendationViewsExternalReferrals(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_recommendation_views_external_referrals", "day"));
	}

	@Override
	public Insights applicationWidgetShareViews(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_share_views", "day"));
	}

	@Override
	public Insights applicationWidgetShareViewsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_share_views_unique", "day"));
	}

	@Override
	public Insights applicationWidgetViews(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_views", "day"));
	}

	@Override
	public Insights applicationWidgetViewsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_views_unique", "day"));
	}

	@Override
	public Insights applicationWidgetViewsLogin(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_views_login", "day"));
	}

	@Override
	public Insights applicationWidgetViewsLoginUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_views_login_unique", "day"));
	}

	@Override
	public Insights applicationWidgetViewsLogout(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_widget_views_logout", "day"));
	}

	@Override
	public Insights applicationCanvasViews(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_canvas_views", "day"));
	}

	@Override
	public Insights applicationCanvasViewsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_canvas_views_unique", "day"));
	}

	@Override
	public Insights applicationCanvasViewsLogin(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_canvas_views_login", "day"));
	}

	@Override
	public Insights applicationCanvasViewsLoginUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_canvas_views_login_unique", "day"));
	}

	@Override
	public Insights applicationCanvasViewsLogout(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_canvas_views_logout", "day"));
	}

	@Override
	public Insights applicationCanvasViewsInternalReferrals(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_canvas_views_internal_referrals", "day"));
	}

	@Override
	public Insights applicationCanvasViewsExternalReferrals(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_canvas_views_external_referrals", "day"));
	}

	@Override
	public Insights applicationTabViews(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_tab_views", "day"));
	}

	@Override
	public Insights applicationTabViewsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_tab_views_unique", "day"));
	}

	@Override
	public Insights applicationApiCalls(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_api_calls", "day"));
	}

	@Override
	public Insights applicationApiCallsTop(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_api_calls_top", "day"));
	}

	@Override
	public Insights applicationApiCallsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_api_calls_unique", "day"));
	}

	@Override
	public Insights applicationApiErrors(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_api_errors", "day"));
	}

	@Override
	public Insights applicationApiErrorsRate(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_api_errors_rate", "day"));
	}

	@Override
	public Insights applicationApiErrorsTop(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_api_errors_top", "day"));
	}

	@Override
	public Insights applicationApiTimeAverage(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_api_time_average", "day"));
	}

	@Override
	public Insights applicationCanvasErrors(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_canvas_errors", "day"));
	}

	@Override
	public Insights applicationCanvasErrorsRate(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_canvas_errors_rate", "day"));
	}

	@Override
	public Insights applicationCanvasTimeAverage(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "application_canvas_time_average", "day"));
	}
	
	@Override
	public Insights pageStories(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_stories", periodConverter(period)));
	}

	@Override
	public Insights pageStories(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_stories", period));
	}

	@Override
	public Insights pageStorytellers(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_storytellers", periodConverter(period)));
	}

	@Override
	public Insights pageStorytellers(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_storytellers", period));
	}

	@Override
	public Insights pageStoriesByStoryType(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_stories_by_story_type", periodConverter(period)));
	}

	@Override
	public Insights pageStoriesByStoryType(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_stories_by_story_type", period));
	}

	@Override
	public Insights pageStorytellersByStoryType(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_storytellers_by_story_type", periodConverter(period)));
	}

	@Override
	public Insights pageStorytellersByStoryType(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_storytellers_by_story_type", period));
	}

	@Override
	public Insights pageStorytellersByAgeGender(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_storytellers_by_age_gender", periodConverter(period)));
	}

	@Override
	public Insights pageStorytellersByAgeGender(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_storytellers_by_age_gender", period));
	}

	@Override
	public Insights pageStorytellersByCountry(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_storytellers_by_country", periodConverter(period)));
	}

	@Override
	public Insights pageStorytellersByCountry(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_storytellers_by_country", period));
	}

	@Override
	public Insights pageStorytellersByCity(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_storytellers_by_city", periodConverter(period)));
	}

	@Override
	public Insights pageStorytellersByCity(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_storytellers_by_city", period));
	}

	@Override
	public Insights pageStorytellersByLocale(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_storytellers_by_locale", periodConverter(period)));
	}

	@Override
	public Insights pageStorytellersByLocale(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_storytellers_by_locale", period));
	}

	@Override
	public Insights pageImpressions(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions", periodConverter(period)));
	}

	@Override
	public Insights pageImpressions(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions", period));
	}

	@Override
	public Insights pageImpressionsUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_unique", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_unique", period));
	}

	@Override
	public Insights pageImpressionsPaid(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_paid", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsPaid(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_paid", period));
	}

	@Override
	public Insights pageImpressionsPaidUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_paid_unique", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsPaidUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_paid_unique", period));
	}

	@Override
	public Insights pageImpressionsOrganic(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_organic", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsOrganic(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_organic", period));
	}

	@Override
	public Insights pageImpressionsOrganicUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_organic_unique", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsOrganicUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_organic_unique", period));
	}

	@Override
	public Insights pageImpressionsViral(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_viral", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsViral(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_viral", period));
	}

	@Override
	public Insights pageImpressionsViralUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_viral_unique", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsViralUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_viral_unique", period));
	}

	@Override
	public Insights pageImpressionsByStoryType(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_by_story_type", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsByStoryType(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_by_story_type", period));
	}

	@Override
	public Insights pageImpressionsByStoryTypeUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_by_story_type_unique", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsByStoryTypeUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_by_story_type_unique", period));
	}

	@Override
	public Insights pageImpressionsByCityUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_by_city_unique", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsByCityUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_by_city_unique", period));
	}

	@Override
	public Insights pageImpressionsByCountryUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_by_country_unique", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsByCountryUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_by_country_unique", period));
	}

	@Override
	public Insights pageImpressionsByLocaleUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_by_locale_unique", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsByLocaleUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_by_locale_unique", period));
	}

	@Override
	public Insights pageImpressionsByAgeGenderUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_by_age_gender_unique", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsByAgeGenderUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_by_age_gender_unique", period));
	}

	@Override
	public Insights pageImpressionsFrequencyDistribution(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_frequency_distribution", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsFrequencyDistribution(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_frequency_distribution", period));
	}

	@Override
	public Insights pageImpressionsViralFrequencyDistribution(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_viral_frequency_distribution", periodConverter(period)));
	}

	@Override
	public Insights pageImpressionsViralFrequencyDistribution(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_impressions_viral_frequency_distribution", period));
	}

	@Override
	public Insights pageEngagedUsers(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_engaged_users", periodConverter(period)));
	}

	@Override
	public Insights pageEngagedUsers(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_engaged_users", period));
	}

	@Override
	public Insights pageConsumptions(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_consumptions", periodConverter(period)));
	}

	@Override
	public Insights pageConsumptions(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_consumptions", period));
	}

	@Override
	public Insights pageConsumptionsUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_consumptions_unique", periodConverter(period)));
	}

	@Override
	public Insights pageConsumptionsUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_consumptions_unique", period));
	}

	@Override
	public Insights pageConsumptionsByConsumptionType(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_consumptions_by_consumption_type", periodConverter(period)));
	}

	@Override
	public Insights pageConsumptionsByConsumptionType(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_consumptions_by_consumption_type", period));
	}

	@Override
	public Insights pageConsumptionsByConsumptionTypeUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_consumptions_by_consumption_type_unique", periodConverter(period)));
	}

	@Override
	public Insights pageConsumptionsByConsumptionTypeUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_consumptions_by_consumption_type_unique", period));
	}

	@Override
	public Insights pagePlacesCheckinTotal(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_places_checkin_total", periodConverter(period)));
	}

	@Override
	public Insights pagePlacesCheckinTotal(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_places_checkin_total", period));
	}

	@Override
	public Insights pagePlacesCheckinTotalUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_places_checkin_total_unique", periodConverter(period)));
	}

	@Override
	public Insights pagePlacesCheckinTotalUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_places_checkin_total_unique", period));
	}

	@Override
	public Insights pagePlacesCheckinMobile(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_places_checkin_mobile", periodConverter(period)));
	}

	@Override
	public Insights pagePlacesCheckinMobile(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_places_checkin_mobile", period));
	}

	@Override
	public Insights pagePlacesCheckinMobileUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_places_checkin_mobile_unique", periodConverter(period)));
	}

	@Override
	public Insights pagePlacesCheckinMobileUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_places_checkin_mobile_unique", period));
	}

	@Override
	public Insights pagePlacesCheckinsByAgeGender(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_places_checkins_by_age_gender", "day"));
	}

	@Override
	public Insights pagePlacesCheckinsByLocale(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_places_checkins_by_locale", "day"));
	}

	@Override
	public Insights pagePlacesCheckinsByCity(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_places_checkins_by_city", "day"));
	}

	@Override
	public Insights pagePlacesCheckinsByCountry(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_places_checkins_by_country", "day"));
	}

	@Override
	public Insights pageNegativeFeedback(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_negative_feedback", periodConverter(period)));
	}

	@Override
	public Insights pageNegativeFeedback(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_negative_feedback", period));
	}

	@Override
	public Insights pageNegativeFeedbackUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_negative_feedback_unique", periodConverter(period)));
	}

	@Override
	public Insights pageNegativeFeedbackUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_negative_feedback_unique", period));
	}

	@Override
	public Insights pageNegativeFeedbackByType(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_negative_feedback_by_type", periodConverter(period)));
	}

	@Override
	public Insights pageNegativeFeedbackByType(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_negative_feedback_by_type", period));
	}

	@Override
	public Insights pageNegativeFeedbackByTypeUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_negative_feedback_by_type_unique", periodConverter(period)));
	}

	@Override
	public Insights pageNegativeFeedbackByTypeUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_negative_feedback_by_type_unique", period));
	}
	
	@Override
	public Insights pageFans(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_fans", "lifetime"));
	}

	@Override
	public Insights pageFansLocale(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_fans_locale", "lifetime"));
	}

	@Override
	public Insights pageFansCity(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_fans_city", "lifetime"));
	}

	@Override
	public Insights pageFansCountry(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_fans_country", "lifetime"));
	}

	@Override
	public Insights pageFansGender(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_fans_gender", "lifetime"));
	}

	@Override
	public Insights pageFansAge(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_fans_age", "lifetime"));
	}

	@Override
	public Insights pageFansGenderAge(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_fans_gender_age", "lifetime"));
	}

	@Override
	public Insights pageFanAdds(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_fan_adds", "day"));
	}

	@Override
	public Insights pageFanAddsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_fan_adds_unique", "day"));
	}

	@Override
	public Insights pageFansByLikeSource(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_fans_by_like_source", "day"));
	}

	@Override
	public Insights pageFansByLikeSourceUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_fans_by_like_source_unique", "day"));
	}

	@Override
	public Insights pageFanRemoves(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_fan_removes", "day"));
	}

	@Override
	public Insights pageFanRemovesUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_fan_removes_unique", "day"));
	}
	
	@Override
	public Insights pageFriendsOfFans(String userId) {
		return deserializeInsights(getInsights(userId, "page_friends_of_fans", "day"));
	}

	@Override
	public Insights pageTabViewsLoginTopUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "pageTab_views_login_top_unique", periodConverter(period)));
	}

	@Override
	public Insights pageTabViewsLoginTopUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_tab_views_login_top_unique", period));
	}

	@Override
	public Insights pageTabViewsLoginTop(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_tab_views_login_top", periodConverter(period)));
	}

	@Override
	public Insights pageTabViewsLoginTop(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_tab_views_login_top", period));
	}

	@Override
	public Insights pageViews(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_views", "day"));
	}

	@Override
	public Insights pageViewsUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_views_unique", periodConverter(period)));
	}

	@Override
	public Insights pageViewsUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_views_unique", period));
	}

	@Override
	public Insights pageViewsLogin(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_views_login", periodConverter(period)));
	}

	@Override
	public Insights pageViewsLogin(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_views_login", period));
	}

	@Override
	public Insights pageViewsLoginUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_views_login_unique", periodConverter(period)));
	}

	@Override
	public Insights pageViewsLoginUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_views_login_unique", period));
	}

	@Override
	public Insights pageViewsLogout(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_views_logout", "day"));
	}

	@Override
	public Insights pageViewsExternalReferrals(String userId) {
		return deserializeInsights(getInsights(userId, "page_views_external_referrals", "day"));
	}

	@Override
	public Insights pagePostsImpressions(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions", periodConverter(period)));
	}

	@Override
	public Insights pagePostsImpressions(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions", period));
	}

	@Override
	public Insights pagePostsImpressionsUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_unique", periodConverter(period)));
	}

	@Override
	public Insights pagePostsImpressionsUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_unique", period));
	}

	@Override
	public Insights pagePostsImpressionsPaid(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_paid", periodConverter(period)));
	}

	@Override
	public Insights pagePostsImpressionsPaid(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_paid", period));
	}

	@Override
	public Insights pagePostsImpressionsPaidUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_paid_unique", periodConverter(period)));
	}

	@Override
	public Insights pagePostsImpressionsPaidUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_paid_unique", period));
	}

	@Override
	public Insights pagePostsImpressionsOrganic(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_organic", periodConverter(period)));
	}

	@Override
	public Insights pagePostsImpressionsOrganic(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_organic", period));
	}

	@Override
	public Insights pagePostsImpressionsOrganicUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_organic_unique", periodConverter(period)));
	}

	@Override
	public Insights pagePostsImpressionsOrganicUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_organic_unique", period));
	}

	@Override
	public Insights pagePostsImpressionsViral(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_viral", periodConverter(period)));
	}

	@Override
	public Insights pagePostsImpressionsViral(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_viral", period));
	}

	@Override
	public Insights pagePostsImpressionsViralUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_viral_unique", periodConverter(period)));
	}

	@Override
	public Insights pagePostsImpressionsViralUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_viral_unique", period));
	}

	@Override
	public Insights pagePostsImpressionsFrequencyDistribution(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_frequency_distribution", periodConverter(period)));
	}

	@Override
	public Insights pagePostsImpressionsFrequencyDistribution(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_posts_impressions_frequency_distribution", period));
	}

	@Override
	public Insights postStories(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_stories", "lifetime"));
	}

	@Override
	public Insights postStorytellers(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_storytellers", "lifetime"));
	}

	@Override
	public Insights postStoriesByActionType(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_stories_by_action_type", "lifetime"));
	}

	@Override
	public Insights postStorytellersByActionType(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_storytellers_by_action_type", "lifetime"));
	}

	@Override
	public Insights postImpressions(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_impressions", "lifetime"));
	}

	@Override
	public Insights postImpressionsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_impressions_unique", "lifetime"));
	}

	@Override
	public Insights postImpressionsPaid(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_impressions_paid", "lifetime"));
	}

	@Override
	public Insights postImpressionsPaidUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_impressions_paid_unique", "lifetime"));
	}

	@Override
	public Insights postImpressionsOrganic(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_impressions_organic", "lifetime"));
	}

	@Override
	public Insights postImpressionsOrganicUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_impressions_organic_unique", "lifetime"));
	}

	@Override
	public Insights postImpressionsViral(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_impressions_viral", "lifetime"));
	}

	@Override
	public Insights postImpressionsViralUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_impressions_viral_unique", "lifetime"));
	}

	@Override
	public Insights postImpressionsByStoryType(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_impressions_by_story_type", "lifetime"));
	}

	@Override
	public Insights postImpressionsByStoryTypeUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_impressions_by_story_type_unique", "lifetime"));
	}

	@Override
	public Insights postConsumptions(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_consumptions", "lifetime"));
	}

	@Override
	public Insights postConsumptionsUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_consumptions_unique", "lifetime"));
	}

	@Override
	public Insights postConsumptionsByType(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_consumptions_by_type", "lifetime"));
	}

	@Override
	public Insights postConsumptionsByTypeUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_consumptions_by_type_unique", "lifetime"));
	}

	@Override
	public Insights postEngagedUsers(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_engaged_users", "lifetime"));
	}

	@Override
	public Insights postNegativeFeedback(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_negative_feedback", "lifetime"));
	}

	@Override
	public Insights postNegativeFeedbackUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_negative_feedback_unique", "lifetime"));
	}

	@Override
	public Insights postNegativeFeedbackByType(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_negative_feedback_by_type", "lifetime"));
	}

	@Override
	public Insights postNegativeFeedbackByTypeUnique(String userId) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "post_negative_feedback_by_type_unique", "lifetime"));
	}

	@Override
	public Insights domainFeedClicks(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_feed_clicks", periodConverter(period)));
	}

	@Override
	public Insights domainFeedClicks(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_feed_clicks", period));
	}

	@Override
	public Insights domainFeedViews(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_feed_views", periodConverter(period)));
	}

	@Override
	public Insights domainFeedViews(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_feed_views", period));
	}

	@Override
	public Insights domainStories(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_stories", periodConverter(period)));
	}

	@Override
	public Insights domainStories(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_stories", period));
	}

	@Override
	public Insights domainWidgetCommentsAdds(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_comments_adds", periodConverter(period)));
	}

	@Override
	public Insights domainWidgetCommentsAdds(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_comments_adds", period));
	}

	@Override
	public Insights domainWidgetCommentsViews(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_comments_views", periodConverter(period)));
	}

	@Override
	public Insights domainWidgetCommentsViews(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_comments_views", period));
	}

	@Override
	public Insights domainWidgetCommentsFeedViews(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_comments_feed_views", periodConverter(period)));
	}

	@Override
	public Insights domainWidgetCommentsFeedViews(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_comments_feed_views", period));
	}

	@Override
	public Insights domainWidgetCommentsFeedClicks(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_comments_feed_clicks", periodConverter(period)));
	}

	@Override
	public Insights domainWidgetCommentsFeedClicks(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_comments_feed_clicks", period));
	}

	@Override
	public Insights domainWidgetLikeViews(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_like_views", periodConverter(period)));
	}

	@Override
	public Insights domainWidgetLikeViews(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_like_views", period));
	}

	@Override
	public Insights domainWidgetLikes(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_likes", periodConverter(period)));
	}

	@Override
	public Insights domainWidgetLikes(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_likes", period));
	}

	@Override
	public Insights domainWidgetLikeFeedViews(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_like_feed_views", periodConverter(period)));
	}

	@Override
	public Insights domainWidgetLikeFeedViews(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_like_feed_views", period));
	}

	@Override
	public Insights domainWidgetLikeFeedClicks(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_like_feed_clicks", periodConverter(period)));
	}

	@Override
	public Insights domainWidgetLikeFeedClicks(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_like_feed_clicks", period));
	}

	@Override
	public Insights domainWidgetSendViews(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_send_views", periodConverter(period)));
	}

	@Override
	public Insights domainWidgetSendViews(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_send_views", period));
	}

	@Override
	public Insights domainWidgetSendClicks(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_send_clicks", periodConverter(period)));
	}

	@Override
	public Insights domainWidgetSendClicks(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_send_clicks", period));
	}

	@Override
	public Insights domainWidgetSendInboxViews(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_send_inbox_views", periodConverter(period)));
	}

	@Override
	public Insights domainWidgetSendInboxViews(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_send_inbox_views", period));
	}

	@Override
	public Insights domainWidgetSendInboxClicks(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_send_inbox_clicks", periodConverter(period)));
	}

	@Override
	public Insights domainWidgetSendInboxClicks(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "domain_widget_send_inbox_clicks", period));
	}

	@Override
	public Insights pageStoryAdds(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds", periodConverter(period)));
	}

	@Override
	public Insights pageStoryAdds(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds", period));
	}

	@Override
	public Insights pageStoryAddsUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_unique", periodConverter(period)));
	}

	@Override
	public Insights pageStoryAddsUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_unique", period));
	}

	@Override
	public Insights pageStoryAddsByStoryType(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_by_story_type", periodConverter(period)));
	}

	@Override
	public Insights pageStoryAddsByStoryType(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_by_story_type", period));
	}

	@Override
	public Insights pageStoryAddsByStoryTypeUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_by_story_type_unique", periodConverter(period)));
	}

	@Override
	public Insights pageStoryAddsByStoryTypeUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_by_story_type_unique", period));
	}

	@Override
	public Insights pageStoryAddsByAgeGenderUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_by_age_gender_unique", periodConverter(period)));
	}

	@Override
	public Insights pageStoryAddsByAgeGenderUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_by_age_gender_unique", period));
	}

	@Override
	public Insights pageStoryAddsByCountryUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_by_country_unique", periodConverter(period)));
	}

	@Override
	public Insights pageStoryAddsByCountryUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_by_country_unique", period));
	}

	@Override
	public Insights pageStoryAddsByCityUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_by_city_unique", periodConverter(period)));
	}

	@Override
	public Insights pageStoryAddsByCityUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_by_city_unique", period));
	}

	@Override
	public Insights pageStoryAddsByLocaleUnique(String userId, int period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_by_locale_unique", periodConverter(period)));
	}

	@Override
	public Insights pageStoryAddsByLocaleUnique(String userId, String period) {
		requireAuthorization();
		return deserializeInsights(getInsights(userId, "page_story_adds_by_locale_unique", period));
	}
}
