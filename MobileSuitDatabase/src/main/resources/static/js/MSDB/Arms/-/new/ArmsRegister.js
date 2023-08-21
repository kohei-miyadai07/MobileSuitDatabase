document.addEventListener("DOMContentLoaded", function () {
  // フォーム入力値が変更されたときにバリデーションを実行する
  const form = document.getElementById("armsRegistForm");

  const armsNameInput = document.getElementById("armsName");

  form.addEventListener("input", function () {
    validateForm();
  });

  function validateForm() {
    // 初期化
    clearErrors();

    const armsName = armsNameInput.value.trim();

    let hasError = false;

    if (armsName === "") {
      showError(armsNameInput, "armsNameError", "武器名は必須入力です。");
      hasError = true;
    }

    if (armsName.length > 300) {
      showError(
        armsNameInput,
        "armsNameError",
        "武器名は300文字以下で入力して下さい。"
      );
      hasError = true;
    }

    // 登録ボタンの活性/非活性を設定
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
