package com.jet2006.entity;

import java.text.DecimalFormat;

import com.jet2006.enumdefin.DownLoadEnum;

public class DownLoadData {

	private String name;
	private int filesize;
	private int completesize;
	private int status;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFilesize() {
		return filesize;
	}

	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}

	public int getCompletesize() {
		return completesize;
	}

	public void setCompletesize(int completesize) {
		this.completesize = completesize;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInfo() {
		DecimalFormat fnum = new DecimalFormat(".0");
		String prString = fnum
				.format((this.completesize * 100) / this.filesize) + "%";

		if (this.status == DownLoadEnum.Sleep) {
			return "�ȴ���" + prString;
		} else if (this.status == DownLoadEnum.Start) {
			return "������" + prString;
		} else if (this.status == DownLoadEnum.Pause) {
			return "����ͣ" + prString;
		} else if (this.status == DownLoadEnum.Complete) {
			return "�����" + prString;
		}
		return "";
	}

}
