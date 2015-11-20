
public abstract class User {
	
	private String teamName;
	private String permission;
	
	public User(String teamName){
		
		if(teamName.equals("administer")){//권한
			permission = "관리자";
		}
		else
			permission = "일반";		
	}
	
	public abstract boolean logIn();
	//유저 권한을 확인하여, 일반유저일경우 false, 관리자일경우 true 반환
	//로그인 할때 권한 판단할때 이용.
	
	public String getTeamName(){
		return teamName;
	}
	
	public String getPermission(){
		return permission;
	}
}
