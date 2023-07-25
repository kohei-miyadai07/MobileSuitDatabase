document.addEventListener("DOMContentLoaded", function () {
  // フォーム入力値が変更されたときにバリデーションを実行する
  const form = document.getElementById("msSearchForm");
  const modelNumberInput = document.getElementById("modelNumber");

  form.addEventListener("input", function () {
    validateForm();
  });

  function validateForm() {
    // 初期化
    clearErrors();

    const modelNumber = modelNumberInput.value.trim();

    let hasError = false;

    // 型式番号のバリデーション
    if (modelNumber === "") {
      showError(
        modelNumberInput,
        "modelNumberError",
        "型式番号は必須項目です。"
      );
      hasError = true;
    } else if (modelNumber.length > 50) {
      showError(
        modelNumberInput,
        "modelNumberError",
        "型式番号は50文字以下で入力してください。"
      );
      hasError = true;
    }

    // 検索ボタンの活性/非活性を設定
    const button = document.getElementById("search");
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
