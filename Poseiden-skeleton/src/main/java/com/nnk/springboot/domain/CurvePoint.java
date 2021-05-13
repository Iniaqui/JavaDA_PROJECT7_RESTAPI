package com.nnk.springboot.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
/**
 *  Constitutes the entity that refers to the name of the table in the database  
 * @author maure
 *
 */

@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // TODO: Map columns in data table CURVEPOINT with corresponding java fields
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer id;
	@Min(value=1 ,message="The minimal value is 1")
	Integer curveId;
	Timestamp asOfDate;
	@Min(value=1 ,message="The minimal value is 1")
	Double term;
	@Min(value=1 ,message="The minimal value is 1")
	Double value;
	Timestamp creationDate;
	public CurvePoint() {
		
	}
	
	public CurvePoint(int curveId,double value) {
		this.curveId= curveId;
		this.value = value;
	}
	public CurvePoint(Integer id, Integer curveId, Timestamp asOfDate, Double term, Double value,
			Timestamp creationDate) {
		super();
		this.id = id;
		this.curveId = curveId;
		this.asOfDate = asOfDate;
		this.term = term;
		this.value = value;
		this.creationDate = creationDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCurveId() {
		return curveId;
	}
	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}
	public Timestamp getAsOfDate() {
		return asOfDate;
	}
	public void setAsOfDate(Timestamp asOfDate) {
		this.asOfDate = asOfDate;
	}
	public Double getTerm() {
		return term;
	}
	public void setTerm(Double term) {
		this.term = term;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Timestamp getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	@Override
	public String toString() {
		return "CurvePoint [id=" + id + ", curveId=" + curveId + ", asOfDate=" + asOfDate + ", term=" + term
				+ ", value=" + value + ", creationDate=" + creationDate + "]";
	}
	
}
