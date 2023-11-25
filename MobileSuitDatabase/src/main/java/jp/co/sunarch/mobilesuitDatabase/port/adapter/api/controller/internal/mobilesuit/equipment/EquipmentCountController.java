package jp.co.sunarch.mobilesuitDatabase.port.adapter.api.controller.internal.mobilesuit.equipment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jp.co.sunarch.mobilesuitDatabase.port.adapter.query.mobilesuit.equipment.EquipmentQuery;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EquipmentCountController {

	private final EquipmentQuery equipmentQuery;

	@GetMapping("/api/internal/MobileSuit/Equipment/count")
	public ResponseEntity<EquipmentCountModel> getEquipmentCount() {
		EquipmentCountModel model = equipmentQuery.getEquipmentCount();

		return ResponseEntity.status(HttpStatus.OK).body(model);
	}
}
