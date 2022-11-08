package jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.MobileSuitRepository;
import jp.co.sunarch.mobilesuitDatabase.common.utils.FileOperations;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MobileSuitRecodeService {

	private final MobileSuitRepository mobileSuitRepository;
	
	public String registMobileSuit(MobileSuit mobileSuit) {
		String message = null;

		int result = mobileSuitRepository.save(mobileSuit);
		if (result != 1) {
			message = "登録処理に失敗しました。";
		} else {
			message = "登録処理に成功しました。";
		}

		return message;
	}

	public String updateMobileSuit(MobileSuit mobileSuit) {
		String message = null;

		int result = mobileSuitRepository.save(mobileSuit);
		if (result != 1) {
			message = "更新処理に失敗しました。";
		} else {
			message = "更新処理に成功しました。";
		}

		return message;
	}

	public int deleteMobileSuit(MobileSuit mobileSuit) {
		return mobileSuitRepository.deleteMobileSuitById(mobileSuit.getMsId().getValue());
	}

	public void uploadImageFile(MultipartFile multipartFile) {
		String filePath = "lib/images/";

		try {
			FileOperations.uploadImageFile(multipartFile, filePath);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.getMessage();
		}
	}

	public void updateImageFile(MultipartFile multipartFile) {
		String filePath = "lib/images/";

		try {
			FileOperations.updateImageFile(multipartFile, filePath);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RuntimeException e) {
			e.getMessage();
		}
	}

	public void deleteImageFile(String filePath) {

		try {
			FileOperations.deleteImageFile(filePath);
		} catch (RuntimeException e) {
			e.getMessage();
		}
		
	}

}
