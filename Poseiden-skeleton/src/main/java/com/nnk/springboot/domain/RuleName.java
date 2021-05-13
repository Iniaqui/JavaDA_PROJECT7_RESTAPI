package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.persistence.Id;
/**
*  Constitutes the entity that refers to the name of the table in the database 
* @author maure
*
*/

@Entity
@Table(name = "rulename")
public class RuleName {
    // TODO: Map columns in data table RULENAME with corresponding java fields
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name ;
	private String description;
	private String json;
	private String sqlStr;
	private String sqlPart;
	
	public RuleName() {
		this(0,"unknow","unknow","unknow","unknow","unknow");
	}
	
	public RuleName(String ruleName) {
		this.name = ruleName;
	}
	public RuleName(int id, String name, String description, String json, String sqlStr, String sqlPart) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.json = json;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "RuleName [id=" + id + ", name=" + name + ", description=" + description + ", json=" + json + ", sqlStr="
				+ sqlStr + ", sqlPart=" + sqlPart + "]";
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getSqlStr() {
		return sqlStr;
	}
	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}
	public String getSqlPart() {
		return sqlPart;
	}
	public void setSqlPart(String sqlPart) {
		this.sqlPart = sqlPart;
	}
	
}
