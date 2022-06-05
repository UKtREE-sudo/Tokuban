package jp.co.bungeejump.tokuban.entity.real;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * t_fortune_specialtyのやつ
 * @author 小野
 * @version 0.0.1
 */
@Entity
public class TFortuneSpecialty {

	@Id
	@Temporal(TemporalType.DATE)
	private Date date;
	private Integer q1Id;
	private Integer q2Id;
	private Integer q3Id;
	@Column(columnDefinition = "Integer CHECK(fs1Id % 3 = 2)")
	private Integer fs1Id;
	@Column(columnDefinition = "Integer CHECK(fs2Id % 3 = 2)")
	private Integer fs2Id;
	@Column(columnDefinition = "Integer CHECK(fs3Id % 3 = 1)")
	private Integer fs3Id;
	@Column(columnDefinition = "Integer CHECK(fs4Id % 3 = 1)")
	private Integer fs4Id;
	@Column(columnDefinition = "Integer CHECK(fs5Id % 3 = 2)")
	private Integer fs5Id;
	@Column(columnDefinition = "Integer CHECK(fs6Id % 3 = 2)")
	private Integer fs6Id;
	@Column(columnDefinition = "Integer CHECK(fs7Id % 3 = 1)")
	private Integer fs7Id;
	@Column(columnDefinition = "Integer CHECK(fs8Id % 3 = 1)")
	private Integer fs8Id;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getQ1Id() {
		return q1Id;
	}
	public void setQ1Id(Integer q1Id) {
		this.q1Id = q1Id;
	}
	public Integer getQ2Id() {
		return q2Id;
	}
	public void setQ2Id(Integer q2Id) {
		this.q2Id = q2Id;
	}
	public Integer getQ3Id() {
		return q3Id;
	}
	public void setQ3Id(Integer q3Id) {
		this.q3Id = q3Id;
	}
	public Integer getFs1Id() {
		return fs1Id;
	}
	public void setFs1Id(Integer fs1Id) {
		this.fs1Id = fs1Id;
	}
	public Integer getFs2Id() {
		return fs2Id;
	}
	public void setFs2Id(Integer fs2Id) {
		this.fs2Id = fs2Id;
	}
	public Integer getFs3Id() {
		return fs3Id;
	}
	public void setFs3Id(Integer fs3Id) {
		this.fs3Id = fs3Id;
	}
	public Integer getFs4Id() {
		return fs4Id;
	}
	public void setFs4Id(Integer fs4Id) {
		this.fs4Id = fs4Id;
	}
	public Integer getFs5Id() {
		return fs5Id;
	}
	public void setFs5Id(Integer fs5Id) {
		this.fs5Id = fs5Id;
	}
	public Integer getFs6Id() {
		return fs6Id;
	}
	public void setFs6Id(Integer fs6Id) {
		this.fs6Id = fs6Id;
	}
	public Integer getFs7Id() {
		return fs7Id;
	}
	public void setFs7Id(Integer fs7Id) {
		this.fs7Id = fs7Id;
	}
	public Integer getFs8Id() {
		return fs8Id;
	}
	public void setFs8Id(Integer fs8Id) {
		this.fs8Id = fs8Id;
	}

}
