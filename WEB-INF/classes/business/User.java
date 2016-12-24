package business;

import java.io.Serializable;

/**
 * 
 * @author mohan
 *
 */

public class User implements Serializable {

    private String username;
    private String email;
    private String type;
    private String password;
    private int numCoins;
    private int numPostedStudies;
    private int numParticipation;
    
    public User() { 
        username="";
        email="";
        type="";
        numCoins=0;
        numPostedStudies=0;
        numParticipation=0;
        password="";
    }
    
    public User(String name, String email, int coins,int participants,int participation) {
        this.username = name;
        this.email = email;
        this.numCoins = coins;
        this.numPostedStudies = participants;
        this.numParticipation = participation;
        //this.type = type;
        //this.password = password;
    }

    public String getName()
    {
        return username;
    }
    public void setName(String username)
    {
        this.username = username;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getNumPostedStudies() {
		return numPostedStudies;
	}

	public void setNumPostedStudies(int numPostedStudies) {
		this.numPostedStudies = numPostedStudies;
	}

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    
    
    public int getNumCoins()
    {
        return numCoins;
    }
     public void setNumCoins(int numCoins)
    {
        this.numCoins= numCoins;
    }
     
    public int getNumParticipation()
    {
        return numParticipation;
    }
     public void setNumParticipation(int numParticipation)
    {
        this.numParticipation= numParticipation;
    }

	@Override
	public String toString() {
		return "User [email=" + email +", type=" + type + ", numCoins=" + numCoins
				+ ", numPostedStudies=" + numPostedStudies + ", numParticipation=" + numParticipation + "]";
	}
}