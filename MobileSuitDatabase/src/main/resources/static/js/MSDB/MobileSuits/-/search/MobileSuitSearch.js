document.addEventListener("DOMContentLoaded", function () {
  // フォーム入力値が変更されたときにバリデーションを実行する
  const form = document.getElementById("msSearchForm");

  const modelNumberInput = document.getElementById("modelNumber");
  const msNameInput = document.getElementById("msName");
  const headHeightFromInput = document.getElementById("headHeightFrom");
  const headHeightToInput = document.getElementById("headHeightTo");
  const overallHeightFromInput = document.getElementById("overallHeightFrom");
  const overallHeightToInput = document.getElementById("overallHeightTo");
  const weightFromInput = document.getElementById("weightFrom");
  const weightToInput = document.getElementById("weightTo");
  const totalWeightFromInput = document.getElementById("totalWeightFrom");
  const totalWeightToInput = document.getElementById("totalWeightTo");
  const powerSourceInput = document.getElementById("powerSource");
  const materialInput = document.getElementById("material");
  const effectiveSensorRadiusFromInput = document.getElementById(
    "effectiveSensorRadiusFrom"
  );
  const effectiveSensorRadiusToInput = document.getElementById(
    "effectiveSensorRadiusTo"
  );
  const generatorOutputFromInput = document.getElementById(
    "generatorOutputFrom"
  );
  const generatorOutputToInput = document.getElementById("generatorOutputTo");
  const totalThrustersOutputFromInput = document.getElementById(
    "totalThrustersOutputFrom"
  );
  const totalThrustersOutputToInput = document.getElementById(
    "totalThrustersOutputTo"
  );

  form.addEventListener("input", function () {
    validateForm();
  });

  function validateForm() {
    // 初期化
    clearErrors();

    const modelNumber = modelNumberInput.value.trim();
    const msName = msNameInput.value.trim();
    const headHeightFrom = headHeightFromInput.value.trim();
    const headHeightTo = headHeightToInput.value.trim();
    const overallHeightFrom = overallHeightFromInput.value.trim();
    const overallHeightTo = overallHeightToInput.value.trim();
    const weightFrom = weightFromInput.value.trim();
    const weightTo = weightToInput.value.trim();
    const totalWeightFrom = totalWeightFromInput.value.trim();
    const totalWeightTo = totalWeightToInput.value.trim();
    const powerSource = powerSourceInput.value.trim();
    const material = materialInput.value.trim();
    const effectiveSensorRadiusFrom =
      effectiveSensorRadiusFromInput.value.trim();
    const effectiveSensorRadiusTo = effectiveSensorRadiusToInput.value.trim();
    const generatorOutputFrom = generatorOutputFromInput.value.trim();
    const generatorOutputTo = generatorOutputToInput.value.trim();
    const totalThrustersOutputFrom = totalThrustersOutputFromInput.value.trim();
    const totalThrustersOutputTo = totalThrustersOutputToInput.value.trim();

    let hasError = false;

    // 型式番号のバリデーション
    if (modelNumber.length > 300) {
      showError(
        modelNumberInput,
        "modelNumberError",
        "型式番号は300文字以下で入力してください。"
      );
      hasError = true;
    }

    // 機体名のバリデーション
    if (msName.length > 300) {
      showError(
        msNameInput,
        "msNameError",
        "機体名は300文字以下で入力してください。"
      );
      hasError = true;
    }

    // 頭高頂(From)のバリデーション
    if (!/^(\d{1,3}(\.\d{1,2})?)?$/.test(headHeightFrom)) {
      showError(
        headHeightFromInput,
        "headHeightFromError",
        "頭高頂(From)は整数3桁以下、小数点以下2桁までの数値を入力してください。"
      );
      hasError = true;
    }

    // 頭高頂(To)のバリデーション
    if (!/^(\d{1,3}(\.\d{1,2})?)?$/.test(headHeightTo)) {
      showError(
        headHeightToInput,
        "headHeightToError",
        "頭高頂(To)は整数3桁以下、小数点以下2桁までの数値を入力してください。"
      );
      hasError = true;
    }

    // 全高(From)のバリデーション
    if (!/^(\d{1,3}(\.\d{1,2})?)?$/.test(overallHeightFrom)) {
      showError(
        overallHeightFromInput,
        "overallHeightFromError",
        "全高(From)は整数3桁以下、小数点以下2桁までの数値を入力してください。"
      );
      hasError = true;
    }

    // 全高(To)のバリデーション
    if (!/^(\d{1,3}(\.\d{1,2})?)?$/.test(overallHeightTo)) {
      showError(
        overallHeightToInput,
        "overallHeightToError",
        "全高(To)は整数3桁以下、小数点以下2桁までの数値を入力してください。"
      );
      hasError = true;
    }

    // 本体重量(From)のバリデーション
    if (!/^(\d{1,3}(\.\d{1,2})?)?$/.test(weightFrom)) {
      showError(
        weightFromInput,
        "weightFromError",
        "本体重量(From)は整数3桁以下、小数点以下2桁までの数値を入力してください。"
      );
      hasError = true;
    }

    // 本体重量(To)のバリデーション
    if (!/^(\d{1,3}(\.\d{1,2})?)?$/.test(weightTo)) {
      showError(
        weightToInput,
        "weightToError",
        "本体重量(To)は整数3桁以下、小数点以下2桁までの数値を入力してください。"
      );
      hasError = true;
    }

    // 全備重量(From)のバリデーション
    if (!/^(\d{1,3}(\.\d{1,2})?)?$/.test(totalWeightFrom)) {
      showError(
        totalWeightFromInput,
        "totalWeightFromError",
        "全備重量(From)は整数3桁以下、小数点以下2桁までの数値を入力してください。"
      );
      hasError = true;
    }

    // 全備重量(To)のバリデーション
    if (!/^(\d{1,3}(\.\d{1,2})?)?$/.test(totalWeightTo)) {
      showError(
        totalWeightToInput,
        "totalWeightToError",
        "全備重量(To)は整数3桁以下、小数点以下2桁までの数値を入力してください。"
      );
      hasError = true;
    }

    // 動力源のバリデーション
    if (powerSource.length > 300) {
      showError(
        powerSourceInput,
        "powerSourceError",
        "動力源は300文字以下で入力してください。"
      );
      hasError = true;
    }

    // 装甲材質のバリデーション
    if (material.length > 300) {
      showError(
        materialInput,
        "materialError",
        "装甲材質は300文字以下で入力してください。"
      );
      hasError = true;
    }

    // センサー有効半径(From)のバリデーション
    if (isNaN(effectiveSensorRadiusFrom)) {
      showError(
        effectiveSensorRadiusFromInput,
        "effectiveSensorRadiusFromError",
        "センサー有効半径(From)は数値を入力してください。"
      );
      hasError = true;
    }

    // センサー有効半径(To)のバリデーション
    if (isNaN(effectiveSensorRadiusTo)) {
      showError(
        effectiveSensorRadiusToInput,
        "effectiveSensorRadiusToError",
        "センサー有効半径(To)は数値を入力してください。"
      );
      hasError = true;
    }

    // ジェネレーター出力(From)のバリデーション
    if (isNaN(generatorOutputFrom)) {
      showError(
        generatorOutputFromInput,
        "generatorOutputFromError",
        "ジェネレーター出力(From)は数値を入力してください。"
      );
      hasError = true;
    }

    // ジェネレーター出力(To)のバリデーション
    if (isNaN(generatorOutputTo)) {
      showError(
        generatorOutputToInput,
        "generatorOutputToError",
        "ジェネレーター出力(To)は数値を入力してください。"
      );
      hasError = true;
    }

    // スラスター総推力(From)のバリデーション
    if (isNaN(totalThrustersOutputFrom)) {
      showError(
        totalThrustersOutputFromInput,
        "totalThrustersOutputFromError",
        "スラスター総推力(From)は数値を入力してください。"
      );
      hasError = true;
    }

    // スラスター総推力(To)のバリデーション
    if (isNaN(totalThrustersOutputTo)) {
      showError(
        totalThrustersOutputToInput,
        "totalThrustersOutputToError",
        "スラスター総推力(To)は数値を入力してください。"
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
