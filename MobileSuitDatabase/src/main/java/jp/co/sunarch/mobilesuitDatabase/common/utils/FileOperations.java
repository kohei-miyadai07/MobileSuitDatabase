package jp.co.sunarch.mobilesuitDatabase.common.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

public class FileOperations {
	
	public static void uploadImageFile(MultipartFile multipartFile, String path) throws IOException {
		Path filePath = Paths.get(path, multipartFile.getOriginalFilename());

		if (Files.notExists(filePath)) {
			byte[] bytes  = multipartFile.getBytes();
			OutputStream stream = Files.newOutputStream(filePath);

			stream.write(bytes);
		} else {
			throw new RuntimeException("登録対象のファイルが存在します。");
		}
	}

	public static void updateImageFile(MultipartFile multipartFile, String path) throws IOException {
		Path filePath = Paths.get(path, multipartFile.getOriginalFilename());

		if (Files.exists(filePath)) {
			new File(path + multipartFile.getOriginalFilename()).delete();

			byte[] bytes  = multipartFile.getBytes();
			OutputStream stream = Files.newOutputStream(filePath);

			stream.write(bytes);
		} else {
			byte[] bytes  = multipartFile.getBytes();
			OutputStream stream = Files.newOutputStream(filePath);

			stream.write(bytes);
		}
	}

	public static String getImage(String path) throws IOException {
		File file = new File(path);

		byte[] byteImg = Files.readAllBytes(file.toPath());

		return Base64.getEncoder().encodeToString(byteImg);
	}

}
