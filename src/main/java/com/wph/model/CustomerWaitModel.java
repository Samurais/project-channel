package com.wph.model;

import java.sql.Timestamp;
import java.util.Date;

public class CustomerWaitModel {
	//�����ֶ�
	private Integer ctId;
	private Double ctLevel;

	//������ֶ�

	private Timestamp beginWaited;//��ʼ�ȴ�ʱ��
	private Timestamp realtime;//ʵ��ʱ���ʱ��
	public Integer getCtId() {
		return ctId;
	}
	public void setCtId(Integer ctId) {
		this.ctId = ctId;
	}
	public Double getCtLevel() {
		return ctLevel;
	}
	public void setCtLevel(Double ctLevel) {
		this.ctLevel = ctLevel;
	}
	public Timestamp getBeginWaited() {
		return beginWaited;
	}
	public void setBeginWaited(Timestamp beginWaited) {
		this.beginWaited = beginWaited;
	}
	public Timestamp getRealtime() {
		return realtime;
	}
	public void setRealtime(Timestamp realtime) {
		this.realtime = realtime;
	}
	@Override
	public String toString() {
		return "Customer [ctId=" + ctId + ", ctLevel=" + ctLevel
				+ ", beginWaited=" + beginWaited + ", realtime=" + realtime
				+ "]";
	}
	
	
	
	
}
