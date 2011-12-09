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
package org.springframework.social.facebook.api;

import java.util.List;

/**
 * Ad Account Group object on Facebook {@link http://developers.facebook.com/docs/reference/ads-api/adaccountgroup/}
 *
 * @author Will Taylor
 *
 */
public class AdAccountGroup {

    private final String accountGroupId;
    private final String id;
    private final String name;
    private final int status;
    private final List<LiteAdAccountUser> users;
    private final List<LiteAdAccount> accounts;

    public AdAccountGroup(String accountGroupId, String id, String name, int status, List<LiteAdAccountUser> users, List<LiteAdAccount> accounts) {
        this.accountGroupId = accountGroupId;
        this.id = id;
        this.name = name;
        this.status = status;
        this.users = Collections2.immutable(users);
        this.accounts = Collections2.immutable(accounts);
    }

    public String getAccountGroupId() {
        return accountGroupId;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public List<LiteAdAccountUser> getUsers() {
        return Collections2.copy(users);
    }

    public List<LiteAdAccount> getAccounts() {
        return Collections2.copy(accounts);
    }

    public static class LiteAdAccountUser {
        private final String uid;
        private final int role;

        public LiteAdAccountUser(String uid, int role) {
            this.uid = uid;
            this.role = role;
        }

        public String getUid() {
            return uid;
        }

        public int getRole() {
            return role;
        }
    }

    public static class LiteAdAccount {
        private final String accountId;
        private final int status;

        public LiteAdAccount(String accountId, int status) {
            this.accountId = accountId;
            this.status = status;
        }

        public String getAccountId() {
            return accountId;
        }

        public int getStatus() {
            return status;
        }
    }

}
