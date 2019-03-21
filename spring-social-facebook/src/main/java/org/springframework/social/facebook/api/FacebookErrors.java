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

/**
 * A set of constants for all of Facebook's error codes. Not all of these errors are necessarily handled by FacebookErrorHandler
 * nor are all of them necessarily thrown by the API binding. This just gathers them in one place for convenience in defining
 * the error handler.
 * @author Craig Walls
 */
public class FacebookErrors {

	// General errors
	public static final int SUCCESS = 0;
	public static final int UNKNOWN = 1;
	public static final int SERVICE = 2;
	public static final int METHOD = 3;
	public static final int TOO_MANY_CALLS = 4;
	public static final int BAD_IP = 5;
	public static final int HOST_API = 6;
	public static final int HOST_UP = 7;
	public static final int SECURE = 8;
	public static final int RATE = 9;
	public static final int PERMISSION_DENIED = 10;
	public static final int DEPRECATED = 11;
	public static final int VERSION = 12;
	public static final int INTERNAL_FQL_ERROR = 13;
	public static final int HOST_PUP = 14;
	public static final int SESSION_SECRET_NOT_ALLOWED = 15;
	public static final int HOST_READONLY = 16;
	public static final int USER_TOO_MANY_CALLS = 17;
	public static final int REQUEST_RESOURCES_EXCEEDED = 18;
	
	// Parameter errors
	public static final int PARAM = 100;
	public static final int PARAM_API_KEY = 101;
	public static final int PARAM_SESSION_KEY = 102;
	public static final int PARAM_CALL_ID = 103;
	public static final int PARAM_SIGNATURE = 104;
	public static final int PARAM_TOO_MANY = 105;
	public static final int PARAM_USER_ID = 110;
	public static final int PARAM_USER_FIELD = 111;
	public static final int PARAM_SOCIAL_FIELD = 112;
	public static final int PARAM_EMAIL = 113;
	public static final int PARAM_USER_ID_LIST = 114;
	public static final int PARAM_FIELD_LIST = 115;
	public static final int PARAM_ALBUM_ID = 120;
	public static final int PARAM_PHOTO_ID = 121;
	public static final int PARAM_FEED_PRIORITY = 130;
	public static final int PARAM_CATEGORY = 140;
	public static final int PARAM_SUBCATEGORY = 141;
	public static final int PARAM_TITLE = 142;
	public static final int PARAM_DESCRIPTION = 143;
	public static final int PARAM_BAD_JSON = 144;
	public static final int PARAM_BAD_EID = 150;
	public static final int PARAM_UNKNOWN_CITY = 151;
	public static final int PARAM_BAD_PAGE_TYPE = 152;
	public static final int PARAM_BAD_LOCALE = 170;
	public static final int PARAM_BLOCKED_NOTIFICATION = 180;
	public static final int PARAM_ACCESS_TOKEN = 190;
	
	// User permission errors
	public static final int PERMISSION = 200;
	public static final int PERMISSION_USER = 210;
	public static final int PERMISSION_NO_DEVELOPERS = 211;
	public static final int PERMISSION_OFFLINE_ACCESS = 212;
	public static final int PERMISSION_ALBUM = 220;
	public static final int PERMISSION_PHOTO = 221;
	public static final int PERMISSION_MESSAGE = 230;
	public static final int PERMISSION_MARKUP_OTHER_USER = 240;
	public static final int PERMISSION_STATUS_UPDATE = 250;
	public static final int PERMISSION_PHOTO_UPLOAD = 260;
	public static final int PERMISSION_VIDEO_UPLOAD = 261;
	public static final int PERMISSION_SMS = 270;
	public static final int PERMISSION_CREATE_LISTING = 280;
	public static final int PERMISSION_CREATE_NOTE = 281;
	public static final int PERMISSION_SHARE_ITEM = 282;
	public static final int PERMISSION_EVENT = 290;
	public static final int PERMISSION_LARGE_FBML_TEMPLATE = 291;
	public static final int PERMISSION_LIVEMESSAGE = 292;
	public static final int PERMISSION_XMPP_LOGIN = 293;
	public static final int PERMISSION_ADS_MANAGEMENT = 294;
	public static final int PERMISSION_CREATE_EVENT = 296;
	public static final int PERMISSION_READ_MAILBOX = 298;
	public static final int PERMISSION_RSVP_EVENT = 299;
	
