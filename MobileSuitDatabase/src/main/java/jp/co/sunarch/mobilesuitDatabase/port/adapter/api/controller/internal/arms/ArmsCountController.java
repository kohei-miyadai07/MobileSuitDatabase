package jp.co.sunarch.mobilesuitDatabase.port.adapter.api.controller.internal.arms;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.arms.ArmsQuery;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ArmsCountController {

	private final ArmsQuery armsQuery;

	@GetMapping("/api/internal/Arms/count")
	public ResponseEntity<ArmsCountModel> getArmsCount() {
		ArmsCountModel model = armsQuery.getArmsCount();

		return ResponseEntity.status(HttpStatus.OK).body(model);
	}
}
