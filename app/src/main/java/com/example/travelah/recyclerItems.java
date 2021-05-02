package com.example.travelah;

public class recyclerItems {

    private String itemName;
    private int icon;

    public recyclerItems() {

    }

    public recyclerItems(String item_name, int _icon) {
        itemName = item_name;
        icon = _icon;
    }

    //Getter
    public String getItemName() {
        return itemName;
    }

    public  int getIcon() {
        return icon;
    }

    //Setter
    public void setFeaturedName(String featuredName) {
        this.itemName = featuredName;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
