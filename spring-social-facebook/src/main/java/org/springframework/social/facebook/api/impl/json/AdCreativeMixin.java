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
package org.springframework.social.facebook.api.impl.json;

import java.util.List;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonProperty;

//@JsonIgnoreProperties(ignoreUnknown = true)
class AdCreativeMixin {

    @JsonCreator
    AdCreativeMixin(@JsonProperty("name") String name,
                    @JsonProperty("type") int type,
                    @JsonProperty("object_id") long objectId,
                    @JsonProperty("body") String body,
                    @JsonProperty("image_hash") String imageHash,
                    @JsonProperty("image_url") String imageUrl,
                    @JsonProperty("creative_id") long creativeId,
                    @JsonProperty("count_current_adgroups") int countCurrentAdGroups,
                    @JsonProperty("title") String title,
                    @JsonProperty("run_status") int runStatus,
                    @JsonProperty("link_url") String linkUrl,
                    @JsonProperty("preview_url") String previewUrl,
                    @JsonProperty("view_tag") String viewTag,
                    @JsonProperty("alt_view_tags") List<String> altViewTags,
                    @JsonProperty("id") String id,
                    @JsonProperty("url_tags") String urlTags) {}

}
