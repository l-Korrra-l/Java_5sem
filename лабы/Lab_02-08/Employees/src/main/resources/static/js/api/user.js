//TODO
async function getAllUsers() {
    return await fetch("/users", {
        method: "POST",
    }).then(function (res) {
        return res.json();
    }).then(function (data) {
        return data;
    });
}

async function logUser(data) {
    return await fetch("/login", {
        method: "POST",
        body: JSON.stringify(data),
        headers: {
            'Content-Type': 'application/json'
        }
    });
}

async function regUser(data) {
    return  await fetch("/register", {
        method: "POST",
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(data)
    });
}

async function getUserByToken(token) {
    return await fetch(`/getUser`, {
        method: 'POST',
        headers: {
            'Authorisation': `Bearer ${token}`
        }
    });
}

async function authorizedUser(token) {
    return await fetch("/authorized", {
        method: "POST",
        headers: {
            'Authorization': `Bearer ${token}`
        }
    });
}

async function adminDeleteUser(id){
    let token = localStorage.getItem('token');
    await deleteEmailByUserId(id, token);
    await deleteUserById(id, token);
    alert("Deleted");
    window.location.replace("/index");

}
async function deleteUserById(id, token) {
    if(confirm('Are u sure?')){
        return await fetch(`/admin/deleteUserById/${id}`, {
            method: 'DELETE',
            headers: {
                'Authorization': `Bearer ${token}`,
                'content-type': 'application/json'
            },
        });}
}

async function deleteEmailByUserId(id, token) {
    return await fetch(`/admin/deleteEmailByUserId/${id}`, {
        method: 'DELETE',
        headers: {
            'Authorization': `Bearer ${token}`,
            'content-type': 'application/json'
        }
    });
}