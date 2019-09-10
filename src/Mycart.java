import java.util.*;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.Serializable;

public class Mycart implements Serializable{
	public void writeUser(ArrayList<User> ud)
	{
		try
		{
			FileOutputStream f=new FileOutputStream("User.obj");
			ObjectOutputStream oos=new ObjectOutputStream(f);
			oos.writeObject(ud);
			f.close();
			oos.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public ArrayList<User> readUser()
	{
		ArrayList<User> ud=new ArrayList<User>();
		try
		{
			FileInputStream f=new FileInputStream("User.obj");
			ObjectInputStream ois =new ObjectInputStream(f);
			ArrayList<User> u=(ArrayList<User>) ois.readObject();
			for(User i:u)
			{
				ud.add(i);
			}
			f.close();
			ois.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return ud;
	}
	
	public void writeItem(ArrayList<Item> id)
	{
		try
		{
			FileOutputStream fos=new FileOutputStream("Item.obj");
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(id);
			fos.close();
			oos.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public ArrayList<Item> readItem()
	{
		ArrayList<Item> id=new ArrayList<Item>();
		try
		{
			FileInputStream fis=new FileInputStream("Item.obj");
			ObjectInputStream ois=new ObjectInputStream(fis);
			id=(ArrayList<Item>) ois.readObject();
			fis.close();
			ois.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return id;
	}
	
	public void writeUserId(int userid)
	{
		try
		{
			FileOutputStream fos=new FileOutputStream("UserId.txt");
			DataOutputStream dos=new DataOutputStream(fos);
			dos.writeInt(userid);
			fos.close();
			dos.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public void writeItemid(int itemid)
	{
		try
		{
			FileOutputStream fos=new FileOutputStream("ItemId.txt");
			DataOutputStream dos=new DataOutputStream(fos);
			dos.writeInt(itemid);
			fos.close();
			dos.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public int readUserId()
	{
		int x=0;
		try
		{
			FileInputStream fis=new FileInputStream("UserId.txt");
			DataInputStream dis=new DataInputStream(fis);
			x=dis.readInt();
			fis.close();
			dis.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return x;
	}
	public int readItemId()
	{
		int x=0;
		try
		{
			FileInputStream fis=new FileInputStream("ItemId.txt");
			DataInputStream dis=new DataInputStream(fis);
			x=dis.readInt();
			fis.close();
			dis.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return x;
	}
	
	public static void main(String[] args)
	{
		Mycart m=new Mycart();
	
		int userid=m.readUserId();
		int itemid=m.readItemId();
		
		
		ArrayList<User> ud =new ArrayList<User>();
		ArrayList<Item> id =new ArrayList<Item>();
		ud=m.readUser();
		id=m.readItem();
		
		System.out.println("Welcome!");
		
		Scanner in=new Scanner(System.in);
		
		int ch1=-1;
		while(ch1!=3){
			System.out.println("1. Login");
			System.out.println("2. Signup");
			System.out.println("3. Exit");
			ch1=in.nextInt();
			switch(ch1)
			{
				case 1:
					System.out.println("Enter user id");
					int uid=in.nextInt();
					User u=new User();
					u=null;
					for(User i:ud)
					{
						if(i.getUid()==uid)
						{
							u=i;
						}
					}
					if(u!=null)
					{
						System.out.println("Welcome "+u.uname);
						int ch2=-1;
						if(u.isAdmin==false)
						{
							while(ch2!=4)
							{
								System.out.println("1. BrowseItems");
								System.out.println("2. Checkout");
								System.out.println("3. BrowseCart");
								System.out.println("4. Logout");
								ch2=in.nextInt();
								switch(ch2)
								{
								case 1:
									for(Item i:id)
									{
										i.getInfo();
									}
									System.out.println("Selected Item Id or 0 for back.");
									int iid=in.nextInt();
									if(iid!=0 && iid<=itemid)
									{
										for(Item i:id)
										{
											if(i.getId()==iid)
											{
												u.addItem(i);
											}
										}
									}
									else if (iid >itemid)
									{
										System.out.println("Enter a valid id");
									}
									break;
								case 2:
									System.out.println("Amount paid:"+u.amount);
									u.amount=0;
									break;
								
								case 3:
									u.getCartInfo();
								}
							}
						}
						else
						{
							int ch3=-1;
							while(ch3!=4)
							{
								System.out.println("1.Add Item");
								System.out.println("2.Browse Item");
								System.out.println("3.Browse User");
								System.out.println("4.Logout");	
								ch3=in.nextInt();
								switch(ch3)
								{
								case 1:
									Item i1=new Item();
									String item_name;
									int price;
									int item_count;
									System.out.println("Enter Item name ");
									item_name=in.next();
									System.out.println("Enter Item price");
									price=in.nextInt();
									System.out.println("Enter Item count");
									item_count=in.nextInt();
									i1.addItem(item_name,price,++itemid,item_count);
									id.add(i1);
								    break;
								case 2:
									int iid;
									for(Item i:id)
									{
										i.getInfo();
									}
									System.out.println("Enter ID of Item to be modified or 0 for back");
									iid=in.nextInt();
									int temp;
									
									if(iid!=0)
									{
										System.out.println("1.update");
										System.out.println("2.delete");
										temp=in.nextInt();
										
									
										for(Item i:id)
										{
											if(i.getId()==iid)
											{
												if(temp==1)
												{
													i.update();
												}
												else
												{
													id.remove(i);
												}
												break;
											}
										}
									}
									
									break;
								case 3:
									for(User i:ud)
									{
										i.getInfo();
									}
									System.out.println("Enter Id of User to be deleted or 0 for back");
									int uid1=in.nextInt();
									if(uid1!=0)
									{
										for(User i:ud)
										{
											if(i.getUid()==uid1)
											{
												ud.remove(i);
											}
										}
										
									}
									break;
								}
							}
						}
					}
					else
					{
						System.out.println("User Don't Exist");
					}
					break;
				case 2:
					User nwusr=new User();
					nwusr.addUser(++userid);
					ud.add(nwusr);
					break;
			}
		}
		
		in.close();
		m.writeUser(ud);
		m.writeItem(id);
		m.writeUserId(userid);
		m.writeItemid(itemid);
		
	}
}