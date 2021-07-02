
public class Word 
{
	String key;
	Object data;
	public Word(String key, Object data) 
	{
		// TODO Auto-generated constructor stub
		this.key = key;
		this.data = data;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return data.toString();
	}
}
