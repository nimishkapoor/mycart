import java.util.*;
import java.io.Serializable;
public class User implements Serializable{
	private int uid;
	String uname;
	int amount=0;
	boolean isAdmin=false;
	
	Scanner in =new Scanner(System.in);
	ArrayList<Item> crt=new ArrayList<Item>();
	
	public void addUser(int uid)
	{
		this.uid=uid;
		System.out.println("Enter Name");
		this.uname=in.next();
		System.out.println("Want to an admin?");
		System.out.println("1: Yes");
		System.out.println("2: No");
		int temp=in.nextInt();
		if(temp==1)
		{
			System.out.println("Enter Admin Secure Pass");
			int pass=in.nextInt();
			if(pass==619)
			{
				isAdmin=true;
			}
		}
		System.out.println("Your User Id is "+this.uid);
	}
	
	public int getUid()
	{
		return uid;
	}
	public void addItem(Item i)
	{
		if(i.getItemCount()>0)
		{
			crt.add(i);
			amount+=i.getPrice();
			i.updateItemCount();
		}
		else
		{
			System.out.println("Out of Stock");
		}
	}
	public void getInfo()
	{
		System.out.println(this.uname+" "+this.uid+" "+isAdmin);
	}
	public void getCartInfo()
	{
		
	}
}
