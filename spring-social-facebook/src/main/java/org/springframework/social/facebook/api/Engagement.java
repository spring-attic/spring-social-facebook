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
package org.springframework.social.facebook.api;

public class Engagement extends FacebookObject {

	private int count;
	
	private String countString;
	
	private String countStringWithLike;
	
	private String countStringWithoutLike;
	
	private String socialSentence;
	
	private String socialSentenceWithLike;
	
	private String socialSentenceWithoutLike;
	
	public int getCount() {
		return count;
	}
	
	public String getCountString() {
		return countString;
	}
	
	public String getCountStringWithLike() {
		return countStringWithLike;
	}
	
	public String getCountStringWithoutLike() {
		return countStringWithoutLike;
	}
	
	public String getSocialSentence() {
		return socialSentence;
	}
	
	public String getSocialSentenceWithLike() {
		return socialSentenceWithLike;
	}
	
	public String getSocialSentenceWithoutLike() {
		return socialSentenceWithoutLike;
	}

}
