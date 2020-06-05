package managedBeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.Part;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import tn.esprit.Entity.Category;
import tn.esprit.Entity.Event;
import tn.esprit.Entity.User;
import tn.esprit.Services.EventService;
import tn.esprit.Services.UserMail;

@ManagedBean
@SessionScoped
public class EventBean {
	private int EventId;
	private String Name;
	private Category Category;
	private int Phone;
	private String DateEvent;
	private Date date;
	private String Debut;
	private Date deb;
	private String Fin;
	private Date fi;
	private String Description;
	private static String Image;
	private long days, heure, minute, second;
	private static final String QR_CODE_IMAGE_PATH = "C:\\Users\\AR\\Desktop\\Ichrak\\JardinDenfant-PartieJEE-master\\KinderGarten-web\\src\\main\\webapp\\resources\\Uploads\\";
	private List<Event> Event;
	private static final long serialVersionUID = 1L;

	public EventService K = new EventService();
	@EJB
	UserMail mail = new UserMail();

	public int getEventId() {
		return EventId;
	}

	public void setEventId(int eventId) {
		EventId = eventId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Category getCategory() {
		return Category;
	}

	public void setCategory(Category category) {
		Category = category;
	}

	public int getPhone() {
		return Phone;
	}

	public void setPhone(int phone) {
		Phone = phone;
	}

	public String getDateEvent() {
		return DateEvent;
	}

	public void setDateEvent(String dateEvent) {
		DateEvent = dateEvent;
	}

	public String getDebut() {
		return Debut;
	}

	public void setDebut(String debut) {
		Debut = debut;
	}

	public String getFin() {
		return Fin;
	}

	public void setFin(String fin) {
		Fin = fin;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public static String getImage() {
		return Image;
	}

	public static void setImage(String image) {
		Image = image;
	}

	public List<Event> getEvent() {
		Event = K.GetAll();
		return Event;
	}

	public void setEvent(List<Event> event) {
		Event = event;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDeb() {
		return deb;
	}

	public void setDeb(Date deb) {
		this.deb = deb;
	}

	public Date getFi() {
		return fi;
	}

	public void setFi(Date fi) {
		this.fi = fi;
	}

	private Part logo;

	public Part getLogo() {
		return logo;
	}

	public void setLogo(Part logo) {
		this.logo = logo;
	}

	public long getDays() {
		return days;
	}

	public void setDays(long days) {
		this.days = days;
	}

	public long getHeure() {
		return heure;
	}

	public void setHeure(long heure) {
		this.heure = heure;
	}

	public long getMinute() {
		return minute;
	}

	public void setMinute(long minute) {
		this.minute = minute;
	}

	public long getSecond() {
		return second;
	}

	public void setSecond(long second) {
		this.second = second;
	}

	private String qrCode;

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	String qrCodePath;

	private void generateQRCode() {
		String filename = getSaltString() + ".png";
		qrCodePath = QR_CODE_IMAGE_PATH + filename;
		System.out.println(qrCodePath);
		this.setQrCode(filename);

	}

	private static void generateQRCodeImage(String text, int width, int height, String filePath)
			throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
		Path path = FileSystems.getDefault().getPath(filePath);
		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

	}

	private String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 30) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	public void sendMail(String destination, String qr) {
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "driftmoon134@gmail.com";
		String password = "barchanaf5/";

		// message info
		String mailTo = destination;
		String subject = "New Event";
		StringBuffer body = new StringBuffer("<html>Attention parents: A new Event has been created.Stay safe!<br>");
		body.append("Scan to show details:<br>");
		body.append("<img src=\"cid:image1\" width=\"30%\" height=\"30%\" /><br>");

		body.append("End of message.");
		body.append("</html>");

		// inline images
		Map<String, String> inlineImages = new HashMap<String, String>();
		inlineImages.put("image1",
				"C:\\Users\\AR\\Desktop\\Ichrak\\JardinDenfant-PartieJEE-master\\KinderGarten-web\\src\\main\\webapp\\resources\\Uploads\\"
						+ qr);

		try {
			UserMail.send(host, port, mailFrom, password, mailTo, subject, body.toString(), inlineImages);
			System.out.println("Email sent.");
		} catch (Exception ex) {
			System.out.println("Could not send email.");
			ex.printStackTrace();
		}

	}

	public void doUpload() {

		try {
			InputStream in = logo.getInputStream();
			Image = logo.getSubmittedFileName();

			File f = new File(
					"C:\\Users\\AR\\Desktop\\Ichrak\\JardinDenfant-PartieJEE-master\\KinderGarten-web\\src\\main\\webapp\\resources\\Uploads\\"
							+ logo.getSubmittedFileName());
			f.createNewFile();
			FileOutputStream out = new FileOutputStream(f);

			byte[] buffer = new byte[1024];
			int length;

			while ((length = in.read(buffer)) > 0) {
				out.write(buffer, 0, length);
			}

			out.close();
			in.close();

		} catch (Exception e) {
			e.printStackTrace(System.out);
		}
	}

	public String addEvent() {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		DateEvent = format.format(date);
		Debut = format.format(deb);
		Fin = format.format(fi);
		generateQRCode();

		K.Create(new Event(Name, Category, Phone, DateEvent, Debut, Fin, Description, Image, qrCode));
		Event newE = K.GetByQrCode(qrCode);
		try {
			generateQRCodeImage(
					"http://localhost:44389/Event/Details/" + newE.getEventId(), 350,
					350, qrCodePath);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (User user : K.GetAllUsersMail()) {
			sendMail(user.getEmail(), qrCode);
		}

		this.setName(null);
		this.setPhone(0);
		this.setDescription(null);
		this.setDate(null);
		this.setDeb(null);
		this.setFi(null);
		this.setCategory(null);
		this.setImage(null);

		return "/faces/Event/Index?faces-redirect=true";

	}

	public String modifier(Event e) {

		this.setEventId(e.getEventId());
		this.setName(e.getName());
		this.setPhone(e.getPhone());
		this.setDescription(e.getDescription());
		this.setDate(e.getDate());
		this.setDeb(e.getDeb());
		this.setFi(e.getFi());

		return "/faces/Event/Edit?faces-redirect=true";

	}

	public String PutKinder() throws InterruptedException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		DateEvent = format.format(date);
		Debut = format.format(deb);
		Fin = format.format(fi);

		K.Update(EventId, new Event(EventId, Name, Category, Phone, DateEvent, Debut, Fin, Description, Image));
		this.setName(null);
		this.setPhone(0);
		this.setDescription(null);
		this.setDate(null);
		this.setDeb(null);
		this.setFi(null);
		this.setCategory(null);
		this.setImage(null);

		return "/faces/Event/Index?faces-redirect=true";
	}

	public String supprimer(Event k) {
		K.Delete(k);
		return "/faces/Event/Index?faces-redirect=true";
	}

	private String LO;

	public String getLO() {
		return LO;
	}

	public void setLO(String lO) {
		LO = lO;
	}

	public String Details(Event e) {

		this.setEventId(e.getEventId());
		this.setName(e.getName());
		this.setPhone(e.getPhone());
		this.setDescription(e.getDescription());
		this.setDate(e.getDate());
		this.setDeb(e.getDeb());
		this.setFi(e.getFi());
		LO = e.getImage();

		Date now = Calendar.getInstance().getTime();

		long diff = date.getTime() - now.getTime();

		days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		diff = diff - days * 24 * 60 * 60 * 1000;

		heure = TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
		diff = diff - heure * 60 * 60 * 1000;

		minute = TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
		diff = diff - minute * 60 * 1000;

		second = TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);

		return "/faces/Event/Details?faces-redirect=true";

	}
}
