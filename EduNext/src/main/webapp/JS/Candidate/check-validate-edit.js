var today = new Date().toISOString().split('T')[0];
document.getElementById("dob").setAttribute("max", today);
function checkAll() {
    var requirement = true;
    var submitBtn = document.getElementById("submitBtn");
    var fullNameRegex = /^\S+\s+\S+/;
    var fullName = document.getElementById("fullName").value.trim();
    var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    var email = document.getElementById("email").value.trim();
    var address = document.getElementById("address").value.trim();
    var dob = document.getElementById("dob").value.trim();
    var phone = document.getElementById("phone").value.trim();
    var phoneRegex = /^(?:\+84|0|84)([1-9]{1})([0-9]{8})$/;
    var skill = document.getElementById("skill");
    var selectedSkill = document.getElementById("skill").selectedOptions.length;
    var yoe = document.getElementById("yoe").value;
    if (fullNameRegex.test(fullName)) {
        document.getElementById("fullNameAlert").style.color = "green";
    } else {
        requirement = false;
        document.getElementById("fullNameAlert").style.color = "#FF6B6B";
    }
    if (emailRegex.test(email)) {
        document.getElementById("emailAlert").style.color = "green";
    } else {
        requirement = false;
        document.getElementById("emailAlert").style.color = "#FF6B6B";
    }
    if (address !== '') {
        document.getElementById("addressAlert").style.color = "green";
    } else {
        requirement = false;
        document.getElementById("addressAlert").style.color = "#FF6B6B";
    }
    if (dob !== '') {
        document.getElementById("dobAlert").style.color = "green";
    } else {
        requirement = false;
        document.getElementById("dobAlert").style.color = "#FF6B6B";
    }
    if (phoneRegex.test(phone)) {
        document.getElementById("phoneAlert").style.color = "green";
    } else {
        requirement = false;
        document.getElementById("phoneAlert").style.color = "#FF6B6B";
    }
    if (selectedSkill > 0) {
        document.getElementById("skillAlert").style.color = "green";
        console.log("If selectedSkill.length: " + selectedSkill);
    } else {
        requirement = false;
        document.getElementById("skillAlert").style.color = "red";
    }
    if (yoe !== '' && yoe >= 1) {
        document.getElementById("yoeAlert").style.color = "green";
    } else {
        requirement = false;
        document.getElementById("yoeAlert").style.color = "#FF6B6B";
    }
    if (requirement) {
        submitBtn.style.backgroundColor = "green";
        submitBtn.disabled = false;
    } else {
        submitBtn.style.backgroundColor = "#9b9b9b";
        submitBtn.disabled = true;
    }

}


