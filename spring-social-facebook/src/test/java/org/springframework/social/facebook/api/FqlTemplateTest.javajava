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

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.http.MediaType;

public class FqlTemplateTest extends AbstractFacebookApiTest {

	@Test
	public void query_basic() {
		mockServer.expect(requestTo("https://graph.facebook.com/fql?q=SELECT+uid%2C+status_id%2C+message%2C+time%2C+source+FROM+status+WHERE+uid%3Dme%28%29"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("fql-result-basic"), MediaType.APPLICATION_JSON));
		List<StatusObject> results = facebook.fqlOperations().query("SELECT uid, status_id, message, time, source FROM status WHERE uid=me()", new FqlResultMapper<StatusObject>() {
			public StatusObject mapObject(FqlResult result) {
				StatusObject status = new StatusObject();
				status.uid = result.getInteger("uid");
				status.statusId = result.getString("status_id");
				status.statusIdLong = result.getLong("status_id");
				status.message = result.getString("message");
				status.time = result.getTime("time");
				status.source = result.getLong("source");
				return status;
			}
		});
		
		assertEquals(3, results.size());
		StatusObject s1 = results.get(0);
		assertEquals(1234567890, s1.uid);
		assertEquals("10151137016185580", s1.statusId);
		assertEquals(10151137016185580L, s1.statusIdLong);
		assertEquals("Test Message 1", s1.message);
		assertEquals(1326503998000L, s1.time.getTime());
		assertEquals(6628568379L, s1.source);
		StatusObject s2 = results.get(1);
		assertEquals(1234567890, s2.uid);
		assertEquals("10150997723860580", s2.statusId);
		assertEquals(10150997723860580L, s2.statusIdLong);
		assertEquals("Test Message 2", s2.message);
		assertEquals(1323184190000L, s2.time.getTime());
		assertEquals(0, s2.source);
		StatusObject s3 = results.get(2);
		assertEquals(1234567890, s3.uid);
		assertEquals("10150958796185580", s3.statusId);
		assertEquals(10150958796185580L, s3.statusIdLong);
		assertEquals("Test Message 3", s3.message);
		assertEquals(null, s3.time);
		assertEquals(6628568379L, s3.source);
	}
	
	@Test
	public void query_basicWithFloat() {
		mockServer.expect(requestTo("https://graph.facebook.com/fql?q=SELECT+vid%2C+title%2C+length+FROM+video+WHERE+owner%3Dme%28%29"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("fql-result-basic-with-float"), MediaType.APPLICATION_JSON));
		List<VideoObject> results = facebook.fqlOperations().query("SELECT vid, title, length FROM video WHERE owner=me()", new FqlResultMapper<VideoObject>() {
			public VideoObject mapObject(FqlResult result) {
				VideoObject video = new VideoObject();
				video.videoId = result.getLong("vid");
				video.title = result.getString("title");
				video.length = result.getFloat("length");
				return video;
			}
		});
		
		assertEquals(3, results.size());
		VideoObject video1 = results.get(0);
		assertEquals(10151147608775580L, video1.videoId);
		assertEquals("Video 1", video1.title);
		assertEquals(20.62, video1.length, 0.00001);
		VideoObject video2 = results.get(1);
		assertEquals(10150902511815580L, video2.videoId);
		assertEquals("Video 2", video2.title);
		assertEquals(128.16, video2.length, 0.00001);
		VideoObject video3 = results.get(2);
		assertEquals(10150447297960580L, video3.videoId);
		assertEquals("Video 3", video3.title);
		assertEquals(31, video3.length, 0.0);
	}
	
	@Test
	public void query_resultsWithAnObjectField() {
		mockServer.expect(requestTo("https://graph.facebook.com/fql?q=select+app_id%2C+display_name%2C+restriction_info+from+application+where+app_id%3D162886103757745"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("fql-result-with-object-field"), MediaType.APPLICATION_JSON));
		List<RestrictionObject> restrictions = facebook.fqlOperations().query("select app_id, display_name, restriction_info from application where app_id=162886103757745", new FqlResultMapper<RestrictionObject>() {
			public RestrictionObject mapObject(FqlResult result) {
				RestrictionObject restriction = result.getObject("restriction_info", new FqlResultMapper<RestrictionObject>() {
					public RestrictionObject mapObject(FqlResult result) {
						RestrictionObject restriction = new RestrictionObject();
						restriction.type = result.getString("type");
						restriction.location = result.getString("location");
						restriction.age = result.getString("age");
						restriction.ageDistribution = result.getString("age_distribution");
						return restriction;
					}
				});
				return restriction;
			}
		});
		
		assertEquals(1, restrictions.size());
		RestrictionObject restriction = restrictions.get(0);
		assertEquals("alcohol", restriction.type);
		assertEquals("US", restriction.location);
		assertEquals("18", restriction.age);
		assertEquals("18+", restriction.ageDistribution);		
	}
	
