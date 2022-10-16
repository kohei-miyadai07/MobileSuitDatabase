package jp.co.sunarch.mobilesuitDatabase.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileOperations {
	
	public static void updateImageFile(MultipartFile multipartFile, String path) throws IOException {
		Path filePath = Paths.get(path, multipartFile.getOriginalFilename());

		if (Files.notExists(filePath)) {
			byte[] bytes  = multipartFile.getBytes();
			OutputStream stream = Files.newOutputStream(filePath);

			stream.write(bytes);
		} else {
			throw new RuntimeException("登録対象のファイルが存在します。");
		}
	}

}
