package org.springframework.social.facebook.itest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Reference;


public class FriendTemplateTest extends AbstractFacebookITest implements ShowcaseApp {
	
	@Test
	public void createFriendList() {
		String friendListId = fb.friendOperations().createFriendList("My Good Friends");
		assertNotNull(friendListId);
		
		PagedList<Reference> friendLists = fb.friendOperations().getFriendLists();
		assertEquals(1, friendLists.size());
		
		Reference friendList = fb.friendOperations().getFriendList(friendListId);
		assertEquals(friendListId, friendList.getId());
		assertEquals("My Good Friends", friendList.getName());
		
		fb.friendOperations().deleteFriendList(friendListId);
		friendLists = fb.friendOperations().getFriendLists();
		assertEquals(0, friendLists.size());
	}
	
	
	
	@Override
	protected AppCredentials getAppCredentials() {
		return new AppCredentials(APP_ID, APP_SECRET, APP_NAMESPACE);
	}

}
