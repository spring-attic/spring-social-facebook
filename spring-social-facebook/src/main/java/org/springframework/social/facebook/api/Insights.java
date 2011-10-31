package org.springframework.social.facebook.api;

import java.util.Date;
import java.util.List;

public class Insights {
	private String id;
	private String name;
	private String period;
	private List<Value> values;
	private String description;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public List<Value> getValues() {
		return values;
	}

	public void setValues(List<Value> values) {
		this.values = values;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public class Value {
		private String value;
		private Date end_time;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public Date getEnd_time() {
			return end_time;
		}

		public void setEnd_time(Date end_time) {
			this.end_time = end_time;
		}
	}
}