	// Data editing errors
	public static final int EDIT = 300;
	public static final int EDIT_USER_DATA = 310;
	public static final int EDIT_PHOTO = 320;
	public static final int EDIT_ALBUM_SIZE = 321;
	public static final int EDIT_PHOTO_TAG_SUBJECT = 322;
	public static final int EDIT_PHOTO_TAG_PHOTO = 323;
	public static final int EDIT_PHOTO_FILE = 324;
	public static final int EDIT_PHOTO_PENDING_LIMIT = 325;
	public static final int EDIT_PHOTO_TAG_LIMIT = 326;
	public static final int EDIT_ALBUM_REORDER_PHOTO_NOT_IN_ALBUM = 327;
	public static final int EDIT_ALBUM_REORDER_TOO_FEW_PHOTOS = 328;
	public static final int MALFORMED_MARKUP = 329;
	public static final int EDIT_MARKUP = 330;
	public static final int EDIT_FEED_TOO_MANY_USER_CALLS = 340;
	public static final int EDIT_FEED_TOO_MANY_USER_ACTION_CALLS = 341;
	public static final int EDIT_FEED_TITLE_LINK = 342;
	public static final int EDIT_FEED_TITLE_LENGTH = 343;
	public static final int EDIT_FEED_TITLE_NAME = 344;
	public static final int EDIT_FEED_TITLE_BLANK = 345;
	public static final int EDIT_FEED_BODY_LENGTH = 346;
	public static final int EDIT_FEED_PHOTO_SRC = 347;
	public static final int EDIT_FEED_PHOTO_LINK = 348;
	public static final int EDIT_VIDEO_SIZE = 350;
	public static final int EDIT_VIDEO_INVALID_FILE = 351;
	public static final int EDIT_VIDEO_INVALID_TYPE = 352;
	public static final int EDIT_VIDEO_FILE = 353;
	public static final int EDIT_VIDEO_NOT_TAGGED = 354;
	public static final int EDIT_VIDEO_ALREADY_TAGGED = 355;
	public static final int EDIT_FEED_TITLE_ARRAY = 360;
	public static final int EDIT_FEED_TITLE_PARAMS = 361;
	public static final int EDIT_FEED_BODY_ARRAY = 362;
	public static final int EDIT_FEED_BODY_PARAMS = 363;
	public static final int EDIT_FEED_PHOTO = 364;
	public static final int EDIT_FEED_TEMPLATE = 365;
	public static final int EDIT_FEED_TARGET = 366;
	public static final int EDIT_FEED_MARKUP = 367;
	public static final int EDIT_FEED_BLOCKED = 368;
	public static final int USERS_CREATE_INVALID_EMAIL = 370;
	public static final int USERS_CREATE_EXISTING_EMAIL = 371;
	public static final int USERS_CREATE_BIRTHDAY = 372;
	public static final int USERS_CREATE_PASSWORD = 373;
	public static final int USERS_REGISTER_INVALID_CREDENTIAL = 374;
	public static final int USERS_REGISTER_CONF_FAILURE = 375;
	public static final int USERS_REGISTER_EXISTING = 376;
	public static final int USERS_REGISTER_DEFAULT_ERROR = 377;
	public static final int USERS_REGISTER_PASSWORD_BLANK = 378;
	public static final int USERS_REGISTER_PASSWORD_INVALID_CHARS = 379;
	public static final int USERS_REGISTER_PASSWORD_SHORT = 380;
	public static final int USERS_REGISTER_PASSWORD_WEAK = 381;
	public static final int USERS_REGISTER_USERNAME_ERROR = 382;
	public static final int USERS_REGISTER_MISSING_INPUT = 383;
	public static final int USERS_REGISTER_INCOMPLETE_BDAY = 384;
	public static final int USERS_REGISTER_INVALID_EMAIL = 385;
	public static final int USERS_REGISTER_EMAIL_DISABLED = 386;
	public static final int USERS_REGISTER_ADD_USER_FAILED = 387;
	public static final int USERS_REGISTER_NO_GENDER = 388;
	
	// Authentication errors
	public static final int AUTH_EMAIL = 400;
	public static final int AUTH_LOGIN = 401;
	public static final int AUTH_SIG = 402;
	public static final int AUTH_TIME = 403;
	
	// Session errors
	public static final int SESSION_TIMED_OUT = 450;
	public static final int SESSION_METHOD = 451;
	public static final int SESSION_KEY_INVALID = 452;
	public static final int SESSION_REQUIRED = 453;
	public static final int SESSION_REQUIRED_FOR_SECRET = 454;
	public static final int SESSION_CANNOT_USE_SESSION_SECRET = 455;
	
