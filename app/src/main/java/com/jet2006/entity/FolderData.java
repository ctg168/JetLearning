package com.jet2006.entity;

import java.util.HashMap;

public class FolderData {

	private int FolderId;
	private int ParentId;
	private String FolderName;
	private Boolean IsRoot;
	private HashMap<String, Object> Datas; 

	public static String[] filed() {
		return new String[] { "FolderId", "ParentId", "FolderName", "IsRoot" };
	}

	public int getFolderId() {
		return FolderId;
	}

	public void setFolderId(int folderId) {
		this.FolderId = folderId;
	}

	public int getParentId() {
		return ParentId;
	}

	public void setParentId(int parentId) {
		this.ParentId = parentId;
	}

	public String getFolderName() {
		return FolderName;
	}

	public void setFolderName(String folderName) {
		this.FolderName = folderName;
	}

	public Boolean getIsRoot() {
		return IsRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.IsRoot = isRoot;
	}

	public HashMap<String, Object> getDatas() {
		return Datas;
	}

	public void setDatas(HashMap<String, Object> datas) {
		Datas = datas;
	}
}
