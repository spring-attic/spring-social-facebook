package org.springframework.social.facebook.api;

/**
 * Interface defining operations that can be performed via Facebook insights.
 * @author Tomasz Wójtowicz
 */
public interface InsightsOperations {
	
	//**********************************************************************
	// Application Users
	//**********************************************************************
	
	/**
	 * 1 day, 7 day, and 30 day counts of users who have engaged with your
	 *  app or viewed your app
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2592000 (month)
	 * @return a {@link Insights}
	 */
	Insights applicationActiveUsers(String userId, int period);
	
	/**
	 *  1 day, 7 day, and 30 day counts of users who have engaged with your
	 *   app or viewed your app
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "month"
	 * @return a {@link Insights}
	 */
	Insights applicationActiveUsers(String userId, String period);
	
	/**
	 * Top locales of your active users
	 * @return a {@link Insights}
	 */
	Insights applicationActiveUsersLocale(String userId);
	
	/**
	 * Top cities of your active users 
	 * @return a {@link Insights}
	 */
	Insights applicationActiveUsersCity(String userId);
	
	/**
	 * Top countries of your active users
	 * @return a {@link Insights}
	 */
	Insights applicationActiveUsersCountry(String userId);
	
	/**
	 * Gender of your active users
	 * @return a {@link Insights}
	 */
	Insights applicationActiveUsersGender(String userId);
	
	/**
	 * Age of your active users
	 * @return a {@link Insights}
	 */
	Insights applicationActiveUsersAge(String userId);
	
	/**
	 * Gender and age demographics of your active users
	 * @return a {@link Insights}
	 */
	Insights applicationActiveUsersGenderAge(String userId);
	
	/**
	 * Total installations of your app or connections to your Connect app
	 * @return a {@link Insights}
	 */
	Insights applicationInstalledUsers(String userId);
	
	/**
	 * 
	 * @return a {@link Insights}
	 */
	Insights applicationInstalledUsersLocale(String userId);
	
	/**
	 * Top locales of your app's installed user base
	 * @return a {@link Insights}
	 */
	Insights applicationInstalledUsersCity(String userId);
	
	/**
	 * Top countries of your app's installed user base
	 * @return a {@link Insights}
	 */
	Insights applicationInstalledUsersCountry(String userId);
	
	/**
	 * Gender of your app's installed user base
	 * @return a {@link Insights}
	 */
	Insights applicationInstalledUsersGender(String userId);
	
	/**
	 * Age of your app's installed user base
	 * @return a {@link Insights}
	 */
	Insights applicationInstalledUsersAge(String userId);
	
	/**
	 * Gender and age demographics of your app's installed user base
	 * @return a {@link Insights}
	 */
	Insights applicationInstalledUsersGenderAge(String userId);
	
	/**
	 * New installations of your app or connections to your Connect
	 * site. Follows acceptance of your Terms of Service
	 * @return a {@link Insights}
	 */
	Insights applicationInstallationAdds(String userId);
	
	/**
	 * New installations of your app or connections to your Connect site. 
	 * Follows acceptance of your Terms of Service
	 * @return a {@link Insights}
	 */
	Insights applicationInstallationAddsUnique(String userId);
	
	/**
	 * Removed installations of your app or connections to your Connect site
	 * @return a {@link Insights}
	 */
	Insights applicationInstallationRemoves(String userId);
	
	/**
	 * Removed installations of your app or connections to your Connect site
	 * @return a {@link Insights}
	 */
	Insights applicationInstallationRemovesUnique(String userId);
	
	/**
	 * Impressions of the Terms Of Service dialog of your app
	 * @return a {@link Insights}
	 */
	Insights applicationTosViews(String userId);
	
	/**
	 * Impressions of the Terms Of Service dialog of your app
	 * @return a {@link Insights}
	 */
	Insights applicationTosViewsUnique(String userId);
	
	/**
	 * Impressions of each permissions dialog type
	 * @return a {@link Insights}
	 */
	Insights applicationPermissionViewsTop(String userId);
	
	/**
	 * Impressions of each permissions dialog type
	 * @return a {@link Insights}
	 */
	Insights applicationPermissionViewsTopUnique(String userId);
	
	/**
	 * Impressions of each permissions dialog type
	 * @return a {@link Insights}
	 */
	Insights applicationPermissionGrantsTop(String userId);
	
	/**
	 * Impressions of each permissions dialog type
	 * @return a {@link Insights}
	 */
	Insights applicationPermissionGrantsTopUnique(String userId);
	
	/**
	 * Blocks of your app
	 * @return a {@link Insights}
	 */
	Insights applicationBlockAdds(String userId);
	
	/**
	 * Blocks of your app
	 * @return a {@link Insights}
	 */
	Insights applicationBlockAddsUnique(String userId);
	
	/**
	 * Removed blocks of your app
	 * @return a {@link Insights}
	 */
	Insights applicationBlockRemoves(String userId);
	
	/**
	 * Removed blocks of your app
	 * @return a {@link Insights}
	 */
	Insights applicationBlockRemovesUnique(String userId);

	
	//**********************************************************************
	// Application Content
	//**********************************************************************
	
	/**
	 * Likes created via your app
	 * @return a {@link Insights}
	 */
	Insights applicationLikeAdds(String userId);
	
	/**
	 * Likes created via your app
	 * @return a {@link Insights}
	 */
	Insights applicationLikeAddsUnique(String userId);
	
	/**
	 * Likes created via your app
	 * @return a {@link Insights}
	 */
	Insights applicationLikeRemoves(String userId);
	
	/**
	 * Likes created via your app
	 * @return a {@link Insights}
	 */
	Insights applicationLikeRemovesUnique(String userId);
	
