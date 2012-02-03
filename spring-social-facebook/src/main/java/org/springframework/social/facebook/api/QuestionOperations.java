/*
 * Copyright 2012 the original author or authors.
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

import org.springframework.social.ApiException;
import org.springframework.social.InsufficientPermissionException;
import org.springframework.social.MissingAuthorizationException;

public interface QuestionOperations {

	/**
	 * Publishes a question.
	 * Requires "publish_stream" permission.
	 * @param questionText the question text
	 * @return the ID of the newly created question
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String askQuestion(String questionText);
	
	/**
	 * Adds an option to a question.
	 * Requires "publish_stream" permission.
	 * @param questionId the question to add the option to
	 * @param optionText the text of the option
	 * @return the ID of the newly created option
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	String addOption(String questionId, String optionText);

	/**
	 * Retrieves a question.
	 * Requires "user_questions" permission to retrieve a question from the authenticated user and "friends_questions" to retrieve a question
	 * from one of the authenticated user's friends.
	 * @param questionId the ID of the question
	 * @return the {@link Question}
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	Question getQuestion(String questionId);
	
	/**
	 * Retrieves all questions asked by the authenticated user.
	 * Requires "user_questions" permission to retrieve questions from the authenticated user.
	 * @return a list of {@link Question}s
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Question> getQuestions();

	/**
	 * Retrieves all questions asked by the specified user.
	 * Requires "user_questions" permission to retrieve questions from the authenticated user and "friends_questions" to retrieve questions
	 * from one of the authenticated user's friends.
	 * @return a list of {@link Question}s
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<Question> getQuestions(String userId);

	/**
	 * Retrieves a question option.
	 * Requires "user_questions" permission to retrieve an option from the authenticated user and "friends_questions" to retrieve an option
	 * from one of the authenticated user's friends.
	 * @param optionId the ID of the option
	 * @return the {@link QuestionOption}
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	QuestionOption getOption(String optionId);

	/**
	 * Retrieves all options for a specified question.
	 * Requires "user_questions" permission to retrieve options from the authenticated user and "friends_questions" to retrieve options
	 * from one of the authenticated user's friends.
	 * @param questionId the ID of the question to retrieve options for
	 * @return a list of {@link QuestionOption}s
	 * @throws ApiException if there is an error while communicating with Facebook.
	 * @throws InsufficientPermissionException if the user has not granted "publish_stream" permission.
	 * @throws MissingAuthorizationException if FacebookTemplate was not created with an access token.
	 */
	List<QuestionOption> getOptions(String questionId);

}
