/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

public class MailClass {
    public static void sendMail(String body, String from,String sub,String to) {
		// TODO Auto-generated method stub
		String id = "nbadweb4@gmail.com"; // email of the sender
		String pass = "kantri4u"; // password of the sender's email
		GMailSender sender = new GMailSender(id, pass);
		
		try {
			sender.sendMail(sub + " " + from, body, "nbadweb4@gmail.com", to);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
