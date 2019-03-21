/*
 * Copyright 2016 the original author or authors.
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

import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * An object which represents a target operation.
 * Offers a builder-like way of targeting a post to a specific audition.
 * @author Jorge Vasquez
 */
public class Targeting {

    private Post.PrivacyType privacyType = Post.PrivacyType.CUSTOM;
    private String[] countries;
    private String[] locales;
    private String[] regions;
    private String[] cities;
    private Map<String, String> attributesMap = new HashMap<String, String>();


    public Targeting privacyType(Post.PrivacyType privacyType) {
        this.privacyType = privacyType;
        return this;
    }

    public Targeting countries(String... countries) {
        this.countries = countries;
        return this;
    }

    public Targeting locales(String[] locales) {
        this.locales = locales;
        return this;
    }

    public Targeting regions(String[] regions) {
        this.regions = regions;
        return this;
    }


    public Targeting cities(String[] cities) {
        this.cities = cities;
        return this;
    }

    /*
     * Help us adding attributes not specified in the class
     */
    public Targeting addAttributes(String attributeName, String attributeValue){
        attributesMap.put(attributeName,attributeValue);
        return this;
    }

    /*
     *Creating Json format with all the attributes set with values
     */
    @Override
    public String toString() {
        StringBuffer privacyBuffer = new StringBuffer();
        privacyBuffer.append("{'value': '").append(privacyType).append("'");

        if (privacyType == Post.PrivacyType.CUSTOM) {

            if (countries != null) {
                privacyBuffer.append(",'countries':'").append(StringUtils.arrayToCommaDelimitedString(countries)).append("'");
            }

            if (locales != null) {
                privacyBuffer.append(",'locales':'").append(StringUtils.arrayToCommaDelimitedString(locales)).append("'");
            }
            if (regions != null) {
                privacyBuffer.append(",'regions':'").append(StringUtils.arrayToCommaDelimitedString(regions)).append("'");
            }
            if (cities != null) {
                privacyBuffer.append(",'cities':'").append(StringUtils.arrayToCommaDelimitedString(cities)).append("'");
            }

        }
        if(!attributesMap.isEmpty()){

            for (Map.Entry<String, String > entry : attributesMap.entrySet()) {
                privacyBuffer.append(",'"+entry.getKey()+"':'" + entry.getValue() + "'");
            }
        }
        privacyBuffer.append("}");
        return privacyBuffer.toString();
    }

}