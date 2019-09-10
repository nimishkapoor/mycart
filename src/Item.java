import java.util.*;
import java.io.Serializable;
public class Item implements Serializable{
	private String item_name;
	private int price;
	private int item_id;
	private int item_count;
	Scanner in=new Scanner(System.in);
	public void addItem(String item_name, int price, int item_id,int item_count)
	{
		this.item_name=item_name;
		this.price=price;
		this.item_id=item_id;
		this.item_count=item_count;
	}
	public void update()
	{
		System.out.println("Enter new price");
		int price=in.nextInt();
		this.price=price;
	}
	public void getInfo()
	{
		System.out.println(item_id+" : "+item_name+" Rs "+price+" "+item_count);
	}
	public int getId()
	{
		return item_id;
	}
	public int getPrice()
	{
		return price;
	}
	public int getItemCount()
	{
		return item_count;
	}
	public void updateItemCount()
	{
		this.item_count--;
	}
}