package jp.co.bungeejump.tokuban.entity.real;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 *
 * @author 末永
 * @version 0.0.1
 *
 */
@Entity
@Table(name = "t_nine")
public class TNine {
	private Integer userId;
	private Integer favCenterId;
	private Integer favHokkaidoId;
	private Integer favTohokuId;
	private Integer favKantoId;
	private Integer favChubuId;
	private Integer favKinkiId;
	private Integer favChugokuId;
	private Integer favShikokuId;
	private Integer favKyushuId;

	@Id
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getFavCenterId() {
		return favCenterId;
	}
	public void setFavCenterId(Integer favCenterId) {
		this.favCenterId = favCenterId;
	}
	public Integer getFavHokkaidoId() {
		return favHokkaidoId;
	}
	public void setFavHokkaidoId(Integer favHokkaidoId) {
		this.favHokkaidoId = favHokkaidoId;
	}
	public Integer getFavTohokuId() {
		return favTohokuId;
	}
	public void setFavTohokuId(Integer favTohokuId) {
		this.favTohokuId = favTohokuId;
	}
	public Integer getFavKantoId() {
		return favKantoId;
	}
	public void setFavKantoId(Integer favKantoId) {
		this.favKantoId = favKantoId;
	}
	public Integer getFavChubuId() {
		return favChubuId;
	}
	public void setFavChubuId(Integer favChubuId) {
		this.favChubuId = favChubuId;
	}
	public Integer getFavKinkiId() {
		return favKinkiId;
	}
	public void setFavKinkiId(Integer favKinkiId) {
		this.favKinkiId = favKinkiId;
	}
	public Integer getFavChugokuId() {
		return favChugokuId;
	}
	public void setFavChugokuId(Integer favChugokuId) {
		this.favChugokuId = favChugokuId;
	}
	public Integer getFavShikokuId() {
		return favShikokuId;
	}
	public void setFavShikokuId(Integer favShikokuId) {
		this.favShikokuId = favShikokuId;
	}
	public Integer getFavKyushuId() {
		return favKyushuId;
	}
	public void setFavKyushuId(Integer favKyushuId) {
		this.favKyushuId = favKyushuId;
	}


}
