/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.facebook.itest;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.springframework.social.facebook.api.Account;
import org.springframework.social.facebook.api.Page;
import org.springframework.social.facebook.api.PageOperations;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.TestUser;
import org.springframework.social.facebook.api.impl.FacebookTemplate;

public class PageOperationsITests extends FacebookITest implements ITestCredentials {

	private static final String SPRING_SOCIAL_PAGE_ID = "197280340404056";

	private static final Logger logger = Logger.getLogger(PageOperationsITests.class.getName());
	
	private TestUser testUser1;
	private PageOperations pageOps1;

	public PageOperationsITests() {
		super(APP_ID, APP_SECRET);
	}

	@Before
	public void setupTestUsers() {
		testUser1 = createTestUser(true, "publish_actions,read_stream,user_posts,user_tagged_places", "Alice Arensen");
		pageOps1 = new FacebookTemplate(testUser1.getAccessToken()).pageOperations();
		logger.info("CREATED TEST USERS: " + testUser1.getId());
	}

	@Test
	public void pageTests() {
		// NOTE: Only able to do surface tests
		
		PagedList<Account> accounts = pageOps1.getAccounts();
		assertEquals(0, accounts.size());
		assertFalse(pageOps1.isPageAdmin(SPRING_SOCIAL_PAGE_ID));
		
		Page page = pageOps1.getPage(SPRING_SOCIAL_PAGE_ID);
		assertEquals("Spring Social", page.getName());
		
		// Can't post to page as page administrator because the test user isn't a page admin. Therefore, the postXXX()
		// methods can't be automatically tests.
	}

}
