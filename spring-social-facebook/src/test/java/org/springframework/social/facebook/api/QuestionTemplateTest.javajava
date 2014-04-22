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

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * @author Craig Walls
 */
public class QuestionTemplateTest extends AbstractFacebookApiTest {

	@Test
	public void askQuestion() {
		String requestBody = "question=What+is+your+favorite+color%3F";
		mockServer.expect(requestTo("https://graph.facebook.com/me/questions"))
				.andExpect(method(POST))
				.andExpect(content().string(requestBody))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("id-only"), MediaType.APPLICATION_JSON));
		assertEquals("297875170268724", facebook.questionOperations().askQuestion("What is your favorite color?"));
	}

	@Test
	public void addOption() {
		String requestBody = "option=Dallas+Cowboys";
		mockServer.expect(requestTo("https://graph.facebook.com/297875170268725/options"))
				.andExpect(method(POST))
				.andExpect(content().string(requestBody))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("id-only"), MediaType.APPLICATION_JSON));
		assertEquals("297875170268724", facebook.questionOperations().addOption("297875170268725", "Dallas Cowboys"));
	}

	@Test
	public void getQuestion_noOptions() {
		mockServer.expect(requestTo("https://graph.facebook.com/297875170268724"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("question-without-options"), MediaType.APPLICATION_JSON));
		Question question = facebook.questionOperations().getQuestion("297875170268724");
		assertSingleQuestion(question);
		List<QuestionOption> options = question.getOptions();
		assertEquals(0, options.size());
	}

	@Test
	public void getQuestion_withOptions() {
		mockServer.expect(requestTo("https://graph.facebook.com/297875170268724"))
				.andExpect(method(GET))
				.andExpect(header("Authorization", "OAuth someAccessToken"))
				.andRespond(withSuccess(jsonResource("question-with-options"), MediaType.APPLICATION_JSON));
		Question question = facebook.questionOperations().getQuestion("297875170268724");
		assertSingleQuestion(question);
		List<QuestionOption> options = question.getOptions();
		assertOptionsList(options);
	}
	
	@Test
	public void getQuestions_forAuthenticatedUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/me/questions"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("questions"), MediaType.APPLICATION_JSON));
		List<Question> questions = facebook.questionOperations().getQuestions();
		assertQuestionList(questions);		
	}

	@Test
	public void getQuestions_forSpecificUser() {
		mockServer.expect(requestTo("https://graph.facebook.com/100001387295207/questions"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("questions"), MediaType.APPLICATION_JSON));
		List<Question> questions = facebook.questionOperations().getQuestions("100001387295207");
		assertQuestionList(questions);		
	}

	@Test
	public void deleteQuestion() {
		mockServer.expect(requestTo("https://graph.facebook.com/297875170268725"))
			.andExpect(method(POST))
			.andExpect(content().string("method=delete"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		facebook.questionOperations().deleteQuestion("297875170268725");
		mockServer.verify();
	}
	
	@Test
	public void getOption() {
		mockServer.expect(requestTo("https://graph.facebook.com/338689702832185"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("question-option"), MediaType.APPLICATION_JSON));
		QuestionOption option = facebook.questionOperations().getOption("338689702832185");
		assertSingleOption(option);
	}

	@Test
	public void getQuestionOptions() {
		mockServer.expect(requestTo("https://graph.facebook.com/338689702832185/options"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("question-options"), MediaType.APPLICATION_JSON));
		List<QuestionOption> options = facebook.questionOperations().getOptions("338689702832185");
		assertOptionsList(options);
	}
	
	@Test
	public void deleteOption() {
		mockServer.expect(requestTo("https://graph.facebook.com/297875170268725"))
			.andExpect(method(POST))
			.andExpect(content().string("method=delete"))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess("", MediaType.APPLICATION_JSON));
		facebook.questionOperations().deleteOption("297875170268725");
		mockServer.verify();
	}

	private void assertSingleQuestion(Question question) {
		assertEquals("297875170268724", question.getId());
		assertEquals("What's your favorite football team?", question.getText());
		assertEquals("100001387295207", question.getFrom().getId());
		assertEquals("Art Names", question.getFrom().getName());
		assertEquals(toDate("2012-02-03T17:40:36+0000"), question.getCreatedTime());
		assertEquals(toDate("2012-02-03T17:40:36+0000"), question.getUpdatedTime());
	}

	private void assertQuestionList(List<Question> questions) {
		assertEquals(2, questions.size());
		assertSingleQuestion(questions.get(0));
		Question question2 = questions.get(1);
		assertEquals("297905380265703", question2.getId());
		assertEquals("What is your favorite ice cream?", question2.getText());
		assertEquals("100001387295207", question2.getFrom().getId());
		assertEquals("Art Names", question2.getFrom().getName());
		assertEquals(toDate("2012-02-03T18:27:06+0000"), question2.getCreatedTime());
		assertEquals(toDate("2012-02-03T18:27:06+0000"), question2.getUpdatedTime());
	}

	private void assertSingleOption(QuestionOption option) {
		assertEquals("338689702832185", option.getId());
		assertEquals("Dallas Cowboys", option.getName());
		assertEquals(1023, option.getVotes());
		assertEquals("100001387295207", option.getFrom().getId());
		assertEquals("Art Names", option.getFrom().getName());
		assertEquals(toDate("2012-02-03T18:44:51+0000"), option.getCreatedTime());
	}

	private void assertOptionsList(List<QuestionOption> options) {
		assertEquals(2, options.size());
		QuestionOption option1 = options.get(0);
		assertSingleOption(option1);
		QuestionOption option2 = options.get(1);
		assertEquals("367412276605710", option2.getId());
		assertEquals("New York Giants", option2.getName());
		assertEquals(981, option2.getVotes());
		assertEquals("100001387295207", option2.getFrom().getId());
		assertEquals("Art Names", option2.getFrom().getName());
		assertEquals(toDate("2012-02-03T18:46:43+0000"), option2.getCreatedTime());
	}
	
}
