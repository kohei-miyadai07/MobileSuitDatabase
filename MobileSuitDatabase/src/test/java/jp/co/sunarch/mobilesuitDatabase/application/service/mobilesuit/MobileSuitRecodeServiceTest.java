package jp.co.sunarch.mobilesuitDatabase.application.service.mobilesuit;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import jp.co.sunarch.mobilesuitDatabase.application.repository.mobilesuit.MobileSuitRepository;
import jp.co.sunarch.mobilesuitDatabase.common.utils.FileOperations;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuit;
import jp.co.sunarch.mobilesuitDatabase.domain.model.mobilesuit.MobileSuitId;

@ExtendWith(MockitoExtension.class)
class MobileSuitRecodeServiceTest {
	@InjectMocks
	MobileSuitRecodeService sut;

	@Mock
	MobileSuitRepository mobileSuitRepository;

	@Mock
	FileOperations fileOperations;

	@Test
	void モビルスーツ登録処理が呼び出されること() {
		doNothing().when(mobileSuitRepository).save(any());

		MobileSuit mobileSuit = MobileSuit.create(
				MobileSuitId.of("ms1"),
				"msNum1",
				"テストモビルスーツ1",
				"/ms/url1",
				new BigDecimal("18.55"),
				new BigDecimal("18.55"),
				new BigDecimal("18.55"),
				new BigDecimal("18.55"),
				"テストパワーソース1",
				"テストマテリアル1",
				100L,
				200L,
				300L,
				"テスト説明1",
				"テスト活躍1",
				Instant.ofEpochSecond(0),
				Instant.ofEpochSecond(0),
				Integer.valueOf(1));
		sut.registMobileSuit(mobileSuit);

		verify(mobileSuitRepository, times(1)).save(mobileSuit);
	}

	@Test
	void モビルスーツ更新処理が呼び出されること() {
		doNothing().when(mobileSuitRepository).save(any());

		MobileSuit mobileSuit = MobileSuit.create(
				MobileSuitId.of("ms1"),
				"msNum1",
				"テストモビルスーツ1",
				"/ms/url1",
				new BigDecimal("18.55"),
				new BigDecimal("18.55"),
				new BigDecimal("18.55"),
				new BigDecimal("18.55"),
				"テストパワーソース1",
				"テストマテリアル1",
				100L,
				200L,
				300L,
				"テスト説明1",
				"テスト活躍1",
				Instant.ofEpochSecond(0),
				Instant.ofEpochSecond(0),
				Integer.valueOf(2));
		sut.updateMobileSuit(mobileSuit);

		verify(mobileSuitRepository, times(1)).save(mobileSuit);
	}

	@Test
	void モビルスーツ削除処理が呼び出されること() {
		doNothing().when(mobileSuitRepository).deleteMobileSuitById(any());

		MobileSuit mobileSuit = MobileSuit.create(
				MobileSuitId.of("ms1"),
				"msNum1",
				"テストモビルスーツ1",
				"/ms/url1",
				new BigDecimal("18.55"),
				new BigDecimal("18.55"),
				new BigDecimal("18.55"),
				new BigDecimal("18.55"),
				"テストパワーソース1",
				"テストマテリアル1",
				100L,
				200L,
				300L,
				"テスト説明1",
				"テスト活躍1",
				Instant.ofEpochSecond(0),
				Instant.ofEpochSecond(0),
				Integer.valueOf(1));
		sut.deleteMobileSuit(mobileSuit);

		verify(mobileSuitRepository, times(1)).deleteMobileSuitById("ms1");
	}

	@Nested
	class UploadImageFile {
		@SuppressWarnings("static-access")
		void ファイルアップロード処理が呼び出されること() throws Exception {

			byte[] bArry = {10,20,30};
			MultipartFile mockMultipartFile = 
					new MockMultipartFile("test-name", "origin-test-name", "text/plain", bArry);

			doNothing().when(fileOperations).uploadImageFile(mockMultipartFile, "test/test");

			sut.uploadImageFile(mockMultipartFile);

			verify(fileOperations, times(1)).uploadImageFile(mockMultipartFile, "test/test");
		}
	}
}
