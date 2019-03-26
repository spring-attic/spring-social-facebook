/*
 * Copyright 2015 the original author or authors.
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
import org.springframework.web.bind.annotation.RequestParam;
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
	
	private String postDeclineUrl = "https://www.facebook.com";

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
	 * @param postSignInUrl the url to redirect to after successful canvas authorization. Defaults to "/".
	 */
	public void setPostSignInUrl(String postSignInUrl) {
		this.postSignInUrl = postSignInUrl;
	}
	
	/**
	 * The URL or path to redirect to if a user declines authorization.
	 * The redirect will happen in the top-level window. 
	 * If you want the redirect to happen in the canvas iframe, then override the {@link #postDeclineView()} method to return a different implementation of {@link View}.
	 * @param postDeclineUrl the url to redirect to after a user declines authorization. Defaults to "https://www.facebook.com".
	 */
	public void setPostDeclineUrl(String postDeclineUrl) {
		this.postDeclineUrl = postDeclineUrl;
	}
	
	/**
	 * The scope to request during authorization.
	 * @param scope the scope to request. Defaults to null (no scope will be requested; Facebook will offer their default scope).
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}

	@RequestMapping(method={ RequestMethod.POST, RequestMethod.GET }, params={"signed_request", "!error"})
	public View signin(Model model, NativeWebRequest request) throws SignedRequestException {
		String signedRequest = request.getParameter("signed_request");
		if (signedRequest == null) {
			debug("Expected a signed_request parameter, but none given. Redirecting to the application's Canvas Page: " + canvasPage);
			return new RedirectView(canvasPage, false);
		}
		
		Map<String, ?> decodedSignedRequest = signedRequestDecoder.decodeSignedRequest(signedRequest);
		String accessToken = (String) decodedSignedRequest.get("oauth_token");
		if (accessToken == null) {
			debug("No access token in the signed_request parameter. Redirecting to the authorization dialog.");
			model.addAttribute("clientId", clientId);
			model.addAttribute("canvasPage", canvasPage);
			if (scope != null) {
				model.addAttribute("scope", scope);
			}
			return new TopLevelWindowRedirect() {
				@Override
				protected String getRedirectUrl(Map<String, ?> model) {
					String clientId = (String) model.get("clientId");
					String canvasPage = (String) model.get("canvasPage");
					String scope = (String) model.get("scope");
					String redirectUrl = "https://www.facebook.com/v2.8/dialog/oauth?client_id=" + clientId + "&redirect_uri=" + canvasPage;
					if (scope != null) {
						redirectUrl += "&scope=" + formEncode(scope);
					}
					return redirectUrl;
				}
			};
		}

		debug("Access token available in signed_request parameter. Creating connection and signing in.");
		OAuth2ConnectionFactory<Facebook> connectionFactory = (OAuth2ConnectionFactory<Facebook>) connectionFactoryLocator.getConnectionFactory(Facebook.class);
		AccessGrant accessGrant = new AccessGrant(accessToken);
		// TODO: Maybe should create via ConnectionData instead?
		Connection<Facebook> connection = connectionFactory.createConnection(accessGrant);
		handleSignIn(connection, request);
		debug("Signed in. Redirecting to post-signin page.");
		return new RedirectView(postSignInUrl, true); 
	}

	@RequestMapping(method={ RequestMethod.POST, RequestMethod.GET }, params="error")
	public View error(@RequestParam("error") String error, @RequestParam("error_description") String errorDescription) {
		String string = "User declined authorization: '" + errorDescription + "'. Redirecting to " + postDeclineUrl;
		debug(string);
		return postDeclineView();
	}

	/**
	 * View that redirects the top level window to the URL defined in postDeclineUrl property after user declines to authorize application.
	 * May be overridden for custom views, particularly in the case where the post-decline view should be rendered in-canvas.
	 * @return a view to display after a user declines authoriation. Defaults as a redirect to postDeclineUrl
	 */
	protected View postDeclineView() {
		return new TopLevelWindowRedirect() {
			@Override
			protected String getRedirectUrl(Map<String, ?> model) {
				return postDeclineUrl;
			}
		};
	}
	
	private void debug(String string) {
		if (logger.isDebugEnabled()) {
			logger.debug(string);
		}
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
	
	private static abstract class TopLevelWindowRedirect implements View {
		
		public String getContentType() {
			return "text/html";
		}

		public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
			response.getWriter().write("<script>");
			response.getWriter().write("top.location.href='" + getRedirectUrl(model) + "';");
			response.getWriter().write("</script>");
			response.flushBuffer();
		}
		
		protected abstract String getRedirectUrl(Map<String, ?> model);

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
