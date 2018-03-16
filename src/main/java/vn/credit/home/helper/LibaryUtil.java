/**
 * 
 */
package vn.credit.home.helper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import vn.credit.home.config.ext.ExtLdapUserDetails;
import vn.credit.home.util.param.Result;

/**
 * @author an.nd
 *
 */
public class LibaryUtil {

	/**
	 * @return
	 */
	public static void sendPost(String url) {

	}

	public static String GenerateNumber() {
		Random rand = new Random();
		int randomNum = rand.nextInt((1000 - 5000) + 1) + 5000;
		return String.valueOf(randomNum);
	}

	public static Date truncDate() {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(System.currentTimeMillis());
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		Date date = c.getTime();
		return date;
	}

	public static String SaveFileImport(MultipartFile fileTransferInput, ExtLdapUserDetails user)
			throws FileNotFoundException {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFTime = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");
		Date date = new Date();

		String filename = fileTransferInput.getOriginalFilename();
		// long filetype1 = fileTransferInput.getSize();

		String directory = "Upload/TransferCard/" + dateFormat.format(date).toString();

		if (!new File(directory).exists()) {
			File dir = new File(directory);
			dir.mkdirs();

		}

		/* Save lai file import */

		String filepath = Paths
				.get(directory, filename.replace(".xlsx",
						"_" + user.getUsername().toUpperCase() + "_" + dateFTime.format(date).toString() + ".xlsx"))
				.toString();

		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));

		try {
			stream.write(fileTransferInput.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return filepath;
	}

	public static String SaveFileImport(String Path, MultipartFile file) throws IOException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		ExtLdapUserDetails user = (ExtLdapUserDetails) auth.getPrincipal();

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFTime = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss");
		Date date = new Date();

		String filename = file.getOriginalFilename();
		// long filetype1 = fileTransferInput.getSize();
		String files = file.getContentType();

		String directory = Path + dateFormat.format(date).toString();// "D:/WebData/ICMS/TransferCard/"

		if (!new File(directory).exists()) {
			File dir = new File(directory);
			dir.mkdirs();

		}

		/* Save lai file import */
		String filepath = "";

		if (files.contains("image")) {
			filepath = Paths
					.get(directory, filename.replace(".jpg",
							"_" + user.getUsername().toUpperCase() + "_" + dateFTime.format(date).toString() + ".jpg"))
					.toString();
		} else {
			filepath = Paths
					.get(directory, filename.replace(".xlsx",
							"_" + user.getUsername().toUpperCase() + "_" + dateFTime.format(date).toString() + ".xlsx"))
					.toString();
		}

		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
		stream.write(file.getBytes());
		stream.close();

		try {
			stream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return filepath;
	}

	public static Result setError(int errorCode, String message) {
		Result result = new Result();
		result.setError(errorCode);
		result.setMessage(message);
		return result;
	}
}
