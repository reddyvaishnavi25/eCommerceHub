package com.comp440.model;

public class Item {
	int itemId;
    String itemName;
	public Item(int itemId, String itemName) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
	}
	public int getItemId() {
		return itemId;
	}
	public String getItemName() {
		return itemName;
	}


}
