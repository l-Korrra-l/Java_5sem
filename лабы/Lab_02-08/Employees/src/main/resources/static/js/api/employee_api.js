
async function getAllEmployees(token) {
    return await fetch("/user/getAllEmployees", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        }
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}

//TODO
//TODO /updateemployeeAdmin/
//TODO adminDeleteEmployee
//TODO adminDeleteUser
async function getAllEmployeesForAdmin(token) {
    return await fetch("/admin/getAllEmployees", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`,
        }
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}



async function getEmployeeById(id, token) {
    return await fetch(`/user/getEmployeeById/${id}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}

async function getEmployeeByIdForAdmin(id, token) {
    return await fetch(`/admin/getEmployeeById/${id}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json'
        }

    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}//TODO
async function userAddEmployee() {
    let info;
    let token=localStorage.getItem("token");
    fetch('/getUserInfo',{
        headers:{'Authorization': `Bearer ${token}`}
    })
        .then(result=>{
            if(result.ok){
                return result.json()
            }
        }).then(data=> {
        info = {
            name: data.name,
            surname: data.surname,
            email: data.email,
            subject: document.getElementById("subject").value,
            cost: document.getElementById("cost").value
        };
        createTutor(info);
        alert("Ваше объявление успешно обработано");
        window.location.replace(window.location.origin);
    });

}
//TODO
async function createEmployee(data) {
    let token=localStorage.getItem("token");
    return await fetch("/user/createTutor",{
        method :'POST',
        headers:{'Authorization': `Bearer ${token}`,
            'Content-Type':'application/json'},
        body:JSON.stringify(data)
    });
}




async function updateTutor() {
    let token=localStorage.getItem("token");
    return await fetch("/user/updateTutor", {
        method: 'PUT',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        },
        body: JSON.stringify({

        })

    });
}