/*
 * Copyright 2010 the original author or authors.
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

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.social.facebook.api.ads.Estimation;

/**
 * A Jackson mixin for the {@link Estimation} object.
 * @author Karthick Sankarachary
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class EstimationMixin {

	@JsonCreator
	EstimationMixin() {
	}

	@JsonProperty("cpc_min")
	int cpcMin;

	@JsonProperty("cpc_median")
	int cpcMedian;

	@JsonProperty("cpc_max")
	int cpcMax;

	@JsonProperty("cpm_min")
	int cpmMin;

	@JsonProperty("cpm_median")
	int cpmMedian;

	@JsonProperty("cpm_max")
	int cpmMax;
}
