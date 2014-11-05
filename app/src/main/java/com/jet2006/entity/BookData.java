package com.jet2006.entity;

public class BookData {
	private String BookName;
	private String BookUrl;
	private Boolean IsWeb;

	public static String[] filed() {
		return new String[] { "BookName", "BookUrl", "IsWeb" };
	}

	public String getBookName() {
		return BookName;
	}

	public void setBookName(String bookName) {
		BookName = bookName;
	}

	public String getBookUrl() {
		return BookUrl;
	}

	public void setBookUrl(String bookUrl) {
		BookUrl = bookUrl;
	}

	public Boolean getIsWeb() {
		return IsWeb;
	}

	public void setIsWeb(Boolean isWeb) {
		IsWeb = isWeb;
	}

}
