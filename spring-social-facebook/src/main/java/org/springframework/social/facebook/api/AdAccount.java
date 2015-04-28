package org.springframework.social.facebook.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Model class representing an ad account.
 *
 * @author Sebastian Górecki
 */
public class AdAccount extends FacebookObject {
	private String id;
	private List<AdAccountGroup> accountGroups;
	private long accountId;
	private AccountStatus status;
	private int age;
	private AgencyClientDeclaration agencyClientDeclaration;
	private String amountSpent;
	private String balance;
	private String businessCity;
	private String businessCountryCode;
	private String businessName;
	private String businessState;
	private String businessStreet;
	private String businessStreet2;
	private String businessZip;
	private List<Capabilities> capabilities;
	private Date createdTime;
	private String currency;
	private String dailySpendLimit;
	private long endAdvertiser;
	private String fundingSource;
	private Map<String, Object> fundingSourceDetails;
	private int isPersonal;
	private long mediaAgency;
	private String name;
	private boolean offsitePixelsTOSAccepted;
	private long partner;
	private String spendCap;
	private int timezoneId;
	private String timezoneName;
	private int timezoneOffsetHoursUTC;
	private Map<String, Integer> tosAccepted;
	private List<AdUser> users;
	private TaxStatus taxStatus;

	public String getId() {
		return id;
	}

	public List<AdAccountGroup> getAccountGroups() {
		return accountGroups;
	}

	public long getAccountId() {
		return accountId;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public int getAge() {
		return age;
	}

	public AgencyClientDeclaration getAgencyClientDeclaration() {
		return agencyClientDeclaration;
	}

	public String getAmountSpent() {
		return amountSpent;
	}

	public String getBalance() {
		return balance;
	}

	public String getBusinessCity() {
		return businessCity;
	}

	public String getBusinessCountryCode() {
		return businessCountryCode;
	}

	public String getBusinessName() {
		return businessName;
	}

	public String getBusinessState() {
		return businessState;
	}

	public String getBusinessStreet() {
		return businessStreet;
	}

	public String getBusinessStreet2() {
		return businessStreet2;
	}

	public String getBusinessZip() {
		return businessZip;
	}

	public List<Capabilities> getCapabilities() {
		return capabilities;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public String getCurrency() {
		return currency;
	}

	public String getDailySpendLimit() {
		return dailySpendLimit;
	}

	public long getEndAdvertiser() {
		return endAdvertiser;
	}

	public String getFundingSource() {
		return fundingSource;
	}

	public Map<String, Object> getFundingSourceDetails() {
		return fundingSourceDetails;
	}

	public int getIsPersonal() {
		return isPersonal;
	}

	public long getMediaAgency() {
		return mediaAgency;
	}

	public String getName() {
		return name;
	}

	public boolean isOffsitePixelsTOSAccepted() {
		return offsitePixelsTOSAccepted;
	}

	public long getPartner() {
		return partner;
	}

	public String getSpendCap() {
		return spendCap;
	}

	public int getTimezoneId() {
		return timezoneId;
	}

	public String getTimezoneName() {
		return timezoneName;
	}

	public int getTimezoneOffsetHoursUTC() {
		return timezoneOffsetHoursUTC;
	}

	public Map<String, Integer> getTosAccepted() {
		return tosAccepted;
	}

	public List<AdUser> getUsers() {
		return users;
	}

	public TaxStatus getTaxStatus() {
		return taxStatus;
	}

	public enum AccountStatus {
		ACTIVE, DISABLED, UNSETTLED, PENDING_REVIEW, IN_GRACE_PERIOD, TEMPORARILY_UNAVAILABLE, PENDING_CLOSURE, UNKNOWN
	}

	public enum Capabilities {
		BULK_ACCOUNT, CAN_CREATE_LOOKALIKES_WITH_CUSTOM_RATIO, CAN_USE_CONVERSION_LOOKALIKES, CAN_USE_MOBILE_EXTERNAL_PAGE_TYPE_FOR_LPP,
		CAN_USE_REACH_AND_FREQUENCY, CUSTOM_CLUSTER_SHARING, DIRECT_SALES, HAS_AD_SET_TARGETING, HAS_AVAILABLE_PAYMENT_METHODS,
		HOLDOUT_VIEW_TAGS, MOBILE_ADVERTISER_ID_UPLOAD, MOBILE_APP_REENGAGEMENT_ADS, MOBILE_APP_VIDEO_ADS,
		NEKO_DESKTOP_CANVAS_APP_ADS, NEW_CAMPAIGN_STRUCTURE, PREMIUM, VIEW_TAGS, PRORATED_BUDGET, OFFSITE_CONVERSION_HIGH_BID,
		CAN_USE_MOBILE_EXTERNAL_PAGE_TYPE, CAN_USE_OLD_AD_TYPES, CAN_USE_VIDEO_METRICS_BREAKDOWN, ADS_CF_INSTORE_DAILY_BUDGET,
		AD_SET_PROMOTED_OBJECT_APP, AD_SET_PROMOTED_OBJECT_OFFER, AD_SET_PROMOTED_OBJECT_PAGE, AD_SET_PROMOTED_OBJECT_PIXEL,
		CONNECTIONS_UI_V2, LOOKALIKE_AUDIENCE, CUSTOM_AUDIENCES_OPT_OUT_LINK, CUSTOM_AUDIENCES_FOLDERS, UNKNOWN
	}

	public enum TaxStatus {
		UNKNOWN, VAT_NOT_REQUIRED_US_CA, VAT_INFORMATION_REQUIRED, VAT_INFORMATION_SUBMITTED, OFFLINE_VAT_VALIDATION_FAILED,
		ACCOUNT_IS_PERSONAL_ACCOUNT
	}

	public class AgencyClientDeclaration {
		private int agencyRepresentingClient;
		private int clientBasedInFrance;
		private String clientCity;
		private String clientCountryCode;
		private String clientEmailAddress;
		private String clientName;
		private String clientPostalCode;
		private String clientProvince;
		private String clientStreet;
		private String clientStreet2;
		private int hasWrittenMandateFromAdvertiser;
		private int isClientPayingInvoices;

		public int getAgencyRepresentingClient() {
			return agencyRepresentingClient;
		}

		public int getClientBasedInFrance() {
			return clientBasedInFrance;
		}

		public String getClientCity() {
			return clientCity;
		}

		public String getClientCountryCode() {
			return clientCountryCode;
		}

		public String getClientEmailAddress() {
			return clientEmailAddress;
		}

		public String getClientName() {
			return clientName;
		}

		public String getClientPostalCode() {
			return clientPostalCode;
		}

		public String getClientProvince() {
			return clientProvince;
		}

		public String getClientStreet() {
			return clientStreet;
		}

		public String getClientStreet2() {
			return clientStreet2;
		}

		public int getHasWrittenMandateFromAdvertiser() {
			return hasWrittenMandateFromAdvertiser;
		}

		public int getIsClientPayingInvoices() {
			return isClientPayingInvoices;
		}
	}
}
