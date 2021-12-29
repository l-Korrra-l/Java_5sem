//
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

//
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


//
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
//
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
}


async function adminDeleteEmployee(id){
    let token = localStorage.getItem('token');
    await deleteEmailByEmployeeId(id, token);
    await deleteEmployeeById(id, token);
    alert("Employee deleted");
    window.location.replace('/index');

}


async function deleteEmailByEmployeeId(id, token) {
    return await fetch(`/admin/deleteEmailByEmployeeId/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        }
    });
}

async function deleteEmployeeById(id, token) {
    if(confirm('Are u sure?')){
        return await fetch(`/admin/deleteEmployeeById/${id}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`,
                'content-type': 'application/json'
            },
        });}
}