	/**
	 * Comments created via your app
	 * @return a {@link Insights}
	 */
	Insights applicationCommentAdds(String userId);
	
	/**
	 * Comments created via your app
	 * @return a {@link Insights}
	 */
	Insights applicationCommentAddsUnique(String userId);
	
	/**
	 * Photos created via your app
	 * @return a {@link Insights}
	 */
	Insights applicationPhotos(String userId);
	
	/**
	 * Photos created via your app
	 * @return a {@link Insights}
	 */
	Insights applicationPhotosUnique(String userId);
	
	/**
	 * Shares Created by your app via the Links.post method
	 * @return a {@link Insights}
	 */
	Insights applicationShares(String userId);
	
	/**
	 * Shares Created by your app via the Links.post method
	 * @return a {@link Insights}
	 */
	Insights applicationSharesUnique(String userId);
	
	/**
	 * Status Updates created via your app
	 * @return a {@link Insights}
	 */
	Insights applicationStatusUpdates(String userId);
	
	/**
	 * Status Updates created via your app
	 * @return a {@link Insights}
	 */
	Insights applicationStatusUpdatesUnique(String userId);
	
	/**
	 * Stream.publish stories created via your app
	 * @return a {@link Insights}
	 */
	Insights applicationStreamStories(String userId);
	
	/**
	 * Stream.publish stories created via your app
	 * @return a {@link Insights}
	 */
	Insights applicationStreamStoriesUnique(String userId);
	
	/**
	 * Impressions of the Feed form dialog
	 * @return a {@link Insights}
	 */
	Insights applicationFeedFormViews(String userId);
	
	/**
	 * Impressions of the Feed form dialog from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationFeedFormViewsUnique(String userId);
	
	/**
	 * Impressions of the Feed form dialog from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationFeedFormViewsLogin(String userId);
	
	/**
	 * Impressions of the Feed form dialog from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationFeedFormViewsLoginUnique(String userId);
	
	/**
	 * Impressions of the Feed form dialog from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationFeedFormViewsLogout(String userId);

	
	//**********************************************************************
	// Plugins
	//**********************************************************************
	
	/**
	 * Impressions of your activity plugin
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetActivityViews(String userId);
	
	/**
	 * Activity Plugin impressions from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetActivityViewsUnique(String userId);
	
	/**
	 * Activity Plugin impressions from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetActivityViewsLogin(String userId);
	
	/**
	 * Activity Plugin impressions from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetActivityViewsLoginUnique(String userId);
	
	/**
	 * Activity Plugin impressions from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetActivityViewsLogout(String userId);
	
	/**
	 * Top domains of impressions of your Activity plugins
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetActivityViewsExternalReferrals(String userId);
	
	/**
	 * Impressions of your Comments plugin
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetCommentsViews(String userId);
	
	/**
	 * Impressions of your Comments plugin from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetCommentsViewsUnique(String userId);
	
	/**
	 * Impressions of your Comments plugin from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetCommentsViewsLogin(String userId);
	
	/**
	 * Impressions of your Comments plugin from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetCommentsViewsLoginUnique(String userId);
	
	/**
	 * Impressions of your Comments plugin from users not logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetCommentsViewsLogout(String userId);
	
	/**
	 * Impressions of your Like Box
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetFanViews(String userId);
	
	/**
	 * Impressions of your Like Box plugin from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetFanViewsUnique(String userId);
	
	/**
	 * Impressions of your Like Box plugin from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetFanViewsLogin(String userId);
	
	/**
	 * Impressions of your Like Box plugin from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetFanViewsLoginUnique(String userId);
	
	/**
	 * Impressions of your Like Box plugin from users not logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetFanViewsLogout(String userId);
	
	/**
	 * Top domains of impressions of your Like Box plugins
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetFanViewsExternalReferrals(String userId);
	
	/**
	 * Impressions of your Like plugin
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetLikeViews(String userId);
	
	/**
	 * Like Button impressions from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetLikeViewsUnique(String userId);
	
	/**
	 * Like Button impressions from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetLikeViewsLogin(String userId);
	
	/**
	 * Like Button impressions from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetLikeViewsLoginUnique(String userId);
	
	/**
	 * Like Button impressions from users not logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetLikeViewsLogout(String userId);
	
	/**
	 * Top domains of impressions of your Like plugins
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetLikeViewsExternalReferrals(String userId);
	
	/**
	 * Impressions of your Live Stream plugin
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetLiveStreamViews(String userId);
	
	/**
	 * Impressions of your Live Stream plugin from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetLiveStreamViewsUnique(String userId);
	
	/**
	 * Impressions of your Live Stream plugin from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetLiveStreamViewsLogin(String userId);
	
	/**
	 * Impressions of your Live Stream plugin from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetLiveStreamViewsLoginUnique(String userId);
	
	/**
	 * Impressions of your Live Stream plugin from users not logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetLiveStreamViewsLogout(String userId);
	
	/**
	 * Top domains of impressions of your Live Stream plugins
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetLiveStreamViewsExternalReferrals(String userId);
	
	/**
	 * Impressions of your Recommendation plugin
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetRecommendationViews(String userId);
	
	/**
	 * Recommendation Plugin impressions from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetRecommendationViewsUnique(String userId);
	
	/**
	 * Recommendation Plugin impressions from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicatonWidgetRecommendationViewsLogin(String userId);
	
	/**
	 * Recommendation Plugin impressions from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetRecommendationViewsLoginUnique(String userId);
	
	/**
	 * Recommendation Plugin impressions from users not logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetRecommendationViewsLogout(String userId);
	
	/**
	 * Top domains of impressions of your Recommendation plugins
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetRecommendationViewsExternalReferrals(String userId);
	
	/**
	 * Impressions of your Share widget from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetShareViews(String userId);
	
	/**
	 * Impressions of your Share widget from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetShareViewsUnique(String userId);
	
	/**
	 * Total social plugin impressions
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetViews(String userId);
	
	/**
	 * Total social plugin impressions from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetViewsUnique(String userId);
	
	/**
	 * Total social plugin impressions from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetViewsLogin(String userId);
	
	/**
	 * Total social plugin impressions from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetViewsLoginUnique(String userId);
	
	/**
	 * Total social plugin impressions from users not logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationWidgetViewsLogout(String userId);

	
	//**********************************************************************
	// Canvas
	//**********************************************************************
	
	/**
	 * Number of impressions of your app's canvas page
	 * @return a {@link Insights}
	 */
	Insights applicationCanvasViews(String userId);
	/**
	 * Number of impressions of your app's canvas page from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationCanvasViewsUnique(String userId);
	/**
	 * Number of impressions of your app's canvas page from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationCanvasViewsLogin(String userId);
	/**
	 * Number of impressions of your app's canvas page from users logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationCanvasViewsLoginUnique(String userId);
	/**
	 * Number of impressions of your app's canvas page from users not logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationCanvasViewsLogout(String userId);
	/**
	 * Top referrers to your Canvas page on Facebook
	 * @return a {@link Insights}
	 */
	Insights applicationCanvasViewsInternalReferrals(String userId);
	/**
	 * Top referrering external domains to your Canvas page
	 * @return a {@link Insights}
	 */
	Insights applicationCanvasViewsExternalReferrals(String userId);

	
	//**********************************************************************
	// Application Tabs
	//**********************************************************************
	
