package nic.elastic.login.utility;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CaptchaImageGeneration extends HttpServlet 
{
	private static final Logger LOGGER=LoggerFactory.getLogger(CaptchaImageGeneration.class);
	private static final long serialVersionUID = 4175850577062220527L;
	public static final String FILE_TYPE = "jpeg";
	public static String captchaGen(HttpServletRequest request, HttpSession session)
	{
		String captchaStr="";
		captchaStr=CaptchaValueGeneration.generateCaptchaTextMethod(6);
		String imageString = "";
		int width=130;      
		int height=30;
		Color bg = new Color(255,255,255);
		Color fg = new Color(0,0,0);
		Font font = new Font("Arial", Font.BOLD, 20);
		BufferedImage cpimg =new BufferedImage(width,height,BufferedImage.OPAQUE);
		Graphics g = cpimg.createGraphics();
		g.setFont(font);
		g.setColor(bg);
		g.fillRect(0, 0, width, height);
		g.setColor(fg);
		g.drawString(captchaStr,10,25);   
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try 
		{
		ImageIO.write(cpimg, FILE_TYPE, bos);
		byte[] imageBytes = bos.toByteArray();
		imageString = Base64.getEncoder().encodeToString(imageBytes);
		if(request.getSession(true) != null)
		{
		session.setAttribute("genCaptcha",captchaStr);
		LOGGER.info("CAPTCHA VALUE IN SESSION - "+session.getAttribute("genCaptcha"));
		}
		bos.close();
		} 
		catch (IOException e) 
		{
			LOGGER.debug("Invalid input output operations.");
		}
		return imageString;
	}
}