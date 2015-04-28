package org.springframework.social.facebook.api.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class AdInsightActionMixin extends FacebookObjectMixin {
    @JsonProperty("action_type")
    private String actionType;

    @JsonProperty("value")
    private double value;
}
