package org.springframework.social.facebook.itest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.LinkPost;
import org.springframework.social.facebook.api.NotePost;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.Post.PostType;
import org.springframework.social.facebook.api.StatusPost;


public class FeedTemplateTest extends AbstractFacebookITest implements ShowcaseApp {

	// TODO : Do deeper testing at some point. For now, it's enough to know that the API request didn't fail.
	
	private List<String> feedIds;
	
	@Before
	public void loadFeed() {
		feedIds = new ArrayList<String>();
		feedIds.add(fb.feedOperations().updateStatus("Hello Facebook"));
		feedIds.add(fb.feedOperations().updateStatus("How's it going?"));
		feedIds.add(fb.feedOperations().updateStatus("Okay, goodbye!"));
		feedIds.add(fb.feedOperations().postLink("Here's a link", new FacebookLink("http://spring.io", "Spring.io", "Spring", "The Spring Framework website")));
	}
	
	@Test
	public void updateStatus_and_getFeed() {
		PagedList<Post> feed = fb.feedOperations().getFeed();
		assertEquals(feedIds.size(), feed.size());
	}

	@Test
	public void deletePost() {
		PagedList<Post> feed = fb.feedOperations().getFeed();
		assertEquals(feedIds.size(), feed.size());
		fb.feedOperations().deletePost(feedIds.get(0));
		feed = fb.feedOperations().getFeed();
		assertEquals(feedIds.size() - 1, feed.size());
	}
	
	@Test
	public void getHomeFeed() {
		PagedList<Post> feed = fb.feedOperations().getHomeFeed();
		assertEquals(feedIds.size(), feed.size());
	}
	
	@Test
	public void getLinks() {
		PagedList<LinkPost> feed = fb.feedOperations().getLinks();
		assertEquals(1, feed.size());
	}
	
	@Test
	public void getNotes() {
		PagedList<NotePost> notes = fb.feedOperations().getNotes();
		assertEquals(0, notes.size());
	}
	
	@Test
	public void getPosts() {
		PagedList<Post> posts = fb.feedOperations().getPosts();
		assertEquals(4, posts.size());
	}
	
	@Test
	public void getStatuses() {
		PagedList<StatusPost> posts = fb.feedOperations().getStatuses();
		assertEquals(3, posts.size());
	}

	@Test
	public void getPost() {
		Post post = fb.feedOperations().getPost(feedIds.get(3));
		assertEquals(PostType.LINK, post.getType());
	}
	
	@Override
	protected AppCredentials getAppCredentials() {
		return new AppCredentials(APP_ID, APP_SECRET, APP_NAMESPACE);
	}

}