	// authorization subcodes
	public static final int SESSION_USER_HAS_NOT_AUTHORIZED = 458;
	public static final int SESSION_USER_CHECKPOINTED = 459;
	public static final int SESSION_DOES_NOT_MATCH_STORED_SESSION = 460;
	public static final int SESSION_INVALID = 461;
	public static final int SESSION_STALE_VERSION = 462;
	public static final int SESSION_EXPIRED = 463;
	public static final int SESSION_NOT_CONFIRMED = 464;
	public static final int SESSION_USER_INVALID = 465;
	public static final int SESSION_INVALIDATED_VIA_API = 466;
	public static final int SESSION_USER_LOGGED_OUT = 467;
	public static final int SESSION_USER_MIA = 468;
	
	// Application messaging errors
	public static final int MESG_BANNED = 500;
	public static final int MESG_NO_BODY = 501;
	public static final int MESG_TOO_LONG = 502;
	public static final int MESG_RATE = 503;
	public static final int MESG_INVALID_THREAD = 504;
	public static final int MESG_INVALID_RECIP = 505;
	public static final int MESG_DUPLICATE = 506;
	public static final int POKE_INVALID_RECIP = 510;
	public static final int POKE_OUTSTANDING = 511;
	public static final int POKE_RATE = 512;
	public static final int POKE_USER_BLOCKED = 513;
	
	// Ref errors
	public static final int REF_SET_FAILED = 700;
	
	// Application integration errors
	public static final int FB_APP_UNKNOWN_ERROR = 750;
	public static final int FB_APP_FETCH_FAILED = 751;
	public static final int FB_APP_NO_DATA = 752;
	public static final int FB_APP_NO_PERMISSIONS = 753;
	public static final int FB_APP_TAG_MISSING = 754;
	public static final int FB_APP_DB_FAILURE = 755;
	
	// Data store API errors
	public static final int DATA_UNKNOWN_ERROR = 800;
	public static final int DATA_INVALID_OPERATION = 801;
	public static final int DATA_QUOTA_EXCEEDED = 802;
	public static final int DATA_OBJECT_NOT_FOUND = 803;
	public static final int DATA_OBJECT_ALREADY_EXISTS = 804;
	public static final int DATA_DATABASE_ERROR = 805;
	public static final int DATA_CREATE_TEMPLATE_ERROR = 806;
	public static final int DATA_TEMPLATE_EXISTS_ERROR = 807;
	public static final int DATA_TEMPLATE_HANDLE_TOO_LONG = 808;
	public static final int DATA_TEMPLATE_HANDLE_ALREADY_IN_USE = 809;
	public static final int DATA_TOO_MANY_TEMPLATE_BUNDLES = 810;
	public static final int DATA_MALFORMED_ACTION_LINK = 811;
	public static final int DATA_TEMPLATE_USES_RESERVED_TOKEN = 812;
	
	// Mobile/SMS errors
	public static final int SMS_INVALID_SESSION = 850;
	public static final int SMS_MSG_LEN = 851;
	public static final int SMS_USER_QUOTA = 852;
	public static final int SMS_USER_ASLEEP = 853;
	public static final int SMS_APP_QUOTA = 854;
	public static final int SMS_NOT_REGISTERED = 855;
	public static final int SMS_NOTIFICATIONS_OFF = 856;
	public static final int SMS_CARRIER_DISABLE = 857;
	
	// Application information errors
	public static final int NO_SUCH_APP = 900;
	
	// Batch API errors
	public static final int BATCH_ALREADY_STARTED = 951;
	public static final int BATCH_NOT_STARTED = 952;
	public static final int BATCH_METHOD_NOT_ALLOWED_IN_BATCH_MODE = 953;
	
	// Events API errors
	public static final int EVENT_INVALID_TIME = 1000;
	public static final int EVENT_NAME_LOCKED = 1001;
	
	// Info section errors
	public static final int INFO_NO_INFORMATION = 1050;
	public static final int INFO_SET_FAILED = 1051;
	
	// LiveMessage errors
	public static final int LIVEMESSAGE_SEND_FAILED = 1100;
	public static final int LIVEMESSAGE_EVENT_NAME_TOO_LONG = 1101;
	public static final int LIVEMESSAGE_MESSAGE_TOO_LONG = 1102;
	
