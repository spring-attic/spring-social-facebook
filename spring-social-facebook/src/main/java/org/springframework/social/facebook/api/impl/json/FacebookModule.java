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
package org.springframework.social.facebook.api.impl.json;

import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.Photo.Image;
import org.springframework.social.facebook.api.Video.VideoFormat;
import org.springframework.social.facebook.api.WorkEntry.Project;
import org.springframework.social.facebook.api.ads.*;
import org.springframework.social.facebook.api.ads.impl.json.*;
import org.springframework.social.facebook.api.impl.json.PhotoMixin.ImageMixin;
import org.springframework.social.facebook.api.impl.json.VideoMixin.VideoFormatMixin;
import org.springframework.social.facebook.api.impl.json.WorkEntryMixin.ProjectMixin;

import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Jackson module for setting up mixin annotations on Facebook model types. This enables the use of Jackson annotations without
 * directly annotating the model classes themselves.
 * @author Craig Walls
 */
public class FacebookModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public FacebookModule() {
		super("FacebookModule");
	}
	
	@Override
	public void setupModule(SetupContext context) {
		context.setMixInAnnotations(Achievement.class, AchievementMixin.class);
		context.setMixInAnnotations(AchievementType.class, AchievementTypeMixin.class);
		context.setMixInAnnotations(AchievementType.Image.class, AchievementTypeMixin.ImageMixin.class);
		context.setMixInAnnotations(Action.class, ActionMixin.class);
		context.setMixInAnnotations(Currency.class, CurrencyMixin.class);
		context.setMixInAnnotations(Device.class, DeviceMixin.class);
		context.setMixInAnnotations(ApplicationReference.class, ApplicationReferenceMixin.class);
		
		context.setMixInAnnotations(Place.class, PlaceMixin.class);
		
		context.setMixInAnnotations(ImageSource.class, ImageSourceMixin.class);
		
		context.setMixInAnnotations(Page.class, PageMixin.class);
		context.setMixInAnnotations(RestaurantServices.class, RestaurantServicesMixin.class);
		context.setMixInAnnotations(PageRestaurantSpecialties.class, RestaurantSpecialtiesMixin.class);
		context.setMixInAnnotations(PageParking.class, PageParkingMixin.class);
		context.setMixInAnnotations(PagePaymentOptions.class, PagePaymentOptionsMixin.class);
		context.setMixInAnnotations(PageRestaurantServices.class, PageRestaurantServicesMixin.class);
		context.setMixInAnnotations(Engagement.class, EngagementMixin.class);
		
		context.setMixInAnnotations(PostProperty.class, PostPropertyMixin.class);
		
		context.setMixInAnnotations(StoryAttachment.class, StoryAttachmentMixin.class);
		context.setMixInAnnotations(StoryAttachment.StoryAttachmentMedia.class, StoryAttachmentMixin.StoryAttachmentMediaMixin.class);
		context.setMixInAnnotations(StoryAttachment.StoryAttachmentTarget.class, StoryAttachmentMixin.StoryAttachmentTargetMixin.class);
		
		context.setMixInAnnotations(VideoFormat.class, VideoFormatMixin.class);
		
		context.setMixInAnnotations(Project.class, ProjectMixin.class);
		
		context.setMixInAnnotations(User.class, UserMixin.class);
		context.setMixInAnnotations(WorkEntry.class, WorkEntryMixin.class);
		context.setMixInAnnotations(EducationExperience.class, EducationExperienceMixin.class);
		context.setMixInAnnotations(Experience.class, ExperienceMixin.class);
		context.setMixInAnnotations(Reference.class, ReferenceMixin.class);
		context.setMixInAnnotations(GroupMemberReference.class, GroupMemberReferenceMixin.class);
		context.setMixInAnnotations(Album.class, AlbumMixin.class);
		context.setMixInAnnotations(Group.class, GroupMixin.class);
		context.setMixInAnnotations(Event.class, EventMixin.class);
		context.setMixInAnnotations(Invitation.class, InvitationMixin.class);
		context.setMixInAnnotations(EventInvitee.class, EventInviteeMixin.class);
		context.setMixInAnnotations(Location.class, LocationMixin.class);
		context.setMixInAnnotations(Comment.class, CommentMixin.class);
		context.setMixInAnnotations(PlaceTag.class, PlaceTagMixin.class);
		context.setMixInAnnotations(Tag.class, TagMixin.class);
		context.setMixInAnnotations(Video.class, VideoMixin.class);
		context.setMixInAnnotations(Photo.class, PhotoMixin.class);
		context.setMixInAnnotations(Image.class, ImageMixin.class);
		context.setMixInAnnotations(Post.class, PostMixin.class);
		context.setMixInAnnotations(Post.AdminCreator.class, PostMixin.AdminCreatorMixin.class);
		context.setMixInAnnotations(Post.Privacy.class, PostMixin.PrivacyMixin.class);
		context.setMixInAnnotations(Account.class, AccountMixin.class);
		context.setMixInAnnotations(GroupMembership.class, GroupMembershipMixin.class);
		context.setMixInAnnotations(FamilyMember.class, FamilyMemberMixin.class);
		context.setMixInAnnotations(MessageTag.class, MessageTagMixin.class);
		context.setMixInAnnotations(CoverPhoto.class, CoverPhotoMixin.class);
		
		context.setMixInAnnotations(FriendList.class, FriendListMixin.class);
		
		context.setMixInAnnotations(VoipInfo.class, VoipInfoMixin.class);
		
		context.setMixInAnnotations(MailingAddress.class, MailingAddressMixin.class);
		
		context.setMixInAnnotations(PaymentPricePoints.class, PaymentPricePointsMixin.class);
		context.setMixInAnnotations(PaymentPricePoint.class, PaymentPricePointMixin.class);
		
		context.setMixInAnnotations(SecuritySettings.class, SecuritySettingsMixin.class);
		context.setMixInAnnotations(SecuritySettings.SecureBrowsing.class, SecuritySettingsMixin.SecureBrowsingMixin.class);
		
		context.setMixInAnnotations(TestUser.class, TestUserMixin.class);
		
		context.setMixInAnnotations(UserIdForApp.class, UserIdForAppMixin.class);
		
		context.setMixInAnnotations(UserInvitableFriend.class, UserInvitableFriendMixin.class);
		context.setMixInAnnotations(UserTaggableFriend.class, UserTaggableFriendMixin.class);
		
		context.setMixInAnnotations(VideoUploadLimits.class, VideoUploadLimitsMixin.class);
		
		context.setMixInAnnotations(ProfilePictureSource.class, ProfilePictureSourceMixin.class);


		context.setMixInAnnotations(AdAccountGroup.class, AdAccountGroupMixin.class);
		context.setMixInAnnotations(AdAccount.AgencyClientDeclaration.class, AdAccountMixin.AgencyClientDeclarationMixin.class);
		context.setMixInAnnotations(AdUser.class, AdUserMixin.class);

		context.setMixInAnnotations(AdInsightAction.class, AdInsightActionMixin.class);
		context.setMixInAnnotations(AdInsight.class, AdInsightMixin.class);

		context.setMixInAnnotations(AdAccount.class, AdAccountMixin.class);
		context.setMixInAnnotations(AdCampaign.class, AdCampaignMixin.class);

		context.setMixInAnnotations(BidInfo.class, BidInfoMixin.class);
		context.setMixInAnnotations(Targeting.class, TargetingMixin.class);
		context.setMixInAnnotations(TargetingCityEntry.class, TargetingCityEntryMixin.class);
		context.setMixInAnnotations(TargetingEntry.class, TargetingEntryMixin.class);
		context.setMixInAnnotations(TargetingLocation.class, TargetingLocationMixin.class);
		context.setMixInAnnotations(AdSet.class, AdSetMixin.class);
	}
}
