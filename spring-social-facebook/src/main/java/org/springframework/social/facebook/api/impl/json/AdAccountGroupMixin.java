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
import org.springframework.social.facebook.api.AdAccount;
import org.springframework.social.facebook.api.AdAccountUser;

//@JsonIgnoreProperties(ignoreUnknown = true)
class AdAccountGroupMixin {

    @JsonCreator
    AdAccountGroupMixin(@JsonProperty("account_group_id") String accountGroupId,
                        @JsonProperty("id") String id,
                        @JsonProperty("name") String name,
                        @JsonProperty("status") int status,
                        @JsonProperty("users") List<AdAccountUser> users,
                        @JsonProperty("accounts") List<AdAccount> accounts) {}

    //@JsonIgnoreProperties(ignoreUnknown = true)
    static class LiteAdAccountUserMixin {
        @JsonCreator
        LiteAdAccountUserMixin(@JsonProperty("uid") String uid,
                               @JsonProperty("role") int role) {}
    }

    //@JsonIgnoreProperties(ignoreUnknown = true)
    static class LiteAdAccountMixIn {
        @JsonCreator
        LiteAdAccountMixIn(@JsonProperty("account_id") String accountId,
                           @JsonProperty("status") int status) {}
    }

}