	// Credits errors
	public static final int PAYMENTS_UNKNOWN = 1150;
	public static final int PAYMENTS_APP_INVALID = 1151;
	public static final int PAYMENTS_DATABASE = 1152;
	public static final int PAYMENTS_PERMISSION_DENIED = 1153;
	public static final int PAYMENTS_APP_NO_RESPONSE = 1154;
	public static final int PAYMENTS_APP_ERROR_RESPONSE = 1155;
	public static final int PAYMENTS_INVALID_ORDER = 1156;
	public static final int PAYMENTS_INVALID_PARAM = 1157;
	public static final int PAYMENTS_INVALID_OPERATION = 1158;
	public static final int PAYMENTS_PAYMENT_FAILED = 1159;
	public static final int PAYMENTS_DISABLED = 1160;
	public static final int PAYMENTS_INSUFFICIENT_BALANCE = 1161;
	public static final int PAYMENTS_EXCEED_CREDIT_BALANCE_LIMIT = 1162;
	public static final int PAYMENTS_EXCEED_CREDIT_DAILY_PURCHASE_LIMIT = 1163;
	public static final int PAYMENTS_EXCEED_CREDIT_DAILY_SPEND_LIMIT = 1164;
	public static final int PAYMENTS_INVALID_FUNDING_AMOUNT = 1166;
	public static final int PAYMENTS_NON_REFUNDABLE_PAYMENT_METHOD = 1167;
	public static final int PAYMENTS_USER_THROTTLED = 1168;
	public static final int PAYMENTS_LOGIN_REQUIRED = 1169;
	public static final int APP_INFO_FETCH_FAILURE = 1170;
	public static final int INVALID_APP_INFO = 1171;
	public static final int PAYMENTS_APP_INSUFFICIENT_BALANCE = 1172;
	
	// Chat errors
	public static final int CHAT_SEND_FAILED = 1200;
	
	// Facebook page errors
	public static final int PAGES_CREATE = 1201;
	
	// Facebook links errors
	public static final int SHARE_BAD_URL = 1500;
	
	// Facebook Notes errors
	public static final int NOTE_CANNOT_MODIFY = 1600;
	
	// Comment errors
	public static final int COMMENTS_UNKNOWN = 1700;
	public static final int COMMENTS_POST_TOO_LONG = 1701;
	public static final int COMMENTS_DB_DOWN = 1702;
	public static final int COMMENTS_INVALID_XID = 1703;
	public static final int COMMENTS_INVALID_UID = 1704;
	public static final int COMMENTS_INVALID_POST = 1705;
	public static final int COMMENTS_INVALID_REMOVE = 1706;
	
	// Path errors
	public static final int PATH_UNKNOWN = 2500;
	
	// Test user errors
	public static final int TEST_ACCOUNTS_INVALID_ID = 2901;
	public static final int TEST_ACCOUNTS_CANT_REMOVE_APP = 2902;
	public static final int TEST_ACCOUNTS_CANT_DELETE = 2903;
	
	public static boolean isGeneralError(int code) {
		return code < 100;
	}
	
	public static boolean isParameterError(int code) {
		return code >= 100 && code < 200;
	}
	
	public static boolean isUserPermissionError(int code) {
		return code >= 200 && code < 300;
	}
	
	public static boolean isDataEditingError(int code) {
		return code >= 300 && code < 400;
	}
	
	public static boolean isAuthencationError(int code) {
		return code >= 400 && code < 450;
	}
	
	public static boolean isSessionError(int code) {
		return code >= 450 && code < 500;
	}
	
	public static boolean isApplicationMessagingError(int code) {
		return code >=500 && code < 550;
	}
	
	public static boolean isFQLError(int code) {
		return code >= 600 && code < 617;
	}
	
	public static boolean isRefError(int code) {
		return code >=700 && code < 750;
	}
	
	public static boolean isApplicationIntegrationError(int code) {
		return code >= 750 && code < 800;
	}
	
	public static boolean isDataStoreApiError(int code) {
		return code >= 800 && code < 850;
	}
	
	public static boolean isMobileSMSError(int code) {
		return code >= 850 && code < 900;
	}
	
	public static boolean isApplicationInformationError(int code) {
		return code >= 900 && code < 950;
	}
	
	public static boolean isBatchApiError(int code) {
		return code >= 950 && code < 1000;
	}
	
	public static boolean isEventsApiError(int code) {
		return code >= 1000 && code < 1050;
	}
	
	public static boolean isInfoSectionError(int code) {
		return code >= 1050 && code < 1100;
	}
	
	public static boolean isLiveMessageError(int code) {
		return code >= 1100 && code < 1150;
	}
	
	public static boolean isCreditsError(int code) {
		return code >= 1150 && code < 1200;
	}
	
	public static boolean isChatError(int code) {
		return code == 1200;
	}
	
	public static boolean isPageError(int code) {
		return code == 1201;
	}
	
	public static boolean isLinksError(int code) {
		return code == 1500;
	}
	
	public static boolean isNotesError(int code) {
		return code == 1600;
	}
	
	public static boolean isCommentError(int code) {
		return code >= 1700 && code < 1750;
	}
	
	public static boolean isTestUserError(int code) {
		return (code >= 2900 && code <= 2903) || (code >= 3000 && code <= 3403);
	}
}
