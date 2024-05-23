const toastLiveExample = document.getElementById('liveToast');

function showAutoToast(message) {
    const toastBootstrap = new bootstrap.Toast(toastLiveExample);
    toastLiveExample.querySelector('.toast-body').textContent = message;
    toastBootstrap.show();
}
//var mess = '${sessionScope.mess}';
console.log(mess);
if (mess !== "") {
    showAutoToast(mess);
}



