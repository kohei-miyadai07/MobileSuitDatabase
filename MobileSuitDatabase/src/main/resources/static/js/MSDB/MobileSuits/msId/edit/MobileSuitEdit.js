document.addEventListener("DOMContentLoaded", function () {
    // フォーム入力値が変更されたときにバリデーションを実行する
    const form = document.getElementById("msEditForm");
  
    const msNameInput = document.getElementById("msName");
    const modelNumberInput = document.getElementById("modelNumber");
    const headHeightInput = document.getElementById("headHeight");
    const overallHeightInput = document.getElementById("overallHeight");
    const weightInput = document.getElementById("weight");
    const totalWeightInput = document.getElementById("totalWeight");
    const powerSourceInput = document.getElementById("powerSource");
    const materialInput = document.getElementById("material");
    const effectiveSensorRadiusInput = document.getElementById(
      "effectiveSensorRadius"
    );
    const generatorOutputInput = document.getElementById("generatorOutput");
    const totalThrustersOutputInput = document.getElementById(
      "totalThrustersOutput"
    );
  
    form.addEventListener("input", function () {
      validateForm();
    });
  
    function validateForm() {
      // 初期化
      clearErrors();
  
      const msName = msNameInput.value.trim();
      const modelNumber = modelNumberInput.value.trim();
      const headHeight = headHeightInput.value.trim();
      const overallHeight = overallHeightInput.value.trim();
      const weight = weightInput.value.trim();
      const totalWeight = totalWeightInput.value.trim();
      const powerSource = powerSourceInput.value.trim();
      const material = materialInput.value.trim();
      const effectiveSensorRadius = effectiveSensorRadiusInput.value.trim();
      const generatorOutput = generatorOutputInput.value.trim();
      const totalThrustersOutput = totalThrustersOutputInput.value.trim();
  
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
  
      // 型式番号のバリデーション
      if (modelNumber === "") {
        showError(
          modelNumberInput,
          "modelNumberError",
          "型式番号は必須入力です。"
        );
        hasError = true;
      }
  
      if (modelNumber.length > 300) {
        showError(
          modelNumberInput,
          "modelNumberError",
          "型式番号は300文字以下で入力して下さい。"
        );
        hasError = true;
      }
  
      // 頭高頂のバリデーション
      if (!/^(\d{1,3}(\.\d{1,2})?)?$/.test(headHeight)) {
        showError(
          headHeightInput,
          "headHeightError",
          "頭高頂は整数3桁以下、小数点以下2桁までの数値を入力してください。"
        );
        hasError = true;
      }
  
      // 全高のバリデーション
      if (!/^(\d{1,3}(\.\d{1,2})?)?$/.test(overallHeight)) {
        showError(
          overallHeightInput,
          "overallHeightError",
          "全高は整数3桁以下、小数点以下2桁までの数値を入力してください。"
        );
        hasError = true;
      }
  
      // 本体重量のバリデーション
      if (!/^(\d{1,3}(\.\d{1,2})?)?$/.test(weight)) {
        showError(
          weightInput,
          "weightError",
          "本体重量は整数3桁以下、小数点以下2桁までの数値を入力してください。"
        );
        hasError = true;
      }
  
      // 全備重量のバリデーション
      if (!/^(\d{1,3}(\.\d{1,2})?)?$/.test(totalWeight)) {
        showError(
          totalWeightInput,
          "totalWeightError",
          "全備重量は整数3桁以下、小数点以下2桁までの数値を入力してください。"
        );
        hasError = true;
      }
  
      // 動力源のバリデーション
      if (powerSource.length > 300) {
        showError(
          powerSourceInput,
          "powerSourceError",
          "動力源は300文字以下で入力して下さい。"
        );
        hasError = true;
      }
  
      // 装甲材質のバリデーション
      if (material.length > 300) {
        showError(
          materialInput,
          "materialError",
          "装甲材質は300文字以下で入力して下さい。"
        );
        hasError = true;
      }
  
      // センサー有効半径のバリデーション
      if (isNaN(effectiveSensorRadius)) {
        showError(
          effectiveSensorRadiusInput,
          "effectiveSensorRadiusError",
          "センサー有効半径は数値を入力してください。"
        );
        hasError = true;
      }
  
      // ジェネレーター出力のバリデーション
      if (isNaN(generatorOutput)) {
        showError(
          generatorOutputInput,
          "generatorOutputError",
          "ジェネレーター出力は数値を入力してください。"
        );
        hasError = true;
      }
  
      // スラスター総推力のバリデーション
      if (isNaN(totalThrustersOutput)) {
        showError(
          totalThrustersOutputInput,
          "totalThrustersOutputError",
          "スラスター総推力は数値を入力してください。"
        );
        hasError = true;
      }
  
      // 検索ボタンの活性/非活性を設定
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
  