
public abstract class User {
	
	private String teamName;
	private String permission;
	
	public User(String teamName){
		
		if(teamName.equals("administer")){//����
			permission = "������";
		}
		else
			permission = "�Ϲ�";		
	}
	
	public abstract boolean logIn();
	//���� ������ Ȯ���Ͽ�, �Ϲ������ϰ�� false, �������ϰ�� true ��ȯ
	//�α��� �Ҷ� ���� �Ǵ��Ҷ� �̿�.
	
	public String getTeamName(){
		return teamName;
	}
	
	public String getPermission(){
		return permission;
	}
}
