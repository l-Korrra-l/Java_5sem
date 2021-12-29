function validateLoginPass(login, password , email) {
    if (!(login.length >= 4 && login.length <= 16)) {
        return "not correct login";
    }
    if (!(password.length >= 4 && password.length <= 16)) {
        return "not correct password";
    }
    if (!email.length >= 4) {
        return "not correct email";
    }

    return true;
}

async function regUser(data) {
    return await fetch("/registration", {
        method: "POST",
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

async function reg() {
    let login = document.getElementById("login").value;
    let password = document.getElementById("password").value;
    let email = document.getElementById("email").value;
    let mes = document.getElementById("message");
    let sp = document.getElementById("sp");
    let result = validateLoginPass(login, password , email);
    if (result === true) {
        let data = {username: login, password: password,email:email};
        mes.innerHTML = "Wait...";
        sp.innerHTML = "";
        let res = await regUser(data);
        if (res.ok) {
            mes.innerHTML = "Confirmation letter has been sent to email";
        } else {
            mes.innerHTML = "this user already exist";
        }

    } else {
        mes.innerHTML = result;
    }
}