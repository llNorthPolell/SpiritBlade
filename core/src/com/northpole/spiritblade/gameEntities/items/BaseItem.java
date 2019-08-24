package com.northpole.spiritblade.gameEntities.items;

public abstract class BaseItem implements Item{
	private String itemIcon;
	
	private String itemName;
	private String itemDescription;
	
	private int itemQuantity;
	private int itemMaxStackQuantity;
	
	
	public BaseItem() {
		
	}
	
	public String getItemIcon() {
		return itemIcon;
	}


	public void setItemIcon(String itemIcon) {
		this.itemIcon = itemIcon;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemDescription() {
		return itemDescription;
	}


	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}


	public int getItemQuantity() {
		return itemQuantity;
	}


	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}


	public int getItemMaxStackQuantity() {
		return itemMaxStackQuantity;
	}


	public void setItemMaxStackQuantity(int itemMaxStackQuantity) {
		this.itemMaxStackQuantity = itemMaxStackQuantity;
	}



	
	
}
