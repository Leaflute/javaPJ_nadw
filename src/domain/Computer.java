package domain;

public class Computer implements Cloneable {
	
	private int serialNum;
	private String name;
	private int price;
	private int amount;
	
	// 생성자
	public Computer() {}
	public Computer(String name, int price, int amount) {
		this.serialNum = (int)(Math.random()*1000) + 1000;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}
	
	// getter setter
	public int getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	// toString
	@Override
	public String toString() {
		return serialNum + "\t" + name + "\t" + price + "\t" + amount;
	}	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
