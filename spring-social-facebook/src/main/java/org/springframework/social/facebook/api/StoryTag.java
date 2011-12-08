package org.springframework.social.facebook.api;

public class StoryTag {
	private final String id;
	private final String name;
	private final Integer offset;
	private final Integer length;
	
	public StoryTag(String id, String name, Integer offset, Integer length) {
		this.id = id;
		this.name = name;
		this.offset = offset;
		this.length = length;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getOffset() {
		return offset;
	}

	public Integer getLength() {
		return length;
	}
}
