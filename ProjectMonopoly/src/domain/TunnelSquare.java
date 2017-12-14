package domain;
public class TunnelSquare implements Square {
	
	private int id;
	private String name;
	
	public TunnelSquare(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	@Override
	public void getAction(Player p) {
		if(id==58) {
			p.setLocation(78);
		}else if(id==78){
			p.setLocation(58);
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getId() {
		return id;
	}

}