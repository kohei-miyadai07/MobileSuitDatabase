document.addEventListener("DOMContentLoaded", function () {
    // フォーム入力値が変更されたときにバリデーションを実行する
    const form = document.getElementById("equipmentEditForm");
  
    const numberEquipmentInput = document.getElementById("numberEquipment");
  
    form.addEventListener("input", function () {
      validateForm();
    });
  
    function validateForm() {
      // 初期化
      clearErrors();
  
      const numberEquipment = numberEquipmentInput.value.trim();
  
      let hasError = false;
  
      if (isNaN(numberEquipment)) {
        showError(
          numberEquipmentInput,
          "numberEquipmentError",
          "装備数は数値を入力してください。"
        );
        hasError = true;
      }
  
      // 更新ボタンの活性/非活性を設定
      const button = document.getElementById("update");
      button.disabled = hasError;
    }
  
    function showError(inputElement, errorElementId, message) {
      inputElement.classList.add("is-invalid");
      document.getElementById(errorElementId).textContent = message;
    }
  
    function clearErrors() {
      const invalidInputs = form.querySelectorAll(".is-invalid");
      invalidInputs.forEach((input) => input.classList.remove("is-invalid"));
      const errorElements = form.querySelectorAll(".invalid-feedback");
      errorElements.forEach((element) => (element.textContent = ""));
    }
  
    // 初期表示でバリデーションチェックを実行
    validateForm();
  });
  