	/**
	 * Impressions of your app's tab on users' Profiles
	 * @return a {@link Insights}
	 */
	Insights applicationTabViews(String userId);
	/**
	 * Impressions of your app's tab on users' Profiles
	 * @return a {@link Insights}
	 */
	Insights applicationTabViewsUnique(String userId);

	
	//**********************************************************************
	// API Performance
	//**********************************************************************
	
	/**
	 * Total API calls from your app
	 * @return a {@link Insights}
	 */
	Insights applicationApiCalls(String userId);
	
	/**
	 * Most frequent API calls from your app
	 * @return a {@link Insights}
	 */
	Insights applicationApiCallsTop(String userId);
	
	/**
	 * Total API calls from your app
	 * @return a {@link Insights}
	 */
	Insights applicationApiCallsUnique(String userId);
	
	/**
	 * Total API errors from your app
	 * @return a {@link Insights}
	 */
	Insights applicationApiErrors(String userId);
	
	/**
	 * Average number of errors per API request from your app
	 * @return a {@link Insights}
	 */
	Insights applicationApiErrorsRate(String userId);
	
	/**
	 * Most frequent API errors from your app
	 * @return a {@link Insights}
	 */
	Insights applicationApiErrorsTop(String userId);
	
	/**
	 * Average time for API requests from your app, in milliseconds
	 * @return a {@link Insights}
	 */
	Insights applicationApiTimeAverage(String userId);
	
	/**
	 * HTTP request errors on your Canvas page
	 * @return a {@link Insights}
	 */
	Insights applicationCanvasErrors(String userId);
	/**
	 * Average number of errors per canvas request of your app
	 * @return a {@link Insights}
	 */
	Insights applicationCanvasErrorsRate(String userId);
	/**
	 * Average HTTP response time on your Canvas page, in milliseconds
	 * @return a {@link Insights}
	 */
	Insights applicationCanvasTimeAverage(String userId);

	
	//**********************************************************************
	// Stories and People talking about this
	//**********************************************************************
	
