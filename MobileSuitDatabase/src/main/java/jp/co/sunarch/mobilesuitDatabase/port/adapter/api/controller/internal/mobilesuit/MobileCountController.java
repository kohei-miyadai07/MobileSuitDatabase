package jp.co.sunarch.mobilesuitDatabase.port.adapter.api.controller.internal.mobilesuit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.MobileSuitQuery;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MobileCountController {

	private final MobileSuitQuery mobileSuitQuery;

	@GetMapping("/api/internal/MobileSuit/count")
	public ResponseEntity<MobileSuitCountModel> getMobileSuitCount() {
		MobileSuitCountModel model = mobileSuitQuery.getMobileSuitCount();

		return ResponseEntity.status(HttpStatus.OK).body(model);
	}
}
