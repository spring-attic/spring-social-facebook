/*
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.facebook.api.ads.impl.json;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;
import org.springframework.social.facebook.api.Identifier;
import org.springframework.social.facebook.api.ads.AccountStats;
import org.springframework.social.facebook.api.ads.AdAccount;
import org.springframework.social.facebook.api.ads.AdAccountGroup;
import org.springframework.social.facebook.api.ads.AdCampaign;
import org.springframework.social.facebook.api.ads.AdCreative;
import org.springframework.social.facebook.api.ads.AdGroup;
import org.springframework.social.facebook.api.ads.AdGroupStats;
import org.springframework.social.facebook.api.ads.CampaignStats;
import org.springframework.social.facebook.api.ads.Estimation;
import org.springframework.social.facebook.api.ads.ReachEstimate;
import org.springframework.social.facebook.api.ads.Stats;
import org.springframework.social.facebook.api.ads.Targeting;
import org.springframework.social.facebook.api.ads.User;
import org.springframework.social.facebook.api.impl.json.IdentifierMixin;

/**
 * The <code>FacebookAdsModule</code> registers the Jackson mixins for the
 * Facebook ad objects.
 * 
 * @author Karthick Sankarachary
 */
public class FacebookAdsModule extends SimpleModule {

	public FacebookAdsModule() {
		super("FacebookAdsModule", new Version(1, 0, 0, null));
	}

	@Override
	public void setupModule(SetupContext context) {
		context.setMixInAnnotations(AdAccount.class, AdAccountMixin.class);
		context.setMixInAnnotations(AdAccountGroup.class,
				AdAccountGroupMixin.class);
		context.setMixInAnnotations(AdCampaign.class, AdCampaignMixin.class);
		context.setMixInAnnotations(AdCreative.class, AdCreativeMixin.class);
		context.setMixInAnnotations(AdGroup.class, AdGroupMixin.class);

		context.setMixInAnnotations(Stats.class, StatsMixin.class);
		context.setMixInAnnotations(User.class, UserMixin.class);
		context.setMixInAnnotations(Targeting.class, TargetingMixin.class);

		context.setMixInAnnotations(ReachEstimate.class,
				ReachEstimateMixin.class);
		context.setMixInAnnotations(Estimation.class, EstimationMixin.class);
		context.setMixInAnnotations(Identifier.class, IdentifierMixin.class);

		context.setMixInAnnotations(AdGroupStats.class, AdGroupStatsMixin.class);
		context.setMixInAnnotations(AccountStats.class, AccountStatsMixin.class);
		context.setMixInAnnotations(CampaignStats.class, CampaignStatsMixin.class);
	}
}