	@Test
	public void query_resultsWithAnObjectArrayField() {
		mockServer.expect(requestTo("https://graph.facebook.com/fql?q=select+uid%2C+name%2C+family+from+user+where+uid%3Dme%28%29"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("fql-result-list-of-objects"), MediaType.APPLICATION_JSON));
		List<List<FamilyMemberObject>> queryResult = facebook.fqlOperations().query("select uid, name, family from user where uid=me()", new FqlResultMapper<List<FamilyMemberObject>>() {
			public List<FamilyMemberObject> mapObject(FqlResult result) {
				List<FamilyMemberObject> family = result.getList("family", new FqlResultMapper<FamilyMemberObject>() {
					public FamilyMemberObject mapObject(FqlResult result) {
						FamilyMemberObject familyMember = new FamilyMemberObject();
						familyMember.uid = result.getLong("uid");
						familyMember.relationship = result.getString("relationship");
						return familyMember;
					}
				});
				
				return family;
			}
		});
		
		List<FamilyMemberObject> listOfFamilyMembers = queryResult.get(0);
		assertEquals(4, listOfFamilyMembers.size());
		FamilyMemberObject member1 = listOfFamilyMembers.get(0);
		assertEquals(12345678901L, member1.uid);
		assertEquals("father", member1.relationship);
		FamilyMemberObject member2 = listOfFamilyMembers.get(1);
		assertEquals(12345678902L, member2.uid);
		assertEquals("mother", member2.relationship);
		FamilyMemberObject member3 = listOfFamilyMembers.get(2);
		assertEquals(12345678903L, member3.uid);
		assertEquals("sister", member3.relationship);
		FamilyMemberObject member4 = listOfFamilyMembers.get(3);
		assertEquals(12345678904L, member4.uid);
		assertEquals("sister", member4.relationship);
	}
	
	@Test
	public void nullChecks() {		
		mockServer.expect(requestTo("https://graph.facebook.com/fql?q=select+stuff+from+somewhere+where+uid%3Dme%28%29"))
			.andExpect(method(GET))
			.andExpect(header("Authorization", "OAuth someAccessToken"))
			.andRespond(withSuccess(jsonResource("fql-with-nulls"), MediaType.APPLICATION_JSON));

		facebook.fqlOperations().query("select stuff from somewhere where uid=me()", new FqlResultMapper<Object>() {
			public Object mapObject(FqlResult result) {
				assertNotNull(result.getString("string"));
				assertNull(result.getString("string_null"));
				assertNotNull(result.getInteger("number"));
				assertNull(result.getInteger("number_null"));
				assertNotNull(result.getLong("number"));
				assertNull(result.getLong("number_null"));
				assertNotNull(result.getFloat("float"));
				assertNull(result.getFloat("float_null"));
				assertNotNull(result.getBoolean("boolean"));
				assertNull(result.getBoolean("boolean_null"));
				assertNotNull(result.getTime("time"));
				assertNull(result.getTime("time_null"));
				assertNotNull(result.getList("list", new FqlResultMapper<String>() {
					public String mapObject(FqlResult objectValues) {
						return null;
					}
				}));
				assertNotNull(result.getList("list_empty", new FqlResultMapper<String>() {
					public String mapObject(FqlResult objectValues) {
						return null;
					}
				}));
				assertNull(result.getList("list_null", new FqlResultMapper<String>() {
					public String mapObject(FqlResult objectValues) {
						return null;
					}
				}));
				assertNotNull(result.getObject("object"));
				assertEquals("someValue", result.getObject("object", new FqlResultMapper<String>() { 
					public String mapObject(FqlResult objectValues) {
						return objectValues.getString("fieldA");
					} 
				}));
				assertNull(result.getObject("object_null"));
				assertNull(result.getObject("object_null", new FqlResultMapper<String>() { 
					public String mapObject(FqlResult objectValues) {
						fail("mapObjects() shouldn't be called for a null value");
						return null;
					} 
				}));
				return null;
			}
		});

		
	}

	private static class StatusObject {
		public int uid;
		public String statusId;
		public long statusIdLong;
		public String message;
		public Date time;
		public long source;
	}
	
	private static class VideoObject {
		public long videoId;
		public String title;
		public float length;
	}
	
	private static class FamilyMemberObject {
		public long uid;
		public String relationship;
	}
	
	private static class RestrictionObject {
		public String type;
		public String location;
		public String age;
		public String ageDistribution;
	}
	
}