	/**
	 * The number of stories created about your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStories(String userId, int period);
	
	/**
	 * The number of stories created about your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStories(String userId, String period);
	
	/**
	 * The number of people sharing stories about your page. These stories include 
	 * liking your Page, posting to your Page's Wall, liking, commenting on or sharing
	 * one of your Page posts, answering a Question you posted, RSVPing to one of your
	 * events, mentioning your Page, phototagging your Page or checking in at your Place 
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStorytellers(String userId, int period);
	
	/**
	 * 	The number of people sharing stories about your page. These stories include 
	 * liking your Page, posting to your Page's Wall, liking, commenting on or sharing
	 * one of your Page posts, answering a Question you posted, RSVPing to one of your
	 * events, mentioning your Page, phototagging your Page or checking in at your Place 
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStorytellers(String userId, String period);
	
	/**
	 * The number of stories about your Page by story type
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	
	Insights pageStoriesByStoryType(String userId, int period);
	/**
	 * The number of stories about your Page by story type
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStoriesByStoryType(String userId, String period);
	
	/**
	 * The number of people talking about your Page, by story type
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStorytellersByStoryType(String userId, int period);
	
	/**
	 * The number of people talking about your Page, by story type
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStorytellersByStoryType(String userId, String period);
	
	/**
	 * The number of People Talking About the Page by user age and gender
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStorytellersByAgeGender(String userId, int period);
	
	/**
	 * The number of People Talking About the Page by user age and gender
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStorytellersByAgeGender(String userId, String period);
	
	/**
	 * The number of People Talking About the Page by user country
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStorytellersByCountry(String userId, int period);
	
	/**
	 * The number of People Talking About the Page by user country
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStorytellersByCountry(String userId, String period);
	
	/**
	 * The number of People Talking About the Page by user city
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStorytellersByCity(String userId, int period);
	
	/**
	 * The number of People Talking About the Page by user city
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStorytellersByCity(String userId, String period);
	
	/**
	 * The number of People Talking About the Page by user language
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStorytellersByLocale(String userId, int period);
	
	/**
	 * The number of People Talking About the Page by user language
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStorytellersByLocale(String userId, String period);

	
	//**********************************************************************
	// Page impressions
	//**********************************************************************
	
	/**
	 * The total number of impressions seen of any content associated with your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressions(String userId, int period);
	
	/**
	 * The total number of impressions seen of any content associated with your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressions(String userId, String period);
	
	/**
	 * The number of people who have seen any content associated with your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsUnique(String userId, int period);
	
	/**
	 * The number of people who have seen any content associated with your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsUnique(String userId, String period);
	
	/**
	 * The number of impressions of a Sponsored Story or Ad pointing to your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsPaid(String userId, int period);
	
	/**
	 * The number of impressions of a Sponsored Story or Ad pointing to your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsPaid(String userId, String period);
	
	/**
	 * Number of people who saw a sponsored story or Ad about your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsPaidUnique(String userId, int period);
	
	/**
	 * Number of people who saw a sponsored story or Ad about your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsPaidUnique(String userId, String period);
	
	/**
	 * The number of times your posts were seen in News Feed or Ticker or on visits to
	 * your Page. These impressions can be Fans or non-Fans
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsOrganic(String userId, int period);
	/**
	 * The number of times your posts were seen in News Feed or Ticker or on visits to
	 * your Page. These impressions can be Fans or non-Fans
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsOrganic(String userId, String period);
	
	/**
	 * The number of people who visited your Page, or saw your Page or one of its posts
	 * in News Feed or Ticker. These impressions can be Fans or non-Fans
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsOrganicUnique(String userId, int period);
	
	/**
	 * The number of people who visited your Page, or saw your Page or one of its posts
	 * in News Feed or Ticker. These impressions can be Fans or non-Fans
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsOrganicUnique(String userId, String period);
	
	/**
	 * The number of impressions of a story published by a friend about your Page. These
	 * stories include liking your Page, posting to your Page's Wall, liking, commenting
	 * on or sharing one of your Page posts, answering a Question you posted, RSVPing to
	 * one of your events, mentioning your Page, phototagging your Page or checking in at
	 * your Place
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsViral(String userId, int period);
	
	/**
	 * The number of impressions of a story published by a friend about your Page. These
	 * stories include liking your Page, posting to your Page's Wall, liking, commenting
	 * on or sharing one of your Page posts, answering a Question you posted, RSVPing to
	 * one of your events, mentioning your Page, phototagging your Page or checking in at
	 * your Place
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsViral(String userId, String period);
	
	/**
	 * The number of people who saw your Page or one of its posts from a story published
	 * by a friend. These stories include liking your Page, posting to your Page's Wall,
	 * liking, commenting on or sharing one of your Page posts, answering a Question you
	 * posted, RSVPing to one of your events, mentioning your Page, phototagging your Page
	 * or checking in at your Place
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsViralUnique(String userId, int period);
	
	/**
	 * The number of people who saw your Page or one of its posts from a story published
	 * by a friend. These stories include liking your Page, posting to your Page's Wall,
	 * liking, commenting on or sharing one of your Page posts, answering a Question you
	 * posted, RSVPing to one of your events, mentioning your Page, phototagging your Page
	 * or checking in at your Place
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsViralUnique(String userId, String period);
	
	/**
	 * Total impressions of stories published by a friend about your Page by story type
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsByStoryType(String userId, int period);
	/**
	 * Total impressions of stories published by a friend about your Page by story type
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsByStoryType(String userId, String period);
	
	/**
	 * The number of people who saw a story about your Page by story type
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsByStoryTypeUnique(String userId, int period);
	/**
	 * The number of people who saw a story about your Page by story type
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsByStoryTypeUnique(String userId, String period);
	
	/**
	 * The number of people who have seen any content associated with your Page by city
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsByCityUnique(String userId, int period);
	
	/**
	 * The number of people who have seen any content associated with your Page by city
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsByCityUnique(String userId, String period);

	/**
	 * The number of people who have seen any content associated with your Page by country
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsByCountryUnique(String userId, int period);
	
	/**
	 * The number of people who have seen any content associated with your Page by country
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsByCountryUnique(String userId, String period);
	
	/**
	 * The number of people who have seen any content associated with your Page by locale
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsByLocaleUnique(String userId, int period);
	/**
	 * The number of people who have seen any content associated with your Page by locale
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsByLocaleUnique(String userId, String period);
	
	/**
	 * The number of people who have seen any content associated with your Page by age
	 * and gender grouping
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsByAgeGenderUnique(String userId, int period);
	
	/**
	 * The number of people who have seen any content associated with your Page by age
	 * and gender grouping
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsByAgeGenderUnique(String userId, String period);
	
	/**
	 * The number of people your Page reached broken down by how many times people saw
	 * any content about your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsFrequencyDistribution(String userId, int period);
	
	/**
	 * The number of people your Page reached broken down by how many times people saw
	 * any content about your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsFrequencyDistribution(String userId, String period);
	
	/**
	 * The number of people your Page reached from a story published by a friend, broken
	 * down by how many times people saw stories about your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsViralFrequencyDistribution(String userId, int period);
	
	/**
	 * The number of people your Page reached from a story published by a friend, broken
	 * down by how many times people saw stories about your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageImpressionsViralFrequencyDistribution(String userId, String period);
	
	
	
	//**********************************************************************
	// Page Engagement
	//**********************************************************************
	
	/**
	 * The number of people who engaged with your Page. Engagement includes any click
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageEngagedUsers(String userId, int period);
	
	/**
	 * The number of people who engaged with your Page. Engagement includes any click
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageEngagedUsers(String userId, String period);
	
	/**
	 * The number of times people clicked on any of your content without generating a story
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageConsumptions(String userId, int period);
	
	/**
	 * The number of times people clicked on any of your content without generating a story
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageConsumptions(String userId, String period);
	
	/**
	 * The number of people who clicked on any of your content without generating a story
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageConsumptionsUnique(String userId, int period);
	
	/**
	 * The number of people who clicked on any of your content without generating a story
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageConsumptionsUnique(String userId, String period);
	
	/**
	 * The number of times people clicked on any of your content without generating a
	 * story, by type
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageConsumptionsByConsumptionType(String userId, int period);
	
	/**
	 * The number of times people clicked on any of your content without generating a
	 * story, by type
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageConsumptionsByConsumptionType(String userId, String period);
	
	/**
	 * The number of people who clicked on any of your content without generating a 
	 * story, by type
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageConsumptionsByConsumptionTypeUnique(String userId, int period);
	
	/**
	 * The number of people who clicked on any of your content without generating a 
	 * story, by type
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageConsumptionsByConsumptionTypeUnique(String userId, String period);
	
	/**
	 * The number of times people checked into a place
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pagePlacesCheckinTotal(String userId, int period);
	/**
	 * The number of times people checked into a place
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pagePlacesCheckinTotal(String userId, String period);
	
	/**
	 * The number of people who checked into a place
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pagePlacesCheckinTotalUnique(String userId, int period);
	/**
	 * The number of people who checked into a place
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pagePlacesCheckinTotalUnique(String userId, String period);
	
	/**
	 * The number of times people checked into a place using mobile phones
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pagePlacesCheckinMobile(String userId, int period);
	/**
	 * The number of times people checked into a place using mobile phones
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pagePlacesCheckinMobile(String userId, String period);
	
	/**
	 * The number of people who checked into a place using mobile phones
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pagePlacesCheckinMobileUnique(String userId, int period);
	/**
	 * The number of people who checked into a place using mobile phones
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pagePlacesCheckinMobileUnique(String userId, String period);
	
	/**
	 * gender and age of people who checked in at your Place
	 * @return a {@link Insights}
	 */
	Insights pagePlacesCheckinsByAgeGender(String userId);
	
