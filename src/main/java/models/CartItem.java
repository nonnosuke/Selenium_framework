package models;

public class CartItem {
    private final String name;
    private final String price;
    private final int quantity;

    public CartItem(String name, String price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }

    public String getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    @Override
    public String toString(){
        return "CartItem{ " + "productName='" + name + '\'' + ", price='" + price + '\''
                + ", quantity=" + quantity + " }";
    }
}
