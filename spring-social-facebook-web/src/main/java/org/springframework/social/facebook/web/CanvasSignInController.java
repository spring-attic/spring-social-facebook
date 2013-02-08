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
package org.springframework.social.facebook.web;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Sign in controller that uses the signed_request parameter that Facebook gives to Canvas applications to obtain an access token.
 * If no access token exists in signed_request, this controller will redirect the top-level browser window to Facebook's authorization dialog.
 * When Facebook redirects back from the authorization dialog, the signed_request parameter should contain an access token.
 * @author Craig Walls
 */
@Controller
@RequestMapping(value="/canvas")
public class CanvasSignInController {
	
	private final static Log logger = LogFactory.getLog(CanvasSignInController.class);

	private final String clientId;
	
	private final String canvasPage;

	private final ConnectionFactoryLocator connectionFactoryLocator;

	private final UsersConnectionRepository usersConnectionRepository;

	private final SignInAdapter signInAdapter;
	
	private final SignedRequestDecoder signedRequestDecoder;
	
	private String postSignInUrl = "/";

	private String scope;

	@Inject
	public CanvasSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository, SignInAdapter signInAdapter, String clientId, String clientSecret, String canvasPage) {
		this.usersConnectionRepository = usersConnectionRepository;
		this.signInAdapter = signInAdapter;
		this.clientId = clientId;
		this.canvasPage = canvasPage;
		this.connectionFactoryLocator = connectionFactoryLocator;
		this.signedRequestDecoder = new SignedRequestDecoder(clientSecret);
	}
	
	/**
	 * The URL or path to redirect to after successful canvas authorization.
	 * Defaults to "/".
	 */
	public void setPostSignInUrl(String postSignInUrl) {
		this.postSignInUrl = postSignInUrl;
	}
	
	/**
	 * The scope to request during authorization.
	 * Defaults to null (no scope will be requested; Facebook will offer their default scope).
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	@RequestMapping(method=RequestMethod.POST)
	public View signin(Model model, NativeWebRequest request) throws SignedRequestException {
		String signedRequest = request.getParameter("signed_request");
		if (signedRequest == null) {
			logger.info("Expected a signed_request parameter, but none given. Redirecting to the application's Canvas Page: " + canvasPage);
			return new RedirectView(canvasPage, false);
		}
		
		Map<String, ?> decodedSignedRequest = signedRequestDecoder.decodeSignedRequest(signedRequest);
		String accessToken = (String) decodedSignedRequest.get("oauth_token");
		if (accessToken == null) {
			logger.info("No access token in the signed_request parameter. Redirecting to the authorization dialog.");
			model.addAttribute("clientId", clientId);
			model.addAttribute("canvasPage", canvasPage);
			if (scope != null) {
				model.addAttribute("scope", scope);
			}
			return new AuthorizationDialogRedirectView();
		}

		logger.info("Access token available in signed_request parameter. Creating connection and signing in.");
		OAuth2ConnectionFactory<Facebook> connectionFactory = (OAuth2ConnectionFactory<Facebook>) connectionFactoryLocator.getConnectionFactory(Facebook.class);
		AccessGrant accessGrant = new AccessGrant(accessToken);
		Connection<Facebook> connection = connectionFactory.createConnection(accessGrant);
		handleSignIn(connection, request);
		logger.info("Signed in. Redirecting to post-signin page.");
		return new RedirectView(postSignInUrl, true); 
	}

	
	private void handleSignIn(Connection<Facebook> connection, NativeWebRequest request) {
		List<String> userIds = usersConnectionRepository.findUserIdsWithConnection(connection);
		if (userIds.size() == 1) {
			usersConnectionRepository.createConnectionRepository(userIds.get(0)).updateConnection(connection);
			signInAdapter.signIn(userIds.get(0), connection, request);
		} else {
			// TODO: This should never happen, but need to figure out what to do if it does happen. 
			logger.error("Expected exactly 1 matching user. Got " + userIds.size() + " metching users.");
		}
	}
	
	private static final class AuthorizationDialogRedirectView implements View {
		public String getContentType() {
			return "text/html";
		}

		public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
			String clientId = (String) model.get("clientId");
			String canvasPage = (String) model.get("canvasPage");
			String scope = (String) model.get("scope");
			response.getWriter().write("<script>");
			response.getWriter().write("top.location.href='https://www.facebook.com/dialog/oauth?client_id=" + clientId + "&redirect_uri=" + canvasPage);
			if (scope != null) {
				response.getWriter().write("&scope=" + formEncode(scope));
			}
			response.getWriter().write("';");
			response.getWriter().write("</script>");
			response.flushBuffer();
		}

		private String formEncode(String data) {
			try {
				return URLEncoder.encode(data, "UTF-8");
			}
			catch (UnsupportedEncodingException ex) {
				// should not happen, UTF-8 is always supported
				throw new IllegalStateException(ex);
			}
		}
	}
	
}
