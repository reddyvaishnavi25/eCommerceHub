package com.comp440.model;

public class ItemCategory {
private String itemName;
private String categoryName;
public ItemCategory(String itemName, String categoryName) {
	super();
	this.itemName = itemName;
	this.categoryName = categoryName;
}
public String getItemName() {
	return itemName;
}

public String getCategoryName() {
	return categoryName;
}

}
