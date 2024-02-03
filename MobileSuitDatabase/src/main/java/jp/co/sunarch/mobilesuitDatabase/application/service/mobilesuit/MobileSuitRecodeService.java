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
	
	public void registMobileSuit(MobileSuit mobileSuit) {
		mobileSuitRepository.save(mobileSuit);
	}

	public void updateMobileSuit(MobileSuit mobileSuit) {
		mobileSuitRepository.save(mobileSuit);
	}

	public void deleteMobileSuit(MobileSuit mobileSuit) {
		mobileSuitRepository.deleteMobileSuitById(mobileSuit.getMsId().getValue());
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
