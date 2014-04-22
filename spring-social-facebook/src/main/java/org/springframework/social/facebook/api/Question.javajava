/*
 * Copyright 2013 the original author or authors.
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

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Model class representing a question asked by a Facebook user.
 * @author habuma
 */
public class Question extends FacebookObject {

	private final String id;
	
	private final String text;

	private final Date createdTime;

	private final Date updatedTime;
	
	private final Reference from;
	
	private List<QuestionOption> options;

	public Question(String id, String text, Reference from, Date createdTime, Date updatedTime) {
		this.id = id;
		this.text = text;
		this.createdTime = createdTime;
		this.updatedTime = updatedTime;
		this.from = from;
	}

	public String getId() {
		return id;
	}

	public String getText() {
		return text;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getUpdatedTime() {
		return updatedTime;
	}

	public Reference getFrom() {
		return from;
	}

	public List<QuestionOption> getOptions() {
		return options != null ? options : Collections.<QuestionOption>emptyList();
	}
}