	/**
	 * top locales of people who checked into your Place
	 * @return a {@link Insights}
	 */
	Insights pagePlacesCheckinsByLocale(String userId);
	
	/**
	 * top cities of people who checked into your Place
	 * @return a {@link Insights}
	 */
	Insights pagePlacesCheckinsByCity(String userId);
	
	/**
	 * top countries of people who checked into your Place
	 * @return a {@link Insights}
	 */
	Insights pagePlacesCheckinsByCountry(String userId);
	
	/**
	 * The number of times people took a negative action (e.g., un-liked or hid a post)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageNegativeFeedback(String userId, int period);
	/**
	 * The number of times people took a negative action (e.g., un-liked or hid a post)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageNegativeFeedback(String userId, String period);
	
	/**
	 * The number of people who took a negative action (e.g., un-liked or hid a post)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageNegativeFeedbackUnique(String userId, int period);
	/**
	 * The number of people who took a negative action (e.g., un-liked or hid a post)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageNegativeFeedbackUnique(String userId, String period);
	
	/**
	 * The number of times people took a negative action broken down by type
	 * (see negative feedback types)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageNegativeFeedbackByType(String userId, int period);
	/**
	 * The number of times people took a negative action broken down by type
	 * (see negative feedback types)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageNegativeFeedbackByType(String userId, String period);
	
	/**
	 * The number of people who took a negative action broken down by type 
	 * (see negative feedback types)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageNegativeFeedbackByTypeUnique(String userId, int period);
	/**
	 * The number of people who took a negative action broken down by type 
	 * (see negative feedback types)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageNegativeFeedbackByTypeUnique(String userId, String period);
	
	
	//**********************************************************************
	// Page Users
	//**********************************************************************
	
	/**
	 * The total number of people who have liked your Page.
	 * @return a {@link Insights}
	 */
	Insights pageFans(String userId);
	
	/**
	 * Aggregated language data about the people who likeyour Page based on the default
	 * language setting selected when accessing Facebook.
	 * @return a {@link Insights}
	 */
	Insights pageFansLocale(String userId);
	
	/**
	 * Aggregated Facebook location data, sorted by city, about the people who like your Page.
	 * @return a {@link Insights}
	 */
	Insights pageFansCity(String userId);
	
	/**
	 * Aggregated Facebook location data, sorted by country, about the people who like your Page
	 * @return a {@link Insights}
	 */
	Insights pageFansCountry(String userId);
	
	/**
	 * Aggregated demographic data about the people who like your Page based on the
	 * gender information they provide in their user profiles.
	 * @return a {@link Insights}
	 */
	Insights pageFansGender(String userId);
	
	/**
	 * Aggregated demographic data about the people who like your Page based on the age
	 * information they provide in their user profiles.
	 * @return a {@link Insights}
	 */
	Insights pageFansAge(String userId);
	
	/**
	 * Aggregated demographic data about the people who like your Page based on the age and
	 * gender information they provide in their user profiles.
	 * @return a {@link Insights}
	 */
	Insights pageFansGenderAge(String userId);
	
	/**
	 * The number of new people who have liked your Page.
	 * @return a {@link Insights}
	 */
	Insights pageFanAdds(String userId);
	
