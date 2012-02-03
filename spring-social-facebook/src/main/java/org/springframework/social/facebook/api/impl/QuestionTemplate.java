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
package org.springframework.social.facebook.api.impl;

import java.util.List;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.Question;
import org.springframework.social.facebook.api.QuestionOperations;
import org.springframework.social.facebook.api.QuestionOption;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

class QuestionTemplate extends AbstractFacebookOperations implements QuestionOperations {

	private GraphApi graphApi;
	
	public QuestionTemplate(GraphApi graphApi, boolean isAuthorized) {
		super(isAuthorized);
		this.graphApi = graphApi;
	}
	
	public String askQuestion(String questionText) {
		requireAuthorization();
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("question", questionText);
		return graphApi.publish("me", "questions", data);
	}
	
	public String addOption(String questionId, String optionText) {
		requireAuthorization();
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("option", optionText);
		return graphApi.publish(questionId, "options", data);
	}
	
	public List<Question> getQuestions() {
		return getQuestions("me");
	}
	
	public List<Question> getQuestions(String userId) {
		requireAuthorization();
		return graphApi.fetchConnections(userId, "questions", Question.class);
	}

	public Question getQuestion(String questionId) {
		requireAuthorization();
		return graphApi.fetchObject(questionId, Question.class);
	}
	
	public QuestionOption getOption(String optionId) {
		requireAuthorization();
		return graphApi.fetchObject(optionId, QuestionOption.class);
	}

	public List<QuestionOption> getOptions(String questionId) {
		requireAuthorization();
		return graphApi.fetchConnections(questionId, "options", QuestionOption.class);
	}

}
