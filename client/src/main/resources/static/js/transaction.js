const button = document.getElementById("myButton");
const spinner = document.getElementById("loadingSpinner");
const data = document.getElementById("dataId");
function performAction() {
  button.classList.add("disabled");
  spinner.style.display = "inline-block";
  const id = data.innerText;

  const statusRadios = document.getElementsByName("radiostatus");
  let selectedValue = "";

  for (let i = 0; i < statusRadios.length; i++) {
    if (statusRadios[i].checked) {
      selectedValue = statusRadios[i].value;
      break;
    }
  }

  $.ajax({
    method: "PUT",
    url: "rest/transaction/" + id,
    dataType: "JSON",
    contentType: "application/json",
    beforeSend: addCsrfToken(),
    data: JSON.stringify({
      statusUpdate: selectedValue,
    }),
    success: (res) => {
      console.log("selesai");
      button.classList.remove("disabled");
      spinner.style.display = "none";
      window.location.href = "/admin/dasboard/transaction";
    },
  });
}