	/**
	 * The number of new people who have liked your Page.
	 * @return a {@link Insights}
	 */
	Insights pageFanAddsUnique(String userId);
	
	/**
	 * This is a breakdown of the number of Page likes fromthe most common places where
	 * people can like your Page (see like sources).
	 * @return a {@link Insights}
	 */
	Insights pageFansByLikeSource(String userId);
	
	/**
	 * The number of people who liked your Page, broken down by the most common places where
	 * people can like your Page (see like sources).
	 * @return a {@link Insights}
	 */
	Insights pageFansByLikeSourceUnique(String userId);
	
	/**
	 * Unlikes of your Page
	 * @return a {@link Insights}
	 */
	Insights pageFanRemoves(String userId);
	
	/**
	 * Unlikes of your Page
	 * @return a {@link Insights}
	 */
	Insights pageFanRemovesUnique(String userId);
	
	/**
	 * The number of people who are friends of the Fans of your Page (estimated)
	 * @return a {@link Insights}
	 */
	Insights pageFriendsOfFans(String userId);

	
	
	//**********************************************************************
	// Page Content
	//**********************************************************************
	
	/**
	 * The number of users logged into Facebook who saw tabs on your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week)
	 * @return a {@link Insights}
	 */
	Insights pageTabViewsLoginTopUnique(String userId, int period);
	
	/**
	 * The number of users logged into Facebook who saw tabs on your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week"
	 * @return a {@link Insights}
	 */
	Insights pageTabViewsLoginTopUnique(String userId, String period);
	
	/**
	 * The number of times users logged into Facebook saw tabs on your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week)
	 * @return a {@link Insights}
	 */
	Insights pageTabViewsLoginTop(String userId, int period);
	
	/**
	 * The number of times users logged into Facebook saw tabs on your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week"
	 * @return a {@link Insights}
	 */
	Insights pageTabViewsLoginTop(String userId, String period);
	
	
	//**********************************************************************
	// Page Views
	//**********************************************************************
	
	/**
	 * Page views
	 * @return a {@link Insights}
	 */
	Insights pageViews(String userId);
	
	/**
	 * Page Views from users logged into Facebook
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week)
	 * @return a {@link Insights}
	 */
	Insights pageViewsUnique(String userId, int period);
	
	/**
	 * Page Views from users logged into Facebook
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week"
	 * @return a {@link Insights}
	 */
	Insights pageViewsUnique(String userId, String period);
	
	/**
	 * Page Views from users logged into Facebook
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week)
	 * @return a {@link Insights}
	 */
	Insights pageViewsLogin(String userId, int period);
	
	/**
	 * Page Views from users logged into Facebook
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week"
	 * @return a {@link Insights}
	 */
	Insights pageViewsLogin(String userId, String period);
	
	/**
	 * Page Views from users logged into Facebook
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week)
	 * @return a {@link Insights}
	 */
	Insights pageViewsLoginUnique(String userId, int period);
	
	/**
	 * Page Views from users logged into Facebook
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week"
	 * @return a {@link Insights}
	 */
	Insights pageViewsLoginUnique(String userId, String period);
	
	/**
	 * Page views from users not logged into Facebook
	 * @return a {@link Insights}
	 */
	Insights pageViewsLogout(String userId);
	
	/**
	 * Top referrering external domains sending traffic to your Page.
	 * @return a {@link Insights}
	 */
	Insights pageViewsExternalReferrals(String userId);

	
	
	//**********************************************************************
	// Page Posts
	//**********************************************************************
	
	/**
	 * The number of impressions that came from all of your posts
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressions(String userId, int period);
	
	/**
	 * The number of impressions that came from all of your posts
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressions(String userId, String period);
	
	/**
	 * The number of people who saw any of your Page posts
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsUnique(String userId, int period);
	
	/**
	 * The number of people who saw any of your Page posts
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsUnique(String userId, String period);
	
	/**
	 * The number of impressions of your Page posts in an Ad or Sponsored Story
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsPaid(String userId, int period);
	
	/**
	 * The number of impressions of your Page posts in an Ad or Sponsored Story
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsPaid(String userId, String period);
	
	/**
	 * The number of people who saw your Page posts in an Ad or Sponsored Story
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsPaidUnique(String userId, int period);
	
	/**
	 * The number of people who saw your Page posts in an Ad or Sponsored Story
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsPaidUnique(String userId, String period);
	
	/**
	 * The number of impressions of your posts in News Feed or Ticker or on your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsOrganic(String userId, int period);
	
	/**
	 * The number of impressions of your posts in News Feed or Ticker or on your Page
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsOrganic(String userId, String period);
	
	/**
	 * The number of people who saw your Page posts in News Feed or Ticker, or on your
	 * Page's Wall
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsOrganicUnique(String userId, int period);
	
	/**
	 * The number of people who saw your Page posts in News Feed or Ticker, or on your
	 * Page's Wall
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsOrganicUnique(String userId, String period);
	
	/**
	 * The number of times users saw your posts via stories published by their friends
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsViral(String userId, int period);
	
	/**
	 * The number of times users saw your posts via stories published by their friends
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsViral(String userId, String period);
	
	/**
	 * The number of people who saw your Page posts via a story from a friend
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsViralUnique(String userId, int period);
	
	/**
	 * The number of people who saw your Page posts via a story from a friend
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsViralUnique(String userId, String period);
	
	/**
	 * The number of people who saw your Page posts, broken down by how many times people
	 * saw your posts
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsFrequencyDistribution(String userId, int period);
	
	/**
	 * The number of people who saw your Page posts, broken down by how many times people
	 * saw your posts
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pagePostsImpressionsFrequencyDistribution(String userId, String period);
	
	
	
	
	//**********************************************************************
	// Page Post: Stories and People talking about this
	//**********************************************************************
	
	/**
	 * The number of stories generated about your Page post
	 * @return a {@link Insights}
	 */
	Insights postStories(String userId);
	
