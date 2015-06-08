package org.springframework.social.facebook.api.ads.impl.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.social.facebook.api.impl.json.FacebookObjectMixin;

/**
 * @author Sebastian Górecki
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BidInfoMixin extends FacebookObjectMixin {
}
