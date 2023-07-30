document.addEventListener("DOMContentLoaded", function () {
  // フォーム入力値が変更されたときにバリデーションを実行する
  const form = document.getElementById("msRegisterForm");

  const msNameInput = document.getElementById("msName");

  form.addEventListener("input", function () {
    validateForm();
  });

  function validateForm() {
    // 初期化
    clearErrors();

    const msName = msNameInput.value.trim();

    let hasError = false;

    // 機体名のバリデーション
    if (msName === "") {
      showError(msNameInput, "msNameError", "機体名は必須入力です。");
      hasError = true;
    }

    if (msName.length > 300) {
      showError(
        msNameInput,
        "msNameError",
        "機体名は300文字以下で入力して下さい。"
      );
      hasError = true;
    }

    // 検索ボタンの活性/非活性を設定
    const button = document.getElementById("regist");
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