	/**
	 * The number of unique people who created a story about your Page post
	 * @return a {@link Insights}
	 */
	Insights postStorytellers(String userId);
	
	/**
	 * The number of stories created about your Page post, by action type
	 * @return a {@link Insights}
	 */
	Insights postStoriesByActionType(String userId);
	
	/**
	 * The number of unique people who created a story about your Page post, by action type
	 * @return a {@link Insights}
	 */
	Insights postStorytellersByActionType(String userId);

	
	
	//**********************************************************************
	// Page Post: Impressions
	//**********************************************************************
	
	/**
	 * The number of times your Page post was seen
	 * @return a {@link Insights}
	 */
	Insights postImpressions(String userId);	

	/**
	 * The number of people who saw your Page post
	 * @return a {@link Insights}
	 */
	Insights postImpressionsUnique(String userId);

	/**
	 * The number of impressions of your Page post in an Ad or Sponsored Story
	 * @return a {@link Insights}
	 */
	Insights postImpressionsPaid(String userId);

	/**
	 * The number of people who saw your Page post in an Ad or Sponsored Story
	 * @return a {@link Insights}
	 */
	Insights postImpressionsPaidUnique(String userId);

	/**
	 * The number of impressions of your post in Newsfeed, Ticker, or on your Page's Wall
	 * @return a {@link Insights}
	 */
	Insights postImpressionsOrganic(String userId);

	/**
	 * The number of people who saw your post in their Newsfeed or Ticker or on your Page's Wall
	 * @return a {@link Insights}
	 */
	Insights postImpressionsOrganicUnique(String userId);

	/**
	 * The number of impressions of your Page post in a story generated by a friend
	 * @return a {@link Insights}
	 */
	Insights postImpressionsViral(String userId);

	/**
	 * The number of people who saw your page post in a story from a friend
	 * @return a {@link Insights}
	 */
	Insights postImpressionsViralUnique(String userId);

	/**
	 * The number of times this post was seen via a story published by a friend of the person viewing the post
	 * @return a {@link Insights}
	 */
	Insights postImpressionsByStoryType(String userId);

	/**
	 * The number of people who saw your page post in a story from a friend, by story type
	 * @return a {@link Insights}
	 */
	Insights postImpressionsByStoryTypeUnique(String userId);
	
	
	//**********************************************************************
	// Page Post: Engagement
	//**********************************************************************
	
	/**
	 * The number of times people clicked on anywhere in your posts without generating a story
	 * @return a {@link Insights}
	 */
	Insights postConsumptions(String userId);
	
	/**
	 * The number of people who clicked anywhere in your post without generating a story
	 * @return a {@link Insights}
	 */
	Insights postConsumptionsUnique(String userId);
	
	/**
	 * The number of times people clicked on anywhere in your posts without generating a
	 * story, by consumption type
	 * @return a {@link Insights}
	 */
	Insights postConsumptionsByType(String userId);
	
	/**
	 * The number of people who clicked anywhere in your post without generating a story,
	 * by consumption type
	 * @return a {@link Insights}
	 */
	Insights postConsumptionsByTypeUnique(String userId);
	
	/**
	 * The number of people who clicked anywhere in your posts
	 * @return a {@link Insights}
	 */
	Insights postEngagedUsers(String userId);
	
	/**
	 * The number of times people took a negative action in your post (e.g. hid it)
	 * @return a {@link Insights}
	 */
	Insights postNegativeFeedback(String userId);
	
	/**
	 * The number of people who took a negative action in your post (e.g., hid it)
	 * @return a {@link Insights}
	 */
	Insights postNegativeFeedbackUnique(String userId);
	
	/**
	 * The number of times people took a negative action in your post broken down by type
	 * @return a {@link Insights}
	 */
	Insights postNegativeFeedbackByType(String userId);
	
	/**
	 * The number of people who took a negative action in your post broken down by type
	 * @return a {@link Insights}
	 */
	Insights postNegativeFeedbackByTypeUnique(String userId);

	
	
	
	//**********************************************************************
	// Domain Content
	//**********************************************************************
	
	/**
	 * The number of clicks sent to your site from stories in News Feed, Page Walls,
	 * or Profile Walls
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainFeedClicks(String userId, int period);
	
	/**
	 * The number of clicks sent to your site from stories in News Feed, Page Walls,
	 * or Profile Walls
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainFeedClicks(String userId, String period);
	
	/**
	 * The number of times people viewed stories that link to your site in News Feed,
	 * Page Walls, or Profile Walls
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainFeedViews(String userId, int period);
	
	/**
	 * The number of times people viewed stories that link to your site in News Feed,
	 * Page Walls, or Profile Walls
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainFeedViews(String userId, String period);
	
	/**
	 * The number of times people posted a link to your site through an action on a
	 * social plugin or through a status message or Wall post
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainStories(String userId, int period);
	
	/**
	 * The number of times people posted a link to your site through an action on a
	 * social plugin or through a status message or Wall post
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainStories(String userId, String period);
	
	/**
	 * The number of times people left comments on your site using the Comments plugin.
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainWidgetCommentsAdds(String userId, int period);
	
	/**
	 * The number of times people left comments on your site using the Comments plugin.
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainWidgetCommentsAdds(String userId, String period);
	
	/**
	 * Comments Box impressions on your domain
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainWidgetCommentsViews(String userId, int period);
	
	/**
	 * Comments Box impressions on your domain
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainWidgetCommentsViews(String userId, String period);
	
	/**
	 * The number of times people viewed stories generated from Comments Box comments on your site.
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainWidgetCommentsFeedViews(String userId, int period);
	
	/**
	 * The number of times people viewed stories generated from Comments Box comments on your site.
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainWidgetCommentsFeedViews(String userId, String period);
	
	/**
	 * The number of clicks sent to your site from Comment stories.
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainWidgetCommentsFeedClicks(String userId, int period);
	
	/**
	 * The number of clicks sent to your site from Comment stories.
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainWidgetCommentsFeedClicks(String userId, String period);
	
	/**
	 * The number of times people viewed Like buttons on your site
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainWidgetLikeViews(String userId, int period);
	
	/**
	 * The number of times people viewed Like buttons on your site
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainWidgetLikeViews(String userId, String period);
	
	/**
	 * The number of times people clicked the Like button on your site
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainWidgetLikes(String userId, int period);
	
	/**
	 * The number of times people clicked the Like button on your site
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */	
	Insights domainWidgetLikes(String userId, String period);
	
