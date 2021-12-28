function isTokenExist() {
    return localStorage.getItem('token') != null;
}

async function isAuth() {
    if (isTokenExist()) {
        let token = localStorage.getItem('token');
        await authorizedUser(token);
        return true;
    }
    return false;
}

async function sendData(data) {
    return await fetch("/emailForm", {
        method: "POST",
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

async function emailform() {
    let mes = document.getElementById("message");
    var checkboxes = document.getElementsByName("employees");
    var checkboxesChecked = [];

    for (var i=0; i<checkboxes.length; i++) {
        console.log(checkboxes[i].value);
        console.log(typeof checkboxes[i].value);
        if (checkboxes[i].checked) {
            checkboxesChecked.push(checkboxes[i].value);
        }
    }
        let data = {employeeList: checkboxesChecked};
        let res = await sendData(data);
        if (res.ok) {
            window.location.replace(window.location.origin);
        } else {
            mes.innerHTML = "this user already exist";
        }
}