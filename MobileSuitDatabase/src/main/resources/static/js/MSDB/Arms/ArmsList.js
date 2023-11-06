window.addEventListener("load", () => {
    const resultMessage = document.getElementById("resultMessageView");
    const id = Math.random();

    // メッセージを埋め込む
    if (messageText) {
      resultMessage.insertAdjacentHTML(
        "beforeend",
        `<div id=message-${id} class="alert alert-primary alert-dismissible fade show" role="alert"><div>${messageText}</div><button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>`
      );

      // 5秒後にメッセージを削除する
      const targetId = `message-${id}`;
      setTimeout(() => {
        const messageAlert = document.getElementById(targetId);
        messageAlert.remove();
      }, 5000);
    }
  });