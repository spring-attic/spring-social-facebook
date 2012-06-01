package org.springframework.social.facebook.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Model class representing insights output
 * @author Tomasz Wójtowicz
 */
public class Insights
{
	private String id;
	private String name;
	private String period;
	private List<Values> valuesList;

	public static class Values
	{
		private HashMap <String, Integer> value;
		private String end_time;
		
		@Override
		public String toString()
		{
			String str;
			str = "\n" + value.toString() + "\n";
			str += "End time: " + getEnd_time();
			return str;
		}
		
		public void putValue(String key, int value)
		{
			this.value.put(key, value);
		}
		
		public Values()
		{
			this.value = new HashMap<String, Integer>();
			this.end_time = "";
		}
		
		public Values(String end_time)
		{
			this.end_time = end_time;
		}
		
		public Values(HashMap<String, Integer> value, String end_time)
		{
			this.value = value;
			this.end_time = end_time;
		}
		
		public HashMap <String, Integer> getValue() {
			return value;
		}
		
		public void setValue(HashMap <String, Integer> value) {
			this.value = value;
		}
		
		public String getEnd_time() {
			return end_time;
		}
		
		public void setEnd_time(String end_time) {
			this.end_time = end_time;
		}
	}
	
	private String title;
	private String description;
	
	public void addValues(Values values)
	{
		this.valuesList.add(values);
	}
	
	@Override
	public String toString()
	{
		String str;
		str =  "ID: " + getId() + "\n";
		str += "Name: " + getName() + "\n";
		str += "Values:" + valuesList.toString() + "\n";
		str += "Title: " + getTitle() + "\n";
		str += "Description: " + description + "\n";
		return str;
	}
	
	public Insights()
	{
		this.valuesList = new ArrayList<Values>();
	}
	
	public Insights (String id, String name, String period, List<Values> valuesList, String title, String description)
	{
		this.setId(id);
		this.setName(name);
		this.setPeriod(period);
		this.setTitle(title);
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPeriod() {
		return period;
	}

	public List<Values> getValuesList() {
		return valuesList;
	}

	public void setValuesList(List<Values> valuesList) {
		this.valuesList = valuesList;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