	/**
	 * The number of times people viewed stories generated from Like button clicks on your site
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainWidgetLikeFeedViews(String userId, int period);
	
	/**
	 * The number of times people viewed stories generated from Like button clicks on your site
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainWidgetLikeFeedViews(String userId, String period);
	
	/**
	 * The number of clicks sent to your site from stories in News Feed, Page Walls, or Profile Walls
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainWidgetLikeFeedClicks(String userId, int period);
	
	/**
	 * The number of clicks sent to your site from stories in News Feed, Page Walls, or Profile Walls
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainWidgetLikeFeedClicks(String userId, String period);
	
	/**
	 * 	Impressions on the Send Button on your domain
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainWidgetSendViews(String userId, int period);
	
	/**
	 * 	Impressions on the Send Button on your domain
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainWidgetSendViews(String userId, String period);
	
	/**
	 * The number of times people sent messages from your domain using the Send button
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainWidgetSendClicks(String userId, int period);
	
	/**
	 * The number of times people sent messages from your domain using the Send button
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainWidgetSendClicks(String userId, String period);
	
	/**
	 * Views of Inbox messages generated by the Send Button
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainWidgetSendInboxViews(String userId, int period);
	
	/**
	 * Views of Inbox messages generated by the Send Button
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainWidgetSendInboxViews(String userId, String period);
	
	/**
	 * The number of clicks sent to your site from Send button messages
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 0 (lifetime)
	 * @return a {@link Insights}
	 */
	Insights domainWidgetSendInboxClicks(String userId, int period);
	
	/**
	 * The number of clicks sent to your site from Send button messages
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "lifetime"
	 * @return a {@link Insights}
	 */
	Insights domainWidgetSendInboxClicks(String userId, String period);
	
	
	//**********************************************************************
	// Page Story Adds
	//**********************************************************************
	
	/**
	 * The number of stories created about your Page. (Total Count
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStoryAdds(String userId, int period);
	
	/**
	 * The number of stories created about your Page. (Total Count
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStoryAdds(String userId, String period);
	
	/**
	 * The number of people sharing stories about your page. These stories include
	 * liking your Page, posting to your Page's Wall, liking, commenting on or sharing one 
	 * of your Page posts, answering a Question you posted, RSVPing to one of your events, 
	 * mentioning your Page, phototagging your Page or checking in at your Place. (Unique Users)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsUnique(String userId, int period);
	
	/**
	 * The number of people sharing stories about your page. These stories include
	 * liking your Page, posting to your Page's Wall, liking, commenting on or sharing one 
	 * of your Page posts, answering a Question you posted, RSVPing to one of your events, 
	 * mentioning your Page, phototagging your Page or checking in at your Place. (Unique Users)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsUnique(String userId, String period);
	
	/**
	 * The number of stories about your Page by story type. (Total Count)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsByStoryType(String userId, int period);
	
	/**
	 * The number of stories about your Page by story type. (Total Count)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsByStoryType(String userId, String period);
	
	/**
	 * The number of people talking about your Page, by story type. (Unique Users)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsByStoryTypeUnique(String userId, int period);
	
	/**
	 * The number of people talking about your Page, by story type. (Unique Users)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsByStoryTypeUnique(String userId, String period);

	/**
	 * The number of People Talking About the Page by user age and gender (Unique Users)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsByAgeGenderUnique(String userId, int period);
	
	/**
	 * The number of People Talking About the Page by user age and gender (Unique Users)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsByAgeGenderUnique(String userId, String period);
	
	/**
	 * The number of People Talking About the Page by user country (Unique Users)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsByCountryUnique(String userId, int period);
	
	/**
	 * The number of People Talking About the Page by user country (Unique Users)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsByCountryUnique(String userId, String period);
	
	/**
	 * The number of People Talking About the Page by user city. (Unique Users)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsByCityUnique(String userId, int period);
	
	/**
	 * The number of People Talking About the Page by user city. (Unique Users)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsByCityUnique(String userId, String period);
	
	/**
	 * The number of People Talking About the Page by user language. (Unique Users)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed in seconds as one of 86400 (day), 604800 (week), 2419200 (28 days)
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsByLocaleUnique(String userId, int period);
	
	/**
	 * The number of People Talking About the Page by user language. (Unique Users)
	 * @param period The length of the period during which the metrics were collected,
	 * expressed as string as one of "day", "week", "days28"
	 * @return a {@link Insights}
	 */
	Insights pageStoryAddsByLocaleUnique(String userId, String period);
		
}
