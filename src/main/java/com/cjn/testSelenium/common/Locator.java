package com.cjn.testSelenium.common;

public class Locator {

	public enum ByType{
		xpath, id, linkText, name, className, cssSelector, partialLinkText, tagName
	}
	
	private ByType byType;

	public ByType getByType() {
		return byType;
	}

	public void setByType(ByType byType) {
		this.byType = byType;
	}
}
